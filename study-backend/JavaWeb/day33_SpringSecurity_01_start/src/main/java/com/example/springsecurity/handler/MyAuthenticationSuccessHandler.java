package com.example.springsecurity.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 实现自定义登录成功重定向
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private String url;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		// 获取ip
		System.out.println(httpServletRequest.getRemoteAddr());
		
		// 获取登录的 用户
		User user = (User) authentication.getPrincipal();
		// 基于安全考虑，密码会被隐藏
		System.out.println(user);
		httpServletResponse.sendRedirect(url);
	}
}
