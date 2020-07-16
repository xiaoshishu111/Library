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
    <script>
        function check() {
            var pas1=document.getElementById("readerPassword01").value;
            var pas2=document.getElementById("readerPassword02").value;
            if (pas2!=pas1){
                alert("您第二次输入的密码不对");
            }
        }
    </script>
</head>
<body>
        <form action="${pageContext.request.contextPath}/userservlet?action=register" method="post">
            请输入您的姓名：<input id="readerName" type="text" name="readerAccount"><br>
            请输入您的账号：<input id="readerAccount" type="text" name="readerName"><br>
            请输入您的密码：<input id="readerPassword01" type="password" name="readerPassword"><br>
            请确认您的密码：<input id="readerPassword02" type="password" name="readerPassword" onblur="check()"><br>
            <span>${requestScope.result}</span>
            <input type="submit" >
        </form>
</body>
</html>
