package com.example.products9998.controller;

import com.example.products9998.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
// @DefaultProperties(defaultFallback = "fallBackMothod") // 指定这个类全局熔断方法
public class ProductController {
	
	@Value("${server.port}")
	private int port;
	
	@GetMapping("/product/showMsg")
	public String showMsg() {
		log.info("进入商品服务，展示商品信息");
		return "进入商品服务，展示商品信息，当前服务端口：" + port;
	}
	
	@GetMapping("/product/findAll")
	public Map<String, Object> findAll() {
		Map<String, Object> map = new HashMap<>();
		log.info("进入商品服务，查询所有商品信息");
		map.put("status", true);
		map.put("msg", "查询所有商品信息成功， 当前端口：" + port);
		return map;
	}
	
	@GetMapping("/product/findOne")
	public Map<String, Object> findOne(@RequestParam("productId")  String productId) throws InterruptedException {
		Thread.sleep(2000);
		HashMap<String, Object> map = new HashMap<>();
		log.info("接收商品服务，接收商品id为：[{}]", productId);
		map.put("status", true);
		map.put("msg", "根据商品id查询商品id成功，当前服务端口：" + port);
		map.put("productId", productId);
		return map;
	}
	
	@PostMapping("/product/save")
	public Map<String, Object> save(@RequestParam("name")  String name) {
		HashMap<String, Object> map = new HashMap<>();
		log.info("接收商品服务，接收商品name为：[{}]", name);
		map.put("status", true);
		map.put("msg", "根据商品id查询商品id成功，当前服务端口：" + port);
		map.put("name", name);
		return map;
	}
	
	@PostMapping("/product/update")
	public Map<String, Object> update(@RequestBody Product product) {
		HashMap<String, Object> map = new HashMap<>();
		log.info("接收商品服务，接收商品product为：[{}]", product);
		map.put("status", true);
		map.put("msg", "根据商品id查询商品id成功，当前服务端口：" + port);
		map.put("product", product);
		return map;
	}
	
	@GetMapping("/product/break")
	// @HystrixCommand(fallbackMethod = "testBreakFallBack") // 熔断注解 fallbackMethod为处理熔断的的方法
	@HystrixCommand(defaultFallback = "testBreakFallBack")
	public String testBreak(Integer id) {
		if (id < 0) {
			throw new RuntimeException("非法参数，id不能小于零");
		}
		return "访问成功，当前的id为：" + id;
	}
	// 触发熔断时的fallback方法
	// public String testBreakFallBack(Integer id) {
	// 	return "当前传入的参数id：" + id + " 不是有效参数，触发熔断";
	// }
	// 默认熔断方法
	public String testBreakFallBack() {
		return "服务不可用，触发熔断";
	}
}
