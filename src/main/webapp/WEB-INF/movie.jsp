<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="icon" href="resources/img/favicon.ico" type="image/x-icon">
  <link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"> 
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/jquery-ui.css">
  <link rel="stylesheet" href="resources/css/fabflix-style.css">
  <link rel="stylesheet" href="resources/css/movie-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Movie View -->
  <div class="movie_list">

    <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="50%">
      <tr>
        <td class=title>
          <h1>${movie.title} (${movie.year})</h1>
          <h4 id="id">[${movie.id}]</h4>
        </td>
      </tr>
    </table>
    <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="50%">
      <tr>
        <td class="poster_pic">
          <img src="${movie.bannerUrl}" alt="" onError="this.src='resources/img/dora-error-img.png';" height="300px" />
        </td>
      </tr>

      <tr align="center">
        <td class="movie_button">
          <table class="table_buttons" border="0" cellpadding="0" cellspacing="0" width="50%">
            <tr>
              <td>
                <form id="cart" method="post" action="./cart">
                  <input type="hidden" name="action" value="add">
                  <button type="submit" class="btn btn-default" name="productId" value="${movie.id}"><img src="resources/img/shop.png" alt="cart" height="20px"> Add to Cart</button>
                </form>
              </td>

              <td class="spacing"></td>

              <td>
                <form id="trailer" method="get" action="${movie.trailerUrl}">
                  <button type="submit" class="btn btn-default"><img src="resources/img/watch.png" alt="watch" height="20px"> Watch Trailer</button>
                </form>
              </td>
            </tr>
          </table>
        </td>
      </tr>

      <tr>
        <td class="description">
          <h4>
            <span>Director</span>: ${movie.director}<br>
            <span>Genres</span>:&nbsp;<c:forEach var="genre" items="${movie.genreSet}" varStatus="genreSetStatus">
                      <c:choose>
                        <c:when test="${genreSetStatus.last == true}">
                          ${genre}
                        </c:when>
                        <c:otherwise>
                          ${genre},&nbsp;
                        </c:otherwise>
                      </c:choose>
                    </c:forEach><br>
            <span>Stars</span>:&nbsp;<c:forEach var="star" items="${movie.starSet}" varStatus="starSetStatus">
                      <c:choose>
                        <c:when test="${starSetStatus.last == true}">
                          <a href="./star/${star.id}">${star.firstName}&nbsp;${star.lastName}</a>
                        </c:when>
                        <c:otherwise>
                          <a href="./star/${star.id}">${star.firstName}&nbsp;${star.lastName}</a>,&nbsp;
                        </c:otherwise>
                      </c:choose>
                   </c:forEach><br>
          </h4>
        </td>
      </tr>
    </table>

  </div>
  <!-- End of Movie View -->

  <!-- Start of Footer -->
  <footer>
    <%@ include file="include/footer.jsp" %>
  </footer>
  <!-- End of Footer -->
</body>
</html>
