package controller;

import model.Borrower;
import model.DVDItem;
import model.ObjectNotFoundException;
import model.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorrowServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// Keep a set of strings to record form processing errors.
		List errorMsgs = new LinkedList();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		request.setAttribute("errorMsgs", errorMsgs);
		
		try {
			
			// Retrieve form parameters.
			String name = request.getParameter("name").trim();
			String ageStr = request.getParameter("age").trim();
			String sex = request.getParameter("sex").trim();
			String yearStr = request.getParameter("year").trim();
			String title = request.getParameter("title").trim();
			
			if ( name.length() == 0 ) {
				errorMsgs.add("You must enter your full name.");
			}
			
			int age = -1;
			try {
				age = Integer.parseInt(ageStr);
			} catch (NumberFormatException nfe) {
				errorMsgs.add("The 'age' field must be a positive integer.");
			}
			// System.out.println(age);
			
			if ( sex.equals("UNKNOWN") ) {
				errorMsgs.add("Please select a sex.");
			}
			
			if ( title.equals("UNKNOWN") ) {
				errorMsgs.add("Please select a DVD title.");
			}
			
			int year = -1;
			try {
				year = Integer.parseInt(yearStr);
			} catch (NumberFormatException nfe) {
				errorMsgs.add("The 'year' field must be a positive integer.");
			}
			
			// Send the ErrorPage view if there were errors
			if ( ! errorMsgs.isEmpty() ) {
				RequestDispatcher view
						= request.getRequestDispatcher("registForm.view");
				view.forward(request, response);
				return;
			}
			
			// Perform business logic
			String dataPath = (String)getServletContext().getAttribute("dataPath");
			// System.out.println("BorrowSrvlet" + dataPath);
			
			RegisterService registerSvc = new RegisterService(dataPath);
			// Retrieve the league
			DVDItem dvdItem = registerSvc.getDVDItem(year, title);
			// Update the player info
			Borrower borrower = registerSvc.getBorrower(name);
			// System.out.println(age);
			borrower.setAge(age);
			borrower.setName(name);
			borrower.setSex(sex);
			borrower.setTitle(title);
			
			// Perform the registration
			registerSvc.register(dvdItem, borrower, 15);
			
			// Store the league and player objects in the request-scope
			request.setAttribute("dvdItem", dvdItem);
			request.setAttribute("borrower", borrower);
			// request.setAttribute("title", title);
			
			// Send the Success view
			RequestDispatcher view
					= request.getRequestDispatcher("borrowSuccess.view");
			view.forward(request, response);
			return;
			
			// Handle business exceptions
		} catch (ObjectNotFoundException onfe) {
			errorMsgs.add("The DVD you selected does not yet exist."
					+ " Please select another.");
			RequestDispatcher view
					= request.getRequestDispatcher("registForm.view");
			view.forward(request, response);
			
			// Handle any unusual exceptions
		} catch (RuntimeException e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher view
					= request.getRequestDispatcher("registForm.view");
			view.forward(request, response);
			
			// Log stack trace
			e.printStackTrace(System.err);
			
		} // END of try-catch block
		
	} // END of doPost method
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
