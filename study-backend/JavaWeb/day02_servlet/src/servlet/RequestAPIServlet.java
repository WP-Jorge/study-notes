package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 常见的API
 */

public class RequestAPIServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1.getRequestURI      获取请求的路径
		System.out.println("URI:" + req.getRequestURI()); // URI:/day02_servlet/requestAPIServlet
		// 2.getRequestURL      获取请求的绝对路径
		System.out.println("URL:" + req.getRequestURL()); // URL:http://localhost:8080/day02_servlet/requestAPIServlet
		// 3.getRemoteHost      获取客户端ip地址
		System.out.println("RemoteHost:" + req.getRemoteHost()); // 使用localhost为基地址Host:0:0:0:0:0:0:0:1 使用127.0.0.1Host:127.0.0.1
		// 4.getHeader          获取请求头
		System.out.println("Header:" + req.getHeader("User-Agent")); // 传入请求头 输出Header:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36
		// 5.getMethod          获取请求方式
		System.out.println("Method:" + req.getMethod()); // Method:GET
		
	}
}
