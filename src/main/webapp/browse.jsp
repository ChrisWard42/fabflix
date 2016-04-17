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
    <%@ include file="header.jsp" %>
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
            <a href="#Action">Action</a>
          </td>
          <td>
            <a href="#Crime">Crime</a>
          </td>
          <td>
            <a href="#Foreign">Foreign</a>
          </td>
          <td>
            <a href="#Romance">Romance</a>
          </td>
        </tr>
        <!-- End of Row 1 -->

        <!-- Start of Row 2 -->
        <tr>
          <td>
            <a href="#Adventure">Adventure</a>
          </td>
          <td>
            <a href="#Drama">Drama</a>
          </td>
          <td>
            <a href="#Horror">Horror</a>
          </td>
          <td>
            <a href="#Science_Fiction">Science Fiction</a>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <a href="#Animation">Animation</a>
          </td>
          <td>
            <a href="#Family">Family</a>
          </td>
          <td>
            <a href="#Musical">Musical</a>
          </td>
          <td>
            <a href="#Thriller">Thriller Fiction</a>
          </td>
        </tr>
        <!-- End of Row 3 -->

        <!-- Start of Row 4 -->
        <tr>
          <td>
            <a href="#Comedy">Comedy</a>
          </td>
          <td>
            <a href="#Fantasy">Fantasy</a>
          </td>
          <td>
            <a href="#Mystery">Mystery</a>
          </td>
          <td>
            <a href="#War">War</a>
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
            <a href="#0">0</a>
          </td>
          <td>
             <a href="#1">1</a>
          </td>
          <td>
             <a href="#2">2</a>
          </td>
          <td>
             <a href="#3">3</a>
          </td>
          <td>
             <a href="#4">4</a>
          </td>
          <td>
             <a href="#5">5</a>
          </td>
          <td>
             <a href="#6">6</a>
          </td>
          <td>
             <a href="#7">7</a>
          </td>
          <td>
             <a href="#8">8</a>
          </td>
          <td>
             <a href="#9">9</a>
          </td>
        </tr>
      </table>
      <table align="center" border="0" border="0" cellpadding="0" cellspacing="0" width="60%">

        <!-- Start of Row 2 -->
        <tr>
          <td>
            <a href="#A">A</a>
          </td>
          <td>
            <a href="#B">B</a>
          </td>
          <td>
            <a href="#C">C</a>
          </td>
          <td>
            <a href="#D">D</a>
          </td>
          <td>
            <a href="#E">E</a>
          </td>
          <td>
            <a href="#F">F</a>
          </td>
          <td>
            <a href="#G">G</a>
          </td>
          <td>
            <a href="#H">H</a>
          </td>
          <td>
            <a href="#I">I</a>
          </td>
          <td>
            <a href="#J">J</a>
          </td>
          <td>
            <a href="#K">K</a>
          </td>
          <td>
            <a href="#L">L</a>
          </td>
          <td>
            <a href="#M">M</a>
          </td>
        </tr>
        <!-- End of Row 2 -->

        <!-- Start of Row 3 -->
        <tr>
          <td>
            <a href="#N">N</a>
          </td>
          <td>
            <a href="#O">O</a>
          </td>
          <td>
            <a href="#P">P</a>
          </td>
          <td>
            <a href="#Q">Q</a>
          </td>
          <td>
            <a href="#R">R</a>
          </td>
          <td>
            <a href="#S">S</a>
          </td>
          <td>
            <a href="#T">T</a>
          </td>
          <td>
            <a href="#U">U</a>
          </td>
          <td>
            <a href="#V">V</a>
          </td>
          <td>
            <a href="#W">W</a>
          </td>
          <td>
            <a href="#X">X</a>
          </td>
          <td>
            <a href="#Y">Y</a>
          </td>
          <td>
            <a href="#Z">Z</a>
          </td>
        </tr>
        <!-- End of Row 3 -->
      </table>
    </div>
  </div>
  <!-- End of Browse by Title -->

  <!-- Start of Movie List View -->
  <div class="movie_list">
    <table border="0" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="left">
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Sort by:</span>
            <div class="input-group-btn">
              <button class="btn btn-default" type="button"><img src="resources/img/expand-gray.png" alt="" height="10px" hspace="10px">Title</button>
              <button class="btn btn-default" type="button"><img src="resources/img/expand-gray.png" alt="" height="10px" hspace="10px">Year</button>
            </div>
          </div>
        </td>
        <td align="right">
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Items per Page:</span>
            <div class="input-group-btn">
              <button class="btn btn-default" type="button">10</button>
              <button class="btn btn-default" type="button">25</button>
              <button class="btn btn-default" type="button">50</button>
            </div>
          </div>
        </td>
      </tr>
    </table>

    <!-- Start on top pagination -->
    <nav>
      <ul class="pager">
        <li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> Previous</a></li>
        <li class="next"><a href="#">Next <span aria-hidden="true">&rarr;</span></a></li>
      </ul>
    </nav>
    <!-- End on top pagination -->

    <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr style="padding: 10px 0">
        <td class="poster_pic">
          <img src="resources/img/poster-5.jpg" alt="" width="100%"/>
        </td>

        <td class="spacing"></td>

        <td class="description">
          <h3><a href="https://en.wikipedia.org/wiki/Leon:_The_Professional">Leon: The Professional</a> (1994)</h3>
          <h4 id="id">[269307161]</h4>
          <br>
          <h4>
            Director: Luc Besson<br>
            Genres: Thriller<br>
            Stars: Jean Reno, Gary Oldman, Natalie Portman, Danny Aiello<br>
          </h4>

          <table class="table_buttons" border="0" cellpadding="0" cellspacing="0">
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
        <td class="poster_pic">
          <img src="resources/img/poster-5.jpg" alt="" width="100%"/>
        </td>

        <td class="spacing"></td>

        <td class="description">
          <h3><a href="https://en.wikipedia.org/wiki/Leon:_The_Professional">Leon: The Professional</a> (1994)</h3>
          <h4 id="id">[269307161]</h4>
          <br>
          <h4>
            Director: Luc Besson<br>
            Genres: Thriller<br>
            Stars: Jean Reno, Gary Oldman, Natalie Portman, Danny Aiello<br>
          </h4>

          <table class="table_buttons" border="0" cellpadding="0" cellspacing="0">
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
        <td class="poster_pic">
          <img src="resources/img/poster-5.jpg" alt="" width="100%"/>
        </td>

        <td class="spacing"></td>

        <td class="description">
          <h3><a href="https://en.wikipedia.org/wiki/Leon:_The_Professional">Leon: The Professional</a> (1994)</h3>
          <h4 id="id">[269307161]</h4>
          <br>
          <h4>
            Director: Luc Besson<br>
            Genres: Thriller<br>
            Stars: Jean Reno, Gary Oldman, Natalie Portman, Danny Aiello<br>
          </h4>

          <table class="table_buttons" border="0" cellpadding="0" cellspacing="0">
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
    </table>

    <!-- Start on bottom pagination -->
    <nav>
      <ul class="pager">
        <li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> Previous</a></li>
        <li class="next"><a href="#">Next <span aria-hidden="true">&rarr;</span></a></li>
      </ul>
    </nav>
    <!-- End on bottom pagination -->
  </div>

  <!-- End of Movie List View -->
  <footer>
    <%@ include file="footer.jsp" %>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
