package com.example.eurekaserver8761;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 代表这是一个eure服务注册中心的服务端
public class Day37SpringCloud02Eurekaserver8761Application {

	public static void main(String[] args) {
		SpringApplication.run(Day37SpringCloud02Eurekaserver8761Application.class, args);
	}

}
