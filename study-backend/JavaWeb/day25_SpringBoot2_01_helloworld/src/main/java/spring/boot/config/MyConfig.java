package spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.boot.bean.Pet;
import spring.boot.bean.User;

// 可以使用@Import({User.class})导入多个组件，给容器中自动创建出这些组件
@Import({User.class})

// 声明配置类
// 配置页本身也是一个组件
// springboot2.0中有一个proxyBeanMethods = true，默认true
@Configuration(proxyBeanMethods = true)
public class MyConfig {
	// 给容器中添加组件，以方法名作为组件的id，返回值就是组件类型，返回的值就是组件在实例中的实例
	// 配置类中使用@Bean标注在方法上给容器注册组件默认是单例的
	@Bean
	public User user01() {
		return new User("zhangsan", 18);
	}
	
	@Bean("tom")
	public Pet tomcat() {
		return new Pet("tomcat");
	}
}
