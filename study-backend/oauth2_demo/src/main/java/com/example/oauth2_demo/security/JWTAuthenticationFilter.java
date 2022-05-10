package com.example.oauth2_demo.security;

import com.example.oauth2_demo.exception.MyAuthenticationException;
import com.example.oauth2_demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	UserService userService;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (request.getContentType().equals("application/json;charset=UTF-8") || request.getContentType().equals("application/json")) {
			ObjectMapper mapper = new ObjectMapper();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
			// authenticationBean对象
			Map<String, String> authenticationBean = null;
			try (InputStream is = request.getInputStream()) {
				// 将用户认证数据转换为Map对象
				authenticationBean = mapper.readValue(is, Map.class);
			} catch (IOException e) {
				throw new MyAuthenticationException(e.getMessage());
			}
			// 获取authenticationBean成功后将用户名密码交给UsernamePasswordAuthenticationToken管理
			try {
				if (!authenticationBean.isEmpty()) {
					// 获取账号密码
					String username = authenticationBean.get(SPRING_SECURITY_FORM_USERNAME_KEY);
					String password = authenticationBean.get(SPRING_SECURITY_FORM_PASSWORD_KEY);
					// 调用自己的service登录方法 检查账号密码是否正确
					Boolean isChecked = userService.checkLogin(username, password);
					if (isChecked) {
						// 验证正确后将UsernamePasswordAuthenticationToken交给security管理
						usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
						setDetails(request, usernamePasswordAuthenticationToken);
						return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
					}
				}
			} catch (Exception e) {
				throw new MyAuthenticationException(e.getMessage());
			}
			return null;
		} else {
			return this.attemptAuthentication(request, response);
		}
	}
}
