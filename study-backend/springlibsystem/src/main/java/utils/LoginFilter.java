package utils;

import domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:03 2021/4/21
 * @Param 
 * @return 
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements javax.servlet.Filter {
	@Override
	public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp,
	                     javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		User token = null;
		User user = new User();
		HttpSession session = request.getSession();
		String userParam = "user";
		if (session.getAttribute(userParam) != null) {
			token = user;
		}
		Boolean isLogin = null;
		Boolean isStatic = null;
		Boolean isRegist = null;
		
		isLogin = request.getRequestURI().contains("login.do");
		isRegist = request.getRequestURI().contains("regist");
		isStatic = request.getRequestURI().contains("static");
		
		if (token != null || isStatic || isLogin || isRegist) {
			chain.doFilter(req, resp);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(req, resp);
		}
	}
}