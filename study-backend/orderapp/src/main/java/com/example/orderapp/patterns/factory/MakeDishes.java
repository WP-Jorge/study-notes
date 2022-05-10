package com.example.orderapp.patterns.factory;

import com.example.orderapp.patterns.factory.Dishes.Dishes;
import com.example.orderapp.patterns.factory.factory.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author Admin
 * Create 2021/6/9 16:57
 */
@Component
public class MakeDishes {
	public Dishes makeDishs(Map dishes) {
		Dishes dishesProduct;
		switch (dishes.get("dishesName").toString()) {
			case "爆炒鹿肉":
				LuRouFactory luRouFactory = new LuRouFactory();
				dishesProduct = luRouFactory.makeDishes();
				break;
			case "西红柿炒鸡蛋":
				XiHongShiFactory xiHongShiFactory = new XiHongShiFactory();
				dishesProduct = xiHongShiFactory.makeDishes();
				break;
			case "碳烤猪肋排":
				ZhuPaiFactory zhuPaiFactory = new ZhuPaiFactory();
				dishesProduct = zhuPaiFactory.makeDishes();
				break;
			case "啤酒蒜蓉爆龙虾":
				XiaoLongXiaFactory xiaoLongXiaFactory = new XiaoLongXiaFactory();
				dishesProduct = xiaoLongXiaFactory.makeDishes();
				break;
			case "酸菜鱼":
				SuanCaiYuFactory suanCaiYuFactory = new SuanCaiYuFactory();
				dishesProduct = suanCaiYuFactory.makeDishes();
				break;
			case "蒜蓉生蚝":
				ShengHaoFactory shengHaoFactory = new ShengHaoFactory();
				dishesProduct = shengHaoFactory.makeDishes();
				break;
			default:
				dishesProduct = null;
		}
		return dishesProduct;
	}
}
