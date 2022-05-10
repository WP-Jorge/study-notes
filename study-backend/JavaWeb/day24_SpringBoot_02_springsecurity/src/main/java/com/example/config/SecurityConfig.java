package com.example.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 配置访问权限规则
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/level1/**").hasRole("vip1")
				.antMatchers("/level2/**").hasRole("vip2");
		// .antMatchers("/level3/**").hasRole("vip3");
		
		// 没有权限退到登陆页面
		http.formLogin();
	}
}
