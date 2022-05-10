package handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {
	// 验证登录的用户信息，正确return true ，其它return false
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("11111拦截器的MyInterceptor的preHandle()");
		String loginName = "";
		Object attr = request.getSession().getAttribute("name");
		if (attr != null) {
			loginName = (String) attr;
		}
		// 判断登录的用户是否符合要求
		if (!"张三".equals(loginName)) {
			// 不能访问系统
			// 给用户提示
			request.getRequestDispatcher("/tips.jsp").forward(request, response);
			return false;
		}
		// 登录
		return true;
	}
}
