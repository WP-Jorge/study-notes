package servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet {
	/*
	 * Servlet生命周期函数
	 * 1 执行Servlet构造器方法
	 * 2 执行init初始化方法
	 * 3 执行service方法
	 * 4 执行destory方法
	 * */
	public HelloServlet() {
		// 先执行这个
		System.out.println("1 构造器方法");
	}
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// 再执行这个
		System.out.println("2 初始化方法");
		
		// 1 获取servlet程序的别名servlet-name
		System.out.println("HelloServlet程序的别名是：" + servletConfig.getServletName());
		// 2 获取初始化参数init-param
		System.out.println("初始化参数名userName：" + servletConfig.getInitParameter("userName"));
		System.out.println("初始化参数名userPwd：" + servletConfig.getInitParameter("userPwd"));
		
		// 3 获取ServletContext对象
	}
	
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}
	
	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
		// 然后执行这个 每次访问都会被调用
		System.out.println("3 servlet方法 Hello Servlet 被访问了");
		// 获取请求方式
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String method = httpServletRequest.getMethod();
		// System.out.println(method);
		if ("GET".equals(method)) {
			doGet();
		} else {
			doPost();
		}
	}
	
	/*
	 * get请求操作*/
	public void doGet() {
		System.out.println("GET方法");
		
	}
	
	/*
	 * post请求操作*/
	public void doPost() {
		System.out.println("POST方法");
	}
	
	@Override
	public String getServletInfo() {
		return null;
	}
	
	@Override
	public void destroy() {
		// web工程停止的时候会执行
		System.out.println("4 distory方法");
	}
}
