<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<meta charset="UTF-8">
	<title>Fabflix</title>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<link rel="stylesheet" href="resources/css/login-style.css">
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/index-original.js"></script>
</head>
<body background = "resources/img/background_image.jpg">
	<a class="switch" href="#"><img src="resources/img/fabflix.png" alt="" height="100px"></a>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="./login" method="post">
				<input type="hidden" name="action" value="auth">
				<ul color="white">${errorMsg}</ul>
				
				<input type="text" name="email" placeholder="Email">
				<input type="password" name="password" placeholder="Password">
				<input type="submit" value="Sign In" id="enter">
			</form>
		</div>
	</div>
</body>
</html>