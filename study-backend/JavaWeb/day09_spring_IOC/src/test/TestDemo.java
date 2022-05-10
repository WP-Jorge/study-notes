package test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.Book;
import spring.Order;
import spring.Student;
import spring.User;

public class TestDemo {
	@Test
	public void testUser() {
		// 1、加载spring配置文件（使用ApplicationContext，在创建时就加载）
		//      ClassPathXmlApplicationContext相对src下的路径
		//      FileSystemXmlApplicationContext相对盘符下的绝对路径
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		
		// 2、获取创建的对象
		User user = context.getBean("user", User.class);
		
		// 3、检测结果
		System.out.println(user);
		user.add();
	}
	
	@Test
	public void testBook() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		
		Book book = context.getBean("book", Book.class);
		
		System.out.println(book);
	}
	
	@Test
	public void testOrder() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		
		Order order = context.getBean("order", Order.class);
		
		System.out.println(order);
	}
	
	@Test
	public void testStudet() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		Student student = context.getBean("student", Student.class);
		System.out.println(student);
	}
	
	@Test
	public void testStudet2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
		Student student = context.getBean("student2", Student.class);
		System.out.println(student);
	}
}
