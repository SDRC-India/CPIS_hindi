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
<title>SOCIAL INVESTIGATION REPORT FOR CHILD IN NEED OF CARE AND PROTECTION - FORM 6</title>

<!-- Bootstrap css -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">

<!-- Font awesome css -->
<!-- 	  <link rel="stylesheet" href="resources/fonts/font-awesome/font-awesome.min.css"> -->
<spring:url value="/resources/css/style.css" var="styleCss" />
<link href="${styleCss}" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/styles.css">
<!-- jquery-ui.css file is not that big so we can afford to load it -->
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>
</head>

<body ng-app="socialInvest" ng-controller="ciclSocialInvestigation"
	ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
	<div class="modal fade" id="socialInvestigationModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to save the social investigation report.</span></h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="submitData" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="saveData()" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Submit</span></button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deleteCICLSIRFamilyDetails" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><span translate>Are you sure to delete this record ?</span></h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
					<button id="button4id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="deleteNewDetails()"><span translate>Yes</span></button>
						<button id="button5id" name="button4id" class="btn btn-info bigbutton2 submitNoModalButton"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate><span translate>No</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="ciclChild" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <p style="text-align:center">
	          	<h4 class="selectChildalign"><span translate>This form is not applicable for Child in conflict with law.</span></h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true"><span translate>OK</span></button>  
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="finalOrderModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <p style="text-align:center">
	          	<h4 class="selectChildalign"><span translate>Final order for this child has been filled up.Could not make any changes.</span></h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<input type="hidden" id="idValue" value="${selectedId}">
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="box-border box-border-padding">
				<!-- <hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr>	 -->		
					<div class="childlist-heading1 borderPersonal"><span translate>Social Investigation Report For Children in Conflict With Law</span><br><span translate>Form6</span><br><span translate>[Rules 10 (9), 11 (2), 64(1), 64(3)(i)]</span></div>
					<form class="form-horizontal basicchildform" id="socialIR"
						name="socialIR">
						<fieldset>

							<div class="grey-header marginTop"
								style="border-top: none; margin-top: 4px;"><span translate>Social Investigation Report</span></div>

							<div class="col-md-12 summaryspace">
								<div class="col-md-6 childidheader"
									style="margin-left: 0px !important; padding-left: 0px !important;">
									<div class="social_headng">
										<img src="resources/img/cpis_ccts_Child_ID_SVG.svg"
											style="width: 6%;" /> <span><span translate>Child ID:</span>&nbsp;&nbsp;${selectedChild}</span>
									</div>
								</div>
								<div class="col-md-6 childnameheader"
									style="margin-right: 0px !important; padding-right: 0px !important;">
									<div class="social_headng1">
										<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{soiMainObj.childName}}</span>
									</div>
								</div>
							</div>

							<div class="form-group box-border-padding interimPlanmargintop">
								<label class="col-md-4 control-label" for="textinput"><span translate>1.Sl. No</span></label>
								<div class="col-md-7">
									<input id="serialno" name="serialno" maxlength="15"
										ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter serial number'| translate}}"
										class="form-control input-md" type="text"
										ng-model="soiMainObj.slNo"
										oninvalid="this.setCustomValidity('Please enter case no')"
										oninput="setCustomValidity('')">
									<div id="slnoerror" class="error-style"></div>
								</div>
							</div>
							<!----------------------- new ------------------------------>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>2.Submitted to the Juvenile Justice Board(address)</span><span
							class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<textarea  maxlength="100" class="form-control" id="JJBAddress"
										ng-disabled="ifSubmittedDisable" required
										ng-model="soiMainObj.jjbAddress" placeholder="{{'Enter address'| translate}}"></textarea>
								</div>
								<div id="producedBeforeCwcerror" class="error-style"></div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>3.Probation Officer/ Voluntary/ Non- Governmental Organization/Child Welfare Officer</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="previousData" class="form-control"
										ng-model="soiMainObj.ciclOrgType.id" required
										ng-disabled="ifSubmittedDisable"
										ng-change="fetchReportPreparedBy(soiMainObj.ciclOrgType); clearName()"
										oninvalid="this.setCustomValidity('Please select an option')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in ciclOrgType" ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
									<div id="ciclOrgTypeError" class="error-style"></div>
								</div>
								<div class="form-group box-border-padding">
									<div class="col-md-4 control-label" for="textinput"></div>
									<div class="col-md-5"
										style="margin-left: 3px; margin-top: 10px;">
										<input ng-if="soiMainObj.ciclOrgType.id"
											id="vocationaltraining" name="vocationaltraining" required
											placeholder="{{'Enter name of the person'| translate}}" 
											ng-disabled="ifSubmittedDisable" maxlength="30"
											class="form-control input-md" type="text"
											ng-blur="blur(soiMainObj.nameOfPerson,'nameOfPerson')"
											ng-trim="false"
											ng-model="soiMainObj.nameOfPerson">
										<div id="jjbNameError" class="error-style"></div>
									</div>
								</div>
							</div>

							<div class="form-group box-border-padding"
								style="margin-top: -20px;">
								<label class="col-md-4 control-label" for="textinput"><span translate>4.FIR No</span></label>
								<div class="col-md-7">
									<input id="fir" name="fir" ng-model="soiMainObj.firNumber"
										readonly class="form-control input-md" type="text">
									<div id="firerror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>5.Under sections</span></label>
								<div class="col-md-7">
									<input id="fir" name="fir" ng-model="soiMainObj.underSections"
										readonly class="form-control input-md" type="text">
									<div id="firerror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>6.Police Station</span></label>
								<div class="col-md-7">
									<input id="fir" name="fir" ng-model="soiMainObj.policeStation" 
										readonly class="form-control input-md" type="text">
									<div id="firerror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>7.Nature of offence alleged</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="previousData" class="form-control"
										ng-model="soiMainObj.natureOfOffenceAlleged.id" required
										ng-disabled="ifSubmittedDisable"
										oninvalid="this.setCustomValidity('Please select an option')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in natureOfOffenceAlleged"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
									<!-- ng-change="fetchReportPreparedBy(soiMainObj.natureOfOffence)" -->
								</div>
							</div>

							<!------------------------ END ---------------------------->



							<div class="grey-header"><span translate>8. Details of child in conflict with law</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i)Name</span><span
									class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input  maxlength="30" id="childName" placeholder="{{'Enter name of the child'| translate}}"
										ng-disabled="ifSubmittedDisable" required
										oninvalid="this.setCustomValidity('Enter name of the child')"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.childName">

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii)Age</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="producerAge" placeholder="{{'Enter age of the child'| translate}}"
										ng-disabled="ifSubmittedDisable" required
										class="form-control input-md" type="number" name="childdAge"
										oninvalid="this.setCustomValidity('Please enter age of the person')"
										oninput="setCustomValidity('')" ng-model="soiMainObj.childAge"
										ng-keyUp="validateName(soiMainObj.childAge,'childAgeError')"
										ng-blur="eraseErrorMsg()">

									<div id="childAgeError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii)Sex</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="previousData" class="form-control"
										ng-model="soiMainObj.childSex.id"
										ng-disabled="ifSubmittedDisable" required
										oninvalid="this.setCustomValidity('Select ')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in childSex" ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>

								</div>
							</div>

							<!-- 							<div class="form-group box-border-padding"> -->
							<!-- 								<label class="col-md-4 control-label" for="textinput">(iv) Caste<span class="mandatory_star">&#42;</span></label> -->
							<!-- 								<div class="col-md-7"> -->
							<!-- 									<select id="Fatherchild" class="form-control" ng-disabled="ifSubmittedDisable" required -->
							<!-- 										ng-model="soiMainObj.childCast.id" -->
							<!-- 										oninvalid="this.setCustomValidity('Please select relationship')" -->
							<!-- 										oninput="setCustomValidity('')" > -->
							<!-- 										<option value="" disabled selected>Select</option> -->
							<!-- 										<option ng-repeat="item in casteList" ng-value="{{item.id}}">{{item.name}}</option> -->
							<!-- 									</select> -->

							<!-- 								</div> -->
							<!-- 							</div> -->
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv)Religion</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id=religionDetail " class="form-control"
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.childReligion.id"
										ng-change="religionOthers();showReligionCast()"
										oninvalid="this.setCustomValidity('Please select religion details')"
										oninput="setCustomValidity('')" required>
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in religionList"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="showOtherCast">
								<label class="col-md-4 control-label" for="textinput"><span translate>PleaseSpecify</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input  maxlength="30" id="idTypeOther" placeholder="{{'Please specify'| translate}}" 
										ng-disabled="ifSubmittedDisable" class="form-control input-md"
										type="text" ng-model="soiMainObj.otherChildReligion" required>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-if="showcast">
								<label class="col-md-4 control-label" for="textinput">{{'Caste' | translate}}<span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id=caste " name="caste" class="form-control"
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.childCast.id"
										oninvalid="this.setCustomValidity('Please select caste')"
										oninput="setCustomValidity('')" required>
										<option value="" disabled selected>{{'Select Caste' | translate}}</option>
										<option ng-repeat="item in casteList" ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v)Child's Father Name</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input  maxlength="30" id="childFatherName" name="childFatherName" 
										ng-disabled="ifSubmittedDisable" required
										ng-model="soiMainObj.fatherName"
										placeholder="{{'Enter father\'s name of the child'| translate}}"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vi)Child's Mother Name</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input  maxlength="30" id="childMotherName" name="childMotherName" 
										ng-disabled="ifSubmittedDisable" required
										ng-model="soiMainObj.motherName"
										placeholder="{{'Enter mother\'s name of the child'| translate}}"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vii)Child's Guardian Name</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input  maxlength="30" id="childGuardianName" name="childGuardianName" 
										ng-disabled="ifSubmittedDisable" required
										ng-model="soiMainObj.guardianName"
										placeholder="{{'Enter guardian\'s name of the child'| translate}}"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>(viii)Permanent Address</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<textarea  class="form-control" id="permanentAddress" maxlength="100"
										ng-disabled="ifSubmittedDisable" required
										ng-model="soiMainObj.permanantAddress"
										placeholder="{{'Enter permanent address'| translate}}"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>(ix)Landmark of the Address</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<textarea class="form-control" id="LandmarkOfAddress" maxlength="100"
										ng-disabled="ifSubmittedDisable" required
										ng-model="soiMainObj.landmark"
										placeholder="{{'Enter landmark of the address'| translate}}"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>(x)Address of last residence</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<textarea class="form-control" id="addressOflastResidence" maxlength="100"
										ng-disabled="ifSubmittedDisable" required
										ng-model="soiMainObj.lastResidenceAddress"
										placeholder="{{'Enter address of last residence'| translate}}"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(xi)Contact number of any family member</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input only-ten-digits id="contactNumbber" required
										name="contactNumbber" ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.familyMemberContactNo"
										placeholder="{{'Enter contact number of any family member'| translate}}"
										oninvalid="this.setCustomValidity('Please enter contact no. of any family member')"
										oninput="setCustomValidity('')"
										ng-keyUp="validateName(soiMainObj.familyMemberContactNo,'phoneNoError')"
										ng-pattern="/^[0-9]{1}[0-9]{9}$/"
										class="form-control input-md"
										ng-blur="resetphnNo(soiMainObj.familyMemberContactNo,'contactNumbber')">
									<div id="phoneNoError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(xii)Whether the child is differently abled</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="radios" id="childabled"
										ng-value="differentlyAbledYesValue"
										ng-click="ShowPassport('Y');yesClicked()" type="radio"
										ng-model="soiMainObj.childDifferentlyAbled"><span translate> Yes</span>
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="radios" 
										id="childabledn" ng-value="differentlyAbledNoValue"
										ng-click="ShowPassport('N');noClicked()" type="radio"
										ng-model="soiMainObj.childDifferentlyAbled"><span translate> No</span>
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding" ng-show="IsVisible">
								<div class="col-md-7">
									<ul class="conent-align">
										<li><label class="radio-inline" for="radios-0"
											ng-repeat="d in differentlyAbledType | orderBy:filterType" ng-checked="d"
											style="margin-left: 10px;"> <input ng-disabled="ifSubmittedDisable"
											    ng-model="d.checked" ng-change="setIds(d)" type="checkbox"
											    class="chkbox">&nbsp;&nbsp;{{langType==0?d.name:d.typeNameHindi}}
													<div ng-if="d.id == 45 && d.checked==true" id="{{$index}}" style="margin-left: 50px;">
														<ul ng-if="!ifSubmittedDisable">
															<li><input type="radio" name="myradiogroup1" value="Mild" ng-model="soiMainObj.mentalIllSeverity" required>
															<label style="margin-right: 70px; margin-left: 2px;"><span translate>Mild</span></label></li>
															<li><input type="radio" name="myradiogroup1" value="Moderate" ng-model="soiMainObj.mentalIllSeverity" required>
															<label style="margin-right: 70px; margin-left: 2px;"><span translate>Moderate</span></label></li>
															<li><input type="radio" name="myradiogroup1" value="Severe" ng-model="soiMainObj.mentalIllSeverity" required>
															<label style="margin-right: 70px; margin-left: 2px;"><span translate>Severe</span></label></li>
														</ul>
													</div>
													
													<div ng-if="d.id == 384 && d.checked==true" id="{{$index}}" style="margin-left: 50px;"> 
														<ul ng-if="!ifSubmittedDisable">
															<li><input type="radio" name="myradiogroup2" value="Mild" ng-model="soiMainObj.mentalRetireSeverity" required>
															<label style="margin-right: 70px; margin-left: 2px;"><span translate>Mild</span></label></li>
															<li><input type="radio" name="myradiogroup2" value="Moderate" ng-model="soiMainObj.mentalRetireSeverity" required>
															<label style="margin-right: 70px; margin-left: 2px;"><span translate>Moderate</span></label></li>
															<li><input type="radio" name="myradiogroup2" value="Severe" ng-model="soiMainObj.mentalRetireSeverity" required>
															<label style="margin-right: 70px; margin-left: 2px;"><span translate>Severe</span></label></li>
														</ul>
													</div>
													<input ng-if="ifSubmittedDisable && soiMainObj.mentalRetireSeverity!=null && d.id == 384"
														   class="form-control input-md" type="text"
												           placeholder="{{soiMainObj.mentalRetireSeverity | translate}}" disabled>
												           
													<input ng-if="ifSubmittedDisable && soiMainObj.mentalIllSeverity!=null && d.id == 45"
														   class="form-control input-md" type="text"
														   placeholder="{{soiMainObj.mentalIllSeverity | translate}}" disabled> <br>
										</label></li>
										<label class="radio-inline"> <input
											ng-disabled="ifSubmittedDisable"
											id="othersDifferentlyAbledSpecify"  maxlength="30"
											ng-if="keyString.includes('46')" placeholder="{{'Specify'| translate}}"
											ng-model="soiMainObj.otherDifferentlyAbled" required
											class="form-control input-md physicalAbuse" type="text"
											ng-blur="blur(soiMainObj.otherDifferentlyAbled,'otherDifferentlyAbled')"
											ng-trim="false">
										</label>
										<div id="differentlyabledError" class="error-style"></div>
									</ul>
								</div>
							</div>

							<div class="grey-header"><span translate>9. Family Details</span> <span class="mandatory_star">&#42;</span></div>
							<div class="">
								<div class="familyDetails col-md-12 mrgnspce"
									ng-repeat="serial in familyDetailsRepetitionArray"
									ng-init="sectionIndex = $index">
									<div data-toggle="collapse"  ng-click="toggleGroup(serial)"
										data-target="{{'#childfamilydetails'+($index+1)}}" class="addDetailPanel collapsed in"
										 ng-class="{active: isGroupShown(serial)}"><span translate>Add Details</span><i class="icon" style="float:right;margin: 4px; margin-right: -7px;"
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
											<label class="col-md-4 control-label" for="textinput" ng-bind="details.name | translate"> <span class="mandatory_star">&#42;</span></label>
											<div class="col-md-7" ng-if="details.type=='text'">
												<input  maxlength="30" placeholder="{{details.placeholder | translate}}"
													ng-disabled="ifSubmittedDisable" 
													oninvalid="this.setCustomValidity('Specify')"
													oninput="setCustomValidity('')"
													class="form-control input-md" type="text"
													ng-blur="dynamicBlur(details.value,sectionIndex, $index)"
											        ng-trim="false"
													ng-model="details.value" required>
											</div>

											<div class="col-md-7" ng-if="details.type=='ageNumber'">
												<input only-ten-digits placeholder="{{details.placeholder | translate}}"
													ng-disabled="ifSubmittedDisable"
													oninvalid="this.setCustomValidity('Please enter age')"
													oninput="setCustomValidity('')"
													ng-keyup="validateFdArrAge(details.value, 'familyMemberAgeError', sectionIndex, $index)"
													ng-blur="clearAgeError(sectionIndex, $index)"
													class="form-control input-md" type="text"
													ng-model="details.value" required>
												<div id="{{'familyMemberAgeError'+(sectionIndex)+($index)}}" class="error-style"></div>
											</div>
											<div class="col-md-7" ng-if="details.desc=='sexNumber'">
												<select id="familysex" class="form-control"
													ng-disabled="ifSubmittedDisable" ng-model="details.id"
													oninvalid="this.setCustomValidity('Please select gender of the person')"
													oninput="setCustomValidity('')" required>
													<option value="" disabled selected>{{'Select' | translate}}</option>
													<option ng-repeat="item in familyMemberSex"
														ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
												</select>

											</div>
											<div class="col-md-7" ng-if="details.desc=='eduDropdown'">
												<select id="familysex" class="form-control"
													ng-disabled="ifSubmittedDisable" ng-model="details.id"
													oninvalid="this.setCustomValidity('Please select education')"
													oninput="setCustomValidity('')" required>
													<option value="" disabled selected>{{'Select' | translate}}</option>
													<option ng-repeat="item in education"
														ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
												</select>

											</div>
											<div class="col-md-7" ng-if="details.type=='incomeNumber'">
												<input placeholder="{{details.placeholder | translate}}"
													ng-disabled="ifSubmittedDisable"
													oninvalid="this.setCustomValidity('Enter income')"
													oninput="setCustomValidity('')"
													class="form-control input-md" type="text"
													only-ten-digits
													ng-model="details.value"
													ng-keyup="validateFdIncome(details.value, 'maximumIncomeError', sectionIndex, $index)"
													ng-blur="clearIncomeError(sectionIndex, $index)" required>
												<div id="{{'incomeError'+(sectionIndex)+($index)}}" class="error-style"></div>	
											</div>
										</div>
									</div>
								</div>
								<div class="addingnewdetails-container">
								<div id="familydetailserror" class="error-style"></div>
									<button type="button" class="addingnewdetails"
										ng-disabled="ifSubmittedDisable || maximumLimit10"
										style="margin-left: 0px;" ng-click="addNewDetails()"
										ng-blur="deleteErrorMsg()">+</button>
									<button type="button" class="addingnewdetails" ng-disabled="ifSubmittedDisable || familyDetailsRepetitionArray.length==1"
									data-toggle="modal" data-target="#deleteCICLSIRFamilyDetails">-</button>
									<div id="maximumLimit10Error" class="error-style"></div>
								</div>


								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"></label>
									<div class="col-md-7"></div>
								</div>
							</div>


							<div class="grey-header"><span translate>10. Relationship among the family members</span><span class="mandatory_star">&#42;</span></div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i)Father & mother</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="fathermother" class="form-control" required
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.relnFatherMother.id"
										oninvalid="this.setCustomValidity('Please select relationship')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in relnFatherMother"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii)Father & child</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="Fatherchild" class="form-control" required
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.relnFatherChild.id"
										oninvalid="this.setCustomValidity('Please select relationship')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in relnFatherMother"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii)Mother & child</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="Motherchild" class="form-control" required
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.relnMotherChild.id"
										oninvalid="this.setCustomValidity('Please select relationship')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in relnFatherMother"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv)Father & siblings</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="Fathersiblings" class="form-control" required
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.relnFatherSiblings.id"
										oninvalid="this.setCustomValidity('Please select relationship')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in relnFatherMother"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v)Mother & siblings</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="Mothersiblings" class="form-control" required
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.relnMotherSiblings.id"
										oninvalid="this.setCustomValidity('Please select relationship')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in relnFatherMother"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vi)Child & siblings</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="Childsiblings" class="form-control" required
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.relnChildSiblings.id"
										oninvalid="this.setCustomValidity('Please select relationship')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in relnFatherMother"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vii)Child &amp; grandparents (paternal/maternal)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="Childrelative" class="form-control" required
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.relnChildGrandParent.id"
										oninvalid="this.setCustomValidity('Please select relationship')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in relnFatherMother"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>11.If child is married</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="childmarried"
										id="childmarried" ng-value="childMarriedYesValue" type="radio"
										ng-click="showSelection('Yes');yesClicked()"
										ng-model="soiMainObj.childMarried"><span translate>Yes</span></label> 
						<label class="radio-inline" for="radios-1"> <input
										ng-disabled="ifSubmittedDisable" name="childmarried" 
										id="childmarried" ng-value="childMarriedNoValue"
										ng-model="soiMainObj.childMarried"
										ng-click="showSelection('No');cmClicked()" type="radio"><span translate>No</span></label>
								</div>
							</div>


							<div class="form-group box-border-padding spousedetails"
								ng-show="IsChildMarriedVisible">
								<div class="col-md-7">
									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>a.Spouse Name</span></label>
										<div class="col-md-7">
											<input id="SpouseName" placeholder="{{'Enter name of spouse'| translate}}"
												ng-disabled="ifSubmittedDisable"  maxlength="30"
												oninvalid="this.setCustomValidity('Please enter spouse name')"
												oninput="setCustomValidity('')"
												class="form-control input-md" type="text"
												ng-model="soiMainObj.spouseName">

										</div>
									</div>
									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>b.Spouse Age</span></label>
										<div class="col-md-7">
											<input id="spouseAge" placeholder="{{'Enter spouse age'| translate}}"
												ng-disabled="ifSubmittedDisable"
												class="form-control input-md pocTakingE" type="number"
												oninvalid="this.setCustomValidity('Please enter age of the person')"
												oninput="setCustomValidity('')"
												ng-model="soiMainObj.spouseAge" ng-pattern="/^[0-9]{1,2}?$/"
												ng-keyUp="validateName(soiMainObj.spouseAge,'personAgeerror')"
												ng-blur="eraseErrorMsg('personAgeerror')">
											<div id="personAgeerror" class="error-style"></div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label" for="textarea"><span translate>c.Spouse Details</span></label>
										<div class="col-md-7">
											<textarea class="form-control" id="spouseDetails"
												ng-disabled="ifSubmittedDisable" maxlength="100"
												ng-model="soiMainObj.spouseDetails"
												placeholder="{{'Enter spouse details'| translate}}"></textarea>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>d.Child 1 Name</span></label>
										<div class="col-md-7">
											<input id="ChildName" placeholder="{{'Enter name of Child'| translate}}"
												ng-disabled="ifSubmittedDisable"  maxlength="30"
												oninvalid="this.setCustomValidity('Please enter Child name')"
												oninput="setCustomValidity('')"
												ng-model="soiMainObj.childrenName1"
												class="form-control input-md" type="text">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>e.Child 1 Age</span></label>
										<div class="col-md-7">
											<input id="ChildAge" placeholder="{{'Enter age'| translate}}"
												ng-disabled="ifSubmittedDisable"
												class="form-control input-md pocTakingE" type="number"
												oninvalid="this.setCustomValidity('Please enter age of the Child')"
												oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1,2}?$/"
												ng-model="soiMainObj.childrenAge1"
												ng-keyUp="validateName(soiMainObj.childrenAge1,'childrenAge1Error')"
												ng-blur="eraseErrorMsg('childrenAge1Error')">
											<div id="childrenAge1Error" class="error-style"></div>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>f.Child 1 Sex</span></label>
										<div class="col-md-7">
											<select id="detailsofeducation" class="form-control"
												ng-disabled="ifSubmittedDisable"
												ng-model="soiMainObj.childrenSex1.id"
												oninvalid="this.setCustomValidity('Please select child sex')"
												oninput="setCustomValidity('')">
												<option value="" disabled selected>{{'Select' | translate}}</option>
												<option ng-repeat="item in childSex" ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
											</select>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>g.Child 2 Name</span></label>
										<div class="col-md-7">
											<input id="ChildName" placeholder="{{'Enter name of Child'| translate}}"
												ng-disabled="ifSubmittedDisable"  maxlength="30"
												oninvalid="this.setCustomValidity('Please enter Child name')"
												oninput="setCustomValidity('')"
												ng-model="soiMainObj.childrenName2"
												class="form-control input-md" type="text">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>h.Child 2 Age</span></label>
										<div class="col-md-7">
											<input id="ChildAge" placeholder="{{'Enter age'| translate}}"
												ng-disabled="ifSubmittedDisable"
												class="form-control input-md pocTakingE" type="number"
												oninvalid="this.setCustomValidity('Please enter age of the Child')"
												oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1,2}?$/"
												ng-model="soiMainObj.childrenAge2"
												ng-keyUp="validateName(soiMainObj.childrenAge2,'childrenAge2Error')"
												ng-blur="eraseErrorMsg('childrenAge2Error')">
											<div id="childrenAge2Error" class="error-style"></div>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>i.Child 2 Sex</span></label>
										<div class="col-md-7">
											<select id="detailsofeducation" class="form-control"
												ng-disabled="ifSubmittedDisable"
												ng-model="soiMainObj.childrenSex2.id"
												oninvalid="this.setCustomValidity('Please select child sex')"
												oninput="setCustomValidity('')">
												<option value="" disabled selected>{{'Select' | translate}}</option>
												<option ng-repeat="item in childSex" ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="grey-header"><span translate>12. History of involvement of family members in offences, if any</span></div>

							<div class="">
								<div class="familyDetails col-md-12">
									<button data-toggle="collapse"
										data-target="#childfamilyMembersOffence" type="button"
										class="addDetailPanel collapsed"><span translate>Add Details</span><i class="fa fa-plus" aria-hidden="true"></i><i
											class="fa fa-minus" aria-hidden="true"></i>
									</button>
									<div id="childfamilyMembersOffence" class="collapse">
										<table class="table table-bordered table-margintop">
											<thead>
												<tr>
													<th><span translate>Relationship</span></th>
													<th><span translate>Nature of Crime</span></th>
													<th><span translate>Legal status of the case</span></th>
													<th><span translate>Arrest if any Made</span></th>
													<th><span translate>Period of Confinement(in months)</span></th>
													<th><span translate>Punishment awarded</span></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<div class="form-group box-border-padding textAlign">
															<label class="control-label" for="textinput"><span translate>Father</span></label>

														</div>
													</td>
													<td><input id="childFatherOffence"
														oninvalid="this.setCustomValidity('Please enter nature of crime')"
														oninput="setCustomValidity('')"  maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiFathernoc"></td>
													<td><input id="statusOfcase"
														oninvalid="this.setCustomValidity('Please enter Legal status of the case')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiFatherLs"></td>
													<td>
														<div class="col-md-7 rbtn-align">
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arrestifanyMadey"
																id="arrestifanyMadey" ng-value="hoiFatherArYesValue"
																type="radio" ng-model="soiMainObj.hoiFatherAr"><span translate>Yes</span></label>
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arrestifanyMadey"
																id="arrestifanyMaden" ng-value="hoiFatherArNoValue"
																type="radio" ng-model="soiMainObj.hoiFatherAr"><span translate>No</span></label>
														</div>
													</td>
													<td><input id="periodofConfinement" only-three-digits
														oninvalid="this.setCustomValidity('Please enter period of Confinement')"
														oninput="setCustomValidity('')"
														class="form-control input-md pocTakingE" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiFatherPoc"></td>
													<td><input id="punishmentawarded"
														oninvalid="this.setCustomValidity('Please enter punishment that awarded')"
														oninput="setCustomValidity('')" maxlength="200"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiFatherPa"></td>
												</tr>

												<tr>
													<td>
														<div class="form-group box-border-padding textAlign">
															<label class="control-label" for="textinput"><span translate>Step father</span></label>

														</div>
													</td>
													<td><input id="stepfathercrime"
														oninvalid="this.setCustomValidity('Please enter nature of crime')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepFathernoc"></td>
													<td><input id="stepfatherlegalStaus"
														oninvalid="this.setCustomValidity('Please enter legal status of the case')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepFatherLs"></td>
													<td>
														<div class="col-md-7 rbtn-align">
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="Arrestifany"
																id="Arrestifany" ng-value="hoiStepFatherArYesValue"
																type="radio" ng-model="soiMainObj.hoiStepFatherAr"><span translate>Yes</span>
															</label> <label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="Arrestifany"
																id="Arrestifany" ng-value="hoiStepFatherArNoValue"
																type="radio" ng-model="soiMainObj.hoiStepFatherAr"><span translate>No</span>
															</label>
														</div>
													</td>
													<td><input id="periodofConfinement" only-three-digits
														oninvalid="this.setCustomValidity('Please enter period of Confinement')"
														oninput="setCustomValidity('')"
														class="form-control input-md pocTakingE" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepFatherPoc"></td>
													<td><input id="punishmentawarded"
														oninvalid="this.setCustomValidity('Please enter punishment that awarded')"
														oninput="setCustomValidity('')" maxlength="200"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepFatherPa"></td>
												</tr>
												<tr>
													<td>
														<div class="form-group box-border-padding textAlign">
															<label class="control-label" for="textinput"><span translate>Mother</span></label>

														</div>
													</td>
													<td><input id="motherNatureofCrime"
														oninvalid="this.setCustomValidity('Please enter nature of crime')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiMothernoc"></td>
													<td><input id="motherlegalstatusofthecase"
														oninvalid="this.setCustomValidity('Please enter legal status of the case')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiMotherLs"></td>
													<td>
														<div class="col-md-7 rbtn-align">
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="yArrestifanyMade"
																id="yArrestifanyMade" ng-value="hoiMotherArYesValue"
																type="radio" ng-model="soiMainObj.hoiMotherAr"><span translate>Yes</span>
															</label> <label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="yArrestifanyMade"
																id="yArrestifanyMade" ng-value="hoiMotherArNoValue"
																type="radio" ng-model="soiMainObj.hoiMotherAr"><span translate>No</span>
															</label>
														</div>
													</td>
													<td><input id="motherperiodofConfinement" only-three-digits
														oninvalid="this.setCustomValidity('Please enter period of Confinement')"
														oninput="setCustomValidity('')"
														class="form-control input-md pocTakingE" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiMotherPoc"></td>
													<td><input id="motherpunishmentawarded"
														oninvalid="this.setCustomValidity('Please enter punishment awarded')"
														oninput="setCustomValidity('')" maxlength="200"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiMotherPa"></td>
												</tr>
												<tr>
													<td>
														<div class="form-group box-border-padding textAlign">
															<label class="control-label" for="textinput"><span translate>Step Mother</span></label>

														</div>
													</td>
													<td><input id="stepMothernatureofCrime"
														oninvalid="this.setCustomValidity('Please enter nature of Crime')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepMothernoc"></td>
													<td><input id="stepmotherlegalStatus"
														oninvalid="this.setCustomValidity('Please enter legal status of the case')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepMotherLs"></td>
													<td>
														<div class="col-md-7 rbtn-align">
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arrestifanyY"
																id="arrestifanyY" ng-value="hoiStepMotherArYesValue"
																type="radio" ng-model="soiMainObj.hoiStepMotherAr"><span translate>Yes</span></label>
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arrestifanyY"
																id="arrestifanyY" ng-value="hoiStepMotherArNoValue"
																type="radio" ng-model="soiMainObj.hoiStepMotherAr"><span translate>No</span>
															</label>
														</div>
													</td>
													<td><input id="stepmotherperiodofConfinement" only-three-digits
														oninvalid="this.setCustomValidity('Please enter period of Confinement')"
														oninput="setCustomValidity('')"
														class="form-control input-md pocTakingE" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepMotherPoc"></td>
													<td><input id="stepmotherpunishmentawarded"
														oninvalid="this.setCustomValidity('Please enter punishment awarded')"
														oninput="setCustomValidity('')" maxlength="200"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiStepMotherPa"></td>
												</tr>

												<tr>
													<td>
														<div class="form-group box-border-padding textAlign">
															<label class="control-label" for="textinput"><span translate>Brother</span></label>

														</div>
													</td>
													<td><input id="brothernatureofcrime"
														oninvalid="this.setCustomValidity('Please enter nature of crime')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiBrothernoc"></td>
													<td><input id="brotherlegalstatus"
														oninvalid="this.setCustomValidity('Please enter legal status of the case')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiBrotherLs"></td>
													<td>
														<div class="col-md-7 rbtn-align">
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arrestify"
																id="arrestify" ng-value="hoiBrotherArYesValue"
																type="radio" ng-model="soiMainObj.hoiBrotherAr"><span translate>Yes</span>
															</label> <label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arrestify"
																id="arrestify" ng-value="hoiBrotherArNoValue"
																type="radio" ng-model="soiMainObj.hoiBrotherAr"><span translate>No</span>
															</label>
														</div>
													</td>
													<td><input id="brotherPeriodofConfinement" only-three-digits
														oninvalid="this.setCustomValidity('Please enter Period of Confinement')"
														oninput="setCustomValidity('')"
														class="form-control input-md pocTakingE" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiBrotherPoc"></td>
													<td><input id="brotherPunishmentawarded"
														oninvalid="this.setCustomValidity('Please enter punishment awarded')"
														oninput="setCustomValidity('')" maxlength="200"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiBrotherPa"></td>
												</tr>
												<tr>
													<td>
														<div class="form-group box-border-padding textAlign">
															<label class="control-label" for="textinput"><span translate>Sister</span></label>

														</div>
													</td>
													<td><input id="sisternatureofCrime"
														oninvalid="this.setCustomValidity('Please enter nature of Crime')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiSisternoc"></td>
													<td><input id="brotherlegalstatus"
														oninvalid="this.setCustomValidity('Please enter legal status of the case')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiSisterLs"></td>
													<td>
														<div class="col-md-7 rbtn-align">
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arresty"
																id="arresty" ng-value="hoiSisterArYesValue" type="radio"
																ng-model="soiMainObj.hoiSisterAr"><span translate> Yes</span></label> 
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="arresty"
																id="arresty" ng-value="hoiSisterArNoValue" type="radio"
																ng-model="soiMainObj.hoiSisterAr"><span translate> No</span>
															</label>
														</div>
													</td>
													<td><input id="sisterperiodofConfinement" only-three-digits
														oninvalid="this.setCustomValidity('Please enter period of Confinement')"
														oninput="setCustomValidity('')"
														class="form-control input-md pocTakingE" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiSisterPoc"></td>
													<td><input id="sisterPunishmentawarded"
														oninvalid="this.setCustomValidity('Please enter punishment awarded')"
														oninput="setCustomValidity('')" maxlength="200"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiSisterPa"></td>
												</tr>
												<tr>
													<td>
														<div class="form-group box-border-padding textAlign">
															<label class="control-label" for="textinput"><span translate>Others<br>(specify relationship)</span></label> 
															<input id="othersRelationship"
																placeholder="{{'Please specify'| translate}}" 
																oninvalid="this.setCustomValidity('Please specify')"
																oninput="setCustomValidity('')" maxlength="30"
																class="form-control textsize" type="text"
																ng-disabled="ifSubmittedDisable"
																ng-model="soiMainObj.hoiOtherFamilyMemberName">
														</div>
													</td>
													<td><input id="othercrime"
														oninvalid="this.setCustomValidity('Please enter nature of crime')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiOthersnoc"></td>
													<td><input id="otherstatus"
														oninvalid="this.setCustomValidity('Please enter legal status of the case')"
														oninput="setCustomValidity('')" maxlength="30"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiOthersLs"></td>
													<td>
														<div class="col-md-7 rbtn-align">
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="ifyes" id="ifyes"
																ng-value="hoiOthersArYesValue" type="radio"
																ng-model="soiMainObj.hoiOthersAr"><span translate> Yes</span>
									                        </label>
															<label class="radio-inline" for="radios-0"> <input
																ng-disabled="ifSubmittedDisable" name="ifyes" id="ifyes"
																ng-value="hoiOthersArNoValue" type="radio"
																ng-model="soiMainObj.hoiOthersAr"><span translate> No</span>
															</label>
														</div>
													</td>
													<td><input id="otherperiodOfconfinment" only-three-digits
														oninvalid="this.setCustomValidity('Please period of Confinement')"
														oninput="setCustomValidity('')"
														class="form-control input-md pocTakingE" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiOthersPoc"></td>
													<td><input id="otherpunushmentawarded" 
														oninvalid="this.setCustomValidity('Please enter punishment that awared')"
														oninput="setCustomValidity('')" maxlength="200"
														class="form-control input-md" type="text"
														ng-disabled="ifSubmittedDisable"
														ng-model="soiMainObj.hoiOthersPa"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div class="form-group box-border-padding marginbottomSpce">
								<label class="col-md-4 control-label" for="textinput"><span translate>13.Attitude towards religion of child and family</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input maxlength="200" id="attitudetowardsreligion" required
										placeholder="{{'Enter attitude towards religion'| translate}}" 
										oninvalid="this.setCustomValidity('Please enter attitude towards religion')"
										oninput="setCustomValidity('')" class="form-control input-md"
										ng-disabled="ifSubmittedDisable" type="text"
										ng-model="soiMainObj.religionAttitude">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>14.Present living conditions</span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="presentlivingconditions" required 
										placeholder="{{'Enter present living conditions'| translate}}" maxlength="200"
										oninvalid="this.setCustomValidity('Please enter present living conditions')"
										oninput="setCustomValidity('')" class="form-control input-md"
										ng-disabled="ifSubmittedDisable" type="text"
										ng-model="soiMainObj.livingConditions">

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>15.Other factors of importance if any</span></label>
								<div class="col-md-7">
									<input id="otherfactorsofimportance" maxlength="200"
										placeholder="{{'Enter other factors of importance'| translate}}"
										oninvalid="this.setCustomValidity('Please enter other factors of importance')"
										oninput="setCustomValidity('')" class="form-control input-md"
										ng-disabled="ifSubmittedDisable" type="text"
										ng-model="soiMainObj.otherFactorImportance">

								</div>
							</div>


							<div class="grey-header"><span translate>16. Habits of the child</span><span class="mandatory_star">&#42;</span>
							</div>

							<div class="form-group box-border-padding boxbrdr">
								<div class="col-md-6 habitshead">
									<label class="control-label" for="textinput"><span translate>A - Bad Habits</span></label>
									<div class="badhabits">
										<ul class="content-align">
											<li class="habitswidth"
												ng-repeat="b in badHabits | orderBy:'id'""><label
												class="radio" for="radios-0" ng-checked="b"> <input
													ng-disabled="ifSubmittedDisable" ng-model="b.checked"
													ng-change="setBadIds(b)" type="checkbox" class="chkbox">&nbsp;&nbsp;{{langType==0?b.name:b.typeNameHindi}}
											</label> <input ng-disabled="ifSubmittedDisable" required
												id="othersHabitspecify" ng-if="b.id==58 && b.checked" maxlength="30"
												placeholder="{{'Please Specify'| translate}}" ng-model="soiMainObj.drugType"
												class="form-control input-md physicalAbuse"  type="text">
											</li>
											<label class="radio-inline"> <input
												ng-disabled="ifSubmittedDisable" id="othersHabitspecify"
												ng-if="keyBadString.includes('61')" required maxlength="30"
												placeholder="{{'Specify'| translate}}" ng-model="soiMainObj.otherBadHabits"
												class="form-control input-md physicalAbuse" type="text"
												ng-blur="blur(soiMainObj.otherBadHabits,'otherBadHabits')"
												ng-trim="false" >
											</label>
										</ul>
										<div id="badHabitsListSocialError" class="error-style"></div>
									</div>
								</div>
								<div class="col-md-6 habitshead">
									<label class="control-label" for="textinput"><span translate>B - Good Habits</span></label>
									<div class="badhabits">
										<ul class="content1-align">
											<li class="habitswidth"
												ng-repeat="g in goodHabits | orderBy:'id'""><label
												class="radio" for="radios-0" ng-checked="g"> <input
													ng-disabled="ifSubmittedDisable" ng-model="g.checked"
													ng-change="setGoodIds(g)" type="checkbox" class="chkbox">&nbsp;&nbsp;{{langType==0?g.name:g.typeNameHindi}}
											</label></li>
											<label class="radio-inline"> <input
												ng-disabled="ifSubmittedDisable" id="othersbadHabitspecify"
												ng-if="keyGoodString.includes('55')" placeholder="{{'Specify'| translate}}"
												ng-model="soiMainObj.otherGoodHabits" required maxlength="30"
												class="form-control input-md physicalAbuse" type="text"
												ng-blur="blur(soiMainObj.otherGoodHabits,'otherGoodHabits')"
												ng-trim="false" >
											</label>
										</ul>
										<div id="goodHabitsListSocialError" class="error-style"></div>
									</div>
								</div>
							</div>

							<div class="form-group box-border-padding extraativity">
								<label class="col-md-4 control-label" for="textinput">
								<span translate>17. Extra-curricular interests</span>
								<span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="extracurricularinterests"
										ng-disabled="ifSubmittedDisable" required
										name="extracurricularinterests" maxlength="200"
										placeholder="{{'Enter name of extra-curricular interests'| translate}}"
										class="form-control input-md" type="text"
										ng-model="soiMainObj.extracurricularInterests"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="extracurricularinterestsError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>18.Outstanding characteristics and personality traits</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="childcharacteristics" name="childcharacteristics"
										ng-disabled="ifSubmittedDisable" required 
										placeholder="{{'Enter characteristics and personality traits'| translate}}"
										class="form-control input-md" type="text" maxlength="200" 
										ng-model="soiMainObj.personalityTraits"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="childcharacteristicserror" class="error-style"></div>
								</div>
							</div>

							<!---------------------------- new ------------------------->

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>19.Child's opinion/reaction towards discipline in the home</span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="opinionreaction" name="opinionreaction" 
										ng-disabled="ifSubmittedDisable" required 
										ng-model="soiMainObj.childOpinionTowardsDiscipline" maxlength="200"
										placeholder="{{'Enter child\'s opinion/reaction towards discipline in the home'| translate}}"
										class="form-control input-md" type="text">
								</div>
							</div>

							<!------------------------------ end ------------------------>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>20.Employment Details, if any</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="employmentDetails" name="employmentDetails" required
										ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter employment details'| translate}}" 
										class="form-control input-md" type="text" maxlength="200"
										ng-model="soiMainObj.employmentDetails"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="employmentDetailsError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>21.Details of income utilization</span>
								</label>
								<div class="col-md-7">
									<input id="detailsofincomeutilization"
										ng-disabled="ifSubmittedDisable"
										name="detailsofincomeutilization"
										placeholder="{{'Enter details of income utilization'| translate}}" 
										class="form-control input-md" type="text" maxlength="200"
										ng-model="soiMainObj.incomeUtilization"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="detailsofincomeutilizationError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>22.Work record (reasons for leaving vocational interests, attitude towards job or employers)</span></label>
								<div class="col-md-7">
									<input id="workrecord" name="workrecord"
										ng-disabled="ifSubmittedDisable" maxlength="200"
										placeholder="{{'Enter work record'| translate}}" class="form-control input-md"
										type="text" ng-model="soiMainObj.workRecord" 
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="workrecordError" class="error-style"></div>
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>23.The details of education of the child</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<select id="detailsofeducation" class="form-control" 
										ng-disabled="ifSubmittedDisable" required 
										ng-model="soiMainObj.education.id" 
										oninvalid="this.setCustomValidity('Please select education details')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in education" ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>24.Attitude of classmates towards the child</span>
								</label>
								<div class="col-md-7">
									<input id="attitudeofclassmates" name="attitudeofclassmates"
										ng-disabled="ifSubmittedDisable" maxlength="200"
										placeholder="{{'Enter attitude of classmates towards the child'| translate}}"
										class="form-control input-md" type="text" 
										ng-model="soiMainObj.classMatesAttitude"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="attitudeofclassmatesError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>25.Attitude of teachers towards the child</span>
								</label>
								<div class="col-md-7">
									<input id="attitudeofteachers" name="attitudeofteachers"
										ng-disabled="ifSubmittedDisable" maxlength="200"
										placeholder="{{'Enter attitude of teachers towards the child'| translate}}"
										class="form-control input-md" type="text" 
										ng-model="soiMainObj.teachersAttitude">
									<div id="attitudeofteachersError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>26.The reason for leaving School (tick as applicable)</span></label>
								<div class="col-md-6">
									<ul class="contents-align">
										<li class="reasonswidth"
											ng-repeat="r in reasonLeavingSchool | orderBy:'id'"><label
											class="radio-inline" for="radios-0" ng-checked="r"> <input
												ng-disabled="ifSubmittedDisable" ng-model="r.checked"
												ng-change="setReasonIds(r)" type="checkbox" class="chkbox">&nbsp;&nbsp;{{langType==0?r.name:r.typeNameHindi}}
										</label></li>
										<label class="radio-inline"> <input
											ng-disabled="ifSubmittedDisable" id="othersreasonpl"
											ng-if="reasonString.includes('85')" required
											placeholder="{{'Please specify'| translate}}"  maxlength="30"
											ng-model="soiMainObj.otherReasonLeavingSchool"
											class="form-control input-md physicalAbuse" type="text"
											ng-blur="blur(soiMainObj.otherReasonLeavingSchool,'otherReasonLeavingSchool')"
											ng-trim="false">
										</label>
									</ul>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>27.The details of the school in which studied last</span>
								</label>
								<div class="col-md-7">
									<select id="detailsoftheschool" class="form-control"
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.schoolType.id"
										oninvalid="this.setCustomValidity('Please select any')"
										oninput="setCustomValidity('')">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in schoolType" ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>28.Vocational training, if any</span></label>
								<div class="col-md-7">
									<input id="vocationaltraining" name="vocationaltraining"
										ng-disabled="ifSubmittedDisable" maxlength="200"
										placeholder="{{'Enter name of vocational training'| translate}}"
										class="form-control input-md" type="text"  
										ng-model="soiMainObj.vocationalTraining"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="vocationaltrainingError" class="error-style"></div>
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>29.Majority of the friends are (tick as applicable)</span></label>
								<div class="col-md-4">
									<ul>
										<li class="majoritywidth"
											ng-repeat="m in majorityFriendTypes | orderBy:'id'""><label
											class="radio-inline" for="radios-0" ng-checked="m"> <input
												ng-disabled="ifSubmittedDisable" ng-model="m.checked"
												ng-change="setMajority(m)" type="checkbox" class="chkbox">&nbsp;&nbsp;{{langType==0?m.name:m.typeNameHindi}}
										</label></li>
									</ul>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>30.Attitude of the child towards friends</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="attitudeofthechildtowardsfriends"
										ng-disabled="ifSubmittedDisable" required
										name="attitudeofthechildtowardsfriends" maxlength="200"
										placeholder="{{'Enter attitude of the child towards friends'| translate}}"
										class="form-control input-md" type="text"
										ng-model="soiMainObj.attitudeTowardsFriends"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="attitudeofthechildError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>31.Attitude of friends towards the child</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="attitudeoffriendstowardsthechild"
										ng-disabled="ifSubmittedDisable" required
										name="attitudeoffriendstowardsthechild" maxlength="200"
										placeholder="{{'Enter attitude of friends towards the child'| translate}}"
										class="form-control input-md" type="text"
										ng-model="soiMainObj.friendsAttitudeTowardsChild"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="attitudeoffriendsError" class="error-style"></div>
								</div>
							</div>

							<!------------------- new ------------------ -->

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>32.Observations of neighbours towards the child</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="neighbourstowardsthechild"
										ng-disabled="ifSubmittedDisable" required
										name="neighbours towards the child"  maxlength="200"
										placeholder="{{'Enter oservations of neighbours towards the child'| translate}}"
										class="form-control input-md" type="text"
										ng-model="soiMainObj.observationAboutNeighbourhood"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="attitudeoffriendsError" class="error-style"></div>
								</div>
							</div>


							<!----------------------------end-------------------------- -->

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>33.Observation about neighbourhood (to assess the influence of neighbourhood on the child)</span>
									<span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="observationaboutneighbourhood"
										ng-disabled="ifSubmittedDisable" required 
										name="observationaboutneighbourhood" maxlength="200" 
										placeholder="{{'Enter observation about neighbourhood'| translate}}"
										class="form-control input-md" type="text"
										ng-model="soiMainObj.observationAboutNeighbourhoodToAsses"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="observationchildNameError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>34.Whether the child has been subjected to any form of abuse, if applicable</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="victim1" id="yes"
										ng-value="childVictimYesValue" type="radio"
										ng-model="soiMainObj.childSubjectedOfAbuse"><span translate> Yes</span>
									</label>
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="victim1" id="no"
										ng-value="childVictimNoValue" type="radio"
										ng-model="soiMainObj.childSubjectedOfAbuse"> <span translate>No</span>
									</label>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>35.Types of abuse met by the child (tick as applicable)</span><span
									class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-4">
									<ul class="parent cf">
										<li><label class="radio-inline"> <b><span translate>Verbal abuse</span></b></label>
												<ul class="children">
													<li class="reasonleavewidth"
														ng-repeat="a in vebalAbusedBy | orderBy:'id'""><label
														class="radio-inline" for="radios-0" ng-checked="a">
															<input ng-model="a.checked"
															ng-disabled="ifSubmittedDisable"
															ng-change="setVerbalAbusedBy(a)" type="checkbox"
															class="chkbox">&nbsp;&nbsp;{{langType==0?a.name:a.typeNameHindi}}
													</label></li>

													<label class="radio-inline" for="radios-0"> <input
														ng-disabled="ifSubmittedDisable" id="othersplspecify"
														ng-if="verbalAbusedString.includes('125')"
														placeholder="{{'Please specify'| translate}}"  maxlength="30"
														oninvalid="this.setCustomValidity('Specify')"
														oninput="setCustomValidity('')" required
														class="form-control input-md physicalAbuse" type="text"
														ng-model="soiMainObj.otherVerbalAbuse"
														ng-blur="blur(soiMainObj.otherVerbalAbuse,'otherVerbalAbuse')"
														ng-trim="false">
													</label>
												</ul>
												
										</li>
									</ul>
									<ul class="parent cf">
										<li><label class="radio-inline"><b><span translate>Physical abuse</span></b></label>
												<ul class="children">
													<li class="reasonleavewidth"
														ng-repeat="b in physicalAbusedBy | orderBy:'id'""><label
														class="radio-inline" for="radios-0" ng-checked="b">
															<input ng-model="b.checked"
															ng-disabled="ifSubmittedDisable"
															ng-change="setphysicalAbusedBy(b)" type="checkbox"
															class="chkbox">&nbsp;&nbsp;{{langType==0?b.name:b.typeNameHindi}}
													</label></li>

													<label class="radio-inline" for="radios-0"> <input
														ng-disabled="ifSubmittedDisable" id="othersplspecify"
														ng-if="physicalAbusedString.includes('125')"
														placeholder="{{'Please specify'| translate}}" maxlength="30"
														oninvalid="this.setCustomValidity('Specify')"
														oninput="setCustomValidity('')" required
														class="form-control input-md physicalAbuse" type="text"
														ng-model="soiMainObj.otherPhysicalAbuse"
														ng-blur="blur(soiMainObj.otherPhysicalAbuse,'otherPhysicalAbuse')"
														ng-trim="false">
													</label></li>
									</ul>
									<div id="AbuseError" class="error-style"></div>
									
									</li>
									</ul>
								</div>
								<div class="col-md-4">
									<ul class="parent cf">

										<li><label class="radio-inline"><b><span translate>Sexual abuse</span></b></label>
												<ul class="children">
													<li class="reasonleavewidth"
														ng-repeat="c in sexualAbusedBy | orderBy:'id'""><label
														class="radio-inline" for="radios-0" ng-checked="c">
															<input ng-model="c.checked"
															ng-disabled="ifSubmittedDisable"
															ng-change="setSexualAbusedBy(c)" type="checkbox"
															class="chkbox">&nbsp;&nbsp;{{langType==0?c.name:c.typeNameHindi}}
													</label></li>
													<li><label class="radio-inline" for="radios-0">

															<input id="othersexualabuseSpecify"
															ng-disabled="ifSubmittedDisable"
															ng-if="sexualAbusedString.includes('125')"
															placeholder="{{'Please specify'| translate}}" maxlength="30"
															oninvalid="this.setCustomValidity('Specify')"
															oninput="setCustomValidity('')" required
															class="form-control input-md otherSexualAbuse"
															type="text" ng-model="soiMainObj.otherSexualAbuse"
															ng-blur="blur(soiMainObj.otherSexualAbuse,'otherSexualAbuse')"
															ng-trim="false">
													</label></li>
												</ul> </li>
									</ul>
									
									<ul class="parent cf">
										<li><label class="radio-inline" for="radios-0"><b><span translate>Others(pl. specify)</span></b> 
												<input id="othersAnyabusespecify"
												placeholder="{{'Please specify'| translate}}"
												ng-disabled="ifSubmittedDisable" 
												oninvalid="this.setCustomValidity('Specify')"
												oninput="setCustomValidity('')" maxlength="30"
												class="form-control input-md others" type="text"
												ng-model="soiMainObj.otherInOtherAbuse"
												ng-blur="blur(soiMainObj.otherInOtherAbuse,'otherInOtherAbuse')"
												ng-trim="false"> </label></li>
									</ul>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>36.Whether the child is a victim of any offence</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="victim" id="yes"
										ng-value="childVictimYesValue" type="radio"
										ng-model="soiMainObj.childVictim"><span translate> Yes</span>
									</label> 
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="victim" id="no"
										ng-value="childVictimNoValue" type="radio"
										ng-model="soiMainObj.childVictim"><span translate> No</span>
									</label>

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>37.Whether the child is used by any gangs or adults or group of adults or has been used for drug peddling</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="childGang"
										id="childGang" ng-value="usedByAnyGangYesValue" type="radio"
										ng-model="soiMainObj.usedByAnyGang"><span translate> Yes</span>
									</label> 
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="childGang"
										id="childGang" ng-value="usedByAnyGangNoValue"
										checked="checked" type="radio"
										ng-model="soiMainObj.usedByAnyGang"><span translate> No</span>
									</label>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>38.History/ tendency of the child to run away from home, if any</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="tendencyofthechild" name="tendencyofthechild" required
										ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter tendency of the child'| translate}}" 
										class="form-control input-md" maxlength="200"
										oninvalid="this.setCustomValidity('Please enter tendency of the child')"
										oninput="setCustomValidity('')" type="text"
										ng-model="soiMainObj.historyRunAwayFromHome"
										ng-keyUp="validateName(formInfo.personProducingChildName,'personNameerror')">
									<div id="tendencyofthechilderror" class="error-style"></div>
								</div>
							</div>

							<!---------------------- new -------------------- -->

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>39.Circumstances of apprehension of the child</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="Circumstances" name="Circumstances" required
										ng-disabled="ifSubmittedDisable" maxlength="200"
										placeholder="{{'Enter circumstances of apprehension of the child'| translate}}"
										class="form-control input-md"
										oninvalid="this.setCustomValidity('Please enter circumstances of apprehension of the child')"
										oninput="setCustomValidity('')" type="text"
										ng-model="soiMainObj.circumstancesOfApprehension">
									<div id="tendencyofthechilderror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>40.Alleged role of the child in the offence</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="tendencyofthechild" name="tendencyofthechild" required
										ng-disabled="ifSubmittedDisable" maxlength="200"
										placeholder="{{'Enter alleged role of the child in the offence'| translate}}"
										class="form-control input-md"
										oninvalid="this.setCustomValidity('Please enter alleged role of the child in the offence')"
										oninput="setCustomValidity('')" type="text"
										ng-model="soiMainObj.allegedRoleInOffence"
										ng-keyUp="validateName(formInfo.personProducingChildName,'personNameerror')">
									<div id="tendencyofthechilderror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>41.Reason for alleged offence</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
									<ul>
										<li class="reasonforalleged"
											ng-repeat="r in reasonForAllegedOffence | orderBy:'id'""><label
											class="radio-inline" for="radios-0" ng-checked="r"> <input
												ng-disabled="ifSubmittedDisable" ng-model="r.checked"
												ng-change="setReasonsForallegedOffence(r)" type="checkbox"
												class="chkbox">&nbsp;&nbsp;{{langType==0?r.name:r.typeNameHindi}}
										</label></li>
										<li><input id="otherss" ng-disabled="ifSubmittedDisable"
											ng-if="reasonsForallegedOffenceString.includes('368')"
											placeholder="{{'Please specify'| translate}}" maxlength="200"
											oninvalid="this.setCustomValidity('Please enter')"
											oninput="setCustomValidity('')" required
											class="form-control input-md reasonLeaving" type="text"
											ng-model="soiMainObj.otherReasonForAllegedOffence"
											ng-blur="blur(soiMainObj.otherReasonForAllegedOffence,'otherReasonForAllegedOffence')"
											ng-trim="false"></li>
									</ul>
									<div id="allegedOffenceError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>42.Whether the child has been apprehended earlier for any offence,if yes give details including stay in a child care institution</span></label>
								<div class="col-md-7">
									<label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="apprehendedOffence"
										id="apprehendedOffence" ng-value="apprehendedYesValue"
										type="radio" ng-model="soiMainObj.apprehendedForOffence"><span translate>Yes</span>
									</label> <label class="radio-inline" for="radios-0"> <input
										ng-disabled="ifSubmittedDisable" name="apprehendedOffence"
										id="apprehendedOffence" ng-value="apprehendedNoValue" ng-click="clearApprehendedOther()"
										type="radio" ng-model="soiMainObj.apprehendedForOffence"><span translate>No</span>
									</label>
								</div>
							</div>
							<div class="form-group box-border-padding"
								style="margin-top: -25px;"
								ng-if="soiMainObj.apprehendedForOffence">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-5">
									<input id="offenseOther" name="offenseOther"
										ng-disabled="ifSubmittedDisable" maxlength="200"
										ng-model="soiMainObj.apprehendedForOffenceDtls"
										placeholder="{{'Please specify'| translate}}" class="form-control input-md"
										type="text"
										ng-blur="blur(soiMainObj.apprehendedForOffenceDtls,'apprehendedForOffenceDtls')"
										ng-trim="false">
									<div id="mentalconditionError" class="error-style"></div>
								</div>
							</div>

							<!---------------------- end -------------------- -->

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>43.Previous institutional data if any</span></label>
								<div class="col-md-7">
									<select id="previousData" class="form-control"
										ng-disabled="ifSubmittedDisable"
										ng-model="soiMainObj.institutionDocType.id"
										oninvalid="this.setCustomValidity('Select ')"
										oninput="setCustomValidity('')" 
										ng-change="fetchInstituteType(soiMainObj.institutionDocType)">
										<option value="" disabled selected>{{'Select' | translate}}</option>
										<option ng-repeat="item in institutionDocType"
											ng-value="{{item.id}}">{{langType==0?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>

							<!-- done upto here -->
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>44.Physical appearance of the child</span><span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="physicalcondition" name="physicalcondition"
										ng-disabled="ifSubmittedDisable" required
										placeholder="{{'Enter physical appearance of the child'| translate}}"
										class="form-control input-md" type="text" maxlength="200"
										ng-model="soiMainObj.physicalAppearance"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="physicalconditionError" class="error-style"></div>
								</div>
							</div>

							<!-------------------------new  ----------------------------->

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>45.Health condition of the child </span>
									<span class="mandatory_star">&#42;</span>
									<span translate>(including medical examination report, if applicable)</span>
								</label>
								<div class="col-md-3">
									<input id="Health condition" name="Health condition"
										placeholder="{{'Enter health condition of the child'| translate}}"
										class="form-control input-md" type="text"
										ng-disabled="ifSubmittedDisable" maxlength="200"
										ng-model="soiMainObj.healthCondition" required
										style="margin-top: 10px;">
								</div>
								<!-- <div class="form-group box-border-padding"> -->
								<!-- <label class="col-md-4 control-label" for="textinput" ng-if="!ifSubmittedDisable">Upload image<span class="mandatory_star">&#42;</span></label> -->
								<div>
									<input ng-if="!ifSubmittedDisable" type="file"
										name="childImage[]" id="js-upload-files" multiple
										ng-files="getReport($files,'childImage')" required
										accept=".png, .jpg, .jpeg, .pdf" style="margin-top: 20px;">
								</div>

<!-- 								<img style="margin-left: 60px; margin-top: -25px;" -->
<!-- 									ng-if="!ifSubmittedDisable" src={{childImage}} alt="No image" -->
<!-- 									height="45" width="45" data-action="zoom"> -->
<!-- 								<div class="col-md-1"> -->
<!-- 									<img ng-show="ifSubmittedDisable" src={{soiMainObj.childPhoto}} -->
<!-- 										alt="No image" height="80" width="80" data-action="zoom"> -->
<!-- 								</div> -->
								<div class="col-md-1" style="margin-top: 11px;">
									<button id="button2id" name="button2id" class="btn otherbut"
										ng-show="ifSubmittedDisable"
										ng-click="downloadImg(soiMainObj.childPhoto)" class="close"><span translate>Download</span></button>
								</div>



								<!-- </div> -->

							</div>
					

							<!----------------------------end ------------------------->
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>46.Mental condition of the child</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="mentalcondition" name="mentalcondition" 
										ng-disabled="ifSubmittedDisable" required
										placeholder="{{'Enter mental condition of the child'| translate}}"
										class="form-control input-md" type="text" maxlength="200"
										ng-model="soiMainObj.mentalCondition">
									<div id="mentalconditionError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>47.Any other remark</span></label>
								<div class="col-md-7">
									<input id="mentalcondition" name="mentalcondition"
										ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter any other remark'| translate}}" 
										class="form-control input-md" type="text"
										ng-model="soiMainObj.anyOtherRemark" maxlength="200">
									<div id="mentalconditionError" class="error-style"></div>
								</div>
							</div>

							<div class="grey-header"><span translate>48. RESULT OF INQUIRY</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i)Emotional factors</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="emotionalFactors" ng-disabled="ifSubmittedDisable"
										required placeholder="{{'Enter emotional factors'| translate}}" maxlength="200"
										oninvalid="this.setCustomValidity('Enter emotional factors)"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.roiEmotionalFactors">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii)Physical condition</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="physicalcondition" ng-disabled="ifSubmittedDisable"
										required placeholder="{{'Enter physical condition'| translate}}" maxlength="200"
										oninvalid="this.setCustomValidity('Enter physical condition')"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.roiPhysicalCondition">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii)Intelligence</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="intelligence" placeholder="{{'Enter intelligence'| translate}}"
										ng-disabled="ifSubmittedDisable" required
										oninvalid="this.setCustomValidity('')" maxlength="200"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.roiIntelligence">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv)Social and economic factors</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="socialeconomicFactors"
										ng-disabled="ifSubmittedDisable" required
										placeholder="{{'Enter social and economic factors'| translate}}" maxlength="200"
										oninvalid="this.setCustomValidity('')" 
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.roiSocialEconomicFactors">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v)Suggestive causes of the problems</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="suggestiveCausesoftheProblems" 
										ng-disabled="ifSubmittedDisable" required
										placeholder="{{'Enter suggestive causes of the problems'| translate}}" maxlength="200"
										oninvalid="this.setCustomValidity('Enter suggestive causes of the problems')"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.roiSuggestiveCausesProblems">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vi)Analysis of the case, including reasons/contributing factors for the offence</span>
									<span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-7">
									<input id="analysisofthecase" ng-disabled="ifSubmittedDisable"
										required placeholder="{{'Enter analysis of the case'| translate}}" maxlength="200"
										oninvalid="this.setCustomValidity('Enter analysis of the case')"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.roiAnalysisOfCase">
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vii)Opinion of experts consulted</span>
									<span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="opinionofexpertsconsulted" required
										ng-disabled="ifSubmittedDisable" maxlength="200"
										placeholder="{{'Enter opinion of experts consulted'| translate}}"
										oninvalid="this.setCustomValidity('Enter opinion of experts consulted')"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.roiOpinionExperts">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(viii)Recommendation of</span></label>
								<div class="col-md-7">
									<input id="riskAnalyse" readonly 
										placeholder="{{'The option selected for Q.3 will appear here'| translate}}"
										oninvalid="this.setCustomValidity('The option selected for Q.3 will appear here')"
										oninput="setCustomValidity('')" class="form-control input-md"
										ng-model="lang=='en'?fetchedReportPreparedBy.name:fetchedReportPreparedBy.typeNameHindi" type="text">
								</div>
							</div>
							
							<div class="grey-header"><span translate>49. BANK ACCOUNT DETAILS</span></div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Account holder name</span></label>
								<div class="col-md-7">
									<input id="accountholdername" ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter Account holder name'| translate}}" maxlength="50"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.accountholdername">
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Bank name</span></label>
								<div class="col-md-7">
									<input id="bankname" ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter name of the bank'| translate}}" maxlength="50"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.bankname">
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Branch</span></label>
								<div class="col-md-7">
									<input id="branch" ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter name of the branch'| translate}}" maxlength="50"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.branch">
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) IFSC Code</span></label>
								<div class="col-md-7">
									<input id="ifsccode" ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter IFSC code'| translate}}" maxlength="11"
										oninput="setCustomValidity('')" class="form-control input-md"
										type="text" ng-model="soiMainObj.ifsccode">
								</div>
							</div>		
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v) Bank A/c no. </span></label>
								<div class="col-md-7">
									<input id="physicalcondition" name="physicalcondition"
										ng-disabled="ifSubmittedDisable"
										placeholder="{{'Enter Bank A/c No.'| translate}}"
										class="form-control input-md" type="text" only-twenty-digits
										ng-model="soiMainObj.bankAccountNo"
										ng-keyup="validateName(formInfo.childName,'childNameError')">
									<div id="physicalconditionError" class="error-style"></div>
								</div>
							</div>

							<div style="text-align: center; margin-top: 27px;">
								<!-- 								<button ng-show="showUpdateBtn" ng-click="update()" -->
								<!-- 									class="btn btn-info" type="submit">Update</button> -->
								<button ng-show="showUpdateBtn"
									ng-click="printCiclSocialInvestigationSubmitData()"
									class="btn btn-info" type="submit"><span translate>Print</span></button>
								<button ng-show="showSubmitBtn" id="button1id" name="button1id"
									class="btn btn-info" type="submit"
									ng-click="socialIR.$invalid ? '' : validateForm()"><span translate>Submit</span></button>
								<!-- 									<button ng-show="showSubmitBtn" class="btn btn-info" type="submit" ng-click="saveData()">Submit</button> -->
								<!-- 								<button id="button2id" name="button2id" class="btn btn-info" -->
								<!-- 									type="reset">Reset</button> -->
							</div>
							<div class="modal fade" id="childCiclSocialInvestigationModal"
								tabindex="-1" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-body">
												<h4 class="modal-title" id="myModalLabel"><span translate>The social investigation report of the child has been successfully saved.</span></h4>
												<p style="text-align: center">
												<button id="button5id" name="button6id" class="btn btn-info"
													type="submit" class="close" data-dismiss="modal"
													aria-hidden="true"
													ng-click="printCiclSocialInvestigationSubmitData()"><span translate>Print</span></button>
												<button id="button5id" name="button5id" class="btn btn-info"
													type="submit" class="close" data-dismiss="modal"
													aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
											</p>
										</div>
									</div>
								</div>
							</div>
							<a href="#" class="back-to-top" style="display: inline;">
								<i class="fa fa-arrow-circle-up"></i>
							</a>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="errorImageModal" tabindex="-1"
			role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
							<h4 class="modal-title" id="myModalLabel"><span translate>Uploaded file is not in correct format.</span></h4>
							<p style="text-align: center">
							<button id="button5id" name="button5id" class="btn btn-info"
								type="submit" class="close" data-dismiss="modal"
								aria-hidden="true">Ok</button>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="filenotfound" tabindex="-1"
			role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
							<h4 class="modal-title" id="myModalLabel"><span translate>File not found.</span></h4>
							<p style="text-align: center">
							<button id="button5id" name="button5id" class="btn btn-info"
								type="submit" class="close" data-dismiss="modal"
								aria-hidden="true"><span translate>Ok</span></button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./common/cctsFooter.jsp" />

	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<!-- <script type="text/javascript" src="resources/js/angular.min.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
	<script type="text/javascript" src="resources/js/download.js"></script>

	<script>
		var app = angular.module('socialInvest', ['gettext']);
		var myAppConstructor = angular.module('socialInvest');
	</script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularController/ciclSocialInvestigationController.js"></script>
<!-- 					<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
	<!-- 	<script src="resources/js/AngularController/child_basic.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
	<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// 			$("#basicdetail").on('submit', function() {
			// 				$('#socialInvestigationModal').modal('show');
			// 			});

			$("#datepicker").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d',
				changeYear: true
			});
			// 			$( "#datepicker" ).datepicker('setDate', 'today');
			$("#datepicker2").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d',
				changeYear: true
			});
			// 			$( "#datepicker2" ).datepicker('setDate', 'today');
		});
	</script>
	<script>
		$(document).ready(function() {
			var checkbox = $('#otherverbal');
			var dependent = $('#showotherText');
			if (checkbox.attr('checked') !== undefined) {
				dependent.show();
			} else {
				dependent.hide();
			}

			checkbox.change(function(e) {
				dependent.toggle();
			});
		});
	</script>
	<script type="text/javascript">
	   
		$(document).ready(function() {
			//reloadTrimming();
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
