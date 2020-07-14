<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/9
  Time: 下午10:00
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
<%--    ${requestScope.readers}--%>
    <tr>
<%--        <td>读者id</td>--%>
        <td>读者姓名</td>
        <td>读者账号</td>
        <td>读者密码</td>
<%--        <td>读者权限</td>--%>
    </tr>
    <c:forEach items="${readers}" var="reader">
        <tr>
<%--            <td>${reader.getReaderId()}</td>--%>
            <td>${reader.getReaderName()}</td>
            <td>${reader.getReaderAccount()}</td>
            <td>${reader.getReaderPassword()}</td>
<%--            <td>${reader.getReaderAuthorty()==0 ? "普通读者" : "管理员"}</td>--%>
            <td><a href="${pageContext.request.contextPath}/readerservlet?action=delete&readerId=${reader.getReaderId()}">删除</a></td>
            <td><a href="${pageContext.request.contextPath}/UpdateReader.jsp?readerId=${reader.getReaderId()}&readerName=${reader.getReaderName()}&readerAccount=${reader.getReaderAccount()}&readerPassword=${reader.getReaderPassword()}&readerAuthorty=${reader.getReaderAuthorty()}">修改</a></td>
        </tr>
    </c:forEach>
    <form action="${pageContext.request.contextPath}/readerservlet?action=add" method="post">
        <tr>
<%--            <td></td>--%>
            <td><input type="text" name="readerName"></td>
            <td><input type="text" name="readerAccount"></td>
            <td><input type="text" name="readerPassword"></td>
<%--            <td></td>--%>
            <td><input type="submit"></td>
        </tr>
    </form>
</table>

</body>
</html>
