package com.example.orderapp.patterns.chain.chain;

import java.util.List;

/**
 * Author Admin
 * Create 2021/6/9 20:00
 */
public abstract class Handler {
	protected String name;
	protected Handler nextHandler;
	// public Handler(String name) {
	// 	this.name = name;
	// }
	public void setNextHanlder(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public abstract void handlerRequest(Double discount, List<String> handlerFlag);
}
