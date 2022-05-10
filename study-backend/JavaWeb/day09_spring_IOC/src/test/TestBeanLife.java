package test;

import beanlife.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanLife {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean7.xml");
		Orders orders = context.getBean("orders", Orders.class);
		System.out.println("第四步 获取创建出来的bean实例对象");
		System.out.println(orders);
		// 手动调用close进行销毁
		// 需要强转为ClassPathXmlApplicationContext
		((ClassPathXmlApplicationContext) context).close();
	}
}
