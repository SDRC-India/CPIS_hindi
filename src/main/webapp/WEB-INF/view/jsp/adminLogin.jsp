<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="resources/js/angular.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/styles.css">


<title>Change Password</title>
</head>
<body style="background:#fff;" ng-app="userChangePassApp" ng-controller="UserChangePassController">
<serror:Error id="msgBoxlogin"  errorList="${formError}"
								cssInfClass="${className}">
								${formError}
<div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <p style="text-align:center">
	          	Password changed successfully.
	          <br>
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">Ok</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>							</serror:Error>
<form class="sign-up" action="/CPIS/checkCredentials" method="post">

   <img class="cpisloginsvgheight" src="resources/img/cpis_ccts_login_screen_Tracker_SVG.svg">

    <h1 class="sign-up-title">Child Case Tracking System</h1>
    <div class="col-md-12">
 
 		  <div class="col-md-5" style="text-align:right;"><level style="line-height:35px;">Username</level></div>
          <div class="col-md-7" style="text-align:left;"><input type="text" id="newPw" name="username" ng-model="username" required 
          placeholder="Enter username" class="form-control input-md sign-up-input" style="background:#dfdfdf;"></div>
        
		  <div class="col-md-5" style="text-align:right;"><level style="line-height:35px;">Password</level></div>
          <div class="col-md-7" style="text-align:left;"><input type="password" id="confirmPw" name="password" ng-model="password" required 
          placeholder="Confirm Password" class="form-control input-md sign-up-input" style="background:#dfdfdf;"></div>

 </div>   
 
    
   <input type="submit" value="LOGIN" class="sign-up-button" >
    
  </form>
  <div style="height:27px"></div>
  <div class="container">
		<div class="row  footer-bg">
			<div class="col-md-6" style="color: #eee;">
				Supported by<a href="#" style="color: #79c8ff;"> &nbsp;UNICEF</a>
			</div>
			<div class="col-md-6" style="color: #eee; text-align: right;">
				Powered by <a href="http://sdrc.co.in" target="_blank"
					style="color: #ffcc00;"> &nbsp;SDRC</a>
			</div>
		</div>
	</div>
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