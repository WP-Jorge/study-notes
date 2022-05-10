package com.example.jwtandshiro.config;

import com.example.jwtandshiro.shiros.CustomerRealm;
import com.example.jwtandshiro.shiros.JWTFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
		// ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// // 给filter设置安全管理器
		// shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		// // 配置系统公共资源
		// HashMap<String, String> map = new HashMap<>();
		// // 放行swagger
		// map.put("/swagger-ui.html", "anon");
		// map.put("/swagger-resources/**", "anon");
		// map.put("/v2/api-docs", "anon");
		// map.put("/webjars/springfox-swagger-ui/**", "anon");
		// // 配置公共资源
		// map.put("/", "anon"); // anon设置为公共资源，放在限制资源上面
		// map.put("/register", "anon");
		// // 配置系统首受限资源
		// map.put("/**", "authc"); // authc 表示请求该资源需要权限 anon 表示不需要权限
		// shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		//
		// // 默认认证页面路径
		// shiroFilterFactoryBean.setLoginUrl("/login");
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		
		Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
		filterChainDefinitionMap.put("/toLogin","anon");
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		
		//shiro自定义过滤器
		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("jwt", new JWTFilter());
		shiroFilterFactoryBean.setFilters(filters);
		filterChainDefinitionMap.put("/**","jwt");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	// 2.创建安全管理器
	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("customerRealm") CustomerRealm realm, SubjectFactory subjectFactory, SessionManager sessionManager) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 给安全管理器设置realm
		defaultWebSecurityManager.setRealm(realm);
		
		//关闭shiro自带的session
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		defaultWebSecurityManager.setSubjectDAO(subjectDAO);
		
		defaultWebSecurityManager.setSubjectFactory(subjectFactory);
		defaultWebSecurityManager.setSessionManager(sessionManager);
		return defaultWebSecurityManager;
	}
	
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager defaultSessionManager = new DefaultWebSessionManager();
		defaultSessionManager.setSessionValidationSchedulerEnabled(false);
		return defaultSessionManager;
	}
	// 3.创建自定义realm
	@Bean("customerRealm")
	public CustomerRealm getRealm() {
		CustomerRealm realm = new CustomerRealm();
		// 修改凭证校验匹配器
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 设置加密算法为md5
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		// 设置散列次数
		hashedCredentialsMatcher.setHashIterations(1024);
		realm.setCredentialsMatcher(hashedCredentialsMatcher);
		// // 缓存管理
		// // 设置缓存管理器
		// realm.setCacheManager(new EhCacheManager());
		// // 开启全局缓存
		// realm.setCachingEnabled(true);
		// // 开启认证缓存
		// realm.setAuthenticationCachingEnabled(true);
		// // 给认证缓存取名字
		// realm.setAuthenticationCacheName("authenticationCache"); // 可以不设置，他有默认的：realm + AuthenticationCache
		// // 开启授权缓存
		// realm.setAuthorizationCachingEnabled(true);
		// realm.setAuthorizationCacheName("authorizationCache");
		return realm;
	}
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
