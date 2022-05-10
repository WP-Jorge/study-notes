package com.example.oauth2_demo;

import com.example.oauth2_demo.entity.Permission;
import com.example.oauth2_demo.entity.Role;
import com.example.oauth2_demo.service.PermissionService;
import com.example.oauth2_demo.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PermissionTests {
	
	@Autowired
	private PermissionService permissionService;
	
	@Test
	void addRoleTest() {
		Permission permission = new Permission();
		permission.setPermission("/delete");
		boolean save = permissionService.save(permission);
		if (save) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
}
