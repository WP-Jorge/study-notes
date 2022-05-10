package com.example.orderapp.patterns.strategy.strategy;

/**
 * Author Admin
 * Create 2021/6/11 19:37
 */
public class Orange implements Strategy {
	@Override
	public String lottery() {
		return "恭喜你获得了一个大橙子！";
	}
}
