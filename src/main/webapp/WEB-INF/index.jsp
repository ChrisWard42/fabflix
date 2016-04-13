<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login Page</title>
	<link rel="stylesheet" href="resources/css/style.css">
</head>
<body background = "resources/images/background_image.jpg">
	<a class="switch" href="#"><img src="resources/images/fabflix.png" alt="" height="100px"></a>
	<div class="login-page">
		<div class="form">
			<form class="credits">
				<table align="center" border="0" border-radius="15px" cellpadding="0" cellspacing="0" width="100%">
					<tr valign="middle">
						<td>
							<p>Benjamin You (Benla)</p>
							<p>Chris Ward (Captain)</p>
							<p>Stephen Castro (Stevo)</p>
						</td>
					</tr>
				</table>
			</form>

			<form class="login-form" action="./process-login" method="POST">
				<input type="hidden" name="action" value="add">
				
				<input type="text" name="email" placeholder="Email">
				<input type="password" name="pword" placeholder="Password">
				<input type="submit" value="Sign In" id="enter">
			</form>
		</div>
	</div>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="resources/js/index-original.js"></script>
</body>
</html>