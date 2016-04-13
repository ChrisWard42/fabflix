<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login Page</title>
	<link rel="stylesheet" href="style.css">
</head>
<body background = "images/background_image.jpg">
	<img src="images/fabflix.png" alt="" height="100px">
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="./TomcatForm" method="POST">
				<input type="hidden" name="action" value="add">
				
				<input type="text" name="email" placeholder="Email">
				<input type="password" name="pword" placeholder="Password">
				<input type="submit" value="Login" id="enter">
			</form>
		</div>
	</div>
</body>
</html>