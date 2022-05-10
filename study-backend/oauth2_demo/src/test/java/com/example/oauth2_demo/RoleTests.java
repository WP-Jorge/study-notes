package com.example.oauth2_demo;

import com.example.oauth2_demo.entity.Role;
import com.example.oauth2_demo.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleTests {
	
	@Autowired
	private RoleService roleService;
	
	@Test
	void addRoleTest() {
		Role role = new Role();
		role.setRole("superAdmin");
		boolean save = roleService.save(role);
		if (save) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
}
