package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 这个注解可以放回一个状态码信息
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "看你不舒服")
public class UserException extends RuntimeException {
	public UserException() {
	
	}
	
	// 自定义用户异常
	public UserException(String errorMsg) {
	
	}
}
