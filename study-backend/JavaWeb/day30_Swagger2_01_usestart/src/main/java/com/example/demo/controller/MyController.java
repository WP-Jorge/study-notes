package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@RequestMapping("/req")
	public String req(String m) {
		return "req";
	}
	
	@GetMapping("/get")
	public String get(String a, String b) {
		return "get";
	}
	
	@GetMapping("/post")
	public String post(String a, String b) {
		return "post";
	}
}
