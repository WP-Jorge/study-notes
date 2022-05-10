package test;

import bean.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmp {
	@Test
	public void testEmp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
		Emp emp = context.getBean("emp", Emp.class);
		System.out.println(emp);
	}
	
	@Test
	public void testEmp2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
		Emp emp = context.getBean("emp2", Emp.class);
		System.out.println(emp);
	}
}
