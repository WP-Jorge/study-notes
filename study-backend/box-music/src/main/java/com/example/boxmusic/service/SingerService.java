package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddSingerDTO;
import com.example.boxmusic.pojo.dto.UpdateSingerDTO;
import com.example.boxmusic.pojo.entity.Singer;
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
public interface SingerService extends IService<Singer> {
	
	R getSingersBySingerNamePage(Page<Map<String, Object>> page, String singerName);
	
	R addSinger(MultipartFile picture, AddSingerDTO addSingerDTO);
	
	R deleteSingersBySingerIds(List<Long> singerIds);
	
	R updateSinger(MultipartFile picture, UpdateSingerDTO updateSingerDTO);
}
