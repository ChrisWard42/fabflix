<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Movie List View</title>
    <link rel="stylesheet" href="resources/css/styles.css" type="text/css/">
</head>

<%@page import="java.sql.*,
 java.util.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*,
 fabflix.beans.Movie,
 fabflix.beans.MovieInfo"
 %>

 <%
List<MovieInfo> movies = (List<MovieInfo>) session.getAttribute("search-results");
if (movies == null) {
    movies = new ArrayList<Movie>();
}
%>
    <body>
        <h1>Movie Results</h1>
        <table border>
            <% for (MovieInfo movie : movies){%>
            <tr>
                <td><span><%=movie.getTitle()%></span></td>
                <td><%=movie.getYear()%></td>
                <td><%=movie.getDirector()%></td>
                <td><a href="<%=movie.getBannerUrl()%>" >Banner URL</a></td>
                <td><a href="<%=movie.getTrailerUrl()%>" >Trailer URL</a></td>
                <td>
                <% for (Star star : movie.getStars()) { %>
                <a href="/star/<%star.getId()%>" ><%star.getName()%>, </a>
                <% } %>
                </td>
                <td>
                <% for (String genre : movie.getGenres()) { %>
                <%genre%>, 
                <% } %>
                </td>
                <!-- Shopping cart thing that redirects to ./cart?add=movie.getID() -->
            </tr>
            <% }
                session.setAttribute("search-results", null);
            %>
        </table>

    </body>