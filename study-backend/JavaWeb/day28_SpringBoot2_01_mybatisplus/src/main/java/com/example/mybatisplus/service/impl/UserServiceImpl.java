package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.domain.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.UserService;
import org.springframework.stereotype.Component;

/**
 *  继承extends ServiceImpl<UserMapper, User>可以使用大量常用的方法
 */
@Component
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	// @Autowired
	// UserService userService;
	//
	// @Override
	// public User getUserById(Integer id) {
	// 	return userService.getUserById(id);
	// }
}
