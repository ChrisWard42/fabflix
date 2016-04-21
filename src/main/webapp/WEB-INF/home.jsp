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
            <img class="img-responsive" src="http://images.amazon.com/images/P/B00005LKHW.01.LZZZZZZZ.jpg" alt="" height="260px">
            <div class="overlay">
              <h2>Barbershop</h2>
              <a class="info" href="./movie/683008">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://images.amazon.com/images/P/B00004Z4WR.01.LZZZZZZZ.jpg" alt="" height="260px">
            <div class="overlay">
              <h2>Gone in 60 Seconds</h2>
              <a class="info" href="./movie/140004">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://images.amazon.com/images/P/B000062XH9.01.LZZZZZZZ.jpg" alt="" height="260px">
            <div class="overlay">
              <h2>Oceans Eleven</h2>
              <a class="info" href="./movie/683006">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://images.amazon.com/images/P/B00003CX5A.01.LZZZZZZZ.jpg" alt="" height="260px">
            <div class="overlay">
              <h2>Sleepy Hollow</h2>
              <a class="info" href="./movie/855008">View Movie Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://images.amazon.com/images/P/B00005R87Q.01.LZZZZZZZ.jpg" alt="" height="260px">
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
            <img class="img-responsive" src="http://www.kcweb.com/superm/supermg/b_willis.jpg" alt="">
            <div class="overlay">
              <h2>Bruce Willis</h2>
              <a class="info" href="./star/855102">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://www.askmen.com/imagesmodel/aug00/estella_warren/estella_warren_150.JPG" alt="">
            <div class="overlay">
              <h2>Estella Warren</h2>
              <a class="info" href="./star/683019">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://www.askmen.com/imagessexsymbol/2001_jun/gina_gershon/gina_gershon_150.jpg" alt="">
            <div class="overlay">
              <h2>Gina Gershon</h2>
              <a class="info" href="./star/683018">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://www.kidzworld.com/img/upload/article/a957i0_bradpittAw.jpg" alt="">
            <div class="overlay">
              <h2>Brad Pitt</h2>
              <a class="info" href="./star/855106">View Star Page</a>
            </div>
          </div>
        </td>
        <td width="20%">
          <div class="hovereffect">
            <img class="img-responsive" src="http://www.eforu.com/cards/pictures/jackiechan/jc10_thumb.jpg" alt="">
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
