<!DOCTYPE html>

<%@page import="org.sdrc.cpis.util.Constants"%>
<%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
	<title>Case History - Form 43</title>
	
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
	<spring:url value="/resources/css/style.css" var="styleCss" />
	<link href="${styleCss}" rel="stylesheet" />
	<link rel="stylesheet" href="resources/css/styles.css">
	<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"	var="jQueryUiCss" />
	<link href="${jQueryUiCss}" rel="stylesheet"></link>
</head>


<body ng-app="caseHistoryApp" ng-controller="caseHistoryController"	ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
<%
				UserDetailModel user = null;
				
				user = (UserDetailModel)request.getSession().getAttribute(Constants.USER_PRINCIPAL);
				Integer designationId=user.getDesignationId();
				Integer cciId=user.getCciId();
				%>
				<input type="hidden" id="designationId" value = "<%=designationId%>" >
	<div class="modal fade" id="confirmationModalForCaseHistory"
		tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to save the details.</span></h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id" class="btn btn-info bigbutton"
							type="submit" ng-click="saveData()" class="close"
							data-dismiss="modal" aria-hidden="true">{{'Submit' | translate}}</button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true">{{'Back' | translate}}</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="printCaseHistory"
		tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><span translate>The form has been saved successfully.</span></h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id" class="btn btn-info bigbutton"
							type="submit" ng-click="printCaseHistoryData()" class="close"
							data-dismiss="modal" aria-hidden="true">{{'Print' | translate}}</button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="accordionDeletionModal"
		tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><span translate>Are you sure you want to delete</span></h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id" class="btn btn-info bigbutton"
							type="submit" ng-click="deleteAccordion(whichAccord)" class="close"
							data-dismiss="modal" aria-hidden="true"><span translate>Confirm</span></button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Cancel</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="caseHistoryNotFilledModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <p style="text-align:center">
	          	<h4 class="selectChildalign" translate>Case History for this child is yet to be filled.</h4>
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="finalOrderFilledModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <p style="text-align:center">
	          	<h4 class="selectChildalign" translate>You can not fill this form.Final Order of this child had already been passed.</h4>
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true" class="close" >OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>

	<div class="container" ng-cloak>
		<div class="row">
			<div class="col-md-12">

				<div class="box-border box-border-padding">
				<!-- <hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr> -->	
					<div class="childlist-heading1 borderPersonal">
						<span translate>CASE HISTORY OF THE CHILD (FOR CHILD CARE INSTITUTION)</span><br><span translate>FORM 43</span><br><span translate>[Rule 69 (H) (3)]</span>
					</div>
					
					<input type="hidden" id="childId" value="${selectedChild}">
					
					<form class="form-horizontal basicchildform" name="caseHistory" 
						id="caseHistory">
						<fieldset>
							<div class="grey-header marginTop"
								style="border-top: none; margin-top: 4px;"><span translate>CASE HISTORY</span></div>
							<div class="col-md-12 summaryspace">
								<div class="col-md-6 childidheader"
									style="margin-left: 0px !important; padding-left: 0px !important;">
									<div class="social_headng">
										<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" style="width:6%;" />
										<span><span translate>Child ID:</span>&nbsp;&nbsp;${selectedChild}</span>
									</div>
								</div>
								<div class="col-md-6 childnameheader"
									style="margin-right: 0px !important; padding-right: 0px !important;">
									<div class="social_headng1">
										<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{prefetchData.childName}}</span>
									</div>
								</div>
							</div>

							<div class="form-group box-border-padding interimPlanmargintop">
								<label class="col-md-4 control-label" for="textinput"><span translate>1.Case/ProfileNo.</span></label>
								<div class="col-md-7">
									<input id="CaseProfileNo" name="CaseProfileNo"
										placeholder="{{'Enter Case/Profile No'| translate}}"
										class="form-control input-md" type="text" readonly
										ng-model="prefetchData.caseNum">
									<div id="casenoerror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>2. Date</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-2 col-sm-6 col-xs-6">
									<input type="text" id="datepicker"
										readonly class="form-control" ng-show="!viewPage">
									<input type="text" ng-model="formInfo.date"
										readonly class="form-control" ng-show="viewPage">	
									<div id="childProducedDateerror" class="error-style"></div>
								</div>
								<i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>3. Time</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4" ng-if="!viewPage">
								   <select ng-options="item as item for item in hour" ng-model="$parent.hr" required class="form-control"
						           style="width:32%; display:inline;" oninvalid="this.setCustomValidity('Please select hour')"
						           oninput="setCustomValidity('')">
						           		<option value="" disabled selected><span translate>HH</span></option>
						           </select>
						           <select ng-options="item as item for item in min" ng-model="$parent.minute" required class="form-control"
						           style="width:32%; display:inline;" oninvalid="this.setCustomValidity('Please select minute')"
						           oninput="setCustomValidity('')">
						           		<option value="" disabled selected><span translate>MM</span></option>
						           </select>
						           <select ng-options="item as item for item in ampm" ng-model="$parent.ap" required class="form-control"
						           style="width:32%; display:inline;" oninvalid="this.setCustomValidity('Please select period')"
						           oninput="setCustomValidity('')">
						           		<option value="" disabled selected><span translate>AM/PM</span></option>
						           </select>
								</div>
								
								<div class="col-md-2" ng-if="viewPage">
									<input type="text" ng-model="time" readonly class="form-control">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>4.UploadPhoto</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-3" ng-if="!viewPage">
									<input type="file" id="uploadChildPhoto" ng-files="getReport($files,'childImgpath')"
										accept=".png, .jpg, .jpeg">
									<div id="uploadPhotoError" class="error-style"></div>
								</div>
								<div class="col-md-3">
									<img style="margin-left: 100px;" ng-src="{{childImgpath}}" alt="No image" height="45" width="45" data-action="zoom">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>5. Name</span></label>
								<div class="col-md-7">
									<input id="caseHistoryChildname" placeholder="{{'Enter name'| translate}}"
										class="form-control input-md" type="text" readonly 
										ng-model="prefetchData.childName">

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>6. Sex</span> </label>
								<div class="col-md-7">
									<input id="caseHistoryChildSex" placeholder="{{'Enter sex'| translate}}"
										class="form-control input-md" type="text" readonly
										ng-model="lang=='en'?prefetchData.sexObject.name:prefetchData.sexObject.typeNameHindi">
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>7. Age at the time of admission </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="admissionAge" placeholder="{{'Enter age at the time of admission'| translate}}"
										class="form-control input-md" type="number" name="ageAtTimeAdmission"
										oninvalid="this.setCustomValidity('Please enter age of the child')"
										oninput="setCustomValidity('')" required
										ng-model="formInfo.ageAtTimeOfAdmission"
										ng-keyUp="validateName(formInfo.ageAtTimeOfAdmission,'ageatAdmission')"
										 ng-disabled="viewPage">
									<div id="ageatAdmission" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>8. Present Age</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="presentAge" placeholder="{{'Enter present age'| translate}}"
										class="form-control input-md" type="number" name="presentAge"
										oninvalid="this.setCustomValidity('Please enter age of the child')"
										oninput="setCustomValidity('')" required
										ng-model="formInfo.presentAge"
										ng-keyUp="validateName(formInfo.presentAge,'presentage')"
										 ng-disabled="viewPage">
									<div id="presentage" class="error-style"></div>
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>9. Category </span><span class="mandatory_star">&#42;</span><br><span translate>(tick as applicable)</span></label>
								<div class="col-md-4">
									<ul ng-if="!viewPage">
										<li ng-repeat="category in childCategory" ng-checked="category">
											<label class="radio-inline">
													<input type="checkbox" ng-model="category.checked"
													ng-change="countCategory(category);validateCheckbox(category,'anyOtherCategoryError')" >
													&nbsp;&nbsp;{{langType==0?category.name:category.typeNameHindi}}
											</label>
<!-- 											 thirty-characters-validation -->
										 	<input ng-if="category.id==181 && category.checked == true" maxlength="30"
												id="anyOtherCategory" placeholder="{{'Give details'| translate}}" name="detailsOfCategory"
												class="form-control input-md" type="text" required
												ng-model="formInfo.categoryDetails"
												ng-blur="blur(formInfo.categoryDetails,'categoryDetails')"
											 	ng-trim="false"> 
<!-- 											 	 thirty-characters-validation -->
								 		 	<input name="otherDetailsCategory" maxlength="30"
												ng-if="category.id==183 && category.checked == true"
												id="anyOtherCategory1" placeholder="{{'Please Specify'| translate}}"
												class="form-control input-md" type="text" required
												ng-model="formInfo.categoryDetailsOther"
												ng-blur="blur(formInfo.categoryDetailsOther,'categoryDetailsOther')"
													 ng-trim="false">
										</li>
									</ul>
									<div id="anyOtherCategoryError" class="error-style"></div>
									<textarea readonly ng-model="formInfo.categoryName" ng-if="viewPage" 
										class="form-control input-md"></textarea>
										
									<input ng-if="formInfo.categoryDetails && viewPage" type="text"
											class="form-control input-md margnfrombox" readonly
											ng-model="formInfo.categoryDetails">
											
									<input ng-if="formInfo.categoryDetailsOther && viewPage"
											class="form-control input-md margnfrombox" type="text" readonly
											ng-model="formInfo.categoryDetailsOther">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>10. Religion </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select ng-if="!viewPage"
										ng-options="religion as langType==0?religion.name:religion.typeNameHindi for religion in religionList"
										name="religion" ng-model="formInfo.religionObject" required
										class="form-control" ng-change="religionOthers()">
										<option value="" disabled selected><span translate>{{'Select Religion' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.religionObject.name:formInfo.religionObject.typeNameHindi" ng-if="viewPage" 
										class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="formInfo.religionObject.id == 187">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Please Specify</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
<!-- 								 thirty-characters-validation -->
									<input required id="idTypeOther" placeholder="{{'Please Specify'| translate}}" maxlength="30"
										ng-disabled="viewPage" ng-blur="blur(formInfo.religionOthers,'religionOthers')"
												 ng-trim="false"
										class="form-control input-md" type="text"
										ng-model="formInfo.religionOthers">
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="formInfo.religionObject.id == 184">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Caste</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caste" name="caste" class="form-control" required
										ng-model="formInfo.casteObject" ng-if="!viewPage"
										ng-options="caste as langType==0?caste.name:caste.typeNameHindi for caste in casteList">
										<option value="" disabled selected><span translate>{{'Select Caste' | translate}}</span></option>
									</select>
									<input readonly ng-model="lang=='en'?formInfo.casteObject.name:formInfo.casteObject.typeNameHindi" ng-if="viewPage" 
										class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>11. State </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
<!-- 								 thirty-characters-validation -->
									<input id="idTypeOther" placeholder="{{'Enter state name'| translate}}"
										ng-disabled="viewPage" required name="state" maxlength="30"
										class="form-control input-md" type="text"
										ng-model="formInfo.nativeState">
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>12. Native District</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
<!-- 								 thirty-characters-validation -->
									<input id="idTypeOther" placeholder="{{'Enter native district name'| translate}}"
										ng-disabled="viewPage" required name="nativeDistrict" maxlength="30"
										class="form-control input-md" type="text"
										ng-model="formInfo.nativeDistrict">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><b><span translate>13. Description of the Housing</span> </b></label>
								<div class="col-md-7"></div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Types of Construction </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caste" name="caste" class="form-control"
										ng-if="!viewPage" required
										ng-model="formInfo.descriptionOfHousing1Object"
										ng-options="types as langType==0?types.name:types.typeNameHindi for types in typeOfConstructionList">
										<option value="" disabled selected><span translate>{{'Select type of construction'  | translate}}</span></option>
									</select>
									
									<input id="idTypeOther" placeholder="{{'Please specify'| translate}}"
										ng-if="viewPage" readonly
										class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.descriptionOfHousing1Object.name:formInfo.descriptionOfHousing1Object.typeNameHindi">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) No.of Rooms</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select class="form-control" ng-if="!viewPage" required
										ng-model="formInfo.descriptionOfHousing2Object"
										ng-options="rooms as langType==0?rooms.name:rooms.typeNameHindi for rooms in noOfRoomsList">
										<option value="" disabled selected><span translate>{{'Select no. of rooms'  | translate}}</span></option>
									</select>
									
									<input id="idTypeOther" placeholder="{{'Please specify'| translate}}"
										ng-if="viewPage" readonly
										class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.descriptionOfHousing2Object.name:formInfo.descriptionOfHousing2Object.typeNameHindi">
								</div> 
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Types of Occupancy </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caste" name="caste" class="form-control"
										ng-if="!viewPage" required
										ng-model="formInfo.descriptionOfHousing3Object"
										ng-options="occupancy as langType==0?occupancy.name:occupancy.typeNameHindi for occupancy in typeOfOccupancyList">
										<option value="" disabled selected><span translate>{{'Select' | translate}} </span></option>
									</select>
									
									<input id="idTypeOther" placeholder="{{'Please specify'| translate}}"
										ng-if="viewPage" readonly
										class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.descriptionOfHousing3Object.name:formInfo.descriptionOfHousing3Object.typeNameHindi">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>14. By whom the child was brought before the Child Welfare Committee/Juvenile Justice Board</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="childBroughtByWhom" name="childBroughtByWhom"
										class="form-control" ng-if="!viewPage" required
										ng-options="broughtBy as langType==0?broughtBy.name:broughtBy.typeNameHindi for broughtBy in childBroughtByWhomList"
										ng-model="formInfo.childBroughtBeforeCWCByWhomObject"
										ng-change="childBroughtByWhomFunction()">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input ng-if="viewPage" readonly class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.childBroughtBeforeCWCByWhomObject.name:formInfo.childBroughtBeforeCWCByWhomObject.typeNameHindi">
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="formInfo.childBroughtBeforeCWCByWhomObject.id == 204 || formInfo.childBroughtBeforeCWCByWhomRelationship">
								<label class="col-md-4 control-label" for="textinput"><span translate>Please specify the relationship</span></label>
								<div class="col-md-4">
<!-- 								one-twenty-eight -->
									<input id="bywhombroughtChild" maxlength="128"
										placeholder="{{'Specify relationship'| translate}}" required
										class="form-control input-md" type="text" 
										ng-model="formInfo.childBroughtBeforeCWCByWhomRelationship"
										ng-disabled="viewPage">

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>15. The reason for leaving Family </span><span class="mandatory_star">&#42;</span><br><span translate>(tick as applicable)</span></label>
								<div class="col-md-4">
									<ul ng-if="!viewPage">
										<li	ng-repeat="reason in reasonsLeavingFamilyList | orderBy:'id'">
											<label class="radio-inline"> <input 
												type="checkbox" ng-model="reason.checked"
												ng-change="countReasonForLeavingFamily(reason);">
												&nbsp;&nbsp;{{langType==0?reason.name:reason.typeNameHindi}}
											</label>
										</li>
									</ul>
									<div id="reasonError" class="error-style"></div>
									<textarea readonly ng-model="formInfo.reasonsForLeavingFamilyName"
										ng-if="viewPage" class="form-control input-md margnfrombox"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="showReasonForLeavingFamilyOtherField == true || formInfo.reasonsForLeavingFamilyOthers">
								<label class="col-md-4 control-label" for="textinput"><span translate>Please specify</span></label>
								<div class="col-md-4">
<!-- 								twohundred-characters-validation -->
									<input ng-blur="blur(formInfo.reasonsForLeavingFamilyOthers,'reasonsForLeavingFamilyOthers')"
										maxlength="200"
									 	ng-trim="false" id="reasonForLeavingFamily" placeholder="{{'Specify reason'| translate}}"
										class="form-control input-md" type="text" required ng-disabled="viewPage"
										ng-model="formInfo.reasonsForLeavingFamilyOthers">

								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>16. Types of abuse met by the child</span><span class="mandatory_star">&#42;</span><br><span translate>(tick as applicable)</span></label>
								<div class="col-md-4">
									<label class="radio-inline"><b><span translate>(i) Verbal Abuse</b></span></label>
									<ul ng-if="!viewPage">
										<li
											ng-repeat="verbalAbuse in verbalAbusedByList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="verbalAbuse.checked"
												ng-change="verbalAbuseCount(verbalAbuse)">
												&nbsp;&nbsp;{{langType==0?verbalAbuse.name:verbalAbuse.typeNameHindi}}
										</label>
										</li>
<!-- 										 twohundred-characters-validation -->
										<li ng-if="verbalAbuseFlag == true"><input maxlength="200"
											placeholder="{{'Please Specify'| translate}}"
											ng-blur="blur(formInfo.verbalAbuseMetByChildOthers,'verbalAbuseMetByChildOthers')"
										 	ng-trim="false" name="otherVerbalAbuse" required type="text"
											oninvalid="this.setCustomValidity('Please specify')"
											oninput="setCustomValidity('')"
											class="form-control input-md others othersVerbal" 
											ng-model="formInfo.verbalAbuseMetByChildOthers">
										</li>
									</ul>
									<div id="verbalError" class="error-style"></div>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.verbalAbuseMetByChildName"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.verbalAbuseMetByChildOthers" readonly>
										</li>
									</ul>

									<label class="radio-inline"><b><span translate>(iii) Physical Abuse</span></b></label>
									<ul ng-if="!viewPage">
										<li
											ng-repeat="physicalAbuse in physicallAbusedByList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox"
												ng-model="physicalAbuse.checked"
												ng-change="physicalAbuseCount(physicalAbuse)">
												&nbsp;&nbsp;{{langType==0?physicalAbuse.name:physicalAbuse.typeNameHindi}}
										</label>
										</li>
<!-- 										twohundred-characters-validation -->
										<li ng-if="physicalAbuseFlag == true"><input maxlength="200"
											placeholder="{{'Please Specify'| translate}}"
											ng-blur="blur(formInfo.physicalAbuseMetByChildOthers,'physicalAbuseMetByChildOthers')"
												 ng-trim="false"
											oninvalid="this.setCustomValidity('Please specify')"
											oninput="setCustomValidity('')" name="otherPhysicalAbuse"
											class="form-control input-md others othersVerbal" type="text"
											ng-model="formInfo.physicalAbuseMetByChildOthers" required>
										</li>
									</ul>
									
<!-- 									<div id="physicalError" class="error-style"></div> -->
									<div id="abuseErrorMain" class="error-style"></div>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.physicalAbuseMetByChildName"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.physicalAbuseMetByChildOthers" readonly>
										</li>
									</ul>
								</div>
								
								<div class="col-md-4">
									<label class="radio-inline"><b><span translate>(ii) Sexual Abuse</span></b></label>
									<ul ng-if="!viewPage">
										<li
											ng-repeat="sexualAbuse in sexualAbusedByList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="sexualAbuse.checked"
												ng-change="sexualAbuseCount(sexualAbuse)">
												&nbsp;&nbsp;{{langType==0?sexualAbuse.name:sexualAbuse.typeNameHindi}}
										</label>
										</li>
<!-- 										twohundred-characters-validation -->
										<li ng-if="sexualAbuseFlag == true"><input maxlength="200"
											placeholder="{{'Please Specify'| translate}}"
											ng-blur="blur(formInfo.sexualAbuseMetByChildOthers,'sexualAbuseMetByChildOthers')"
												 ng-trim="false"
											oninvalid="this.setCustomValidity('Please specify')"
											oninput="setCustomValidity('')" name="otherSexualAbuse"
											class="form-control input-md others othersVerbal" type="text"
											ng-model="formInfo.sexualAbuseMetByChildOthers" required>
										</li>
									</ul>
									<div id="sexualError" class="error-style"></div>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.sexualAbuseMetByChildName"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.sexualAbuseMetByChildOthers" readonly>
										</li>
									</ul>

									<label class="radio-inline" ng-if="!viewPage"><b><span translate>(iv) Other Abuse</span></b></label>
									<label class="radio-inline" ng-if="viewPage"><b> (iv) {{formInfo.otherAbuseMetByChildName}} </b></label>
									<ul ng-if="!viewPage">
<!-- 									 twohundred-characters-validation -->
										<li><input id="othersAnyabusespecify"
											class="form-control input-md others othersVerbal"
											maxlength="45"
											placeholder="{{'Specify other abuse'| translate}}"
											ng-model="formInfo.otherAbuseMetByChildName" type="text"
											ng-change="clearCheckList('otherAbusedByList', 'otherAbuseCount', formInfo.otherAbuseMetByChildName)">
											<div id="othersAnyabusespecifyError" class="error-style"></div>
										</li>
										<li ng-repeat="otherAbuse in otherAbusedByList | orderBy:'id'"
											ng-if="formInfo.otherAbuseMetByChildName">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="otherAbuse.checked"
												ng-change="otherAbuseCount(otherAbuse)">
												&nbsp;&nbsp;{{langType==0?otherAbuse.name:otherAbuse.typeNameHindi}}
										</label>
										</li>
<!-- 										 twohundred-characters-validation -->
										<li ng-if="otherAbuseFlag == true"><input maxlength="200"
											placeholder="{{'Please Specify'| translate}}"
											oninvalid="this.setCustomValidity('Please specify')"
											oninput="setCustomValidity('')" name="otherOtherAbuse"
											class="form-control input-md others othersVerbal" type="text"
											ng-model="formInfo.otherAbuseMetByChildOthers" required>
										</li>
									</ul>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.otherAbuseMetByChildNames"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.otherAbuseMetByChildOthers" readonly>
										</li>
									</ul>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>17. Types of ill-treatment met by the child</span> <span class="mandatory_star">&#42;</span><br><span translate>(tick as applicable)</span></label>
								<div class="col-md-4">
									<label class="radio-inline"><b><span translate>(i) Denial of food</span></b></label>
									<ul ng-if="!viewPage">
										<li ng-repeat="dof in illTreatmentDOFList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="dof.checked"
												ng-change="dofCount(dof)"> &nbsp;&nbsp;{{langType==0?dof.name:dof.typeNameHindi}}
										</label>
										</li>
<!-- 										 twohundred-characters-validation -->
										<li ng-if="dofFlag == true"><input placeholder="{{'Please Specify'| translate}}"
											oninvalid="this.setCustomValidity('Please specify')"
											oninput="setCustomValidity('')" name="otherDenialOfFood" maxlength="200"
											class="form-control input-md others othersVerbal" type="text"
											ng-blur="blur(formInfo.illTreatmentDOFOthers,'illTreatmentDOFOthers')"
										 	ng-trim="false"
											ng-model="formInfo.illTreatmentDOFOthers" required></li>
									</ul>
									
									<div id="denialError" class="error-style"></div>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.illTreatmentDOFName"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.illTreatmentDOFOthers" readonly>
										</li>
									</ul>

									<label class="radio-inline"><b><span translate>(iii) Beaten mercilessly</span></b></label>
									<ul ng-if="!viewPage">
										<li ng-repeat="bm in illTreatmentBMList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="bm.checked"
												ng-change="bmCount(bm)"> &nbsp;&nbsp;{{langType==0?bm.name:bm.typeNameHindi}}
										</label>
										</li>
<!-- 										 twohundred-characters-validation -->
										<li ng-if="bmFlag == true"><input placeholder="{{'Please Specify'| translate}}"
											oninvalid="this.setCustomValidity('Please specify')"
											ng-blur="blur(formInfo.illTreatmentBMOthers,'illTreatmentBMOthers')"
											ng-trim="false" maxlength="200"
											oninput="setCustomValidity('')" name="otherBeatenMercilessly"
											class="form-control input-md others othersVerbal" type="text"
											ng-model="formInfo.illTreatmentBMOthers" required></li>
									</ul>
									
									<div id="beatenError" class="error-style"></div>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.illTreatmentBMName"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.illTreatmentBMOthers" readonly>
										</li>
									</ul>

									<label class="radio-inline" ng-if="!viewPage"><b><span translate>(v) Other ill-treatment</span></b></label>
									<label class="radio-inline" ng-if="viewPage"><b>(v) {{formInfo.illTreatmentOtherName}}</b></label>
									<ul ng-if="!viewPage">
<!-- 									twohundred-characters-validation -->
										<li><input id="othersAnyabusespecify"
											class="form-control input-md others othersVerbal"
											maxlength="45"
											placeholder="{{'Please Specify'| translate}}" 
											ng-model="formInfo.illTreatmentOtherName" type="text"
											ng-change="clearCheckList('illTreatmentOtherList', 'illOtherCount', formInfo.illTreatmentOtherName)">
										</li>
										<li ng-if="formInfo.illTreatmentOtherName"
											ng-repeat="illOther in illTreatmentOtherList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="illOther.checked"
												ng-change="illOtherCount(illOther)">
												&nbsp;&nbsp;{{langType==0?illOther.name:illOther.typeNameHindi}}
										</label>
										</li>
<!-- 										twohundred-characters-validation -->
										<li ng-if="illOtherFlag == true"><input maxlength="200"
											placeholder="{{'Please Specify'| translate}}"
											oninvalid="this.setCustomValidity('Please specify')" 
											oninput="setCustomValidity('')" name="otherIllTreatment"
											class="form-control input-md others othersVerbal" type="text"
											ng-model="formInfo.illTreatmentOtherOther" required>
										</li>
									</ul>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.illTreatmentOtherNames"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.illTreatmentOtherOther" readonly>
										</li>
									</ul>
									<div id="illMainError" class="error-style"></div>
								</div>

								<div class="col-md-4">
									<label class="radio-inline"><b><span translate>(ii) Causing Injury</span></b></label>
									<ul ng-if="!viewPage">
										<li ng-repeat="ci in illTreatmentCIList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="ci.checked"
												ng-change="ciCount(ci)"> &nbsp;&nbsp;{{langType==0?ci.name:ci.typeNameHindi}}
										</label>
										</li>
<!-- 										twohundred-characters-validation -->
										<li ng-if="ciFlag == true"><input placeholder="{{'Please Specify'| translate}}"
											oninvalid="this.setCustomValidity('Please specify')" maxlength="200"
											ng-blur="blur(formInfo.illTreatmentCIOthers,'illTreatmentCIOthers')"
										 	ng-trim="false" type="text" required 
											oninput="setCustomValidity('')" name="otherCausingInjury"
											class="form-control input-md others othersVerbal" 
											ng-model="formInfo.illTreatmentCIOthers"></li>
									</ul>
									
									<div id="causingError" class="error-style"></div>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.illTreatmentCIName"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.illTreatmentCIOthers" readonly>
										</li>
									</ul>
									
									<label class="radio-inline"><b><span translate>(iv) Detention</span></b></label>
									<ul ng-if="!viewPage">
										<li ng-repeat="dp in illTreatmentDPList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="radios" type="checkbox" ng-model="dp.checked"
												ng-change="dpCount(dp)"> &nbsp;&nbsp;{{langType==0?dp.name:dp.typeNameHindi}}
										</label>
										</li>
<!-- 										twohundred-characters-validation -->
										<li ng-if="dpFlag == true"><input placeholder="{{'Please Specify'| translate}}"
											oninvalid="this.setCustomValidity('Please specify')" maxlength="200"
											ng-blur="blur(formInfo.illTreatmentDPOthers,'illTreatmentDPOthers')"
										 	ng-trim="false" type="text" required 
											oninput="setCustomValidity('')" name="otherDetention"
											class="form-control input-md others othersVerbal"
											ng-model="formInfo.illTreatmentDPOthers"></li>
									</ul>
									
									<div id="detentionError" class="error-style"></div>
									
									<ul ng-if="viewPage">
										<li>
											<textarea readonly ng-model="formInfo.illTreatmentDPName"
												class="form-control input-md"></textarea>
										</li>
										<li>
											<input class="form-control input-md others othersVerbal"
												ng-model="formInfo.illTreatmentDPOthers" readonly>
										</li>
									</ul>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>18. Exploitation faced by the child</span></label>
								<div class="col-md-4">
									<ul ng-if="!viewPage">
										<li
											ng-repeat="exploitation in exploitationFacedList | orderBy:'id'">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="exploitation.checked"
												ng-change="countExploitationFaced(exploitation)">
												&nbsp;&nbsp;{{langType==0?exploitation.name:exploitation.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									
									<textarea readonly ng-model="formInfo.exploitaionFacedByTheChildName"
										ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="countExploitationFlag == true || formInfo.exploitaionFacedByTheChildOthers">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-4">
<!-- 								twohundred-characters-validation -->
									<input ng-blur="blur(formInfo.exploitaionFacedByTheChildOthers,'exploitaionFacedByTheChildOthers')"
							 			ng-trim="false" id="reasonForLeavingFamily" placeholder="{{'Specify reason'| translate}}" 
										class="form-control input-md othersVerbal " type="text" required maxlength="45"
										ng-model="formInfo.exploitaionFacedByTheChildOthers" ng-disabled="viewPage">
								</div>
							</div>


							<div class="grey-header"><span translate>19. Health status of the child before admission</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Respiratory disorders </span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caserespiratorydisorders" class="form-control"
										ng-options="resp as langType==0?resp.name:resp.typeNameHindi for resp in childHealthStatusList"
										ng-model="formInfo.respiratoryDisorders" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.respiratoryDisorders.name:formInfo.respiratoryDisorders.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Hearing impairment</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehearingimpairment" class="form-control"
										ng-options="hearing as langType==0?hearing.name:hearing.typeNameHindi for hearing in childHealthStatusList"
										ng-model="formInfo.hearingImpairment" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.hearingImpairment.name:formInfo.hearingImpairment.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Eye diseases</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryeyediseases" class="form-control"
										ng-options="eye as langType==0?eye.name:eye.typeNameHindi for eye in childHealthStatusList"
										ng-model="formInfo.eyeDiseases" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.eyeDiseases.name:formInfo.eyeDiseases.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Dental disease </span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casedentaldisease" class="form-control"
										ng-options="dental as langType==0?dental.name:dental.typeNameHindi for dental in childHealthStatusList"
										ng-model="formInfo.dentalDisease" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.dentalDisease.name:formInfo.dentalDisease.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v) Cardiac diseases</span>  <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistorycardiacdiseases" class="form-control"
										ng-options="cardiac as langType==0?cardiac.name:cardiac.typeNameHindi for cardiac in childHealthStatusList"
										ng-model="formInfo.cardiacDiseases" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.cardiacDiseases.name:formInfo.cardiacDiseases.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vi) Skin disease </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseskindisease" class="form-control"
										ng-options="skin as langType==0?skin.name:skin.typeNameHindi for skin in childHealthStatusList"
										ng-model="formInfo.skinDisease" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.skinDisease.name:formInfo.skinDisease.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vii) Sexually transmitted diseases </span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistorydiseases" class="form-control"
										ng-options="std as langType==0?std.name:std.typeNameHindi for std in childHealthStatusList"
										ng-model="formInfo.sexuallyTransmittedDiseases" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.sexuallyTransmittedDiseases.name:formInfo.sexuallyTransmittedDiseases.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(viii) Neurological disorders </span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseneurologicaldisorders" class="form-control"
										ng-options="nd as langType==0?nd.name:nd.typeNameHindi for nd in childHealthStatusList"
										ng-model="formInfo.neurologicalDisorders" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.neurologicalDisorders.name:formInfo.neurologicalDisorders.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ix) Mental handicap </span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casementalhandicap" class="form-control"
										ng-options="md as langType==0?md.name:md.typeNameHindi for md in childHealthStatusList"
										ng-model="formInfo.mentalHandicap" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.mentalHandicap.name:formInfo.mentalHandicap.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(x) Physical handicap </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casephysicalhandicap" class="form-control"
										ng-options="pd as langType==0?pd.name:pd.typeNameHindi for pd in childHealthStatusList"
										ng-model="formInfo.physicalHandicap" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.physicalHandicap.name:formInfo.physicalHandicap.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(xi) Urinary tract infections </span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryinfections" class="form-control"
										ng-options="uti as langType==0?uti.name:uti.typeNameHindi for uti in childHealthStatusList"
										ng-model="formInfo.urinaryTractInfections" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.urinaryTractInfections.name:formInfo.urinaryTractInfections.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(xii) Other Disease </span> </label>
								<div class="col-md-7">
<!-- 								one-twenty-eight -->
									<input id="caseHistoryhealthOthers" name="Others" maxlength="100"
										 class="form-control input-md"
										placeholder="{{'Please Specify'| translate}}" ng-disabled="viewPage"
										type="text" ng-model="formInfo.otherHealthIssuesName">
								</div>
								<br> <label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-7" ng-if="formInfo.otherHealthIssuesName">
									<select id="casehistoryotherhealthissues" class="form-control"
										ng-options="ohi as langType==0?ohi.name:ohi.typeNameHindi for ohi in childHealthStatusList"
										ng-model="formInfo.otherHealthIssues" ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select status'  | translate}}</span></option>
									</select>
								</div>
								<div class="col-md-4" style="margin-top:10px;">
								<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.otherHealthIssues.name:formInfo.otherHealthIssues.typeNameHindi" readonly
										ng-if="viewPage" />
										</div>
										
							</div>



							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>20. With whom the child was staying prior to<br>admission</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryPriority" class="form-control"
										ng-options="who as langType==0?who.name:who.typeNameHindi for who in childWasStayingWithList"
										ng-model="formInfo.childWasStayingWithWhomPriorToAdmission"
										ng-change="childStayingPriorToAdmission()" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.childWasStayingWithWhomPriorToAdmission.name:formInfo.childWasStayingWithWhomPriorToAdmission.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="formInfo.childWasStayingWithWhomPriorToAdmission.id == 110">
								<label class="col-md-4 control-label" for="textinput"><span translate>Please specify</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
<!-- 								twohundred-characters-validation -->
									<input id="caseHistorystayingPriority" name="Others" maxlength="200" 
										placeholder="{{'Please Specify'| translate}}" class="form-control input-md"
										type="text" ng-disabled="viewPage" ng-trim="false" required
										ng-blur="blur(formInfo.childWasStayingWithWhomPriorToAdmissionOther,'childWasStayingWithWhomPriorToAdmissionOther')"
										ng-model="formInfo.childWasStayingWithWhomPriorToAdmissionOther">
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>21. Visit of the parents to meet the child Prior to institutionalization</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryPriorityto" class="form-control"
										ng-options="frequency as langType==0?frequency.name:frequency.typeNameHindi for frequency in frequencyOfVisitList"
										ng-model="formInfo.visitOfParentsPriorToInstitutionalizationObject" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.visitOfParentsPriorToInstitutionalizationObject.name:formInfo.visitOfParentsPriorToInstitutionalizationObject.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>22. Visit of the parents to meet the child After institutionalization </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryPriorityafter" class="form-control"
										ng-options="frequency as langType==0?frequency.name:frequency.typeNameHindi for frequency in frequencyOfVisitList"
										ng-model="formInfo.visitOfParentsAfterInstitutionalizationObject" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.visitOfParentsAfterInstitutionalizationObject.name:formInfo.visitOfParentsAfterInstitutionalizationObject.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>23. Visit of the Child to his parents Prior to institutionalization</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryPriorityto" class="form-control"
										ng-options="frequency as langType==0?frequency.name:frequency.typeNameHindi for frequency in frequencyOfVisitList"
										ng-model="formInfo.visitOfChildPriorToInstitutionalizationObject" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.visitOfChildPriorToInstitutionalizationObject.name:formInfo.visitOfChildPriorToInstitutionalizationObject.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>24. Visit of the Child to his parents After institutionalization </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryPriorityafter" class="form-control"
										ng-options="frequency as langType==0?frequency.name:frequency.typeNameHindi for frequency in frequencyOfVisitList"
										ng-model="formInfo.visitOfChildAfterInstitutionalizationObject" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.visitOfChildAfterInstitutionalizationObject.name:formInfo.visitOfChildAfterInstitutionalizationObject.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>25. Correspondence with parents Prior to institutionalization </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryCorrespondence" class="form-control"
										ng-options="frequency as langType==0?frequency.name:frequency.typeNameHindi for frequency in frequencyOfVisitList"
										ng-model="formInfo.correspondenceWithParentPriorToInstitutionalizationObject" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.correspondenceWithParentPriorToInstitutionalizationObject.name:formInfo.correspondenceWithParentPriorToInstitutionalizationObject.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>26. Correspondence with parents After institutionalization</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="casehistoryaster" class="form-control"
										ng-options="frequency as langType==0?frequency.name:frequency.typeNameHindi for frequency in frequencyOfVisitList"
										ng-model="formInfo.correspondenceWithParentAfterInstitutionalizationObject"
										ng-if="!viewPage" required>
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.correspondenceWithParentAfterInstitutionalizationObject.name:formInfo.correspondenceWithParentAfterInstitutionalizationObject.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>27. Details of disability</span></label>
								<div class="col-md-7">
<!-- 								twohundred-characters-validation -->
									<input id="detailsofDisability" maxlength="200"
										ng-model="formInfo.detailsOfDisability"
										placeholder="{{'Enter name of disability'| translate}}"
										name="detailsOfDisability" 
										class="form-control input-md" type="text"
										ng-disabled="viewPage" >
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>28. Type of Family</span> </label>
								<div class="col-md-7">
									<select id="casehistoryTypeoffamily" class="form-control"
										ng-options="family as langType==0?family.name:family.typeNameHindi for family in familyTypeList"
										ng-model="formInfo.familyTypeObject"
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.familyTypeObject.name:formInfo.familyTypeObject.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="grey-header"><span translate>29. Relationship among the family members</span> </div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Father & mother</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseHistoryfathermother" class="form-control"
										ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in familyMemberRelationshipList"
										ng-model="formInfo.relationFatherMother"
										ng-if="!viewPage" required>
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.relationFatherMother.name:formInfo.relationFatherMother.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Father & child </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseHistoryFatherchild" class="form-control" required
										ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in familyMemberRelationshipList"
										ng-model="formInfo.relationFatherChild" ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.relationFatherChild.name:formInfo.relationFatherChild.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Mother & child </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseHistoryMotherchild" class="form-control" required
										ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in familyMemberRelationshipList"
										ng-model="formInfo.relationMotherChild" ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.relationMotherChild.name:formInfo.relationMotherChild.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Father & siblings</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseFathersiblings" class="form-control" required
										ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in familyMemberRelationshipList"
										ng-model="formInfo.relationFatherSiblings" ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.relationFatherSiblings.name:formInfo.relationFatherSiblings.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v) Mother & siblings</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseMothersiblings" class="form-control"
										ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in familyMemberRelationshipList"
										ng-model="formInfo.relationMotherSiblings" ng-if="!viewPage" required>
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.relationMotherSiblings.name:formInfo.relationMotherSiblings.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vi) Child & siblings</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseChildsiblings" class="form-control"
										ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in familyMemberRelationshipList"
										ng-model="formInfo.relationChildSiblings" ng-if="!viewPage" required>
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.relationChildSiblings.name:formInfo.relationChildSiblings.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vii) Child & relative</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caseChildrelative" class="form-control"
										ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in familyMemberRelationshipList"
										ng-model="formInfo.relationChildRelative" ng-if="!viewPage" required>
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input class="form-control input-md" type="text"
										ng-model="lang=='en'?formInfo.relationChildRelative.name:formInfo.relationChildRelative.typeNameHindi" readonly
										ng-if="viewPage">
								</div>
							</div>

<!-- 							---------------HISTORY OF CRIME COMMITTED BY FAMILY MEMBERS STARTS--------------- -->

							<div class="grey-header"><span translate>30. History of crime committed by family members, if any</span></div>

							<div class="familyDetailss col-md-12" ng-if="!viewPage"
								ng-repeat="serial in historyRepetition"
								ng-init="sectionIndex = $index">
								<button data-toggle="collapse"
									data-target="{{'#childfamilyHistoryDetails'+ ($index+1)}}"
									type="button" class="addDetailPanel collapsed">
									<span translate>Add Details</span> <i class="fa fa-plus" aria-hidden="true"> </i><i
										class="fa fa-minus" aria-hidden="true"></i>
								</button>
								<div id="{{'childfamilyHistoryDetails' + ($index +1)}}"
									class="collapse">

									<div class="form-group box-border-padding"
										style="margin-top: 20px;">
										<label class="col-md-4 control-label" for="textinput"><span translate>(i) Relationship</span></label>
										<div class="col-md-7">
											<select id="chooseRelationship" name="chooseRelationship"
												class="form-control" ng-change="accordionOtherField($index,serial.relationshipWithChild)"
												ng-model="serial.relationshipWithChild"
												ng-options="relation as langType==0?relation.name:relation.typeNameHindi for relation in realtionShipWithChildList">
												<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
											</select>
										</div>
										<div id="producedBeforeCwcerror" class="error-style"></div>
									</div>
									
									
									<div class="form-group box-border-padding" ng-if="serial.relationshipWithChild.id == 335">
										<label class="col-md-4 control-label" for="textinput"><span translate>a. Please specify</span></label>
										<div class="col-md-7">
<!-- 										twohundred-characters-validation -->
											<input id="relationshipOthers" name="relationshipOthers" maxlength="70"
												placeholder="{{'Please specify relationship'| translate}}" required
												class="form-control input-md" type="text" 
												ng-model="serial.relationshipWithChildOthers"
												ng-blur="dynamicBlur(serial.relationshipWithChildOthers,sectionIndex, $index)"
											    ng-trim="false">
											<div id="natureofCrimeerror" class="error-style"></div>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Nature of Crime</span></label>
										<div class="col-md-7">
<!-- 										twohundred-characters-validation -->
											<input id="natureofCrime" name="natureofCrime" maxlength="200"
												placeholder="{{'Enter nature of crime'| translate}}" 
												class="form-control input-md" type="text"
												ng-model="serial.natureOfCrime">
											<div id="natureofCrimeerror" class="error-style"></div>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Legal status of the case</span></label>
										<div class="col-md-7">
<!-- 										twohundred-characters-validation -->
											<input id="legalstatusoftheCase" name="legalstatusoftheCase" maxlength="200"
												placeholder="{{'Enter legal status of the case'| translate}}"
												class="form-control input-md" type="text" 
												ng-model="serial.legalStatusOfTheCase">
											<div id="legalstatusoftheCaseerror" class="error-style"></div>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Arrest if any Made</span></label>
										<div class="col-md-7">
											<label class="radio-inline">
												<input name="{{'arrestIfmade' + ($index +1)}}" id="{{'arrestIfmadeT' + ($index +1)}}"
												ng-value="true" ng-model="serial.arrestIfAny"
												type="radio"> {{'Yes' | translate}}
											</label>
											<label class="radio-inline">
												<input name="{{'arrestIfmade' + ($index +1)}}" id="{{'arrestIfmadeF' + ($index +1)}}"
												checked="checked" type="radio" ng-value="false" 
												ng-model="serial.arrestIfAny"> {{'No' | translate}}
											</label>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>(v) Period of confinement(in months)</span></label>
										<div class="col-md-7">
											<input only-three-digits id="periodofconfinement" name="periodofconfinement"
												placeholder="{{'Enter period of confinement'| translate}}"
												class="form-control input-md" type="text"
												ng-model="serial.periodOfConfinement">
											<div id="periodofconfinementerror" class="error-style"></div>
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>(vi) Punishment Awarded</span></label>
										<div class="col-md-7">
<!-- 										one-twenty-eight -->
											<input id="PunishmentAwarded" name="PunishmentAwarded" maxlength="128" 
												placeholder="{{'Enter punishment awarded'| translate}}" class="form-control input-md"
												type="text" ng-model="serial.punishmentAwarded">
											<div id="PunishmentAwardederror" class="error-style"></div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-7">
									<div id="cannotAddNew" class="error-style"></div>
								</div>
							</div>

							<div class="addingnewdetails-container" ng-if="!viewPage">
								<button type="button" class="addingnewdetails"
									style="margin-left: 0px;" ng-click="addNewDetails($index)">+</button>
								<button type="button" class="addingnewdetails" ng-if="historyRepetition.length > 1"
									ng-click="deleteAccordionConfirmation('familyHistory')">-</button>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-7">
									<div id="limitExceeded" class="error-style"></div>
								</div>
							</div>
							
							<div ng-if="viewPage" ng-repeat="crime in formInfo.familyHistoryOfCrimeModels">
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>(i) Relationship</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text" 
											ng-model="lang=='en'?crime.relationshipWithChild.name:crime.relationshipWithChild.typeNameHindi" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding" ng-if="crime.relationshipWithChildOthers">
									<label class="col-md-4 control-label" for="textinput"><span translate>a. Please specify</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text" 
											ng-model="crime.relationshipWithChildOthers" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Nature of crime</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text" 
											ng-model="crime.natureOfCrime" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Legal status of the case</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text" 
											ng-model="crime.legalStatusOfTheCase" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Arrest, if any made</span></label>
									<div class="col-md-7">
										<input placeholder="{{crime.arrestIfAny != true ? 'No' : 'Yes'}}"
											class="form-control input-md" type="text" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>(v) Period of confinement</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="crime.periodOfConfinement" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>(vi)Punishment awarded</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text" 
											ng-model="crime.punishmentAwarded" readonly>
									</div>
								</div><br><br>
							</div>


<!-- 							--------------HISTORY OF CRIME COMMITTED BY FAMILY MEMBERS ENDS--------------- -->

							<div class="spaceWhite"></div>


							<div class="grey-header"><span translate>31. Properties owned by the family</span></div>


							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Landed properties</span><br><span translate>(Please specify the area)</span></label>
								<div class="col-md-7">
									<input id="landedproperties" name="landedproperties"
										placeholder="{{'Please Specify the area'| translate}}" maxlength="60"
										class="form-control input-md" type="text"
										ng-model="formInfo.landedPropertiesOBF"
										ng-disabled="viewPage">
									<div id="landedpropertieserror" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Household articles</span></label>
								<div class="col-md-7">
									<ul ng-if="!viewPage">
										<li
											ng-repeat="hhArticles in householdArticlesList | orderBy:'id'">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="hhArticles.checked">
												&nbsp;&nbsp;{{langType==0?hhArticles.name:hhArticles.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<textarea readonly ng-model="formInfo.householdArticlesOBFName"
											ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Vehicles</span></label>
								<div class="col-md-7">
									<ul ng-if="!viewPage">
										<li ng-repeat="vehicles in vehiclesOwnedList | orderBy:'id'">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="vehicles.checked">
												&nbsp;&nbsp;{{langType==0?vehicles.name:vehicles.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<textarea readonly ng-model="formInfo.vehiclesOBFName"
											ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">
									<span translate>(iv) Others<br>(Please specify) </span></label>
								<div class="col-md-7">
									<input id="othersProperties" name="othersProperties"
										placeholder="{{'Please Specify'| translate}}" class="form-control input-md"
										type="text" ng-model="formInfo.othersOBF" maxlength="200"
										ng-disabled="viewPage">
									<div id="othersPropertieserror" class="error-style"></div>
								</div>
							</div>

							<div class="grey-header"><span translate>32. Marriage details of family members</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Parents</span></label>
								<div class="col-md-7">
									<select id="parentsMarriagedetails" class="form-control"
										ng-options="type as langType==0?type.name:type.typeNameHindi for type in marraigeTypeList"
										ng-model="formInfo.parentsMarraigeTypeObject"
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.parentsMarraigeTypeObject.name:formInfo.parentsMarraigeTypeObject.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Brothers</span></label>
								<div class="col-md-7">
									<select id="brothersMarriagedetails" class="form-control"
										ng-options="type as langType==0?type.name:type.typeNameHindi for type in marraigeTypeList"
										ng-model="formInfo.brothersMarraigeTypeObject"
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.brothersMarraigeTypeObject.name:formInfo.brothersMarraigeTypeObject.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Sisters</span></label>
								<div class="col-md-7">
									<select id="sistersMarriagedetails" class="form-control"
										ng-options="type as langType==0?type.name:type.typeNameHindi for type in marraigeTypeList"
										ng-model="formInfo.sistersMarraigeTypeObject"
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.sistersMarraigeTypeObject.name:formInfo.sistersMarraigeTypeObject.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>33. Social activities of family members</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-5">
									<ul ng-if="!viewPage">
										<li ng-repeat="activity in socialActivityList | orderBy:'id'">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="activity.checked">
												&nbsp;&nbsp;{{langType==0?activity.name:activity.typeNameHindi}}
										</label>
										</li>
									</ul>
									<div id="activityError" class="error-style"></div>
									
									<textarea readonly ng-model="formInfo.socialActivitesOfFamilyMembersName"
											ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>34. Parental care towards child before admission</span></label>
								<div class="col-md-4">
									<ul ng-if="!viewPage">
										<li
											ng-repeat="care in parentalCareTowardsChildList | orderBy:'id'">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="care.checked">
												&nbsp;&nbsp;{{langType==0?care.name:care.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<textarea readonly ng-model="formInfo.parentalCareTowardsChildBeforeAdmissionName"
											ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="grey-header"><span translate>35. ADOLESCENCE HISTORY (Between 12 and 18 years)</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) At what age did the child attain puberty</span></label>
								<div class="col-md-7">
									<input id="childAge" name="childAge" placeholder="{{'Enter at what age did the child attain puberty'| translate}}"
									oninvalid="this.setCustomValidity('Please enter at what age did the child attain puberty')"
										oninput="setCustomValidity('')" 
										class="form-control input-md" type="number"
										ng-model="formInfo.childAttainPubertyAge"
										ng-blur="validateName(formInfo.childAttainPubertyAge,'childAgeError')" 
										ng-disabled="viewPage">
									<div id="childAgeError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Details of delinquent behaviour if any</span></label>
								<div class="col-md-4">
									<ul ng-if="!viewPage">
										<li	ng-repeat="dBehaviour in delinquentBehaviourList | orderBy:'id'">
											<label class="radio-inline">
												<input type="checkbox" ng-model="dBehaviour.checked"
												ng-change="countDelinquentBehaviour(dBehaviour)">
												&nbsp;&nbsp;{{langType==0?dBehaviour.name:dBehaviour.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<textarea readonly ng-model="formInfo.detailsOfDelinquentBehaviourName"
											ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="countDelinquentBehaviourFlag == true || formInfo.detailsOfDelinquentBehaviourOthers">
								<label class="col-md-4 control-label" for="textinput"><span translate>Please specify</span></label>
								<div class="col-md-4">
<!-- 								twohundred-characters-validation -->
									<input id="otherDelinquentBehaviour" name="otherDelinquentBehaviour" placeholder="{{'Please specify'| translate}}"
										ng-blur="blur(formInfo.detailsOfDelinquentBehaviourOthers,'detailsOfDelinquentBehaviourOthers')"
									 	ng-trim="false" class="form-control input-md othersVerbal" maxlength="200" 
										type="text" ng-disabled="viewPage" required
										ng-model="formInfo.detailsOfDelinquentBehaviourOthers">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Reason for delinquent behaviour</span></label>
								<div class="col-md-4">
									<ul ng-if="!viewPage">
										<li ng-repeat="reason in delinquentbehaviourReasonList | orderBy:'id'">
											<label class="radio-inline">
												<input type="checkbox" ng-model="reason.checked"
												ng-change="countDelinquentBehaviourReason(reason)">
												&nbsp;&nbsp;{{langType==0?reason.name:reason.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<textarea readonly ng-model="formInfo.reasonForDelinquentBehaviourName"
											ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="countDelinquentBehaviourReasonFlag == true || formInfo.reasonForDelinquentBehaviourOthers">
								<label class="col-md-4 control-label" for="textinput"><span translate>Please specify</span></label>
								<div class="col-md-4">
<!-- 								twohundred-characters-validation -->
									<input id="otherDelinquentBehaviourReason" name="otherDelinquentBehaviourReason" required
										maxlength="200"
										ng-blur="blur(formInfo.reasonForDelinquentBehaviourOthers,'reasonForDelinquentBehaviourOthers')"
									 	ng-trim="false" class="form-control input-md othersVerbal" placeholder="{{'Please specify'| translate}}" 
										ng-disabled="viewPage" type="text" ng-model="formInfo.reasonForDelinquentBehaviourOthers">
								</div>
							</div>

							<div class="grey-header"><span translate>36. Habits of the child </span><span class="mandatory_star">&#42;</span></div>
							<div class="form-group box-border-padding boxbrdr">
								<div class="col-md-6 habitshead">
									<label class="control-label" for="textinput"><span translate>A - Bad Habits</span></label>
									<div class="badhabits">
										<ul class="content-align" ng-if="!viewPage">
											<li ng-repeat="bHabits in badHabitsList | orderBy:'id'">
												<label class="radio-inline"> <input
													type="checkbox" ng-model="bHabits.checked"
													ng-change="badHabitCount(bHabits)">
													&nbsp;&nbsp;{{langType==0?bHabits.name:bHabits.typeNameHindi}}
											</label>
											</li>
										</ul>
										
										<div id="badHabitsListError" class="error-style"></div>
										
										
										
										<textarea readonly ng-model="formInfo.childBadHabitsName"
											ng-if="viewPage" class="form-control input-md"></textarea>
<!-- 										twohundred-characters-validation -->
										<input id="otherBadHabits" name="otherBadHabits" ng-trim="false"
											maxlength="200"
											ng-blur="blur(formInfo.childBadHabitsOthers,'childBadHabitsOthers')"
											ng-if="badHabitCountFlag == true || formInfo.childBadHabitsOthers"
											placeholder="{{'Please specify'| translate}}" class="form-control input-md othersVerbal"
											type="text" ng-model="formInfo.childBadHabitsOthers" 
											ng-disabled="viewPage" required>
									</div>
								</div>
								<div class="col-md-6 habitshead">
									<label class="control-label" for="textinput"><span translate>B -Good Habits</span></label>
									<div class="badhabits">
										<ul class="content-align" ng-if="!viewPage">
											<li ng-repeat="gHabits in goodHabitsList | orderBy:'id'">
												<label class="radio-inline"> <input
													type="checkbox" ng-model="gHabits.checked"
													ng-change="goodHabitCount(gHabits)">
													&nbsp;&nbsp;{{langType==0?gHabits.name:gHabits.typeNameHindi}}
											</label>
											</li>
										</ul>
										
										<div id="goodHabitsListError" class="error-style"></div>
										
										<textarea readonly ng-model="formInfo.childGoodHabitsName"
											ng-if="viewPage" class="form-control input-md"></textarea>
<!-- 											twohundred-characters-validation -->
										<input id="otherBadHabits" name="otherBadHabits"
											maxlength="200"
											ng-blur="blur(formInfo.childGoodHabitsOthers,'childGoodHabitsOthers')"
										 	ng-trim="false" ng-disabled="viewPage" placeholder="{{'Please specify'| translate}}"
											ng-if="goodHabitCountFlag == true || formInfo.childGoodHabitsOthers"
											required class="form-control input-md othersVerbal" 
											type="text" ng-model="formInfo.childGoodHabitsOthers">
									</div>
								</div>
							</div>

							<div class="grey-header margintop"><span translate>37. EMPLOYMENT DETAILS</span></div>

							<div id="accordion-employmentDetails" class="panel-group" ng-if="!viewPage">
								<div class="familyDetailss col-md-12 panel-default"
									ng-init="sectionIndex = $index"
									ng-repeat="serial in employRepetition">
									<button data-toggle="collapse"
										data-target="{{'#employmentDetails'+ ($index+1)}}"
										type="button" class="addDetailPanel collapsed"><span translate>
										Employment details of the child prior to entry into the Home</span><i
											class="fa fa-plus" aria-hidden="true"> </i><i
											class="fa fa-minus" aria-hidden="true"></i>
									</button>
									<div id="{{'employmentDetails' + ($index +1)}}"
										class="collapse">
										<div class="form-group box-border-padding"
											style="margin-top: 20px;">
											<label class="col-md-4 control-label" for="textinput"><span translate>a. Types of Employment</span></label>
											<div class="col-md-7">
												<select id="typesOfEmployment" name="typesOfEmployment"
													class="form-control" ng-model="serial.typeOfEmployment"
													ng-options="type as langType==0?type.name:type.typeNameHindi for type in childEmploymentDetailsList"
													ng-change="employmentOtherField($index,serial.typeOfEmployment)">
													<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
												</select>
											</div>
											<div id="typesOfEmploymenterror" class="error-style"></div>
										</div>
										
										<div class="form-group box-border-padding" ng-if="serial.typeOfEmployment.id == 343">
											<label class="col-md-4 control-label" for="textinput"><span translate>Please specify</span></label>
											<div class="col-md-7">
<!-- 											twohundred-characters-validation -->
												<input id="typesOfEmploymentOther" ng-trim="false" maxlength="25"
													ng-blur="dynamicBlur1(serial.typesOfEmploymentOther,sectionIndex, $index)"
													name="typesOfEmploymentOther" 
													placeholder="{{'Please specify'| translate}}" class="form-control input-md"
													type="text" ng-model="serial.typesOfEmploymentOther">
												<div id="typesOfEmploymentOtherError" class="error-style"></div>
											</div>
										</div>

										<div class="form-group box-border-padding">
											<label class="col-md-4 control-label" for="textinput"><span translate>b. Timing</span></label>
											<div class="col-md-7">
												<input id="empTiming" name="empTiming" maxlength="25"
													placeholder="{{'Enter timing'| translate}}" class="form-control input-md"
													type="text" ng-model="serial.timing">
												<div id="timingDurationerror" class="error-style"></div>
											</div>
										</div>
										
										<div class="form-group box-border-padding">
											<label class="col-md-4 control-label" for="textinput"><span translate>c. Duration (in months)</span></label>
											<div class="col-md-7">
												<input only-three-digits id="empDuration" name="empDuration"
													placeholder="{{'Enter duration'| translate}}" class="form-control input-md"
													type="text" ng-model="serial.duration">
												<div id="timingDurationerror" class="error-style"></div>
											</div>
										</div>

										<div class="form-group box-border-padding">
											<label class="col-md-4 control-label" for="textinput"><span translate>d. Wages earned</span></label>
											<div class="col-md-7">
												<input dynamic-digit id="wagesEarned" name="wagesEarned"
													placeholder="{{'Enter Wages earned'| translate}}"
													class="form-control input-md" type="text"
													ng-model="serial.wagesEarned">
												<div id="Wagesearnederror" class="error-style"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-7">
									<div id="cannotAddNew1" class="error-style"></div>
								</div>
							</div>

							<div class="addingnewdetails-container" ng-if="!viewPage">
								<button type="button" class="addingnewdetails"
									style="margin-left: 0px;" ng-click="addNewEmpDetails()">+</button>
								<button type="button" class="addingnewdetails" ng-if="employRepetition.length > 1"
									ng-click="deleteAccordionConfirmation('empDetails')">-</button>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-7">
									<div id="limitExceeded1" class="error-style"></div>
								</div>
							</div>
							
							<!-- This code is for whenn data is prefetched -->
							
							<div ng-if="viewPage" ng-repeat="empDetails in formInfo.childEmploymentDetailsModels">
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>a. Type of employment</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="lang=='en'?empDetails.typeOfEmployment.name:empDetails.typeOfEmployment.typeNameHindi" readonly>
										<div id="landedpropertieserror" class="error-style"></div>
									</div>
								</div>
								<div class="form-group box-border-padding" ng-if="empDetails.typeOfEmployment.id == 343">
									<label class="col-md-4 control-label" for="textinput"><span translate>Please specify</span></label>
									<div class="col-md-7">
										<input class="form-control input-md" readonly
											type="text" ng-model="empDetails.typesOfEmploymentOther">
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>b. Timing</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="empDetails.timing" readonly>
										<div id="landedpropertieserror" class="error-style"></div>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>c. Duration (in months)</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="empDetails.duration" readonly>
										<div id="landedpropertieserror" class="error-style"></div>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>d. Wages earned</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="empDetails.wagesEarned" readonly>
										<div id="landedpropertieserror" class="error-style"></div>
									</div>
								</div><br><br>
							</div>
							
							<!-- This code is for whenn data is prefetched -->

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Details of income utilization</span></label>
								<div class="col-md-4">
									<ul class="parent cf" ng-if="!viewPage">
										<li ng-repeat="util in incomeUtilizationList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="materials" id="materials" type="checkbox"
												ng-model="util.checked"> &nbsp;&nbsp;{{langType==0?util.name:util.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<textarea readonly ng-model="formInfo.detailsOfIncomeUtilizationName"
										ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Details of savings</span></label>
								<div class="col-md-7">
									<ul class="parent cf" ng-if="!viewPage">
										<li ng-repeat="savings in detailsOfSavingList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="materials" id="materials" type="checkbox"
												ng-model="savings.checked"
												ng-change="savingDetailsCount(savings)">
												&nbsp;&nbsp;{{langType==0?savings.name:savings.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<textarea readonly ng-model="formInfo.detailsOfSavingName"
										ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding"
								ng-if="savingDetailsCountFlag == true">
								<label class="col-md-4 control-label" for="textinput"><span translate>Please specify</span></label>
								<div class="col-md-7">
<!-- 								twohundred-characters-validation -->
									<input id="otherDetailsOfSaving" ng-blur="blur(formInfo.detailsOfSavingOthers,'detailsOfSavingOthers')"
										maxlength="200"
										ng-trim="false" name="otherDetailsOfSaving" ng-disabled="viewPage" required
										placeholder="{{'Please specify'| translate}}" class="form-control input-md othersVerbal" 
										type="text" ng-model="formInfo.detailsOfSavingOthers">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Duration of working hours</span></label>
								<div class="col-md-7">
									<select id="durationofWorking" class="form-control"
										ng-model="formInfo.durationOfWorkingHoursObject"
										ng-if="!viewPage"
										ng-options="duration as langType==0?duration.name:duration.typeNameHindi for duration in workDurationList">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.durationOfWorkingHoursObject.name:formInfo.durationOfWorkingHoursObject.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
								</div>
							</div>

							<div class="grey-header"><span translate>38. EDUCATIONAL DETAILS</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) The details of education of the child prior to the admission to Children's Home</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="admissiontoChildren" class="form-control"
										ng-options="edu as langType==0?edu.name:edu.typeNameHindi for edu in educationLevelsList"
										ng-model="formInfo.detailsOfPastEducationObject"
										ng-if="!viewPage" required>
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.detailsOfPastEducationObject.name:formInfo.detailsOfPastEducationObject.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) The reason for leaving the School</span> </label>
								<div class="col-md-4">
									<ul class="parent cf" ng-if="!viewPage">
										<li
											ng-repeat="reason in reasonLeavingSchoolList | orderBy:'id'">
											<label class="radio-inline"> <input
												name="failure" id="failureinClass" ng-model="reason.checked"
												type="checkbox"
												ng-change="reasonForLeavingSchoolCount(reason)">
												&nbsp;&nbsp;{{langType==0?reason.name:reason.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<div id="reasonforleavingError" class="error-style"></div>
									
									<textarea readonly ng-model="formInfo.reasonBehindLeavingSchoolName"
										ng-if="viewPage" class="form-control input-md"></textarea>
<!-- 										twohundred-characters-validation -->
									<input id="studiedLastOther" ng-trim="false" placeholder="{{'Please specify'| translate}}"
										maxlength="200" 
										ng-blur="blur(formInfo.reasonBehindLeavingSchoolOthers,'reasonBehindLeavingSchoolOthers')"
										ng-if="reasonForLeavingSchoolCountFlag == true || formInfo.reasonBehindLeavingSchoolOthers"
										class="form-control input-md spcifyother othersVerbal" type="text"
										ng-model="formInfo.reasonBehindLeavingSchoolOthers"
										ng-disabled="viewPage" required=>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) The details of the school in which studied last </span></label>
								<div class="col-md-7">
									<select id="studiedLast" class="form-control" ng-if="!viewPage"
										ng-options="details as langType==0?details.name:details.typeNameHindi for details in childSchoolDtlsList"
										ng-model="formInfo.detailsOfSchoolStudiedLastObject">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.detailsOfSchoolStudiedLastObject.name:formInfo.detailsOfSchoolStudiedLastObject.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Medium instruction</span></label>
								<div class="col-md-7">
									<select id="instructionMedium" class="form-control"
										ng-options="medium as langType==0?medium.name:medium.typeNameHindi for medium in mediumOfInstructionList | orderBy:'id'"
										ng-model="formInfo.schoolMediumInstructionObject" 
										ng-change="checkMediumInstruction()" ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select> 
									
									<input readonly ng-model="lang=='en'?formInfo.schoolMediumInstructionObject.name:formInfo.schoolMediumInstructionObject.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
<!-- 										twohundred-characters-validation -->
									<input required id="studiedLastOther" placeholder="{{'Please specify'| translate}}"
										ng-blur="blur(formInfo.schoolMediumInstructionOthers,'schoolMediumInstructionOthers')"
										ng-trim="false" maxlength="200" 
										ng-if="formInfo.schoolMediumInstructionObject.id == 277 || formInfo.schoolMediumInstructionOthers"
										class="form-control input-md spcifyother othersVerbal" type="text" ng-readonly="viewPage"
										ng-model="formInfo.schoolMediumInstructionOthers">
								</div>
							</div>

							<div class="grey-header"><span translate>39. After admission to Children's Home, the educational attainment from the date of admission till date</span></div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) No.of years</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="noofYears" placeholder="{{'Enter No. of years'| translate}}"
										name="educationalAttainment"
										class="form-control input-md" type="number"
										ng-pattern="/^[0-9]{1,2}?$/" required
										oninvalid="this.setCustomValidity('Please enter No. of years')"
										oninput="setCustomValidity('')" 
										ng-model="formInfo.educationalAttainmentNoOfYears"
										ng-keyUp="validateName(formInfo.educationalAttainmentNoOfYears,'noofYearsError')"
										 ng-blur="resetInputyear(formInfo,'educationalAttainmentNoOfYears','noofYearsError')"
										 ng-disabled="viewPage">
										<div id="noofYearsError" class="error-style"></div>
								</div>
								
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Class studied</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="classStudied" placeholder="{{'Enter Class studied'| translate}}"
										class="form-control input-md" type="text" required
										name="classStudied" maxlength="120"
										ng-model="formInfo.educationalAttainmentClassStudied"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Promoted /detained</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="instructionMedium" class="form-control"
										ng-options="status as langType==0?status.name:status.typeNameHindi for status in promotionStatusList"
										ng-model="formInfo.educationalAttainmentPromote_Detained" required
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.educationalAttainmentPromote_Detained.name:formInfo.educationalAttainmentPromote_Detained.typeNameHindi"
										ng-if="viewPage" class="form-control input-md">
								</div>
							</div>

							<div class="grey-header"><span translate>40. Vocational training undergone form the date of admission into Children's Home till date</span></div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) No.of years </span></label>
								
								<div class="col-md-7">
									<input id="noofYearsVocational"
										placeholder="{{'Enter No. of years'| translate}}" name="noOfYears"
										class="form-control input-md" type="number"
										ng-pattern="/^[0-9]{1,2}?$/"
										oninvalid="this.setCustomValidity('Please enter age of the person')"
										oninput="setCustomValidity('')"
										ng-model="formInfo.voactionalTrainingNoOfYears"
										ng-keyUp="validateName(formInfo.voactionalTrainingNoOfYears,'noofYearsVocationalError')"
										 ng-blur="resetInput1(formInfo,'voactionalTrainingNoOfYears','noofYearsVocationalError','Year')"
										 ng-disabled="viewPage">
										<div id="noofYearsVocationalError" class="error-style"></div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Name of Vocational Trade</span></label>
									
								<div class="col-md-7">
<!-- 								twohundred-characters-validation -->
									<input id="nameofVocationalTrade" type="text" maxlength="100"
										name="nameofVocationalTrade" 
										placeholder="{{'Enter Name of Vocational Trade'| translate}}"
										class="form-control input-md" 
										ng-model="formInfo.vocationalTrainingNameOfTrade"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Proficiency Attained</span></label>
									<div class="col-md-7">
<!-- 									twohundred-characters-validation -->
									<input id="proficiencyAttained" type="text" maxlength="110"
										name="proficiencyAttained" 
										placeholder="{{'Enter Proficiency Attained'| translate}}"
										class="form-control input-md" 
										ng-model="formInfo.vocationalTrainingProficiencyObtained"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Details of certification</span> </label>
									<div class="col-md-7">
									<input id="certification" type="text"
										name="certification" maxlength="200"
										placeholder="{{'Enter Details of certification'| translate}}"
										class="form-control input-md" 
										ng-model="formInfo.detailsOfCertificationPath"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>41. Extra-curricular activities developed form the date of admission into the Children's Home till date</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<ul class="content-align" ng-if="!viewPage">
										<li
											ng-repeat="activity in extraCurricularActivityList | orderBy:'id'">
											<label class="radio-inline"> <input 
												type="checkbox" ng-model="activity.checked"
												ng-change="extraCurricularActivityCount(activity)">
												&nbsp;&nbsp;{{langType==0?activity.name:activity.typeNameHindi}}
										</label>
										</li>
									</ul>
									<div id="activitiesError" class="error-style"></div>
									
									<textarea readonly ng-model="formInfo.extraCurricularActivitiesName"
										ng-if="viewPage" class="form-control input-md"></textarea>
<!-- 										twohundred-characters-validation -->
									<input id="otherXtraCurricular" type="text" maxlength="200" 
										ng-if="extraCurricularActivityCountFlag == true || formInfo.extraCurricularActivitiesOthers"
										placeholder="{{'Please specify'| translate}}" class="form-control input-md othersVerbal"
										ng-model="formInfo.extraCurricularActivitiesOthers" required
										ng-disabled="viewPage" ng-trim="false"
										ng-blur="blur(formInfo.extraCurricularActivitiesOthers,'extraCurricularActivitiesOthers')">
								</div>
							</div>

							<div class="grey-header"><span translate>42. MEDICAL HISTORY</span></div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Height at the time of admission (in cm)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="heightWeight" name="heightWeight"
										ng-model="formInfo.heightAtTimeOfAdmission"
										placeholder="{{'Enter height at the time of admission'| translate}}"
										class="form-control input-md" type="text"
										ng-disabled="viewPage" only-three-digits
										ng-blur="resetInput1(formInfo,'heightAtTimeOfAdmission','heighterror','Height')" required>
										<div id="heighterror" class="error-style"></div>  
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Weight at the time of admission (in kg)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="heightWeight" name="heightWeight"
										ng-model="formInfo.weightAtTimeOfAdmission" only-three-digits
										placeholder="{{'Enter weight at the time of admission'| translate}}"
										class="form-control input-md" type="text"
										ng-blur="resetInput1(formInfo,'weightAtTimeOfAdmission','wighterror','Weight')"
										ng-disabled="viewPage" required>
										<div id="wighterror" class="error-style"></div>  
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Physical condition at the time of admission</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="physicalCondition" name="physicalCondition"
										ng-model="formInfo.physicalCondition" maxlength="125"
										oninvalid="this.setCustomValidity('Please enter physical condition of the person')"
										oninput="setCustomValidity('')" required
										placeholder="{{'Enter physical condition at the time of admission'| translate}}"
										class="form-control input-md" type="text"
										ng-disabled="viewPage">
										<div id="physicalConditionerror" class="error-style"></div>   
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Medical history of child (gist)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="medicalhistoryofchild" name="medicalhistoryofchild"
										ng-model="formInfo.medicalHistoryOfChild" maxlength="200"
										placeholder="{{'Enter medical history of child'| translate}}"
										class="form-control input-md" type="text"
										ng-disabled="viewPage" required>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v) Medical history of parent/guardian (gist)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="medicalhistoryofParent" name="medicalhistoryofParent"
										ng-model="formInfo.medicalHistoryOfParents" maxlength="200"
										placeholder="{{'Enter medical history of parent/guardian'| translate}}"
										class="form-control input-md" type="text"
										ng-disabled="viewPage" required>
								</div>
							</div>


							<div class="grey-header"><span translate>43. Present health status of the child (Annual Observation)</span><span class="mandatory_star">&#42;</span> </div>
							<div class="familyDetails col-md-12" ng-if="!viewPage"
								ng-repeat="serial in healthStatsRepetition"
								style="margin-bottom: 10px;">
								<button data-toggle="collapse"
									data-target="{{'#healthstatusoftheChild'+ ($index+1)}}"
									type="button" class="addDetailPanel collapsed">
									{{$index+1 == 1 ? "1st": $index+1 == 2 ? "2nd" : $index+1 == 3 ? "3rd" : "4th" | translate}} {{'Quarter' | translate}} 
									<i class="fa fa-plus" aria-hidden="true"></i>
									<i class="fa fa-minus" aria-hidden="true"></i>
								</button>
								
									<div id="{{'healthstatusoftheChild'+ ($index+1)}}" class="collapse">

									<div class="form-group box-border-padding marginSpaceTop">
										<label class="col-md-4 control-label" for="textinput"><span translate>a. Date of Review</span> <span class="mandatory_star">&#42;</span></label>
										<div class="col-md-2">
											<input type="text" id="{{'datepicker' + ($index+1)}}" readonly
												name="{{'datepicker' + ($index+1)}}"
												class="form-control" ng-model="serial.dateOfReview">
										</div>
										 <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>b. Height (in cm)</span><span class="mandatory_star">&#42;</span></label>
										<div class="col-md-7">
											<input id="Heightchild" placeholder="{{'Enter Height'| translate}}"
												name="Heightchild" only-three-digits
												class="form-control input-md" type="text"
												ng-model="serial.height">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>c. Weight (in kg)</span><span class="mandatory_star">&#42;</span> </label>
										<div class="col-md-7">
											<input id="Weightchild" placeholder="{{'Enter Weight'| translate}}" only-three-digits
												name="Weightchild"
												class="form-control input-md" type="text"
												ng-model="serial.weight">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>d. Nutritious diet given</span><span class="mandatory_star">&#42;</span> </label>
										<div class="col-md-7">
											<input id="dietgiven" name="dietgiven" maxlength="105"
												placeholder="{{'Enter Nutritious diet given'| translate}}"
												class="form-control input-md" type="text"
												ng-model="serial.nutritiousDietGiven">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>e. Stress</span><span class="mandatory_star">&#42;</span> </label>
										<div class="col-md-7">
											<input id="stress" placeholder="{{'Enter stress issue'| translate}}" name="stress"
												class="form-control input-md" type="text" maxlength="105"
												ng-model="serial.stress">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>f. Dental</span><span class="mandatory_star">&#42;</span> </label>
										<div class="col-md-7">
											<input id="dental" placeholder="{{'Enter dental issue'| translate}}"
												class="form-control input-md" type="text" maxlength="105"
												ng-model="serial.dental">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>g. ENT</span><span class="mandatory_star">&#42;</span> </label>
										<div class="col-md-7">
											<input id="ent" placeholder="{{'Enter ENT issue'| translate}}" name="ent"
												class="form-control input-md" type="text" maxlength="105"
												ng-model="serial.ent">
										</div>
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput"><span translate>h. Eye</span><span class="mandatory_star">&#42;</span> </label>
										<div class="col-md-7">
											<input id="eye" placeholder="{{'Enter eye issue'| translate}}" name="eye"
												class="form-control input-md" type="text" maxlength="105"
												ng-model="serial.eye">
										</div>
									</div>
								</div>
							</div>
							
							<div class="form-group box-border-padding" ng-if="!viewPage">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-7">
									<div id="cannotAddNew2" class="error-style"></div>
								</div>
							</div>
							
							<div class="addingnewdetails-container" ng-if="!viewPage">
								<button type="button" class="addingnewdetails"
									style="margin-left: 0px;" ng-click="addNewQuarterDetails()">+</button>
								<button type="button" class="addingnewdetails" ng-if="healthStatsRepetition.length > 1"
									ng-click="deleteAccordionConfirmation('healthStatus')">-</button>
							</div>
							
							<div class="form-group box-border-padding" ng-if="!viewPage">
								<label class="col-md-4 control-label" for="textinput"></label>
								<div class="col-md-7">
									<div id="limitExceeded2" class="error-style"></div>
								</div>
							</div>
							
							<div ng-if="viewPage" ng-repeat="health in formInfo.healthStatusOfChildModels">
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label">
								{{$index+1 == 1 ? "1st": $index+1 == 2 ? "2nd" : $index+1 == 3
									? "3rd" : "4th" }} Quarter
									</label>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>a. Date of review</span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.dateOfReview" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>b. Height (in cm)</span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.height" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>c. Weight (in kg)</span> <span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.weight" only-three-digits readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>d. Nutritious diet given</span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.nutritiousDietGiven" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>e. Stress </span><span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.stress" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>f. Dental</span> <span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.dental" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>g. Ent</span> <span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.ent" readonly>
									</div>
								</div>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput"><span translate>h. Eye</span> <span class="mandatory_star">&#42;</span></label>
									<div class="col-md-7">
										<input placeholder="" class="form-control input-md" type="text"
											ng-model="health.eye" readonly>
									</div>
								</div><br><br>
							</div>

							
							<div class="grey-header"><span translate>44. Height and Weight Chart</span></div>

							<div class="form-group box-border-padding marginSpaceTop">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Date,Month and Year</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-2">
									<input type="text" id="dateForHeightWeightChart" readonly
										class="form-control" ng-show="!viewPage">
									<input type="text" ng-model="formInfo.dateMonthYearForHeightWeightChart"
										readonly class="form-control" ng-show="viewPage">	
								</div>
								<i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Height (in cm)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="heightchildChart" placeholder="{{'Enter Height'| translate}}" name="heightchildChart"
										class="form-control input-md" type="text" required only-three-digits
										ng-model="formInfo.heightForHeightWeightChart"
										ng-blur="resetInput1(formInfo,'heightForHeightWeightChart','heightForHeightWeightCharterror','Height')"
										ng-disabled="viewPage">
										<div id="heightForHeightWeightCharterror" class="error-style"></div>  
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Admissible Weight (in kg)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="admissibleWeightChart" name="admissibleWeightChart" placeholder="{{'Enter admissible weight'| translate}}" only-three-digits
										class="form-control input-md" type="number" required
										ng-model="formInfo.admissibleWeightForHeightWeightChart"
										ng-blur="resetInput1(formInfo,'admissibleWeightForHeightWeightChart','admissibleWeightForHeightWeightCharterror','Weight')"
										ng-disabled="viewPage">
										<div id="admissibleWeightForHeightWeightCharterror" class="error-style"></div>  
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Actual Weight (in kg)</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="actualWeightChart" name="actualWeightChart" placeholder="{{'Enter actual weight'| translate}}" only-three-digits
										class="form-control input-md" type="number" required
										ng-model="formInfo.actualWeightForHeightWeightChart"
										ng-blur="resetInput1(formInfo,'actualWeightForHeightWeightChart','actualWeightForHeightWeightCharterror','Weight')"
										ng-disabled="viewPage">
										<div id="actualWeightForHeightWeightCharterror" class="error-style"></div>
								</div>
							</div>

							<div class="grey-header"><span translate>45. SOCIAL HISTORY</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Details of friendship prior to admission into Children's Home</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
									<ul class="parent cf" ng-if="!viewPage">
										<li
											ng-repeat="frndshp in friendshipPriorToChildrensHomeList | orderBy:'id'">
											<label class="radio-inline"> <input 
												type="checkbox" ng-model="frndshp.checked" 
												ng-change="detailsOfFriendshipCount(frndshp)">
												&nbsp;&nbsp;{{langType==0?frndshp.name:frndshp.typeNameHindi}}
										</label>
										</li>
									</ul>
									<div id="friendshipError" class="error-style"></div>
									
									<textarea readonly ng-model="formInfo.friendshipPriorToAdmissionIntoChildrensHomeName"
										ng-if="viewPage" class="form-control input-md"></textarea>
										
									<input id="detailsOfFrndshpOthers" placeholder="{{'Please specify'| translate}}"  maxlength="200"
										ng-if="detailsOfFriendshipCountFlag == true || formInfo.friendshipPriorToAdmissionIntoChildrensHomeOthers"
										ng-model="formInfo.friendshipPriorToAdmissionIntoChildrensHomeOthers"
										class="form-control input-md othersVerbal" type="text"
										ng-disabled="viewPage" required
										ng-blur="blur(formInfo.friendshipPriorToAdmissionIntoChildrensHomeOthers,'friendshipPriorToAdmissionIntoChildrensHomeOthers')"
									 	ng-trim="false">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Majority of the friends are</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
									<ul class="parent cf" ng-if="!viewPage">
										<li
											ng-repeat="majority in majorityFriendTypesList | orderBy:'id'">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="majority.checked">
												&nbsp;&nbsp;{{langType==0?majority.name:majority.typeNameHindi}}
										</label>
										</li>
									</ul>
									<div id="friendsError" class="error-style"></div>
									
									<textarea readonly ng-model="formInfo.majorityFriendsAreName"
										ng-if="viewPage" class="form-control input-md"></textarea>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Details of membership in group</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-4">
									<ul class="parent cf" ng-if="!viewPage">
										<li
											ng-repeat="group in membershipInGroupDetailsList | orderBy:'id'">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="group.checked"
												ng-change="detailsOfMembershipCount(group)">
												&nbsp;&nbsp;{{langType==0?group.name:group.typeNameHindi}}
										</label>
										</li>
									</ul>
									
									<div id="membershipError" class="error-style"></div>
									
									<textarea readonly ng-model="formInfo.detailOfMembershipInGroupName"
										ng-if="viewPage" class="form-control input-md"></textarea>
										
									<input id="detailsOfMembershipOthers" ng-disabled="viewPage" maxlength="200"
										placeholder="{{'Please specify'| translate}}" required
										ng-blur="blur(formInfo.detailOfMembershipInGroupOthers,'detailOfMembershipInGroupOthers')"
									 	ng-trim="false"
										ng-if="detailsOfMembershipCountFlag == true || formInfo.detailOfMembershipInGroupOthers"
										ng-model="formInfo.detailOfMembershipInGroupOthers"
										class="form-control input-md spcifyother" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) The position of the child in the groups/league </span></label>
								<div class="col-md-7">
									<select id="positionoftheChild" class="form-control" name="positionoftheChild"
										ng-options="pos as langType==0?pos.name:pos.typeNameHindi for pos in positionInGroupList"
										ng-model="formInfo.positionOfChildInGroup_LeagueObject"
										ng-if="!viewPage" >
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input ng-if="viewPage" readonly
										ng-model="lang=='en'?formInfo.positionOfChildInGroup_LeagueObject.name:formInfo.positionOfChildInGroup_LeagueObject.typeNameHindi"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v) Purpose of taking membership in the group</span> </label>
								<div class="col-md-4">
									<ul class="parent cf" ng-if="!viewPage">
										<li ng-repeat="purpose in purposeOfTakingMembershipList">
											<label class="radio-inline"> <input
												type="checkbox" ng-model="purpose.checked"
												ng-change="purposeOfMembershipCount(purpose)">
												&nbsp;&nbsp;{{langType==0?purpose.name:purpose.typeNameHindi}}
										</label>
										</li>
									</ul>
									<div id="PurposeError" class="error-style"></div>
									
									<textarea readonly ng-model="formInfo.purposeOfTakingMembershipInGroupName"
										ng-if="viewPage" class="form-control input-md"></textarea>
										
									<input id="purposeOfTakingPosition" placeholder="{{'Please specify'| translate}}" ng-trim="false"
										ng-if="purposeOfMembershipCountFlag == true || formInfo.purposeOfTakingMembershipInGroupOthers"
										ng-model="formInfo.purposeOfTakingMembershipInGroupOthers"
										ng-disabled="viewPage" required maxlength="200"
										ng-blur="blur(formInfo.purposeOfTakingMembershipInGroupOthers,'purposeOfTakingMembershipInGroupOthers')"
										class="form-control input-md spcifyother othersVerbal" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vi) Attitude of the group / league</span></label>
								<div class="col-md-7">
									<select id="groupleague" class="form-control"  name="groupleague"
										ng-options="att as langType==0?att.name:att.typeNameHindi for att in attitudeOfTheGroupList"
										ng-model="formInfo.attitudeOfGroup_LeagueObject"
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input ng-if="viewPage" readonly
										ng-model="lang=='en'?formInfo.attitudeOfGroup_LeagueObject.name:formInfo.attitudeOfGroup_LeagueObject.typeNameHindi"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vii) The location/meeting point of the groups</span></label>
								<div class="col-md-7">
									<select id="pointoftheGroups" class="form-control" name="pointoftheGroups"
										ng-options="point as langType==0?point.name:point.typeNameHindi for point in meetingPointOfGroupList"
										ng-model="formInfo.locationMeetingPointOfGroupsObject"
										ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input ng-if="viewPage" readonly
										ng-model="lang=='en'?formInfo.locationMeetingPointOfGroupsObject.name:formInfo.locationMeetingPointOfGroupsObject.typeNameHindi"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(viii) The reaction of the society when the child first came out of the family</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7" ng-if="!viewPage">
									<select id="reactionOfSociety" class="form-control" required name="reactionOfSociety"
										ng-options="reaction as langType==0?reaction.name:reaction.typeNameHindi for reaction in reactionOfSocietyTowardsChildList"
										ng-model="formInfo.reactionOfSocietyTowardsChildObject">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
								</div>
								
								<div class="col-md-7" ng-if="viewPage">
									<input readonly
										ng-model="lang=='en'?formInfo.reactionOfSocietyTowardsChildObject.name:formInfo.reactionOfSocietyTowardsChildObject.typeNameHindi"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ix) The reaction of the police towards children</span></label>
								<div class="col-md-7" ng-if="!viewPage">
									<select id="reactionOfPolice" class="form-control" name="reactionOfPolice"
										ng-options="reaction as langType==0?reaction.name:reaction.typeNameHindi for reaction in reactionOfPoliceTowardsChildList"
										ng-model="formInfo.reactionOfPoliceTowardsChildrenObject">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
								</div>
								
								<div class="col-md-7" ng-if="viewPage">
									<input readonly
										ng-model="lang=='en'?formInfo.reactionOfPoliceTowardsChildrenObject.name:formInfo.reactionOfPoliceTowardsChildrenObject.typeNameHindi"
										class="form-control input-md" type="text">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(x) The response of the general public towards the<br>child</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="responsetowardstheChild" required name="responsetowardstheChild"
										placeholder="{{'Enter response'| translate}}" class="form-control input-md"
										type="text" ng-disabled="viewPage" maxlength="200"
										ng-model="formInfo.responseOfGeneralPublicTowardsChild">
								</div>
							</div>

							<div class="grey-header"><span translate>46. HISTORY OF THE CHILD</span></div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Education </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="historyEducation" required name="historyEducation"
										placeholder="{{'Enter education details'| translate}}"
										class="form-control input-md" type="text" maxlength="200"
										ng-model="formInfo.historyOfChildEducation"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Health</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="historyHealth" placeholder="{{'Enter health details'| translate}}" name="historyHealth"
										class="form-control input-md" type="text" required
										ng-model="formInfo.historyOfChildHealth" maxlength="200"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Vocational training</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="historyVocationaltraining" required name="historyVocationaltraining"
										placeholder="{{'Enter vocational training details'| translate}}"
										class="form-control input-md" type="text" maxlength="200"
										ng-model="formInfo.historyOfChildVocationalTraining"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Extra curricular activities</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="historyExtra" name="historyExtra" maxlength="200"
										placeholder="{{'Enter extra curricular activities details'| translate}}"
										class="form-control input-md" type="text" required
										ng-model="formInfo.historyOfChildExtraCuricularActivites"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(v) Others</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="historyOthers" placeholder="{{'Please Specify'| translate}}" name="historyOthers"
										class="form-control input-md" type="text" maxlength="200"
										ng-model="formInfo.historyOfChildOthers"
										ng-disabled="viewPage" required>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vi) Suggestion of </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="Suggestionof" class="form-control" required name="Suggestionof"
										ng-options="by as langType==0?by.name:by.typeNameHindi for by in suggestionByList"
										ng-model="formInfo.suggestionByWhom" ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.suggestionByWhom.name:formInfo.suggestionByWhom.typeNameHindi" ng-if="viewPage" 
										class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(vii) Suggestion of Child Welfare Officer/ Probation Officer after orientation to child and the response towards orientation </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="historyresponse" placeholder="{{'Enter Suggestion of CWO/ PO after orientation to child'| translate}}"
										class="form-control input-md" type="text" name="historyresponse"
										ng-model="formInfo.suggestion" required maxlength="200"
										ng-disabled="viewPage">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(viii) Follow up by</span> <span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="followupBy" class="form-control" required name="followupBy"
										ng-options="up as langType==0?up.name:up.typeNameHindi for up in followUpByList"
										ng-model="formInfo.followUpWhom" ng-if="!viewPage">
										<option value="" disabled selected><span translate>{{'Select' | translate}}</span></option>
									</select>
									
									<input readonly ng-model="lang=='en'?formInfo.followUpWhom.name:formInfo.followUpWhom.typeNameHindi" ng-if="viewPage"
										class="form-control input-md">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ix) Quarterly Review of Case History by Management Committee </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="QuarterlyReview" placeholder="{{'Enter Quarterly Review of Case History'| translate}}"
										class="form-control input-md" type="text" name="QuarterlyReview"
										ng-model="formInfo.quarterlyReviewOfCase" maxlength="200"
										ng-disabled="viewPage" required>
								</div>
							</div>
 
							<div style="text-align: center">
								<button id="button1id" name="button1id" class="btn btn-info"
									type="submit" ng-click="caseHistory.$invalid ? '' : validateForm()"
									ng-if="!viewPage"><span translate>Submit</span></button>
									
								<button id="button2id" name="button2id" class="btn btn-info"
									type="submit" ng-if="viewPage" ng-click="printCaseHistoryData()"><span translate>Print</span></button>
							</div>
						<a href="#" class="back-to-top" style="display: inline;">
							<i class="fa fa-arrow-circle-up"></i>
						</a>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="errorImgCaseModal" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p style="text-align: center"><span translate>
							Uploaded file is not in correct format.</span><br>
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>	
	<script>
		var app = angular.module('caseHistoryApp', ['gettext']);
		var myAppConstructor = angular.module('caseHistoryApp');
	</script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
		 <!--  <script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script> -->
	<script type="text/javascript"
		src="resources/js/AngularController/caseHistoryController.js"></script>
<!-- 	<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
	<script
		src="resources/js/moment-with-locales.js"></script>
	<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
	<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
// 			$("#datepicker").datepicker({
// 				dateFormat : "yy-mm-dd",
// 				maxDate : '+0d',
// 				changeYear: true
// 			});
			$("#datepicker1").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d',
				changeYear: true
			});
// 			$("#dateForHeightWeightChart").datepicker({
// 				dateFormat : "yy-mm-dd",
// 				maxDate : '+0d',
// 				changeYear: true
// 			});
		});

		$('.parent input[type="checkbox"]:first-child').change(
				function() {
					$(this).closest('.parent').find('.children').toggle(
							$(this).is(':checked'));
				});
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
