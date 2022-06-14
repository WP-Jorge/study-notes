package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddMusicDTO;
import com.example.boxmusic.pojo.dto.AddSingerDTO;
import com.example.boxmusic.pojo.dto.UpdateMusicDTO;
import com.example.boxmusic.pojo.dto.UpdateSingerDTO;
import com.example.boxmusic.service.MusicService;
import com.example.boxmusic.service.SingerService;
import com.example.boxmusic.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@RestController
@Validated
@RequestMapping("/music")
public class MusicController {
	@Autowired
	private MusicService musicService;
	
	@ApiOperation("根据音乐名模糊查询音乐")
	@GetMapping("/getMusicsByMusicTitlePage")
	public R getMusicsByMusicTitlePage(@RequestParam(defaultValue = "1") Integer currentPage,
										@RequestParam(defaultValue = "10") Integer pageSize,
										@RequestParam(defaultValue = "") String musicTitle) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return musicService.getMusicsByMusicTitlePage(page, musicTitle);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据音乐id批量删除音乐")
	@DeleteMapping("/deleteMusicsByMusicIds")
	public R deleteMusicsByMusicIds(@RequestBody @NotEmpty(message = "歌手id不能为空") List<Long> musicIds) {
		return musicService.deleteMusicsByMusicIds(musicIds);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加歌曲")
	@PostMapping("/addMusic")
	public R addMusic(MultipartFile picture, MultipartFile song, @Valid AddMusicDTO addMusicDTO) {
		return musicService.addMusic(picture, song, addMusicDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新歌曲")
	@PutMapping("/updateMusic")
	public R updateMusic(MultipartFile picture, MultipartFile song, @Valid UpdateMusicDTO updateMusicDTO) {
		return musicService.updateMusic(picture, song, updateMusicDTO);
	}
	
}

