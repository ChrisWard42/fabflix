
/* Displays the reports page and handles interactions therein */
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

public class Reports extends HttpServlet {
    public String getServletInfo() {
       return "Displays the reports page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Get the report requested from the path info
        String report = null;
        
        if (request.getPathInfo() != null)
            report = request.getPathInfo().toString();

        if (report != null && !report.equals("") && !report.equals("/"))
            report = report.substring(1);

        request.setAttribute("report", report);
        
        request.getRequestDispatcher("/WEB-INF/reports.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}