package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.*;
import com.example.boxmusic.pojo.entity.Playlist;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.utils.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
public interface PlaylistService extends IService<Playlist> {
	
	R getPlaylistsByPlaylistNamePage(Page<Map<String, Object>> page, String playlistName);
	
	R addPlaylist(String headerToken, MultipartFile picture, AddPlaylistDTO addPlaylistDTO);
	
	R deletePlaylistsByPlaylistIds(List<Long> playlistIds);
	
	R updatePlaylist(MultipartFile picture, UpdatePlaylistAdminDTO updatePlaylistAdminDTO);
	
	R getPlaylistsByTotalViewsSortPage(String token);
	
	R getPlaylistsByCategoryIdPage(Page<Map<String, Object>> page, Long categoryId);

	R addSimplePlaylist(String headerToken, AddSimplePlaylistDTO addSimplePlaylistDTO);

	R deleteSimplePlaylistsByPlaylistIds(List<Long> playlistIds);

	R getSimplePlaylistsWithMusics(String headerToken);
	
	R getPlaylistsByPlaylistNameAndUserIdPage(Page<Map<String, Object>> page, String headerToken, String playlistName);
	
	R updateSimplePlaylist(String headerToken, UpdateSimplePlaylistDTO updateSimplePlaylistDTO);
	
	R deleteUserPlaylists(String headerToken, List<Long> playlistIds);
	
	R addPlaylistToCollection(String headerToken, Long playlistId);
	
	R addMusicToPlaylist(AddMusicPlaylistDTO addMusicPlaylistDTO);
	
	R deleteMusicFromPlaylist(AddMusicPlaylistDTO addMusicPlaylistDTO);
}
