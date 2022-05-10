package com.example.orderapp.patterns.decorator.add;

import com.example.orderapp.patterns.decorator.milktea.MilkTea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Admin
 * Create 2021/6/8 22:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZhenZhu extends Decorator {
	private String description = "珍珠";
	private MilkTea milkTea = null;
	private Double dishesPrice = 2.00;
	
	public ZhenZhu(MilkTea milkTea){
		this.milkTea = milkTea;
	}
	@Override
	public String getDescription(){
		return milkTea.getDescription() + "，" + description;
	}
	@Override
	public Double getDishesPrice(){
		return milkTea.getDishesPrice() + dishesPrice;
	}
}
