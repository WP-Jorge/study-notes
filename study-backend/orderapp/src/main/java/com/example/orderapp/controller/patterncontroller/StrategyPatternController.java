package com.example.orderapp.controller.patterncontroller;

import com.example.orderapp.data.OrderData;
import com.example.orderapp.patterns.strategy.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/11 19:32
 */
@RestController
@RequestMapping("/strategyPattern")
public class StrategyPatternController {
	
	@Autowired
	private OrderData orderData;
	
	@GetMapping("/getPresent")
	public Map getPresent() {
		String present = Lottery.getPresent(orderData.getPrice());
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("present", present);
		return map;
	}
}
