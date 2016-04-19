<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/search-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Advanced Search Menu -->
  <div class="advance_search">
    <button id="adv_search" class="btn btn-info" data-toggle="collapse" data-target="#demo"><img src="resources/img/expand-white.png" alt="" height="10px" hspace="10px">Advanced Search</button>
    <div id="demo" class="collapse in">
      <table border="0" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <form class="adv_search_form" role="search" method="get" action="./search">
          <td width="80%">
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Title</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1" name="title">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Year</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1" name="year">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Director</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1" name="director">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Star</span>
              <input type="text" class="form-control" aria-describedby="basic-addon1" name="star">
            </div>
          </td>
          <td width="20%">
            <button id="search_button" type="submit" class="btn btn-default">Search</button>
          </td>
          </form>
        </tr>
      </table>
    </div>
  </div>
  <!-- End of Advanced Search Menu -->

  <!-- Start of Movie List View -->
  <%@ include file="include/movielist.jsp" %>
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
