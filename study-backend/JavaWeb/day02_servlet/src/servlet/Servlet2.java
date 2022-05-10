package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数（办事材料）查看
		String userName = request.getParameter("userName");
		System.out.println("在servlet2（柜台2）中查看参数（材料）userName：" + userName);
		
		// 查看servlet1（柜台1）中的章
		Object key = request.getAttribute("key");
		System.out.println("柜台2中查看柜台1的章：" + key);
		
		// 处理自己的业务
		System.out.println("servlet2处理自己的业务");
	}
}
