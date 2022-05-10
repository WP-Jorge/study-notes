package com.example.config;

import com.example.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration告诉容器这是个配置类，来替代之前的beans.xml 在配置文件中用<bean><bean/>标签添加组件
 */
@Configuration
public class MyAppConfig {
	// 将方法的返回值添加到容器中;容器中这个组件默认的id就是方法名
	@Bean
	public HelloService helloService() {
		System.out.println("配置类@Bean给容器添加组件了");
		return new HelloService();
	}
}
