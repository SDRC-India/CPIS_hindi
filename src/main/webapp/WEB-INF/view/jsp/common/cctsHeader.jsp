 <!DOCTYPE html>

<%@page import="org.sdrc.cpis.util.Constants"%>
<%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
 <link rel="stylesheet" type="text/css" href="resources/css/gridMenu.css">
 <link rel="stylesheet" type="text/css" href="resources/css/font-awesome.min.css">
 <link rel="stylesheet" href="resources/css/customLoader.css">
 <link rel="stylesheet" type="text/css" href="resources/css/style.css">
 <link rel="stylesheet" type="text/css" href="resources/css/styles.css">
  <link rel="stylesheet" type="text/css" href="resources/css/notification.css">
 <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
 	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
 <body >
 <div class="modal fade" id="langModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">डाउनलोड करने के बाद हिंदी में पीडीएफ फार्म देखने के लिए, आपको <a href='resource' target='_blank'>Resource</a> से एडोब एक्रोबेट रीडर डीसी और फ़ॉन्ट पैक को इंस्टॉल करना होगा।</h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true">Ok</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
 <div class="headerFixed">
 <div class="container-fluid margntopSpace " ng-cloak>
		<div class="row  header-bg">
			<%
				UserDetailModel user = null;
				Integer userLevel=0;
				Integer designation=0;
				if(request.getSession()!=null)
				user = (UserDetailModel) request.getSession().getAttribute(
						Constants.USER_PRINCIPAL);
				userLevel = user.getAreaLevelId();
				designation = user.getDesignationId();
				
				%>
				<div class="col-md-3" style="margin-top: 14px;">
					<img class="cctsHeaderdesign" src="resources/img/cpis_ccts_menu_staticn_SVG.svg">
					<h4>Child Case Tracking System</h4>
					<ul>
					<li class="welcome">Welcome <span class="username"> <%=user.getName()%></span></li>
					</ul>
				</div>
				<input type="hidden" id="userId" value=<%=user.getUserId()%> >
				<div class="col-md-2 resizeofUser">
				  <%--  <ul>
					<li class="welcome">Welcome <span class="username"> <%=user.getName()%></span></li>
					</ul> --%>
					</div>
				<div class="col-md-7 text-right" style="padding: 0;" ng-show="menuList.length==0">
					<button type="button" class="navbar-toggle menu-toggle" data-toggle="collapse" data-target="#menu" aria-expanded="false">
									<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
								</button>
					<ul class="welcome-user navbar-collapse collapse nomenu" id="menu">
					<li class="home"><a href="ccts" >Home</a></li>
					<li ng-show="<%=userLevel%>!=2" class="openNavMenu"><a href="#" >Menu</a></li>				
					<li class="dashboard"><a href="dashboard">Dashboard</a></li>

<%-- 					<li ng-if="<%=user.getDesignationId()%>==1 || <%=user.getDesignationId()%>==2 || <%=user.getDesignationId()%>==3 || <%=user.getDesignationId()%>==4 || <%=user.getDesignationId()%>==5 || <%=user.getDesignationId()%>==6" --%>
<!-- 					 class="report"><a href="report">Report</a></li> -->
					<li><a href="report">Report</a></li>
					<li><a href="factSheet">Factsheet</a></li>
					<li class="help" ng-if="<%=userLevel%>==2"><a href="reportSummary">Institutional Report</a></li>
					<li class="resources"><a href="resource">Resources</a></li>
					<li class="help" ng-if="<%=user.getDesignationId()%>==5 || <%=user.getDesignationId()%>==6 || <%=user.getDesignationId()%>==10><a href="notification" class="glyphicon glyphicon-envelope"><div class="num" ng-if="notificationCount!=0">{{notificationCount}}</div></a></li>
					<li class="help"><a href="changeUserPassword">Change Password</a></li>
					<li class="help"><a href="#">Help</a></li>
					<li class="logout logoutview"><a href="logout">Logout</a></li>
					
					</ul>
					<ul>
					<li><a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
					<a href="javascript:void(0)" onclick="showLangWarning()" ng-click="changeLanguage('hi_IN')">&#2361;&#2367;&#2344;&#2381;&#2342;&#2368;</a></li>
					</ul>
					</div>
					
					<!--  For no menu-->
					<div class="col-md-7 text-right withMenu" style="padding: 0;" ng-show="menuList.length !=0">
					<button type="button" class="navbar-toggle menu-toggle" data-toggle="collapse" data-target="#menu" aria-expanded="false">
									<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
								</button>
					<ul class="welcome-user navbar-collapse collapse forNoMenu" id="menu"  >
					<li class="home"><a href="ccts" >Home</a></li>
					<li  class="openNavMenu"><a href="#" >Menu</a></li>				
					<li class="dashboard"><a href="dashboard">Dashboard</a></li>

<%-- 					<li ng-if="<%=user.getDesignationId()%>==1 || <%=user.getDesignationId()%>==2 || <%=user.getDesignationId()%>==3 || <%=user.getDesignationId()%>==4 || <%=user.getDesignationId()%>==5 || <%=user.getDesignationId()%>==6" --%>
<!-- 					 class="report"><a href="report">Report</a></li> -->
					<li><a href="report">Report</a></li>
					<li><a href="factSheet">Factsheet</a></li>
<%-- 					<li class="help" ng-if="<%=userLevel%>==2"><a href="reportSummary">Institutional Report</a></li> --%>
					<li class="resource"><a href="resource">Resources</a></li>
					<li class="help" ng-if="<%=user.getDesignationId()%>==5 || <%=user.getDesignationId()%>==6 || <%=user.getDesignationId()%>==10"><a href="notification" class="glyphicon glyphicon-envelope"><div class="num" ng-if="notificationCount!=0">{{notificationCount}}</div></a></li>
					<!-- <li class="help" id="noti_Container">
						<div id="noti_Counter">{{notificationCount}}</div>   SHOW NOTIFICATIONS COUNT.
						<a href="notification" ><div id="noti_Button" class="glyphicon glyphicon-envelope"></div></a>
					</li> -->
					<li class="help"><a href="changeUserPassword">Change Password</a></li>
					<li class="help"><a href="#">Help</a></li>
					<li class="logout logoutview"><a href="logout">Logout</a></li>
					
					</ul>
					<ul class="language" id="language">
					<li><a href="javascript:void(0)" ng-class="{'activeLang': lang == 'en'}" ng-click="changeLanguage('en')">English</a> | 
					<a href="javascript:void(0)" ng-class="{'activeLang': lang == 'hi_IN'}" onclick="showLangWarning()" ng-click="changeLanguage('hi_IN')">&#2361;&#2367;&#2344;&#2381;&#2342;&#2368;</a></li>
					</ul>
					</div>
					

<!-- 					<div>
<!-- 						<img src="resources/img/grid.png" alt="grid" height="25" width="25" style="margin-top:6px; float:right"> 
<!-- 					</div><i class="glyphicon glyphicon-log-out icons" style="background:#333;"></i> -->
					
<!-- 					<div class="col-sm-2 text-right"> -->
<!-- 					<div class="openNavMenu" style="background:#333;"> -->
<!-- 						<i class="fa fa-th fa-lg" aria-hidden="true"></i> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
				</div>
<!-- 				<div class="col-md-1 text-right" style="padding: 0;" > -->
<!-- 				<ul class="logoutWithoutPushpin"> -->
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

<!-- 	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script> -->
<!-- 	<script type="text/javascript" src="resources/js/angular.min.js"></script> -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">			
<script type="text/javascript" src="resources/js/angular.min.js"></script>
<script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script>
	<script type="text/javascript">
		$("#menu li a").click(function() {
        $(this).parent().addClass('active').siblings().removeClass('active');
    });
   </script>
   <script type="text/javascript">
   var userLevel = <%=userLevel%>;
   var designation = <%=designation%>;
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
   		if(location.pathname == "/CPIS/factsheet"){
            $(".factsheet").addClass("active");
        }
   })
   
   function showLangWarning(){
	   $("#langModal").modal("show");
   }
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
   
   <script>
   
</script>
   </body>
	<!-- spinner -->
	<div id="spinner" class="loader" style="display: none;"></div>
	<div id="loader-mask" class="loader" style="display: none;"></div>
	<!-- /spinner -->