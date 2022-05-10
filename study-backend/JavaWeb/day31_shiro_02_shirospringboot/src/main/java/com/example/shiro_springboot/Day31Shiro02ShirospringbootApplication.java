package com.example.shiro_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 自动管理事务
@EnableTransactionManagement
@MapperScan("com.example.shiro_springboot.mapper")
@SpringBootApplication
public class Day31Shiro02ShirospringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day31Shiro02ShirospringbootApplication.class, args);
	}

}
