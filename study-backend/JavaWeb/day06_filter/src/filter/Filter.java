package filter;

import java.io.IOException;

public class Filter implements javax.servlet.Filter {
	/**
	 * 当访问服务器的资源时，过滤器可以将请求拦截下来，完成一些特殊的功能。
	 * 过滤器的作用:
	 *  般用于完成通用的操作。如:登录验证、统一编码处理、敏感字符过滤
	 */
	
	/**
	 * 步骤:
	 * 1.定义一个类，实现接口Filter
	 * 2.复写方法
	 * 3.配置拦截路径
	 */
	public void destroy() {
	}
	
	public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
		// 执行过滤器逻辑代码
		// System.out.println("过滤器被执行了");
		
		// 放行
		chain.doFilter(req, resp);
	}
	
	public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
		
	}
	
}
