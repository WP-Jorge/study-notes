package cn.cslg.applysystem.config;

import cn.cslg.applysystem.security.*;
import cn.cslg.applysystem.utils.BCryptPasswordEncoderUtil;
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
	JWTAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
	
	@Autowired
	JWTAuthenticationFailHandler jwtAuthenticationFailHandler;
	
	@Autowired
	JWTAuthorizationFIlter jwtAuthorizationFIlter;
	
	@Autowired
	JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	JWTAccessDeniedHandler jwtAccessDeniedHandler;
	
	// 设置密码加密算法
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoderUtil);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 1、解决跨域问题
		http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
		
		// 2、取消security的session创建
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().headers().cacheControl();
		
		// 3、请求权限配置
		// 放行部分不需要通过验证的请求，其他请求都需要通过验证才能访问
		http.authorizeRequests()
				.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
						"/swagger-resources", "/swagger-resources/configuration/security",
						"/swagger-ui.html", "/webjars/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/imgs/**").permitAll()
				.antMatchers("/wx/*").permitAll()
				.antMatchers("/wxLogin").permitAll()
				.antMatchers("/socketServer/*").permitAll()
				.antMatchers("/user/logout").permitAll()
				.antMatchers("/carousel/*").permitAll()
				.antMatchers("/compete/getAllCompetes").permitAll()
				.antMatchers("/compete/getAllCompetesWithPage").permitAll()
				.antMatchers("/**").permitAll()
				// .antMatchers("/").permitAll()
				.anyRequest().access("@dynamicPermission.checkPermission(request, authentication)");
		
		// http.formLogin().loginPage("/login.html").loginProcessingUrl("/applysystem/login");
		// http.formLogin().loginProcessingUrl("/applysystem/login")
		// 		.permitAll()
		// 		.successHandler(jwtAuthenticationSuccessHandler)
		// 		.failureHandler(jwtAuthenticationFailHandler);
		//
		// 4、拦截账号、密码使用自定义过滤器替换 AuthenticationFilter 过滤器
		http.addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// 5、拦截并验证token，在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
		http.addFilterBefore(jwtAuthorizationFIlter, UsernamePasswordAuthenticationFilter.class);
		
		// 6、异常处理，认证失败及权限不足
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler);
	}
	
	@Bean
	JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
		jwtAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthenticationFailHandler);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return jwtAuthenticationFilter;
	}
	
}
