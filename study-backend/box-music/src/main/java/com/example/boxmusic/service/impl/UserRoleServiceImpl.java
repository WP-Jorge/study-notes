package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.entity.RoleApi;
import com.example.boxmusic.pojo.entity.UserRole;
import com.example.boxmusic.mapper.UserRoleMapper;
import com.example.boxmusic.pojo.vo.UserWithRoleVO;
import com.example.boxmusic.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.R;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
	
	@Override
	public R getUserRolesByUsernamePage(Page<Map<String, Object>> page, String username) {
		IPage<UserWithRoleVO> userRoleList = baseMapper.getUserRolesWithUsernamePage(page, username);
		return R.successPage("获取用户角色成功", userRoleList);
	}
	
	@Override
	public R updateUserRolesByUserId(Long userId, List<Long> roleIds) {
		QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		baseMapper.delete(queryWrapper);
		if (roleIds.size() == 0) {
			return R.success("更新用户角色成功");
		}
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (Long roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		}
		boolean b = saveBatch(userRoles);
		if (b) {
			return R.success("更新用户角色成功");
		}
		return R.error("更新用户角色失败");
	}
	
	@Override
	public Boolean addUserRoles(Long userId, List<Long> roleIds) {
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (Long roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		}
		return saveBatch(userRoles);
	}
}
