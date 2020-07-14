<%--
  Created by IntelliJ IDEA.
  User: jiangyue
  Date: 2020/7/8
  Time: 上午1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/addbook" method="post">
        请填写书的id：<input type="text" name="bookId"><br>
        请填写书的名称：<input type="text" name="bookName"><br>
        请填写书的作者：<input type="text" name="author"><br>
        请填写书的价格：<input type="text" name="price"><br>
    <input type="submit" >
</form>
</body>
</html>
