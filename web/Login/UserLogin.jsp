<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/7
  Time: 下午12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ws</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/userservlet?action=userLogin" method="post">
    请填写您的账号：<input id="readerAccount" type="text" name="readerAccount"><br>
    请填写您的密码：<input id="readerPassword" type="text" name="readerPassword"><br>
    <input type="submit" ><a href="Register/Register.jsp">还没有账号？点击注册</a>
</form>
<c:if test="${isreader=='0'}">
    <span>没有此账号</span>
</c:if>
<c:if test="${isreader=='1'}">
    <span>您的密码有误</span>
</c:if>
<div><a href="${pageContext.request.contextPath}/Login/AdminLogin.jsp">进入管理员登陆界面</a></div>
</body>
</html>
