package com.example.users9999.fallback;

import com.example.users9999.clients.ProductClient;
import com.example.users9999.entity.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductClientFallBack implements ProductClient {
	
	private Map<String, Object> result = new HashMap<>();
	
	@Override
	public String showMsg() {
		return "当前服务已被降级";
	}
	
	@Override
	public Map<String, Object> findAll() {
		result.put("status", false);
		result.put("msg", "当前查询所有不可用，服务已被降级");
		return result;
	}
	
	@Override
	public Map<String, Object> findOne(String productId) {
		result.put("status", false);
		result.put("msg", "当前查询单品不可用，服务已被降级");
		return result;
	}
	
	@Override
	public Map<String, Object> save(String name) {
		result.put("status", false);
		result.put("msg", "当前保存不可用，服务已被降级");
		return result;
	}
	
	@Override
	public Map<String, Object> update(Product product) {
		result.put("status", false);
		result.put("msg", "当前更新不可用，服务已被降级");
		return result;
	}
}
