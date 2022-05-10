package com.example.jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.jwt.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class Day32Jwt01StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day32Jwt01StartApplication.class, args);
	}

}
