package com.example.oauth2_demo;

import com.example.oauth2_demo.entity.User;
import com.example.oauth2_demo.service.UserService;
import com.example.oauth2_demo.utils.BCryptPasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTests {
	
	@Autowired
	BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
	@Autowired
	UserService userService;
	
	// 测试密码加密及验证
	@Test
	void contextLoads() {
		String encode = bCryptPasswordEncoderUtil.encode("111111");
		System.out.println(encode);
		boolean matches = bCryptPasswordEncoderUtil.matches("111111", encode);
		System.out.println(matches);
	}
	
	// 测试通过自实现mapper.xml的添加方法
	@Test
	void testAddUser() {
		User user = new User();
		user.setUsername("王五");
		user.setPassword(bCryptPasswordEncoderUtil.encode("111111"));
		userService.addUser(user);
	}

	// 测试自带的插入方法
	@Test
	void testInsert() {
		User user = new User();
		user.setUsername("Z09418299");
		user.setPassword(bCryptPasswordEncoderUtil.encode("111111"));
		// userService.insert(user);
		userService.save(user);
	}
	
	// 逻辑删除
	@Test
	void testDelete() {
		boolean b = userService.removeById(23);
		if (b) {
			System.out.println("逻辑删除成功！");
		} else {
			System.out.println("逻辑删除失败！");
		}
	}
	
	@Test
	void testSearch() {
		System.out.println("############@@@@@@@@@@@@@@@@##################@@@@@@@@@@@@@@@");
		userService.getUserByUsername("Z09418233");
		System.out.println("############@@@@@@@@@@@@@@@@##################@@@@@@@@@@@@@@@");
		userService.getUserByUsername("Z09418233");
	}
}
