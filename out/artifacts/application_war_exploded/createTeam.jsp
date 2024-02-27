<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Archway HTML Template - Contact Us</title>
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
            <h2>get in touch</h2>
            <h1>signup</h1>
        </div>
    </section>
    <!-- End Page Title -->

    <!-- Contact One -->
    <section class="contact-one">
        <div class="auto-container">
        
            <div class="row clearfix">
                <!-- Column -->
                <div class="form-column col-lg-12 col-md-12 col-sm-12" style="text-align: center">
                    <div class="inner-column">
                        <h2>创建我的队伍:</h2>

                        <!-- Default Form -->
						<div class="contact-form">
							<form method="post" action="${pageContext.request.contextPath}/createTeam" id="contact-form">
                                <div class="form-group">
                                    队伍名称<input type="text" name="TName" maxlength="30" placeholder="输入队伍名称" required="">
                                </div>
								<div class="form-group">
                                   队伍简介 <input type="text" name="Introduction" maxlength="50" placeholder="输入队伍信息，如项目简介等" required="">
                                </div>
		                        <div class="form-group">
                                    队伍容量<input type="number" name="Max_Num"  min="1">
                                </div>
                                <div class="form-group text-right">
                                    <button class="theme-btn btn-style-two">
                                        <span class="btn-box"><span class="btn-txt">创建</span><i class="btn-arrow fal fa-arrow-right"></i></span>
                                    </button>
                                </div>
                                <style>
                                    span.btn-box:hover{
                                        color:rgb(255, 200, 0);
                                        background-color: rgba(255, 174, 0, 0.991);
                                    }
                                </style>
								
							</form>
						</div>
						<!-- End Default Form -->

                    </div>
                </div>

            </div>
        </div>
    </section>
    <!-- End Contact One -->
    
    <!--Subscribe Section-->
    <section class="subscribe-section">
        <div class="bg-layer" style="background-image: url(images/background/pattern-2.jpg);"></div>
        <div class="auto-container">
            <div class="content-box">
                <div class="row clearfix">
                    <div class="text-col col-xl-8 col-lg-12 col-md-12 col-sm-12">
                        <div class="inner">
                            <h2>Subscribe to our newsletter today!</h2>
                            <div class="text">Don't miss out on the latest in architecture and design</div>
                        </div>
                    </div>
                    <div class="form-col col-xl-4 col-lg-12 col-md-12 col-sm-12">
                        <div class="inner">
                            <div class="form-box subscribe-form">
                                <form method="post" action="new_for_manage.html">
                                    <div class="form-group">
                                        <div class="field-inner">
                                            <input type="text" name="massage" value="" placeholder="Say some to us" required>
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

    <!--Main Footer-->

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
<script src="js/validate.js"></script>
<script src="js/custom-script.js"></script>
</body>
</html>
