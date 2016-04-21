<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <!--  Logo Button -->
      <ul class="nav navbar-nav">
        <li><a href="./home"><img src="resources/img/fabflix.png" alt="logo" height="20px"></a></li>
      </ul>
      <!--  Logo Button -->

      <!--  Rest of Buttons -->
      <ul class="nav navbar-nav navbar-right">
        <li><a href="./search">Adv. Search</a></li>
        <li><a href="./browse">Browse</a></li>
        <!--
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Your Account</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Logout</a></li>
          </ul>
        </li>
        -->
        <li><a href="./cart"><img src="resources/img/shop.png" alt="cart" height="20px"></a></li>
      </ul>
      <!--  Rest of Buttons -->

      <!--  Search Bar -->
      <form class="navbar-form navbar-right" role="search" method="get" action="./search">
        <div class="form-group">
          <input type="text" class="form-control" name="query" placeholder="Search" style="width: 400px !important">
        </div>
        <button type="submit" class="btn btn-default"><img src="resources/img/search.png" alt="search" height="20px"></button>
      </form>
      <!--  Search Bar -->

    </div>
  </div>
</nav>