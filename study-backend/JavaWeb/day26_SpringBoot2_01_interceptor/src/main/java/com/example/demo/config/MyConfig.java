package com.example.demo.config;

import com.example.demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 实现WebMvcConfigurer接口
 */

@Configuration
public class MyConfig implements WebMvcConfigurer {
	// 添加配置拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor())
				// 配置拦截那些路径
				.addPathPatterns("/**") // 所有请求都被拦截，包括静态资源
				// 添加放行路径
				// 添加静态资源访问路径
				.excludePathPatterns("/", "/static/**", "/login", "/css/**", "/js/**", "/static/**");
				// 可以去application.properties配置文件中设置静态资源前缀,同样也需要每个都设置一遍，麻烦
	}
}

