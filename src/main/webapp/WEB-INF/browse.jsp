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
            <c:if test="${param.genre == 'Crime'}">
              <a class="disabled" href="./browse?genre=Crime">Crime</a>
            </c:if>
            <c:if test="${param.genre != 'Crime'}">
              <a href="./browse?genre=Crime">Crime</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Foreign'}">
              <a class="disabled" href="./browse?genre=Foreign">Foreign</a>
            </c:if>
            <c:if test="${param.genre != 'Foreign'}">
              <a href="./browse?genre=Foreign">Foreign</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Romance'}">
              <a class="disabled" href="./browse?genre=Romance">Romance</a>
            </c:if>
            <c:if test="${param.genre != 'Romance'}">
              <a href="./browse?genre=Romance">Romance</a>
            </c:if>
          </td>
        </tr>
        <!-- End of Row 1 -->

        <!-- Start of Row 2 -->
        <tr>
          <td>
            <c:if test="${param.genre == 'Adventure'}">
              <a class="disabled" href="./browse?genre=Adventure">Adventure</a>
            </c:if>
            <c:if test="${param.genre != 'Adventure'}">
              <a href="./browse?genre=Adventure">Adventure</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Drama'}">
              <a class="disabled" href="./browse?genre=Drama">Drama</a>
            </c:if>
            <c:if test="${param.genre != 'Drama'}">
              <a href="./browse?genre=Drama">Drama</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Horror'}">
              <a class="disabled" href="./browse?genre=Horror">Horror</a>
            </c:if>
            <c:if test="${param.genre != 'Horror'}">
              <a href="./browse?genre=Horror">Horror</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Science Fiction'}">
              <a class="disabled" href="./browse?genre=Science Fiction">Science Fiction</a>
            </c:if>
            <c:if test="${param.genre != 'Science Fiction'}">
              <a href="./browse?genre=Science Fiction">Science Fiction</a>
            </c:if>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <c:if test="${param.genre == 'Amination'}">
              <a class="disabled" href="./browse?genre=Amination">Amination</a>
            </c:if>
            <c:if test="${param.genre != 'Amination'}">
              <a href="./browse?genre=Amination">Amination</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Family'}">
              <a class="disabled" href="./browse?genre=Family">Family</a>
            </c:if>
            <c:if test="${param.genre != 'Family'}">
              <a href="./browse?genre=Family">Family</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Musical'}">
              <a class="disabled" href="./browse?genre=Musical">Musical</a>
            </c:if>
            <c:if test="${param.genre != 'Musical'}">
              <a href="./browse?genre=Musical">Musical</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Thriller'}">
              <a class="disabled" href="./browse?genre=Thriller">Thriller</a>
            </c:if>
            <c:if test="${param.genre != 'Thriller'}">
              <a href="./browse?genre=Thriller">Thriller</a>
            </c:if>
          </td>
        </tr>
        <!-- End of Row 3 -->

        <!-- Start of Row 4 -->
        <tr>
          <td>
            <c:if test="${param.genre == 'Comedy'}">
              <a class="disabled" href="./browse?genre=Comedy">Comedy</a>
            </c:if>
            <c:if test="${param.genre != 'Comedy'}">
              <a href="./browse?genre=Comedy">Comedy</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Fantasy'}">
              <a class="disabled" href="./browse?genre=Fantasy">Fantasy</a>
            </c:if>
            <c:if test="${param.genre != 'Fantasy'}">
              <a href="./browse?genre=Fantasy">Fantasy</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'Mystery'}">
              <a class="disabled" href="./browse?genre=Mystery">Mystery</a>
            </c:if>
            <c:if test="${param.genre != 'Mystery'}">
              <a href="./browse?genre=Mystery">Mystery</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.genre == 'War'}">
              <a class="disabled" href="./browse?genre=War">War</a>
            </c:if>
            <c:if test="${param.genre != 'War'}">
              <a href="./browse?genre=War">War</a>
            </c:if>
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
            <c:if test="${param.startsWith == 'A'}">
              <a class="disabled" href="./browse?startsWith=A">A</a>
            </c:if>
            <c:if test="${param.startsWith != 'A'}">
              <a href="./browse?startsWith=A">A</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'B'}">
              <a class="disabled" href="./browse?startsWith=B">B</a>
            </c:if>
            <c:if test="${param.startsWith != 'B'}">
              <a href="./browse?startsWith=B">B</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'C'}">
              <a class="disabled" href="./browse?startsWith=C">C</a>
            </c:if>
            <c:if test="${param.startsWith != 'C'}">
              <a href="./browse?startsWith=C">C</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'D'}">
              <a class="disabled" href="./browse?startsWith=D">D</a>
            </c:if>
            <c:if test="${param.startsWith != 'D'}">
              <a href="./browse?startsWith=D">D</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'E'}">
              <a class="disabled" href="./browse?startsWith=E">E</a>
            </c:if>
            <c:if test="${param.startsWith != 'E'}">
              <a href="./browse?startsWith=E">E</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'F'}">
              <a class="disabled" href="./browse?startsWith=F">F</a>
            </c:if>
            <c:if test="${param.startsWith != 'F'}">
              <a href="./browse?startsWith=F">F</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'G'}">
              <a class="disabled" href="./browse?startsWith=G">G</a>
            </c:if>
            <c:if test="${param.startsWith != 'G'}">
              <a href="./browse?startsWith=G">G</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'H'}">
              <a class="disabled" href="./browse?startsWith=H">H</a>
            </c:if>
            <c:if test="${param.startsWith != 'H'}">
              <a href="./browse?startsWith=H">H</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'I'}">
              <a class="disabled" href="./browse?startsWith=I">I</a>
            </c:if>
            <c:if test="${param.startsWith != 'I'}">
              <a href="./browse?startsWith=I">I</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'J'}">
              <a class="disabled" href="./browse?startsWith=J">J</a>
            </c:if>
            <c:if test="${param.startsWith != 'J'}">
              <a href="./browse?startsWith=J">J</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'K'}">
              <a class="disabled" href="./browse?startsWith=K">K</a>
            </c:if>
            <c:if test="${param.startsWith != 'K'}">
              <a href="./browse?startsWith=K">K</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'L'}">
              <a class="disabled" href="./browse?startsWith=L">L</a>
            </c:if>
            <c:if test="${param.startsWith != 'L'}">
              <a href="./browse?startsWith=L">L</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'M'}">
              <a class="disabled" href="./browse?startsWith=M">M</a>
            </c:if>
            <c:if test="${param.startsWith != 'M'}">
              <a href="./browse?startsWith=M">M</a>
            </c:if>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <c:if test="${param.startsWith == 'N'}">
              <a class="disabled" href="./browse?startsWith=N">N</a>
            </c:if>
            <c:if test="${param.startsWith != 'N'}">
              <a href="./browse?startsWith=N">N</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'O'}">
              <a class="disabled" href="./browse?startsWith=O">O</a>
            </c:if>
            <c:if test="${param.startsWith != 'O'}">
              <a href="./browse?startsWith=O">O</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'P'}">
              <a class="disabled" href="./browse?startsWith=P">P</a>
            </c:if>
            <c:if test="${param.startsWith != 'P'}">
              <a href="./browse?startsWith=P">P</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'Q'}">
              <a class="disabled" href="./browse?startsWith=Q">Q</a>
            </c:if>
            <c:if test="${param.startsWith != 'Q'}">
              <a href="./browse?startsWith=Q">Q</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'R'}">
              <a class="disabled" href="./browse?startsWith=R">R</a>
            </c:if>
            <c:if test="${param.startsWith != 'R'}">
              <a href="./browse?startsWith=R">R</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'S'}">
              <a class="disabled" href="./browse?startsWith=S">S</a>
            </c:if>
            <c:if test="${param.startsWith != 'S'}">
              <a href="./browse?startsWith=S">S</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'T'}">
              <a class="disabled" href="./browse?startsWith=T">T</a>
            </c:if>
            <c:if test="${param.startsWith != 'T'}">
              <a href="./browse?startsWith=T">T</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'U'}">
              <a class="disabled" href="./browse?startsWith=U">U</a>
            </c:if>
            <c:if test="${param.startsWith != 'U'}">
              <a href="./browse?startsWith=U">U</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'V'}">
              <a class="disabled" href="./browse?startsWith=V">V</a>
            </c:if>
            <c:if test="${param.startsWith != 'V'}">
              <a href="./browse?startsWith=V">V</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'W'}">
              <a class="disabled" href="./browse?startsWith=W">W</a>
            </c:if>
            <c:if test="${param.startsWith != 'W'}">
              <a href="./browse?startsWith=W">W</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'X'}">
              <a class="disabled" href="./browse?startsWith=X">X</a>
            </c:if>
            <c:if test="${param.startsWith != 'X'}">
              <a href="./browse?startsWith=X">X</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'Y'}">
              <a class="disabled" href="./browse?startsWith=Y">Y</a>
            </c:if>
            <c:if test="${param.startsWith != 'Y'}">
              <a href="./browse?startsWith=Y">Y</a>
            </c:if>
          </td>
          <td>
            <c:if test="${param.startsWith == 'Z'}">
              <a class="disabled" href="./browse?startsWith=Z">Z</a>
            </c:if>
            <c:if test="${param.startsWith != 'Z'}">
              <a href="./browse?startsWith=Z">Z</a>
            </c:if>
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
