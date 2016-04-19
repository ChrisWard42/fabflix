
/* Displays the single star page and handles interactions therein */
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

public class SingleStar extends HttpServlet {
    public String getServletInfo() {
       return "Displays the single star page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Get the star id from the path info
        String starId = request.getPathInfo().toString().substring(1);

        // // TEMPORARY TEST CODE
        // StarInfo star = new StarInfo(420420, "Max", "Ushkalov", new Date(420, 4, 20)
        //     , "https://lh6.googleusercontent.com/-X0MlOK7eabU/AAAAAAAAAAI/AAAAAAAABTs/3_u0TpKa4V0/s0/photo.jpg", new HashSet<Movie>());
        // TEMPORARY TEST CODE

        // TODO: Implement search query to get a single star by his/her id, along with list of movies
        StarInfo star = Star.getStarById(starId);
        request.setAttribute("star", star);

        request.getRequestDispatcher("/WEB-INF/star.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
       doGet(request, response);
    }
}