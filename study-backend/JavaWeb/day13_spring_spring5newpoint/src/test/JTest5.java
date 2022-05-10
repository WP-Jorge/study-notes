package test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import service.UserService;

// 第一种
// @ExtendWith(SpringExtension.class)
// @ContextConfiguration("classpath:bean1.xml")

// 第二种
@SpringJUnitConfig(locations = "classpath:bean1.xml")
public class JTest5 {
	@Autowired
	private UserService userService;
	
	@Test
	public void test1() {
		userService.accountMoney();
	}
	
}
