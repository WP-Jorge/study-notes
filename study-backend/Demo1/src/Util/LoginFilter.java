package Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
	public void destroy() {
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("username");
		// System.out.println(request.getRequestURI());
		Boolean isLogin = null;
		isLogin = request.getRequestURI().contains("loginServlet.do");
		if (isLogin || token != null) {
			chain.doFilter(req, resp);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("login.view");
			view.forward(req, resp);
		}
	}
	
	public void init(FilterConfig config) throws ServletException {
	
	}
}
