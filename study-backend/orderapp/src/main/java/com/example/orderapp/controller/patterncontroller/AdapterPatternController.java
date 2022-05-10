package com.example.orderapp.controller.patterncontroller;

import com.example.orderapp.data.OrderData;
import com.example.orderapp.patterns.adapter.Adapter;
import com.example.orderapp.patterns.adapter.AliPay;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/11 12:31
 */
@RestController
@RequestMapping("/adapterPattern")
public class AdapterPatternController {
	
	@Autowired
	private OrderData orderData;
	
	@PostMapping("/payWithAliPay")
	public Map payWithAliPay(@RequestBody Map price) {
		System.out.println(price);
		String price1 = (String) price.get("price");
		Double price2 = Double.valueOf(price1);
		AliPay aliPay = new Adapter();
		orderData.setPrice(price2);
		String pay = aliPay.pay(price2);
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("msg", pay);
		return map;
	}
}
