package com.example.demo.controller;

import com.example.demo.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

// 类注释
@Api(tags = "My控制类", description = "getAPI的描述")
@RestController
public class MyController {
	// @ApiIgnore忽略当前方法不生成文档
	@ApiIgnore
	@RequestMapping("/req")
	public String req(String m) {
		return "req";
	}
	
	// ApiOperation value 接口名 notes接口的描述
	// @ApiParam(name = "用户名", value = "新增用户时提供的用户名", required = true) 它会让请求参数变成用body接收 尽量使用@ApiImplicitParams
	@ApiOperation(value = "get接口", notes = "get接口的api文档")
	@ApiImplicitParams({
				@ApiImplicitParam(name="username", value = "用户名", paramType = "字符串", required =true),
				@ApiImplicitParam(name="password",value = "密码", paramType = "字符串", required = true)
			})
	@GetMapping("/get")
	public String get(String username, String password) {
		return username + "  " + password;
	}

	@GetMapping("/post")
	public String post(String a, String b) {
		return "post";
	}
	
	// 只要接口中返回实体类，这个实体类就会被扫描到swagger中
	@ApiOperation("user接口")
	@PostMapping("/user")
	public User user(User user) {
		return user;
	}

	// ApiOperation 接口注释
	@ApiOperation("hello接口")
	@GetMapping("/hello")
	public String hello(String username) {
		return "hello";
	}
	
}
