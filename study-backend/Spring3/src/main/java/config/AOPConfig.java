package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"dao", "proxy", "service"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AOPConfig {
}
