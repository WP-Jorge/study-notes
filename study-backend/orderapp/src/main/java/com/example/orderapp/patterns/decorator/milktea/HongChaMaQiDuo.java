package com.example.orderapp.patterns.decorator.milktea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Admin
 * Create 2021/6/8 21:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HongChaMaQiDuo extends MilkTea {
	private String dishesName = "红茶玛奇朵";
	private Double dishesPrice = 13.00;
	private String description = "红茶玛奇朵";
	@Override
	public String getDishesName() {
		return dishesName;
	}
	
	@Override
	public Double getDishesPrice() {
		return dishesPrice;
	}
}
