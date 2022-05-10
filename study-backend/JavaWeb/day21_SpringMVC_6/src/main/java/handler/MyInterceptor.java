package handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("11111拦截器的MyInterceptor的preHandle()");
		return false;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		if (mv != null) {
			mv.addObject("mydate", new Date());
			mv.setViewName("other");
		}
		System.out.println("11111拦截器的MyInterceptor的postHandle()");
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("11111拦截器的MyInterceptor的afterCompletion()");
	}
}
