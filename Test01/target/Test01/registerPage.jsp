<%--
  Created by IntelliJ IDEA.
  User: Sunny
  Date: 2018/10/10
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="MainServlet?state=registerPage" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    确认密码：<input type="password" name="password2"><br>
    性别：<input type="button" name="gender"><br>
    邮箱：<input type="text" name="email"><br>
    联系方式：<input type="text" name="phoneNum"><br>
    身份：<input type="text" name="identity"><br>

    <input type="submit" value="注册">

</form>

</body>
</html>
