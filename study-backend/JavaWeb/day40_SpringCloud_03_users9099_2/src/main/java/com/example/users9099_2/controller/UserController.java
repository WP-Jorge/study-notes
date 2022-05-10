package com.example.users9099_2.controller;


import com.example.users9099_2.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
	
	@Value("${server.port}")
	private int port;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ProductClient productClient;
	
	@GetMapping("/user/getProductInfo")
	public Map<String, Object> getProductinfo(@RequestParam("productId") String productId) {
		// 1、第一种调用方式：restTemplate直接调用 (不推荐)
		// RestTemplate restTemplate = new RestTemplate();
		// String forObject = restTemplate.getForObject("http://localhost:9098/product/find?id=" + productId, String.class);
		
		// 2、第二种调用方式：restTemplate + ribbon 负载均衡客户端 DiscoveryClient LoadBalanceClient 注解形式@LoadBalanceClient 自己实现负载均衡(不推荐)
		// List<ServiceInstance> products = discoveryClient.getInstances("products");
		// for (ServiceInstance product : products) {
		// 	log.info("服务地址：[{}]", product.getUri());
		// }
		// ServiceInstance product = loadBalancerClient.choose("products");
		// log.info("当前处理服务负载均衡客户端主机为：[{}]", product.getUri());
		// String forObject1 = restTemplate.getForObject(product.getUri() + "/product/find?id=" + productId, String.class);
		
		// 3、第三种方式自定一一个带有负载均衡的RestTemplate的配置类 实现负载均衡
		// String forObject2 = restTemplate.getForObject("http://products/product/find?id=" + productId, String.class);
		
		// 4、第四种方式：使用openfeign (推荐)（openfeign 的具体操作如前几期的实现）(网关也如前几期的实现)
		Map<String, Object> stringObjectMap = productClient.find(productId);
		
		log.info("返回的信息：[{}]", stringObjectMap);
		return stringObjectMap;
	}
}
