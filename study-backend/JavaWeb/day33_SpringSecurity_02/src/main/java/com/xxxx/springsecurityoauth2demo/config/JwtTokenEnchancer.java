package com.xxxx.springsecurityoauth2demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


// 添加自定义负载到token中
public class JwtTokenEnchancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
		Map<String, Object> map = new HashMap<>();
		map.put("enhance", "enhancer info");
		((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(map);
		return oAuth2AccessToken;
	}
}
