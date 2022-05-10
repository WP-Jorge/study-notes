package com.example.orderapp.patterns.singleton;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author Admin
 * Create 2021/6/10 20:26
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}