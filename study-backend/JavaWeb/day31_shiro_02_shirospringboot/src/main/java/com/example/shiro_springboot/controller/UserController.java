package com.example.shiro_springboot.controller;

import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "User控制器", description = "包含User相关操作的控制器")
public class    UserController {
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "根接口", notes = "默认根接口")
	@GetMapping("/")
	public String index() {
		return "主页";
	}
	
	@ApiOperation(value = "后台接口", notes = "后台根接口")
	@GetMapping("/admin")
	public String admin() {
		return "后台页面";
	}
	
	@ApiOperation(value = "登录页", notes = "登录提示")
	@GetMapping("/login")
	public String loginPage() {
		return "登陆页面";
	}
	
	@ApiOperation(value = "注册页", notes = "注册")
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
	public User login(User user) {
		// 获取主体对象
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
			System.out.println(subject.getSession().getId());
			
			return user;
		} catch (UnknownAccountException e) {
			System.out.println("用户名错误");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		}
		return null;
	}
	
	@PostMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout(); // 退出登录
		return "退出登录";
	}
}
