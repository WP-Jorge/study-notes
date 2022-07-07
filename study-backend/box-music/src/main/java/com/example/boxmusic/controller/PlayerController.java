package com.example.boxmusic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.service.AlbumService;
import com.example.boxmusic.service.PlaylistService;
import com.example.boxmusic.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private PlaylistService playlistService;
	
	
	
	
	
	
	
}
