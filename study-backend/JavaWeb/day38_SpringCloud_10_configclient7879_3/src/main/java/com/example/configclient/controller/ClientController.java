package com.example.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 刷新当前的代码区域，有@Value("${server.port}")的地方要加上才能实时更新
public class ClientController {
	
	@Value("${server.port}")
	private int port;
	@Value("${name}")
	private String name;
	
	@GetMapping("/client/init")
	public String init() {
		return "当前服务的端口为：" + port + "，姓名：" + name;
	}
	
}
