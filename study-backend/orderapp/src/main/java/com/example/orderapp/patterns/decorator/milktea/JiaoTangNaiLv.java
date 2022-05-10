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
public class JiaoTangNaiLv extends MilkTea {
	private String dishesName = "焦糖奶绿";
	private Double dishesPrice = 12.00;
	private String description = "焦糖奶绿";
	@Override
	public String getDishesName() {
		return dishesName;
	}
	
	@Override
	public Double getDishesPrice() {
		return dishesPrice;
	}
}
