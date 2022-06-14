package com.example.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
	private static final String SIGN = "!@#$%^&";
	
	// 生成token
	public static String getToken(Map<String, Object> map) {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DATE, 7); // 默认7天过期
		// 创建jwt builder
		JWTCreator.Builder builder = JWT.create();
		// payload
		map.forEach((k, v) -> {
			builder.withClaim(k, v.toString());
		});
		// sign
		String token = builder.withExpiresAt(instance.getTime()) // 过期时间
				.sign(Algorithm.HMAC256(SIGN));
		return token;
	}
	
	// 验证token 并返回token信息
	public static DecodedJWT verify(String token) {
		return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
	}
	
	// // 获取token信息的方法
	// public static DecodedJWT getTokenInfo(String token) {
	// 	DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
	// 	return verify;
	// }
}