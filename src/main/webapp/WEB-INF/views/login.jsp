<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>My JSP 'MyJsp.jsp' starting page</title>  
  </head>  
    
  <body>  
    <h1>登录页面----${message}</h1>  
    <form:form action="/pokerTicket/login" commandName="admin" method="post">  
        用户名：<form:input path="username"/> <form:errors path="username"/> <br/>  
        密 &nbsp;&nbsp;码：<form:password path="password"/> <form:errors path="password"/> <br/>  
        <form:button name="button">submit</form:button>  
    </form:form>  
  </body>  
</html>  