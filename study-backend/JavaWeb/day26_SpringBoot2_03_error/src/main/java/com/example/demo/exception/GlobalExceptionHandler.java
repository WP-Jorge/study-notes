package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	// 数组里可以放多个需要处理的异常
	@ExceptionHandler({ArithmeticException.class, NullPointerException.class}) // 处理异常
	public String handleArithException(Exception e) {
		log.error("异常是：{}", e);
		return "error"; // 返回视图地址
	}
}
