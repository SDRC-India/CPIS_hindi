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
	  <title>PERIODIC REPORT FOR CHILD RELEASED ON PROBATION - FORM 10</title>
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
	

	<body ng-app="CICLPeriodicReportApp" ng-controller="CICLPeriodicReportController" ng-cloak>
   <jsp:include page="./common/cctsHeader.jsp" />	
	<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to save the details.</span></h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button3id" name="button3id" class="btn btn-info bigbutton" type="submit" ng-click="saveData()" class="close" data-dismiss="modal" aria-hidden="true"><span translate>Submit</span></button>
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="childIdModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	         <h4 class="modal-title" id="myModalLabel"><span translate>The form has been submitted successfully.</span></h4>
	           <p style="text-align:center">
			  <button id="button5id" name="button6id" class="btn btn-info" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="printForm()"><span translate>Print</span></button>
	          <button id="button5id" name="button5id" class="btn btn-info" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
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
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>  
	      </div>    
	    </div>
	  </div>
	</div>
	
	<input type="hidden" id="modelValue" value="${selectedChild}">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				
				<div class="box-border box-border-padding">
				<!-- <hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr>		 -->	
					<div class="childlist-heading1 borderPersonal"><span translate>Periodic report for child released on probation.</span><br><span translate>FORM 10</span><br><span translate>[Rules 11(9) and 64 (3) (xiii)]</span></div>	
					<form class="form-horizontal basicchildform" id="periodicForm" name="periodicForm" >
						<fieldset>
						<div class="grey-header marginTop"
								style="border-top: none; margin-top: 4px;"><span translate>Periodic Report By Probation Officer</span></div>
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
										<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{periodicFormInfo.childName | limitTo : 30}}</span>
									</div>
								</div>
							</div>
						<div class="form-group box-border-padding interimPlanmargintop">
						  <label class="col-md-4 control-label" for="textinput"><span translate>1. FIR No.</span> </label>  
						  <div class="col-md-7">
						  <input maxlength="15" id="caseno" name="caseno" ng-disabled="ifSubmittedDisable||ifPreFetchDisable" placeholder="{{'Enter FIR no.'| translate}}"
						   class="form-control input-md" type="text" ng-model="periodicFormInfo.firNumber" 
						    oninvalid="this.setCustomValidity('Please enter FIR no')" 
						   oninput="setCustomValidity('')">
						
						  </div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>2. Police Station.</span> </label>  
						  <div class="col-md-7">
						  <input maxlength="30" placeholder="{{'Enter police station'| translate}}" ng-disabled="ifSubmittedDisable||ifPreFetchDisable"
						   class="form-control input-md" type="text" ng-model="periodicFormInfo.policeStation" 
						    oninvalid="this.setCustomValidity('Please enter police station')" 
						   oninput="setCustomValidity('')" >
						
						  </div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>3. U/Sections.</span> </label>  
						  <div class="col-md-7">
						  <input maxlength="15" placeholder="{{'Enter u/sections'| translate}}" ng-disabled="ifSubmittedDisable||ifPreFetchDisable"
						   class="form-control input-md" type="text" ng-model="periodicFormInfo.sections" 
						    oninvalid="this.setCustomValidity('Please enter u/sections')" 
						   oninput="setCustomValidity('')" >
						
						  </div>
						</div>
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>4. In The Matter of.</span> <span class="mandatory_star">&#42;</span></label>    
						  <div class="col-md-3">
						  <input maxlength="30" placeholder="{{'Enter the matter'| translate}}" ng-disabled="ifSubmittedDisable"
						   class="form-control input-md" type="text" ng-model="periodicFormInfo.matter" 
						   required oninvalid="this.setCustomValidity('Please enter the matter')" 
						   oninput="setCustomValidity('')" >
			
						  </div>
						
						  <label class="col-md-1 control-label" for="textinput"><span translate>5. VS</span> </label>    
						  <div class="col-md-3">
						  <input maxlength="30" placeholder="{{'Enter the VS'| translate}}" ng-disabled="ifSubmittedDisable"
						   class="form-control input-md" type="text" ng-model="periodicFormInfo.vs" 
						   oninvalid="this.setCustomValidity('Please enter the VS')" 
						   oninput="setCustomValidity('')" >
						  </div>
						</div>
				
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>6. Child Name</span> </label>    
						  <div class="col-md-7">
						  <input maxlength="30" id="child" name="child"  placeholder="{{'Enter name of the child '| translate}}" ng-disabled="ifSubmittedDisable||ifPreFetchDisable"
						   class="form-control input-md" type="text" ng-model="periodicFormInfo.childName" 
						    oninvalid="this.setCustomValidity('Please enter the name of the child ')" 
						   oninput="setCustomValidity('')" >
						
						  </div>
						</div>
				
						<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>7. Age </span></label>  
							  <div class="col-md-7">
							  <input id="producerAge" placeholder="{{'Enter age'| translate}}" class="form-control input-md" ng-disabled="ifSubmittedDisable||ifPreFetchDisable"
							  	oninvalid="this.setCustomValidity('Please enter age of the person')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.age" >
							  </div>
							</div>
						
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>8. Date </span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepicker" ng-disabled="ifSubmittedDisable" ng-model="periodicFormInfo.entryDate" readonly class="form-control">
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>9. Child under care of </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="familysex" class="form-control" ng-disabled="ifSubmittedDisable"
									ng-model="periodicFormInfo.childUnderCare.id" ng-disabled="ifSubmittedDisable"
									oninvalid="this.setCustomValidity('Please select one option')"
									oninput="setCustomValidity('')" required>
									<option value="" disabled selected translate>Select</option>
									<option ng-repeat="item in icpParentType" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
								</select>
							 </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>10. Child under supervision of</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input maxlength="30" id="producerAge" placeholder="{{'Enter name of supervisor'| translate}}" class="form-control input-md" ng-model="periodicFormInfo.childUnderSupervision"
							  	oninvalid="this.setCustomValidity('Please enter name of supervisor')" oninput="setCustomValidity('')" ng-disabled="ifSubmittedDisable"
							  	required>   
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>11. Reg No.</span></label>  
							  <div class="col-md-7">
							  <input maxlength="15" id="regno" placeholder="{{'Enter Reg no.'| translate}}" class="form-control input-md" ng-disabled="ifSubmittedDisable"
							  	oninvalid="this.setCustomValidity('Please enter Reg no.')" oninput="setCustomValidity('')"
							  	ng-model="periodicFormInfo.regNo" > 
							  </div>
							</div>
							
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>12. Sex</span></label> 
							  <div class="col-md-7">
							    <select id="gender" class="form-control" 
							        ng-disabled="ifSubmittedDisable||ifPreFetchDisable"
									ng-model="periodicFormInfo.sexObject.id"
									oninvalid="this.setCustomValidity('Please select gender of the person')"
									oninput="setCustomValidity('')" >
									<option value="" disabled selected>Select</option>
									<option ng-repeat="item in childSex"
										ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
								</select>
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>13. Father's Name </span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input maxlength="30" id="fathers" placeholder="{{'Enter Father\'s Name'| translate}}"  class="form-control input-md"
							    ng-disabled="ifSubmittedDisable"
							  	oninvalid="this.setCustomValidity('Please enter father's name ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.fatherName" >
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>14. Religion</span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="religion" class="form-control" 
							            ng-disabled="ifSubmittedDisable" 
										ng-model="periodicFormInfo.religionObject.id" ng-change="religionOthers();showReligionCast()"
										oninvalid="this.setCustomValidity('Please select religion details')"
										oninput="setCustomValidity('')" required>
										<option value="" disabled selected>{{'Select Religion' | translate}}</option>
										<option ng-repeat="item in religionList" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
									</select>
							  </div>
							</div>
							<div class="form-group box-border-padding"
								ng-if="showOtherCast">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Please Specify </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<input id="idTypeOther" maxlength="30" placeholder="{{'Please specify'| translate}}"
									    ng-disabled="ifSubmittedDisable"
										class="form-control input-md" type="text"
										ng-model="periodicFormInfo.casteOtherType" required>
								</div>
							</div>
							<div class="form-group box-border-padding"
								ng-if="showcast">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Caste </span><span class="mandatory_star">&#42;</span></label>
								<div class="col-md-7">
									<select id="caste" name="caste" class="form-control" ng-disabled="ifSubmittedDisable"  
										ng-model="periodicFormInfo.casteObject.id"
										ng-disabled="ifSubmittedDisable"
										oninvalid="this.setCustomValidity('Please select caste')"
										oninput="setCustomValidity('')" required>
										<option value="" disabled selected translate>Select Caste</option>
										<option ng-repeat="item in casteList" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
									</select>
								</div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>15. Education </span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							 	<select id="education" class="form-control" ng-disabled="ifSubmittedDisable"
									ng-model="periodicFormInfo.educationObject.id"
									ng-disabled="ifSubmittedDisable"
									oninvalid="this.setCustomValidity('Please select education details')"
									oninput="setCustomValidity('')" required>
									<option value="" disabled selected translate>Select</option>
									<option ng-repeat="item in education" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
								</select>
							 
							  <div id="personAgeerror" class="error-style"></div>   
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>16. Vocational training, if any </span> </label>  
							  <div class="col-md-7">
							  <input max-length="200" id="vocational" placeholder="{{'Enter vocational training'| translate}}"  class="form-control input-md"
							    ng-disabled="ifSubmittedDisable"
							  	oninvalid="this.setCustomValidity('Please enter vocational training ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.vocationalTraining" >
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>17. Language(s) known </span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <select id="language" class="form-control" ng-disabled="ifSubmittedDisable"
							        ng-disabled="ifSubmittedDisable"
									ng-model="periodicFormInfo.languageObject.id" ng-change="OtherLanguage()"
									oninvalid="this.setCustomValidity('Please select languages known ')"
									oninput="setCustomValidity('')" required>
									<option value="" disabled selected translate>Select</option>
									<option ng-repeat="item in mediumOfInstructionList" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
								</select>
							  </div>
							</div>
							<div class="form-group box-border-padding"
								ng-if="showOtherLanguage">
								<label class="col-md-4 control-label" for="textinput"><span translate>(i) Please Specify</span></label>
								<div class="col-md-7">
									<input maxlength="30" id="idTypelanguageOther" placeholder="{{'Please specify'| translate}}"
								     	ng-disabled="ifSubmittedDisable" required
										class="form-control input-md" type="text"
										ng-model="periodicFormInfo.otherLanguage">
								</div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>18. Next court date</span> <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepicker2" ng-disabled="ifSubmittedDisable" ng-model="periodicFormInfo.nextCourtDate" readonly class="form-control">
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>19. Employment, if any </span> </label>  
							  <div class="col-md-7">
							  <input max-length="200" id="employment" placeholder="{{'Enter employment'| translate}}"  class="form-control input-md"
							    ng-disabled="ifSubmittedDisable"
							  	oninvalid="this.setCustomValidity('Please enter employment ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.employmentDetails" >
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>20. Date of admission(in case of fit person/fit facility)</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepicker3" ng-disabled="ifSubmittedDisable" ng-model="periodicFormInfo.dateOfAdmission" readonly class="form-control">
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>21. Case details and summary </span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  	<textarea maxlength="200" class="form-control" ng-disabled="ifSubmittedDisable" id="withWhomChildFoundAddress" name="withWhomChildFoundAddress" ng-model="periodicFormInfo.caseDetailsAndSummary" placeholder="{{'Enter case details and summary'| translate}}" required></textarea>
							  </div>
							</div>
							<div class="grey-header"><span translate>22. Preliminary details</span></div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>(i) Visit Date</span>
							 <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" ng-disabled="ifSubmittedDisable" id="datepicker4" ng-model="periodicFormInfo.visitDate" readonly class="form-control">
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(ii) Name of parent / guardian</span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input maxlength="30" id="guardian" placeholder="{{'Enter Name of parent / guardian'| translate}}"  class="form-control input-md"
							    ng-disabled="ifSubmittedDisable"
							  	oninvalid="this.setCustomValidity('Please enter Name of parent / guardian ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.nameOfParentGuardian" >
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Names of other adults living in the home and with whom the probation officer interacted</span><span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
									<textarea maxlength="200" required class="form-control" ng-disabled="ifSubmittedDisable"
										id="withWhomChildFoundAddress"
										name="withWhomChildFoundAddress"
										ng-model="periodicFormInfo.nameOfOtherAdults"
										placeholder="{{'Enter name of other adults'| translate}}"></textarea>
								</div>
							</div>
							
						<div class="grey-header"><span translate>23. Observations</span></div>
						
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(i) Behaviours of child </span>   <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" id="behaviors" placeholder="{{'Enter child behaviours'| translate}}"  class="form-control input-md"
							    ng-disabled="ifSubmittedDisable"
							  	oninvalid="this.setCustomValidity('Please enter child behaviours ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.behaviourOfChild" >
							  <div id="personAgeerror" class="error-style"></div>   
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(ii) Physical and mental health status/needs of child and family </span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" id="health" placeholder="{{'Enter physical and mental health status'| translate}}"  class="form-control input-md"
							    ng-disabled="ifSubmittedDisable"
							  	oninvalid="this.setCustomValidity('Please enter physical and mental health status ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.physicalAndMentalHealthStatus" >
							  </div>
							</div>
	
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Inter-personal relationship of the child with the family </span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" id="relationship" placeholder="{{'Enter inter-personal relationship of the child with the family'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter Inter-personal relationship of the child with the family ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.interpersonalRelationshipChildwithFamily" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Inter-personal relationship of the child with the friends </span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" id="relationshipwithfriends" placeholder="{{'Enter inter-personal relationship of the child with the friends'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter Inter-personal relationship of the child with the friends ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.interpersonalRelationshipChildwithFriends" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(v) Safety and supervision in the family </span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" id="supervision" placeholder="{{'Enter safety and supervision in the family'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter safety and supervision in the family ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.safetyAndSupervisionInFamily" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(vi) Difficulties faced by the child </span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter difficulties faced by the child'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter difficulties faced by the child ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.difficultiesFacedByChild" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(vii) Difficulties faced by the family </span>  <span class="mandatory_star">&#42;</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter difficulties faced by the family'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter difficulties faced by the family ')" oninput="setCustomValidity('')"
							  	required ng-model="periodicFormInfo.difficultiesFacedByFamily" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(viii) Changes in the household</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter changes in the household'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter changes in the household ')" oninput="setCustomValidity('')"
							  	ng-model="periodicFormInfo.changesInHousehold" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(ix) Vocational training, if any being undertaken by the child </span>  </label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter vocational training, if any being undertaken by the child'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter vocational training, if any being undertaken by the child ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.vocationalTrainingByChild" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(x) Engagement of child in any anti-social activities or harmful activities (Examples could be exhibiting bullying behaviour, violent outbursts, destructions, self-harm, lying, defiance, impulsiveness, lack of empathy, sexually deviant actions etc.) </span>  </label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter engagement of child in any anti-social activities'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter engagement of child in any anti-social activities ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.engagementOfChildInAntiSocialActivities" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(xi) Time elapsed since last engagement in any anti-social behavior or harmful activities</span></label>  
							  <div class="col-md-7">
							  <input maxlength="200" placeholder="{{'Enter time elapsed since last engagement in any anti-social behavior or harmful activities'| translate}}" class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter time elapsed since last engagement in any anti-social behavior or harmful activities ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.timeElapsed" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="grey-header"><span translate>24. Visit to school/ vocational training centre</span></div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(i) Name of the school/centre </span> </label>  
							  <div class="col-md-7">
							  <input maxlength="30" placeholder="{{'Enter name of the school/centre'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter name of the school/centre ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.nameOfSchoolOrCenter" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(ii) Name of the Teacher / Principal met</span> </label>  
							  <div class="col-md-7">
							  <input maxlength="30" placeholder="{{'Enter name of the Teacher / Principal met'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter name of the Teacher / Principal met ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.nameOFTeacherOrPrincipal" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Any unusual behavior observed </span> </label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter any unusual behavior observed'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter any unusual behavior observed ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.unusualBehaviour" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Feedback received on the progress of the child</span></label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter feedback received on the progress of the child'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter feedback received on the progress of the child ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.feedbackRecived" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(v) Attitude of the peers towards the child </span>  </label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter attitude of the peers towards the child'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter attitude of the peers towards the child ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.attitudeOfPeersToChild" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(vi) Attitude of the child towards the peers </span>  </label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter attitude of the child towards the peers'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter attitude of the child towards the peers ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.attitudeOFChildToPeers" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="grey-header"><span translate>25. Visit to place of employment</span></div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(i) Nature of work </span>  </label>  
							  <div class="col-md-7">
							  <input max-length="200"  placeholder="{{'Enter Nature of work'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter Nature of work ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.natureOfWork" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(ii) Working hours(in hrs)</span>  </label>  
							  <div class="col-md-7">
							  <input only-four-digits placeholder="{{'Enter Working hours'| translate}}"  class="form-control input-md onlyNumber"
							  	oninvalid="this.setCustomValidity('Working hours ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.workingHours" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iii) Attitude of the child towards work </span>  </label>  
							  <div class="col-md-7">
							  <input max-length="200"  placeholder="{{'Enter attitude of the child towards work'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter attitude  of the child towards work ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.attitudeOfChildToWork" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Violation of any labour laws, Low wages or wages being withheld,if observed and action taken against employer </span>  </label>  
							  <div class="col-md-7">
							  <input max-length="200" placeholder="{{'Enter violation of any labour laws'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please enter violation of any labour laws ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.violationOfLabourLawsAndActionTaken" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>26. Did you spend time speaking privately with the child</span><span class="mandatory_star">&#42;</span></label>
							  <div class="col-md-7" >
							 	<label class="radio-inline" for="radios-0">
							      <input name="radios" id="radios-0" ng-model="periodicFormInfo.spentTimeSpeakingPrivately" ng-disabled="ifSubmittedDisable" checked ng-click="ShowPpentTimeSpeakingPrivately('Yes')"  ng-value="spentTimeSpeakingPrivatelyYes" type="radio" ><span translate> Yes</span>
							    </label> 
							    <label class="radio-inline" for="radios-0">
							      <input name="radios" id="radios-0" ng-model="periodicFormInfo.spentTimeSpeakingPrivately" ng-disabled="ifSubmittedDisable" ng-click="ShowPpentTimeSpeakingPrivately('No')"   ng-value="spentTimeSpeakingPrivatelyNo" type="radio"> <span translate>No</span>
							    </label> 
							    <div ng-if="showDetailsSpentTimeSpeakingPrivately"><span translate>Give reasons</span>
           						 <input required type="text" class="form-control input-md" ng-disabled="ifSubmittedDisable" id="reasons" 
           						 ng-model="periodicFormInfo.detailsSpentTimeSpeakingPrivately" placeholder="{{'Please specify reason'| translate}}"
           						 ng-blur="blur(periodicFormInfo.detailsSpentTimeSpeakingPrivately,'detailsSpentTimeSpeakingPrivately')"
								 ng-trim="false" max-length="200">
        						</div>
							  </div>
							</div>
							
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate> 27. Progress made as per Rehabilitation and Restoration Plan under the Individual Care Plan</span></label>  
							  <div class="col-md-7">
									<textarea max-length="200" class="form-control" id="withWhomChildFoundAddress"
										ng-disabled="ifSubmittedDisable"
										name="withWhomChildFoundAddress"
										ng-model="periodicFormInfo.progressMadeAsRehabilition"
										placeholder="{{'Enter progress and restoration Plan'| translate}}"></textarea>
								</div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate> 28. Recommendations for modifications in  Rehabilitation and Restoration Plan under the Individual Care Plan, if any</span></label>  
							  <div class="col-md-7">
									<textarea max-length="200" class="form-control" id="withWhomChildFoundAddress"
										ng-disabled="ifSubmittedDisable"
										name="recommendations"
										ng-model="periodicFormInfo.recommendation"
										placeholder="{{'Enter recommendations for modifications'| translate}}"></textarea>
								</div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(i) Prepared by</span>  <span class="mandatory_star">&#42;</span> </label>  
							  <div class="col-md-7">
							  <input maxlength="30" placeholder="{{'Enter Prepared by'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please Prepared by ')" oninput="setCustomValidity('')"
							  	ng-model="periodicFormInfo.preparedBy" ng-disabled="ifSubmittedDisable" required>
							  </div>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>(ii) Date</span> <span class="mandatory_star">&#42;</span>
							 </label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepicker5" ng-model="periodicFormInfo.rehabilitionDate" ng-disabled="ifSubmittedDisable" readonly class="form-control">
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>(iii) Plan: Date of next visit</span> <span class="mandatory_star">&#42;</span>
							 </label>  
							  <div class="col-md-2 col-sm-6 col-xs-6">
										<input type="text" id="datepicker6" ng-model="periodicFormInfo.planDateOfNextVisit" ng-disabled="ifSubmittedDisable" readonly class="form-control">
							  </div>
							  <i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>
							<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>(iv) Action point if any</span>  </label>  
							  <div class="col-md-7">
							  <input maxlength="30" placeholder="{{'Enter action point if any'| translate}}"  class="form-control input-md"
							  	oninvalid="this.setCustomValidity('Please action point if any ')" oninput="setCustomValidity('')"
							  	 ng-model="periodicFormInfo.actionPoint" ng-disabled="ifSubmittedDisable">
							  </div>
							</div>
							
							<div style="text-align:center">
								<button id="buttonid" ng-show="!ifSubmittedDisable" name="buttonid" ng-click ="periodicForm.$invalid ? '' :validateForm()"
								class="btn btn-info" type="submit"><span translate>Submit</span></button>
								<button id="buttonid" ng-show="ifSubmittedDisable" name="buttonid" ng-click ="printForm()"
								class="btn btn-info" type="submit"><span translate>Print</span></button>
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
	var app = angular.module('CICLPeriodicReportApp', ['gettext']);
	var myAppConstructor= angular.module('CICLPeriodicReportApp');
	</script>
	<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
	
	<script src="resources/js/AngularController/CICLPeriodicReportController.js"></script>
			<!-- 	<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<!--     <script src="resources/js/bootstrap-datetimepicker.min.js"></script> -->
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   	<script type="text/javascript">
		$(document).ready(function() {
			$("#periodicFormInfo").on('submit', function(){
	   			$('#thankyouModal').modal('show');
			});
	
			$("#button3id").on('submit', function(){
				  $('#childIdModal').modal('show');
			});
			
// 			$( "#datepicker2" ).datepicker('setDate', 'today');
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
