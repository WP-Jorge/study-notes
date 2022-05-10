package org.example.redis;

import org.junit.After;
import org.junit.Before;
import redis.clients.jedis.Jedis;

public class TestKey {
	private Jedis jedis;
	
	@Before
	public void before() {
		this.jedis = new Jedis("192.168.61.128", 6379);
	}
	
	@After
	public void after() {
		jedis.close();
	}
	
	// 1、测试key相关
	public void testKeys() {
		// 删除一个key
		jedis.del("sex");
		// 删除多个key
		jedis.del("name", "age");
		// 判断一个key是否存在exists
		Boolean name = jedis.exists("name");
		System.out.println(name);
		// 设置一个key的超时时间 expire pexpire
		Long age = jedis.expire("age", 100);
		System.out.println(age);
		// 查看一个key的超时时间 ttl
		Long ttl = jedis.ttl("age");
		System.out.println(ttl);
		// 随机获取一个key
		String s = jedis.randomKey();
		// 修改key名称
		String rename = jedis.rename("age", "newAge");
		// 查看对应值的类型
		String age1 = jedis.type("age");
		System.out.println(age1);
	}
}
