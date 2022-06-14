package com.example.jwtandshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwtandshiro.entity.Perms;
import com.example.jwtandshiro.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
	User findRolesByUsername(String username);
	
	// 根据角色id查询权限集合
	List<Perms> findPermsByRoleId(Integer roleId);
}