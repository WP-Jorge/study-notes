package com.example.dashboard9990;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrixDashboard // 开启仪表盘
public class Day37SpringCloud07Dashboard99903Application {

    public static void main(String[] args) {
        SpringApplication.run(Day37SpringCloud07Dashboard99903Application.class, args);
    }
}
