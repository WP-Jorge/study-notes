package com.xxxx.springsecurityoauth2demo.config;

import com.xxxx.springsecurityoauth2demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

/**
 * @author zhoubin
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	// @Autowired
	// @Qualifier("redisTokenStore")
	// private TokenStore tokenStore;
	@Autowired
	@Qualifier("jwtTokenStore")
	private TokenStore tokenStore;
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	@Autowired
	private JwtTokenEnchancer jwtTokenEnchancer;

	/**
	 * 密码模式
	 *
	 * @param endpoints
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// 设置JWT增强内容
		TokenEnhancerChain chain = new TokenEnhancerChain();
		ArrayList<TokenEnhancer> delegates = new ArrayList<>();
		// 将自定义负载放进去
		delegates.add(jwtTokenEnchancer);
		// 将JWTToken转换器放进去
		delegates.add(jwtAccessTokenConverter);
		chain.setTokenEnhancers(delegates);
		
		endpoints.authenticationManager(authenticationManager)
				.userDetailsService(userService)
				// .tokenStore(tokenStore);
				// accessToken转成JWTToken
				.tokenStore(tokenStore)
				.accessTokenConverter(jwtAccessTokenConverter)
				.tokenEnhancer(chain);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				//客户端ID
				.withClient("client")
				//秘钥
				.secret(passwordEncoder.encode("112233"))
				//重定向地址
				.redirectUris("http://localhost:8081/login")
				//授权范围
				.scopes("all")
				// 设置失效时间
				.accessTokenValiditySeconds(30)
				// 设置刷新令牌的过期时间
				.refreshTokenValiditySeconds(60 * 60 * 1)
				// 自动授权
				.autoApprove(true)
				/**
				 * 授权类型
				 * authorization_code：授权码模式
				 * password:密码模式
				 * refresh_token：刷新令牌
				 */
				.authorizedGrantTypes("authorization_code", "password", "refresh_token");
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// 获取密钥，必须要身份认证，单点登录必备配置
		security.tokenKeyAccess("isAuthenticated()");
	}
}