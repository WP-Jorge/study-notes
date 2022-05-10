package filter;

import javax.servlet.*;
import javax.servlet.Filter;
import java.io.IOException;

public class Filter3 implements Filter {
	/**
	 * 一、过滤器拦截路径配置
	 * 1.具体的资源路径：/index.jsp    只有访问index.jsp资源时过滤器才会被执行
	 * 2.拦截目录：/user/*            访问/user目录下的所有资源时才会执行过滤器
	 * 3.后缀名拦截：*.jsp            拦截后缀名为.jsp的资源时过滤器才会被执行
	 * 4.拦截所有资源：/*
	 * 二、拦截方式的配置：资源被访问的方式
	 * 1.web-xml中配置 <dispacther></dispacther>
	 * ①REQUEST：默认值，浏览器直接请求资源
	 * ②FORWORD：转发请求资源
	 * ③INCLUDE：包含访问资源
	 * ④ERROR：错误转跳资源
	 * ⑤ASYNC：拦截异步访问资源
	 */
	public void destroy() {
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		chain.doFilter(req, resp);
	}
	
	public void init(FilterConfig config) throws ServletException {
		
	}
	
}
