package org.example.redis;

import org.junit.After;
import org.junit.Before;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TestString {
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
	public void testString() {
		// set
		jedis.set("name", "野猪");
		// get
		jedis.get("name");
		// mset
		jedis.mset("content", "好人", "address", "上海");
		// mget
		List<String> mget = jedis.mget("name", "content", "address");
		System.out.println(mget);
		// getSet
		String set = jedis.getSet("name", "小明");
		System.out.println(set);
		
	}
}
