package test;

import config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class TestAnnotation {
	@Test
	public void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		UserService userService = context.getBean("userService", UserService.class);
		userService.add();
	}
	
	@Test
	public void test2() {
		// 使用完全注解开发，加载配置类
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		UserService userService = context.getBean("userService", UserService.class);
		userService.add();
	}
}
