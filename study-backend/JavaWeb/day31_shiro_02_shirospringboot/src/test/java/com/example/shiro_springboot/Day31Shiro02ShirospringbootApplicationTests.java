package com.example.shiro_springboot;

import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day31Shiro02ShirospringbootApplicationTests {
	
	@Autowired
	UserServiceImpl userService;
	@Test
	void contextLoads() {
		User user = userService.findRolesByUsername("野猪乔治");
		System.out.println(user);
	}
	
	@Test
	void test() {
		System.out.println(userService.getUser());
	}
	
	@Test
	void test1() {
		System.out.println(userService.findByUsername("野猪乔治"));
	}

}
