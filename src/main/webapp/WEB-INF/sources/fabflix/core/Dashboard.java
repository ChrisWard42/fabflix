
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
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.*;
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

        // Database information and credentials, consider making external
        // String loginUser = "root";
        // String loginPasswd = "waydowninthehole";
        // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

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
                String checkEmployee = "SELECT * FROM employees " +
                        "WHERE email = ? AND password = ?;";

                try {
                    // Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Context initCtx = new InitialContext();
                    Context envCtx = (Context) initCtx.lookup("java:comp/env");

                    DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
                    try (// Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                         Connection connection = ds.getConnection();
                         PreparedStatement statement = connection.prepareStatement(checkEmployee);)
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


        // TODO: Move the Insert Movie database logic out of this file
        else if (Objects.equals(action, "insertmovie")) {
            // Get parameters from form
            String movieTitle = request.getParameter("movieTitle");
            String movieYear = request.getParameter("movieYear");
            String movieDirector = request.getParameter("movieDirector");
            String movieBannerUrl = request.getParameter("movieBannerUrl");
            String movieTrailerUrl = request.getParameter("movieTrailerUrl");

            String genreName = request.getParameter("genreName");

            String starFirstName = request.getParameter("starFirstName");
            String starLastName = request.getParameter("starLastName");
            String starDob = request.getParameter("starDob");
            String starPhotoUrl = request.getParameter("starPhotoUrl");

            // If title/year/director not entered, print error message
            if (movieTitle.isEmpty() || movieYear.isEmpty() || movieDirector.isEmpty()) {
                request.setAttribute("errorMsg", "Invalid movie information."
                    + " You must enter a title, year, and director to insert a movie.");
                request.setAttribute("dashboard", "insertmovie");
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                return;
            }
            // If star dob or photo url entered, but no first/last name, print an error msg
            else if (starFirstName.isEmpty() && starLastName.isEmpty() &&
                    (!starDob.isEmpty() || !starPhotoUrl.isEmpty())) {
                request.setAttribute("errorMsg", "Invalid star information. Please either provide a first name"
                    + " or last name for the star, or leave the date of birth and photo url fields blank.");
                request.setAttribute("dashboard", "insertmovie");
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                return;
            }

            // If they only specified a first name, store it as the last name for DB consistency
            if (starLastName.isEmpty()) {
                starLastName = starFirstName;
                starFirstName = "";
            }

            try {
                // Class.forName("com.mysql.jdbc.Driver").newInstance();
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");

                DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
                try (// Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                     Connection connection = ds.getConnection();)
                {                    
                    String procedure = "{call add_movie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                    try (CallableStatement cs = connection.prepareCall(procedure)) {
                        // Store Movie Info in the corresponding procedure parameters
                        cs.setString(1, movieTitle);
                        cs.setInt(2, Integer.parseInt(movieYear));
                        cs.setString(3, movieDirector);

                        if (!movieBannerUrl.isEmpty())
                            cs.setString(4, movieBannerUrl);
                        else
                            cs.setNull(4, java.sql.Types.VARCHAR);

                        if (!movieTrailerUrl.isEmpty())
                            cs.setString(5, movieTrailerUrl);
                        else
                            cs.setNull(5, java.sql.Types.VARCHAR);

                        // Store Star Info in the corresponding procedure parameters
                        cs.setString(6, starFirstName);
                        cs.setString(7, starLastName);

                        if (!starDob.isEmpty())
                            cs.setDate(8, java.sql.Date.valueOf(starDob));
                        else
                            cs.setNull(8, java.sql.Types.DATE);

                        if (!starPhotoUrl.isEmpty())
                            cs.setString(9, starPhotoUrl);
                        else
                            cs.setNull(9, java.sql.Types.VARCHAR);

                        // Store Genre Info in the corresponding procedure parameters
                        cs.setString(10, genreName);

                        // Indicate to procedure the request comes from Insert Movie page
                        cs.setInt(11, 0);

                        // Register the return variables to check error status and message
                        cs.registerOutParameter(12, java.sql.Types.INTEGER);
                        cs.registerOutParameter(13, java.sql.Types.VARCHAR);

                        // Execute the stored procedure with provided parameters
                        cs.execute();

                        // Get the return variables containing error status and message
                        int status = (Integer) cs.getInt(12);
                        String output = (String) cs.getString(13);

                        // If there was an error on insertion, report it and return to index
                        if (status == 1 || status == 2) {
                            request.getSession().setAttribute("errorMsg", output);
                            response.sendRedirect(request.getContextPath() + "/_dashboard");
                            return;
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // If everything succeeded, send back to the dashboard page with a success message
            request.getSession().setAttribute("successMsg", "Successfully inserted Movie into database: "
                                 + movieTitle + ".");
            response.sendRedirect(request.getContextPath() + "/_dashboard");
            return;
        }


        // TODO: Move the Insert Star database logic out of this file
        else if (Objects.equals(action, "insertstar")) {
            // Get parameters from form
            String firstName = request.getParameter("starFirstName");
            String lastName = request.getParameter("starLastName");
            String dob = request.getParameter("starDob");
            String photoURL = request.getParameter("starPhotoUrl");

            // If no first or last name specified, return to page and print an error message
            if (firstName.isEmpty() && lastName.isEmpty()) {
                request.setAttribute("errorMsg", "Please provide at least a first name or last name.");
                request.setAttribute("dashboard", "insertstar");
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                return;
            }

            // If they only specified a first name, store it as the last name for DB consistency
            if (lastName.isEmpty()) {
                lastName = firstName;
                firstName = "";
            }

            try {
                // Class.forName("com.mysql.jdbc.Driver").newInstance();
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");

                DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
                try (// Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                     Connection connection = ds.getConnection();)
                {                    
                    String insert = "INSERT INTO stars(first_name, last_name, dob, photo_url) VALUES(?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insert);){
                           
                        preparedStatement.setString(1, firstName);
                        preparedStatement.setString(2, lastName);

                        // Perform validation on dob and photoURL fields
                        if (dob == null || dob.isEmpty()) {
                            preparedStatement.setNull(3, java.sql.Types.DATE);
                        }
                        else {
                            preparedStatement.setDate(3, java.sql.Date.valueOf(dob));
                        }
                        if (photoURL == null || photoURL.isEmpty()) {
                            preparedStatement.setNull(4, java.sql.Types.VARCHAR);
                        }
                        else {
                            preparedStatement.setString(4, photoURL);
                        }
                        
                        preparedStatement.executeUpdate();
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // If everything succeeded, send back to the dashboard page with a success message
            request.getSession().setAttribute("successMsg", "Successfully inserted Star into database: "
                                 + firstName + " " + lastName + ".");
            response.sendRedirect(request.getContextPath() + "/_dashboard");
            return;
        }


        // TODO: Move the Update Movie Check database logic out of this file
        else if (Objects.equals(action, "updatemoviecheck")) {
            // Get parameters from form
            String movieTitle = request.getParameter("movieTitle");
            String movieYear = request.getParameter("movieYear");
            String movieDirector = request.getParameter("movieDirector");

            // If title/year/director not entered, print error message
            if (movieTitle.isEmpty() || movieYear.isEmpty() || movieDirector.isEmpty()) {
                request.setAttribute("errorMsg", "Invalid movie information."
                    + " You must enter a title, year, and director to update a movie.");
                request.setAttribute("dashboard", "updatemoviecheck");
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                return;
            }

            String checkMovie = "SELECT * FROM movies " +
                        "WHERE title = ? AND year = ? AND director = ?;";

            try {
                // Class.forName("com.mysql.jdbc.Driver").newInstance();
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");

                DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
                try (// Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                     Connection connection = ds.getConnection();
                     PreparedStatement statement = connection.prepareStatement(checkMovie);)
                {                    
                    statement.setString(1, movieTitle);
                    statement.setString(2, movieYear);
                    statement.setString(3, movieDirector);

                    try (ResultSet results = statement.executeQuery())
                    {
                        // Found a matching movie, store it in request attributes and pass to update movie
                        if (results.next()) {
                            request.setAttribute("movieTitle", results.getString("title"));
                            request.setAttribute("movieYear", results.getInt("year"));
                            request.setAttribute("movieDirector", results.getString("director"));
                            request.setAttribute("movieBannerUrl", results.getString("banner_url"));
                            request.setAttribute("movieTrailerUrl", results.getString("trailer_url"));
                            request.setAttribute("dashboard", "updatemovie");
                            request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                            return;
                        }

                        // No matching movie found, set error parameter and send back to updatemoviecheck page
                        else {
                            request.setAttribute("errorMsg", "Movie not found. Please provide information for a movie"
                                + " which exists in the database, or insert the movie from the dashboard.");
                            request.setAttribute("dashboard", "updatemoviecheck");
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


        // TODO: Move the Update Movie database logic out of this file
        else if (Objects.equals(action, "updatemovie")) {
            // Get parameters from form
            String movieTitle = request.getParameter("movieTitle");
            String movieYear = request.getParameter("movieYear");
            String movieDirector = request.getParameter("movieDirector");
            String movieBannerUrl = request.getParameter("movieBannerUrl");
            String movieTrailerUrl = request.getParameter("movieTrailerUrl");

            String genreName = request.getParameter("genreName");

            String starFirstName = request.getParameter("starFirstName");
            String starLastName = request.getParameter("starLastName");
            String starDob = request.getParameter("starDob");
            String starPhotoUrl = request.getParameter("starPhotoUrl");

            // If title/year/director not entered, something went wrong, should be hidden attributes
            if (movieTitle.isEmpty() || movieYear.isEmpty() || movieDirector.isEmpty()) {
                request.setAttribute("errorMsg", "Something went wrong. Tried to update a movie without"
                    + " first specifying which movie to update. Please utilize the dashboard.");
                response.sendRedirect(request.getContextPath() + "/_dashboard");
                return;
            }

            // If nothing relevant entered, print an error message
            if (genreName.isEmpty() && starLastName.isEmpty()) {
                request.setAttribute("errorMsg", "Please enter a star or genre to update the movie entry with,"
                    + " or return to the dashboard for other options.");
                request.setAttribute("dashboard", "updatemovie");
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                return;
            }

            // If star dob or photo url entered, but no first/last name, print an error msg
            else if (starFirstName.isEmpty() && starLastName.isEmpty() &&
                    (!starDob.isEmpty() || !starPhotoUrl.isEmpty())) {
                request.setAttribute("errorMsg", "Invalid star information. Please either provide a first name"
                    + " or last name for the star, or leave the date of birth and photo url fields blank.");
                request.setAttribute("dashboard", "updatemovie");
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                return;
            }

            // If they only specified a first name, store it as the last name for DB consistency
            if (starLastName.isEmpty()) {
                starLastName = starFirstName;
                starFirstName = "";
            }

            try {
                // Class.forName("com.mysql.jdbc.Driver").newInstance();
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");

                DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
                try (// Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                     Connection connection = ds.getConnection();)
                {                    
                    String procedure = "{call add_movie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                    try (CallableStatement cs = connection.prepareCall(procedure)) {
                        // Store Movie Info in the corresponding procedure parameters
                        cs.setString(1, movieTitle);
                        cs.setInt(2, Integer.parseInt(movieYear));
                        cs.setString(3, movieDirector);

                        if (!movieBannerUrl.isEmpty())
                            cs.setString(4, movieBannerUrl);
                        else
                            cs.setNull(4, java.sql.Types.VARCHAR);

                        if (!movieTrailerUrl.isEmpty())
                            cs.setString(5, movieTrailerUrl);
                        else
                            cs.setNull(5, java.sql.Types.VARCHAR);

                        // Store Star Info in the corresponding procedure parameters
                        cs.setString(6, starFirstName);
                        cs.setString(7, starLastName);

                        if (!starDob.isEmpty())
                            cs.setDate(8, java.sql.Date.valueOf(starDob));
                        else
                            cs.setNull(8, java.sql.Types.DATE);

                        if (!starPhotoUrl.isEmpty())
                            cs.setString(9, starPhotoUrl);
                        else
                            cs.setNull(9, java.sql.Types.VARCHAR);

                        // Store Genre Info in the corresponding procedure parameters
                        cs.setString(10, genreName);

                        // Indicate to procedure the request comes from Update Movie page
                        cs.setInt(11, 1);

                        // Register the return variables to check error status and message
                        cs.registerOutParameter(12, java.sql.Types.INTEGER);
                        cs.registerOutParameter(13, java.sql.Types.VARCHAR);

                        // Execute the stored procedure with provided parameters
                        cs.execute();

                        // Get the return variables containing error status and message
                        int status = (Integer) cs.getInt(12);
                        String output = (String) cs.getString(13);

                        // If there was an error on insertion, report it and return to index
                        if (status == 1 || status == 2) {
                            request.getSession().setAttribute("errorMsg", output);
                            response.sendRedirect(request.getContextPath() + "/_dashboard");
                            return;
                        }
                    }
                }
            }
            catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String errorOutput = sw.toString();
                request.getSession().setAttribute("errorMsg", "Encountered SQL error.<br><br>"
                    + movieTitle + "<br>"
                    + movieYear + "<br>"
                    + movieDirector + "<br>"
                    + movieBannerUrl + "<br>"
                    + movieTrailerUrl + "<br>"
                    + genreName + "<br>"
                    + starFirstName + "<br>"
                    + starLastName + "<br>"
                    + starDob + "<br>"
                    + starPhotoUrl + "<br>"
                    + errorOutput);
                response.sendRedirect(request.getContextPath() + "/_dashboard");
                return;
            }

            // If everything succeeded, send back to the dashboard page with a success message
            request.getSession().setAttribute("successMsg", "Successfully updated Movie in the database: "
                                 + movieTitle + ".");
            response.sendRedirect(request.getContextPath() + "/_dashboard");
            return;
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
            try {
                // Class.forName("com.mysql.jdbc.Driver").newInstance();
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");

                DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
                try (// Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                     Connection connection = ds.getConnection();)
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