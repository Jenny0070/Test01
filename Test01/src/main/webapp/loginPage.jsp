<%--
  Created by IntelliJ IDEA.
  User: Sunny
  Date: 2018/10/10
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="MainServlet?state=loginPage" method="post">
    用户名：<input type="text" name="username">
    密&nbsp;码：<input type="password" name="password">
    <input type="submit" value="登录">
</form>

</body>
</html>
