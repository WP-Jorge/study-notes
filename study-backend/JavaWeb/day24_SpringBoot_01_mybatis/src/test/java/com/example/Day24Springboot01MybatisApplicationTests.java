package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class Day24Springboot01MybatisApplicationTests {
	
	@Autowired
	DataSource dataSource;
	
	@Test
	void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());
		System.out.println(dataSource.getConnection());
		System.out.println();
	}
	
}
