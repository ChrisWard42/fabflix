/* Servlet performs backend actions for the Fabflix Android application */
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
import com.google.gson.Gson;

import javax.naming.Context;
import javax.sql.DataSource;

public class Mobile extends HttpServlet
{
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
       return "Servlet performs backend actions for the Fabflix Android application";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // NOTES
        // Requests should be of form: {servlet url}/m/{mobile_action}?param1=val1&param2=val2
        //      May need to hardcode {servlet url} as http://www.fabflix.me/fabflix
        //      May need to make server side changes to reenable HTTP access or configure 
        //          Android to utilize HTTPS URLs, some related threads on Piazza
        // Pagination etc should be handled Android application side via manipulation of existing
        //   JSON object returned from last search request.

        // Get the action requested from the path info
        String mobile_action = null;
        
        if (request.getPathInfo() != null)
            mobile_action = request.getPathInfo().toString();

        if (mobile_action != null && !mobile_action.equals("") && !mobile_action.equals("/"))
            mobile_action = mobile_action.substring(1);

        // Process mobile login (EX: ../m/login?email=a@email.com&password=a2)
        if (Objects.equals(mobile_action, "login")) {
            // Get email/password from request
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // If email/password not provided, send an error back
            if (email == null || password == null || email.equals("") || password.equals("")) {
                // TODO: Send back error JSON which indicates to display error and stay on login page

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
                                // TODO: Login was a success, send back JSON indicating to navigate past login page
                                user = new Customer(results.getInt("id"), results.getString("first_name"), results.getString("last_name"),
                                            results.getString("cc_id"), results.getString("address"), results.getString("email"), null);
                                request.getSession().setAttribute("user", user);
                                
                                String json = new Gson().toJson("Success");
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                response.getWriter().write(json); 

                                return;
                            }

                            // No matching user found, set error parameter and send back to login page
                            else {
                                request.getSession().setAttribute("user", null);
                                request.setAttribute("errorMsg", "Incorrect login information. Please try again.");

                                String json = new Gson().toJson("Failure");
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                response.getWriter().write(json);

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

        // Process mobile search (EX: ../m/search?query=good%20bad%20ug)
        else if (Objects.equals(mobile_action, "search")) {

            String query = request.getParameter("query");
            String json = "";
            // Get the result of the query string
            if (request.getParameter("query") != null && !request.getParameter("query").equals("")) {
                // NOTE: Movie.searchMovies(query) needs to be replaced with a function which performs full-text
                //       search on 'title' only, as opposed to LIKE predicate search on all movie fields.
               List<Movie> searchResults = 
                    new ArrayList<>(MovieDB.searchMoviesByTitle(request.getParameter("query"), ds));

                json = new Gson().toJson(searchResults);
            }

            // If no query parameters at all are supplied, generate an empty list
            else {
                List<Movie> emptyList = new ArrayList<>();
                json = new Gson().toJson(emptyList);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        // Process single movie page (EX: ../m/movie/2839402)
        else if (Objects.equals(mobile_action, "movie")) {
            // Get the movie id from the path info
            String movieId = null;

            if (request.getPathInfo() != null)
                movieId = request.getPathInfo().toString();

            if (movieId != null && !movieId.equals("") && !movieId.equals("/")) {
                movieId = movieId.substring(1);
                MovieInfo movie = MovieDB.getMovieById(movieId, ds);

                // TODO: Convert the MovieInfo object returned into a JSON to be used for the display
                //       of the single movie. See movie.jsp for transformations to the MovieInfo data
                //       which result in valid Star URLs and use something similar in the view.
            }   
            else {
                // TODO: Something went wrong, invalid movie id, send back JSON telling Android app to
                //       redirect back to the search or home page.
            }
            return;
        }

        // Process single star page (EX: ../m/star/493020)
        else if (Objects.equals(mobile_action, "star")) {
            // Get the star id from the path info
            String starId = null;

            if (request.getPathInfo() != null)
                starId = request.getPathInfo().toString();

            if (starId != null && !starId.equals("") && !starId.equals("/")) {
                starId = starId.substring(1);
                StarInfo star = Star.getStarById(starId);

                // TODO: Convert the StarInfo object returned into a JSON to be used for the display
                //       of the single star. See star.jsp for transformations to the StarInfo data
                //       which result in valid Movie URLs and use something similar in the view.
            }
            else {
                // TODO: Something went wrong, invalid star id, send back JSON telling Android app to
                //       redirect back to the search or home page.
            }

            return;
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doPost(request, response);
    }
}
