package com.example.jwt.contiroller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.entity.User;
import com.example.jwt.service.impl.UserServiceImpl;
import com.example.jwt.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/login")
	public Map<String, Object> login(User user) {
		System.out.println("用户名：" + user.getUsername() + " 密码：" + user.getPassword());
		HashMap<String, Object> map = new HashMap<>();
		try {
			User userDB = userService.login(user);
			// 认证成功生成令牌
			HashMap<String, Object> payload = new HashMap<>();
			payload.put("id", userDB.getId());
			payload.put("username", userDB.getUsername());
			String token = JWTUtils.getToken(payload);
			map.put("state", true);
			map.put("msg", "登录成功");
			map.put("token", token);
		} catch (RuntimeException e) {
			map.put("state", false);
			map.put("msg", e.getMessage());
			System.out.println(e.getMessage());
		}
		return map;
	}
	
	@PostMapping("/token")
	public Map<String, Object> testTolen(String token) {
		System.out.println("当前token：" + token);
		HashMap<String, Object> map = new HashMap<>();
		try {
			DecodedJWT verify = JWTUtils.verify(token); // 验证令牌
			map.put("state", true);
			map.put("msg", "请求成功");
			return map;
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
		map.put("state", false);
		return map;
	}
	
	@PostMapping("/user/login")
	public Map<String, Object> userLogin(User user) {
		System.out.println("用户名：" + user.getUsername() + " 密码：" + user.getPassword());
		HashMap<String, Object> map = new HashMap<>();
		try {
			User userDB = userService.login(user);
			// 认证成功生成令牌
			HashMap<String, Object> payload = new HashMap<>();
			payload.put("id", userDB.getId());
			payload.put("username", userDB.getUsername());
			String token = JWTUtils.getToken(payload);
			map.put("state", true);
			map.put("msg", "登录成功");
			map.put("token", token);
		} catch (RuntimeException e) {
			map.put("state", false);
			map.put("msg", e.getMessage());
			System.out.println(e.getMessage());
		}
		return map;
	}
	
	@PostMapping("/user/token")
	public Map<String, Object> userTolen(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		// 处理自己的业务逻辑
		String token = request.getHeader("token");
		DecodedJWT verify = JWTUtils.verify(token);
		System.out.println("用户id：" + verify.getClaim("id").asString());
		System.out.println("用户名：" + verify.getClaim("username").asString());
		System.out.println("token：" + verify.getToken());
		
		
		
		map.put("msg", "请求成功");
		map.put("state", true);
		return map;
	}
	
}
