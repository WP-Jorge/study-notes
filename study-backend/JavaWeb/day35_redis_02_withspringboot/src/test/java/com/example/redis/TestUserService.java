package com.example.redis;

import com.example.redis.entity.User;
import com.example.redis.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestUserService {
	@Autowired
	UserServiceImpl userService;
	
	@Test
	public void testFindAll() {
		// 没有开mybatis缓存，所以这个会重复执行，也必须在User中实现Serializable，不然会报错
		userService.findAll();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		userService.findAll();
	}
	@Test
	public void testFindById() {
		// 没有开mybatis缓存，所以这个会重复执行，也必须在User中实现Serializable，不然会报错
		userService.findById(1);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		userService.findById(1);
	}
	
	@Test
	public void testDelete() {
		userService.delete(1);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	@Test
	public void testSaveUser() {
		userService.saveUser(new User(3, "野猪", 21, new Date()));
	}
	
	@Test
	public void testUpdateUser() {
		userService.updateUser(new User(3, "野猪乔治", 12, new Date()));
	}
}
