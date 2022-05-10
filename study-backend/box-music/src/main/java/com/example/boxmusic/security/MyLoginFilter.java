package com.example.boxmusic.security;

import com.example.boxmusic.exception.MyAuthenticationException;
import com.example.boxmusic.exception.MyPasswordErrorException;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.utils.RedisUtil;
import com.example.boxmusic.utils.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// 1、判断是否是 post 方式登录
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		// 2、判断是否是 json 格式的请求类型
		if (Value.CONTENT_TYPE_JSON_UTF8.equals(request.getContentType()) || Value.CONTENT_TYPE_JSON.equals(request.getContentType())) {
			// 3、从 json 数据中获取用户输入的用户名和密码进行认证
			ObjectMapper mapper = new ObjectMapper();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
			Map<String, String> authenticationBean = null;
			try {
				// 将用户登录数据转换为 Map 对象
				authenticationBean = mapper.readValue(request.getInputStream(), Map.class);
				if (!authenticationBean.isEmpty()) {
					// 检验验证码是否正确
					String verificationCode = authenticationBean.get(Value.VERIFICATION_CODE);
					String codeId = authenticationBean.get(Value.CODE_ID);
					String code = redisUtil.getVerificationCode("code_" + codeId);
					if (code == null || verificationCode == null || !code.equals(verificationCode)) {
						throw new MyPasswordErrorException("验证码错误");
					}
					redisUtil.removeVerificationCode("code_" + codeId);
					
					// 获取账号密码
					String username = authenticationBean.get(getUsernameParameter());
					String password = authenticationBean.get(getPasswordParameter());
					
					// 调用自己的 service 登录方法 检查账号密码是否正确
					Boolean isChecked = userService.checkLogin(username, password);
					if (isChecked) {
						// 验证正确后将 UsernamePasswordAuthenticationToken 交给 security 管理
						// 获取 authenticationBean 成功后将用户名密码交给 UsernamePasswordAuthenticationToken 管理
						usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
						// 允许子类设置 details
						setDetails(request, usernamePasswordAuthenticationToken);
						return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
					}
				}
			} catch (MyPasswordErrorException e) {
				throw new MyPasswordErrorException(e.getMessage());
			} catch (IOException e) {
				throw new MyAuthenticationException(e.getMessage());
			} catch (Exception e) {
				throw new MyAuthenticationException(e.getMessage());
			}
			return null;
		} else {
			return this.attemptAuthentication(request, response);
		}
	}
}
