package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddPlaylistDTO;
import com.example.boxmusic.pojo.dto.AddSimplePlaylistDTO;
import com.example.boxmusic.pojo.dto.UpdatePlaylistAdminDTO;
import com.example.boxmusic.pojo.dto.UpdateSimplePlaylistDTO;
import com.example.boxmusic.pojo.entity.*;
import com.example.boxmusic.mapper.PlaylistMapper;
import com.example.boxmusic.pojo.vo.*;
import com.example.boxmusic.service.FileService;
import com.example.boxmusic.service.PlaylistCategoryService;
import com.example.boxmusic.service.PlaylistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.JwtTokenUtil;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.Value;
import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
public class PlaylistServiceImpl extends ServiceImpl<PlaylistMapper, Playlist> implements PlaylistService {
	@Autowired
	JsonUtil jsonUtil;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CachedUidGenerator cachedUidGenerator;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private PlaylistCategoryService playlistCategoryService;
	
	@org.springframework.beans.factory.annotation.Value("${basePath}")
	private String basePath;
	
	@org.springframework.beans.factory.annotation.Value("${playlistPicturePath}")
	private String playlistPicturePath;
	
	@Override
	public R getPlaylistsByPlaylistNamePage(Page<Map<String, Object>> page, String playlistName) {
		return R.successPage("获取歌单成功", baseMapper.getPlaylistsByPlaylistNamePage(page, playlistName));
	}
	
	@Override
	public R addPlaylist(String headerToken, MultipartFile picture, AddPlaylistDTO addPlaylistDTO) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		if (picture == null) {
			return R.error("添加失败，请上传歌单图片");
		}
		// postMan测试时，自动假如的前缀，要去掉。
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		try {
			QueryWrapper<User> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("username", username);
			User user = userService.getOne(queryWrapper);
			long uid = cachedUidGenerator.getUID();
			String pictureName = fileService.getFilename(picture, uid);
			Playlist playlist = new Playlist();
			BeanUtils.copyProperties(addPlaylistDTO, playlist);
			playlist.setUserId(user.getUserId());
			playlist.setPlaylistId(uid);
			playlist.setPlaylistPic(pictureName);
			boolean save = this.save(playlist);
			if (!save) {
				throw new RuntimeException("添加失败");
			}
			if (addPlaylistDTO.getCategoryList().size() > 0) {
				List<PlaylistCategory> playlistCategoryList = new ArrayList<>();
				for (Category category : addPlaylistDTO.getCategoryList()) {
					PlaylistCategory playlistCategory = new PlaylistCategory();
					playlistCategory.setPlaylistId(playlist.getPlaylistId());
					playlistCategory.setCategoryId(category.getCategoryId());
					playlistCategoryList.add(playlistCategory);
				}
				boolean playlistCategoryFlag = playlistCategoryService.saveBatch(playlistCategoryList);
				if (!playlistCategoryFlag) {
					throw new RuntimeException("添加歌单分类时发生错误");
				}
			}
			fileService.uploadFile(picture, basePath + playlistPicturePath, pictureName);
			redisTemplate.delete("com.example.boxmusic.mapper.UserMapper");
			return R.success("添加成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，歌单已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R deletePlaylistsByPlaylistIds(List<Long> playlistIds) {
		List<Playlist> playlists = baseMapper.selectBatchIds(playlistIds);
		int count = baseMapper.deleteBatchIds(playlistIds);
		if (count <= 0) {
			return R.error("删除歌曲分类失败");
		}
		for (Playlist playlist : playlists) {
			Boolean aBoolean = fileService.deleteFile(basePath + playlistPicturePath, playlist.getPlaylistPic());
			if (!aBoolean) {
				log.warn("删除歌单图片失败");
			}
		}
		redisTemplate.delete("com.example.boxmusic.mapper.UserMapper");
		return R.success("删除歌单图片成功");
	}
	
	@Override
	public R updatePlaylist(MultipartFile picture, UpdatePlaylistAdminDTO updatePlaylistAdminDTO) {
		try {
			Playlist playlist = baseMapper.selectById(updatePlaylistAdminDTO.getPlaylistId());
			BeanUtils.copyProperties(updatePlaylistAdminDTO, playlist);
			String pictrueName = playlist.getPlaylistPic();
			String picturefilename = null;
			if (picture != null) {
				picturefilename = fileService.getFilename(picture, updatePlaylistAdminDTO.getPlaylistId());
				playlist.setPlaylistPic(picturefilename);
			}
			int i = baseMapper.updateById(playlist);
			if (i <= 0) {
				throw new RuntimeException("更新失败");
			}
			
			QueryWrapper<PlaylistCategory> playlistCategoryQueryWrapper = new QueryWrapper<>();
			playlistCategoryQueryWrapper.eq("playlist_id", playlist.getPlaylistId());
			boolean playlistCategoryDeleted = playlistCategoryService.remove(playlistCategoryQueryWrapper);
			
			if (!playlistCategoryDeleted) {
				throw new RuntimeException("删除歌单分类时发生错误");
			}
			
			if (updatePlaylistAdminDTO.getCategoryList().size() > 0) {
				List<PlaylistCategory> playlistCategoryList = new ArrayList<>();
				for (Category category : updatePlaylistAdminDTO.getCategoryList()) {
					PlaylistCategory playlistCategory = new PlaylistCategory();
					playlistCategory.setPlaylistId(playlist.getPlaylistId());
					playlistCategory.setCategoryId(category.getCategoryId());
					playlistCategoryList.add(playlistCategory);
				}
				boolean playlistCategoryFlag = playlistCategoryService.saveBatch(playlistCategoryList);
				if (!playlistCategoryFlag) {
					throw new RuntimeException("添加歌单分类时发生错误");
				}
			}
			
			if (picture != null) {
				Boolean deleteImage = fileService.deleteFile(basePath + playlistPicturePath, pictrueName);
				if (!deleteImage) {
					log.warn("本地图片不存在");
				}
				fileService.uploadFile(picture, basePath + playlistPicturePath, picturefilename);
			}
			redisTemplate.delete("com.example.boxmusic.mapper.UserMapper");
			return R.success("更新成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，歌单名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R getPlaylistsByTotalViewsSortPage(Page<Map<String, Object>> page) {
		return R.successPage("获取歌单排行成功", baseMapper.getPlaylistsByTotalViewsSortPage(page));
	}
	
	@Override
	public R getPlaylistsByCategoryIdPage(Page<Map<String, Object>> page, Long categoryId) {
		IPage<MusicVO> musicPages = baseMapper.getPlaylistsByCategoryIdPage(page, categoryId);
		return R.successPage("获取歌曲分类成功", musicPages);
	}
	
	@Override
	public R addSimplePlaylist(String headerToken, AddSimplePlaylistDTO addSimplePlaylistDTO) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		// postMan测试时，自动假如的前缀，要去掉。
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserVO userInfo = userService.getUserInfoWithUsername(username);
		try {
			long uid = cachedUidGenerator.getUID();
			Playlist playlist = new Playlist();
			BeanUtils.copyProperties(addSimplePlaylistDTO, playlist);
			playlist.setUserId(userInfo.getUserId());
			//			playlist.setPlaylistId(uid);
			playlist.setOpened(0);
			boolean save = this.save(playlist);
			if (!save) {
				throw new RuntimeException("添加失败");
			}
			redisTemplate.delete("com.example.boxmusic.mapper.MusicMapper");
			return R.success("添加成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，歌单已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R deleteSimplePlaylistsByPlaylistIds(List<Long> playlistIds) {
		int i = baseMapper.deleteBatchIds(playlistIds);
		if (i > 0) {
			redisTemplate.delete("com.example.boxmusic.mapper.MusicMapper");
			return R.success("删除成功");
		}
		return R.error("删除失败");
	}
	
	@Override
	public R getSimplePlaylistsWithMusics(String headerToken) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserVO userInfo = userService.getUserInfoWithUsername(username);
		//		QueryWrapper<Playlist> queryWrapper = new QueryWrapper<>();
		//		queryWrapper.eq("user_id", userInfo.getUserId());
		//		queryWrapper.eq("opened", 0);
		//		List<Playlist> list = this.list(queryWrapper);
		List<PlaylistWithMusicVO> playlists = baseMapper.getSimplePlaylistsWithMusics(userInfo.getUserId());
		return R.success("pageList", playlists);
	}
	
	@Override
	public R getPlaylistsByPlaylistNameAndUserIdPage(Page<Map<String, Object>> page, String headerToken, String playlistName) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserVO userInfo = userService.getUserInfoWithUsername(username);
		return R.successPage("获取歌单成功", baseMapper.getPlaylistsByPlaylistNameAndUserIdPage(page, userInfo.getUserId(), playlistName));
	}
	
	@Override
	public R updateSimplePlaylist(String headerToken, UpdateSimplePlaylistDTO updateSimplePlaylistDTO) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		// postMan测试时，自动假如的前缀，要去掉。
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserVO userInfo = userService.getUserInfoWithUsername(username);
		try {
			Playlist playlist = new Playlist();
			BeanUtils.copyProperties(updateSimplePlaylistDTO, playlist);
			playlist.setUserId(userInfo.getUserId());
			boolean save = this.updateById(playlist);
			if (!save) {
				throw new RuntimeException("更新失败");
			}
			redisTemplate.delete("com.example.boxmusic.mapper.MusicMapper");
			return R.success("更新成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，歌单名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R deleteUserPlaylists(String headerToken, List<Long> playlistIds) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		// postMan测试时，自动假如的前缀，要去掉。
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserVO userInfo = userService.getUserInfoWithUsername(username);
		try {
			Integer integer = baseMapper.deleteUserPlaylists(userInfo.getUserId(), playlistIds);
			if (integer > 0) {
				return R.success("删除成功");
			}
			return R.error("删除失败");
			// redisTemplate.delete("com.example.boxmusic.mapper.MusicMapper");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，歌单名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
}
