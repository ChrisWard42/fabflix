<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Main Page</title>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/home-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <!--  Logo Button -->
          <ul class="nav navbar-nav">
            <li><a href="#"><img src="resources/images/fabflix.png" alt="logo" height="20"></a></li>
          </ul>
          <!--  Logo Button -->

          <!--  Rest of Buttons -->
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Browse</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Your Account</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Logout</a></li>
              </ul>
            </li>
            <li><a href="#"><img src="resources/images/shop.png" alt="cart" height="20"></a></li>
          </ul>
          <!--  Rest of Buttons -->

          <!--  Search Bar -->
          <form class="navbar-form navbar-right" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search" style="width: 300px !important">
            </div>
            <button type="submit" class="btn btn-default"><img src="resources/images/search.png" alt="search" height="20"></button>
          </form>
          <!--  Search Bar -->

        </div>
      </div>
    </nav>
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
        <img src="resources/images/background-image.jpg" alt="picture">
      </div>

      <div class="item">
        <img src="resources/images/background-image-2.jpg" alt="picture">
      </div>

      <div class="item">
        <img src="resources/images/background-image-3.jpg" alt="picture">
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
          <a href="#"><img src="resources/images/poster-1.jpg" alt="twilight"></a>
        </div>
      </div>

      <!-- Poster 2 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/images/poster-2.jpg" alt="mockingjay"></a>
        </div>
      </div>

      <!-- Poster 3 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/images/poster-3.jpg" alt="star wars"></a>
        </div>
      </div>

      <!-- Poster 4 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/images/poster-4.jpg" alt="fight club"></a>
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
          <a href="#"><img src="resources/images/star-1.jpg" alt="will smith"></a>
        </div>
      </div>

      <!-- Poster 2 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/images/star-2.jpg" alt="..."></a>
        </div>
      </div>

      <!-- Poster 3 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/images/star-3.jpg" alt="..."></a>
        </div>
      </div>

      <!-- Poster 4 -->
      <div class="col-sm-3 centered">
        <div class="thumbnail">
          <a href="#"><img src="resources/images/star-4.jpg" alt="..."></a>
        </div>
      </div>
    </div>
  </div>
  <!-- End of Featured Stars -->

  <footer>
    <div class="panel panel-default">
      <div class="panel-footer">
        <table border="0" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td align="left">
              A Jamshid company
            </td>
            <td align="right">
              Copyright (c) 2016 Benla, Captain, Stevo
            </td>
          </tr>
        </table>
      </div>
    </div>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
