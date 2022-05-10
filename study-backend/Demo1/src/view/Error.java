package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class Error extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		generateView(request, response);
	}
	
	private void generateView(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// Set page title
		String pageTitle = "热门电影动画：添加DVD失败页面";
		
		// Retrieve the errorMsgs from the request-scope
		List errorMsgs = (List) request.getAttribute("errorMsgs");
		
		// Specify the content type is HTML
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// Generate the HTML response
		out.println("<html>");
		out.println("<head>");
		out.println("  <title>" + pageTitle + "</title>");
		out.println("</head>");
		out.println("<body bgcolor='white'>");
		
		// Generate page heading
		out.println("<!-- Page Heading -->");
		out.println("<table border='1' cellpadding='5' cellspacing='0' width='400'>");
		out.println("<tr bgcolor='red' align='center' valign='center' height='20'>");
		out.println("  <td><h3>" + pageTitle + "</h3></td>");
		out.println("</tr>");
		out.println("</table>");
		
		// Generate main body
		out.println("<p>");
		out.println("<font color='red'>请修正以下错误：");
		out.println("<ul>");
		Iterator items = errorMsgs.iterator();
		while ( items.hasNext() ) {
			String message = (String) items.next();
			out.println("  <li>" + message + "</li>");
		}
		out.println("</ul>");
		out.println("请返回重新添加一遍。");
		out.println("</font>");
		out.println("</p>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		generateView(request, response);
	}
}
