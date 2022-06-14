package com.example.boxmusic.controller;


import com.example.boxmusic.pojo.dto.UpdateApiDTO;
import com.example.boxmusic.service.ApiService;
import com.example.boxmusic.utils.R;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Validated
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	@ApiOperation("根据用户id获取权限树")
	@GetMapping("/getApiTreeByUserId")
	public R getApiTreeByUserId(@RequestParam Long userId) {
		return apiService.getApiTreeByUserId(userId);
	}
	
	@ApiOperation("根据用户名获取权限")
	@GetMapping("/getApisByUsername")
	public R getApisByUsername(@PathParam("username") String username) {
		return apiService.getApisByUsername(username);
	}
	
	@ApiOperation("获取所有权限的权限树")
	@GetMapping("/getApiTree")
	public R getApiTree() {
		return apiService.getApiTree();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新权限")
	@PostMapping("/updateApis")
	public R updateApis(@RequestBody @Valid List<UpdateApiDTO> apis) {
		return apiService.updateApis(apis);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新单条权限")
	@PostMapping("/updateApi")
	public R updateApi(@RequestBody @Valid UpdateApiDTO api) {
		return apiService.updateApi(api);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据apiId批量删除权限")
	@DeleteMapping("/deleteApisByApiIds")
	public R deleteApisByApiIds(@RequestBody List<Long> apiIds) {
		return apiService.deleteApisByApiIds(apiIds);
	}
	
}

