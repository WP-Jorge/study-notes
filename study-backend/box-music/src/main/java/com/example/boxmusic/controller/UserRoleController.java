package com.example.boxmusic.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.UpdateRoleApiDTO;
import com.example.boxmusic.service.UserRoleService;
import com.example.boxmusic.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Validated
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@ApiOperation("根据用户名模糊查询用户角色")
	@GetMapping("/getUserRolesByUsernamePage")
	public R getUserRolesByUsernamePage(@RequestParam(defaultValue = "1") Integer currentPage,
										@RequestParam(defaultValue = "10") Integer pageSize,
										@RequestParam(defaultValue = "") String username) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return userRoleService.getUserRolesByUsernamePage(page, username);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据用户id更新用户角色")
	@PostMapping("/updateUserRolesByUserId")
	public R updateUserRolesByUserId(@RequestBody  Map<String, Object> maps) {
		Long userId = Long.parseLong(String.valueOf(maps.get("userId")));
		List<UpdateRoleApiDTO> updateRoleApiDTOList = JSON.parseArray(JSON.toJSONString(maps.get("roleApis")), UpdateRoleApiDTO.class);
		List<Long> roleIds = JSON.parseArray(JSON.toJSONString(maps.get("roleIds")), Long.class);
		return userRoleService.updateUserRolesByUserId(userId, roleIds);
	}
}

