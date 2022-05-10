package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // 开启配置服务器
public class Day38SpringCloud09Configserver78783Application {

    public static void main(String[] args) {
        SpringApplication.run(Day38SpringCloud09Configserver78783Application.class, args);
    }

}
