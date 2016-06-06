package fabflix.core;
/* All code below borrowed from 
 * http://archive.oreilly.com/pub/a/onjava/2006/04/19/database-connection-pooling-with-tomcat.html?page=2
 */
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class DBCPoolingListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce){

	    try {
			// Obtain our environment naming context
			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");

			// Look up our data source
			DataSource  ds = (DataSource) envCtx.lookup("jdbc/moviedb");
			String supman = "sup man";

			sce.getServletContext().setAttribute("DBCPool", ds);

			// Second data source for writing only to master

			// Obtain our environment naming context
			Context envCtx2 = (Context) new InitialContext().lookup("java:comp/env");

			// Look up our data source
			DataSource ds2 = (DataSource) envCtx2.lookup("jdbc/moviedbwrite");

			sce.getServletContext().setAttribute("DBCPool2", ds2);
		} catch(NamingException e){
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce){
	}
}