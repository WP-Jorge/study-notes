package com.example.shirojwt;

import com.example.shirojwt.entity.User;
import com.example.shirojwt.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day32Shirojwt01ApplicationTests {

	@Autowired
	UserServiceImpl userService;
	
	@Test
	void contextLoads() {
		// User user = new User();
		// user.setPassword("111111");
		// user.setUsername("野猪乔治");
		// Boolean login = userService.login(user);
	}

}
