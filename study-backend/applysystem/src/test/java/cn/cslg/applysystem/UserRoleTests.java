package cn.cslg.applysystem;

import cn.cslg.applysystem.pojo.dto.AddUserRoleDTO;
import cn.cslg.applysystem.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRoleTests {
	
	@Autowired
	UserRoleService userRoleService;
	
	@Test
	void testAddUserRole() {
		AddUserRoleDTO addUserRoleDTO = new AddUserRoleDTO();
		addUserRoleDTO.setUserId(2);
		addUserRoleDTO.setRoleId(5);
		int i = userRoleService.addUserRole(addUserRoleDTO);
		System.out.println(i);
	}
	
}
