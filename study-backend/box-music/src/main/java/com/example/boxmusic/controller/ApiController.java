package com.example.boxmusic.controller;


import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.RoleWithApiVO;
import com.example.boxmusic.service.ApiService;
import com.example.boxmusic.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	@ApiOperation("根据用户id获取权限树")
	@GetMapping("/getApiTreeByUserId")
	public R getApiTreeByUserId(@RequestParam Integer userId) {
		return apiService.getApiTreeByUserId(userId);
	}
	
	@ApiOperation("根据用户名获取权限")
	@GetMapping("/getApisByUsername")
	public R getApisByUsername(@PathParam("username") String username) {
		return apiService.getApisByUsername(username);
	}
}

