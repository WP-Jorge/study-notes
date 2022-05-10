package com.xxxx.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author zhoubin
 * @since 1.0.0
 */
@SpringBootTest
public class SpringApplicationTest {

	/**
	 * 生成jwt
	 */
	@Test
	public void testJwt() {
		JwtBuilder jwtBuilder = Jwts.builder()
				//唯一ID{“id”：“888”}
				.setId("888")
				//接受的用户 {“sub”：“Rose”}
				.setSubject("Rose")
				//签发时间 {“iat”：“。。。”}
				.setIssuedAt(new Date())
				//签名算法，及秘钥
				.signWith(SignatureAlgorithm.HS256, "xxxx");
		//签发token
		String token = jwtBuilder.compact();
		System.out.println(token);

		System.out.println("=================================");
		String[] split = token.split("\\.");
		System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
		System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
		//这个会乱码
		System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
	}


	/**
	 * 解析token
	 */
	@Test
	public void testParseToken() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNTk0NzA4NTg5fQ" +
				".SRQn_SYT-N6iypx-rtQye2B1WjpsJ1w8F4a3LYrljmc";
		//解析token，获取Claims，jwt中荷载申明的对象
		Claims claims = Jwts.parser()
				//秘钥
				.setSigningKey("xxxx")
				.parseClaimsJws(token)
				.getBody();
		System.out.println("id=" + claims.getId());
		System.out.println("sub=" + claims.getSubject());
		System.out.println("iat=" + claims.getIssuedAt());
	}


	/**
	 * 生成jwt(失效时间)
	 */
	@Test
	public void testJwtHasExpire() {
		//当前时间
		long date = System.currentTimeMillis();
		//失效时间
		long exp = date + 60 * 1000;
		JwtBuilder jwtBuilder = Jwts.builder()
				//唯一ID{“id”：“888”}
				.setId("888")
				//接受的用户 {“sub”：“Rose”}
				.setSubject("Rose")
				//签发时间 {“iat”：“。。。”}
				.setIssuedAt(new Date())
				//签名算法，及秘钥
				.signWith(SignatureAlgorithm.HS256, "xxxx")
				//设置失效时间
				.setExpiration(new Date(exp));
		//签发token
		String token = jwtBuilder.compact();
		System.out.println(token);

		System.out.println("=================================");
		String[] split = token.split("\\.");
		System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
		System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
		//这个会乱码
		System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
	}


	/**
	 * 解析token(失效时间)
	 */
	@Test
	public void testParseTokenHasExpire() {
		String token = "eyJhbGciOiJIUzI1NiJ9" +
				".eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNTk0NzA4OTIwLCJleHAiOjE1OTQ3MDg5ODB9" +
				".XuQzhPAI-3Ez6B_N8dsMNGyw7CcIuOoaLIWEBmGRq1E";
		//解析token，获取Claims，jwt中荷载申明的对象
		Claims claims = Jwts.parser()
				//秘钥
				.setSigningKey("xxxx")
				.parseClaimsJws(token)
				.getBody();
		System.out.println("id=" + claims.getId());
		System.out.println("sub=" + claims.getSubject());
		System.out.println("iat=" + claims.getIssuedAt());
	}


	/**
	 * 生成jwt(自定义申明)
	 */
	@Test
	public void testJwtHasEnhancer() {
		//当前时间
		long date = System.currentTimeMillis();
		//失效时间
		long exp = date + 60 * 1000;
		JwtBuilder jwtBuilder = Jwts.builder()
				//唯一ID{“id”：“888”}
				.setId("888")
				//接受的用户 {“sub”：“Rose”}
				.setSubject("Rose")
				//签发时间 {“iat”：“。。。”}
				.setIssuedAt(new Date())
				//签名算法，及秘钥
				.signWith(SignatureAlgorithm.HS256, "xxxx")
				//自定义申明
				.claim("name", "Jack")
				.claim("logo", "xxx.jpg");
		// .addClaims(map);
		//签发token
		String token = jwtBuilder.compact();
		System.out.println(token);

		System.out.println("=================================");
		String[] split = token.split("\\.");
		System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
		System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
		//这个会乱码
		System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
	}


	/**
	 * 解析token(自定义申明)
	 */
	@Test
	public void testParseTokenHasEnhancer() {
		String token = "eyJhbGciOiJIUzI1NiJ9" +
				".eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNTk0NzA5MjE2LCJuYW1lIjoiSmFjayIsImxvZ28iOiJ4eHguanBnIn0" +
				".-MQ4I5GcD2l-SrUJaVH-mfneKXIRKiDlM_ae1XmrrY0";
		//解析token，获取Claims，jwt中荷载申明的对象
		Claims claims = Jwts.parser()
				//秘钥
				.setSigningKey("xxxx")
				.parseClaimsJws(token)
				.getBody();
		System.out.println("id=" + claims.getId());
		System.out.println("sub=" + claims.getSubject());
		System.out.println("iat=" + claims.getIssuedAt());
		System.out.println("name=" + claims.get("name"));
		System.out.println("logo=" + claims.get("logo"));
	}


}