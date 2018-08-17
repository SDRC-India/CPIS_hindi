<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="resources/js/angular.min.js"></script>
<script src="resources/js/AngularController/login_controller.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/styles.css">
<link rel="stylesheet" type="text/css" href="resources/css/customLoader.css">

<title>Forget Password</title>
</head>
<body style="background:#fff;" ng-app="cctsLogin" ng-controller="LoginController">
<div class="modal fade" id="forgetPasswordModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">Reset password link has been sent to your email address. Please change your password within 30 minutes.</h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="redirect()">Close</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>

<form class="sign-up" method="post" id="example">

   <img class="cpisloginsvgheight" src="resources/img/cpis_ccts_login_screen_Tracker_SVG.svg">

    <h1 class="sign-up-title">Child Case Tracking System</h1>
    <div class="col-md-12" style="margin-bottom:15px;">
 
    <div class="col-md-4" style="text-align:right;"><level style="line-height:35px;">E-mail Id <span class="mandatory_star">&#42;</span></level></div>
    <div class="col-md-8" style="text-align:left;"><input id="email" name="email" ng-model="email" placeholder="Enter registered e-mail Id" class="form-control input-md sign-up-input" style="background:#dfdfdf;" type="text" ng-blur="checkEmail()" required></div>
	
 </div>  
 <div style="text-align:center;">
 <div id= "emailError" class="error-style">
  </div></div>
 <div style="text-align:center;">
 <button id="button1id" name="button1id" class="btn btn-info otherbut text-center" type="submit" ng-click="forgotPassword()">Submit</button>
 </div>
  </form>
<jsp:include page="./common/cctsFooter.jsp" />
<script type="text/javascript" src="resources/js/jquery-min.js"></script>                      
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/angular.min.js"></script>
	
	<!-- spinner -->
	<div id="spinner" class="loader" style="display: none;"></div>
	<div id="loader-mask" class="loader" style="display: none;"></div>
	<!-- /spinner -->
</body>
</html>