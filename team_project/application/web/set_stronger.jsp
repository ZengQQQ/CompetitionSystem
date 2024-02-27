<html>
<head>
<meta charset="utf-8">
<title>Archway HTML Template - Home 08</title>
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
                                <ul class="navigation clearfix">
									<li class="dropdown"><a href="#">管理个人信息</a>
                                        <ul>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/return_student_info">完善个人信息</a></li>
                                            <li class="dropdown"><a href="#">补充信息</a>
                                                <ul>
                                                    <li><a href="set_strength.html">专长</a></li>
                                                    <li><a href="set_need.jsp">需求</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
									<li class="dropdown"><a href="about.jsp">关于我们 <span class="st">what we do</span></a>
                                        <ul>
                                            <li><a href="about.jsp">开发团队介绍</a></li>
                                            <li><a href="contact.html">联系我们</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown"><a href="#">组队<span class="st">to work</span></a>
                                        <ul>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/showStudentByPage">查找用户</a></li>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/showTeamByPage">寻找队伍</a></li>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/checkLeader">创建队伍</a></li>
                                            <li class="dropdown"><a href="#">团队信息</a>
                                                <ul>
                                                    <li><a href="${pageContext.request.contextPath}/showMyTeam_Student">已加入队伍/查看队员</a></li>
                                                    <li><a class="menu-link" href="${pageContext.request.contextPath}/checkTeam">管理队伍</a></li>
                                                </ul>
                                            </li>
                                            
                                        </ul>
                                    </li>
                                    <li class="dropdown"><a href="#">消息邮箱 <span class="st">Don't get lost</span></a>
                                        <ul>
                        
                                            <li>
                                                <a class="menu-link" href="${pageContext.request.contextPath}/showInvitation">收到邀请</a>
                                            </li>
                                            <li><a class="menu-link" href="${pageContext.request.contextPath}/checkReply">回复信息</a></li>
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
    <section class="hidden-bar">
        <!-- Hidden Bar Wrapper -->
        <div class="hidden-bar-wrapper">
            <div class="hidden-bar-closer"><span class="icon"><svg class="icon-close" role="presentation" viewBox="0 0 16 14"><path d="M15 0L1 14m14 0L1 0" stroke="currentColor" fill="none" fill-rule="evenodd"></path></svg></span></div>
            <div class="nav-logo-box">
                <div class="logo"><a href="index.jsp" title="Archway HTML Template"><img src="images/logo-2.png" alt="" title="Archway HTML Template"></a></div>
            </div>
            <!-- .Side-menu -->
            <div class="side-menu">
                 <ul class="navigation clearfix">
                    <li class="current dropdown"><a href="index.jsp">Main</a>
                        <ul>
                            <li><a href="index-5.html">Parallax</a></li>
                            <li class="dropdown"><a href="index-6.html">Interactive</a>
                                <ul>
                                    <li><a href="index-6.html">Slider</a></li>
                                    <li><a href="index-7.html">Metro</a></li>
                                    <li><a href="index-8.html">Minimal Portfolio</a></li
                                    >
                                    <li><a href="index-9.html">Vertical Slider</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a href="index.jsp">Corporate</a>
                                <ul>
                                    <li><a href="index.jsp">Type 01</a></li>
                                    <li><a href="index-2.html">Type 02</a></li>
                                    <li><a href="index-3.html">Type 03</a></li
                                    >
                                    <li><a href="index-4.html">Yellow Skin</a></li
                                    >
                                </ul>
                            </li>
                            <li><a href="index-10.html">Tabbed</a></li>
                            <li><a href="index-11.html">Carousel</a></li>
                            <li><a href="index-12.html">Minimal Menu</a></li>
                            <li><a href="index-13.jsp">Card</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="about.jsp">About</a>
                        <ul>
                            <li><a href="about.jsp">About Us</a></li>
                            <li><a href="contact.html">Contact Us</a></li>
                            <li><a href="services.jsp">Our Services</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="portfolio-1.html">Work</a>
                        <ul>
                            <li><a href="portfolio-1.html">Portfolio Default</a></li>
                            <li><a href="portfolio-2.html">Portfolio Masonry 01</a></li>
                            <li><a href="portfolio-3.html">Portfolio Masonry 02</a></li>
                            <li><a href="portfolio-4.html">Portfolio Grid</a></li>
                            <li class="dropdown"><a href="project-single-1.html">Project Single</a>
                                <ul>
                                    <li><a href="project-single-1.html">Single Project 01</a></li>
                                    <li><a href="project-single-2.html">Single Project 02</a></li>
                                    <li><a href="project-single-3.html">Single Project 03</a></li>
                                    <li><a href="project-single-4.html">Single Project 04</a></li>
                                    <li><a href="project-single-5.html">Single Project 05</a></li>
                                    <li><a href="project-single-6.html">Single Project 06</a></li>
                                    <li><a href="project-single-7.html">Single Project 07</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="blog.html">Blog</a></li>
                </ul>
            </div><!-- /.Side-menu -->
            
            <div class="links-box clearfix">
                <div class="clearfix">
                    <div class="link"><a href="about.jsp" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">About</span></span></a></div>
                    <div class="link"><a href="contact.html" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">Contact</span></span></a></div>
                </div>
            </div>
        
        </div><!-- / Hidden Bar Wrapper -->
    </section>
    <!-- / Hidden Bar -->

    <!-- Project Two -->
	<section class="project-two">
		<div class="inner-container row clearfix">

			<!-- Project Block Two -->
			<div class="project-block_two hpt-offer-1-item-single active">
				<div class="project-block_two-inner">
                    <div class="post-number">01</div>
                    <div class="post-title">Paragon <span> \ &ensp; &ensp; houses</span></div>
					<div class="project-block_two-image">
						<img src="images/resource/tabbed.jpg" alt="" />
						<div class="project-block_two-content">
                            <h1 class="project-block_two-heading"><a href="project-detail.html">Paragon</a></h1>
                            <div class="project-block_two-text">Inspired by the lush, green landscapes of the desert, Oasis is a project that aims to create an urban sanctuary.</div>
							<div class="link-box">
                                <a href="portfolio-1.html" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">All Projects</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
                            </div>
						</div>
					</div>
				</div>
			</div>

			<!-- Project Block Two -->
			<div class="project-block_two hpt-offer-1-item-single">
				<div class="project-block_two-inner">
                    <div class="post-number">02</div>
                    <div class="post-title">Equilibrium <span> \ &ensp; &ensp; halls</span></div>
					<div class="project-block_two-image">
						<img src="images/resource/tabbed.jpg" alt="" />
						<div class="project-block_two-content">
                            <h1 class="project-block_two-heading"><a href="project-detail.html">Equilibrium</a></h1>
                            <div class="project-block_two-text">Inspired by the lush, green landscapes of the desert, Oasis is a project that aims to create an urban sanctuary.</div>
							<div class="link-box">
                                <a href="portfolio-1.html" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">All Projects</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
                            </div>
						</div>
					</div>
				</div>
			</div>

			<!-- Project Block Two -->
			<div class="project-block_two hpt-offer-1-item-single">
				<div class="project-block_two-inner">
                    <div class="post-number">03</div>
                    <div class="post-title">Horizon <span> \ &ensp; &ensp; stadiums</span></div>
					<div class="project-block_two-image">
						<img src="images/resource/tabbed.jpg" alt="" />
						<div class="project-block_two-content">
                            <h1 class="project-block_two-heading"><a href="project-detail.html">Horizon</a></h1>
                            <div class="project-block_two-text">Inspired by the lush, green landscapes of the desert, Oasis is a project that aims to create an urban sanctuary.</div>
							<div class="link-box">
                                <a href="portfolio-1.html" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">All Projects</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
                            </div>
						</div>
					</div>
				</div>
			</div>

			<!-- Project Block Two -->
			<div class="project-block_two hpt-offer-1-item-single">
				<div class="project-block_two-inner">
                    <div class="post-number">04</div>
                    <div class="post-title">Cascade <span> \ &ensp; &ensp; office</span></div>
					<div class="project-block_two-image">
						<img src="images/resource/tabbed.jpg" alt="" />
						<div class="project-block_two-content">
                            <h1 class="project-block_two-heading"><a href="project-detail.html">Cascade</a></h1>
                            <div class="project-block_two-text">Inspired by the lush, green landscapes of the desert, Oasis is a project that aims to create an urban sanctuary.</div>
							<div class="link-box">
                                <a href="portfolio-1.html" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">All Projects</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
                            </div>
						</div>
					</div>
				</div>
			</div>

			<!-- Project Block Two -->
			<div class="project-block_two hpt-offer-1-item-single">
				<div class="project-block_two-inner">
                    <div class="post-number">05</div>
                    <div class="post-title">Zenith <span> \ &ensp; &ensp; buildings</span></div>
					<div class="project-block_two-image">
						<img src="images/resource/tabbed.jpg" alt="" />
						<div class="project-block_two-content">
                            <h1 class="project-block_two-heading"><a href="project-detail.html">Zenith</a></h1>
                            <div class="project-block_two-text">Inspired by the lush, green landscapes of the desert, Oasis is a project that aims to create an urban sanctuary.</div>
							<div class="link-box">
                                <a href="portfolio-1.html" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">All Projects</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
                            </div>
						</div>
					</div>
				</div>
			</div>

			<!-- Project Block Two -->
			<div class="project-block_two hpt-offer-1-item-single">
				<div class="project-block_two-inner">
                    <div class="post-number">06</div>
                    <div class="post-title">Paragon <span> \ &ensp; &ensp; houses</span></div>
					<div class="project-block_two-image">
						<img src="images/resource/tabbed.jpg" alt="" />
						<div class="project-block_two-content">
                            <h1 class="project-block_two-heading"><a href="project-detail.html">Paragon</a></h1>
                            <div class="project-block_two-text">Inspired by the lush, green landscapes of the desert, Oasis is a project that aims to create an urban sanctuary.</div>
							<div class="link-box">
                                <a href="portfolio-1.html" class="theme-btn btn-style-one"><span class="btn-box"><span class="btn-txt">All Projects</span><i class="btn-arrow fal fa-arrow-right"></i></span></a>
                            </div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</section>
	<!-- End Project Two -->

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
