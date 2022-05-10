package proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.LogService;

import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Integer.parseInt;

@Component
@Aspect
public class LogProxy {
	@Autowired
	private LogService logService;
	
	@Pointcut(value = "execution(* service.AccountService.transfer(..))")
	public void pointcut() {}
	
	@Before(value = "pointcut()")
	public void before(JoinPoint point) {
		Object[] args = point.getArgs();
		String des = "付款方：" + args[0] + ", 收款方：" + args[1] + ", 转账金额：" + args[2] + " 元" + ", 开始转账";
		logService.insertIntoLog("INFO", "转账", des, new Timestamp(new Date().getTime()));
	}
	
	@AfterThrowing(value = "pointcut()")
	public void afterThrowing(JoinPoint point) {
		String des = "转账过程出现异常, 转账失败";
		logService.insertIntoLog("WARN", "转账", des, new Timestamp(new Date().getTime()));
	}
	
	@AfterReturning(value = "pointcut()", returning = "result")
	public void afterReturn(JoinPoint point, Object result) {
		String des = "";
		String type = "";
		int res = parseInt(result.toString());
		if (res > 0) {
			type = "INFO";
			des = "转账成功";
		} else {
			type = "WARN";
			des = "转账失败";
		}
		logService.insertIntoLog(type, "转账", des, new Timestamp(new Date().getTime()));
	}
}
