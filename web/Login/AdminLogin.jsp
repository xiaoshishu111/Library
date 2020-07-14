<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/11
  Time: 下午4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/userservlet?action=adminLogin" method="post">
    请填写您的账号：<input id="readerAccount" type="text" name="readerAccount"><br>
    请填写您的密码：<input id="readerPassword" type="text" name="readerPassword"><br>
    <input type="submit" >
</form>
<c:if test="${isreader=='0'}">
    <span>没有此账号</span>
</c:if>
<c:if test="${isreader=='1'}">
    <span>您不是管理员</span>
</c:if>
<c:if test="${isreader=='2'}">
    <span>您的密码有误</span>
</c:if>
</body>
</html>
