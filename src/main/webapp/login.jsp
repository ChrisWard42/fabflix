<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Fabflix</title>

	<link rel="stylesheet" href="/resources/css/style.css">
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/index-original.js"></script>
</head>
<body background = "/resources/images/background_image.jpg">
	<a class="switch" href="#"><img src="/resources/images/fabflix.png" alt="" height="100px"></a>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="./login" method="POST">
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