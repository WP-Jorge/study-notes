package filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Filter4 implements Filter {
	/**
	 * 过滤器链（配置多个过滤器）
	 * 执行顺序：有两个过滤器：过滤器1，过滤器2
	 * 1.过滤器1
	 * 2.过滤器2
	 * 3.资源执行
	 * 4.过滤器2
	 * 5.过滤器1
	 * 过滤器先后顺序：
	 * 1.注解配置：按照类名的字符串比较规则比较，值小的先执行
	 * ①如Afilter和Bfilter，A比B小，先执行Afilter
	 * 2.web.xml配置：谁在上面谁先执行
	 */
	public void destroy() {
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		// 如果要获取URL、URI等资源，需要进行强转
		HttpServletRequest request = (HttpServletRequest) req;
		chain.doFilter(req, resp);
	}
	
	public void init(FilterConfig config) throws ServletException {
		
	}
	
}
