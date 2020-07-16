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
<%--输入读者的姓名或者账号对读者数据进行查询--%>
<form action="${pageContext.request.contextPath}/readerservlet?action=search&currentPage=1" method="post">
    <input type="text" placeholder="请输入读者姓名：" name="readerName" value="${sessionScope.readerName}">
    <input type="text" placeholder="请输入书的账号：" name="readerAccount" value="${sessionScope.readerAccount}">
    <input type="submit" value="查询" >
</form>
<table>
    <tr>
        <td>读者姓名</td>
        <td>读者账号</td>
        <td>读者密码</td>
    </tr>
    <c:forEach items="${requestScope.readerPageBean.getBeans()}" var="reader">
        <tr>
            <td>${reader.getReaderName()}</td>
            <td>${reader.getReaderAccount()}</td>
            <td>${reader.getReaderPassword()}</td>
            <td><a href="${pageContext.request.contextPath}/readerservlet?action=delete&readerId=${reader.getReaderId()}">删除</a></td>
            <td><a href="${pageContext.request.contextPath}/Service/UpdateReader.jsp?readerId=${reader.getReaderId()}&readerName=${reader.getReaderName()}&readerAccount=${reader.getReaderAccount()}&readerPassword=${reader.getReaderPassword()}&readerAuthorty=${reader.getReaderAuthorty()}">修改</a></td>
        </tr>
    </c:forEach>

    <tr>
        <td><a href="${pageContext.request.contextPath}/readerservlet?action=search&currentPage=1">首页</a></td>
        <td><a href="${pageContext.request.contextPath}/readerservlet?action=search&currentPage=${readerPageBean.getCurrentPage()-1}&totalPage=${readerPageBean.getTotalPage()}">上一页</a></td>
        <td><a href="${pageContext.request.contextPath}/readerservlet?action=search&currentPage=${readerPageBean.getCurrentPage()+1}&totalPage=${readerPageBean.getTotalPage()}">下一页</a></td>
        <td><a href="${pageContext.request.contextPath}/readerservlet?action=search&currentPage=${readerPageBean.getTotalPage()}">尾页</a></td>
    </tr>

    <form action="${pageContext.request.contextPath}/readerservlet?action=add" method="post">
        <tr>
            <td><input type="text" name="readerName"></td>
            <td><input type="text" name="readerAccount"></td>
            <td><input type="text" name="readerPassword"></td>
            <td><input type="submit"></td>
        </tr>
    </form>
</table>
<a href="${pageContext.request.contextPath}/readerservlet?action=back">返回主界面</a>
</body>
</html>
