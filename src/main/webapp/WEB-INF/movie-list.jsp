<!doctype html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<meta charset="utf-8">
	<title>Fabflix</title>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<link rel="stylesheet" href="resources/css/styles.css" type="text/css/">
</head>

<%@page import="java.sql.*,
 java.util.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*,
 fabflix.beans.Movie,
 fabflix.beans.MovieInfo,
 fabflix.beans.Star"
 %>

 <%
List<MovieInfo> movies = (List<MovieInfo>) session.getAttribute("search-display");
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
                <td>
                <% for (Star star : movie.getStarSet()){ %>
                	<%=star.getFirstName()%> <%=star.getLastName()%><br/>
                <% } %>
                </td>
                <td>
                <% for (String genre : movie.getGenreSet()){ %>
                	<%=genre%><br/>
                <% } %>
                </td>
			</tr>
			<% }
				session.setAttribute("search-display", null);
                //session.setAttribute("search-results", null);
			%>
		</table>

	</body>
