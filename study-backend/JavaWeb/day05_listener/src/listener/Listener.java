package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class Listener implements ServletContextListener {
	/**
	 * 1、Listener 监听器它是JavaWeb的三大组件之一。JavaWeb 的三大组件分别是: Servlet 程序、Filter 过滤器、Listener 监听器。
	 * 2、Listener 它是JavaEE 的规范，就是接口.
	 * 3、监听器的作用是，监听某种事物的变化。然后通过回调函数，反馈给客户(程序)去做一些相应的处理。
	 */
	
	/**
	 * ServletContextListener它可以监听ServletContext对象的创建和销毁。
	 * ServletContext对象在web工程启动的时候创建,在web工程停止的时候销毁。
	 * 监听到创建和销毁之后都会分别调用ServletContextListener监听器的方法反馈。
	 */
	
	public void contextInitialized(ServletContextEvent sce) {
		// 可以在这里加载资源文件
		// 1.获取servletContext对象
		ServletContext servletContext = sce.getServletContext();
		
		// 2.加载资源文件
		// 去webxml文件中获取初始化参数
		String contentConfigLocation = servletContext.getInitParameter("contentConfigLocation");
		System.out.println("servletContent对象被创建了");
		
		// 3.获取真实路径getRealPath
		String path = servletContext.getRealPath("contentConfigLocation");
		
		// 4.将参数存到想要存放的地方
		if (path != null) {
			servletContext.setAttribute("path", path);
		}
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("servletContent对象被销毁了");
		
	}
	
}
