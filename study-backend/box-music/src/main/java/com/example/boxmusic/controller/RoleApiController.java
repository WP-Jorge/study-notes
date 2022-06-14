package com.example.boxmusic.controller;


import cn.hutool.json.xml.JSONXMLParser;
import com.alibaba.fastjson.JSON;
import com.example.boxmusic.pojo.dto.UpdateRoleApiDTO;
import com.example.boxmusic.pojo.entity.RoleApi;
import com.example.boxmusic.service.RoleApiService;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
@Validated
@RestController
@RequestMapping("/roleApi")
public class RoleApiController {
	
	@Autowired
	private RoleApiService roleApiService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根新据角色id更新角色权限")
	@PostMapping("/updateRoleApisByRoleId")
	public R updateRoleApisByRoleId(@RequestBody Map<String, Object> maps) {
		// Long roleId = JSON.parseObject(JSON.toJSONString(maps.get("roleId")), Long.class);
		Long roleId = Long.parseLong(String.valueOf(maps.get("roleId")));
		List<UpdateRoleApiDTO> updateRoleApiDTOList = JSON.parseArray(JSON.toJSONString(maps.get("roleApis")), UpdateRoleApiDTO.class);
		// redisUtil.deleteByKey("com.example.boxmusic.mapper.ApiMapper");
		return roleApiService.updateRoleApisByRoleId(roleId, updateRoleApiDTOList);
	}
	
	@ApiOperation("根据角色id获取权限")
	@GetMapping("/getApisByRoleId")
	public R getApisByRoleId(@RequestParam Long roleId) {
		return roleApiService.getApisByRoleId(roleId);
	}
}

