package com.example.boxmusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
// 开启 swagger2
// 会扫描当前类所在包，以及自包中所有类型中的注解，做 swagger 文档的定值
@EnableSwagger2
public class SwaggerConfig {
	// 配置 swagger 的 Docket 的实例
	
	// 配置多个分组
	// @Bean
	// public Docket docket1() {
	// 	return new Docket(DocumentationType.SWAGGER_2).groupName("野猪小组1");
	// }
	// @Bean
	// public Docket docket2() {
	// 	return new Docket(DocumentationType.SWAGGER_2).groupName("野猪小组2");
	// }
	// @Bean
	// public Docket docket3() {
	// 	return new Docket(DocumentationType.SWAGGER_2).groupName("野猪小组3");
	// }
	
	@Bean
	public Docket docket(Environment environment) {
		// 设置要显示的 swagger 环境
		Profiles profiles = Profiles.of("dev", "test");
		// 获取项目环境
		// acceptsProfiles 判断是否存在在自己设定的环境中
		boolean acceptsProfiles = environment.acceptsProfiles(profiles);
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				// 是否启动swagger
				.enable(acceptsProfiles)
				.groupName("盒子音乐")
				.select()
				// RequestHandlerSelectors 配置要扫描接口的方式
				// basePackage() 指定要扫描的包
				// any() 扫描全部
				// none() 都不扫描
				// withClassAnnotation() 扫描类上的注解，参数是一个注解的反射对象
				// withMethodAnnotation() 扫描方法上的注解
				.apis(RequestHandlerSelectors.basePackage("com.example.boxmusic.controller"))
				// paths() 过滤什么路径
				// .paths(PathSelectors.none())
				.build();
				// .globalOperationParameters(globalOperation());
	}
	
	private List<Parameter> globalOperation(){
		//添加 head 参数配置 start
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		//第一个 token 为传参的 key，第二个 token 为 swagger 页面显示的值
		tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());
		return pars;
	}
	
	// 配置 Swagger 信息 apiInfo
	private ApiInfo apiInfo() {
		// 作者信息
		Contact contact = new Contact("Box Music", "http://localhost:8081", "853539461@qq.com");
		return new ApiInfo(
				"Box Music接口",
				"Box Music API 接口",
				"1.0",
				contact.getUrl(),
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>());
	}
}
