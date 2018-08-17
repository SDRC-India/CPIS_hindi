/*
 * @Author souravnath@sdrc.co.in.
 * This js is responsible for registering a new child into the system and generate a unique id for
 * the child. This file is also responsible for printing and downloading the form in pdf format.
 */
myAppConstructor.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);
myAppConstructor.controller('CICLSocialBackgroundReportController', function($scope, $http, $filter, commonService, $window, $timeout,gettextCatalog) {
		
	$scope.lang="en";
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
	};	

	$scope.selectedChildId=$('#modelValue').val();
	$(".loader").css("display", "block");
	console.log($scope.selectedChildId);
	$scope.cwcList=[];
	$scope.otherOrganizationFlag = false;
	$scope.childId = "";
	$scope.child={};
	$scope.ciclRegDisabled=false;
	$scope.regex = '/^[0-9]{10,10}$/';
	
	$scope.getGridMenuItemsSuccessful = false;
	$scope.getTypeDetailsSuccessful = false;
	$scope.getChildRegMstDataSuccessful = false;

	
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.getTypeDetailsSuccessful 
				&& $scope.getChildRegMstDataSuccessful && $scope.getTypeDetailsSuccessful
				&& $scope.getSocialBackgroundReportSuccessful){
			$(".loader").css("display", "none");
		}
	};
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
		console.log(result);
	});
	$scope.soiFdObjRaw = 
		[
		    {
				"name" : "(i) Name",
				"value" : null,
				"type" : "text",
				"placeholder":"Enter name"
			}, {
				"name" : "(ii) Age",
				"value" : "",
				"type" : "ageNumber",
				"placeholder":"Enter age(Maximum should be 99)"
			}, {
				"name" : "(iii) Relationship",
				"value" : null,
				"type" : "text",
				"placeholder":"Enter relationship"
			}, {
				"checked" :	false,
				"desc" : "sexNumber",
				"id" : null,
				"key":null,
				"name" : "(iv) Sex",
				"value" : null
			}, {
				"checked" :	false,
				"desc" : "eduDropdown",
				"id" : null,
				"key":null,
				"name" : "(v) Education",
				"value" : null
			}, {
				"name" : "(vi) Occupation",
				"value" : null,
				"type" : "text",
				"placeholder":"Enter occupation"
			}, {
				"name" : "(vii) Income",
				"value" : "",
				"type" : "incomeNumber",
				"placeholder":"Enter income"
			}, {
				"name" : "(viii) Health status",
				"value" : null,
				"type" : "text",
				"placeholder":"Enter health status"
			}, {
				"name" : "(ix) History of mental illness",
				"value" : null,
				"type" : "text",
				"placeholder":"Enter history of mental illness"
			}, {
				"name" : "(x) Addiction",
				"value" : null,
				"type" : "text",
				"placeholder":"Enter addiction"
			}
		];
	$scope.soiFdObj = JSON.parse(JSON.stringify($scope.soiFdObjRaw));
	
	$scope.familyDetailsRepetition = {"1":""};
	var fdIndex = 1;
	$scope.familyDetailsRepetition[fdIndex] = JSON.parse(JSON.stringify($scope.soiFdObjRaw));
	$scope.familyDetailsRepetitionArray = $.map($scope.familyDetailsRepetition, function(value, index) {
	    return [value];
	});
	
	$("#entryDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear: true ,
		onSelect: function(date) {
			$scope.formInfo.entryDate = date;
		}	
	});
    //This will hide the DIV by default.
    $scope.IsVisible = false;
    
    $scope.toggleGroup = function(activity) {
    	if ($scope.isGroupShown(activity)) {
      		$scope.shownGroup = null;
    	} else {
	      	$scope.shownGroup = activity;
    	}
  	};

  	$scope.isGroupShown = function(activity) {
  		if($scope.shownGroup == activity){
  			return true;
  		}else{
  			return false;
  		}
    	//return $scope.shownGroup == activity;
  	};
    $scope.ShowPassport = function (value) {
        //If DIV is visible it will be hidden and vice versa.
        $scope.IsVisible = value == "Y";
    };
    
    $scope.showSelection = function (value) {
        //If DIV is visible it will be hidden and vice versa.
        $scope.IsChildMarriedVisible = value == "Yes";
    };
    
    $scope.addNewDetails = function(){
    	
    	if($scope.familyDetailsRepetitionArray.length != 10){
    		$scope.familyDetailsRepetitionArray[($scope.familyDetailsRepetitionArray.length-1)+1] = JSON.parse(JSON.stringify($scope.soiFdObjRaw));
    	}else{
    		document.getElementById('maximumLimit6Error').innerHTML = "You can add details of maximun <b>ten</b> family members";
    	}
    };
    
    $scope.deleteErrorMsg = function(){
    	document.getElementById('maximumLimit6Error').innerHTML = "";
    };
    
     $scope.deleteNewDetails = function(){
    	$scope.maximumLimit6 = false;
    	document.getElementById('maximumLimit6Error').innerHTML = "";
	    if($scope.familyDetailsRepetitionArray.length > 1){
	    	$scope.familyDetailsRepetitionArray.pop();
	    	$scope.soiFdObj.pop();
	    }
      };
 	  $scope.dynamicBlur = function (value,objIndex,index) {
	  		$scope.familyDetailsRepetitionArray[objIndex][index].value =value.trim();  
	  }
      $scope.blur = function (value,name) {
    	  if(name=="otherFamilyInvolvementInOffence"){
        	  $scope.formInfo.otherFamilyInvolvementInOffence=value.trim();  
    	  }else if(name=="otherBadHabits"){
        	  $scope.formInfo.otherBadHabits=value.trim();  
    	  }else if(name=="otherDrugBadHabits"){
        	  $scope.formInfo.otherDrugBadHabits=value.trim();  
    	  }else if(name=="otherGoodHabits"){
        	  $scope.formInfo.otherGoodHabits=value.trim();  
    	  }else if(name=="otherAbused"){
        	  $scope.formInfo.otherAbused=value.trim();  
    	  }else if(name=="otherVerbalAbuse"){
        	  $scope.formInfo.otherVerbalAbuse=value.trim();  
    	  }else if(name=="otherPhysicalAbuse"){
        	  $scope.formInfo.otherPhysicalAbuse=value.trim();  
    	  }else if(name=="otherSexualAbuse"){
        	  $scope.formInfo.otherSexualAbuse=value.trim();  
    	  }else if(name=="otherInOtherAbuse"){
        	  $scope.formInfo.otherInOtherAbuse=value.trim();  
    	  }else if(name=="otherVictimOfOffence"){
        	  $scope.formInfo.otherVictimOfOffence=value.trim();  
    	  }else if(name=="otherChildDrugPeddling"){
        	  $scope.formInfo.otherChildDrugPeddling=value.trim();  
    	  }else if(name=="otherDifferentlyAbledType"){
        	  $scope.formInfo.otherDifferentlyAbledType=value.trim();  
    	  }else if(name=="otherSchoolLeavingReason"){
        	  $scope.formInfo.otherSchoolLeavingReason=value.trim();  
    	  }
    	  
      }
      if($scope.selectedChildId != undefined){
      	$scope.getChildByIdSuccessful = false;
      	//$(".loader").css("display", "block");
  	    $http.get("getChildById?selectedChildId="+$scope.selectedChildId).
  	    then(function(result){
  	    	$scope.getChildByIdSuccessful = true;
  			//$scope.checkAllDataSuccessful();
  	    	if(result.data.finalOrderFilled==1){
  	    		$scope.finalOrder = true;
  	    	}
  	    	else
  	    		$scope.finalOrder = false;
  	    });
      };
    $http.get('getTypeDetails').
    then(function(result){
    	$scope.getTypeDetailsSuccessful = true;
		$scope.checkAllDataSuccessful();
 	   	   $scope.reportPreparedBy = result.data.reportPreparedBy;
 	  	   $scope.childSex = result.data.childSex;
 	  	   $scope.differentlyAbledType = result.data.differentlyAbledType;
 		   $scope.religionList = result.data.religionList;
 		   $scope.casteList = result.data.casteList;
 	  	   $scope.familyMemberSex = result.data.genderList;
 	  	   
 	  	   $scope.relnFatherMother = result.data.familyMemberRelationship;
 	  	   $scope.relnFatherChild = result.data.familyMemberRelationship;
 	  	   $scope.relnMotherChild = result.data.familyMemberRelationship;
 	  	   $scope.relnFatherSiblings = result.data.familyMemberRelationship;
 	  	   $scope.relnMotherSiblings = result.data.familyMemberRelationship;
 	  	   $scope.relnChildSiblings = result.data.familyMemberRelationship;
 	  	   $scope.relnChildRelative = result.data.familyMemberRelationship;
 	  	   
 	  	   $scope.goodHabits = result.data.goodHabits;
 	  	   $scope.badHabits = result.data.badHabits;
 	  	   $scope.education = result.data.childEducationDtls;
 	  	   $scope.schoolType = result.data.childSchoolDtls;
	  	   
 	  	   $scope.reasonLeavingSchool = result.data.reasonLeavingSchool;
 	  	   $scope.majorityFriendTypes = result.data.majorityFriendTypes;
 	  	   
 	  	   $scope.healthStatus = result.data.childHealthStatus;
 	  	   
 	  	   $scope.withWhomChildWasStaying = result.data.childWasStayingWith;
 	  	   $scope.reasonsLeavingFamily = result.data.reasonsLeavingFamily;
 	  	   $scope.abusedBy = result.data.abusedBy;
 	  	   $scope.vebalAbusedBy = JSON.parse(JSON.stringify($scope.abusedBy));
 	  	   $scope.physicalAbusedBy = JSON.parse(JSON.stringify($scope.abusedBy));
 	  	   $scope.sexualAbusedBy = JSON.parse(JSON.stringify($scope.abusedBy));
 	  	   $scope.illTreatedBy = result.data.abusedBy;
 	  	   $scope.dofIllTreatedBy = JSON.parse(JSON.stringify($scope.illTreatedBy));
 	  	   $scope.bmIllTreatedBy = JSON.parse(JSON.stringify($scope.illTreatedBy));	    	  	   
 	  	   $scope.ciIllTreatedBy = JSON.parse(JSON.stringify($scope.illTreatedBy));
 	  	   $scope.dpIllTreatedBy = JSON.parse(JSON.stringify($scope.illTreatedBy));
 	  	   $scope.exploitationFacedBy = result.data.exploitationFaced;
 	  	   $scope.institutionDocType = result.data.institutionDocType;
 	  	   
 	  	 console.log(result.data);
    	   }, function(error){
    		   console.log(error);
    	   });
    $http.get("getChildRegMstData").
    then(function(result){
    	$scope.getChildRegMstDataSuccessful = true;
    	$scope.checkAllDataSuccessful();
	   	$scope.cwcList=result.data.value.cwcList;
	    $scope.cciList=result.data.value.cciList;
	    $scope.sexList = result.data.value.sexList;
	    $scope.ageList = result.data.value.ageList;
	    $scope.organizationType = result.data.value.organizationType;
 	  
   	    console.log(result);
    });	 
    $http.get('getTypeDetails').
    then(function(result){
    	$scope.getTypeDetailsSuccessful = true;
    	$scope.checkAllDataSuccessful();
    	$scope.childSex = result.data.childSex;
	console.log(result.data);
   }, function(error){
	   console.log(error);
   });
    
    $scope.redirectForm=function(url){
		if(url == "child_registration"  || url=="reportSummary" || url=="constitutionofSociety"){
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

    $scope.min 	=	["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"];
    $scope.hour =	["01","02","03","04","05","06","07","08","09","10","11","12"];
    $scope.ampm	=	["AM", "PM"];
   
    $scope.ShowFamilyInvolvementInOffence = function (value) {
        $scope.showFamilyInvolvementInOffence = value == "Yes";
        if(value=="No"){
			$scope.formInfo.otherFamilyInvolvementInOffence=null;
		}
    };
    $scope.ShowAbused = function (value) {
        $scope.showAbused = value == "Yes";
        if(value=="No"){
		   $scope.formInfo.otherAbused=null;
		   for(var i=0; i<$scope.vebalAbusedBy.length; i++){
	 		  $scope.vebalAbusedBy[i].checked = false;
	 	   }
		   $scope.verbalAbusedByArr = [];
	       $scope.verbalAbusedNameString = "";
		   $scope.verbalAbusedString = "";
		   $scope.formInfo.otherVerbalAbuse = null;
		   
	       for(var i=0; i<$scope.physicalAbusedBy.length; i++){
	 		 $scope.physicalAbusedBy[i].checked = false;
	 	   }
	       $scope.physicalAbusedByArr = [];
	       $scope.physicalAbusedNameString = "";
	       $scope.physicalAbusedString = "";
		   $scope.formInfo.otherPhysicalAbuse = null;
		   
	       for(var i=0; i<$scope.sexualAbusedBy.length; i++){
	 		   $scope.sexualAbusedBy[i].checked = false;
	 	   }
	       $scope.sexualAbusedByArr = [];
	       $scope.sexualAbusedNameString = "";
	       $scope.sexualAbusedString = "";
		   $scope.formInfo.otherSexualAbuse = null;
		   
		   $scope.formInfo.otherInOtherAbuse = null;
		}
    };
    $scope.ShowOtherVictimOfOffence = function (value) {
        $scope.showOtherVictimOfOffence = value == "Yes";
        if(value=="No"){
			$scope.formInfo.otherVictimOfOffence=null;
		}
    };
    $scope.ShowChildDrugPeddling = function (value) {
        $scope.showChildDrugPeddling = value == "Yes";
        if(value=="No"){
			$scope.formInfo.otherChildDrugPeddling=null;
		}
    };
    $scope.showcast=false;
    $scope.showOtherCast=false;
    $scope.showReligionCast=function(){
    	if($scope.formInfo.religionObject.id==184){
    		$scope.showcast=true;
    		$scope.showOtherCast=false;
    	}else if($scope.formInfo.religionObject.id==187){
    		$scope.showcast=false;
    		$scope.showOtherCast=true;
    	}
    	else{
    		$scope.showcast=false;
    		$scope.showOtherCast=false;
    	}
    };
	$scope.religionOthers = function(){
		if($scope.formInfo.religionObject.id != 184)
				$scope.formInfo.casteObject = {};
		
		if($scope.formInfo.religionObject.id != 187)
				$scope.formInfo.casteOtherType = null;
	};
	
	$scope.differentlyAbledUncheck=function(){
    	if($scope.differentlyAbledType != null){
			for(var j=0; j < $scope.differentlyAbledType.length; j++){
			  $scope.differentlyAbledType[j].checked = false;
			}
   		}
    	$scope.arr = [];
    	$scope.differentlyAbledTypeString = "";
 	    $scope.keyString = "";
    	$scope.formInfo.otherDifferentlyAbledType = null;
	};
	
	   
    $scope.differentlyAbledYes=true;
    $scope.differentlyAbledNo=false;	
    
    $scope.familyInvolvementInOffenceYes=true;
    $scope.familyInvolvementInOffenceNo=false;

    $scope.usedByFamilyYes=true;
    $scope.usedByFamilyNo=false;
    
    $scope.usedBySelfYes=true;
    $scope.usedBySelfNo=false;
    
    $scope.usedBySelfDressYes=true;
    $scope.usedBySelfDressNo=false;
    
    $scope.usedBySelfDrugYes=true;
    $scope.usedBySelfDrugNo=false;
    
    $scope.usedBySelfGamblingYes=true;
    $scope.usedBySelfGamblingNo=false;
    
    $scope.usedBySelfAlcoholYes=true;
    $scope.usedBySelfAlcoholNo=false;
    
    $scope.usedBySelfSmokingYes=true;
    $scope.usedBySelfSmokingNo=false;
    
    $scope.usedBySelfSavingsYes=true;
    $scope.usedBySelfSavingsNo=false;
    
    $scope.abusedYes=true;
    $scope.abusedNo=false;
    
    $scope.victimOfOffenceYes=true;
    $scope.victimOfOffenceNo=false;
    
    $scope.childDrugPeddlingYes=true;
    $scope.childDrugPeddlingNo=false;
    
    $scope.formInfo={};
    $scope.noClicked = function(){
 	   $scope.checkFlag = false;
    };
    $scope.yesClicked = function(){
 	   $scope.checkFlag = true;
    };
    
    $scope.validateFDAge = function (errorId, obj){
 	   if(errorId=='familyMemberAgeError' && obj.value > 99){
   	   	    obj.value="";
            return false;
 	   }
    };
    
    $scope.validateFdArrAge = function (name, errorId, FdIndex, incomeIndex){
 	   if(Number(name) > 99){
 		   var ss = ($scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value).toString();
 		   $scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value = Number(ss.slice(0, 2));
 		   document.getElementById('familyMemberAgeError'+FdIndex+incomeIndex).innerHTML = "Age cannot be more than 99";
           return false;
 	   }else if(Number(name) == 0){
 		   $scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value = null;
 		   document.getElementById('familyMemberAgeError'+FdIndex+incomeIndex).innerHTML = "Age cannot be 0";
           return false;
 	   }
    };
    
    //validate adhar card
    
    $scope.validateName = function (name, errorId){
    	if(name != undefined){
 		   if( name.length != 12 && name != ""  ){
 	        	if(errorId=='adharcardError' || errorId=='adharcardError2') {
 	        		document.getElementById(errorId).innerHTML = "Please enter 12 digit aadhaar number";
 	        		document.ciclSocialBackgroungReport.ciclChildAdhaarCardNo.focus() ;
 	        		return false;
 	        	}
 	        	
 	       }else{
 	    	   document.getElementById(errorId).innerHTML = "";
 	    	   return true;
 	       }
 	   } else{
 		  if(errorId=='adharcardError' || errorId=='adharcardError2') {
       		document.getElementById(errorId).innerHTML = "Please enter 12 digit aadhaar number";
       		document.ciclSocialBackgroungReport.ciclChildAdhaarCardNo.focus() ;
       		return false;
       	}
 	   }  
    };
    
    $scope.resetphnNo = function(model,id,errorId){
  		if(model == undefined){
  			$("#"+id).val('');
  			document.getElementById(errorId).innerHTML = "";
  			$scope.formInfo.adhaarCardNo="";
  		}
  	};
    
   $scope.validateFdIncome = function (name, errorId, FdIndex, incomeIndex){
	   if(Number(name) > 999999){
		   var ss = ($scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value).toString();
		   $scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value = Number(ss.slice(0, 6));
		   document.getElementById('incomeError'+FdIndex+incomeIndex).innerHTML = "Maximum limit for income is 999999";
           return false;
	   }
   };
    $scope.clearIncomeError = function(FdIndex, incomeIndex){
    	document.getElementById('incomeError'+FdIndex+incomeIndex).innerHTML = "";
    };
    $scope.clearAgeError = function(FdIndex, incomeIndex){
 	   document.getElementById('familyMemberAgeError'+FdIndex+incomeIndex).innerHTML = "";
    };

    $scope.nameString={};
    $scope.arr=[];
    $scope.goodArr=[];
    $scope.badArr=[];
    $scope.reasonArr=[];
    $scope.maorityArr=[];
    $scope.verbalAbusedByArr=[];

    $scope.setIds = function(selectedObjs){
    	var newDAString = "";
    	var newDANameString = "";
		if($scope.formInfo.differentlyAbledType != null || $scope.formInfo.differentlyAbledType != undefined){
	    	  for(var i=0; i<$scope.differentlyAbledType.length; i++){
	    		  if($scope.differentlyAbledType[i].id == selectedObjs.id && selectedObjs.checked == false){
	    			  $scope.differentlyAbledType[i].checked = false; 
	    		  }else if($scope.differentlyAbledType[i].id == selectedObjs.id && selectedObjs.checked == true){
	    			  $scope.differentlyAbledType[i].checked = true; 
	    		  }
	    	  }
	    	  for(var k=0; k<$scope.differentlyAbledType.length; k++){
	    		  if($scope.differentlyAbledType[k].checked == true){
	    			  newDAString += $scope.differentlyAbledType[k].id+",";
	    			  $scope.lang == 'en' ? newDANameString += $scope.differentlyAbledType[k].name+",  " : newDANameString += $scope.differentlyAbledType[k].typeNameHindi+",  ";
	    		  }
	    	  }
	    	  newDAString = newDAString.substring(0, newDAString.length-1);
//	    	  newDANameString = newDANameString.substring(0, newDANameString.length-1);
	    	  $scope.keyString = newDAString; 
	    	  $scope.differentlyAbledTypeString = newDANameString;
	      }else{
	 	      $scope.keyString = "";
	 	      $scope.differentlyAbledTypeString = "";
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
	 		      $scope.lang == 'en'?$scope.differentlyAbledTypeString+=$scope.arr[j].name+",  ":$scope.differentlyAbledTypeString+=$scope.arr[j].typeNameHindi+",  ";
	 	      }
	 	      $scope.keyString = $scope.keyString.substring(0, $scope.keyString.length-1);
	      }
	 	      if(!$scope.keyString.includes('46')){
	    	      $scope.formInfo.otherDifferentlyAbledType=null;
	          }
	 	   
	 	  $scope.differentlyAbledTypeString = $scope.differentlyAbledTypeString.substring(0, $scope.differentlyAbledTypeString.length-3);
	   	  $scope.nameString.differentlyAbledTypeString = $scope.differentlyAbledTypeString;
	   	  console.log("differentlyAbledType: "+$scope.nameString.differentlyAbledTypeString);
    };
    $scope.setReasonIds = function(selectedObjs){
    	var newReasonString = "";
    	var newReasonNameString = "";
        if($scope.formInfo.schoolLeavingReason != null || $scope.formInfo.schoolLeavingReason != undefined){
      	  for(var i=0; i<$scope.reasonLeavingSchool.length; i++){
      		  if($scope.reasonLeavingSchool[i].id == selectedObjs.id && selectedObjs.checked == false){
      			  $scope.reasonLeavingSchool[i].checked = false; 
      		  }else if($scope.reasonLeavingSchool[i].id == selectedObjs.id && selectedObjs.checked == true){
      			  $scope.reasonLeavingSchool[i].checked = true; 
      		  }
      	  }
      	  for(var k=0; k<$scope.reasonLeavingSchool.length; k++){
      		  if($scope.reasonLeavingSchool[k].checked == true){
      			  newReasonString += $scope.reasonLeavingSchool[k].id+",";
      			  $scope.lang == 'en'?newReasonNameString += $scope.reasonLeavingSchool[k].name+",  ":newReasonNameString += $scope.reasonLeavingSchool[k].typeNameHindi+",  ";
      		  }
      	  }
      	  newReasonString = newReasonString.substring(0, newReasonString.length-1);
//      	  newReasonNameString = newReasonNameString.substring(0, newReasonNameString.length-1);
      	  $scope.reasonString = newReasonString; 
      	  $scope.reasonNameString = newReasonNameString;
        }else{
	 	   $scope.reasonString = "";
	 	   $scope.reasonNameString = "";
	 	   $scope.reasonArr.push(selectedObjs);
	 	   for(var i=0; i<$scope.reasonArr.length; i++){
	 		   if($scope.reasonArr[i].checked == false){
	 			   $scope.reasonArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var i=0; i<$scope.reasonArr.length; i++){
	 		   if($scope.reasonArr[i].checked == false){
	 			   $scope.reasonArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var j=0;j<$scope.reasonArr.length;j++){
	 		   $scope.reasonString+=$scope.reasonArr[j].id+",";
	 		  $scope.lang == 'en'?$scope.reasonNameString+=$scope.reasonArr[j].name+",  ":$scope.reasonNameString+=$scope.reasonArr[j].typeNameHindi+",  ";
	 	   }
	 	   $scope.reasonString = $scope.reasonString.substring(0, $scope.reasonString.length-1);
       }
 	   if(!$scope.reasonString.includes('85')){
    	   $scope.formInfo.otherSchoolLeavingReason=null;
       }
 	   $scope.reasonNameString = $scope.reasonNameString.substring(0, $scope.reasonNameString.length-3);
   	   $scope.nameString.reasonNameString = $scope.reasonNameString;

   	   console.log("reasons: "+$scope.nameString.reasonNameString);
    };
    $scope.setMaority = function(selectedObjs){
    	var newMaorityString = "";
    	var newMaorityNameString = "";
        if($scope.formInfo.majorityOfFriends != null || $scope.formInfo.majorityOfFriends != undefined){
		  for(var i=0; i<$scope.majorityFriendTypes.length; i++){
			  if($scope.majorityFriendTypes[i].id == selectedObjs.id && selectedObjs.checked == false){
				  $scope.majorityFriendTypes[i].checked = false; 
			  }else if($scope.majorityFriendTypes[i].id == selectedObjs.id && selectedObjs.checked == true){
				  $scope.majorityFriendTypes[i].checked = true; 
			  }
		  }
		  for(var k=0; k<$scope.majorityFriendTypes.length; k++){
			  if($scope.majorityFriendTypes[k].checked == true){
				  newMaorityString += $scope.majorityFriendTypes[k].id+",";
				  $scope.lang == 'en'?newMaorityNameString += $scope.majorityFriendTypes[k].name+",  ":newMaorityNameString += $scope.majorityFriendTypes[k].typeNameHindi+",  ";
			  }
		  }
		  newMaorityString = newMaorityString.substring(0, newMaorityString.length-1);
//		  newMaorityNameString = newMaorityNameString.substring(0, newMaorityNameString.length-3);
		  $scope.maorityString = newMaorityString;
		  $scope.maorityNameString = newMaorityNameString;
        }else{
	 	   $scope.maorityString = "";
	 	   $scope.maorityNameString = "";
	 	   $scope.maorityArr.push(selectedObjs);
	 	   for(var i=0; i<$scope.maorityArr.length; i++){
	 		   if($scope.maorityArr[i].checked == false){
	 			   $scope.maorityArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var i=0; i<$scope.maorityArr.length; i++){
	 		   if($scope.maorityArr[i].checked == false){
	 			   $scope.maorityArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var j=0;j<$scope.maorityArr.length;j++){
	 		   $scope.maorityString+=$scope.maorityArr[j].id+",";
	 		   $scope.lang == 'en'?$scope.maorityNameString+=$scope.maorityArr[j].name+",  ":$scope.maorityNameString+=$scope.maorityArr[j].typeNameHindi+",  ";
	 	   }
	 	   $scope.maorityString = $scope.maorityString.substring(0, $scope.maorityString.length-1);
       }
 	   $scope.maorityNameString = $scope.maorityNameString.substring(0, $scope.maorityNameString.length-3);
 	   $scope.nameString.maorityNameString = $scope.maorityNameString;
   	   console.log("maorityString: "+$scope.nameString.maorityNameString);
    };
    $scope.setVerbalAbusedBy = function(selectedObjs){
    	var newVerbalAbusedString = "";
	 	var newVerbalAbusedNameString = "";
    	
    	if($scope.formInfo.verbalAbuse != null || $scope.formInfo.verbalAbuse != undefined){
		  for(var i=0; i<$scope.vebalAbusedBy.length; i++){
			  if($scope.vebalAbusedBy[i].id == selectedObjs.id && selectedObjs.checked == false){
				  $scope.vebalAbusedBy[i].checked = false; 
			  }else if($scope.vebalAbusedBy[i].id == selectedObjs.id && selectedObjs.checked == true){
				  $scope.vebalAbusedBy[i].checked = true; 
			  }
		  }
		  for(var k=0; k<$scope.vebalAbusedBy.length; k++){
			  if($scope.vebalAbusedBy[k].checked == true){
				  newVerbalAbusedString += $scope.vebalAbusedBy[k].id+",";
				  $scope.lang == 'en'?newVerbalAbusedNameString += $scope.vebalAbusedBy[k].name+",  ":newVerbalAbusedNameString += $scope.vebalAbusedBy[k].typeNameHindi+",  ";
			  }
		  }
		  newVerbalAbusedString = newVerbalAbusedString.substring(0, newVerbalAbusedString.length-1);
//		  newMaorityNameString = newMaorityNameString.substring(0, newMaorityNameString.length-3);
		  $scope.verbalAbusedString = newVerbalAbusedString;
		  $scope.verbalAbusedNameString = newVerbalAbusedNameString;
    	}else{
	 	   $scope.verbalAbusedString = "";
	 	   $scope.verbalAbusedNameString = "";
	 	   $scope.verbalAbusedByArr.push(selectedObjs);
	 	   for(var i=0; i<$scope.verbalAbusedByArr.length; i++){
	 		   if($scope.verbalAbusedByArr[i].checked == false){
	 			   $scope.verbalAbusedByArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var i=0; i<$scope.verbalAbusedByArr.length; i++){
	 		   if($scope.verbalAbusedByArr[i].checked == false){
	 			   $scope.verbalAbusedByArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var j=0;j<$scope.verbalAbusedByArr.length;j++){
	 		   $scope.verbalAbusedString+=$scope.verbalAbusedByArr[j].id+",";
	 		   $scope.lang == 'en'?$scope.verbalAbusedNameString+=$scope.verbalAbusedByArr[j].name+",  ":$scope.verbalAbusedNameString+=$scope.verbalAbusedByArr[j].typeNameHindi+",  ";
	 	   }
	 	   $scope.verbalAbusedString = $scope.verbalAbusedString.substring(0, $scope.verbalAbusedString.length-1);
	 	   if(!$scope.verbalAbusedString.includes('125')){
	   		  $scope.formInfo.otherVerbalAbuse=null;
	   	   }
    	}
 	   $scope.verbalAbusedNameString = $scope.verbalAbusedNameString.substring(0, $scope.verbalAbusedNameString.length-3);
   	   console.log("verbalAbusedString: "+$scope.verbalAbusedNameString);
   	   $scope.nameString.verbalAbusedNameString = $scope.verbalAbusedNameString;
    };
    $scope.physicalAbusedByArr = [];
    $scope.setphysicalAbusedBy = function(selectedObjs){
    	var newPhysicalAbusedString = "";
	 	var newPhysicalAbusedNameString = "";
    	if($scope.formInfo.physicalAbuse != null || $scope.formInfo.physicalAbuse != undefined){
		  for(var i=0; i<$scope.physicalAbusedBy.length; i++){
			  if($scope.physicalAbusedBy[i].id == selectedObjs.id && selectedObjs.checked == false){
				  $scope.physicalAbusedBy[i].checked = false; 
			  }else if($scope.physicalAbusedBy[i].id == selectedObjs.id && selectedObjs.checked == true){
				  $scope.physicalAbusedBy[i].checked = true; 
			  }
		  }
		  for(var k=0; k<$scope.physicalAbusedBy.length; k++){
			  if($scope.physicalAbusedBy[k].checked == true){
				  newPhysicalAbusedString += $scope.physicalAbusedBy[k].id+",";
				  $scope.lang == 'en'?newPhysicalAbusedNameString += $scope.physicalAbusedBy[k].name+",  ":newPhysicalAbusedNameString += $scope.physicalAbusedBy[k].typeNameHindi+",  ";
			  }
		  }
		  newPhysicalAbusedString = newPhysicalAbusedString.substring(0, newPhysicalAbusedString.length-1);
		  $scope.physicalAbusedString = newPhysicalAbusedString;
		  $scope.physicalAbusedNameString = newPhysicalAbusedNameString;
    	}else{
	 	   $scope.physicalAbusedString = "";
	 	   $scope.physicalAbusedNameString = "";
	 	   $scope.physicalAbusedByArr.push(selectedObjs);
	 	   for(var i=0; i<$scope.physicalAbusedByArr.length; i++){
	 		   if($scope.physicalAbusedByArr[i].checked == false){
	 			   $scope.physicalAbusedByArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var i=0; i<$scope.physicalAbusedByArr.length; i++){
	 		   if($scope.physicalAbusedByArr[i].checked == false){
	 			   $scope.physicalAbusedByArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var j=0;j<$scope.physicalAbusedByArr.length;j++){
	 		   $scope.physicalAbusedString+=$scope.physicalAbusedByArr[j].id+",";
	 		   $scope.lang == 'en'?$scope.physicalAbusedNameString+=$scope.physicalAbusedByArr[j].name+",  ":$scope.physicalAbusedNameString+=$scope.physicalAbusedByArr[j].typeNameHindi+",  ";
	 	   }
	 	   $scope.physicalAbusedString = $scope.physicalAbusedString.substring(0, $scope.physicalAbusedString.length-1);
	 	   if(!$scope.physicalAbusedString.includes('125')){
				 $scope.formInfo.otherPhysicalAbuse=null;
		   }
    	}
 	   $scope.physicalAbusedNameString = $scope.physicalAbusedNameString.substring(0, $scope.physicalAbusedNameString.length-3);
   	   $scope.nameString.physicalAbusedNameString = $scope.physicalAbusedNameString;
   	   console.log("physicalAbusedString: "+$scope.nameString.physicalAbusedNameString);
    };
    
    $scope.sexualAbusedByArr = [];
    $scope.setSexualAbusedBy = function(selectedObjs){
       var newSexualAbusedString = "";
	   var newSexualAbusedNameString = "";
	   if($scope.formInfo.sexualAbuse != null || $scope.formInfo.sexualAbuse != undefined){
		   for(var i=0; i<$scope.sexualAbusedBy.length; i++){
				  if($scope.sexualAbusedBy[i].id == selectedObjs.id && selectedObjs.checked == false){
					  $scope.sexualAbusedBy[i].checked = false; 
				  }else if($scope.sexualAbusedBy[i].id == selectedObjs.id && selectedObjs.checked == true){
					  $scope.sexualAbusedBy[i].checked = true; 
				  }
			  }
			  for(var k=0; k<$scope.sexualAbusedBy.length; k++){
				  if($scope.sexualAbusedBy[k].checked == true){
					  newSexualAbusedString += $scope.sexualAbusedBy[k].id+",";
					  $scope.lang == 'en'?newSexualAbusedNameString += $scope.sexualAbusedBy[k].name+",  ":newSexualAbusedNameString += $scope.sexualAbusedBy[k].typeNameHindi+",  ";
				  }
			  }
			  newSexualAbusedString = newSexualAbusedString.substring(0, newSexualAbusedString.length-1);
			  $scope.sexualAbusedString = newSexualAbusedString;
			  $scope.sexualAbusedNameString = newSexualAbusedNameString;
	   }else{
	 	   $scope.sexualAbusedString = "";
	 	   $scope.sexualAbusedNameString = "";
	 	   $scope.sexualAbusedByArr.push(selectedObjs);
	 	   for(var i=0; i<$scope.sexualAbusedByArr.length; i++){
	 		   if($scope.sexualAbusedByArr[i].checked == false){
	 			   $scope.sexualAbusedByArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var i=0; i<$scope.sexualAbusedByArr.length; i++){
	 		   
	 		   if($scope.sexualAbusedByArr[i].checked == false){
	 			   $scope.sexualAbusedByArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var j=0;j<$scope.sexualAbusedByArr.length;j++){
	 		   $scope.sexualAbusedString+=$scope.sexualAbusedByArr[j].id+",";
	 		   $scope.lang == 'en'?$scope.sexualAbusedNameString+=$scope.sexualAbusedByArr[j].name+",  ":$scope.sexualAbusedNameString+=$scope.sexualAbusedByArr[j].typeNameHindi+",  ";
	 	   }
	 	   $scope.sexualAbusedString = $scope.sexualAbusedString.substring(0, $scope.sexualAbusedString.length-1);
	 	   if(!$scope.sexualAbusedString.includes('125')){
			  $scope.formInfo.otherSexualAbuse=null;
		   }
	   }
 	   $scope.sexualAbusedNameString = $scope.sexualAbusedNameString.substring(0, $scope.sexualAbusedNameString.length-3);
 	   $scope.nameString.sexualAbusedNameString = $scope.sexualAbusedNameString;
   	   console.log("sexualAbusedString: "+$scope.nameString.sexualAbusedNameString);
    };  
    $scope.setBadIds = function(selectedObjs){
      var newBadHabits = "";
      var newBadHabitsName = "";
      if($scope.formInfo.badHabits != null || $scope.formInfo.badHabits != undefined){
    	  for(var i=0; i<$scope.badHabits.length; i++){
    		  if($scope.badHabits[i].id == selectedObjs.id && selectedObjs.checked == false){
    			  $scope.badHabits[i].checked = false; 
    		  }else if($scope.badHabits[i].id == selectedObjs.id && selectedObjs.checked == true){
    			  $scope.badHabits[i].checked = true; 
    		  }
    	  }
    	  for(var k=0; k<$scope.badHabits.length; k++){
    		  if($scope.badHabits[k].checked == true){
    			  newBadHabits += $scope.badHabits[k].id+",";
    			  $scope.lang == 'en'?newBadHabitsName += $scope.badHabits[k].name+",  ":newBadHabitsName += $scope.badHabits[k].typeNameHindi+",  ";
    		  }	  
    	  }
    	  newBadHabits = newBadHabits.substring(0, newBadHabits.length-1);
//    	  newBadHabitsName = newBadHabitsName.substring(0, newBadHabitsName.length-3);
    	  $scope.keyBadString = newBadHabits;
    	  $scope.badNameString = newBadHabitsName;
      }else{
	 	   $scope.keyBadString = "";
	 	   $scope.badNameString = "";
	 	   $scope.badArr.push(selectedObjs);
	 	   for(var i=0; i<$scope.badArr.length; i++){
	 		   if($scope.badArr[i].checked == false){
	 			   $scope.badArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var i=0; i<$scope.badArr.length; i++){
	 		   if($scope.badArr[i].checked == false){
	 			   $scope.badArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var j=0;j<$scope.badArr.length;j++){
	 		   $scope.keyBadString+=$scope.badArr[j].id+",";
	 		   $scope.lang == 'en'?$scope.badNameString+=$scope.badArr[j].name+",  ":$scope.badNameString+=$scope.badArr[j].typeNameHindi+",  ";
	 	   }
	 	   $scope.keyBadString = $scope.keyBadString.substring(0, $scope.keyBadString.length-1);
       }
       if(!$scope.keyBadString.includes('61')){
 		  $scope.formInfo.otherBadHabits=null;
 	   }
 	   if(!$scope.keyBadString.includes('58')){
    	  $scope.formInfo.otherDrugBadHabits=null;
       }
 	   $scope.badNameString = $scope.badNameString.substring(0, $scope.badNameString.length-3);
   	   $scope.nameString.badNameString = $scope.badNameString;
   	   console.log("bad habits: "+$scope.nameString.badNameString);
    };
    $scope.setGoodIds = function(selectedObjs){
    	var newGoodHabits = "";
    	var newGoodHabitsName = "";
        if($scope.formInfo.goodHabits != null || $scope.formInfo.goodHabits != undefined){
      	  for(var i=0; i<$scope.goodHabits.length; i++){
      		  if($scope.goodHabits[i].id == selectedObjs.id && selectedObjs.checked == false){
      			  $scope.goodHabits[i].checked = false; 
      		  }else if($scope.goodHabits[i].id == selectedObjs.id && selectedObjs.checked == true){
      			  $scope.goodHabits[i].checked = true; 
      		  }
      	  }
      	  for(var k=0; k<$scope.goodHabits.length; k++){
      		  if($scope.goodHabits[k].checked == true){
      			  newGoodHabits += $scope.goodHabits[k].id+",";
      			  $scope.lang == 'en'?newGoodHabitsName += $scope.goodHabits[k].name+",  ":newGoodHabitsName += $scope.goodHabits[k].typeNameHindi+",  ";
      		  }
      	  }
      	  newGoodHabits = newGoodHabits.substring(0, newGoodHabits.length-1);
//      	  newGoodHabitsName = newGoodHabitsName.substring(0, newGoodHabitsName.length-3);
      	  $scope.keyGoodString = newGoodHabits;
      	  $scope.goodNameString = newGoodHabitsName;
       }else{
	 	   $scope.keyGoodString = "";
	 	   $scope.goodNameString = "";
	 	   $scope.goodArr.push(selectedObjs);
	 	   for(var i=0; i<$scope.goodArr.length; i++){
	 		   if($scope.goodArr[i].checked == false){
	 			   $scope.goodArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var i=0; i<$scope.goodArr.length; i++){
	 		   if($scope.goodArr[i].checked == false){
	 			   $scope.goodArr.splice(i, 1);
	 		   }
	 	   }
	 	   for(var j=0;j<$scope.goodArr.length;j++){
	 		   $scope.keyGoodString+=$scope.goodArr[j].id+",";
	 		   $scope.lang == 'en'?$scope.goodNameString+=$scope.goodArr[j].name+",  ":$scope.goodNameString+=$scope.goodArr[j].typeNameHindi+",  ";
	 	   }
	 	   $scope.keyGoodString = $scope.keyGoodString.substring(0, $scope.keyGoodString.length-1);
      }
 	   if(!$scope.keyGoodString.includes('55')){
  		  $scope.formInfo.otherGoodHabits=null;
  	   }
 	   $scope.goodNameString = $scope.goodNameString.substring(0, $scope.goodNameString.length-3);
   	   $scope.nameString.goodNameString = $scope.goodNameString;
   	   console.log("good habits: "+$scope.nameString.goodNameString);
    };
    
    $scope.resetRadios = function(){
    	$scope.formInfo.usedBySelfDress=false;
    	$scope.formInfo.usedBySelfGambling=false;
 	    $scope.formInfo.usedBySelfAlcohol=false;
 	    $scope.formInfo.usedBySelfSmoking=false;
 	    $scope.formInfo.usedBySelfSavings=false;
 	    $scope.formInfo.usedBySelfDrug=false;
    };
    
    $scope.defaultFalseForRadio=function(){
    	$scope.formInfo.familyInvolvementInOffence=false;
 		$scope.showSubmitBtn = true;
 		$scope.formInfo.usedByFamily=false;
 	    $scope.formInfo.usedBySelf=false;
 	    $scope.formInfo.usedBySelfDress=false;
 	    $scope.formInfo.usedBySelfGambling=false;
 	    $scope.formInfo.usedBySelfAlcohol=false;
 	    $scope.formInfo.usedBySelfSmoking=false;
 	    $scope.formInfo.usedBySelfSavings=false;
 	    $scope.formInfo.usedBySelfDrug=false;
 	    
 	    $scope.formInfo.abused=false;
 	    $scope.formInfo.victimOfOffence=false;
 	    $scope.formInfo.childDrugPeddling=false;

 	    $scope.formInfo.differentlyAbled=false;
    };
    
    $scope.editForm = function(){
    	$scope.ciclRegDisabled=false;
    	$scope.ciclRegIfUpdateDisabled=true;
    };
    $scope.getSocialBackgroundReportSuccessful = true;
    

    if($scope.selectedChildId!=undefined && $scope.selectedChildId!=""){
    	$scope.getSocialBackgroundReportSuccessful = false;
    	$http.get('getSocialBackgroundReport?childId='+$scope.selectedChildId).
    	then(function(result){
    		$scope.getSocialBackgroundReportSuccessful = true;
        	$scope.checkAllDataSuccessful();
    		if(result.data!=""){
    			$timeout(function(){
    			$scope.formInfo = result.data;
    			$scope.printFitData = result.data;
    			$scope.ciclRegDisabled=true;
    			var fetchedFamilyDetails = result.data.familyDetails;
    			var familyDetailsArrayforFetch=[];
    			var familyDetailsArrayforFetch1=[];
    			for(var i=0; i<fetchedFamilyDetails.length; i++){
    				familyDetailsArrayforFetch.push(JSON.parse(JSON.stringify($scope.familyDetailsRepetitionArray)));
    			}
    			for(var i=0; i<fetchedFamilyDetails.length; i++){
    				
    				for(var j=0; j<familyDetailsArrayforFetch[i][0].length; j++){
    					 switch(familyDetailsArrayforFetch[i][0][j].name){
    	    		   		case '(i) Name':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].name;
    	    		   			break;
    	    		   		case '(ii) Age':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].age;
    	    		   			break;
    	    		   		case '(iii) Relationship':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].relationship;
    	    		   			break;
    	    		   		case '(iv) Sex':
    	    		   			if(fetchedFamilyDetails[i].sex!=null){
    		    		   			familyDetailsArrayforFetch[i][0][j]=fetchedFamilyDetails[i].sex;
    		    		   			familyDetailsArrayforFetch[i][0][j].desc = "sexNumber";
    		    		   			//familyDetailsArrayforFetch[i][0][j].key = familyDetailsArrayforFetch[i][0][j].name;
    		    		   			familyDetailsArrayforFetch[i][0][j].name = "(iv) Sex";
    	    		   			}	
    	    		   			break;
    	    		   		case '(v) Education':
    	    		   			if(fetchedFamilyDetails[i].education!=null){
    		    		   			familyDetailsArrayforFetch[i][0][j]=fetchedFamilyDetails[i].education;
    		    		   			familyDetailsArrayforFetch[i][0][j].desc = "eduDropdown";
    		    		   			//familyDetailsArrayforFetch[i][0][j].key = familyDetailsArrayforFetch[i][0][j].name;
    		    		   			familyDetailsArrayforFetch[i][0][j].name = "(v) Education";
    	    		   			}	
    	    		   			break;
    	    		   		case '(vi) Occupation':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].occupation;
    	    		   			break;
    	    		   		case '(vii) Income':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].income;
    	    		   			break;
    	    		   		case '(viii) Health status':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].healthStatus;
    	    		   			break;
    	    		   		case '(ix) History of mental illness':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].historyOfMentalIllness;
    	    		   			break;
    	    		   		case '(x) Addiction':
    	    		   			familyDetailsArrayforFetch[i][0][j].value=fetchedFamilyDetails[i].addictions;
    	    		   			break;	
    	    		   }
    					 
    				}
    				familyDetailsArrayforFetch1.push(familyDetailsArrayforFetch[i][0]);
    			}
    			
    			$scope.familyDetailsRepetitionArray = familyDetailsArrayforFetch1;
    			
    			if($scope.formInfo.differentlyAbled){
    				$scope.ShowPassport('Y');
    			}
    			if($scope.formInfo.familyInvolvementInOffence){
    				$scope.showFamilyInvolvementInOffence=true;
    			}
    			if($scope.formInfo.victimOfOffence){
    				$scope.showOtherVictimOfOffence=true;
    			}
    			if($scope.formInfo.childDrugPeddling){
    				$scope.showChildDrugPeddling=true;
    			}
    			if($scope.formInfo.abused){
    				$scope.showAbused=true;
    			}

    			
    			if($scope.formInfo.religionObject.id==184){
    	    		$scope.showcast=true;
    	    		$scope.showOtherCast=false;
    	    	}else if($scope.formInfo.religionObject.id==187){
    	    		$scope.showcast=false;
    	    		$scope.showOtherCast=true;
    	    	}
    	    	else{
    	    		$scope.showcast=false;
    	    		$scope.showOtherCast=false;
    	    	}

    			var timeString = "";
    			if($scope.formInfo.entryTime != null){
    				timeString = $scope.formInfo.entryTime;
    				var timeArr = timeString.split(":");
    				$scope.minute = timeArr[1];
	    			/*if(timeArr[0] > 12){
	    				$scope.ap = "PM";
	    			}else{
	    				$scope.hr = timeArr[0];
	    				$scope.ap = "AM";
	    			}
	    			if(timeArr[0] < 13){
	    				$scope.hr = timeArr[0];
	    			}else{
	    				$scope.hr = (timeArr[0]-12).toString();
	    				if($scope.hr.length == 1)
	    					$scope.hr = "0"+$scope.hr;
	    			}*/
    				 if( timeArr[0]>12){
       				  if(timeArr[0]-12>0 && timeArr[0]-12<10)
       					  $scope.hr="0"+(timeArr[0]-12).toString(); 
       				  
       				  else
       					  $scope.hr=(timeArr[0]-12).toString();
       				  
       				  $scope.minute = timeArr[1];
       				  $scope.ap="PM";
       			  } else if(timeArr[0]==="00"){
       				  $scope.hr="12";
       				  $scope.minute = timeArr[1];
       				  $scope.ap="AM"; 
       			  }else if(timeArr[0]==="12"){
       				  $scope.hr=timeArr[0];
       				  $scope.minute = timeArr[1];
       				  $scope.ap="PM"; 
       			  }  
       			  else{
       				  $scope.hr=timeArr[0];
       				  $scope.minute = timeArr[1];
       				  $scope.ap="AM";  
       			  }
    			}
    			$scope.showUpdateBtn = true;
    			//$scope.ifSubmittedDisable = true;

    			//do multiple select check-box prefetch here
    			if($scope.formInfo.goodHabits != null){
    				var pfGoodHabits = $scope.formInfo.goodHabits;
    				$scope.goodNameString = "";
    				$scope.keyGoodString = $scope.formInfo.goodHabits;
    				$scope.pfGoodHabitArr = pfGoodHabits.split(',');
    				for(var i=0; i < $scope.pfGoodHabitArr.length; i++){
    					for(var j=0; j < $scope.goodHabits.length; j++){
    						if(Number($scope.pfGoodHabitArr[i]) == $scope.goodHabits[j].id){
    							$scope.goodHabits[j].checked = true;
    							$scope.goodNameString+= $scope.lang == 'en' ? $scope.goodHabits[j].name+",  " : $scope.goodHabits[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.goodNameString = $scope.goodNameString.substring(0, $scope.goodNameString.length-3);
    				$scope.nameString.goodNameString = $scope.goodNameString;
    			}
    			if($scope.formInfo.badHabits != null){
    				var pfBadHabits = $scope.formInfo.badHabits;
    				$scope.badNameString = "";
    				$scope.keyBadString = $scope.formInfo.badHabits;
    				$scope.pfBadHabitArr = pfBadHabits.split(',');
    				for(var i=0; i < $scope.pfBadHabitArr.length; i++){
    					for(var j=0; j < $scope.badHabits.length; j++){
    						if(Number($scope.pfBadHabitArr[i]) == $scope.badHabits[j].id){
    							$scope.badHabits[j].checked = true;
    							$scope.badNameString+= $scope.lang == 'en' ? $scope.badHabits[j].name+",  " : $scope.badHabits[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.badNameString = $scope.badNameString.substring(0, $scope.badNameString.length-3);
    				$scope.nameString.badNameString = $scope.badNameString;
    			}
    			if($scope.formInfo.schoolLeavingReason != null){
    				var pfReasonLeavingSchool = $scope.formInfo.schoolLeavingReason;
    				$scope.reasonNameString = "";
    				$scope.reasonString = $scope.formInfo.schoolLeavingReason;
    				$scope.pfReasonLeavingSchoolArr = pfReasonLeavingSchool.split(',');
    				for(var i=0; i < $scope.pfReasonLeavingSchoolArr.length; i++){
    					for(var j=0; j < $scope.reasonLeavingSchool.length; j++){
    						if(Number($scope.pfReasonLeavingSchoolArr[i]) == $scope.reasonLeavingSchool[j].id){
    							$scope.reasonLeavingSchool[j].checked = true;
    							$scope.reasonNameString+= $scope.lang == 'en' ? $scope.reasonLeavingSchool[j].name+",  " : $scope.reasonLeavingSchool[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.reasonNameString = $scope.reasonNameString.substring(0, $scope.reasonNameString.length-3);
    				$scope.nameString.reasonNameString = $scope.reasonNameString;
    			}
    			if($scope.formInfo.majorityOfFriends != null){
    				var pfMajorityFriendTypes = $scope.formInfo.majorityOfFriends;
    				$scope.maorityNameString = "";
    				$scope.maorityString = $scope.formInfo.majorityOfFriends;
    				$scope.pfMajorityFriendTypesArr = pfMajorityFriendTypes.split(',');
    				for(var i=0; i < $scope.pfMajorityFriendTypesArr.length; i++){
    					for(var j=0; j < $scope.majorityFriendTypes.length; j++){
    						if(Number($scope.pfMajorityFriendTypesArr[i]) == $scope.majorityFriendTypes[j].id){
    							$scope.majorityFriendTypes[j].checked = true;
    							$scope.maorityNameString+= $scope.lang == 'en' ? $scope.majorityFriendTypes[j].name+",  " : $scope.majorityFriendTypes[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.maorityNameString = $scope.maorityNameString.substring(0, $scope.maorityNameString.length-3);
    				$scope.nameString.maorityNameString = $scope.maorityNameString;
    			}
    			if($scope.formInfo.verbalAbuse != null){
    				var pfVerbalAbuse = $scope.formInfo.verbalAbuse;
    				$scope.verbalAbusedNameString = "";
    				$scope.verbalAbusedString = $scope.formInfo.verbalAbuse;
    				$scope.pfVerbalAbuseArr = pfVerbalAbuse.split(',');
    				for(var i=0; i < $scope.pfVerbalAbuseArr.length; i++){
    					for(var j=0; j < $scope.vebalAbusedBy.length; j++){
    						if(Number($scope.pfVerbalAbuseArr[i]) == $scope.vebalAbusedBy[j].id){
    							$scope.vebalAbusedBy[j].checked = true;
    							$scope.verbalAbusedNameString+=$scope.lang == 'en' ? $scope.vebalAbusedBy[j].name+",  " : $scope.vebalAbusedBy[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.verbalAbusedNameString = $scope.verbalAbusedNameString.substring(0, $scope.verbalAbusedNameString.length-3);
    				$scope.nameString.verbalAbusedNameString = $scope.verbalAbusedNameString;
    			}
    			if($scope.formInfo.physicalAbuse != null){
    				var pfPhysicalAbuse = $scope.formInfo.physicalAbuse;
    				$scope.physicalAbusedNameString = "";
    				$scope.physicalAbusedString = $scope.formInfo.physicalAbuse;
    				$scope.pfPhysicalAbuseArr = pfPhysicalAbuse.split(',');
    				for(var i=0; i < $scope.pfPhysicalAbuseArr.length; i++){
    					for(var j=0; j < $scope.physicalAbusedBy.length; j++){
    						if(Number($scope.pfPhysicalAbuseArr[i]) == $scope.physicalAbusedBy[j].id){
    							$scope.physicalAbusedBy[j].checked = true;
    							$scope.physicalAbusedNameString+= $scope.lang == 'en' ? $scope.physicalAbusedBy[j].name+",  " : $scope.physicalAbusedBy[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.physicalAbusedNameString = $scope.physicalAbusedNameString.substring(0, $scope.physicalAbusedNameString.length-3);
    				$scope.nameString.physicalAbusedNameString = $scope.physicalAbusedNameString;
    			}
    			if($scope.formInfo.sexualAbuse != null){
    				var pfSexualAbuse = $scope.formInfo.sexualAbuse;
    				$scope.sexualAbusedNameString = "";
    				$scope.sexualAbusedString = $scope.formInfo.sexualAbuse;
    				$scope.pfSexualAbuseArr = pfSexualAbuse.split(',');
    				for(var i=0; i < $scope.pfSexualAbuseArr.length; i++){
    					for(var j=0; j < $scope.sexualAbusedBy.length; j++){
    						if(Number($scope.pfSexualAbuseArr[i]) == $scope.sexualAbusedBy[j].id){
    							$scope.sexualAbusedBy[j].checked = true;
    							$scope.sexualAbusedNameString+=$scope.lang == 'en' ? $scope.sexualAbusedBy[j].name+",  " : $scope.sexualAbusedBy[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.sexualAbusedNameString = $scope.sexualAbusedNameString.substring(0, $scope.sexualAbusedNameString.length-3);
    				$scope.nameString.sexualAbusedNameString = $scope.sexualAbusedNameString;
    			}
    			if($scope.formInfo.differentlyAbledType != null){
    				var differentlyAbled = $scope.formInfo.differentlyAbledType;
    				$scope.differentlyAbledTypeString = "";
    				$scope.keyString = $scope.formInfo.differentlyAbledType;
    				$scope.pfDifferentlyAbledArr = differentlyAbled.split(',');
    				for(var i=0; i < $scope.pfDifferentlyAbledArr.length; i++){
    					for(var j=0; j < $scope.differentlyAbledType.length; j++){
    						if(Number($scope.pfDifferentlyAbledArr[i]) == $scope.differentlyAbledType[j].id){
    							$scope.differentlyAbledType[j].checked = true;
    							$scope.differentlyAbledTypeString+= $scope.lang == 'en' ? $scope.differentlyAbledType[j].name+",  " : $scope.differentlyAbledType[j].typeNameHindi+",  ";
    						}
    					}
    				}
    				$scope.differentlyAbledTypeString = $scope.differentlyAbledTypeString.substring(0, $scope.differentlyAbledTypeString.length-3);
    				$scope.nameString.differentlyAbledTypeString = $scope.differentlyAbledTypeString;
    			}
    	
    			},500);
    		}
    	},function(error){
    		console.log(error);
    	});
    }else{
    	$scope.defaultFalseForRadio();
    }

    $scope.refreshCheckBox=function(){
    	if($scope.goodHabits != null){
			for(var j=0; j < $scope.goodHabits.length; j++){
					$scope.goodHabits[j].checked = false;
					$scope.goodNameString="";
			}
		}
		if($scope.badHabits != null){
			for(var j=0; j < $scope.badHabits.length; j++){
					$scope.badHabits[j].checked = false;
					$scope.badNameString="";
			}
		}
		if($scope.reasonLeavingSchool != null){
			for(var j=0; j < $scope.reasonLeavingSchool.length; j++){
				$scope.reasonLeavingSchool[j].checked = false;
				$scope.reasonNameString="";
			}
		}
		if($scope.majorityFriendTypes != null){
		    for(var j=0; j < $scope.majorityFriendTypes.length; j++){
				$scope.majorityFriendTypes[j].checked = false;
				$scope.maorityNameString="";
			}
		}
		if($scope.vebalAbusedBy != null){
		    for(var j=0; j < $scope.vebalAbusedBy.length; j++){
				$scope.vebalAbusedBy[j].checked = false;
				$scope.verbalAbusedNameString="";
			}
		}
		if($scope.physicalAbusedBy != null){
			for(var j=0; j < $scope.physicalAbusedBy.length; j++){
				$scope.physicalAbusedBy[j].checked = false;
				$scope.physicalAbusedNameString="";
			}
		}
		if($scope.sexualAbusedBy != null){
			for(var j=0; j < $scope.sexualAbusedBy.length; j++){
				$scope.sexualAbusedBy[j].checked = false;
				$scope.sexualAbusedNameString="";
			}
		}
		if($scope.reasonLeavingSchool != null){
			for(var j=0; j < $scope.reasonLeavingSchool.length; j++){
				$scope.reasonLeavingSchool[j].checked = false;
				$scope.reasonForLeavingFamilyNameString="";
			}
		};
		if($scope.differentlyAbledType != null){
			for(var j=0; j < $scope.differentlyAbledType.length; j++){
				$scope.differentlyAbledType[j].checked = false;
				$scope.differentlyAbledTypeString="";
			}
		}
    }
    
 //---------------------------------------------------------------------------------------------   
    $scope.childImage = "resources/img/photo.jpg";
    $scope.defaultImage = "resources/img/photo.jpg";
    $scope.clearImageField = function(type){
    	switch(type){
		case 'childImage':
			$timeout(function() {
				$scope.childImage = "resources/img/photo.jpg";
				$scope.formInfo.childImage=$scope.childImage;
		    }, 100);
			break;
    	}
    };
	$scope.getBase64=function(file, type) {
	 	var reader = new FileReader();
	 	reader.readAsDataURL(file);
	 	reader.onload = function () {
	 		switch(type){
	 			case 'childImage':
	 				$timeout(function() {
	 					$scope.childImage=reader.result;
	 					$scope.formInfo.childImage=$scope.childImage;
	 			    }, 100);
	 				break;
	 		}
	 	};
	 	reader.onerror = function (error) {
//	 	 console.log('Error: ', error);
	 	};
	};
	$scope.getReport = function ($files,type) {
		var validFormats = ["jpg","JPG","jpeg","JPEG","png","PNG"];
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
	};//-----------------------------------------
    
    
    
    var familyDetailsArray=[];
    $scope.saveData = function (){
    	var finalFdArr=[];
    	familyDetailsArray=$scope.familyDetailsRepetitionArray;
        for(var i=0;i<familyDetailsArray.length;i++){
     	   var finalObj = {};
     	   for(var j=0;j<$scope.familyDetailsRepetitionArray[i].length;j++){
     		   switch($scope.familyDetailsRepetitionArray[i][j].name){
     		   		case '(i) Name': 
     		   			finalObj.name=$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   		case '(ii) Age': 
     		   			finalObj.age=$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   		case '(iii) Relationship': 
     		   			finalObj.relationship=$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   		case '(iv) Sex': 
     		   			finalObj.sex=$scope.familyDetailsRepetitionArray[i][j].id==null?null:$scope.familyDetailsRepetitionArray[i][j];
     		   			break;
     		   		case '(v) Education': 
     		   			finalObj.education=$scope.familyDetailsRepetitionArray[i][j].id==null?null:$scope.familyDetailsRepetitionArray[i][j];
     		   			break;
     		   		case '(vi) Occupation': 
     		   			finalObj.occupation=$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   		case '(vii) Income': 
     		   			finalObj.income=$scope.familyDetailsRepetitionArray[i][j].value===0?0:$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   		case '(viii) Health status': 
     		   			finalObj.healthStatus=$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   		case '(ix) History of mental illness': 
     		   			finalObj.historyOfMentalIllness=$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   		case '(x) Addiction': 
     		   			finalObj.addictions=$scope.familyDetailsRepetitionArray[i][j].value;
     		   			break;
     		   }
     		   
     	   }
     	   finalFdArr.push(finalObj);
        }
	  
	  	var str = $scope.hr+":"+$scope.minute+" "+$scope.ap;
	  	if(str.split(" ")[1] == "PM" && $scope.hr!="12")
				  $scope.hr = parseInt($scope.hr)+12;
	  	else if(str.split(" ")[1] == "PM" && $scope.hr==="12")
			  $scope.hr = parseInt($scope.hr);
		else if(str.split(" ")[1] == "PM" && $scope.hr>Number('12') || str.split(" ")[1] == "AM" && $scope.hr<Number('12') )
			  $scope.hr = parseInt($scope.hr);
		else
			$scope.hr = "00";
	  	
	  	$scope.formInfo.entryTime = $scope.hr+":"+$scope.minute+":00";
	  	
		
		$scope.formInfo.differentlyAbledType = null==$scope.keyString?null:$scope.keyString;
	  	$scope.formInfo.schoolLeavingReason=null==$scope.reasonString?null:$scope.reasonString;
	  	$scope.formInfo.majorityOfFriends=null==$scope.maorityString?null:$scope.maorityString;
		$scope.formInfo.verbalAbuse = null==$scope.verbalAbusedString?null:$scope.verbalAbusedString;
		$scope.formInfo.physicalAbuse = null==$scope.physicalAbusedString?null:$scope.physicalAbusedString;
		$scope.formInfo.sexualAbuse = null==$scope.sexualAbusedString?null:$scope.sexualAbusedString;
		$scope.formInfo.goodHabits = null ==  $scope.keyGoodString ? null :$scope.keyGoodString ;
		$scope.formInfo.badHabits = null == $scope.keyBadString ? null : $scope.keyBadString;
		
		
		$scope.formInfo.familyInvolvementInOffence=$scope.formInfo.familyInvolvementInOffence;
		$scope.formInfo.abused=$scope.formInfo.abused;
		$scope.formInfo.victimOfOffence=$scope.formInfo.victimOfOffence;
		$scope.formInfo.childDrugPeddling=$scope.formInfo.childDrugPeddling;
		
		$scope.formInfo.usedByFamily=$scope.formInfo.usedByFamily;
		$scope.formInfo.usedBySelf=$scope.formInfo.usedBySelf;
		$scope.formInfo.usedBySelfDress=$scope.formInfo.usedBySelfDress;
		$scope.formInfo.usedBySelfGambling=$scope.formInfo.usedBySelfGambling;
		$scope.formInfo.usedBySelfAlcohol=$scope.formInfo.usedBySelfAlcohol;
		$scope.formInfo.usedBySelfDrug=$scope.formInfo.usedBySelfDrug;
		$scope.formInfo.usedBySelfSmoking=$scope.formInfo.usedBySelfSmoking;
		$scope.formInfo.usedBySelfSavings=$scope.formInfo.usedBySelfSavings;
		
		$scope.formInfo.familyDetails=finalFdArr;
//		  var formObject = $scope.formInfo; 
		$scope.formInfo.address=$scope.formInfo.address.replace(/\n/g, " ");
		$scope.formInfo.allegedOffence=$scope.formInfo.allegedOffence.replace(/\n/g, " ");
		$scope.formInfo.apprehendedCircumstances=$scope.formInfo.apprehendedCircumstances.replace(/\n/g, " ");
		$scope.formInfo.articlesRecovered=$scope.formInfo.articlesRecovered.replace(/\n/g, " ");
		$scope.formInfo.allegedRole=$scope.formInfo.allegedRole.replace(/\n/g, " ");
		$scope.formInfo.suggestions=$scope.formInfo.suggestions.replace(/\n/g, " ");

		$scope.formInfo.childImage = $scope.childImage == $scope.defaultImage ? null : $scope.childImage;
		$(".loader").css("display", "block");
		  $http.post('save_social',$scope.formInfo).
		  then(function(response){
		  		if(typeof response.data == 'string' && response.data.indexOf("You have no access to this page.") != -1){
					$("body").append('<div id="sessionOutMessage" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body text-center"><h3>Session is expired</h3><a href="ccts_login" class="btn btn-default errorOk" type="submit">OK</a></div></div></div></div>');
					$("#sessionOutMessage").modal("show");
				}
				else
					{
			  $(".loader").css("display", "none");
			  checkSessionOut(response.data);
				if(typeof response.data == 'string' && response.data.indexOf("You have no access to this page.") != -1){
					$("body").append('<div id="sessionOutMessage" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body text-center"><h3>Session is expired</h3><a href="ccts_login" class="btn btn-default errorOk" type="submit">OK</a></div></div></div></div>');
					$("#sessionOutMessage").modal("show");
				}
				else
					{
			  $('#childIdModal').modal('show');
			  $scope.childId=response.data;
			  $scope.disableDDNumber=false; 
			  $scope.disableGDNumber=false;
					}
		  }
		  },function(error){
		    console.log(error);
		  });	
      		  
    };
   
    $scope.reDirect = function(){
    	$window.location.href = 'ccts';
    };
    //====================================download image============================
    $scope.downloadImg = function(){
    	download($scope.formInfo.childImage, "download.jpeg", "image/jpeg");
    };
    //==================================== update ================================///
    $scope.updateForm = function (){
		 $scope.formInfo.id = $scope.formInfo.id; 
		 $scope.saveData();
    };
    //====================================download pdf============================
    $scope.printSubmitData = function(){
    	$(".loader").css("display", "block");
    	$scope.formInfo.childId = (($scope.formInfo.childId=="" || $scope.formInfo.childId==undefined)?$scope.childId:$scope.formInfo.childId);
    	$http.get('getSocialBackgroundReport?childId='+$scope.formInfo.childId).
    	then(function(result){
    	    var serverURL = 'downloadPDFDataForCICLSBR?type='+$scope.lang;
    	    $scope.printData=result.data;
    		$scope.printData.entryDateStr = $scope.formInfo.entryDate;
    		
    		$scope.filteredAge=($filter('filter')($scope.ageList, {id: $scope.formInfo.age}));
    		$scope.printData.age=$scope.filteredAge[0].name;
    		$scope.printData.badNameString = (null==$scope.nameString.badNameString?"":$scope.nameString.badNameString);
    		$scope.printData.differentlyAbledTypeString=(null==$scope.nameString.differentlyAbledTypeString?"":$scope.nameString.differentlyAbledTypeString);
    		$scope.printData.goodNameString=(null==$scope.nameString.goodNameString?"":$scope.nameString.goodNameString);
    		$scope.printData.maorityNameString=(null==$scope.nameString.maorityNameString?"":$scope.nameString.maorityNameString);
    		$scope.printData.reasonNameString=(null==$scope.nameString.reasonNameString?"":$scope.nameString.reasonNameString);
    		$scope.printData.sexualAbusedNameString=(null==$scope.nameString.sexualAbusedNameString?"":$scope.nameString.sexualAbusedNameString);
      		$scope.printData.verbalAbusedNameString=(null==$scope.nameString.verbalAbusedNameString?"":$scope.nameString.verbalAbusedNameString);
      		$scope.printData.physicalAbusedNameString=(null==$scope.nameString.physicalAbusedNameString?"":$scope.nameString.physicalAbusedNameString);
//      		$scope.printData.childSexType = commonService.getNameBySelectedLanguageType($scope.printFitData.childSexType, $scope.childSex, $scope.lang);
      		
      		var badNameArr = $scope.printData.badNameString.split(',  ');
	  		var newBadNameString = "";
	  		for(var i = 0; i < badNameArr.length; i++){
	  			if(badNameArr[i] == 'Drug use'){
	  				badNameArr[i] = badNameArr[i]+=" ("+$scope.printData.otherDrugBadHabits+")";
	  			}
	  			newBadNameString += badNameArr[i]+",  ";
	  		}
	  		newBadNameString = newBadNameString.substring(0, newBadNameString.length-3);
	  		$scope.printData.badNameString = newBadNameString;
	  		$scope.printData.entryTimeString = commonService.timeConverter($scope.printData.entryTime);
//	  	//	$scope.printData.programType = $scope.printData.programType;
//	  		$scope.printData.childSexType = commonService.getNameBySelectedLanguageType($scope.printFitData.childSexType, $scope.childSex, $scope.lang);
	  		
      		commonService.downloadFile(serverURL, $scope.printData);
      		document.getElementById('maximumLimit6Error').innerHTML = "";
      		$("#childimageerror").html(" ");
	  		if(!$scope.showUpdateBtn){
	  			$scope.showcast=false;
	  			$scope.hr = "";
	  			$scope.minute = "";
	  			$scope.ap = "";	
	  			$scope.formInfo={};
	  			$scope.showFamilyInvolvementInOffence=false;
	  			$scope.IsVisible=false;
	  			$scope.showOtherVictimOfOffence=false;
	  			$scope.showChildDrugPeddling=false;
	  		    $scope.keyGoodString = "";
	  		    $scope.keyBadString = "";
	  		    $scope.reasonString="";
	  		    $scope.verbalAbusedString = "";
	  		    $scope.sexualAbusedString = "";
	  		    $scope.physicalAbusedString = "";
	  		    $scope.keyString = "";
	  		    $scope.showAbused=false;
	  			$scope.defaultFalseForRadio();
	  			$scope.refreshCheckBox();
	  			$scope.childImage ="resources/img/photo.jpg";
	  			commonService.clearUploadFile();
	  			$scope.familyDetailsRepetition = {"1":""};
	  			var fdIndex = 1;
	  			$scope.familyDetailsRepetition[fdIndex] = JSON.parse(JSON.stringify($scope.soiFdObjRaw));
	  			$scope.familyDetailsRepetitionArray = $.map($scope.familyDetailsRepetition, function(value, index) {
	  			    return [value];
	  			});
	  		}else{
	  			$scope.ciclRegDisabled=true;
	  			$scope.formInfo.childImage=$scope.printData.childImage;
	  		}
    	});
    };
    
     
	//==================================End ========================================
  
    $scope.showOrganizationNameField = function(){
    	$scope.otherOrganizationFlag = true;
    	$scope.formInfo.organizationCCISAAName = null;
    };
        	  
    $scope.changeChildProducedPlace = function(){
    	$scope.formInfo.childProducedPlace = $scope.formInfo.childWelfareCommittee.areaName;
    };
    $scope.disableDDNumber=false; 
    $scope.disableGDNumber=false;
    $scope.keyupForDDandGdNumber=function(val,name){
    	var value= val==undefined?"":val;
    	if(value!="" && name=="DD"){
    		$scope.disableGDNumber=true;
    	}else if(value!="" && name=="GD"){
    		$scope.disableDDNumber=true;
    	}else{
    		$scope.disableDDNumber=false; 
    	    $scope.disableGDNumber=false;
    	}
    	
    };
    $scope.validateForm = function (){
    	var val1=$scope.formInfo.ddNumber==undefined?"":$scope.formInfo.ddNumber;
    	var val2=$scope.formInfo.gdNumber==undefined?"":$scope.formInfo.gdNumber;
    	 if(val1!="" ||val2!=""){
    		 $("#ddnumbererror").html("");
    		 $("#gdnumbererror").html("");
    	 }else{
    		 $("#ddnumbererror").html("Please enter DD or GD number");
			 $('html,body').animate({
			        scrollTop: $("#ddnumbererror").offset().top - 250},'slow');
			 return false;
    	 }
    	 
    	 if($scope.formInfo.childImage=="resources/img/photo.jpg"||$scope.formInfo.childImage==undefined){
    		 $("#childimageerror").html("Please select child image");
    		 $('html,body').animate({
			        scrollTop: $("#childimageerror").offset().top - 250},'slow');
    		 return false;
    	 }else
    		 {
    		 $("#childimageerror").html(" ");
    		 }
    	
    	 
    	 var abuseObj=$scope.vebalAbusedBy;
    	 $scope.abuseObjecForErrorCheck=abuseObj.concat($scope.physicalAbusedBy,$scope.sexualAbusedBy);
    	 
    	 var checkboxList = [$scope.badHabits,$scope.goodHabits,$scope.majorityFriendTypes,$scope.abuseObjecForErrorCheck,$scope.differentlyAbledType];
    	 var errorDivList = ['#BadHabitsError','#GoodHabitsError','#MajorityError','#AbuseError','#differentlyAbledError'];
    	 var showErrorDiv = ['#BadHabitsError','#GoodHabitsError','#MajorityError','#AbuseError','#differentlyAbledError'];

    	 
    	 for(var i=0; i < checkboxList.length; i++){
 			for(var j=0; j<checkboxList[i].length; j++){
 				if(checkboxList[i][j].checked == true){
 					var index = showErrorDiv.indexOf(errorDivList[i]);
 						if(index != -1)
 					 showErrorDiv.splice(index, 1);
 					}
 			}
 		}	
    	 for(var i=0; i<errorDivList.length; i++){
    		 if(!$scope.formInfo.abused){
    			 if(showErrorDiv.indexOf("#AbuseError") != -1){
    				 showErrorDiv.splice(showErrorDiv.indexOf("#AbuseError"), 1);
    			 }
    		 }
    		 if(!$scope.formInfo.differentlyAbled){
    			 if(showErrorDiv.indexOf("#differentlyAbledError") != -1){
    				 showErrorDiv.splice(showErrorDiv.indexOf("#differentlyAbledError"), 1);
    			 }
    		 }
 			$(errorDivList[i]).html("");
 		}
         var count=0;
    	 for(var i=0;i<$scope.familyDetailsRepetitionArray.length;i++){
    		for(var j=0;j<$scope.familyDetailsRepetitionArray[i].length;j++){
	            if($scope.familyDetailsRepetitionArray[i][j].name=="(iv) Sex" ||
	         	   $scope.familyDetailsRepetitionArray[i][j].name=="(v) Education"){
		            	 if($scope.familyDetailsRepetitionArray[i][j].id==null){
		                	 count++;
		            	 }
	             }else{
	            	 if($scope.familyDetailsRepetitionArray[i][j].value==null||
	            	    $scope.familyDetailsRepetitionArray[i][j].value===""||
	               		$scope.familyDetailsRepetitionArray[i][j].value==undefined){
	                	 count++;
	            	 }
	             }
    	   }
    	 }
    	 if(count>0){
    		 $("#familydetailserror").html("Please enter family details");
			 $('html,body').animate({
			        scrollTop: $("#familydetailserror").offset().top - 250},'slow');
			 return false;
    	 }else{
    		 $("#familydetailserror").html("");
    	 }
    	 
    	 
    	 
    	 
	    if($scope.formInfo.entryDate === undefined){
	    	$( "#entryDate" ).datepicker("show");
	    }else if(showErrorDiv.length){
			 $(showErrorDiv[0]).html("Please select at least one option");
			 $('html,body').animate({
			        scrollTop: $(showErrorDiv[0]).offset().top - 250},'slow');
			 return false;
		}else{
	    	$('#thankyouModal').modal('show');
	    }
   };
   
 
    $scope.resetInput = function(model, key,errorId){
    	if(model[key] == 0){
    		model[key] = undefined;
        	document.getElementById(errorId).innerHTML = "Age cannot blank";
    	}
    };
    


//  $scope.hr ="";
//  $scope.ap="";
//  $scope.minute = "";

  //child_validation
  $timeout(function(){
  $http.get("getChild?childId="+$scope.selectedChildId).
  then(function(result){
	  	$scope.getTypeDetailsSuccessful = true;
	  	$scope.checkAllDataSuccessful();
	   	$scope.child=result.data;   	
        var a = $scope.child.childProducedTime.split(":");
	   	
//		  if( a[0]>12){
//			  if(a[0]-12>0 && a[0]-12<10){
//				  $scope.hr="0"+(a[0]-12).toString(); 
//			     }
//			  else{$scope.hr=(a[0]-12).toString();}
//			  $scope.minute = a[1];
//			  $scope.ap="PM";
//		   }
//		  else if(a[0]==="00"){
//			  $scope.hr="12";
//			  $scope.minute = a[1];
//			  $scope.ap="AM"; 
//		  }
//		  else{
//			  $scope.hr=a[0];
//			  $scope.minute = a[1];
//			  $scope.ap="AM";  
//		  }
       var b = $scope.child.childCameToCCITime.split(":");

 	   $scope.childWelfareCommitteeName;
 	   $scope.childProducedPlaceName;
 	  
   angular.forEach($scope.cwcList, function(value, key) {
 	   if(value.userId==result.data.childWelfareCommittee){
 		   $scope.childWelfareCommitteeName=value.name;
 		   $scope.childProducedPlaceName=value.areaName;
 	   }
	 
   });
	 
 });
  },1000);
})
.directive('ngFiles', ['$parse', function ($parse){
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

/*  for  */
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
					var val = '';
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
					var val = '';
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
					var val = '';
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
					var val = '';
				}
				
				var clean = val.toString().replace(/[^0-9]/g, '');
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
directive('onlyTwelveDigits', function () {

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
	            	 if(clean.length>10 ){
	            		 num =clean.slice(0,12);
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