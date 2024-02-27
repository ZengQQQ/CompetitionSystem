<%--
  Created by IntelliJ IDEA.
  User: SPC
  Date: 2023/11/4
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员模式</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px;
        }

        h3 {
            margin: 0;
        }

        a {
            text-decoration: none;
            color: #333;
        }

        a:hover {
            color: #555;
        }
        header {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px;
        }

        h1 {
            margin: 0;
        }
        .container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .container a {
            display: block;
            margin: 10px 0;
            padding: 10px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
        }

        .container a:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<header>
    <h1>管理员模式</h1>
</header>
<div class="container">
    <a href="${pageContext.request.contextPath}/showStudent_Manager">管理认证名单</a>
    <a href="${pageContext.request.contextPath}/showStudentByPage">用户管理</a>
</div>
</body>
</html>
