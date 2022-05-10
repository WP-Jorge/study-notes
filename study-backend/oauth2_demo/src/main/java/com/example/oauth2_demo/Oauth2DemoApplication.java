package com.example.oauth2_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.oauth2_demo.mapper")
public class Oauth2DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2DemoApplication.class, args);
	}
	
}
