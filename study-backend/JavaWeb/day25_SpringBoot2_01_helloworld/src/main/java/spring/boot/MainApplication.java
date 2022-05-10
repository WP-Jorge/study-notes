package spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import spring.boot.bean.Pet;
import spring.boot.bean.User;
import spring.boot.config.MyConfig;

// 告诉springboot这是一个springboot应用
// 主程序类
@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		// 1.返回ioc容器
		ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
		
		// 2.从容器中获取组件
		Pet tom1 = run.getBean("tom", Pet.class);
		Pet tom2 = run.getBean("tom", Pet.class);
		System.out.println("组件：" + (tom1 == tom2));
		
		// 3.如果@Configuration(proxyBeanMethods = true)代理对象调用方法，保持组件单实例
		// 如果是false，代理对象调用方法就不是单实例
		MyConfig bean = run.getBean(MyConfig.class);
		User user = bean.user01();
		User user1 = bean.user01();
		System.out.println(user == user1);
		
		// 4.获取Import的组件
		System.out.println("---------");
		String[] beanNamesForType = run.getBeanNamesForType(User.class);
		for (String s : beanNamesForType) {
			System.out.println(s);
		}
	}
}
