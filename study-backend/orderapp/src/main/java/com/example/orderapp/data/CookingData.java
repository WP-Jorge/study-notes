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
 * Create 2021/6/9 21:25
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookingData {
	private List<Map<String, Object>> unCookingList = new ArrayList<>();
	public void addUnCookingOrder(Map<String, Object> unCookingOrder) {
		unCookingList.add(unCookingOrder);
	}
	public void deleteUnCookingOrder(String name) {
		for (Map<String, Object> map : unCookingList) {
			String name1 = (String) map.get("name");
			if (name1.equals(name)) {
				unCookingList.remove(map);
				break;
			}
		}
	}
	private List<Map<String, Object>> cookingList = new ArrayList<>();
	public void addCookingOrder(Map<String, Object> cookingOrder) {
		cookingList.add(cookingOrder);
	}
	public void deleteCookingOrder(String name) {
		for (Map<String, Object> map : cookingList) {
			String name1 = (String) map.get("name");
			if (name1.equals(name)) {
				cookingList.remove(map);
				break;
			}
		}
	}
}
