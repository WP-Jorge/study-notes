package com.example.redis;

import com.example.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestStringRedisTemplate {
	// 注入StringRedisTemplate
	@Autowired
	private StringRedisTemplate stringRedisTemplate; // key和value都是字符串
	
	// 操作redis中key相关的
	@Test
	public void testKey() {
		// 删除一个key
		// stringRedisTemplate.delete("name");
		// 判断一个key是否存在
		Boolean hasKey = stringRedisTemplate.hasKey("name");
		System.out.println(hasKey);
		// 判断key值对应的类型
		DataType name = stringRedisTemplate.type("name");
		System.out.println(name);
		// 获取redis中的所有key
		Set<String> keys = stringRedisTemplate.keys("*");
		System.out.println(keys);
		// 获取key的超时时间
		Long name1 = stringRedisTemplate.getExpire("name");
		System.out.println(name1);
		// 随机获取一个key
		String randomKey = stringRedisTemplate.randomKey();
		System.out.println(randomKey);
		// 重命名key（不做存在判断）name不存在会报错
		stringRedisTemplate.rename("name", "newname");
		// 重命名key（key不存在时，将指定key改成目标key）
		stringRedisTemplate.renameIfAbsent("name", "neaname");
		// 移动一个key
		stringRedisTemplate.move("name1", 1); // 移动到一号库
		
		
	}
	
	// 操作redis中的字符串 opsForValue实际上就是操作redis中的String类型
	@Test
	public void testString() {
		// 设置一个key value
		stringRedisTemplate.opsForValue().set("name", "野猪");
		// 用来后去一个key对应的value值
		String name = stringRedisTemplate.opsForValue().get("name");
		System.out.println(name);
		// 设置key value并给一个超时时间(120s)
		stringRedisTemplate.opsForValue().set("code", "1111", 120, TimeUnit.SECONDS);
		// 追加
		stringRedisTemplate.opsForValue().append("name", "乔治");
	}
	
	// 操作redis中list类型 opsForList
	@Test
	public void testList() {
		// 创建一个列表并放入一个元素
		stringRedisTemplate.opsForList().leftPush("names", "家猪佩奇");
		// 创建一个列表并放入多个元素
		stringRedisTemplate.opsForList().leftPushAll("names", "小城", "张三");
		// 创建一个列表并放入一个列表
		ArrayList<String> names = new ArrayList<>();
		names.add("小明");
		names.add("小红");
		stringRedisTemplate.opsForList().leftPushAll("names", names);
		// 查看列表
		List<String> list = stringRedisTemplate.opsForList().range("name", 0, -1);
		System.out.println(list);
		// 截取指定区间的list
		stringRedisTemplate.opsForList().trim("name", 1, 3);
	}
	
	// 操作set opsSet
	@Test
	public void testSet() {
		// 创建set集合并放入多个元素
		stringRedisTemplate.opsForSet().add("sets", "张三", "李四", "王五", "张三");
		// 查看set中的元素
		Set<String> sets = stringRedisTemplate.opsForSet().members("sets");
		System.out.println(sets);
		// 获取集合长度
		Long sets1 = stringRedisTemplate.opsForSet().size("sets");
		System.out.println(sets1);
	}
	
	// 操作zset optForZSet
	@Test
	public void testZset() {
		// 创建zset并放入元素
		stringRedisTemplate.opsForZSet().add("zsets", "张三", 100);
		// 指定范围查询
		Set<String> zsets = stringRedisTemplate.opsForZSet().range("zsets", 0, -1);
		System.out.println(zsets);
		// 获取指定元素以及分数
		Set<ZSetOperations.TypedTuple<String>> zsets1 = stringRedisTemplate.opsForZSet().rangeByScoreWithScores("zsets", 0, -1);
		zsets1.forEach(typeTuple -> {
			System.out.println(typeTuple.getValue());
			System.out.println(typeTuple.getScore());
		});
	}
	
	// 操作hash optForHash
	@Test
	public void testHash() {
		// 创建一个hash类型并放入value
		stringRedisTemplate.opsForHash().put("maps", "name", "张三");
		// 一次性放入多个
		HashMap<String, String> map = new HashMap<>();
		map.put("age", "12");
		map.put("bir", "2020.02.02");
		stringRedisTemplate.opsForHash().putAll("maps", map);
		// 获取多个
		List<Object> maps2 = stringRedisTemplate.opsForHash().multiGet("maps", Arrays.asList("name, age"));
		System.out.println(maps2);
		// 获取maps中某个值
		String o = (String) stringRedisTemplate.opsForHash().get("maps", "name");
		System.out.println(o);
		// 获取所有value
		List<Object> maps1 = stringRedisTemplate.opsForHash().values("maps");
		System.out.println(maps1);
		// 获取所有key
		Set<Object> maps = stringRedisTemplate.opsForHash().keys("maps");
		System.out.println(maps);
	}
	
	// 放入对象
	@Test
	public void testObject() {
		// StringRedisTemplate不能放对象，会报错
		// stringRedisTemplate.opsForValue().set("user", new User());
	}
}
