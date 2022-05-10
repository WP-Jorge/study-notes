package test;

import collectiontype.Stu;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCollectionType {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
		Stu stu = context.getBean("stu", Stu.class);
		System.out.println(stu);
	}
	
	@Test
	public void test2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
		Stu stu = context.getBean("stu", Stu.class);
		System.out.println(stu);
	}
}
