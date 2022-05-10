package com.example.db;

import com.example.db.dao.UserDao;
import com.example.db.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day27SpringBoot201DbApplicationTests {
	@Autowired
	UserDao userDao;
	@Test
	void contextLoads() {
		User user = userDao.getUser();
		System.out.println(user);
	}

}
