package com.example.springsecurity.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 自定义403异常处理
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		// 响应状态
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		// 返回json
		httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writer = httpServletResponse.getWriter();
		writer.write("{\"state\": 403}, \"msg\": \"权限不足\"}");
		writer.flush();
		writer.close();
	}
}
