package com.xxxx.springsecurityoauth2demo.controller;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author zhoubin
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/getCurrentUser")
	public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
		// return authentication.getPrincipal();
		
		// 获取令牌
		String header = request.getHeader("Authorization");
		String token = header.substring(header.lastIndexOf("bearer") + 7);
		
		return Jwts.parser()
				// 设置密钥
				.setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
				// 转申明
				.parseClaimsJws(token)
				.getBody();
	}

}