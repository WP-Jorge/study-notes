package com.example.oauth2_demo.service.impl;

import com.example.oauth2_demo.entity.Permission;
import com.example.oauth2_demo.mapper.PermissionMapper;
import com.example.oauth2_demo.service.PermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
	
	@Override
	public List<Permission> getPermissionByUsername(String username) {
		List<Permission> permission = baseMapper.getPermissionByUsername(username);
		return permission;
	}
}
