package com.example.oauth2_demo.service.impl;

import com.example.oauth2_demo.entity.Role;
import com.example.oauth2_demo.mapper.RoleMapper;
import com.example.oauth2_demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo_team
 * @since 2021-03-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	
	@Override
	public List<String> getRolesByUsername(String username) {
		List<String> rolesByUsername = baseMapper.getRolesByUsername(username);
		return rolesByUsername;
	}
}
