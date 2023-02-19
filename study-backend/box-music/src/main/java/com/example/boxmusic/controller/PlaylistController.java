package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.*;
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
 * 前端控制器
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
	
	@ApiOperation("获取歌单排行")
	@GetMapping("/getPlaylistsByTotalViewsSortPage")
	public R getPlaylistsByTotalViewsSortPage(@RequestParam(defaultValue = "1") Integer currentPage,
											  @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return playlistService.getPlaylistsByTotalViewsSortPage(page);
	}
	
	@ApiOperation("根据分类id查询歌单")
	@GetMapping("/getPlaylistsByCategoryIdPage")
	public R getPlaylistsByCategoryIdPage(@RequestParam(defaultValue = "1") Integer currentPage,
										  @RequestParam(defaultValue = "10") Integer pageSize, Long categoryId) {
		if (categoryId == null) {
			return R.error("分类id不能为空");
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return playlistService.getPlaylistsByCategoryIdPage(page, categoryId);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加歌单")
	@PostMapping("/addSimplePlaylist")
	public R addSimplePlaylist(HttpServletRequest httpServletRequest, @RequestBody @Valid AddSimplePlaylistDTO addSimplePlaylistDTO) {
		return playlistService.addSimplePlaylist(httpServletRequest.getHeader(Value.HEADER), addSimplePlaylistDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据歌单id批量删除歌单")
	@DeleteMapping("/deleteSimplePlaylistsByPlaylistIds")
	public R deleteSimplePlaylistsByPlaylistIds(@RequestBody @NotEmpty(message = "歌单id不能为空") List<Long> playlistIds) {
		return playlistService.deleteSimplePlaylistsByPlaylistIds(playlistIds);
	}
	
	@ApiOperation("获取个人歌单")
	@GetMapping("/getSimplePlaylistsWithMusics")
	public R getSimplePlaylistsWithMusics(HttpServletRequest httpServletRequest) {
		return playlistService.getSimplePlaylistsWithMusics(httpServletRequest.getHeader(Value.HEADER));
	}
	
	@ApiOperation("根据歌单名模糊查询收藏歌单")
	@GetMapping("/getPlaylistsByPlaylistNameAndUserIdPage")
	public R getPlaylistsByPlaylistNameAndUserIdPage(HttpServletRequest httpServletRequest, @RequestParam(defaultValue = "1") Integer currentPage,
											@RequestParam(defaultValue = "10") Integer pageSize,
											@RequestParam(defaultValue = "") String playlistName) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return playlistService.getPlaylistsByPlaylistNameAndUserIdPage(page, httpServletRequest.getHeader(Value.HEADER), playlistName);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("修改歌单")
	@PutMapping("/updateSimplePlaylist")
	public R updateSimplePlaylist(HttpServletRequest httpServletRequest, @RequestBody @Valid UpdateSimplePlaylistDTO updateSimplePlaylistDTO) {
		return playlistService.updateSimplePlaylist(httpServletRequest.getHeader(Value.HEADER), updateSimplePlaylistDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量删除收藏歌单")
	@DeleteMapping("/deleteUserPlaylists")
	public R deleteUserPlaylists(HttpServletRequest httpServletRequest, @RequestBody @NotEmpty(message = "歌单id不能为空") List<Long> playlistIds) {
		return playlistService.deleteUserPlaylists(httpServletRequest.getHeader(Value.HEADER), playlistIds);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("收藏歌单")
	@PostMapping("/addPlaylistToCollection")
	public R addPlaylistToCollection(HttpServletRequest httpServletRequest, @RequestBody AddSimpleUserPlaylistDTO simpleUserPlaylistDTO) {
		return playlistService.addPlaylistToCollection(httpServletRequest.getHeader(Value.HEADER), simpleUserPlaylistDTO.getPlaylistId());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加歌曲至对应歌单")
	@PostMapping("/addMusicToPlaylist")
	public R addMusicToPlaylist(@RequestBody AddMusicPlaylistDTO addMusicPlaylistDTO) {
		return playlistService.addMusicToPlaylist(addMusicPlaylistDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("从对应歌单中删除歌曲")
	@DeleteMapping("/deleteMusicFromPlaylist")
	public R deleteMusicFromPlaylist(@RequestBody AddMusicPlaylistDTO addMusicPlaylistDTO) {
		return playlistService.deleteMusicFromPlaylist(addMusicPlaylistDTO);
	}
}
