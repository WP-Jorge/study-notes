package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController // @Controller和@ResponseBody合体
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello world quick";
	}
}
