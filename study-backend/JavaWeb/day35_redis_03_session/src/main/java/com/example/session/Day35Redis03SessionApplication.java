package com.example.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
// 将整个应用中使用session的数据全部交给redis处理
@EnableRedisHttpSession
public class Day35Redis03SessionApplication { // 要打包时继承这个 SpringBootServletInitializer

	public static void main(String[] args) {
		SpringApplication.run(Day35Redis03SessionApplication.class, args);
	}
	
	// 然后复写这个
	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	// 	return builder.sources(Day35Redis03SessionApplication.class);
	// }
}
