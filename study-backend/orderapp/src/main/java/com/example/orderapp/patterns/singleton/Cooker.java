package com.example.orderapp.patterns.singleton;

import com.example.orderapp.data.CookingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * Author Admin
 * Create 2021/6/9 21:46
 */
@Component
public class Cooker {
	
	private CookingData cookingData = (CookingData) ApplicationContextUtil.getBean("cookingData");
	
	private static final Cooker cooker = new Cooker();
	
	public static Cooker getCooker() {
		return cooker;
	}
	
	private void cookNext() {
		List<Map<String, Object>> unCookingList = cookingData.getUnCookingList();
		Map<String, Object> map = unCookingList.get(0);
		unCookingList.remove(0);
		List<Map<String, Object>> cookingList = cookingData.getCookingList();
		cookingList.add(map);
	}
	
	@Scheduled(fixedRate = 10000)
	public void autoCooking() {
		List<Map<String, Object>> unCookingList = cookingData.getUnCookingList();
		List<Map<String, Object>> cookingList = cookingData.getCookingList();
		System.out.println(unCookingList);
		System.out.println(cookingList);
		if (unCookingList.size() > 0) {
			Cooker.getCooker().cookNext();
			System.out.println(unCookingList);
			System.out.println(cookingList);
		}
	}
}
