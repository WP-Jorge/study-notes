package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddRoleDTO;
import cn.cslg.applysystem.pojo.dto.UpdateRoleDTO;
import cn.cslg.applysystem.pojo.entity.Role;
import cn.cslg.applysystem.service.RoleService;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.pojo.vo.RoleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/role")
@Validated
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加角色")
	@PostMapping("/addRole")
	public R addUser(@Valid @RequestBody AddRoleDTO addRoleDTO) {
		try {
			int i = roleService.addRole(addRoleDTO);
			if (i != 0) {
				return R.ok("添加角色成功");
			}
			return R.ok("添加角色失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加角色失败，键名重复，请检查数据是否正确");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，请检查数据是否正确");
		}
	}
	
	@ApiOperation("获取全部角色")
	@GetMapping("/getAllRolesWithPage")
	public R getAllRolesWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                             @RequestParam(defaultValue = "10") Integer pageSize) {
		IPage<RoleVO> roles = roleService.getAllRolesWithPage(new Page(currentPage, pageSize));
		return R.ok("roles", Collections.singletonList(roles));
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据角色ID删除角色")
	@DeleteMapping("/deleteRoleByRoleId")
	public R deleteRoleByRoleId(@PathParam("roleId") @NotNull(message = "角色ID不能为空") Integer roleId) {
		try {
			boolean isDeleted = roleService.removeById(roleId);
			if (isDeleted) {
				return R.ok("删除角色成功");
			}
			return R.ok("删除角色失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量删除角色")
	@DeleteMapping("/deleteRolesByRoleIds")
	public R deleteRolesByRoleIds(@RequestBody List<Integer> roleIdList) {
		try {
			boolean isDeleted = roleService.removeByIds(roleIdList);
			if (isDeleted) {
				return R.ok("批量删除角色成功");
			}
			return R.ok("批量删除角色失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，请检查数据是否正确");
		}
	}
	
	@ApiOperation("根据角色名称模糊查询角色")
	@GetMapping("/getRolesByRoleNameWithPage")
	public R getRolesByRoleNameWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                             @RequestParam(defaultValue = "10") Integer pageSize,
	                                    String roleName) {
		IPage<RoleVO> roles = roleService.getRolesByRoleNameWithPage(new Page(currentPage, pageSize), roleName);
		return R.ok("roles", Collections.singletonList(roles));
	}
	
	@ApiOperation("修改角色信息")
	@PostMapping("/updateRole")
	public R updateRole(@Valid @RequestBody UpdateRoleDTO updateRoleDTO) {
		Role role = new Role();
		role.setRoleName(updateRoleDTO.getRoleName());
		boolean isUpdate = roleService.updateById(role);
		if (isUpdate) {
			return R.ok("更新角色成功");
		}
		return R.ok("更新角色失败");
	}
}

