package view;

import model.Borrower;
import model.DVDItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BorrowSuccess extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		generateView(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		generateView(request, response);
	}
	
	public void generateView(HttpServletRequest request,
	                         HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// Set page title
		String pageTitle = "恭喜你，借阅成功！";
		
		// Retrieve the 'league' and 'player' from the request-scope
		DVDItem dvdItem = (DVDItem) request.getAttribute("dvdItem");
		Borrower borrower = (Borrower) request.getAttribute("borrower");
		
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
		out.print("Thank you " + borrower.getName() + " for borrowing for the ");
		out.println("<i>" + dvdItem.getTitle() + "</i> DVD successfully!");
		out.println("</p>");
		
		out.println("</body>");
		out.println("</html>");
	}
}
