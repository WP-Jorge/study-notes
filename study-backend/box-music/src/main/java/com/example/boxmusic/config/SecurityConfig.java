package com.example.boxmusic.config;

import com.example.boxmusic.security.*;
import com.example.boxmusic.utils.BCryptPasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Qualifier("myUserDetailsServiceImpl")
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
	
	@Autowired
	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	MyAuthenticationFailHandler myAuthenticationFailHandler;
	
	@Autowired
	MyAuthorizationFilter myAuthorizationFilter;
	
	@Autowired
	MyAuthenticationEntryPoint myAuthenticationEntryPoint;
	
	@Autowired
	MyAccessDeniedHandler myAccessDeniedHandler;
	
	// 设置密码加密算法
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoderUtil);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 1、解决跨域问题
		// http.authorizeRequests()
				// .requestMatchers(CorsUtils::isPreFlightRequest)
				// .permitAll();
		
		// 2、取消 security 的 session 创建
		http.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.headers()
				.cacheControl();
		
		// 3、请求权限配置 (放行部分不需要通过验证的请求，其他请求都需要通过验证才能访问)
		http.authorizeRequests()
				.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
						"/swagger-resources", "/swagger-resources/configuration/security",
						"/swagger-ui.html", "/webjars/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/user/logout").permitAll()
				.antMatchers("/user/register").permitAll()
				.antMatchers("/user/getVerifyCode").permitAll()
				.antMatchers("/user/verifyVerificationCode").permitAll()
				.antMatchers("/file/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/**").permitAll()
				// .antMatchers("/").permitAll()
				.anyRequest().access("@dynamicPermission.checkPermission(request, authentication)");
		
		// http.formLogin().loginPage("/login.html").loginProcessingUrl("/applysystem/login");
		// http.formLogin().loginProcessingUrl("/applysystem/login")
		// 		.permitAll()
		// 		.successHandler(jwtAuthenticationSuccessHandler)
		// 		.failureHandler(jwtAuthenticationFailHandler);
		
		// 4、拦截账号、密码，使用自定义 MyLoginFilter 过滤器替换 UsernamePasswordAuthenticationFilter 过滤器
		http.addFilterAt(myLoginFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// 5、拦截并验证 token，在 UsernamePasswordAuthenticationFilter 之前添加 MyAuthenticationTokenFilter
		http.addFilterBefore(myAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		
		// 6、异常处理，认证失败及权限不足
		http.exceptionHandling().
				// 没有 token，即未认证处理
				authenticationEntryPoint(myAuthenticationEntryPoint).
				// 没有权限
				accessDeniedHandler(myAccessDeniedHandler);
	}
	
	@Bean
	MyLoginFilter myLoginFilter() throws Exception {
		MyLoginFilter myLoginFilter = new MyLoginFilter();
		// 指定登录 url
		// myLoginFilter.setFilterProcessesUrl("/user/login");
		// 指定登录用户名密码字段
		myLoginFilter.setUsernameParameter("username");
		myLoginFilter.setPasswordParameter("password");
		// 设置认证管理器
		myLoginFilter.setAuthenticationManager(authenticationManagerBean());
		// 设置认证成功过滤器
		myLoginFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
		// 设置认证失败过滤器
		myLoginFilter.setAuthenticationFailureHandler(myAuthenticationFailHandler);
		return myLoginFilter;
	}
	
}
