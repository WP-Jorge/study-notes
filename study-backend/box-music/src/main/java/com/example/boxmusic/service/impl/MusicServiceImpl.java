package com.example.boxmusic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddMusicDTO;
import com.example.boxmusic.pojo.dto.UpdateMusicDTO;
import com.example.boxmusic.pojo.entity.*;
import com.example.boxmusic.mapper.MusicMapper;
import com.example.boxmusic.pojo.vo.CategoryMusicCountsVO;
import com.example.boxmusic.pojo.vo.DesMusicVO;
import com.example.boxmusic.pojo.vo.MusicLevelCountsVO;
import com.example.boxmusic.pojo.vo.MusicVO;
import com.example.boxmusic.service.FileService;
import com.example.boxmusic.service.MusicCategoryService;
import com.example.boxmusic.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.service.MusicSingerService;
import com.example.boxmusic.utils.FfmpegUtil;
import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {
	@Autowired
	private RestHighLevelClient client;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private FfmpegUtil ffmpegUtil;
	
	@Value("${tempPath}")
	private String tempPath;

	@Value("${basePath}")
	private String basePath;

	@Value("${musicPath}")
	private String musicPath;
	
	@Autowired
	private MusicCategoryService musicCategoryService;
	
	@Autowired
	private MusicSingerService musicSingerService;
	
	@Autowired
	private CachedUidGenerator cachedUidGenerator;
	
	@Override
	public R getMusicsByMusicTitlePage(Page<Map<String, Object>> page, String musicTitle) {
		IPage<MusicVO> musicPages = baseMapper.getMusicsByMusicTitlePage(page, musicTitle);
		return R.successPage("获取音乐成功", musicPages);
	}
	
	@Override
	public R deleteMusicsByMusicIds(List<Long> musicIds) {
		int count = baseMapper.deleteBatchIds(musicIds);
		if (count > 0) {
			return R.success("删除音乐成功");
		}
		return R.error("删除音乐失败");
	}
	
	@Override
	public R addMusic(MultipartFile song, AddMusicDTO addMusicDTO) {
		try {
			if (song == null) {
				return R.error("添加失败，请上传歌曲");
			}
			Music music = new Music();
			BeanUtils.copyProperties(addMusicDTO, music);
			long uid = cachedUidGenerator.getUID();
			String musicFilename = null;
			musicFilename = fileService.getFilename(song, uid);
			music.setMusicId(uid);
			music.setMusicUrl(musicFilename);
			Map musicInfo = ffmpegUtil.getMusicInfo(song, musicFilename);
			Map format = (Map) musicInfo.get("format");
			Double duration = Double.parseDouble(String.valueOf(format.get("duration"))) * 1000;
			Integer size = Integer.parseInt(String.valueOf(format.get("size")));
			Integer bitRate = Integer.parseInt(String.valueOf(format.get("bit_rate")));
			String formatName = (String) format.get("format_name");
			music.setDuration(duration);
			music.setSize(size);
			music.setLevel(ffmpegUtil.getMusicLevel(bitRate));
			music.setMusicFormat(formatName);
			music.setBitrate(bitRate);
			int i = baseMapper.insert(music);
			if (i <= 0) {
				throw new RuntimeException("添加歌曲失败");
			}
			List<MusicCategory> musicCategoryList = new ArrayList<>();
			List<MusicSinger> musicSingerList = new ArrayList<>();
			for (Category category : addMusicDTO.getCategoryList()) {
				MusicCategory musicCategory = new MusicCategory();
				musicCategory.setMusicId(music.getMusicId());
				musicCategory.setCategoryId(category.getCategoryId());
				musicCategoryList.add(musicCategory);
			}
			for (Singer singer : addMusicDTO.getSingerList()) {
				MusicSinger musicSinger = new MusicSinger();
				musicSinger.setMusicId(music.getMusicId());
				musicSinger.setSingerId(singer.getSingerId());
				musicSingerList.add(musicSinger);
			}
			boolean musicCategoryFlag = musicCategoryService.saveBatch(musicCategoryList);
			boolean musicSingerFlag = musicSingerService.saveBatch(musicSingerList);
			if (!musicCategoryFlag || !musicSingerFlag) {
				throw new RuntimeException("添加歌曲失败，添加歌曲分类、歌曲歌手时发生错误");
			}
			// ffmpegUtil.saveMusic(musicPath, musicFilename);
			Boolean moveFile = fileService.moveFile(musicFilename, basePath + tempPath, basePath + musicPath);
			if (!moveFile) {
				return R.error("添加歌曲失败");
			}
			return R.success("添加歌曲成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，音乐名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public R updateMusic(MultipartFile song, UpdateMusicDTO updateMusicDTO) {
		try {
			Music music = baseMapper.selectById(updateMusicDTO.getMusicId());
			BeanUtils.copyProperties(updateMusicDTO, music);
			String musicName = music.getMusicUrl();
			String musicFilename = null;
			if (song != null) {
				musicFilename = fileService.getFilename(song, updateMusicDTO.getMusicId());
				music.setMusicUrl(musicFilename);
				Map musicInfo = ffmpegUtil.getMusicInfo(song, musicFilename);
				Map format = (Map) musicInfo.get("format");
				Double duration = Double.parseDouble(String.valueOf(format.get("duration"))) * 1000;
				Integer size = Integer.parseInt(String.valueOf(format.get("size")));
				Integer bitRate = Integer.parseInt(String.valueOf(format.get("bit_rate")));
				String formatName = (String) format.get("format_name");
				music.setDuration(duration);
				music.setSize(size);
				music.setLevel(ffmpegUtil.getMusicLevel(bitRate));
				music.setMusicFormat(formatName);
				music.setBitrate(bitRate);
			}
			
			int i = baseMapper.updateById(music);
			if (i <= 0) {
				throw new RuntimeException("添加歌曲失败");
			}
			
			QueryWrapper<MusicCategory> musicCategoryQueryWrapper = new QueryWrapper<>();
			musicCategoryQueryWrapper.eq("music_id", music.getMusicId());
			boolean musicCategoryDeleted = musicCategoryService.remove(musicCategoryQueryWrapper);
			QueryWrapper<MusicSinger> musicSingerQueryWrapper = new QueryWrapper<>();
			musicSingerQueryWrapper.eq("music_id", music.getMusicId());
			boolean musicSingerDeleted = musicSingerService.remove(musicSingerQueryWrapper);
			
			if (!musicCategoryDeleted || !musicSingerDeleted) {
				throw new RuntimeException("删除音乐分类、音乐歌手时发生错误");
			}
			
			List<MusicCategory> musicCategoryList = new ArrayList<>();
			List<MusicSinger> musicSingerList = new ArrayList<>();
			for (Category category : updateMusicDTO.getCategoryList()) {
				MusicCategory musicCategory = new MusicCategory();
				musicCategory.setMusicId(music.getMusicId());
				musicCategory.setCategoryId(category.getCategoryId());
				musicCategoryList.add(musicCategory);
			}
			for (Singer singer : updateMusicDTO.getSingerList()) {
				MusicSinger musicSinger = new MusicSinger();
				musicSinger.setMusicId(music.getMusicId());
				musicSinger.setSingerId(singer.getSingerId());
				musicSingerList.add(musicSinger);
			}
			boolean musicCategoryFlag = musicCategoryService.saveBatch(musicCategoryList);
			boolean musicSingerFlag = musicSingerService.saveBatch(musicSingerList);
			if (!musicCategoryFlag || !musicSingerFlag) {
				throw new RuntimeException("添加歌曲失败，添加歌曲分类、歌曲歌手时发生错误");
			}
			if (song != null) {
				Boolean deleteImage = fileService.deleteFile(basePath + musicPath, musicName);
				if (!deleteImage) {
					log.warn("本地歌曲不存在");
				}
				// ffmpegUtil.saveMusic(musicPath, musicFilename);
				Boolean moveFile = fileService.moveFile(musicFilename, basePath + tempPath, basePath + musicPath);
				if (!moveFile) {
					return R.error("添加歌曲失败");
				}
			}
			return R.success("更新歌曲成功");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R getMusicsByTotalViewsSortPage(Page<Map<String, Object>> page) {
		IPage<MusicVO> musicPages = baseMapper.getMusicsByTotalViewsSortPage(page);
		return R.successPage("获取歌曲排行成功", musicPages);
	}
	
	@Override
	public R getMusicsByCreateTimeSortPage(Page<Map<String, Object>> page) {
		IPage<MusicVO> musicPages = baseMapper.getMusicsByCreateTimeSortPage(page);
		return R.successPage("获取最近音乐成功", musicPages);
	}
	
	@Override
	public R getMusicsByCategoryIdPage(Page<Map<String, Object>> page, Long categoryId) {
		IPage<MusicVO> musicPages = baseMapper.getMusicsByCategoryIdPage(page, categoryId);
		return R.successPage("获取歌曲成功", musicPages);
	}
	
	@Override
	public R getMusicsByPlaylistIdPage(Page<Map<String, Object>> page, Long playlistId) {
		return R.successPage("获取音乐成功", baseMapper.getMusicsByPlaylistIdPage(page, playlistId));
	}
	
	@Override
	public R getMusicsByAlbumIdPage(Page<Map<String, Object>> page, Long albumId) {
		return R.successPage("获取音乐成功", baseMapper.getMusicsByAlbumIdPage(page, albumId));
	}
	
	@Override
	public R getMusicsByKeyword(String keyword) throws IOException {
		// 创建请求
		SearchSourceBuilder builder = new SearchSourceBuilder()
				.query(QueryBuilders.multiMatchQuery(keyword, "music_title", "singer_name", "lyric", "category_name"));
		
		//搜索
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("music_info");
		searchRequest.types("_doc");
		searchRequest.source(builder);
		// 执行请求
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		// 解析查询结果
		SearchHit[] res = response.getHits().getHits();
		List<Object> musics = new ArrayList<>();
		for (SearchHit hit : res) {
			//获取到结果的map集合
			Map<String, Object> map = hit.getSourceAsMap();
			DesMusicVO music = JSON.parseObject(JSON.toJSONString(map), DesMusicVO.class);
			musics.add(music);
		}
		
		return R.success("musics", musics);
	}
	
	@Override
	public R getMusicLevelCounts() {
		List<MusicLevelCountsVO> musicLevelCounts = baseMapper.getMusicLevelCounts();
		return R.success("musicLevelCounts", musicLevelCounts);
	}
	
	@Override
	public R getCategoryMusicCounts() {
		List<CategoryMusicCountsVO> categoryMusicCounts = baseMapper.getCategoryMusicCounts();
		return R.success("categoryMusicCounts", categoryMusicCounts);
	}
}
