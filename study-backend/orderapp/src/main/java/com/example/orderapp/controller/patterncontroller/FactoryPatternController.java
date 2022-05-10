package com.example.orderapp.controller.patterncontroller;

import com.example.orderapp.data.OrderData;
import com.example.orderapp.patterns.factory.Dishes.Dishes;
import com.example.orderapp.patterns.factory.MakeDishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/8 21:35
 */
@RestController
@RequestMapping("/factoryPattern")
public class FactoryPatternController {
	
	@Autowired
	private MakeDishes makeDishes;
	
	@Autowired
	private OrderData orderData;
	
	@PostMapping("/addDishes")
	public Map addDishes(@RequestBody Map<String, Object> dishes) {
		Dishes dishes1 = makeDishes.makeDishs(dishes);
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", dishes1.getDishesName());
		map.put("price", dishes1.getDishesPrice());
		map.put("description", dishes1.getDishesName());
		orderData.addOrder(map);
		
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", 200);
		resultMap.put("msg", "添加成功，待付款后由师傅为您制作！");
		return resultMap;
	}
}
