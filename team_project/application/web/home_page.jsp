<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--前端嵌入java来获取sessiion中的值--%>
<% HttpSession httpSession=request.getSession();
    String id=(String) httpSession.getAttribute("Student_ID");
    String UName=(String) httpSession.getAttribute("UName");
%>
<html>
<head>
<meta charset="utf-8">
<title>Archway HTML Template - Home Fullscreen</title>
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
                                <ul class="navigation clearfix">
                                    <li style="background-color: black;width:350px" ><a href="#"><div id="showPlace"style="font-size:20px " ></div></a></li>
                                    <script>
                                        // JavaScript获取Java代码块中的值
                                        let id = <%=id%>;
                                        let UName='<%=UName%>';
                                        let ele = document.getElementById('showPlace');
                                        if(UName=='NAN'||UName==null||UName=='') {
                                            ele.innerHTML = "用户 " + id + ", 欢迎访问!";
                                        }
                                        else {
                                            ele.innerHTML= "用户 " + UName + ", 欢迎访问!";
                                        }
                                    </script>
									<li class="dropdown"><a href="#">管理个人信息</a>
                                        <ul>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/return_student_info">完善个人信息</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown"><a href="#">组队</a>
                                        <ul>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/showStudentByPage">查找用户</a></li>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/showTeamByPage">寻找队伍</a></li>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/checkLeader">创建队伍</a></li>
                                            <li class="dropdown"><a href="#">团队信息</a>
                                                <ul>
                                                    <li><a href="${pageContext.request.contextPath}/showMyTeam_Student">已加入队伍</a></li>
                                                    <li><a class="menu-link" href="${pageContext.request.contextPath}/checkTeam">我的队伍</a></li>
                                                </ul>
                                            </li>
                                            
                                        </ul>
                                    </li>
                                    <li class="dropdown"><a href="#">消息邮箱 </a>
                                        <ul>
                        
                                            <li>
                                                <a class="menu-link" href="${pageContext.request.contextPath}/showInvitation">收到邀请</a>
                                            </li>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/checkReply">收到通知</a></li>
                                            <li class="dropdown"><a href="#">比赛信息</a>
                                                <ul>
                                                    <li><a href="class-a.html">Class a</a></li>
                                                </ul>
                                            </li>
                                        
                                            
                                            
                                        </ul>
                                    </li>
                                </ul>
                            </nav>
                            <!-- Main Menu End-->
                        </div>
                        <!--Nav Outer End-->

                        <div class="links-box clearfix">
                            <div class="link contact-link">
                                <a href="login.jsp" class="theme-btn contact-btn"><span class="btn-box"><span class="btn-txt">Login/sign up</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
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

    <!-- Hidden Navigation Bar -->
    
    <!-- / Hidden Bar -->


    <!-- Banner Section -->
      <section class="banner-full">

          <div class="banner-full-carousel owl-theme owl-carousel">
              <!--Slide Item-->
              <div class="slide-item">
                  <div class="image-layer" style="background-image: url(images/main-slider/slider-2.jpg);"></div>
                  <div class="content-box">
                      <div class="cat"><span>金融</span></div>
                      <div class="slide-num"><span class="num active-num">01</span><span class="num total-num">03</span></div>
                      <div class="content">
                          <div class="inner">
                              <h1><span class="outlined">财富</span> 体会运筹帷幄</h1>
                          </div>
                      </div>
                  </div>
              </div>
              <!--Slide Item-->
              <div class="slide-item">
                  <div class="image-layer" style="background-image: url(images/main-slider/slider-3.jpg);"></div>
                  <div class="content-box">
                      <div class="cat"><span>网安</span></div>
                      <div class="slide-num"><span class="num active-num">02</span><span class="num total-num">03</span></div>
                      <div class="content">
                          <div class="inner">
                              <h1><span class="outlined">智斗</span>感悟斗智斗勇</h1>
                          </div>
                      </div>
                  </div>
              </div>
              <!--Slide Item-->
              <div class="slide-item">
                  <div class="image-layer" style="background-image: url(images/main-slider/slider-4.jpg);"></div>
                  <div class="content-box">
                      <div class="cat"><span>机械</span></div>
                      <div class="slide-num"><span class="num active-num">03</span><span class="num total-num">03</span></div>
                      <div class="content">
                          <div class="inner">
                              <h1><span class="outlined">钻研</span>实现超越自我</h1>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </section>
    <!--End Banner Section -->

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
