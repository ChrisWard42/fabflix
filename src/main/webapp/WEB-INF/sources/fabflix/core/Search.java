
/* A servlet to search the DB for movies */
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

public class Search extends HttpServlet {
    private DataSource ds = null;

    public String getServletInfo() {
       return "Servlet searches for a movie";
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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // If we don't have previous results or params don't match what's in session, run query for results
        if (request.getSession().getAttribute("searchResults") == null ||
            !Objects.equals(request.getSession().getAttribute("query"), request.getParameter("query")) ||
            !Objects.equals(request.getSession().getAttribute("titleQuery"), request.getParameter("title")) ||
            !Objects.equals(request.getSession().getAttribute("yearQuery"), request.getParameter("year")) ||
            !Objects.equals(request.getSession().getAttribute("directorQuery"), request.getParameter("director")) ||
            !Objects.equals(request.getSession().getAttribute("starQuery"), request.getParameter("star")))
        {
            // Set the session attributes for future requests
            request.getSession().setAttribute("query", request.getParameter("query"));
            request.getSession().setAttribute("titleQuery", request.getParameter("title"));
            request.getSession().setAttribute("yearQuery", request.getParameter("year"));
            request.getSession().setAttribute("directorQuery", request.getParameter("director"));
            request.getSession().setAttribute("starQuery", request.getParameter("star"));
            request.getSession().setAttribute("genreQuery", request.getParameter("genre"));
            request.getSession().setAttribute("startsWithQuery", request.getParameter("startsWith"));

            // If query is in the parameter list, use that and ignore the rest
            if (request.getParameter("query") != null && !request.getParameter("query").equals("")) {
                List<MovieInfo> searchResults = MovieDB.searchMovies(request.getParameter("query"), ds);
                System.out.println("searchmovies called");
                request.getSession().setAttribute("searchResults", searchResults);
            }

            // Otherwise, if the advanced search parameters have at least one non-null, non-empty supplied, do adv search
            else if ((request.getParameter("title") != null && !request.getParameter("title").equals("")) ||
                     (request.getParameter("year") != null && !request.getParameter("year").equals("")) ||
                     (request.getParameter("director") != null && !request.getParameter("director").equals("")) ||
                     (request.getParameter("star") != null && !request.getParameter("star").equals("")))
            {
                List<MovieInfo> searchResults = MovieDB.searchMovies(
                    request.getParameter("title"),
                    request.getParameter("star"),
                    request.getParameter("year"),
                    request.getParameter("director"), ds);
                request.getSession().setAttribute("searchResults", searchResults);
            }

            // If no query parameters at all are supplied, generate an empty list
            else {
                request.getSession().setAttribute("searchResults", new ArrayList<MovieInfo>());
            }
        }

        request.setAttribute("listSrc", "search");
        request.getRequestDispatcher("/movie-list").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
	   doGet(request, response);
    }
}
