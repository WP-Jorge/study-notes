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
public class ZhuPai extends Dishes {
	private String dishesName = "碳烤猪肋排";
	private Double dishesPrice = 78.00;
}
