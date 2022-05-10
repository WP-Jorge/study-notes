package test;

import collectiontype.Course;
import factorybean.MyBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyBean {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean6.xml");
		Course course = context.getBean("mybean", Course.class);
		System.out.println(course);
	}
}
