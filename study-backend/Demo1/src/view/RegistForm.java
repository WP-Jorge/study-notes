package view;

import model.DVDItem;
import model.DVDItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class RegistForm extends HttpServlet {
	/** There are the default seasons for the US. */
	private static final String DEFAULT_SEX = "male,female";
	
	/** This variable holds the set of seasons. */
	private String[] SEX;
	
	/** There are the default divisions for the US. */
	private static final String DEFAULT_TITLE
			= "猪猪侠,蜘蛛侠,超人强,龙珠,航海王";
	
	/** This variable holds the set of divisions. */
	private String[] TITLES;
	
	/** The init method configures the set of seasons and divisions. */
	public void init() {
		String sex_list = getInitParameter("sex-list");
		if ( sex_list != null ) {
			SEX = sex_list.split(",");
		} else {
			SEX = DEFAULT_SEX.split(",");
		}
		// String divisions_list = getInitParameter("divisions-list");
		String titles_list = "";
		
		String dataPath = (String) getServletContext().getAttribute("dataPath");
		// dataPath = dataPath + "my-library.txt";
		
		DVDItemService dvdItemService = new DVDItemService(dataPath);
		List dvdlist = dvdItemService.getAllDVDItems();
		Iterator set = dvdlist.iterator();
		while (set.hasNext()) {
			DVDItem dvdItem = (DVDItem) set.next();
			titles_list = titles_list + dvdItem.getTitle() + ',';
		}
		
		if ( titles_list != null ) {
			TITLES = titles_list.split(",");
		} else {
			TITLES = DEFAULT_TITLE.split(",");
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
		
		// Report any errors (if any)
		if ( errorMsgs != null ) {
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
		
		// Present the registration form
		out.println("<p>");
		out.println("这个表单可以让你借阅DVD");
		out.println("</p>");
		out.println("<form action='borrowServlet.do' method='POST'>");
		
		//
		// "Select a league" fields
		//
		
		
		//
		// "Enter player info" fields
		//
		out.println("<h4>Enter Borrower Information</h4>");
		// Repopulate the name field
		String name = request.getParameter("name");
		if ( name == null ) {
			name = "";
		}
		out.println("Name: <input type='text' name='name' value='"
				+ name + "' /> <br/><br/>");
		// Repopulate the address field
		String age = request.getParameter("age");
		if ( age == null ) {
			age = "";
		}
		out.println("Age: <input type='text' name='age' value='"
				+ age + "' /> <br/><br/>");
		
		// Repopulate the season drop-down menu
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
		// Repopulate the city field
		// String city = request.getParameter("city");
		// if ( city == null ) {
		// 	city = "";
		// }
		// out.println("City: <input type='text' name='city' value='"
		// 		+ city + "' /> <br/><br/>");
		// Repopulate the province field
		// String province = request.getParameter("province");
		// if ( province == null ) {
		// 	province = "";
		// }
		// out.println("Province: <input type='text' name='province' value='"
		// 		+ province + "' /> <br/><br/>");
		// Repopulate the postalCode field
		// String postalCode = request.getParameter("postalCode");
		// if ( postalCode == null ) {
		// 	postalCode = "";
		// }
		// out.println("Postal code: <input type='text' name='postalCode' value='"
		// 		+ postalCode + "' /> <br/><br/>");
		
		//
		// "Select a division" field
		//
		out.println("<h4>Select a Title</h4>");
		// Repopulate the division drop-down menu
		out.println("Title: <select name='title'>");
		String title = request.getParameter("title");
		if ( (title == null) || title.equals("UNKNOWN") ) {
			out.println("          <option value='UNKNOWN'>select...</option>");
		}
		for ( int i = 0; i < TITLES.length; i++ ) {
			out.print("          <option value='" + TITLES[i] + "'");
			if ( TITLES[i].equals(title) ) {
				out.print(" selected");
			}
			out.println(">" + TITLES[i] + "</option>");
		}
		out.println("        </select> <br/><br/>");
		// out.println("<h4>Select a DVD</h4>");
		// Repopulate the year field
		String year = request.getParameter("year");
		if ( year == null ) {
			year = "";
		}
		out.println("Year: <input type='text' name='year' value='"
				+ year + "' /> <br/><br/>");
		
		out.println("<input type='Submit' value='Register' />");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}
}
