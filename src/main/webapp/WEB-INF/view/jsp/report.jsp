<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html ng-app="reportApp">


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
	}
	#errorMessage .btn{
		    color: #fff;
    background-color: #386d5c !important;
    border-color: #386d5c !important;
    border-radius: 0px !important;
    width: 96px;
}
</style>
</head>

<body ng-controller="ReportController" ng-cloak>
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
		<div style="font-size: 25px;font-weight: bold;padding-bottom: 10px">Report</div>
			<div class="row text-center mb-20">
			
			<!-- sector -->
			<div class="select-container text-center margin-leftsector" ng-if="designation<=6">
				<div class="input-group">
					<input type="text" placeholder="Sector *" id="sector"
						class="form-control not-visible-input" name="sector"
						readonly="" ng-model="selectedsectorName"
						oninvalid="this.setCustomValidity('Please select Sector')">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="sector in allSectors" 
							ng-click="selectSector(sector)"><a href="">{{sector.sectorName}}</a></li>
								
						</ul>
					</div>
				</div>
			</div>
			<!-- end sector -->
			
			<div class="select-container text-center" id="indicatorField" ng-show="ifSectorSelected">
				<div class="input-group">
					<input type="text" placeholder="Indicator *" id="indicator"
						class="form-control not-visible-input" name="indicator"
						readonly="" ng-model="selectedIndicator.indicatorName"
						oninvalid="this.setCustomValidity('Please select Indicator')">
					<div class="input-group-btn" style="position: relative;">
						<button data-toggle="dropdown"
							class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-list"></i>
						</button>
						<ul class="dropdown-menu" role="menu" style="max-height: 300px; overflow-y: auto;">
							<li ng-repeat="indicator in (designation>6)?allIndicators:selectedsectorName=='CNCP'?cncpIndicators:ciclIndicators"
								ng-click="selectIndicator(indicator)"><a href="">{{indicator.indicatorName}}</a></li>
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
			<label style="    margin-left: 11px;"  ng-show="selectedIndicator.indicatorUnitSubgroupId != 232 && selectedIndicator.indicatorUnitSubgroupId != 198" >&nbsp;&nbsp;To : </label>
			
			<div class="select-container text-center" ng-show="selectedIndicator.indicatorUnitSubgroupId != 232 && selectedIndicator.indicatorUnitSubgroupId != 198">
				
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
			
			<div class="select-container text-center" ng-show="selectedIndicator.indicatorUnitSubgroupId != 232 && selectedIndicator.indicatorUnitSubgroupId != 198">
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
					ng-click="callSubmit()">Submit</button>
			</div>
			</div>
			
		</form>
		<div id="main-content">
			<!-- <button class="submit-selection" ng-if="tableData.length"
				style="float: right; background-color: #37393d;"
				ng-click="exportTableData('dataTable')">
				<i class="fa fa-download" aria-hidden="true"></i> Download Excel
			</button> -->
			<div class="table-section-container" ng-if="tableData.length ">
				<h5>{{lockedIndicator.indicatorName}}&nbsp;({{lockedStartTimeperiod.timeperiod}}<span ng-if="lockedIndicator.indicatorUnitSubgroupId != 232 && lockedIndicator.indicatorUnitSubgroupId != 198">-</span>{{lockedEndTimeperiod.timeperiod}})</h5>
				<div ng-if="tableData.length || tableDataForChildAbove18.length" class="download-excel-container text-right selction-submit" style="margin-left: 18px;">
					<button type="submit" class="submit-selection"
						ng-click="exportTableData('dataTable', lockedIndicator.indicatorName)"><i class="fa fa-file-excel-o" aria-hidden="true"></i> Download Excel</button>
				</div>
				<!-- <div ng-if="tableDataForChildAbove18.length" class="download-excel-container text-right selction-submit" style="margin-left: 18px;" ng-show="callAbove18IndicatorFlag">
					<button type="submit" class="submit-selection"
						ng-click="exportTableData('dataTable1')"><i class="fa fa-file-excel-o" aria-hidden="true"></i> Download Excel</button>
				</div> -->
			</div>
 			<div class=" table-responsive" style="width: 100%; margin-bottom: 100px;" ng-show="tableData.length">
 				<table items="tableData" show-filter="true" cellpadding="0"
 					cellspacing="0" border="0" class="dataTable table table-bordered"
 					id="dataTable" ng-class="{'dhSelected':selectedFacility.iC_NId==17}">
 					<thead>
 						<tr>
 						
 						<th ng-click="order('District')" style="position: relative;" rowspan="2">District
 								<div class="sorting1">
 									<i class="fa fa-caret-up asc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != 'District' || sortReverse == true)}"></i>
 									<i class="fa fa-caret-down dsc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != 'District' || sortReverse == false)}"></i>
 								</div>
 							</th>
 							
 							<th ng-click="order('CCI')" style="position: relative;" rowspan="2" ng-if="tableData[0].CCI!=undefined">CCI
 								<div class="sorting1">
 									<i class="fa fa-caret-up asc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != 'CCI' || sortReverse == true)}"></i>
 									<i class="fa fa-caret-down dsc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != 'CCI' || sortReverse == false)}"></i>
 								</div>
 							</th>
 							
 							<th ng-repeat="column in topHeader "
 								ng-click="column.childs==null||column.childs.length==0?order(column.name):''" ng-style="column.childs==null||column.childs.length==0?'':{'text-align':'center','font-size': '18px'}"style="position: relative; " rowspan="{{column.childs==null||column.childs.length==0?2:1}}" colspan="{{column.childs==null||column.childs.length==0?1:column.childs.length}}">{{column.name}}
 								<div class="sorting1" ng-if="column.childs==null||column.childs.length==0">
 									<i class="fa fa-caret-up asc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != column.name || sortReverse == true)}"></i>
 									<i class="fa fa-caret-down dsc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != column.name || sortReverse == false)}"></i>
 								</div>
 							</th>
						</tr>
						
					<tr>
 							<th ng-repeat="column in bottomHeader "
 								ng-click="order(column.name)" style="position: relative;">{{column.name}}
 								<div class="sorting1">
 									<i class="fa fa-caret-up asc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != column.name || sortReverse == true)}"></i>
 									<i class="fa fa-caret-down dsc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != column.name || sortReverse == false)}"></i>
 								</div>
 							</th>
						</tr>
							
 					</thead>
					<tbody>
 						<tr 
 							ng-repeat="rowData in tableData | orderBy:filterType:sortReverse">
 							<td 
								sortable="'{{rowData.District}}'"><span>{{rowData['District']}}</span></td>
								
							<td 
								sortable="'{{rowData.CCI}}'" ng-if="tableData[0].CCI!=undefined"><span>{{rowData['CCI']}}</span></td>	
								
 							<td ng-repeat="column in columnHeader " ng-click=" rowData[column.name]!=0 && column.name!='CCI' ? getChildDetailTable(rowData, column.name):''" ng-class="{'clickable':  rowData[column.name]!=0 && column.name!='CCI'}"
								sortable="'{{rowData.column.name}}'"><span>{{rowData[column.name]}}</span></td>
 						</tr>
					</tbody>
 				</table>
			</div>
			<!-- <div class=" table-responsive" style="width: 100%; margin-bottom: 100px;" ng-show="callAbove18IndicatorFlag">
				<table items="tableDataForChildAbove18" show-filter="true" cellpadding="0"
					cellspacing="0" border="0" class="dataTable table table-bordered"
					id="dataTable1">
					<thead>
						<tr>
							<th ng-repeat="column in columns1"
								ng-click="order(column)" style="position: relative;">{{column}}
								<div class="sorting1">
									<i class="fa fa-caret-up asc" aria-hidden="true"
										ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
									<i class="fa fa-caret-down dsc" aria-hidden="true"
										ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="rowData in tableDataForChildAbove18  | orderBy:filterType:sortReverse">
							<td ng-repeat="column in columns1"
								sortable="'{{rowData.column}}'">{{rowData[column]}}</td>
						</tr>
					</tbody>
				</table>
			</div> -->
		</div>
	</div>
	<!-- Modal for division table -->
	<div id="childDetailTable" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body">
					<div class="table-section-modal-container">
					<div class="modalIndicatorName">{{lockedIndicator.indicatorName}} {{lockedSubIndicator}}</div>
					<div class="downloadexclbtn">
						<button class="submit-selection" ng-if="tableData.length"
						ng-click="exportTableData('divisionalDataTable', lockedIndicator.indicatorName+' '+lockedSubIndicator)">
						<i class="fa fa-download" aria-hidden="true"></i> Download Excel
						</button>
						</div>
						<button type="button" class="close" data-dismiss="modal">×</button>
					</div>
					<div class=" table-responsive" style="width: 100%">
						<table items="tableData" show-filter="true" cellpadding="0"
							cellspacing="0" border="0" class="dataTable table table-bordered"
							id="divisionalDataTable">
							<thead>
								<tr>
									<th style="position: relative;">Sl.&nbsp;No.
										
									</th>
									<th ng-click="order('childId')" style="position: relative;">Child&nbsp;Id
										<div class="sorting1">
											<i class="fa fa-caret-up asc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
											<i class="fa fa-caret-down dsc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
										</div>
									</th>
									<th ng-click="order('childName')" style="position: relative;">Child&nbsp;Name
										<div class="sorting1">
											<i class="fa fa-caret-up asc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
											<i class="fa fa-caret-down dsc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
										</div>
									</th>
									
									<th ng-click="order('sirFatherName')" style="position: relative;">Father&nbsp;Name
										<div class="sorting1">
											<i class="fa fa-caret-up asc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
											<i class="fa fa-caret-down dsc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
										</div>
									</th>
									<th ng-click="order('sirMotherName')" style="position: relative;">Mother&nbsp;Name
										<div class="sorting1">
											<i class="fa fa-caret-up asc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
											<i class="fa fa-caret-down dsc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
										</div>
									</th>
									<th ng-click="order('address')" style="position: relative;">Address
										<div class="sorting1">
											<i class="fa fa-caret-up asc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
											<i class="fa fa-caret-down dsc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
										</div>
									</th>
									<th ng-if="lockedIndicator.indicatorName =='ICP developed for CNCP cases' || lockedIndicator.indicatorName =='ICP developed for CICL cases' || lockedIndicator.indicatorName =='Follow up of CNCP distributed by time period'"
									 ng-click="order('count')" style="position: relative;">Form&nbsp;Updation&nbsp;Count
										<div class="sorting1">
											<i class="fa fa-caret-up asc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
											<i class="fa fa-caret-down dsc" aria-hidden="true"
												ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr
									ng-repeat="rowData in childDetailTableData | orderBy:filterType:sortReverse"
									>
									<td sortable="'{{rowData.column}}'">{{$index+1}}</td>
									<td sortable="'{{rowData.column}}'">{{rowData["childId"]}}</td>
									<td sortable="'{{rowData.column}}'">{{rowData["childName"]}}</td>
									<td sortable="'{{rowData.column}}'">{{rowData["sirFatherName"]}}</td>
									<td sortable="'{{rowData.column}}'">{{rowData["sirMotherName"]}}</td>
									<td sortable="'{{rowData.column}}'">{{rowData["address"]}}</td>
									<td sortable="'{{rowData.column}}'" ng-if="lockedIndicator.indicatorName =='ICP developed for CNCP cases' || lockedIndicator.indicatorName =='ICP developed for CICL cases' || lockedIndicator.indicatorName =='Follow up of CNCP distributed by time period'">{{rowData["count"]}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal for error message -->
	<div id="sectorMessage" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content modal-content-noheight">
				<div class="modal-body text-center">
					<h3>Please select the Sector.</h3>
					<button type="button" class="btn btn btn-info errorOk" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
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
	<!-- Modal for no data available -->
	<div id="noDataAvailable" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content modal-content-noheight">
				<div class="modal-body text-center">
					<h3>No data available.</h3>
					<button type="button" class="btn btn btn-info bigbutton2 errorOk" data-dismiss="modal">Close</button>
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
	<jsp:include page="./common/cctsFooter.jsp" />
	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/angular.min.js"></script>
	<script>
		var app = angular.module("reportApp", []);
		var myAppConstructor = angular.module('reportApp');
	</script>
	<script src="resources/js/AngularController/reportController.js"
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