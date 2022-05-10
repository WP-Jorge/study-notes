package com.example.mps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mps.dao.Userdao;
import com.example.mps.pojo.User;
import com.example.mps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<Userdao, User> implements UserService {
	
	// @Autowired
	// Userdao userdao;
	@Override
	public User getUser(Integer id) {
		return baseMapper.getUser(id);
	}
}
