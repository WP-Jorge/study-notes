package com.example.orderapp.patterns.decorator;

import com.example.orderapp.patterns.decorator.add.*;
import com.example.orderapp.patterns.decorator.milktea.*;

import java.util.List;
import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/9 14:12
 */
public class MakeMilTea {
	public static MilkTea makeMilkTea(Map<String, Object> milkTeaMap) {
		Map<String, Object> milkTea = (Map<String, Object>) milkTeaMap.get("milkTea");
		List<Map<String, Object>> addList = (List<Map<String, Object>>) milkTeaMap.get("addList");
		// System.out.println(milkTea);
		// System.out.println(addList);
		
		MilkTea milkTeaProduct;
		switch (milkTea.get("dishesName").toString()) {
			case "奶绿":
				milkTeaProduct = new NaiLv();
				milkTeaProduct = addAdd(milkTeaProduct, addList);
				return milkTeaProduct;
			case "红茶玛奇朵":
				milkTeaProduct = new HongChaMaQiDuo();
				milkTeaProduct = addAdd(milkTeaProduct, addList);
				return milkTeaProduct;
			case "阿华田":
				milkTeaProduct = new AHuaTian();
				milkTeaProduct = addAdd(milkTeaProduct, addList);
				return milkTeaProduct;
			case "抹茶奶绿":
				milkTeaProduct = new MoChaNailv();
				milkTeaProduct = addAdd(milkTeaProduct, addList);
				return milkTeaProduct;
			case "可可奶茶":
				milkTeaProduct = new KeKeNaiCha();
				milkTeaProduct = addAdd(milkTeaProduct, addList);
				return milkTeaProduct;
			case "焦糖奶绿":
				milkTeaProduct = new JiaoTangNaiLv();
				milkTeaProduct = addAdd(milkTeaProduct, addList);
				return milkTeaProduct;
			default:
				return null;
		}
	}
	
	private static MilkTea addAdd(MilkTea milkTea, List<Map<String, Object>> addList) {
		for (Map<String, Object> map : addList) {
			switch (map.get("addName").toString()) {
				case "布丁":
					milkTea = new BuDing(milkTea);
					break;
				case "双皮奶":
					milkTea = new ShuangPiNai(milkTea);
					break;
				case "椰果":
					milkTea = new YeGuo(milkTea);
					break;
				case "珍珠":
					milkTea = new ZhenZhu(milkTea);
					break;
				default:
			}
		}
		return milkTea;
	}
}
