package com.example.boxmusic.cache;

import com.example.boxmusic.utils.ApplicationContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import java.util.concurrent.locks.ReadWriteLock;

// 自定义 Redis 缓存的实现
public class RedisCache implements Cache {
	
	// 定义一个 String 类型的 id
	// 放入当前缓存 mapper 的 namespace
	private final String id;
	// 必须存在构造方法
	public RedisCache(String id) {
		System.out.println("id: " + id);
		this.id = id;
	}
	
	// 返回 catch 的唯一标识
	@Override
	public String getId() {
		return this.id;
	}
	
	// 缓存中放入数据
	@Override
	public void putObject(Object o, Object o1) {
		System.out.println("缓存中放入数据");
		System.out.println("key: " + o);
		System.out.println("value: " + o1);
		RedisTemplate redisTemplate = getRedisTemplate();
		// 使用 redis hash 类型作为缓存存储模型 key hashKey value
		redisTemplate.opsForHash().put(id.toString(), getKeyToMD5(o.toString()), o1);
	}
	
	// 缓存中获取数据
	@Override
	public Object getObject(Object o) {
		System.out.println("缓存中获取数据");
		System.out.println("key: " + o.toString());
		RedisTemplate redisTemplate = getRedisTemplate();
		// 根据 key 从 redis 的 hash 类型中获取数据
		Object o1 = redisTemplate.opsForHash().get(id.toString(), getKeyToMD5(o.toString()));
		return o1;
	}
	
	// 这个方法 mybatis 保留方法，默认没有实现，后续版本肯能会追加使用
	// 移除一个对象,这个方法只有在发生回滚是调用。
	// 在删除是不会调用，目的是如果发生了删除且缓存过，则查不用读库
	@Override
	public Object removeObject(Object o) {
		System.out.println("根据指定的 key 删除缓存" + o);
		if (o != null) {
			getRedisTemplate().opsForHash().delete(id.toString(), o.toString());
		}
		return null;
	}
	
	@Override
	public void clear() {
		System.out.println("清空缓存: " + id);
		RedisTemplate redisTemplate = getRedisTemplate();
		// 清空 namespace
		// 特殊处理 如果是需要清除 UserRoleMapper 中的缓存，则也需要清除 UserMapper 中的缓存
		// if ("cn.cslg.applysystem.mapper.UserRoleMapper".equals(id)) {
		// 	redisTemplate.delete("cn.cslg.applysystem.mapper.UserMapper");
		// }
		redisTemplate.delete(id.toString()); // 清空缓存
	}
	
	// 用来计算缓存数量
	@Override
	public int getSize() {
		RedisTemplate redisTemplate = getRedisTemplate();
		// 获取 hash 中 key value 数量
		return redisTemplate.opsForHash().size(id.toString()).intValue();
	}
	
	// @Override
	// public ReadWriteLock getReadWriteLock() {
	// 	return null;
	// }
	
	// 封装 redisTemplate
	private RedisTemplate getRedisTemplate() {
		// 通过 application 工具类获取 redisTemplate
		RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}
	
	// 封装一个对 key 进行 md5 处理方法
	private String getKeyToMD5(String key) {
		return DigestUtils.md5DigestAsHex(key.getBytes());
	}
}
