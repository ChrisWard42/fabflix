
/* Servlet logs a user in, rejects if invalid credentials */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

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
        String action = request.getParameter("action");

        if (Objects.equals(action, "auth")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email == null || password == null || email.equals("") || password.equals("")) {
                request.getSession().setAttribute("user", null);
                request.getSession().setAttribute("errorMsg", "Please fill out all fields.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            else {
                String loginUser = "root";
                String loginPasswd = "waydowninthehole";
                String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

                Customer user = null;
                String checkUser = "SELECT * FROM customers " +
                        "WHERE email = ? AND password = ?;";

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    try (Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                         PreparedStatement statement = connection.prepareStatement(checkUser))
                    {
                        statement.setString(1, email);
                        statement.setString(2, password);

                        try (ResultSet results = statement.executeQuery())
                        {
                            // Found a matching user in the database, so create user object from Customer and put it in session
                            if (results.next()) {
                                user = new Customer(results.getInt("id"), results.getString("first_name"), results.getString("last_name"),
                                            results.getString("cc_id"), results.getString("address"), results.getString("email"), null);
                                request.getSession().setAttribute("user", user);
                                request.getSession().setAttribute("errorMsg", "");
                                response.sendRedirect(request.getContextPath() + "/");
                                return;
                            }

                            // No matching user found, set error parameter and send back to login page
                            else {
                                request.getSession().setAttribute("user", null);
                                request.getSession().setAttribute("errorMsg", "Incorrect login information. Please try again.");
                                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doPost(request, response);
    }
}