
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
        String movieId = request.getPathInfo().toString().substring(1);

        // TEMPORARY TEST CODE
        // Star star = new Star(420420, "Max", "Ushkalov", new Date(420, 4, 20)
        //     , "https://lh6.googleusercontent.com/-X0MlOK7eabU/AAAAAAAAAAI/AAAAAAAABTs/3_u0TpKa4V0/s0/photo.jpg");
        // HashSet<Star> stars = new HashSet<Star>();
        // stars.add(star);
        // String genre = "Book of Manners";
        // HashSet<String> genres = new HashSet<String>();
        // genres.add(genre);
        // MovieInfo movie = new MovieInfo(420420, "Yolo Blaze It", 420, "Max Ushkalov"
        //     , "https://lh6.googleusercontent.com/-X0MlOK7eabU/AAAAAAAAAAI/AAAAAAAABTs/3_u0TpKa4V0/s0/photo.jpg"
        //     , "https://lh6.googleusercontent.com/-X0MlOK7eabU/AAAAAAAAAAI/AAAAAAAABTs/3_u0TpKa4V0/s0/photo.jpg"
            // , stars, genres);
        // TEMPORARY TEST CODE

        // TODO: Implement search query to get a single movie by its id
        MovieInfo movie = Movie.getMovieById(movieId);
        request.setAttribute("movie", movie);
        
        request.getRequestDispatcher("/WEB-INF/movie.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}