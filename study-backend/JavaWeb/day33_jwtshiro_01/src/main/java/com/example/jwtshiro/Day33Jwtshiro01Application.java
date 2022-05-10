package com.example.jwtshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 自动管理事务
@EnableTransactionManagement
@MapperScan("com.example.jwtshiro.mapper")
@SpringBootApplication
public class Day33Jwtshiro01Application {

	public static void main(String[] args) {
		SpringApplication.run(Day33Jwtshiro01Application.class, args);
	}

}
