package com.example.shiro;

import com.example.shiro.entity.User;
import com.example.shiro.service.UserService;
import com.example.shiro.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {
	
	@Autowired
	UserServiceImpl userService;
	// UserService userService;
	// @Test
	// void contextLoads() {
	// 	User user = userService.findRolesByUsername("野猪乔治");
	// 	System.out.println(user);
	// }
	
	@Test
	void testGetUser() {
		User user = userService.getUser(1);
		System.out.println(user);
	}
}
