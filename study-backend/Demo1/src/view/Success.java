package view;

import model.DVDItem;
import model.DVDItemService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Success extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		generateView(request, response);
	}
	
	private void generateView(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// Set page title
		String pageTitle = "热门电影动画：添加DVD成功页面";
		
		// Retrieve the 'league' from the request-scope
		DVDItem item = (DVDItem) request.getAttribute("DVDItem");
		
		
		
		// Specify the content type is HTML
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// Generate the HTML response
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("  <title>" + pageTitle + "</title>");
		out.println("</head>");
		out.println("<body bgcolor='white'>");
		
		// Generate page heading
		out.println("<!-- Page Heading -->");
		out.println("<table border='1' cellpadding='5' cellspacing='0' width='400'>");
		out.println("<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>");
		out.println("  <td><h3>" + pageTitle + "</h3></td>");
		out.println("</tr>");
		out.println("</table>");
		
		// Generate main body
		out.println("<p>");
		out.print("Your request to add the ");
		out.print("<i>" + item.getTitle() + "</i>");
		out.println(" title was successful.");
		out.println("</p>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		generateView(request, response);
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
	}
}
