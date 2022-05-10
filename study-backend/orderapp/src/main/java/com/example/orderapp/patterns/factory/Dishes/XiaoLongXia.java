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
public class XiaoLongXia extends Dishes {
	private String dishesName = "啤酒蒜蓉爆龙虾";
	private Double dishesPrice = 32.00;
}
