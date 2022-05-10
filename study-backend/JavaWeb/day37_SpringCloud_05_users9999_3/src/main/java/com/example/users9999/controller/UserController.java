package com.example.users9999.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	LoadBalancerClient loadBalancerClient;
	@Autowired
	RestTemplate restTemplate; // 具有负载均衡的restTemplate
	
	@GetMapping("/user/showProductMsg")
	public String showProductMsg() {
		
		// 1、第一种服务端的调用方式 服务地址：http://localhost:9998/product/showMsg 返回值String
		RestTemplate restTemplate = new RestTemplate();
		// 参数1：请求路径 参数2：返回值类型
		String msg = restTemplate.getForObject("http://localhost:9998/product/showMsg", String.class);
		log.info("调用成功，返回的信息:[{}]", msg);
		return msg;
	}
	
	@GetMapping("/user/findAllProduct")
	public String findAllProduct() {
		log.info("进入用户服务....");
		// 1、使用restemplate方式直接调用
		// RestTemplate restTemplate = new RestTemplate();
		// String forObject = restTemplate.getForObject("http://" + randomHost() + "/product/findAll", String.class);
		// log.info("商品服务调用返回结果: [{}]", forObject);
		
		// 2、ribbon调用方式 discovery client LoadBalancerClient @LoadBalance
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("products");
		for (ServiceInstance serviceInstance : serviceInstances) {
			System.out.println(serviceInstance.getHost());
			System.out.println(serviceInstance.getPort());
		}
		
		// 3、使用LoadBalancerClient
		// ServiceInstance serviceInstance = loadBalancerClient.choose("products");
		// System.out.println(serviceInstance.getHost());
		// System.out.println(serviceInstance.getPort());
		// RestTemplate restTemplate = new RestTemplate();
		// String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/findAll";
		// String forObject = restTemplate.getForObject(url, String.class);
		
		// 4、@LoadBalance + RestTempalte
		String forObject = restTemplate.getForObject("http://products/product/findAll", String.class);
		
		return forObject;
		
	}
	
	public static String randomHost(){
		List<String> list = new ArrayList<>();
		list.add("localhost:9997");
		list.add("localhost:9998");
		int i = new Random().nextInt(2) ;
		return list.get(i);
	}
}
