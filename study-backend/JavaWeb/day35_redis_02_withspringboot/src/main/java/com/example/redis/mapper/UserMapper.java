package com.example.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.redis.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
	List<User> findAll();
	User findById(Integer id);
	// 增删改都会执行清空缓存操作
	void delete(Integer id);
	void saveUser(User user);
	void updateUser(User user);
}
