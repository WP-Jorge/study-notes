package com.example.boxmusic.security;

import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailHandler extends JsonUtil implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		R r = R.warning(e.getMessage());
		this.WriteJSON(httpServletRequest, httpServletResponse, r);
	}
}
