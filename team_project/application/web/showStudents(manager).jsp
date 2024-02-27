<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>Archway HTML Template - Blog</title>
<!-- Stylesheets -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
<link rel="icon" href="images/favicon.png" type="image/x-icon">
<!-- Responsive -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link href="css/responsive.css" rel="stylesheet">
<!--[if lt IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script><![endif]--> 

</head>

<body>
  <div class="page-wrapper"> 
  
    <!-- Preloader -->

    <!-- Main Header-->
      <header class="main-header">
          <!-- Header Upper -->
          <div class="header-upper">
              <div class="auto-container">
                  <!-- Main Box -->
                  <div class="main-box clearfix">
                      <!--Logo-->
                      <div class="logo-box">
                          <div class="logo"><a href="index.jsp" title="Archway HTML Template"><img src="images/logo.png" alt="" title="Archway HTML Template"></a></div>
                      </div>

                      <div class="nav-box clearfix">
                          <!--Nav Outer-->
                          <div class="nav-outer clearfix">
                              <nav class="main-menu">
                              </nav>
                              <!-- Main Menu End-->
                          </div>
                          <!--Nav Outer End-->

                          <div class="links-box clearfix">
                              <div class="link contact-link">
                                  <a href="home_page.jsp" class="theme-btn contact-btn"><span class="btn-box"><span class="btn-txt">主页</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
                              </div>
                          </div>

                          <!-- Hidden Nav Toggler -->
                          <div class="nav-toggler">
                              <button class="hidden-bar-opener"><span class="icon"><img src="images/icons/menu-icon.png" alt=""></span></button>
                          </div>

                      </div>

                  </div>
              </div>
          </div>
      </header>
    <!--End Main Header -->

    <!--Search Backdrop-->
    <div class="search-backdrop"></div>

    <!--Menu Backdrop-->
    <div class="menu-backdrop"></div>

   

    <!-- Page Title -->
    <section class="page-title" style="background-image: url(images/background/page-title.jpg);">
        <div class="auto-container">
            <h2>已经注册的用户</h2>
            <h1>管理</h1>
        </div>
    </section>
    <!-- End Page Title -->

    <!-- Blog One -->
    <section class="blog-one">
        <div class="auto-container">
            <div class="inner-container">
                
                <div class="form-container">
                    <form class="form-inline" action="${pageContext.request.contextPath}/showStudentByPage" method="post">
                        <div class="ai-block alternate col-lg-4 col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="Input1">姓名</label>
                                <input type="text" class="form-control" name="Student_Name" value="${consition.Student_Name[0]}" id="Input1" >
                            </div>
                        </div>
                        <div class="ai-block alternate col-lg-4 col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="Input2">学号</label>
                                <input type="text" class="form-control" name="Student_ID" value="${condition.Student_ID[0]}" id="Input2">
                            </div>
                        </div>
                    
                        <div class="ai-block alternate col-lg-4 col-md-4 col-sm-12">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" id="submit">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
                <script>
                    let input1 = document.getElementById("Input1");
                    let input2 = document.getElementById("Input2");
                    let submit = document.getElementById("submit");
                    submit.addEventListener('click', judge, false);
            
                    function judge() {
                        if (input1.value === '' && input2.value === '' ) {
                            alert("没有输入条件，无法查询！");
                            return false;
                        }
                        return true;
                    }
                </script>
                <div class="row clearfix">
                    <h2>已经注册的用户信息:</h2>
                <!--Block-->        
                        <div class="container" ng-app="winnieApp" ng-controller="winnieCtrl">
                            <h4>名单</h4>
                            <div class="box">
                                <div class="container push-left d-flex justify-content-center align-items-center" ng-app="winnieApp" ng-controller="winnieCtrl">
                                <table class="table table-striped" style="font-size: 12px;">
                                    <thead style="font-size: 20px;">
                                        <th>编号</th>
                                        <th>学号</th>
                                        <th>姓名</th>
                                        <th>昵称</th>
                                        <th>联系方式</th>
                                        <th>年级</th>
                                        <th>专业</th>
                                        <th>特长</th>
                                        <th>注册时间</th>
                                        <th>账户状态</th>
                                        <th>操作</th>
                                    </thead>
                                    <tbody style="font-size: 18px">
                                        <c:forEach items="${pb.list}" var="student" varStatus="s">
                                            <tr ng-repeat="data in information | filter:search">
                                                <td><div>${s.count}</div></td>
                                                <td><div>${student.getStudent_ID()}</div></td>
                                                <td>${student.getStudent_Name()}</td>
                                                <td>${student.getUName()}</td>
                                                <td>${student.getTel()}</td>
                                                <td>${student.getGrade()}</td>
                                                <td>${student.getMajor()}</td>
                                                <td>${student.getStrong_point()}</td>
                                                <td>${student.getRegistration_time()}</td>
                                                <td>${student.getState()}</td>
                                                <td>
                                                    <a class="ban-link" href="javascript:tip('${student.getState()}','${student.getStudent_ID()}')">
                                                            ${student.getState().equals("正常") ? '封禁':'解除封禁'}
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                    <script>
                                        function tip(state,id){
                                            if(state=='正常'){
                                                if(confirm('确定封禁账户'+id+'？')){
                                                    window.location.href="${pageContext.request.contextPath}/ban?student_id="+id;
                                                }
                                                else {
                                                    return false;
                                                }
                                            }
                                            else {
                                                if(confirm('确定解封账户'+id+'？')){
                                                    window.location.href="${pageContext.request.contextPath}/setback?student_id="+id;
                                                }
                                                else {
                                                    return false;
                                                }
                                            }
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                <!--Block-->

                </div>

                <!-- Pagination Outer -->
                <div class="pagination-outer">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${pb.currentPage == 1}">
                            <li class="disabled">
                                </c:if>
                                <c:if test="${pb.currentPage != 1}">
                            <li>
                                </c:if>
                                <a href="${pageContext.request.contextPath}/showStudentByPage?currentPage=${pb.currentPage - 1}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                                <c:if test="${pb.currentPage == i}">
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/showStudentByPage?currentPage=${i}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}">${i}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pb.currentPage != i}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/showStudentByPage?currentPage=${i}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}">${i}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${pb.currentPage == pb.totalPage}">
                            <li class="disabled">
                                </c:if>
                                <c:if test="${pb.currentPage != pb.totalPage}">
                            <li>
                                </c:if>
                                <a href="${pageContext.request.contextPath}/showStudentByPage?currentPage=${pb.currentPage + 1}&rows=5&Student_Name=${condition.Student_Name[0]}&Major=${condition.Major[0]}&Strong_point=${condition.Stront_point[0]}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <span style="font-size: 25px; margin-left: 5px">
                共${pb.totalCount}条记录，共${pb.totalPage}页
            </span>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
    </section>
    <!-- End Blog One -->

    <!--Subscribe Section-->
      <section class="subscribe-section">
          <div class="bg-layer" style="background-image: url(images/background/pattern-2.jpg);"></div>
          <div class="auto-container">
              <div class="content-box">
                  <div class="row clearfix">
                      <div class="text-col col-xl-8 col-lg-12 col-md-12 col-sm-12">
                          <div class="inner">
                              <h2>联系我们!</h2>
                              <div class="text">如果遇到问题可以告诉我们你的联系方式</div>
                          </div>
                      </div>
                      <div class="form-col col-xl-4 col-lg-12 col-md-12 col-sm-12">
                          <div class="inner">
                              <div class="form-box subscribe-form">
                                  <form method="post" action="new_for_manage.html">
                                      <div class="form-group">
                                          <div class="field-inner">
                                              <input type="text" name="massage" value="" placeholder="your Email" required>
                                          </div>
                                          <button type="submit" class="theme-btn"><i class="icon fal fa-arrow-right"></i></button>
                                      </div>
                                  </form>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </section>


</div>
<!--End pagewrapper--> 

<!--Scroll to top-->
<div class="scroll-to-top scroll-to-target" data-target="html" title="Go To Top"><span class="icon fa fa-angle-up"></span></div>

<script src="js/jquery.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.fancybox.js"></script>
<script src="js/owl.js"></script>
<script src="js/appear.js"></script>
<script src="js/wow.js"></script>
<script src="js/custom-script.js"></script>
</body>
</html>
