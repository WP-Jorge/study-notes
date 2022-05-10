package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// 使用WebMvcConfigurationSupport可以扩展springmvc功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {
	public void addViewControllers(ViewControllerRegistry registry) {
		// 浏览器发送/example请求也来到success页面
		registry.addViewController("/example").setViewName("success");
	}
}
