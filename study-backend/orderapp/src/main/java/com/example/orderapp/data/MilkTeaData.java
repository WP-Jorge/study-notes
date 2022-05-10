package com.example.orderapp.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/8 20:26
 */
public class MilkTeaData {
	public static List<Map<String, Object>> milkTeaList = new ArrayList<Map<String, Object>>() {{
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("dishesName", "奶绿");
		map1.put("dishesPrice", 8.00);
		map1.put("dishesDes", "淡淡的绿茶芬芳");
		map1.put("dishesImg", "https://www.alittle-tea.com/upload/catalog_list_pic/cnL_catalog_21D29_i8gmn7dder.png");
		add(map1);
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("dishesName", "红茶玛奇朵");
		map2.put("dishesPrice", 13.00);
		map2.put("dishesDes", "红茶位十足");
		map2.put("dishesImg", "https://www.alittle-tea.com/upload/catalog_list_pic/cnL_catalog_21D29_axzswjhpsh.png");
		add(map2);
		HashMap<String, Object> map3 = new HashMap<>();
		map3.put("dishesName", "阿华田");
		map3.put("dishesPrice", 12.00);
		map3.put("dishesDes", "百搭的玛奇朵口感绵密香甜");
		map3.put("dishesImg", "https://www.alittle-tea.com/upload/catalog_list_pic/cnL_catalog_21D29_fv8pg9wfsy.png");
		add(map3);
		HashMap<String, Object> map4 = new HashMap<>();
		map4.put("dishesName", "抹茶奶绿");
		map4.put("dishesPrice", 9.00);
		map4.put("dishesDes", "抹茶味十足");
		map4.put("dishesImg", "https://www.alittle-tea.com/upload/catalog_list_pic/cnL_catalog_21D29_uukw4m8nmq.png");
		add(map4);
		HashMap<String, Object> map5 = new HashMap<>();
		map5.put("dishesName", "可可奶茶");
		map5.put("dishesPrice", 14.00);
		map5.put("dishesDes", "喜爱巧克力的你，想来一杯吗");
		map5.put("dishesImg", "https://www.alittle-tea.com/upload/catalog_list_pic/cnL_catalog_21D29_ahbqrrvcxc.png");
		add(map5);
		HashMap<String, Object> map6 = new HashMap<>();
		map6.put("dishesName", "焦糖奶绿");
		map6.put("dishesPrice", 12.00);
		map6.put("dishesDes", "好喝不腻");
		map6.put("dishesImg", "https://www.alittle-tea.com/upload/catalog_list_pic/cnL_catalog_21D29_nz5vbkindz.png");
		add(map6);
	}};
	
	public static List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>() {{
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("addName", "布丁");
		map1.put("addPrice", 3.00);
		add(map1);
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("addName", "双皮奶");
		map2.put("addPrice", 4.00);
		add(map2);
		HashMap<String, Object> map3 = new HashMap<>();
		map3.put("addName", "椰果");
		map3.put("addPrice", 2.00);
		add(map3);
		HashMap<String, Object> map4 = new HashMap<>();
		map4.put("addName", "珍珠");
		map4.put("addPrice", 2.00);
		add(map4);
	}};
}
