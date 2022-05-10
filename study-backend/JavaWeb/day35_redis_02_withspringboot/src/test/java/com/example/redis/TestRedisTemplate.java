package com.example.redis;

import com.example.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestRedisTemplate {
	// 注入RedisTemplate
	@Autowired
	private RedisTemplate redisTemplate;
	
	// opsForXxx Value String List Set ZSet Hash
	
	@Test
	public void testRedisTemplate() {
		/**
		 * redisTemplate中 key value的序列化都是JdkSerializationRedisSerializer
		 *      key: String
		 *      value: object
		 *      修改默认的序列化方案
		 */
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		
		// 设置key的默认序列化,改成string类型的序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 修改hash key的序列化
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		
		User user = new User();
		user.setName("野猪").setAge(18).setBir(new Date()).setId(1);
		redisTemplate.opsForValue().set("user", user);
		Object user1 = redisTemplate.opsForValue().get("user");
		System.out.println(user);
		
		// 放入List中
		redisTemplate.opsForList().leftPush("list", user);
		
		// hash中的map中的第一个key默认还是被序列化
		redisTemplate.opsForHash().put("map", "name", "乔治");
	}
}
