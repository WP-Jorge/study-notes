package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddPlaylistDTO;
import com.example.boxmusic.pojo.dto.UpdatePlaylistAdminDTO;
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
	
	R getPlaylistsByTotalViewsSortPage(Page<Map<String, Object>> page);
	
	R getPlaylistsByCategoryIdPage(Page<Map<String, Object>> page, Long categoryId);
}
