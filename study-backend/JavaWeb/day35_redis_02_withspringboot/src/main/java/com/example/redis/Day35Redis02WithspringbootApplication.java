package com.example.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.redis.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class Day35Redis02WithspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day35Redis02WithspringbootApplication.class, args);
	}

}
