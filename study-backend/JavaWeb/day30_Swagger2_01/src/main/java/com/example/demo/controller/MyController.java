package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@RequestMapping("/req")
	public String req() {
		return "req";
	}
	
	@PostMapping("/post")
	public String post(String a, String b) {
		return "post";
	}
	
	@GetMapping("/get")
	public String get(String m) {
		return "get";
	}
}
