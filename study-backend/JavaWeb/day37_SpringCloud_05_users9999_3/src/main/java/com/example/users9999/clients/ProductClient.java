package com.example.users9999.clients;

import com.example.users9999.entity.Product;
import com.example.users9999.fallback.ProductClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

// 调用商品服务的openfeign组件
// 用来标识当前接口是一个feign的组件 value:书写调用服务的id 就是服务的名字,fallback:fallback方法的类
@FeignClient(value = "products", fallback = ProductClientFallBack.class)
public interface ProductClient {
	
	@GetMapping("/product/showMsg")
	String showMsg();
	
	@GetMapping("/product/findAll")
	Map<String, Object> findAll();
	
	// 使用openfeign的get方式传参，参数变量必须通过@RequestParam注解进行修饰
	@GetMapping("/product/findOne")
	Map<String, Object> findOne(@RequestParam("productId") String productId);
	
	@PostMapping("/product/save")
	// 使用openfeign的post方式传参，参数变量必须通过@RequestParam注解进行修饰
	Map<String, Object> save(@RequestParam("name") String name);
	
	@PostMapping("/product/update")
	// 使用openFeign传递对象信息 时要使用@RequestBody注解，要求服务端和调用端都需要使用注解 将json转换为对象信息
	Map<String, Object> update(@RequestBody Product product);
}
