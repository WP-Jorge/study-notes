package com.example.oauth2_demo.security;

import com.example.oauth2_demo.security.JSONAuthentication;
import com.example.oauth2_demo.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFailHandler extends JSONAuthentication implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		// R<Object> data = new R<Object>();
		// data.setCode(-1);
		// data.setMsg("登录失败" + e.getMessage());
		
		R r = R.unauthorized("登录失败" + e.getMessage());
		
		
		// 输出
		this.WriteJSON(httpServletRequest, httpServletResponse, r);
	}
}
