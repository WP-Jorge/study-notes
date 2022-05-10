package com.example.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mp.entity.User;
import com.example.mp.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
	User getUser(Integer id);
}
