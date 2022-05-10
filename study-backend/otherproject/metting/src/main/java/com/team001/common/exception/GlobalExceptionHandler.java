package com.team001.common.exception;

import com.team001.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


//    @ExceptionHandler(value = RuntimeException.class)
//    public Result handler(RuntimeException e){
//        log.error("运行时异常----------{}",e);
//        return Result.fail(e.getMessage());
//    }



    @ExceptionHandler(value = NumberFormatException.class)
    public Result num(NumberFormatException e){
        log.error("{}",e);
        return Result.fail(-1,e.getMessage(),null);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public Result handler(MethodArgumentTypeMismatchException e){
        log.error("参数异常--------{}",e);
        return Result.fail("参数异常");
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体校验异常--------{}",e);
        BindingResult bindingResult = e.getBindingResult();

        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    @ExceptionHandler(value = SQLException.class)
    public Result sql(SQLException e){
        log.error("数据库异常----------{}",e);
        return Result.fail(-1,e.getMessage(),null);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常----------{}",e);
        return Result.fail(e.getMessage());
    }
}
