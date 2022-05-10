package com.example.orderapp.patterns.chain.chain;

import java.util.List;

/**
 * Author Admin
 * Create 2021/6/9 19:59
 */
public class Waiter extends Handler {
	
	protected String name = "服务员";
	
	@Override
	public void handlerRequest(Double discount, List<String> handlerFlag) {
		if (discount >= 0.99) {
			handlerFlag.add(name);
		} else {
			nextHandler.handlerRequest(discount, handlerFlag);
		}
	}
}
