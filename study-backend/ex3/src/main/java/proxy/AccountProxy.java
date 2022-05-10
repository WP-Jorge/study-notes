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
public class AccountProxy {
	public static final Logger log = LoggerFactory.getLogger(AccountProxy.class);
	
	@Pointcut(value = "execution(* service.AccountService.transfer(..))")
	public void pointcut() {}
	
	@Before(value = "pointcut()")
	public void before(JoinPoint point) {
		Object[] args = point.getArgs();
		log.info("付款方：" + args[0]);
		log.info("收款方：" + args[1]);
		log.info("转账金额：" + args[2] + " 元");
		log.info("开始转账");
	}
	
	@AfterThrowing(value = "pointcut()")
	public void afterThrowing(JoinPoint point) {
		log.warn("转账过程出现异常");
		log.warn("转账失败");
	}
	
	@AfterReturning(value = "pointcut()", returning = "result")
	public void afterReturn(JoinPoint point, Object result) {
		int res = parseInt(result.toString());
		if (res > 0) {
			log.info("转账成功");
		} else {
			log.warn("转账失败");
		}
	}
}
