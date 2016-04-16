
/* A servlet to search the DB for movies */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

public class Search extends HttpServlet {
    public String getServletInfo() {
       return "Servlet searches for a movie";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

        // if(request.getQueryString() == null){
        //     String urlSession = response.encodeRedirectURL(request.getContextPath());
        //     response.sendRedirect(urlSession);
        //     return;
        // }

        if(request.getParameter("query") == null){
            request.getSession().setAttribute("search-results", new ArrayList<Movie>());
            RequestDispatcher  dispatcher = request.getRequestDispatcher("/get-movie-list");
            dispatcher.forward(request, response);
            return;
        }
        String url = "/get-movie-list";
        List<Movie> searchResults = Movie.searchMovies(request.getParameter("query"));
        request.getSession().setAttribute("search-results", searchResults);
        RequestDispatcher  dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
        return;

        // String loginUser = "root";
        // String loginPasswd = "waydowninthehole";
        // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        // response.setContentType("text/html");    // Response mime type

        // // Output stream to STDOUT
        // PrintWriter out = response.getWriter();

        // out.println("<HTML><HEAD><TITLE>MovieDB</TITLE></HEAD>");
        // out.println("<BODY><H1>MovieDB</H1>");


        // try
        //    {
        //       //Class.forName("org.gjt.mm.mysql.Driver");
        //       Class.forName("com.mysql.jdbc.Driver").newInstance();

        //       Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        //       // Declare our statement
        //       Statement statement = dbcon.createStatement();

	       //      String lastname = request.getParameter("lastname");
        //       out.println(lastname);
        //       String query = "SELECT * from stars where last_name = '" + lastname + "'";

        //       // Perform the query
        //       ResultSet rs = statement.executeQuery(query);

        //       out.println("<TABLE border>");

        //       // Iterate through each row of rs
        //       while (rs.next())
        //       {
        //           String m_ID = rs.getString("id");
        //           String m_FN = rs.getString("first_name");
        //           String m_LN = rs.getString("last_name");
        //           out.println("<tr>" +
        //                       "<td>" + m_ID + "</td>" +
        //                       "<td>" + m_FN + "</td>" +
        //                       "<td>" + m_LN + "</td>" +
        //                       "</tr>");
        //       }

        //       out.println("</TABLE>");

        //       rs.close();
        //       statement.close();
        //       dbcon.close();
        //     }
        // catch (SQLException ex) {
        //       while (ex != null) {
        //             System.out.println ("SQL Exception:  " + ex.getMessage ());
        //             ex = ex.getNextException ();
        //         }  // end while
        //     }  // end catch SQLException

        // catch(java.lang.Exception ex)
        //     {
        //         out.println("<HTML>" +
        //                     "<HEAD><TITLE>" +
        //                     "MovieDB: Error" +
        //                     "</TITLE></HEAD>\n<BODY>" +
        //                     "<P>SQL error in doGet: " +
        //                     ex.getMessage() + "</P></BODY></HTML>");
        //         return;
        //     }
        //  out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
	   doGet(request, response);
    }
}
