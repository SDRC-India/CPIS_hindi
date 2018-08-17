<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<html>
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->

<title>Form for the follow up of the Child restored with his/her
	Family</title>
	<link rel="stylesheet" href="resources/css/style.css">
	<!-- Bootstrap css -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">

<spring:url value="/resources/css/style.css" var="styleCss" />
<link href="${styleCss}" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/styles.css">
<!-- jquery-ui.css file is not that big so we can afford to load it -->
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>

<!-- <script type="text/javascript" src="resources/js/jquery-min.js"></script> -->
<!-- 	<script type="text/javascript" src="resources/js/angular.min.js"></script> -->
<!-- 	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script> -->
</head>
<body ng-app="FollowUp" ng-controller="FollowUpFormController" id="followUpFrom" ng-cloak>
<jsp:include page="./common/cctsHeader.jsp" />
<input type="hidden" id="modelValue" value="${selectedChild }">
<input type="hidden" id="designationBox" value="${designation }">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="box-border box-border-padding">
					<div class="childlist-heading1 borderPersonal">
						<span translate> Form for the follow up of the Child
							restored with his/her Family.</span>
					</div>
					<div class="col-md-3">
				<div class="box-border">
					<div class="panel-heading panelPoinetr">
						<span translate>Follow Up Forms</span>
					</div>
					<div class="panel-group">
						<div class=" panel-default">
							<div class="panel-body">
								<ul>
									<li ng-repeat="item in formList"><span
										class="glyphicons glyphicons-chevron-right"></span><a href="#"
										ng-click="showForm(item)"> {{item.dateOfVisit}}</a></li>
									<li ng-if="editAccess"><span
										class="glyphicon glyphicon-plus"></span> <a href="#"
										ng-click="addNewForm()"><span translate>Add New</span></a></li>
								</ul>
							</div>
						</div>
					</div>
					</div>
					</div>
				
					<form class="form-horizontal followupform" name="followupform"
						id="followupform">
						<fieldset>
							<!-- <div class="grey-header marginTop"
								style="border-top: none; margin-top: 4px;">
								<span translate>Form for the follow up of the Child restored with his/her
	Family</span>
							</div> -->
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"> <span
									translate>1. Date of Visit</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input type="text" id="datepicker" ng-model="formInfo.dateOfVisit" readonly class="form-control">
							          <div id="dateOfVisit" class="error-style"></div>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>2. Periodic Follow up </span></label>  
							  <div class="col-md-7">
							  <select id="periodicFollowUp" class="form-control"  ng-model="formInfo.periodicFollowUp" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('Please select periodic follow up')" oninput="setCustomValidity('')">
							   	<option value=""  disabled selected translate>Select Periodic Follow up</option>
							    <option class="nms" ng-repeat="option in typeList.followType" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							  
							  <input type="text" id="datepicker" ng-model="lang=='en'?formInfo.periodicFollowUp.name:formInfo.periodicFollowUp.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  </div>
							</div>
							
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>3.Availability of the child at the restored place </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7" >
							 	<label class="radio-inline" for="radios-14">
							      <input name="radios14" id="radios-14" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.availabilityOfChild" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-14">
							      <input name="radios14" id="radios-14" ng-change="resetErrOfAvailability()" ng-value=false type="radio" ng-model="formInfo.availabilityOfChild" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="availabiltyError" class="error-style"></div>
							  </div>
							</div>
							 
							
							<div class="form-group" ng-show="formInfo.availabilityOfChild == false">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If no, please specify where is he gone</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="childGoneWhere" ng-change="resetErrOfAvailability()"  ng-model="formInfo.childGoneWhere" placeholder="{{'Enter Address'| translate}}" ng-disabled="!isNewForm"
							    oninvalid="this.setCustomValidity('Please enter address of the person')" oninput="setCustomValidity('')"></textarea>
							      <div id="availabiltyIfNoError" class="error-style"></div>
							  </div>
							 
							</div>
							
							<div class="form-group box-border-padding" ng-show="isNewForm">
							  <label class="col-md-4 control-label" for="textinput"><span translate>4. Type of restoration </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="restorationType" class="form-control" ng-change="resetErrOfAvailability()"  ng-model="formInfo.restorationType" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('Please select a type')" oninput="setCustomValidity('')">
							   	<option value=""  disabled selected translate>Select restoration type</option>
							    <option class="nms" ng-repeat="option in typeList.restorationType" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							  
							  <input type="text" id="datepicker" ng-model="lang=='en'?formInfo.restorationType.name:formInfo.restorationType.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  <div id="typeofRestoreError" class="error-style"></div>
							  </div>
							</div>
							
						
						
							
							<div class="form-group box-border-padding" ng-show="isNewForm">
								<label class="col-md-4 control-label" for="textinput"><span translate>5. Passport size photo of the child</span></label>
								<div class="col-md-3">
									<input style="padding-top:7px;" type="file" name="childPhoto[]" id="js-upload-files" multiple ng-files="getReports($files,'childPhoto')"   accept=".png, .jpg, .jpeg">
								</div>
								<img id="image" ng-src={{childPhoto}} alt="No image" height="45" width="45" data-action="zoom" style="margin-left: 40px;">
							<div id="imageError" class="col-md-3 error-style"></div>
							</div>
							
							<div class="form-group box-border-padding" ng-show="!isNewForm">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>Uploaded image</span><span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<img ng-src={{formInfo.childPhoto}} alt="No image" height="80"
											width="80" data-action="zoom">
									</div>
								</div>
							
							<div class="grey-header"><span translate>6. Details of the child </span></div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(a) Name of the child</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="childName" ng-change="resetErrOfAvailability()"  ng-model="selectedChild.childName" placeholder="{{'Enter name of the child'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter name of the child')" oninput="setCustomValidity('')" disabled></textarea>
							     <div id="nameChildError" class="col-md-3 error-style"></div>
							  </div>
							 
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"> <span
									translate>(b) Date of Birth</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input type="text" id="dateofbirth" ng-model="formInfo.dateOfBirth" readonly class="form-control" ng-disabled="!isNewForm">
							          <div id="dateofbirtherror" class="error-style"></div>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(c) Age </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<select required id="childAge" ng-change="resetErrOfAvailability()" class="form-control" ng-model="selectedChild.age" disabled>
							      <option value="" disabled selected translate="">Select age of the child</option>
							      <option class="nms" ng-repeat="age in typeList.ageList" ng-value="{{age.id}}">{{age.name}}</option>
							    </select>
							    <div id="ageChildError" class="col-md-3 error-style"></div>
							    
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(d) Gender  </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select required id="childSex" class="form-control" ng-change="resetErrOfAvailability()"  ng-model="selectedChild.childSex"
							  	oninvalid="this.setCustomValidity('Please select gender of the person')" oninput="setCustomValidity('')" disabled>
							   	<option value="" disabled selected translate>Select Gender</option>
							    <option class="nms" ng-repeat="sex in typeList.childSex" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							  </select>
							     <div id="genderChildError" class="col-md-3 error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label padding-left-zero" for="textinput"><span translate>(e) Date of restoration</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
								 <input type="text" id="dateOfRestoration" ng-model="selectedChild.dateOfRestoration" 
								 readonly class="form-control" disabled>
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							  </div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(f) Aadhaar card number</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input id="adhaarCardNo" ng-change="resetErrOfAvailability()" ng-keyUp="validateAdharCard(formInfo.adhaarCardNo,'adharcardError1')"
							  	ng-blur="resetAdharNo(formInfo.adhaarCardNo,'identityMark1','adharcardError1')" 
							  	placeholder="{{'Enter Aadhaar card number of the child'| translate}}"  ng-pattern="/^[0-9]{12}$/"
							  	 class="form-control input-md" type="text" ng-model="selectedChild.adhaarNo" only-twelve-digits ng-disabled="!isNewForm">
							  	 <div id="adharcardError1" class="error-style"></div>
							  </div>
							</div>
							
							<div class="grey-header"><span translate>7. Details of Parents </span></div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(a) Name of the Father/ Guardian</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="fatherName" ng-change="resetErrOfAvailability()"  ng-model="formInfo.fatherName" placeholder="{{'Enter Name of the Father/ Guardian'| translate}}"
							    oninvalid="this.setCustomValidity('Please Name of the Father/ Guardian')" oninput="setCustomValidity('')" ng-disabled="!isNewForm"></textarea>
							    <div id="nameFatherError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(b) Name of the Mother / Guardian</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="motherName" ng-change="resetErrOfAvailability()"  ng-model="formInfo.motherName" placeholder="{{'Enter Name of the Mother / Guardian'| translate}}"
							    oninvalid="this.setCustomValidity('Please Name of the Mother / Guardian')" oninput="setCustomValidity('')" ng-disabled="!isNewForm"></textarea>
							    <div id="nameMotherError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(c)Temporary Address</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()"  id="parentTemporaryAddress" ng-model="formInfo.parentTemporaryAddress" placeholder="{{'Enter temporary address'| translate}}" ng-disabled="!isNewForm"></textarea>
							    <div id="addressError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(d) Permanent Address</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()"  id="parentPermanentAddress" ng-model="formInfo.parentPermanentAddress" placeholder="{{'Enter Permanent Address'| translate}}" ng-disabled="!isNewForm"></textarea>
							      <div id="permanentaddressError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(e) Landline number</span></label> 
							  <div class="col-md-7">
							  	<input only-eleven-digits id="parentLandlineNo" placeholder="{{'Enter landline number of the parents/guardian'| translate}}" class="form-control input-md" ng-model="formInfo.parentLandlineNo"
							  	ng-keyUp="validateName(formInfo.parentLandlineNo,'phoneNoError2')" ng-disabled="!isNewForm">
							  	<div id="phoneNoError1" class="error-style" ></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(f) Mobile number</span></label> 
							  <div class="col-md-7">
							  	<input only-ten-digits id="parentMobileNo" ng-change="resetErrOfAvailability()" placeholder="{{'Enter mobile number of the parents/guardian'| translate}}" class="form-control input-md" ng-model="formInfo.parentMobileNo"
							  	ng-keyUp="validateName(formInfo.parentMobileNo,'phoneNoError2')" ng-disabled="!isNewForm">
							  	<div id="phoneNoError2" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(g) Aadhaar Card Number of father/Guardian</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input id="fatherAdhaarCardNo" ng-keyUp="validateAdharCard(formInfo.fatherAdhaarCardNo,'adharcardError2')"
							  	ng-blur="resetAdharNo(formInfo.adhaarCardNo,'identityMark1','adharcardError1')" ng-change="resetErrOfAvailability()" 
							  	placeholder="{{'Enter Aadhaar card number of the father'| translate}}"  ng-pattern="/^[0-9]{12}$/"
							  	 class="form-control input-md" type="text" ng-model="formInfo.fatherAdhaarCardNo" ng-disabled="!isNewForm" only-twelve-digits>
							  	 <div id="adharcardError2" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(h) Aadhaar Card Number of Mother/Guardian</span>
							  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<input id="motherAdhaarCardNo" ng-keyUp="validateAdharCard(formInfo.motherAdhaarCardNo,'adharcardError3')"
							  	ng-blur="resetAdharNo(formInfo.adhaarCardNo,'identityMark1','adharcardError1')" ng-change="resetErrOfAvailability()" 
							  	placeholder="{{'Enter Aadhaar card number of the mother'| translate}}"  ng-pattern="/^[0-9]{12}$/"
							  	 class="form-control input-md" type="text" ng-model="formInfo.motherAdhaarCardNo" ng-disabled="!isNewForm" only-twelve-digits>
							  	 <div id="adharcardError3" class="error-style"></div>
							  </div>
							</div>
							<div id="adharcardError4" class="error-style"></div>
							
							<div class="grey-header"><span translate>8. Key Highlighted Points of Previous Visit: (250 words input capacity) </span></div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>Key Highlighted Points of Previous Visit</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control"   id="keyPoints" ng-model="formInfo.keyPoints" placeholder="{{'Enter Key Points'| translate}}" ng-disabled="!isNewForm"></textarea>
							  </div>
							</div>
							
							<div class="grey-header"><span translate>9. Progress of the Child </span></div>
							
							<div class="grey-header"><span translate>Physical growth of the child </span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(a) Height (cm)</span></label>  
							  <div class="col-md-7">
							  	<input id="height" only-three-digits
							  	placeholder="{{'Enter height of child'| translate}}"  ng-change="calculateBMI(formInfo.childHeight,formInfo.childWeight)"
							  	 class="form-control input-md" type="text" ng-model="formInfo.childHeight" ng-disabled="!isNewForm">
							  	 <div id="heightError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(b) Weight (kg)</span></label>  
							  <div class="col-md-7">
							  	<input id="weight" only-three-digits
							  	placeholder="{{'Enter weight of child'| translate}}"  ng-change="calculateBMI(formInfo.childHeight,formInfo.childWeight)"
							  	 class="form-control input-md" type="text" ng-model="formInfo.childWeight" ng-disabled="!isNewForm">
							  	 <div id="weightError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(c) Body mass index</span></label>  
							  <div class="col-md-7">
							  	<input id="massIndex" 
							  	placeholder="{{'Enter body mass index of child'| translate}}"  ng-pattern="/^[0-9]{12}$/"
							  	 class="form-control input-md" type="text" ng-model="formInfo.bodyMassIndex" ng-disabled=true>
							  	 <div id="massError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(d) How does the child look?  </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="childLook" class="form-control" ng-change="resetErrOfAvailability()"  ng-model="formInfo.childLook" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('How does the child look?')" oninput="setCustomValidity('')">
							   	<option value="" disabled selected translate>Select</option>
							    <option class="nms" ng-repeat="option in typeList.childLook" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							   <div id="childLookError" class="error-style"></div>
							  <input type="text" id="datepicker" ng-model="lang=='en'?formInfo.childLook.name:formInfo.childLook.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  </div>
							</div>
							
							<div class="grey-header"><span translate>Educational status of the child (After getting reference from the school teacher) </span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(a) Child is enrolled in school </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-14">
							      <input name="radios15" id="radios-15" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.childEnrolled" ng-disabled="!isNewForm" required>
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-14">
							      <input name="radios15" id="radios-15" ng-change="resetErrOfAvailability()" ng-value=false type="radio" ng-model="formInfo.childEnrolled" ng-disabled="!isNewForm" required>
							     <span translate> No</span>
							    </label> 
							       <div id="childEnrolledError" class="error-style"></div>
							  </div>
							</div>
							<div ng-show="formInfo.childEnrolled == true">
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"> 
								<span translate>(i) Date of admission in the School</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input type="text" id="dateOfAdmission" ng-model="formInfo.dateOfAdmission" readonly class="form-control" ng-disabled="!isNewForm">
							          <div id="dateOfAdmissionerror" class="error-style"></div>
								</div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(ii) Class</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="classOfStudy" only-two-digits ng-change="resetErrOfAvailability()" ng-model="formInfo.classOfStudy" placeholder="{{'Enter class'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter class')" oninput="setCustomValidity('')" ng-disabled="!isNewForm"></textarea>
							     <div id="classError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(iii) Roll number</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="rollNo" ng-change="resetErrOfAvailability()"  ng-model="formInfo.rollNo" placeholder="{{'Enter roll number'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter roll number')" oninput="setCustomValidity('')" ng-disabled="!isNewForm"></textarea>
							     <div id="rollNumrror" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(iv) Name of school and address</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()" id="schoolAddress" ng-model="formInfo.schoolAddress" placeholder="{{'Enter School Name And Address'| translate}}" ng-disabled="!isNewForm"></textarea>
							     <div id="nameSchoolError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(v) Type of school </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="schoolType" class="form-control" ng-change="resetErrOfAvailability()"  ng-model="formInfo.schoolType" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('Please select Type of school')" oninput="setCustomValidity('')">
							   	<option value="" disabled selected translate>Select Type of school</option>
							    <option class="nms" ng-repeat="option in typeList.schoolType" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							    <div id="typeOfSchoolError" class="error-style"></div>
							  <input type="text" id="datepicker" ng-model="lang=='en'?formInfo.schoolType.name:formInfo.schoolType.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(vi) Performance of Child</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()"  id="childPerformance" ng-model="formInfo.childPerformance" placeholder="{{'Enter Performance of Child'| translate}}" ng-disabled="!isNewForm"></textarea>
							     <div id="performanceError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(vii) Name of Class Teacher / Principal</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="teacherName" ng-change="resetErrOfAvailability()"  ng-model="formInfo.teacherName" placeholder="{{'Enter Name of Class Teacher / Principal'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter Name of Class Teacher / Principal')" oninput="setCustomValidity('')" ng-disabled="!isNewForm"></textarea>
							    <div id="namePrinciplError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(viii)Remarks of Teacher / Principal</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control"   id="remarksOfTeacher" ng-model="formInfo.remarksOfTeacher" placeholder="{{'Enter Remarks of Teacher / Principal'| translate}}" ng-disabled="!isNewForm"></textarea>
							  </div>
							</div>
							
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ix)Availabile Items</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-6">
									<ul class="contents-align">
										<li class="reasonswidth"
											ng-repeat="option in typeList.itemsAvailable | orderBy:'id'""><label
											class="radio-inline" for="radios-13" ng-checked="option"> <input ng-disabled="!isNewForm"
												ng-model="option.checked" ng-change="setitemsAvailableIds(option)" 
												type="checkbox" class="chkbox">&nbsp;&nbsp;{{lang=='en'?option.name:option.typeNameHindi}}
										</label></li>
									</ul>
									 <div id="availabilityChildError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span
									translate>(x)Remarks</span></label>
								<div class="col-md-7">
									<textarea maxlength="2000" class="form-control"  
										id="remarks" ng-model="formInfo.remarks"
										placeholder="{{'Enter Remarks'| translate}}" ng-disabled="!isNewForm"></textarea>
								</div>
							</div>
							</div>
							
							<div class="grey-header"><span translate>Health Status of the child </span></div>
							
								<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(a) Health Card provided </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-0">
							      <input name="radios" id="radios-0" ng-value=true type="radio" ng-change="resetErrOfAvailability()" ng-model="formInfo.healthCardProvided" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-0">
							      <input name="radios" id="radios-0" ng-value=false checked="checked" type="radio" ng-change="resetErrOfAvailability()" ng-model="formInfo.healthCardProvided" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="healthcardError" class="error-style"></div>
							  </div>
							</div>
							<div class="form-group" ng-show="formInfo.healthCardProvided == false">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If No please specify the reason</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()"  id="healthCardProvidedReason" ng-model="formInfo.healthCardProvidedReason" placeholder="{{'Please specify the reason'| translate}}" ng-disabled="!isNewForm"></textarea>
							     <div id="specifyReasonError" class="error-style"></div>
							  </div>
							</div>
							
								<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(b) Routine Check-up </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-1">
							      <input name="radios1" id="radios-1" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.routineCheckUp" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-1">
							      <input name="radios1" id="radios-1" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.routineCheckUp" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							     <div id="routineCheckError" class="error-style"></div>
							  </div>
							</div>
							<div class="form-group" ng-show="formInfo.routineCheckUp == false">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If No please specify the reason</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()" id="routineCheckupTypeReason" ng-model="formInfo.routineCheckupReason" placeholder="{{'Please specify the reason'| translate}}" ng-disabled="!isNewForm"></textarea>
							     <div id="specifyRoutineCheckError" class="error-style"></div>
							  </div>
							</div>
							
								<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(c) Since last visit Illness </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 	<label class="radio-inline" for="radios-2">
							      <input name="radios2" id="radios-2" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.illness" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-2">
							      <input name="radios2" id="radios-2" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.illness" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="lastVisitError" class="error-style"></div>
							  </div>
							</div>
							<div class="form-group" ng-show="formInfo.illness == true">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If Yes please specify the reason</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()" id="remarksOfTeacher" ng-model="formInfo.illnessStatusReason" placeholder="{{'Please specify the reason'| translate}}" ng-disabled="!isNewForm"></textarea>
							     <div id="specifylastVisitError" class="error-style"></div>
							  </div>
							</div>
							
								<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(d) Hospitalized </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 	<label class="radio-inline" for="radios-3">
							      <input name="radios3" id="radios-3" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.hospitalized" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-3">
							      <input name="radios3" id="radios-3" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.hospitalized" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="hospitalizedError" class="error-style"></div>
							  </div>
							</div>
							<div class="form-group" ng-show="formInfo.hospitalized == true">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If Yes please specify the reason</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" ng-change="resetErrOfAvailability()"  id="isHospitalizedReason" ng-model="formInfo.isHospitalizedReason" placeholder="{{'Please specify the reason'| translate}}" ng-disabled="!isNewForm"></textarea>
							    <div id="specifyhospitalizedError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(e) Remark</span></label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control"   id="healthRemark" ng-model="formInfo.healthRemark" placeholder="{{'Please give remark'| translate}}" ng-disabled="!isNewForm"></textarea>
							  </div>
							</div>
							
							<div class="grey-header"><span translate> Intellective status </span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(a) Intellective status of child </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="intellectiveStatus" class="form-control" ng-change="resetErrOfAvailability()"  ng-model="formInfo.intellectiveStatus" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('Please select Intellective status of child')" oninput="setCustomValidity('')">
							   	<option value="" disabled selected translate>Select Intellective status of child</option>
							    <option class="nms" ng-repeat="option in typeList.intellectiveStatus" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							  <div id="intellectivestatusError" class="error-style"></div>
							  <input type="text" id="datepicker" ng-model="lang=='en'?formInfo.intellectiveStatus.name:formInfo.intellectiveStatus.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  </div>
							</div>
							
							<div class="grey-header"><span translate>Skill development</span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(a) Skill development of the child </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-4">
							      <input name="radios4" id="radios-4" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.skillDeveloped" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-4">
							      <input name="radios4" id="radios-4" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.skillDeveloped" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="skillDevelopError" class="error-style"></div>
							  </div>
							</div>
							<div ng-show="formInfo.skillDeveloped == true">
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(i) Name of the Vocational Course</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="courseName" ng-change="resetErrOfAvailability()" ng-model="formInfo.courseName" placeholder="{{'Enter Name of the Vocational Course'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter Name of the Vocational Course')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							     <div id="vocationalCourseError" class="error-style"></div>
							  </div>
							</div>
							
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(ii) Name of Institution</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="instituteName" ng-change="resetErrOfAvailability()" ng-model="formInfo.instituteName" placeholder="{{'Enter Name of Institution'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter Name of Institution')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							     <div id="institutionError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Status of the course </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="courseStatus" class="form-control" ng-change="resetErrOfAvailability()" ng-model="formInfo.courseStatus" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('Please select Status of the course')" oninput="setCustomValidity('')">
							   	<option value=""  disabled selected translate>Select Status of the course</option>
							    <option class="nms" ng-repeat="option in typeList.courseStatus" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							  <div id="statusourseError" class="error-style"></div>
							  <input type="text" id="vocationalProgress" ng-model="lang=='en'?formInfo.courseStatus.name:formInfo.courseStatus.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Progress of the vocational training </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="vocationalProgress" class="form-control" ng-change="resetErrOfAvailability(); progressStatus()" ng-model="formInfo.vocationalProgress" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('Please select Progress of the vocational training')" oninput="setCustomValidity('')">
							   	<option value="" disabled selected translate>Select Progress of the vocational training</option>
							    <option class="nms" ng-repeat="option in typeList.progessOfCourse" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							  <div id="progressError" class="error-style"></div>
							  <input type="text" id="vocationalProgress" ng-model="lang=='en'?formInfo.vocationalProgress.name:formInfo.vocationalProgress.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  </div>
							</div>
							
							<div class="form-group" ng-show="formInfo.vocationalProgress.id==410">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If passed, please specify the status of the employment</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="vocationalProgressStatus" ng-change="resetErrOfAvailability()"  ng-model="formInfo.vocationalProgressStatus" placeholder="{{'Specify the status'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify the status')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							     <div id="specifyPassedError" class="error-style"></div>
							  </div>
							</div>
							</div>
							
							<div class="grey-header"><span translate>Behaviour of the parents with the child</span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(a) Behaviour of the parents with the child</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="parentsBehaviour" class="form-control" ng-change="resetErrOfAvailability()"  ng-model="formInfo.parentsBehaviour" ng-show="isNewForm"
							  	oninvalid="this.setCustomValidity('Please select Behaviour of the parents with the child')" oninput="setCustomValidity('')">
							   	<option value="" disabled selected translate>Select Behaviour of the parents with the child</option>
							    <option class="nms" ng-repeat="option in typeList.parentsBehaviour" ng-value="option">{{lang=='en'?option.name:option.typeNameHindi}}</option>
							  </select>
							  <div id="behaviorparentsError" class="error-style"></div>
							  <input type="text" id="datepicker" ng-model="lang=='en'?formInfo.parentsBehaviour.name:formInfo.parentsBehaviour.typeNameHindi" readonly class="form-control" ng-show="!isNewForm">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(b) Whether Child at any time sexually assaulted or abused? </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-5">
							      <input name="radios5" id="radios-5" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.sexuallyAbused" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-5">
							      <input name="radios5" id="radios-5" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.sexuallyAbused" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							     <div id="sexuallyAssultedError" class="error-style"></div>
							  </div>
							  <label>(Hint: If “Yes” immediate steps should be taken to remove the child and send to a place of safety and support the child with medical and psycho-social therapy)</label>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(c) Whether Child beaten up by the parent?  </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 	<label class="radio-inline" for="radios-6">
							      <input name="radios6" id="radios-6" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.beatenByParents" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-6">
							      <input name="radios6" id="radios-6" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.beatenByParents" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="beatenChildError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group" ng-show="formInfo.beatenByParents == true">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If yes, how frequently is the child being beaten up by the parent during the reporting period</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="beatenByParentsFrequency" ng-change="resetErrOfAvailability()"  ng-model="formInfo.beatenByParentsFrequency" placeholder="{{'Specify the frequency'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							    <div id="specifybeatenChildError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(d) Whether Child do household chores?  </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-7">
							      <input name="radios7" id="radios-7" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.childDoHouseholdChores" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-7">
							      <input name="radios7" id="radios-7" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.childDoHouseholdChores" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="householdChoresError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group" ng-show="formInfo.childDoHouseholdChores == true">
							  <label class="col-md-4 control-label" for="textarea"><span translate>If yes, please specify what type of work</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="typeOfWork" ng-change="resetErrOfAvailability()"  ng-model="formInfo.typeOfWork" placeholder="{{'Specify the type of work'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							     <div id="specifyhouseholdChoresError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="grey-header"><span translate>10. Discussion with Parents regarding the child</span></div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(a) Behaviour of the child towards the parents</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-6">
									<ul class="contents-align">
										<li class="reasonswidth"
											ng-repeat="option in typeList.childsBehaviour"><label
											class="radio-inline" for="radios-8" ng-checked="option"> <input ng-disabled="!isNewForm"
												ng-model="option.checked" ng-change="setChildsBehaviourIds(option)" 
												type="checkbox" class="chkbox">&nbsp;&nbsp;{{lang=='en'?option.name:option.typeNameHindi}}
										</label></li>
									</ul>
									<div id="behaviorChildError" class="error-style"></div>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(b) Compliances for the child by the Govt. officials</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-6">
									<ul class="contents-align">
										<li class="reasonswidth"
											ng-repeat="option in typeList.complianceByGovt"><label
											class="radio-inline" for="radios-9" ng-checked="option"> <input ng-disabled="!isNewForm"
												ng-model="option.checked" ng-change="setComplianceByGovtIds(option)" 
												type="checkbox" class="chkbox">&nbsp;&nbsp;{{lang=='en'?option.name:option.typeNameHindi}}
										</label></li>
									</ul>
							<div class="form-group" ng-if="complianceOthersChecked">
							  <div class="col-md-7">
							    <input type="text" maxlength="2000" class="form-control" id="otherComplianceByGovt"  ng-model="formInfo.otherComplianceByGovt" placeholder="{{'Specify other compliance by government'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							      <div id="otherComplianceByGovtError" class="error-style"></div>
							  </div>
						   </div>
									<div id="behaviorErr" class="error-style"></div>
								</div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(c) The amount of time parent spends with the child(Per day)</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="timeSpentWithParents" ng-change="resetErrOfAvailability()"  ng-model="formInfo.timeSpentWithParents" placeholder="{{'Specify the amount of time parent spends with the child(Per day)'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							      <div id="timeSpendError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>(d) Behaviour of the neighbour towards the child</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="behaviourOfNeighbour" ng-change="resetErrOfAvailability()"  ng-model="formInfo.behaviourOfNeighbour" placeholder="{{'Specify the behaviour of the neighbour towards the child'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							      <div id="behaviourNeighbourError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(e) Does the child share with the parent’s problems that he /she is facing either at home, school in the neighbourhood or emotionally feeling not happy?    </span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-10">
							      <input name="radios10" id="radios-10" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.childShareProblems" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-10">
							      <input name="radios10" id="radios-10" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.childShareProblems" ng-disabled="!isNewForm">
							     <span translate> No</span>
							    </label> 
							    <div id="sharingProblemError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding" ng-show="formInfo.childShareProblems == true">
								<label class="col-md-4 control-label" for="textinput"><span translate> If Yes then (select one) At the time of</span></label>
								<div class="col-md-6">
									<ul class="contents-align">
										<li class="reasonswidth"
											ng-repeat="r in typeList.problemShareTime"><label
											class="radio-inline" for="radios-11" ng-checked="r"> <input ng-disabled="!isNewForm"
												ng-model="r.checked" ng-change="setProblemShareTimeIds(r)" 
												type="checkbox" class="chkbox">&nbsp;&nbsp;{{lang=='en'?r.name:r.typeNameHindi}}
										</label></li>
									</ul>
									
							<!-- <div class="form-group" ng-if="complianceOthersChecked">
							  <div class="col-md-7">
							    <input type="text" maxlength="2000" class="form-control" id="otherComplianceByGovt"  ng-model="formInfo.otherComplianceByGovt" placeholder="{{'Specify other compliance by government'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							      <div id="otherComplianceByGovtError" class="error-style"></div>
							  </div>
						   </div> -->
									 <div id="sharingListsError" class="error-style"></div>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(f) Heading for Facilitation of Child’s Parents from any welfare scheme / programme of Govt.</span><span class="mandatory_star">&#42;</span></label>  
							   <div class="col-md-7">
							 	<label class="radio-inline" for="radios-12">
							      <input name="radios12" id="radios-12" ng-change="resetErrOfAvailability()" ng-value=true type="radio" ng-model="formInfo.headingForFacilitation" ng-disabled="!isNewForm">
							      <span translate>Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-12">
							      <input name="radios12" id="radios-12" ng-change="resetErrOfAvailability()" ng-value=false checked="checked" type="radio" ng-model="formInfo.headingForFacilitation" ng-disabled="!isNewForm">
							   	  <span translate>No</span>
							    </label> 
							    <div id="facilitationError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group" ng-show="formInfo.headingForFacilitation == true">
							  <label class="col-md-4 control-label" for="textarea"><span translate> If yes, then Name of Scheme / program </span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="typeOfWork" ng-change="resetErrOfAvailability()"  ng-model="formInfo.schemeName" placeholder="{{'Specify name of scheme/program'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							     <div id="nameOfSchemeError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="form-group" ng-show="formInfo.headingForFacilitation == true">
							  <label class="col-md-4 control-label" for="textarea"><span translate>Type of facilitation from the Scheme / program</span> <span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7">                     
							    <input type="text" maxlength="2000" class="form-control" id="typeOfFacilitation"   ng-model="formInfo.typeOfFacilitation" placeholder="{{'Type of facilitation from the Scheme / program'| translate}}"
							    oninvalid="this.setCustomValidity('Please specify')" oninput="setCustomValidity('')" ng-disabled="!isNewForm">
							       <div id="typeOffacilitationError" class="error-style"></div>
							  </div>
							</div>
							
							<div class="grey-header"><span translate>11. Remarks</span></div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="textarea"><span translate>Key Remarks of the visit: (Maximum 500 words input capacity) </label>
							  <div class="col-md-7">                     
							    <textarea maxlength="2000" class="form-control" id="childGoneWhere"   ng-model="formInfo.keyRemarks" placeholder="{{'Enter key Remarks of the visit'| translate}}"
							    oninvalid="this.setCustomValidity('Please enter address of the person')" oninput="setCustomValidity('')" ng-disabled="!isNewForm"></textarea>
							  
							  </div>
							</div>
							
							<div style="text-align:center">
								<button id="button1id" name="button1id" ng-click = "validateForm()" ng-show="isNewForm"
								class="btn btn-info" type="submit"><span translate>Submit</span></button>
								
								<button id="button3id" name="button3id" ng-show="!isNewForm"
										class="btn btn-info" ng-click="printSubmitData()">{{'Print'
										| translate}}</button>
							</div>
 
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="submitModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to submit the form.</span></h4>
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
	
	<div class="modal fade" id="printModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <h4 class="modal-title" id="myModalLabel">
	          	<span translate>The form has been submitted successfully.</span><br>
	          <p style="text-align:center">
			  <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="printSubmitData()">{{'Print' | translate}}</button>
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">{{'OK' | translate}}</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <h4 class="modal-title" id="myModalLabel">
	          	<span translate>{{errorMessage}}</span><br>
	          <p style="text-align:center">
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirectToHome()">{{'BACK' | translate}}</button>
	          </p>                     
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
	var app = angular.module('FollowUp', ['gettext']);
	var myAppConstructor = angular.module('FollowUp');
	</script>
	
	<script type="text/javascript">
		
		$(document).ready(function() {
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
	<script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularController/FollowUpFormController.js"></script>
<!-- 					<script type="text/javascript" -->
<!-- 		src="resources/js/AngularController/headerController.js"></script> -->
	<!-- 	<script src="resources/js/AngularController/child_basic.js"></script> -->
	<script
		src="resources/js/moment-with-locales.js"></script>
<!-- 	<script src="resources/js/bootstrap-datetimepicker.min.js"></script> -->
	<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript"></script>
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>