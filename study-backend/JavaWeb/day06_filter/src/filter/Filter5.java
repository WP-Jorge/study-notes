package filter;

import javax.servlet.*;
import javax.servlet.Filter;
import java.io.IOException;

public class Filter5 implements Filter {
	/**
	 * FilterConfig类见名知义，它是Filter过滤器的配置文件类。
	 * Tomcat每次创建Filter的时候，也会同时创建一个FilterConfig类，这里包含了Filter 配置文件的配置信息。
	 * FilterConfig类的作用是获取filter过滤器的配置内容
	 * 1、获取Filter的名称filter-name的内容
	 * 2、获取在web.xml中配置的init-param初始化参数
	 * 3、获取ServletContext对象
	 */
	public void destroy() {
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		chain.doFilter(req, resp);
	}
	
	public void init(FilterConfig config) throws ServletException {
		// 1、获取Filter的名称filter-name的内容
		System.out.println("Filter-name的值是：" + config.getFilterName());
		// 2、获取在web.xml中配置的init-param初始化参数
		System.out.println("web.xml中的配置的参数是：" + config.getInitParameter("username"));
		// 3、获取ServletContext对象
		System.out.println("ServletContext对象是：" + config.getServletContext());
	}
	
}
