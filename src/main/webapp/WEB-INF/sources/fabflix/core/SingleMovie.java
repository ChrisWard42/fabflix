
/* Displays the single movie page and handles interactions therein */
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

import javax.naming.Context;
import javax.sql.DataSource;

public class SingleMovie extends HttpServlet {
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

    public String getServletInfo() {
       return "Displays the single movie page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Get the movie id from the path info
        String movieId = null;

        if (request.getPathInfo() != null)
            movieId = request.getPathInfo().toString();

        if (movieId != null && !movieId.equals("") && !movieId.equals("/")) {
            movieId = movieId.substring(1);
            MovieInfo movie = MovieDB.getMovieById(movieId, ds);
            request.setAttribute("movie", movie);
        }   
        else {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/movie.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}