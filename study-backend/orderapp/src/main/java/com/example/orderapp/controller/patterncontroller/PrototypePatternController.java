package com.example.orderapp.controller.patterncontroller;

import com.example.orderapp.data.OrderData;
import com.example.orderapp.patterns.prototype.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/11 16:08
 */
@RestController
@RequestMapping("/prototypePattern")
public class PrototypePatternController {
	
	private Bill bill = new Bill();
	
	@Autowired
	private OrderData orderData;
	
	@PostMapping("/getBill")
	public Map getBill(@RequestBody Map billData) {
		HashMap<String, Object> map = new HashMap<>();
		String name = (String) billData.get("name");
		String email = (String) billData.get("email");
		Bill billClone = bill.clone();
		billClone.setPrice(orderData.getPrice() + "0 元");
		billClone.setEmail(email);
		billClone.setName(name);
		map.put("code", 200);
		map.put("bill", billClone);
		return map;
	}
}
