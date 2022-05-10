package com.example.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 直接开启包扫描，就不用写@mapper了
// @MapperScan("com.example.db.dao")
@SpringBootApplication
public class Day27SpringBoot201DbApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day27SpringBoot201DbApplication.class, args);
	}

}
