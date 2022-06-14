package com.example.orderapp.patterns.factory.factory;

import com.example.orderapp.patterns.factory.Dishes.Dishes;
import com.example.orderapp.patterns.factory.Dishes.SuanCaiYu;

/**
 * Author Admin
 * Create 2021/6/9 16:52
 */
public class SuanCaiYuFactory extends Factory {
	@Override
	public Dishes makeDishes() {
		return new SuanCaiYu();
	}
}