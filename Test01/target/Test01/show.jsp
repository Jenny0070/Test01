<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sunny
  Date: 2018/10/13
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table style="border:1px solid #F00">
    <caption>新闻</caption>
    <tr>
        <thread>
            <th>用户名</th>
            <th>身份</th>
            <th>邮箱</th>
            <th>联系方式</th>
        </thread>

    </tr>
    <tbody>
    <script type="text/javascript">
        var aa='<%=request.getAttribute("jsonObject")%>'
        alert(aa);
        <%
//        System.out.println(aa);
        %>
    </script>
        <c:forEach items="${requestScope.list}" var="users">
            <tr><td>${users.username}</td><td>${users.identity}</td><td>${users.email}</td><td>${users.phoneNum}</td></tr>
        </c:forEach>


    </tbody>
</table>

</body>
</html>
