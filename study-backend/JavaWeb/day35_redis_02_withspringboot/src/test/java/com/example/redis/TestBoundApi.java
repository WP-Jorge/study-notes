package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@SpringBootTest
public class TestBoundApi {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	// spring data为了方便我们对redi s进行更友好的操作因此有提供了bound api简化操作
	
	@Test
	public void testBound() {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		
		// 将一个key的多次操作进行绑定， 对key的绑定
		// 对字符串类型的key进行绑定，后续所有操作都是基于这个key的操作
		BoundValueOperations<String, String> nameValueOperations = stringRedisTemplate.boundValueOps("name");
		nameValueOperations.set("张三");
		nameValueOperations.append("是法外狂徒");
		String name = nameValueOperations.get();
		System.out.println(name);
		
		// 对list set zset hash进行绑定
		// list
		BoundListOperations<String, String> listsOperations = stringRedisTemplate.boundListOps("lists");
		listsOperations.leftPushAll("张三", "李四", "王五");
		List<String> list = listsOperations.range(0, -1);
		System.out.println(list);
		
		// set
		// stringRedisTemplate.opsForSet();
		// redisTemplate.boundSetOps()
		// zset
		// stringRedisTemplate.boundZSetOps()
		// redisTemplate.boundZSetOps()
		// hash
		// stringRedisTemplate.boundHashOps()
		// redisTemplate.boundHashOps()
		
		/**
		 * 1.针对于日后处理key value 都是String使用StringRedisTermplate
		 * 2.针对于日后处理的key value 存在对象使用RedisTemplate
		 * 3.针对于同一个key多次操作可以使用boundXXxOps() Value List Set Zset Hash的api 简化书写
		 */
	}
}
