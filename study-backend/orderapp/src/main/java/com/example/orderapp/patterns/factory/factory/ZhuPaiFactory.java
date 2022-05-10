package com.example.orderapp.patterns.factory.factory;

import com.example.orderapp.patterns.factory.Dishes.Dishes;
import com.example.orderapp.patterns.factory.Dishes.ZhuPai;

/**
 * Author Admin
 * Create 2021/6/9 16:52
 */
public class ZhuPaiFactory extends Factory {
	@Override
	public Dishes makeDishes() {
		return new ZhuPai();
	}
}
