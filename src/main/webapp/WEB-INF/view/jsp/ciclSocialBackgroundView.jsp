<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<title>SOCIAL BACKGROUND REPORT - FORM 1</title>

<!-- Bootstrap css -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/styles.css">

<!-- Font awesome css -->
<!-- 	  <link rel="stylesheet" href="resources/fonts/font-awesome/font-awesome.min.css"> -->
<spring:url value="/resources/css/style.css" var="styleCss" />
<link href="${styleCss}" rel="stylesheet" />
<!-- jquery-ui.css file is not that big so we can afford to load it -->
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>
</head>
<body ng-app="CICLSocialBackgroundReport"
	ng-controller="CICLSocialBackgroundReportController" ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
	<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><span translate>Please click on the update button to update the child details.</span></h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id" class="btn btn-info bigbutton"
							type="submit" ng-click="updateForm()" class="close"
							data-dismiss="modal" aria-hidden="true">{{'Update' | translate}}</button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true">{{'Back' | translate}}</button>
					</p>
				</div>
			</div>
		</div>
	</div>
<div class="modal fade" id="deleteFamilyDetails" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel" translate>Please click on the update button to update the child details.</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
					<button id="button4id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="deleteNewDetails()">{{'Yes' | translate}}</button>
						<button id="button5id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true">{{'No' | translate}}</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="childIdModal" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel"><span translate>The child data has been successfully updated</span></h4>
						<p style="text-align: center">
						<button id="button5id" name="button6id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="printSubmitData()">{{'Print' | translate}}</button>
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="reDirect()">{{'OK' | translate}}</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="modelValue" value="${selectedChild}">
	<input type="hidden" id="idValue" value="${selectedId}">

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="box-border box-border-padding">
				<!-- <hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr> -->
					<div class="childlist-heading1 borderPersonal">
						<span translate>SOCIAL BACKGROUND REPORT</span><br><span translate>FORM 1</span><br><span translate>[Rules 8 (1), 8 (5)]</span>
					</div>
					<form class="form-horizontal basicchildform"
						name="ciclSocialBackgroungReport" id="ciclSocialBackgroungReport">
						<fieldset>
							<div class="grey-header marginTop"
								style="border-top: none; margin-top: 4px;">
								<span translate>Social Background Report</span></div>

							<div class="col-md-12 summaryspace">
								<div class="col-md-6 childidheader"
									style="margin-left: 0px !important; padding-left: 0px !important;">
									<div class="social_headng">
										<img src="resources/img/cpis_ccts_Child_ID_SVG.svg"
											style="width: 6%;" /> <span>Child
											ID:&nbsp;&nbsp;${selectedChild}</span>
									</div>
								</div>
								<div class="col-md-6 childnameheader"
									style="margin-right: 0px !important; padding-right: 0px !important;">
									<div class="social_headng1">
										<span>Name of Child:&nbsp;&nbsp;{{formInfo.childName}}</span>
									</div>
								</div>
							</div>
							<div class="form-group box-border-padding interimPlanmargintop">
								<label class="col-md-4 control-label" for="textinput"><span translate>1.FIR No. </span>
								</label>
								<div class="col-md-4">
									
									<input id="firddNo" name="firddNo" maxlength="15"
										ng-model="formInfo.firNumber" placeholder="{{'Enter FIR no.' | translate}}"
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>2.DD No. </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
									<input id="ddNo" maxlength="15" name="ddNo" ng-disabled="disableDDNumber||ciclRegIfUpdateDisabled||ciclRegDisabled" ng-keyup="keyupForDDandGdNumber(formInfo.ddNumber,'DD')" ng-model="formInfo.ddNumber"
										placeholder="{{'Enter DD no.' | translate}}" class="form-control input-md"
										type="text" ng-disabled="ciclRegDisabled">
								</div>
                                <div id="ddnumbererror" class="error-style"></div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>3.GD No. </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
									<input id="gdNo" maxlength="15" name="gdNo" ng-disabled="disableGDNumber||ciclRegIfUpdateDisabled||ciclRegDisabled" ng-keyup="keyupForDDandGdNumber(formInfo.gdNumber,'GD')" ng-model="formInfo.gdNumber"
										placeholder="{{'Enter GD no.' | translate}}" class="form-control input-md"
										type="text" ng-disabled="ciclRegDisabled">
								</div>
								<div id="gdnumbererror" class="error-style"></div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>4.U/Sections</span></label>
								<div class="col-md-7">
									<input id="usections" name="usections" maxlength="15"
										ng-model="formInfo.sections" placeholder="{{'Enter U/Sections' | translate}}"
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
								<div id="usectionserror" class="error-style"></div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>5.Police Station </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="policeStation" name="policeStation" maxlength='30'
										ng-model="formInfo.policeStation"
										placeholder="{{'Enter police station name' | translate}}" required
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
								<div id="usectionserror" class="error-style"></div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput" ng-disabled="ciclRegDisabled"><span translate>6.Date</span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-6">
									<input type="text" id="datepicker"
										ng-model="formInfo.entryDate" readonly class="form-control">
									<div id="dateerror" class="error-style"></div>
								</div>
								<i class="fa fa-calendar fa-5x"
									style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
									aria-hidden="true"></i>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>7.Time </span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-4">
									<select ng-options="item as item for item in hour"
										ng-model="hr" required class="form-control"
										style="width: 32%; display: inline;" ng-disabled="ciclRegDisabled">
										<option value="" disabled selected>HH</option>
									</select> <select ng-options="item as item for item in min"
										ng-model="minute" required class="form-control"
										style="width: 32%; display: inline;" ng-disabled="ciclRegDisabled">
										<option value="" disabled selected>MM</option>
									</select> <select ng-options="item as item for item in ampm"
										ng-model="ap" required class="form-control"
										style="width: 32%; display: inline;" ng-disabled="ciclRegDisabled">
										<option value="" disabled selected>AM/PM</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>8.Name of I.O. </span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="nameofIO" ng-model="formInfo.nameOfIo" maxlength='30'
										placeholder="{{'Enter name of I.O.' | translate}}" class="form-control input-md"
										type="text" required ng-disabled="ciclRegDisabled">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>9. Name of CWPO </span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="nameofcwpo" name="nameofcwpo" required
										ng-model="formInfo.nameOfCwpo" maxlength='30'
										placeholder="{{'Enter name of CWPO' | translate}}" class="form-control input-md"
										type="text" required ng-disabled="ciclRegDisabled">
									<div id="personNameerror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>10. Name </span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="nameofChild" name="nameofChild"
										ng-model="formInfo.childName" ng-disabled="ciclRegDisabled"
										placeholder="{{'Enter name of child' | translate}}" maxlength='30'
										class="form-control input-md" type="text" required>
									<div id="casenoerror" class="error-style" ></div>
								</div>
							</div>
							 <div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>11. Uploaded image</span><span class="mandatory_star">&#42;</span></label>
								<img ng-src={{formInfo.childImage}} height="80" width="80" data-action="zoom" style="margin-left:16px;"">
							</div> 
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>12. Father Name </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="fatherNamecicl" ng-model="formInfo.fatherName" maxlength='30'
										placeholder="{{'Enter Father\'s Name' | translate}}" required
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>13. Mother Name </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="motherName" ng-model="formInfo.motherName" maxlength='30'
										placeholder="{{'Enter Mother\'s Name' | translate}}" required
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>14. Guardian's name</span>
								</label>
								<div class="col-md-7">
									<input id="Guardianname" 
										ng-model="formInfo.guardianName" maxlength='30'
										placeholder="{{'Enter guardian\'s name of the child' | translate}}"
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>15. Age </span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<select id="childAge" class="form-control" ng-disabled="ciclRegDisabled"
										ng-model="formInfo.age" required>
										<option value="" disabled selected>Select age of the child</option>
										<option ng-repeat="age in ageList | orderBy:'id'" ng-value="age.id">{{age.name}}</option>
									</select>
									<div id="personAgeerror" class="error-style"></div>
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput" ><span translate>16. Sex</span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<select id="gender" class="form-control"
										ng-model="formInfo.sexObject.id"
										oninvalid="this.setCustomValidity('Please select gender of the person')"
										oninput="setCustomValidity('')" required ng-disabled="ciclRegDisabled">
										<option value="" disabled selected>Select</option>
										<option ng-repeat="item in childSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>17. Address</span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<textarea onpaste="return false;" maxlength="200" class="form-control" id="ciclChildAddress" required
										ng-model="formInfo.address" placeholder="{{'Enter Address' | translate}}" ng-disabled="ciclRegDisabled"
										required>
									</textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>18. Aadhaar Card Number</span>
								</label>
								<div class="col-md-7">
									<input type="text"  class="form-control" ng-keyUp="validateName(formInfo.adhaarCardNo,'adharcardError2')" 
										id="ciclChildAdhharCardNo2"   ng-model="formInfo.adhaarCardNo" ng-pattern="/^[0-9]{12}$/"
										ng-blur="resetphnNo(formInfo.adhaarCardNo,'ciclChildAdhaarCardNo2','adharcardError2')"
										placeholder="{{'Enter Aadhaar card number' | translate}}" ng-disabled="ciclRegDisabled" only-twelve-digits/>
										<div id="adharcardError2" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>19. Religion </span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<select id=religionDetail " class="form-control"
										ng-disabled="ciclRegDisabled" required
										ng-model="formInfo.religionObject.id"
										ng-change="religionOthers();showReligionCast()"
										oninvalid="this.setCustomValidity('Please select religion details')"
										oninput="setCustomValidity('')" >
										<option value="" disabled selected translate>Select</option>
										<option ng-repeat="item in religionList"
											ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="showOtherCast">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Please Specify</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input maxlength='30' required id="idTypeOther" placeholder="{{'Please specify' | translate}}"
										class="form-control input-md" type="text"
										ng-model="formInfo.casteOtherType" ng-disabled="ciclRegDisabled">
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="showcast">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Caste</span></label>
								<div class="col-md-7">
									<select id=caste " name="caste" class="form-control" ng-disabled="ciclRegDisabled"
										ng-disabled="ifSubmittedDisable" required
										ng-model="formInfo.casteObject.id"
										oninvalid="this.setCustomValidity('Please select caste')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected translate>Select Caste</option>
										<option ng-repeat="item in casteList" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>20. Whether the child is differently abled</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										name="radios" id="childabled"
										ng-value="differentlyAbledYes"
										ng-click="ShowPassport('Y');yesClicked()" type="radio"
										ng-model="formInfo.differentlyAbled" ng-disabled="ciclRegDisabled"> {{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-disabled="ciclRegDisabled"
										ng-model="formInfo.differentlyAbled" name="radios"
										id="childabledn" ng-value="differentlyAbledNo"
										ng-blur="blur(formInfo.differentlyAbled,'differentlyAbled')"
									 					 ng-trim="false"
										ng-click="ShowPassport('N');noClicked();differentlyAbledUncheck()" type="radio"
										ng-model="formInfo.differentlyAbled"> {{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-show="IsVisible">
								<div class="col-md-7">
									<ul class="conent-align">
										<li><label class="radio-inline" for="radios-0"
											ng-repeat="d in differentlyAbledType" ng-checked="d"
											style="margin-left: 10px;"> <input
												ng-model="d.checked"
												ng-change="setIds(d)" type="checkbox" class="chkbox" ng-disabled="ciclRegDisabled">&nbsp;&nbsp;{{lang=='en'?d.name:d.typeNameHindi}}
										</label></li>
										<label class="radio-inline"> <input
											id="othersDifferentlyAbledSpecify"
											ng-if="keyString.includes('46')" placeholder="{{'Specify' | translate}}"
											ng-model="formInfo.otherDifferentlyAbledType" maxlength='30'
											ng-blur="blur(formInfo.otherDifferentlyAbledType,'otherDifferentlyAbledType')"
											ng-trim="false" required
											class="form-control input-md physicalAbuse" type="text" ng-disabled="ciclRegDisabled">
										</label>
										<div id="differentlyAbledError" class="error-style"></div>
									</ul>
									
								</div>
								
							</div>


							<div class="grey-header"><span translate>21. Family Details</span><span class="mandatory_star">&#42;</span></div>
							<div class="">
								<div class="familyDetails col-md-12 mrgnspce"
									ng-repeat="serial in familyDetailsRepetitionArray"
									ng-init="sectionIndex = $index">
									<div data-toggle="collapse"  ng-click="toggleGroup(serial)"
										data-target="{{'#childfamilydetails'+($index+1)}}" class="addDetailPanel collapsed in"
										 ng-class="{active: isGroupShown(serial)}"><span translate>Add Details </span><i class="icon" style="float:right;margin: 4px; margin-right: -7px;"
										  ng-class="isGroupShown(serial) ? 'fa fa-minus' : 'fa fa-plus'"></i></div>
<!-- 									<button data-toggle="collapse" -->
<!-- 										data-target="{{'#childfamilydetails'+($index+1)}}" -->
<!-- 										type="button" class="addDetailPanel collapsed"> -->
<!-- 										Add Details <i class="fa fa-plus" aria-hidden="true"></i><i -->
<!-- 											class="fa fa-minus" aria-hidden="true"></i> -->
<!-- 									</button> -->
									<div id="{{'childfamilydetails'+($index+1)}}" class="collapse" ng-if="isGroupShown(serial)">
										<div class="form-group box-border-padding"
											ng-repeat="details in serial">
											<label class="col-md-4 control-label" for="textinput" ng-bind="details.name | translate"><span class="mandatory_star">&#42;</span></label>
											<div class="col-md-7" ng-if="details.type=='text'">
												<input placeholder="{{details.placeholder | translate}}"
													ng-disabled="ciclRegDisabled" maxlength='30'
													oninvalid="this.setCustomValidity('Please specify')"
													oninput="setCustomValidity('')"
													class="form-control input-md" type="text"
													ng-blur="dynamicBlur(details.value,sectionIndex, $index)"
											        ng-trim="false"
													ng-model="details.value" required>
											</div>

											<div class="col-md-7" ng-if="details.type=='ageNumber'">
												<input placeholder="{{details.placeholder | translate}}"
													ng-disabled="ciclRegDisabled"
													oninvalid="this.setCustomValidity('Please specify')"
													oninput="setCustomValidity('')"
													ng-keyUp="validateFDAge('familyMemberAgeError',details)"
													class="form-control input-md" type="number"
													ng-model="details.value" required>
												<div id="familyMemberAgeError" class="error-style"></div>
											</div>
											<div class="col-md-7" ng-if="details.desc=='sexNumber'">
												<select id="familysex" class="form-control"
													ng-disabled="ciclRegDisabled" ng-model="details.id"
													oninvalid="this.setCustomValidity('Please select sex')"
													oninput="setCustomValidity('')" required>
													<option value="" disabled selected>Select</option>
													<option ng-repeat="item in familyMemberSex"
														ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
												</select>

											</div>
											<div class="col-md-7" ng-if="details.desc=='eduDropdown'">
												<select id="familysex" class="form-control"
													ng-disabled="ciclRegDisabled" ng-model="details.id"
													oninvalid="this.setCustomValidity('Please select education')"
													oninput="setCustomValidity('')" required>
													<option value="" disabled selected>Select</option>
													<option ng-repeat="item in education"
														ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
												</select>

											</div>
											<div class="col-md-7" ng-if="details.type=='incomeNumber'">
												<input placeholder="{{details.placeholder | translate}}"
													ng-disabled="ciclRegDisabled"
													ng-keyup="validateFdIncome(details.value, 'maximumIncomeError', sectionIndex, $index)"
													oninvalid="this.setCustomValidity('Please specify')"
													oninput="setCustomValidity('')"
													class="form-control input-md" type="number"
													ng-model="details.value" required				
													ng-blur="clearIncomeError(sectionIndex, $index)">
												<div id="{{'incomeError'+(sectionIndex)+($index)}}" class="error-style"></div>
											</div>
										</div>
									</div>
									
								</div>
								<div class="addingnewdetails-container">
								<div id="familydetailserror" class="error-style"></div>
									<button type="button" class="addingnewdetails"
										ng-disabled="ciclRegDisabled||maximumLimit6"
										style="margin-left: 0px;" ng-click="addNewDetails()">+</button>
									<button type="button" class="addingnewdetails" ng-disabled="ciclRegDisabled||familyDetailsRepetitionArray.length <2"
										data-toggle="modal" data-target="#deleteFamilyDetails">-</button>
										
<!-- 									<button type="button" class="addingnewdetails" -->
<!-- 										ng-disabled="ciclRegDisabled" ng-click="deleteNewDetails()">-</button> -->
									<div id="maximumLimit6Error" class="error-style"></div>
									
								</div>
								


								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"></label>
									<div class="col-md-7"></div>
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>22. Reasons for leaving home</span></label>
								<div class="col-md-7">
									<input maxlength='100' id="reasonForLeaving"
										ng-model="formInfo.homeLeavingReason" ng-disabled="ciclRegDisabled"
										placeholder="{{'Enter reasons for leaving home' | translate}}"
										class="form-control input-md" type="text" Reasons for leaving
										home>
								</div>
							</div>



							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>23. Whether there is a history of involvement of family members in offences, if any</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-value="familyInvolvementInOffenceYes"
										name="familyInvolvementInOffence"
										id="familyInvolvementInOffence" type="radio"
										ng-click="ShowFamilyInvolvementInOffence('Yes')"
										ng-model="formInfo.familyInvolvementInOffence" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										name="familyInvolvementInOffence"
										id="familyInvolvementInOffence"
										ng-model="formInfo.familyInvolvementInOffence"
										ng-value="familyInvolvementInOffenceNo"
										ng-click="ShowFamilyInvolvementInOffence('No')" type="radio" ng-disabled="ciclRegDisabled">
										{{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="showFamilyInvolvementInOffence">
								<label class="col-md-4 control-label" for="textinput"> </label>
								<div class="col-md-5">
									<input id="specifyhistory" placeholder="{{'Please specify' | translate}}" maxlength='100'
										ng-if="familyInvolvementInOffenceYes" required
										ng-model="formInfo.otherFamilyInvolvementInOffence"
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
							</div>

							<div class="grey-header"><span translate>24. Habits of the child</span><span class="mandatory_star">&#42;</span></div>

							<div class="form-group box-border-padding boxbrdr">
								<div class="col-md-6 habitshead">
									<label class="control-label" for="textinput"><span translate>A - Bad Habits</span></label>
									<div class="badhabits">
										<ul class="content-align">
											<li class="habitswidth"
												ng-repeat="b in badHabits | orderBy:'id'""><label
												class="radio" for="radios-0" ng-checked="b"> <input
													ng-disabled="ciclRegDisabled" ng-model="b.checked"
													ng-change="setBadIds(b)" type="checkbox" class="chkbox">&nbsp;&nbsp;{{lang=='en'?b.name:b.typeNameHindi}}
											</label>
											    <div ng-if="b.id=='58'&& b.checked">
													<input maxlength='30' id="othersDrugHabitspecify" 	placeholder="{{'Specify' | translate}}" ng-model="formInfo.otherDrugBadHabits"
												     ng-blur="blur(formInfo.otherDrugBadHabits,'otherDrugBadHabits')" ng-disabled="ciclRegDisabled"
												     ng-trim="false" class="form-control input-md physicalAbuse" type="text">
                                                 </div>
											</li>
											<label class="radio-inline"> <input maxlength="100"
												ng-disabled="ciclRegDisabled" id="othersHabitspecify"
												ng-if="keyBadString.includes('61')" placeholder="{{'Specify' | translate}}"
												ng-model="formInfo.otherBadHabits" required
												class="form-control input-md physicalAbuse" type="text">
											</label>
											
											
										</ul>
									</div>
								</div>
								<div class="col-md-6 habitshead">
									<label class="control-label" for="textinput"><span translate>B - Good Habits</span></label>
									<div class="badhabits">
										<ul class="content1-align">
											<li class="habitswidth"
												ng-repeat="g in goodHabits | orderBy:'id'""><label
												class="radio" for="radios-0" ng-checked="g"> <input
													ng-disabled="ciclRegDisabled" ng-model="g.checked"
													ng-change="setGoodIds(g)" type="checkbox" class="chkbox">&nbsp;&nbsp;{{lang=='en'?g.name:g.typeNameHindi}}
											</label></li>
											<label class="radio-inline"> <input maxlength="100"
												ng-disabled="ciclRegDisabled" id="othersbadHabitspecify"
												ng-if="keyGoodString.includes('55')" placeholder="{{'Specify' | translate}}"
												ng-model="formInfo.otherGoodHabits" required
												class="form-control input-md physicalAbuse" type="text">
											</label>
										</ul>
									</div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"></label>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>25. Employment Details, if any</span></label>
								<div class="col-md-7">
									<input id="empDetails" ng-model="formInfo.employmentDetails" ng-disabled="ciclRegDisabled"
										placeholder="{{'Enter employment details' | translate}}" maxlength="100"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="grey-header"><span translate>26. Details of income utilization</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Sent to family to meet family need</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										name="incomeUtilization" id="incomeUtilization"
										ng-model="formInfo.usedByFamily" ng-value="usedByFamilyYes"
										type="radio" ng-disabled="ciclRegDisabled"> {{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										name="incomeUtilization" id="incomeUtilization"
										ng-model="formInfo.usedByFamily" ng-value="usedByFamilyNo"
										type="radio" ng-disabled="ciclRegDisabled"> {{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Used by self for</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										name="usedbyself" id="usedbyself" ng-value="usedBySelfYes"
										type="radio" ng-model="formInfo.usedBySelf" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input ng-click="resetRadios()"
										name="usedbyself" id="usedbyself" ng-value="usedBySelfNo"
										ng-model="formInfo.usedBySelf" type="radio" ng-disabled="ciclRegDisabled"> {{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="formInfo.usedBySelf">
								<label class="col-md-4 control-label" for="textinput"><span translate>(a) For dress materials</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.usedBySelfDress" name="fordressMaterials"
										id="fordressMaterialsYes" ng-value="usedBySelfDressYes"
										type="radio" ng-disabled="ciclRegDisabled"> {{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.usedBySelfDress" name="fordressMaterials"
										id="fordressMaterialsNo" ng-value="usedBySelfDressNo"
										type="radio" ng-disabled="ciclRegDisabled"> {{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="formInfo.usedBySelf">
								<label class="col-md-4 control-label" for="textinput"><span translate>(b) For gambling</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.usedBySelfGambling" name="forGambling"
										id="forGambling" ng-value="usedBySelfGamblingYes" type="radio" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.usedBySelfGambling" name="forGambling"
										id="forGambling" ng-value="usedBySelfGamblingNo" type="radio" ng-disabled="ciclRegDisabled">
										{{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="formInfo.usedBySelf">
								<label class="col-md-4 control-label" for="textinput"><span translate>(c) For alcohol</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.usedBySelfAlcohol" name="forAlcohol"
										id="forAlcohol" ng-value="usedBySelfAlcoholYes" type="radio" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										name="forAlcohol" id="forAlcohol"
										ng-value="usedBySelfAlcoholNo"
										ng-model="formInfo.usedBySelfAlcohol" type="radio" ng-disabled="ciclRegDisabled">
										{{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="formInfo.usedBySelf">
								<label class="col-md-4 control-label" for="textinput"><span translate>(d) For smoking</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.usedBySelfSmoking" name="forSmoking"
										id="forSmoking" ng-value="usedBySelfSmokingYes" type="radio" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										name="forSmoking" id="forSmoking"
										ng-value="usedBySelfSmokingNo"
										ng-model="formInfo.usedBySelfSmoking" type="radio" ng-disabled="ciclRegDisabled">
										{{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="formInfo.usedBySelf">
								<label class="col-md-4 control-label" for="textinput"><span translate>(e) Savings</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										name="saving" id="saving" ng-value="usedBySelfSavingsYes"
										type="radio" ng-model="formInfo.usedBySelfSavings" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										name="saving" id="saving" ng-value="usedBySelfSavingsNo"
										ng-model="formInfo.usedBySelfSavings" type="radio" ng-disabled="ciclRegDisabled">
										{{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="formInfo.usedBySelf">
								<label class="col-md-4 control-label" for="textinput"><span translate>(f) For drug</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.usedBySelfDrug" name="usedBySelfDrug"
										id="usedBySelfDrug" ng-value="usedBySelfDrugYes" type="radio" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										name="usedBySelfDrug" id="usedBySelfDrug"
										ng-value="usedBySelfDrugNo" ng-model="formInfo.usedBySelfDrug" ng-disabled="ciclRegDisabled"
										type="radio"> {{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>27. The details of education of the child </span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<select id=educationDetail " class="form-control"
										ng-disabled="ciclRegDisabled" required
										ng-model="formInfo.educationDetails.id"
										oninvalid="this.setCustomValidity('Please select education details')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>Select</option>
										<option ng-repeat="item in education" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>28. The reason for leaving School (tick as applicable)</span></label>
								<div class="col-md-6">
									<ul class="contents-align">
										<li class="reasonswidth"
											ng-repeat="r in reasonLeavingSchool | orderBy:'id'""><label
											class="radio-inline" for="radios-0" ng-checked="r"> <input
												ng-disabled="ciclRegDisabled" ng-model="r.checked"
												ng-change="setReasonIds(r)" type="checkbox" class="chkbox">&nbsp;&nbsp;{{lang=='en'?r.name:r.typeNameHindi}}
										</label></li>
										<label class="radio-inline"> <input
											ng-disabled="ciclRegDisabled" id="othersreasonpl"
											ng-if="reasonString.includes('85')" maxlength='30'
											placeholder="{{'Please specify' | translate}}" required
											ng-model="formInfo.otherSchoolLeavingReason"
											ng-blur="blur(formInfo.otherSchoolLeavingReason,'otherSchoolLeavingReason')"
											ng-trim="false"
											class="form-control input-md physicalAbuse" type="text">
										</label>
									</ul>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>29. The details of the school in which studied last</span></label>
								<div class="col-md-7">
									<select id="detailsoftheschool" class="form-control"
										ng-disabled="ciclRegDisabled"
										ng-model="formInfo.schoolDetails.id"
										oninvalid="this.setCustomValidity('Please select school')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>Select</option>
										<option ng-repeat="item in schoolType" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>30. Vocational training, if any</span>
								</label>
								<div class="col-md-7">
									<input id="vocationaltraining" name="vocationaltraining"
										placeholder="{{'Enter name vocational training' | translate}}" maxlength='30'
										ng-model="formInfo.vocationalTraining"
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
									<div id="vocationaltrainingError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>31. Majority of the friends are (tick as applicable) </span><span
									class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
									<ul>
										<li class="majoritywidth"  ng-disabled="ciclRegDisabled"
											ng-repeat="m in majorityFriendTypes | orderBy:'id'""><label
											class="radio-inline" for="radios-0" ng-checked="m"> <input
												ng-model="m.checked"
												ng-change="setMaority(m)" type="checkbox" class="chkbox" ng-disabled="ciclRegDisabled">&nbsp;&nbsp;{{lang=='en'?m.name:m.typeNameHindi}}
										</label></li>
									</ul>
									<div id="MajorityError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>32. Whether the child has been subjected to any form of abuse </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.abused" name="anyformAbuse"
										id="anyformAbuse" ng-click="ShowAbused('Yes')"
										ng-value="abusedYes" type="radio" ng-disabled="ciclRegDisabled"> {{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.abused" name="anyformAbuse"
										id="anyformAbuse" ng-click="ShowAbused('No')"
										ng-value="abusedNo" type="radio" ng-disabled="ciclRegDisabled"> {{'No' | translate}}
									</label>
								</div>
							</div>
							<div class="form-group box-border-padding" ng-if="showAbused">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-4">
									<ul class="parent cf">
										<li><label class="radio-inline"> <b><span translate>Verbal abuse</span></b>
												<ul class="children">
													<li class="reasonleavewidth"
														ng-repeat="a in vebalAbusedBy | orderBy:'id'""><label
														class="radio-inline" for="radios-0" ng-checked="a">
															<input ng-model="a.checked"
															ng-disabled="ciclRegDisabled"
															ng-change="setVerbalAbusedBy(a)" type="checkbox"
															class="chkbox">&nbsp;&nbsp;{{lang=='en'?a.name:a.typeNameHindi}}
													</label></li>

													<label class="radio-inline" for="radios-0"> <input
														ng-disabled="ciclRegDisabled" id="othersplspecify"
														ng-if="verbalAbusedString.includes('125')" maxlength='30'
														placeholder="{{'Please specify' | translate}}" required
														oninvalid="this.setCustomValidity('Specify')"
														oninput="setCustomValidity('')"
														class="form-control input-md physicalAbuse" type="text"
														ng-model="formInfo.otherVerbalAbuse">
													</label>
												</ul>
										</label></li>
									</ul>
									<ul class="parent cf">
										<li><label class="radio-inline"><b><span translate>Physical abuse</span></b>
												<ul class="children">
													<li class="reasonleavewidth"
														ng-repeat="b in physicalAbusedBy | orderBy:'id'""><label
														class="radio-inline" for="radios-0" ng-checked="b">
															<input ng-model="b.checked"
															ng-disabled="ciclRegDisabled"
															ng-change="setphysicalAbusedBy(b)" type="checkbox"
															class="chkbox">&nbsp;&nbsp;{{lang=='en'?b.name:b.typeNameHindi}}
													</label></li>

													<label class="radio-inline" for="radios-0"> <input
														ng-disabled="ciclRegDisabled" id="othersplspecify"
														ng-if="physicalAbusedString.includes('125')" maxlength='30'
														placeholder="{{'Please specify' | translate}}"
														oninvalid="this.setCustomValidity('Specify')"
														oninput="setCustomValidity('')" required
														class="form-control input-md physicalAbuse" type="text"
														ng-model="formInfo.otherPhysicalAbuse">
													</label></li>
									</ul>
									<div id="AbuseError" class="error-style"></div>
									</label>
									</li>
									</ul>
								</div>
								<div class="col-md-4">
									<ul class="parent cf">

										<li><label class="radio-inline"><b><span translate>Sexual abuse</span></b>
												<ul class="children">
													<li class="reasonleavewidth"
														ng-repeat="c in sexualAbusedBy | orderBy:'id'""><label
														class="radio-inline" for="radios-0" ng-checked="c">
															<input ng-model="c.checked"
															ng-disabled="ciclRegDisabled"
															ng-change="setSexualAbusedBy(c)" type="checkbox"
															class="chkbox">&nbsp;&nbsp;{{lang=='en'?c.name:c.typeNameHindi}}
													</label></li>
													<li><label class="radio-inline" for="radios-0">

															<input id="othersexualabuseSpecify"
															ng-disabled="ciclRegDisabled" maxlength='30'
															ng-if="sexualAbusedString.includes('125')"
															placeholder="{{'Please specify' | translate}}" required
															oninvalid="this.setCustomValidity('Specify')"
															oninput="setCustomValidity('')"
															class="form-control input-md otherSexualAbuse"
															type="text" ng-model="formInfo.otherSexualAbuse">
													</label></li>
												</ul>
												 </label></li>
									</ul>
									<ul class="parent cf">
										<li><label class="radio-inline" for="radios-0"><b><span translate>Others (pl. specify)</span></b> <input id="othersAnyabusespecify"
												placeholder="{{'Specify' | translate}}" ng-disabled="ciclRegDisabled"
												oninvalid="this.setCustomValidity('Specify')"
												oninput="setCustomValidity('')" maxlength='30'
												class="form-control input-md others" type="text"
												ng-model="formInfo.otherInOtherAbuse" 
												ng-blur="blur(formInfo.otherInOtherAbuse,'otherInOtherAbuse')"
									 					 ng-trim="false"> </label></li>
									</ul>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>33. Whether the child is a victim of any offense </span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.victimOfOffence" name="victimofOffense"
										id="victimofOffense"
										ng-click="ShowOtherVictimOfOffence('Yes')"
										ng-value="victimOfOffenceYes" type="radio" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.victimOfOffence" name="victimofOffense"
										id="victimofOffense" ng-click="ShowOtherVictimOfOffence('No')"
										ng-value="victimOfOffenceNo" type="radio" checked ng-disabled="ciclRegDisabled">
										{{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="showOtherVictimOfOffence">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-5">
									<input id="offenseSpecify" placeholder="{{'Please specify' | translate}}" maxlength='100'
										ng-model="formInfo.otherVictimOfOffence" required
										class="form-control input-md" type="text" ng-disabled="ciclRegDisabled">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>
								34. Whether the child is used by any gangs or adults or group of adults or has been used for drug peddling </span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.childDrugPeddling" name="drugpeddling"
										id="drugpeddling" ng-click="ShowChildDrugPeddling('Yes')"
										ng-value="childDrugPeddlingYes" type="radio" ng-disabled="ciclRegDisabled">
										{{'Yes' | translate}}
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-model="formInfo.childDrugPeddling" name="drugpeddling"
										id="drugpeddling" ng-click="ShowChildDrugPeddling('No')"
										ng-value="childDrugPeddlingNo" type="radio" checked ng-disabled="ciclRegDisabled">
										{{'No' | translate}}
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="showChildDrugPeddling">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-5">
									<input id="drugOthers"
										ng-model="formInfo.otherChildDrugPeddling" maxlength='100'
										placeholder="{{'Please specify' | translate}}" class="form-control input-md"
										type="text" required ng-disabled="ciclRegDisabled">
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>
								35. Reason for alleged offense such as parental neglect or over protection, peer group influence etc </span><span
									class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<textarea onpaste="return false;" class="form-control" id="reasonforalleged"
										ng-model="formInfo.allegedOffence" required maxlength="200"
										placeholder="{{'Enter reason for alleged offense' | translate}}" ng-disabled="ciclRegDisabled">
										</textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>36. Circumstances in which the child was apprehended </span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<textarea onpaste="return false;" maxlength="200" class="form-control" id="circumstances"
										ng-model="formInfo.apprehendedCircumstances"
										placeholder="{{'Enter circumstances' | translate}}" required ng-disabled="ciclRegDisabled">
										</textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>37. Details of articles recovered from the child </span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<textarea onpaste="return false;" maxlength="200" class="form-control" id="detailsofArticles"
										ng-model="formInfo.articlesRecovered"
										placeholder="{{'Enter details of articles' | translate}}" required ng-disabled="ciclRegDisabled">
										</textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>38. Alleged role of the child in the offense</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<textarea onpaste="return false;" maxlength="200" class="form-control" id="roleofchild"
										ng-model="formInfo.allegedRole"
										placeholder="{{'Enter role of child' | translate}}" required ng-disabled="ciclRegDisabled">
										</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>39. Suggestions of Child Welfare Police Officer</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<textarea onpaste="return false;" maxlength="200" class="form-control" id="suggestions"
										ng-model="formInfo.suggestions"
										placeholder="{{'Enter Suggestions of Child Welfare Police Officer' | translate}}"
										required ng-disabled="ciclRegDisabled">
										</textarea>
								</div>
							</div>


							<div style="text-align: center">
								<button ng-if="!ciclRegDisabled && !finalOrder" id="button1id" name="button1id"
									ng-click="ciclSocialBackgroungReport.$invalid ? '' : validateForm()"
									class="btn btn-info" type="submit">{{'Update' | translate}}</button>
								<button ng-if="ciclRegDisabled && !finalOrder" id="button2id" name="button2id" class="btn btn-info otherbut" ng-click="editForm()" type="reset" >{{'Edit' | translate}}</button>
								<button ng-if="ciclRegDisabled" id="button3id" name="button3id" class="btn btn-info otherbut" ng-click="printSubmitData()" type="button" >{{'Print' | translate}}</button>
							</div>
							<a href="#" class="back-to-top" style="display: inline;">
								<i class="fa fa-arrow-circle-up"></i>
							</a>
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
		var app = angular.module('CICLSocialBackgroundReport', ['gettext']);
		var myAppConstructor = angular.module('CICLSocialBackgroundReport');
	</script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
		<script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script>
		<script type="text/javascript" src="resources/js/download.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularController/CICLSocialBackgroundReportController.js"></script>
			<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
	<!--     <script src="resources/js/bootstrap-datetimepicker.min.js"></script> -->
	<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#basicdetail").on('submit', function() {
				$('#thankyouModal').modal('show');
			});

			$("#button3id").on('submit', function() {
				$('#childIdModal').modal('show');
			});
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

	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>