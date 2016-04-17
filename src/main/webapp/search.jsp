<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/result-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Advanced Search Menu -->
  <div class="advance_search">
    <button id="adv_search" class="btn btn-info" data-toggle="collapse" data-target="#demo"><img src="/resources/img/expand-white.png" alt="" height="10px" hspace="10px">Advanced Search</button>
    <div id="demo" class="collapse">
      <table border="0" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="80%">
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Title</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Year</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Director</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Star</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1">
            </div>
          </td>
          <td width="20%">
            <button id="search_button" type="submit" class="btn btn-default">Search</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <!-- End of Advanced Search Menu -->

  <!-- Start of Movie List View -->
  <div class="movie_list">
    <table border="0" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="left">
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Sort by:</span>
            <div class="input-group-btn">
              <button class="btn btn-default" type="button"><img src="/resources/img/expand-gray.png" alt="" height="10px" hspace="10px">Title</button>
              <button class="btn btn-default" type="button"><img src="/resources/img/expand-gray.png" alt="" height="10px" hspace="10px">Year</button>
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
          <img src="/resources/img/poster-5.jpg" alt="" width="100%"/>
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
          <img src="/resources/img/poster-5.jpg" alt="" width="100%"/>
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
          <img src="/resources/img/poster-5.jpg" alt="" width="100%"/>
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
  <script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
