package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
	
	@RequestMapping({"/", "/index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "views/login";
	}
	
	@RequestMapping("/level1/vip1")
	public String toLevel1() {
		return "views/level1/vip1";
	}
	
	@RequestMapping("/level2/vip2")
	public String toLevel2() {
		return "views/level2/vip2";
	}
	
	@RequestMapping("/level3/vip3")
	public String toLevel3() {
		return "views/level3/vip3";
	}
	
}
