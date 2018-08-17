/**
 * @author Pratyush Kumar Rath
 */
app.controller('ciclSocialInvestigation', function ($scope, $timeout, $http, commonService, $window,$filter,gettextCatalog) {
	
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
		$scope.langType = lang != 'en' ? 1 : 0;
		$scope.lang=lang;
		$http({
	        method : "GET",
	        url : "setLangString?language="+$scope.lang
	    }).then(function mySucces(response) {
	    	
	});
//		$scope.$apply();
	};
	$scope.selectedChildId=$('#modelValue').val();
//	$scope.selectedChildId = "233Allahbad2017";
	$scope.selectedId=$('#idValue').val();
	$(".loader").css("display", "block");
//	console.log($scope.selectedId);
	
	$scope.reDirect = function(){
		$window.location.href = 'ccts';
	};
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.getChildRegMstDataSuccessful 
				&& $scope.getChildByIdSuccessful && $scope.getTypeDetailsSuccessful
				&& $scope.getCICLSocialIvestigationreportSuccessful ){
			$(".loader").css("display", "none");
		}
	};
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
//		console.log($scope.menuList);
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
			}else{
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}
		}
	};
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
  	
  	$scope.resetphnNo = function(model,id){
  		if(model == undefined){
  			$("#"+id).val('');
  		}
  	};
  	
  	$scope.clearApprehendedOther = function(){
  		$scope.soiMainObj.apprehendedForOffenceDtls = null;
  	};
  	
	$("#perpetratorContactNo").keypress(function(event) {
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	$("#perpetratorAge").keypress(function(event) {
        if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
            return false;
        }
    });
	$scope.soiMainObj={};
	$scope.checkFlag;
//	console.log($filter('translate')("(i) Name"))
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
	$scope.formInfo = {};
	
	$http.get("getChildRegMstData").
    then(function(result){
    	$scope.getChildRegMstDataSuccessful = true;
    	$scope.checkAllDataSuccessful();
	   	$scope.cwcList=result.data.value.cwcList;
    });
	
	$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
	then(function(result){
		$scope.getChildByIdSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.selectedChild = result.data;
		$scope.soiMainObj.firNumber = $scope.selectedChild.firNumber;
		$scope.soiMainObj.underSections = $scope.selectedChild.sections;
		$scope.soiMainObj.policeStation = $scope.selectedChild.policeStation;
		$scope.soiMainObj.childName = $scope.selectedChild.childName;
	    $scope.soiMainObj.childAge = $scope.selectedChild.age;
	    $scope.cwcName = $scope.selectedChild.cwc.name;
	    var childSex = $scope.selectedChild.childSex;
	    $timeout(function(){
	 	   for(var i=0; i<$scope.childSex.length;i++){
	 		   if($scope.childSex[i].id==childSex)
//	 			   $scope.fetchedChildSex = $scope.childSex[i];
	 			  $scope.soiMainObj.childSex = $scope.childSex[i];
	 	   }
	     },500);
	    $scope.caseNumber = $scope.selectedChild.caseNum;
	    if($scope.selectedChild.sirFilled==null && $scope.selectedChild.finalOrderFilled==1){
	    	$('#finalOrderModal').modal('show');
	    }
	},function(error){
		console.log(error);
	});
    
	
	$scope.familyDetailsRepetition = {"1":""};
	var fdIndex = 1;
	$scope.familyDetailsRepetition[fdIndex] = JSON.parse(JSON.stringify($scope.soiFdObjRaw));
	$scope.familyDetailsRepetitionArray = $.map($scope.familyDetailsRepetition, function(value, index) {
	    return [value];
	});
    //This will hide the DIV by default.
    $scope.IsVisible = false;
	
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
    		document.getElementById('maximumLimit10Error').innerHTML = "You can add details of maximun <b>ten</b> family members";
    	}
    };
    
    $scope.deleteErrorMsg = function(){
   		document.getElementById('maximumLimit10Error').innerHTML = "";
    };
    
    $scope.deleteNewDetails = function(){
    	$scope.maximumLimit10 = false;
    	document.getElementById('maximumLimit10Error').innerHTML = "";
	    if($scope.familyDetailsRepetitionArray.length > 1){
	    	$scope.familyDetailsRepetitionArray.pop();
	    	$scope.soiFdObj.pop();
	    }
    };

   $http.get('getTypeDetails').
   then(function(result){
	   		$scope.getTypeDetailsSuccessful = true;
	   		$scope.checkAllDataSuccessful();
	   	   $scope.reportPreparedBy = result.data.reportPreparedBy;
	  	   $scope.childSex = result.data.childSex;
	  	   $scope.religionList = result.data.religionList;
	  	   $scope.casteList = result.data.casteList;
	  	   $scope.differentlyAbledType = result.data.differentlyAbledType;
	  	   
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
	  	   $scope.ciclOrgType = result.data.ciclOrgType;
	  	   $scope.natureOfOffenceAlleged = result.data.natureOfOffenceAlleged;
	  	   $scope.reasonForAllegedOffence = result.data.reasonForAllegedOffence;
	  	 
	  	   
//	  	 console.log(result.data);
   	   }, function(error){
   		   console.log(error);
   	   });
   
   $scope.showcast=false;
   $scope.showOtherCast=false;
   $scope.showReligionCast=function(){
   	if($scope.soiMainObj.childReligion.id==184){//hindu
   		$scope.showcast=true;
   		$scope.showOtherCast=false;
   	}else if($scope.soiMainObj.childReligion.id==187){//other
   		$scope.showcast=false;
   		$scope.showOtherCast=true;
   	}
   	else{
   		$scope.showcast=false;
   		$scope.showOtherCast=false;
   	}
   };
   
    $scope.religionOthers = function(){
		if($scope.soiMainObj.childReligion.id != 184)//hindu
				$scope.soiMainObj.childCast = {};
		
		if($scope.soiMainObj.childReligion.id != 187)//other
				$scope.soiMainObj.otherChildReligion = null;
	};
	
   $scope.fetchInstituteType = function(instObj){
	   if($scope.institutionDocType != undefined){
		   for(var i=0; i<$scope.institutionDocType.length;i++){
			   if($scope.institutionDocType[i].id==instObj.id)
				   $scope.fetchedName = $scope.institutionDocType[i].name; 
		   }
	   }
   };
   $scope.fetchReportPreparedBy = function(instObj){
	   if($scope.reportPreparedBy != undefined){
		   for(var i=0; i<$scope.ciclOrgType.length;i++){
			   if($scope.ciclOrgType[i].id==instObj.id)
				   $scope.fetchedReportPreparedBy = $scope.ciclOrgType[i];
		   }
	   }
   };
   
   $scope.clearName = function(){
	   $scope.soiMainObj.nameOfPerson = null;
   };
   
   $scope.arr = [];
   $scope.badArr = [];
   $scope.goodArr = [];
   $scope.reasonArr = [];
   $scope.maorityArr = [];
   $scope.reasonForLeavingArr = [];
   $scope.exploitationFacedArr = [];
   $scope.verbalAbusedByArr = [];
   $scope.reasonForAllegedOffenceArr = [];
   
   $scope.noClicked = function(){
	   $scope.checkFlag = false;
	   if($scope.differentlyAbledType != undefined){
		   for(var i=0; i<$scope.differentlyAbledType.length; i++){
			   $scope.differentlyAbledType[i].checked = false;
		   }
	   }
	   $scope.arr = [];
	   $scope.differentlyAbledTypeString = "";
	   $scope.keyString = "";
	   $scope.soiMainObj.otherDifferentlyAbled = null;
   };
   $scope.cmClicked = function(){
	   $scope.soiMainObj.spouseName = null;
	   $scope.soiMainObj.spouseAge = null;
	   $scope.soiMainObj.spouseDetails = null;
	   $scope.soiMainObj.childrenName1 = null;
	   $scope.soiMainObj.childrenAge1 = null;
	   $scope.soiMainObj.childrenSex1.id = null;
	   $scope.soiMainObj.childrenName2 = null;
	   $scope.soiMainObj.childrenAge2 = null;
	   $scope.soiMainObj.childrenSex2.id = null;
//	   if($scope.showUpdateBtn){
//		   $scope.soiMainObj.spouseName = null;
//		   $scope.soiMainObj.spouseAge = null;
//		   $scope.soiMainObj.spouseDetails = null;
//		   $scope.soiMainObj.childrenName1 = null;
//		   $scope.soiMainObj.childrenAge1 = null;
////		   $scope.soiMainObj.childrenSex1.id = null;
//		   $scope.soiMainObj.childrenName2 = null;
//		   $scope.soiMainObj.childrenAge2 = null;
////		   $scope.soiMainObj.childrenSex2.id = null;
//	   }
   };
   $scope.yesClicked = function(){
	   $scope.checkFlag = true;
   };
   $scope.nameString={};
   $scope.filterType = function(val) {
		if (val['name']=='Others (please specify)')
			return true;
		else
			return false;
	};
   $scope.setIds = function(selectedObjs){
	   $scope.selectedObjs=selectedObjs;
	   if($scope.selectedObjs.id==45){
		   angular.forEach($scope.differentlyAbledType, function(value, key){
			  if(value.id == 384 && value.checked == true)
				  value.checked = false;
		   });
		   $scope.soiMainObj.mentalRetireSeverity=null;
	   }else if($scope.selectedObjs.id==384){
		   angular.forEach($scope.differentlyAbledType, function(value, key){
			  if(value.id == 45 && value.checked == true)
				  value.checked = false;
		   });
		   $scope.soiMainObj.mentalIllSeverity=null;
	   }
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
		   if($scope.langType == 0)
			   $scope.differentlyAbledTypeString+=$scope.arr[j].name+",  ";
		   else
			   $scope.differentlyAbledTypeString+=$scope.arr[j].typeNameHindi+",  ";
	   }
	   $scope.keyString = $scope.keyString.substring(0, $scope.keyString.length-1);
	   if(!$scope.keyString.includes('46')){
		   $scope.soiMainObj.otherDifferentlyAbled = null;
	   }
	   $scope.differentlyAbledTypeString = $scope.differentlyAbledTypeString.substring(0, $scope.differentlyAbledTypeString.length-3);
  	   $scope.nameString.differentlyAbledTypeString = $scope.differentlyAbledTypeString;
  	   console.log("differentlyAbledTypeString: "+$scope.nameString.differentlyAbledTypeString);
   };
   
   $scope.setBadIds = function(selectedObjs){
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
		   if($scope.langType == 0)
			   $scope.badNameString+=$scope.badArr[j].name+",  ";
		   else
			   $scope.badNameString+=$scope.badArr[j].typeNameHindi+",  ";
	   }
	   $scope.keyBadString = $scope.keyBadString.substring(0, $scope.keyBadString.length-1);
	   if(!$scope.keyBadString.includes('61')){
		   $scope.soiMainObj.otherBadHabits = null;
	   }
	   if(!$scope.keyBadString.includes('58')){
		   $scope.soiMainObj.drugType = null;
	   }
	   $scope.badNameString = $scope.badNameString.substring(0, $scope.badNameString.length-3);
  	   $scope.nameString.badNameString = $scope.badNameString;
  	   console.log("bad habits: "+$scope.badNameString);
   };
   
   $scope.setGoodIds = function(selectedObjs){
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
		   if($scope.langType == 0)
			   $scope.goodNameString+=$scope.goodArr[j].name+",  ";
		   else
			   $scope.goodNameString+=$scope.goodArr[j].typeNameHindi+",  ";
	   }
	   $scope.keyGoodString = $scope.keyGoodString.substring(0, $scope.keyGoodString.length-1);
	   if(!$scope.keyGoodString.includes('55')){
		   $scope.soiMainObj.otherGoodHabits = null;
	   }
	   $scope.goodNameString = $scope.goodNameString.substring(0, $scope.goodNameString.length-3);
  	   $scope.nameString.goodNameString = $scope.goodNameString;
  	   console.log("good habits: "+$scope.nameString.goodNameString);
   };
   
   $scope.setReasonsForallegedOffence = function(selectedObjs){
	   $scope.reasonsForallegedOffenceString = "";
	   $scope.reasonsForallegedOffenceNameString = "";
	   $scope.reasonForAllegedOffenceArr.push(selectedObjs);
	   for(var i=0; i<$scope.reasonForAllegedOffenceArr.length; i++){
		   if($scope.reasonForAllegedOffenceArr[i].checked == false){
			   $scope.reasonForAllegedOffenceArr.splice(i, 1);
		   }
	   }
	   for(var i=0; i<$scope.reasonForAllegedOffenceArr.length; i++){
		   if($scope.reasonForAllegedOffenceArr[i].checked == false){
			   $scope.reasonForAllegedOffenceArr.splice(i, 1);
		   }
	   }
	   for(var j=0;j<$scope.reasonForAllegedOffenceArr.length;j++){
		   $scope.reasonsForallegedOffenceString+=$scope.reasonForAllegedOffenceArr[j].id+",";
		   if($scope.langType == 0)
			   $scope.reasonsForallegedOffenceNameString+=$scope.reasonForAllegedOffenceArr[j].name+",  ";
		   else
			   $scope.reasonsForallegedOffenceNameString+=$scope.reasonForAllegedOffenceArr[j].typeNameHindi+",  ";
	   }
	   $scope.reasonsForallegedOffenceString = $scope.reasonsForallegedOffenceString.substring(0, $scope.reasonsForallegedOffenceString.length-1);
	   if(!$scope.reasonsForallegedOffenceString.includes('368')){
		   $scope.soiMainObj.otherReasonForAllegedOffence = null;
	   }
	   $scope.reasonsForallegedOffenceNameString = $scope.reasonsForallegedOffenceNameString.substring(0, $scope.reasonsForallegedOffenceNameString.length-3);
  	   $scope.nameString.reasonForAllegedOffenceNameString = $scope.reasonsForallegedOffenceNameString;
  	   console.log("reasonsForallegedOffenceString: "+$scope.nameString.reasonForAllegedOffenceNameString);
   };
   
   $scope.setReasonIds = function(selectedObjs){
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
		   if($scope.langType == 0)
			   $scope.reasonNameString+=$scope.reasonArr[j].name+",  ";
		   else
			   $scope.reasonNameString+=$scope.reasonArr[j].typeNameHindi+",  ";
	   }
	   $scope.reasonString = $scope.reasonString.substring(0, $scope.reasonString.length-1);
	   if(!$scope.reasonString.includes('85')){
		   $scope.soiMainObj.otherReasonLeavingSchool = null;
	   }
	   $scope.reasonNameString = $scope.reasonNameString.substring(0, $scope.reasonNameString.length-3);
  	   $scope.nameString.reasonNameString = $scope.reasonNameString;
  	   console.log("reasons: "+$scope.nameString.reasonNameString);
   };
   
   $scope.setMajority = function(selectedObjs){
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
		   if($scope.langType == 0)
			   $scope.maorityNameString+=$scope.maorityArr[j].name+",  ";
		   else
			   $scope.maorityNameString+=$scope.maorityArr[j].typeNameHindi+",  ";
	   }
	   $scope.maorityString = $scope.maorityString.substring(0, $scope.maorityString.length-1);
	   $scope.maorityNameString = $scope.maorityNameString.substring(0, $scope.maorityNameString.length-3);
	   $scope.nameString.maorityNameString = $scope.maorityNameString;
  	   console.log("maorityString: "+$scope.nameString.maorityNameString);
   };
   
   $scope.setReasonsForLeaving = function(selectedObjs){
	   $scope.reasonForLeavingString = "";
	   $scope.reasonForLeavingFamilyNameString = "";
	   $scope.reasonForLeavingArr.push(selectedObjs);
	   for(var i=0; i<$scope.reasonForLeavingArr.length; i++){
		   if($scope.reasonForLeavingArr[i].checked == false){
			   $scope.reasonForLeavingArr.splice(i, 1);
		   }
	   }
	   for(var i=0; i<$scope.reasonForLeavingArr.length; i++){
		   if($scope.reasonForLeavingArr[i].checked == false){
			   $scope.reasonForLeavingArr.splice(i, 1);
		   }
	   }
	   for(var j=0;j<$scope.reasonForLeavingArr.length;j++){
		   $scope.reasonForLeavingString+=$scope.reasonForLeavingArr[j].id+",";
		   $scope.reasonForLeavingFamilyNameString+=$scope.reasonForLeavingArr[j].name+",  ";
	   }
	   $scope.reasonForLeavingString = $scope.reasonForLeavingString.substring(0, $scope.reasonForLeavingString.length-1);
	   $scope.reasonForLeavingFamilyNameString = $scope.reasonForLeavingFamilyNameString.substring(0, $scope.reasonForLeavingFamilyNameString.length-3);
	   $scope.nameString.reasonForLeavingFamilyNameString = $scope.reasonForLeavingFamilyNameString;
  	   console.log("reasonForLeavingFamilyNameString: "+$scope.nameString.reasonForLeavingFamilyNameString);
   };
   
   $scope.setexploitationFaced = function(selectedObjs){
	   $scope.exploitationFacedString = "";
	   $scope.exploitationFacedNameString = "";
	   $scope.exploitationFacedArr.push(selectedObjs);
	   for(var i=0; i<$scope.exploitationFacedArr.length; i++){
		   if($scope.exploitationFacedArr[i].checked == false){
			   $scope.exploitationFacedArr.splice(i, 1);
		   }
	   }
	   for(var i=0; i<$scope.exploitationFacedArr.length; i++){
		   if($scope.exploitationFacedArr[i].checked == false){
			   $scope.exploitationFacedArr.splice(i, 1);
		   }
	   }
	   for(var j=0;j<$scope.exploitationFacedArr.length;j++){
		   $scope.exploitationFacedString+=$scope.exploitationFacedArr[j].id+",";
		   $scope.exploitationFacedNameString+=$scope.exploitationFacedArr[j].name+",  ";
	   }
	   $scope.exploitationFacedString = $scope.exploitationFacedString.substring(0, $scope.exploitationFacedString.length-1);
	   $scope.exploitationFacedNameString = $scope.exploitationFacedNameString.substring(0, $scope.exploitationFacedNameString.length-3);
	   $scope.nameString.exploitationFacedNameString = $scope.exploitationFacedNameString;
  	   console.log("reasonForLeavingString: "+$scope.nameString.exploitationFacedNameString);
   };
   
   $scope.setVerbalAbusedBy = function(selectedObjs){
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
		   if($scope.langType == 0)
			   $scope.verbalAbusedNameString+=$scope.verbalAbusedByArr[j].name+",  ";
		   else
			   $scope.verbalAbusedNameString+=$scope.verbalAbusedByArr[j].typeNameHindi+",  ";
	   }
	   $scope.verbalAbusedString = $scope.verbalAbusedString.substring(0, $scope.verbalAbusedString.length-1);
	   if(!$scope.verbalAbusedString.includes('125')){
		   $scope.soiMainObj.otherVerbalAbuse = null;
	   }
	   $scope.verbalAbusedNameString = $scope.verbalAbusedNameString.substring(0, $scope.verbalAbusedNameString.length-3);
  	   $scope.nameString.verbalAbusedNameString = $scope.verbalAbusedNameString;
  	   console.log("verbalAbusedString: "+$scope.nameString.verbalAbusedNameString);
   };
   $scope.physicalAbusedByArr = [];
   $scope.setphysicalAbusedBy = function(selectedObjs){
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
		   if($scope.langType == 0)
			   $scope.physicalAbusedNameString+=$scope.physicalAbusedByArr[j].name+",  ";
		   else
			   $scope.physicalAbusedNameString+=$scope.physicalAbusedByArr[j].typeNameHindi+",  ";
	   }
	   $scope.physicalAbusedString = $scope.physicalAbusedString.substring(0, $scope.physicalAbusedString.length-1);
	   if(!$scope.physicalAbusedString.includes('125')){
		   $scope.soiMainObj.otherPhysicalAbuse = null;
	   }
	   $scope.physicalAbusedNameString = $scope.physicalAbusedNameString.substring(0, $scope.physicalAbusedNameString.length-3);
  	   $scope.nameString.physicalAbusedNameString = $scope.physicalAbusedNameString;
  	   console.log("physicalAbusedString: "+$scope.nameString.physicalAbusedNameString);
   };
   
   $scope.sexualAbusedByArr = [];
   $scope.setSexualAbusedBy = function(selectedObjs){
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
		   if($scope.langType == 0)
			   $scope.sexualAbusedNameString+=$scope.sexualAbusedByArr[j].name+",  ";
		   else
			   $scope.sexualAbusedNameString+=$scope.sexualAbusedByArr[j].typeNameHindi+",  ";
	   }
	   $scope.sexualAbusedString = $scope.sexualAbusedString.substring(0, $scope.sexualAbusedString.length-1);
	   if(!$scope.sexualAbusedString.includes('125')){
		   $scope.soiMainObj.otherSexualAbuse = null;
	   }
	   $scope.sexualAbusedNameString = $scope.sexualAbusedNameString.substring(0, $scope.sexualAbusedNameString.length-3);
	   $scope.nameString.sexualAbusedNameString = $scope.sexualAbusedNameString;
  	   console.log("sexualAbusedString: "+$scope.nameString.sexualAbusedNameString);
   };       
   
   var socialInvestigationReportFamilyDetails = $scope.familyDetailsRepetitionArray;
  //for getting data from database for selected child if available
   $scope.showUpdateBtn = false;
   $scope.showSubmitBtn = false;
   
   $scope.differentlyAbledYesValue = true;
   $scope.differentlyAbledNoValue = false;
   
   $scope.childMarriedYesValue = true;
   $scope.childMarriedNoValue = false;
   
   $scope.childAddictionYesValue = true;
   $scope.childAddictionNoValue = false;
   
   $scope.childVictimYesValue = true;
   $scope.childVictimNoValue = false;
   
   $scope.childBoughtSoldYesValue = true;
   $scope.childBoughtSoldNoValue = false;
   
   $scope.childBeggingYesValue = true;
   $scope.childBeggingNoValue = false;
   
   $scope.usedByAnyGangYesValue = true;
   $scope.usedByAnyGangNoValue = false;
   
   $scope.middleMenInvolvedYesValue = true;
   $scope.middleMenInvolvedNoValue = false;
   
   $scope.otherChildAbusedYesValue = true;
   $scope.otherChildAbusedNoValue = false;
   
   $scope.hoiFatherArYesValue = true;
   $scope.hoiFatherArNoValue = false;
   
   $scope.hoiStepFatherArYesValue = true;
   $scope.hoiStepFatherArNoValue = false;
   
   $scope.hoiMotherArYesValue = true;
   $scope.hoiMotherArNoValue = false;
   
   $scope.hoiStepMotherArYesValue = true;
   $scope.hoiStepMotherArNoValue = false;
   
   $scope.hoiBrotherArYesValue = true;
   $scope.hoiBrotherArNoValue = false;
   
   $scope.hoiSisterArYesValue = true;
   $scope.hoiSisterArNoValue = false;
   
   $scope.hoiOthersArYesValue = true;
   $scope.hoiOthersArNoValue = false;
   
   $scope.apprehendedYesValue = true;
   $scope.apprehendedNoValue = false;
   
   $scope.newSub = {};
   
   var getdata = function(){
	   $(".loader").css("display", "block");
	   $http.get('getCICLSocialIvestigationreport?childId='+$scope.selectedChildId).
	   then(function(result){
		   $(".loader").css("display", "none");
		   checkSessionOut(result.data);
		   $scope.newSub = result.data;
	   },function(error){
			console.log(error);
		});
   };
   $scope.dynamicBlur = function (value,objIndex,index) {
  		$scope.familyDetailsRepetitionArray[objIndex][index].value =value.trim();  
   }
   $scope.blur = function (value,name) {
 	  if(name=="otherDifferentlyAbled"){
     	  $scope.soiMainObj.otherDifferentlyAbled=value.trim();  
 	  }else if(name=="otherReasonLeavingSchool"){
     	  $scope.soiMainObj.otherReasonLeavingSchool=value.trim();  
 	  }else if(name=="otherReasonForAllegedOffence"){
     	  $scope.soiMainObj.otherReasonForAllegedOffence=value.trim();  
 	  }else if(name=="otherVerbalAbuse"){
     	  $scope.soiMainObj.otherVerbalAbuse=value.trim();  
 	  }else if(name=="otherPhysicalAbuse"){
     	  $scope.soiMainObj.otherPhysicalAbuse=value.trim();  
 	  }else if(name=="otherSexualAbuse"){
     	  $scope.soiMainObj.otherSexualAbuse=value.trim();  
 	  }else if(name=="otherInOtherAbuse"){
     	  $scope.soiMainObj.otherInOtherAbuse=value.trim();  
 	  }else if(name=="otherBadHabits"){
     	  $scope.soiMainObj.otherBadHabits=value.trim();  
 	  }else if(name=="otherGoodHabits"){
     	  $scope.soiMainObj.otherGoodHabits=value.trim();  
 	  }else if(name=="apprehendedForOffenceDtls"){
     	  $scope.soiMainObj.apprehendedForOffenceDtls=value.trim();  
 	  }else if(name=="nameOfPerson"){
    	  $scope.soiMainObj.nameOfPerson=value.trim(); 
	  }
 	  
   }
   
   $http.get('getCICLSocialIvestigationreport?childId='+$scope.selectedChildId).
	then(function(result){
		if(result.data!=""){
			$timeout(function(){	
			$scope.soiMainObj = result.data;
			if($scope.soiMainObj.childReligion != null){
				if($scope.soiMainObj.childReligion.id == 187)
					$scope.showOtherCast=true;
				else if($scope.soiMainObj.childReligion.id == 184)
					$scope.showcast=true;
			}
			$scope.fetchedReportPreparedBy = $scope.soiMainObj.ciclOrgType;
			$scope.childImage = result.childPhoto == "" ? null : result.childPhoto;
			$scope.printFitData = $scope.soiMainObj;
			var fetchedFamilyDetails = result.data.cICLSocialInvestigationReportFamilyDetailsModel;
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
		    		   			familyDetailsArrayforFetch[i][0][j].key = familyDetailsArrayforFetch[i][0][j].name;
		    		   			familyDetailsArrayforFetch[i][0][j].name = "(iv) Sex";
	    		   			}	
	    		   			break;
	    		   		case '(v) Education':
	    		   			if(fetchedFamilyDetails[i].education!=null){
		    		   			familyDetailsArrayforFetch[i][0][j]=fetchedFamilyDetails[i].education;
		    		   			familyDetailsArrayforFetch[i][0][j].desc = "eduDropdown";
		    		   			familyDetailsArrayforFetch[i][0][j].key = familyDetailsArrayforFetch[i][0][j].name;
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
			
			
			$scope.showUpdateBtn = true;
			$scope.ifSubmittedDisable = true;
			if($scope.soiMainObj.childMarried)
				$scope.showSelection('Yes');
			if($scope.soiMainObj.childDifferentlyAbled)
				$scope.ShowPassport('Y');
			if($scope.soiMainObj.institutionDocType != null)
				$scope.fetchInstituteType($scope.soiMainObj.institutionDocType);
			if($scope.soiMainObj.reportPreparedBy != null)
				$scope.fetchReportPreparedBy($scope.soiMainObj.reportPreparedBy);
//				$scope.IsChildMarriedVisible = value == "Yes";
			//do multiple select check-box prefetch here
			if($scope.soiMainObj.goodHabits != null){
				var pfGoodHabits = $scope.soiMainObj.goodHabits;
				$scope.goodNameString = "";
				$scope.keyGoodString = $scope.soiMainObj.goodHabits;
				$scope.pfGoodHabitArr = pfGoodHabits.split(',');
				for(var i=0; i < $scope.pfGoodHabitArr.length; i++){
					for(var j=0; j < $scope.goodHabits.length; j++){
						if(Number($scope.pfGoodHabitArr[i]) == $scope.goodHabits[j].id){
							$scope.goodHabits[j].checked = true;
							$scope.goodNameString+=$scope.langType == 0 ? $scope.goodHabits[j].name+",  " : $scope.goodHabits[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.goodNameString = $scope.goodNameString.substring(0, $scope.goodNameString.length-3);
				$scope.nameString.goodNameString = $scope.goodNameString;
			}
			if($scope.soiMainObj.badHabits != null){
				var pfBadHabits = $scope.soiMainObj.badHabits;
				$scope.badNameString = "";
				$scope.keyBadString = $scope.soiMainObj.badHabits;
				$scope.pfBadHabitArr = pfBadHabits.split(',');
				for(var i=0; i < $scope.pfBadHabitArr.length; i++){
					for(var j=0; j < $scope.badHabits.length; j++){
						if(Number($scope.pfBadHabitArr[i]) == $scope.badHabits[j].id){
							$scope.badHabits[j].checked = true;
							$scope.badNameString+=$scope.langType == 0 ? $scope.badHabits[j].name+",  ":$scope.badHabits[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.badNameString = $scope.badNameString.substring(0, $scope.badNameString.length-3);
				$scope.nameString.badNameString = $scope.badNameString;
			}
			if($scope.soiMainObj.reasonLeavingSchool != null){
				var pfReasonLeavingSchool = $scope.soiMainObj.reasonLeavingSchool;
				$scope.reasonNameString = "";
				$scope.reasonString = $scope.soiMainObj.reasonLeavingSchool;
				$scope.pfReasonLeavingSchoolArr = pfReasonLeavingSchool.split(',');
				for(var i=0; i < $scope.pfReasonLeavingSchoolArr.length; i++){
					for(var j=0; j < $scope.reasonLeavingSchool.length; j++){
						if(Number($scope.pfReasonLeavingSchoolArr[i]) == $scope.reasonLeavingSchool[j].id){
							$scope.reasonLeavingSchool[j].checked = true;
							$scope.reasonNameString+=$scope.langType == 0 ? $scope.reasonLeavingSchool[j].name+",  ":$scope.reasonLeavingSchool[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.reasonNameString = $scope.reasonNameString.substring(0, $scope.reasonNameString.length-3);
				$scope.nameString.reasonNameString = $scope.reasonNameString;
			}
			
//-----------------------------------------------------------------------------------------------
			if($scope.soiMainObj.reasonForAllegedOffence != null){
				var pfReasonForAllegedOffence = $scope.soiMainObj.reasonForAllegedOffence;
				$scope.reasonsForallegedOffenceNameString = "";
				$scope.reasonsForallegedOffenceString = $scope.soiMainObj.reasonForAllegedOffence;
				$scope.pfReasonForAllegedOffenceArr = pfReasonForAllegedOffence.split(",");
				for(var i=0; i < $scope.pfReasonForAllegedOffenceArr.length; i++){
					for(var j=0; j < $scope.reasonForAllegedOffence.length; j++){
						if(Number($scope.pfReasonForAllegedOffenceArr[i] == $scope.reasonForAllegedOffence[j].id)){
							$scope.reasonForAllegedOffence[j].checked = true;
							$scope.reasonsForallegedOffenceNameString+=$scope.langType == 0 ? $scope.reasonForAllegedOffence[j].name+",  ":$scope.reasonForAllegedOffence[j].typeNameHindi+",  ";
						}
					}
				}
			    $scope.reasonsForallegedOffenceNameString = $scope.reasonsForallegedOffenceNameString.substring(0, $scope.reasonsForallegedOffenceNameString.length-3);
		  	    $scope.nameString.reasonForAllegedOffenceNameString = $scope.reasonsForallegedOffenceNameString;
			}
//-----------------------------------------------------------------------------------------------			
			
			if($scope.soiMainObj.majorityFriendTypes != null){
				var pfMajorityFriendTypes = $scope.soiMainObj.majorityFriendTypes;
				$scope.maorityNameString = "";
				$scope.maorityString = $scope.soiMainObj.majorityFriendTypes;
				$scope.pfMajorityFriendTypesArr = pfMajorityFriendTypes.split(',');
				for(var i=0; i < $scope.pfMajorityFriendTypesArr.length; i++){
					for(var j=0; j < $scope.majorityFriendTypes.length; j++){
						if(Number($scope.pfMajorityFriendTypesArr[i]) == $scope.majorityFriendTypes[j].id){
							$scope.majorityFriendTypes[j].checked = true;
							$scope.maorityNameString+=$scope.langType == 0 ? $scope.majorityFriendTypes[j].name+",  ":$scope.majorityFriendTypes[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.maorityNameString = $scope.maorityNameString.substring(0, $scope.maorityNameString.length-3);
				$scope.nameString.maorityNameString = $scope.maorityNameString;
			}
			if($scope.soiMainObj.verbalAbuse != null){
				var pfVerbalAbuse = $scope.soiMainObj.verbalAbuse;
				$scope.verbalAbusedNameString = "";
				$scope.verbalAbusedString = $scope.soiMainObj.verbalAbuse;
				$scope.pfVerbalAbuseArr = pfVerbalAbuse.split(',');
				for(var i=0; i < $scope.pfVerbalAbuseArr.length; i++){
					for(var j=0; j < $scope.vebalAbusedBy.length; j++){
						if(Number($scope.pfVerbalAbuseArr[i]) == $scope.vebalAbusedBy[j].id){
							$scope.vebalAbusedBy[j].checked = true;
							$scope.verbalAbusedNameString+=$scope.langType == 0 ? $scope.vebalAbusedBy[j].name+",  ":$scope.vebalAbusedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.verbalAbusedNameString = $scope.verbalAbusedNameString.substring(0, $scope.verbalAbusedNameString.length-3);
				$scope.nameString.verbalAbusedNameString = $scope.verbalAbusedNameString;
			}
			if($scope.soiMainObj.physicalAbuse != null){
				var pfPhysicalAbuse = $scope.soiMainObj.physicalAbuse;
				$scope.physicalAbusedNameString = "";
				$scope.physicalAbusedString = $scope.soiMainObj.physicalAbuse;
				$scope.pfPhysicalAbuseArr = pfPhysicalAbuse.split(',');
				for(var i=0; i < $scope.pfPhysicalAbuseArr.length; i++){
					for(var j=0; j < $scope.physicalAbusedBy.length; j++){
						if(Number($scope.pfPhysicalAbuseArr[i]) == $scope.physicalAbusedBy[j].id){
							$scope.physicalAbusedBy[j].checked = true;
							$scope.physicalAbusedNameString+=$scope.langType == 0 ? $scope.physicalAbusedBy[j].name+",  ":$scope.physicalAbusedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.physicalAbusedNameString = $scope.physicalAbusedNameString.substring(0, $scope.physicalAbusedNameString.length-3);
				$scope.nameString.physicalAbusedNameString = $scope.physicalAbusedNameString;
			}
			if($scope.soiMainObj.sexualAbuse != null){
				var pfSexualAbuse = $scope.soiMainObj.sexualAbuse;
				$scope.sexualAbusedNameString = "";
				$scope.sexualAbusedString = $scope.soiMainObj.sexualAbuse;
				$scope.pfSexualAbuseArr = pfSexualAbuse.split(',');
				for(var i=0; i < $scope.pfSexualAbuseArr.length; i++){
					for(var j=0; j < $scope.sexualAbusedBy.length; j++){
						if(Number($scope.pfSexualAbuseArr[i]) == $scope.sexualAbusedBy[j].id){
							$scope.sexualAbusedBy[j].checked = true;
							$scope.sexualAbusedNameString+=$scope.langType == 0 ? $scope.sexualAbusedBy[j].name+",  ":$scope.sexualAbusedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.sexualAbusedNameString = $scope.sexualAbusedNameString.substring(0, $scope.sexualAbusedNameString.length-3);
				$scope.nameString.sexualAbusedNameString = $scope.sexualAbusedNameString;
			}
			if($scope.soiMainObj.illTreatmentDenialOfFood != null){
				var pfIllTreatmentDenialOfFood = $scope.soiMainObj.illTreatmentDenialOfFood;
				$scope.dofIllTreatedNameString = "";
				$scope.dofIllTreatedString = $scope.soiMainObj.illTreatmentDenialOfFood;
				$scope.pfIllTreatmentDenialOfFoodArr = pfIllTreatmentDenialOfFood.split(',');
				for(var i=0; i < $scope.pfIllTreatmentDenialOfFoodArr.length; i++){
					for(var j=0; j < $scope.dofIllTreatedBy.length; j++){
						if(Number($scope.pfIllTreatmentDenialOfFoodArr[i]) == $scope.dofIllTreatedBy[j].id){
							$scope.dofIllTreatedBy[j].checked = true;
							$scope.dofIllTreatedNameString+=$scope.langType == 0 ? $scope.dofIllTreatedBy[j].name+",  ":$scope.dofIllTreatedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.dofIllTreatedNameString = $scope.dofIllTreatedNameString.substring(0, $scope.dofIllTreatedNameString.length-3);
				$scope.nameString.dofIllTreatedNameString = $scope.dofIllTreatedNameString;
			}
			if($scope.soiMainObj.illTreatmentBeatenMercilessly != null){
				var pfIllTreatmentBeatenMercilessly = $scope.soiMainObj.illTreatmentBeatenMercilessly;
				$scope.bmIllTreatedNameString = "";
				$scope.bmIllTreatedString = $scope.soiMainObj.illTreatmentBeatenMercilessly;
				$scope.pfIllTreatmentBeatenMercilesslyArr = pfIllTreatmentBeatenMercilessly.split(',');
				for(var i=0; i < $scope.pfIllTreatmentBeatenMercilesslyArr.length; i++){
					for(var j=0; j < $scope.bmIllTreatedBy.length; j++){
						if(Number($scope.pfIllTreatmentBeatenMercilesslyArr[i]) == $scope.bmIllTreatedBy[j].id){
							$scope.bmIllTreatedBy[j].checked = true;
							$scope.bmIllTreatedNameString+=$scope.langType == 0 ? $scope.bmIllTreatedBy[j].name+",  ":$scope.bmIllTreatedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.bmIllTreatedNameString = $scope.bmIllTreatedNameString.substring(0, $scope.bmIllTreatedNameString.length-3);
				$scope.nameString.bmIllTreatedNameString = $scope.bmIllTreatedNameString;
			}
			if($scope.soiMainObj.illTreatmentCausingInjury != null){
				var pfIllTreatmentCausingInjury = $scope.soiMainObj.illTreatmentCausingInjury;
				$scope.ciIllTreatedNameString = "";
				$scope.ciIllTreatedString = $scope.soiMainObj.illTreatmentCausingInjury;
				$scope.pfIllTreatmentCausingInjuryArr = pfIllTreatmentCausingInjury.split(',');
				for(var i=0; i < $scope.pfIllTreatmentCausingInjuryArr.length; i++){
					for(var j=0; j < $scope.ciIllTreatedBy.length; j++){
						if(Number($scope.pfIllTreatmentCausingInjuryArr[i]) == $scope.ciIllTreatedBy[j].id){
							$scope.ciIllTreatedBy[j].checked = true;
							$scope.ciIllTreatedNameString+=$scope.langType == 0 ? $scope.ciIllTreatedBy[j].name+",  ":$scope.ciIllTreatedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.ciIllTreatedNameString = $scope.ciIllTreatedNameString.substring(0, $scope.ciIllTreatedNameString.length-3);
				$scope.nameString.ciIllTreatedNameString = $scope.ciIllTreatedNameString;
			}
			if($scope.soiMainObj.illTreatmentDetention != null){
				var pfIllTreatmentDetention = $scope.soiMainObj.illTreatmentDetention;
				$scope.dpIllTreatedNameString = "";
				$scope.dpIllTreatedString = $scope.soiMainObj.illTreatmentDetention;
				$scope.pfIllTreatmentDetentionArr = pfIllTreatmentDetention.split(',');
				for(var i=0; i < $scope.pfIllTreatmentDetentionArr.length; i++){
					for(var j=0; j < $scope.dpIllTreatedBy.length; j++){
						if(Number($scope.pfIllTreatmentDetentionArr[i]) == $scope.dpIllTreatedBy[j].id){
							$scope.dpIllTreatedBy[j].checked = true;
							$scope.dpIllTreatedNameString+=$scope.langType == 0 ? $scope.dpIllTreatedBy[j].name+",  ":$scope.dpIllTreatedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.dpIllTreatedNameString = $scope.dpIllTreatedNameString.substring(0, $scope.dpIllTreatedNameString.length-3);
				$scope.nameString.dpIllTreatedNameString = $scope.dpIllTreatedNameString;
			}
			if($scope.soiMainObj.exploitationFaced != null){
				var pfExploitationFaced = $scope.soiMainObj.exploitationFaced;
				$scope.exploitationFacedNameString = "";
				$scope.exploitationFacedString = $scope.soiMainObj.exploitationFaced;
				$scope.pfExploitationFacedArr = pfExploitationFaced.split(',');
				for(var i=0; i < $scope.pfExploitationFacedArr.length; i++){
					for(var j=0; j < $scope.exploitationFacedBy.length; j++){
						if(Number($scope.pfExploitationFacedArr[i]) == $scope.exploitationFacedBy[j].id){
							$scope.exploitationFacedBy[j].checked = true;
							$scope.exploitationFacedNameString+=$scope.langType == 0 ? $scope.exploitationFacedBy[j].name+",  ":$scope.exploitationFacedBy[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.exploitationFacedNameString = $scope.exploitationFacedNameString.substring(0, $scope.exploitationFacedNameString.length-3);
				$scope.nameString.exploitationFacedNameString = $scope.exploitationFacedNameString;
			}
			if($scope.soiMainObj.reasonsLeavingFamily != null){
				var pfReasonsLeavingFamily = $scope.soiMainObj.reasonsLeavingFamily;
				$scope.reasonForLeavingFamilyNameString = "";
				$scope.reasonForLeavingString = $scope.soiMainObj.reasonsLeavingFamily;
				$scope.pfReasonsLeavingFamilyArr = pfReasonsLeavingFamily.split(',');
				for(var i=0; i < $scope.pfReasonsLeavingFamilyArr.length; i++){
					for(var j=0; j < $scope.reasonsLeavingFamily.length; j++){
						if(Number($scope.pfReasonsLeavingFamilyArr[i]) == $scope.reasonsLeavingFamily[j].id){
							$scope.reasonsLeavingFamily[j].checked = true;
							$scope.reasonForLeavingFamilyNameString+=$scope.langType == 0 ? $scope.reasonsLeavingFamily[j].name+",  ":$scope.reasonsLeavingFamily[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.reasonForLeavingFamilyNameString = $scope.reasonForLeavingFamilyNameString.substring(0, $scope.reasonForLeavingFamilyNameString.length-3);
				$scope.nameString.reasonForLeavingFamilyNameString = $scope.reasonForLeavingFamilyNameString;
			}
			if($scope.soiMainObj.differentlyAbledType != null){
				var differentlyAbled = $scope.soiMainObj.differentlyAbledType;
				$scope.differentlyAbledTypeString = "";
				$scope.keyString = $scope.soiMainObj.differentlyAbledType;
				$scope.pfDifferentlyAbledArr = differentlyAbled.split(',');
				for(var i=0; i < $scope.pfDifferentlyAbledArr.length; i++){
					for(var j=0; j < $scope.differentlyAbledType.length; j++){
						if(Number($scope.pfDifferentlyAbledArr[i]) == $scope.differentlyAbledType[j].id){
							$scope.differentlyAbledType[j].checked = true;
							if($scope.differentlyAbledType[j].id==45)
								$scope.differentlyAbledTypeString += $scope.langType == 0 ? $scope.differentlyAbledType[j].name+"("+$scope.soiMainObj.mentalIllSeverity+") ,  " :
									   								 					$scope.differentlyAbledType[j].typeNameHindi+"("+$scope.soiMainObj.mentalIllSeverity+") ,  "	;
							else if($scope.differentlyAbledType[j].id==384)
								$scope.differentlyAbledTypeString += $scope.langType == 0 ? $scope.differentlyAbledType[j].name+"("+$scope.soiMainObj.mentalRetireSeverity+") ,  " :
																						$scope.differentlyAbledType[j].typeNameHindi+"("+$scope.soiMainObj.mentalRetireSeverity+") ,  ";
							else
								$scope.differentlyAbledTypeString += $scope.langType == 0 ? $scope.differentlyAbledType[j].name+",  " : $scope.differentlyAbledType[j].typeNameHindi+",  ";
						}
					}
				}
				$scope.differentlyAbledTypeString = $scope.differentlyAbledTypeString.substring(0, $scope.differentlyAbledTypeString.length-3);
				$scope.nameString.differentlyAbledTypeString = $scope.differentlyAbledTypeString;
			}
			},500);
//		  	   $scope.exploitationFacedBy = result.data.exploitationFaced;
//		  	   $scope.institutionDocType = result.data.institutionDocType;
		}else{
			$scope.showSubmitBtn = true;
			$scope.soiMainObj.childDifferentlyAbled = false;
			$scope.soiMainObj.childMarried = false;
			$scope.soiMainObj.childVictim = false;
			$scope.soiMainObj.usedByAnyGang = false;
			$scope.soiMainObj.hoiFatherAr = false;
			$scope.soiMainObj.hoiStepFatherAr = false;
			$scope.soiMainObj.hoiMotherAr = false;
			$scope.soiMainObj.hoiStepMotherAr = false;
			$scope.soiMainObj.hoiBrotherAr = false;
			$scope.soiMainObj.hoiSisterAr = false;
			$scope.soiMainObj.hoiOthersAr = false;
			$scope.soiMainObj.childSubjectedOfAbuse = false;
			$scope.soiMainObj.apprehendedForOffence = false;
		}
		$scope.getCICLSocialIvestigationreportSuccessful = true;
		$scope.checkAllDataSuccessful();
	},function(error){
		console.log(error);
	});
   
   $(".pocTakingE").keypress(function(event) {
       if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
           return false;
       }
   });
   
   $scope.update = function(){
	   $scope.soiMainObj.id = Number($scope.selectedId); 
	   $scope.saveData();
   };
   $("#producerAge").keypress(function(event) {
       if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
           return false;
       }
   });
   
   $scope.validateForm = function(){
	   if($scope.soiMainObj.childDifferentlyAbled == true){
		   if($scope.keyString == "" || $scope.keyString == undefined){
			   $("#differentlyabledError").html("please select at least one option");
				 $('html,body').animate({
				        scrollTop: $("#differentlyabledError").offset().top - 250},'slow');
				 return false;
		   }else{
			   $("#differentlyabledError").html("");
		   }
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
 	            	    $scope.familyDetailsRepetitionArray[i][j].value==""||
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
	  	 
       var abuseObj=$scope.vebalAbusedBy;
       $scope.abuseObjecForErrorCheck=abuseObj.concat($scope.physicalAbusedBy,$scope.sexualAbusedBy);
       
	   var checkboxList = [$scope.badHabits,$scope.goodHabits,$scope.abuseObjecForErrorCheck,$scope.reasonForAllegedOffence];
		                    
	   var errorDivList = ['#badHabitsListSocialError','#goodHabitsListSocialError','#AbuseError','#allegedOffenceError'];
	   
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
	   
	   if($scope.childImage == ""){
		   console.log("");
	   }else{
		   $('#socialInvestigationModal').modal('show');
	   }
      
   };
   
   $scope.eraseErrorMsg = function(errorId){
	   document.getElementById(errorId).innerHTML="";
   };
   $scope.validateFDAge = function (errorId, obj){
	   if(errorId=='personAgeerror' && obj > 99){
		   $scope.soiMainObj.spouseAge=null;
           return false;
	   }
   };
   $scope.validateFdArrAge = function (name, errorId, FdIndex, incomeIndex){
	   if(Number(name) > 99){
		   var ss = ($scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value).toString();
		   $scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value = Number(ss.slice(0, 2));
		   document.getElementById('familyMemberAgeError'+FdIndex+incomeIndex).innerHTML = "Age cannot be more than 99";
           return false;
	   }
   };
   $scope.validateFdIncome = function (name, errorId, FdIndex, incomeIndex){
//	   if(name > 1000000){
//   	   	   $scope.familyDetailsRepetitionArray[FdIndex][incomeIndex].value = null;
//           return false;
//	   }
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
   $scope.validateName = function (name, errorId){
	   if(errorId=='childAgeError' && $scope.soiMainObj.childAge > 18){
		   document.getElementById(errorId).innerHTML = "Age cannot be more than 18";
	       $scope.soiMainObj.childAge="";
	       return false;
   	   }else if(errorId=='personAgeerror' && name == undefined){
   	   	   document.getElementById(errorId).innerHTML = "Age cannot be more than 99";
           $scope.soiMainObj.spouseAge="";
           return false;
	   }else if(errorId=='childrenAge1Error' && name > 10 || errorId=='childrenAge1Error' && name == undefined){
   	   	   document.getElementById(errorId).innerHTML = "Age cannot be more than 10";
           $scope.soiMainObj.childrenAge1="";
           return false;
	   }else if(errorId=='childrenAge2Error' && name > 10 || errorId=='childrenAge2Error' && name == undefined){
   	   	   document.getElementById(errorId).innerHTML = "Age cannot be more than 10";
           $scope.soiMainObj.childrenAge2="";
           return false;
	   }
	   if( name == undefined  ){
        	if(errorId=='phoneNoError') {
        		document.getElementById(errorId).innerHTML = "Please enter 10 digit phone number";
  	            document.socialIR.contactNumbber.focus() ;
  	            return false;
        	}else if(errorId=='phoneNoError2'){
        		document.getElementById(errorId).innerHTML = "Please enter 10 digit phone number";
  	            document.socialIR.prptrContact.focus() ;
        	}
       }else{
    	   document.getElementById(errorId).innerHTML = "";
    	   return true;
       }
   };
   $scope.resetInput = function(model, key,errorId){
   	if(model[key] == 0){
   		model[key] = undefined;
       	document.getElementById(errorId).innerHTML = "Age cannot blank";
   	}
   	
   }
   $scope.saveData = function(){
	   var finalFdArr=[];
       for(var i=0;i<socialInvestigationReportFamilyDetails.length;i++){
    	   var finalObj = {};
    	   for(var j=0;j<socialInvestigationReportFamilyDetails[i].length;j++){
    		   switch(socialInvestigationReportFamilyDetails[i][j].name){
    		   case '(i) Name': 
		   			finalObj.name=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
		   		case '(ii) Age': 
		   			finalObj.age=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
		   		case '(iii) Relationship': 
		   			finalObj.relationship=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
		   		case '(iv) Sex': 
		   			finalObj.sex=socialInvestigationReportFamilyDetails[i][j];
		   			break;
		   		case '(v) Education': 
		   			finalObj.education=socialInvestigationReportFamilyDetails[i][j];
		   			break;
		   		case '(vi) Occupation': 
		   			finalObj.occupation=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
		   		case '(vii) Income': 
		   			finalObj.income=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
		   		case '(viii) Health status': 
		   			finalObj.healthStatus=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
		   		case '(ix) History of mental illness': 
		   			finalObj.historyOfMentalIllness=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
		   		case '(x) Addiction': 
		   			finalObj.addictions=socialInvestigationReportFamilyDetails[i][j].value;
		   			break;
    		   }
    		   
    	   }
    	   finalFdArr.push(finalObj);
       }

	   $scope.soiMainObj.childId = $scope.selectedChildId;
	   $scope.soiMainObj.childDifferentlyAbled = Number($scope.soiMainObj.childDifferentlyAbled);
	   $scope.soiMainObj.childVictim = Number($scope.soiMainObj.childVictim);
	   $scope.soiMainObj.usedByAnyGang = Number($scope.soiMainObj.usedByAnyGang);
	   
	   $scope.soiMainObj.hoiFatherAr=Number($scope.soiMainObj.hoiFatherAr);
	   $scope.soiMainObj.hoiStepFatherAr=Number($scope.soiMainObj.hoiStepFatherAr);
	   $scope.soiMainObj.hoiMotherAr=Number($scope.soiMainObj.hoiMotherAr);
	   $scope.soiMainObj.hoiStepMotherAr=Number($scope.soiMainObj.hoiStepMotherAr);
	   $scope.soiMainObj.hoiBrotherAr=Number($scope.soiMainObj.hoiBrotherAr);
	   $scope.soiMainObj.hoiSisterAr=Number($scope.soiMainObj.hoiSisterAr);
	   $scope.soiMainObj.hoiOthersAr=Number($scope.soiMainObj.hoiOthersAr);
	   $scope.soiMainObj.childSubjectedOfAbuse = Number($scope.soiMainObj.childSubjectedOfAbuse);
	   $scope.soiMainObj.apprehendedForOffence = Number($scope.soiMainObj.apprehendedForOffence);
	   
	   $scope.soiMainObj.childMarried = Number($scope.soiMainObj.childMarried);
	   
	   if($scope.checkFlag)
		   $scope.soiMainObj.differentlyAbledType = $scope.keyString;
	   else
		   $scope.soiMainObj.differentlyAbledType = null;
	   
	   $scope.soiMainObj.goodHabits = $scope.keyGoodString;
	   $scope.soiMainObj.badHabits = $scope.keyBadString;
	   $scope.soiMainObj.reasonLeavingSchool = $scope.reasonString;
	   $scope.soiMainObj.majorityFriendTypes = $scope.maorityString;
	   $scope.soiMainObj.verbalAbuse = $scope.verbalAbusedString;
	   $scope.soiMainObj.physicalAbuse = $scope.physicalAbusedString;
	   $scope.soiMainObj.sexualAbuse = $scope.sexualAbusedString;
	   $scope.soiMainObj.reasonForAllegedOffence = $scope.reasonsForallegedOffenceString
	   $scope.soiMainObj.jjbAddress = $scope.soiMainObj.jjbAddress.replace(/\n/g, " ");
	   $scope.soiMainObj.permanantAddress = $scope.soiMainObj.permanantAddress.replace(/\n/g, " ");
	   $scope.soiMainObj.landmark = $scope.soiMainObj.landmark.replace(/\n/g, " ");
	   $scope.soiMainObj.lastResidenceAddress= $scope.soiMainObj.lastResidenceAddress.replace(/\n/g, " ");
	   if($scope.soiMainObj.spouseDetails != undefined)
		   $scope.soiMainObj.spouseDetails = $scope.soiMainObj.spouseDetails.replace(/\n/g, " ");
	   
	   $scope.soiMainObj.cICLSocialInvestigationReportFamilyDetailsModel = finalFdArr;
	   $scope.printFitData = $scope.soiMainObj;
	   
	   
	   $scope.soiMainObj.childPhoto = ($scope.childImage==""?null:$scope.childImage);
	   $(".loader").css("display", "block");
	   $http.post('saveCICLSocialIvestigationreport',$scope.soiMainObj).
   		then(function(result){
   			console.log(result);
   			$(".loader").css("display", "none");
   			checkSessionOut(result.data);
   			$( "#childCiclSocialInvestigationModal" ).modal("show");
   			//these bunch of code is for resetting radio buttons//-------------------------
   			
   			if($scope.soiMainObj.childDifferentlyAbled === 0)
   				$scope.soiMainObj.childDifferentlyAbled = false;
   			else
   				$scope.soiMainObj.childDifferentlyAbled = true;
   			
   			if($scope.soiMainObj.childMarried === 0)
   				$scope.soiMainObj.childMarried = false;
   			else
   				$scope.soiMainObj.childMarried = true;
   			
   			if($scope.soiMainObj.hoiFatherAr === 0)
   				$scope.soiMainObj.hoiFatherAr = false;
   			else
   				$scope.soiMainObj.hoiFatherAr = true;
   			
   			if($scope.soiMainObj.hoiStepFatherAr === 0)
   				$scope.soiMainObj.hoiStepFatherAr = false;
   			else
   				$scope.soiMainObj.hoiStepFatherAr = true;
   			
   			if($scope.soiMainObj.hoiMotherAr === 0)
   				$scope.soiMainObj.hoiMotherAr = false;
   			else
   				$scope.soiMainObj.hoiMotherAr = true;
   			
   			if($scope.soiMainObj.hoiStepMotherAr === 0)
   				$scope.soiMainObj.hoiStepMotherAr = false;
   			else
   				$scope.soiMainObj.hoiStepMotherAr = true;
   			
   			if($scope.soiMainObj.hoiBrotherAr === 0)
   				$scope.soiMainObj.hoiBrotherAr = false;
   			else
   				$scope.soiMainObj.hoiBrotherAr = true;
   			
   			if($scope.soiMainObj.hoiSisterAr === 0)
   				$scope.soiMainObj.hoiSisterAr = false;
   			else
   				$scope.soiMainObj.hoiSisterAr = true;
   			
   			if($scope.soiMainObj.hoiOthersAr === 0)
   				$scope.soiMainObj.hoiOthersAr = false;
   			else
   				$scope.soiMainObj.hoiOthersAr = true;
   			
   			if($scope.soiMainObj.childVictim === 0)
   				$scope.soiMainObj.childVictim = false;
   			else
   				$scope.soiMainObj.childVictim = true;
   			
   			if($scope.soiMainObj.usedByAnyGang === 0)
   				$scope.soiMainObj.usedByAnyGang = false;
   			else
   				$scope.soiMainObj.usedByAnyGang = true;
   			
   			if($scope.soiMainObj.childSubjectedOfAbuse === 0)
   				$scope.soiMainObj.childSubjectedOfAbuse = false;
   			else
   				$scope.soiMainObj.childSubjectedOfAbuse = true;

			if($scope.soiMainObj.apprehendedForOffence === 0)
				$scope.soiMainObj.apprehendedForOffence = false;
   			else
   				$scope.soiMainObj.apprehendedForOffence = true;	
   			//end//-------------------------------------------------------------------------
   			$scope.ifSubmittedDisable = true;
   			$scope.showUpdateBtn = true;
   			$scope.showSubmitBtn = false;
   		},function(error){
   			console.log(error);
   		});
   };
   
   $scope.downloadImg = function(img){
	   if(img!=null){
		   var date = new Date();
		   var ext = "";
		   if(img.includes("application/pdf")){
			   ext = ".pdf";
			   mimeType = "application/pdf";
		   }else{
			   ext = ".jpeg";
			   mimeType = "image/jpeg";
		   }
		   download(img, "Medical Examination Report "
						 + date.getFullYear() + (date.getMonth() + 1)
						 + date.getHours() + date.getMinutes()
						 + date.getSeconds() + ext, mimeType);
	   }else{
		   $('#filenotfound').modal('show');
	   }
	   
   };
   /*_______________________________________________Photo Upload -----------------------------*/
   
   $scope.childImage ="";
    $scope.clearImageField = function(type){
    	switch(type){
		case 'childImage':
			$timeout(function() {
				$scope.childImage ="";
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
	 			    }, 100);
	 				break;
	 		}
	 	};
	 	reader.onerror = function (error) {
//	 	 console.log('Error: ', error);
	 	};
	};
	$scope.getReport = function ($files,type) {
		var validFormats = ["jpg","JPG","jpeg","JPEG","png","PNG","pdf"];
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
	
	 /*_______________________________________________Photo Upload end-----------------------------*/
   
	
		//====================================download pdf============================
	  	$scope.printCiclSocialInvestigationSubmitData = function(){
	  		 $http.get('getCICLSocialIvestigationreport?childId='+$scope.selectedChildId).
		  	   then(function(result){
		  		    $scope.newSub = result.data;
		  		    $scope.printFitData = $scope.newSub;
		  		   
			  		var serverURL = $scope.langType == 0 ? 'downloadPDFDataForCICLSIR?type=en' : 'downloadPDFDataForCICLSIR?type=hi_IN';
			  		
//			  		$scope.printFitData.caseNo = $scope.selectedChild.caseNum;
//			  		$scope.printFitData.cwcName = $scope.selectedChild.cwc.name;
			  		$scope.printFitData.childId = $scope.selectedChildId;
			  		$scope.printFitData.badNameString=(null==$scope.nameString.badNameString?"":$scope.nameString.badNameString);
			  		$scope.printFitData.differentlyAbledTypeString=(null==$scope.nameString.differentlyAbledTypeString?"":$scope.nameString.differentlyAbledTypeString);
			  		$scope.printFitData.goodNameString=(null==$scope.nameString.goodNameString?"":$scope.nameString.goodNameString);
			  		$scope.printFitData.maorityNameString=(null==$scope.nameString.maorityNameString?"":$scope.nameString.maorityNameString);
			  		$scope.printFitData.physicalAbusedNameString=(null==$scope.nameString.physicalAbusedNameString?"":$scope.nameString.physicalAbusedNameString);
			  		$scope.printFitData.reasonForAllegedOffenceNameString=(null==$scope.nameString.reasonForAllegedOffenceNameString?"":$scope.nameString.reasonForAllegedOffenceNameString);
			  		$scope.printFitData.reasonNameString=(null==$scope.nameString.reasonNameString?"":$scope.nameString.reasonNameString);
			  		$scope.printFitData.sexualAbusedNameString=(null==$scope.nameString.sexualAbusedNameString?"":$scope.nameString.sexualAbusedNameString);
			  		$scope.printFitData.verbalAbusedNameString=(null==$scope.nameString.verbalAbusedNameString?"":$scope.nameString.verbalAbusedNameString);
			  		
			  		var badNameArr = $scope.printFitData.badNameString.split(',  ');
			  		var newBadNameString = "";
			  		for(var i = 0; i < badNameArr.length; i++){
			  			if(badNameArr[i] == 'Drug use'){
			  				badNameArr[i] = badNameArr[i]+=" ("+$scope.printFitData.drugType+")";
			  			}
			  			newBadNameString += badNameArr[i]+",  ";
			  		}
			  		newBadNameString = newBadNameString.substring(0, newBadNameString.length-3);
			  		$scope.printFitData.badNameString = newBadNameString;
			  		if($scope.newSub.differentlyAbledType != null && $scope.differentlyAbledTypeString.trim() != ""){
						var differentlyAbled = $scope.newSub.differentlyAbledType;
						$scope.differentlyAbledTypeString = "";
						$scope.keyString = $scope.newSub.differentlyAbledType;
						$scope.pfDifferentlyAbledArr = differentlyAbled.split(',');
						for(var i=0; i < $scope.pfDifferentlyAbledArr.length; i++){
							for(var j=0; j < $scope.differentlyAbledType.length; j++){
								if(Number($scope.pfDifferentlyAbledArr[i]) == $scope.differentlyAbledType[j].id){
									$scope.differentlyAbledType[j].checked = true;
									if($scope.differentlyAbledType[j].id==45)
										$scope.differentlyAbledTypeString += $scope.langType == 0 ? $scope.differentlyAbledType[j].name+"("+$scope.newSub.mentalIllSeverity+") ,  " :
																								$scope.differentlyAbledType[j].typeNameHindi+"("+$scope.newSub.mentalIllSeverity+") ,  ";
									else if($scope.differentlyAbledType[j].id==384)
										$scope.differentlyAbledTypeString += $scope.langType == 0 ? $scope.differentlyAbledType[j].name+"("+$scope.newSub.mentalRetireSeverity+") ,  " :
																								$scope.differentlyAbledType[j].typeNameHindi+"("+$scope.newSub.mentalRetireSeverity+") ,  ";
									else
										$scope.differentlyAbledTypeString += $scope.langType == 0 ? $scope.differentlyAbledType[j].name+",  " : $scope.differentlyAbledType[j].typeNameHindi+",  ";
								}
							}
						}
						$scope.differentlyAbledTypeString = $scope.differentlyAbledTypeString.substring(0, $scope.differentlyAbledTypeString.length-3);
						$scope.printFitData.differentlyAbledTypeString = $scope.differentlyAbledTypeString;
					}
			  		
			  		commonService.downloadFile(serverURL, $scope.printFitData);
		  		   
		  		   
		  	   },function(error){
	  			console.log(error);
	  		});
	      };
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
        
        $('.parent input[type="checkbox"]:first-child').change(function(){
            $(this).closest('.parent').find('.children').toggle($(this).is(':checked'));
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
        directive('onlyTwentyDigits', function () {
        	
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
        					if(clean.length>20 ){
        						num =clean.slice(0,20);
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