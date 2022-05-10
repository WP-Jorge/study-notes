package com.example.demo.config;

import com.example.demo.shiro.realms.CustomerRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 用啦整合shiro框架相关的配置类
 * authc: 示请求该资源需要权限
 * shiro中提供多个默认的过滤器，我们可以通过这些过滤器来配置指定url的权限
 *      配置缩写        对应过滤器                       功能
 *      anon          AnonymousFilter                指定url可以匿名访问（无需权限）
 *      authc	      FormAuthenticationFilter       基于表单的拦截器；如“/**=authc”，如果没有登录会跳到相应的登录页面登录；
 */
@Configuration
public class ShiroConfig {
	// 1.创建shiroFilter 负责拦截所有请求
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 给filter设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		// 配置系统公共资源
		HashMap<String, String> map = new HashMap<>();
		// 放行swagger
		map.put("/swagger-ui.html", "anon");
		map.put("/swagger-resources/**", "anon");
		map.put("/v2/api-docs", "anon");
		map.put("/webjars/springfox-swagger-ui/**", "anon");
		// 配置公共资源
		map.put("/", "anon"); // anon设置为公共资源，放在限制资源上面
		map.put("/register", "anon");
		// 配置系统首受限资源
		map.put("/**", "authc"); // authc 表示请求该资源需要权限 anon 表示不需要权限
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		
		// 默认认证页面路径
		shiroFilterFactoryBean.setLoginUrl("/login");
		
		
		return shiroFilterFactoryBean;
	}
	// 2.创建安全管理器
	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") Realm realm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 给安全管理器设置realm
		defaultWebSecurityManager.setRealm(realm);
		return defaultWebSecurityManager;
	}
	// 3.创建自定义realm
	@Bean("realm")
	public Realm getRealm() {
		CustomerRealm realm = new CustomerRealm();
		return realm;
	}
}
