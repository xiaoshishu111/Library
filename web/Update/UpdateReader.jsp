<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/11
  Time: 上午12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <title>Title</title>

    <script type="text/javascript">

        function GetQueryString(name)
        {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
            if(r!=null)return  decodeURI(r[2]); return null;
        }

        function doGender(readerAuthorty) {
            if (readerAuthorty == "0") {
                document.getElementById("readerAuthorty01").checked = "true";
            } else {
                document.getElementById("readerAuthorty02").checked = "true";
            }
        }
        window.onload = function () {
            var readerId = GetQueryString("readerId");
            // alert(bookId);
            var readerName = GetQueryString("readerName");
            var readerAccount = GetQueryString("readerAccount");
            var readerPassword= GetQueryString("readerPassword");
            var readerAuthorty = GetQueryString("readerAuthorty");
            document.getElementById("readerId").value = readerId;
            document.getElementById("readerName").value = readerName;
            document.getElementById("readerAccount").value = readerAccount;
            document.getElementById("readerPassword").value = readerPassword;
            doGender(readerAuthorty);
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/readerservlet?action=update" method="post">

    读者的id：<input hidden id="readerId" type="text" name="readerId" readonly="readonly"><br>
    请填写更新后读者名字：<input id="readerName" type="text" name="readerName"><br>
    请填写更新后读者账号：<input id="readerAccount" type="text" name="readerAccount"><br>
    请填写更新后账号密码：<input id="readerPassword" type="text" name="readerPassword"><br>
    请填写更新后书的状态：
    <div hidden>
        <label><input id="readerAuthorty01" type="radio" name="readerAuthorty" value="0" >普通读者</label>
        <label><input id="readerAuthorty02" type="radio" name="readerAuthorty" value="1" >管理员</label>
    </div>
    <input type="submit" >
</form>
</body>
</html>
