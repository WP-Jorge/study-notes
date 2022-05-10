package com.example.demo.controller;

import com.example.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@GetMapping("/")
	public String index() {
		System.out.println("/");
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("/login");
		
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin(User user, HttpServletRequest request) {
		System.out.println("/postlogin");
		System.out.println(user);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			return "redirect:/admin";
		} else {
			return "login";
		}
	}
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
