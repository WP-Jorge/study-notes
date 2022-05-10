package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 作为配置类， 替代xml配置文件
@ComponentScan(basePackages = {"dao", "service"})
// 等价配置文件<context:component-scan base-package="dao, service"></context:component-scan>
public class SpringConfig {
}
