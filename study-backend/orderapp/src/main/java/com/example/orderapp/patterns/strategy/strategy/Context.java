package com.example.orderapp.patterns.strategy.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Author Admin
 * Create 2021/6/11 19:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Context {
	private Strategy strategy;
	public void replaceStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public String lottery() {
		return strategy.lottery();
	}
}
