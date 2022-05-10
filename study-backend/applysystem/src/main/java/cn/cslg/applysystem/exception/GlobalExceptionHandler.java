package cn.cslg.applysystem.exception;

import cn.cslg.applysystem.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = NumberFormatException.class)
	public R num(NumberFormatException e){
		log.error("数字格式化异常----------{}", e.getMessage());
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "数字格式化异常(" + e.getMessage() + ")");
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public R handler(IllegalArgumentException e){
		log.error("传递参数异常----------{}", e.getMessage());
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "传递参数异常(" + e.getMessage() + ")");
	}
	
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public R handler(MethodArgumentTypeMismatchException e){
		log.error("参数异常--------{}", e.getMessage());
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "参数异常，请检查参数是否正确");
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public R handler(MethodArgumentNotValidException e){
		log.error("实体校验异常--------{}", e.getMessage());
		BindingResult bindingResult = e.getBindingResult();
		
		ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "实体校验异常(" + objectError.getDefaultMessage() + ")");
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public R handler(ConstraintViolationException e) {
		log.error("参数约束违例异常----------{}", e.getMessage());
		String msg = e.getMessage();
		String[] msgs = msg.split(": ", 2);
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "参数约束违例异常(" + msgs[1] + ")");
	}
	
	@ExceptionHandler(value = SQLException.class)
	public R sql(SQLException e){
		log.error("数据库异常----------{}", e.getMessage());
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "数据库异常(" + e.getMessage() + ")");
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public R sql(DataIntegrityViolationException e) {
		log.error("数据完整性违规异常----------{}", e.getMessage());
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "数据完整性违规异常(" + e.getMessage() + ")");
	}
	
	@ExceptionHandler(value = DuplicateKeyException.class)
	public R sql(DuplicateKeyException e) {
		log.error("键名重复异常----------{}", e.getMessage());
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "键名重复异常(" + e.getMessage() + ")");
	}
	
	@ExceptionHandler(value = MyAuthorizationException.class)
	public R hander(MyAuthorizationException e) {
		log.error("token验证失败----------{}", e.getMessage());
		return R.error(HttpStatus.SC_UNAUTHORIZED, "token验证失败(" + e.getMessage() + ")");
	}
	
	@ExceptionHandler(value = BindException.class)
	public R hander(BindException e) {
		log.error("参数验证失败----------{}", e.getMessage());
		
		String msg = e.getMessage();
		
		List<String> list=new ArrayList<String>();
		Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
		Matcher m = p.matcher(msg);
		while(m.find()){
			list.add(m.group().substring(1, m.group().length()-1));
		}
		
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, list.get(list.size() - 1));
	}
	
	
	@ExceptionHandler(value = RuntimeException.class)
	public R handler(RuntimeException e){
		log.error("运行时异常----------{}", e.getMessage());
		return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "运行时异常(" + e.getMessage() + ")");
	}
	
}
