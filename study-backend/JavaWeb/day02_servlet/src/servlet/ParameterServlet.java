package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取参数值
 */
public class ParameterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------Post请求-------------");
		// post不设置字符集则出现乱码，设置请求体字符集utf-8  需要在全部代码前调用
		request.setCharacterEncoding("UTF-8");
		// 获取form表单传来的Get参数request.getParameter("参数名")
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		// 获取多值request.getParameterValues("参数名")，以数组接收
		String hobbys[] = request.getParameterValues("hobby");
		System.out.println("userName:" + userName);
		System.out.println("userPwd:" + userPwd);
		for (String hobby : hobbys) {
			System.out.println("hobbys：" + hobby);
		}
		// System.out.println("hobbys:" + hobbys);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------Get-------------");
		// 设置请求体字符集utf-8  需要在全部代码前调用
		request.setCharacterEncoding("UTF-8");
		// 获取form表单传来的Get参数request.getParameter("参数名")
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		// 获取多值request.getParameterValues("参数名")，以数组接收
		String hobbys[] = request.getParameterValues("hobby");
		System.out.println("userName:" + userName);
		System.out.println("userPwd:" + userPwd);
		for (String hobby : hobbys) {
			System.out.println("hobbys：" + hobby);
		}
		// System.out.println("hobbys:" + hobbys);
	}
}
