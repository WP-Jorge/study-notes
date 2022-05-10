package com.example.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.example.shiro.mapper")
// 自动管理事务
@EnableTransactionManagement
@SpringBootApplication
public class ShiroApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}
	
}
