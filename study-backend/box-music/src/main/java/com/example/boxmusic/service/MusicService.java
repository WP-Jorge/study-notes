package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddMusicDTO;
import com.example.boxmusic.pojo.dto.UpdateMusicDTO;
import com.example.boxmusic.pojo.entity.Music;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
public interface MusicService extends IService<Music> {
	
	R getMusicsByMusicTitlePage(Page<Map<String, Object>> page, String musicTitle);
	
	R deleteMusicsByMusicIds(List<Long> musicIds);
	
	R addMusic(MultipartFile song, AddMusicDTO addMusicDTO);
	
	R updateMusic(MultipartFile song, UpdateMusicDTO updateMusicDTO);
	
	R getMusicsByTotalViewsSortPage(String token);
	
	R getMusicsByCreateTimeSortPage(Page<Map<String, Object>> page);
	
	R getMusicsByCategoryIdPage(Page<Map<String, Object>> page, Long categoryId);
	
	R getMusicsByPlaylistIdPage(Page<Map<String, Object>> page, Long playlistId);
	
	R getMusicsByAlbumIdPage(Page<Map<String, Object>> page, Long albumId);
	
	R getMusicsByKeyword(String keyword) throws IOException;
	
	R getMusicLevelCounts();
	
	R getCategoryMusicCounts();
}
