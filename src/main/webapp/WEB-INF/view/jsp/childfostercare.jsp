<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<title>RECORD OF A CHILD IN FOSTER CARE - FORM 34</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">

<spring:url value="/resources/css/style.css" var="styleCss" />
<link href="${styleCss}" rel="stylesheet" />
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>
</head>


<body ng-app="childFosterCare" ng-controller="childFosterCareController"
	ng-cloak>

	<jsp:include page="./common/cctsHeader.jsp" />

	<div class="modal fade" id="confirmationModalForFosterCare"
		tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog modalCenter">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to save the details.</span></h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id" class="btn btn-info bigbutton" type="submit"
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
	
	<div class="modal fade" id="finalOrderModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <p style="text-align:center">
	          	<h4 class="selectChildalign"><span translate>Final order for this child has been filled up.Could not make any changes.</span></h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true" ng-click="redirect()"><span translate>OK</span></button>  
	      </div>    
	    </div>
	  </div>
	</div>

	<div class="modal fade" id="successModalForFosterCare" tabindex="-1" role="dialog">
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
							type="submit" ng-click="printChildRecordInFosterData()" class="close"
							data-dismiss="modal" aria-hidden="true"><span translate>Print</span></button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="uploadError" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<p style="text-align: center">
						<span translate>Uploaded file is not in correct format.</span><br>
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Ok</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="sizeError" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<p style="text-align: center">
						<span translate>Uploaded file should not be more than 3MB.</span><br>
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Ok</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="box-border box-border-padding">
				<!-- <hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr> -->
					<div class="childlist-heading1 borderPersonal">
						<span translate>Record of a child in foster care.</span><br><span translate>FORM 34</span><br>
						<span translate>[Rules 23(17)]</span>
					</div>
					<form class="form-horizontal basicchildform" name="childRecordInFosterCare"
						id="childRecordInFosterCare">
						<fieldset>
							<div class="grey-header marginTop"
								style="border-top: none; margin-top: 4px;"><span translate>RECORD OF A CHILD IN FOSTER CARE</span></div>
							<div class="col-md-12 summaryspace">
								<div class="col-md-6 childidheader" 
									style="margin-left: 0px !important; padding-left: 0px !important;">
									<div class="social_headng">
										<img src="resources/img/cpis_ccts_Child_ID_SVG.svg"
											style="width: 6%;" /> <span><span translate>Child ID:</span>&nbsp;&nbsp;{{childId}}</span>
									</div>
								</div>
								<div class="col-md-6 childnameheader"
									style="margin-right: 0px !important; padding-right: 0px !important;">
									<div class="social_headng1">
										<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{childMstData.childName}}</span>
									</div>
								</div>
							</div>

							<input type="hidden" id="childId" value="${selectedChild}">

							<div class="form-group box-border-padding interimPlanmargintop">
								<label class="col-md-4 control-label" for="textinput"><span translate>1.Case No.</span> </label>
								<div class="col-md-7">
									<input class="form-control input-md" readonly type="text"
										ng-model="childMstData.caseNum">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>2.Name of the Child</span> </label>
								<div class="col-md-7">
									<input class="form-control input-md" type="text" readonly
										ng-model="childMstData.childName">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>3.Age</span> </label>
								<div class="col-md-7">
									<input class="form-control input-md" readonly type="text"
										ng-model="childMstData.age">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>4.Gender</span> </label>
								<div class="col-md-7">
									<input readonly class="form-control input-md" type="text"
										ng-model="lang=='en'?childMstData.sexObject.name:childMstData.sexObject.typeNameHindi">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>5.(i) Name of the Child Care Institution, if any from where the child has been given for foster care</span> </label>
								<div class="col-md-7">
									<select id="producedBeforeCci" name="producedBeforeCci"
										class="form-control" ng-if="!viewChildInFosterCare"
										ng-options="cci as cci.name for cci in cciList"
										oninvalid="this.setCustomValidity('Please enter cci name')"
										oninput="setCustomValidity('')" ng-model="$parent.fosterCare.cciObject">
										<option value="" disabled selected>{{'Select CCI' | translate}}</option>
									</select>
									
									<input class="form-control input-md" readonly ng-if="viewChildInFosterCare"
										ng-model="fosterCare.cciObject.name">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Address of the Child Care Institution, if any from where the child has been given for foster care</span> </label>
								<div class="col-md-7">
									<input class="form-control input-md" readonly
										ng-model="fosterCare.cciObject.address">
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>6.Individual Care Plan</span> </label>
								<div class="col-md-7">
									<a ng-href="{{icpPage}}" target="_blank">
									<button class="col-md-3" class="btn btn-sm btn-primary"
										type="button" margin-left: 0px;"><span translate>Download ICP</span></button></a>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>7.Any other source of referral</span> </label>
								<div class="col-md-7">
									<textarea class="form-control" id="otherSourceOfReferral"
										ng-model="fosterCare.otherSourceOfReferral" maxlength="100"
										placeholder="{{'Enter other source of referral'| translate}}"
										ng-disabled="viewChildInFosterCare">
									</textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea">
								<span translate>8. (i) Details of the child placed in foster care including Photograph of the child, foster care giver/parent, biological parents, if available</span><span class="mandatory_star">&#42;</span> </label>
								<div class="col-md-7">
									<textarea required class="form-control" id="childDetailsInFosterCare"
										ng-model="fosterCare.childDetailsInFosterCare" maxlength="100"
										placeholder="{{'Enter details of the child placed in foster care'| translate}}"
										ng-disabled="viewChildInFosterCare">
									</textarea>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Upload image of child</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-3">
									<input type="file" name="childImage" id="childImage"
										ng-files="getPhoto($files,'childImage')"
										accept=".png, .jpg, .jpeg" ng-if="!viewChildInFosterCare">
									<div id="childImageUploadError" class="error-style"></div>
								</div>
								<img style="margin-left: 100px;" ng-src={{childImage}}
									alt="No image" height="45" width="45" data-action="zoom">
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iii) Upload image of foster care giver/parent</span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-3">
									<input type="file" name="careGiverImage" id="careGiverImage"
										ng-files="getPhoto($files,'fosterCareParentImage')"
										accept=".png, .jpg, .jpeg" ng-if="!viewChildInFosterCare">
									<div id="careGiverUploadError" class="error-style"></div>
								</div>
								<img style="margin-left: 100px;"
									ng-src={{fosterCareParentImage}} alt="No image" height="45"
									width="45" data-action="zoom">
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(iv) Upload image of biological parents</span></label>
								<div class="col-md-3">
									<input type="file" name="biologicalParentImage" id="biologicalParentImage"
										ng-files="getPhoto($files,'biologicalParentImage')"
										accept=".png, .jpg, .jpeg" ng-if="!viewChildInFosterCare">
								</div>
								<img style="margin-left: 100px;" ng-src={{biologicalParentImage}} alt="No image" height="45"
									width="45" data-action="zoom">
							</div>

							<div class="grey-header"><span translate>9. Details of the placement</span></div>
							
							<div class="form-group box-border-padding" ng-if="cifcList.length > 0">
								<div class="col-md-2"></div>
								<div class="col-md-9 col-sm-12 col-xs-12">
									<div class="col-md-4 col-sm-4 col-xs-4 text-center">
										<strong><span translate>Individual/Group</span></strong>
									</div>
									<div class="col-md-4 col-sm-4 col-xs-4 text-center">
										<strong><span translate>Date</span></strong>
									</div>
									<div class="col-md-4 col-sm-4 col-xs-4 text-center">
										<strong><span translate>Period of placement(in months)</span></strong>
									</div>
								</div>
							</div>
							
							<div class="form-group box-border-padding" ng-repeat="details in cifcList">
								<label class="col-md-2 col-sm-12 col-xs-12 control-label">9.{{$index+1}}</label>
								<div class="col-md-9">
									<div class="col-md-4 col-sm-4 col-xs-4 text-center">
										<input class="form-control input-md" type="text" ng-if="details.cciName != null"
											ng-model="details.cciName" readonly>
											
										<input class="form-control input-md" type="text" ng-if="details.fosterParentName1 != null"
											ng-model="details.fosterParentName1" readonly>
									</div>
									<div class="col-md-4 col-sm-4 col-xs-4 text-center">
										<input class="form-control input-md" type="text" readonly
											ng-model="details.dateOfFormFilled">
									</div>
									<div class="col-md-4 col-sm-4 col-xs-4 text-center">
										<input class="form-control input-md" type="number"
											ng-model="details.durationOfStayAtFosterCare" readonly>
									</div>
								</div>
							</div>
							<div class="form-group box-border-padding">
								<div class="col-md-8" ng-if="cifcList.length == 0">
										<label class="col-md-7 control-label"><span translate>Data for details of placement not available</span></label>
								</div>
							</div>
							

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">
								<span translate>10. Home Study Report of the biological family, where applicable with photograph</span> </label>
									
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 30.pdf" download ng-if="!viewChildInFosterCare">
								<button class="col-md-3" type="button" class="btn btn-sm btn-primary" id="hsrStudyButton"
									style="margin-left: 10px; margin-top: 10px;"><span translate>Download</span></button></a>
									
								<button class="col-md-3" type="button" class="btn btn-sm btn-primary" id="hsrStudyButton"
									style="margin-left: 10px; margin-top: 10px;" ng-if="viewChildInFosterCare && hsrBiologicalParent"
									ng-click="downloadPdf(hsrBiologicalParent,'HSRBiologicalFamily')"><span translate>Download</span></button>
									
								<label class="col-md-7 control-label" style="text-align: center;" ng-if="viewChildInFosterCare && !hsrBiologicalParent">
									<span translate>Human study report of biological parent not available.</span>
								</label>
									
								<div class="col-md-5 ">
									<input class="input-file" type="file" ng-if="!viewChildInFosterCare"
										file-model="escortOrderModel" name="hsrBiological" id="hsrBiological"
										ng-files="getReport($files,'hsrBiologicalParent')" accept=".pdf"
										style="display: inline-block; margin-top: 12px;">
								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">
								<span translate>11. Home Study report of the foster family- individual or group care, with photograph</span> </label>
									
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 30.pdf" download ng-if="!viewChildInFosterCare">
								<button class="col-md-3" class="btn btn-sm btn-primary"
									type="button" style="margin-left: 10px; margin-top: 10px;"><span translate>Download</span></button></a>
									
								<button class="col-md-3" type="button" class="btn btn-sm btn-primary" id="hsrStudyButton"
									style="margin-left: 10px; margin-top: 10px;" ng-if="viewChildInFosterCare && hsrFosterFamily"
									ng-click="downloadPdf(hsrFosterFamily,'HSRFosterFamily')"><span translate>Download</span></button>
									
								<label class="col-md-7 control-label" style="text-align: center;" ng-if="viewChildInFosterCare && !hsrFosterFamily">
									<span translate>Human study report of foster family not available.</span>
								</label>
									
								<div class="col-md-5 ">
									<input class="input-file" type="file" name="hsrFoster" id="hsrFoster"
										ng-files="getReport($files,'hsrFosterFamily')" accept=".pdf"
										style="display: inline-block; margin-top: 12px;" ng-if="!viewChildInFosterCare">
								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>12. Child Study Report</span></label>
								
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 31.pdf" download ng-if="!viewChildInFosterCare">	
								<button class="col-md-3" class="btn btn-sm btn-primary" type="button"
									style="margin-left: 10px; margin-top: 10px;"><span translate>Download</span></button></a>
									
								<button class="col-md-3" type="button" class="btn btn-sm btn-primary" id="hsrStudyButton"
									style="margin-left: 10px; margin-top: 10px;" ng-if="viewChildInFosterCare && childReport"
									ng-click="downloadPdf(childReport,'ChildStudyReport')"><span translate>Download</span></button>
									
								<label class="col-md-7 control-label" style="text-align: center;" ng-if="viewChildInFosterCare && !childReport">
									<span translate>Child report not available.</span>
								</label>
									
								<div class="col-md-5 ">
									<input class="input-file" type="file"
										name="childStudyReport" id="childStudyReport"
										ng-files="getReport($files,'childReport')" accept=".pdf"
										style="display: inline-block; margin-top: 12px;" ng-if="!viewChildInFosterCare">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>13. Address of the Child Welfare Committee</span> </label>
								<div class="col-md-7">
									<textarea class="form-control" id="producerAddress" readonly
										ng-model="childMstData.cwc.name"
										placeholder="{{'Enter Address'| translate}}" oninput="setCustomValidity('')"></textarea>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">
								<span translate>14. Particulars of the order of the Committee placing the child in foster care(download form no 32)</span> </label>
									<div  class="col-md-8"  ng-if="cifcList.length > 0">
									<div ng-repeat="data in cifcList">
									<label class="col-md-4 control-label" for="textarea" style="text-align:left;" >{{data.dateOfFormFilled}}</label>
									<button class="col-md-4 fostercare" class="btn btn-sm btn-primary " type="button"
										 ng-click="downloadForm32(data)"><span translate>Download</span></button>
								</div>
									</div>
								
								<div class="col-md-8" ng-if="cifcList.length == 0">
									<label class="col-md-7 control-label"><span translate>Form 32 not available for download</span></label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea">
								<span translate>15. Record (number and significant details) of each visit with the child, foster family, Biological family, if available and school of child</span> </label>
								<div class="col-md-7">
									<div class="record-box">
										<textarea class="form-control" id="eachVisitRecordText" maxlength="100"
											name="eachVisitRecordText" placeholder="{{'Enter record of each visit'| translate}}"
											ng-model="fosterCare.recordOfEachVisit" ng-disabled="viewChildInFosterCare">
											</textarea>
									</div>
									<div class="record-upload ">
										<input class="input-file" type="file" name="eachVisitRecord"
											id="eachVisitRecord" ng-if="!viewChildInFosterCare"
											ng-files="getReport($files,'eachVisitChildRecord')" accept=".pdf"
											style="display: inline-block; margin-top: 10px;">
											
											<button class="col-md-3" type="button" class="btn btn-sm btn-primary" id="eachVisitChildRecordButton"
											style="margin-left: 10px; margin-top: 10px;" ng-if="viewChildInFosterCare && eachVisitChildRecord"
											ng-click="downloadPdf(eachVisitChildRecord,'eachVisitChildRecord')"><span translate>Download</span></button>
									</div>
								</div>
							</div>
							
							<div class="grey-header"><span translate>16. Record of all reviews of the placement</span></div>
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>(i) Observations</span> </label>
								<div class="col-md-7">
									<textarea class="form-control" id="observations" maxlength="100"
										ng-model="fosterCare.observation" name="observations"
										placeholder="{{'Enter observations'| translate}}" ng-disabled="viewChildInFosterCare">
									</textarea>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea">
								<span translate>(ii) Extent and quality of compliance with Care Plan</span> </label>
								<div class="col-md-7">
									<textarea class="form-control" id="QualityCompliance" name="QualityCompliance"
										placeholder="{{'Enter extent and quality of compliance with Care Plan'| translate}}" maxlength="100"
										ng-model="fosterCare.extentQualityCompliance" ng-disabled="viewChildInFosterCare">
									</textarea>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>(iii) Developmental milestones of child</span></label>
								<div class="col-md-7">
									<textarea class="form-control" id="milestones" name="milestones"
										ng-model="fosterCare.developmentalMilestones" maxlength="100"
										placeholder="{{'Enter developmental milestones of child'| translate}}" ng-disabled="viewChildInFosterCare">
									</textarea>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>(iv) Academic progress of child</span> </label>
								<div class="col-md-7">
									<textarea class="form-control" id="academicProgress" maxlength="100"
										name="academicProgress" ng-model="fosterCare.academicProgress"
										placeholder="{{'Enter academic progress of child'| translate}}"
										ng-disabled="viewChildInFosterCare">
									</textarea>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="textarea"><span translate>(v) Any changes in family environment</span> </label>
								<div class="col-md-7">
									<textarea class="form-control" id="changesInFamily" maxlength="100"
										name="changesInFamily" ng-model="fosterCare.changesInFamilyEnvironment"
										placeholder="{{'Enter any changes in family environment'| translate}}"
										ng-disabled="viewChildInFosterCare">
									</textarea>
								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>17 (i).Reason for termination</span> </label>
								<div class="col-md-7">
									<div class="col-md-5" style="margin-left: -17px;">
										<input id="reasonForTermination" name="reasonForTermination"
											placeholder="{{'Reason for termination'| translate}}" maxlength="100"
											class="form-control input-md" type="text" 
											ng-model="fosterCare.reasonForTermination"
											ng-disabled="viewChildInFosterCare">
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-3 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>Date of termination</span> </label>
										<div class="col-md-3 col-sm-6 col-xs-6">
											<input type="text" id="datepicker6" readonly
												class="form-control" ng-model="fosterCare.dateForTermination"
												ng-disabled="viewChildInFosterCare">
										</div>
										<i class="fa fa-calendar fa-5x"
											style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
											aria-hidden="true"></i>
									</div>
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>(ii) Reason for extension</span> </label>
								<div class="col-md-7">
									<div class="col-md-5" style="margin-left: -17px;">
										<input id="reasonForExtension" type="text"
											name="reasonForExtension" maxlength="100"
											placeholder="{{'reason for extension'| translate}}" 
											class="form-control input-md"
											ng-model="fosterCare.reasonForExtension"
											ng-disabled="viewChildInFosterCare">
									</div>

									<div class="form-group box-border-padding">
										<label class="col-md-3 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>Date of extension</span> </label>
										<div class="col-md-3 col-sm-6 col-xs-6">
											<input type="text" id="datepicker7" readonly
												class="form-control" ng-model="fosterCare.dateForExtension"
												ng-disabled="viewChildInFosterCare">
										</div>
										<i class="fa fa-calendar fa-5x"
											style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
											aria-hidden="true"></i>
									</div>
								</div>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>18. Date of the child being handed over to the foster family</span><span class="mandatory_star">&#42;</span> </label>
								<div class="col-md-3 col-sm-6 col-xs-6">
									<input type="text" id="datepicker8" readonly
										class="form-control" ng-model="fosterCare.childHandedOverDate"
										ng-disabled="viewChildInFosterCare">
								</div>
								<i class="fa fa-calendar fa-5x"
									style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
									aria-hidden="true"></i>
							</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>19. Financial assistance provided, if any</span> </label>
								<div class="col-md-7">
									<input id="financialAssistance" name="financialAssistance"
										placeholder="{{'Enter financial assistance provided'| translate}}" maxlength="100"
										class="form-control input-md" type="text"
										ng-model="fosterCare.financialAssistance"
										ng-disabled="viewChildInFosterCare">
								</div>
							</div>
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput"><span translate>20. Name of the Case Worker appointed</span><span class="mandatory_star">&#42;</span> </label>
								<div class="col-md-7">
									<input required id="caseWorkerName" name="caseWorkerName"
										placeholder="{{'Enter name of the Case Worker appointed'| translate}}"
										class="form-control input-md" type="text" maxlength="100"
										ng-model="fosterCare.caseWorkerName"
										ng-disabled="viewChildInFosterCare">
								</div>
							</div>



							<div style="text-align: center">
								<button id="button1id" name="button1id" ng-if="!viewChildInFosterCare"
									ng-click="childRecordInFosterCare.$invalid ? '' : validateChildRecord()"
									class="btn btn-info" type="submit"><span translate>Submit</span></button>
									
								<button id="button2id" name="button2id" class="btn btn-info" type="submit"
									ng-if="viewChildInFosterCare" ng-click="printChildRecordInFosterData()">
									<span translate>Print</span></button>
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
	<script>
		var app = angular.module('childFosterCare', ['gettext']);
		var myAppConstructor = angular.module('childFosterCare');
	</script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
	<script src="resources/js/AngularController/childInFosterCare.js"></script>
	<script src="resources/js/moment-with-locales.js"></script>
	<script src="resources/js/jquery-ui.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#dateOfPlacement1").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$("#datepicker6").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$("#datepicker7").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$("#datepicker8").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
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

	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script type="text/javascript" src="resources/js/download.js"></script>
	<script src="${jQueryUiCore}"></script>
</body>
</html>
