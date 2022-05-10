package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // 申明配置类
@ComponentScan(basePackages = "aopano") // 开启包扫描
@EnableAspectJAutoProxy(proxyTargetClass = true) // 开启AspectJ注解扫描，默认false
public class ConfigAop {
}
