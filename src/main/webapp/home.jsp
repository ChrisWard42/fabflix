<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <link rel="stylesheet" href="resources/css/style2.css">
  </head>
  <body background="resources/images/background_image-3.jpg">
    <div class="login-page">
      <div class="form">
        <form class="browse-box" action="#" method="get">
          <button id="browse">Browse by Genre</button>
          <button id="browse">Browse by Letter</button>
          <button class="back1">Back</button>
        </form>

        <form class="search-box" action="#" method="post">
          <input type="text" name="query" placeholder="Title"/>
          <button id="enter" type="submit" formaction="./search" formmethod="post">Enter</button>
          <button class="back2">Back</button>
        </form>

        <form class="login-form" id="login" action="#" method="get">
          <button class="browse">Browse</button>
          <button class="search">Search</button>
        </form>
      </div>
    </div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="resources/js/index.js"></script>
    </body>
</html>