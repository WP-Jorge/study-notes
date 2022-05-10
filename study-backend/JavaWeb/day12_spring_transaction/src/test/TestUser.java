package test;

import config.TxConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class TestUser {
	@Test
	public void testAccount() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		UserService userService = context.getBean("userService", UserService.class);
		userService.accountMoney();
	}
	
	@Test
	public void testAccount2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
		UserService userService = context.getBean("userService", UserService.class);
		userService.accountMoney();
	}
	
	@Test
	public void testAccountAno() {
		ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = context.getBean(UserService.class);
		userService.accountMoney();
	}
}
