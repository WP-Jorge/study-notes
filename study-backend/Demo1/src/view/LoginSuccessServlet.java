package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("登陆成功！");
		PrintWriter out = response.getWriter();
		out.println("登陆成功！正在转跳！");
		try {
			wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("index.jsp");
	}
}
