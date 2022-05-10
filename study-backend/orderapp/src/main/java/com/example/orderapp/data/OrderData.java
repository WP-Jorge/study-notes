package com.example.orderapp.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/9 13:40
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
	private Double price = 0.00;
	private List<Map<String, Object>> orderList = new ArrayList<>();
	private Double discount = 1.0;
	public void addOrder(Map<String, Object> order) {
		orderList.add(order);
	}
	public void delete(String name) {
		for (Map<String, Object> map : orderList) {
			String name1 = (String) map.get("name");
			if (name1.equals(name)) {
				orderList.remove(map);
				break;
			}
		}
	}
}
