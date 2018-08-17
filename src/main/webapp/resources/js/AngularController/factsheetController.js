		myAppConstructor.run(function ($rootScope) {
			$rootScope.rootpath="/CPIS/";
		    return $rootScope.rootpath;
		});
         myAppConstructor.controller('FactsheetController', function($scope, $http,$location,$rootScope,commonService) {
        	
        	 var url = $location.absUrl().split('/')[4];//current page
        		console.log($scope.rootlang);//root language
        	 $scope.selectedAreaLevel="";
        	 $scope.selectedDesignation=null;
        	 $scope.selectedUser=null;
        	 $scope.selectedArea=null;
        	 $scope.password="";
        	 commonService.getGridMenuItems().then(function(result) {
					$scope.menuList = result;
				}, function() {

				});
        	 $scope.allOption = {
        			 areaName: "All",
        			 areaId: "All"
        	 };
        	 
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
        	 
        	 $scope.userLevel = userLevel;
				$scope.selectDivision = function(division) {
					$scope.districtDisable=false;
					$scope.selectedStartTimeperiod=null;
					$scope.selectedStartYear=null;
					$scope.selectedDivision = division;
					$scope.allEndTimeperiods = [];
					$scope.allStartTimeperiods = [];
					$scope.selectedEndYear = undefined;
					$scope.selectedEndTimeperiod = undefined;
					$scope.selectedDistrict = undefined;
					if($scope.selectedDivision.areaName == "All"){
						$scope.selectedDistrict = $scope.allOption;
						$scope.districtDisable=true;
//						$scope.selectedDivision.children = [];
					}
					if($scope.selectedDivision.children.length){
//						$scope.selectedDivision.children.unshift($scope.allOption);
					}
					
				};
				$scope.selectDistrict = function(district) {
					$scope.selectedStartTimeperiod=null;
					$scope.selectedStartYear=null;
					$scope.selectedDistrict = district;
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
				$scope.callSubmit = function(){
					if($scope.selectedIndicator.indicatorName == 'Child above age 18 years living in CCIs'){
						$scope.callAbove18IndicatorFlag = true;
						$scope.getTableDataForChildAbove18();
					}
					else{
						$scope.callAbove18IndicatorFlag = false;
						$scope.getTableData();
					}
				}
				
				
				$scope.getAllAreas = function() {
					$http.get("getAllAreaForLoginUser").then(function(response) {
						$scope.allAreas = response.data;
						if(userLevel == 2 || userLevel == 1){
							$scope.allDivisions = $scope.convert($scope.allAreas, 1).children;
							$scope.allDivisions.unshift($scope.allOption);
						}
						if(userLevel == 3){
							$scope.selectedDivision={};
							$scope.selectedDivision.children = $scope.allAreas;
							$scope.selectedDivision.children.unshift($scope.allOption);
						}
						else{
							
						}
					}, function() {

					});
				};
				$scope.getAllTimeperiods = function() {
					$http.get("getAllTimeperiods").then(function(response) {
						$scope.Timeperiods = response.data;
						$scope.years=Object.keys($scope.Timeperiods);
							$scope.allStartTimeperiods=[];
							$scope.allEndTimeperiods=[];
					}, function() {

					});
				};
				$scope.getAllTimeperiods();
				$scope.getAllAreas();
				$scope.downloadFactSheet = function() {
					if (!$scope.selectedDivision && userLevel < 3) {
						$scope.errorMsg = "division name";
						$("#errorMessage").modal("show");
					}
					else if(!$scope.selectedDistrict && userLevel <= 3 && $scope.selectedDivision.areaName != "All"){
						$scope.errorMsg = "district name";
						$("#errorMessage").modal("show");
					}
					else if ($scope.selectedStartYear == undefined || $scope.selectedStartYear == null) {
							$scope.errorMsg = "start year";
						$("#errorMessage").modal("show");
					} else if ($scope.selectedStartTimeperiod == undefined || $scope.selectedStartTimeperiod == null) {
							$scope.errorMsg = "start Month";
						$("#errorMessage").modal("show");
					} else if (($scope.selectedEndYear == undefined || $scope.selectedEndYear == null)) {
						$scope.errorMsg = "end year";
						$("#errorMessage").modal("show");
					} else if (($scope.selectedEndTimeperiod == undefined || $scope.selectedEndTimeperiod == null)) {
						$scope.errorMsg = "end month";
						$("#errorMessage").modal("show");
					}
					else{
						if(!$scope.selectedDivision){
							$scope.selectedDivision = {};
							$scope.selectedDivision.areaId = null;
						}
						if(!$scope.selectedDistrict){
							$scope.selectedDistrict = {};
							$scope.selectedDistrict.areaId = null;
						}
						$(".loader").css("display", "block");

						$http.get("isFactsheetAvailable?startTimeperiod="+ $scope.selectedStartTimeperiod.timePeriodId +
								"&endTimeperiod="+ $scope.selectedEndTimeperiod.timePeriodId +
								"&divisionId="+ $scope.selectedDivision.areaId +
								"&districtId="+  $scope.selectedDistrict.areaId)
							.then(function(response) {
								$(".loader").css("display", "none");
								if(response.data==true){
									window.location.href = "getFactsheet?startTimeperiod="+ $scope.selectedStartTimeperiod.timePeriodId +
									"&endTimeperiod="+ $scope.selectedEndTimeperiod.timePeriodId +
									"&divisionId="+ $scope.selectedDivision.areaId +
									"&districtId="+  $scope.selectedDistrict.areaId;
									if(userLevel == 2)
										$scope.selectDivision(undefined);
									if(userLevel == 3)
										$scope.selectDistrict(undefined);
									if(userLevel == 4){
										$scope.selectedEndYear = null;
										$scope.selectedEndTimeperiod = null;
										$scope.selectedStartTimeperiod=null;
										$scope.selectedStartYear=null;
									}

								}else{
									$scope.nodataMsg = "Data not available";
									$("#dataNotFoundMessage").modal("show");
									if(userLevel == 2)
										$scope.selectDivision(undefined);
									if(userLevel == 3)
										$scope.selectDistrict(undefined);
									if(userLevel == 4){
										$scope.selectedEndYear = null;
										$scope.selectedEndTimeperiod = null;
										$scope.selectedStartTimeperiod=null;
										$scope.selectedStartYear=null;
									}
								}
						}, function() {

						});
						/*window.location.href = "getFactsheet?startTimeperiod="+ $scope.selectedStartTimeperiod.timePeriodId +
						"&endTimeperiod="+ $scope.selectedEndTimeperiod.timePeriodId +
						"&divisionId="+ $scope.selectedDivision.areaId +
						"&districtId="+  $scope.selectedDistrict.areaId;*/
						
					}
				}
				
				
				
				
				
				$scope.convert = function(array){
				    var map = {};
				    for(var i = 0; i < array.length; i++){
				        var obj = array[i];
				        if(obj.parentAreaId == 1)
				        	obj.parentAreaId =  null;
				        if(!(obj.areaId in map)){
				            map[obj.areaId] = obj;
				            map[obj.areaId].children = [];
				        }

				        if(typeof map[obj.areaId].areaName == 'undefined'){
				            map[obj.areaId].areaId = String(obj.areaId);
				            map[obj.areaId].areaName = obj.areaName;
				            map[obj.areaId].parentAreaId= String(obj.parentAreaId);
				        }

				        var parent = obj.parentAreaId || '-';
				        if(!(parent in map)){
				            map[parent] = {};
				            map[parent].children = [];
				        }
				        map[parent].children.push(map[obj.areaId]);
				    }
				    return map['-'];
				};
        	 
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