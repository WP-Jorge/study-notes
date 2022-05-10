package com.example.orderapp.patterns.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Admin
 * Create 2021/6/11 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Cloneable {
	private String shopName = "曹县小吃";
	private String address = "华夏国燕京市腾龙街道2号";
	private String price;
	private String email;
	private String name;
	
	@Override
	public Bill clone() {
		Bill bill = null;
		try {
			bill = (Bill) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return bill;
	}
}
