package com.example.jwt.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JWTInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HashMap<String, Object> map = new HashMap<>();
		// 获取请求头中的令牌
		String token = request.getHeader("token");
		try {
			JWTUtils.verify(token); // 验证令牌
			map.put("state", true);
			map.put("msg", "请求成功");
			return true; // 放行请求
		} catch (SignatureVerificationException e) {
			System.out.println("签名异常");
			map.put("msg", "签名异常");
		} catch (TokenExpiredException e) {
			System.out.println("token过期");
			map.put("msg", "token过期");
		} catch (AlgorithmMismatchException e) {
			System.out.println("算法不匹配");
			map.put("msg", "算法不匹配");
		} catch (InvalidClaimException e) {
			System.out.println("payload异常");
			map.put("msg", "payload异常");
		} catch (Exception e) {
			System.out.println("token无效");
			map.put("msg", "token无效");
		}
		map.put("state", false); // 设置状态
		// 将map转为json jackson
		String json = new ObjectMapper().writeValueAsString(map);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(json);
		return false;
	}
}
