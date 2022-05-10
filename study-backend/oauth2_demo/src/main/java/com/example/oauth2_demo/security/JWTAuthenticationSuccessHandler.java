package com.example.oauth2_demo.security;

import com.example.oauth2_demo.security.JSONAuthentication;
import com.example.oauth2_demo.utils.JwtTokenUtil;
import com.example.oauth2_demo.utils.R;
import com.example.oauth2_demo.utils.RedisUtil;
import com.example.oauth2_demo.utils.TokenCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功进行的操作
 */
@Component
public class JWTAuthenticationSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler {
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		
		// 获取账号信息
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// 取token
		// 好的解决方案，登录成功后token存储到数据库中
		// 只要token还在过期内，不需要每次重新生成
		// 先去缓存中找
		String token = (String) redisTemplate.opsForValue().get(userDetails.getUsername());
		// String token = TokenCacheUtils.getTokenFromCache(userDetails.getUsername());
		if (token == null) {
			System.out.println("初次登录，缓存中没有token，生成token");
			// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
			token = jwtTokenUtil.generateToken(userDetails);
			// 将token存入缓存中
			// redisTemplate.opsForValue().set(userDetails.getUsername(), token);
			// TokenCacheUtils.setToken(userDetails.getUsername(), token);
		} else {
			// token = jwtTokenUtil.refreshToken(token);
			// redisTemplate.opsForValue().set(userDetails.getUsername(), token);
			// redisUtil.pushToken(userDetails, token);
			token = redisUtil.refreshToken(userDetails, token);
		}
		redisUtil.pushToken(userDetails, token);
		
		// 将token封装成json数据发送给前端
		HashMap<String, Object> map = new HashMap<>();
		map.put("username", userDetails.getUsername());
		// map.put("auth", userDetails.getAuthorities());
		System.out.println(userDetails);
		map.put("token", token);
		// 装入token
		// R<Map<String, Object>> data = R.ok(map);
		R r = R.ok(map);
		// 输出
		this.WriteJSON(httpServletRequest, httpServletResponse, r);
	}
	
}
