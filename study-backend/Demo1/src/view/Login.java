package view;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected  void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("\t<title>登录</title>");
		out.println("\t<style type=\"text/css\">");
		out.println("        * {");
		out.println("\t\t\tpadding: 0;");
		out.println("\t\t\tmargin: 0;");
		out.println("\t\t}");
		out.println("       .container {");
		out.println("\t\t\twidth: 300px;");
		out.println("\t\t\tmargin: 100px auto;");
		out.println("\t\t}");
		out.println("        .container h2 {");
		out.println("\t\t\ttext-align: center;");
		out.println("\t\t}");
		out.println("\t\tform input:nth-child(1),form input:nth-child(3) {");
		out.println("\t\t\twidth: 100%;");
		out.println("\t\t\theight: 30px;");
		out.println("\t\t\tmargin-bottom: 10px;");
		out.println("\t\t}");
		out.println("\t\tform input:nth-child(1) {");
		out.println("\t\t\tmargin-top: 10px;");
		out.println("\t\t}");
		out.println("\t</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		String errMsg = (String) request.getAttribute("errorMsgs");
		if (errMsg != null) {
			out.println("<p style='color: red'>" + errMsg + "</p>");
		}
		out.println("\t<h2>登录DVD借阅系统</h2>");
		out.println("\t<div class=\"form\">");
		out.println("\t\t<form action=\"loginServlet.do\" method=\"post\">");
		out.println("\t\t\t<input type=\"text\" name=\"username\" placeholder=\"请输入用户名\" />");
		out.println("\t\t\t<br />");
		out.println("\t\t\t<input type=\"password\" name=\"password\" placeholder=\"请输入密码\" />");
		out.println("\t\t\t<br />");
		out.println("\t\t\t<input type=\"submit\" value=\"登录\"/>");
		out.println("\t\t</form>");
		out.println("\t</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
