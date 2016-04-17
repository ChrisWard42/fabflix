
/* Displays the home page and handles interactions therein */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

public class Home extends HttpServlet {
    public String getServletInfo() {
       return "Displays the home page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
       doGet(request, response);
    }
}
