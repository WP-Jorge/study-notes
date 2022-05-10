package myservlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取web.xml中配置的上下文参数context=param
        // ServletContext context = getServletConfig().getServletContext();
        // 还能这样写，底层方法一样
        ServletContext context = getServletContext();
        String userName = context.getInitParameter("userName");
        String userPwd = context.getInitParameter("userPwd");
        System.out.println("userName:" + userName + '\n' + "userPwd:" + userPwd);

        // 2.获取当前工程路径，格式：/工程路径
        System.out.println("当前工程路径：" + context.getContextPath());

        /**
         *  / 斜杠被服务器解析地址为：http://ip:port/工程名/ <br>
         */
        // 3.获取工程部署后在服务器硬盘上的绝对路径
        System.out.println("工程部署的路径是：" + context.getRealPath("/"));

        // 4.存数据 作用域为整个web工程
        context.setAttribute("sex", "男");
        context.setAttribute("key1", "value1");
        System.out.println("获取自定义存数据值：" + context.getAttribute("sex"));
        System.out.println("获取自定义存数据值：" + context.getAttribute("key1"));

    }
}
