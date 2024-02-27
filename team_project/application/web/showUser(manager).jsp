<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>认证名单</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fff;
            color: #000;
            margin: 0;
        }
        .form-container {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            max-width: 800px;
            margin: 0 auto;
        }
        form.form-inline {
            display: flex;
            justify-content: center;
        }
        .form-group {
            margin-right: 10px;
        }
        .form-control {
            width: 150px;
        }
        a {
            display: block;
            text-decoration: none;
            color: #000;
        }
        h3 {
            text-align: center;
        }
        form {
            margin-top: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #ddd;
            background-color: #fff;
            border-radius: 5px;
            margin-top: 20px;
        }
        th, td {
            text-align: center;
            padding: 10px;
        }
        th {
            background-color: #000;
            color: #fff;
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
    </style>
</head>
<body>
<header>
    <h1>认证清单</h1>
</header>
<div class="container">
    <a href="${pageContext.request.contextPath}/home_page(manager).jsp">返回首页</a>
    <div class="form-container">
        <h4>添加认证用户</h4>
        <form action="${pageContext.request.contextPath}/addUser" onsubmit="return tip()">
            学号<input type="text" name="id" required> &nbsp; 姓名<input type="text" name="name" required> &nbsp;专业<input type="text" name="major" required> &nbsp;<input type="submit" value="添加">
        </form>
    </div>
    <div class="form-container">
        <h4>认证列表</h4>
        <form class="form-inline" action="${pageContext.request.contextPath}/showStudent_Manager" method="post">
            <div class="form-group">
                <label for="Input1">姓名</label>
                <input type="text" class="form-control" name="Student_Name" value="${consition.Student_Name[0]}" id="Input1">
            </div>
            <div class="form-group">
                <label for="Input2">学号</label>
                <input type="text" class="form-control" name="Student_ID" value="${condition.Student_ID[0]}" id="Input2">
            </div>
            <button type="submit" class="btn btn-default" id="submit">查询</button>
        </form>
    </div>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>专业</th>
            <th>账户状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="student" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${student.getStudent_ID()}</td>
                <td>${student.getStudent_Name()}</td>
                <td>${student.getMajor()}</td>
                <td>${student.getState()}</td>
                <td><a href="javascript:updateUser('${student.getStudent_ID()}')">更新专业</a></td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${pb.currentPage == 1}">
                <li class="disabled"></li>
            </c:if>
            <c:if test="${pb.currentPage != 1}">
                <li><a href="${pageContext.request.contextPath}/showStudent_Manager?currentPage=${pb.currentPage - 1}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                <c:if test="${pb.currentPage == i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/showStudent_Manager?currentPage=${i}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pb.currentPage != i}">
                    <li><a href="${pageContext.request.contextPath}/showStudent_Manager?currentPage=${i}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${pb.currentPage == pb.totalPage}">
                <li class="disabled"></li>
            </c:if>
            <c:if test="${pb.currentPage != pb.totalPage}">
                <li><a href="${pageContext.request.contextPath}/showStudent_Manager?currentPage=${pb.currentPage + 1}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
                </li>
            </c:if>
            <span style="font-size:25px;margin-left: 5px">
                共${pb.totalCount}条记录，共${pb.totalPage}页
            </span>
        </ul>
    </nav>
</div>
<script>
    function tip() {
        if (confirm("确定添加认证用户？")) {
            return true;
        } else {
            return false;
        }
    }

    function updateUser(id) {
        let major = prompt('输入新的专业名称')
        window.location.href = '${pageContext.request.contextPath}/updateUser?id=' + id + '&major=' + major;
    }
</script>
</body>
</html>
