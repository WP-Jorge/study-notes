package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 两个字节流的介绍
 * getWriter()和getOutputStream()不可同时使用，不然报错
 */

public class RequestIOServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.这两个不同同时使用
		// response.getWriter();
		// response.getOutputStream();
		
		// 2.要求：往客户端回传字符串数据
		PrintWriter writer = response.getWriter();
		writer.println("response's content!!!");
		// 使用中文出现乱码
		System.out.println("response的字符集：" + response.getCharacterEncoding());
		writer.println("response的内容！！！");
		
	}
}
