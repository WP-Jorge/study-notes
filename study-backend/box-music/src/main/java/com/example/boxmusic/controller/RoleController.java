package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddRoleDTO;
import com.example.boxmusic.pojo.dto.UpdateRoleDTO;
import com.example.boxmusic.service.RoleService;
import com.example.boxmusic.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/role")
@Validated
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@ApiOperation("根据角色名模糊查询角色")
	@GetMapping("/getRolesByRoleNamePage")
	public R getRolesByRoleNamePage(@RequestParam(defaultValue = "1") Integer currentPage,
									@RequestParam(defaultValue = "10") Integer pageSize,
									@RequestParam(defaultValue = "") String roleName) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return roleService.getRolesByRoleNamePage(page, roleName);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据用户id批量删除角色")
	@DeleteMapping("/deleteRolesByRoleIds")
	public R deleteRolesByRoleIds(@RequestBody @NotEmpty(message = "角色id不能为空") List<Long> roleIds) {
		return roleService.deleteRolesByRoleIds(roleIds);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加角色")
	@PostMapping("/addRole")
	public R addUser(@RequestBody @Valid AddRoleDTO addRoleDTO) {
		return roleService.addUser(addRoleDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新角色")
	@PutMapping("/updateRole")
	public R updateRole(@RequestBody @Valid UpdateRoleDTO updateRoleDTO) {
		return roleService.updateRole(updateRoleDTO);
	}
}

