package com.example.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mp.mapper.UserMapper;
import com.example.mp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MpApplicationTests {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	BaseMapper baseMapper;
	@Test
	void contextLoads() {
		System.out.println(userService.getUser(1));
		
	}
	
	@Test
	void test() {
		System.out.println(baseMapper.selectById(1));
	}
	
}
