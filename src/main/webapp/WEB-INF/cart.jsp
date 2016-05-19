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
  <link rel="stylesheet" href="resources/css/cart-style.css">
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
          <h1>Shopping Cart</h1>
        </td>
      </tr>
    </table>
    <!-- End of Title -->

    <!-- Start of Shopping Cart -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <c:choose>
        <c:when test="${empty cart == true}">
          <tr class="empty">
            <td width="100%">
              <h4><b>Your cart is empty.</b></h4>
            </td>
          </tr>
        </c:when>
        <c:otherwise>
          <tr class="title">
            <td width="">
              <h4><b>Movie Title</b></h4>
            </td>
            <td width="120px">
              <h4><b>Quantity</b></h4>
            </td>
            <td width="120px"></td>
            <td width="120px"></td>
          </tr>
        </c:otherwise>
      </c:choose>

      <c:forEach var="product" items="${cart}">
        <tr>
          <td>
            <a href="./movie/${product.key}">${product.value.details.title} (${product.value.details.year})</a>
          </td>
          <form id="itemaction" method="post" action="./cart">
            <td width="100px">
              <div class="input-group">
                <input type="hidden" name="productId" value="${product.key}">
                <input type="text" placeholder="Quantity" name="quantity" value="${product.value.quantity}">
              </div>
            </td>
            <td>
              <button type="submit" class="btn btn-default update" name="action" value="update">Update</button>
            </td>
            <td>
              <button type="submit" class="btn btn-default delete" name="action" value="remove">Remove</button>
            </td>
          </form>
        </tr>
      </c:forEach>
    </table>
    <!-- End of Shopping Cart -->

    <!-- Start of Checkout -->
    <c:if test="${empty cart == false}">
      <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
        <tr class="title">
          <td align="center" style="padding: 20px">
            <form id="empty-cart" method="post" action="./cart">
              <button type="submit" class="btn btn-default delete big" name="action" value="empty">Empty Cart</button>
            </form>
          </td>
          <td align="center" style="padding: 20px">
            <form id="check-out" method="post" action="./checkout">
              <button type="submit" class="btn btn-default update big">Check Out</button>
            </form>
          </td>
        </tr>
      </table>
    </c:if>
    <!-- End of Checkout -->
  </div>
  <!-- End of Total Purchase -->

  <!-- Start of Footer -->
  <footer>
    <%@ include file="include/footer.jsp" %>
  </footer>
  <!-- End of Footer -->
</body>
</html>
