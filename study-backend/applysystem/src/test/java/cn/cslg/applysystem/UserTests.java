package cn.cslg.applysystem;

import cn.cslg.applysystem.service.UserService;
import cn.cslg.applysystem.pojo.vo.UserWithRoleVO;
import cn.cslg.applysystem.pojo.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {
	
	@Autowired
	private UserService userService;
	
	@Test
	void testGetUserInfo() {
		UserVO userinfo = userService.getUserInfoByUsername("Z9418233");
		System.out.println(userinfo);
	}
	
	@Test
	void testGetAllUsersWithPage() {
		Page<Map> page = new Page<>(1, 10);
		IPage<UserWithRoleVO> users = userService.getAllUsersWithPage(page);
		System.out.println(users);
	}
	
	@Test
	void testGetAllUsers() {
		List<UserVO> users = userService.getAllUsers();
		System.out.println(users);
	}
}
