package com.example.mybatisplus;

import com.example.mybatisplus.domain.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class Day28SpringBoot201MybatisplusApplicationTests {
	
	@Autowired
	UserServiceImpl userService;
	

	@Test
	void contextLoads() {
		List<User> userList = userService.list();
		log.info("用户信息：{}", userList);
	}

}
