package controller;

import model.DVDItem;
import model.DVDItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class AddDVDItemServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		List errorMsgs = new LinkedList();
		request.setAttribute("errorMsgs", errorMsgs);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("  <title>添加DVD</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>使用POST进行访问</h1>");
		out.println("</body>");
		out.println("</html>");
		
		
		int year = 0;
		try {
			String yearStr = request.getParameter("year").trim();
			String title = request.getParameter("title").trim();
			String genre = request.getParameter("genre");
			try {
				year = Integer.parseInt(yearStr);
			} catch (NumberFormatException nfe) {
				errorMsgs.add("The 'year' field must be a positive integer.");
			}
			if ((year != 0) && ((year < 2000) || (year > 2010))) {
				errorMsgs.add("The 'year' field must within 2000 to 2010.");
			}
			if ( genre.equals("UNKNOWN") ) {
				errorMsgs.add("Please select a DVD genre.");
			}
			if (title.length() == 0) {
				errorMsgs.add("Please enter the title of the DVD.");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher view
						= request.getRequestDispatcher("addDVDItemFormServlet.view");
				view.forward(request, response);
				return;
			}
			
			ServletContext context = this.getServletContext();
			String SystemPath=context.getRealPath("/");
			String DVDsFile = SystemPath + context.getInitParameter("data-direstory");
			DVDItemService dvdItemService = new DVDItemService(DVDsFile);
			DVDItem dvdItem = dvdItemService.createDVD(year, genre, title);
			request.setAttribute("DVDItem", dvdItem);

			// Send the Success view
			RequestDispatcher view
					= request.getRequestDispatcher("success.view");
			view.forward(request, response);
			return;

		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher view
					= request.getRequestDispatcher("addDVDItemFormServlet.view");/*add_league.view*/
			view.forward(request, response);

			e.printStackTrace(System.err);

		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
