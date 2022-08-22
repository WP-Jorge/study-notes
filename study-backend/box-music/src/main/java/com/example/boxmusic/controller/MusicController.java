package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddMusicDTO;
import com.example.boxmusic.pojo.dto.AddSingerDTO;
import com.example.boxmusic.pojo.dto.UpdateMusicDTO;
import com.example.boxmusic.pojo.dto.UpdateSingerDTO;
import com.example.boxmusic.service.MusicCategoryService;
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
	public R addMusic(MultipartFile song, @Valid AddMusicDTO addMusicDTO) {
		return musicService.addMusic(song, addMusicDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新歌曲")
	@PutMapping("/updateMusic")
	public R updateMusic(MultipartFile song, @Valid UpdateMusicDTO updateMusicDTO) {
		return musicService.updateMusic(song, updateMusicDTO);
	}
	
	@ApiOperation("获取音乐排行")
	@GetMapping("/getMusicsByTotalViewsSortPage")
	public R getMusicsByTotalViewsSortPage(@RequestParam(defaultValue = "1") Integer currentPage,
											@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return musicService.getMusicsByTotalViewsSortPage(page);
	}
	
	@ApiOperation("获取最近新增音乐")
	@GetMapping("/getMusicsByCreateTimeSortPage")
	public R getMusicsByCreateTimeSortPage(@RequestParam(defaultValue = "1") Integer currentPage,
										   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return musicService.getMusicsByCreateTimeSortPage(page);
	}
	
	@ApiOperation("根据分类id查询音乐")
	@GetMapping("/getMusicsByCategoryIdPage")
	public R getMusicsByCategoryIdPage(@RequestParam(defaultValue = "1") Integer currentPage,
											 @RequestParam(defaultValue = "10") Integer pageSize, Long categoryId) {
		if (categoryId == null) {
			return R.error("分类id不能为空");
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return musicService.getMusicsByCategoryIdPage(page, categoryId);
	}
	
	@ApiOperation("根据歌单id查询音乐")
	@GetMapping("/getMusicsByPlaylistIdPage")
	public R getMusicsByPlaylistIdPage(@RequestParam(defaultValue = "1") Integer currentPage,
											 @RequestParam(defaultValue = "10") Integer pageSize, Long playlistId) {
		if (playlistId == null) {
			return R.error("歌单id不能为空");
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return musicService.getMusicsByPlaylistIdPage(page, playlistId);
	}
	
	@ApiOperation("根据专辑id查询音乐")
	@GetMapping("/getMusicsByAlbumIdPage")
	public R getMusicsByAlbumIdPage(@RequestParam(defaultValue = "1") Integer currentPage,
									   @RequestParam(defaultValue = "10") Integer pageSize, Long albumId) {
		if (albumId == null) {
			return R.error("歌单id不能为空");
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return musicService.getMusicsByAlbumIdPage(page, albumId);
	}
	
}

