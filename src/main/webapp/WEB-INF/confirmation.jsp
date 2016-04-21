<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/confirmation-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- End of Total Purchase -->
  <div class="cart">

    <!-- Start of Title -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr>
        <td align="center" style="padding: 20px">
          <h1>Confirmation</h1>
        </td>
      </tr>
      <tr>
        <td>
          <table align="center" border="0" cellpadding="0" cellspacing="0" width="60%">
            <tr>
              <td width="125px">
                <img src="resources/img/good.png" alt="" height="125px"/>
              </td>
              <td>
                <h3 id="id">Your purchase is completed.</h3><br>
                <h4>Thank you for shopping with Fabflix.</h4>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <!-- End of Title -->

    <!-- Start of Shopping Cart -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr class="title">
        <td width="">
          <h4><b>Movie(s) Purchased</b></h4>
        </td>
        <td class="quantity" width="120px">
          <h4><b>Quantity</b></h4>
        </td>
      </tr>

      <c:forEach var="product" items="${purchased}">
        <tr>
          <td>
            <a href="./movie/${product.key}">${product.value.details.title} (${product.value.details.year})</a>
          </td>
          <td class="quantity">
            ${product.value.quantity}
          </td>
        </tr>
      </c:forEach>
    </table>
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr class="title">
        <td align="center" style="padding: 20px">
          <form id="home" method="post" action="./home">
            <button type="submit" class="btn btn-default update big">Return to Home</button>
          </form>
        </td>
      </tr>
    </table>
    <!-- End of Shopping Cart -->
  </div>
  <!-- End of Total Purchase -->

  <!-- Start of Footer -->
  <footer>
    <%@ include file="include/footer.jsp" %>
  </footer>
  <!-- End of Footer -->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
