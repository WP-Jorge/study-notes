package com.example.shiro.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket docket(Environment environment) {
		// 设置要显示的swagger环境
		Profiles profiles = Profiles.of("dev", "test");
		// 获取项目环境
		// acceptsProfiles 判断是否存在在自己设定的环境中
		boolean b = environment.acceptsProfiles(profiles);
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				// 是否启动swagger
				.enable(b)
				.groupName("野猪小组")
				.select()
				// RequestHandlerSelectors 配置要扫描接口的方式
				// basePackage() 指定要扫描的包
				// any() 扫描全部
				// none() 都不扫描
				// withClassAnnotation() 扫描类上的注解，参数是一个注解的反射对象
				// withMethodAnnotation() 扫描方法上的注解
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				// paths() 过滤什么路径
				// .paths(PathSelectors.none())
				.build();
	}
	
	// 配置Swagger信息 apiInfo
	private ApiInfo apiInfo() {
		// 作者信息
		Contact contact = new Contact("乔治", "https://www.jorge.com/", "853539461@qq.com");
		return new ApiInfo(
				"野猪乔治的Shiro接口",
				"野猪乔治的Shiro接口的描述",
				"1.1",
				contact.getUrl(),
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>());
	}
	// @Primary
	// @Bean
	// public SqlSessionFactory sysSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
	// 		throws Exception {
	// 	MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
	// 	bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
	// 	bean.setDataSource(dataSource);
	// 	return bean.getObject();
	// }
	
}
