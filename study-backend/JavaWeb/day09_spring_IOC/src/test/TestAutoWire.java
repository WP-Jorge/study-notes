package test;

import autowire.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutoWire {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean8.xml");
		Emp emp = context.getBean("emp", Emp.class);
		System.out.println(emp);
	}
}
