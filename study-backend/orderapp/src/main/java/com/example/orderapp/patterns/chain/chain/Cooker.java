package com.example.orderapp.patterns.chain.chain;

import java.util.List;

/**
 * Author Admin
 * Create 2021/6/9 19:59
 */
public class Cooker extends Handler {
	
	protected String name = "厨师";
	
	@Override
	public void handlerRequest(Double discount, List<String> handlerFlag) {
		if (discount >= 0.98) {
			handlerFlag.add(name);
		} else {
			nextHandler.handlerRequest(discount, handlerFlag);
		}
	}
}
