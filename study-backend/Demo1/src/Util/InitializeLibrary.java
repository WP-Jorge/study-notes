package Util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializeLibrary implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String SystemPath=context.getRealPath("/");
		String DVDsFile = SystemPath + context.getInitParameter("data-direstory");
		// System.out.println(DVDsFile);
		// System.out.println(sce.getServletContext().getContextPath());
		if ( DVDsFile != null ) {
			// Store this directory as a System property
			context.setAttribute("dataPath", DVDsFile);
			
			context.log("The dataDirectory attribute has been set.");
		} else {
			context.log("The 'data-directory' context parameter was not set.");
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	
	}
}
