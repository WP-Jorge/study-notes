package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

public class ListLibraryServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
        String pageTitle = "Duke's Soccer League: List Leagues";
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
        out.println("  <title>" + pageTitle + "</title>");
        out.println("</head>");
        out.println("<body bgcolor='white'>");
		out.println("<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" height=\"249\">");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>年份</th>");
		out.println("<th>标题</th>");
		out.println("<th>类型</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		
		ServletContext context = this.getServletContext();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		// UserDao userDao = new UserDao();
		DVDService dvdService = new DVDService();
		List<DVDItem> dvdItemList = dvdService.getDVDList(username);
		for (DVDItem dvdItem : dvdItemList) {
			out.println("<tr>");
			out.println("  <td>" + dvdItem.getYear() + "</td>");
			out.println("  <td>" + dvdItem.getTitle() + "</td>");
			out.println("  <td>" + dvdItem.getGenre() + "</td>");
			out.println("</tr>");
		}
		
		
		out.println("</tbody>");
		out.println("</table>");
        out.println("</body>");
        out.println("</html>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
