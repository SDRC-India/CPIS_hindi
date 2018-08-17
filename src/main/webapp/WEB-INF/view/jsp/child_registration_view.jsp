<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico"
	type="image/x-icon">
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<title>REPORT TO BE SUBMITTED AT TIME OF PRODUCTION OF CHILD
	BEFORE THE COMMITTEE - FORM 17</title>
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
<style>
@media ( max-width :992px) {
	.form-fieldset {
		width: 100%;
	}
}
</style>
</head>
<body ng-app="childBasic" ng-controller="BasicForm17" ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">{{'Please click on the update button to update the child details.' | translate}}</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="updateForm()" class="close" data-dismiss="modal"
							aria-hidden="true">{{'Update' | translate}}</button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true">{{'Back' | translate}}</button>
					</p>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="childIdModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">{{'The child record has been updated successfully.' | translate}}</h4>
					<p style="text-align: center">
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="reDirect()">{{'Ok' | translate}}</button>
					</p>
				</div>
			</div>
		</div>
	</div>



	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="box-border box-border-padding">

					<div class="childlist-heading1 borderPersonal">
						<span translate>Report to be submitted at time of production of child before the committee.</span>
						<br> {{'FORM' | translate}} 17<br>[Rules 18(2), 19(25)]
					</div>

					<div class="col-md-12">
						<div class="col-md-6 childidheader"
							style="margin-left: 0px !important; padding-left: 0px !important;">
							<div class="social_headng">
								<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" /> <span><span
									translate>Child ID:</span>&nbsp;&nbsp;${selectedChild}</span>
							</div>
						</div>
						<div class="col-md-6 childnameheader"
							style="margin-right: 0px !important; padding-right: 0px !important;">
							<div class="social_headng1">
								<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{child.childName}}</span>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<form class="form-horizontal basicchildform" name="basicdetail"
							id="basicdetail" style="margin-bottom: 70px;">
							<fieldset class="form-fieldset">

								<div class="form-group box-border-padding"
									style="margin-top: 40px;">
									<label class="col-md-4 control-label" for="textinput" translate>1.Case No.</label>
									<div class="col-md-7">
										<input id="caseno" name="caseno" maxlength="30"
											placeholder="{{'Enter case no.'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.caseNo" readonly ng-disabled="regDisable">
										<div id="casenoerror" class="error-style"></div>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput" translate>2. Produced before the Child Welfare Committee</label>
									<div class="col-md-7">
										<input id="producedBeforeCwc" name="producedBeforeCwc"
											placeholder="Enter child welfare committee name"
											class="form-control input-md" type="text"
											ng-model="child.nameOfchildWelfareCommittee" readonly
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 col-sm-12 col-xs-12 control-label"
										for="textinput"><span translate>3. Date of production</span></label>
									<div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepickerview"
											placeholder="Enter date of production"
											ng-model="child.childProducedDate" readonly
											class="form-control" ng-disabled="regDisable">

									</div>
									<i class="fa fa-calendar fa-5x"
										style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
										aria-hidden="true"></i>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput" translate>4. Time of production</label>
									<div class="col-md-3">
										<input type="text" ng-model="hr" readonly class="form-control"
											style="width: 32%; display: inline;" placeholder="hour"
											ng-disabled="regDisable"> <input type="text"
											ng-model="minute" readonly class="form-control"
											style="width: 32%; display: inline;" placeholder="minute"
											ng-disabled="regDisable"> <input type="text"
											ng-model="ap" readonly class="form-control"
											style="width: 32%; display: inline;" placeholder="AM/PM"
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput" translate>5. Place of production</label>
									<div class="col-md-7">
										<input id="productionPlace"
											placeholder="{{'Enter place of production'| translate }}"
											maxlength="30"
											oninvalid="this.setCustomValidity('Please enter place of production')"
											oninput="setCustomValidity('')" class="form-control input-md"
											type="text" ng-model="child.nameOfChildProducedPlace"
											readonly ng-disabled="regDisable">

									</div>
								</div>

								<div class="grey-header" translate>6. Details of person who is producing the child</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(i) Name of the person</span> <span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="personName" maxlength="30" name="personName"
											placeholder="{{'Enter name of the person'| translate}}"
											class="form-control input-md"
											oninvalid="this.setCustomValidity('Please enter name of the person')"
											oninput="setCustomValidity('')" type="text" required
											ng-model="child.personProducingChildName"
											ng-disabled="regDisable">
										<!-- 							  	 ng-keyUp="validateName(child.personProducingChildName,'personNameerror')" -->
										<div id="personNameerror" class="error-style"></div>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(ii) Age </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input only-two-digits id="producerAge"
											placeholder="{{'Enter age'| translate}}"
											class="form-control input-md"
											oninvalid="this.setCustomValidity('Please enter age of the person')"
											oninput="setCustomValidity('')" required
											ng-model="child.personProducingChildAge"
											ng-pattern="/^[0-9]{1,2}?$/"
											ng-keyUp="validateName(child.personProducingChildAge,'personAgeerror')"
											ng-blur="resetInput(child,'personProducingChildAge','personAgeerror')"
											ng-disabled="regDisable">
										<div id="personAgeerror" class="error-style"></div>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(iii) Sex </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<select id="producerSex" class="form-control" required
											ng-model="child.personProducingChildSex"
											oninvalid="this.setCustomValidity('Please select gender of the person')"
											oninput="setCustomValidity('')" ng-disabled="regDisable">
											<option value="" disabled selected>{{'Select Sex' |
												translate}}</option>
											<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
										</select>

									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label" for="textarea"><span
										translate>(iv) Address </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<textarea class="form-control" id="producerAddress" required
											ng-model="child.personProducingChildAddress"
											placeholder="{{'Enter Address'| translate}}"
											oninvalid="this.setCustomValidity('Please enter address of the person')"
											maxlength="100" oninput="setCustomValidity('')"
											ng-disabled="regDisable"></textarea>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(v) Contact number </span><span
										class="mandatory_star">&#42;</span> </label>
									<div class="col-md-7">
										<input only-ten-digits id="contactNumber"
											name="personProducingChildContactNo"
											placeholder="{{'Enter contact number'| translate}}"
											class="form-control input-md"
											oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
											oninput="setCustomValidity('')"
											ng-model="child.personProducingChildContactNo" required
											ng-keyUp="validateName(child.personProducingChildContactNo,'phoneNoError')"
											ng-pattern="/^[0-9]{1}[0-9]{9}$/" type="text"
											ng-disabled="regDisable">
										<div id="phoneNoError" class="error-style"></div>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(vi) Occupation/ designation </span><span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="occupation" maxlength="30"
											name="personProducingChildOccupation"
											placeholder="{{'Enter occupation/ designation'| translate}}"
											required class="form-control input-md"
											oninvalid="this.setCustomValidity('Please enter occupation of the person')"
											oninput="setCustomValidity('')" type="text"
											ng-model="child.personProducingChildOccupation"
											ng-disabled="regDisable">

									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(vii) Type of organization</span></label>
									<div class="col-md-7">
										<select id="cciName" class="form-control"
											ng-model="child.typeOfOrganization"
											ng-change="showOrganizationNameField()"
											oninvalid="this.setCustomValidity('Please select type of organization')"
											oninput="setCustomValidity('')" ng-disabled="regDisable">
											<option value="" disabled selected>{{'Select type of
												organization' | translate}}</option>
											<option ng-repeat="organization in organizationType"
												ng-value="organization.id">{{organization.name}}</option>
										</select>

									</div>
								</div>

								<!-- 							<div class="form-group box-border-padding"> -->
								<!-- 							  <label class="col-md-4 control-label" for="textinput" translate>(vii) Name of the organization/CCI/SAA</label>   -->
								<!-- 							  <div class="col-md-7"> -->
								<!-- 							  <select id="cciName" class="form-control" ng-model="child.organizationCCISAAName" required ng-change="otherOrganization()" -->
								<!-- 							  	oninvalid="this.setCustomValidity('Please select an organization/CCI/SAA')" oninput="setCustomValidity('')"> -->
								<!-- 							      <option value="" disabled selected>Select organization/CCI/SAA</option> -->
								<!-- 							     <option ng-repeat="cci in cciList" ng-value="cci.cciId">{{cci.name}}</option> -->
								<!-- 							    </select> -->

								<!-- 							  </div> -->
								<!-- 							</div> -->

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>Please Specify</span></label>
									<div class="col-md-7">
										<input id="otherOrganization" maxlength="30"
											name="otherOrganizationCCISAAName"
											placeholder="{{'Enter organization name'| translate}}"
											class="form-control input-md"
											oninvalid="this.setCustomValidity('Please enter organization name')"
											oninput="setCustomValidity('')" type="text"
											ng-model="child.organizationCCISAAName"
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="grey-header" translate>7. The child who is being produced</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(i) Name (if any)</span></label>
									<div class="col-md-7">
										<input maxlength="30" id="childName" name="childName"
											placeholder="{{'Enter Name of the child'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.childName" ng-disabled="regDisable">
										<!-- 							  ng-pattern="/^[a-zA-Z\s]*$/" ng-keyup="validateName(child.childName,'childNameError')" -->
										<div id="childNameError" class="error-style"></div>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(ii) Uploaded image</span><span
										class="mandatory_star">&#42;</span></label>
									<!--  <div class="col-md-3">
									<input type="file" name="childImage[]" id="js-upload-files" multiple ng-files="getReport($files,'childImage')" required accept=".png, .jpg, .jpeg">
								</div> -->
									<!--  <img style="margin-left: 100px;" src={{childImage}} alt="No image" height="45" width="45" data-action="zoom"> -->
									<div class="col-md-7">
										<img ng-src={{child.childImage}} alt="No image" height="80"
											width="80" data-action="zoom">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(iii) Age (stated age/ age based on appearance)</span>
										<span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<select id="childAge" class="form-control"
											ng-model="child.childAge" ng-disabled="regDisable">
											<option value="" disabled selected>{{'Select age of the child' | translate}}</option>
											<option ng-repeat="age in ageList" ng-value="{{age.id}}">{{age.name}}</option>
										</select>

									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(iv) Sex </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<select id="childSex" class="form-control"
											ng-model="child.childSex" required ng-disabled="regDisable">
											<option value="" disabled selected>{{'Select sex' |
												translate}}</option>
											<option ng-repeat="sex in childSex" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
										</select>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(v) Identity mark/s </span><span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="identityMark" required
											placeholder="{{'Enter identity mark/s of the child'| translate}}"
											maxlength="200" class="form-control input-md" type="text"
											ng-model="child.childIdentityMarks" ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(vi) Aadhaar card number</span></label>
									<div class="col-md-7">
										<input id="identityMark3"
											ng-keyUp="validateAdharCard(formInfo.adhaarCardNo,'adharcardError3')"
											ng-blur="resetAdharNo(formInfo.adhaarCardNo,'identityMark3','adharcardError3')"
											placeholder="{{'Enter Aadhaar card number of the child'| translate}}"
											ng-pattern="/^[0-9]{12}$/" only-twelve-digits
											class="form-control input-md" type="text"
											ng-model="formInfo.adhaarCardNo" ng-disabled="regDisable">
										<div id="adharcardError3" class="error-style"></div>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(vii) Language used by the child </span><span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="language" required maxlength="30"
											placeholder="{{'Enter language used by the child'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.childLanguageUsed" ng-disabled="regDisable">
									</div>
								</div>

								<div class="grey-header">
									<span translate>8. Details of parents / guardian (if available)</span>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(i) Name </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="parentName" maxlength="30"
											placeholder="{{'Enter parents/guardian name'| translate}}"
											class="form-control input-md" required type="text"
											ng-model="child.parentName" ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(ii) Age </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input only-two-digits id="parentAge"
											placeholder="{{'Enter age of the parents/guardian'| translate}}"
											class="form-control input-md"
											oninvalid="this.setCustomValidity('Please enter age of the person')"
											oninput="setCustomValidity('')" ng-model="child.parentAge"
											ng-pattern="/^[0-9]{1,2}?$/" required
											ng-keyUp="validateName(child.parentAge,'personAgeerrorOne')"
											ng-blur="resetInput(child,'parentAge','personAgeerrorOne')"
											ng-disabled="regDisable">
										<div id="personAgeerrorOne" class="error-style"></div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label" for="textarea"><span
										translate>(iii) Address </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<textarea class="form-control" maxlength="100" required
											id="parentAddress" ng-model="child.parentAddress"
											placeholder="{{'Enter address of the parents/guardian'| translate}}"
											ng-disabled="regDisable"></textarea>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"
										ng-pattern="/^[0-9]{10}$/"><span translate>(iv) Contact number</span></label>
									<div class="col-md-7">
										<input only-ten-digits id="parentContactNo"
											placeholder="{{'Enter contact number of the parents/guardian'| translate}}"
											class="form-control input-md" maxlength="10" type="text"
											ng-model="child.parentContactNo" ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(v) Occupation</span></label>
									<div class="col-md-7">
										<input id="parentOccupation" maxlength="30"
											placeholder="{{'Enter occupation of the parents/guardian'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.parentOccupation" ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>9. Place where the child was found </span><span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="childFound" maxlength="30"
											placeholder="{{'Enter the place where the child was found'| translate}}"
											required class="form-control input-md" type="text"
											ng-model="child.childFoundPlace" ng-disabled="regDisable">
									</div>
								</div>

								<div class="grey-header">
									<span translate>10. The details of the person (if any) with whom the child was found</span>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(i) Name </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="withWhomChildFoundName" maxlength="30"
											name="withWhomChildFoundName" required
											placeholder="{{'Enter name of the person'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.withWhomChildFoundName"
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"
										type="number"><span translate>(ii) Age </span><span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input only-two-digits id="withWhomChildFoundAge"
											placeholder="{{'Enter age of the person'| translate}}"
											required
											oninvalid="this.setCustomValidity('Please enter age of the person')"
											oninput="setCustomValidity('')" class="form-control input-md"
											type="text" ng-model="formInfo.withWhomChildFoundAge"
											ng-pattern="/^[0-9]{1,2}?$/"
											ng-keyUp="validateName(formInfo.withWhomChildFoundAge)"
											ng-disabled="regDisable">
										<!-- 							  	<div id="withWhomChildFounderror" class="error-style"></div>   -->
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(iii) Address </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<textarea class="form-control" maxlength="100" required
											id="withWhomChildFoundAddress"
											name="withWhomChildFoundAddress"
											ng-model="child.withWhomChildFoundAddress"
											placeholder="{{'Enter address of the person'| translate}}"
											ng-disabled="regDisable"></textarea>
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(iv) Contact number</span></label>
									<div class="col-md-7">
										<input only-ten-digits id="withWhomChildFoundContactNo"
											name="withWhomChildFoundContactNo"
											placeholder="{{'Enter contact number'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.withWhomChildFoundContactNo"
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>(v) Occupation </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="withWhomChildFoundOccupation" maxlength="30"
											name="withWhomChildFoundOccupation"
											placeholder="{{'Enter occupation'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.withWhomChildFoundOccupation" required
											ng-disabled="regDisable">
									</div>
								</div>
								</br>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>11. Circumstances under which the child was found </span>
										<span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="childCircumstancesWhenFound;" required
											maxlength="200" name="childCircumstancesWhenFound;"
											placeholder="{{'Enter under which circumstances the child was found'| translate}}"
											class="form-control input-md" type="text"
											ng-model="child.childCircumstancesWhenFound;"
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>12. Allegation by the child of any offence/abuse committed on the child in any manner</span>
										<span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="allegationByChild" name="allegationByChild"
											required maxlength="200"
											placeholder="{{'Enter allegation by the child of any offence/abuse'|translate}}"
											class="form-control input-md" type="text"
											ng-model="child.allegationByChild;" ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>13. Physical condition of the child </span><span
										class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="physicalConditionOfChild"
											name="physicalConditionOfChild" required maxlength="200"
											placeholder="{{'Enter physical condition of the child'|translate}}"
											class="form-control input-md" type="text"
											ng-model="child.physicalConditionOfChild;"
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>14. Belongings of the child at the time of production </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="childBelongings" name="childBelongings" required
											maxlength="200"
											placeholder="{{'Enter belongings of the child at the time of production'|translate}}"
											class="form-control input-md" type="text"
											ng-model="child.childBelongings;" ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 col-sm-12 col-xs-12 control-label"
										for="textinput"><span translate>15. Date at which the child came to the CCI/SAA</span></label>
									<div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepicker2"
											ng-model="child.childCameToCCIDate" readonly
											class="form-control" ng-disabled="regDisable">
									</div>
									<i class="fa fa-calendar fa-5x"
										style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
										aria-hidden="true"></i>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>16. Time at which the child came to the CCI/SAA</span></label>
									<div class="col-md-4 col-sm-6 col-xs-6">
										<select ng-options="item as item for item in hour"
											ng-model="hr2" class="form-control"
											style="width: 32%; display: inline;" ng-disabled="regDisable">
											<option value="" disabled selected>HH</option>
										</select> <select ng-options="item as item for item in min"
											ng-model="minute2" class="form-control"
											style="width: 32%; display: inline;" ng-disabled="regDisable">
											<option value="" disabled selected>MM</option>
										</select> <select ng-options="item as item for item in ampm"
											ng-model="ap2" class="form-control"
											style="width: 32%; display: inline;" ng-disabled="regDisable">
											<option value="" disabled selected>AM/PM</option>
										</select>
									</div>
									<!-- <div class="col-md-2 col-sm-6 col-xs-6">
										<button id="resetbtn" name="resetbtn" class="btn otherbut"
											type="button" ng-disabled="regDisable" ng-click="resetTime()">RESET</button>
									</div> -->
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"></label>
									<div class="col-md-4">
										<div id="timeError" class="error-style"></div>
									</div>
								</div>

								<!-- <div class="form-group box-border-padding"> -->
								<!--   <label class="col-md-4 control-label" for="textinput" translate>Date and Time at which the child came to the CCI/SAA</label>   -->
								<!--   <div class="col-md-7"> -->

								<!--   <div>  -->
								<!--         <input type="text" id="datepicker2" ng-model="child.childCameToCCIDateAndTime" readonly class="form-control"> -->
								<!--   </div>    -->
								<!--   </div>      -->
								<!--   </div> -->

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>17. Immediate efforts made to trace family of the child</span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input id="immediateEffortsToTraceFamily" required
											maxlength="200" name="immediateEffortsToTraceFamily"
											placeholder="{{'Enter immediate efforts made to trace family of the child'|translate}}"
											class="form-control input-md" type="text"
											ng-model="child.immediateEffortsToTraceFamily;"
											ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>18. Medical treatment, if provided to the child</span></label>
									<div class="col-md-7">
										<input id="medicalTreatment" maxlength="200"
											name="medicalTreatment"
											placeholder="{{'Enter medical treatment, if provided to the child'|translate}}"
											class="form-control input-md" type="text"
											ng-model="child.medicalTreatment;" ng-disabled="regDisable">
									</div>
								</div>

								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span
										translate>19. Whether police has been informed </span></label>
									<div class="col-md-7">
										<label class="radio-inline" for="radios-0"> <input
											name="radios" id="radios-0" type="radio"
											ng-model="child.policeInformed" value="Yes"
											ng-disabled="regDisable"> <span translate>Yes</span>
										</label> <label class="radio-inline" for="radios-0"> <input
											name="radios" id="radios-0" type="radio"
											ng-model="child.policeInformed" value="No"
											ng-disabled="regDisable"> <span translate> No</span>
										</label>
									</div>
								</div>

								<div style="text-align: center">
									<button ng-if="!regDisable" id="button1id" name="button1id"
										ng-click="basicdetail.$invalid ? '' : validateUpdate()"
										class="btn btn-info" type="submit">{{'Update' | translate}}</button>
									<button ng-if="regDisable && !finalOrder" id="button2id"
										name="button2id" class="btn btn-info" ng-click="editField()">{{'Edit'
										| translate}}</button>
									<button ng-if="regDisable" id="button3id" name="button3id"
										class="btn btn-info" ng-click="printUpdateData()">{{'Print'
										| translate}}</button>
								</div>
								<a href="#" class="back-to-top" style="display: inline;"> <i
									class="fa fa-arrow-circle-up"></i>
								</a>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./common/cctsFooter.jsp" />

	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/angular.min.js"></script>


	<script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script>
	<script>
		var app = angular.module('childBasic', [ 'gettext' ]);
		var myAppConstructor = angular.module('childBasic');

		myAppConstructor.run(function(gettextCatalog) {
			//gettextCatalog.debug = true;
			gettextCatalog.setCurrentLanguage('hi_IN');
			console.log(gettextCatalog)
		});
	</script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
	<script src="resources/js/AngularController/child_basic.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
	<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// 			$("#basicdetail").on('submit', function(){
			// 	   			$('#thankyouModal').modal('show');
			// 			});

			//$("#button3id").on('submit', function(){
			//   $('#childIdModal').modal('show');
			//});
			$("#datepicker").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			// 			$( "#datepicker" ).datepicker('setDate', 'today');
			$("#datepicker2").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			// 			$( "#datepicker2" ).datepicker('setDate', 'today');
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('input').blur(function() {
				var value = $.trim($(this).val());
				$(this).val(value);
			});
			$('textarea').blur(function() {
				var value = $.trim($(this).val());
				$(this).val(value);
			});

		});
	</script>
	<script>
		jQuery(document).ready(function() {
			jQuery('.back-to-top').fadeOut();
			var offset = 250;
			var duration = 500;
			jQuery(window).scroll(function() {
				if (jQuery(this).scrollTop() > offset) {
					jQuery('.back-to-top').fadeIn(duration);
				} else {
					jQuery('.back-to-top').fadeOut(duration);
				}
			});
			jQuery('.back-to-top').click(function(event) {
				event.preventDefault();
				jQuery('html, body').animate({
					scrollTop : 0
				}, duration);
				return false;
			});
		});
	</script>
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>