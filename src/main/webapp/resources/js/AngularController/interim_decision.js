
//var app = angular.module('interimDecision',[]);


myAppConstructor.controller('InterimDecisionController', function($scope, $location, $http, commonService, $rootScope,gettextCatalog){
	$scope.lang = 'en';
	$http({
        method : "GET",
        url : "getLang"
    }).then(function mySucces(response) {
    	$scope.lang=response.data;
    	$scope.changeLanguage($scope.lang);
});
	
	$scope.changeLanguage = function(lang){
		console.log(lang);
		$scope.lang = lang;
		$http({
	        method : "GET",
	        url : "setLangString?language="+$scope.lang
	    }).then(function mySucces(response) {
	    	
	});
		gettextCatalog.setCurrentLanguage(lang);
	};
	$scope.selectedChildId=$('#modelValue').val();
	console.log($scope.selectedChildId);
	$scope.selectedChild = {};
	$scope.getGridMenuItemsSuccessful = false;
	$scope.findAllInterimOrdersSuccessful = false;
	$scope.findCaseMonitoringSuccessful = false;
	$scope.getChildByIdSuccessful = false;
	$(".loader").css("display", "block");
	$scope.forCWC=true;
	
	$scope.designationId=document.getElementById('designationId').value;
	//disabling for CCI view
	if($scope.designationId!=7)
		$scope.forCWC=false;
	
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.findAllInterimOrdersSuccessful 
				&& $scope.findCaseMonitoringSuccessful && $scope.getChildByIdSuccessful){
			$(".loader").css("display", "none");
		}
	};
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
		console.log($scope.menuList);
	});
	
	$scope.showForm = function(formData){
		if(formData.decisionType=="fitInstitution"){
			$('#one').hide();
			$('#two').hide();
			$('#four').hide();
			$('#three').show();
		}
		else if(formData.decisionType=="fitPerson"){
			$('#three').hide();
			$('#four').hide();
			$('#two').hide();
			$('#one').show();
		}
		else if(formData.decisionType=="fosterCare"){
			$('#one').hide();
			$('#two').show();
			$('#four').hide();
			$('#three').hide();
		}
		else{
			$('#one').hide();
			$('#two').hide();
			$('#four').show();
			$('#three').hide();
		}
		$rootScope.$broadcast("formData",formData);
	};
	
	$scope.interimOrders=[];
	$http.get("findAllInterimOrders?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.findAllInterimOrdersSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.interimOrders = result.data;
		
		if($scope.designationId!=7 && $scope.interimOrders.length!=0){
			$scope.showForm($scope.interimOrders[0]);
		}
		console.log(result);
		$rootScope.$broadcast("interimOrders", $scope.interimOrders);
	},function(error){
		console.log(error);
	});
	
	
	$scope.caseMonitering;
	$http.get("findCaseMonitoring?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.findCaseMonitoringSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.caseMonitering = result.data;
		console.log(result);
		$rootScope.$broadcast("caseMonitering", $scope.caseMonitering);
	},function(error){
		console.log(error);
	});
	

	
	$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
	then(function(result){
		$scope.getChildByIdSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.selectedChild = result.data;
		console.log(result);
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.finalOrderFilled=false;
		}
		else
			$scope.finalOrderFilled=true;
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
	},function(error){
		console.log(error);
	});
	
	
	
	$scope.redirectForm=function(url){
		if(url == "child_registration"){
			commonService.redirectForm(url, $scope.selectedChildId);
		}else{
			if($scope.selectedChildId==null || $scope.selectedChildId == undefined 
				|| $scope.selectedChildId == ""){
				$('#noChildSelected').modal('show');
			}else if($scope.selectedChild.programType==1 && url=="socialInvestigation"){
				url="ciclSocialInvestigationReport";
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}else{
				commonService.redirectForm(url, $scope.selectedChildId);
			}
		}
	};
	
	$scope.reDirect = function(){
		$window.location.reload();
	};
	
	$rootScope.$on("cmData", function(event, data){
		$scope.caseMonitering.push(data);
	});
	
	$rootScope.$on("interimData", function(event, data){
		$scope.interimOrders.push(data);
	});
	

});
/*
 * 
 * 
*/
myAppConstructor.controller('CaseMonitoringController', function($scope, $http, $rootScope, commonService, $timeout,$window){
	
	$scope.min 	= 	["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","2","23","24","25","26","27",
	           	  	 "28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55",
	           	  	 "56","57","58","59"];
	$scope.hour =	["01","02","03","04","05","06","07","08","09","10","11","12"];
	$scope.ampm	=	["AM", "PM"];
	$scope.designationId=document.getElementById('designationId').value;
	//disabling for CCI view
	if($scope.designationId!=7)
		$scope.caseMonitoringDisable=true;
	
	$scope.formInfo = {};
	$scope.prefetchData = {};
	$scope.caseMonitoringBtn=true;
	$rootScope.$on("formData", function(event, data){
		$scope.formInfo = data;
		
		var timeString = "";
		if($scope.formInfo.childProducedBeforeCommitteeTime != null){
			timeString = $scope.formInfo.childProducedBeforeCommitteeTime;
			var timeArr = timeString.split(":");
			$scope.minute = timeArr[1];
			if(timeArr[0] > 12){
				if(timeArr[0]>9)
					$scope.hr = "0"+(timeArr[0]-12).toString();
				else
					$scope.hr = (timeArr[0]-12).toString();
				$scope.ap = "PM";
			}else{
				$scope.hr = timeArr[0];
				$scope.ap = "AM";
			}
		}
		
		$scope.caseMonitoringDisable=true;
		if($scope.formInfo.id==undefined){
			$scope.caseMonitoringBtn=false;
		}
	});
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
		if($scope.prefetchData.timeOfFirstProduction != null || $scope.prefetchData.timeOfFirstProduction != undefined || $scope.prefetchData.timeOfFirstProduction.trim() != ""){
			$scope.prefetchData.timeOfFirstProduction = commonService.timeConverter($scope.prefetchData.timeOfFirstProduction);
		}
		console.log($scope.prefetchData);
	});
		
	$scope.validatePgContact = function(name, errorId){
		if(name.length != 10) {
		    document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
		    $scope.formInfo.pgContactNo="";
		    $timeout(function(){
		    	document.getElementById(errorId).innerHTML = "";
		    },1000);
		}
	};
	
	$scope.validateFields = function(name, errorId){
		if(errorId =='pgContactError' && name.length != 10 && name != ""){
			    document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
			    document.getElementById('pgcontact').focus();
			    return false;
		}
		else if(errorId=='ageondateerror' && name == undefined){
			document.getElementById(errorId).innerHTML = "Please enter a valid age";
			$scope.formInfo.ageondate = null;
	 	}
		else if(errorId=='ageondateerror' && name>18){
			document.getElementById(errorId).innerHTML = "Child age should not be greater than 18";
			$scope.formInfo.ageondate = null;
	 	}
		else if(errorId == "ageondateerror" && name == 0){
			   document.getElementById(errorId).innerHTML = "Age cannot <b>0</b>";
			   document.getElementById('ageondate').focus();
			   $scope.formInfo.ageondate = null;
//	           return false;
	   }else{
			document.getElementById(errorId).innerHTML = "";
	        return true;
	   }
	};
	
	$scope.resetInput = function(model, key,errorId){
    	if(model[key] == null){
    		model[key] = undefined;
        	document.getElementById(errorId).innerHTML = "Age cannot blank";
    	}
    	
    };
	$scope.resetText = function(model, key,errorId){
    	if(model[key] == 0){
    		model[key] = undefined;
//        	document.getElementById(errorId).innerHTML = "Month can not be 0";
    	}
    	
    };
	
	$("#medicalExaminationDatePicker").datepicker({dateFormat: "yy-mm-dd",minDate: 1, changeYear: true ,
		onSelect: function(date) {
			$scope.formInfo.dateOfMedicalExamination = date;
		}	
	});
	$("#date").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.date = date;
		}	
	});
	$("#dispositionalS").datepicker({dateFormat: "yy-mm-dd", changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.scheduledDateOfFinalOrder = date;
		}	
	});
	$("#dispositionalA").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.actualDateOfFinalOrder = date;
		}	
	});
	$("#postdispositionalS").datepicker({dateFormat: "yy-mm-dd", changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.scheduledDateOfPostDispositionalReview = date;
		}	
	});
	$("#postdispositionalA").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.actualDateOfPostDispositionalReview = date;
		}	
	});
	$("#indivisualcareplanS").datepicker({dateFormat: "yy-mm-dd", changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.scheduledDateOfIndividualCarePlan = date;
		}	
	});
	$("#indivisualcareplanA").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.actualDateOfIndividualCarePlan = date;
		}	
	});
	$("#statechildS").datepicker({dateFormat: "yy-mm-dd", changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.scheduledDateOfStatementOfChild = date;
		}	
	});
	$("#statechildA").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.actualDateOfStatementOfChild = date;
		}	
	});
	$("#submissionreportS").datepicker({dateFormat: "yy-mm-dd", changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.scheduledDateOfSubmissionReportOnProvisions = date;
		}	
	});
	$("#submissionreportA").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.actualDateOfSubmissionReportOnProvisions = date;
		}	
	});
	$("#socialivgS").datepicker({dateFormat: "yy-mm-dd", changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.scheduledDateOfSocialInvestigationReport = date;
		}	
	});
	$("#socialivgA").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.actualDateOfSocialInvestigationReport = date;
		}	
	});
	$("#agedeterminS").datepicker({dateFormat: "yy-mm-dd", changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.scheduledDateOfAgeDetermination = date;
		}	
	});
	$("#agedeterminA").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.actualDateOfAgeDetermination = date;
		}	
	});
	$("#from").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.dateChildSentToSupervisionInstitution = date;
		}	
	});
	$("#to").datepicker({dateFormat: "yy-mm-dd",changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.dateTillChildSentToSupervisionInstitution = date;
			if($scope.formInfo.dateChildSentToSupervisionInstitution != undefined && $scope.formInfo.dateChildSentToSupervisionInstitution <= $scope.formInfo.dateTillChildSentToSupervisionInstitution){
				document.getElementById('toerror').innerHTML = "";
			}
		}	
	});
	$("#ageondtrmntion").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.dateOfAgeDetermination = date;
		}	
	});
	$("#childProducedBeforeCommittee").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.childProducedBeforeCommitteeDate = date;
		}	
	});
	
	$("#datefproduct").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.olala = date;
		}	
	});
	
	$scope.resetphnNo = function(model,id){
		   if($("#"+id).val() != "" && $("#"+id).val().length != 10){
			   $("#"+id).focus();
			   return true;
		   }
		   else{
			   return false;
		   }
	   }
	
	$scope.validateForm = function(){
		
		$scope.phnValidation1 = $scope.resetphnNo($scope.formInfo.pgContactNo,'pgcontact');
//		   $scope.phnValidation2 = $scope.resetphnNo($scope.jjbformInfo.socialWorkerOneContactNo,'contactNumbberSocialWorkerdata1');
//		   $scope.phnValidation3 = $scope.resetphnNo($scope.jjbformInfo.socialWorkerTwoContactNo,'contactNumbbersocialWorker2');
		   if($scope.phnValidation1){
			   return false;
		   }
		  
		
		if($scope.formInfo.pgContactNo === undefined) {
//		    document.getElementById(errorId).innerHTML = "Please enter 10 digit phone number";
            document.casemonitor.pgContactNo.focus() ;
//            return false;
        } 
		
		if($scope.formInfo.date === undefined){
			$( "#date" ).datepicker("show");
		}else if($scope.formInfo.dateTillChildSentToSupervisionInstitution < $scope.formInfo.dateChildSentToSupervisionInstitution){
			document.getElementById('toerror').innerHTML = "<b>TO</b> date must not be smaller than <b>FROM</b> date";
			document.getElementById('to').focus();
		}
		else if($scope.formInfo.childProducedBeforeCommitteeDate === undefined){
			$("#childProducedBeforeCommittee").datepicker("show");
		}else if($scope.formInfo.childProducedBeforeCommitteeDate === undefined){
			$("#childProducedBeforeCommittee").datepicker("show");
		}else if($scope.formInfo.dateChildSentToSupervisionInstitution === undefined){
			$("#formerror").datepicker("show");
		}else if($scope.formInfo.dateTillChildSentToSupervisionInstitution === undefined){
			$("#toerror").datepicker("show");
		}else if($scope.formInfo.scheduledDateOfSocialInvestigationReport === undefined){
			$("#socialivgerrorS").datepicker("show");
//		}else if($scope.formInfo.actualDateOfSocialInvestigationReport === undefined){
//			$("#socialivgerrorA").datepicker("show");
		}else if($scope.formInfo.scheduledDateOfStatementOfChild === undefined){
			$("#statechilderrorS").datepicker("show");
//		}else if($scope.formInfo.actualDateOfStatementOfChild === undefined){
//			$("#statechilderrorA").datepicker("show");
		}else if($scope.formInfo.scheduledDateOfFinalOrder === undefined){
			$("#dispositionalerrorS").datepicker("show");
//		}else if($scope.formInfo.actualDateOfFinalOrder === undefined){
//			$("#dispositionalerrorA").datepicker("show");
		}else if($scope.formInfo.scheduledDateOfPostDispositionalReview === undefined){
			$("#postdispositionalerrorS").datepicker("show");
//		}else if($scope.formInfo.actualDateOfPostDispositionalReview === undefined){
//			$("#postdispositionalerrorA").datepicker("show");
		}
		/*else if($scope.formInfo.datefproduct === undefined){
			$( "#datefproduct" ).datepicker("show");
		}*/
		else{
				$scope.formInfo.childId = $scope.prefetchData.childId;
	   			$('#confirmationModalForCaseMonitoring').modal('show');
		}
			
		console.log($scope.formInfo);
	};
	
	$scope.determineChildAge = null;
	$scope.medicalReportsRelied = null;
	$scope.clearImageUploadField = function(type){
		switch(type){
			case 'determineChildAge':
				$timeout(function() {
					$scope.determineChildAge = null;
			    }, 100);
				break;
			case 'medicalReportsRelied':
				$timeout(function() {
					$scope.medicalReportsRelied = null;
				 }, 100);
				break;
		}
	};
	$scope.getBase64=function(file, type) {
	 	var reader = new FileReader();
	 	reader.readAsDataURL(file);
	 	reader.onload = function () {
	 		switch(type){
	 			case 'determineChildAge':
	 				$timeout(function() {
	 					$scope.determineChildAge =reader.result;
	 			    }, 100);
	 				break;
	 			case 'medicalReportsRelied':
	 				$timeout(function() {
	 					$scope.medicalReportsRelied = reader.result;
	 				 }, 100);
	 				break;
	 		}
	 	};
	 	reader.onerror = function (error) {
//	 	 console.log('Error: ', error);
	 	};
	};
	$scope.getReport = function ($files,type) {
		var validFormats = ["pdf"];
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
	
	$scope.saveData = function(){
		
		var str = $scope.hr+":"+$scope.minute+" "+$scope.ap;
	  	if(str.split(" ")[1] == "PM" && $scope.hr!="12")
				  $scope.hr = parseInt($scope.hr)+12;
	  	
	  	$scope.formInfo.childProducedBeforeCommitteeTime = $scope.hr+":"+$scope.minute+":00";
		
	  	
	  	
		$scope.printFitData =$scope.formInfo;
		$scope.formInfo.determineChildAge = $scope.determineChildAge == null || $scope.determineChildAge == "" ? null : $scope.determineChildAge;
		$scope.formInfo.medicalReportsRelied = $scope.medicalReportsRelied == null || $scope.medicalReportsRelied == "" ? null : $scope.medicalReportsRelied;
		$scope.determineChildAge = $scope.determineChildAge == "" ? null : $scope.determineChildAge;
		$scope.medicalReportsRelied = $scope.medicalReportsRelied == "" ? null : $scope.medicalReportsRelied;
		$scope.formInfo.doc=$scope.formInfo.determineChildAge;
		$scope.formInfo.medical=$scope.formInfo.medicalReportsRelied;
		
		$(".loader").css("display", "block");
		$http.post('getCaseMonitoringDetails', $scope.formInfo).
		then(function(result){
			console.log(result);
			$(".loader").css("display", "none");
			$( "#caseMonitoringModal" ).modal("show");
			checkSessionOut(result.data);
//			$scope.formInfo={};
		},function(error){
			console.log(error);
		});
	};
	
	$scope.reDirect = function(){
		$window.location.reload();
	};
	/*------------------------Download----------*/	
	   
	   $scope.downloadImg = function(img,name){
		   var date = new Date();
		   download(img , name + " "
					+ date.getFullYear() + (date.getMonth() + 1)
					+ date.getHours() + date.getMinutes()
					+ date.getSeconds() + ".pdf", "application/pdf");
	   };
	   
	//====================================download pdf============================
	$scope.printCaseMonitoringSubmitData = function(){
		if(!$scope.caseMonitoringBtn){
			$scope.printFitData =$scope.formInfo;
		}
		var serverURL = 'downloadPDFDataReportForCaseMonitoringDetails?type='+$scope.lang;
		$rootScope.$broadcast("cmData", $scope.formInfo);
		$scope.caseMonitoringDisable=true;
		if($scope.formInfo.id==undefined){
			$scope.caseMonitoringBtn=false;
		}
		$scope.printFitData.childId=$scope.prefetchData.childId;
		$scope.printFitData.childName=$scope.prefetchData.childName;
		$scope.printFitData.cwcName = $scope.prefetchData.cwc.name;
		$scope.printFitData.caseNum = $scope.prefetchData.caseNum;
		$scope.printFitData.dateOfFirstProduction = $scope.prefetchData.dateOfFirstProduction;
		$scope.printFitData.timeOfFirstProduction = commonService.timeConverter($scope.prefetchData.timeOfFirstProduction);
		$scope.printFitData.childProducedBeforeCommitteeTimeString = commonService.timeConverter($scope.printFitData.childProducedBeforeCommitteeTime);
		$scope.printFitData.programType = $scope.prefetchData.programType;
		
		delete $scope.printFitData.dateOfReg;
		delete $scope.printFitData.dateWhenCNCP;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
//		$scope.hr=null;
//		$scope.minute=null;
//		$scope.ap=null;
		
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

/**
 * 
*/
myAppConstructor.controller('FosterCareController', function($scope, $http, commonService, $rootScope,$window){
	
	$scope.fosterGroupBtn=true;
	$scope.fosterParentBtn=true;
	$rootScope.$on("selectedChild", function(event, data){
		$scope.selectedChildInfo = data;
	});
	$scope.designationId=document.getElementById('designationId').value;
	//disabling for CCI view
	if($scope.designationId!=7)
		$scope.fosterDisable=true;
	$scope.formInfoTemp={};
	$scope.formInfo={};
	$scope.careForFamily=false;
//	$scope.fosterGroupBtn=true;
//	$scope.fosterParentBtn=true
//	$scope.childId="";
	
//	$rootScope.$on("formData", function(event, data){
//		$scope.formInfo = data;
//	});
	$scope.secondRadioDisable=false;
	$scope.firstRadioDisable=false;
	$rootScope.$on("formData", function(event, data){
		$scope.formInfo = data;
		$scope.careForFamily=false;
		$scope.careGroup=false;
		$scope.fosterDisable=true;
//		$("#groupbutton3id").show();
//		$("#groupbutton2id").hide();
		$scope.secondRadioDisable=true;
		$scope.firstRadioDisable=true;
		if($scope.formInfo.id!=null){
			$scope.fosterGroupBtn=false;
			$scope.fosterParentBtn=false;
		}

		if(data.fosterType=="FP")
		{
			$scope.careForFamily=false;
			$scope.careGroup=true;
			$scope.firstradioChecked=true;
			$scope.secondradioChecked=false;
		
		}
		else{
			$scope.careForFamily=true;
			$scope.careGroup=false;
			$scope.firstradioChecked=false;
			$scope.secondradioChecked=true;
		}
		
	});
	
//	$("#secondRadio").hide();
//	$("#firstRadio").hide();
	
	$scope.saveData = function(fosterType){
		
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!

		var yyyy = today.getFullYear();
		if(dd<10){
		    dd='0'+dd;
		} 
		if(mm<10){
		    mm='0'+mm;
		} 
		$scope.formInfo.dateOfFormFilled = yyyy+'-'+mm+'-'+dd;
		dd+'/'+mm+'/'+yyyy;
		
		if(!(fosterType=="FP")){
			$scope.formInfo.cciAddress = $scope.formInfo.cciAddress.replace(/\n/g, " ");
			$scope.formInfo.fosterCareWorkerAddress = $scope.formInfo.fosterCareWorkerAddress.replace(/\n/g, " ");
		}else{
			$scope.formInfo.childAddress = $scope.formInfo.childAddress.replace(/\n/g, " ");
			$scope.formInfo.fosterParentAddress = $scope.formInfo.fosterParentAddress.replace(/\n/g, " ");
		}	
		$scope.formInfoTemp=$scope.formInfo;
		//here fosterType FP and FG refers as Foster Parent and Foster Group respectively
		$scope.formInfo.fosterType = fosterType;
		$scope.formInfo.childId=$scope.selectedChildInfo.childId;
//		if($scope.formInfo.id==undefined || $scope.formInfo.id==null){
		$(".loader").css("display", "block");
		$http.post('fosterCareDetails',$scope.formInfo).
		then(function(response){
			$(".loader").css("display", "none");
			
			//$scope.childId=response.data;
		    console.log(response);
		    $( "#childFosterCareIdModal" ).modal("show");
		    checkSessionOut(response.data);
//		    $scope.formInfo={};
		},function(error){
		    console.log(error);
		});
		$scope.reDirect = function(){
			$window.location.reload();
		};
//		}
//		else{
//			$http.post('updateFosterCareDetailsData',$scope.formInfo).
//			then(function(response){
//				//$scope.childId=response.data;
//			    console.log(response);
//			    $( "#childFosterCareIdModal" ).modal("show");
//			},function(error){
//			    console.log(error);
//			});
//		}
		$scope.eraseData($scope.formInfo.fosterType);
	};
	
	$scope.openModalForFP = function(){
		if($scope.formInfo.parentName1 != undefined && $scope.formInfo.parentName2 != undefined && $scope.formInfo.fosterParentName1 != undefined
				&& $scope.formInfo.fosterParentAddress != undefined
				&& $scope.formInfo.fosterParentContact != undefined && $scope.formInfo.parentName1 != "" && $scope.formInfo.parentName2 != "" && $scope.formInfo.fosterParentName1 != ""
				&& $scope.formInfo.fosterParentAddress != ""
				&& $scope.formInfo.fosterParentContact != "" && $scope.formInfo.childAddress!=undefined && $scope.formInfo.fosterParentName2 != undefined)
			$('#thankyouModal2').modal('show');
	};
	$scope.openModalForFG = function(){
		if($scope.formInfo.cciName != undefined && $scope.formInfo.cciName != "" && $scope.formInfo.cciAddress != undefined && $scope.formInfo.cciAddress != "" && $scope.formInfo.durationOfStayAtFosterCare != undefined && $scope.formInfo.durationOfStayAtFosterCare != ""
				&& $scope.formInfo.fosterCareWorkerName != undefined && $scope.formInfo.fosterCareWorkerName != "" && $scope.formInfo.fosterCareWorkerAddress != undefined && $scope.formInfo.fosterCareWorkerAddress != "")
			$('#thankyouModal1').modal('show');
	};
	
	$scope.openUpdateModalForFP = function(){
		$('#thankyouModal2').modal('show');
	};
	
	$scope.resetphnNoBlank = function(model,id){
		if(model == undefined){
			$("#"+id).val("");
		}
	}
	
	$scope.validateName = function (name, errorId){
	if($scope.formInfo.durationOfStayAtFosterCare){
		if( name == undefined  ){
			if(errorId=='phoneNoError') {
				document.getElementById(errorId).innerHTML = "Please enter 10 digit phone number";
  	            document.basicdetail.fosterContactNo.focus() ;
  	          return false;
			}else if(errorId=='durationError'){
				document.getElementById(errorId).innerHTML = "Please enter only number";
  	            document.groupDetail.groupTimeDuration.focus() ;
		}
  	            else if(errorId == "noofMonthError"){
 	     		document.getElementById(errorId).innerHTML = "Number of months can not be more than 99";
 	            document.caseHistory.timePeriod.focus() ;
 	            $scope.fitPersonFormData.timePeriod="";
 	            return false;
 	 	    }   
		}
		else{
			document.getElementById(errorId).innerHTML = "";
	        return true;
		}
	}
	};
	
	$scope.resetInput = function(model, key,errorId){
    	if(model[key] == 0){
    		model[key] = undefined;
        	document.getElementById(errorId).innerHTML = "Month can not be blank";
    	}
    };
	
	$scope.eraseData = function(type){
	if(type=="FP")
		{
		$scope.formInfo.cciName=null;
		$scope.formInfo.cciAddress=null;
		$scope.formInfo.durationOfStayAtFosterCare=null;
		$scope.formInfo.fosterCareWorkerName=null;
		$scope.formInfo.fosterCareWorkerAddress=null;
		
			$scope.careForFamily=false;
			$scope.careGroup=true;
			$scope.firstradioChecked=true;
			$scope.secondradioChecked=false;
		}
		else{
			$scope.formInfo.childAddress=null;
			$scope.formInfo.parentName1=null;
			$scope.formInfo.parentName2=null;
			$scope.formInfo.fosterParentName1=null;
			$scope.formInfo.fosterParentName2=null;
			$scope.formInfo.fosterParentAddress=null;
			$scope.formInfo.fosterParentContact=null;
			
			$scope.careForFamily=true;
			$scope.careGroup=false;
			$scope.firstradioChecked=false;
			$scope.secondradioChecked=true;
		}
//		$scope.formInfo={};
	};
	//====================================download pdf============================
	$scope.printOldFosterCareSubmitData = function(){
		var serverURL = 'downloadPDFDataReportForFosterCare?type='+$scope.lang;
		if($scope.fosterDisable){
			$scope.printOldFitData = $scope.formInfo;
			$scope.printOldFitData.childName=$scope.selectedChildInfo.childName;
			$scope.printOldFitData.childAge=$scope.selectedChildInfo.age;
			$scope.printOldFitData.programType = $scope.selectedChildInfo.programType;
		}
		delete $scope.printOldFitData.dateOfReg;
		delete $scope.printOldFitData.dateWhenCNCP;
		
		$scope.firstRadioDisable=true;
		$scope.secondRadioDisable=true;
		commonService.downloadFile(serverURL, $scope.printOldFitData);
	};
	
	$scope.printFosterCareSubmitData = function(){
		
		var serverURL = 'downloadPDFDataReportForFosterCare?type='+$scope.lang;
		$scope.printFitData =$scope.formInfoTemp;
		$scope.printFitData.childName=$scope.selectedChildInfo.childName;
		$scope.printFitData.childAge=$scope.selectedChildInfo.age;
//		$scope.printFitData.childAge=$scope.prefetchData.address;
		$scope.printFitData.programType = $scope.selectedChildInfo.programType;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
		$scope.formInfoTemp.decisionType="fosterCare";
		$rootScope.$broadcast("interimData", $scope.formInfoTemp);
		$scope.secondRadioDisable=true;
		$scope.firstRadioDisable=true;
		$scope.fosterDisable=true;
//		$scope.formInfo={};

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
				
				var clean = val.replace(/[^0-9]/g, '');
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

/*
**
*/

myAppConstructor.controller('FitPersonController', function($scope, $http, $rootScope, commonService,$window){
$scope.fitPersonFormData={};
$scope.masterData={};
$scope.fitPersonBtn=true;

$scope.designationId=document.getElementById('designationId').value;
//disabling for CCI view
if($scope.designationId!=7)
	$scope.fitPersonDisable=true;

	$rootScope.$on("selectedChild", function(event, data){
		$scope.masterData = data;
		if($scope.selectedChild.finalOrderFilled==1){
			$scope.fitPersonDisable = true;
		}
//		$scope.fitPersonFormData.district=$scope.masterData.district;
		$scope.childName=$scope.masterData.childName;
		$scope.caseNum=$scope.masterData.caseNum;
		$scope.fitPersonFormData.childId=$scope.masterData.childId;
		$scope.fitPersonFormData.dateWhenCNCP = $scope.masterData.dateOfCaseRegistered;
		 $scope.fitPersonFormData.dateOfReg = $scope.masterData.dateOfCaseRegistered;
	});
	
	$rootScope.$on("formData", function(event, data){
		$scope.fitPersonFormData = data;
		$scope.fitPersonDisable=true;
		$scope.fitPersonFormData.dateOfReg = $scope.masterData.dateOfCaseRegistered;
		$scope.fitPersonFormData.dateWhenCNCP = $scope.masterData.dateOfCaseRegistered;
		if($scope.fitPersonFormData.id!=null){
			$scope.fitPersonBtn=false;
		}
	});
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	$scope.dateOfFormFilled = yyyy+'-'+mm+'-'+dd;
//	$scope.fitPersonFormData.dateOfFormFilled=$scope.dateOfFormFilled;
//	$("#dateOfFormFilled").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
//		onSelect: function(date) {
//		    $scope.fitPersonFormData.dateOfFormFilled = date;
//		  }	
//	});
	
//	$("#dateOfReg").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
//		onSelect: function(date) {
//		    $scope.fitPersonFormData.dateOfReg = date;
//		  }	
//	});
	
//	$("#dateWhenCNCP").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
//		onSelect: function(date) {
//		    $scope.fitPersonFormData.dateWhenCNCP = date;
//		  }
//	});
	$("#dateOfFormFilled").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.fitPersonFormData.dateOfFormFilled = date;
		  }
	});
	
	$scope.validateFitPerson = function(){
		/*if($scope.fitPersonFormData.dateOfReg === undefined){
			$("#dateOfReg").datepicker("show");
		}else if($scope.fitPersonFormData.dateWhenCNCP === undefined){
			$("#dateWhenCNCP").datepicker("show");
		}else */
		if($scope.fitPersonFormData.timePeriod!=null){
		if($scope.fitPersonFormData.dateOfFormFilled === undefined){
			$("#dateOfFormFilled").datepicker("show");
		}
		else
			$('#fitPersonModal').modal('show');
		}
	};
	
	
	$scope.saveFitPerson = function(){
//		$scope.fitPersonFormData.dateOfFormFilled=$scope.dateOfFormFilled;
//		if($scope.fitPersonFormData.id==undefined || $scope.fitPersonFormData.id==null){
		$scope.printFitData =$scope.fitPersonFormData;
		$scope.fitPersonFormData.childId=$scope.masterData.childId;
		$scope.fitPersonFormData.schoolAddress = $scope.fitPersonFormData.schoolAddress.replace(/\n/g, " ");
		$scope.fitPersonFormData.fitPersonAddress = $scope.fitPersonFormData.fitPersonAddress.replace(/\n/g, " ");
		$(".loader").css("display", "block");
		$http.post('saveFitPersonData', $scope.fitPersonFormData).
		then(function(result){
			console.log(result);
			$(".loader").css("display", "none");
			$( "#childFitPersonIdModal" ).modal("show");
			checkSessionOut(result.data);
		},function(error){
			console.log(error);
		});
		$scope.reDirect = function(){
			$window.location.reload();
		};
//		}
//		else{
//			$http.post('updateFitPersonDataData', $scope.fitPersonFormData).
//			then(function(result){
//				console.log(result);
//				$scope.fitPersonFormData = {};
//				$( "#childFitPersonIdModal" ).modal("show");
//			},function(error){
//				console.log(error);
//			});
//		}
	};
	$scope.validateFields = function(name, errorId){
//		if(name === undefined){
			if(errorId =='pgContactError'  && name>9999999999) {
			    document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
			    $scope.formInfo.pgContactNo="";
//	        } 
		}else if(errorId=='noofMonthError' && name == 0){
			document.getElementById(errorId).innerHTML = "Month can not be <b>0</b>";
			$scope.fitPersonFormData.timePeriod="";
			 if(errorId == "noofMonthError")
				   document.basicdetail.timePeriod.focus() ;
		           return false;
	 	}
		else{
			document.getElementById(errorId).innerHTML = "";
	        return true;
	   }
	};	
	
	$scope.resetInput = function(model, key,errorId){
    	if(model[key] == 0){
    		model[key] = undefined;
        	document.getElementById(errorId).innerHTML = "Month can not be blank";
    	}
    };
	//====================================download pdf============================
	$scope.printOldFitPersonSubmitData = function(){
    	$scope.printOldFitData = $scope.fitPersonFormData;
    	var serverURL = 'downloadPDFDataReportForFitPerson?type='+$scope.lang;
		
		$scope.printOldFitData.childName=$scope.masterData.childName;
		$scope.printOldFitData.caseNum = $scope.masterData.caseNum;
		$scope.printOldFitData.cwcName = $scope.masterData.cwc.name;
		$scope.printOldFitData.districtName = $scope.masterData.district.name;
		$scope.printOldFitData.dateOfFound = $scope.masterData.dateOfCaseRegistered;
		$scope.printOldFitData.programType = $scope.masterData.programType;
		commonService.downloadFile(serverURL, $scope.printOldFitData);
    };
	
	$scope.printFitPersonSubmitData = function(){
		
		var serverURL = 'downloadPDFDataReportForFitPerson?type='+$scope.lang;
		
		$scope.printFitData.childName=$scope.masterData.childName;
		$scope.printFitData.caseNum = $scope.masterData.caseNum;
		$scope.printFitData.cwcName = $scope.masterData.cwc.name;
		$scope.printFitData.districtName = $scope.masterData.district.name;
		$scope.printFitData.dateOfFound = $scope.masterData.dateOfCaseRegistered;
		$scope.printFitData.programType = $scope.masterData.programType;;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
		$scope.fitPersonFormData.decisionType="fitPerson";
		$rootScope.$broadcast("interimData", $scope.fitPersonFormData);
		$scope.fitPersonDisable=true;
//		$scope.fitPersonFormData={};
		
    };
	
});

/*
**
*/

myAppConstructor.controller('FitInstitution', function($scope, $rootScope, $http, commonService,$window){
	
	$scope.formInfo = {};
	$scope.prefetchData = {};
	$scope.showFitInstitutionBtn=true;
	$scope.designationId=document.getElementById('designationId').value;
	//disabling for CCI view
	if($scope.designationId!=7)
		$scope.fitInstituteDisable=true;
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
		$(".loader").css("display", "block");
		$http.get("getAllCciList").
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			$scope.cciList = result.data;
			console.log(result);
//			$rootScope.$broadcast("cciList", $scope.cciList);
		},function(error){
			console.log(error);
		});
	});
	$scope.cncpList = function(cci){
			return cci.typeId===1 || cci.typeId===2 || cci.typeId===3 || cci.typeId===4 || cci.typeId===5 || cci.typeId===6;
	};
//	$rootScope.$on("cciList", function(event, data){
//		$scope.cciList = data;
//	});
	
	
	$rootScope.$on("formData", function(event, data){
		$scope.formInfo = data;
		$scope.fitInstituteDisable=true;
		if($scope.formInfo.id!=null){
			$scope.showFitInstitutionBtn=false;
		}
	});
	
	$("#dateOfPlacemennt").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			document.getElementById('dateOfPlacementError').innerHTML = "";
		    $scope.formInfo.dateChildPlacedInFitInstitution = date;
		  }	
	});
	
	$("#dateOfOrder").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			document.getElementById('dateOfFormFilledError').innerHTML = "";
		    $scope.formInfo.dateOfFormFilled = date;
		  }	
	});
	
	
	$("#timePeriod").keypress(function(event) {
		//if (event.which == 101 || event.which == 69)
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	$("#ageondate").keypress(function(event) {
		//if (event.which == 101 || event.which == 69)
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	$("#timePeriodinMonths").keypress(function(event) {
		//if (event.which == 101 || event.which == 69)
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	
	$("#groupTimeDuration").keypress(function(event) {
		//if (event.which == 101 || event.which == 69)
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	$scope.validateFields = function(name, errorId){
//		if(name === undefined){
			if(errorId =='pgContactError'  && name>9999999999) {
			    document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
			    $scope.formInfo.pgContactNo="";
//	        } 
		}else if(errorId=='timePeriodinMonthserror' && name == 0){
			document.getElementById(errorId).innerHTML = "Month can not be <b>0</b>";
			$scope.formInfo.timePeriodinMonths="";
			 if(errorId == "timePeriodinMonthserror")
				   document.basicdetail.timePeriodinMonths.focus() ;
		           return false;
	 	}
		else{
			document.getElementById(errorId).innerHTML = "";
	        return true;
	   }
	};	
	$scope.resetInput = function(model, key,errorId){
    	if(model[key] == 0){
    		model[key] = undefined;
        	document.getElementById(errorId).innerHTML = "Month can not be blank";
    	}
    	
    };
	
	$scope.validateForm = function(){
//		if($scope.formInfo.dateChildPlacedInFitInstitution === undefined){
//			$( "#dateOfPlacemennt" ).datepicker("show");
//		}
//		else if($scope.formInfo.dateOfFormFilled === undefined){
//			$( "#dateOfOrder" ).datepicker("show");
//		}
		if($scope.formInfo.periodForWhichSentToFitInstitution!=null){
		if($scope.formInfo.dateChildPlacedInFitInstitution === undefined){
			$("#dateOfPlacemennt").datepicker("show");
			document.getElementById('dateOfPlacementError').innerHTML = "Please select <b>Date of placement</b>";
		}else if($scope.formInfo.dateOfFormFilled === undefined){
			$( "#dateOfOrder" ).datepicker("show");
			document.getElementById('dateOfFormFilledError').innerHTML = "Please select <b>Date of order</b>";
		}
		else{
			$scope.formInfo.childId = $scope.prefetchData.childId;
			$('#confirmationModal').modal('show');
			console.log($scope.formInfo);
		}
		}
		
			
//			if($scope.formInfo.dateTillChildSentToSupervisionInstitution < $scope.formInfo.dateChildSentToSupervisionInstitution){
//				document.getElementById('toerror').innerHTML = "<b>TO</b> date must not be smaller than <b>FROM</b> date";
//				document.getElementById('to').focus();
//			}
	};
	
	$scope.saveData = function(){
		$scope.formInfo.caseNo=$scope.prefetchData.caseNum;
		$scope.formInfo.addressOfChild = $scope.formInfo.addressOfChild.replace(/\n/g, " ");
		$(".loader").css("display", "block")
		$http.post('fitInstitutionDetails',$scope.formInfo).
		then(function(result){
			$(".loader").css("display", "none");
			checkSessionOut(result.data)
			console.log(result);
			$( "#childFitIdModal" ).modal("show");
		},function(error){
			console.log(error);
		});
		
	};
	$scope.reDirect = function(){
		$window.location.reload();
	};
	//====================================download pdf============================
	$scope.printOldFitInstitutionSubmitData = function(){
    	$scope.printOldFitData = $scope.formInfo;
    	var serverURL = 'downloadPDFDataReportForFitInstitution?type='+$scope.lang;
		
		$scope.printOldFitData.childName=$scope.prefetchData.childName;
		$scope.printOldFitData.caseNo = $scope.prefetchData.caseNum;
		$scope.printOldFitData.cwcName = $scope.prefetchData.cwc.name;
		$scope.printOldFitData.childrenHomeSAAFacility=$scope.printOldFitData.cci.name;
		$scope.printOldFitData.childAge=$scope.prefetchData.age;
		$scope.printOldFitData.programType = $scope.prefetchData.programType;
		
		delete $scope.printOldFitData.dateOfReg;
		delete $scope.printOldFitData.dateWhenCNCP;
		
		commonService.downloadFile(serverURL, $scope.printOldFitData);
    };
	
	$scope.printFitInstitutionSubmitData = function(){
		
		var serverURL = 'downloadPDFDataReportForFitInstitution?type='+$scope.lang;
		$scope.printFitData =$scope.formInfo;
		$scope.printFitData.childName=$scope.prefetchData.childName;
		$scope.printFitData.cwcName = $scope.prefetchData.cwc.name;
		$scope.printFitData.childrenHomeSAAFacility=$scope.printFitData.cci.name;
		$scope.printFitData.childAge=$scope.prefetchData.age;
		$scope.printFitData.programType = $scope.prefetchData.programType;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
		$scope.formInfo.decisionType="fitInstitution";
		$scope.formInfo.periodForWhichSentToFitInstitution = Number($scope.formInfo.periodForWhichSentToFitInstitution);
		$rootScope.$broadcast("interimData", $scope.formInfo);
		$scope.fitInstituteDisable=true;
//		$scope.formInfo = {};
		 
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
directive('onlyTwoDigits', function () {

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
	            	 if(clean.length>2 ){
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

myAppConstructor.
directive('twohundredCharactersValidation', function () {

  return {
      restrict: 'A',
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					var val = '';
				}
				
				var clean = val;
				if(!angular.isUndefined(clean)) {
	            	 var num=0;
	            	 if(clean.length>200 ){
	            		 num =clean.slice(0,200);
	            		 clean= num;
	            	 }
	            	 clean = clean.replace(/[^a-zA-z ,.]/g, '');
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
					var val = '';
				}
				
				var clean = val;
				if(!angular.isUndefined(clean)) {
	            	 var num=0;
	            	 if(clean.length>30 ){
	            		 num =clean.slice(0,30);
	            		 clean= num;
	            	 }
	            	 clean = clean.replace(/[^a-zA-z ,.]/g, '');
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
directive('hundredCharactersValidation', function () {

  return {
      restrict: 'A',
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					var val = '';
				}
				
				var clean = val;
				if(!angular.isUndefined(clean)) {
	            	 var num=0;
	            	 if(clean.length>100 ){
	            		 num =clean.slice(0,100);
	            		 clean= num;
	            	 }
	            	 clean = clean.replace(/[^a-zA-z ,.]/g, '');
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
