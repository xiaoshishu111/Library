<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/11
  Time: 下午5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h5>欢迎读者：${sessionScope.NormalReader.getReaderName()}</h5>
<form action="${pageContext.request.contextPath}/normalindex" method="post">
    <h1>图书馆系统</h1>
    请输入您要选择的功能：<br>
    <label><input type="radio" name="choice" value="1">借书</label><br>
    <label><input type="radio" name="choice" value="2">还书</label><br>
    <input type="submit" >
</form>
<h5><a href="${pageContext.request.contextPath}/logoutservlet">退出</a></h5>
</body>
</html>
