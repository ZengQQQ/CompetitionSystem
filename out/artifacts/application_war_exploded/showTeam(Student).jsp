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
            <h2>队伍广场，找寻你中意的队伍</h2>
            <h1>查找队伍</h1>
        </div>
    </section>
    <!-- End Page Title -->

    <!-- Blog One -->
    <section class="blog-one">
        <div class="auto-container">
            <div class="inner-container">
                
                

                <div class="row clearfix">
                    <!--Block-->
                        <h2>查看自己的队友:</h2>
                        <div class="container" ng-app="winnieApp" ng-controller="winnieCtrl">
                            <h4>队伍信息：</h4>
                            <div class="box"><div class="container push-left d-flex justify-content-center align-items-center" ng-app="winnieApp" ng-controller="winnieCtrl">
                                
                                <table class="table table-striped">
                                    <thead>
                                        <th>队伍名</th>
                                        <th>队长学号</th>
                                        <th>队伍简介</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </thead>
                                    <tbody style="font-size: 18px">
                                        <c:forEach items="${team_list}" var="team">
                                        <tr ng-repeat="data in information | filter:search">
                                            <td>${team.getTName()}</td>
                                            <td>${team.getUID_leader()}</td>
                                            <td>${team.getIntroduction()}</td>
                                            <td>${team.getStart_time()}</td>
                                            <td>
                                                <a href="javascript:teammate(${team.getUID_leader()})" >查看队友</a> &nbsp;
                                                <a href="javascript:tip('${team.getUID_leader()}','${team.getTName()}')" >退出队伍</a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                        
                            </div>
                            
                    <!--Block-->

                </div>

                <!-- Pagination Outer -->

            </div>
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
<script>
    function tip(leader_id, name) {
        if (confirm('确认退出' + name + '吗?')) {
            window.location.href = '${pageContext.request.contextPath}/quitTeam?leader_id=' + leader_id;
        } else {
            return false;
        }
    }

    function teammate(leader_id) {
        window.location.href = '${pageContext.request.contextPath}/showTeammate?leader_id=' + leader_id;
    }
</script>
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
