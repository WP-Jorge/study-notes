package controller;

import model.Borrower;
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

public class EnterBorrowerServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public EnterBorrowerServlet() {
		super();
	}
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		execute(request,response);
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request,response);
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	
	public void execute(HttpServletRequest request,
	                    HttpServletResponse response) throws ServletException, IOException{
		
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
			
			// Verify 'Enter Player Information' form fields
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
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher view = request.getRequestDispatcher("selectBorrowerServlet.view");
				
				view.forward(request, response);
			}
			
			ServletContext context = this.getServletContext();
			String dataDirectory = (String)context.getAttribute("dataDirectory");
			RegisterService registerSvc = new RegisterService(dataDirectory);
			
			// Update the player info
			Borrower borrower = registerSvc.getBorrower(name);
			// System.out.println(age);
			borrower.setAge(age);
			borrower.setName(name);
			borrower.setSex(sex);
			
			// Store the player object in the session-scope
			HttpSession session = request.getSession();
			session.setAttribute("borrower", borrower);
			
			// Send the Success view
			RequestDispatcher view = request.getRequestDispatcher("thanks.view");
			
			view.forward(request, response);
			
			// Handle any unusual exceptions
		} catch (RuntimeException e) {
			// Log stack trace
			e.printStackTrace(System.err);
			
			// Send the Error view if there were errors
			errorMsgs.add(e.getMessage());
			RequestDispatcher view=request.getRequestDispatcher("selectBorrowerServlet.view");
			
			view.forward(request, response);
			
		} // END of try-catch block
		
		
	}
}
