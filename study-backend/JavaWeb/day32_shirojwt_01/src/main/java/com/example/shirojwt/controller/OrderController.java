package com.example.shirojwt.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	@GetMapping("/save")
	public String save() {
		// 代码方式
		// 获取主体对象
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.hasRole("admin"));
		if (subject.hasRole("admin")) {
			return "save页面：保存订单";
		} else {
			return "save页面：无权访问";
		}
	}
	
	// @RequiresRoles(value={"admin", "user", "delete"}, logical = Logical.AND) // 可以使用多个,表示同时具有
	@RequiresPermissions("admin:*:01") // 权限字符串
	@GetMapping("/delete")
	public String delete() {
		
		// 注解方式
		// 获取主体对象
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.hasRole("admin"));
		if (subject.hasRole("admin")) {
			return "delete页面：删除订单";
		} else {
			return "delete页面：无权访问";
		}
	}
}
