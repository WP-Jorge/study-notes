package com.example.orderapp.patterns.strategy.strategy;

/**
 * Author Admin
 * Create 2021/6/11 19:39
 */
public class Cup implements Strategy {
	@Override
	public String lottery() {
		return "恭喜你获得了一个豪华杯子";
	}
}
