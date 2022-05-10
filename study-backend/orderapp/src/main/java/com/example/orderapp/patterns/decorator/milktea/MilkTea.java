package com.example.orderapp.patterns.decorator.milktea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Admin
 * Create 2021/6/8 21:53
 */
@Data
// @AllArgsConstructor
// @NoArgsConstructor
public abstract class MilkTea {
	private String description;
	private String dishesName;
	private Double DishesPrice;
	public abstract String getDishesName();
	public abstract Double getDishesPrice();
}
