package com.example.jwtandshiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jwtandshiro.entity.Perms;
import com.example.jwtandshiro.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
	int insert(User user);
	User findByUsername(String username);
	User findRolesByUsername(String username);
	List<Perms> findPermsByRoleId(Integer roleId);
	Boolean login(User user);
}
