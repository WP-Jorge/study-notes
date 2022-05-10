package com.example.jwtandshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.jwtandshiro.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class Day33Jwtandshiro01Application {

	public static void main(String[] args) {
		SpringApplication.run(Day33Jwtandshiro01Application.class, args);
	}

}
