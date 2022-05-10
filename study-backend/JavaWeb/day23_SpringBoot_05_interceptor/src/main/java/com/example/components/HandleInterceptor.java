package com.example.components;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleInterceptor implements HandlerInterceptor {
	// 目标方法执行之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object loginUser = request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			// 未登录放回登录页面
			request.getRequestDispatcher("/login.html").forward(request, response);
			return false;
		}
		// 已登录，放行请求
		return true;
	}
	
	//
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}
