package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SignatureException;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class Day32Jwt01StartApplicationTests {

	// 生成令牌
	@Test
	void contextLoads() {
		HashMap<String, Object> map = new HashMap<>();
		
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND, 60 * 60 *1);
		
		String token = JWT.create()
				.withHeader(map) // header 可以不写，它有默认值
				.withClaim("userId", 21) // payload
				.withClaim("username", "乔治")
				.withExpiresAt(instance.getTime()) // 过期时间
				.sign(Algorithm.HMAC256("!@#$%^&"));//签名
		System.out.println(token);
	}
	
	// 验证令牌
	// @Test
	void test () {
		/**
		 * 1.先验证签名（有算法和签名）
		 * 2，再验证过期时间
		 * 3，再验证payload
		 *
		 * 1.SignatureVerificationException     签名不一致异常
		 * 2.TokenExpiredException              令牌过期异常
		 * 3.AlgorithmMismatchException         算法不匹配异常
		 * 4.InvalidClaimException              失效的payload异常
		 */
		// 创建验证对象
		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!@#$%^&")).build();
		// 解码token
		DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTIwNjg5MjksInVzZXJJZCI6MjEsInVzZXJuYW1lIjoi5LmU5rK7In0.rqF8kMgVWtosJfQuTwSNrJZrE83hJkTe0WD6Mi9Eswo");
		//
		System.out.println("getHeader: " + verify.getHeader());
		System.out.println("getExpiresAt: " + verify.getExpiresAt());
		System.out.println("getToken: " + verify.getToken());
		System.out.println("userId: " + verify.getClaim("userId").asInt());
		System.out.println("username: " + verify.getClaim("username").asString());
		System.out.println("getSignature: " + verify.getSignature());
		
	}

}
