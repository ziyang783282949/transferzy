<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>积分管理系统登录页面</title>

<link rel='stylesheet prefetch' href='/css/bootstrap.min.css'>
<link rel="stylesheet" href="css/login.css">
</head>

<body>
	<div class="wrapper">
		<form class="form-signin" action="/pokerTicket/login">
			<h2 class="form-signin-heading">积分管理系统登录页面</h2>
			<input type="text" class="form-control" name="username" placeholder="用户名" required="" autofocus="" /> 
			<input type="password" class="form-control" name="password" placeholder="密码" required="" /> 
			<label class="checkbox"> 
				<input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> 记住我
			</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>
	</div>
</body>
</html>
