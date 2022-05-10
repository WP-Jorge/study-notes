package com.example.bean;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 先加@Component
 *
 * @ConfigurationProperties(prefix = "person")将配置文件中配置的每一个属性的值，映射到这个组件中
 * prefix的值表示yml文件中哪个属性进行映射
 * @PropertySource(value = {"classpath:person.properties"})
 * 告诉springboot加载person.properties配置文件，人后植入值
 */
// @PropertySource(value = {"classpath:person.properties"})
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
	/**
	 * 也可以使用@Value("")
	 * 可以加字面量${key},从环境变量、配置文件中读取
	 * #{SpEL}
	 */
	// @Value("${person.lastName}")
	private String lastName;
	// @Value("#{11 * 2}")
	private Integer age;
	// @Value("true")
	private Boolean boss;
	private Date birth;
	
	private Map<String, Object> maps;
	private List<Object> lists;
	private Dog dog;
}
