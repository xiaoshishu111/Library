<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/14
  Time: 下午7:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form action="${pageContext.request.contextPath}/registerservlet" method="post">
            请输入您的姓名：<input id="readerName" type="text" name="readerAccount"><br>
            请输入您的账号：<input id="readerAccount" type="text" name="readerName"><br>
            请输入您的密码：<input id="readerPassword" type="text" name="readerPassword"><br>
            请确认您的密码：<input id="readerPassword2" type="text" name="readerPassword"><br>
            <span>${requestScope.result}</span>
            <input type="submit" >
        </form>
</body>
</html>
