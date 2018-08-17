/*
 * 
*/
myAppConstructor.controller('caseHistoryController', function($scope, $http, commonService, $rootScope, $timeout, $window, gettextCatalog){
	$scope.langType = 0;
	$http({
        method : "GET",
        url : "getLang"
    }).then(function mySucces(response) {
    	$scope.lang=response.data;
    	$scope.changeLanguage($scope.lang);
});
	$scope.changeLanguage = function(lang){
		gettextCatalog.setCurrentLanguage(lang);
		$scope.lang=lang;
		$scope.langType = lang != 'en' ? 1 : 0;
		
		$http({
	        method : "GET",
	        url : "setLangString?language="+$scope.lang
	    }).then(function mySucces(response) {
	    	
	});
		
//		$scope.$apply();
	};
	$scope.childId = $('#childId').val();
	$scope.viewPage = false;
	$scope.min 	= 	["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","2","23","24","25","26","27",
	           	  	 "28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55",
	           	  	 "56","57","58","59"];
	$scope.hour =	["01","02","03","04","05","06","07","08","09","10","11","12"];
	$scope.ampm	=	["AM", "PM"];
	$scope.hr = null;
	$scope.minute = null;
	$scope.ap = null;
	$scope.designationId=document.getElementById('designationId').value;
	
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	
	$scope.gridMenuFetch = false;
	$scope.childMstFetch = false;
	$scope.caseHistoryFetch = false;
	$scope.typeDetailsFetch = false;
	$(".loader").css("display", "block");
	
	$scope.checkAllFetch = function(){
		if($scope.gridMenuFetch && $scope.childMstFetch	
				&& $scope.caseHistoryFetch && $scope.typeDetailsFetch){
			$(".loader").css("display", "none");
		}
	};
	
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
			}else if($scope.selectedChild.childId!=null && $scope.selectedChild.programType==1 && url=="childfostercare"){
				$('#ciclChild').modal('show');
			}else if($scope.selectedChild.childId!=null && $scope.selectedChild.programType==1 && url=="interim_order"){
				url="ciclinterimOrder";
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}else{
				if($scope.selectedChild.programType==1 && url=="socialInvestigation"){
					url="ciclSocialInvestigationReport";
				}
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}
		}
	};
	
	var indexOfDate = 1;
//	$scope.formInfo = {}; // main object which will be sent to the server
	$scope.familyHistoryCrime = {arrestIfAny : false}; // for pushing objects in the accordion array
	$scope.childEmploymentDetails = {};
	$scope.childHealthObject = {quarterNo : 1};
	$scope.childCategory = [];
	$scope.religionList = [];
	$scope.casteList = [];
	$scope.historyRepetition = []; // array used in the 1st accordion.
	$scope.employRepetition = [];
	$scope.healthStatsRepetition = [];
	
	$scope.historyRepetition.push($scope.familyHistoryCrime);
	$scope.employRepetition.push($scope.childEmploymentDetails);
	$scope.healthStatsRepetition.push($scope.childHealthObject);
	
	/* @author - Naseem Akhtar.
	 * Step - 
	 * These variables are used to enable and disable 
	 * other fields of multiple and single select questions
	 * */
	 
	$scope.trueFlag = true;
	$scope.falseFlag = false;
	$scope.otherReligionFlag = false;
	$scope.categoryDetailFlag = false;
	$scope.categoryOtherFlag = false;
	$scope.showReasonForLeavingFamilyOtherField = false;
	$scope.verbalAbuseFlag = false;
	$scope.physicalAbuseFlag = false;
	$scope.sexualAbuseFlag = false;
	$scope.otherAbuseFlag = false;
	$scope.dofFlag = false;
	$scope.bmFlag = false;
	$scope.illOtherFlag = false;
	$scope.ciFlag = false;
	$scope.dpFlag = false;
	$scope.countExploitationFlag = false;
	$scope.countDelinquentBehaviourFlag = false;
	$scope.countDelinquentBehaviourReasonFlag = false;
	$scope.badHabitCountFlag = false;
	$scope.goodHabitCountFlag = false;
	$scope.savingDetailsCountFlag = false;
	$scope.reasonForLeavingSchoolCountFlag = false;
	$scope.extraCurricularActivityCountFlag = false;
	$scope.detailsOfFriendshipCountFlag = false;
	$scope.detailsOfMembershipCountFlag = false;
	$scope.purposeOfMembershipCountFlag = false;
	
	/*
	 * Checking whether this child has case history form previously filled.
	 * If so then prefetch the data that has been filled up and freeze the form.
	 */
	if($scope.childId != null){
		$http.get("getChildById?selectedChildId="+$scope.childId)
		.then(function(result){
			$scope.prefetchData = result.data;
			$scope.selectedChild = result.data;
			$scope.childMstFetch = true;
			$scope.checkAllFetch();
			console.log(result);
			
			if($scope.selectedChild.finalOrderFilled==1){
				$scope.viewPage=true;
				$('#finalOrderFilledModal').modal('show');
			}
			
			$http.get("caseHistoryByChildId?childId="+$scope.childId)
			.then(function(result){
				if(result.data == ""){
					if($scope.designationId!=10){
						$('#caseHistoryNotFilledModal').modal('show');
						$scope.viewPage=true;
					}
					$scope.formInfo = {};
					$scope.formInfo.childName = $scope.prefetchData.childName;
					$scope.formInfo.childSex = $scope.prefetchData.sexObject.name;
					$scope.formInfo.caseNo = $scope.prefetchData.caseNum;
				}else{
					$scope.viewPage = true;
					$scope.formInfo = result.data;
					$scope.formInfo.childName = $scope.prefetchData.childName;
					$scope.formInfo.childSex = $scope.prefetchData.sexObject.name;
					$scope.formInfo.caseNo = $scope.prefetchData.caseNum;
					$scope.childImgpath = $scope.formInfo.childImgpath;
					
					$scope.time = $scope.formInfo.time.split(":");
					$scope.hr = parseInt($scope.time[0]);
					$scope.minute = $scope.time[1];
					
					if($scope.hr == 0)
						$scope.hr = 12;
					
					if($scope.hr > 12){
						$scope.hr = $scope.hr - 12;
						$scope.period = "PM";
					}
					else
						$scope.period = "AM";
					
					$scope.time = $scope.hr+" : "+$scope.minute+" "+$scope.period;
					$scope.formInfo.timeToString = $scope.time;
					
//					if($scope.formInfo.reasonsForLeavingFamilyOthers)
//						$scope.formInfo.reasonsForLeavingFamilyName +=  " " + $scope.formInfo.reasonsForLeavingFamilyOthers;
//					if($scope.formInfo.verbalAbuseMetByChildOthers)
//						$scope.formInfo.verbalAbuseMetByChildName += " " + $scope.formInfo.verbalAbuseMetByChildOthers;
//					if($scope.formInfo.physicalAbuseMetByChildOthers)
//						$scope.formInfo.physicalAbuseMetByChildName += " " + $scope.formInfo.physicalAbuseMetByChildOthers;
//					if($scope.formInfo.sexualAbuseMetByChildOthers)
//						$scope.formInfo.sexualAbuseMetByChildName += " " + $scope.formInfo.sexualAbuseMetByChildOthers;
//					if($scope.formInfo.otherAbuseMetByChildOthers)
//						$scope.formInfo.otherAbuseMetByChildNames += " " + $scope.formInfo.otherAbuseMetByChildOthers;
//					if($scope.formInfo.illTreatmentDOFOthers)
//						$scope.formInfo.illTreatmentDOFName += " " + $scope.formInfo.illTreatmentDOFOthers;
//					if($scope.formInfo.illTreatmentBMOthers)
//						$scope.formInfo.illTreatmentBMName += " " + $scope.formInfo.illTreatmentBMOthers;
//					if($scope.formInfo.illTreatmentOtherOther)
//						$scope.formInfo.illTreatmentOtherNames += " " + $scope.formInfo.illTreatmentOtherOther;
//					if($scope.formInfo.illTreatmentCIOthers)
//						$scope.formInfo.illTreatmentCIName += " " + $scope.formInfo.illTreatmentCIOthers;
//					if($scope.formInfo.illTreatmentDPOthers)
//						$scope.formInfo.illTreatmentDPName += " " + $scope.formInfo.illTreatmentDPOthers;
//					if($scope.formInfo.exploitaionFacedByTheChildOthers)
//						$scope.formInfo.exploitaionFacedByTheChildName += " " + $scope.formInfo.exploitaionFacedByTheChildOthers;
				}
				console.log(result);
				$scope.caseHistoryFetch = true;
				$scope.checkAllFetch();
			}, function(error){
				console.log(error);
			});
		}, function(error){
			console.log(error);
		});
	}
	

	
	$scope.addNewDetails = function(){
		if($scope.historyRepetition.length == 10)
			document.getElementById('limitExceeded').innerHTML = "Maximum of 10 details can be added";
		else{
			var check = validateFamilyHistoryOfCrime($scope.historyRepetition.length -1);
			if(check){
				document.getElementById('limitExceeded').innerHTML = "";
				$scope.familyHistoryCrime = {arrestIfAny : false};
				$scope.historyRepetition.push($scope.familyHistoryCrime);
			}
		}
	};
	
	$scope.deleteNewDetails = function(){
		if($scope.historyRepetition.length > 1){
			document.getElementById('limitExceeded').innerHTML = "";
	    	$scope.historyRepetition.pop();
	    }
	};
	
	var validateFamilyHistoryOfCrime = function(ind){
		if($scope.historyRepetition[ind].relationshipWithChild == undefined){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.historyRepetition[ind].relationshipWithChild.id == 335 && ($scope.historyRepetition[ind].relationshipWithChildOthers == undefined
				|| $scope.historyRepetition[ind].relationshipWithChildOthers == "")){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.historyRepetition[ind].natureOfCrime == undefined || $scope.historyRepetition[ind].natureOfCrime == ""){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.historyRepetition[ind].legalStatusOfTheCase == undefined || $scope.historyRepetition[ind].legalStatusOfTheCase == ""){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.historyRepetition[ind].periodOfConfinement == undefined || $scope.historyRepetition[ind].periodOfConfinement == ""){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.historyRepetition[ind].punishmentAwarded == undefined || $scope.historyRepetition[ind].punishmentAwarded == ""){
			document.getElementById('cannotAddNew').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else{
			document.getElementById('cannotAddNew').innerHTML = "";
			return true;
		}
	};
	
	
	$scope.addNewEmpDetails = function(){
		if($scope.employRepetition.length == 8)
			document.getElementById('limitExceeded1').innerHTML = "Maximum of 8 details can be added";
		else{
			var check = validateEmploymentDetails($scope.employRepetition.length - 1);
			if(check){
				document.getElementById('limitExceeded1').innerHTML = "";
				$scope.childEmploymentDetails = {};
				$scope.employRepetition.push($scope.childEmploymentDetails);
			}
		}
	};
	
	$scope.deleteNewEmpDetails = function(){
		if($scope.employRepetition.length > 1){
			document.getElementById('limitExceeded1').innerHTML = "";
	    	$scope.employRepetition.pop();
	    }
	};
	
	var validateEmploymentDetails = function(ind){
		if($scope.employRepetition[ind].typeOfEmployment == undefined){
			document.getElementById('cannotAddNew1').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.employRepetition[ind].typeOfEmployment.id == 343 && ($scope.employRepetition[ind].typesOfEmploymentOther == undefined || 
				$scope.employRepetition[ind].typesOfEmploymentOther == "")){
			document.getElementById('cannotAddNew1').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.employRepetition[ind].timing == undefined || $scope.employRepetition[ind].timing == ""){
			document.getElementById('cannotAddNew1').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.employRepetition[ind].duration == undefined || $scope.employRepetition[ind].duration == ""){
			document.getElementById('cannotAddNew1').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else if($scope.employRepetition[ind].wagesEarned == undefined || $scope.employRepetition[ind].wagesEarned == ""){
			document.getElementById('cannotAddNew1').innerHTML = "Please fill all fields to 'Add New'";
			return false;
		}else{
			document.getElementById('cannotAddNew1').innerHTML = "";
			return true;
		}
		
	};
	
	
	$scope.addNewQuarterDetails = function(){
		if($scope.healthStatsRepetition.length<4){
			var check = validateHealthStatusOfChild($scope.healthStatsRepetition.length-1);
			if(check){
				indexOfDate++;
				$scope.childHealthObject = {quarterNo : (indexOfDate)};
				$scope.healthStatsRepetition.push($scope.childHealthObject);
				$timeout(function(){
					$("#datepicker"+(indexOfDate)).datepicker({
						dateFormat : "yy-mm-dd",
						maxDate : '+0d'
					});
				}, 1000);
			}
		}
		else if($scope.healthStatsRepetition.length == 4)
			document.getElementById('limitExceeded2').innerHTML = "Maximum of 4 quarters can be added";
	};
	
	var validateHealthStatusOfChild = function(ind){
		for(var i=1; i<=4; i++){
			if($scope.healthStatsRepetition[i-1] != undefined){
				if(document.getElementById('datepicker'+(i)) != null){
					document.getElementById('datepicker'+i).value == ""?undefined:$scope.healthStatsRepetition[i-1].dateOfReview = document.getElementById('datepicker'+i).value;
				}
			}
		}
		
		if($scope.healthStatsRepetition[ind].dateOfReview == undefined){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields to add details for next quarter";
			return false;
		}
		if($scope.healthStatsRepetition[ind].height == undefined || $scope.healthStatsRepetition[ind].height == ""){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields  to add details for next quarter";
			return false;
		}else if($scope.healthStatsRepetition[ind].weight == undefined || $scope.healthStatsRepetition[ind].weight == ""){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields  to add details for next quarter";
			return false;
		}else if($scope.healthStatsRepetition[ind].nutritiousDietGiven == undefined || $scope.healthStatsRepetition[ind].nutritiousDietGiven == ""){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields  to add details for next quarter";
			return false;
		}else if($scope.healthStatsRepetition[ind].stress == undefined || $scope.healthStatsRepetition[ind].stress == ""){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields  to add details for next quarter";
			return false;
		}else if($scope.healthStatsRepetition[ind].dental == undefined || $scope.healthStatsRepetition[ind].dental == ""){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields  to add details for next quarter";
			return false;
		}else if($scope.healthStatsRepetition[ind].ent == undefined || $scope.healthStatsRepetition[ind].ent == ""){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields  to add details for next quarter";
			return false;
		}else if($scope.healthStatsRepetition[ind].eye == undefined || $scope.healthStatsRepetition[ind].eye == ""){
			document.getElementById('cannotAddNew2').innerHTML = "Please fill all fields  to add details for next quarter";
			return false;
		}else{
			document.getElementById('cannotAddNew2').innerHTML = "";
			return true;
		}
	};
	
	$scope.deleteNewQuarterDetails = function(){
		if($scope.healthStatsRepetition.length > 1){
	    	$scope.healthStatsRepetition.pop();
	    	indexOfDate--;
	    	document.getElementById('limitExceeded2').innerHTML = "";
	    }
	};
	
//	$scope.toggleGroup = function(activity) {
//    	if ($scope.isGroupShown(activity)) {
//      		$scope.shownGroup = null;
//    	} else {
//	      	$scope.shownGroup = activity;
//    	}
//  	};
//
//  	$scope.isGroupShown = function(activity) {
//  		if($scope.shownGroup == activity){
//  			return true;
//  		}else{
//  			return false;
//  		}
//  	};
	
	$scope.accordionOtherField = function(index, relation){
		if(relation.id != 335)
			$scope.historyRepetition[index].relationshipWithChildOthers = null;
	};
	
	$scope.employmentOtherField = function(index, empType){
		if(empType.id != 343)
			$scope.employRepetition[index].typesOfEmploymentOther = null;
	};
	
	$scope.dynamicBlur = function (value,objIndex,index) {
	  $scope.historyRepetition[objIndex].relationshipWithChildOthers = value.trim();  
	};
	
	$scope.dynamicBlur1 = function (value,objIndex,index) {
		  $scope.employRepetition[objIndex].typesOfEmploymentOther = value.trim();  
	};
	
	$scope.dynamicOnlyDigits = function (value,objIndex,index) {
		if(value == undefined)
			$scope.employRepetition[objIndex].periodOfConfinement = null;
		else
			$scope.employRepetition[objIndex].periodOfConfinement = value;  
	};
	
	$scope.blur = function (value,name) {
  	  if(name=="categoryDetails" && $scope.formInfo.categoryDetails != undefined){
      	  $scope.formInfo.categoryDetails=value.trim();  
  	  }else if(name=="categoryDetailsOther"){
      	  $scope.formInfo.categoryDetailsOther=value.trim();  
  	  }else if(name=="religionOthers" &&  $scope.formInfo.religionOthers != undefined){
      	  $scope.formInfo.religionOthers=value.trim();  
  	  }else if(name=="reasonsForLeavingFamilyOthers"){
      	  $scope.formInfo.reasonsForLeavingFamilyOthers=value.trim();  
  	  }else if(name=="verbalAbuseMetByChildOthers"){
      	  $scope.formInfo.verbalAbuseMetByChildOthers=value.trim();  
  	  }else if(name=="physicalAbuseMetByChildOthers"){
      	  $scope.formInfo.physicalAbuseMetByChildOthers=value.trim();  
  	  }else if(name=="sexualAbuseMetByChildOthers"){
      	  $scope.formInfo.sexualAbuseMetByChildOthers=value.trim();  
  	  }else if(name=="illTreatmentDOFOthers"){
      	  $scope.formInfo.illTreatmentDOFOthers=value.trim();  
  	  }else if(name=="illTreatmentBMOthers"){
      	  $scope.formInfo.illTreatmentBMOthers=value.trim();  
  	  }else if(name=="illTreatmentCIOthers"){
      	  $scope.formInfo.illTreatmentCIOthers=value.trim();  
  	  }else if(name=="illTreatmentDPOthers"){
      	  $scope.formInfo.illTreatmentDPOthers=value.trim();  
  	  }else if(name=="exploitaionFacedByTheChildOthers"){
      	  $scope.formInfo.exploitaionFacedByTheChildOthers=value.trim();  
  	  }else if(name=="detailsOfDelinquentBehaviourOthers"){
      	  $scope.formInfo.detailsOfDelinquentBehaviourOthers=value.trim();  
  	  }else if(name=="reasonForDelinquentBehaviourOthers"){
      	  $scope.formInfo.reasonForDelinquentBehaviourOthers=value.trim();  
  	  }else if(name=="childBadHabitsOthers"){
      	  $scope.formInfo.childBadHabitsOthers=value.trim();  
  	  }else if(name=="childGoodHabitsOthers"){
      	  $scope.formInfo.childGoodHabitsOthers=value.trim();  
  	  }else if(name=="typesOfEmploymentOther"){
      	  $scope.formInfo.typesOfEmploymentOther=value.trim();  
  	  }else if(name=="detailsOfSavingOthers"){
      	  $scope.formInfo.detailsOfSavingOthers=value.trim();  
  	  }else if(name=="reasonBehindLeavingSchoolOthers"){
      	  $scope.formInfo.reasonBehindLeavingSchoolOthers=value.trim();  
  	  }else if(name=="extraCurricularActivitiesOthers"){
      	  $scope.formInfo.extraCurricularActivitiesOthers=value.trim();  
  	  }else if(name=="friendshipPriorToAdmissionIntoChildrensHomeOthers"){
      	  $scope.formInfo.friendshipPriorToAdmissionIntoChildrensHomeOthers=value.trim();  
  	  }else if(name=="detailOfMembershipInGroupOthers"){
      	  $scope.formInfo.detailOfMembershipInGroupOthers=value.trim();  
  	  }else if(name=="purposeOfTakingMembershipInGroupOthers"){
      	  $scope.formInfo.purposeOfTakingMembershipInGroupOthers=value.trim();  
  	  }else if(name=="schoolMediumInstructionOthers"){
      	  $scope.formInfo.schoolMediumInstructionOthers=value.trim();  
  	  }else if(name=="childWasStayingWithWhomPriorToAdmissionOther"){
      	  $scope.formInfo.childWasStayingWithWhomPriorToAdmissionOther=value.trim();  
  	  }
  	
    };
	
	
	$http.get("getTypeDetails").
	then(function(result){
		$scope.childCategory = result.data.childCategoryList;
		$scope.religionList = result.data.religionList;
		$scope.casteList = result.data.casteList;
		$scope.typeOfConstructionList = result.data.typeOfConstruction;
		$scope.noOfRoomsList = result.data.noOfRooms;
		$scope.typeOfOccupancyList = result.data.typeOfOccupancy;
		$scope.childBroughtByWhomList = result.data.childBroughtByWhomList;
		$scope.reasonsLeavingFamilyList = result.data.reasonsLeavingFamily;
		$scope.verbalAbusedByList = result.data.abusedBy;
		$scope.physicallAbusedByList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.sexualAbusedByList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.otherAbusedByList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.illTreatmentDOFList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.illTreatmentBMList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.illTreatmentCIList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.illTreatmentDPList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.illTreatmentOtherList = JSON.parse(JSON.stringify($scope.verbalAbusedByList));
		$scope.exploitationFacedList = result.data.exploitationFaced;
		$scope.childHealthStatusList = result.data.childHealthStatus;
		$scope.childWasStayingWithList = result.data.childWasStayingWith;
		$scope.frequencyOfVisitList = result.data.frequencyOfVisit;
		$scope.familyTypeList = result.data.familyType;
		$scope.familyMemberRelationshipList = result.data.familyMemberRelationship;
		$scope.householdArticlesList = result.data.householdArticles;
		$scope.vehiclesOwnedList = result.data.vehiclesOwned;
		$scope.marraigeTypeList = result.data.marraigeType;
		$scope.socialActivityList = result.data.socialActivity;
		$scope.parentalCareTowardsChildList = result.data.parentalCareTowardsChild;
		$scope.delinquentBehaviourList = result.data.delinquentBehaviour;
		$scope.delinquentbehaviourReasonList = result.data.delinquentbehaviourReason;
		$scope.badHabitsList = result.data.badHabits;
		$scope.goodHabitsList = result.data.goodHabits;
		$scope.incomeUtilizationList = result.data.incomeUtilization;
		$scope.detailsOfSavingList = result.data.detailsOfSaving;
		$scope.workDurationList = result.data.workDuration;
		$scope.educationLevelsList = result.data.educationLevels;
		$scope.reasonLeavingSchoolList = result.data.reasonLeavingSchool;
		$scope.childSchoolDtlsList = result.data.childSchoolDtls;
		$scope.mediumOfInstructionList = result.data.mediumOfInstruction;
		$scope.promotionStatusList = result.data.promotionStatus;
		$scope.extraCurricularActivityList = result.data.extraCurricularActivity;
		$scope.friendshipPriorToChildrensHomeList = result.data.friendshipPriorToChildrensHome;
		$scope.majorityFriendTypesList = result.data.majorityFriendTypes;
		$scope.membershipInGroupDetailsList = result.data.membershipInGroupDetails;
		$scope.positionInGroupList = result.data.positionInGroup;
		$scope.purposeOfTakingMembershipList = result.data.purposeOfTakingMembership;
		$scope.attitudeOfTheGroupList = result.data.attitudeOfTheGroup;
		$scope.meetingPointOfGroupList = result.data.meetingPointOfGroup;
		$scope.reactionOfSocietyTowardsChildList = result.data.reactionOfSocietyTowardsChild;
		$scope.reactionOfPoliceTowardsChildList = result.data.reactionOfPoliceTowardsChild;
		$scope.suggestionByList = result.data.suggestionBy;
		$scope.followUpByList = result.data.followUpBy;
		$scope.realtionShipWithChildList = result.data.realtionShipWithChild;
		$scope.childEmploymentDetailsList = result.data.childEmploymentDetails;
		
		console.log(result);
		$scope.typeDetailsFetch = true;
		$scope.checkAllFetch();
	}, function(error){
		console.log(error);
	});
	
	$scope.countCategory = function(category){
		if(category.checked == false){
			if(category.id == 181)
				$scope.formInfo.categoryDetails = null;
			else if(category.id == 183)
				$scope.formInfo.categoryDetailsOther = null;
		}
	};
	
	$scope.religionOthers = function(){
		if($scope.formInfo.religionObject.id != 184)
				$scope.formInfo.casteObject = {};
		
		if($scope.formInfo.religionObject.id != 187)
				$scope.formInfo.religionOthers = null;
	};
	
	$scope.childBroughtByWhomFunction = function(){
		if($scope.formInfo.childBroughtBeforeCWCByWhomObject.id != 204)
			$scope.formInfo.childBroughtBeforeCWCByWhomRelationship = null;
	};
	
	$scope.countReasonForLeavingFamily = function(reason){
		if(reason.id == 121 && reason.checked == true){
			$scope.showReasonForLeavingFamilyOtherField = true;
		}
		if(reason.id == 121 && reason.checked == false){
			$scope.showReasonForLeavingFamilyOtherField = false;
			$scope.formInfo.reasonsForLeavingFamilyOthers = null;
		}
	};
	
	$scope.verbalAbuseCount = function(abuse){
		if(abuse.id == 125){
			if(abuse.checked == true)
				$scope.verbalAbuseFlag = true;
			else{
				$scope.verbalAbuseFlag = false;
				$scope.formInfo.verbalAbuseMetByChildOthers = null;
			}
		}
	};
	
	$scope.physicalAbuseCount = function(abuse){
		if(abuse.id == 125){
			if(abuse.checked == true)
				$scope.physicalAbuseFlag = true;
			else{
				$scope.physicalAbuseFlag = false;
				$scope.formInfo.physicalAbuseMetByChildOthers = null;
			}
		}
	};
	
	$scope.sexualAbuseCount = function(abuse){
		if(abuse.id == 125){
			if(abuse.checked == true)
				$scope.sexualAbuseFlag = true;
			else{
				$scope.sexualAbuseFlag = false;
				$scope.formInfo.sexualAbuseMetByChildOthers = null;
			}
		}
	};
	
	$scope.otherAbuseCount = function(abuse){
		if(abuse.id == 125){
			if(abuse.checked == true)
				$scope.otherAbuseFlag = true;
			else{
				$scope.otherAbuseFlag = false;
				$scope.formInfo.otherAbuseMetByChildOthers = null;
			}
		}
	};
	
	$scope.dofCount = function(dof){
		if(dof.id == 125){
			if(dof.checked == true)
				$scope.dofFlag = true;
			else{
				$scope.dofFlag = false;
				$scope.formInfo.illTreatmentDOFOthers = null;
			}
		}
	};
	
	$scope.bmCount = function(bm){
		if(bm.id == 125){
			if(bm.checked == true)
				$scope.bmFlag = true;
			else{
				$scope.bmFlag = false;
				$scope.formInfo.illTreatmentBMOthers = null;
			}
		}
	};
	
	$scope.illOtherCount = function(illOther){
		if(illOther.id == 125){
			if(illOther.checked == true)
				$scope.illOtherFlag = true;
			else{
				$scope.illOtherFlag = false;
				$scope.formInfo.illTreatmentOtherOther = null;
			}
		}
	};
	
	$scope.ciCount = function(ci){
		if(ci.id == 125){
			if(ci.checked == true)
				$scope.ciFlag = true;
			else{
				$scope.ciFlag = false;
				$scope.formInfo.illTreatmentCIOthers = null;
			}
		}
	};
	
	$scope.dpCount = function(dp){
		if(dp.id == 125){
			if(dp.checked == true)
				$scope.dpFlag = true;
			else{
				$scope.dpFlag = false;
				$scope.formInfo.illTreatmentDPOthers = null;
			}
		}
	};
	
	$scope.countExploitationFaced = function(exploitation){
		if(exploitation.id == 150){
			if(exploitation.checked == true)
				$scope.countExploitationFlag = true;
			else{
				$scope.countExploitationFlag = false;
				$scope.formInfo.exploitaionFacedByTheChildOthers = null;
			}
		}
	};
	
	$scope.childStayingPriorToAdmission = function(){
		if($scope.formInfo.childWasStayingWithWhomPriorToAdmission.id != 110)
			$scope.formInfo.childWasStayingWithWhomPriorToAdmissionOther = null;
	};
	
	$scope.countDelinquentBehaviour = function(dBehaviour){
		if(dBehaviour.id == 245){
			if(dBehaviour.checked == true)
				$scope.countDelinquentBehaviourFlag = true;
			else{
				$scope.countDelinquentBehaviourFlag = false;
				$scope.formInfo.detailsOfDelinquentBehaviourOthers = null;
			}
		}
	};
	
	$scope.countDelinquentBehaviourReason = function(dBehaviourReason){
		if(dBehaviourReason.id == 251){
			if(dBehaviourReason.checked == true)
				$scope.countDelinquentBehaviourReasonFlag = true;
			else{
				$scope.countDelinquentBehaviourReasonFlag = false;
				$scope.formInfo.reasonForDelinquentBehaviourOthers = null;
			}
		}
	};
	
	$scope.badHabitCount = function(bHabits){
		if(bHabits.id == 61){
			if(bHabits.checked == true)
				$scope.badHabitCountFlag = true;
			else{
				$scope.badHabitCountFlag = false;
				$scope.formInfo.childBadHabitsOthers = null;
			}
		}
	};
	
	$scope.goodHabitCount = function(gHabits){
		if(gHabits.id == 55){
			if(gHabits.checked == true)
				$scope.goodHabitCountFlag = true;
			else{
				$scope.goodHabitCountFlag = false;
				$scope.formInfo.childGoodHabitsOthers = null;
			}
		}
	};
	
	$scope.savingDetailsCount = function(savings){
		if(savings.id == 263){
			if(savings.checked == true)
				$scope.savingDetailsCountFlag = true;
			else{
				$scope.savingDetailsCountFlag = false;
				$scope.formInfo.detailsOfSavingOthers = null;
			}
		}
	};
	
	$scope.reasonForLeavingSchoolCount = function(reason){
		if(reason.id == 85){
			if(reason.checked == true)
				$scope.reasonForLeavingSchoolCountFlag = true;
			else{
				$scope.reasonForLeavingSchoolCountFlag = false;
				$scope.formInfo.reasonBehindLeavingSchoolOthers = null;
			}
		}
	};
	
	$scope.checkMediumInstruction = function(){
		if($scope.formInfo.schoolMediumInstructionObject.id != 277)
			$scope.formInfo.schoolMediumInstructionOthers = null;
	};
	
	$scope.extraCurricularActivityCount = function(activity){
		if(activity.id == 285){
			if(activity.checked == true)
				$scope.extraCurricularActivityCountFlag = true;
			else{
				$scope.extraCurricularActivityCountFlag = false;
				$scope.formInfo.extraCurricularActivitiesOthers = null;
			}
		}
	};
	
	$scope.detailsOfFriendshipCount = function(frndshp){
		if(frndshp.id == 289){
			if(frndshp.checked == true)
				$scope.detailsOfFriendshipCountFlag = true;
			else{
				$scope.detailsOfFriendshipCountFlag = false;
				$scope.formInfo.friendshipPriorToAdmissionIntoChildrensHomeOthers = null;
			}
		}
	};
	
	$scope.detailsOfMembershipCount = function(group){
		if(group.id == 295){
			if(group.checked == true)
				$scope.detailsOfMembershipCountFlag = true;
			else{
				$scope.detailsOfMembershipCountFlag = false;
				$scope.formInfo.detailOfMembershipInGroupOthers = null;
			}
		}
	};
	
	$scope.purposeOfMembershipCount = function(purpose){
		if(purpose.id == 304){
			if(purpose.checked == true)
				$scope.purposeOfMembershipCountFlag = true;
			else{
				$scope.purposeOfMembershipCountFlag = false;
				$scope.formInfo.purposeOfTakingMembershipInGroupOthers = null;
			}
		}
	};
	
	
	$("#datepicker").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d',
		changeYear: true,
		onSelect:function(date){
			$scope.formInfo.date = date;
		}
	});
//	$("#datepicker1").datepicker({
//		dateFormat : "yy-mm-dd",
//		maxDate : '+0d',
//		changeYear: true,
//		onSelect:function(date){
//			$scope.formInfo.date = date;
//		}
//	});
	$("#dateForHeightWeightChart").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d',
		changeYear: true,
		onSelect:function(date){
			$scope.formInfo.dateMonthYearForHeightWeightChart = date;
		}
	});
	
	
	$scope.validateForm = function(){
		var hour = 0;
		
		if($scope.formInfo.date == undefined){
			$('#datepicker').datepicker('show');
			return false;
		}else if($scope.childImgpath == "resources/img/photo.jpg"){
			document.getElementById('uploadPhotoError').innerHTML = "Please upload an image of the child";
			$('html,body').animate({
		        scrollTop: $('#uploadPhotoError').offset().top - 250},'slow');
			return false;
		}else if($scope.formInfo.presentAge < $scope.formInfo.ageAtTimeOfAdmission){
			$scope.formInfo.presentAge = null;
			document.getElementById('presentage').innerHTML = "Present age cannot be less than age at the time of admission";
			$('html,body').animate({
		        scrollTop: $('#presentAge').offset().top - 250},'slow');
			return false;
		}
			
		finalCount();
		var check = validateHealthStatusOfChild($scope.healthStatsRepetition.length-1);
		
		if(check == false){
			if($scope.healthStatsRepetition.length ==1)
				document.getElementById('cannotAddNew2').innerHTML = "Please fill details for atleast one quarter";
			else
				document.getElementById('cannotAddNew2').innerHTML = "Please fill details for added quarter(s)";
			$('html,body').animate({
		        scrollTop: $('#cannotAddNew2').offset().top - 250},'slow');
			return false;
		}
		
			$scope.formInfo.childImgpath = $scope.childImgpath;
			$scope.formInfo.familyHistoryOfCrimeModels = $scope.historyRepetition;
			$scope.formInfo.childEmploymentDetailsModels = $scope.employRepetition;
			$scope.formInfo.healthStatusOfChildModels = $scope.healthStatsRepetition;
			$scope.formInfo.timeToString = $scope.hr+" : "+$scope.minute+" "+$scope.ap;
			
			if($scope.ap == "PM")
				hour = parseInt($scope.hr)+12;
			else
				hour = parseInt($scope.hr);
			
			$scope.formInfo.time = hour+":"+$scope.minute+":00";
			$scope.formInfo.childId = $scope.childId;
			
			console.log($scope.formInfo);
			
			var abuseCheckBoxList = [$scope.verbalAbusedByList, $scope.physicallAbusedByList,
					                    $scope.sexualAbusedByList,$scope.otherAbusedByList];
//			var errorList1 = ['#verbalError','#physicalError','#sexualError'];
			var noAbuseError = false;
			
			for(var i=0; i<abuseCheckBoxList.length; i++){
				for(var j=0; j<abuseCheckBoxList[i].length; j++){
					if(abuseCheckBoxList[i][j].checked == true){
						noAbuseError = true;
					}
				}
			}
			
			if(noAbuseError == false){
				$('#abuseErrorMain').html("Please select at least one option");
				$('html,body').animate({
				        scrollTop: $('#abuseErrorMain').offset().top - 250},'slow');
				 return false;
			}else{
				document.getElementById('abuseErrorMain').innerHTML = "";
			}
			
			var illCheckBoxList = [ $scope.illTreatmentDOFList,$scope.illTreatmentBMList,$scope.illTreatmentCIList,
			                        	$scope.illTreatmentDPList,];
//			'#denialError','#beatenError','#causingError','#detentionError'
			var noIllError = false;
			
			for(var i=0; i<illCheckBoxList.length; i++){
				for(var j=0; j<illCheckBoxList[i].length; j++){
					if(illCheckBoxList[i][j].checked == true){
						noIllError = true;
					}
				}
			}
			if(noIllError == false){
				$('#illMainError').html("Please select at least one option");
				$('html,body').animate({
				        scrollTop: $('#illMainError').offset().top - 250},'slow');
				 return false;
			}else{
				document.getElementById('illMainError').innerHTML = "";
			}
			
			var checkboxList = [$scope.childCategory, $scope.reasonsLeavingFamilyList,
			                    $scope.socialActivityList,$scope.badHabitsList,$scope.goodHabitsList,
			                    $scope.extraCurricularActivityList,
			                    $scope.friendshipPriorToChildrensHomeList,$scope.majorityFriendTypesList,
			                    $scope.membershipInGroupDetailsList];
			var errorDivList = ['#anyOtherCategoryError','#reasonError',
			                    '#activityError','#badHabitsListError','#goodHabitsListError',
			                    '#activitiesError','#friendshipError',
			                    '#friendsError','#membershipError'];
			var showError = "";
			
			for(var i=0; i < checkboxList.length; i++){
				var noError = false;
				for(var j=0; j<checkboxList[i].length; j++){
					if(checkboxList[i][j].checked == true){
						noError = true;
					}
				}
				if(noError == false){
					showError = errorDivList[i];
					break;
				}
			}	
			for(var i=0; i<errorDivList.length; i++){
				$(errorDivList[i]).html("");
			}
			
			
			if(showError != ""){
				 $(showError).html("please select at least one option");
				 $('html,body').animate({
				        scrollTop: $(showError).offset().top - 250},'slow');
				 return false;
			}
			else if($scope.formInfo.dateMonthYearForHeightWeightChart == undefined){
				$( "#dateForHeightWeightChart" ).datepicker("show");
			}
	else{
					document.getElementById('noofYearsVocationalError').innerHTML = "";
					document.getElementById('cannotAddNew').innerHTML = "";
					document.getElementById('cannotAddNew1').innerHTML = "";
					document.getElementById('presentage').innerHTML = "";
					document.getElementById('limitExceeded').innerHTML	= "";
					document.getElementById('limitExceeded1').innerHTML = "";
					document.getElementById('limitExceeded2').innerHTML = "";
					document.getElementById('childAgeError').innerHTML = "";
				 $('#confirmationModalForCaseHistory').modal('show');
			}
	};
	
	$scope.resetInput = function(model, key,errorId){
    	if(model[key] == 0){
    		model[key] = undefined;
        	document.getElementById(errorId).innerHTML = "";
    	}
    };
    
    $scope.resetInput1 = function(model, key, errorId, message){
    	if(model[key] == 0){
    		model[key] = undefined;
        	document.getElementById(errorId).innerHTML = message+" cannot be zero";
    	}else{
    		document.getElementById(errorId).innerHTML = "";
    	}
    };
	
	$scope.saveData = function(){
		$(".loader").css("display", "block");
		$http.post('saveCaseHistory', $scope.formInfo)
		.then(function(result){
			console.log(result);
			$(".loader").css("display", "none");
			$('#printCaseHistory').modal('show');
			checkSessionOut(result.data);
		}, function(error){
			console.log(error);
		});
	};
	
	 $scope.childImgpath = "resources/img/photo.jpg";
	 $scope.clearImageUploadCaseField = function(type){
		 switch(type){
			case 'childImgpath':
				$timeout(function() {
					$scope.childImgpath ="resources/img/photo.jpg";
					angular.element('#uploadChildPhoto').val(null);
			    }, 100);
				break;
		}
	 };
	 $scope.getBase64=function(file, type) {
		 	var reader = new FileReader();
		 	reader.readAsDataURL(file);
		 	reader.onload = function () {
		 		switch(type){
		 			case 'childImgpath':
		 				$timeout(function() {
		 					$scope.childImgpath=reader.result;
		 			    }, 100);
		 				break;
		 		}
		 	};
		 	reader.onerror = function (error) {
		 		console.log('Error: ', error);
		 	};
		};
		$scope.getReport = function ($files,type) {
			var validFormats = ["jpg","JPG","jpeg","JPEG","png","PNG"];
			
			if($files.length == 0)
				$scope.clearImageUploadCaseField(type);
			
			else{
		    	 var ext = $files[0].name.split(".").pop();
		    	 if(validFormats.indexOf(ext) == -1){
		    		$('#errorImgCaseModal').modal('show');
		    		$scope.clearImageUploadCaseField(type);
		         }else{
		        	 $scope.getBase64($files[0], type);
		         }
			}
		};
	
	$scope.reDirect = function(){
		$window.location.href = '/CPIS/ccts';
	};
	
	finalCount = function(){
		$scope.formInfo.category = "";
		$scope.formInfo.categoryName = "";
		$scope.formInfo.reasonsForLeavingFamily = "";
		$scope.formInfo.reasonsForLeavingFamilyName = "";
		$scope.formInfo.verbalAbuseMetByChild = "";
		$scope.formInfo.verbalAbuseMetByChildName = "";
		$scope.formInfo.physicalAbuseMetByChild = "";
		$scope.formInfo.physicalAbuseMetByChildName = "";
		$scope.formInfo.sexualAbuseMetByChild = "";
		$scope.formInfo.sexualAbuseMetByChildName = "";
		$scope.formInfo.otherAbuseMetByChild = "";
		$scope.formInfo.otherAbuseMetByChildNames = "";
		$scope.formInfo.illTreatmentDOF = "";
		$scope.formInfo.illTreatmentDOFName = "";
		$scope.formInfo.illTreatmentBM = "";
		$scope.formInfo.illTreatmentBMName = "";
		$scope.formInfo.illTreatmentCI = "";
		$scope.formInfo.illTreatmentCIName = "";
		$scope.formInfo.illTreatmentDP = "";
		$scope.formInfo.illTreatmentDPName = "";
		$scope.formInfo.illTreatmentOther = "";
		$scope.formInfo.illTreatmentOtherNames = "";
		$scope.formInfo.exploitaionFacedByTheChild = "";
		$scope.formInfo.exploitaionFacedByTheChildName = "";
		$scope.formInfo.householdArticlesOBF = "";
		$scope.formInfo.householdArticlesOBFName = "";
		$scope.formInfo.vehiclesOBF = "";
		$scope.formInfo.vehiclesOBFName = "";
		$scope.formInfo.socialActivitesOfFamilyMembers = "";
		$scope.formInfo.socialActivitesOfFamilyMembersName = "";
		$scope.formInfo.parentalCareTowardsChildBeforeAdmission = "";
		$scope.formInfo.parentalCareTowardsChildBeforeAdmissionName = "";
		$scope.formInfo.detailsOfDelinquentBehaviour = "";
		$scope.formInfo.detailsOfDelinquentBehaviourName = "";
		$scope.formInfo.reasonForDelinquentBehaviour = "";
		$scope.formInfo.reasonForDelinquentBehaviourName = "";
		$scope.formInfo.childBadHabits = "";
		$scope.formInfo.childBadHabitsName = "";
		$scope.formInfo.childGoodHabits = "";
		$scope.formInfo.childGoodHabitsName = "";
		$scope.formInfo.detailsOfIncomeUtilization = "";
		$scope.formInfo.detailsOfIncomeUtilizationName = "";
		$scope.formInfo.detailsOfSaving = "";
		$scope.formInfo.detailsOfSavingName = "";
		$scope.formInfo.reasonBehindLeavingSchool = "";
		$scope.formInfo.reasonBehindLeavingSchoolName = "";
		$scope.formInfo.extraCurricularActivities = "";
		$scope.formInfo.extraCurricularActivitiesName = "";
		$scope.formInfo.friendshipPriorToAdmissionIntoChildrensHome = "";
		$scope.formInfo.friendshipPriorToAdmissionIntoChildrensHomeName = "";
		$scope.formInfo.majorityFriendsAre = "";
		$scope.formInfo.majorityFriendsAreName = "";
		$scope.formInfo.detailOfMembershipInGroup = "";
		$scope.formInfo.detailOfMembershipInGroupName = "";
		$scope.formInfo.purposeOfTakingMembershipInGroup = "";
		$scope.formInfo.purposeOfTakingMembershipInGroupName = "";
		
		var finalArrayList = [$scope.childCategory,$scope.reasonsLeavingFamilyList,$scope.verbalAbusedByList,$scope.physicallAbusedByList,
		                      $scope.sexualAbusedByList,$scope.otherAbusedByList,$scope.illTreatmentDOFList,$scope.illTreatmentBMList,$scope.illTreatmentCIList,
		                      $scope.illTreatmentDPList,$scope.illTreatmentOtherList,$scope.exploitationFacedList,$scope.householdArticlesList,
		                      $scope.vehiclesOwnedList,$scope.socialActivityList,$scope.parentalCareTowardsChildList,$scope.delinquentBehaviourList,
		                      $scope.delinquentbehaviourReasonList,$scope.badHabitsList,$scope.goodHabitsList,$scope.incomeUtilizationList,$scope.detailsOfSavingList,
		                      $scope.reasonLeavingSchoolList,$scope.extraCurricularActivityList,$scope.friendshipPriorToChildrensHomeList,
		                      $scope.majorityFriendTypesList,$scope.membershipInGroupDetailsList,$scope.purposeOfTakingMembershipList];
		
		for(var j=0; j<finalArrayList.length; j++){
			var tempList = finalArrayList[j];
			for(var i=0; i<tempList.length; i++){
				if(tempList[i].checked == true){
					switch(j){
					case 0:
						$scope.formInfo.category += tempList[i].id + ",";
						$scope.formInfo.categoryName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 1:
						$scope.formInfo.reasonsForLeavingFamily += tempList[i].id + ",";
						$scope.formInfo.reasonsForLeavingFamilyName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 2:
						$scope.formInfo.verbalAbuseMetByChild += tempList[i].id + ",";
						$scope.formInfo.verbalAbuseMetByChildName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 3:
						$scope.formInfo.physicalAbuseMetByChild += tempList[i].id + ",";
						$scope.formInfo.physicalAbuseMetByChildName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 4:
						$scope.formInfo.sexualAbuseMetByChild += tempList[i].id + ",";
						$scope.formInfo.sexualAbuseMetByChildName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 5:
						$scope.formInfo.otherAbuseMetByChild += tempList[i].id + ",";
						$scope.formInfo.otherAbuseMetByChildNames += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 6:
						$scope.formInfo.illTreatmentDOF += tempList[i].id + ",";
						$scope.formInfo.illTreatmentDOFName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 7:
						$scope.formInfo.illTreatmentBM += tempList[i].id + ",";
						$scope.formInfo.illTreatmentBMName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 8:
						$scope.formInfo.illTreatmentCI += tempList[i].id + ",";
						$scope.formInfo.illTreatmentCIName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 9:
						$scope.formInfo.illTreatmentDP += tempList[i].id + ",";
						$scope.formInfo.illTreatmentDPName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 10:
						$scope.formInfo.illTreatmentOther += tempList[i].id + ",";
						$scope.formInfo.illTreatmentOtherNames += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 11:
						$scope.formInfo.exploitaionFacedByTheChild += tempList[i].id + ",";
						$scope.formInfo.exploitaionFacedByTheChildName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 12:
						$scope.formInfo.householdArticlesOBF += tempList[i].id + ",";
						$scope.formInfo.householdArticlesOBFName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 13:
						$scope.formInfo.vehiclesOBF += tempList[i].id + ",";
						$scope.formInfo.vehiclesOBFName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 14:
						$scope.formInfo.socialActivitesOfFamilyMembers += tempList[i].id + ",";
						$scope.formInfo.socialActivitesOfFamilyMembersName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 15:
						$scope.formInfo.parentalCareTowardsChildBeforeAdmission += tempList[i].id + ",";
						$scope.formInfo.parentalCareTowardsChildBeforeAdmissionName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 16:
						$scope.formInfo.detailsOfDelinquentBehaviour += tempList[i].id + ",";
						$scope.formInfo.detailsOfDelinquentBehaviourName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 17:
						$scope.formInfo.reasonForDelinquentBehaviour += tempList[i].id + ",";
						$scope.formInfo.reasonForDelinquentBehaviourName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 18:
						$scope.formInfo.childBadHabits += tempList[i].id + ",";
						$scope.formInfo.childBadHabitsName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 19:
						$scope.formInfo.childGoodHabits += tempList[i].id + ",";
						$scope.formInfo.childGoodHabitsName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 20:
						$scope.formInfo.detailsOfIncomeUtilization += tempList[i].id + ",";
						$scope.formInfo.detailsOfIncomeUtilizationName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 21:
						$scope.formInfo.detailsOfSaving += tempList[i].id + ",";
						$scope.formInfo.detailsOfSavingName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 22:
						$scope.formInfo.reasonBehindLeavingSchool += tempList[i].id + ",";
						$scope.formInfo.reasonBehindLeavingSchoolName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 23:
						$scope.formInfo.extraCurricularActivities += tempList[i].id + ",";
						$scope.formInfo.extraCurricularActivitiesName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 24:
						$scope.formInfo.friendshipPriorToAdmissionIntoChildrensHome += tempList[i].id + ",";
						$scope.formInfo.friendshipPriorToAdmissionIntoChildrensHomeName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 25:
						$scope.formInfo.majorityFriendsAre += tempList[i].id + ",";
						$scope.formInfo.majorityFriendsAreName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 26:
						$scope.formInfo.detailOfMembershipInGroup += tempList[i].id + ",";
						$scope.formInfo.detailOfMembershipInGroupName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					case 27:
						$scope.formInfo.purposeOfTakingMembershipInGroup += tempList[i].id + ",";
						$scope.formInfo.purposeOfTakingMembershipInGroupName += $scope.langType == 0 ? tempList[i].name + "," : tempList[i].typeNameHindi + ",";
						break;
					}
				}
			}
		}
		
		var variableList = ["category","categoryName","reasonsForLeavingFamily","reasonsForLeavingFamilyName","verbalAbuseMetByChild","verbalAbuseMetByChildName",
		                    "physicalAbuseMetByChild","physicalAbuseMetByChildName","sexualAbuseMetByChild","sexualAbuseMetByChildName","otherAbuseMetByChild",
		                    "otherAbuseMetByChildNames","illTreatmentDOF","illTreatmentDOFName","illTreatmentBM","illTreatmentBMName","illTreatmentCI",
		                    "illTreatmentCIName","illTreatmentDP","illTreatmentDPName","illTreatmentOther","illTreatmentOtherNames","exploitaionFacedByTheChild",
		                    "exploitaionFacedByTheChildName","householdArticlesOBF","householdArticlesOBFName","vehiclesOBF","vehiclesOBFName",
		                    "socialActivitesOfFamilyMembers","socialActivitesOfFamilyMembersName","parentalCareTowardsChildBeforeAdmission",
		                    "parentalCareTowardsChildBeforeAdmissionName","detailsOfDelinquentBehaviour","detailsOfDelinquentBehaviourName","reasonForDelinquentBehaviour",
		                    "reasonForDelinquentBehaviourName","childBadHabits","childBadHabitsName","childGoodHabits","childGoodHabitsName","detailsOfIncomeUtilization",
		                    "detailsOfIncomeUtilizationName","detailsOfSaving","detailsOfSavingName","reasonBehindLeavingSchool","reasonBehindLeavingSchoolName",
		                    "extraCurricularActivities","extraCurricularActivitiesName","friendshipPriorToAdmissionIntoChildrensHome",
		                    "friendshipPriorToAdmissionIntoChildrensHomeName","majorityFriendsAre","majorityFriendsAreName","detailOfMembershipInGroup",
		                    "detailOfMembershipInGroupName","purposeOfTakingMembershipInGroup","purposeOfTakingMembershipInGroupName"];
		
		for(var i=0;i<variableList.length;i++){
			$scope.formInfo[variableList[i]] = $scope.formInfo[variableList[i]].substring(0, $scope.formInfo[variableList[i]].length-1);
		}
	};
	
	
	/*
	 * Print form code begins here
	 */
	
	//====================================download pdf============================
	$scope.printCaseHistoryData = function(){
		$(".loader").css("display", "block");
		$scope.viewPage = true;
		
//		var hour = parseInt($scope.hr);
//		if(hour > 12)
//			hour -= 12;
//		else if(hour == 0)
//			hour = 12;
		$scope.time = $scope.hr+":"+$scope.minute+":"+$scope.ap;
		
		var serverURL = $scope.langType == 0 ? 'downloadPDFDataReportForCaseHistoryCCI?type=en' : 'downloadPDFDataReportForCaseHistoryCCI?type=hi_IN';
		
		$scope.printCaseData =$scope.formInfo;
		$scope.printCaseData.childSex = $scope.lang=='en'?$scope.prefetchData.sexObject.name:$scope.prefetchData.sexObject.typeNameHindi;
		$scope.printCaseData.programType = $scope.prefetchData.programType;
		commonService.downloadFile(serverURL, $scope.printCaseData);
    };
    
    $scope.validateName = function (name, errorId){
		if(errorId == "ageatAdmission" && name > 18 ){
			document.getElementById(errorId).innerHTML = "Age cannot be more than 18";
		    document.caseHistory.admissionAge.focus() ;
		    $scope.formInfo.ageAtTimeOfAdmission = null;
		    return false;
		}else if(errorId == "ageatAdmission" && name == 0 ){
			document.getElementById(errorId).innerHTML = "Age cannot 0";
		    document.caseHistory.admissionAge.focus() ;
		    $scope.formInfo.ageAtTimeOfAdmission = null;
		    return false;
		}
		else if(errorId == "presentage" && name > 18){
			document.getElementById(errorId).innerHTML = "Age cannot be more than 18";
		        document.caseHistory.presentAge.focus() ;
		        $scope.formInfo.presentAge=null;
		        return false;
		}else if(errorId == "presentage" && name == 0){
			document.getElementById(errorId).innerHTML = "Age cannot be 0";
	        document.caseHistory.presentAge.focus() ;
	        $scope.formInfo.presentAge=null;
	        return false;
		}
		else if(errorId == "childAgeError" && (name < 12 || name > 18)){
			document.getElementById(errorId).innerHTML = "Age cannot be less than 12 and more than 18";
		    $scope.formInfo.childAttainPubertyAge=null;
		    return false;
		}
		if(name == undefined){
			if(errorId == "noofYearsError"){
				document.getElementById(errorId).innerHTML = "Year cannot be more than 99";
				document.caseHistory.noofYears.focus();
				$scope.formInfo.educationalAttainmentNoOfYears = "";
				return false;
			}else if (errorId == "noofYearsVocationalError"){
				document.getElementById(errorId).innerHTML = "Year cannot be more than 99";
				document.caseHistory.noofYearsVocational
						.focus();
				$scope.formInfo.voactionalTrainingNoOfYears = "";
				return false;
			}else if (errorId == "physicalConditionerror"){
				document.getElementById(errorId).innerHTML = "Enter only characters";
				document.caseHistory.physicalCondition.focus();
				$scope.formInfo.physicalCondition = "";
				return false;
			}
		}else{
			document.getElementById(errorId).innerHTML = "";
			return true;
		}
     };
     $scope.resetInput = function(model,key,errorId){
     	if(model[key] == 0){
     		model[key] = undefined;
         	document.getElementById(errorId).innerHTML = "Age cannot be blank";
     	}
     	
     };
     $scope.resetInputyear = function(model,key,errorId){
      	if(model[key] == 0){
      		model[key] = undefined;
          	document.getElementById(errorId).innerHTML = "Year cannot be blank";
      	}
      	
      };
      
     $("#admissionAge").keypress(function(event) {
 		//if (event.which == 101 || event.which == 69)
         if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
             return false;
         }
     });
     $("#presentAge").keypress(function(event) {
  		//if (event.which == 101 || event.which == 69)
          if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
              return false;
          }
      });
     $("#noofYears").keypress(function(event) {
   		//if (event.which == 101 || event.which == 69)
           if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
               return false;
           }
       });
     $("#childAge").keypress(function(event) {
    		//if (event.which == 101 || event.which == 69)
            if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
                return false;
            }
        });
     $("#noofYearsVocational").keypress(function(event) {
 		//if (event.which == 101 || event.which == 69)
         if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
             return false;
         }
     });
     
     $scope.deleteAccordionConfirmation = function(accordionType){
    	 switch(accordionType){
    	 case 'familyHistory':
    		 $scope.whichAccord = accordionType;
    		 break;
    	 case 'empDetails':
    		 $scope.whichAccord = accordionType;
    		 break;
    	 case 'healthStatus':
    		 $scope.whichAccord = accordionType;
    		 break;
    	 }
    	 $('#accordionDeletionModal').modal('show');
     };
     
     $scope.deleteAccordion = function(whichAccord){
    	 switch(whichAccord){
    	 case 'familyHistory':
    		 $scope.deleteNewDetails();
    		 break;
    	 case 'empDetails':
    		 $scope.deleteNewEmpDetails();
    		 break;
    	 case 'healthStatus':
    		 $scope.deleteNewQuarterDetails();
    		 break;
    	 }
     };
     
     $scope.clearCheckList = function(list, method, key){
    	 if(!key){
	    	for(var i=0; i<$scope[list].length; i++){
	    		if($scope[list][i].checked == true){
	    			$scope[list][i].checked = false;
	    			$scope[method]($scope.otherAbusedByList[i]);
	    		}
	    	}
    	 }
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
				
				if(clean == "0"){
					clean = '';
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				else if (val !== clean) {
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
				if (angular.isUndefined(val)) {
					val = '';
				}
				
				var clean = val.toString().replace(/[^0-9]/g, '');
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
directive('dynamicDigit', function () {
	
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
					if(clean.length>6 ){
						num =clean.slice(0,6);
						clean= num;
					}
					
				}
				
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				
				clean = parseInt(clean);
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
directive('dynamicDouble', function () {
	
	return {
		restrict: 'A',
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if(!ngModelCtrl) {
				return; 
			}
			
			ngModelCtrl.$parsers.push(function(val) {
				var temp = val;
				
				if (angular.isUndefined(temp) || temp === null || isNaN(temp)) {
					temp = '';
				}else{
					var clean = temp.toString().replace(/[^0-9 .]/g, '');
					var temp1 = parseFloat(clean);
					if(isNaN(temp1)){
						temp = '';
						val = '';
					}
				}
				
				ngModelCtrl.$setViewValue(val);
				ngModelCtrl.$render();
				return temp;
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
directive('oneTwentyEight', function () {

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
	            	 if(clean.length>128 ){
	            		 num =clean.slice(0,128);
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


