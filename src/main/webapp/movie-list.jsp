<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Movie Results</title>
	<link rel="stylesheet" href="resources/css/styles.css" type="text/css/">
</head>

<%@page import="java.sql.*,
 java.util.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*,
 fabflix.beans.Movie
 fabflix.beans.MovieInfo"
 %>

 <%
<<<<<<< HEAD
List<MovieInfo> movies = (List<MovieInfo>) session.getAttribute("search-results");
=======
List<Movie> movies = (List<Movie>) session.getAttribute("search-display");
>>>>>>> ed00cb46e643a7ef6f6fd3d8611f2f25fc789d29
if (movies == null) {
    movies = new ArrayList<MovieInfo>();
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
			</tr>
			<% }
				session.setAttribute("search-display", null);
                //session.setAttribute("search-results", null);
			%>
		</table>

	</body>
