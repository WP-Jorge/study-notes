package com.example.eurekaclient8888;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 表示这是一个客户端
public class Day37SpringCloud03Eurekaclient88881Application {

	public static void main(String[] args) {
		SpringApplication.run(Day37SpringCloud03Eurekaclient88881Application.class, args);
	}

}
