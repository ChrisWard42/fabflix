<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/home2-style.css">
  <link rel="stylesheet" href="resources/css/hover-effect.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Carousel -->
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
      <li data-target="#myCarousel" data-slide-to="4"></li>
      <li data-target="#myCarousel" data-slide-to="5"></li>
      <li data-target="#myCarousel" data-slide-to="6"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="resources/img/background-image.jpg" alt="picture" width="100%">
      </div>

      <div class="item">
        <img src="resources/img/background-image-2.jpg" alt="picture" width="100%">
      </div>

      <div class="item">
        <img src="resources/img/background-image-4.jpg" alt="picture" width="100%">
      </div>

      <div class="item">
        <img src="resources/img/background-image-5.jpg" alt="picture" width="100%">
      </div>

      <div class="item">
        <img src="resources/img/background-image-6.jpg" alt="picture" width="100%">
      </div>

      <div class="item">
        <img src="resources/img/background-image-7.jpg" alt="picture" width="100%">
      </div>

      <div class="item">
        <img src="resources/img/background-image-8.jpg" alt="picture" width="100%">
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

  <div class="title">
    <table align="center" border="1" cellpadding="0" cellspacing="0" width="100%">
      <tr height="50px">
        <td>
          <h2>Featured Movies</h2>
        </td>
      </tr>
    </table>
  </div>

  <div class="movies">
    <table align="center" border="1" cellpadding="0" cellspacing="0" width="100%">
      <tr height="260px">
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/banner_url/683008" alt="" height="260px">
            <div class="overlay">
              <h2>Barbershop</h2>
              <a class="info" href="./movie/683008">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/banner_url/140004" alt="" height="260px">
            <div class="overlay">
              <h2>Gone in 60 Seconds</h2>
              <a class="info" href="./movie/140004">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/banner_url/683006" alt="" height="260px">
            <div class="overlay">
              <h2>Oceans Eleven</h2>
              <a class="info" href="./movie/683006">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/banner_url/855008" alt="" height="260px">
            <div class="overlay">
              <h2>Sleepy Hollow</h2>
              <a class="info" href="./movie/855008">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/banner_url/683007" alt="" height="260px">
            <div class="overlay">
              <h2>The Fast and the Furious</h2>
              <a class="info" href="./movie/683007" style="margin:160px 0 0;">View Movie Page</a>
            </div>
          </div>
        </td>
      </tr>
    </table>
  </div>

  <div class="title">
    <table align="center" border="1" cellpadding="0" cellspacing="0" width="100%">
      <tr height="50px">
        <td>
          <h2>Featured Stars</h2>
        </td>
      </tr>
    </table>
  </div>

  <div class="movies">
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr height="260px">
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/photo_url/855102" alt="">
            <div class="overlay">
              <h2>Bruce Willis</h2>
              <a class="info" href="./star/855102">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/photo_url/683019" alt="">
            <div class="overlay">
              <h2>Estella Warren</h2>
              <a class="info" href="./star/683019">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/photo_url/683018" alt="">
            <div class="overlay">
              <h2>Gina Gershon</h2>
              <a class="info" href="./star/683018">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/photo_url/855106" alt="">
            <div class="overlay">
              <h2>Brad Pitt</h2>
              <a class="info" href="./star/855106">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="resources/img/photo_url/855100" alt="">
            <div class="overlay">
              <h2>Jackie Chan</h2>
              <a class="info" href="./star/855100">View Star Page</a>
            </div>
          </div>
        </td>
      </tr>
    </table>
  </div>
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
