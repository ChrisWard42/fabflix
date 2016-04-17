<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/home-style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Carousel -->
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="resources/img/background-image.jpg" alt="picture">
      </div>

      <div class="item">
        <img src="resources/img/background-image-2.jpg" alt="picture">
      </div>

      <div class="item">
        <img src="resources/img/background-image-3.jpg" alt="picture">
      </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  <!-- End of Carousel -->

  <!-- Start of Featured Movies -->
  <div class=featured_movies>
    <div class="row" max-width="100%">
      <div class="thumbnail" style="margin-bottom: 0px">
        <h1 class="featured_title">Featured Movies <span class="label label-default">NEW</span></h1>
      </div>
    </div>

    <div class="row" max-width="100%">
      <!-- Poster 1 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/poster-1.jpg" alt="twilight"></a>
        </div>
      </div>

      <!-- Poster 2 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/poster-2.jpg" alt="mockingjay"></a>
        </div>
      </div>

      <!-- Poster 3 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/poster-3.jpg" alt="star wars"></a>
        </div>
      </div>

      <!-- Poster 4 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/poster-4.jpg" alt="fight club"></a>
        </div>
      </div>
    </div>
  </div>
  <!-- End of Featured Movies -->

  <!-- Start of Featured Stars -->
  <div class=featured_stars>
    <div class="row" max-width="100%">
      <div class="thumbnail" style="margin-bottom: 0px">
        <h1 class="featured_title">Featured Stars <span class="label label-default">HOT</span></h1>
      </div>
    </div>

    <div class="row" max-width="100%">
      <!-- Poster 1 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/star-1.jpg" alt="will smith"></a>
        </div>
      </div>

      <!-- Poster 2 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/star-2.jpg" alt="..."></a>
        </div>
      </div>

      <!-- Poster 3 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/star-3.jpg" alt="..."></a>
        </div>
      </div>

      <!-- Poster 4 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/img/star-4.jpg" alt="..."></a>
        </div>
      </div>
    </div>
  </div>
  <!-- End of Featured Stars -->

  <footer>
    <%@ include file="footer.jsp" %>
  </footer>
</body>
</html>
