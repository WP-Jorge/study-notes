package cn.cslg.applysystem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private ApplicationContext applicationContext;
	
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
		redisTemplate.opsForValue().set(userDetails.getUsername(), token, Value.EXPIRATION_TIME, TimeUnit.DAYS);
	}
	
	public void pushToken(String username, String token) {
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		// 设置key的默认序列化,改成string类型的序列化
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// // 修改hash key的序列化
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.opsForValue().set(username, token, Value.EXPIRATION_TIME, TimeUnit.DAYS);
	}
	
	public String getToken(UserDetails userDetails) {
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		// 设置key的默认序列化,改成string类型的序列化
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// // 修改hash key的序列化
		// System.out.println(redisTemplate);
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		String token = (String) redisTemplate.opsForValue().get(userDetails.getUsername());
		return token;
	}
	
	public String getToken(String username) {
		// redis进行设置，对象需要序列化, User需要实现Serializable接口
		// 设置key的默认序列化,改成string类型的序列化
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// // 修改hash key的序列化
		// System.out.println(redisTemplate);
		// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		String token = (String) redisTemplate.opsForValue().get(username);
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
	
	// 删除除了token以外的redis缓存
	public void deleteAllKeys() {
		Map<String, Object> annotatedBeans = applicationContext.getBeansWithAnnotation(SpringBootApplication.class);
		String name = String.valueOf(annotatedBeans.values().toArray()[0].getClass().getPackage());
		String srcPath = name.substring(Value.PACKAGE_PREFIX_LENGTH);
		Set<String> keys = redisTemplate.keys(srcPath + Value.WILDCARD_ALL);
		System.out.println("程序启动清除缓存：" + keys);
		redisTemplate.delete(keys);
	}
	
	// 删除token
	public Boolean deleteToken(String token) {
		return redisTemplate.delete(token);
	}
}
