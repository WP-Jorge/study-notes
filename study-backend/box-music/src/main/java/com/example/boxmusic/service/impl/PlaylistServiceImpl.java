package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddPlaylistDTO;
import com.example.boxmusic.pojo.dto.UpdatePlaylistAdminDTO;
import com.example.boxmusic.pojo.entity.Playlist;
import com.example.boxmusic.mapper.PlaylistMapper;
import com.example.boxmusic.pojo.entity.User;
import com.example.boxmusic.service.FileService;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
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
			return R.error("添加失败，请上传头像");
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
			fileService.uploadFile(picture, playlistPicturePath, pictureName);
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
			Boolean aBoolean = fileService.deleteFile(playlistPicturePath, playlist.getPlaylistPic());
			if (!aBoolean) {
				log.warn("删除歌单图片失败");
			}
		}
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
			if (picture != null) {
				Boolean deleteImage = fileService.deleteFile(playlistPicturePath, pictrueName);
				if (!deleteImage) {
					log.warn("本地图片不存在");
				}
				fileService.uploadFile(picture, playlistPicturePath, picturefilename);
			}
			return R.success("更新成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，歌单名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
