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
  <link rel="stylesheet" href="resources/css/template-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

  <div class="page">

    <!-- Start of Section -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <c:choose>
      <c:when test="${report == 'readme'}">
        <%@ include file="include/report-readme.jsp" %>
      </c:when>
      <c:when test="${report == 'like-predicate'}">
        <%@ include file="include/report-like-predicate.jsp" %>
      </c:when>
      <c:when test="${report == 'xml-parsing-optimization'}">
        <%@ include file="include/report-xml-parsing-optimization.jsp" %>
      </c:when>
      <c:when test="${report == 'connection_pooling'}">
        <%@ include file="include/report-connection-pooling.jsp" %>
      </c:when>
      <c:when test="${report == 'jmeter_report'}">
        <%@ include file="include/report-jmeter.jsp" %>
      </c:when>
      <c:when test="${report == 'jmeter_report.html'}">
        <%@ include file="include/report-jmeter.jsp" %>
      </c:when>
      <c:otherwise>
        <%@ include file="include/report-index.jsp" %>
      </c:otherwise>
      </c:choose>
    </table>
    <!-- End of Section -->

  </div>

  <!-- Start of Footer -->
  <footer>
    <%@ include file="include/footer.jsp" %>
  </footer>
  <!-- End of Footer -->
</body>
</html>
