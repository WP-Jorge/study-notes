package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 */
public class RedirectServlet1 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 请求重定向特点：
		 * 1.地址栏有变化
		 * 2.发送两次请求
		 * 3.不共享request域中的数据
		 * 4.不能访问WEB-INFO中的对象
		 * 5、可以访问外面的网址，如www.baidu.com
		 *
		 * 一切的原因：相当于重新发送请求访问，相当于浏览器直接访问
		 *
		 * 请求转发对内，请求重定向向外
		 */
		
		// // 第一种方法：
		// System.out.println("到此一游 RedirectServlet1");
		// // 设置响应状态码302 表示重定向， （已搬迁）
		// response.setStatus(302);
		//
		// // 设置响应头,说明新的地址在哪里
		// response.setHeader("location", "http://localhost:8080/day03_servlet/redirectServlet2");
		
		// 第二种方法：（推荐使用）
		response.sendRedirect("http://localhost:8080/day03_servlet/redirectServlet2");
	}
}
