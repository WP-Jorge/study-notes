package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
public class TestMD5 {
	@Test
	public void testMD5() {
		String key = "1605065535:5662903300:com.example.redis.mapper.EmpMapper.findAll:0:2147483647:select * from emp:MybatisSqlSessionFactoryBean";
		// 利用spring框架的md5工具类
		String s = DigestUtils.md5DigestAsHex(key.getBytes());
		System.out.println(s);
	}
}
