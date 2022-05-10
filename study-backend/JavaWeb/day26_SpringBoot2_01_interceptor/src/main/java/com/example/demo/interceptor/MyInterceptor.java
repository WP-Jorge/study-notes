package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查
 * 1.实现Handlerincepterceptor接口
 */
public class MyInterceptor implements HandlerInterceptor {
	
	/**
	 * 目标完成之前
	 * 1.配置好拦截器要拦截那些请求
	 * 2.把这些配置放置在容器中
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 登录检查逻辑
		HttpSession session = request.getSession();
		Object loginUser = session.getAttribute("loginUser");
		System.out.println(request.getRequestURI() + "==访问");
		if (loginUser != null) {
			// 放行
			System.out.println(request.getRequestURI() + "==放行");
			return true;
		} else {
			// 拦截
			System.out.println(request.getRequestURI() + "==被拦截");
			// 被拦截，重定向到登录页,推荐使用重定向
			response.sendRedirect("/login");
			// 可以使用请求转发
			// request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}
	}
	
	/**
	 * 目标完成之后
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	
	}
	
	/**
	 * 页面渲染后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	
	}
}
