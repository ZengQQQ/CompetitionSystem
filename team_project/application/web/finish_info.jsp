<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <h1>Contact</h1>
        </div>
    </section>
    <!-- End Page Title -->

    <!-- Contact One -->
    <section class="contact-one">
        <div class="auto-container">
        
            <div class="row clearfix">
                <!-- Column -->
                <div class="form-column col-lg-6 col-md-12 col-sm-12">
                    <div class="inner-column">
                        <h2>编辑个人信息</h2>

                        <!-- Default Form -->
                        <div class="contact-form">
                            <form method="post" action="http://localhost:8080/_war_exploded/Finish_Info">
                                <p class="form-description">昵称</p>
                                <div class="form-group">
                                    <input type="text" name="UName" maxlength="30" value="${student_info.get("UName")}">
                                </div>

                                <p class="form-description">联系电话</p>
                                <div class="form-group">
                                    <input type="text" name="Tel" maxlength="20" value="${student_info.get("Tel")}">
                                </div>


                                <p class="form-description">竞赛需求信息</p>
                                <div class="form-group">
                                    <textarea name="requirement" rows="10" cols="50" maxlength="500" title="竞赛需求信息">${student_info.get("requirement")}</textarea>
                                </div>
                                <p class="form-description">队员招募要求</p>
                                <div class="form-group">
                                    <textarea name="requirement_leader" rows="10" cols="50" maxlength="500" title="队员招募要求">${student_info.get("requirement_leader")}</textarea>
                                </div>
                                <p class="form-description">特长、强项</p>
                                <div class="form-group">
                                    <textarea name="Strong_point" rows="5" cols="20" maxlength="100" title="特长、强项">${student_info.get("Strong_point")}</textarea>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">
                                        <span class="btn-box"><span class="btn-txt">提交</span><i class="btn-arrow fal fa-arrow-right"></i></span>
                                    </button>
                                    <button type="reset" class="btn btn-secondary">
                                        <span class="btn-box"><span class="btn-txt">重置</span><i class="btn-arrow fal fa-arrow-right"></i></span>
                                    </button>
                                </div>


                            </form>
                        </div>
						<!-- End Default Form -->

                    </div>
                </div>
                <!-- Column -->
                <div class="social-column col-lg-6 col-md-12 col-sm-12">
                    <div class="inner-column">
                        <h2>完善个人信息需要注册</h2>
                        <ul class="social-links">
                            <li><a href="register.jsp">Sign up <span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                            <li><a href="#">There is nothing here <span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                            <li><a href="#">It's true<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                            <li><a href="#">Believe me <span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                        </ul>
                    </div>
                </div>

                <!-- Column -->
            

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
