package com.example.orderapp.data;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Author Admin
 * Create 2021/6/8 18:26
 */
public class DishesData {
	public static List<Map<String, Object>> dishesList = new ArrayList<Map<String, Object>>() {{
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("dishesName", "爆炒鹿肉");
		map1.put("dishesPrice", 98.00);
		map1.put("dishesDes", "来自漂亮国釜山的新鲜鹿肉");
		map1.put("dishesImg", "http://n.sinaimg.cn/sinacn20191029ac/239/w640h399/20191029/588a-ihqyuyk8451224.jpg");
		add(map1);
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("dishesName", "西红柿炒鸡蛋");
		map2.put("dishesPrice", 13.00);
		map2.put("dishesDes", "家常菜，让你感受家的温暖");
		map2.put("dishesImg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVuIpMMotROvcYoSwdRvxwAtl4Cl7sts_5d3eLN-gqwxaEdsyHWKempQSE6JHiYtfryqw&usqp=CAU");
		add(map2);
		HashMap<String, Object> map3 = new HashMap<>();
		map3.put("dishesName", "碳烤猪肋排");
		map3.put("dishesPrice", 78.00);
		map3.put("dishesDes", "好吃不贵的烤二师兄");
		map3.put("dishesImg", "https://maj.tw/image/cache/catalog/product/processing-foods/6BU/0190AA-400x400.jpg");
		add(map3);
		HashMap<String, Object> map4 = new HashMap<>();
		map4.put("dishesName", "啤酒蒜蓉爆龙虾");
		map4.put("dishesPrice", 32.00);
		map4.put("dishesDes", "夏天到来，小龙虾配啤酒，起飞");
		map4.put("dishesImg", "http://p9.bdxiguaimg.com/img/tos-cn-i-0004/c51329cf62214d79b8637e32755a54d1~c5_q75_864x486.jpeg");
		add(map4);
		HashMap<String, Object> map5 = new HashMap<>();
		map5.put("dishesName", "酸菜鱼");
		map5.put("dishesPrice", 46.00);
		map5.put("dishesDes", "又酸又菜还多鱼");
		map5.put("dishesImg", "https://i.jmsla.cn/upimg/allimg/190528/18-1Z52Q025514A.jpg");
		add(map5);
		HashMap<String, Object> map6 = new HashMap<>();
		map6.put("dishesName", "蒜蓉生蚝");
		map6.put("dishesPrice", 88.00);
		map6.put("dishesDes", "来分生蚝加加油");
		map6.put("dishesImg", "https://p3.pstatp.com/large/a9000496fce24a8ec4");
		add(map6);
	}};
}
