package com.example.boxmusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	
	/**
	 * redisTemplate 序列化使用的 jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean("redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		// redis 进行设置，对象需要序列化，User 需要实现 Serializable 接口
		// 设置 key 的默认序列化，改成 String 类型的序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 修改 hash key 的序列化
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}
}