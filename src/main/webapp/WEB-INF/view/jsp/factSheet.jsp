<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html ng-app="factsheetApp">


<!-- end  header row -->
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<title>UPCPIS</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/customLoader.css">
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>
<style type="text/css">
	#errorMessage h3{
	margin-top: 3px;
    margin-bottom: 15px;
    font-size:20px;
	}
	#dataNotFoundMessage h3{
    font-size:20px;
	}
	#errorMessage .btn{
		    color: #fff;
    background-color: #386d5c !important;
    border-color: #386d5c !important;
    border-radius: 0px !important;
    width: 96px;
}
#dataNotFoundMessage .btn{
		    color: #fff;
    background-color: #386d5c !important;
    border-color: #386d5c !important;
    border-radius: 0px !important;
    width: 96px;
}

</style>
</head>

<body ng-controller="FactsheetController" ng-cloak>
	<!-- spinner -->
	<div id="spinner" class="loader" style="display: none;"></div>
	<div id="loader-mask" class="loader" style="display: none;"></div>
	<!-- /spinner -->

	<jsp:include page="./common/cctsHeader.jsp" />
	<div id="errMsg" class="text-center">
		<serror:Error id="msgBox" errorList="${formError}"
			cssInfClass="${className}">
		</serror:Error>
	</div>
	<div id="mymain" class="container-fluid min-height-wrap">
		<form class="selection text-center" name="selctionForm">
		<div class="row text-center mb-20" style="font-size: 25px;font-weight: bold">Factsheet</div>
			<div class="row text-center mb-20" ng-show="userLevel == 2 || userLevel == 1">
			<div class="select-container text-center">
				<div class="input-group">
					<input type="text" placeholder="Division *" id="division"
						class="form-control not-visible-input" name="division"
						readonly="" ng-model="selectedDivision.areaName"
						oninvalid="this.setCustomValidity('Please select Division')">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="division in allDivisions" ng-if="division.areaId != 1"
								ng-click="selectDivision(division)"><a href="">{{division.areaName}}</a></li>
						</ul>
					</div>
				</div>
			</div>
			</div>
			<div class="row text-center mb-20"  ng-show="userLevel <= 3">
			<div class="select-container text-center">
				<div class="input-group">
					<input type="text" placeholder="District *" id="district"
						class="form-control not-visible-input" name="district"
						readonly="" ng-model="selectedDistrict.areaName"
						oninvalid="this.setCustomValidity('Please select District')">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button"  ng-disabled="districtDisable">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="district in selectedDivision.children"
								ng-click="selectDistrict(district)"><a href="">{{district.areaName}}</a></li>
						</ul>
					</div>
				</div>
			</div>
			</div>
			<div class="row text-center mb-20 date-selection">
			<label ng-show="selectedIndicator.indicatorUnitSubgroupId != 232 && selectedIndicator.indicatorUnitSubgroupId != 198">From : </label>
			<label style="    margin-left: 11px;" ng-show="selectedIndicator.indicatorUnitSubgroupId == 232 || selectedIndicator.indicatorUnitSubgroupId == 198">&nbsp;&nbsp;Till : </label>
			<div class="select-container text-center" >
				
				<div class="input-group ">
					<input type="text" placeholder="Year *" id="year"
						class="form-control not-visible-input" readonly=""
						ng-model="selectedStartYear">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="year in years"
								ng-click="selectStartYear(year)"><a href="">{{year}}</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="select-container text-center" >
				<div class="input-group ">
					<input type="text" placeholder="Month *" id="timePeriod"
						class="form-control not-visible-input" readonly=""
						ng-model="selectedStartTimeperiod.timeperiod.split(' ')[0]">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="timeperiod in allStartTimeperiods"
								ng-click="selectStartTimeperiod(timeperiod)"><a href="">{{timeperiod.timeperiod.split(' ')[0]}}</a></li>
						</ul>
					</div>
				</div>
			</div>
			</div>
			<div class="row text-center mb-20 date-selection">
			<label style="    margin-left: 11px;" >&nbsp;&nbsp;To : </label>
			
			<div class="select-container text-center">
				
				<div class="input-group ">
					<input type="text" placeholder="Year *" id="year"
						class="form-control not-visible-input" readonly=""
						ng-model="selectedEndYear">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="year in years | filterEndYear:this"
								ng-click="selectEndYear(year)"><a href="">{{year}}</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="select-container text-center">
				<div class="input-group ">
					<input type="text" placeholder="Month *" id="timePeriod"
						class="form-control not-visible-input" readonly=""
						ng-model="selectedEndTimeperiod.timeperiod.split(' ')[0]">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="timeperiod in allEndTimeperiods"
								ng-click="selectEndTimeperiod(timeperiod)"><a href="">{{timeperiod.timeperiod.split(' ')[0]}}</a></li>
						</ul>
					</div>
				</div>
			</div>
			</div>
			<div class="row text-center mb-20">
			<div class="text-right selction-submit">
				<button type="submit" class="submit-selection"
					ng-click="downloadFactSheet()">Submit</button>
			</div>
			</div>
			
		</form>
	</div>
	
	<!-- Modal for error message -->
	<div id="errorMessage" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content modal-content-noheight">
				<div class="modal-body text-center">
					<h3>Please select the <span style="color: #517e71">{{errorMsg}}</span>.</h3>
					<button type="button" class="btn btn btn-info errorOk" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal for no data found message -->
	<div id="dataNotFoundMessage" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content modal-content-noheight">
				<div class="modal-body text-center">
					<h3>{{nodataMsg}}</h3><br>
					<button type="button" class="btn btn btn-info errorOk" data-dismiss="modal">Close</button>
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
	
	<div class="modal fade" id="noChildSelected" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign">Please select a child to proceed further.</h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>
	<jsp:include page="./common/cctsFooter.jsp" />
	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/angular.min.js"></script>
	<script>
		var app = angular.module("factsheetApp", []);
		var myAppConstructor = angular.module('factsheetApp');
	</script>
	<script src="resources/js/AngularController/factsheetController.js"
		type="text/javascript"></script>
					<!-- <script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
	<script src="resources/js/AngularService/commonService.js"
		type="text/javascript"></script>	
	<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript">
		$("#msgBox").show().delay(2000).fadeOut(400);
		$(".min-height-wrap").css("min-height", $(window).height())
	</script>
</body>
</html>