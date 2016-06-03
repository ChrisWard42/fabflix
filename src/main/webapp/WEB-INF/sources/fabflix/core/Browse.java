
/* Displays the browse page and handles interactions therein */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

import javax.naming.Context;
import javax.sql.DataSource;

public class Browse extends HttpServlet {

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
       return "Displays the browse page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // If we don't have previous results or params don't match what's in session, run query for results
        if (request.getSession().getAttribute("browseResults") == null ||
            !Objects.equals(request.getSession().getAttribute("genre"), request.getParameter("genre")) ||
            !Objects.equals(request.getSession().getAttribute("startsWith"), request.getParameter("startsWith")))
        {
            // Set the session attributes for future requests
            request.getSession().setAttribute("query", request.getParameter("query"));
            request.getSession().setAttribute("titleQuery", request.getParameter("title"));
            request.getSession().setAttribute("yearQuery", request.getParameter("year"));
            request.getSession().setAttribute("directorQuery", request.getParameter("director"));
            request.getSession().setAttribute("starQuery", request.getParameter("star"));
            request.getSession().setAttribute("genreQuery", request.getParameter("genre"));
            request.getSession().setAttribute("startsWithQuery", request.getParameter("startsWith"));

            // If genre is in parameter list, use that
            if (request.getParameter("genre") != null && !request.getParameter("genre").equals("")) {
                List<MovieInfo> searchResults = MovieDB.browseMoviesByGenre(request.getParameter("genre"), ds);
                request.getSession().setAttribute("browseResults", searchResults);
            }

            // Otherwise, if startsWith is in parameter list, use that
            else if (request.getParameter("startsWith") != null && !request.getParameter("startsWith").equals(""))
            {
                List<MovieInfo> searchResults = MovieDB.browseMoviesByLetter(request.getParameter("startsWith"), ds);
                request.getSession().setAttribute("browseResults", searchResults);
            }

            // If no query parameters at all are supplied, get all results
            else {
                List<MovieInfo> searchResults = MovieDB.browseMoviesByLetter("", ds);
                request.getSession().setAttribute("browseResults", searchResults);
            }
        }

        request.setAttribute("listSrc", "browse");
        request.getRequestDispatcher("/movie-list").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
       doGet(request, response);
    }
}
