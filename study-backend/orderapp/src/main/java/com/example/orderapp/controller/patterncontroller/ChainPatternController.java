package com.example.orderapp.controller.patterncontroller;

import com.example.orderapp.data.OrderData;
import com.example.orderapp.patterns.chain.GetChain;
import com.example.orderapp.patterns.chain.chain.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/9 20:20
 */
@RestController
@RequestMapping("/chainPattern")
public class ChainPatternController {
	
	@Autowired
	private GetChain getChain;
	
	@Autowired
	private OrderData orderData;
	
	@PostMapping("/getDiscount")
	public Map addSealedFood(@RequestBody Map<String, Object> discountMap) {
		System.out.println(discountMap);
		Handler chain = getChain.getChain();
		ArrayList<String> flag = new ArrayList<>();
		String discount = (String) discountMap.get("discount");
		Double discount1 = Double.parseDouble(discount);
		chain.handlerRequest(discount1, flag);
		HashMap<String, Object> map = new HashMap<>();
		if (flag.size() == 0) {
			map.put("code", 201);
			map.put("msg", "非常抱歉，您要求的折扣我们无法满足，请尝试选择一个合乎情理的折扣");
		} else {
			orderData.setDiscount(discount1);
			map.put("code", 200);
			map.put("msg", "您申请的折扣已被我们的 " + flag.get(0) + " 处理成功，您可以使用该折扣进行付款");
			map.put("discount", discount1);
		}
		return map;
	}
}
