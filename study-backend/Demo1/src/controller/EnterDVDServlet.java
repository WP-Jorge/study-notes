package controller;

import model.DVDItem;
import model.ObjectNotFoundException;
import model.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class EnterDVDServlet extends HttpServlet {
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		execute(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		execute(request,response);
	}
	
	public void init() throws ServletException {
	}
	
	
	public void execute(HttpServletRequest request,
	                    HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		List errorMsgs = new LinkedList();
		request.setAttribute("errorMsgs", errorMsgs);
		
		try {
			
			// Retrieve form parameters.
			String yearStr = request.getParameter("year").trim();
			String title = request.getParameter("title").trim();
			// Perform data conversions.
			int year = -1;
			try {
				year = Integer.parseInt(yearStr);
			} catch (NumberFormatException nfe) {
				errorMsgs.add("The 'year' field must be a positive integer.");
			}
			
			// Verify 'Select League' form fields
			if ((year != -1) && ((year < 2000) || (year > 2010))) {
				errorMsgs.add("The 'year' field must within 2000 to 2010.");
			}
			if (title.equals("UNKNOWN")) {
				errorMsgs.add("Please select a DVD title.");
			}
			
			// Send the ErrorPage view if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher view = request.getRequestDispatcher("selectDVDServlet.view");
				
				view.forward(request, response);
			}
			
			// Perform business logic
			ServletContext context = this.getServletContext();
			String dataDirectory = (String) context.getAttribute("dataPath");
			RegisterService registerSvc = new RegisterService(dataDirectory);
			
			// Retrieve the league
			DVDItem dvdItem = registerSvc.getDVDItem(year, title);
			
			// Store the league object in the session-scope
			HttpSession session = request.getSession();
			session.setAttribute("dvdItem", dvdItem);
			
			// Send the Success view
			RequestDispatcher view = request.getRequestDispatcher("selectBorrowerServlet.view");
			
			view.forward(request, response);
			
			// Handle business exceptions
		} catch (ObjectNotFoundException onfe) {
			errorMsgs.add("The DVD you selected does not yet exist."
					+ " Please select another.");
			RequestDispatcher view = request.getRequestDispatcher("/selectDVDServlet.view");
			
			view.forward(request, response);
			
			// Handle any unusual exceptions
		} catch (RuntimeException e) {
			// Log stack trace
			e.printStackTrace(System.err);
			
			// Send the Error view if there were errors
			errorMsgs.add(e.getMessage());
			RequestDispatcher view = request.getRequestDispatcher("selectDVDServlet.view");
			
			view.forward(request, response);
		} // END of try-catch block
	}
}
