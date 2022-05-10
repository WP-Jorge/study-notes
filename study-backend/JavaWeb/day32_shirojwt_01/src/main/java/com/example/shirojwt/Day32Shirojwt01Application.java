package com.example.shirojwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// @EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.example.shirojwt.mapper")
public class Day32Shirojwt01Application {

	public static void main(String[] args) {
		SpringApplication.run(Day32Shirojwt01Application.class, args);
	}

}
