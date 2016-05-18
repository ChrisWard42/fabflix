<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta charset="UTF-8">
  <title>Fabflix</title>
  <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/dashboard-style.css">
</head>
<body>
  <!--  Start of Navigation Bar -->
  <header>
    <%@ include file="include/header.jsp" %>
  </header>
  <!--  End of Navigation Bar -->

    <!-- Start of Section -->
      <c:choose>
      <c:when test="${dashboard == 'login'}">
        <%@ include file="include/dashboard-login.jsp" %>
      </c:when>
      <c:when test="${dashboard == 'insertstar'}">
        <%@ include file="include/dashboard-insertstar.jsp" %>
      </c:when>
      <c:when test="${dashboard == 'metadata'}">
        <%@ include file="include/dashboard-metadata.jsp" %>
      </c:when>
      <c:when test="${dashboard == 'insertmovie'}">
        <%@ include file="include/dashboard-insertmovie.jsp" %>
      </c:when>
      <c:when test="${dashboard == 'updatemoviecheck'}">
        <%@ include file="include/dashboard-updatemoviecheck.jsp" %>
      </c:when>
      <c:when test="${dashboard == 'updatemovie'}">
        <%@ include file="include/dashboard-updatemovie.jsp" %>
      </c:when>
      <c:otherwise>
        <%@ include file="include/dashboard-index.jsp" %>
      </c:otherwise>
      </c:choose>
    <!-- End of Section -->

  <!-- Start of Footer -->
  <footer>
    <%@ include file="include/footer.jsp" %>
  </footer>
  <!-- End of Footer -->
</body>
</html>
