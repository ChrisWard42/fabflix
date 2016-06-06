
/* Servlet logs a user in, rejects if invalid credentials or failed ReCAPTCHA */
package fabflix.core;

import java.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;

public class Login extends HttpServlet
{
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SITE_KEY ="6Lc4VB4TAAAAAAa2OMV10xH92aFAvOukCyNhKGIs";
    public static final String SECRET_KEY ="6Lc4VB4TAAAAAPceIq-EY4HWHyppISqAz5LkAzWN";
    private DataSource ds = null;

    @Override
    public void init() throws ServletException {
        
        try {
             //Create a datasource for pooled connections.
             ds = (DataSource) getServletContext().getAttribute("DBCPool");

             // //Register the driver for non-pooled connections.
             // Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception e) {
          throw new ServletException(e.getMessage());
        }

    }

    public String getServletInfo()
    {
       return "Servlet logs a user in, rejects if invalid credentials or failed ReCAPTCHA";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String action = request.getParameter("action");

        if (Objects.equals(action, "auth")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            /*String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean success = false;

            if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
                request.getSession().setAttribute("user", null);
                request.setAttribute("errorMsg", "Failed ReCAPTCHA. Please try again.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
     
            try {
                URL verifyUrl = new URL(SITE_VERIFY_URL);
     
                // Open Connection to URL
                HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
     
                // Add Request Header
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
     
                // Data will be sent to the server.
                String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
     
                // Send Request
                conn.setDoOutput(true);
                
                // Get the output stream of Connection and send to server
                OutputStream outStream = conn.getOutputStream();
                outStream.write(postParams.getBytes());
                outStream.flush();
                outStream.close();
     
                // Response code return from server.
                int responseCode = conn.getResponseCode(); 
      
                // Get the InputStream from Connection to read data sent from the server.
                InputStream is = conn.getInputStream();
                JsonReader jsonReader = Json.createReader(is);
                JsonObject jsonObject = jsonReader.readObject();
                jsonReader.close();
     
                // Check if ReCAPTCHA validation succeeded
                success = jsonObject.getBoolean("success");
            } catch (Exception e) {
                e.printStackTrace();
                success = false;
            }

            if (!success) {
                request.getSession().setAttribute("user", null);
                request.setAttribute("errorMsg", "Failed ReCAPTCHA. Please try again.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }*/

            if (email == null || password == null || email.equals("") || password.equals("")) {
                request.getSession().setAttribute("user", null);
                request.setAttribute("errorMsg", "Please fill out all fields.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            else {
                // String loginUser = "root";
                // String loginPasswd = "waydowninthehole";
                // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

                Customer user = null;
                String checkUser = "SELECT * FROM customers " +
                        "WHERE email = ? AND password = ?;";

                try {
                    // Class.forName("com.mysql.jdbc.Driver").newInstance();
                    try (// Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                         Connection connection = MovieDB.getConnection(ds);)
                    {
                        connection.setReadOnly(true);

                        try (PreparedStatement statement = connection.prepareStatement(checkUser);)
                        {
                            statement.setString(1, email);
                            statement.setString(2, password);

                            try (ResultSet results = statement.executeQuery();)
                            {
                                // Found a matching user in the database, so create user object from Customer and put it in session
                                if (results.next()) {
                                    user = new Customer(results.getInt("id"), results.getString("first_name"), results.getString("last_name"),
                                                results.getString("cc_id"), results.getString("address"), results.getString("email"), null);
                                    request.getSession().setAttribute("user", user);
                                    response.sendRedirect(request.getContextPath() + "/");
                                    statement.close();
                                    connection.close();
                                    return;
                                }

                                // No matching user found, set error parameter and send back to login page
                                else {
                                    request.getSession().setAttribute("user", null);
                                    request.setAttribute("errorMsg", "Incorrect login information. Please try again.");
                                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                                    statement.close();
                                    connection.close();
                                    return;
                                }
                            }
                        }
                    }
                    // }
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
