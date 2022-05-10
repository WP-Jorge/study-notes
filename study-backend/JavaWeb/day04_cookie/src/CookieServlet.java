import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		/**
		 * cookie:
		 * 1.Cookie是服务器端通知客户端保存键值对的一种技术
		 * 2.客户端有了cookie后，每次发送请求都要把cookie发送给服务器
		 * 3.每个cookie不能超过4kb
		 */
		// 1.创建两个cookie
		Cookie cookie1 = new Cookie("name", "张三");
		Cookie cookie2 = new Cookie("age", "18");
		
		// 2.向响应中添加cookie
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		response.getWriter().println("创建cookie成功！");
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			response.getWriter().println(cookie.getName());
		}
	}
}
