package com.example.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mybatisplus.mapper")
@SpringBootApplication
public class Day28SpringBoot201MybatisplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day28SpringBoot201MybatisplusApplication.class, args);
	}

}
