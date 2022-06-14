package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.example.demo.mapper")
// 自动管理事务
@EnableTransactionManagement
@SpringBootApplication
public class Day31Shiro02SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day31Shiro02SpringbootApplication.class, args);
	}

}