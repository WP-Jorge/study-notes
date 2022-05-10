package com.example.users9099_2.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Bean
	@LoadBalanced // 代表创建一个带有负载均衡的RestTemplate对象
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
