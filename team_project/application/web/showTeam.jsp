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
            <h2>找寻你中意的队伍</h2>
            <h1>广场队伍</h1>
        </div>
    </section>
    <!-- End Page Title -->

    <!-- Blog One -->
    <section class="blog-one">
        <div class="auto-container">
            <div class="inner-container">
                
                <div class="form-container">
                    <form class="form-inline" action="${pageContext.request.contextPath}/showTeamByPage" method="post">
                        <div class="ai-block alternate col-lg-4 col-md-6 col-sm-12">
                            <div class="form-group">
                                <label for="Input1">队长学号</label>
                                <input type="text" class="form-control" name="Student_ID_leader" value="${consition.Student_ID_leader[0]}" id="Input1" >
                            </div>
                        </div>
                        <div class="ai-block alternate col-lg-4 col-md-6 col-sm-12">
                            <div class="form-group">
                                <label for="Input2">队伍特征</label>
                                <input type="text" class="form-control" name="Introduction" value="${condition.Introduction[0]}" id="Input2" >
                            </div>
                        </div>
                        <div class="ai-block alternate col-lg-4 col-md-6 col-sm-12">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" id="submit">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
                <h2>队伍相关信息：</h2>
                <div class="row clearfix">
                    <c:forEach items="${pb.list}" var="team" varStatus="s">
                    <!--Block-->
                        <div class="ai-block alternate col-lg-4 col-md-6 col-sm-12">
                            <div class="inner-box">
                                <div class="cap-box">
                                    <ul class="cap-box" style="font-size:18px;">
                                        <li><a href="#">编号:${s.count}</a></li>
                                        <li><a href="#">队伍名:${team.getTName()}</a></li>
                                        <li><a href="#">队长学号:${team.getUID_leader()}</a></li>
                                        <li><a href="#">队伍简介:${team.getIntroduction()}</a></li>
                                        <li><a href="#">招募人数:${team.getExist_num()}</a></li>
                                        <li><a href="#">创建时间:${team.getIntroduction()}</a></li>
                                        <li>
                                            操作:<a href="javascript:tip(${team.getUID_leader()})">申请加入</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!--Block-->

                </div>
                <script>
                    function tip(id) {
                        if (confirm('确定申请加入该队伍？')) {
                            window.location.href = "${pageContext.request.contextPath}/apply?leader_id=" + id;
                        } else {
                            return false;
                        }
                    }
                </script>

                <!-- Pagination Outer -->
                <div class="pagination-outer">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${pb.currentPage == 1}">
                                <li class="disabled"></li>
                            </c:if>
                            <c:if test="${pb.currentPage != 1}">
                                <li><a href="${pageContext.request.contextPath}/showTeamByPage?currentPage=${pb.currentPage - 1}&rows=5&Student_ID_leader=${condition.Student_ID_leader[0]}&Introduction=${condition.Introduction[0]}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                            </c:if>
                            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                                <c:if test="${pb.currentPage == i}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/showTeamByPage?currentPage=${i}&rows=5&Student_ID_leader=${condition.Student_ID_leader[0]}&Introduction=${condition.Introduction[0]}">${i}</a></li>
                                </c:if>
                                <c:if test="${pb.currentPage != i}">
                                    <li><a href="${pageContext.request.contextPath}/showTeamByPage?currentPage=${i}&rows=5&Student_ID_leader=${condition.Student_ID_leader[0]}&Introduction=${condition.Introduction[0]}">${i}</a></li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${pb.currentPage == pb.totalPage}">
                                <li class="disabled"></li>
                            </c:if>
                            <c:if test="${pb.currentPage != pb.totalPage}">
                                <li><a href="${pageContext.request.contextPath}/showTeamByPage?currentPage=${pb.currentPage + 1}&rows=5&Student_ID_leader=${condition.Student_ID_leader[0]}&Introduction=${condition.Introduction[0]}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                            </c:if>
                            <span style="font-size:25px;margin-left: 5px">
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
