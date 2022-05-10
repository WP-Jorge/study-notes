package com.example.orderapp.patterns.chain;

import com.example.orderapp.patterns.chain.chain.*;
import org.springframework.stereotype.Component;

/**
 * Author Admin
 * Create 2021/6/9 20:22
 */
@Component
public class GetChain {
	public Handler getChain() {
		Handler waiter = new Waiter();
		Handler cooker = new Cooker();
		Handler manager = new Manager();
		Handler shopManager = new ShopManager();
		waiter.setNextHanlder(cooker);
		cooker.setNextHanlder(manager);
		manager.setNextHanlder(shopManager);
		return waiter;
	}
}
