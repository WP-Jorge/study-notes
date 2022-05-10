package com.example.mps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mps.pojo.User;

public interface UserService extends IService<User> {
	User getUser(Integer id);
}
