package com.example.shirojwt.controller;

import com.example.shirojwt.bean.ResponseBean;
import com.example.shirojwt.entity.User;
import com.example.shirojwt.service.UserService;
import com.example.shirojwt.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
	
	@RequiresRoles(value = {"admin", "user", "product"}, logical = Logical.OR)
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
		// Subject subject = SecurityUtils.getSubject();
		HashMap<String, Object> map = new HashMap<>();
		try {
			// subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
			if (userService.login(user)) {
				map.put("state",200);
				map.put("msg", "登录成功");
				HashMap<String, Object> tokenMap = new HashMap<>();
				tokenMap.put("username", user.getUsername());
				String token = JWTUtils.getToken(tokenMap);
				map.put("token", token);
				// return new ResponseBean(200, "Login success", JWTUtils.getToken());
				return map;
			} else {
				map.put("state",200);
				map.put("mag", "登录失败，用户名或密码错误");
				return map;
			}
			
		} catch (UnknownAccountException e) {
			System.out.println("用户名错误");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		}
		map.put("state",200);
		map.put("msg", "未知错误");
		return map;
	}
	
	@GetMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout(); // 退出登录
		return "退出登录";
	}
	
	// @GetMapping("/unauthorized")
	@RequestMapping(path = "/401")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseBean unauthorized() {
		return new ResponseBean(401, "Unauthorized", null);
	}
}
