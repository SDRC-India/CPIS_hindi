
/**
 * @author Biswa (biswabhusan@sdrc.co.in)
 * @author Naseem (naseem@sdrc.co.in)
 * In individual care plan controller, the child's master data is being prefetched along with the typedetails list.
 * The typedetails and child master data are broadcasted so that they can be used in other controllers in this js.
 */

myAppConstructor.controller('individualcarePlanController', function($scope, $http, $rootScope, commonService,$window,gettextCatalog){
	$scope.lang='en';
	$http({
        method : "GET",
        url : "getLang"
    }).then(function mySucces(response) {
    	$scope.lang=response.data;
    	$scope.changeLanguage($scope.lang);
});
	$scope.changeLanguage = function(lang){
		$scope.lang=lang;
		   gettextCatalog.setCurrentLanguage(lang);
			$http({
		        method : "GET",
		        url : "setLangString?language="+$scope.lang
		    }).then(function mySucces(response) {
		    	
		});
	}
	$scope.selectedChildId=$('#modelValue').val();
	$scope.selectedChild = {};
	$scope.designationId=document.getElementById('designationId').value;
	$scope.personalDetailsShow=true;
	$scope.progressReportShow=true;
	$scope.preReleaseShow=true;
	$scope.postReleaseShow=true;
	$scope.addNewProgressReport=true;
	$scope.checkAllDataSuccessful = function(){
		if ($scope.getPersonalDetailsDataSuccessful
				&& $scope.getProgressReportsSuccessful
				&& $scope.getPreReleaseReportSuccessful
				&& $scope.getPostReleaseReportSuccessful
				&& $scope.getTypeDetailsSuccessful) {
			$(".loader").css("display", "none");
		}
	};
	$scope.getPersonalDetailsDataSuccessful = false;
	$scope.getProgressReportsSuccessful = false;
	$scope.getPreReleaseReportSuccessful = false;
	$scope.getPostReleaseReportSuccessful = false;
	$scope.getTypeDetailsSuccessful = false;
	$scope.getChildByIdSuccessful = false;
	 $(".loader").css("display", "block");
	 $http.post("getNotificationCount").then(
				function(response) {
					$scope.notificationCount=response.data;
				},
				function(error){
					console.log(error);
				});
	
	
//	$scope.icpBlist;
	$http.get("getTypeDetails").
	then(function(result){
		$scope.getTypeDetailsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$rootScope.$broadcast("typeDetails",result);
	},function(error){
		console.log(error);
	});
	$("#one").show();
	$scope.showForm = function(formData){
		$('#one').hide();
		$('#two').show();
		$('#four').hide();
		$('#three').hide();
		$rootScope.$broadcast("formData",formData);
	};
	
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.menuList=result;
	});
	

	
	
	$http.get("getPersonalDetailsData?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getPersonalDetailsDataSuccessful = true;
		$scope.checkAllDataSuccessful();
		if($scope.designationId==7 || $scope.designationId==8){
			if(result.data.id==null){
				$('#icpNotFilled').modal('show');
				$scope.personalDetailsShow=false;
				$scope.progressReportShow=false;
				$scope.preReleaseShow=false;
				$scope.postReleaseShow=false;
			}
		}
		if(result!=null){
		$scope.personalDetailsData=result.data;
		$rootScope.$broadcast("personalDetails",result);
		}
		else{
			$scope.personalDetailsFilled=true;
		}
	},function(error){
		console.log(error);
	});
	
	$http.get("getProgressReports?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getProgressReportsSuccessful = true;
		$scope.checkAllDataSuccessful();
		if($scope.designationId==7 || $scope.designationId==8){
			$scope.addNewProgressReport=false;
			if(result.data.length==0){
					$scope.progressReportShow=false;
			}
		}
		if(result!=null){
			$rootScope.$broadcast("progressReport",result);
		}
		$scope.icpBlist=result.data;
		$rootScope.$broadcast("icpBlistForPrint",$scope.icpBlist);
	},function(error){
		console.log(error);
	});
	
	$http.get("getPreReleaseReport?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getPreReleaseReportSuccessful = true;
		$scope.checkAllDataSuccessful();
		
		if($scope.designationId==7 || $scope.designationId==8){
			if(result.data.id==null){
				$scope.preReleaseShow=false;
			}
		}
		
		if(result!=null){
		$rootScope.$broadcast("preReleaseReport",result);
		}
		$scope.icpCdata=result.data;
	},function(error){
		console.log(error);
	});
	
	$http.get("getPostReleaseReport?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.getPostReleaseReportSuccessful = true;
		$scope.checkAllDataSuccessful();
		
		if($scope.designationId==7 || $scope.designationId==8){
			if(result.data.id==null){
				$scope.postReleaseShow=false;
			}
		}
		
		if(result!=null){
		$rootScope.$broadcast("postReleaseReport",result);
		}
		$scope.icpDdata=result.data;
	},function(error){
		console.log(error);
	});
	
	
	$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
	then(function(result){
		$scope.getChildByIdSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.selectedChild = result.data;
		if($scope.selectedChild.finalOrderFilled==1)
			$scope.addNewProgressReport=false;
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
//		console.log($scope.selectedChild);
	},function(error){
		console.log(error);
	});
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
		if($scope.prefetchData.cciId==null){
			$('#cciCheck').modal('show');
		}
	});
	
	$rootScope.$on('addRecord', function(event,data){
		$scope.icpBlist.push(data);
	});
	
	$scope.redirectForm=function(url){
		if(url == "child_registration"){
			commonService.redirectForm(url, $scope.selectedChild.childId);
		}else{
			if($scope.selectedChild.childId==null || $scope.selectedChild.childId == undefined 
				|| $scope.selectedChild.childId == ""){
				$('#noChildSelected').modal('show');
			}else if($scope.selectedChild.childId!=null && $scope.selectedChild.programType==1 && url=="childfostercare"){
				$('#ciclChild').modal('show');
			}else if($scope.selectedChild.childId!=null && $scope.selectedChild.programType==1 && url=="interim_order"){
				url="ciclinterimOrder";
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}else if($scope.selectedChild.childId!=null && url=="followUpForm" && $scope.selectedChild.programType==1 ){
				$('#ciclChild').modal('show');
			}
			else{
				if($scope.selectedChild.programType==1 && url=="socialInvestigation"){
					url="ciclSocialInvestigationReport";
				}
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}
		}
	};
	
	$scope.redirect = function(){
		$window.location.href = 'ccts';
	};
});

myAppConstructor.controller('PreReleaseReport', function($scope, $http, $rootScope, commonService, $timeout, $window){
	
//	$scope.formInfo = {};
	$scope.printFitData = {};
	$scope.otherIdProof = false;
	$scope.medicalReport ="";
	$scope.mouOfSponsors ="";
	$scope.mouWithNgo ="";
	$scope.preReleaseView = false;
	$scope.formInfo={};
	
	$rootScope.$on("personalDetails", function(event, data){
		if(data.data!=undefined)
			$scope.icpAdata=data.data;
		else
			$scope.icpAdata=data;
	});
	
	$rootScope.$on("preReleaseReport", function(event, data){
		var result = data;
		if(result.data.id != null){
			$scope.preReleaseView = true;
			$scope.formInfo=result.data;
			$scope.printFitData=result.data;
			$scope.mouWithNgo = result.data.mouWithNgoPath;
			$scope.mouOfSponsors = result.data.mouOfSponsorsPath;
			$scope.medicalReport = result.data.medicalReportPath;
			console.log($scope.formInfo);
		}
	});
	$rootScope.$on("icpBlistForPrint", function(event, data){
		var result = data;
		$scope.latestICPList = result[result.length-1];
		
//		$scope.latestICPList = JSON.parse(angular.toJson($scope.latestICPList));
	});
	$rootScope.$on("icpBPrint", function(event, data){
//		var result = data;
//		$scope.latestICPList = result[result.length-1];
		$scope.latestICPList=data;
	});
	$scope.printLatestReport = function(){
		$(".loader").css("display", "block");
		if($scope.latestICPList != undefined){
			$scope.latestICPList.admissionNum = $scope.icpAdata.admissionNum;
			$scope.latestICPList.cwcName = $scope.prefetchData.cwc.name;
			$scope.latestICPList.caseNum = $scope.prefetchData.caseNum;
			$scope.latestICPList.cat1pi = $scope.icpAdata.cat1pi;
			$scope.latestICPList.cat2pi = $scope.icpAdata.cat2pi;
			$scope.latestICPList.cat3pi = $scope.icpAdata.cat3pi;
			$scope.latestICPList.cat4pi = $scope.icpAdata.cat4pi;
			$scope.latestICPList.cat5pi = $scope.icpAdata.cat5pi;
			$scope.latestICPList.cat6pi = $scope.icpAdata.cat6pi;
			$scope.latestICPList.cat7pi = $scope.icpAdata.cat7pi;
			$scope.latestICPList.cat8pi = $scope.icpAdata.cat8pi;
			$scope.latestICPList.cat9pi = $scope.icpAdata.cat9pi;
			$scope.latestICPList.cat10pi = $scope.icpAdata.cat10pi;
			$scope.latestICPList.programType = $scope.selectedChild.programType;
			var serverURL = 'downloadPDFDataReportForICPBPersonDetails?type='+$scope.lang;
			commonService.downloadFile(serverURL, $scope.latestICPList);
		}else{
			$(".loader").css("display", "none");
			$('#noDataAvailable').modal('show');
		}
	};
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
	});
	
	$rootScope.$on("typeDetails", function(event, data){
		var result = data;
		$scope.orderType = result.data.orderType;
		$scope.idProofList = result.data.idProofList;
	});
	
	$("#datepicker9").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.formInfo.dateOfOrder = date;
		}	
	});
	
	$scope.idProofTypeChange = function(){
		/*if($scope.formInfo.identificationProofType.id == 156)
			$scope.otherIdProof = true;
		else{
			$scope.otherIdProof = false;
			$scope.formInfo.otherIdentificationProof = null;
		}*/
		if($scope.formInfo.identificationProofType != undefined)
		if($scope.formInfo.identificationProofType.id != 156)
			$scope.formInfo.otherIdentificationProof = null;
		$scope.formInfo.identificationProofNumber = ""; 
	};
	
	$scope.validateForm = function(){
		if($scope.mouWithNgo != "" && $scope.medicalReport != "")
		{
		if($scope.formInfo.dateOfOrder == undefined || $scope.formInfo.dateOfOrder == null)
			$('#datepicker9').datepicker('show');
//		else if($scope.mouWithNgo == "")
//			console.log("");
//		else if($scope.medicalReport == "")
//			console.log("");
		else
			$('#preReleaseConfirmationModal').modal('show');
		}
	};
	
	$scope.clearImageICPField = function(type){
		switch(type){
		case 'medicalReport':
			$timeout(function() {
				$scope.medicalReport ="";
		    }, 100);
			break;
		case 'mouOfSponsors':
			$timeout(function() {
				$scope.mouOfSponsors ="";
			 }, 100);
			break;
		case 'mouWithNgo':
			$timeout(function() {
				$scope.mouWithNgo ="";
			}, 100);
			break;
	}
	};
	$scope.getBase64=function(file, type) {
	 	var reader = new FileReader();
	 	reader.readAsDataURL(file);
	 	reader.onload = function () {
	 		switch(type){
	 			case 'medicalReport':
	 				$timeout(function() {
	 					$scope.medicalReport=reader.result;
	 			    }, 100);
	 				break;
	 			case 'mouOfSponsors':
	 				$timeout(function() {
	 					$scope.mouOfSponsors = reader.result;
	 				 }, 100);
	 				break;
	 			case 'mouWithNgo':
	 				$timeout(function() {
	 					$scope.mouWithNgo = reader.result;
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
		    		$('#errorImageICPModal').modal('show');
		    		commonService.clearUploadFile();
		    		$scope.clearImageICPField(type);
		         } else{
		        	 $scope.getBase64(value, type);
		        	 checkFile = true;
		         }
	     });
	     if(checkFile == true){
	    	 $scope.clearImageICPField(type);   
	     }
	     if($files.length == 0){
	    	 $scope.clearImageICPField(type);   
	     };
	};
	
	   $scope.downloadImg = function(img,name){
		   var date = new Date();
							   	download(img , name + " "
									+ date.getFullYear() + (date.getMonth() + 1)
									+ date.getHours() + date.getMinutes()
									+ date.getSeconds() + ".pdf", "application/pdf");
	   };

	$scope.saveData = function(){
		$(".loader").css("display", "block");
		$scope.formInfo.medicalReport = $scope.medicalReport==""?null:$scope.medicalReport;
		$scope.formInfo.mouOfSponsors = $scope.mouOfSponsors == "" ? null : $scope.mouOfSponsors;
		$scope.formInfo.mouWithNgo = $scope.mouWithNgo==""?null:$scope.mouWithNgo;
		$scope.formInfo.childId = $scope.prefetchData.childId;
		$scope.printFitData =$scope.formInfo;
		
		$http.post('saveICPC', $scope.formInfo).
		then(function(result){
			$(".loader").css("display", "none");
			$( "#icpPersonDetailsCModal" ).modal("show");
		},function(error){
			console.log(error);
		});
	};
	
	$scope.reDirect = function(){
		$window.location.href = 'ccts';	};
	//====================================download pdf============================
	$scope.printPreReleaseForm = function(){
		$(".loader").css("display", "block");
		if($scope.preReleaseView == false)
			$scope.preReleaseView = true;
			
		var serverURL = 'downloadPDFDataReportForICPCPersonDetails?type='+$scope.lang;
		$scope.printFitData.programType = $scope.selectedChild.programType;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
		    
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

myAppConstructor.controller('PostReleaseController', function($scope, $http, $rootScope, commonService, $window) {
	$scope.formInfo = {
			idBirthCertificateProduced : false,
			idSchoolCertificateProduced : false,
			idCasteCertificateProduced : false,
			idBplCardProduced : false,
			idDisabiltyCertificateProduced : false,
			idImmunizationCardProduced : false,
			idRationCardProduced : false,
			idAdhaarCardProduced : false,
			recievedCompensation : false
	};
	$scope.printFitData = {};
	$scope.trueValue = true;
	$scope.falseValue = false;
	$scope.query = {
			belongingsHandedOver : false,
			childAdmittedToSchool : false
	};
	$scope.viewPostReleasePage = false;
//	$scope.idBirthCertificateProduced = false;
//	$scope.idBPLCard = false;
//	$scope.idCastecertificate = false;
//	$scope.idCastecertificate = false;
//	$scope.idSchoolcertificate = false;
//	$scope.idDisabilityCertificate = false;
//	$scope.idImmunizationcard = false;
//	$scope.idRationCard = false;
//	$scope.idAdhaarCard = false;
//	$scope.idcompensation = false;
	
	$rootScope.$on("postReleaseReport", function(event, data){
		var result = data;
		if(result.data.id != null){
			$scope.viewPostReleasePage = true;
			$scope.formInfo=data.data;
		}
	});
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
	});
	
	$rootScope.$on("typeDetails", function(event, data){
		var result = data;
		$scope.bankAccountStatus = result.data.bankAccountStatus;
		$scope.childBelongingsHandedOverToWhom = result.data.childBelongingsHandedOverToWhom;
		$scope.firstInteractionReportByWhom = result.data.firstInteractionReportByWhom;
		$scope.learningInstitute = result.data.learningInstitute;
	});
	
	$scope.clearToWhomField = function(){
		if($scope.query.belongingsHandedOver == false)
			$scope.formInfo.belongingsHandedToParents = null;
	};
	
	$scope.clearFieldForQ9 = function(){
		if($scope.query.childAdmittedToSchool == false){
			$scope.formInfo.childWentToLearningInstitueType = null;
			$scope.formInfo.schoolName = null;
			$scope.formInfo.admissionDate = null;
		}
		else{
			$("#dateOfAdmissionInSchool").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
				onSelect: function(date) {
					$scope.formInfo.admissionDate = date;
				}	
			});
		}
	}; 
	
	
	$scope.blur = function (value,name) {
	  	  if(name=="idBirthCertificateNum"){
	      	  $scope.formInfo.idBirthCertificateNum=value.trim();  
	  	  }else if(name=="idSchoolCertificateNum"){
	      	  $scope.formInfo.idSchoolCertificateNum=value.trim();  
	  	  }else if(name=="idCasteCertificateNum"){
	      	  $scope.formInfo.idCasteCertificateNum=value.trim();  
	  	  }else if(name=="idBplCardNum"){
	      	  $scope.formInfo.idBplCardNum=value.trim();  
	  	  }else if(name=="idDisabiltyCertificateNum"){
	      	  $scope.formInfo.idDisabiltyCertificateNum=value.trim();  
	  	  }else if(name=="idImmunizationCardNum"){
	      	  $scope.formInfo.idImmunizationCardNum=value.trim();  
	  	  }else if(name=="idRationCardNum"){
	      	  $scope.formInfo.idRationCardNum=value.trim();  
	  	  }else if(name=="idAdhaarCardNum"){
	      	  $scope.formInfo.idAdhaarCardNum=value.trim();  
	  	  }else if(name=="recievedCompensationAction"){
	      	  $scope.formInfo.recievedCompensationAction=value.trim();  
	  	  }
	  	  
	    };
	
	$scope.clearFieldForQ12 = function(value, qid){
		console.log(value, qid);
		switch (qid) {
		case "birthC":
			if(value == true)
				$scope.formInfo.idBirthCertificateNum = null;
			else
				$scope.formInfo.idBirthCertificateNum = null;
			break;
		case "schoolC":
			if(value == true)
				$scope.formInfo.idSchoolCertificateNum = null;
			else
				$scope.formInfo.idSchoolCertificateNum = null;
			break;
		case "casteC":
			if(value == true)
				$scope.formInfo.idCasteCertificateNum = null;
			else
				$scope.formInfo.idCasteCertificateNum = null;
			break;
		case "bplC":
			if(value == true)
				$scope.formInfo.idBplCardNum = null;
			else
				$scope.formInfo.idBplCardNum = null;
			break;
		case "disabilityC":
			if(value == true)
				$scope.formInfo.idDisabiltyCertificateNum = null;
			else
				$scope.formInfo.idDisabiltyCertificateNum = null;
			break;
		case "immunizationC":
			if(value == true)
				$scope.formInfo.idImmunizationCardNum = null;
			else
				$scope.formInfo.idImmunizationCardNum = null;
			break;
		case "rationC":
			if(value == true)
				$scope.formInfo.idRationCardNum = null;
			else
				$scope.formInfo.idRationCardNum = null;
			break;
		case "adhaarC":
			if(value == true)
				$scope.formInfo.idAdhaarCardNum = null;
			else
				$scope.formInfo.idAdhaarCardNum = null;
			break;
		case "compenR":
			if(value == true)
				$scope.formInfo.recievedCompensationAction = null;
			else
				$scope.formInfo.recievedCompensationAction = null;
			break;
		}
	};
	
	$scope.validateForm = function(){
		$scope.formInfo.childId = $scope.prefetchData.childId;
		
		if($scope.query.childAdmittedToSchool == true && ($scope.formInfo.admissionDate == null || $scope.formInfo.admissionDate == undefined)){
			$('#dateOfAdmissionInSchool').datepicker('show');
		}
		else{
			$('#postReleaseConfirmationModal').modal('show');
		}
	};
	
	$scope.saveData = function(){
		$(".loader").css("display", "block");
		$http.post('saveICPD', $scope.formInfo).
		then(function(result){
			$(".loader").css("display", "none");
			$( "#icpPostReleaseModal" ).modal("show");
		},function(error){
			console.log(error);
		});
	};
	
	$scope.reDirect = function(){
		$window.location.href = 'ccts';
	};
	//====================================download pdf============================
	$scope.printicpPersonDetailsDSubmitData = function(){
		$(".loader").css("display", "block");
		if($scope.viewPostReleasePage == false)
			$scope.viewPostReleasePage = true;
		
		$scope.printFitData = $scope.formInfo;
		$scope.printFitData.programType = $scope.selectedChild.programType;
		var serverURL = 'downloadPDFDataReportForICPDPersonDetails?type='+$scope.lang;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
    	/*$http.post(serverURL, $scope.printFitData).
		  then(function(response){
			  var fileName = {
						"fileName" : response.data
					};
			  commonService.download("downloadFile/", fileName, 'POST');
		  });*/
    };
});

myAppConstructor.controller('personalDetailsController', function($scope, $http, $rootScope, commonService, $window) {
	
	$scope.prsnlDtlsViewPage = false;
	$scope.personalDetail = {};
	$scope.radioObject = {
			earnings : false,
			accountDetail : false,
			awardDetails : false
	};
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
		if($scope.prefetchData.finalOrderFilled==1){
			$scope.prsnlDtlsViewPage=true;
		}
		$scope.personalDetail.childId=$scope.prefetchData.childId;
		$scope.personalDetail.fatherName=$scope.prefetchData.sirFatherName;
		$scope.personalDetail.motherName=$scope.prefetchData.sirMotherName;
		$scope.personalDetail.religion = $scope.prefetchData.sirChildReligion;
		$scope.personalDetail.caste = $scope.prefetchData.sirChildCast == null ? null :$scope.prefetchData.sirChildCast;
		$scope.personalDetail.religionOther = $scope.prefetchData.sirOtherChildReligion;
//		$scope.personalDetail.year=$scope.prefetchData.year;
	});
	
	$rootScope.$on("personalDetails", function(event, data){
		if(data.data!=undefined)
			$scope.icpAdata=data.data;
		else
			$scope.icpAdata=data;
//		$scope.icpAdata = data.data;
		if($scope.icpAdata.id != null){
			$scope.personalDetail = $scope.icpAdata;
			
//			$scope.personalDetail.earnings=null==$scope.personalDetail.earnings?'No':$scope.personalDetail.earnings;
//			$scope.personalDetail.awardDetails=null==$scope.personalDetail.awardDetails?'No':$scope.personalDetail.awardDetails;
//			$scope.personalDetail.accountDetail=null==$scope.personalDetail.accountDetail?'No':$scope.personalDetail.accountDetail;
			$scope.prsnlDtlsViewPage = true;
		}
	});
	
	$rootScope.$on("typeDetails", function(event, data){
		var result = data;
		
		$scope.designationList=result.data.icpDesignation;
		$scope.stayOfTheChild=result.data.stayOfTheChild;
		$scope.childSexList = result.data.childSex;
		$scope.casteList=result.data.casteList;
		$scope.educationList=result.data.childEducationDtls;
		$scope.educationLevels=result.data.educationLevels;
		$scope.religionList = result.data.religionList;
		
	});
	
	$("#datepicker").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.personalDetail.dateOfIcp = date;
		}	
	});
	
	$("#datepicker1").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.personalDetail.admissionDate = date;
		}	
	});
	
	$("#dob").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.personalDetail.dob = date;
		}	
	});
	
	$scope.checkReligion = function(){
		if($scope.personalDetail.religion.id != 184)
			$scope.personalDetail.caste = null;
		
		if($scope.personalDetail.religion.id != 187)
			$scope.personalDetail.religionOther = null;
	};
	
	$scope.clearFieldForQ13 = function(value, id){
		switch(id){
		case "savAcc":
			$scope.personalDetail.accountDetail = null;
			break;
		case "earnings":
			$scope.personalDetail.earnings = null;
			break;
		case "awards":
			$scope.personalDetail.awardDetails = null;
			break;
		}
	};
	$scope.blur = function (value,name) {
	  	  if(name=="accountDetail"){
	      	  $scope.personalDetail.accountDetail=value.trim();  
	  	  }else if(name=="earnings"){
	      	  $scope.personalDetail.earnings=value.trim();  
	  	  }else if(name=="awardDetails"){
	      	  $scope.personalDetail.awardDetails=value.trim();  
	  	  }
	  	  
	    };
	
	$scope.validateForm = function(){
		if($scope.personalDetail.dateOfIcp == undefined || $scope.personalDetail.dateOfIcp == null)
			$('#datepicker').datepicker('show');
		else if($scope.personalDetail.admissionDate == undefined || $scope.personalDetail.admissionDate == null)
			$('#datepicker1').datepicker('show');
		else{
			$('#personalDetailsConfirmationModal').modal('show');
//			commonService.setTypeOfForm('personalDetail', $scope.personalDetail);
		}
	};
	$scope.printFitData = {};
	$scope.saveData = function(){
		$(".loader").css("display", "block");
		console.log($scope.personalDetail);
		
		
		$scope.printFitData = $scope.personalDetail;
		$scope.personalDetail.boardAddress = $scope.personalDetail.boardAddress.replace(/\n/g, " ");
		$http.post('saveICPA', $scope.personalDetail).
		then(function(result){
			$rootScope.$broadcast("personalDetails",$scope.personalDetail);
//			$scope.personalDetail={};
			$(".loader").css("display", "none");
			$( "#icpPersonDetailsModal" ).modal("show");
		},function(error){
			console.log(error);
		});
	};
	
	$scope.reDirect = function(){
		$window.location.href = 'ccts';
	};
	//====================================download pdf============================
	$scope.printicpPersonDetailsASubmitData = function(){
		$(".loader").css("display", "block")
		
		$http.get("getPersonalDetailsData?childId="+$scope.selectedChildId).
		then(function(result){
			$scope.personalDetail=result.data;
			$scope.getPersonalDetailsDataSuccessful = true;
			$scope.checkAllDataSuccessful();
			if(result!=null){
			$rootScope.$broadcast("personalDetails",result);
			}
			else{
				$scope.personalDetailsFilled=true;
			}
		},function(error){
			console.log(error);
		});
		
		
		if($scope.prsnlDtlsViewPage == false)
			$scope.prsnlDtlsViewPage = true;
		
		var serverURL = 'downloadPDFDataReportForICPAPersonDetails?type='+$scope.lang;
		$rootScope.$broadcast("personalDetails",$scope.personalDetail);
		$scope.printFitData = $scope.personalDetail;
		$scope.printFitData.childName=$scope.prefetchData.childName;
		$scope.printFitData.age=$scope.prefetchData.age;
		$scope.printFitData.year=$scope.prefetchData.year;
		$scope.printFitData.caseNum = $scope.prefetchData.caseNum;
		if($scope.lang==='en'){
			$scope.printFitData.childSexName = ($scope.prefetchData.childSex==145?"Girl":$scope.prefetchData.childSex==144?"Boy":"Third Gender");
		}else{
			$scope.printFitData.childSexName = ($scope.prefetchData.childSex==145?"बालिका":$scope.prefetchData.childSex==144?"बालक":"तीसरा लिंग");
		}
		
		$scope.printFitData.programType = $scope.selectedChild.programType;
		$scope.printFitData.firNo = $scope.prefetchData.firNumber;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
		
    	/*$http.post(serverURL, $scope.printFitData).
		  then(function(response){
			  var fileName = {
						"fileName" : response.data
					};
			  commonService.download("downloadFile/", fileName, 'POST');
		  });*/
    };
});

myAppConstructor.controller('ProgressReportController', function($scope, $http, $rootScope, commonService, $window) {
	
	$scope.printFitData = {};
	$scope.prefetchData;
	$scope.progressReport;
	$scope.icpAdata;
	$scope.icpBdata;
	$scope.progressReportViewPage = false;
	
	/*
	 * prefetching child master data
	 */
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
	});
	
	$rootScope.$on("personalDetails", function(event, data){
		if(data.data!=undefined)
			$scope.icpAdata=data.data;
		else
			$scope.icpAdata=data;
	});
	
//	$rootScope.$on("progressReport", function(event, data){
//		var result = data;
//		$scope.icpBdata=data.data;
//			$scope.progressReportBtn=false;
//	});
	
	$rootScope.$on("formData", function(event, data){
		if(data.id != null)
			$scope.progressReportViewPage = true;
		$scope.progressReport=data;
		$scope.printFitData=data;
	});
	
	/*
	 * fetching all the type details.
	 */
	$rootScope.$on("typeDetails", function(event, data){
		var result = data;
		$scope.proceedingsBeforeCommittee=result.data.proceedingsBeforeCommittee;
		$scope.designationList=result.data.icpDesignation;
		$scope.stayOfTheChild=result.data.stayOfTheChild;
		$scope.icpParentType=result.data.icpParentType;
	});
	
	
	
//	$http.get("getPersonalDetailsData?childId="+$scope.selectedChildId).
//	then(function(result){
//		$scope.progressReport=result.data;
//	},function(error){
//		console.log(error);
//	});
	
	$("#dateOfReport").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.progressReport.dateOfReport = date;
		}	
	});
	
	$("#dateOfInterview").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.progressReport.dateOfInterview = date;
		}	
	});
	
	$("#supervisionCompletionDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',changeYear: true,
		onSelect: function(date) {
			$scope.progressReport.supervisionCompletionDate = date;
		}	
	});
	
	$scope.clearFieldQ16 = function(){
		$scope.progressReport.nameOfParent = null;
		$scope.progressReport.addressOfParent = null;
	};
	
	$scope.validateForm = function(){
		if($scope.progressReport.dateOfInterview == undefined || $scope.progressReport.dateOfInterview == null)
			$('#dateOfInterview').datepicker('show');
		else if($scope.progressReport.dateOfReport == undefined || $scope.progressReport.dateOfReport == null)
			$('#dateOfReport').datepicker('show');
		else
			$('#progressReportConfirmationModal').modal('show');
	};
	
	$scope.saveData = function(){
		$(".loader").css("display", "block");
		$scope.progressReport.childId=$scope.prefetchData.childId;
		$scope.printFitData = $scope.progressReport;
		console.log($scope.progressReport);
		$http.post('saveICPB', $scope.progressReport).
		then(function(result){
			$(".loader").css("display", "none");
			$( "#icpPersonDetailsBModal" ).modal("show");
		},function(error){
			console.log(error);
		});
	};
	
	$scope.reDirect = function(){
		$window.location.href = 'ccts';
	};
	
	//====================================download pdf============================
	$scope.printProgressReport = function(){
		$(".loader").css("display", "block");
		if($scope.progressReportViewPage == false){
			$scope.progressReportViewPage = true;
			$rootScope.$broadcast('addRecord', $scope.progressReport);
			$rootScope.$broadcast('icpBPrint', $scope.progressReport);
		}
		var serverURL = 'downloadPDFDataReportForICPBPersonDetails?type='+$scope.lang;
		$scope.printFitData.childName = $scope.prefetchData.childName;
		$scope.printFitData.cwcName = $scope.prefetchData.cwc.name;
		$scope.printFitData.admissionNum = $scope.icpAdata.admissionNum;
		$scope.printFitData.caseNum = $scope.prefetchData.caseNum;
		$scope.printFitData.cat1pi = $scope.icpAdata.cat1pi;
		$scope.printFitData.cat2pi = $scope.icpAdata.cat2pi;
		$scope.printFitData.cat3pi = $scope.icpAdata.cat3pi;
		$scope.printFitData.cat4pi = $scope.icpAdata.cat4pi;
		$scope.printFitData.cat5pi = $scope.icpAdata.cat5pi;
		$scope.printFitData.cat6pi = $scope.icpAdata.cat6pi;
		$scope.printFitData.cat7pi = $scope.icpAdata.cat7pi;
		$scope.printFitData.cat8pi = $scope.icpAdata.cat8pi;
		$scope.printFitData.cat9pi = $scope.icpAdata.cat9pi;
		$scope.printFitData.cat10pi = $scope.icpAdata.cat10pi;
		$scope.printFitData.programType = $scope.selectedChild.programType;
		
		commonService.downloadFile(serverURL, $scope.printFitData);
		
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
					var val = '';
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
				if (angular.isUndefined(val)) {
					var val = '';
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