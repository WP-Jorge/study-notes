package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.mapper.AlbumMapper;
import com.example.boxmusic.mapper.AlbumMapper;
import com.example.boxmusic.pojo.dto.AddAlbumDTO;
import com.example.boxmusic.pojo.dto.AddAlbumDTO;
import com.example.boxmusic.pojo.dto.UpdateAlbumDTO;
import com.example.boxmusic.pojo.dto.UpdateAlbumDTO;
import com.example.boxmusic.pojo.entity.Album;
import com.example.boxmusic.pojo.entity.Album;
import com.example.boxmusic.pojo.entity.Album;
import com.example.boxmusic.pojo.vo.AlbumVO;
import com.example.boxmusic.pojo.vo.AlbumVO;
import com.example.boxmusic.service.AlbumService;
import com.example.boxmusic.service.FileService;
import com.example.boxmusic.service.AlbumService;
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
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
	@Autowired
	JsonUtil jsonUtil;
	
	@Autowired
	FileService fileService;
	
	@Value("${albumPicturePath}")
	private String albumPicturePath;
	
	@Autowired
	private CachedUidGenerator cachedUidGenerator;
	
	@Override
	public R getAlbumsByAlbumNamePage(Page<Map<String, Object>> page, String albumName) {
		QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
		if (!"".equals(albumName) && albumName != null) {
			queryWrapper.like("album_name", albumName);
		}
		Page<Map<String, Object>> albumPages = baseMapper.selectMapsPage(page, queryWrapper);
		albumPages.setRecords(jsonUtil.transformPages(albumPages, AlbumVO.class));
		return R.successPage("获取专辑成功", albumPages);
	}
	
	@Override
	public R addAlbum(MultipartFile picture, AddAlbumDTO addAlbumDTO) {
		try {
			if (picture == null) {
				return R.error("添加失败，请上传图片");
			}
			long uid = cachedUidGenerator.getUID();
			String pictureName = fileService.getFilename(picture, uid);
			Album album = new Album();
			BeanUtils.copyProperties(addAlbumDTO, album);
			album.setAlbumId(uid);
			album.setAlbumPic(pictureName);
			int i = baseMapper.insert(album);
			if (i <= 0) {
				throw new RuntimeException("添加专辑失败");
			}
			fileService.uploadFile(picture, albumPicturePath, pictureName);
			return R.success("添加专辑成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，专辑名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R deleteAlbumsByAlbumIds(List<Long> albumIds) {
		List<Album> albums = baseMapper.selectBatchIds(albumIds);
		int count = baseMapper.deleteBatchIds(albumIds);
		if (count <= 0) {
			return R.error("删除专辑失败");
		}
		for (Album category : albums) {
			Boolean aBoolean = fileService.deleteFile(albumPicturePath, category.getAlbumPic());
			if (!aBoolean) {
				log.warn("删除本地专辑图片失败");
			}
		}
		return R.success("删除专辑成功");
	}
	
	@Override
	public R updateAlbum(MultipartFile picture, UpdateAlbumDTO updateAlbumDTO) {
		try {
			Album album = baseMapper.selectById(updateAlbumDTO.getAlbumId());
			BeanUtils.copyProperties(updateAlbumDTO, album);
			String pictrueName = album.getAlbumPic();
			String picturefilename = null;
			if (picture != null) {
				picturefilename = fileService.getFilename(picture, album.getAlbumId());
				album.setAlbumPic(picturefilename);
			}
			int i = baseMapper.updateById(album);
			if (i <= 0) {
				throw new RuntimeException("更新失败");
			}
			if (picture != null) {
				Boolean deleteImage = fileService.deleteFile(albumPicturePath, pictrueName);
				if (!deleteImage) {
					log.warn("本地图片不存在");
				}
				fileService.uploadFile(picture, albumPicturePath, picturefilename);
			}
			return R.success("更新成功");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R getAlbumsByTotalViewsSortPage(Page<Map<String, Object>> page) {
		QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("total_views");
		Page<Map<String, Object>> albumPages = baseMapper.selectMapsPage(page, queryWrapper);
		albumPages.setRecords(jsonUtil.transformPages(albumPages, AlbumVO.class));
		return R.successPage("获取专辑排行成功", albumPages);
	}
	
	@Override
	public R getAlbumsBySingerIdPage(Page<Map<String, Object>> page, Long singerId) {
		IPage<AlbumVO> albumPages = baseMapper.getAlbumsBySingerIdPage(page, singerId);
		return R.successPage("获取歌手专辑成功", albumPages);
	}
}
