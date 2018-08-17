		myAppConstructor.config(['$qProvider', function ($qProvider) {
		    $qProvider.errorOnUnhandledRejections(false);
		}]);
        myAppConstructor.controller('CICLPeriodicReportController', function ($scope, $http,commonService,$timeout, $window,$rootScope,gettextCatalog) {
        	$http({
                method : "GET",
                url : "getLang"
            }).then(function mySucces(response) {
            	$scope.lang=response.data;
            	$scope.changeLanguage($scope.lang);
        });
        	$scope.changeLanguage = function(lang){
        		console.log(lang)
        		   gettextCatalog.setCurrentLanguage(lang);
        		$scope.lang=lang;
        		$http({
        	        method : "GET",
        	        url : "setLangString?language="+$scope.lang
        	    }).then(function mySucces(response) {
        	    	
        	});
        	}
        	//This will hide the DIV by default.
        	$scope.selectedChildId=$('#modelValue').val();
            $scope.show = function (value) {
                //If DIV is visible it will be hidden and vice versa.
                $scope.IsVisible = value == "Y";
            };
            $http.post("getNotificationCount").then(
        			function(response) {
        				$scope.notificationCount=response.data;
        			},
        			function(error){
        				console.log(error);
        			});
            $scope.getGridMenuItemsSuccessful = false;
            $scope.getTypeDetailsSuccessful = false;
            $(".loader").css("display", "block");
            
            $scope.checkAllDataSuccessful = function(){
        		if($scope.getGridMenuItemsSuccessful && $scope.getTypeDetailsSuccessful){
        			$(".loader").css("display", "none");
        		}
        	};
        	
        	$scope.reDirect = function(){
        		$window.location.href = 'ccts';
        	};
    
        	commonService.getGridMenuItems()
        	.then(function (result){
        		$scope.getGridMenuItemsSuccessful = true;
        		$scope.checkAllDataSuccessful();
        		$scope.menuList=result;
        		console.log(result);
        	});
        	$scope.redirectForm=function(url){
        		if(url == "child_registration"){
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
        	
        	$(".onlyNumber").keypress(function(event) {
    	       if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
    	           return false;
    	       }
    	    });
        	$scope.blur = function (value,name) {
    	  	   if(name=="detailsSpentTimeSpeakingPrivately"){
    	      	  $scope.periodicFormInfo.detailsSpentTimeSpeakingPrivately=value.trim();  
    	  	   }
        	}
            $http.get('getTypeDetails').
            then(function(result){
            	   $scope.getTypeDetailsSuccessful = true;
            	   $scope.checkAllDataSuccessful();
         	  	   $scope.childSex = result.data.childSex;
         		   $scope.religionList = result.data.religionList;
         		   $scope.casteList = result.data.casteList;
         	  	   $scope.familyMemberSex = result.data.genderList;
         	  	   $scope.education = result.data.childEducationDtls;
         	       $scope.mediumOfInstructionList = result.data.mediumOfInstruction;
         	       $scope.icpParentType=result.data.icpParentType;
            	   }, function(error){
            		   console.log(error);
            	   });
            var count = 0;
            $scope.showDetailsSpentTimeSpeakingPrivately=false;
            $scope.ShowPpentTimeSpeakingPrivately = function (value) {
            	count++;
                $scope.showDetailsSpentTimeSpeakingPrivately = value == "No";
                if($scope.showDetailsSpentTimeSpeakingPrivately==false){
                	$scope.periodicFormInfo.detailsSpentTimeSpeakingPrivately="";
                }
                if(count == 2)
                	$scope.showDetailsSpentTimeSpeakingPrivately=false;
            };
            
            $scope.ifPreFetchDisable=false;
            $scope.ifSubmittedDisable=false;
          
            $scope.spentTimeSpeakingPrivatelyYes=true;
            $scope.spentTimeSpeakingPrivatelyNo=false;
           
            $scope.showcast=false;
            $scope.showOtherCast=false;
            $scope.showReligionCast=function(){
            	if($scope.periodicFormInfo.religionObject.id==184){
            		$scope.showcast=true;
            		$scope.showOtherCast=false;
            	}else if($scope.periodicFormInfo.religionObject.id==187){
            		$scope.showcast=false;
            		$scope.showOtherCast=true;
            	}
            	else{
            		$scope.showcast=false;
            		$scope.showOtherCast=false;
            	}
            };
        	$scope.religionOthers = function(){

        		if($scope.periodicFormInfo.religionObject.id != 184)
        				$scope.periodicFormInfo.casteObject = {};
        		
        		if($scope.periodicFormInfo.religionObject.id != 187)
        				$scope.periodicFormInfo.casteOtherType = null;
        	};
        	$scope.showOtherLanguage=false;
        	$scope.OtherLanguage=function(){
            	if($scope.periodicFormInfo.languageObject.id==277){
            		$scope.showOtherLanguage=true;
            	}else{
            		$scope.showOtherLanguage=false;
            	}
            };
            var getPrintData = function(){
            	$(".loader").css("display", "block");
         	   $http.get('getPeriodicReport?childId='+$scope.selectedChildId).
         	   then(function(result){
         		  $(".loader").css("display", "none");
         		  checkSessionOut(result.data);
         		   $scope.periodicFormInfo = result.data;
         	   },function(error){
         			console.log(error);
         		});
            };
        	$scope.saveData = function (){
        		$scope.periodicFormInfo.childId=$scope.selectedChildId;
        		
        		$scope.periodicFormInfo.age=Number($scope.periodicFormInfo.age);
        		$scope.periodicFormInfo.workingHours=Number($scope.periodicFormInfo.workingHours);
        		
            	$scope.periodicFormInfo.caseDetailsAndSummary=$scope.periodicFormInfo.caseDetailsAndSummary==undefined?"":$scope.periodicFormInfo.caseDetailsAndSummary.replace(/\n/g, " ");
        		$scope.periodicFormInfo.nameOfOtherAdults=$scope.periodicFormInfo.nameOfOtherAdults==undefined?"":$scope.periodicFormInfo.nameOfOtherAdults.replace(/\n/g, " ");
        		$scope.periodicFormInfo.progressMadeAsRehabilition=$scope.periodicFormInfo.progressMadeAsRehabilition==undefined?"":$scope.periodicFormInfo.progressMadeAsRehabilition.replace(/\n/g, " ");
        		$scope.periodicFormInfo.recommendation=$scope.periodicFormInfo.recommendation==undefined?"":$scope.periodicFormInfo.recommendation.replace(/\n/g, " ");

            	$(".loader").css("display", "block");  
            	$http.post('savePeriodicReport',$scope.periodicFormInfo).
        		  then(function(response){
        			  $(".loader").css("display", "none");
        			  checkSessionOut(response.data);
        			  $('#childIdModal').modal('show');
        			  $scope.childId=response.data;
                  	  $scope.ifSubmittedDisable=true;
        		  },function(error){
        		    console.log(error);
        		  });	
              		  
            };
            $scope.printForm=function(){
               $(".loader").css("display", "block"); 
               $http.get('getPeriodicReport?childId='+$scope.selectedChildId).
           	   then(function(result){
	           		$(".loader").css("display", "none");
	           		checkSessionOut(result.data);
           		    $scope.periodicFormInfo = result.data;
           		    $scope.periodicFormInfo.programType = $scope.selectedChild.programType;
           		    $scope.periodicFormInfo.childUnderCare.name=commonService.getNameBySelectedLanguageType($scope.periodicFormInfo.childUnderCare.name,$scope.icpParentType,$scope.lang);
           		    $scope.periodicFormInfo.casteObject.name=commonService.getNameBySelectedLanguageType($scope.periodicFormInfo.casteObject.name,$scope.casteList,$scope.lang);
           		    $scope.periodicFormInfo.educationObject.name=commonService.getNameBySelectedLanguageType($scope.periodicFormInfo.educationObject.name,$scope.education,$scope.lang);
	            	var serverURL = 'downloadPDFDataForCICLPeriodicReport?type='+$scope.lang;
	            	$scope.ifSubmittedDisable=true;
           	
	           		commonService.downloadFile(serverURL, $scope.periodicFormInfo);
	           	 },function(error){
	      			console.log(error);
	      		});
           		
            };
            
            $scope.redirectForm=function(url){
        		if(url == "child_registration"){
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
        	$("#datepicker" ).datepicker({
				dateFormat: "yy-mm-dd",
				maxDate: '+0d',
				onSelect: function(date) {
					$scope.periodicFormInfo.entryDate = date;
				}	
				});
			$("#datepicker2" ).datepicker({
				dateFormat: "yy-mm-dd", 
				minDate: '+0d',
				onSelect: function(date) {
					$scope.periodicFormInfo.nextCourtDate = date;
				}
				});
			$("#datepicker3" ).datepicker({
				dateFormat: "yy-mm-dd",
				maxDate: '+0d',
				onSelect: function(date) {
					$scope.periodicFormInfo.dateOfAdmission = date;
				}
				});
			$("#datepicker4" ).datepicker({
				dateFormat: "yy-mm-dd",
				maxDate: '+0d',
				onSelect: function(date) {
					$scope.periodicFormInfo.visitDate = date;
				}
				});
			$("#datepicker5" ).datepicker({
				dateFormat: "yy-mm-dd",
				maxDate: '+0d',
				onSelect: function(date) {
					$scope.periodicFormInfo.rehabilitionDate = date;
				}
				});
			$("#datepicker6" ).datepicker({
				dateFormat: "yy-mm-dd",
				minDate: '+0d',
				onSelect: function(date) {
					$scope.periodicFormInfo.planDateOfNextVisit = date;
				}
				});
			
            $scope.validateForm = function (){
        	    if($scope.periodicFormInfo.entryDate === undefined){
        	    	$( "#datepicker" ).datepicker("show");
        	    }else if($scope.periodicFormInfo.nextCourtDate === undefined){
        	    	$( "#datepicker2" ).datepicker("show");
        	    }else if($scope.periodicFormInfo.dateOfAdmission === undefined){
        	    	$( "#datepicker3" ).datepicker("show");
        	    }else if($scope.periodicFormInfo.visitDate === undefined){
        	    	$( "#datepicker4" ).datepicker("show");
        	    }else if($scope.periodicFormInfo.rehabilitionDate === undefined){
        	    	$( "#datepicker5" ).datepicker("show");
        	    }else if($scope.periodicFormInfo.planDateOfNextVisit === undefined){
        	    	$( "#datepicker6" ).datepicker("show");
        	    }else{
        	    	$('#thankyouModal').modal('show');
        	    }
           };
           $rootScope.$on("selectedChild", function(event, data){
           if($scope.selectedChildId!=undefined && $scope.selectedChildId!=""){
	           	$http.get('getPeriodicReport?childId='+$scope.selectedChildId).
	           	then(function(result){
	           		if(result.data!=""){
	           			$timeout(function(){
	           			$scope.ifSubmittedDisable=true;
	           			$scope.periodicFormInfo = result.data;
	           			if(!$scope.periodicFormInfo.spentTimeSpeakingPrivately)
	           				$scope.showDetailsSpentTimeSpeakingPrivately=true;
	           			
	           			$scope.printFitData = $scope.periodicFormInfo;
	           			//do multiple select check-box prefetch here
	           		   
	           	
	           			},500);
	           		}else{
	           			if($scope.selectedChild.finalOrderFilled==1){
	    	    	    	$('#finalOrderModal').modal('show');
	    	    	    }
	           			$scope.periodicFormInfo.spentTimeSpeakingPrivately=true;
	           		}
	           	},function(error){
	           		console.log(error);
	           	});
           }
           });
           
           $http.get("getChildById?selectedChildId="+$scope.selectedChildId).
         	then(function(result){
	       		$scope.selectedChild = result.data;
	       		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
	       		$scope.ifPreFetchDisable=true;
	       		$scope.periodicFormInfo.firNumber=$scope.selectedChild.caseNum;
	       		$scope.periodicFormInfo.childName=$scope.selectedChild.childName;
	       		$scope.periodicFormInfo.age=$scope.selectedChild.age;
	       		$scope.periodicFormInfo.sexObject={id:$scope.selectedChild.childSex};
	       		$scope.periodicFormInfo.firNumber=$scope.selectedChild.firNumber;
	       		$scope.periodicFormInfo.policeStation=$scope.selectedChild.policeStation;
	       		$scope.periodicFormInfo.sections=$scope.selectedChild.sections;
         	},function(error){
       		console.log(error);
       	});
          
           
           
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
        				if (angular.isUndefined(val)) {
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