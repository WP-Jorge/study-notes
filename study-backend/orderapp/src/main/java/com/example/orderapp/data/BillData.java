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
 * Create 2021/6/10 21:50
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillData {
	private List<Map<String, Object>> billList = new ArrayList<>();
	public void addBill(Map<String, Object> billMap) {
		billList.add(billMap);
	}
}
