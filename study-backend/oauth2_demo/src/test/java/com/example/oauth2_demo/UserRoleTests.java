package com.example.oauth2_demo;

import com.example.oauth2_demo.entity.UserRole;
import com.example.oauth2_demo.service.RoleService;
import com.example.oauth2_demo.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRoleTests {
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RoleService roleService;
	
	@Test
	void addUserRoleTest() {
		UserRole userRole = new UserRole();
		userRole.setUserId(3);
		userRole.setRoleId(3);
		boolean save = userRoleService.save(userRole);
		if (save) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	@Test
	void getRoles() {
		List<String> rolesByUsername = roleService.getRolesByUsername("张三");
		
		System.out.println(rolesByUsername);
	}
	
}
