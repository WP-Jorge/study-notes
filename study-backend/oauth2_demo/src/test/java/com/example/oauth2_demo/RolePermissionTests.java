package com.example.oauth2_demo;

import com.example.oauth2_demo.entity.RolePermission;
import com.example.oauth2_demo.entity.UserRole;
import com.example.oauth2_demo.service.RolePermissionService;
import com.example.oauth2_demo.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RolePermissionTests {
	
	@Autowired
	RolePermissionService rolePermissionService;
	
	@Test
	void addUserRoleTest() {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleId(2);
		rolePermission.setPermissionId(2);
		boolean save = rolePermissionService.save(rolePermission);
		if (save) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
}
