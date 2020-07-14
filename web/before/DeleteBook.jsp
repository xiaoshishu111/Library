<%--
  Created by IntelliJ IDEA.
  User: jiangyue
  Date: 2020/7/8
  Time: 上午3:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/deletebook" method="post">
    请输入您要删除的书的id：<input type="text" name="bookId"><br>
    <input type="submit" >
</form>
</body>
</html>
