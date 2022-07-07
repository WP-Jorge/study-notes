package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddAlbumDTO;
import com.example.boxmusic.pojo.dto.AddSingerDTO;
import com.example.boxmusic.pojo.dto.UpdateAlbumDTO;
import com.example.boxmusic.pojo.dto.UpdateSingerDTO;
import com.example.boxmusic.service.AlbumService;
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
@RequestMapping("/album")
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	
	@ApiOperation("根据专辑名模糊查询专辑")
	@GetMapping("/getAlbumsByAlbumNamePage")
	public R getAlbumsByAlbumNamePage(@RequestParam(defaultValue = "1") Integer currentPage,
											 @RequestParam(defaultValue = "10") Integer pageSize,
											 @RequestParam(defaultValue = "") String albumName) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return albumService.getAlbumsByAlbumNamePage(page, albumName);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据专辑id批量删除专辑")
	@DeleteMapping("/deleteAlbumsByAlbumIds")
	public R deleteAlbumsByAlbumIds(@RequestBody @NotEmpty(message = "专辑id不能为空") List<Long> albumIds) {
		try {
			return albumService.deleteAlbumsByAlbumIds(albumIds);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("当前专辑下存在歌曲，禁止删除");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加专辑")
	@PostMapping("/addAlbum")
	public R addAlbum(MultipartFile picture, @Valid AddAlbumDTO addAlbumDTO) {
		return albumService.addAlbum(picture, addAlbumDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新专辑")
	@PutMapping("/updateAlbum")
	public R updateSinger(MultipartFile picture, @Valid UpdateAlbumDTO updateAlbumDTO) {
		return albumService.updateAlbum(picture, updateAlbumDTO);
	}
	
	@ApiOperation("获取个性推荐轮播")
	@GetMapping("/getDiscoveryRecommendCaousel")
	public R getMusicsByMusicTitlePage(@RequestParam(defaultValue = "1") Integer currentPage,
									   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return albumService.getAlbumsByTotalViewsSortPage(page);
	}
	
	@ApiOperation("获取专辑排行")
	@GetMapping("/getAlbumsByTotalViewsSortPage")
	public R getAlbumsByTotalViewsSortPage(@RequestParam(defaultValue = "1") Integer currentPage,
										   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return albumService.getAlbumsByTotalViewsSortPage(page);
	}
}

