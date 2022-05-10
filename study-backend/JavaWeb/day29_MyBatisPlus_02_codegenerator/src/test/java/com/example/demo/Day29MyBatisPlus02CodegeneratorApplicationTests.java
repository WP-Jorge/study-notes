package com.example.demo;

import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day29MyBatisPlus02CodegeneratorApplicationTests {

	@Autowired
	UserServiceImpl userService;
	@Test
	void contextLoads() {
		System.out.println(userService.getUser(1));
	}

}
