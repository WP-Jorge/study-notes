package com.xxxx.springsecurityoauth2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenStoreConfig {
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	// JWTToken转换器
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		// 设置密钥
		jwtAccessTokenConverter.setSigningKey("test_key");
		return jwtAccessTokenConverter;
	}
	
	// 把这个自定义负载交给spring进行管理
	@Bean
	public JwtTokenEnchancer jwtTokenEnchancer() {
		return new JwtTokenEnchancer();
	}
}
