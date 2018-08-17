
/*
 * @author - Naseem Akhtar (naseem@sdrc.co.in)
 */
myAppConstructor.controller('childFosterCareController', function($scope, $http, commonService, $timeout, $window,gettextCatalog){
	$http({
        method : "GET",
        url : "getLang"
    }).then(function mySucces(response) {
    	$scope.lang=response.data;
    	$scope.changeLanguage($scope.lang);
});
	$scope.changeLanguage = function(lang){
		console.log(lang);
		$scope.lang=lang;
		$http({
	        method : "GET",
	        url : "setLangString?language="+$scope.lang
	    }).then(function mySucces(response) {
	    	
	});
		   gettextCatalog.setCurrentLanguage(lang);
	}
	/*
	 * 1.Declaring global variables
	 */
	$scope.childId = $('#childId').val();
	var defaultImage = "resources/img/photo.jpg";
	$scope.childImage = "resources/img/photo.jpg";
	$scope.fosterCareParentImage = "resources/img/photo.jpg";
	$scope.biologicalParentImage = "resources/img/photo.jpg";
	$scope.detailsOfPlacement = [];
	$scope.detailsOfPlacementObject = {};
	$scope.icpPage = 'individualCarePlan?selectedChildId='+$scope.childId;
	$scope.detailsOfPlacement.push($scope.detailsOfPlacementObject);
	var indexOfDate = 1;
	
	/*
	 * Declaration of global variables ends here
	 * Loader code starts here
	 */
	
	$("#datepicker6").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d',
		onSelect: function(date) {
			$scope.fosterCare.dateForTermination = date;
		}
	});
	$("#datepicker7").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d',
		onSelect: function(date) {
			$scope.fosterCare.dateForExtension = date;
		}
	});
	$("#datepicker8").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d',
		onSelect: function(date) {
			$scope.fosterCare.childHandedOverDate = date;
		}
	});
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	$(".loader").css("display", "block");
	$scope.gridMenuFetch	= false;
	$scope.cciListFetch		= false;
	$scope.childMstFetch 	= false;
	$scope.fosterCareFetch	= false;
	$scope.allFosterFetch 	= false;
	
	$scope.checkAllFetch = function(){
		if($scope.gridMenuFetch && $scope.cciListFetch && $scope.childMstFetch
				&& $scope.fosterCareFetch && $scope.allFosterFetch){
			$(".loader").css("display", "none");
		}
	};
	
	/*
	 * Loader code ends here
	 * 2.Fetching grid menu
	 */
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.menuList=result;
		$scope.gridMenuFetch = true;
		$scope.checkAllFetch();
	}, function(error){
		console.log(error);
	});
	
	$scope.redirectForm=function(url){
		if(url == "child_registration"){
			commonService.redirectForm(url, $scope.selectedChild.childId);
		}else{
			if($scope.selectedChild.childId==null || $scope.selectedChild.childId == undefined 
				|| $scope.selectedChild.childId == ""){
				$('#noChildSelected').modal('show');
			}else if($scope.selectedChild.childId!=null && url=="followUpForm" && $scope.selectedChild.programType==1 ){
				$('#ciclChild').modal('show');
			}else{
				if($scope.selectedChild.programType==1 && url=="socialInvestigation"){
					url="ciclSocialInvestigationReport";
				}
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}
		}
	};
	
	$scope.reDirect = function(){
		$window.location.href = 'ccts';
	};
	
	/*
	 * Fetching grid menu ends here
	 * 
	 * 3.Fetching master data of child
	 */
	
//	$http.get("getChildRegMstData").
//    then(function(result){
//	   	$scope.cwcList=result.data.value.cwcList;
//    }, function(error){
//    	console.log(error);
//    });
	
	$http.get("getCCIlist").
	then(function(result){
		$scope.cciList = result.data;
		$scope.cciListFetch = true;
		$scope.checkAllFetch();
	}, function(error){
		console.log(error);
	});
	
	if($scope.childId != null && $scope.childId !== undefined && $scope.childId != ""){
		$http.get("getChildById?selectedChildId="+$scope.childId)
		.then(function(result){
			$scope.selectedChild=result.data;
			$scope.childMstData = result.data;
			$scope.childMstFetch = true;
			$scope.checkAllFetch();
			console.log(result);
			
			$http.get('getFosterCareRecord?childId='+$scope.childId).
			then(function(result){
				if(result.data != null && result.data != ""){
					$scope.viewChildInFosterCare = true;
					$scope.fosterCare = result.data;
					$scope.fosterCare.childId = $scope.childId;
					$scope.fosterCare.caseNo = $scope.childMstData.caseNum;
					$scope.fosterCare.childName = $scope.childMstData.childName;
					$scope.fosterCare.childAge = $scope.childMstData.age;
					$scope.fosterCare.childSex = $scope.childMstData.sexObject.name;
//					$scope.detailsOfPlacement = $scope.fosterCare.detailsOfPlacementList;
					$scope.fosterCareParentImage = $scope.fosterCare.fosterCareParentImage == "resources/img/photo.jpg" ? null : $scope.fosterCare.fosterCareParentImage;
					$scope.childImage = $scope.fosterCare.childImage == null ? "resources/img/photo.jpg" : $scope.fosterCare.childImage;
					$scope.biologicalParentImage = $scope.fosterCare.biologicalParentImage == "resources/img/photo.jpg" ? null : $scope.fosterCare.biologicalParentImage;
					$scope.hsrBiologicalParent = $scope.fosterCare.hsrBiologicalFamily;
					$scope.hsrFosterFamily = $scope.fosterCare.hsrFosterFamily;
					$scope.childReport = $scope.fosterCare.childStudyReport;
					$scope.eachVisitChildRecord = $scope.fosterCare.recordOfEachVisitFile;
					
					console.log(result.data);
				}
				else{
					if($scope.selectedChild.finalOrderFilled==1)
					$('#finalOrderModal').modal('show');
					$scope.viewChildInFosterCare = false;
				}
					
				
				$scope.fosterCareFetch = true;
				$scope.checkAllFetch();
			}, function(error){
				console.log(error);
			});
			
		}, function(error){
			console.log(error);
		});
		$scope.redirect = function(){
			$window.location.href = 'ccts';
		};
		$http.get('findAllFosterOrders?childId='+$scope.childId).
		then(function(result){
			$scope.cifcList = result.data;
			$scope.detailsOfPlacement = $scope.cifcList;
			console.log(result);
			$scope.allFosterFetch = true;
			$scope.checkAllFetch();
		}, function(error){
			console.log(error);
		});
		
	}
	
	/*
	 * Master data fetch ends here
	 * 
	 * 4.Add/Delete accordions for details of placement
	 */
	
	$scope.addNewDetails = function(index){
		if($scope.detailsOfPlacement.length == 5)
			document.getElementById('limitExceeded').innerHTML = "Maximum of 5 details can be added";
		else{
			var check = validatePlaceOfDetails($scope.detailsOfPlacement.length - 1);
			
			if(check){
				$scope.detailsOfPlacementObject = {};
				$scope.detailsOfPlacement.push($scope.detailsOfPlacementObject);
				document.getElementById('limitExceeded').innerHTML = "";
				
				$timeout(function(){
					$("#dateOfPlacement"+indexOfDate).datepicker({
						dateFormat : "yy-mm-dd",
						maxDate : '+0d'
					});
				}, 1000);
				indexOfDate++;
			}
		}
	};
	
	var validatePlaceOfDetails = function(ind){
		if($scope.detailsOfPlacement[ind].individualOrGroup == undefined || $scope.detailsOfPlacement[ind].individualOrGroup == null
				|| $scope.detailsOfPlacement[ind].individualOrGroup == ""){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}
		
		else if($scope.detailsOfPlacement[ind].date == undefined || $scope.detailsOfPlacement[ind].date == null
				|| $scope.detailsOfPlacement[ind].date == ""){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}
		
		else if($scope.detailsOfPlacement[ind].periodOfPlacement == undefined || $scope.detailsOfPlacement[ind].periodOfPlacement == null
				|| $scope.detailsOfPlacement[ind].periodOfPlacement == ""){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}
		else{
			document.getElementById('cannotAddNew').innerHTML = "";
			return true;
		}
	};
	
	$scope.deleteNewDetails = function(){
		document.getElementById('cannotAddNew').innerHTML = "";
		if($scope.detailsOfPlacement.length > 1){
	    	$scope.detailsOfPlacement.pop();
	    	indexOfDate--;
	    }
	};
	
	/*
	 * Add/Delete accordions for details of placement ends here.
	 * 
	 * 5.Validation code for 'On Submit' 
	 */
	
	$scope.validateChildRecord = function(){
		
		$scope.fosterCare.childId = $scope.childId;
		$scope.fosterCare.caseNo = $scope.childMstData.caseNum;
		$scope.fosterCare.childName = $scope.childMstData.childName;
		$scope.fosterCare.childAge = $scope.childMstData.age;
		$scope.fosterCare.childSex = $scope.childMstData.sexObject.name;
		$scope.fosterCare.cwcAddress = $scope.childMstData.cwc.name;
		
		if($scope.fosterCare.cciObject !== undefined){
			$scope.fosterCare.cciAddress = $scope.fosterCare.cciObject.address;
			$scope.fosterCare.cciName = $scope.fosterCare.cciObject.name;
		}
		
		if($scope.childImage == defaultImage){
			document.getElementById('childImageUploadError').innerHTML = "Please upload image of the child";
			$('html,body').animate({
		        scrollTop: $('#childImage').offset().top - 250},'slow');
			return false;
		}else if($scope.fosterCareParentImage == defaultImage){
			document.getElementById('careGiverUploadError').innerHTML = "Please upload image of foster care giver/parent";
			$('html,body').animate({
		        scrollTop: $('#careGiverImage').offset().top - 250},'slow');
			return false;
		}
		$scope.fosterCare.childImage = $scope.childImage;
		$scope.fosterCare.fosterCareParentImage = $scope.fosterCareParentImage;
		$scope.fosterCare.biologicalParentImage = $scope.biologicalParentImage == defaultImage ? null : $scope.biologicalParentImage;
		$scope.fosterCare.hsrBiologicalFamily = $scope.hsrBiologicalParent === undefined ? null : $scope.hsrBiologicalParent;
		$scope.fosterCare.hsrFosterFamily = $scope.hsrFosterFamily === undefined ? null : $scope.hsrFosterFamily;
		$scope.fosterCare.childStudyReport = $scope.childReport === undefined ? null : $scope.childReport;
		$scope.fosterCare.recordOfEachVisitFile = $scope.eachVisitChildRecord === undefined ? null : $scope.eachVisitChildRecord;
		
//		if($scope.detailsOfPlacement.length == 1 && ($scope.detailsOfPlacement[0].individualOrGroup == undefined
//				|| $scope.detailsOfPlacement[0].individualOrGroup == ""))
//			$scope.fosterCare.detailsOfPlacementList = [];
//		else
//			$scope.fosterCare.detailsOfPlacementList = $scope.detailsOfPlacement;
		if($scope.fosterCare.childHandedOverDate == undefined){
			$("#datepicker8").datepicker("show");
		}else
			$('#confirmationModalForFosterCare').modal('show');
		console.log($scope.fosterCare);
	};
	
	/*
	 * Validation ends here
	 * 
	 * 6.Sending data to server for save and print.
	 */
	
	$scope.saveData = function(){
		$(".loader").css("display", "block");
		$http.post('saveFosterCareRecord',$scope.fosterCare).
		then(function(result){
			$('#successModalForFosterCare').modal('show');
			$scope.viewChildInFosterCare = true;
			$(".loader").css("display", "none");
			checkSessionOut(result.data);
			console.log(result);
		}, function(error){
			console.log(error);
		});
	};
	
	$scope.printChildRecordInFosterData = function(){
		$(".loader").css("display", "block");
		var serverURL = 'downloadPDFDataForChildInFosterCare?type='+$scope.lang;
		$scope.fosterCare.programType = $scope.selectedChild.programType;
		
//		if($scope.detailsOfPlacement.length > 0)
//			$scope.fosterCare.detailsOfPlacementList = $scope.detailsOfPlacement;
		if($scope.lang != 'en')
			$scope.fosterCare.childSex = ($scope.fosterCare.childSex==='Girl'?"बालिका":$scope.fosterCare.childSex==='Boy'?"बालक":"तीसरा लिंग");
		commonService.downloadFile(serverURL, $scope.fosterCare);
    };
    
    $scope.downloadForm32 = function(object){
    	$scope.form32Object = object;
    	var serverURL = 'downloadPDFDataReportForFosterCare?type='+$scope.lang;
		commonService.downloadFile(serverURL, $scope.form32Object);
    };
	
	/*
	 * Sending data to server for save ends here.
	 * 7.Download pdf by passing base 64.
	 */
	
	
	$scope.downloadPdf = function(pdf,name){
	   var date = new Date();
   		download(pdf , name + " "
			+ date.getFullYear() + (date.getMonth() + 1)
			+ date.getHours() + date.getMinutes()
			+ date.getSeconds() + ".pdf", "application/pdf");
   };
	   
   /*
    * 7 ends here.
    * 8.Code for capturing document(pdf) and photo of child,parent
    */
	   
	$scope.getPhoto = function ($files,type) {
		var validFormats = ["jpg","JPG","jpeg","JPEG","png","PNG"];
		
		if($files.length == 0)
			$scope.clearField(type);
		else{
	    	 var ext = $files[0].name.split(".").pop();
	    	 if(validFormats.indexOf(ext) == -1){
	    		$('#uploadError').modal('show');
	    		$scope.clearField(type);
	         } else{
	        	 if($files[0].size > 3000000){
	        		 $('#sizeError').modal('show');
	        		 $scope.clearField(type);
	        	 }
	        	 else
	        		 $scope.getBase64($files[0], type);
	         }
		}
	};
	
	$scope.getReport = function ($files,type) {
		var validFormats = ["pdf"];
		
		if($files.length == 0)
			$scope.clearField(type);
		else{
	    	 var ext = $files[0].name.split(".").pop();
	    	 if(validFormats.indexOf(ext) == -1){
	    		$('#uploadError').modal('show');
	    		$scope.clearField(type);
	         } else{
	        	 $scope.getBase64($files[0], type);
	         }
		}
	};
	
	$scope.getBase64=function(file, type) {
	 	var reader = new FileReader();
	 	reader.readAsDataURL(file);
	 	reader.onload = function () {
	 		switch(type){
	 			case 'childImage':
	 				$timeout(function() {
	 					$scope.childImage = reader.result;
	 			    }, 100);
	 				document.getElementById('childImageUploadError').innerHTML = "";
	 				break;
	 				
	 			case 'fosterCareParentImage':
	 				$timeout(function() {
	 					$scope.fosterCareParentImage = reader.result;
	 			    }, 100);
	 				document.getElementById('careGiverUploadError').innerHTML = "";
	 				break;
	 				
	 			case 'biologicalParentImage':
	 				$timeout(function() {
	 					$scope.biologicalParentImage = reader.result;
	 			    }, 100);
	 				break;
	 				
	 			case 'hsrBiologicalParent':
	 				$timeout(function() {
	 					$scope.hsrBiologicalParent = reader.result;
	 			    }, 100);
	 				break;
	 				
	 			case 'hsrFosterFamily':
	 				$timeout(function() {
	 					$scope.hsrFosterFamily = reader.result;
	 			    }, 100);
	 				break;
	 				
	 			case 'childReport':
	 				$timeout(function() {
	 					$scope.childReport = reader.result;
	 			    }, 100);
	 				break;
	 				
	 			case 'eachVisitChildRecord':
	 				$timeout(function() {
	 					$scope.eachVisitChildRecord = reader.result;
	 			    }, 100);
	 				break;
	 		}
	 	};
	 	reader.onerror = function (error) {
	 		console.log('Error: ', error);
	 	};
	};
	
	$scope.clearField = function(type){
		switch(type){
			case 'childImage':
				$timeout(function() {
					$scope.childImage = "resources/img/photo.jpg";
					angular.element('#childImage').val(null);
			    }, 100);
				break;
				
			case 'fosterCareParentImage':
				$timeout(function() {
					$scope.fosterCareParentImage = "resources/img/photo.jpg";
					angular.element('#careGiverImage').val(null);
			    }, 100);
				break;
				
			case 'biologicalParentImage':
				$timeout(function() {
					$scope.biologicalParentImage = "resources/img/photo.jpg";
					angular.element('#biologicalParentImage').val(null);
			    }, 100);
				break;
				
			case 'hsrBiologicalParent':
				$timeout(function() {
					$scope.hsrBiologicalFamily = "";
					angular.element('#hsrBiological').val(null);
			    }, 100);
				break;
				
			case 'hsrFosterFamily':
				$timeout(function() {
					$scope.hsrFosterFamily = "";
					angular.element('#hsrFoster').val(null);
			    }, 100);
				break;
				
			case 'childReport':
				$timeout(function() {
					$scope.childReport = "";
					angular.element('#childStudyReport').val(null);
			    }, 100);
				break;
				
			case 'eachVisitChildRecord':
				$timeout(function() {
					$scope.eachVisitChildRecord = "";
					angular.element('#eachVisitRecord').val(null);
			    }, 100);
				break;
		}
	};
	
	/*
	 * code for document and photo capture ends here.
	 */
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
} ]);;




myAppConstructor.
directive('fiftyCharactersValidation', function () {

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
					val = '';
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

