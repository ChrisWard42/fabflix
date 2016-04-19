/* Processes the results of a search by transforming the resulting list of movies and returning a subset */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

public class MovieList extends HttpServlet {

    // Compares titles of MovieInfo class for sorting in ascending order
    private class TitleAscComparator implements Comparator<MovieInfo> {
        
        @Override
        public int compare(MovieInfo m1, MovieInfo m2) {
            return (m1.getTitle().toLowerCase()).compareTo(m2.getTitle().toLowerCase());
        }
    }

    // Compares titles of MovieInfo class for sorting in descending order
    private class TitleDescComparator implements Comparator<MovieInfo> {
        
        @Override
        public int compare(MovieInfo m1, MovieInfo m2) {
            return -(m1.getTitle().toLowerCase()).compareTo(m2.getTitle().toLowerCase());
        }
    }

    // Compares years of MovieInfo class for sorting in ascending order
    private class YearAscComparator implements Comparator<MovieInfo> {
        
        @Override
        public int compare(MovieInfo m1, MovieInfo m2) {
            return m1.getYear() - m2.getYear();
        }
    }

    // Compares years of MovieInfo class for sorting in ascending order
    private class YearDescComparator implements Comparator<MovieInfo> {
        
        @Override
        public int compare(MovieInfo m1, MovieInfo m2) {
            return m2.getYear() - m1.getYear();
        }
    }

    public String getServletInfo() {
       return "Processes the results of a search by transforming the resulting list of movies and returning a subset";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // TODO: Check for search-results being null, can't sort a null
        String listSrc = request.getAttribute("listSrc").toString();

        // Get the movie list from the session object
        List<MovieInfo> movies = new ArrayList<MovieInfo>();
        List<MovieInfo> movieDisplay = new ArrayList<MovieInfo>();
        
        if (listSrc.equals("search")) {
            movies = (ArrayList<MovieInfo>) request.getSession().getAttribute("searchResults");
        }
        else if (listSrc.equals("browse")) {
            movies = (ArrayList<MovieInfo>) request.getSession().getAttribute("browseResults");
        }

        // Sort the movie results if a sort mode is specified
        String sort = request.getParameter("sort");
        if (Objects.equals(sort, "title-desc") && !movies.isEmpty()) {
            Collections.sort(movies, new TitleDescComparator());
        }
        else if (Objects.equals(sort, "title-asc") && !movies.isEmpty()) {
            Collections.sort(movies, new TitleAscComparator());
        }
        else if (Objects.equals(sort, "year-desc") && !movies.isEmpty()) {
            Collections.sort(movies, new YearDescComparator());
        }
        else if (Objects.equals(sort, "year-asc") && !movies.isEmpty()) {
            Collections.sort(movies, new YearAscComparator());
        }

        if (listSrc.equals("search")) {
            request.getSession().setAttribute("searchResults", movies);
            request.getSession().setAttribute("browseResults", null);
        }
        else if (listSrc.equals("browse")) {
            request.getSession().setAttribute("searchResults", null);
            request.getSession().setAttribute("browseResults", movies);
        }

        // Get the page number and limit per page, default to 1 and 10 if none passed
        String pageQuery = request.getParameter("page");
        String limitQuery = request.getParameter("limit");
        int page = 0;
        int limit = 0;

        if (pageQuery == null || pageQuery.equals(""))
            page = 1;
        else
            page = Integer.parseInt(pageQuery);

        if (limitQuery == null || limitQuery.equals(""))
            limit = 10;
        else
            limit = Integer.parseInt(limitQuery);
        
        // If the page is out of bounds in either direction then nothing to display
        if (page < 1 || (page-1)*limit > movies.size()-1)
            request.getSession().setAttribute("error", "No results found for query parameters. Please try another search.");
        
        // Otherwise, slice the movie list down to the correct page and size
        else {
            int begin = (page-1)*limit;
            int end = Math.min(page*limit, movies.size());

            movieDisplay = movies.subList(begin, end);
        }

        // Set the session variables
        request.getSession().setAttribute("movieDisplay", movieDisplay);
        
        // Get request dispatcher and return
        if (listSrc.equals("search"))
            request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/WEB-INF/browse.jsp").forward(request, response);
    }
}