package com.example.jwtshiro.controller;

import com.example.jwtshiro.entity.User;
import com.example.jwtshiro.service.UserService;
import com.example.jwtshiro.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "主页";
	}
	
	@RequiresRoles("admin")
	@GetMapping("/admin")
	public Map admin() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", 200);
		map.put("msg", "admin");
		return map;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "登陆页面";
	}
	
	@PostMapping("/register")
	public User register(User user) {
		try {
			userService.insert(user);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/login")
	public Map login(User user) {
		// 获取主体对象
		Subject subject = SecurityUtils.getSubject();
		HashMap<String, Object> map = new HashMap<>();
		try {
			subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
			map.put("state",200);
			map.put("mag", "登录成功");
			HashMap<String, Object> tokenMap = new HashMap<>();
			tokenMap.put("username", user.getUsername());
			String token = JWTUtils.getToken(tokenMap);
			map.put("token", token);
			
			
			return map;
		} catch (UnknownAccountException e) {
			System.out.println("用户名错误");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		}
		map.put("state",200);
		map.put("mag", "登录失败");
		return map;
	}
	
	@GetMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout(); // 退出登录
		return "退出登录";
	}
	
	// @GetMapping("/unauthorized")
}
