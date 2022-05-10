package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class SelectBorrowerServlet extends HttpServlet {
	/** There are the default seasons for the US. */
	private static final String DEFAULT_SEX = "male,female";
	
	/** This variable holds the set of seasons. */
	private String[] SEX;
	public void init() {
		String sex_list = getInitParameter("sex-list");
		if (sex_list != null) {
			SEX = sex_list.split(",");
		} else {
			SEX = DEFAULT_SEX.split(",");
		}
	}
	
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
		String pageTitle = "借阅DVD";
		
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
		out.println("<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>");
		out.println("  <td><h3>" + pageTitle + "</h3></td>");
		out.println("</tr>");
		out.println("</table>");
		
		// Create the Progress Monitor
		out.println("<br/>");
		out.println("<table border='1' cellspacing='0' cellpadding='0' width='400'>");
		out.println("<tr height='20'>");
		out.println("  <td bgcolor='#CCCCCC' align='center' valign='center' height='20'>");
		out.println("    <b>1) Select DVD</b>");
		out.println("  </td>");
		out.println("  <td bgcolor='#CCCCFF' align='center' valign='center' height='20'>");
		out.println("    <b>2) Enter Borrower Info</b>");
		out.println(" </td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<br/>");
		
		// Report any errors (if any)
		if ( (errorMsgs != null) && ! errorMsgs.isEmpty() ) {
			out.println("<p>");
			out.println("<font color='red'>Please correct the following errors:");
			out.println("<ul>");
			Iterator items = errorMsgs.iterator();
			while ( items.hasNext() ) {
				String message = (String) items.next();
				out.println("  <li>" + message + "</li>");
			}
			out.println("</ul>");
			out.println("</font>");
			out.println("</p>");
		}
		
		// Present the form
		out.println("<form action='enterBorrowerServlet.do;jsessionid=" + request.getSession().getId() + "' method='POST'>");
		
		// Repopulate the name field
		String name = request.getParameter("name");
		if ( name == null ) {
			name = "";
		}
		out.println("Name: <input type='text' name='name' value='"
				+ name + "' /> <br/><br/>");
		
		String age = request.getParameter("age");
		if ( age == null ) {
			age = "";
		}
		out.println("Age: <input type='text' name='age' value='"
				+ age + "' /> <br/><br/>");
		
		// Repopulate the address field
		out.println("sex: <select name='sex'>");
		String sex = request.getParameter("sex");
		if ( (sex == null) || sex.equals("UNKNOWN") ) {
			out.println("          <option value='UNKNOWN'>select...</option>");
		}
		for ( int i = 0; i < SEX.length; i++ ) {
			out.print("          <option value='" + SEX[i] + "'");
			if ( SEX[i].equals(sex) ) {
				out.print(" selected");
			}
			out.println(">" + SEX[i] + "</option>");
		}
		out.println("        </select> <br/><br/>");
		
		out.println("<input type='Submit' value='借阅' />");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}
}
