package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddRoleDTO;
import com.example.boxmusic.pojo.dto.UpdateRoleDTO;
import com.example.boxmusic.pojo.entity.Role;
import com.example.boxmusic.mapper.RoleMapper;
import com.example.boxmusic.pojo.entity.User;
import com.example.boxmusic.pojo.vo.RoleVO;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	
	@Autowired
	JsonUtil jsonUtil;
	
	@Override
	public List<RoleVO> getRolesWithUsername(String username) {
		List<RoleVO> roles = baseMapper.getRolesWithUsername(username);
		return roles;
	}
	
	@Override
	public R getRolesByRoleNamePage(Page<Map<String, Object>> page, String roleName) {
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		if (!"".equals(roleName) && roleName != null) {
			queryWrapper.like("role_name", roleName);
		}
		Page<Map<String, Object>> rolePages = baseMapper.selectMapsPage(page, queryWrapper);
		rolePages.setRecords(jsonUtil.transformPages(rolePages, RoleVO.class));
		return R.successPage("获取用户成功", rolePages);
	}
	
	@Override
	public R deleteRolesByRoleIds(List<Long> roleIds) {
		int count = baseMapper.deleteBatchIds(roleIds);
		if (count > 0) {
			return R.success("删除角色成功");
		}
		return R.error("删除角色失败");
	}
	
	@Override
	public R addUser(AddRoleDTO addRoleDTO) {
		try {
			Role role = new Role();
			BeanUtils.copyProperties(addRoleDTO, role);
			int i = baseMapper.insert(role);
			if (i > 0) {
				return R.success("添加角色成功");
			}
			return R.error("添加角色失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，角色名已存在");
		}
	}
	
	@Override
	public R updateRole(UpdateRoleDTO updateRoleDTO) {
		try {
			Role role = new Role();
			BeanUtils.copyProperties(updateRoleDTO, role);
			int i = baseMapper.updateById(role);
			if (i > 0) {
				return R.success("更新角色成功");
			}
			return R.error("更新角色失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，角色名已存在");
		}
	}
}
