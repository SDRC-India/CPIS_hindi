<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<html>
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="resources/js/jquery-min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/angular.min.js"></script>
 <link rel="stylesheet" href="resources/css/customLoader.css">
<script src="resources/js/AngularController/login_controller.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/styles.css">


<title>CCTS Login</title>
</head>
<body style="background:#fff;" ng-app="cctsLogin" ng-controller="LoginController">
<!-- spinner -->
	<div id="spinner" class="loader" style="display: none;"></div>
	<div id="loader-mask" class="loader" style="display: none;"></div>
	<!-- /spinner -->
<serror:Error id="msgBoxlogin"  errorList="${formError}"
								cssInfClass="${className}">
								${formError} 
							</serror:Error>
<input type="hidden" id="loginStatus" value="${formError}">
<input type="hidden" id="userId" value="${userId}">

<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">Another user is already logged in.Please logout from other devices to continue.</h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="logoutUser()">Logout</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
<form class="sign-up sign-up-ccts" action="checkCredentials" method="post">

   <img class="cpisloginsvgheight cpisloginsvgheight-cctslogin" src="resources/img/cpis_ccts_login_screen_Tracker_SVG.svg">

    <h1 class="sign-up-title">Child Case Tracking System</h1>
    
          <select class="sign-up-input" id="role" autofocus ng-model="selectedDesignation" ng-change="getArealist()" required>
          <option value="" disabled selected>Select Designation</option>
    <option ng-repeat="designation in designationList  | orderBy:'designationId'" ng-value="designation">{{designation.designationName}}</option>

  </select>

    <select class="sign-up-input" id="area" ng-model="selectedArea" ng-change="getUserlist()" required>
    <option value="" disabled selected>Select Area</option>
    
    <option ng-repeat="area in selectedAreaList | orderBy:'areaName'" ng-value="area">{{area.areaName}}</option>
  </select>
    
    <select class="sign-up-input" id="Username" ng-model="selectedUser" name="username" required="" oninvalid="this.setCustomValidity('Please Enter User Name')" oninput="setCustomValidity('')">
    <option value="" disabled selected>Select Username</option>
    
    <option ng-repeat="user in filteredUserList" >{{user.userName}}</option>
  </select>
    
    <input id="passPointer" type="password" name="password" class="sign-up-input" 
    ng-model="password" placeholder="Enter Password" 
    required="" oninvalid="this.setCustomValidity('Please Enter Password')" 
    oninput="setCustomValidity('')" style="background:#dfdfdf;">
    
    
    <div>
        <div style="float:left" class="col-sm-6 caps-style" id="caps" ng-if="capsOn"><b>Caps Lock is on</b></div>
        <div class="col-sm-6" ng-if="!capsOn" style="color:#fff;">-</div>
        <label style="padding-right: 0px !important" class="col-sm-6"><a style="float: right" href='forgot_password'>Forgot Password?</a></label>
        </div>
    <input type="submit" value="LOGIN" class="sign-up-button sign-up-button-ccts" >
    
  </form>
  <jsp:include page="./common/cctsFooter.jsp" />
  </body>
</html>