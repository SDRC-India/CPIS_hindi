<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<html>
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="resources/js/angular.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/styles.css">


<title>Change Password</title>
</head>
<body style="background:#fff;" ng-app="userChangePassApp" ng-controller="UserChangePassController" ng-cloak>
<jsp:include page="./common/adminHeader.jsp" />
<serror:Error id="msgBoxlogin"  errorList="${formError}"
								cssInfClass="${className}">
								${formError}
<%-- <% String email=request.getParameter("email"); %> --%>
<%-- <input type="text" id="xyz" value=<%=request.getParameter("email") %>> --%>
<div class="modal fade" id="changeUserPwModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          	<h4 class="modal-title" id="myModalLabel">{{changePasswordMsg}}</h4>
	          <p style="text-align:center">
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">Ok</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>							</serror:Error>
<form class="sign-up container-fluid" method="post" name="adminpassword" id="adminpassword" >

   <img class="cpisloginsvgheight" src="resources/img/cpis_ccts_login_screen_Tracker_SVG.svg">

    <h1 class="sign-up-title">Child Case Tracking System</h1>
    <div class="col-md-12 widthstyle">
 
  <div >
 			<div class="col-md-4" style="text-align:right;"><level style="line-height:35px;">New Email</level></div>
 			<div class="col-md-6" style="text-align:left;"><input type="text" id="newemail" name="newemail" ng-model="newemail" required 
          		placeholder="Enter new email" class="form-control input-md sign-up-input" style="background:#dfdfdf;" >
   		    </div>
 		</div>
 		  <div class="col-md-4 passwordinMobl" style="text-align:right;"><level style="line-height:35px;">New Password <span class="mandatory_star">&#42;</span></level></div>
          <div class="col-md-6"><input type="password" id="newPw" name="newPw" ng-model="newpassword" required ng-pattern="/(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z])/" 
          placeholder="Enter new Password" ng-minlength="8" ng-maxlength="20" class="form-control input-md sign-up-input" style="background:#dfdfdf;">
          <span class="mandatory_star" ng-show="!adminpassword.newPw.$error.required && (adminpassword.newPw.$error.minlength || adminpassword.newPw.$error.maxlength) && adminpassword.newPw.$dirty">Passwords must be between 8 and 20 characters.</span>
   		 <span class="mandatory_star" ng-show="!adminpassword.newPw.$error.required && !adminpassword.newPw.$error.minlength && !adminpassword.newPw.$error.maxlength && adminpassword.newPw.$error.pattern && adminpassword.newPw.$dirty">Must contain one lower &amp;
   						       uppercase letter, and one non-alpha character (a number or a symbol.)</span></div>
        
		  <div class="col-md-4 passwordinMobl" style="text-align: right;">
    	<level style="line-height:35px;">Confirm Password <span class="mandatory_star">&#42;</span></level></div>
          <div class="col-md-6" ><input type="password" id="confirmPw" name="confirmPw" ng-model="reenteredpassword" required 
           ng-keyup="validatePassword(reenteredpassword)" 
          placeholder="Confirm Password" class="form-control input-md sign-up-input" style="background:#dfdfdf;">
           <div id="passwordError" class="error-style"></div>
          </div>
 </div>   
 
    
    <input type="submit" value="SUBMIT" class="sign-up-button" ng-click="changeUserPw()">
    
  </form>
  
  <div style="height:27px"></div>
  <jsp:include page="./common/cctsFooter.jsp" />
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-animate.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-aria.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-messages.min.js"></script>

  <!-- Angular Material Library -->
  <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.js"></script>
  <script src="resources/js/AngularController/user-change-password-controller.js"></script>
<!--    <script> -->
<!-- 			var app = angular.module('changePassword',[]); -->
<!--  			var myAppConstructor= angular.module('changePassword'); -->
<!-- 		</script> -->
  </body>
</html>