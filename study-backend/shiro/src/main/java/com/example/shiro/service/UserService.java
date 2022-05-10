package com.example.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shiro.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
	int insert(User user);
	User findByUsername(String username);
	User findRolesByUsername(String username);
	User getUser(Integer id);
}
