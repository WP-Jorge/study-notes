package com.example.orderapp.patterns.factory.factory;

import com.example.orderapp.patterns.factory.Dishes.Dishes;
import com.example.orderapp.patterns.factory.Dishes.ShengHao;

/**
 * Author Admin
 * Create 2021/6/9 16:52
 */
public class ShengHaoFactory extends Factory {
	@Override
	public Dishes makeDishes() {
		return new ShengHao();
	}
}
