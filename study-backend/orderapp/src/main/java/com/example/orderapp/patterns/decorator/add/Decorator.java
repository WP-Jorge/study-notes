package com.example.orderapp.patterns.decorator.add;

import com.example.orderapp.patterns.decorator.milktea.MilkTea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Admin
 * Create 2021/6/8 22:00
 */
@Data
// @AllArgsConstructor
// @NoArgsConstructor
public abstract class Decorator extends MilkTea {
	private String description = "";
	private String dishesName = "";
	private Double dishesPrice = 0.00;
	
	// @Override
	// public Double getDishesPrice() {
	// 	return 0.00;
	// }
}
