
/* A servlet to display the contents of the MySQL movieDB database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProcessLogin extends HttpServlet
{
    public String getServletInfo()
    {
       return "Servlet logs a user in, rejects if invalid credentials";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String url = "/";

        String action = request.getParameter("action");
        if(action == null)
          action = "join";

        if(action.equals("join"))
          url = "/";

        else if (action.equals("add")){
          String email = request.getParameter("email");
          String password = request.getParameter("pword");

          url = "/main_page.jsp";
        }
        response.sendRedirect(request.getContextPath() + "/main");
        //getServletContext().getRequestDispatcher(url).forward(request, response);
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doPost(request, response);
    }
}