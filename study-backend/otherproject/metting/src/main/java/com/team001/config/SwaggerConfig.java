package com.team001.config;

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

/**
 * swagger配置类
 */
@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){

        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);
        //获取项目环境

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .groupName("team001")
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定扫描的包
                //any()扫描全部
                //none()都不扫描
                //withClassAnnotation 扫描类上的注解
                .apis(RequestHandlerSelectors.basePackage("com.team001.controller"))
                .build()
                .globalOperationParameters(globalOperation());
    }

    private List<Parameter> globalOperation(){
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        //第一个token为传参的key，第二个token为swagger页面显示的值
        tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return pars;
    }

    //配置swagger信息
    private ApiInfo apiInfo(){

        Contact contact = new Contact("team001","http://localhost:8081","1494135711@qq.com");

        return new ApiInfo(
                "会议室预约接口",
                "会议室SwaggerApi文档",
                "1.0",
                "http://localhost:8081",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
