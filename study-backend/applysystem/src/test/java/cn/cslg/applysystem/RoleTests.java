package cn.cslg.applysystem;

import cn.cslg.applysystem.pojo.dto.AddRoleDTO;
import cn.cslg.applysystem.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleTests {
	
	@Autowired
	private RoleService roleService;
	
	@Test
	void testAddRole() {
		AddRoleDTO addRoleDTO = new AddRoleDTO();
		addRoleDTO.setRoleName("teacher");
		int i = roleService.addRole(addRoleDTO);
		if (i != 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
}
