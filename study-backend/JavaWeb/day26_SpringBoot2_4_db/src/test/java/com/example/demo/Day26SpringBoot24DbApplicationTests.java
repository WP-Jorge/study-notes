package com.example.demo;

import com.example.demo.dao.AuthorDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day26SpringBoot24DbApplicationTests {
	@Autowired
	AuthorDao authorDao;
	
	@Test
	void contextLoads() {
		System.out.println(authorDao.getAuthor());
	}

}
