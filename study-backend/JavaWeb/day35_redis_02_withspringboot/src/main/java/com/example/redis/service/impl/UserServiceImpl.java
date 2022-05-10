package com.example.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redis.entity.User;
import com.example.redis.mapper.UserMapper;
import com.example.redis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Override
	public List<User> findAll() {
		return baseMapper.findAll();
	}
	
	@Override
	public User findById(Integer id) {
		return baseMapper.findById(id);
	}
	
	@Override
	public void delete(Integer id) {
		baseMapper.delete(id);
	}
	
	@Override
	public void saveUser(User user) {
		baseMapper.saveUser(user);
	}
	
	@Override
	public void updateUser(User user) {
		baseMapper.updateUser(user);
	}
}
