package com.example.orderapp.patterns.factory.Dishes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Admin
 * Create 2021/6/9 16:29
 */
@Data
public abstract class Dishes {
	private String dishesName;
	private Double dishesPrice;
}
