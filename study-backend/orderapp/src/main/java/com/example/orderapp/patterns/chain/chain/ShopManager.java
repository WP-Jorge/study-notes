package com.example.orderapp.patterns.chain.chain;

import java.util.List;

/**
 * Author Admin
 * Create 2021/6/9 19:59
 */
public class ShopManager extends Handler {
	
	protected String name = "店长";
	
	@Override
	public void handlerRequest(Double discount, List<String> handlerFlag) {
		if (discount >= 0.80) {
			handlerFlag.add(name);
		}
	}
}
