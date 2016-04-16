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
      <form class="navbar-form navbar-right" role="search" method="get" action="#">
        <div class="form-group">
          <input type="hidden" name="sort" value="title-desc">
          <input type="hidden" name="limit" value="25">
          <input type="hidden" name="page" value="2">
          <input type="text" class="form-control" name="query" placeholder="Search" style="width: 300px !important">
        </div>
        <button type="submit" class="btn btn-default" formaction="./search" formmethod="post"><img src="resources/images/search.png" alt="search" height="20"></button>
      </form>
      <!--  Search Bar -->

    </div>
  </div>
</nav>