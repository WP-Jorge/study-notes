package com.example.redis.cache;

import com.example.redis.util.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

// 自定义Redis缓存的实现
public class RedisCache implements Cache {
	
	// 定义一个String 类型的id
	// 放入当前缓存mapper的namespace
	private final String id;
	// 必须存在构造方法
	public RedisCache(String id) {
		System.out.println("id: " + id);
		this.id = id;
	}
	
	// 返回catch的唯一标识
	@Override
	public String getId() {
		return this.id;
	}
	
	// 缓存中放入数据
	@Override
	public void putObject(Object o, Object o1) {
		System.out.println("key: " + o.toString());
		System.out.println("value: " + o1.toString());
		RedisTemplate redisTemplate = getRedisTemplate();
		// 使用redishash类型作为缓存存储模型 key hashKey value
		redisTemplate.opsForHash().put(id.toString(), getKeyToMD5(o.toString()), o1);
	}
	
	// 缓存中获取数据
	@Override
	public Object getObject(Object o) {
		System.out.println("key: " + o.toString());
		RedisTemplate redisTemplate = getRedisTemplate();
		// 根据key从redis的hash类型中获取数据
		Object o1 = redisTemplate.opsForHash().get(id.toString(), getKeyToMD5(o.toString()));
		return o1;
	}
	
	// 这个方法mybatis保留方法，默认没有实现，后续版本肯能会追加使用
	@Override
	public Object removeObject(Object o) {
		System.out.println("根据指定的key删除缓存");
		return null;
	}
	
	@Override
	public void clear() {
		System.out.println("清空缓存");
		RedisTemplate redisTemplate = getRedisTemplate();
		// 清空namespace
		redisTemplate.delete(id.toString()); // 清空缓存
	}
	
	// 用来计算缓存数量
	@Override
	public int getSize() {
		RedisTemplate redisTemplate = getRedisTemplate();
		// 获取hash中key value数量
		return redisTemplate.opsForHash().size(id.toString()).intValue();
	}
	
	// 封装redisTemplate
	private RedisTemplate getRedisTemplate() {
		// 通过application工具类获取redisTemplate
		RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}
	
	// 封装一个对key进行 md5处理方法
	private String getKeyToMD5(String key) {
		return DigestUtils.md5DigestAsHex(key.getBytes());
	}
}
