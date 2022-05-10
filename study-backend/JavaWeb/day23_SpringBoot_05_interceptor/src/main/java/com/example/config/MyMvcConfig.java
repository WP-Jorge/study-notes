package com.example.config;

import com.example.components.HandleInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns("/**") 添加拦截地址 “/**”表示拦截所有请求
		// excludePathPatterns("/index.html", "/", "/login") 排除"/index.html", "/", "/login"的拦截
		registry.addInterceptor(new HandleInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/index.html", "/", "/login", "/webjars/**", "/static/**", "/asserts/**");
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**", "/images/**", "/css/**", "/js/**", "/fonts/**", "/font-awesome/**")
				.addResourceLocations(
						"classpath:/META-INF/resources/webjars/",
						"classpath:/static/**"
				);
	}
}
