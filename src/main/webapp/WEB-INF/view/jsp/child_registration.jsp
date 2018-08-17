<!DOCTYPE html>
<%@page import="org.sdrc.cpis.util.Constants"%>
<%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
	  <title>REPORT TO BE SUBMITTED AT TIME OF PRODUCTION
OF CHILD BEFORE THE COMMITTEE - FORM 17</title>
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
	<body ng-app="childBasic" ng-controller="BasicForm17" id="cncpReg" ng-cloak>
		
	<jsp:include page="./common/cctsHeader.jsp" />
	<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to register the child.</span></h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button3id" name="button3id" class="btn btn-info bigbutton" type="submit" ng-click="saveData()" class="close" data-dismiss="modal" aria-hidden="true">{{'Submit' | translate}}</button>
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true">{{'Back' | translate}}</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="childIdModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <h4 class="modal-title" id="myModalLabel">
	          	<span translate>The child has been registered successfully.</span><br>
	          	<span translate>The system generated child id is </span>:{{childId}}</h4>
	          <p style="text-align:center">
			  <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="printSubmitData()">{{'Print' | translate}}</button>
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">{{'OK' | translate}}</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="noChildSelected" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign">Please select a child to proceed further.</h4>
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>
				<%
				UserDetailModel user = null;
				
				user = (UserDetailModel)request.getSession().getAttribute(Constants.USER_PRINCIPAL);
				
// 				out.println(user.getUserId());
				Integer userId=user.getUserId();
				String username=user.getUserName();
				Integer areaId=user.getAreaId();
				%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				
				<div class="box-border box-border-padding">
					<!-- <hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr> -->				 
					<div class="childlist-heading1 borderPersonal"><span translate> Report to be submitted at time of production of child before the committee.</span><br>FORM 17<br>[Rules 18(2), 19(25)]</div>	
					<form class="form-horizontal basicchildform" name="basicdetail" id="basicdetail">
						<fieldset>
						<div class="grey-header marginTop"
								style="border-top: none; margin-top: 4px;"> <span translate>Social Background Report</span></div>
			<input type="hidden" id="userValue" value = "<%=userId%>" >
			<input type="hidden" id="areaValue" value = "<%=areaId%>" >
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"> <span translate>1. Case No.</span> <span class="mandatory_star">&#42;</span></label>  
						  <div class="col-md-7">
						  <input id="caseno" name="caseno"  placeholder="{{'Enter case no.'| translate}}" required
						   class="form-control input-md" type="text" ng-model="formInfo.caseNo" 
					    	oninvalid="this.setCustomValidity('Please enter case no')" maxlength="30" 
						   oninput="setCustomValidity('')">
						  <div id="casenoerror" class="error-style"></div>
						  </div>
						</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"> <span translate>2. Produced before the Child Welfare Committee</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 
							  <select id="producedBeforeCwc" name="producedBeforeCwc" class="form-control" 
							  	oninvalid="this.setCustomValidity('Please enter CWC name')" oninput="setCustomValidity('')" 
							  	ng-model="formInfo.childWelfareCommittee" ng-change="changeChildProducedPlace()" required ng-disabled="cwcDisable">
							      <option value="" disabled selected>Select CWC</option>
							    <option ng-repeat="cwc in cwcList" ng-value="cwc">{{cwc.name}}</option>
							    </select>   
<%-- 							    <input type="text" id="producedBeforeCwc" value = "<%=username%>" ng-model="loginUser.userName" readonly class="form-control"> --%>
							  </div>
							  <div id="producedBeforeCwcerror" class="error-style"></div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>3. Date of production</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepicker" ng-model="formInfo.childProducedDate" readonly class="form-control">
							          <div id="childProducedDateerror" class="error-style"></div>
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
							<div class="form-group box-border-padding">
							<label class="col-md-4 control-label" for="textinput"><span translate>4. Time of production</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-4">
							           <select ng-options="item as item for item in hour" ng-model="hr" required class="form-control"
							           style="width:32%; display:inline;" oninvalid="this.setCustomValidity('Please select hour')"
							           oninput="setCustomValidity('')">
							           		<option value="" disabled selected>HH</option>
							           </select>
							           <select ng-options="item as item for item in min" ng-model="minute" required class="form-control"
							           style="width:32%; display:inline;" oninvalid="this.setCustomValidity('Please select minute')"
							           oninput="setCustomValidity('')">
							           		<option value="" disabled selected>MM</option>
							           </select>
							           <select ng-options="item as item for item in ampm" ng-model="ap" required class="form-control"
							           style="width:32%; display:inline;" oninvalid="this.setCustomValidity('Please select period')"
							           oninput="setCustomValidity('')">
							           		<option value="" disabled selected>AM/PM</option>
							           </select>
							  </div>
							  </div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>5. Place of production</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input id="productionPlace" maxlength="30"
							  placeholder="{{'Enter place of production'| translate }}"
							  oninvalid="this.setCustomValidity('Please enter place of production')" 
							  oninput="setCustomValidity('')" 
							  class="form-control input-md" type="text" 
							  ng-model="formInfo.childProducedPlace" readonly>
							    
							  </div>
							</div>
	
							<div class="grey-header"><span translate>6. Details of person who is producing the child</span></div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" 
							  for="textinput"><span translate>(i) Name of the person</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">
<!-- 							  thirty-characters-validation -->
							  <input maxlength="30" id="personName" name="personName" required placeholder="{{'Enter name of the person'| translate}}" class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter name of the person')" oninput="setCustomValidity('')"
							  	type="text" ng-model="formInfo.personProducingChildName">
<!-- 							  	ng-keyUp="validateName(formInfo.personProducingChildName,'personNameerror')"> -->
							    <div id="personNameerror" class="error-style"></div>
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(ii) Age</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input id="producerAge" placeholder="{{'Enter age'| translate}}" class="form-control input-md" only-two-digits
							  	oninvalid="this.setCustomValidity('Please enter age of the person')" oninput="setCustomValidity('')"
							  	required ng-model="formInfo.personProducingChildAge" ng-pattern="/^[0-9]{1,2}?$/" 
							  	ng-keyUp="validateName(formInfo.personProducingChildAge,'personAgeerror')"
							  	 ng-blur="resetInput(formInfo,'personProducingChildAge','personAgeerror')">
							  <div id="personAgeerror" class="error-style"></div>   
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Sex </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="producerSex" class="form-control" required ng-model="formInfo.personProducingChildSex"
							  	oninvalid="this.setCustomValidity('Please select gender of the person')" oninput="setCustomValidity('')">
							   	<option value="" disabled selected translate>Select Sex</option>
							    <option ng-repeat="sex in sexList" ng-value="sex.id">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							  </select>
							    
							  </div>
							</div>
	
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(iv) Address</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="100" class="form-control" id="producerAddress" required ng-model="formInfo.personProducingChildAddress" placeholder="{{'Enter Address'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter address of the person')" oninput="setCustomValidity('')"></textarea>
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(v) Contact number</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input required only-ten-digits id="contactNumber" name="personProducingChildContactNo"  placeholder="{{'Enter contact number'| translate}}" class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter contact no. of the person')" oninput="setCustomValidity('')" 
							  	ng-model="formInfo.personProducingChildContactNo" 
							  	ng-keyUp="validateName(formInfo.personProducingChildContactNo,'phoneNoError')"
							  	ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  	 ng-blur="resetphnNo(formInfo.personProducingChildContactNo,'contactNumber')">
							    <div id="phoneNoError" class="error-style"></div>
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(vi) Occupation/ designation</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
					    <input maxlength="30" id="occupation" name="personProducingChildOccupation" placeholder="{{'Enter occupation/ designation'| translate}}" class="form-control input-md" type="text" 
					    ng-model="formInfo.personProducingChildOccupation" required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(vii) Type of organization</span></label> 
							  <div class="col-md-7">
							  <select id="cciName" class="form-control" ng-model="formInfo.typeOfOrganization" ng-change="showOrganizationNameField()"
							  	oninvalid="this.setCustomValidity('Please select type of organization')" oninput="setCustomValidity('')">
							      <option value="" disabled selected translate>Select type of organization</option>
							     <option ng-repeat="organization in organizationType" ng-value="organization.id">{{lang=='en'?organization.name:organization.typeNameHindi}}</option>
							    </select>
							  </div>
							</div>
	
							<div class="form-group box-border-padding" ng-if="otherOrganizationFlag">
							  <label class="col-md-4 control-label" for="textinput"><span translate>Please Specify</span></label>
							  <div class="col-md-7">
							  	<input maxlength="30" id="otherOrganization" name="otherOrganizationCCISAAName" placeholder="{{'Enter organization name'| translate}}" class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter organization name')" oninput="setCustomValidity('')"
							  	type="text" ng-model="formInfo.organizationCCISAAName">
							  </div>
							</div>
	
							<div class="grey-header"><span translate>7. The child who is being produced</span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(i) Name (if any)</span></label>  
							  <div class="col-md-7">
<!-- 							  thirty-characters-validation -->
							  <input maxlength="30" id="childName" name="childName" placeholder="{{'Enter Name of the child'| translate}}" 
							  class="form-control input-md" type="text"  
							  ng-model="formInfo.childName">
							     <div id="childNameError" class="error-style"></div>
							  </div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Upload image</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-3">
									<input style="padding-top:7px;" type="file" name="childImage[]" id="js-upload-files" multiple ng-files="getReport($files,'childImage')" required accept=".png, .jpg, .jpeg">
								</div>
								<img id="image" ng-src={{childImage}} alt="No image" height="45" width="45" data-action="zoom" style="margin-left: 40px;">
<!-- 								<img style="margin-left: 100px;" src="resources/img/photo.jpg" height="45" width="45" data-action="zoom" ng-if="!childImage"> -->
							<div id="imageError" class="col-md-3 error-style"></div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Age (stated age/ age based on appearance) </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<select id="childAge" class="form-control" ng-model="formInfo.childAge" required>
							      <option value="" disabled selected translate="">Select age of the child</option>
							      <option ng-repeat="age in ageList | orderBy:'id'" ng-value="age.id">{{age.name}}</option>
							    </select>
							    
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Sex </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 	<select id="childSex" class="form-control" ng-model="formInfo.childSex" required>
							      <option value="" disabled selected translate>Select Sex</option>
							      <option ng-repeat="sex in childSex" ng-value="sex.id">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							    </select>
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(v) Identity mark/s</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input id="identityMark" maxlength="200"
							  	placeholder="{{'Enter identity mark/s of the child'| translate}}"
							  	 class="form-control input-md" type="text" ng-model="formInfo.childIdentityMarks" required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(vi) Aadhaar card number</span></label>  
							  <div class="col-md-7">
							  	<input id="identityMark1" ng-keyUp="validateAdharCard(formInfo.adhaarCardNo,'adharcardError1')"
							  	ng-blur="resetAdharNo(formInfo.adhaarCardNo,'identityMark1','adharcardError1')"
							  	placeholder="{{'Enter Aadhaar card number of the child'| translate}}"  ng-pattern="/^[0-9]{12}$/"
							  	 class="form-control input-md" type="text" ng-model="formInfo.adhaarCardNo" only-twelve-digits>
							  	 <div id="adharcardError1" class="error-style"></div>
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(vii) Language used by the child</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="30" id="language" placeholder="{{'Enter language used by the child'| translate}}"  required
							  	class="form-control input-md" type="text" ng-model="formInfo.childLanguageUsed">
							  </div>
							</div>
	
							<div class="grey-header"><span translate>8. Details of parents / guardian (if available)</span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(i) Name</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="30" id="parentName" required placeholder="{{'Enter parents/guardian name'| translate}}" class="form-control input-md" type="text" ng-model="formInfo.parentName">
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput" type="number"><span translate>(ii) Age</span></label>  
							  <div class="col-md-7">
							  	<input id="parentAge" only-two-digits placeholder="{{'Enter age of the parents/guardian'| translate}}"
							  	oninvalid="this.setCustomValidity('Please enter age of the parent')" oninput="setCustomValidity('')"
							  	class="form-control input-md" type="text" ng-model="formInfo.parentAge" ng-pattern="/^[0-9]{1,2}?$/"
							  	ng-keyUp="validateName(formInfo.parentAge,'parentAgeerror')" ng-blur="resetInput(formInfo,'parentAge','parentAgeerror')">
							  	<div id="parentAgeerror" class="error-style"></div>  
							  </div>
							</div>
	
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(iii) Address</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="100" class="form-control" required id="parentAddress" ng-model="formInfo.parentAddress" placeholder="{{'Enter address of the parents/guardian'| translate}}" ></textarea>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Contact number</span></label> 
							  <div class="col-md-7">
							  	<input only-ten-digits id="parentContactNo" placeholder="{{'Enter contact number of the parents/guardian'| translate}}" class="form-control input-md" ng-model="formInfo.parentContactNo"
							  	ng-keyUp="validateName(formInfo.parentContactNo,'phoneNoError2')">
							  	<div id="phoneNoError2" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(v) Occupation</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="30" id="parentOccupation" placeholder="{{'Enter occupation of the parents/guardian'| translate}}" class="form-control input-md" type="text" ng-model="formInfo.parentOccupation">
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>9. Place where the child was found</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="30" id="childFound" required placeholder="{{'Enter the place where the child was found'| translate}}" class="form-control input-md" type="text" ng-model="formInfo.childFoundPlace">
							  </div>
							</div>
							
							<div class="grey-header"><span translate>10. The details of the person (if any) with whom the child was found</span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(i) Name</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="30" id="withWhomChildFoundName" name="withWhomChildFoundName" placeholder="{{'Enter name of the person'| translate}}" class="form-control input-md" type="text" ng-model="formInfo.withWhomChildFoundName" required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput" type="number"><span translate>(ii) Age</span></label>  
							  <div class="col-md-7">
							  	<input id="withWhomChildFoundAge" only-two-digits placeholder="{{'Enter age of the person'| translate}}"
							  	oninvalid="this.setCustomValidity('Please enter age of the person')" oninput="setCustomValidity('')"
							  	class="form-control input-md" type="text" ng-model="formInfo.withWhomChildFoundAge" ng-pattern="/^[0-9]{1,2}?$/"
							  	ng-keyUp="validateName(formInfo.withWhomChildFoundAge,'withWhomChildFounderror')" ng-blur="resetInput(formInfo,'withWhomChildFoundAge','withWhomChildFounderror')">
							  	<div id="withWhomChildFounderror" class="error-style"></div>  
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Address</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<textarea maxlength="100" class="form-control" id="withWhomChildFoundAddress" name="withWhomChildFoundAddress" ng-model="formInfo.withWhomChildFoundAddress" placeholder="{{'Enter address of the person'| translate}}" required></textarea>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Contact number</span></label>  
							  <div class="col-md-7">
							  	<input only-ten-digits 
							  	id="withWhomChildFoundContactNo" name="withWhomChildFoundContactNo" 
							  	placeholder="{{'Enter contact number'| translate}}" class="form-control input-md" 
							  	type="text" ng-model="formInfo.withWhomChildFoundContactNo"
							  	ng-keyUp="validateName(formInfo.withWhomChildFoundContactNo,'phoneNoError3')" >
							  	<div id="phoneNoError3" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(v) Occupation</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="30" id="withWhomChildFoundOccupation" name="withWhomChildFoundOccupation" placeholder="{{'Enter occupation'| translate}}" class="form-control input-md" type="text" ng-model="formInfo.withWhomChildFoundOccupation" required>
							  </div>
							</div>
							</br>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>11. Circumstances under which the child was found</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input id="childCircumstancesWhenFound;" maxlength="200" 
							  	name="childCircumstancesWhenFound;" 
							  	placeholder="{{'Enter under which circumstances the child was found'| translate}}" 
							  	class="form-control input-md" type="text"
							  	 ng-model="formInfo.childCircumstancesWhenFound;"
							  	 required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>12. Allegation by the child of any offence/abuse committed on the child in any manner</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input id="allegationByChild" maxlength="200"
							  	name="allegationByChild" placeholder="{{'Enter allegation by the child of any offence/abuse'|translate}}" 
							  	class="form-control input-md" type="text" ng-model="formInfo.allegationByChild;" required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>13. Physical condition of the child</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input id="physicalConditionOfChild" maxlength="200"
							  	 name="physicalConditionOfChild" placeholder="{{'Enter physical condition of the child'|translate}}" 
							  	 class="form-control input-md" type="text" ng-model="formInfo.physicalConditionOfChild;" required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>14. Belongings of the child at the time of production</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="200" id="childBelongings" name="childBelongings" 
							  	placeholder="{{'Enter belongings of the child at the time of production'|translate}}" 
							  	class="form-control input-md" type="text" 
							  	ng-model="formInfo.childBelongings;" required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>15. Date at which the child came to the CCI/SAA</span></label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
								 <input type="text" id="datepicker2" ng-model="formInfo.childCameToCCIDate" 
								 readonly class="form-control">
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							  </div>
							  
							  <div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>16. Time at which the child came to the CCI/SAA</span></label>  
							  <div class="col-md-4 col-sm-6 col-xs-6">
								  <select  ng-options="item as item for item in hour" ng-model="hr2" class="form-control" style="width:32%; display:inline;">
								  		<option value="" disabled selected>HH</option>
								  </select>
								  <select  ng-options="item as item for item in min" ng-model="minute2" class="form-control" style="width:32%; display:inline;">
								  		<option value="" disabled selected>MM</option>
								  </select>
								  <select  ng-options="item as item for item in ampm" ng-model="ap2" class="form-control " style="width:32%; display:inline;">
								  		<option value="" disabled selected>AM/PM</option>
								  </select>
							  </div>
							   <div class="col-md-2 col-sm-6 col-xs-6">
								<button id="resetbtn" name="resetbtn" class="btn otherbut" type="button" ng-click="resetTime()"><span translate>RESET</span></button>
							</div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"></label>  
							  <div class="col-md-4">
							<div id="timeError" class="error-style"></div>
							</div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>17. Immediate efforts made to trace family of the child</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="200" id="immediateEffortsToTraceFamily" name="immediateEffortsToTraceFamily" 
							  	placeholder="{{'Enter immediate efforts made to trace family of the child'|translate}}" 
							  	class="form-control input-md" type="text" ng-model="formInfo.immediateEffortsToTraceFamily;" required>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>18. Medical treatment, if provided to the child</span></label>  
							  <div class="col-md-7">
							  	<input maxlength="200" id="medicalTreatment" name="medicalTreatment" placeholder="{{'Enter medical treatment, if provided to the child'|translate}}" class="form-control input-md" type="text" ng-model="formInfo.medicalTreatment;">
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>19. Whether police has been informed</span></label>
							  <div class="col-md-7">
							 	<label class="radio-inline" for="radios-0">
							      <input name="radios" id="radios-0" value="Yes" type="radio" ng-model="formInfo.policeInformed">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-0">
							      <input name="radios" id="radios-0" value="No" checked="checked" type="radio" ng-model="formInfo.policeInformed">
							     <span translate> No</span>
							    </label> 
							  </div>
							</div>
							
							<div style="text-align:center">
								<button id="button1id" name="button1id" ng-click = "basicdetail.$invalid ? '' : validateForm()"
								class="btn btn-info" type="submit"><span translate>Submit</span></button>
<!-- 								<button id="button2id" name="button2id" class="btn otherbut" type="reset" >RESET</button> -->
							</div>
							<a href="#" class="back-to-top" style="display: inline;">
								<i class="fa fa-arrow-circle-up"></i>
							</a>
							</fieldset>
					</form>
	        	</div>
			</div>
		</div>
		<div class="modal fade" id="errorImageModal" tabindex="-1"	role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p style="text-align: center">
							Uploaded file is not in correct format.<br>
							<button id="button5id" name="button5id" class="btn btn-info"
								type="submit" class="close" data-dismiss="modal"
								aria-hidden="true">Ok</button>
						</p>
					</div>
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
	var app = angular.module('childBasic', ['gettext']);
	var myAppConstructor = angular.module('childBasic');
	/* Translation  */
	myAppConstructor.run(function (gettextCatalog) {
			//gettextCatalog.debug = true;
			gettextCatalog.setCurrentLanguage('hi_IN');
			console.log(gettextCatalog)    
	});
	</script>
	<script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script>

	<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
	<script src="resources/js/AngularController/child_basic.js"></script>

	<!-- 				<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
	<script src="resources/js/moment-with-locales.js"></script>
    <script src="resources/js/jquery-ui.js"></script>
   	<script type="text/javascript">
		$(document).ready(function() {
			$("#button3id").on('submit', function(){
				  $('#childIdModal').modal('show');
			});
			$( "#datepicker" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true });
			$( "#datepicker2" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});
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
	<script>
		jQuery(document).ready(function(){
			jQuery('.back-to-top').fadeOut();
			var offset = 250;
			var duration = 500;
			jQuery(window).scroll(function(){
				if (jQuery(this).scrollTop() > offset) {
					jQuery('.back-to-top').fadeIn(duration);
				}else{
					jQuery('.back-to-top').fadeOut(duration);
				}
			});
			jQuery('.back-to-top').click(function(event) {
				event.preventDefault();
				jQuery('html, body').animate({scrollTop: 0}, duration);
				return false;
			});
		});
	</script>
	<script type="text/javascript">
var tempUser=document.getElementById('userValue').value;
console.log(tempUser);
// angular.element("#cncpReg").scope().$apply(function() {
// 	angular.element("#cncpReg").scope().loginUser=tempUser;
// 	});
	</script>
	
		
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
	<style>
			.form-horizontal .control-label{
			text-align: left !important;
		}
	</style>
</body>
</html>