package com.example.oauth2_demo.exception;

import com.example.oauth2_demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = NumberFormatException.class)
	public R num(NumberFormatException e){
		log.error("数字格式化异常----------{}", e);
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public R handler(IllegalArgumentException e){
		log.error("传递参数异常----------{}", e);
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public R handler(MethodArgumentTypeMismatchException e){
		log.error("参数异常--------{}", e);
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public R handler(MethodArgumentNotValidException e){
		log.error("实体校验异常--------{}", e);
		BindingResult bindingResult = e.getBindingResult();
		
		ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, objectError.getDefaultMessage());
	}
	
	@ExceptionHandler(value = SQLException.class)
	public R sql(SQLException e){
		log.error("数据库异常----------{}", e);
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public R handler(RuntimeException e){
		log.error("运行时异常----------{}", e);
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
}
