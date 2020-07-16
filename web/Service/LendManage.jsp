<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/9
  Time: 下午10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>借阅id</td>
        <td>读者id</td>
        <td>书本id</td>
        <td>借书时间</td>
        <td>还书时间</td>
    </tr>
    <c:forEach items="${lends}" var="lend">
        <tr>
            <td>${lend.getLendId()}</td>
            <td>${lend.getReaderId()}</td>
            <td>${lend.getBookId()}</td>
            <td>${lend.getBeginTime()}</td>
            <td>${lend.getEndTime()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
