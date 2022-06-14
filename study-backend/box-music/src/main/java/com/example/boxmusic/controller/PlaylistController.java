package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddPlaylistDTO;
import com.example.boxmusic.pojo.dto.UpdatePlaylistAdminDTO;
import com.example.boxmusic.service.PlaylistService;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.Value;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/playlist")
public class PlaylistController {
	@Autowired
	private PlaylistService playlistService;
	
	@ApiOperation("根据歌单名模糊查询歌单")
	@GetMapping("/getPlaylistsByPlaylistNamePage")
	public R getPlaylistsByPlaylistNamePage(@RequestParam(defaultValue = "1") Integer currentPage,
										@RequestParam(defaultValue = "10") Integer pageSize,
										@RequestParam(defaultValue = "") String playlistName) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return playlistService.getPlaylistsByPlaylistNamePage(page, playlistName);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加歌单")
	@PostMapping("/addPlaylist")
	public R addPlaylist(HttpServletRequest httpServletRequest, MultipartFile picture, @Valid AddPlaylistDTO addPlaylistDTO) {
		return playlistService.addPlaylist(httpServletRequest.getHeader(Value.HEADER), picture, addPlaylistDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据歌单id批量删除歌单")
	@DeleteMapping("/deletePlaylistsByPlaylistIds")
	public R deletePlaylistsByPlaylistIds(@RequestBody @NotEmpty(message = "歌单id不能为空") List<Long> playlistIds) {
		return playlistService.deletePlaylistsByPlaylistIds(playlistIds);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新歌单")
	@PutMapping("/updatePlaylist")
	public R updatePlaylist(MultipartFile picture, @Valid UpdatePlaylistAdminDTO updatePlaylistAdminDTO) {
		return playlistService.updatePlaylist(picture, updatePlaylistAdminDTO);
	}
}

