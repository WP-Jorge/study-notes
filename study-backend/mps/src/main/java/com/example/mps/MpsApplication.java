package com.example.mps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mps.dao")
@SpringBootApplication
public class MpsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MpsApplication.class, args);
	}
	
}
