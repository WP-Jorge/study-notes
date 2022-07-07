package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddSingerDTO;
import com.example.boxmusic.pojo.dto.UpdateSingerDTO;
import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.pojo.entity.Playlist;
import com.example.boxmusic.pojo.entity.Singer;
import com.example.boxmusic.mapper.SingerMapper;
import com.example.boxmusic.pojo.vo.CategoryVO;
import com.example.boxmusic.pojo.vo.PlaylistVO;
import com.example.boxmusic.pojo.vo.SingerVO;
import com.example.boxmusic.service.FileService;
import com.example.boxmusic.service.SingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {
	@Autowired
	JsonUtil jsonUtil;
	
	@Autowired
	FileService fileService;
	
	@Value("${spiderPath}")
	private String spiderPath;
	
	@Value("${singerPicturePath}")
	private String singerPicturePath;
	
	@Autowired
	private CachedUidGenerator cachedUidGenerator;
	
	@Override
	public R getSingersBySingerNamePage(Page<Map<String, Object>> page, String singerName) {
		QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
		if (!"".equals(singerName) && singerName != null) {
			queryWrapper.like("singer_name", singerName);
		}
		Page<Map<String, Object>> singerPages = baseMapper.selectMapsPage(page, queryWrapper);
		singerPages.setRecords(jsonUtil.transformPages(singerPages, SingerVO.class));
		return R.successPage("获取歌手成功", singerPages);
	}
	
	@Override
	public R addSinger(MultipartFile picture, AddSingerDTO addSingerDTO) {
		try {
			if (picture == null) {
				return R.error("添加失败，请上传图片");
			}
			long uid = cachedUidGenerator.getUID();
			String pictureName = fileService.getFilename(picture, uid);
			Singer singer = new Singer();
			BeanUtils.copyProperties(addSingerDTO, singer);
			singer.setSingerId(uid);
			singer.setSingerPic(pictureName);
			int i = baseMapper.insert(singer);
			if (i <= 0) {
				throw new RuntimeException("添加歌手失败");
			}
			fileService.uploadFile(picture, singerPicturePath, pictureName);
			return R.success("添加歌手成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，歌手名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R deleteSingersBySingerIds(List<Long> singerIds) {
		int count = baseMapper.deleteBatchIds(singerIds);
		if (count > 0) {
			return R.success("删除歌手成功");
		}
		return R.error("删除歌手失败");
	}
	
	@Override
	public R updateSinger(MultipartFile picture, UpdateSingerDTO updateSingerDTO) {
		try {
			Singer singer = baseMapper.selectById(updateSingerDTO.getSingerId());
			BeanUtils.copyProperties(updateSingerDTO, singer);
			String pictrueName = singer.getSingerPic();
			String picturefilename = null;
			if (picture != null) {
				picturefilename = fileService.getFilename(picture, singer.getSingerId());
				singer.setSingerPic(picturefilename);
			}
			int i = baseMapper.updateById(singer);
			if (i <= 0) {
				throw new RuntimeException("更新失败");
			}
			if (picture != null) {
				Boolean deleteImage = fileService.deleteFile(singerPicturePath, pictrueName);
				if (!deleteImage) {
					log.warn("本地图片不存在");
				}
				fileService.uploadFile(picture, singerPicturePath, picturefilename);
			}
			return R.success("更新成功");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R getSingersByTotalViewsSortPage(Page<Map<String, Object>> page) {
		QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("total_views");
		Page<Map<String, Object>> singerPages = baseMapper.selectMapsPage(page, queryWrapper);
		singerPages.setRecords(jsonUtil.transformPages(singerPages, SingerVO.class));
		return R.successPage("获取歌手排行成功", singerPages);
	}
}
