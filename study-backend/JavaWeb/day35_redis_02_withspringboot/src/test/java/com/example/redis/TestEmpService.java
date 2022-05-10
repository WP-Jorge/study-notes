package com.example.redis;

import com.example.redis.entity.User;
import com.example.redis.service.impl.EmpServiceImpl;
import com.example.redis.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestEmpService {
	@Autowired
	EmpServiceImpl empService;
	
	@Test
	public void testFindAll() {
		empService.findAll();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		empService.findAll();
	}
}
