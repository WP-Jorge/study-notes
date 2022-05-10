package com.example.shiro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Order控制器", description = "包含Order相关操作的控制器")
@RestController
public class OrderController {
	@ApiOperation(value = "save接口", notes = "save接口，基于代码方式")
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
	
	@ApiOperation(value = "delete接口", notes = "delete接口，基于注解方式")
	@RequiresRoles(value={"admin", "user", "delete"}, logical = Logical.AND) // 可以使用多个,表示同时具有
	// @RequiresPermissions("") // 权限字符串
	@GetMapping("/delete")
	public String delete() {
		
		// 注解方式
		// 获取主体对象
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.hasRole("admin"));
		if (subject.hasRole("admin")) {
			return "save页面：保存订单";
		} else {
			return "save页面：无权访问";
		}
	}
}
