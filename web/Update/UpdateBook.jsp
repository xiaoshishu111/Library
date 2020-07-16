<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/8
  Time: 上午3:37
  To change this template use File | Settings | File Templates.
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=utf-8" language="java" %>

<meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript">

        function GetQueryString(name)
        {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
            if(r!=null)return  decodeURI(r[2]); return null;
        }

        function doGender(status) {
            if (status == "0") {
                document.getElementById("status01").checked = "true";
            } else {
                document.getElementById("status02").checked = "true";
            }
        }

        window.onload = function () {
            var bookId = GetQueryString("bookId");
            var bookName = GetQueryString("bookName");
            var bookAuthor = GetQueryString("bookAuthor");
            var price = GetQueryString("price");
            var status = GetQueryString("status");

            document.getElementById("bookId").value = bookId;
            document.getElementById("bookName").value = bookName;
            document.getElementById("bookAuthor").value = bookAuthor;
            document.getElementById("price").value = price;
            doGender(status);

        }

    </script>

</head>
<body>


<form action="${pageContext.request.contextPath}/bookservlet?action=update&currentPage=1" method="post">

    <div hidden>书的id：<input id="bookId" type="text" name="bookId" ><br></div>
    请填写更新后书的名称：<input id="bookName" type="text" name="bookName"><br>
    请填写更新后书的作者：<input id="bookAuthor" type="text" name="author"><br>
    请填写更新后书的价格：<input id="price" type="text" name="price"><br>
    请填写更新后书的状态：
    <div hidden>
        <label><input id="status01" type="radio" name="status" value="0">尚未被借走</label>
        <label><input id="status02" type="radio" name="status" value="1">已经被借走</label>
    </div>
    <input type="submit" >

</form>
</body>
</html>
