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
 fabflix.beans.Movie"
 %>

 <%
List<Movie> movies = (List<Movie>) session.getAttribute("search-results");
if (movies == null) {
    movies = new ArrayList<Movie>();
}
%>
	<body>
		<h1>Movie Results</h1>
		<table border>
			<% for (Movie movie : movies){%>
			<tr>
                <td><span><%=movie.getTitle()%></span></td>
                <td><%=movie.getYear()%></td>
                <td><%=movie.getDirector()%></td>
				<td><a href="<%=movie.getBannerUrl()%>" >Banner URL</a></td>
			</tr>
			<% }
				session.setAttribute("search-results", null);
			%>
		</table>

	</body>
