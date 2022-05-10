package com.example.users9999.controller;

import com.example.users9999.clients.ProductClient;
import com.example.users9999.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class TestFeignController {
	
	@Autowired
	private ProductClient productClient;
	
	@GetMapping("/feign/test")
	public String test() {
		log.info("进入测试feign，调用方法");
		String s = productClient.showMsg();
		Map<String, Object> all = productClient.findAll();
		log.info("调用商品服务，返回信息[{}]",s);
		log.info("调用商品服务，返回信息[{}]",all);
		return s;
	}
	
	@GetMapping("/feign/test1")
	public Map<String, Object> test1(String id) {
		log.info("用来测试openfeign的get方式传递参数");
		Map<String, Object> one = productClient.findOne(id);
		log.info("调用的放回信息[{}]", one);
		return one;
	}
	
	@GetMapping("/feign/test2")
	public Map<String, Object> test2(String name) {
		log.info("用来测试openfeign的post方式传递参数");
		Map<String, Object> one = productClient.save(name);
		log.info("调用的放回信息[{}]", one);
		return one;
	}
	
	@GetMapping("/feign/test3")
	public Map<String, Object> test3(Product product) {
		log.info("接收到的商品信息：[{}]", product);
		Map<String, Object> update = productClient.update(product);
		log.info("返回的信息：[{}]", update);
		return update;
	}
}
