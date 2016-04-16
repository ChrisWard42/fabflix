
/* Servlet logs a user in, rejects if invalid credentials */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet
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

          url = "/home.jsp";
        }
        response.sendRedirect(request.getContextPath() + "/home");
        //getServletContext().getRequestDispatcher(url).forward(request, response);
        return;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doPost(request, response);
    }
}