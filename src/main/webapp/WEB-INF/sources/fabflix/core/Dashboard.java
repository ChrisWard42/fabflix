
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

        // Get the employee information
        Employee employee = (Employee) request.getSession().getAttribute("employee");

        // Perform actions based on requested module assuming employee properly logged in
        String action = request.getParameter("action");

        // TODO: Move the Login database logic out of this file
        if (Objects.equals(action, "login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email == null || password == null || email.equals("") || password.equals("")) {
                request.getSession().setAttribute("employee", null);
                request.setAttribute("errorMsg", "Please fill out all fields.");
                request.setAttribute("dashboard", "login");
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                return;
            }
            else {
                String loginUser = "root";
                String loginPasswd = "waydowninthehole";
                String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

                String checkEmployee = "SELECT * FROM employees " +
                        "WHERE email = ? AND password = ?;";

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    try (Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                         PreparedStatement statement = connection.prepareStatement(checkEmployee))
                    {
                        statement.setString(1, email);
                        statement.setString(2, password);

                        try (ResultSet results = statement.executeQuery())
                        {
                            // Found a matching employee, create Employee object and put it in session
                            if (results.next()) {
                                employee = new Employee(results.getString("email"), results.getString("password"),
                                                        results.getString("fullname"));
                                request.getSession().setAttribute("employee", employee);
                                response.sendRedirect(request.getContextPath() + "/_dashboard");
                                return;
                            }

                            // No matching employee found, set error parameter and send back to login page
                            else {
                                request.getSession().setAttribute("employee", null);
                                request.setAttribute("errorMsg", "Incorrect login information. Please try again.");
                                request.setAttribute("dashboard", "login");
                                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                                return;
                            }
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }            
        }


        else if (Objects.equals(action, "insertmovie")) {
            System.out.println("Insert Movie logic goes here.");
        }


        else if (Objects.equals(action, "insertstar")) {
            System.out.println("Insert Star logic goes here.");
        }


        else if (Objects.equals(action, "updatemovie")) {
            System.out.println("Update Movie logic goes here.");
        }

        // If page requested is login, and employee is logged in, redirect to index
        else if (Objects.equals(dashboard, "login") && employee != null) {
            response.sendRedirect(request.getContextPath() + "/_dashboard");
            return;
        }

        // If page requested is any other page and employee is not logged in, redirect to login
        else if (!Objects.equals(dashboard, "login") && employee == null) {
            response.sendRedirect(request.getContextPath() + "/_dashboard/login");
            return;
        }

        // TODO: Move the Metadata database logic out of this file
        else if (Objects.equals(dashboard, "metadata")) {
            String loginUser = "root";
            String loginPasswd = "waydowninthehole";
            String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                try (Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);)
                {
                    DatabaseMetaData meta = connection.getMetaData();
                    ArrayList<String> tableNames = new ArrayList<String>();
                    try (ResultSet results = meta.getTables(null, null, null, new String[]{"TABLE"});)
                    {
                        while (results.next()) {
                            String table = results.getString("TABLE_NAME");
                            tableNames.add(table);
                        }

                        request.setAttribute("metadataTblsHead", "Tables in Database");
                        request.setAttribute("metadataTbls", tableNames);
                    }

                    LinkedHashMap<String, LinkedHashMap<String, String>> tables = 
                                                        new LinkedHashMap<String, LinkedHashMap<String, String>>();
                    for (String table : tableNames) {
                        LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
                        try (ResultSet colResults = meta.getColumns(null, null, table, null);) {
                            while (colResults.next()) {
                                String field = colResults.getString("COLUMN_NAME");
                                String type = colResults.getString("TYPE_NAME");

                                columns.put(field, type);
                            }
                        }
                        tables.put(table, columns);
                    }

                    request.setAttribute("metadataAttribute", "Attribute");
                    request.setAttribute("metadataType", "Type");
                    request.setAttribute("metadata", tables);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

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