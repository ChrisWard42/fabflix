<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/check-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <!-- Start of Total Purchase -->
  <div class="cart">

    <!-- Start of Title -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr>
        <td align="center" style="padding: 20px">
          <h1>Check Out</h1>
        </td>
      </tr>
    </table>
    <!-- End of Title -->

    <form id="checkout" method = "post" action="./checkout">
      <!-- Start of Customer Info -->
      <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
        <tr>
          <td>
            <c:if test="${empty errorMsg == false}">
              <div class="alert alert-danger" role="alert">${errorMsg}</div>
            </c:if>
          </td>
        </tr>
        <tr>
          <td>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Credit Number</span>
              <input type="text" class="form-control" placeholder="1-20 digits" name="ccNum" value="${param.ccNum}">
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Expiration Date</span>
              <input type="text" class="form-control" placeholder="MM/YY or YYYY-MM-DD format" name="expiry" value="${param.expiry}">
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">First Name</span>
              <input type="text" class="form-control" placeholder="As it appears on the card" name="firstName" value="${param.firstName}">
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">Last Name</span>
              <input type="text" class="form-control" placeholder="As it appears on the card" name="lastName" value=${param.lastName}>
            </div>
          </td>
        </tr>
      </table>
      <!-- End of Customer Info -->

      <!-- Start of Checkout -->
      <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
        <tr class="title">
          <td align="center" style="padding: 20px">
              <button type="submit" class="btn btn-default delete big" formmethod="get" formaction="./cart">Previous</button>
          </td>
          <td align="center" style="padding: 20px">
              <button type="submit" class="btn btn-default update big" name="action" value="purchase">Purchase</button>
          </td>
        </tr>
      </table>
    </form>
    <!-- End of Checkout -->
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
