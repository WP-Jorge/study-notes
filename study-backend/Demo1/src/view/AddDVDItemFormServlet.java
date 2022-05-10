package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class AddDVDItemFormServlet extends HttpServlet {
	private static final String DEFAULT_GENRES = "热血,科幻,幼儿,动漫";
	private String[] GENRES;
	
	public void init() {
		String genresList = getInitParameter("DVDList");
		if (genresList == null) {
			genresList = DEFAULT_GENRES;
		}
		GENRES = genresList.split(",");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		generateView(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		generateView(request, response);
	}
	
	public void generateView(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// Set page title
		String pageTitle = "添加新的DVD";
		
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
			out.println("<font color='red'>请修正以下错误：");
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
		
		// Generate main body
		out.println("<p>");
		out.println("这个表单可以让你创建一个新的DVD。");
		out.println("</p>");
		out.println("<form action='addDVDItemServlet.do' method='POST'>");
		
		// Repopulate the year field
		String year = request.getParameter("year");
		if ( year == null ) {
			year = "";
		}
		out.println("Year: <input type='text' name='year' value='"
				+ year + "' /> <br/><br/>");
		
		// Repopulate the season drop-down menu
		String genre = request.getParameter("genre");
		out.println("Genre: <select name='genre'>");
		if ( (genre == null) || genre.equals("UNKNOWN") ) {
			out.println("          <option value='UNKNOWN'>select...</option>");
		}
		for ( int i = 0; i < GENRES.length; i++ ) {
			out.print("          <option value='" + GENRES[i] + "'");
			if ( GENRES[i].equals(genre) ) {
				out.print(" selected");
			}
			out.println(">" + GENRES[i] + "</option>");
		}
		out.println("        </select> <br/><br/>");
		
		// Repopulate the title field
		String title = request.getParameter("title");
		if ( title == null ) {
			title = "";
		}
		out.println("Title: <input type='text' name='title' value='"
				+ title + "' /> <br/><br/>");
		
		out.println("<input type='Submit' value='Add League' />");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}
}
