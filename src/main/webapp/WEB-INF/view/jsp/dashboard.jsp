<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>


<html lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<title>DASHBOARD</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<!-- <link rel="stylesheet" href="resources/css/style.css"> -->
<!-- Bootstrap css -->

<link rel="stylesheet" href="resources/css/customLoader.css">
<%-- <spring:url value="/resources/css/style.css" var="styleCss" />
<link href="${styleCss}" rel="stylesheet" /> --%>
<!-- jquery-ui.css file is not that big so we can afford to load it -->
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>
<style>
 @media(max-width: 565px){
 	.row, .col-md-12, .col-sm-12 {
    	padding: 0 !important;
	}
 }
</style>

</head>


<body ng-app="dashboardApp" ng-controller="DashboardController" ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
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
	<!-- spinner -->
	<div id="spinner" class="loader" style="display: none;"></div>
	<div id="loader-mask" class="loader" style="display: none;"></div>
	<!-- /spinner -->
	<div style="position: fixed; right: -110px; z-index: 199;"
								class="download-container" 
								ng-class="{'remove': false}">
								<button type="button" id="pdfDownloadBtn"
									class="btn pdfDownloadBtn" title="Download PDF">
									<i class="fa fa-lg fa-file-pdf-o"></i> &nbsp; Download PDF
								</button>
							</div>
	<div class="dashboardSection">
		<div class="left-menu-button-container">
			<button data-toggle="dropdown" style="background-color: #215847; color: #FFF;" class="btn btn-color dropdown-toggle" type="button">
							<i class="fa fa-building-o"></i>
						</button>
		</div>
		<div class="col-md-1 left-section-menu">
			<ul class="left-menu">
				<li ng-repeat="sector in allSectors"><a href="#"
					ng-class="{'active': sector.sectorId == selectedSector.sectorId}"
					ng-click="selectSector(sector)"> {{sector.sectorName}} </a></li>
			</ul>
			<ul class="left-menu submenu-cci" ng-if="selectedSector.sectorId==3 || selectedSector.sectorId==4 || selectedSector.sectorId==5">
				<li ng-repeat="sector in subSectors"><a href="#"
					ng-class="{'active': sector.sectorId == selectedSector.sectorId}"
					ng-click="selectSector(sector)"> {{sector.sectorName}} </a></li>
			</ul>
		</div>
		<div class="col-md-11 content-section">
			<div class="content-area">
				<div class="row box-container">
					<div class="col-md-4 text-center single-box"
						ng-repeat="data in comparisionData">
						<h3 ng-class="data.cssClass">{{data.diffrencePercent > 0 ? "+":"" }}{{data.diffrencePercent != 'N/A' ?data.diffrencePercent + '%': 'N/A'}}</h3>
						<h4>{{data.oldValue}} vs {{data.newValue}}</h4>
						<p>{{data.indicatorName}}<br><b>{{data.timePeriod}}</p>
					</div>
				</div>
				<div class="row select-border">
					<div class="col-md-6 select-container">
						<div class="input-group" style="margin-right: 20px;">
							<input type="text" placeholder="Select Indicator"
								data-toggle="tooltip" data-original-title="{{selectedIndicator.indicatorName}}"
								data-placement="bottom"
								class="form-control not-visible-input ng-pristine ng-untouched ng-valid ng-empty"
								readonly="" ng-model="selectedIndicator.indicatorName">
							<div class="input-group-btn" style="position: relative;">
								<button data-toggle="dropdown"
									class="btn btn-color dropdown-toggle" type="button">
									<i class="fa fa-list"></i>
								</button>
								<ul class="dropdown-menu" style="max-height: 300px; overflow-y: auto; max-width: 380px" role="menu">
									<li ng-repeat="indicator in selectedSector.iusModel"
										ng-click="selectIndicator(indicator)"><a href>{{indicator.indicatorName}}</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-3 select-container">
						<div class="timeperiod row select-container">
							<div class="input-group">
								<input type="text" placeholder="Select Timeperiod"
									data-toggle="tooltip"
									data-original-title="{{selectedTimeperiod.timeperiod.split(' ')[0]}}"
									data-placement="bottom" class="form-control ng-valid ng-empty"
									readonly=""
									ng-model="selectedTimeperiod.timeperiod.split(' ')[0]">
								<div class="input-group-btn" style="position: relative;">
									<button data-toggle="dropdown"
										style="background-color: #215847 !important;"
										class="btn btn-color dropdown-toggle" type="button">
										<i class="fa fa-list"></i>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li ng-repeat="timeperiod in allTimeperiods"
											ng-click="selectTimeperiod(timeperiod)"><a href>{{timeperiod.timeperiod.split('
												')[0]}}</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3 select-container">
						<div class="year row select-container">
							<div class="input-group">
								<input type="text" placeholder="Select Year"
									data-toggle="tooltip" data-original-title="{{selectedYear}}"
									data-placement="bottom" class="form-control ng-valid ng-empty"
									readonly="" ng-model="selectedYear">
								<div class="input-group-btn" style="position: relative;">
									<button data-toggle="dropdown"
										style="background-color: #215847 !important;"
										class="btn btn-color dropdown-toggle" type="button">
										<i class="fa fa-list"></i>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li ng-repeat="year in years" ng-click="selectYear(year)"><a
											href>{{year}}</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="map_popover">
						<div class="map_popover_close"></div>
						<div class="map_popover_content"></div>

					</div>
					<div class="col-md-6">
						<div class="callPopup samiksha-map-popup">
							<i class="fa fa-expand" ng-click="mapPopout()" aria-hidden="true"></i>
						</div>
						<samiksha-map id="pageMap" ng-style="style()"
							style="display:block;margin-top: 15px;"></samiksha-map>
						<!-- <ul class="legends-section">
							<li class="legend" ng-repeat="legend in legends"><div
									ng-class="legend.value"
									style="width: 15px; height: 15px; display: inline-block;"></div>
								&nbsp;<b>{{legend.desc}} ({{legend.value == 'firstslices' ? numberOfFirstslices : legend.value == 'secondslices' ?  numberOfSecondslices  : 
								legend.value == 'thirdslices' ? numberOfThirdslices : legend.value == 'fourthslices' ?  numberOfFourthslices : numberOfFifthslices }})</b></li>
								
						</ul> -->
					</div>
					<div class="col-md-6">
						<div class="callPopup samiksha-line-popup">
							<i class="fa fa-expand" aria-hidden="true"></i>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="col-sm-12 text-center" ng-repeat="data in ldata"
									id="lineChartLandingPage">
									<h4 class="district-heading">{{selectedDistrictName}}<div ng-if="selectedDistrictId" ng-click="showStateLineChart()" class="reset-dist">
							<i class="fa fa-undo" aria-hidden="true"></i>
						</div></h4>
									<h3 style="color: red;" ng-if="!ldataAvailable"
										class="no-data-chart">No Data Available</h3>
									<samiksha-line id="pageLineChart" dataprovider="data"></samiksha-line>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		
	</div>
	
	
	<div id="largeLineChartContainer" class="popup">
		<div class="popup-close js-popup-close modal-close" >X</div>
		<div class="col-md-12">
			<h3 class="popup-indicator">{{selectedIndicator.indicatorName}}</h3>
			<div class="col-sm-12 text-center" ng-repeat="data in ldata"
				id="lineChartLandingPage">
				<h3 style="color: red;" ng-if="!ldataAvailable"
					class="no-data-chart">No Data Available</h3>
				<samiksha-line dataprovider="data"></samiksha-line>
			</div>
		</div>
	</div>
	
		<!-- <div ng-if="tableData.length" class="download-excel-container text-right selction-submit col-md-9" style="margin-left: 18px;">
					<button type="submit" class="submit-selection"
						ng-click="exportTableData('dataTable')"><i class="fa fa-file-excel-o" aria-hidden="true"></i> Download Excel</button>
				</div> -->
	 			<div class=" table-responsive col-md-9" style="width: 80%; margin-bottom: 30px; margin-left:30px;"  id="cciTable">
 				<table items="tableData" show-filter="true" cellpadding="0"
 					cellspacing="0" border="0" class="dataTable table table-bordered"
 					id="dataTable" ng-class="{'dhSelected':selectedFacility.iC_NId==17}">
 					<thead>
 							<th ng-repeat="column in columns | filter:removeColumn"
 								ng-click="order(column)" style="position: relative;">{{column}}
 								<div class="sorting1">
 									<i class="fa fa-caret-up asc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != column || sortReverse == true)}"></i>
 									<i class="fa fa-caret-down dsc" aria-hidden="true"
 										ng-class="{'hiding': !(sortType != column || sortReverse == false)}"></i>
 								</div>
 							</th>
						
							
 					</thead>
					<tbody>
 						<tr 
 							ng-repeat="rowData in tableData | orderBy:filterType:sortReverse">
 							<td ng-repeat="column in columns | filter:removeColumn" 
								sortable="'{{rowData.column}}'" ng-click="column=='CCI Name'?cciLineChart(rowData):''" ng-class="{'clickable':column=='CCI Name'}"><span>{{rowData[column]}}</span></td>
 						</tr>
					</tbody>
 				</table>
			</div>	
	
	<div id="trendDiv" class="trend-viz animate-show" ng-animate=" 'animate' "
					ng-show="ccildata.length">
					<button class="close trendchartClosebtn" aria-hidden="true" type="button"
						ng-click="closeViz()" title="Close">
						<span class="glyphicon glyphicon-remove-circle"></span>
					</button>

					<div class="container-fluid">
						<div class="row show-grid">
							<div class="col-xs-10 col-md-4 col-sm-3 left" >
								<h5 style="background:none; color:black;" >{{selectedCCI}}</h5>
							</div>
							
						</div>
						<div class="line-separator"></div>
					</div>
					<div class="row">
						<div class="col-md-10 trend_colChart">

								<div class="col-sm-6 text-center" ng-repeat="data in ccildata"
																	id="cicllineChartLandingPage">
									<h3 style="color: red;" ng-if="!ccildata.length"
								class="no-data-chart">No Data Available</h3>
												<samiksha-line id="ciclLineChart" dataprovider="data"></samiksha-line>
									</div>
                                                               <!-- <samiksha-line id="ciclLineChart"  dataprovider="data"></samiksha-line> -->
                                                </div>
                                             </div>
                                         </div>
                                      </div>

	
	<div id="largeMapContainer" class='popup'>
		<div class="popup-close js-popup-close modal-close">X</div>
		<div class="col-md-12">
			<h3 class="popup-indicator">{{selectedIndicator.indicatorName}}</h3>
			<samiksha-map ng-style="style()"
				style="display:block;margin-top: 15px;"></samiksha-map>
			<ul class="legends-section">
				<li class="legend" ng-repeat="legend in legends"><div
						ng-class="legend.value"
						style="width: 15px; height: 15px; display: inline-block; margin-bottom: -4px;"></div>
					&nbsp;<b style="color: #555;">{{legend.desc}} ({{legend.value == 'firstslices' ? numberOfFirstslices : legend.value == 'secondslices' ?  numberOfSecondslices  : 
								legend.value == 'thirdslices' ? numberOfThirdslices : legend.value == 'fourthslices' ?  numberOfFourthslices : numberOfFifthslices }})</b></li>
			</ul>
			<div class="north-arrow-section">
				<img alt="North Arrow" src="resources/img/north_arrow_new.png">
				<div style="margin-top: 28px;
    color: #F00;">Map not to scale</div>
			</div>
		</div>
	</div>
	
	
	
	<jsp:include page="./common/cctsFooter.jsp" />

	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	
		<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
	<script type="text/javascript" src="resources/js/angular.min.js"></script>
	<script src="resources/js/d3.min.js"></script>
	<script src="resources/js/topojson.v1.min.js"></script>
	<script type="text/javascript" src="resources/js/AngularService/sdrc.export.js"></script>

	<script>
		var app = angular.module('dashboardApp', []);
		var myAppConstructor = angular.module('dashboardApp');
	</script>
	<script src="resources/js/D3/dashboardDirective.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>

	<script src="resources/js/AngularController/dashboardCtrl.js"></script>
<!-- <script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->



	<script>
		$(document).ready(
				function() {
					if($(window).width() > 768)
					$(".left-section-menu, .content-section").height(
							$(window).height()-65);
					else{
						$(".left-menu-button-container button").click(function(e){
							e.stopPropagation();
							$(".left-section-menu").animate({left: 0})
						})
						$("body").not(".left-menu-button-container").click(function(){$(".left-section-menu").animate({left: "-60%"});});
					}
				});
		
		$(".dashboard").addClass("active");
		$(".samiksha-map-popup").click(function() {
			$("#cciTable").css({
				"display":"none"
			});
			$("#largeMapContainer").css({
				"height" : "140%",
				"padding" : "1% 5%"
			});
		})
		$(".samiksha-line-popup").click(function() {
			$("#cciTable").css({
				"display":"none"
			});
			$("#largeLineChartContainer").css({
				"height" : "140%",
				"padding" : "1% 5%"
			});
		})
		$(".modal-close").click(function() {
			$("#cciTable").css({
				"display":"block"
			});
			$("#largeLineChartContainer").css({
				"height" : "0%",
				"padding" : "0"
			});
			
			$("#ccilargeLineChartContainer").css({
				"height" : "0%",
				"padding" : "0"
			}); 
			$("#largeMapContainer").css({
				"height" : "0%",
				"padding" : "0"
			});
		})
	</script>
	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
			sdrc_export.export_pdf("", "pdfDownloadBtn");
		});
	</script>
</body>
</html>