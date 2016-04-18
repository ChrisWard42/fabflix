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

  <!-- Start of Star View -->
  <div class="movie_list">

    <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="50%">
      <tr>
        <td class=title>
          <h1>Vince Vaughn</h1>
          <h4 id="id">[872004]</h4>
        </td>
      </tr>
    </table>
    <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="50%">
      <tr>
        <td class="poster_pic">
          <img src="http://ia.imdb.com/media/imdb/01/I/18/65/38f.jpg" alt="" onerror="this.src='resources/img/dora-error-img.png'" />
        </td>
      </tr>

      <tr>
        <td class="description">
          <h4>
            <span>Date of Birth</span>: 1970-03-28<br>
            <span>Movies</span>: <a href="#Wedding_Crashers">Wedding Crashers</a><br>
          </h4>
        </td>
      </tr>
    </table>

  </div>
  <!-- End of Star View -->

  <!-- Start of Footer -->
  <footer>
    <%@ include file="footer.jsp" %>
  </footer>
  <!-- End of Footer -->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>