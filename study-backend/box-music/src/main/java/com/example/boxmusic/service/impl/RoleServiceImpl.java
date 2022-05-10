package com.example.boxmusic.service.impl;

import com.example.boxmusic.pojo.entity.Role;
import com.example.boxmusic.mapper.RoleMapper;
import com.example.boxmusic.pojo.vo.RoleVO;
import com.example.boxmusic.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	
	@Override
	public List<RoleVO> getRolesWithUsername(String username) {
		List<RoleVO> roles = baseMapper.getRolesWithUsername(username);
		return roles;
	}
}
