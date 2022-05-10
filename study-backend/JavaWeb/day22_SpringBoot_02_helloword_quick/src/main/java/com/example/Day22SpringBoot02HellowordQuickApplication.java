package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

// @ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class Day22SpringBoot02HellowordQuickApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Day22SpringBoot02HellowordQuickApplication.class, args);
	}
	
}
