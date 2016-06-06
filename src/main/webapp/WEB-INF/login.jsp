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
	<link rel="stylesheet" href="resources/css/login-style.css">
</head>
<body style="background-color:#FF3434">
	<img src="resources/img/fabflix.png" alt="" height="100px">
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="./login" method="post">
				<input type="hidden" name="action" value="auth">
				<c:if test="${empty errorMsg == false}">
					<div class="alert alert-danger" role="alert">${errorMsg}</div>
				</c:if>

				<input type="text" name="email" placeholder="Email">
				<input type="password" name="password" placeholder="Password">
                <!-- <div class="g-recaptcha" data-sitekey="6Lc4VB4TAAAAAAa2OMV10xH92aFAvOukCyNhKGIs"></div> -->
				<input type="submit" value="Sign In" id="enter">
			</form>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>
