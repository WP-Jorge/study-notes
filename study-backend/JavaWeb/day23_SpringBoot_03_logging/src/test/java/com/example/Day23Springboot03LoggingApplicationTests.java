package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day23Springboot03LoggingApplicationTests {
	
	// log记录器
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	void contextLoads() {
		// 日志级别，由低到高
		logger.trace("这是trace日志");
		logger.debug("这是debug日志");
		// springboot默认使用的info级别
		//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别; root级别
		logger.info("这是info日志");
		logger.info("这是warn日志");
		logger.error("这是error日志");
		
	}
	
}
