<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/11
  Time: 下午5:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script>

        function inputprice() {
            var value1 = document.getElementById('input1').value;
            var value2 = document.getElementById('input2').value;
            if (value1=="" && value2!=""){
                alert("请输入价格初始值");
                return false;
            }else if(value1!="" && value2==""){
                alert("请输入价格末值");
                return false;
            }
        }

        function inputChange(ele){
            ele.value = ele.value.replace(/\D+/,'').replace(/^0*/g,'');
        };
        function compare(){
            // 获取输入框的值
            var input1 = document.getElementById('input1');
            var input2 = document.getElementById('input2');
            // 输入框的值转为Number类型
            var num1 = parseInt(input1.value);
            var num2 = parseInt(input2.value);
            // 如果第二个值小于第一个则互换
            if(num2!=0 && num1>num2){
                var temporaryValue = num1;
                num1 = num2;
                num2 = temporaryValue;
                input1.value = num1;
                input2.value = num2;
            };
        };
    </script>
</head>
<body>

<table>
    <form action="${pageContext.request.contextPath}/bookservlet?action=search&service=user&currentPage=1" method="post" onsubmit="return inputprice()">
        <input type="text" placeholder="请输入书的名称：" name="bookName" value="${sessionScope.bookName}">
        <input type="text" placeholder="请输入书的作者：" name="bookAuthor" value="${sessionScope.bookAuthor}">

        <label >价格区间</label>
        <input id="input1" type="text" oninput="inputChange(this)" onchange="compare()" name="priceMin" value="${sessionScope.priceMin}"/>
        <span class='m05'>-</span>
        <input id="input2" type="text" oninput="inputChange(this)" onchange="compare()" name="priceMax" value="${sessionScope.priceMax}"/>
        <input type="submit" value="查询" >
    </form>
    <tr>
        <td>书本名称</td>
        <td>作者</td>
        <td>价格</td>

    </tr>

    <c:forEach items="${requestScope.bookPageBean.beans}" var="book">
        <tr>
            <td>${book.getBookName()}</td>
            <td>${book.getBookAuthor()}</td>
            <td>${book.price}</td>
            <td><a href="${pageContext.request.contextPath}/bookservlet?action=borrow&bookId=${book.bookId}&readerId=${sessionScope.User.readerId}">借阅</a></td>
        </tr>
    </c:forEach>

    <tr>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&service=user&currentPage=1">首页</a></td>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&service=user&currentPage=${bookPageBean.getCurrentPage()-1}&totalPage=${bookPageBean.getTotalPage()}">上一页</a></td>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&service=user&currentPage=${bookPageBean.getCurrentPage()+1}&totalPage=${bookPageBean.getTotalPage()}">下一页</a></td>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&service=user&currentPage=${bookPageBean.getTotalPage()}">尾页</a></td>
    </tr>

</table>
<a href="${pageContext.request.contextPath}/bookservlet?action=backUser">返回主界面</a>
</body>
</html>
