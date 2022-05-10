package com.example.orderapp.patterns.chain.chain;

import java.util.List;

/**
 * Author Admin
 * Create 2021/6/9 19:59
 */
public class Manager extends Handler {
	
	protected String name = "经理";
	
	@Override
	public void handlerRequest(Double discount, List<String> handlerFlag) {
		if (discount >= 0.90) {
			handlerFlag.add(name);
		} else {
			nextHandler.handlerRequest(discount, handlerFlag);
		}
	}
}
