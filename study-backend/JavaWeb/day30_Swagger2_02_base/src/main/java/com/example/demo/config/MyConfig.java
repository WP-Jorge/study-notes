package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
// 开启swagger2
// 会扫描当前类所在包，以及自包中所有类型中的注解，做swagger文档的定值
@EnableSwagger2
public class MyConfig {
	// 配置swagger的Docket的实例
	
	// 配置多个分组
	@Bean
	public Docket docket1() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("野猪小组1");
	}
	@Bean
	public Docket docket2() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("野猪小组2");
	}
	@Bean
	public Docket docket3() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("野猪小组3");
	}
	
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
				"野猪乔治API",
				"野猪乔治的API接口",
				"1.0",
				contact.getUrl(),
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>());
	}
}
