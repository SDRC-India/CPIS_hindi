<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<html>
<head>
	<title>VSL Admin | Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<style>
	html, body
{
    height: 100%;
}

		#home{
			 color: #16a085 !important;
		}
	
	</style>
</head>
<body ng-app="userChangePassApp" ng-controller="UserChangePassController">
		
				<serror:Error id="msgBox"  errorList="${formError}"
								cssInfClass="${className}">
							</serror:Error>			
	<div class="alert alert-warning text-center" id="nomatch">
  <strong>New password in both input field do not match</strong>
</div>
<div class="alert alert-warning text-center" id="min8">
  <strong>Password length must be at least 8 characters</strong>
</div>
<div class="alert alert-success text-center" id="change-success">
  <strong>Password changed successfully</strong>
</div>
<div class="alert alert-info text-center" id="change-failure">
  <strong>Some server error occured while changing password</strong>
</div>
		<form id="change-pass" name="changepassForm" ng-submit="submit()" method="post" class="text-center">
			
		    <h1>Provide your new Password</h1>
		  
		          
			        <md-input-container flex="50" style="max-width: 100%; width: 100%;" class="text-left">
			          <label>Enter New Password</label>
			          <input required type="password" name="newpassword" ng-model="newpassword" style="width: 100%;">
			         
			        </md-input-container>
			        <md-input-container flex="50" style="max-width: 100%; width: 100%;" class="text-left">
			          <label>Reenter New Password</label>
			          <input required type="password" name="reenteredpassword" ng-model="reenteredpassword" ng-invalid="true" style="width: 100%;">
			        
			        </md-input-container>
			        <md-button type="submit" style="background-color: #415684; color: #FFF;">Change Password</md-button>
		</form>
 	
 	
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-animate.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-aria.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-messages.min.js"></script>

  <!-- Angular Material Library -->
  <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.js"></script>
  <script src="resources/js/user-change-password-controller.js"></script>	
  			<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script>
</body>
</html>