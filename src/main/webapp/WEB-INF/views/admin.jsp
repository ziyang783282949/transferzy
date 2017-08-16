<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
</head>
<body>
	<h1>${message}</h1>
	<ul>
		<li>用户名：${admin.username}----密码：${admin.password}----</li>
	</ul>
</body>
</html>