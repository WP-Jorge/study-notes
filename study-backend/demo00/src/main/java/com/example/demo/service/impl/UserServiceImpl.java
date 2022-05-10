package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	UserService userService;
	public void insert(User user) {
		// 处理业务调用dao
		// 1.生成随机盐
		String salt = SaltUtil.getSalt(8);
		// 2.将随机盐保存到数据库
		user.setSalt(salt);
		// 3.明文密码进行调用md5 + salt + hash散列
		Md5Hash hash = new Md5Hash(user.getPassword(), user.getSalt(), 1024);
		user.setPassword(hash.toHex());
		userService.save(user);
	}
}
