package com.example.orderapp.controller.patterncontroller;

import com.example.orderapp.data.BillData;
import com.example.orderapp.data.CookingData;
import com.example.orderapp.data.OrderData;
import com.example.orderapp.patterns.singleton.Cooker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/9 21:28
 */
@RequestMapping("/singletonPattern")
@RestController
public class SingletonPatternController {
	@Autowired
	private CookingData cookingData;
	
	@Autowired
	private OrderData orderData;
	
	@Autowired
	private BillData billData;
	
	@PostMapping("/pay")
	public Map pay(@RequestBody Map<String, Object> payMap) {
		List<Map<String, Object>> orders = (List<Map<String, Object>>) payMap.get("orders");
		System.out.println(orders);
		for (Map<String, Object> order : orders) {
			cookingData.addUnCookingOrder(order);
			billData.addBill(order);
			String name = (String) order.get("name");
			orderData.delete(name);
		}
		return new HashMap();
	}
	
	@GetMapping("/getCooked")
	public Map getCooked() {
		HashMap<String, Object> map = new HashMap<>();
		List<Map<String, Object>> cookingList = cookingData.getCookingList();
		map.put("code", 200);
		map.put("cooked", cookingList);
		return map;
	}
	
	@GetMapping("/getUnCooked")
	public Map getUnCooked() {
		HashMap<String, Object> map = new HashMap<>();
		List<Map<String, Object>> unCookingList = cookingData.getUnCookingList();
		map.put("code", 200);
		map.put("unCooked", unCookingList);
		return map;
	}
}
