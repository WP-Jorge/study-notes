package com.example.users9099_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // 开启openfeign
public class Day40SpringCloud03Users90992Application {

    public static void main(String[] args) {
        SpringApplication.run(Day40SpringCloud03Users90992Application.class, args);
    }

}
