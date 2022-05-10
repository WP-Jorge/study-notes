package controller;

import model.User;
import model.UserDao;
import model.UserService;

import javax.naming.Context;
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

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// UserDao userDao = new UserDao();
		UserService userService = new UserService();
		List<User> user = userService.getUser(username, password);
		
		
		if (user.isEmpty()) {
			request.setAttribute("errorMsgs", "用户名或密码输入错误，请重新输入！");
		}
		
		RequestDispatcher view = null;
		try {
			if (!user.isEmpty()) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				request.setAttribute("username", username);
				view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("login.view");
				view.forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
