package com.example.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mp.entity.User;
import com.example.mp.mapper.UserMapper;
import com.example.mp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	// @Autowired
	// UserMapper userMapper;
	
	@Override
	public User getUser(Integer id) {
		return baseMapper.getUser(id);
	}
}
