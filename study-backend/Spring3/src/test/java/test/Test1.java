package test;

import config.AOPConfig;
import dao.AccountDao;
import dao.AccountDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountService;
import utils.MyBatisUtils;

public class Test1 {
	public static final Logger log = LoggerFactory.getLogger(Test1.class);
	@Test
	public void test1() {
		// int i = new AccountDaoImpl().reduceMoney("张三", 10.0);
		// System.out.println("状态：" + i);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPConfig.class);
		AccountService accountService = context.getBean(AccountService.class);
		accountService.transfer("张三", "李四", 10.0);
	}
}
