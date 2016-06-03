
/* Services Ajax requests */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import fabflix.beans.*;

import javax.naming.Context;
import javax.sql.DataSource;

public class Ajax extends HttpServlet {

    private DataSource ds = null;
    // AutoComplete class for use in auto completion JSON
    class AutoComplete {
        private String value;
        private String label;
        private String id;
        
        public AutoComplete() {
            this.value = "";
            this.label = "";
            this.id = "";
        }

        public AutoComplete(String value, String label, String id) {
            this.value = value;
            this.label = label;
            this.id = id;
        }

        public String getValue() {
            return value;
        }
        public String getLabel() {
            return label;
        }
        public String getId() {
            return id;
        }
    }
    

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

    public String getServletInfo() {
       return "Services Ajax requests";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Get the report requested from the path info
        String ajaxRequest = null;
        
        if (request.getPathInfo() != null)
            ajaxRequest = request.getPathInfo().toString();

        if (ajaxRequest != null && !ajaxRequest.equals("") && !ajaxRequest.equals("/"))
            ajaxRequest = ajaxRequest.substring(1);

        // Database information and credentials, consider making external
        String loginUser = "root";
        String loginPasswd = "waydowninthehole";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        // Perform a search for data source for autocomplete widget
        if (Objects.equals(ajaxRequest, "autocomplete")) {
            String term = request.getParameter("term");

            // Modify the terms for use in boolean mode fulltext search
            String[] terms = term.trim().split(" ");
            term = "";
            for (int i = 0; i < terms.length; ++i) {
                if (i == 0 && !terms[i].isEmpty())
                    term = "+" + terms[i];
                else if (!terms[i].isEmpty())
                    term = term + " +" + terms[i];

                if (i == terms.length - 1)
                    term = term + "*";
            }

            String checkTitles = "SELECT id,title,year FROM movies " +
                        "WHERE MATCH(title) " +
                        "AGAINST(? IN BOOLEAN MODE);";

            List<AutoComplete> movieTitles = new ArrayList<AutoComplete>();
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                try (Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                     PreparedStatement statement = connection.prepareStatement(checkTitles))
                {
                    statement.setString(1, term);

                    try (ResultSet results = statement.executeQuery())
                    {
                        // Found a matching title, add it to the list
                        while (results.next()) {
                            String value = results.getString("title");
                            String label = value + " (" + results.getString("year") + ")";
                            String id = Integer.toString(results.getInt("id"));
                            AutoComplete entry = new AutoComplete(value, label, id);
                            movieTitles.add(entry);
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // Send back the JSON response object
            String json = new Gson().toJson(movieTitles);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        // Perform a tooltip display for tooltip widget
        else if (Objects.equals(ajaxRequest, "tooltip")) {
            // Get the movie id from the request
            String movieId = request.getParameter("id");

            if (movieId != null && !movieId.equals("")) {
                // Search for the movie in the database
                MovieInfo movie = MovieDB.getMovieById(movieId, ds);

                // Send back the JSON response object
                String json = new Gson().toJson(movie);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                return;
            }   
        }

        // Send back an empty JSON response object
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{'failed': 'true'}");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}
