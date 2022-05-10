package test;

import config.Configs;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountService;

public class Test1 {
	public static final Logger log = LoggerFactory.getLogger(Test1.class);
	@Test
	public void test1() {
		ApplicationContext context = new AnnotationConfigApplicationContext(Configs.class);
		AccountService accountService = context.getBean(AccountService.class);
		accountService.transfer("张三", "李四", 10.0);
	}
}
