package com.example.orderapp.controller.patterncontroller;

import com.example.orderapp.data.OrderData;
import com.example.orderapp.patterns.decorator.MakeMilTea;
import com.example.orderapp.patterns.decorator.milktea.MilkTea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/8 21:23
 */
@RestController
@RequestMapping("/decoratorPattern")
public class DecoratorPatternController {
	@Autowired
	private OrderData orderData;
	
	@PostMapping("/addMilkTea")
	public Map addMilkTea(@RequestBody Map<String, Object> milkTeaMap) {
		MilkTea milkTea = MakeMilTea.makeMilkTea(milkTeaMap);
		HashMap<String, Object> map = new HashMap<>();
		map.put("description", milkTea.getDescription());
		map.put("price", milkTea.getDishesPrice());
		HashMap<String, Object> milkTea1 = (HashMap<String, Object>) milkTeaMap.get("milkTea");
		String milkName = (String) milkTea1.get("dishesName");
		map.put("name", milkName);
		orderData.addOrder(map);
		
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", 200);
		resultMap.put("msg", "添加成功，待付款后由师傅为您制作！");
		return resultMap;
	}
	
	@PostMapping("/addSealedFood")
	public void addSealedFood(@RequestBody Map<String, Object> sealedFood) {
		orderData.addOrder(sealedFood);
	}
}
