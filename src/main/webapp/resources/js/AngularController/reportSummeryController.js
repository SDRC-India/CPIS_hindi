myAppConstructor.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
   
}]);
myAppConstructor.controller("reportSummeryController", function($scope,$http,commonService,$rootScope,gettextCatalog){
	$http({
        method : "GET",
        url : "getLang"
    }).then(function mySucces(response) {
    	$scope.lang=response.data;
    	$scope.changeLanguage($scope.lang);
});
	
	$scope.changeLanguage = function(lang){
		console.log(lang)
		$scope.lang=lang;
		   gettextCatalog.setCurrentLanguage(lang);
		$http({
	        method : "GET",
	        url : "setLangString?language="+$scope.lang
	    }).then(function mySucces(response) {
	    	
	});
	}
	
	$scope.showHR=true;
	$scope.showInfra=true;
	$scope.showFinance=true;
	$scope.designationId=document.getElementById('designationId').value;
	$scope.cciId=document.getElementById('cciId').value;
	
	$rootScope.$broadcast("designationId", $scope.designationId);
	$rootScope.$broadcast("cciId", $scope.cciId);
	
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	
	if($scope.designationId==1 || $scope.designationId==2 || $scope.designationId==3){
		$scope.showHR=false;
		$scope.showInfra=false;
		$scope.showFinance=false;
		$scope.showInspection=true;
		$("#one").hide();
		$("#two").hide();
		$("#three").hide();
		$("#four").show();
	}
	else if($scope.designationId==4){
		$scope.showHR=false;
		$scope.showInfra=false;
		$scope.showFinance=true;
		$scope.showInspection=true;
		$scope.showFinanceForm=false;
		$("#one").hide();
		$("#two").hide();
		$("#three").show();
		$("#four").hide();
	}
	else if($scope.designationId==10){
		$scope.showHR=true;
		$scope.showInfra=true;
		$scope.showFinance=false;
		$scope.showInspection=false;
		
		$("#one").show();
		$("#two").hide();
		$("#three").hide();
		$("#four").hide();
	}
	else{
		$scope.showHR=true;
		$scope.showInfra=true;
		$scope.showFinance=true;
		$scope.showInspection=true;
		$scope.showFinanceForm=true;
		
		$("#one").show();
		$("#two").hide();
		$("#three").hide();
		$("#four").hide();
	}
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.getTypeDetailsSuccessful 
				&& $scope.getYearDetailsSuccessful  && $scope.getFinancialInspectionReportDetailsSuccessful){
			$(".loader").css("display", "none");
		}
	};
	$scope.getGridMenuItemsSuccessful = false;
	$scope.getTypeDetailsSuccessful = false;
	$scope.getYearDetailsSuccessful = false;
	$scope.getFinancialInspectionReportDetailsSuccessful = false;
	 $(".loader").css("display", "block");
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
		if($scope.menuList.length==0){
			$scope.showMenu=false;
		}
	});
    $scope.redirectForm=function(url){
		if(url=="child_registration" || url=="ciclSocialBackgroundReport" || url=="reportSummary" || url=="constitutionofSociety"){
			commonService.redirectForm(url, $scope.selectedChildId);
		}else{
			if($scope.selectedChildId==null || $scope.selectedChildId == undefined 
				|| $scope.selectedChildId == ""){
				$('#noChildSelected').modal('show');
			}else{
				commonService.redirectForm(url, $scope.selectedChildId);
			}
		}
	};
	
	$http.get('getTypeDetails').
    then(function(result){
    	$scope.getTypeDetailsSuccessful = true;
    	$scope.checkAllDataSuccessful();
    	$scope.quarterlist = result.data.quarters;
    	$scope.sexList = result.data.childSex;
   }, function(error){
	   console.log(error);
   });
//	$scope.$broadcast('eventName', { message: $scope.quarterlist });
   
	$http.get('getYearDetails').
    then(function(result){
    	$scope.getYearDetailsSuccessful = true;
    	$scope.checkAllDataSuccessful();
    	$scope.yearlist = result.data;
   }, function(error){
	   console.log(error);
   });
	$scope.financialReportlist = [];
	$scope.inspectionReportlist = [];
	$http.get('getFinancialInspectionReportDetails').
    then(function(result){
    	
    	$scope.financialInspectionReportlist = result.data;
    	$scope.getFinancialInspectionReportDetailsSuccessful = true;
    	$scope.checkAllDataSuccessful();
    	for(var i = 0; i<$scope.financialInspectionReportlist.length;i++){
    		if($scope.financialInspectionReportlist[i].desc=="FR"){
    			$scope.financialReportlist.push($scope.financialInspectionReportlist[i]);
    		}else{
    			$scope.inspectionReportlist.push($scope.financialInspectionReportlist[i]);
    		}
    	};
   }, function(error){
	   console.log(error);
   });
});


myAppConstructor.controller("HumanResourceController", function($scope,$http,$timeout,$window,commonService,$rootScope){
	
	$scope.humanResource = 'CCI';

	$scope.cciList=[];
	$scope.cciLists=[];
	$scope.areaDetail2={};
	$scope.formInfo={};
	$scope.oslist=[];
	 $scope.saalist=[];
	
	$(".restrictE").keypress(function(event) {
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	

	
	$scope.eraseData = function(type){
		if(type=="cci"){
			 $scope.ccisaaFormInfo={};
			 $scope.cciosFormInfo={};
			if($scope.cciLists.length==0){
				$('#nocci').modal('show');
			}
		}
		else if(type=="saa"){
			$scope.ccihrFormInfo={};
			$scope.cciosFormInfo={};
			
			 angular.forEach($scope.cciList, function(value, key){
			      if(value.typeId == 4){
			    	  $scope.saalist.push(value);
			      }
			   });
			 if($scope.saalist.length==0){
				 $('#nocci').modal('show');
			 }
		}
		else {
			 $scope.ccihrFormInfo={};
			 $scope.ccisaaFormInfo={};
			
			 angular.forEach($scope.cciList, function(value, key){
			      if(value.typeId == 13){
			    	  $scope.oslist.push(value);
			      }
			   });
			 if($scope.oslist.length==0){
				 $('#nocci').modal('show');
			 }
		}
	}
	
	
	$http.get("getAreadetails").
    then(function(result){
    	$scope.district={};
	   	$scope.areaDetail=result.data;
	   	$scope.areaDetail2=result.data;
	   	$scope.district.name=$scope.areaDetail.value;
	   	$scope.district.id = $scope.areaDetail.key;
	   	$scope.ccihrFormInfo={};
	   	$scope.cciList=[];
		$scope.cciLists=[];
	   	
	   	$http.get("getCciList?areaId="+$scope.areaDetail.key).
		then(function(result){
			for (i = 0; i < result.data.length; i++) { 
				if(result.data[i].id==$scope.cciId){
					if(result.data[i].typeId==13){
					$scope.humanResource='OS';
					$scope.firstRadioDisable=true;
					$scope.secondRadioDisable=true;
					$scope.cciList.push(result.data[i]);
					}
					
					else if(result.data[i].typeId==4){
						$scope.humanResource='SAA';
						$scope.firstRadioDisable=true;
						$scope.thirdRadioDisable=true;
						$scope.cciList.push(result.data[i]);
						}
					
					else{
						$scope.humanResource='CCI';
						$scope.secondRadioDisable=true;
						$scope.thirdRadioDisable=true;
						$scope.cciLists.push(result.data[i]);
						}
				}
				
				else if(result.data[i].typeId==13 || result.data[i].typeId==4)
					$scope.cciList.push(result.data[i]);
				else
					$scope.cciLists.push(result.data[i]);
			}
			//$scope.cciList = result.data;
			console.log(result);
			$rootScope.$broadcast("cciList", result.data);
		},function(error){
			console.log(error);
		});
    });
	
	$scope.validateCCI=function(){
		$('#hrCCIconfirmationModal').modal('show');
	};
	$scope.validateSSA=function(){
		$('#humanresourceSAA').modal('show');
	};
	$scope.validateOS=function(){
		$('#humanresourceOS').modal('show');
	};
	$scope.printHRData = {};
	 $scope.saveCCIHR = function (){
		 $scope.ccihrFormInfo.district=$scope.areaDetail2;
		 $scope.printHRData = $scope.ccihrFormInfo;
		 //$scope.formInfo.nameOfCCI=$scope.formInfo.nameOfCCI.id;
		  $http.post('saveCCIHumanResource',$scope.ccihrFormInfo).
		  then(function(response){
			  console.log(response);
			  $scope.ccihrFormInfo={};
			  $('#alertModal1').modal('show');
		  },function(error){
		    console.log(error);
		  });
		 
	 };
	 
	//====================================download pdf HumanResourceDetailsHRCCI============================
     $scope.printCCIHumanResourceSubmitData = function(){
     	$(".loader").css("display", "block");
     	$scope.printHRData.district.name = $scope.printHRData.district.value;
     		var serverURL = 'downloadPDFDataForHumanResourceDetailsHRCCI?type='+$scope.lang;
     		commonService.downloadFile(serverURL, $scope.printHRData);
     		$scope.ccihrFormInfo = {};
 			$(".loader").css("display", "none");
     };
	 
	 $scope.saveCCISAA = function (){
		 $scope.ccisaaFormInfo.district=$scope.areaDetail2;
		 $scope.printHRSAAData = $scope.ccisaaFormInfo;
		  $http.post('saveCCIHumanResourceSAA',$scope.ccisaaFormInfo).
		  then(function(response){
			  console.log(response);
			  $scope.ccisaaFormInfo={};
			  $('#alertModal2').modal('show');
		  },function(error){
		    console.log(error);
		  });
	 };
	//====================================download pdf HumanResourceDetailsHRSAA============================
	 $scope.printHRSAAData = {};
     $scope.printCCISAAHumanResourceSubmitData = function(){
     	$(".loader").css("display", "block");
     	$scope.printHRSAAData.district.name = $scope.printHRSAAData.district.value;
     		var serverURL = 'downloadPDFDataForHumanResourceDetailsHRSAA?type='+$scope.lang;
     		commonService.downloadFile(serverURL, $scope.printHRSAAData);
     		$scope.ccihrFormInfo = {};
 			$(".loader").css("display", "none");
     };
	 $scope.saveCCIOS = function (){
		 $scope.cciosFormInfo.district=$scope.areaDetail2;
		 $scope.printHROSData = $scope.cciosFormInfo;
		  $http.post('saveCCIHumanResourceOS',$scope.cciosFormInfo).
		  then(function(response){
			  console.log(response);
			  $scope.cciosFormInfo={};
			  $('#alertModal3').modal('show');
		  },function(error){
		    console.log(error);
		  });
	 };
	 
	//====================================download pdf HumanResourceDetailsHRSAA============================
	 $scope.printHROSData = {};
     $scope.printCCIOSHumanResourceSubmitData = function(){
     	$(".loader").css("display", "block");
     	$scope.printHROSData.district.name = $scope.printHROSData.district.value;
     		var serverURL = 'downloadPDFDataForHumanResourceDetailsHROS?type='+$scope.lang;
     		commonService.downloadFile(serverURL, $scope.printHROSData);
     		$scope.cciosFormInfo = {};
 			$(".loader").css("display", "none");
     };
	 
//	 $scope.reDirect = function(){
//			$window.location.href = '/CPIS/ccts';
//	 };
	 $scope.reDirect = function(){
		 $window.location.reload();
	 };

});
myAppConstructor.controller("InfraStructureController", function($scope,$http,commonService){
	
	$scope.infraSt = 'infraCCI';
	$scope.areaDetail2={};
	$scope.buildingType=[];
	$scope.constructionOfBuilding =[];
	$scope.infraCCI={};
	$scope.infraSAA={};
	$scope.infraOS={};
	$scope.cciList=[];
	$scope.cciLists=[];
	$scope.saalist=[];
	$scope.oslist=[];
	
//	if($scope.cciLists.length==0){
//		$('#nocci1').modal('show');
//	}
	
	$scope.eraseData = function(type){
		if(type=="cci"){
			$scope.infraSAA={};
			$scope.infraOS={};
			$scope.buildingProtectedYes=true;
			$scope.buildingProtectedNo=false;
			$scope.infraCCI.building_Protected_by_Boundarywall=false;
			
			$scope.availabilityOfElectricityYes=true;
			$scope.availabilityOfElectricityNo=false;
			$scope.infraCCI.availability_Electricity_Allrooms=false;
			
			$scope.powerBackupYes=true;
			$scope.powerBackupNo=false;
			$scope.infraCCI.power_Backupfacility_AllRooms=false;
			
			$scope.availabilityOfDrinkingYes=true;
			$scope.availabilityOfDrinkingNo=false;
			$scope.infraCCI.availability_of_DrinkingWater=false;
			if($scope.cciLists.length==0){
				$('#nocci1').modal('show');
			}
		}
		else if(type=="saa"){
			$scope.infraCCI={};
			$scope.infraOS={};
			$scope.ssaBuildingProtectedYes=true;
			$scope.ssaBuildingProtectedNo=false;
			$scope.infraSAA.building_Protected_by_Boundarywall=false;
			
			$scope.ssaAVailabilityOfElectricityYes=true;
			$scope.ssaAVailabilityOfElectricityNo=false;
			$scope.infraSAA.availability_Electricity_Allrooms=false;
			
			$scope.ssaPowerBackupYes=true;
			$scope.ssaPowerBackupNo=false;
			$scope.infraSAA.power_Backupfacility_AllRooms=false;
			
			$scope.ssaAvailabilityOfDrinkingYes=true;
			$scope.ssaAvailabilityOfDrinkingNo=false;
			$scope.infraSAA.availability_of_DrinkingWater=false;
			 angular.forEach($scope.cciList, function(value, key){
			      if(value.typeId == 4){
			    	  $scope.saalist.push(value);
			      }
			   });
			 if($scope.saalist.length==0){
				 $('#nocci1').modal('show');
			 }
		}
		else {
			$scope.infraCCI={};
			$scope.infraSAA={};
			
			$scope.osBuildingProtectedYes=true;
			$scope.osBuildingProtectedNo=false;
			$scope.infraOS.building_Protected_by_Boundarywall=false;
			
			$scope.osSeparateToiletsYes=true;
			$scope.osSeparateToiletsNo=false;
			$scope.infraOS.separate_Toiletsfor_Staff=false;
			
			$scope.osAvailabilityOfElectricityYes=true;
			$scope.osAvailabilityOfElectricityNo=false;
			$scope.infraOS.availability_Electricity_Allrooms=false;
			
			$scope.osAvailabilityOfPowerYes=true;
			$scope.osAvailabilityOfPowerNo=false;
			$scope.infraOS.power_Backupfacility_AllRooms=false;
			
			$scope.osAvailabilityOfDrinkingYes=true;
			$scope.osAvailabilityOfDrinkingNo=false;
			$scope.infraOS.availability_of_DrinkingWater=false;
			
			 angular.forEach($scope.cciList, function(value, key){
			      if(value.typeId == 13){
			    	  $scope.oslist.push(value);
			      }
			   });
			 if($scope.oslist.length==0){
				 $('#nocci1').modal('show');
			 }
		}
	}
	
	$scope.checkRoom = function(){
		if(parseInt($scope.infraOS.numbers_RoomForBoys)+parseInt($scope.infraOS.numbers_RoomForGirls) > parseInt($scope.infraOS.total_Numbers_Room)){
			angular.element("#totalRooms").focus();
			document.getElementById('roomError').innerHTML="Sum of Rooms for boys and girls should not exceed total number of rooms.";
		}
		else{
			document.getElementById('roomError').innerHTML="";
		}
	}
	
	$scope.checkToilets = function(){
		if(parseInt($scope.infraOS.number_of_toiletsAvailableFor_Boys)+parseInt($scope.infraOS.number_of_toiletsAvailableFor_Girls) > parseInt($scope.infraOS.totalNumber_of_toiletsAvailable)){
			angular.element("#totalToilets").focus();
			document.getElementById('toiletError').innerHTML="Sum of toilets for boys and girls should not exceed total number of toilets.";
		}
		else{
			document.getElementById('toiletError').innerHTML="";
		}
	}
	

	var osbuildingRadios = document.getElementsByName('osbuilding');
	var osbuildingSetCheck;
	var x = 0;
	for(x = 0; x < osbuildingRadios.length; x++){
		osbuildingRadios[x].onclick = function(){
	        if(osbuildingSetCheck != this){
	             osbuildingSetCheck = this;
	             if(this.checked == true){
	            	 $scope.infraOS.building_Type = this.value;
	             }else{
	            	 $scope.infraOS.building_Type = null;
	             }
	        }else{
	            this.checked = false;
	            osbuildingSetCheck = null;
	            if(this.checked == true){
	            	 $scope.infraOS.building_Type = this.value;
	             }else{
	            	 $scope.infraOS.building_Type = null;
	             }
	        }
	        console.log($scope.infraOS.building_Type);
	    };
	}
	
	var osbuildingStatusRadios = document.getElementsByName('ospakka');
	var osbuildingStatusSetCheck;
	var x = 0;
	for(x = 0; x < osbuildingStatusRadios.length; x++){
		osbuildingStatusRadios[x].onclick = function(){
	        if(osbuildingStatusSetCheck != this){
	             osbuildingStatusSetCheck = this;
	             if(this.checked == true){
	            	 $scope.infraOS.status_of_Building = this.value;
	             }else{
	            	 $scope.infraOS.status_of_Building = null;
	             }
	        }else{
	            this.checked = false;
	            osbuildingStatusSetCheck = null;
	            if(this.checked == true){
	            	 $scope.infraOS.status_of_Building = this.value;
	             }else{
	            	 $scope.infraOS.status_of_Building = null;
	             }
	        }
	        console.log($scope.infraOS.status_of_Building);
	    };
	}
	
	var ccibuildingTypeRadios = document.getElementsByName('type');
	var ccibuildingTypeCheck;
	var x = 0;
	for(x = 0; x < ccibuildingTypeRadios.length; x++){
		ccibuildingTypeRadios[x].onclick = function(){
	        if(ccibuildingTypeCheck != this){
	        	ccibuildingTypeCheck = this;
	             if(this.checked == true){
	            	 $scope.infraCCI.building_Type = this.value;
	             }else{
	            	 $scope.infraCCI.building_Type = null;
	             }
	        }else{
	            this.checked = false;
	            ccibuildingTypeCheck = null;
	            if(this.checked == true){
	            	 $scope.infraCCI.building_Type = this.value;
	             }else{
	            	 $scope.infraCCI.building_Type = null;
	             }
	        }
	        console.log($scope.infraCCI.building_Type);
	    };
	}
	
	var ccibuildingStatusRadios = document.getElementsByName('type2');
	var ccibuildingStatusCheck;
	var x = 0;
	for(x = 0; x < ccibuildingStatusRadios.length; x++){
		ccibuildingStatusRadios[x].onclick = function(){
	        if(ccibuildingStatusCheck != this){
	             ccibuildingStatusCheck = this;
	             if(this.checked == true){
	            	 $scope.infraCCI.status_of_Building = this.value;
	             }else{
	            	 $scope.infraCCI.status_of_Building = null;
	             }
	        }else{
	            this.checked = false;
	            ccibuildingStatusCheck = null;
	            if(this.checked == true){
	            	 $scope.infraCCI.status_of_Building = this.value;
	             }else{
	            	 $scope.infraCCI.status_of_Building = null;
	             }
	        }
	        console.log($scope.infraCCI.status_of_Building);
	    };
	}
	
	var ssabuildingTypeRadios = document.getElementsByName('ssabuilding');
	var ssabuildingTypeCheck;
	var x = 0;
	for(x = 0; x < ssabuildingTypeRadios.length; x++){
		ssabuildingTypeRadios[x].onclick = function(){
	        if(ssabuildingTypeCheck != this){
	        	ssabuildingTypeCheck = this;
	             if(this.checked == true){
	            	 $scope.infraSAA.building_Type = this.value;
	             }else{
	            	 $scope.infraSAA.building_Type = null;
	             }
	        }else{
	            this.checked = false;
	            ssabuildingTypeCheck = null;
	            if(this.checked == true){
	            	 $scope.infraSAA.building_Type = this.value;
	             }else{
	            	 $scope.infraSAA.building_Type = null;
	             }
	        }
	        console.log($scope.infraSAA.building_Type);
	    };
	}
	
	var ssabuildingStatusRadios = document.getElementsByName('ssapakka');
	var ssabuildingStatusCheck;
	var x = 0;
	for(x = 0; x < ssabuildingStatusRadios.length; x++){
		ssabuildingStatusRadios[x].onclick = function(){
	        if(ssabuildingStatusCheck != this){
	             ssabuildingStatusCheck = this;
	             if(this.checked == true){
	            	 $scope.infraSAA.status_of_Building = this.value;
	             }else{
	            	 $scope.infraSAA.status_of_Building = null;
	             }
	        }else{
	            this.checked = false;
	            ssabuildingStatusCheck = null;
	            if(this.checked == true){
	            	 $scope.infraSAA.status_of_Building = this.value;
	             }else{
	            	 $scope.infraSAA.status_of_Building = null;
	             }
	        }
	        console.log($scope.infraSAA.status_of_Building);
	    };
	}
	
	$http.get("getAreadetails").
    then(function(result){
    	$scope.district={};
	   	$scope.areaDetail=result.data;
	   	$scope.areaDetail2=result.data;
	   	$scope.district.name=$scope.areaDetail.value;
	   	$scope.district.id = $scope.areaDetail.key;
	   	$scope.cciList=[];
		$scope.cciLists=[];
		
	   	$http.get("getCciList?areaId="+$scope.areaDetail.key).
		then(function(result){
			for (i = 0; i < result.data.length; i++) { 
				if(result.data[i].id==$scope.cciId){
					if(result.data[i].typeId==13){
					$scope.infraSt='infraOS';
					$scope.firstRadioDisable=true;
					$scope.secondRadioDisable=true;
					$scope.cciList.push(result.data[i]);
					}
					
					else if(result.data[i].typeId==4){
						$scope.infraSt='infraSSA';
						$scope.firstRadioDisable=true;
						$scope.thirdRadioDisable=true;
						$scope.cciList.push(result.data[i]);
						}
					
					else{
						$scope.infraSt='infraCCI';
						$scope.secondRadioDisable=true;
						$scope.thirdRadioDisable=true;
						$scope.cciLists.push(result.data[i]);
						}
				}
				
				
				else if(result.data[i].typeId==13 || result.data[i].typeId==4)
					$scope.cciList.push(result.data[i]);
				else
					$scope.cciLists.push(result.data[i]);
			}
			//$scope.cciList = result.data;
			console.log(result);
//			$rootScope.$broadcast("cciList", $scope.cciList);
		},function(error){
			console.log(error);
		});
    });
	$scope.buildingProtectedYes=true;
	$scope.buildingProtectedNo=false;
	$scope.infraCCI.building_Protected_by_Boundarywall=false;
	
	$scope.availabilityOfElectricityYes=true;
	$scope.availabilityOfElectricityNo=false;
	$scope.infraCCI.availability_Electricity_Allrooms=false;
	
	$scope.powerBackupYes=true;
	$scope.powerBackupNo=false;
	$scope.infraCCI.power_Backupfacility_AllRooms=false;
	
	$scope.availabilityOfDrinkingYes=true;
	$scope.availabilityOfDrinkingNo=false;
	$scope.infraCCI.availability_of_DrinkingWater=false;
	
	
	$scope.ssaBuildingProtectedYes=true;
	$scope.ssaBuildingProtectedNo=false;
	$scope.infraSAA.building_Protected_by_Boundarywall=false;
	
	$scope.ssaAVailabilityOfElectricityYes=true;
	$scope.ssaAVailabilityOfElectricityNo=false;
	$scope.infraSAA.availability_Electricity_Allrooms=false;
	
	$scope.ssaPowerBackupYes=true;
	$scope.ssaPowerBackupNo=false;
	$scope.infraSAA.power_Backupfacility_AllRooms=false;
	
	$scope.ssaAvailabilityOfDrinkingYes=true;
	$scope.ssaAvailabilityOfDrinkingNo=false;
	$scope.infraSAA.availability_of_DrinkingWater=false;
	
	$scope.osBuildingProtectedYes=true;
	$scope.osBuildingProtectedNo=false;
	$scope.infraOS.building_Protected_by_Boundarywall=false;
	
	$scope.osSeparateToiletsYes=true;
	$scope.osSeparateToiletsNo=false;
	$scope.infraOS.separate_Toiletsfor_Staff=false;
	
	$scope.osAvailabilityOfElectricityYes=true;
	$scope.osAvailabilityOfElectricityNo=false;
	$scope.infraOS.availability_Electricity_Allrooms=false;
	
	$scope.osAvailabilityOfPowerYes=true;
	$scope.osAvailabilityOfPowerNo=false;
	$scope.infraOS.power_Backupfacility_AllRooms=false;
	
	$scope.osAvailabilityOfDrinkingYes=true;
	$scope.osAvailabilityOfDrinkingNo=false;
	$scope.infraOS.availability_of_DrinkingWater=false;
	
	$scope.childSex="";
	 $http.get('getTypeDetails').
	    then(function(result){
	    	$scope.buildingType = result.data.buildingType;
	    	$scope.constructionOfBuilding = result.data.constructionOfBuilding;
		console.log(result.data);
	   }, function(error){
		   console.log(error);
	   });
	$scope.validateInfraCCI=function(){
		$('#infraCCI').modal('show');
	};
	$scope.validateInfraSSA=function(){
		$('#infraSAA').modal('show');
	};
	$scope.validateInfraOSDetail=function(){
		$('#infraOS').modal('show');
	};
	$scope.printInfraCCIData = {};
	$scope.printInfraSAAData = {};
	$scope.printInfraOSData = {};
	
	 $scope.saveInfraCCI= function (){
		 $scope.infraCCI.nameOfDistrict=$scope.areaDetail2;
		 
			if($scope.infraCCI.building_Type=="Rent"){
			  $scope.infraCCI.building_Type=$scope.buildingType[1];
		      }
		    else if($scope.infraCCI.building_Type=="Own"){
			  $scope.infraCCI.building_Type=$scope.buildingType[0];}
			
		   if($scope.infraCCI.status_of_Building=="Pakka"){
			$scope.infraCCI.status_of_Building=$scope.constructionOfBuilding[0];
		    }
		  else if($scope.infraCCI.status_of_Building=="Kachha"){
			$scope.infraCCI.status_of_Building=$scope.constructionOfBuilding[1];}
		   
		   $scope.printInfraCCIData = $scope.infraCCI;
		   
		  $http.post('saveInfraCCI',$scope.infraCCI).
		  then(function(response){
			  console.log(response);
			  $scope.infraCCI={};
			    $scope.infraCCI.building_Protected_by_Boundarywall=false;
				$scope.infraCCI.availability_Electricity_Allrooms=false;
				$scope.infraCCI.power_Backupfacility_AllRooms=false;
				$scope.infraCCI.availability_of_DrinkingWater=false;
				 $('#alertModalforInfra1').modal('show');
			  },function(error){
		    console.log(error);
		  });
	    };
	  //====================================download pdf============================
        $scope.printInfraCCISubmitData = function(){
        	$(".loader").css("display", "block");
        		var serverURL = 'downloadPDFDataForInfraCCI?type='+$scope.lang;
        		$scope.printInfraCCIData.nameOfDistrict.name = $scope.printInfraCCIData.nameOfDistrict.value;
        		commonService.downloadFile(serverURL, $scope.printInfraCCIData);
        		$scope.infraCCI = {};
    			$(".loader").css("display", "none");
        };
		
	 
	 $scope.saveInfraSAA = function (){
		 
		 $scope.infraSAA.nameOfDistrict=$scope.areaDetail2;
		 if($scope.infraSAA.building_Type=="Rent"){
			  $scope.infraSAA.building_Type=$scope.buildingType[1];
		      }
		    else if($scope.infraSAA.building_Type=="Own"){
			  $scope.infraSAA.building_Type=$scope.buildingType[0];}
			
			
		if($scope.infraSAA.status_of_Building=="Pakka"){
			$scope.infraSAA.status_of_Building=$scope.constructionOfBuilding[0];
		    }
		  else if($scope.infraSAA.status_of_Building=="Kachha"){
			$scope.infraSAA.status_of_Building=$scope.constructionOfBuilding[1];}
		
		  $scope.printInfraSAAData = $scope.infraSAA;
		  
		  $http.post('saveInfraSAA',$scope.infraSAA).
		  then(function(response){
			  console.log(response);
			  $scope.infraSAA={};
				$scope.infraSAA.building_Protected_by_Boundarywall=false;
				$scope.infraSAA.availability_Electricity_Allrooms=false;
				$scope.infraSAA.power_Backupfacility_AllRooms=false;
				$scope.infraSAA.availability_of_DrinkingWater=false;
				$('#alertModalforInfra2').modal('show');
			  },function(error){
		    console.log(error);
		  });
	 };
	//====================================download pdf============================
     $scope.printInfraSAASubmitData = function(){
     	$(".loader").css("display", "block");
     		var serverURL = 'downloadPDFDataForInfraSAA?type='+$scope.lang;
     		$scope.printInfraSAAData.nameOfDistrict.name = $scope.printInfraSAAData.nameOfDistrict.value;
     		commonService.downloadFile(serverURL, $scope.printInfraSAAData);
     		$scope.infraSAA = {};
 			$(".loader").css("display", "none");
     };
	 $scope.saveInfraOS = function (){
		 $scope.infraOS.nameOfDistrict=$scope.areaDetail2;

		 if($scope.infraOS.building_Type=="Rent"){
			  $scope.infraOS.building_Type=$scope.buildingType[1];
		      }
		    else if($scope.infraOS.building_Type=="Own"){
			  $scope.infraOS.building_Type=$scope.buildingType[0];}
			
		if($scope.infraOS.status_of_Building=="Pakka"){
			$scope.infraOS.status_of_Building=$scope.constructionOfBuilding[0];
		    }
		  else if($scope.infraOS.status_of_Building=="Kachha"){
			$scope.infraOS.status_of_Building=$scope.constructionOfBuilding[1];}
		
		$scope.printInfraOSData = $scope.infraOS;
		
		  $http.post('saveInfraOS',$scope.infraOS).
		  then(function(response){
			  console.log(response);
			  $scope.infraOS={};
				$scope.infraOS.building_Protected_by_Boundarywall=false;
				$scope.infraOS.separate_Toiletsfor_Staff=false;
				$scope.infraOS.availability_Electricity_Allrooms=false;
				$scope.infraOS.power_Backupfacility_AllRooms=false;
				$scope.infraOS.availability_of_DrinkingWater=false;
				
				$('#alertModalforInfra3').modal('show');
			  },function(error){
		    console.log(error);
		  });
	 };
	//====================================download pdf============================
     $scope.printInfraOSSubmitData = function(){
     	$(".loader").css("display", "block");
     		var serverURL = 'downloadPDFDataForInfraOS?type='+$scope.lang;
     		$scope.printInfraOSData.nameOfDistrict.name = $scope.printInfraOSData.nameOfDistrict.value;
     		commonService.downloadFile(serverURL, $scope.printInfraOSData);
     		$scope.infraCCI = {};
 			$(".loader").css("display", "none");
     };
	 
});
myAppConstructor.controller("financialReportController", function($scope,$http,$timeout,$window,commonService,$rootScope){
	
	 $scope.financialReport={};
	 $scope.financialReportPdf = "";
	 $scope.clearField = function(type){
	    	switch(type){
			case 'financialReportPdf':
				$timeout(function() {
					$scope.financialReportPdf ="";
			    }, 100);
				break;
	    	}
	    };
	    $rootScope.$on("cciList", function(event, data){
			$scope.cciList= data;
		}); 
		$scope.getBase64=function(file, type) {
		 	var reader = new FileReader();
		 	reader.readAsDataURL(file);
		 	reader.onload = function () {
		 		switch(type){
		 			case 'financialReportPdf':
		 				$timeout(function() {
		 					$scope.financialReportPdf =reader.result;
		 			    }, 100);
		 				break;
		 		}
		 	};
		 	reader.onerror = function (error) {
//		 	 console.log('Error: ', error);
		 	};
		};
		$scope.getReport = function ($files,type) {
			var validFormats = ['pdf'];
			var checkFile = false;
		     angular.forEach($files, function (value, key) {
		    	 var ext = value.name.split(".").pop();
		    	 if(validFormats.indexOf(ext) == -1){
		    		 alert("File not in proper format");
		    		commonService.clearUploadFile();
		    		$scope.clearField(type);
		         } else{
		        	 $scope.getBase64(value, type);
		        	 checkFile = true;
		         }
		     });
		     if(checkFile == true){
		    	 $scope.clearField(type);   
		     }
		     if($files.length == 0){
		    	 $scope.clearField(type);   
		     };
		};
	 $scope.saveFinancialReport = function (){
		 if($scope.financialReportPdf==""){}
		 
		 else{
			 $scope.financialReport.financialInspectionPath = $scope.financialReportPdf;
			 $scope.financialReport.type = "FR";
			 console.log($scope.financialReport);
			 
			 $http.post('saveFinancialInspectionReport', $scope.financialReport).
			     then(function(result){
			     	if(result.data=="success"){
			     		$scope.messageForModal = "File successfully submitted";
			     		$('#frSuccessResponse').modal('show');
			     	}
			     	$scope.financialReport = {};
			     	commonService.clearUploadFile();
			     	$scope.financialReportPdf="";
			    }, function(error){
			    	$scope.messageForModal = "File upload failed, please try again";
			    	$('#frSuccessResponse').modal('show');
			    });
		 }
	 };
	 
	 $scope.reDirectPage = function(){
		 $window.location.reload();
	 };
	 
	 $scope.downloadPdf = function(pdf,name){
		   var date = new Date();
	   		download(pdf , name + " "
				+ date.getFullYear() + (date.getMonth() + 1)
				+ date.getHours() + date.getMinutes()
				+ date.getSeconds() + ".pdf", "application/pdf");
	   };
}).directive('ngFiles', ['$parse', function ($parse) {

    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function (event) {
            onChange(scope, { $files: event.target.files });
        });
    };
    return {
        link: fn_link
    };
} ]);

myAppConstructor.controller("inspectionReportController", function($scope,$http,$timeout,$window,commonService,$rootScope){
	$scope.inspectionReport={};
	$scope.previousInspectionReport = {};
	$scope.showInspectionForm=false;
//	$rootScope.$on("designationId", function(event, data){
		if($scope.designationId==5 || $scope.designationId==6 ){
			$scope.showInspectionForm=true;
		}
//	});
	$rootScope.$on("cciList", function(event, data){
		$scope.cciList= data;
	});
	$scope.inspectionReportPdf = "";
	 $scope.clearFieldInspection = function(type){
	    	switch(type){
			case 'inspectionReportPdf':
				$timeout(function() {
					$scope.inspectionReportPdf ="";
			    }, 100);
				break;
	    	}
	    };
	    
		$scope.getBaseInspection64=function(file, type) {
		 	var reader = new FileReader();
		 	reader.readAsDataURL(file);
		 	reader.onload = function () {
		 		switch(type){
		 			case 'inspectionReportPdf':
		 				$timeout(function() {
		 					$scope.inspectionReportPdf =reader.result;
		 			    }, 100);
		 				break;
		 		}
		 	};
		 	reader.onerror = function (error) {
//		 	 console.log('Error: ', error);
		 	};
		};
		$scope.getInspectionReport = function ($files,type) {
			var validFormats = ['pdf'];
			var checkFile = false;
		     angular.forEach($files, function (value, key) {
		    	 var ext = value.name.split(".").pop();
		    	 if(validFormats.indexOf(ext) == -1){
		    		 alert("File not in proper format");
		    		commonService.clearUploadFile();
		    		$scope.clearField(type);
		         } else{
		        	 $scope.getBaseInspection64(value, type);
		        	 checkFile = true;
		         }
		     });
		     if(checkFile == true){
		    	 $scope.clearFieldInspection(type);   
		     }
		     if($files.length == 0){
		    	 $scope.clearFieldInspection(type);   
		     };
		};
	 $scope.saveInspectionReport = function (){
		 if($scope.inspectionReportPdf == ""){}
		 
		 else{
			 $scope.inspectionReport.financialInspectionPath = $scope.inspectionReportPdf;
			 $scope.inspectionReport.type = "IR";
			 console.log($scope.inspectionReport);
			 
			 $http.post('saveFinancialInspectionReport', $scope.inspectionReport).
			     then(function(result){
			     	if(result.data=="success"){
			     		$scope.messageForModal = "File successfully submitted";
			     		$('#irSuccessResponse').modal('show');
			     	}
			     	$scope.inspectionReport = {};
			     	commonService.clearUploadFile();
			     	$scope.inspectionReportPdf="";
			    }, function(error){
			    	$scope.messageForModal = "Unable to upload file, please try again";
		     		$('#irSuccessResponse').modal('show');
			    });
		 }
	 };
	 
	 $scope.reDirectPage = function(){
		 $window.location.reload();
	 };
	 
	 $scope.downloadInspectionPdf = function(pdf,name){
		   var date = new Date();
	   		download(pdf , name + " "
				+ date.getFullYear() + (date.getMonth() + 1)
				+ date.getHours() + date.getMinutes()
				+ date.getSeconds() + ".pdf", "application/pdf");
	   };
}).directive('ngFiles', ['$parse', function ($parse) {

    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function (event) {
            onChange(scope, { $files: event.target.files });
        });
    };
    return {
        link: fn_link
    };
} ]);

myAppConstructor.
directive('onlyTwoDigits', function () {
	
	return {
		restrict: 'A',
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val) || val === null) {
					val = '';
				}
				
				var clean = val.toString().replace(/[^0-9]/g, '');
				if(!angular.isUndefined(clean)) {
					var num=0;
					if(clean.length> 2 ){
						num =clean.slice(0,2);
						clean= num;
					}
					
				}
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
			
			element.bind('keypress', function(event) {
				if(event.keyCode === 32) {
					event.preventDefault();
				}
			});
		}
	};
});


myAppConstructor.
directive('onlyThreeDigits', function () {
	
	return {
		restrict: 'A',
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val) || val === null) {
					val = '';
				}
				
				var clean = val.toString().replace(/[^0-9]/g, '');
				if(!angular.isUndefined(clean)) {
					var num=0;
					if(clean.length>3 ){
						num =clean.slice(0,3);
						clean= num;
					}
					
				}
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
			
			element.bind('keypress', function(event) {
				if(event.keyCode === 32) {
					event.preventDefault();
				}
			});
		}
	};
});

myAppConstructor.
directive('onlyFourDigits', function () {
	
	return {
		restrict: 'A',
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val) || val === null) {
					val = '';
				}
				
				var clean = val.toString().replace(/[^0-9]/g, '');
				if(!angular.isUndefined(clean)) {
					var num=0;
					if(clean.length>4 ){
						num =clean.slice(0,4);
						clean= num;
					}
					
				}
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
			
			element.bind('keypress', function(event) {
				if(event.keyCode === 32) {
					event.preventDefault();
				}
			});
		}
	};
});

myAppConstructor
.directive('onlyDecimalNumbers', function() {
    return {
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
        if(!ngModelCtrl) {
          return; 
        }

        ngModelCtrl.$parsers.push(function(val) {
          if (angular.isUndefined(val)) {
              val = '';
          }
          
          var clean = val.replace(/[^-0-9\.]/g, '');
          var negativeCheck = clean.split('-');
			var decimalCheck = clean.split('.');
          if(!angular.isUndefined(negativeCheck[1])) {
              negativeCheck[1] = negativeCheck[1].slice(0, negativeCheck[1].length);
              clean =negativeCheck[0] + '-' + negativeCheck[1];
              if(negativeCheck[0].length > 0) {
              	clean =negativeCheck[0];
              }
              
          }
            
          if(!angular.isUndefined(decimalCheck[1])) {
              decimalCheck[1] = decimalCheck[1].slice(0,2);
              clean =decimalCheck[0] + '.' + decimalCheck[1];
          }

          if (val !== clean) {
            ngModelCtrl.$setViewValue(clean);
            ngModelCtrl.$render();
          }
          return clean;
        });

        element.bind('keypress', function(event) {
          if(event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    };
  });
myAppConstructor.
directive('onlyTenDigits', function () {
	
	return {
		restrict: 'A',
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val) || val === null) {
					val = '';
				}
				
				var clean = val.toString().replace(/[^0-9]/g, '');
				if(!angular.isUndefined(clean)) {
					var num=0;
					if(clean.length> 10 ){
						num =clean.slice(0,10);
						clean= num;
					}
					
				}
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
			
			element.bind('keypress', function(event) {
				if(event.keyCode === 32) {
					event.preventDefault();
				}
			});
		}
	};
});
app.directive('validDecimalNumber62', function() {
	return {
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					val = '';
				}
				
				var clean = val.toString().replace(/[^0-9\.]/g, '');
				var negativeCheck = clean.split('-');
				var decimalCheck = clean.split('.');
				if(!angular.isUndefined(negativeCheck[1])) {
					negativeCheck[1] = negativeCheck[1].slice(0, negativeCheck[1].length);
					clean =negativeCheck[0] + '-' + negativeCheck[1];
					if(negativeCheck[0].length > 0) {
						clean = negativeCheck[0];
					}
					
				}
				
				if(!angular.isUndefined(decimalCheck[1])) {
					decimalCheck[1] = decimalCheck[1].slice(0,2);
					clean =decimalCheck[0] + '.' + decimalCheck[1];
				}
				
				if(!angular.isUndefined(clean)) {
					var num=0;
					 if(clean.length==6 && clean[6]!='.' && clean[5] !='.'){
	            		 num =clean.slice(0,6);
	            		 clean= num;
	            	 }
	            	 if(clean.length==5  && clean[5]!='.'){
	            		 num =clean.slice(0,5);
	            		 clean= num;
	            	 }
	            	 if(clean.length == 7 && clean.indexOf('.') < 0){
	            		 num =clean.slice(0,6);
	            		 clean= num;
	            	 }
	            	 // decimal value 
	            	 if(clean.indexOf('.') > 6){
	            		 num = clean.slice(0,7).slice(0,6).concat(clean.slice(7,10));
	            		 clean = num;
	            	 }
					
				}
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
			
			element.bind('keypress', function(event) {
				if(event.keyCode === 32) {
					event.preventDefault();
				}
			});
		}
	};
});