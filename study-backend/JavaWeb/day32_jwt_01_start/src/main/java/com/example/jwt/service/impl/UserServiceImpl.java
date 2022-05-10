package com.example.jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jwt.entity.User;
import com.example.jwt.mapper.UserMapper;
import com.example.jwt.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Override
	public User login(User user) {
		User userDB = baseMapper.login(user);
		if (userDB != null) {
			return userDB;
		}
		throw new RuntimeException("登陆失败，用户名或密码错误");
	}
}
