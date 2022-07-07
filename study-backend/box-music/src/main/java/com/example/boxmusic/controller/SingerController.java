package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddCategoryDTO;
import com.example.boxmusic.pojo.dto.AddSingerDTO;
import com.example.boxmusic.pojo.dto.UpdateCategoryDTO;
import com.example.boxmusic.pojo.dto.UpdateSingerDTO;
import com.example.boxmusic.service.CategoryService;
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
@RequestMapping("/singer")
public class SingerController {
	@Autowired
	private SingerService singerService;
	
	@ApiOperation("根据歌手名模糊查询歌手")
	@GetMapping("/getSingersBySingerNamePage")
	public R getSingersBySingerNamePage(@RequestParam(defaultValue = "1") Integer currentPage,
											 @RequestParam(defaultValue = "10") Integer pageSize,
											 @RequestParam(defaultValue = "") String singerName) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return singerService.getSingersBySingerNamePage(page, singerName);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据歌手id批量删除歌手")
	@DeleteMapping("/deleteSingersBySingerIds")
	public R deleteSingersBySingerIds(@RequestBody @NotEmpty(message = "歌手id不能为空") List<Long> singerIds) {
		try {
			return singerService.deleteSingersBySingerIds(singerIds);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("当前分类下存在歌曲，禁止删除");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加歌手")
	@PostMapping("/addSinger")
	public R addSinger(MultipartFile picture, @Valid AddSingerDTO addSingerDTO) {
		return singerService.addSinger(picture, addSingerDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新歌手")
	@PutMapping("/updateSinger")
	public R updateSinger(MultipartFile picture, @Valid UpdateSingerDTO updateSingerDTO) {
		return singerService.updateSinger(picture, updateSingerDTO);
	}
	
	@ApiOperation("获取歌手排行")
	@GetMapping("/getSingersByTotalViewsSortPage")
	public R getSingersByTotalViewsSortPage(@RequestParam(defaultValue = "1") Integer currentPage,
											  @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return singerService.getSingersByTotalViewsSortPage(page);
	}
}

