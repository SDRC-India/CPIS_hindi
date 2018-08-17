 <!DOCTYPE html>

<%@page import="java.util.Base64"%>
<%@page import="org.sdrc.cpis.util.Constants"%>
<%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <link rel="stylesheet" type="text/css" href="resources/css/gridMenu.css">
 <link rel="stylesheet" type="text/css" href="resources/css/font-awesome.min.css">
 <link rel="stylesheet" href="resources/css/customLoader.css">
 <link rel="stylesheet" type="text/css" href="resources/css/style.css">
 <link rel="stylesheet" type="text/css" href="resources/css/styles.css">
 <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
 
 <body>
 <div class="headerFixed">
 <div class="container margntopSpace" ng-cloak>
		<div class="row  header-bg">
			
				<div class="col-md-4" style="margin-top: 14px;">
					<img class="cctsHeaderdesign" src="resources/img/cpis_ccts_menu_staticn_SVG.svg">
					<h4>Child Case Tracking System</h4>
				</div>
				<div class="col-md-3 resizeofUser">
				<%
				UserDetailModel user = null;
				
				user = (UserDetailModel) request.getSession().getAttribute(
						Constants.USER_PRINCIPAL);
				String email=Base64.getEncoder().encodeToString(user.getEmail().getBytes("UTF-8"));
				%>
<%-- 				<input type="hidden" id="email" value=<%=email %>> --%>
				   <ul>
					<li class="welcome">Welcome <span class="username"> <%=user.getUserName()%></span></li>
					</ul></div>
				<div class="col-md-4 text-right" style="padding: 0;">
					<button type="button" class="navbar-toggle menu-toggle" data-toggle="collapse" data-target="#menu" aria-expanded="false">
									<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
								</button>
					<ul class="welcome-user navbar-collapse collapse" id="menu">
					<li class="home"><a href="userList" >Home</a></li>
					<li class="openNavMenu"><a href="role_management" >Add User</a></li>				
					<li class="dashboard"><a href="changeUserPassword">Change Password</a></li>
					<li class="logout logoutview"><a href="logout">Logout</a></li>
					
					</ul>
					

<!-- 					<div>
<!-- 						<img src="resources/img/grid.png" alt="grid" height="25" width="25" style="margin-top:6px; float:right"> 
<!-- 					</div><i class="glyphicon glyphicon-log-out icons" style="background:#333;"></i> -->
					
<!-- 					<div class="col-sm-2 text-right"> -->
<!-- 					<div class="openNavMenu" style="background:#333;"> -->
<!-- 						<i class="fa fa-th fa-lg" aria-hidden="true"></i> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
				</div>
<!-- 				<div class="col-md-1 text-right" style="padding: 0;"> -->
<!-- 				<ul class="logoutWithoutPishpin"> -->
<!-- 				<li class="logout logoutmobview"><a href="logout">Logout</a></li> -->
<!-- 				</ul> -->
<!-- 				</div> -->
				<ul class="nav-list-menu" id="nav-list-menu"> 
<!--          


<%-- 					<li ng-if="menuList.length < 3" ng-repeat="button in menuList" class="pickList col-xs-6 text-center" value={{button.itemId}}><a href={{button.url}}><img class="menuLogo" ng-src="{{button.imagePath}}"  /></a></li> --%>
<!--             	<li ng-if="menuList.length > 2" ng-repeat="button in menuList" class="pickList col-xs-4 text-center" value={{button.itemId}}><a href={{button.url}}><img class="menuLogo" ng-src="{{button.imagePath}}" /></a></li> -->

            	<li ng-repeat="button in menuList" id="{{button.itemId}}" class="pickList col-xs-4 text-center" value="{{button.itemId}}" 
            	ng-init="coverImage = button.imagePath"
            	ng-mouseover="coverImage = button.imagePath2"
            	ng-mouseout="coverImage = button.imagePath"
            	 ng-click="redirectForm(button.url)">
            	 <img class="menuLogo" ng-src="{{coverImage}}" />
            	</li>
<!--             	<li ng-repeat="button in menuList" id="{{button.itemId}}" class="pickList col-xs-4 text-center" value="{{button.itemId}}"  -->
<!--             	 ng-click="redirectForm(button.url)" style = ":hover {background: green;}"> -->
<!--             	<img class="menuLogo" ng-src="{{button.imagePath}}" /></li> -->
<!--             	<li ng-if="menuList.length > 2" ng-repeat="button in menuList" class="pickList col-xs-4 text-center" value={{button.itemId}}><img class="menuLogo" ng-src="{{button.imagePath}}" ng-click="redirectForm(button.url)" /></li> -->
<%--             	<li ng-if="menuList.length <= 2" ng-repeat="button in menuList" class="pickList col-xs-4 text-center" value={{button.itemId}}><img class="menuLogo" ng-src="{{button.imagePath}}" ng-click="redirectForm(button.url)" /></li> --%>
           </ul> 
		</div>
	</div>
	</div>
	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
<!-- 	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script> -->
<!-- 	<script type="text/javascript" src="resources/js/angular.min.js"></script> -->
	<script type="text/javascript">

		$("#menu li a").click(function() {
        $(this).parent().addClass('active').siblings().removeClass('active');
    });
   </script>
   <script type="text/javascript">
   $(document).ready(function(){

   		if(location.pathname.includes("child_registration_view") || location.pathname.includes("interim_order") || location.pathname.includes("child_registration") || location.pathname.includes("socialInvestigation") || location.pathname.includes("caseHistory") || location.pathname.includes("finalOrder") || location.pathname.includes("ciclSocialBackgroundReport") 
   				|| location.pathname.includes("ciclinterimOrder") || location.pathname.includes("ciclCaseMonitoring")){
           	$(".openNavMenu").addClass("active");
           }
   		if(location.pathname == "/CPIS/ccts"){
   			$(".home").addClass("active");
   		}
   		if(location.pathname == "/CPIS/report"){
            $(".report").addClass("active");
        }
   })
   </script>
   <script type="text/javascript">
//    $(window).scroll(function(){
// 	   if($(window).scrollTop() >100 && $(window).width() > 768){
// 		   $(".headerFixed").css("position", "fixed");
// 		   }
// 	   if($(window).scrollTop() <100 && $(window).width() > 768){
// 		   $(".headerFixed").css("position", "relative");
// 		   }
// 	   })
   </script>
   </body>
	<!-- spinner -->
	<div id="spinner" class="loader" style="display: none;"></div>
	<div id="loader-mask" class="loader" style="display: none;"></div>
	<!-- /spinner -->