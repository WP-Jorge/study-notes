package com.example.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/hello")
	String hello() {
		System.out.println("hello docker");
		return "hello docker";
	}
}
