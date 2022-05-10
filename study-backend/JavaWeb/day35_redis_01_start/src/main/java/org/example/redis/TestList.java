package org.example.redis;

import org.junit.After;
import org.junit.Before;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.List;

public class TestList {
	private Jedis jedis;
	
	@Before
	public void before() {
		this.jedis = new Jedis("192.168.61.128", 6379);
	}
	
	@After
	public void after() {
		jedis.close();
	}
	
	// 1、测试String相关
	public void testList() {
		// lpush
		Long names1 = jedis.lpush("names1", "张三", "王五", "赵六");
		// rpush
		jedis.rpush("names1", "小懒蕉");
		// lrange
		List<String> names11 = jedis.lrange("names1", 0, -1);
		System.out.println(names11);
		// lpop
		String names12 = jedis.lpop("names1");
		System.out.println(names12);
		// linsert
		jedis.linsert("lists", ListPosition.BEFORE, "小黑", "小黑");
		
	}
}
