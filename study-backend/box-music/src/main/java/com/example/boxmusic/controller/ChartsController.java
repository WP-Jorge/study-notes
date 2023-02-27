package com.example.boxmusic.controller;

import com.example.boxmusic.service.FileService;
import com.example.boxmusic.service.MusicService;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/charts")
public class ChartsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MusicService musicService;
	
	@GetMapping("/getRecentlyUserCounts")
	public R getRecentlyUserCounts() {
		return userService.getRecentlyUserCounts();
	}
	
	@GetMapping("/getMusicLevelCounts")
	public R getMusicLevelCounts() {
		return musicService.getMusicLevelCounts();
	}
	
	@GetMapping("/getCategoryMusicCounts")
	public R getCategoryMusicCounts() {
		return musicService.getCategoryMusicCounts();
	}
}
