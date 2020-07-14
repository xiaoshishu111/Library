<%--
  Created by IntelliJ IDEA.
  User: xiaoshishu
  Date: 2020/7/9
  Time: 下午10:00
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <form action="${pageContext.request.contextPath}/bookservlet?action=search&currentPage=1" method="post" onsubmit="return inputprice()">
       <input type="text" placeholder="请输入书的名称：" name="bookName">
       <input type="text" placeholder="请输入书的作者：" name="bookAuthor">

        <label >价格区间</label>
        <input id="input1" type="text" oninput="inputChange(this)" onchange="compare()" name="price01" />
        <span class='m05'>-</span>
        <input id="input2" type="text" oninput="inputChange(this)" onchange="compare()" name="price02" />

        <select name="status">
            <option value ="2" >所有的</option>
            <option value ="0" >未借出</option>
            <option value ="1" >已经被借出</option>
        </select>

        <input type="submit" value="查询" >
    </form>

<table>
    <tr>
<%--        <td>书本id</td>--%>
        <td>书本名称</td>
        <td>作者</td>
        <td>价格</td>
        <td>借阅状态</td>
    </tr>
    <c:forEach items="${sessionScope.bookPageBean.getBeans()}" var="book">
    <tr>
<%--        <td>${book.getBookId()}</td>--%>
        <td>${book.getBookName()}</td>
        <td>${book.getBookAuthor()}</td>
        <td>${book.getPrice()}</td>
        <td>${book.getStatus()==0 ? "未借出" : "已经被借出"}</td>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=delete&bookId=${book.getBookId()}&currentPage=1">删除</a></td>
        <td><a href="${pageContext.request.contextPath}/UpdateBook.jsp?bookId=${book.getBookId()}&bookName=${book.getBookName()}&bookAuthor=${book.getBookAuthor()}&price=${book.getPrice()}&status=${book.getStatus()}">修改</a></td>
    </tr>
    </c:forEach>

    <tr>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&currentPage=1">首页</a></td>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&currentPage=${sessionScope.bookPageBean.getCurrentPage()-1}">上一页</a></td>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&currentPage=${sessionScope.bookPageBean.getCurrentPage()+1}">下一页</a></td>
        <td><a href="${pageContext.request.contextPath}/bookservlet?action=search&currentPage=${sessionScope.bookPageBean.getTotalPage()}">尾页</a></td>
    </tr>


    <form action="${pageContext.request.contextPath}/bookservlet?action=add&currentPage=1" method="post">
        <tr>
<%--            <td></td>--%>
            <td><input type="text" name="bookName"></td>
            <td><input type="text" name="author"></td>
            <td><input type="text" name="price"></td>
            <td></td>
            <td><input type="submit"></td>
        </tr>
    </form>

</table>

</body>
</html>
