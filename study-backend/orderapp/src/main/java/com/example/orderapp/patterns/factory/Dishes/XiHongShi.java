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
public class XiHongShi extends Dishes {
	private String dishesName = "西红柿炒鸡蛋";
	private Double dishesPrice = 13.00;
}
