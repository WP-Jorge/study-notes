package com.example.jwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jwt.entity.User;

public interface UserService extends IService<User> {
	User login(User user);
}
