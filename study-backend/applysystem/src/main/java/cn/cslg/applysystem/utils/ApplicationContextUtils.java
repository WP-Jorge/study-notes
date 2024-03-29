package cn.cslg.applysystem.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// 用来获取spring创建好的工厂
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
	
	// 保留下来的工厂
	private static ApplicationContext applicationContext;
	
	// 将创建好的工厂以参数的形式传递给这个类
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	// 提供在工厂中获取对象方法
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}
