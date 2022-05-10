package com.example.configclient9066_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope // 在这个注解中的代码启动自动刷新
public class TestController {
	
	@Value("${user.name}")
	private String name;
	
	@GetMapping("/test/test")
	public String test() {
		log.info("当前获取配置中的值name为：[{}]", name);
		return "当前获取配置中的值name为：" + name;
	}
	
}
