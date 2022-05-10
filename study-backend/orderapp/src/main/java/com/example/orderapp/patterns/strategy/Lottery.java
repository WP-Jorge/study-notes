package com.example.orderapp.patterns.strategy;

import com.example.orderapp.patterns.strategy.strategy.Context;
import com.example.orderapp.patterns.strategy.strategy.Cup;
import com.example.orderapp.patterns.strategy.strategy.Mp3;
import com.example.orderapp.patterns.strategy.strategy.Orange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author Admin
 * Create 2021/6/11 19:46
 */
public class Lottery {
	public static String getPresent(Double price) {
		Context context = new Context();
		String lottery;
		if (price <= 50.0) {
			Orange orange = new Orange();
			context.setStrategy(orange);
			lottery = context.lottery();
		} else if (price <= 100) {
			Cup cup = new Cup();
			context.setStrategy(cup);
			lottery = context.lottery();
		} else {
			Mp3 mp3 = new Mp3();
			context.setStrategy(mp3);
			lottery = context.lottery();
		}
		return lottery;
	}
}
