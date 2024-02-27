<%--
  Created by IntelliJ IDEA.
  User: SPC
  Date: 2023/9/19
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>竞技联盟登录</title>
</head>
<body>
<h2>用户登录</h2>
<form method="post" action="http://localhost:8080/application_war_exploded/login_program">
    <p>账号：<input type="text"  name="id" maxlength="20" placeholder="填入账号" required></p>
    <p>密码：<input type="password" name="pwd" maxlength="20" placeholder="填入密码" required></p>
    <p><input type="submit" value="登录"></p>
    <p><input type="reset" value="重置"></p>
    <p>没有注册？<a href="register.jsp">点击注册</a></p>
</form>
</body>
</html>
