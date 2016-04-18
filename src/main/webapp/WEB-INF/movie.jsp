<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/movie-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Movie View -->
  <div class="movie_list">

    <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="50%">
      <tr>
        <td class=title>
          <h1>Leon: The Professional (1994)</h1>
          <h4 id="id">[269307161]</h4>
        </td>
      </tr>
    </table>
    <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="50%">
      <tr>
        <td class="poster_pic">
          <img src="resources/img/poster-5.jpg" alt="" />
        </td>
      </tr>

      <tr align="center">
        <td class="movie_button">
          <table class="table_buttons" border="0" cellpadding="0" cellspacing="0" width="50%">
            <tr>
              <td>
                <button type="submit" class="btn btn-default">Add to Cart</button>
              </td>

              <td class="spacing"></td>

              <td>
                <button type="submit" class="btn btn-default">Watch Trailer</button>
              </td>
            </tr>
          </table>
        </td>
      </tr>

      <tr>
        <td class="description">
          <h4>
            <span>Director</span>: Luc Besson<br>
            <span>Genres</span>: Thriller<br>
            <span>Stars</span>: <a href="#Jean Reno">Jean Reno</a>, <a href="#Gary Oldman">Gary Oldman</a>, <a href="#Natalie Portman">Natalie Portman</a>, <a href="#Danny Aiello">Danny Aiello</a><br>
          </h4>
        </td>
      </tr>
    </table>

  </div>
  <!-- End of Movie View -->

  <!-- Start of Footer -->
  <footer>
    <%@ include file="footer.jsp" %>
  </footer>
  <!-- End of Footer -->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
