package com.example.mps;

import com.example.mps.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MpsApplicationTests {
	@Autowired
	UserServiceImpl userService;
	
	@Test
	void contextLoads() {
		System.out.println(userService.getUser(1));
	}
	
}
