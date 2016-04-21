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
            <c:if test="${param.genre == 'Action'}">
              <a class="disabled" href="./browse?genre=Action">Action</a>
            </c:if>
            <c:if test="${param.genre != 'Action'}">
              <a href="./browse?genre=Action">Action</a>
            </c:if>
          </td>
          <td>
            <a href="./browse?genre=Crime">Crime</a>
            <a href="./browse?genre=Crime">Crime</a>
          </td>
          <td>
            <a href="./browse?genre=Foreign">Foreign</a>
            <a href="./browse?genre=Foreign">Foreign</a>
          </td>
          <td>
            <a href="./browse?genre=Romance">Romance</a>
            <a href="./browse?genre=Romance">Romance</a>
          </td>
        </tr>
        <!-- End of Row 1 -->

        <!-- Start of Row 2 -->
        <tr>
          <td>
            <a href="./browse?genre=Adventure">Adventure</a>
            <a href="./browse?genre=Adventure">Adventure</a>
          </td>
          <td>
            <a href="./browse?genre=Drama">Drama</a>
            <a href="./browse?genre=Drama">Drama</a>
          </td>
          <td>
            <a href="./browse?genre=Horror">Horror</a>
            <a href="./browse?genre=Horror">Horror</a>
          </td>
          <td>
            <a href="./browse?genre=Science+Fiction">Science Fiction</a>
            <a href="./browse?genre=Science+Fiction">Science Fiction</a>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <a href="./browse?genre=Animation">Animation</a>
            <a href="./browse?genre=Animation">Animation</a>
          </td>
          <td>
            <a href="./browse?genre=Family">Family</a>
            <a href="./browse?genre=Family">Family</a>
          </td>
          <td>
            <a href="./browse?genre=Musical">Musical</a>
            <a href="./browse?genre=Musical">Musical</a>
          </td>
          <td>
            <a href="./browse?genre=Thriller">Thriller</a>
            <a href="./browse?genre=Thriller">Thriller</a>
          </td>
        </tr>
        <!-- End of Row 3 -->

        <!-- Start of Row 4 -->
        <tr>
          <td>
            <a href="./browse?genre=Comedy">Comedy</a>
            <a href="./browse?genre=Comedy">Comedy</a>
          </td>
          <td>
            <a href="./browse?genre=Fantasy">Fantasy</a>
            <a href="./browse?genre=Fantasy">Fantasy</a>
          </td>
          <td>
            <a href="./browse?genre=Mystery">Mystery</a>
            <a href="./browse?genre=Mystery">Mystery</a>
          </td>
          <td>
            <a href="./browse?genre=War">War</a>
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
             <c:if test="${param.startsWith == '0'}">
              <a class="disabled" href="./browse?startsWith=0">0</a>
             </c:if>
             <c:if test="${param.startsWith != '0'}">
              <a href="./browse?startsWith=0">0</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '1'}">
              <a class="disabled" href="./browse?startsWith=1">1</a>
             </c:if>
             <c:if test="${param.startsWith != '1'}">
              <a href="./browse?startsWith=1">1</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '2'}">
              <a class="disabled" href="./browse?startsWith=2">2</a>
             </c:if>
             <c:if test="${param.startsWith != '2'}">
              <a href="./browse?startsWith=2">2</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '3'}">
              <a class="disabled" href="./browse?startsWith=3">3</a>
             </c:if>
             <c:if test="${param.startsWith != '3'}">
              <a href="./browse?startsWith=3">3</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '4'}">
              <a class="disabled" href="./browse?startsWith=4">4</a>
             </c:if>
             <c:if test="${param.startsWith != '4'}">
              <a href="./browse?startsWith=4">4</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '5'}">
              <a class="disabled" href="./browse?startsWith=5">5</a>
             </c:if>
             <c:if test="${param.startsWith != '5'}">
              <a href="./browse?startsWith=5">5</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '6'}">
              <a class="disabled" href="./browse?startsWith=6">6</a>
             </c:if>
             <c:if test="${param.startsWith != '6'}">
              <a href="./browse?startsWith=6">6</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '7'}">
              <a class="disabled" href="./browse?startsWith=7">7</a>
             </c:if>
             <c:if test="${param.startsWith != '7'}">
              <a href="./browse?startsWith=7">7</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '8'}">
              <a class="disabled" href="./browse?startsWith=8">8</a>
             </c:if>
             <c:if test="${param.startsWith != '8'}">
              <a href="./browse?startsWith=8">8</a>
             </c:if>
          </td>
          <td>
             <c:if test="${param.startsWith == '9'}">
              <a class="disabled" href="./browse?startsWith=9">9</a>
             </c:if>
             <c:if test="${param.startsWith != '9'}">
              <a href="./browse?startsWith=9">9</a>
             </c:if>
          </td>
        </tr>
      </table>
      <table align="center" border="0" border="0" cellpadding="0" cellspacing="0" width="60%">

        <!-- Start of Row 2 -->
        <tr>
          <td>
            <a href="./browse?startsWith=A">A</a>
            <a href="./browse?startsWith=A">A</a>
          </td>
          <td>
            <a href="./browse?startsWith=B">B</a>
            <a href="./browse?startsWith=B">B</a>
          </td>
          <td>
            <a href="./browse?startsWith=C">C</a>
            <a href="./browse?startsWith=C">C</a>
          </td>
          <td>
            <a href="./browse?startsWith=D">D</a>
            <a href="./browse?startsWith=D">D</a>
          </td>
          <td>
            <a href="./browse?startsWith=E">E</a>
            <a href="./browse?startsWith=E">E</a>
          </td>
          <td>
            <a href="./browse?startsWith=F">F</a>
            <a href="./browse?startsWith=F">F</a>
          </td>
          <td>
            <a href="./browse?startsWith=G">G</a>
            <a href="./browse?startsWith=G">G</a>
          </td>
          <td>
            <a href="./browse?startsWith=H">H</a>
            <a href="./browse?startsWith=H">H</a>
          </td>
          <td>
            <a href="./browse?startsWith=I">I</a>
            <a href="./browse?startsWith=I">I</a>
          </td>
          <td>
            <a href="./browse?startsWith=J">J</a>
            <a href="./browse?startsWith=J">J</a>
          </td>
          <td>
            <a href="./browse?startsWith=K">K</a>
            <a href="./browse?startsWith=K">K</a>
          </td>
          <td>
            <a href="./browse?startsWith=L">L</a>
            <a href="./browse?startsWith=L">L</a>
          </td>
          <td>
            <a href="./browse?startsWith=M">M</a>
            <a href="./browse?startsWith=M">M</a>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <a href="./browse?startsWith=N">N</a>
            <a href="./browse?startsWith=N">N</a>
          </td>
          <td>
            <a href="./browse?startsWith=O">O</a>
            <a href="./browse?startsWith=O">O</a>
          </td>
          <td>
            <a href="./browse?startsWith=P">P</a>
            <a href="./browse?startsWith=P">P</a>
          </td>
          <td>
            <a href="./browse?startsWith=Q">Q</a>
            <a href="./browse?startsWith=Q">Q</a>
          </td>
          <td>
            <a href="./browse?startsWith=R">R</a>
            <a href="./browse?startsWith=R">R</a>
          </td>
          <td>
            <a href="./browse?startsWith=S">S</a>
            <a href="./browse?startsWith=S">S</a>
          </td>
          <td>
            <a href="./browse?startsWith=T">T</a>
            <a href="./browse?startsWith=T">T</a>
          </td>
          <td>
            <a href="./browse?startsWith=U">U</a>
            <a href="./browse?startsWith=U">U</a>
          </td>
          <td>
            <a href="./browse?startsWith=V">V</a>
            <a href="./browse?startsWith=V">V</a>
          </td>
          <td>
            <a href="./browse?startsWith=W">W</a>
            <a href="./browse?startsWith=W">W</a>
          </td>
          <td>
            <a href="./browse?startsWith=X">X</a>
            <a href="./browse?startsWith=X">X</a>
          </td>
          <td>
            <a href="./browse?startsWith=Y">Y</a>
            <a href="./browse?startsWith=Y">Y</a>
          </td>
          <td>
            <a href="./browse?startsWith=Z">Z</a>
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
