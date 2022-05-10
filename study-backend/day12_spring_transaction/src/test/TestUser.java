package test;

import config.TxConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class TestUser {
	@Test
	public void testAccountAno() {
		ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = context.getBean(UserService.class);
		int i = userService.accountMoney("张三", "李四", 10.0);
		System.out.println(i);
	}
}
