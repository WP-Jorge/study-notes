package com.example.users9999;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 启动服务注册和服务的发现功能 新版本默认开启
@EnableFeignClients // 开启openfeign的支持
public class Day37SpringCloud05Users99993Application {

	public static void main(String[] args) {
		SpringApplication.run(Day37SpringCloud05Users99993Application.class, args);
	}

}
