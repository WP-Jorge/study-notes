package com.example.orderapp.controller;

import com.example.orderapp.data.DishesData;
import com.example.orderapp.data.MilkTeaData;
import com.example.orderapp.data.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/8 18:16
 */
@RestController
@RequestMapping("/dishes")
public class OrderController {
	@Autowired
	private OrderData orderData;
	
	@GetMapping("/getAllDishes")
	public Map getAllDishes() {
		HashMap<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dishesList = DishesData.dishesList;
		map.put("dishesList", dishesList);
		map.put("code", 200);
		return map;
	}
	
	@GetMapping("/getAllMilkTeas")
	public Map getAllMilkTeas() {
		HashMap<String, Object> map = new HashMap<>();
		List<Map<String, Object>> milkTeaList = MilkTeaData.milkTeaList;
		map.put("milkTeaList", milkTeaList);
		map.put("code", 200);
		return map;
	}
	
	@GetMapping("/getAllAdds")
	public Map getAllAdds() {
		HashMap<String, Object> map = new HashMap<>();
		List<Map<String, Object>> addList = MilkTeaData.addList;
		map.put("addList", addList);
		map.put("code", 200);
		return map;
	}
	
	@GetMapping("/getAllOrders")
	public Map getAllOrders() {
		HashMap<String, Object> map = new HashMap<>();
		List<Map<String, Object>> orders = orderData.getOrderList();
		map.put("orders", orders);
		map.put("code", 200);
		map.put("discount", orderData.getDiscount());
		return map;
	}
	
	@DeleteMapping("/deleteOrder")
	public Map deleteOrder(@RequestBody Map<String, Object> order) {
		System.out.println(order);
		Map<String, Object> order1 = (Map<String, Object>) order.get("order");
		String name = (String) order1.get("name");
		List<Map<String, Object>> orderList = orderData.getOrderList();
		for (Map<String, Object> map : orderList) {
			if (name.equals(map.get("name"))) {
				orderList.remove(map);
				break;
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("msg", "删除成功");
		return map;
	}
	
	@GetMapping("/getTotalPrice")
	public Map getTotalPrice() {
		Double price = orderData.getPrice();
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("price", price);
		return map;
	}
}
