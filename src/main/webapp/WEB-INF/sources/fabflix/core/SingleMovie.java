
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

public class SingleMovie extends HttpServlet {
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
            MovieInfo movie = Movie.getMovieById(movieId);
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