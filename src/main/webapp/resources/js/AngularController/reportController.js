myAppConstructor
		.controller(
				"ReportController",
				function($rootScope, $scope, $http, $window,$filter, commonService) {
					
					// variable declaration
					$scope.selectedIndicator = {};
					$scope.selectedTimeperiod = {};
					$scope.callAbove18IndicatorFlag = false;
					$scope.tableData = [];
					$scope.ifSectorSelected = false;
					$scope.cncpIndicators=[];
	        		$scope.ciclIndicators=[];
					 $scope.designation = designation;
					 if($scope.designation>6){
						 $scope.ifSectorSelected=true;
					 }
					
					 
					// all sectors
					$scope.allSectors = [
					                     {sectorName: 'CNCP'},
					                     {sectorName: 'CICL'}
					                    ];
					
					commonService.getGridMenuItems().then(function(result) {
						$scope.menuList = result;
					}, function() {

					});
					$http.post("getNotificationCount").then(
							function(response) {
								$scope.notificationCount=response.data;
							},
							function(error){
								console.log(error);
							});
					
					
					$scope.redirectForm=function(url){
		        		if(url=="child_registration" || url=="ciclSocialBackgroundReport" || url=="reportSummary" || url=="constitutionofSociety"){
		        			commonService.redirectForm(url, undefined);
		        		}else{
		        			$('#noChildSelected').modal('show');
		        		}
		        	};
		        	
		        	/*$scope.filterIndicators = function(sectorname)
		        	{*/
		        		
		        		/*if(sectorname == "CNCP")             
		        			{
		        			 $scope.filteredIndicators = $filter('filter')($scope.allIndicators, {sectorId:1,sectorId:4},true);
		        			}
		        		else if(sectorname == "CICL")
		        			{
		        			$scope.filteredIndicators = $filter('filter')($scope.allIndicators, {sectorId:2,sectorId:5},true);
		        			}
		        		else
		        			$scope.filteredIndicators = null;*/
		        			
//		        	}
		        	// for sectors selection
		        	$scope.sectorsName = null;
		        	$scope.selectSector = function(sector) {
		                $scope.sectorsName = sector.sectorName;
		                $scope.selectedsectorName = sector.sectorName;
		                if($scope.selectedsectorName == "")
		                	$scope.ifSectorSelected = false;
		                else
		                	$scope.ifSectorSelected = true;
		                
//		                $scope.filterIndicators($scope.selectedsectorName);
					};
		        	
		        	
					$scope.selectIndicator = function(indicator) {
						$scope.selectedStartTimeperiod=null;
						$scope.selectedStartYear=null;
						$scope.selectedIndicator = indicator;
						$scope.allEndTimeperiods = [];
						$scope.allStartTimeperiods = [];
						$scope.selectedEndYear = undefined;
						$scope.selectedEndTimeperiod = undefined;
					};
					
					$scope.selectStartYear = function(year) {
						$scope.selectedStartYear = year;
						$scope.selectedEndYear = undefined;
						$scope.selectedEndTimeperiod = undefined;
						$scope.allStartTimeperiods=$scope.Timeperiods[year];
						$scope.allEndTimeperiods = [];
						$scope.selectedStartTimeperiod=undefined;
					};
					$scope.selectStartTimeperiod = function(timeperiod) {
						$scope.selectedStartTimeperiod = timeperiod;
						$scope.selectedEndYear = undefined;
						$scope.selectedEndTimeperiod = undefined;
					};
					$scope.selectEndYear = function(year) {
						$scope.selectedEndYear = year;
						if(parseInt($scope.selectedStartYear) == $scope.selectedEndYear){
							$scope.allEndTimeperiods = [];
							for(var i=0; i<$scope.allStartTimeperiods.length;i++){
								if(parseInt($scope.selectedStartTimeperiod.startDate.split("-")[1]) <= parseInt($scope.allStartTimeperiods[i].startDate.split("-")[1]))
									$scope.allEndTimeperiods.push($scope.allStartTimeperiods[i]);
							}
							
						}
						else
							$scope.allEndTimeperiods=$scope.Timeperiods[year];
						$scope.selectedEndTimeperiod=undefined;
					};
					
					$scope.selectEndTimeperiod = function(timeperiod) {
						$scope.selectedEndTimeperiod = timeperiod;
					};
					$scope.order = function(sortType) {
						$scope.sortReverse = ($scope.sortType === sortType) ? !$scope.sortReverse
								: false;
						$scope.sortType = sortType;
					};
					$scope.filterType = function(val) {
						if (isNaN(parseFloat(val[$scope.sortType])))
							return val[$scope.sortType];
						else
							return parseFloat(val[$scope.sortType]);
					};
					$scope.removeColumn = function(item){
						return item.split("-")[item.split("-").length-1] != 'childs';
						};
					$scope.callSubmit = function(){
						if($scope.selectedIndicator.sectorId == 4 || $scope.selectedIndicator.sectorId == 5){
							$scope.callAbove18IndicatorFlag = true;
							$scope.getTableDataForChildAbove18();
						}
						else{
							$scope.callAbove18IndicatorFlag = false;
							$scope.getTableData();
						}
					}
					
					$scope.getTableData = function() {
						$scope.tableData = [];
						if($scope.selectedsectorName==undefined && $scope.designation<6){
							$("#sectorMessage").modal("show");
						}
						else if (!Object.keys($scope.selectedIndicator).length) {
							$scope.errorMsg = "indicator name";
							$("#errorMessage").modal("show");
						} else if ($scope.selectedStartYear == undefined || $scope.selectedStartYear == null) {
							if($scope.selectedIndicator.indicatorUnitSubgroupId != 232 && $scope.selectedIndicator.indicatorUnitSubgroupId != 198)
								$scope.errorMsg = "start year";
							else
								$scope.errorMsg = "Till year";
							$("#errorMessage").modal("show");
						} else if ($scope.selectedStartTimeperiod == undefined || $scope.selectedStartTimeperiod == null) {
							if($scope.selectedIndicator.indicatorUnitSubgroupId != 232 && $scope.selectedIndicator.indicatorUnitSubgroupId != 198)
								$scope.errorMsg = "start Month";
							else
								$scope.errorMsg = "Till Month";
							$("#errorMessage").modal("show");
						} else if (($scope.selectedEndYear == undefined || $scope.selectedEndYear == null) && $scope.selectedIndicator.indicatorUnitSubgroupId != 232 && $scope.selectedIndicator.indicatorUnitSubgroupId != 198) {
							$scope.errorMsg = "end year";
							$("#errorMessage").modal("show");
						} else if (($scope.selectedEndTimeperiod == undefined || $scope.selectedEndTimeperiod == null) && $scope.selectedIndicator.indicatorUnitSubgroupId != 232 && $scope.selectedIndicator.indicatorUnitSubgroupId != 198) {
							$scope.errorMsg = "end month";
							$("#errorMessage").modal("show");
						}
						else {
							$(".loader").css("display", "block");
							$scope.tableData=[];
							if($scope.selectedIndicator.indicatorUnitSubgroupId != 232 && $scope.selectedIndicator.indicatorUnitSubgroupId != 198){
								$http
										.get(
												"getReportsForIndicator?indicatorName="
														+ $scope.selectedIndicator.indicatorName
														+ "&iusNid="
														+ $scope.selectedIndicator.indicatorUnitSubgroupId
														+ "&startTimeperiod="
														+ $scope.selectedStartTimeperiod.timePeriodId
														+ "&endTimeperiod="
														+ $scope.selectedEndTimeperiod.timePeriodId)
										.then(
												function(response) {
													checkSessionOut(response.data);
													$scope.lockedIndicator = $scope.selectedIndicator;
													$scope.lockedStartTimeperiod = $scope.selectedStartTimeperiod;
													$scope.lockedEndTimeperiod = $scope.selectedEndTimeperiod;
													$(".loader").css("display",
															"none");
													$scope.tableData = response.data['table'];
													$scope.headerData=response.data['header'];
													$scope.columnHeader=[];
													$scope.topHeader=[];
													$scope.bottomHeader=[];
													for(i=0;i<$scope.headerData.length;i++)
														{
														 $scope.topHeader.push($scope.headerData[i]);
														 if ($scope.headerData[i].childs==null)
															 {
															 $scope.columnHeader.push($scope.headerData[i]);
															 }
														 else
														
															 {
															 	for(j=0;j<$scope.headerData[i].childs.length;j++)
															 	{
															 		 $scope.bottomHeader.push($scope.headerData[i].childs[j]);
															 		 $scope.columnHeader.push($scope.headerData[i].childs[j]);
															 	}	
															 
															 }
														}
													$scope.tableLength = Object.keys($scope.tableData[0]).length;
													$("body").animate({scrollTop: $("#main-content").offset().top - 15}, 500)
													if($scope.tableData[0].District ==undefined){
														$("#noDataAvailable").modal("show");
													}
													$scope.columns = Object
															.keys($scope.tableData[0]);
													if ($scope.columns
															.indexOf("District") != -1) {
														$scope.columns
																.splice(
																		$scope.columns
																				.indexOf("District"),
																		1);
														$scope.columns
																.unshift("District");
	
													}
												}, function() {
	
												});
							}
							else{
								$scope.tableData=[];
								$http
								.get(
										"getReportsForIndicator?indicatorName="
												+ $scope.selectedIndicator.indicatorName
												+ "&iusNid="
												+ $scope.selectedIndicator.indicatorUnitSubgroupId
												+ "&startTimeperiod="
												+ 0
												+ "&endTimeperiod="
												+ $scope.selectedStartTimeperiod.timePeriodId)
								.then(
										function(response) {
											checkSessionOut(response.data);
											$scope.lockedIndicator = $scope.selectedIndicator;
											$scope.lockedStartTimeperiod = $scope.selectedStartTimeperiod;
											$scope.lockedEndTimeperiod = $scope.selectedEndTimeperiod;
											$(".loader").css("display",
													"none");
											$scope.tableData = response.data['table'];
											$scope.headerData=response.data['header'];
											
											$scope.columnHeader=[];
											$scope.topHeader=[];
											$scope.bottomHeader=[];
											for(i=0;i<$scope.headerData.length;i++)
												{
												 $scope.topHeader.push($scope.headerData[i]);
												 if ($scope.headerData[i].childs==null)
													 {
													 $scope.columnHeader.push($scope.headerData[i]);
													 }
												 else
												
													 {
													 	for(j=0;j<$scope.headerData[i].childs.length;j++)
													 	{
													 		 $scope.bottomHeader.push($scope.headerData[i].childs[j]);
													 		 $scope.columnHeader.push($scope.headerData[i].childs[j]);
													 	}	
													 
													 }
												}
											
											$scope.tableLength = Object.keys($scope.tableData[0]).length;
											$("body").animate({scrollTop: $("#main-content").offset().top - 15}, 500)
											if($scope.tableData[0].District ==undefined){
												$("#noDataAvailable").modal("show");
											}
											$scope.columns = Object
													.keys($scope.tableData[0]);
											if ($scope.columns
													.indexOf("District") != -1) {
												$scope.columns
														.splice(
																$scope.columns
																		.indexOf("District"),
																1);
												$scope.columns
														.unshift("District");

											}
										}, function() {

										});
							}
						}
					};
					$scope.getTableDataForChildAbove18 = function() {
						$scope.tableData = [];
						if (!Object.keys($scope.selectedIndicator).length) {
							$scope.errorMsg = "indicator name";
							$("#errorMessage").modal("show");
						} else if ($scope.selectedStartYear == undefined || $scope.selectedStartYear == null) {
							$scope.errorMsg = "start year";
							$("#errorMessage").modal("show");
						} else if ($scope.selectedStartTimeperiod == undefined || $scope.selectedStartTimeperiod == null) {
							$scope.errorMsg = "start month";
							$("#errorMessage").modal("show");
						} else if ($scope.selectedEndYear == undefined || $scope.selectedEndYear == null) {
							$scope.errorMsg = "end year";
							$("#errorMessage").modal("show");
						} else if ($scope.selectedEndTimeperiod == undefined || $scope.selectedEndTimeperiod == null) {
							$scope.errorMsg = "end month";
							$("#errorMessage").modal("show");
						}else {
							$scope.tableData=[];
							$(".loader").css("display", "block");
							$http
									.get(
											"getReportForIndicatorForChildAbove18?indicatorName="
													+ $scope.selectedIndicator.indicatorName
													+ "&iusNid="
													+ $scope.selectedIndicator.indicatorUnitSubgroupId
													+ "&startTimeperiod="
													+ $scope.selectedStartTimeperiod.timePeriodId
													+ "&endTimeperiod="
													+ $scope.selectedEndTimeperiod.timePeriodId)
									.then(
											function(response) {
												checkSessionOut(response.data);
												$scope.lockedIndicator = $scope.selectedIndicator;
												$scope.lockedStartTimeperiod = $scope.selectedStartTimeperiod;
												$scope.lockedEndTimeperiod = $scope.selectedEndTimeperiod;
												$(".loader").css("display",
														"none");
												$scope.tableData = response.data['table'];
												$scope.headerData=response.data['header'];
												
												$scope.columnHeader=[];
												$scope.topHeader=[];
												$scope.bottomHeader=[];
												for(i=0;i<$scope.headerData.length;i++)
													{
													 $scope.topHeader.push($scope.headerData[i]);
													 if ($scope.headerData[i].childs==null)
														 {
														 $scope.columnHeader.push($scope.headerData[i]);
														 }
													 else
													
														 {
														 	for(j=0;j<$scope.headerData[i].childs.length;j++)
														 	{
														 		 $scope.bottomHeader.push($scope.headerData[i].childs[j]);
														 		 $scope.columnHeader.push($scope.headerData[i].childs[j]);
														 	}	
														 
														 }
													}
												$scope.tableLength = Object.keys($scope.tableData[0]).length;
												$("body").animate({scrollTop: $("#main-content").offset().top - 15},500)
												if($scope.tableData[0].District ==undefined){
													$("#noDataAvailable").modal("show");
												}
												$scope.columns = Object
														.keys($scope.tableData[0]);
												if ($scope.columns
														.indexOf("District") != -1) {
													$scope.columns
															.splice(
																	$scope.columns
																			.indexOf("District"),
																	1);
													$scope.columns
															.unshift("District");

												}
												
											}, function() {

											});
						}
					};
					$scope.getAllIndicators = function() {
						$http.get("getAllIndicators").then(function(response) {
							$scope.allIndicators = response.data;
							
			        		angular.forEach($scope.allIndicators, function(value, key){
		        				if(value.sectorId==1 || value.sectorId==4){
		        					$scope.cncpIndicators.push(value);
		        				}
		        				if(value.sectorId==2 || value.sectorId==5){
		        					$scope.ciclIndicators.push(value);
		        				}
		        			});
						}, function() {

						});
					};
					
					$scope.style=function()
					{
						return "text-align:center";
					}
					
					
					$scope.getAllTimeperiods = function() {
						$http.get("getAllTimeperiods").then(function(response) {
							$scope.Timeperiods = response.data;
							$scope.years=Object.keys($scope.Timeperiods);
								$scope.allStartTimeperiods=[];
								$scope.allEndTimeperiods=[];
						}, function() {

						});
					};
					$scope.getChildDetailTable = function(rowData, indicator){
						$http.get("getChildDetailsForIUS?childs=" + rowData[indicator + "-childs"]+"&indicator="+$scope.lockedIndicator.indicatorName+"&startDate="+$scope.selectedStartTimeperiod.timePeriodId+"&endDate="+$scope.selectedEndTimeperiod.timePeriodId).then(function(response) {
							$scope.lockedSubIndicator = indicator;
							$scope.childDetailTableData = response.data;
							$scope.childDetailTableColumns = Object.keys($scope.childDetailTableData[0]);
							$("#childDetailTable").modal("show");
						}, function() {

						});
					}
					$scope.getAllTimeperiods();
					$scope.getAllIndicators();
					$scope.exportTableData = function(id, indicatorName) {
						var htmls = "";
						var uri = 'data:application/vnd.ms-excel;base64,';
						var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>';
						var base64 = function(s) {
							return window.btoa(unescape(encodeURIComponent(s)));
						};

						var format = function(s, c) {
							return s.replace(/{(\w+)}/g, function(m, p) {
								return c[p];
							});
						};
						if($scope.lockedEndTimeperiod){
							var tab_text = '<table class="text-center" style="margin-top: 14px;"><tr><h2 style="color: #2565a7">'
								+ indicatorName
								+ '&nbsp; in &nbsp; '
								+ $scope.lockedStartTimeperiod.timeperiod + ' - ' + $scope.lockedEndTimeperiod.timeperiod 
								+ '</h2></tr></table>'
						}
						else{
						var tab_text = '<table class="text-center" style="margin-top: 14px;"><tr><h2 style="color: #2565a7">'
								+ indicatorName
								+ '&nbsp; in &nbsp; '
								+ $scope.lockedStartTimeperiod.timeperiod 
								+ '</h2></tr></table>'
						}
						tab_text += "<table border='2px'><tr bgcolor='#a5bad9 '>";
						var textRange;
						var j = 0;
						tab = document.getElementById(id); // id of table

						for (j = 0; j < tab.rows.length; j++) {
							tab_text = tab_text + tab.rows[j].innerHTML
									+ "</tr>";
							if(j < tab.rows.length-1)
							 tab_text=tab_text+"<tr>";
						}

						tab_text = tab_text + "</table>";
						tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");// remove
																			// if u
																			// want
																			// links
																			// in
																			// your
																			// table
						tab_text = tab_text.replace(/<img[^>]*>/gi, ""); // remove
																			// if u
																			// want
																			// images
																			// in
																			// your
																			// table
						tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi,
								""); // reomves input params

						var ctx = {
							worksheet : 'Worksheet',
							table : tab_text
						};

						var link = document.createElement("a");
						if($scope.lockedEndTimeperiod){
						link.download = indicatorName
								+ "_" + $scope.lockedStartTimeperiod.timeperiod + " to " + $scope.lockedEndTimeperiod.timeperiod
								+ ".xls";
						}
						else{
							link.download = indicatorName
							+ "_" + $scope.lockedStartTimeperiod.timeperiod
							+ ".xls";
						}
						link.href = uri + base64(format(template, ctx));
						link.click();
					};
					/*
					 * $scope.writeExcel = function(){
					 * ,.sdjf;
					 * var workbook = new
					 * $.ig.excel.Workbook($.ig.excel.WorkbookFormat.excel2007);
					 * var sheet = workbook.worksheets().add('Sheet1');
					 * sheet.columns(0).setWidth(72,
					 * $.ig.excel.WorksheetColumnWidthUnit.pixel);
					 * sheet.columns(1).setWidth(170,
					 * $.ig.excel.WorksheetColumnWidthUnit.pixel);
					 * sheet.columns(2).setWidth(110,
					 * $.ig.excel.WorksheetColumnWidthUnit.pixel);
					 * sheet.columns(3).setWidth(275,
					 * $.ig.excel.WorksheetColumnWidthUnit.pixel);
					 *  // Create a to-do list table with columns for tasks and
					 * their priorities. sheet.getCell('A1').value('ID');
					 * sheet.getCell('B1').value('Applicant');
					 * sheet.getCell('C1').value('Status');
					 * sheet.getCell('D1').value('Comment'); var table =
					 * sheet.tables().add('A1:D8', true);
					 *  // Specify the style to use in the table (this can also
					 * be specified as an optional 3rd argument to the 'add'
					 * call above).
					 * table.style(workbook.standardTableStyles('TableStyleMedium2'))
					 *  // Populate the table with data
					 * sheet.getCell('A2').value(3223);
					 * sheet.getCell('B2').value('Jack Banner');
					 * sheet.getCell('C2').value('Approved');
					 * sheet.getCell('D2').value('');
					 * 
					 * sheet.getCell('A3').value(3224);
					 * sheet.getCell('B3').value('Armin Barrywater');
					 * sheet.getCell('C3').value('In Review');
					 * sheet.getCell('D3').value('Underwriter is out until next
					 * week.');
					 * 
					 * sheet.getCell('A4').value(3225);
					 * sheet.getCell('B4').value('Shiela Donahue');
					 * sheet.getCell('C4').value('In Review');
					 * sheet.getCell('D4').value('');
					 * 
					 * sheet.getCell('A5').value(3226);
					 * sheet.getCell('B5').value('Perry Kane');
					 * sheet.getCell('C5').value('On Hold');
					 * sheet.getCell('D5').value('Waiting on paperwork from
					 * customer.');
					 * 
					 * sheet.getCell('A6').value(3235);
					 * sheet.getCell('B6').value('Xavier Fannello');
					 * sheet.getCell('C6').value('New');
					 * sheet.getCell('D6').value('');
					 * 
					 * sheet.getCell('A7').value(3244);
					 * sheet.getCell('B7').value('Georgi Angelchov');
					 * sheet.getCell('C7').value('New');
					 * sheet.getCell('D7').value('');
					 * 
					 * sheet.getCell('A8').value(3257);
					 * sheet.getCell('B8').value('Imelda Sanchez');
					 * sheet.getCell('C8').value('New');
					 * sheet.getCell('D8').value('');
					 *  // Sort the table by the Applicant column //
					 * table.columns('Applicant').sortCondition(new
					 * $.ig.excel.OrderedSortCondition());
					 *  // Filter out the Approved applicants //
					 * table.columns('Status').applyCustomFilter(new
					 * $.ig.excel.CustomFilterCondition($.ig.excel.ExcelComparisonOperator.notEqual,
					 * 'Approved'));
					 *  // Save the workbook $scope.saveWorkbook(workbook,
					 * "Table.xlsx"); }
					 * 
					 * $scope.saveWorkbook = function(workbook, name) {
					 * workbook.save({ type: 'blob' }, function (data) {
					 * saveAs(data, name); }, function (error) { alert('Error
					 * exporting: : ' + error); }); };
					 */
				});
myAppConstructor.filter('filterEndYear', function() {
    return function(input, scope) {
    	if(input && scope.selectedStartTimeperiod){
	    	var newArray = [];
	    	for(var i=0; i<input.length; i++){
	    		if(parseInt(input[i]) >= scope.selectedStartYear){
	    			newArray.push(input[i]);
	    		}
	    	}
	    	return newArray;
    	}
    	 else
    	    return [];
    };
});
$("button").click(function() {
	setTimeout(function() {
		$(".open .dropdown-menu").scrollTop(0);
	}, 100);

});
