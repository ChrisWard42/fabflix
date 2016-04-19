<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/browse-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Browse by Genre -->
  <div class="browse_genre">
    <button id="expand_genre" class="btn btn-info" data-toggle="collapse" data-target="#genre"><img src="resources/img/expand-white.png" alt="" height="10px" hspace="10px">Browse by Genre</button>
    <div id="genre" class="collapse">
      <table align="center" border="0" border="0" cellpadding="0" cellspacing="0" width="60%">
        <!-- Start of Row 1 -->
        <tr>
          <td>
            <a href="./browse?genre=Action">Action</a>
          </td>
          <td>
            <a href="./browse?genre=Crime">Crime</a>
          </td>
          <td>
            <a href="./browse?genre=Foreign">Foreign</a>
          </td>
          <td>
            <a href="./browse?genre=Romance">Romance</a>
          </td>
        </tr>
        <!-- End of Row 1 -->

        <!-- Start of Row 2 -->
        <tr>
          <td>
            <a href="./browse?genre=Adventure">Adventure</a>
          </td>
          <td>
            <a href="./browse?genre=Drama">Drama</a>
          </td>
          <td>
            <a href="./browse?genre=Horror">Horror</a>
          </td>
          <td>
            <a href="./browse?genre=Science+Fiction">Science Fiction</a>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <a href="./browse?genre=Animation">Animation</a>
          </td>
          <td>
            <a href="./browse?genre=Family">Family</a>
          </td>
          <td>
            <a href="./browse?genre=Musical">Musical</a>
          </td>
          <td>
            <a href="./browse?genre=Thriller">Thriller</a>
          </td>
        </tr>
        <!-- End of Row 3 -->

        <!-- Start of Row 4 -->
        <tr>
          <td>
            <a href="./browse?genre=Comedy">Comedy</a>
          </td>
          <td>
            <a href="./browse?genre=Fantasy">Fantasy</a>
          </td>
          <td>
            <a href="./browse?genre=Mystery">Mystery</a>
          </td>
          <td>
            <a href="./browse?genre=War">War</a>
          </td>
        </tr>
        <!-- End of Row 4 -->

      </table>
    </div>
  </div>
  <!-- End of Browse by Genre -->

  <!-- Start of Browse by Title -->
  <div class="browse_title">
    <button id="expand_title" class="btn btn-info" data-toggle="collapse" data-target="#title"><img src="resources/img/expand-white.png" alt="" height="10px" hspace="10px">Browse by Title</button>
    <div id="title" class="collapse">
      <table align="center" border="0" border="0" cellpadding="0" cellspacing="0" width="45%" style="margin-top: 10px; border-spacing: 0px !important">
        <tr>
          <td>
             <a href="./browse?startsWith=0">0</a>
          </td>
          <td>
             <a href="./browse?startsWith=1">1</a>
          </td>
          <td>
             <a href="./browse?startsWith=2">2</a>
          </td>
          <td>
             <a href="./browse?startsWith=3">3</a>
          </td>
          <td>
             <a href="./browse?startsWith=4">4</a>
          </td>
          <td>
             <a href="./browse?startsWith=5">5</a>
          </td>
          <td>
             <a href="./browse?startsWith=6">6</a>
          </td>
          <td>
             <a href="./browse?startsWith=7">7</a>
          </td>
          <td>
             <a href="./browse?startsWith=8">8</a>
          </td>
          <td>
             <a href="./browse?startsWith=9">9</a>
          </td>
        </tr>
      </table>
      <table align="center" border="0" border="0" cellpadding="0" cellspacing="0" width="60%">

        <!-- Start of Row 2 -->
        <tr>
          <td>
            <a href="./browse?startsWith=A">A</a>
          </td>
          <td>
            <a href="./browse?startsWith=B">B</a>
          </td>
          <td>
            <a href="./browse?startsWith=C">C</a>
          </td>
          <td>
            <a href="./browse?startsWith=D">D</a>
          </td>
          <td>
            <a href="./browse?startsWith=E">E</a>
          </td>
          <td>
            <a href="./browse?startsWith=F">F</a>
          </td>
          <td>
            <a href="./browse?startsWith=G">G</a>
          </td>
          <td>
            <a href="./browse?startsWith=H">H</a>
          </td>
          <td>
            <a href="./browse?startsWith=I">I</a>
          </td>
          <td>
            <a href="./browse?startsWith=J">J</a>
          </td>
          <td>
            <a href="./browse?startsWith=K">K</a>
          </td>
          <td>
            <a href="./browse?startsWith=L">L</a>
          </td>
          <td>
            <a href="./browse?startsWith=M">M</a>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <a href="./browse?startsWith=N">N</a>
          </td>
          <td>
            <a href="./browse?startsWith=O">O</a>
          </td>
          <td>
            <a href="./browse?startsWith=P">P</a>
          </td>
          <td>
            <a href="./browse?startsWith=Q">Q</a>
          </td>
          <td>
            <a href="./browse?startsWith=R">R</a>
          </td>
          <td>
            <a href="./browse?startsWith=S">S</a>
          </td>
          <td>
            <a href="./browse?startsWith=T">T</a>
          </td>
          <td>
            <a href="./browse?startsWith=U">U</a>
          </td>
          <td>
            <a href="./browse?startsWith=V">V</a>
          </td>
          <td>
            <a href="./browse?startsWith=W">W</a>
          </td>
          <td>
            <a href="./browse?startsWith=X">X</a>
          </td>
          <td>
            <a href="./browse?startsWith=Y">Y</a>
          </td>
          <td>
            <a href="./browse?startsWith=Z">Z</a>
          </td>
        </tr>
        <!-- End of Row 3 -->
      </table>
    </div>
  </div>
  <!-- End of Browse by Title -->

  <!-- Start of Movie List View -->
  <%@ include file="include/movielist.jsp" %>
  <!-- End of Movie List View -->

  <!-- Start of Footer -->
  <footer>
    <%@ include file="include/footer.jsp" %>
  </footer>
  <!-- End of Footer -->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
