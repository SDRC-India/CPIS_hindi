/**
 * author : Biswabhusan Prradhan
 */

myAppConstructor.controller('FinalOrderController', function($scope,$http,commonService,$rootScope,$location, $timeout,$window,gettextCatalog){
	$scope.lang='en';
	
	$http({
        method : "GET",
        url : "getLang"
    }).then(function mySucces(response) {
    	$scope.lang=response.data;
    	$scope.changeLanguage($scope.lang);
});
	
	$scope.changeLanguage = function(lang){
		
		console.log(lang);
		gettextCatalog.setCurrentLanguage(lang);
		$scope.lang=lang;
		$http({
	        method : "GET",
	        url : "setLangString?language="+$scope.lang
	    }).then(function mySucces(response) {
	    	
	});
	};
	$scope.designationId=document.getElementById('designationId').value;
	//disabling for CCI view
	if($scope.designationId!=7 && $scope.designationId!=8){
		$scope.rehabilitation=false;
		$scope.upload=false;
	}
	$(".loader").css("display", "block");
	$scope.isCNCPChild=false;
	$scope.selectedChildId=$('#modelValue').val();
	$scope.interimOrders=[];
	$scope.getChildByIdSuccessful = false;
	$scope.findAllInterimOrdersSuccessful = false;
	$scope.getTypeDetailsSuccessful = false;
	$scope.getDistrictListSuccessful = false;
	$scope.getGridMenuItemsSuccessful = false;
	$scope.getRestorationDataSuccesful = false;
	$scope.getCaseSummarySuccessful = false;
	$scope.getSponsorshipDataSuccessful = false;
	$scope.getAfterCareDataSuccessful = false;

	
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getChildByIdSuccessful && $scope.findAllInterimOrdersSuccessful 
				&& $scope.getTypeDetailsSuccessful && $scope.getDistrictListSuccessful
				&& $scope.getGridMenuItemsSuccessful && $scope.getRestorationDataSuccesful
				&& $scope.getCaseSummarySuccessful && $scope.getSponsorshipDataSuccessful
				&& $scope.getAfterCareDataSuccessful){
			$(".loader").css("display", "none");
		}
	};
	$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
	then(function(result){
		$scope.getChildByIdSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.selectedChild = result.data;
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
		if($scope.selectedChild.programType==0){
			$scope.isCNCPChild=true;
		}
//		$scope.cwcLogin
	},function(error){
		console.log(error);
	});
	
	$http.get("findAllInterimOrders?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.findAllInterimOrdersSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.interimOrders = result.data;
		$rootScope.$broadcast("interimOrders", $scope.interimOrders);
			if($scope.interimOrders.length==0){
				$('#noInterim').modal('show');
			}
	},function(error){
		console.log(error);
	});
//	$rootScope.$on("interimOrders", function(event, data){
//		$scope.interimOrders=data;
//	});
	
	$scope.reDirectToHome = function(){
		$window.location.href = '/CPIS/ccts';
	};
	
	$http.get("getTypeDetails").
	then(function(result){
		$scope.getTypeDetailsSuccessful = true;
		$http.get("getRestorationData?childId="+$scope.selectedChildId).
		then(function(result){
			$scope.getRestorationDataSuccesful = true;
			$scope.checkAllDataSuccessful();
			$scope.restorationselectedChild = result.data;
			if($scope.restorationselectedChild.id!=null){
				$rootScope.$broadcast("restorationData", $scope.restorationselectedChild);
			}
		},function(error){
			console.log(error);
		});
		$rootScope.$broadcast("typeDetails",result);
	},function(error){
		console.log(error);
	});
	
	$http.get("getDistrictList?areaLevel=4").
	then(function(result){
		$scope.getDistrictListSuccessful = true;
		$scope.checkAllDataSuccessful();
	$scope.districtList = result.data;
		$rootScope.$broadcast("districtList", result.data);
	},function(error){
		console.log(error);
	});
	
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
	});
	
	
	$rootScope.$on("ordersByCWC", function(event, data){
		$scope.ordersByCWC = data;
	});	
	
	$http.get("getCaseSummary?childId="+$scope.selectedChildId).
		then(function(result){
		$scope.getCaseSummarySuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.formInfo = result.data;
		$timeout(function(){
				var orderObject = $scope.formInfo.ordersPassedByCWC;
				if(orderObject!=null){
					$scope.splitVal = orderObject.split(',');
					for(var i=0; i < $scope.ordersByCWC.length; i++){
						for(var j=0; j < $scope.splitVal.length; j++){
							if(Number($scope.ordersByCWC[i].id) == $scope.splitVal[j]){
								 $scope.ordersByCWC[i].checked = true;
								}
							}
						}	
					}
				if($scope.formInfo!=null)
					$rootScope.$broadcast("caseSummary", $scope.formInfo);
		},500);
	},function(error){
		console.log(error);
	});
	
	$http.get("getSponsorshipData?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getSponsorshipDataSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.sponsorshipData = result.data;
		if($scope.sponsorshipData.id!=null){
		$rootScope.$broadcast("sponsorshipData", $scope.sponsorshipData);
		}
	},function(error){
		console.log(error);
	});
	
	$http.get("getAfterCareData?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getAfterCareDataSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.afterCareData = result.data;
		if($scope.afterCareData.id!=null)
		$rootScope.$broadcast("afterCareData", $scope.afterCareData);
	},function(error){
		console.log(error);
	});
		
	$http.get("getLegallyFreeForAdoptionData?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getLegallyFreeForAdoptionDataSuccessful = true;
		$scope.legallyFormInfo=result.data;
		if(result.data.id != null){
			$rootScope.$broadcast("legallyFreeForAdoptionData", result.data);
		}
	},function(error){
		console.log(error);
	});
		
	$scope.redirectForm=function(url){
		if(url=="child_registration" || url=="ciclSocialBackgroundReport" || url=="reportSummery"){
			commonService.redirectForm(url, $scope.selectedChild.childId);
		}else{
			if($scope.selectedChild.childId==null || $scope.selectedChild.childId == undefined 
				|| $scope.selectedChild.childId == ""){
				$('#noChildSelected').modal('show');
				
			}
			else if($scope.selectedChild.childId!=null && $scope.selectedChild.programType==1 && url=="interim_order"){
				url="ciclinterimOrder";
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}else if($scope.selectedChild.programType==1 && url=="socialInvestigation"){
				url="ciclSocialInvestigationReport";
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}else{
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}
		}
	};
	$scope.tempObj = {};
	$http.get("getChildEscortAndRehab?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getChildEscortAndRehabSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.tempObj = result.data;
		if($scope.tempObj=="" || $scope.tempObj==undefined){
			$scope.upload=true;
		} else{
			$scope.upload=false;
		}
		if($scope.tempObj.data==null){
			$scope.upload=true;
		} 
		if($scope.tempObj.type==null){
			$scope.rehabilitation=true;
		}
		if($scope.designationId!=7 && $scope.designationId!=8){
			$scope.rehabilitation=false;
			$scope.upload=false;
		}
	},function(error){
		console.log(error);
	});
	//====================================download pdf template============================
	$scope.downloadPdf = function(formNumber,type){
				
		if(type != "template"){
			if(type=="escortOrder")
				formNumber = $scope.tempObj.data;
			else
				formNumber = $scope.tempObj.type;
		}
		var serverURL = 'downloadPDFTemplate?fileName='+formNumber+"&typeOfPdf="+type+"&typeOfLanguage="+$scope.lang;
		commonService.downloadFile(serverURL);		
	};
	$scope.rehabilitationCard ="";
	$scope.escortOrder ="";
	$scope.uploadPDF={};
	$scope.uploadFiles = function(type){
		
		var serverURL = 'uploadEscortPDF';
		if(type=='escortOrder'){
			$scope.uploadPDF.data = $scope.escortOrder;
			$scope.uploadPDF.type = "escortOrder";
		} else{
			$scope.uploadPDF.data = $scope.rehabilitationCard;
			$scope.uploadPDF.type = "rehabilitationCard";
		}
		$scope.uploadPDF.childId = $scope.selectedChildId;
		if($scope.uploadPDF.data=="")
			 $( "#uploadFileIdFalse" ).modal("show"); 
		else{
			$http.post(serverURL, $scope.uploadPDF).
			  then(function(response){
				  if(response.data == true)
					  $( "#uploadIdTrue" ).modal("show");
				  else
					  $( "#uploadIdFalse" ).modal("show"); 
			  });
		}
	};
	$scope.reDirect = function(){
		$window.location.reload();
	};
	$scope.clearField = function(type){
		 switch(type){
			case 'escortOrder':
				$timeout(function() {
					$scope.escortOrder ="";
			    }, 100);
				break;
			case 'rehabilitationCard':
				$timeout(function() {
					$scope.rehabilitationCard ="";
			    }, 100);
				break;
		}
	};
	$scope.getBase64=function(file, type) {
	 	var reader = new FileReader();
	 	reader.readAsDataURL(file);
	 	reader.onload = function () {
	 		switch(type){
	 			case 'escortOrder':
	 				$timeout(function() {
	 					$scope.escortOrder =reader.result;
	 			    }, 100);
	 				break;
	 			case 'rehabilitationCard':
	 				$timeout(function() {
	 					$scope.rehabilitationCard =reader.result;
	 			    }, 100);
	 				break;
	 		}
	 	};
	 	reader.onerror = function (error) {
//	 	 console.log('Error: ', error);
	 	};
	};
	$scope.getReport = function ($files,type) {
		var validFormats = ['pdf'];
		var checkFile = false;
	     angular.forEach($files, function (value, key) {
	    	 var ext = value.name.split(".").pop();
	    	/* var re = /(?:\.([^.]+))?$/;
	    	 var ext = re.exec(value.name)[1]; */
	    	 if(validFormats.indexOf(ext) == -1){
	    		$('#errorModal').modal('show');
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

//var app = angular.module('FinalOrder',[]);
	
myAppConstructor.controller('CaseSummaryController', function($scope,$http,commonService,$rootScope,$location, $timeout,$window){

	$scope.otherFieldFlag = false;
	$scope.formInfo = {};
	$scope.trueValue = true;
	$scope.falseValue = false;
	$scope.radioDisabled = true;
	$scope.ordersByCWC = [];
	$scope.getGridMenuItemsSuccessful = false;
	$scope.getTypeDetailsSuccessful = false;

//	var ids = "";
	$scope.selectedChildId=$('#modelValue').val();
	$scope.designationId=document.getElementById('designationId').value;
	//disabling for CCI view
	if($scope.designationId!=7 && $scope.designationId!=8){
		$scope.caseSummaryDisable=true;
	}
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.getTypeDetailsSuccessful){
			$(".loader").css("display", "none");
		}
	};
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
	});
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.caseSummaryDisable=true;
		}
		$scope.formInfo.socialInvestigationReport = $scope.selectedChild.sirFilled == 1 ? true : false;
		$scope.formInfo.individualCarePlan = $scope.selectedChild.icpFilled == 1 ? true : false;
		$scope.formInfo.rehabitationCard = $scope.selectedChild.rehabilitationCardFilled == 1 ? true : false;
		$scope.formInfo.caseHistory = $scope.selectedChild.caseHistoryFilled == 1 ? true : false;
		$scope.formInfo.parentOrGuardianName = null != $scope.selectedChild.sirFatherName ? $scope.selectedChild.sirFatherName
				: null!= $scope.selectedChild.sirMotherName ? $scope.selectedChild.sirMotherName : null;
	});
	
	$rootScope.$on("selectedChild1", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.caseSummaryDisable=true;
		}
	});
	
	/*$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
	then(function(result){
		$scope.selectedChild = result.data;
		console.log(result);
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
	},function(error){
		console.log(error);
	});*/
//	var checkboxList = [$scope.childCategory];
//	var errorDivList = ['#anyOtherCategoryError'];
//	var showErrorDiv = ['#anyOtherCategoryError'];
//	for(var i=0; i < checkboxList.length; i++){
//		for(var j=0; j<checkboxList[i].length; j++){
//			if(checkboxList[i][j].checked == true){
//				var index = showErrorDiv.indexOf(errorDivList[i]);
//				 showErrorDiv.splice(index, 1);
//				}
//		}
//	}	
//	for(var i=0; i<errorDivList.length; i++){
//		$(errorDivList[i]).html("");
//	}
	
	$http.get('getTypeDetails').
	then(function(result){
		$scope.getTypeDetailsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.ordersByCWC = result.data.ordersByCWC;
		$rootScope.$broadcast("ordersByCWC", $scope.ordersByCWC);
	}, function(error){
		console.log(error);
	});
	
//	$scope.ordersByCWC = [{name:"Declaration that child is in need of care and protection", id:1, checked : false},
//	                      {name:"Finding on age of child", id:2, checked : false},
//	                      {name:"Medical Examination", id:3, checked : false},
//	                      {name:"Interim custody", id:4, checked : false},
//	                      {name:"Undertaking (by parent, guardian or fit person, if applicable)", id:5, checked : false},
//	                      {name:"Order appointing Case Worker & NGO etc.", id:6, checked : false},
//	                      {name:"Order for compensation/recovery of wages (if applicable)", id:7, checked : false},
//	                      {name:"Final Order (concluding inquiry)", id:8, checked : false},
//	                      {name:"Any other order", id:9, checked : false}];
	
//	$scope.validateOrders = function(order){
//		if(order.checked == true){
//			if(order.id == 41)
//				$scope.otherFieldFlag = true;
//			
//			for(var i=0; i<$scope.ordersByCWC.length; i++){
//				if(order.id == $scope.ordersByCWC[i].id)
//					$scope.ordersByCWC[i].checked = true;
//			}
//		}
//		else{
//			if(order.id == 41){
//				$scope.otherFieldFlag = false;
//				$scope.formInfo.ordersPassedByCwcOthers = null;
//			}
//			for(var i=0; i<$scope.ordersByCWC.length; i++){
//				if(order.id == $scope.ordersByCWC[i].id)
//					$scope.ordersByCWC[i].checked = false;
//			}
//		}
//	};
	$scope.arr=[];
	$scope.validateOrders = function(selectedObjs){
		$scope.keyString = "";
		   $scope.arr.push(selectedObjs);
		   for(var i=0; i<$scope.arr.length; i++){
			   if($scope.arr[i].checked == false){
				   $scope.arr.splice(i, 1);
			   }
		   }
		   for(var i=0; i<$scope.arr.length; i++){
			   if($scope.arr[i].checked == false){
				   $scope.arr.splice(i, 1);
			   }
		   }
		   for(var j=0;j<$scope.arr.length;j++){
			   $scope.keyString+=$scope.arr[j].id+",";
		   }
		   $scope.keyString = $scope.keyString.substring(0, $scope.keyString.length-1);
		   $scope.formInfo.ordersPassedByCWC=$scope.keyString;
		   if($scope.keyString.includes("41")){
			   $scope.otherFieldFlag = true;
		   }else{
			   $scope.otherFieldFlag = false;
			   $scope.formInfo.ordersPassedByCwcOthers = null;
		   }
	};
	$rootScope.$on("ordersByCWC", function(event, data){
		$scope.ordersByCWC=data;
		finalizeOrdersPassedByCWC();
	});
	
	var finalizeOrdersPassedByCWC = function(){
		var ids = "";
		$scope.ordersPassedByCWCName = "";
		for(var i=0; i<$scope.ordersByCWC.length; i++){
			if($scope.ordersByCWC[i].checked == true){
				ids += $scope.ordersByCWC[i].id + ",";
				$scope.lang == 'en' ? $scope.ordersPassedByCWCName += $scope.ordersByCWC[i].name + ",  " : $scope.ordersPassedByCWCName += $scope.ordersByCWC[i].typeNameHindi + ",  ";
			}
		}
		ids = ids.substring(0, ids.length - 1);
		$scope.formInfo.ordersPassedByCWC = ids;
		$scope.ordersPassedByCWCName = $scope.ordersPassedByCWCName.substring(0, $scope.ordersPassedByCWCName.length-3);
		console.log("ordersPassedByCWCName: "+$scope.ordersPassedByCWCName);
	};
	
	 $scope.blur = function (value,name) {
	  if(name=="ordersPassedByCwcOthers"){
	   	  $scope.formInfo.ordersPassedByCwcOthers=value.trim();  
	  }
	 };
	$scope.validateCsForm = function(){
		
//		finalizeOrdersPassedByCWC();
		var checkboxList = [$scope.ordersByCWC]; 
		                    
	   var errorDivList = ['#ordersPassedeError'];
	   var showErrorDiv = ['#ordersPassedeError'];
	   for(var i=0; i < checkboxList.length; i++){
			for(var j=0; j<checkboxList[i].length; j++){
				if(checkboxList[i][j].checked == true){
					var index = showErrorDiv.indexOf(errorDivList[i]);
					 showErrorDiv.splice(index, 1);
					}
			}
		}	
	   for(var i=0; i<errorDivList.length; i++){
			$(errorDivList[i]).html("");
		}
	   
	   if(showErrorDiv.length){
			 $(showErrorDiv[0]).html("please select at least one option");
			 $('html,body').animate({
			        scrollTop: $(showErrorDiv[0]).offset().top - 250},'slow');
			 return false;
	   }else if($scope.formInfo.dateOfFormFilled == undefined){
		   $( "#formFilledDate" ).datepicker("show");
	   }else 
		   $('#confirmationModalForCaseSummary').modal('show');  
   };
		
	
	$("#formFilledDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
		    $scope.formInfo.dateOfFormFilled = date;
		  }	
	});
	$scope.medicalReports ="";
	$scope.printFitData ={};
	$scope.saveCSData = function(){
		$scope.formInfo.medicalReports = ($scope.medicalReports==""?null:$scope.medicalReports);
		$scope.formInfo.childId=$scope.selectedChild.childId;
		$scope.printFitData = $scope.formInfo;
		$(".loader").css("display", "block");
		$http.post('saveCaseSummaryCWCData',$scope.formInfo).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$( "#childCaseSummaryModal" ).modal("show");
		}, function(error){
			console.log(error);
		});
	};
	 
	    $scope.clearImageField = function(type){
	    	switch(type){
			case 'medicalReports':
				$timeout(function() {
					$scope.medicalReports ="";
			    }, 100);
				break;
	    	}
	    };
		$scope.getBase64=function(file, type) {
		 	var reader = new FileReader();
		 	reader.readAsDataURL(file);
		 	reader.onload = function () {
		 		switch(type){
		 			case 'medicalReports':
		 				$timeout(function() {
		 					$scope.medicalReports=reader.result;
		 			    }, 100);
		 				break;
		 		}
		 	};
		 	reader.onerror = function (error) {
//		 	 console.log('Error: ', error);
		 	};
		};
		$scope.getReport = function ($files,type) {
			var validFormats = ["pdf"];
			var checkFile = false;
		     angular.forEach($files, function (value, key) {
		    	 var ext = value.name.split(".").pop();
		    	 if(validFormats.indexOf(ext) == -1){
			    		$('#errorImageModal').modal('show');
			    		commonService.clearUploadFile();
			    		$scope.clearImageField(type);
			         } else{
			        	 $scope.getBase64(value, type);
			        	 checkFile = true;
			         }
		     });
		     if(checkFile == true){
		    	 $scope.clearImageField(type);   
		     }
		     if($files.length == 0){
		    	 $scope.clearImageField(type);   
		     };
		};
		
		
		$scope.downloadPdf = function(pdf,name){
		   var date = new Date();
		   if(pdf != null){
   			download(pdf , name + " "
				+ date.getFullYear() + (date.getMonth() + 1)
				+ date.getHours() + date.getMinutes()
				+ date.getSeconds() + ".pdf", "application/pdf");
		   }else{
			   $('#noFileModal').modal('show');
		   }
	   };
	//====================================download pdf============================
	$scope.printCaseSummarySubmitData = function(){
		finalizeOrdersPassedByCWC();
		$scope.caseSummaryDisable = true;
		
		var serverURL = 'downloadPDFDataReportForCaseSummary?type='+$scope.lang;
		
		$scope.printFitData.childName=$scope.selectedChild.childName;
		$scope.printFitData.placeOfFormFilled=$scope.selectedChild.district.name;
		$scope.printFitData.personName=$scope.selectedChild.personWhoProduceChild;
		$scope.printFitData.dateOfProduction=$scope.selectedChild.dateOfFirstProduction;
		$scope.printFitData.interimOrderDates=$scope.date;
		$scope.printFitData.caseNo=$scope.selectedChild.caseNum;
		$scope.printFitData.ordersPassedByCWCName = $scope.ordersPassedByCWCName;
		$scope.printFitData.programType = $scope.selectedChild.programType;
		
		commonService.downloadFile(serverURL, $scope.printFitData);

  };
  $scope.date="";
  $scope.interimOrders=[];
//  $scope.caseSummaryDisable=false;
  $rootScope.$on("caseSummary", function(event, data){
		$scope.formInfo=data;
		$scope.printFitData = $scope.formInfo;
		if($scope.formInfo.id!=null){
			$scope.caseSummaryDisable=true;
			if($scope.formInfo.ordersPassedByCWC != null){
				var pfordersPassedByCWC = $scope.formInfo.ordersPassedByCWC;
				$scope.ordersPassedByCWCName = "";
				$scope.pfordersPassedByCWCArr = pfordersPassedByCWC.split(',');
				for(var i=0; i < $scope.pfordersPassedByCWCArr.length; i++){
					for(var j=0; j < $scope.ordersByCWC.length; j++){
						if(Number($scope.pfordersPassedByCWCArr[i]) == $scope.ordersByCWC[j].id){
							$scope.ordersByCWC[j].checked = true;
							$scope.ordersPassedByCWCName+=$scope.ordersByCWC[j].name+",  ";
						}
					}
				}
				$scope.ordersPassedByCWCName = $scope.ordersPassedByCWCName.substring(0, $scope.ordersPassedByCWCName.length-3);
			}
		}
		
	});
  
//	$http.get("findAllInterimOrders?childId="+$scope.selectedChildId).
//	then(function(result){
//		
//		$scope.interimOrders = result.data;
//	for(var i=0;i<result.data.length;i++){
//			if(i!=result.data.length-1){$scope.date += result.data[i].dateOfFormFilled+" , ";}
//			else $scope.date += result.data[i].dateOfFormFilled;
//		}
//		
//		$rootScope.$broadcast("interimOrders", $scope.interimOrders);
//	},function(error){
//		console.log(error);
//	});
	
	$rootScope.$on("interimOrders", function(event, data){
		for(var i=0;i<data.length;i++){
			if(i!=data.length-1){
				$scope.date += data[i].dateOfFormFilled+" , ";
				}
			else 
					$scope.date += data[i].dateOfFormFilled;
		}
		$scope.interimOrders=data;
	});
	
	$scope.reDirect = function(){
		$window.location.reload();
	};
}).directive('ngFiles', ['$parse', function ($parse){
    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function (event) {
            onChange(scope, { $files: event.target.files });
        });
    };
    return {
        link: fn_link
    };
}]);




myAppConstructor.controller('RestorationOrderController', function($scope,$http,commonService,$rootScope,$window,$location){
	
	$scope.selectedChildId=$('#modelValue').val();
	$scope.cwcName=$('#cwcName').val();
	$scope.district=$('#district').val();
	$scope.selectedChild = {};
	$scope.restorationselectedChild= {};
	$scope.restorationViewPage = false;
	$scope.designationId=document.getElementById('designationId').value;
	if($scope.designationId!=7 && $scope.designationId!=8){
		$scope.restorationViewPage=true;
	}
	
	
	$rootScope.$on("districtList", function(event, data){
		$scope.districtList = data;
	});
	
	$rootScope.$on("typeDetails", function(event, data){
//		var result = data;
		$scope.placedOrder = data.data.placedOrder;
	});
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
		$scope.selectedChild = data;
		$scope.printData= data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.restorationViewPage=true;
		}
	});
	
	$rootScope.$on("selectedChild1", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.restorationViewPage=true;
		}
	});

	$rootScope.$on("restorationData", function(event, data){
		if(data.id != null){
			$scope.restorationselectedChild = data;
			for(var i = 0; i < $scope.placedOrder.length; i++){
				if($scope.restorationselectedChild.childOrderPlacedInId == $scope.placedOrder[i].id){
					$scope.childOrderPlacedInName = $scope.lang=='en'?$scope.placedOrder[i].name:$scope.placedOrder[i].typeNameHindi;
					$scope.childOrderPlacedIn=$scope.placedOrder[i];
				}
			}
			$scope.restorationViewPage = true;
		}
	});
	
	$("#onTheDate10").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
		    $scope.restorationselectedChild.placedDate = date;
		  }	
	});
	
	$("#dateOfOrder").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
		    $scope.restorationselectedChild.orderDate = date;
		  }	
	});
	
	$scope.validateRestorationOrder = function(){
		if($scope.restorationselectedChild.timePeriod!=null || $scope.restorationselectedChild.timePeriod!=undefined){
		if($scope.restorationselectedChild.placedDate == undefined){
			$("#onTheDate10").datepicker("show");
		}else if($scope.restorationselectedChild.orderDate == undefined){
			$("#dateOfOrder").datepicker("show");
		}else{
			$('#confirmationModalForRestorationOrder').modal('show');
		}
		}
	};
	$scope.printData = {};
	$scope.saveRestorationData = function(){
		if($scope.formInfo.id==null){
			$scope.formInfo={};
			$scope.ordersByCWC=[];
			$rootScope.$broadcast("caseSummary", $scope.formInfo);
			$rootScope.$broadcast("ordersByCWC", $scope.ordersByCWC);
		}
		if($scope.sponsorshipData.id==null){
			$scope.sponsorshipData={};
			$rootScope.$broadcast("sponsorshipData", $scope.sponsorshipData);
		}
		if($scope.afterCareData.id==null){
			$scope.afterCareData={};
			$rootScope.$broadcast("afterCareData", $scope.afterCareData);
		}
		if($scope.legallyFormInfo!=null && $scope.legallyFormInfo.id==null){
			$scope.legallyFormInfo=null;
			$rootScope.$broadcast("legallyFreeForAdoptionData", $scope.legallyFormInfo);
		}
		
		$scope.restorationselectedChild.childId=$scope.prefetchData.childId;
		$scope.printData = $scope.restorationselectedChild;
		$(".loader").css("display", "block");
		$http.post('saveRestorationData',$scope.restorationselectedChild).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$('#successModalForRestorationOrder').modal('show');
			for(var i = 0; i < $scope.placedOrder.length; i++){
				if($scope.restorationselectedChild.childOrderPlacedIn.id == $scope.placedOrder[i].id){
					$scope.childOrderPlacedInName = $scope.placedOrder[i].name;
					$scope.childOrderPlacedIn=$scope.placedOrder[i];
				}
			}
		}, function(error){
			console.log(error);
		});
	};
	
	
	  
	$scope.reDirect = function(){
		$window.location.reload();
	};
	
	//====================================download pdf============================
	$scope.printRestorationForm = function(){
		$http.get("getRestorationData?childId="+$scope.selectedChildId).
		then(function(result){
			$scope.restorationselectedChild = result.data;
			if($scope.restorationselectedChild.id!=null){
				$scope.printData=$scope.restorationselectedChild;
				$scope.restorationViewPage = true;
				$scope.selectedChild.finalOrderFilled=1;
				$rootScope.$broadcast("selectedChild1", $scope.selectedChild);
				var serverURL = 'downloadPDFDataRestoration?type='+$scope.lang;
				
				$scope.printData.childId = $scope.selectedChild.childId;
				$scope.printData.childName=$scope.prefetchData.childName;
				$scope.printData.sirFatherName=$scope.prefetchData.sirFatherName;
				$scope.printData.sirMotherName=$scope.prefetchData.sirMotherName;
				$scope.printData.sirAddress=$scope.prefetchData.sirAddress;
				$scope.printData.caseNum=$scope.prefetchData.caseNum;
				$scope.printData.institutionDistrictName=$scope.prefetchData.district.name;
				$scope.printData.cwcName=$scope.prefetchData.cwc.name;
				$scope.printData.childOrderPlacedInName= $scope.childOrderPlacedInName;
				$scope.printData.childOrderPlacedIn=$scope.childOrderPlacedIn;
				$scope.printData.programType = $scope.prefetchData.programType;
				
				commonService.downloadFile(serverURL, $scope.printData);
			}
		},function(error){
			console.log(error);
		});
		
	};
});

myAppConstructor.controller('AfterCareController', function($scope,$http,$rootScope,commonService,$window){
	$scope.afterCareObj = {};
	$scope.afterCarePrefetchObj = {};
	$scope.afterCareDisable=false;
	$scope.designationId=document.getElementById('designationId').value;
	if($scope.designationId!=7 && $scope.designationId!=8){
		$scope.afterCareDisable=true;
	}
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.afterCareDisable = true;
		}
		$scope.afterCarePrefetchObj.fatherName = $scope.selectedChild.sirFatherName;
		$scope.afterCarePrefetchObj.motherName = $scope.selectedChild.sirMotherName;
		$scope.afterCareObj.childId = $scope.selectedChild.childId;
		$scope.printFitData.childName=$scope.selectedChild.childName;
//		$scope.year=18-$scope.selectedChild.age;
	});
	
	$rootScope.$on("selectedChild1", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.afterCareDisable = true;
		}
	});
	
	$rootScope.$on("afterCareData", function(event, data){
		$scope.afterCareDisable=true;
		$scope.afterCareObj = data;
		$scope.printFitData = $scope.afterCareObj; 
	});	
	
//	$("#completing18datepicker").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', minDate: 0, maxDate: '+'+$scope.year+'Y +11M +30D' ,changeYear: true,
		$("#completing18datepicker").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', minDate: 0, maxDate: '+13Y +11M +30D' ,changeYear: true,
		onSelect: function(date) {
		    $scope.afterCareObj.completing18On = date;
		  }	
	});
	
	$("#amountReleased").keypress(function(event) {
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	
	$("#periodOf").keypress(function(event) {
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	
	$scope.validateAcForm = function(){
		if($scope.afterCareObj.completing18On == undefined){
			$("#completing18datepicker").datepicker("show");
		}else{
			$('#afterCareModal').modal('show');
		}
	};
	$scope.printFitData ={};
	$scope.saveAfterCare = function(){
//		$scope.afterCareObj.childId = $scope.selectedChild.childId;
		$scope.printFitData = $scope.afterCareObj;
		$(".loader").css("display", "block");
		$http.post('saveAfterCareData',$scope.afterCareObj).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$('#alertModal').modal('show');
//			$scope.afterCareObj={};
		}, function(error){
			console.log(error);
		});
	};
	//====================================download pdf============================
	$scope.printAfterCareData = function(){
		$scope.afterCareDisable = true;
		$scope.printFitData.childId  = $scope.selectedChild.childId;
		$scope.printFitData.childName = $scope.selectedChild.childName;
		$scope.printFitData.sirFatherName = $scope.selectedChild.sirFatherName;
		$scope.printFitData.sirMotherName = $scope.selectedChild.sirMotherName;
		$scope.printFitData.programType = $scope.selectedChild.programType;
		var serverURL = 'downloadPDFDataAfterCare?type='+$scope.lang;
		commonService.downloadFile(serverURL, $scope.printFitData);
    };
    $scope.reDirect = function(){
		$window.location.reload();
	};
});





myAppConstructor.controller('SponsorshipController', function($scope,$http,commonService,$rootScope,$location,$window){
	$scope.sponsorshipData={};
	$scope.designationId=document.getElementById('designationId').value;
	$scope.sponsorshipDisable=false;
	if($scope.designationId!=7 && $scope.designationId!=8){
		$scope.sponsorshipDisable=true;
	}
	
	$rootScope.$on("typeDetails", function(event, data){
		var result = data;
		$scope.sponsorshipSupportFor = result.data.sponsorshipSupportFor;
	});
	$scope.setSelectedObj = function(item){
		$scope.sponsorshipData.sponsorshipFor=item;
	};
	$scope.validateSponsorshipForm = function(){
		$('#sponsorshipModal').modal('show');
	};
	
	$scope.clearOther = function(){
		$scope.sponsorshipData.otherSponsorshipFor = null;
	};
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.sponsorshipDisable = true;
		}
	});
	$rootScope.$on("selectedChild1", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.sponsorshipDisable = true;
		}
	});
	$rootScope.$on("sponsorshipData", function(event, data){
		$scope.sponsorshipDisable=true;
		$scope.sponsorshipData = data;
		$scope.printFitData=data;
	});	
	 $scope.sponsorsBlur = function (value,name) {
	   if(name=="otherSponsorshipFor"){
	   	  $scope.sponsorshipData.otherSponsorshipFor=value.trim();  
	   }
     };
	$scope.printFitData ={};
	$scope.saveSponsorshipData = function(){
		$scope.sponsorshipData.childId=$scope.prefetchData.childId;
		$scope.printFitData =$scope.sponsorshipData;
		$(".loader").css("display", "block");
		$http.post('saveSponsorshipOrder',$scope.sponsorshipData).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$('#sponsorshipAlertModal').modal('show');
			$scope.sponsorshipPlacement={};
		}, function(error){
			console.log(error);
		});
	};
	
	//====================================download pdf============================
	$scope.printSponsorshipData = function(){
		
		$scope.printFitData.childName = $scope.prefetchData.childName;
		$scope.printFitData.sirAddress = $scope.prefetchData.sirAddress;
		$scope.printFitData.age = $scope.prefetchData.age;
		$scope.printFitData.motherName = $scope.prefetchData.sirMotherName;
		$scope.printFitData.fatherName = $scope.prefetchData.sirFatherName;
		$scope.printFitData.childId = $scope.prefetchData.childId;
		$scope.printFitData.programType = $scope.prefetchData.programType;
		$scope.printFitData.typeOfSponsorship = $("#per_ option:selected").text();
		$scope.printFitData.daysOrMonths = $("#days-or-month option:selected").text();
		var amount = $scope.printFitData.sponsorshipAmount;
		if($scope.printFitData.typeOfSponsorship == 'One Time' || $scope.printFitData.typeOfSponsorship == 'एक बार'){
			$scope.printFitData.sponsorshipAmount = null;
			$scope.printFitData.sponsorshipAmountOneTime = amount;
		}else{
			$scope.printFitData.sponsorshipAmount = amount;
			$scope.printFitData.sponsorshipAmountOneTime = null;
		}
		$scope.printFitData.sponsorshipFor.name=commonService.getNameBySelectedLanguageType($scope.printFitData.sponsorshipFor.name,$scope.sponsorshipSupportFor,$scope.lang);
		
		var serverURL = 'downloadPDFDataSponsorship?type='+$scope.lang;
		commonService.downloadFile(serverURL, $scope.printFitData);
		$scope.sponsorshipDisable=true;
		
    };
    $("#selectDatafromsponsor").keypress(function(event) {
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
    
    $scope.reDirect = function(){
		$window.location.reload();
	};
    
});
//=========================legally free Adoption=============
myAppConstructor.controller('LegallyFreeController', function($scope,$http,$rootScope,commonService,$window,$timeout,$filter){
	$scope.selectedChild ={};
	$scope.legallyFormInfo={};
	$scope.selectedChildId=$('#modelValue').val();
	$scope.cwcId=$('#cwcId').val();
	$scope.legallyFreeDisable = false;
	$scope.designationId=document.getElementById('designationId').value;
	if($scope.designationId!=7 && $scope.designationId!=8){
		$scope.legallyFreeDisable=true;
	}
	
	$scope.getLegallyFreeForAdoptionDataSuccessful = false;
	$scope.getChildRegMstDataSuccessful = false;
	$scope.getDistrictListSuccessful = false;
	$(".loader").css("display", "block");
	$rootScope.$on("selectedChild", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.legallyFreeDisable = true;
		}
		console.log($scope.selectedChild);
		$scope.printFitData.childName=$scope.selectedChild.childName;
	});
	$rootScope.$on("selectedChild1", function(event, data){
		$scope.selectedChild = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.legallyFreeDisable = true;
		}
	});
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getLegallyFreeForAdoptionDataSuccessful && $scope.getChildRegMstDataSuccessful 
				&& $scope.getDistrictListSuccessful){
			$(".loader").css("display", "none");
		}
	};
	
/*	$http.get("getLegallyFreeForAdoptionData?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getLegallyFreeForAdoptionDataSuccessful = true;
		if(result.data.id != null){
			$scope.legallyFormInfo = result.data;
			$scope.legallyFreeDisable = true;
		}
	},function(error){
		console.log(error);
	});*/
	
	$rootScope.$on('legallyFreeForAdoptionData', function(event, data){
		$scope.legallyFormInfo = data;
		if($scope.legallyFormInfo==null){
			$scope.cciAddress=null;
		}
		$scope.legallyFreeDisable = true;
	});
	
	$http.get("getChildRegMstData").
	   then(function(result){
		   $scope.getChildRegMstDataSuccessful = true;
		   $scope.cciList=result.data.value.cciList;
		   if($scope.legallyFormInfo.saaOrCciId!=undefined){
			   $scope.filteredAddress=($filter('filter')($scope.cciList, {cciId: $scope.legallyFormInfo.saaOrCciId})); 
			   $scope.cciAddress=$scope.filteredAddress[0].address;
		   }
		   console.log($scope.cciList);
	
	   }, function(error){
	   	console.log(error);
	   }); 
	
	$http.get("getDistrictList?areaLevel=4").
	then(function(result){
		$scope.getDistrictListSuccessful = true;
	  $scope.districtList = result.data;
	  $scope.filteredDistrct=($filter('filter')($scope.districtList, {areaId: $scope.cwcId})); 
	  $scope.cwcDistrict=$scope.filteredDistrct[0].areaName;
	},function(error){
		console.log(error);
	});

	
	$scope.inquiryReport = null;
	$scope.surrenderDeed = null;
	$scope.declaration = null;
	$scope.clearImageUploadField = function(type){
		switch(type){
			case 'inquiryReport':
				$timeout(function() {
					$scope.inquiryReport = null;
			    }, 100);
				break;
			case 'surrenderDeed':
				$timeout(function() {
					$scope.surrenderDeed = null;
				 }, 100);
				break;
			case 'declaration':
				$timeout(function() {
					$scope.declaration = null;
				 }, 100);
				break;
		}
	};
	$scope.getBase64=function(file, type) {
	 	var reader = new FileReader();
	 	reader.readAsDataURL(file);
	 	reader.onload = function () {
	 		switch(type){
	 			case 'inquiryReport':
	 				$timeout(function() {
	 					$scope.inquiryReport =reader.result;
	 			    }, 100);
	 				break;
	 			case 'surrenderDeed':
	 				$timeout(function() {
	 					$scope.surrenderDeed = reader.result;
	 				 }, 100);
	 				break;
	 			case 'declaration':
	 				$timeout(function() {
	 					$scope.declaration = reader.result;
	 				 }, 100);
	 				break;
	 		}
	 	};
	 	reader.onerror = function (error) {
//	 	 console.log('Error: ', error);
	 	};
	};
	$scope.getReport = function ($files,type) {
		var validFormats = ["PDF","pdf"];
		var checkFile = false;
	     angular.forEach($files, function (value, key) {
	    	 var ext = value.name.split(".").pop();
	    	 if(validFormats.indexOf(ext) == -1){
		    		$('#errorImgModal').modal('show');
		    		commonService.clearUploadFile();
		    		$scope.clearImageUploadField(type);
		         } else{
		        	 $scope.getBase64(value, type);
		        	 checkFile = true;
		         }
	     });
	     if(checkFile == true){
	    	 $scope.clearImageUploadField(type);   
	     }
	     if($files.length == 0){
	    	 $scope.clearImageUploadField(type);   
	     };
     
	};
	$("#legallyFreeDateOfBirth").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear:true,
	    yearRange: "1950:2015",
		onSelect: function(date) {
		    $scope.legallyFormInfo.dateOfBirth = date;
		  }	
	});
	$("#legallyFreeOrderDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
		    $scope.legallyFormInfo.orderDate = date;
		  }	
	});
	$("#legallyFreeDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
		    $scope.legallyFormInfo.legallyFreeDate = date;
		  }	
	});
	
	$scope.validatelegallyForm = function(){
		if($scope.legallyFormInfo.dateOfBirth == undefined || $scope.legallyFormInfo.dateOfBirth == null)
			$('#legallyFreeDateOfBirth').datepicker('show');
		else if($scope.legallyFormInfo.orderDate == undefined || $scope.legallyFormInfo.orderDate == null)
			$('#legallyFreeOrderDate').datepicker('show');
		else if($scope.legallyFormInfo.legallyFreeDate == undefined || $scope.legallyFormInfo.legallyFreeDate == null)
			$('#legallyFreeDate').datepicker('show');
		else{
			$('#confirmationlegallyfree').modal('show');

		}
	};
	
	$scope.printFitData ={};
	$scope.cciSelectChange=function(cciId){
		  $scope.filteredAddress=($filter('filter')($scope.cciList, {cciId: cciId})); 
		  $scope.cciAddress=$scope.filteredAddress[0].address;
		};
	$scope.saveData = function(){
		if($scope.formInfo.id==null){
			$scope.formInfo={};
			$rootScope.$broadcast("caseSummary", $scope.formInfo);
		}
		if($scope.sponsorshipData.id==null){
			$scope.sponsorshipData={};
			$rootScope.$broadcast("sponsorshipData", $scope.sponsorshipData);
		}
		if($scope.afterCareData.id==null){
			$scope.afterCareData={};
			$rootScope.$broadcast("afterCareData", $scope.afterCareData);
		}
		if($scope.restorationselectedChild.id==null){
			$scope.restorationselectedChild={};
			$rootScope.$broadcast("restorationData", $scope.restorationselectedChild);
		}
		
		$scope.legallyFormInfo.inquiryReport = $scope.inquiryReport == "" || $scope.inquiryReport == null ? null : $scope.inquiryReport;
		$scope.legallyFormInfo.surrenderDeed = $scope.surrenderDeed == "" || $scope.surrenderDeed == null ? null : $scope.surrenderDeed;
		$scope.legallyFormInfo.declaration = $scope.declaration =="" || $scope.declaration == null ? null : $scope.declaration;
		$scope.legallyFormInfo.childId=$scope.selectedChild.childId;
		
		$scope.printFitData=$scope.legallyFormInfo;
		$(".loader").css("display", "block");
		$http.post('saveLegallyFreeData',$scope.legallyFormInfo).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$('#legallyfree').modal('show');
		}, function(error){
			console.log(error);
		});
	};
	$scope.printLegallyFreeData = function(){
		$scope.printFitData = $scope.legallyFormInfo;
		$scope.printFitData.childName = $scope.selectedChild.childName;
		$scope.printFitData.cwcName = $scope.selectedChild.cwc.name;
		$scope.printFitData.childId = $scope.selectedChild.childId;
		$scope.selectedChild.finalOrderFilled=1;
		$rootScope.$broadcast("selectedChild1", $scope.selectedChild);
		$scope.filteredAge=($filter('filter')($scope.cciList, {cciId: $scope.legallyFormInfo.saaOrCciId}));
		$scope.printFitData.saaOrCciName=$scope.filteredAge[0].name;
		$scope.printFitData.cciAddress=$scope.filteredAge[0].address;
		$scope.printFitData.programType = $scope.selectedChild.programType;
		
		var serverURL = 'downloadPDFDataForLegallyFreeForAdoption?type='+$scope.lang;
		commonService.downloadFile(serverURL, $scope.printFitData);
		$scope.legallyFreeDisable = true;
			
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
myAppConstructor.
directive('thirtyCharactersValidation', function () {

  return {
      restrict: 'A',
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					val = '';
				}
				
				var clean = val;
				if(!angular.isUndefined(clean)) {
	            	 var num=0;
	            	 if(clean.length>30 ){
	            		 num =clean.slice(0,30);
	            		 clean= num;
	            	 }
	            	 clean = clean.replace(/[^a-zA-z .,]/g, '');
	            	clean = clean.replace('^', '');
	            	clean = clean.replace(/\\/g, '');
	            	clean = clean.replace('[', '');
	            	clean = clean.replace(']', '');
	            	
	            	
	             }
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
		}
  };
});


myAppConstructor.
directive('twoHundredCharactersValidation', function () {

  return {
      restrict: 'A',
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					val = '';
				}
				
				var clean = val;
				if(!angular.isUndefined(clean)) {
	            	 var num=0;
	            	 if(clean.length>200 ){
	            		 num =clean.slice(0,200);
	            		 clean= num;
	            	 }
	            	 clean = clean.replace(/[^a-zA-z .,]/g, '');
	            	clean = clean.replace('^', '');
	            	clean = clean.replace(/\\/g, '');
	            	clean = clean.replace('[', '');
	            	clean = clean.replace(']', '');
	            	
	            	
	             }
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
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
				if (angular.isUndefined(val)) {
					val = '';
				}
				if(val!=null)
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
