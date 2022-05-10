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
public class AHuaTian extends MilkTea {
	private String dishesName = "阿华田";
	private Double dishesPrice = 12.00;
	private String description = "阿华田";
	@Override
	public String getDishesName() {
		return dishesName;
	}
	
	@Override
	public Double getDishesPrice() {
		return dishesPrice;
	}
	
}
