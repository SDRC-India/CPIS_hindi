<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
	<head>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
	  <title>Role Management</title>
	  <link rel="stylesheet" href="resources/css/style.css">	
	  <!-- Bootstrap css -->
	  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
	  <link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
	 
	  <!-- Font awesome css -->
<!-- 	  <link rel="stylesheet" href="resources/fonts/font-awesome/font-awesome.min.css"> -->
		<spring:url value="/resources/css/style.css" var="styleCss" />
		<link href="${styleCss}" rel="stylesheet" />
		<!-- jquery-ui.css file is not that big so we can afford to load it -->
		<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
			var="jQueryUiCss" />
		<link href="${jQueryUiCss}" rel="stylesheet"></link>
		
	</head>
	

	<body ng-app="userRole" ng-controller="UserRoleController" ng-cloak>
	<jsp:include page="./common/adminHeader.jsp" />
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">Are you sure to create the user?</h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button3id" name="button3id" class="btn btn-info bigbutton" type="submit" ng-click="saveData()" class="close" data-dismiss="modal" aria-hidden="true">SUBMIT</button>
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true">BACK</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>	
	
	<div class="modal fade" id="newUserModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          	<h4 class="modal-title" id="myModalLabel">User added successfully.</h4>
	          	<p style="text-align:center">
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" data-dismiss="modal" aria-hidden="true" ng-click="reloadPage()">Ok</button>
	        </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="box-border box-border-padding">
					<div class="childlist-heading1 borderPersonal">Role Management</div>	
					<form class="form-horizontal basicchildform" name="basicdetail" id="basicdetail">
						<fieldset>
				
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">1. Designation<span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 
							  <select id="designation" name="designation" ng-model="newUser.designationId" class="form-control" ng-change="getArealist()" required>
							      <option value="" disabled selected>Select Designation</option>
							      <option ng-repeat="item in designationList | orderBy:'designationId'" value="{{item.designationId}}">{{item.designationName}}</option>
							    </select>   
							  </div>
							  <div id="producedBeforeCwcerror" class="error-style"></div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">2. Area<span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 
							  <select id="area" ng-model="newUser.areaId" name="area" class="form-control" ng-change="checkCCI()" required>
							      <option value="" disabled selected>Select Area</option>
							      <option ng-repeat="item in selectedAreaList | orderBy:'areaName'" value="{{item.areaId}}">{{item.areaName}}</option>
							    </select>   
							  </div>
							  <div id="producedBeforeCwcerror" class="error-style"></div>
							</div>
							
							<div ng-if="newUser.designationId==10" class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">2.i. CCI<span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 
							  <select id="cci" ng-model="newUser.cciId" name="cci" class="form-control" required>
							      <option value="" disabled selected>Select CCI</option>
							      <option ng-repeat="item in areaCciList | orderBy:'name'" value="{{item.id}}">{{item.name}}</option>
							    </select>   
							  </div>
							  <div id="producedBeforeCwcerror" class="error-style"></div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">3. Name <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input id="fullName" name="fullName" ng-model="newUser.name" required fifty-characters-validation
							  placeholder="Enter full name" class="form-control input-md" type="text" >
							    
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">4. User Name <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input id="userName" name="userName" ng-model="newUser.userName" required 
							  placeholder="Enter user name" class="form-control input-md" type="text" ng-blur="checkUsername()">
<!-- 							   <span ng-show="usernameError" class="error-style">Username already exists</span>   -->
							<div id="usernameError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">5. Password <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 
							    
							     <input type="password" id="password" class="form-control input-md" placeholder="Enter password" name="password" ng-model="newUser.password" required
							      ng-minlength="8" ng-maxlength="20" ng-pattern="/(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z])/"   />
  							 <span class="mandatory_star" ng-show="!basicdetail.password.$error.required && (basicdetail.password.$error.minlength || basicdetail.password.$error.maxlength) && basicdetail.password.$dirty">Passwords must be between 8 and 20 characters.</span>
   						      <span class="mandatory_star" ng-show="!basicdetail.password.$error.required && !basicdetail.password.$error.minlength && !basicdetail.password.$error.maxlength && basicdetail.password.$error.pattern && basicdetail.password.$dirty">Must contain one lower &amp;
   						       uppercase letter, and one non-alpha character (a number or a symbol.)</span>
							  </div>
							</div>
							
							
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">6. Confirm Password <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input onpaste="return false" type="password" id="password_c" class="form-control input-md" placeholder="Enter confirm password" ng-model="confirmPassword"
							  ng-keyup="validatePassword(confirmPassword)" name="password_c" required  />
							    <div id="passwordError" class="error-style"></div>
							  </div>
							</div>
							
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput">7. E-mail Id<span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input id="roleemail" name="roleemail" placeholder="Enter e-mail Id" ng-model="newUser.email" required
							  class="form-control input-md" type="text" 
							    ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateEmail(newUser.email,'mgmtEmailError')" ng-blur="checkEmail()">
							<div id= "mgmtEmailError" class="error-style"><br>
<!-- 							<span ng-show="emailError" class="error-style">Email already registered</span></div> -->
							
							    
							  </div>
							</div>
							
							<div style="text-align:center">
								<button id="button1id" name="button1id"	class="btn btn-info" type="submit" ng-click="saveData()">Submit</button>
							</div>
	
							</fieldset>
					</form>
	        	</div>
			</div>
		</div>

	</div>
<jsp:include page="./common/cctsFooter.jsp" />
	<script type="text/javascript" src="resources/js/jquery-min.js"></script>                      
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/angular.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>	
	<script>
	var app = angular.module('userRole', []);
	var myAppConstructor= angular.module('userRole');
	</script>
	<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
	
	<script src="resources/js/AngularController/userRole.js"></script>
	<script src="resources/js/moment-with-locales.js"></script>
    <script src="resources/js/jquery-ui.js"></script>
   	<script type="text/javascript">
		$(document).ready(function() {
			$("#button3id").on('submit', function(){
				  $('#childIdModal').modal('show');
			});
			
			$('input').blur(function() {
    		    var value = $.trim( $(this).val() );
    		    $(this).val( value );
    		});
			$('textarea').blur(function() {
    		    var value = $.trim( $(this).val() );
    		    $(this).val( value );
    		});
			
		});
	</script>
	
		
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>