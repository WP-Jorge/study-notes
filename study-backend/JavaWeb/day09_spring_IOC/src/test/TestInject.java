package test;

import collectiontype.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestInject {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
		Book book1 = context.getBean("book", Book.class);
		System.out.println(book1);
		// 单例模式下创建出来的两个book实例是同一个对象，在bean5中设置scope属性
		Book book2 = context.getBean("book", Book.class);
		System.out.println(book2);
	}
}
