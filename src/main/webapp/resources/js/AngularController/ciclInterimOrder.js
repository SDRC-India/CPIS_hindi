/**
 * @author - NaseemAkhtar(naseem@sdrc.co.in)
 */

myAppConstructor.controller('ciclInterimDecisionController',
			function($scope, $location, $http, commonService, $rootScope, $window, $filter, $timeout,gettextCatalog){
	
	
	/*
	 * Global code for all the cicl Interim order
	 */
	$scope.lang='en';
	
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
	$scope.childId = $('#childId').val();
	$scope.interimOrdersList = [];
	$scope.viewSupervision = false;
	$scope.forJJB=true;
	
	$scope.designationId=document.getElementById('designationId').value;
	//disabling for CCI view
	if($scope.designationId!=8){
		$scope.forJJB=false;
		$scope.viewSupervision=true;
		$scope.viewCCIpending=true;
	}
		

	$scope.min 	= 	["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","2","23","24","25","26","27",
	           	  	 "28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55",
	           	  	 "56","57","58","59"];
	$scope.hour =	["01","02","03","04","05","06","07","08","09","10","11","12"];
	$scope.ampm	=	["AM", "PM"];
	$scope.getGridMenuItemsSuccessful = false;
	$scope.getAllCiclInterimRecordsSuccessful = false;
	$scope.getAllCiclCaseMonitoringDataSuccessful = false;
	$scope.getTypeDetailsSuccessful = false;
	$scope.getChildByIdSuccessful = false;
	$scope.getCciListSuccessful = false;
	$scope.getSocialBackgroundReportSuccessful = false;

	$(".loader").css("display", "block");
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.getAllCiclInterimRecordsSuccessful 
				&& $scope.getAllCiclCaseMonitoringDataSuccessful && $scope.getTypeDetailsSuccessful
				&& $scope.getChildByIdSuccessful && $scope.getCciListSuccessful
				&& $scope.getSocialBackgroundReportSuccessful){
			$(".loader").css("display", "none");
		}
	};
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
	});
	
	 $scope.redirectForm=function(url){
			if(url == "child_registration"){
				commonService.redirectForm(url, $scope.childId);
			}else{
				if($scope.childId==null || $scope.childId == undefined 
					|| $scope.childId == ""){
					$('#noChildSelected').modal('show');
				}else if($scope.childMstData.programType==1 && url=="socialInvestigation"){
					url="ciclSocialInvestigationReport";
					commonService.redirectForm(url, $scope.childMstData.childId);
				}else{
					commonService.redirectForm(url, $scope.childId);
				}
			}
		};
	/*
	 * 1. The following code is for opening new interim form(3 & 4) & case monitoring form.
	 */
	$scope.openInterimForm = function(formNo){
		if(formNo == 3){
//			$scope.supervisionData = {
//					dateOfOrder : $filter('date')(new Date(), 'yyyy-MM-dd')
//			};
			$scope.supervisionData = {};
			$scope.viewSupervision = false;
			$("#one").show();
			$("#two").hide();
			$("#four").hide();
		}else if(formNo == 4){
//			$scope.ccInstitution = {
//					dateOfOrder : $filter('date')(new Date(), 'yyyy-MM-dd')
//			};
			$scope.ccInstitution = {};
			$scope.viewCCIpending = false;
			$("#one").hide();
			$("#two").show();
			$("#four").hide();
		}
	};
	
	$scope.openCaseMonitoring = function(){
		$("#one").hide();
		$("#two").hide();
		$("#four").show();
		
		$scope.ciclCaseMonitoring = {
				date : $filter('date')(new Date(), 'yyyy-MM-dd'),
				createdDate : $filter('date')(new Date(), 'yyyy-MM-dd'),
				district : $scope.childMstData.district.name
		};
		
		if($scope.childMstData.caseNum != null){
			$scope.ciclCaseMonitoring.caseNo =  $scope.childMstData.caseNum;
			$scope.freezeCaseNo = true;
		}
		$scope.viewCaseMonitoring = false;
	};
	$scope.resetText = function(model, key,errorId){
    	if(model[key] == 0){
    		model[key] = undefined;
//        	document.getElementById(errorId).innerHTML = "Month can not be 0";
    	}
    	
    };
    $scope.resetInput = function(model,key,errorId){
     	if(model[key] == 0){
     		model[key] = undefined;
         	document.getElementById(errorId).innerHTML = "Age cannot blank";
     	}
     	
     };
	
	/*
	 * 1 ends here.
	 * 2. The following code is for showing previously filled form.
	 */
	
	$scope.showForm = function(item){
		if(item.formNo == 3){
			$scope.viewSupervision = true;
			$scope.supervisionData = item;
			$("#one").show();
			$("#two").hide();
			$("#four").hide();
			
		}else if(item.formNo == 4){
			$scope.viewCCIpending = true;
			$scope.ccInstitution = item;
			$("#one").hide();
			$("#two").show();
			$("#four").hide();
		}
		console.log(item);
	};
	
	$scope.showCMForm = function(item){
		var timeConverter = item.aprehendedTime == null ? '' : item.aprehendedTime.split(":");
		var hour = parseInt(timeConverter[0]);
		var period = "AM";
		
		if(hour > 12){
			hour -= 12;
			period = "PM";
		}
		else if(hour == 0){
			hour = 12;
			period = "PM";
		}
		if(item.aprehendedTime != null)
			$scope.apprehendedTime = hour+":"+timeConverter[1]+":"+period;
		
		$scope.viewCaseMonitoring = true;
		$scope.ciclCaseMonitoring = item;
		$scope.lang=='hi_IN' && $scope.ciclCaseMonitoring.determinationBy!=null ?$scope.ciclCaseMonitoring.determinationBy.name=$scope.ciclCaseMonitoring.determinationBy.typeNameHindi:$scope.ciclCaseMonitoring.determinationBy!=null?$scope.ciclCaseMonitoring.determinationBy.name:null;
		$scope.lang=='hi_IN'?$scope.ciclCaseMonitoring.cocByWhom.name=$scope.ciclCaseMonitoring.cocByWhom.typeNameHindi:$scope.ciclCaseMonitoring.cocByWhom.name;
		$scope.evidenceReliedDocument = item.evidenceReliedDocument;
		$("#one").hide();
		$("#two").hide();
		$("#four").show();
	};
	
	/* 2 ends here
	 * 
	 * for downloading medical report
	 */
	
	$scope.downloadPdf = function(pdf,name){
		   var date = new Date();
	   		download(pdf , name + " "
				+ date.getFullYear() + (date.getMonth() + 1)
				+ date.getHours() + date.getMinutes()
				+ date.getSeconds() + ".pdf", "application/pdf");
	};
	
	/*
	 *	medical report ends here
	 * 3. The following http requests are for fetching all the previously filled interim forms,
	 * case monitoring forms and also the registration details of the child.
	 */
	
	$http.get("getAllCiclInterimRecords?childId="+$scope.childId).
	then(function(result){
		$scope.getAllCiclInterimRecordsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.interimOrdersList = result.data;
		
		if($scope.designationId!=8 && $scope.interimOrdersList.length!=0){
			$scope.showForm($scope.interimOrdersList[0]);
		}
		console.log($scope.interimOrdersList);
	}, function(error){
		console.log(error);
	});
	
	$http.get('getAllCiclCaseMonitoringData?childId='+$scope.childId).
	then(function(result){
		$scope.getAllCiclCaseMonitoringDataSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.caseMonitoringList = result.data;
	}, function(error){
		console.log(error);
	});
	
	$http.get("getTypeDetails").
	then(function(result){
		$scope.getTypeDetailsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.supervisionCareUnderWhomList = result.data.supervisionCareUnderWhom;
		$scope.natureOfCrimeList = result.data.natureOfCrime;
		$scope.determinationByList = result.data.determinationBy;
		$scope.childInCustodyOfList = result.data.childInCustodyOf;
	}, function(error){
		console.log(error);
	});
	
	$http.get("getChildById?selectedChildId="+$scope.childId).
	then(function(result){
		$scope.childMstData = result.data;
		$scope.getChildByIdSuccessful = true;
		$scope.checkAllDataSuccessful();
		var prodTimeMstData = $scope.childMstData.timeOfFirstProduction.split(":");
		var hr = parseInt(prodTimeMstData[0]);
		var min = prodTimeMstData[1];
		var period = "AM";
		
		$("#one").show();
		
		if(hr > 12){
			hr -= 12;
			period = "PM";
		}
		else if(hr == 0)
			hr = 12;
		
		$scope.firstProductionTime = hr+" : "+min+" : "+period;
		
//		$http.get("getCciList?areaId="+ $scope.childMstData.childDistrict).
		$http.get("getAllCciList").
		then(function(result){
			$scope.getCciListSuccessful = true;
			$scope.checkAllDataSuccessful();
			$scope.cciList = result.data;
		},function(error){
			console.log(error);
		});
		
		console.log(result);
	},function(error){
		console.log(error);
	});
	
	$http.get("getSocialBackgroundReport?childId="+$scope.childId).
	then(function(result){
		$scope.getSocialBackgroundReportSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.childBgData = result.data;
		console.log($scope.childBgData);
	},function(error){
		console.log(error);
	});
	
	/*
	 * 3 ends here.
	 * 4. For redirecting to home page
	 */
	
	$scope.reDirect = function(){
		$window.location.reload();
//		$window.location.href = '/CPIS/ccts';
	};
		
	/*
	 * 4 ends here
	 * 5. Supervision code starts here.
	 */
	
	$scope.validateSupervisionForm = function(){
			$scope.supervisionData.childId = $scope.childId;
			$scope.supervisionData.firNumber = $scope.childBgData.firNumber;
			$scope.supervisionData.ddNumber = $scope.childBgData.ddNumber;
			$scope.supervisionData.entryDate = $scope.childBgData.entryDate;
			$scope.supervisionData.policeStation = $scope.childBgData.policeStation;
			$scope.supervisionData.childName = $scope.childBgData.childName;
			
			console.log($scope.supervisionData);
			if($scope.supervisionData.dateOfOrder == undefined){
//				$('#dateOfOrder').datepicker('show');
				$('#dateOfOrder').focus();
			}
			else
				$('#supervisionConfirmationModal').modal('show');
	};
	
	$scope.saveSuperVisionData = function(){
		$scope.supervisionData.supervisionAuthorityAddress=$scope.supervisionData.supervisionAuthorityAddress.replace(/\n/g, " ");
		$(".loader").css("display", "block");
		$http.post("saveSupervisionOrder",$scope.supervisionData).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$('#supervisionSuccessModal').modal('show');
			$scope.supervisionData.formNo=3;
			$scope.interimOrdersList.push($scope.supervisionData);
			console.log(result);
		}, function(error){
			console.log(error);
		});
	};
	
	$scope.printSupervisionData = function(){
		
//		$scope.interimOrdersList.push($scope.supervisionData);
		
		if($scope.viewSupervision != true)
			$scope.viewSupervision = true;
		
		var serverURL = 'downloadPDFDataForCICLSupervisionOrder?type='+$scope.lang;
		$scope.supervisionPrintObject = $scope.supervisionData;
		$scope.supervisionPrintObject.firNumber = $scope.childBgData.firNumber;
		$scope.supervisionPrintObject.ddNumber = $scope.childBgData.ddNumber;
		$scope.supervisionPrintObject.entryDate = $scope.childBgData.entryDate;
		$scope.supervisionPrintObject.policeStation = $scope.childBgData.policeStation;
		$scope.supervisionPrintObject.childName = $scope.childBgData.childName;
		$scope.supervisionPrintObject.childId = $scope.childBgData.childId;
		$scope.supervisionPrintObject.programType = $scope.childBgData.programType;
		$scope.supervisionPrintObject.childUnderCareOfWhom.name=commonService.getNameBySelectedLanguageType($scope.supervisionPrintObject.childUnderCareOfWhom.name,$scope.supervisionCareUnderWhomList,$scope.lang);
		
		commonService.downloadFile(serverURL, $scope.supervisionPrintObject);
	};
	
	
	/*
	 * 5 ends here.
	 * 	6. Child care Institution code starts from here.
	 */
	
	$scope.ciclCCI = function(cci){
		return cci.typeId===13 || cci.typeId===8 || cci.typeId===9 || cci.typeId===10 || cci.typeId===11 || cci.typeId===12;
};

$("#dateOfOrder").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true ,
	onSelect: function(date) {
		$scope.supervisionData.dateOfOrder = date;
	}	
});


$("#dateOfPlacemennt").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true ,
	onSelect: function(date) {
		$scope.ccInstitution.dateOfPlacement = date;
	}	
});

$("#dateOfHearing").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true ,
	onSelect: function(date) {
		$scope.ccInstitution.nextDateOfHearing = date;
	}	
});

$("#cciDateOfOrder").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true ,
	onSelect: function(date) {
		$scope.ccInstitution.dateOfOrder = date;
	}	
});
$("#dateChildApprehended").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true ,
	onSelect: function(date) {
		$scope.ciclCaseMonitoring.aprehendedDate = date;
	}	
});


	
	$scope.validateChildCareForm = function(){
		$scope.ccInstitution.childId = $scope.childId;
		$scope.ccInstitution.age = $scope.childBgData.age;
		$scope.ccInstitution.childName = $scope.childBgData.childName;
		$scope.ccInstitution.firNumber = $scope.childBgData.firNumber;
		$scope.ccInstitution.ddNumber = $scope.childBgData.ddNumber;
		$scope.ccInstitution.policeStation = $scope.childBgData.policeStation;
		
		console.log($scope.ccInstitution);
		if($scope.ccInstitution.dateOfPlacement == undefined){
//			$('#dateOfPlacemennt').datepicker("show");
			$("#dateOfPlacemennt").focus();
		}
		else if($scope.ccInstitution.nextDateOfHearing == undefined){
//			$('#dateOfHearing').datepicker("show");
			$('#dateOfHearing').focus();
		}
		else if($scope.ccInstitution.dateOfOrder == undefined){
//			$('#cciDateOfOrder').datepicker('show');
			$('#cciDateOfOrder').focus();
		}
		else
			$('#cciConfirmationModal').modal('show');
	};
	
	$scope.saveChildCareForm = function(){
		$scope.ccInstitution.addressOfChild = $scope.ccInstitution.addressOfChild.replace(/\n/g, " ");
		$(".loader").css("display", "block");
		$http.post("saveChildCareInstitutionPendingInquiry",$scope.ccInstitution).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$('#cciSuccessModal').modal('show');
			$scope.ccInstitution.formNo=4;
			$scope.interimOrdersList.push($scope.ccInstitution);
			console.log(result);
		}, function(error){
			console.log(error);
		});
	};
	
	$scope.printCciSubmitData = function(){
		
//		$scope.interimOrdersList.push($scope.ccInstitution);
		
		if($scope.viewCCIpending == false)
			$scope.viewCCIpending = true;
		
		var serverURL = 'downloadPDFDataForCICLChildCareIPI?type='+$scope.lang;
		$scope.ccInstitutionPrintObject = $scope.ccInstitution;
		
		$scope.ccInstitutionPrintObject.childId = $scope.childBgData.childId;
		$scope.ccInstitutionPrintObject.age = $scope.childBgData.age;
		$scope.ccInstitutionPrintObject.childName = $scope.childBgData.childName;
		$scope.ccInstitutionPrintObject.firNumber = $scope.childBgData.firNumber;
		$scope.ccInstitutionPrintObject.ddNumber = $scope.childBgData.ddNumber;
		$scope.ccInstitutionPrintObject.policeStation = $scope.childBgData.policeStation;
		$scope.ccInstitutionPrintObject.programType = $scope.childBgData.programType;
		$scope.ccInstitutionPrintObject.cciObject.typeNameHindi=$scope.ccInstitutionPrintObject.cciObject.name;
		
		commonService.downloadFile(serverURL, $scope.ccInstitutionPrintObject);
	};
	
	
	/*
	 * 6 ends here
	 *  7. Case monitoring code starts here.
	 */
	$scope.blur = function (value,name) {
 	    if(name=="cocByWhomName"){
     	   $scope.ciclCaseMonitoring.cocByWhomName=value.trim();  
 	    }
	}
	 $scope.resetphnNo = function(model,id){
	    	if(model == undefined){
	    		$("#"+id).val('');
	    	}
	    }
	 
	
	$scope.validateFields = function(name, errorId){
		if(errorId == "ageondateerror" && name > 18){
			document.getElementById(errorId).innerHTML = "Age cannot be more than 18 years";
		        document.ciclCase.ageondate.focus();
		        $scope.ciclCaseMonitoring.childAgeOnDateOfOffence = null;
		        return false;
		}
		else if(errorId == "pgContactError" && (name == undefined || name == "")){
			 document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
		        document.ciclCase.pgcontact.focus();
		        return false;
		}
		else if(errorId == "pgContactError" && name != undefined && name != "")
			document.getElementById(errorId).innerHTML = "";
		else if(errorId == "ageondateerror" && name < 18)
			document.getElementById(errorId).innerHTML = "";
	};
	//To claer the error message on valid date selection
	$("#cocToDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.ciclCaseMonitoring.cocToDate = date;
			if($scope.ciclCaseMonitoring.cocFromDate != undefined && $scope.ciclCaseMonitoring.cocToDate > $scope.ciclCaseMonitoring.cocFromDate)
				document.getElementById('ciclToerror').innerHTML = "";
		}	
	});
	$("#prosecutionEvidenceTo").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.ciclCaseMonitoring.prosecutionEvidienceToDate = date;
			if($scope.ciclCaseMonitoring.prosecutionEvidienceFromDate != undefined && $scope.ciclCaseMonitoring.prosecutionEvidienceToDate > $scope.ciclCaseMonitoring.prosecutionEvidienceFromDate)
				document.getElementById('prosecutionEvidenceToerror').innerHTML = "";
		}	
	});
	
	
	$scope.validateCiclCaseMonitoringForm = function(){
		$scope.apprehendedTime = $scope.hr1+":"+$scope.min1+":"+$scope.period1;
//		$scope.firstProductionTime = $scope.hr2+":"+$scope.min2+":"+$scope.period2;
		
		var hour = parseInt($scope.hr1);
//		var hour1 = parseInt($scope.hr2);
		if($scope.period1 == "PM")
			hour += 12;
		$scope.ciclCaseMonitoring.aprehendedTime = hour+":"+$scope.min1+":00";
		
//		if($scope.period2 == "PM")
//			hour1 += 12;
		$scope.ciclCaseMonitoring.firstProductionDate = $scope.childMstData.dateOfFirstProduction;
		$scope.ciclCaseMonitoring.firstProductionTime = $scope.childMstData.timeOfFirstProduction;
		$scope.ciclCaseMonitoring.parentName="";
		$scope.ciclCaseMonitoring.childId = $scope.childId;
		
		if($scope.ciclCaseMonitoring.aprehendedDate == undefined){
//			$('#dateChildApprehended').datepicker('show');
			$('#dateChildApprehended').focus();
		}
		else if($scope.ciclCaseMonitoring.cocToDate < $scope.ciclCaseMonitoring.cocFromDate){
			document.getElementById('ciclToerror').innerHTML = "<b>TO</b> date must not be smaller than <b>FROM</b> date";
//			$('#cocToDate').datepicker('show');
			$('#cocToDate').focus();
		}else if($scope.ciclCaseMonitoring.prosecutionEvidienceToDate < $scope.ciclCaseMonitoring.prosecutionEvidienceFromDate){
			document.getElementById('prosecutionEvidenceToerror').innerHTML = "<b>TO</b> date must not be smaller than <b>FROM</b> date";
//			$('#prosecutionEvidenceTo').datepicker('show');
			$('#prosecutionEvidenceTo').focus();
		}
//		else if($scope.ciclCaseMonitoring.cocBailDate == undefined)
//			$('#dateOfGrantOfBail').datepicker('show');
		else if($scope.ciclCaseMonitoring.cocFromDate == undefined){
//			$('#cocFromDate').datepicker('show');
			$('#cocFromDate').focus();
		}
		else if($scope.ciclCaseMonitoring.cocToDate == undefined){
//			$('#cocToDate').datepicker('show');
			$('#cocToDate').focus();
		}
		else{
			$('#ciclCaseMonitoringModal').modal('show');
		}
	};
	
	$scope.saveCiclCaseMonitoringData = function(){
		$(".loader").css("display", "block");
		$http.post('saveCiclCaseMonitoring', $scope.ciclCaseMonitoring)
		.then(function(result){
			console.log(result);
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$('#ciclCaseMonitoringSuccessModal').modal('show');
			$scope.ciclCaseMonitoring.formNo=11;
			$scope.caseMonitoringList.push($scope.ciclCaseMonitoring);
			$scope.hr1 = null;
			$scope.min1 = null;
			$scope.period1 = null;
		}, function(error){
			console.log(error);
		});
	};
	
	$scope.printCaseHistoryData = function(){
		
//		$scope.caseMonitoringList.push($scope.ciclCaseMonitoring);
		if($scope.viewCaseMonitoring == false )
			$scope.viewCaseMonitoring = true;
		
		var serverURL = 'downloadPDFDataForCICLCaseMonitering?type='+$scope.lang;
		$scope.printCMData = $scope.ciclCaseMonitoring;
		$scope.printCMData.gdNumber = $scope.childBgData.gdNumber;
		$scope.printCMData.firNumber = $scope.childBgData.firNumber;
		$scope.printCMData.ddNumber = $scope.childBgData.ddNumber;
		$scope.printCMData.policeStation = $scope.childBgData.policeStation;
		$scope.printCMData.parentName = $scope.childBgData.fatherName;
		$scope.printCMData.childName = $scope.childBgData.childName;
		$scope.printCMData.programType = $scope.childBgData.programType;
		$scope.printCMData.natureOfOffence.name=commonService.getNameBySelectedLanguageType($scope.printCMData.natureOfOffence.name,$scope.natureOfCrimeList,$scope.lang);
		
		commonService.downloadFile(serverURL, $scope.printCMData);
	};
	
	
	/*
	 * For document upload
	 */
	
	$scope.clearField = function(){
		$scope.ciclCaseMonitoring.evidenceReliedDocument = null;
	};
	
	$scope.getBase64=function(file) {
	 	var reader = new FileReader();
	 	reader.readAsDataURL(file);
	 	reader.onload = function () {
			$timeout(function() {
				$scope.ciclCaseMonitoring.evidenceReliedDocument = reader.result;
		    }, 100);
 		};
	 	reader.onerror = function (error) {
	 	 console.log('Error: ', error);
	 	};
	};
	
	$scope.getReport = function ($files){
		var validFormats = ['pdf'];
		var checkFile = false;
		
		angular.forEach($files, function (value, key){
			var ext = value.name.split(".").pop();
			if(validFormats.indexOf(ext) == -1){
				$('#errorModal').modal('show');
				commonService.clearUploadFile();
				$scope.clearField();
			}else{
				$scope.getBase64(value);
				checkFile = true;
			}
		});
	     
		if(checkFile == true){
			$scope.clearField();
		}
		if($files.length == 0){
			$scope.clearField();   
		};
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
				if (angular.isUndefined(val)) {
					val = '';
				}
				
				var clean = val.replace(/[^0-9]/g, '');
				if(!angular.isUndefined(clean)) {
	            	 var num=0;
	            	 if(clean.length>10 ){
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
directive('sixtyCharactersValidation', function () {

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
	            	 if(clean.length>60 ){
	            		 num =clean.slice(0,60);
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


//$scope.supervisionData = {
//dateOfOrder : $filter('date')(new Date(), 'yyyy-MM-dd')
//};
//$scope.ccInstitution = {
//dateOfOrder : $filter('date')(new Date(), 'yyyy-MM-dd')
//};
//$scope.ciclCaseMonitoring = {
//	date : $filter('date')(new Date(), 'yyyy-MM-dd'),
//	createdDate : $filter('date')(new Date(), 'yyyy-MM-dd')
//};

//$scope.viewCaseMonitoring = false;
//$scope.viewSupervision = false;
//$scope.viewCCIpending = false;


//$http.get('getSupervisionOrderData?childId='+$scope.childId).
//then(function(result){
//	if(result.data.id != null){
//		$scope.viewSupervision = true;
//		$scope.supervisionData = result.data;
//	}
//}, function(error){
//	console.log(error);
//});


//$http.get('getChildCareInstitutionPendingInquiry?childId='+$scope.childId).
//then(function(result){
//	if(result.data.id != null){
//		$scope.viewCCIpending = true;
//		$scope.ccInstitution = result.data;
//	}
//}, function(error){
//	console.log(error);
//});

//$http.get("getCciList?areaId="+$scope.selectedChild.district.id).
//then(function(result){
//	$scope.cciList = result.data;
//	console.log(result);
//},function(error){
//	console.log(error);
//});