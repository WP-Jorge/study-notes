package com.example.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
// 开启security注解
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Day33SpringSecurity01StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day33SpringSecurity01StartApplication.class, args);
	}

}
