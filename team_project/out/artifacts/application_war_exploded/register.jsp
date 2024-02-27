<%--
  Created by IntelliJ IDEA.
  User: SPC
  Date: 2023/9/19
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>竞技联盟注册</title>
</head>
<body>
<h2>用户注册</h2>
  <form method="post" action="http://localhost:8080/application_war_exploded/register_program">
      <p>输入姓名 <input type="text" name="name" maxlength="10" required placeholder="真实姓名"></p>
      <p>输入学号<input type="text"  name="student_id"  maxlength="10"  required placeholder="真实学号"></p>
      <p>输入联系方式: <input type="text" name="phone" maxlength="15" required  placeholder="真实联系电话"></p>
      <p>输入密码:<input type="password" name="pwd1" required></p>
      <p>再次确认密码:<input type="password" name="pwd2" required></p>
      <p><input type="submit" value="注册"></p>
      <p><input type="reset" value="清空"></p>
      <p>已有账号？<a href="login.jsp">返回登录</a></p>
  </form>
</body>
</html>
