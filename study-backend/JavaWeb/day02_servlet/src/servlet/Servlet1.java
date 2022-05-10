package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发
 * 请求转发的特点：
 * 1.浏览器地址栏没有变化
 * 2.他们是一次请求
 * 3。所以他们共享request域中的数据
 * 4.可以通过请求转发访问WEB-INF中的目录文件（不使用请求转发则无法访问）
 * 5.无法访问工程目录外的地址
 */
public class Servlet1 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置浏览器字符集，解决客户端乱码
		// response.setHeader("Content-Type", "text//html; charset=UTF-8");
		
		// 设置服务器和客户端同时使用字符集UTF-8, 还设置了响应头
		// 此方法只有在获取流对象之前调用才有效
		response.setContentType("text/html; charset=UTF-8");
		
		// 1.获取请求参数（办事材料）
		String userName = request.getParameter("userName");
		System.out.println("在servlet1（柜台1）中查看参数（材料）userName：" + userName);
		
		// 2.给材料盖章，并传到柜台二（servlet2）
		request.setAttribute("key", "柜台1的章");
		
		// 3.问路：柜台二（servlet2）怎么走
		/**
		 *请求转发必须以斜杠打头，斜杆表示地址为：http://ip:端口号/工程名/，映射到idea代码的web目录
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet2");
		// 请求转发访问WEB-INF中的目录
		// RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/form.html");
		// 无法访问工程目录外的地址
		// RequestDispatcher requestDispatcher = request.getRequestDispatcher("http://baidu.com");
		
		
		// 4.走向servlet2（柜台2）
		requestDispatcher.forward(request, response);
	}
}
