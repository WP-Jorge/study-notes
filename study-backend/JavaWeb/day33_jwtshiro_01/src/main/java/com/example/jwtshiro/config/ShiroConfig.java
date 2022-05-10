package com.example.jwtshiro.config;

import com.example.jwtshiro.shirojwt.CustomerRealm;
import com.example.jwtshiro.shirojwt.JWTFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
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
		
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		
		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		//设置我们自定义的JWT过滤器
		filterMap.put("jwt", new JWTFilter());
		factoryBean.setFilters(filterMap);
		factoryBean.setSecurityManager(defaultWebSecurityManager);
		// 设置无权限时跳转的 url;
		factoryBean.setUnauthorizedUrl("/unauthorized/无权限");
		Map<String, String> filterRuleMap = new HashMap<>();
		// 所有请求通过我们自己的JWT Filter
		filterRuleMap.put("/**", "jwt");
		// 访问 /unauthorized/** 不通过JWTFilter
		filterRuleMap.put("/unauthorized/**", "anon");
		factoryBean.setFilterChainDefinitionMap(filterRuleMap);
		
		return factoryBean;
	}
	// 2.创建安全管理器
	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") Realm realm, SessionManager sessionManager) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 给安全管理器设置realm
		
		/*
		 * 关闭shiro自带的session，详情见文档
		 * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
		 */
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		defaultWebSecurityManager.setSubjectDAO(subjectDAO);
		defaultWebSecurityManager.setRealm(realm);
		defaultWebSecurityManager.setSessionManager(sessionManager);
		return defaultWebSecurityManager;
	}
	// 3.创建自定义realm
	@Bean("realm")
	public Realm getRealm() {
		CustomerRealm realm = new CustomerRealm();
		// 修改凭证校验匹配器
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 设置加密算法为md5
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		// 设置散列次数
		hashedCredentialsMatcher.setHashIterations(1024);
		realm.setCredentialsMatcher(hashedCredentialsMatcher);
		return realm;
	}
	
	/**
	 * 禁用session, 不保存用户登录状态。保证每次请求都重新认证
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setSessionValidationSchedulerEnabled(false);
		return defaultWebSessionManager;
	}
	
	/**
	 * 添加注解支持
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		// 强制使用cglib，防止重复代理和可能引起代理出错的问题
		// https://zhuanlan.zhihu.com/p/29161098
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}
