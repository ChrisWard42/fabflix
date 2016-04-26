
/* Displays the dashboard page and handles interactions therein */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

public class Dashboard extends HttpServlet {
    public String getServletInfo() {
       return "Displays the dashboard page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Get the report requested from the path info
        String dashboard = null;
        
        if (request.getPathInfo() != null)
            dashboard = request.getPathInfo().toString();

        if (dashboard != null && !dashboard.equals("") && !dashboard.equals("/"))
            dashboard = dashboard.substring(1);

        request.setAttribute("dashboard", dashboard);
        
        request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}