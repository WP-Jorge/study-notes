package com.example.demo.controller;

import com.example.demo.exception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/user")
	public String user() {
		if (1 > 0) {
			throw new UserException();
		}
		return "index";
	}
}
