<%--
  Created by IntelliJ IDEA.
  User: Sunny
  Date: 2018/10/10
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
</head>
<body>
<h1>欢迎来到主页</h1>
<form action="loginPage.jsp"  method = "post">
    <input  type = "submit"   value = "登陆"   >
</form>
<form action="registerPage.jsp" method = "post">
    <input  type = "submit"   value= "注册">
</form>
<form action="MainServlet?state=queryAll"  method = "post">
    <input  type = "submit"   value = "查看"   >
</form>

</body>
</html>

