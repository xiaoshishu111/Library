<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/9
  Time: 下午9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h5>欢迎管理员：${sessionScope.Admin.getReaderName()}</h5>
<form action="${pageContext.request.contextPath}/adminindex?currentPage=1" method="post">
    <h1>图书馆管理员系统</h1>
    请输入您要选择的功能：<br>
    <label><input type="radio" name="choice" value="1">图书管理</label><br>
    <label><input type="radio" name="choice" value="2">读者信息管理</label><br>
    <label><input type="radio" name="choice" value="3">借阅记录查询</label><br>
    <input type="submit" >
</form>
<h5><a href="${pageContext.request.contextPath}/userservlet?action=logout">退出</a></h5>
</body>
</html>
