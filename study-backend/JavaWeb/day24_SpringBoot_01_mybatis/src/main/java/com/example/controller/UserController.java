package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/queryUserList")
	public List<User> queryUser() {
		List<User> users = userMapper.queryUser();
		for (User user : users) {
			System.out.println(user);
		}
		return users;
	}
	
	@GetMapping("/queryUserById")
	public User queryUserById(Integer id) {
		User user = userMapper.queryUserById(1);
		return user;
	}
	
	@GetMapping("/addUser")
	public int addUser(User user) {
		int i = userMapper.addUser(user);
		return i;
	}
	
	@GetMapping("/updateUser")
	public int updateUser(User user) {
		int i = userMapper.updateUser(user);
		return i;
	}
	
	@GetMapping("/deleteUser")
	public int deleteUser(Integer id) {
		int i = userMapper.deleteUser(id);
		return i;
	}
	
}
