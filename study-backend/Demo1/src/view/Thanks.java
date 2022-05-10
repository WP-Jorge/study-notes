package view;

import model.Borrower;
import model.DVDItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Thanks extends HttpServlet {
	public void doGet(HttpServletRequest request,
	                  HttpServletResponse response)
			throws IOException {
		generateView(request, response);
	}
	
	public void doPost(HttpServletRequest request,
	                   HttpServletResponse response)
			throws IOException {
		generateView(request, response);
	}
	
	public void generateView(HttpServletRequest request,
	                         HttpServletResponse response)
			throws IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// Set page title
		String pageTitle = "借阅成功！";
		
		// Retrieve the 'league' and 'player' from the session-scope
		HttpSession session = request.getSession();
		DVDItem dvdItem = (DVDItem) session.getAttribute("dvdItem");
		Borrower borrower = (Borrower) session.getAttribute("borrower");
		
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
		out.println("<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>");
		out.println("  <td><h3>" + pageTitle + "</h3></td>");
		out.println("</tr>");
		out.println("</table>");
		
		// Present the main body
		out.println("<p>");
		out.print("Thank you " + borrower.getName() + " for registering ");
		out.println("for the <i>" + dvdItem.getTitle() + "</i> DVD successfully!.");
		out.println("</p>");
		
		out.println("</body>");
		out.println("</html>");
	}
}
