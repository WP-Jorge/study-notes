package com.example.springsecurity.config;

import com.example.springsecurity.handler.MyAccessDeniedHandler;
import com.example.springsecurity.handler.MyAuthenticationFailureHandler;
import com.example.springsecurity.handler.MyAuthenticationSuccessHandler;
import com.example.springsecurity.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import sun.plugin2.message.JavaScriptBaseMessage;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyAccessDeniedHandler myAccessDeniedHandler;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private PersistentTokenRepository persistentTokenRepository;
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	// 将BCryptPasswordEncoder交给spring管理
	@Bean
	public PasswordEncoder getPw() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		// 设置数据源
		jdbcTokenRepository.setDataSource(dataSource);
		// 设置自动建表 第一次启动时开启，第二次启动时注释掉
		// jdbcTokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 表单提交
		http.formLogin()
				// 自定义表单字段
				.passwordParameter("password123")
				.usernameParameter("username123")
				// 自定义登录页面
				.loginPage("/login.html")
				// 必须和表单提交的接口一样，会去执行自定义登录逻辑
				.loginProcessingUrl("/login")
				// 登录成功后跳转页面
				.successForwardUrl("/toMain")
				// 自定义登录成功处理器
				// .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
				// 登陆失败跳转页面
		        .failureForwardUrl("/toError");
				// 自定义登陆失败处理器
				// .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
		
		// 异常处理
		http.exceptionHandling()
				.accessDeniedHandler(myAccessDeniedHandler);
		
		// 记住我
		http.rememberMe()
				// 设置数据源
				.tokenRepository(persistentTokenRepository)
				// 设置rememberMe的参数，如表单的username那个
				.rememberMeParameter("rem")
				// 设置超时时间，默认两周 这里设置60s
				.tokenValiditySeconds(10)
				// 自定义登录逻辑
				.userDetailsService(userDetailService);
		
		// 退出登录
		http.logout()
				// 自定义退出的接口
				.logoutUrl("/user/logout")
				// 退出成功后跳转页面
				.logoutSuccessUrl("/login.html");
		
		// 关闭csrf防护
		// http.csrf().disable();
		
		// 授权
		http.authorizeRequests()
				// 放行login.html，不需要验证
				.antMatchers("/login.html").permitAll()
				// .antMatchers("/error.html").permitAll()
				// 等价上面那个
				.antMatchers("/error.html").access("permitAll")
				// 放行静态目录
				.antMatchers("/css/**", "/js/**", "/imgs/**").permitAll()
				.antMatchers("/showLogin").permitAll()
				// 放行所有后缀为jpg的图片
				// .antMatchers("/**/*.jpg").permitAll()
				// 放行所有后缀为jpg的图片（正则）
				// .regexMatchers(".+[.]jpg").permitAll()
				// 指定请求方法
				// .regexMatchers(HttpMethod.POST, "/demo").permitAll()
				// mvc匹配
				// .mvcMatchers("/demo").servletPath("/XXX").permitAll()
				// .antMatchers("/demo").permitAll()
				
				// 权限控制
				// 单个权限
				// 可有加多个
				// .antMatchers("/admin.html").hasAuthority("admiN")
				// 多个权限
				// .antMatchers("/admin.html").hasAnyAuthority("admin", "admiN")
				// 角色控制
				// .antMatchers("/admin.html").hasRole("abC")
				// 可有加多个
				// .antMatchers("/admin.html").hasRole("abc")
				// 跟上面一样
				// .antMatchers("/admin.html").access("hasRole('abc')")
				// .antMatchers("/admin.html").hasRole("abC")
				// .antMatchers("/admin.html").hasAnyRole("abc", "abC")
				
				// ip地址
				// .antMatchers("/admin.html").hasIpAddress("127.0.0.1")
				// 所有请求都必须认证才能访问，必须登录
				.anyRequest().authenticated();
				// 自定义的access方法
				// .anyRequest().access("@myServiceImpl.hasPermission(request, authentication)");
	}
}
