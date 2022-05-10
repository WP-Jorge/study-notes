package com.example.shiro_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shiro_springboot.entity.Perms;
import com.example.shiro_springboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends IService<User> {
	int insert(User user);
	User findByUsername(String username);
	User findRolesByUsername(String username);
	User getUser();
	List<Perms> findPermsByRoleId(Integer roleId);
}
