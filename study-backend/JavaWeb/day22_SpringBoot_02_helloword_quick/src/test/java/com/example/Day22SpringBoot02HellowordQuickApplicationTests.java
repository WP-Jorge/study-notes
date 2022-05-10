package com.example;

import com.example.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Day22SpringBoot02HellowordQuickApplicationTests {
	
	@Autowired
	Person person;
	
	@Autowired
	ApplicationContext ioc;
	
	@Test
	void contextLoads() {
		System.out.println(person);
	}
	
	@Test
	public void testHelloService() {
		boolean helloService = ioc.containsBean("helloService");
		System.out.println(helloService);
	}
}
