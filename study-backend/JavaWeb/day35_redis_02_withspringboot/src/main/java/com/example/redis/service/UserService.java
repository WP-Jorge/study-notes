package com.example.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.redis.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
	List<User> findAll();
	User findById(Integer id);
	void delete(Integer id);
	void saveUser(User user);
	void updateUser(User user);
}
