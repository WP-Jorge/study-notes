package com.example.orderapp.patterns.factory.Dishes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Admin
 * Create 2021/6/9 16:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LuRou extends Dishes {
	private String dishesName = "爆炒鹿肉";
	private Double dishesPrice = 98.00;
}
