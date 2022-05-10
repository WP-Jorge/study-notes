package filter;

import javax.servlet.*;
import javax.servlet.Filter;
import java.io.IOException;

public class Filter2 implements Filter {
	/**
	 * filter的生命周期
	 *
	 * @param config
	 * @throws ServletException
	 */
	public void init(FilterConfig config) throws ServletException {
		// 在服务器启动后会创建Filter对象，然后调用init方法
		// 初始化
		System.out.println("init....");
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		// 对request对象做请求消息增强
		System.out.println("filter2执行了");
		
		// 放行
		chain.doFilter(req, resp);
		
		// 对response对象做响应消息增强
		System.out.println("filter2回来了");
	}
	
	public void destroy() {
		// 在服务器正常关闭后会销毁Filter对象，然后执行destory方法， 一般用于释放资源
		// 销毁
		System.out.println("destory....");
	}
	
}
