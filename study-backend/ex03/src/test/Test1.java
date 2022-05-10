package test;

// import config.AOPConfig;
import config.AOPConfig;
import config.TxConfig;
import dao.AccountDaoImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import proxy.AccountProxy;
import service.AccountService;

public class Test1 {
	public static final Logger log = LoggerFactory.getLogger(Test1.class);
	@Test
	public void test1() {
		// AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPConfig.class);
		// AccountService accountService = context.getBean(AccountService.class);
		ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
		AccountService accountService = context.getBean(AccountService.class);
		accountService.transfer("张三", "李四", 10.0);
	}
}
