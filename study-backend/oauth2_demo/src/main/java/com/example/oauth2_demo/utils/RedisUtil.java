package com.example.oauth2_demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	// @Autowired
	// UserDetails userDetails;
	
	// RedisUtil() {
	// 	// redis进行设置，对象需要序列化, User需要实现Serializable接口
	// 	// 设置key的默认序列化,改成string类型的序列化
	// 	redisTemplate.setKeySerializer(new StringRedisSerializer());
	// 	// 修改hash key的序列化
	// 	redisTemplate.setHashKeySerializer(new StringRedisSerializer());
	// }
	
	public void pushToken(UserDetails userDetails, String token) {
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		// 设置key的默认序列化,改成string类型的序列化
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// // 修改hash key的序列化
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.opsForValue().set(userDetails.getUsername(), token);
	}
	
	public String getToken(UserDetails userDetails) {
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		// 设置key的默认序列化,改成string类型的序列化
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// // 修改hash key的序列化
		System.out.println(redisTemplate);
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		String token = (String) redisTemplate.opsForValue().get(userDetails.getUsername());
		return token;
	}
	
	public Boolean isTokenExist(UserDetails userDetails) {
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		// 设置key的默认序列化,改成string类型的序列化
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// // 修改hash key的序列化
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		String token = (String) redisTemplate.opsForValue().get(userDetails.getUsername());
		if (token != null) {
			return true;
		}
		return false;
	}
	
	public String refreshToken(UserDetails userDetails, String token) {
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		// 设置key的默认序列化,改成string类型的序列化
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// // 修改hash key的序列化
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		String refreshToken = jwtTokenUtil.refreshToken(token);
		this.pushToken(userDetails, refreshToken);
		
		return refreshToken;
	}
	
}
