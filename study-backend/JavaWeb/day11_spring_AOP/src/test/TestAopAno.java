package test;

import aopano.User;
import aopxml.Book;
import config.ConfigAop;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAopAno {
	@Test
	public void testAopAno() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		User user = context.getBean("user", User.class);
		user.add();
	}
	
	@Test
	public void testBookXml() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
		Book book = context.getBean("book", Book.class);
		book.buy();
	}
	
	@Test
	public void testConfigAno() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigAop.class);
		User user = context.getBean(User.class);
		user.add();
	}
}
