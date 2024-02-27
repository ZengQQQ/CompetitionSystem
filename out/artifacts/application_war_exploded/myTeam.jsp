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
            <h2>查看或管理你的队伍</h2>
            <h1>队伍现状：</h1>
        </div>
    </section>
    <!-- End Page Title -->

    <!-- Blog One -->
    <section class="blog-one">
        <div class="auto-container">
            <div class="inner-container">

                <div class="nav-outer clearfix">
                    <nav class="main-menu">
                        <ul class="navigation clearfix">
                            <li class="dropdown"><a href="#" style="color: black;">管理队伍</a>
                                <ul>
                                    <li> <a href="${pageContext.request.contextPath}/checkPeople">管理成员<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/dealApply">处理入队申请<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/returnTeam_info">编辑队伍信息<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                                </ul>
                            </li>
                            </li>
                        </ul>
                    </nav>
                    <!-- Main Menu End-->
                </div>


                <div class="row clearfix">
                    <!--Block-->
                        <h3>队伍情况概览:</h3>

<%--                        <a href="${pageContext.request.contextPath}/checkPeople">管理成员<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a>--%>
<%--                        <a href="${pageContext.request.contextPath}/dealApply">处理入队申请<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a>--%>
<%--                        <a href="${pageContext.request.contextPath}/returnTeam_info">编辑队伍信息<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a>--%>
                    <br/>
                    <br/>
                    <br/>
                    <div class="container" ng-app="winnieApp" ng-controller="winnieCtrl">
                                <div class="box"><div class="container push-left d-flex justify-content-center align-items-center" ng-app="winnieApp" ng-controller="winnieCtrl">
                                    <table class="table table-striped">
                                        <thead>
                                            <th>队伍名</th>
                                            <th>队长学号</th>
                                            <th>队伍简介</th>
                                            <th>空余位置</th>
                                            <th>创建时间</th>
                                        </thead>
                                        <tbody style="font-size: 18px">
                                                <tr ng-repeat="data in information | filter:search">
                                                    <td>${myTeam.getTName()}</td>
                                                    <td>${myTeam.getUID_leader()}</td>
                                                    <td>${myTeam.getIntroduction()}</td>
                                                    <td>${myTeam.getExist_num()}</td>
                                                    <td>${myTeam.getStart_time()}</td>
                                                </tr>
                                        </tbody>
                                    </table>
                                    
                                </div>
                            </div>
                    <!--Block-->

                </div>

                <!-- Pagination Outer -->

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
