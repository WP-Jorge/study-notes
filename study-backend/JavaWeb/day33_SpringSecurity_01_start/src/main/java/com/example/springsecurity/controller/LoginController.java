package com.example.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
	// @GetMapping("/login")
	// public String loginIndex(String username, String password) {
	// 	return "主页";
	// }
	@PostMapping("/login")
	public String login(String username, String password) {
		return "redirect:main.html";
	}
	
	// 使用注解，判断是否有这个权限，没有权限报500错误
	// @Secured("ROLE_abcc")
	//
	@PreAuthorize("hasRole('abc')") // ROLE_abc 或者 abc都可以
	@PostMapping("/toMain")
	public String toMain() {
		return "redirect:main.html";
	}
	
	@PostMapping("/toError")
	public String toError() {
		return "redirect:error.html";
	}
	
	@ResponseBody
	@GetMapping("/demo")
	public String demo() {
		return "demo";
	}
	
	@ResponseBody
	@GetMapping("/showLogin")
	public String showLogin() {
		return "showLogin";
	}
	
}
