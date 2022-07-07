package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.pojo.dto.AddAlbumDTO;
import com.example.boxmusic.pojo.dto.UpdateAlbumDTO;
import com.example.boxmusic.pojo.entity.Album;
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
public interface AlbumService extends IService<Album> {
	
	R getAlbumsByAlbumNamePage(Page<Map<String, Object>> page, String albumName);
	
	R deleteAlbumsByAlbumIds(List<Long> albumIds);
	
	R addAlbum(MultipartFile picture, AddAlbumDTO addAlbumDTO);
	
	R updateAlbum(MultipartFile picture, UpdateAlbumDTO updateAlbumDTO);
	
	R getAlbumsByTotalViewsSortPage(Page<Map<String, Object>> page);
}
