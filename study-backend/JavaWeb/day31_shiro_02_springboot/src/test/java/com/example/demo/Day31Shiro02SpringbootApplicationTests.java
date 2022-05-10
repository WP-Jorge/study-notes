package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day31Shiro02SpringbootApplicationTests {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserServiceImpl userService;
	// @Test
	// void contextLoads() {
	// 	User user = userService.findRolesByUsername("野猪乔治");
	// 	System.out.println(user);
	// }
	
	@Test
	void test() {
		System.out.println(userService.getUser());
	}

}
