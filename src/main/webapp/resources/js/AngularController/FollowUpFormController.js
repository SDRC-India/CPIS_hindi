/**
 * @Author : Biswabhusan Pradhan
 */

myAppConstructor
		.controller(
				'FollowUpFormController',
				function($scope, $http, $filter, commonService, $window,
						$timeout, gettextCatalog, $rootScope) {
					$scope.childPhoto = "resources/img/photo.jpg";
					$scope.defaultImage = "resources/img/photo.jpg";
					$scope.selectedChildId = $('#modelValue').val();
					$scope.userDesignation = $('#designationBox').val();
					
					$scope.lang = 'en';
					$scope.nameString = {};
					
					commonService.getGridMenuItems()
					.then(function (result){
						$scope.getGridMenuItemsSuccessful = true;
						$scope.checkAllDataSuccessful();
						$scope.menuList=result;
					});
					
					$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
					then(function(result){
//						$scope.getChildByIdSuccessful = true;
//						$scope.checkAllDataSuccessful();
						$scope.selectedChild = result.data;
						$rootScope.$broadcast("selectedChild", $scope.selectedChild);
						if($scope.selectedChild.programType==0){
							$scope.isCNCPChild=true;
						}
						if($scope.selectedChild.finalOrderFilled!=1){
							$scope.isNewForm = false;
							$scope.errorMessage = "Final order for this child has not been passed yet.";
							$('#errorModal').modal('show');
						}
//						$scope.cwcLogin
					},function(error){
						console.log(error);
					});
					$scope.reDirectToHome = function(){
						$window.location.href = '/CPIS/ccts';
					};
					
					$http({
						method : "GET",
						url : "getLang"
					}).then(function mySucces(response) {
						$scope.lang = response.data;
						$scope.changeLanguage($scope.lang);
						if ($scope.lang == 'en')
							$scope.langType = 0;
						else
							$scope.langType = 1;
					});
					
					$http.post("getNotificationCount").then(
							function(response) {
								$scope.notificationCount=response.data;
							},
							function(error){
								console.log(error);
							});
					$scope.formInfo = {};
					$scope.formInfo.childId = $scope.selectedChildId;

					$scope.changeLanguage = function(lang) {

						console.log(lang);
						gettextCatalog.setCurrentLanguage(lang);
						$scope.lang = lang;
						$http({
							method : "GET",
							url : "setLangString?language=" + $scope.lang
						}).then(function mySucces(response) {

						});
					};

					$http.get("getTypeDetails").then(function(result) {
						$scope.getTypeDetailsSuccessful = true;
						$scope.typeList = result.data;
						$rootScope.$broadcast("typeDetails", result);
					}, function(error) {
						console.log(error);
					});
					
					$scope.getFollowUpForms = function(){
						$http.get("getFollowUpForm?selectedChildId="+$scope.selectedChildId).then(function(result){
							$scope.formList = result.data;
							console.log($scope.formList);
							if($scope.userDesignation == 6){
								$scope.editAccess = true;
								$scope.isNewForm = true;
							}
							else{
								$scope.editAccess = false;
								$scope.isNewForm = false;
								if($scope.formList.length > 0)
								$scope.showForm($scope.formList[0]);
							}
						});
					};
					
					$scope.showForm = function(form){
						$scope.formInfo = form;
						$scope.printData = $scope.formInfo;
						$scope.isNewForm = false;
						$scope.complianceOthersChecked=false;
						if($scope.formInfo.availableItems != null){
							var pfAvailableItems = $scope.formInfo.availableItems;
							$scope.availableItemsString = "";
							$scope.keyAvailableItemsString = $scope.formInfo.availableItems;
							$scope.pfAvailableItemsArr = pfAvailableItems.split(',');
							for(var i=0; i < $scope.pfAvailableItemsArr.length; i++){
								for(var j=0; j < $scope.typeList.itemsAvailable.length; j++){
									if(Number($scope.pfAvailableItemsArr[i]) == $scope.typeList.itemsAvailable[j].id){
										$scope.typeList.itemsAvailable[j].checked = true;
										$scope.availableItemsString+= $scope.lang == 'en' ? $scope.typeList.itemsAvailable[j].name+",  " : $scope.typeList.itemsAvailable[j].typeNameHindi+",  ";
									}
								}
							}
							$scope.availableItemsString = $scope.availableItemsString.substring(0, $scope.availableItemsString.length-3);
							$scope.nameString.itemsAvailableNameString = $scope.availableItemsString;
							$scope.printData.availableItems=$scope.nameString.itemsAvailableNameString;
						}
						
						if($scope.formInfo.complianceByGovt != null){
							var pfComplianceByGovt = $scope.formInfo.complianceByGovt;
							$scope.complianceByGovtString = "";
							$scope.keyComplianceByGovtString = $scope.formInfo.complianceByGovt;
							$scope.pfComplianceByGovtArr = pfComplianceByGovt.split(',');
							for(var i=0; i < $scope.pfComplianceByGovtArr.length; i++){
								for(var j=0; j < $scope.typeList.complianceByGovt.length; j++){
									if(Number($scope.pfComplianceByGovtArr[i]) == $scope.typeList.complianceByGovt[j].id){
										if($scope.typeList.complianceByGovt[j].name=="Others")
											$scope.complianceOthersChecked=true;
										$scope.typeList.complianceByGovt[j].checked = true;
										$scope.complianceByGovtString+= $scope.lang == 'en' ? $scope.typeList.complianceByGovt[j].name+",  " : $scope.typeList.complianceByGovt[j].typeNameHindi+",  ";
									}
								}
							}
							$scope.availableItemsString = $scope.complianceByGovtString.substring(0, $scope.complianceByGovtString.length-3);
							$scope.nameString.complianceByGovtNameString = $scope.complianceByGovtString;
							$scope.printData.complianceByGovt=$scope.nameString.complianceByGovtNameString;
						}
						
						if($scope.formInfo.childsBehaviour != null){
							var pfChildsBehaviour = $scope.formInfo.childsBehaviour;
							$scope.childsBehaviourString = "";
							$scope.keyChildsBehaviourString = $scope.formInfo.childsBehaviour;
							$scope.pfChildsBehaviourArr = pfChildsBehaviour.split(',');
							for(var i=0; i < $scope.pfChildsBehaviourArr.length; i++){
								for(var j=0; j < $scope.typeList.childsBehaviour.length; j++){
									if(Number($scope.pfChildsBehaviourArr[i]) == $scope.typeList.childsBehaviour[j].id){
										$scope.typeList.childsBehaviour[j].checked = true;
										$scope.childsBehaviourString+= $scope.lang == 'en' ? $scope.typeList.childsBehaviour[j].name+",  " : $scope.typeList.childsBehaviour[j].typeNameHindi+",  ";
									}
								}
							}
							$scope.availableItemsString = $scope.childsBehaviourString.substring(0, $scope.childsBehaviourString.length-3);
							$scope.nameString.childsBehaviourNameString = $scope.childsBehaviourString;
							$scope.printData.childsBehaviour=$scope.nameString.childsBehaviourNameString;
						}
						
						if($scope.formInfo.problemShareTime != null){
							var pfProblemShareTime = $scope.formInfo.problemShareTime;
							$scope.problemShareTimeString = "";
							$scope.keypfProblemShareTimeString = $scope.formInfo.problemShareTime;
							$scope.pfpfProblemShareTimeArr = pfProblemShareTime.split(',');
							for(var i=0; i < $scope.pfpfProblemShareTimeArr.length; i++){
								for(var j=0; j < $scope.typeList.problemShareTime.length; j++){
									if(Number($scope.pfpfProblemShareTimeArr[i]) == $scope.typeList.problemShareTime[j].id){
										$scope.typeList.problemShareTime[j].checked = true;
										$scope.problemShareTimeString+= $scope.lang == 'en' ? $scope.typeList.problemShareTime[j].name+",  " : $scope.typeList.problemShareTime[j].typeNameHindi+",  ";
									}
								}
							}
							$scope.availableItemsString = $scope.childsBehaviourString.substring(0, $scope.typeList.problemShareTime.length-3);
							$scope.nameString.problemShareTimeNameString = $scope.problemShareTimeString;
							$scope.printData.problemShareTime=$scope.nameString.problemShareTimeNameString;
						}
						
					};
					
					$scope.progressPass = false;
					
					
					
					$scope.addNewForm = function(){
						$scope.formInfo={};
						$scope.formInfo.childId = $scope.selectedChildId;
						$scope.isNewForm = true;
					};
					
					$scope.getFollowUpForms();

					$("#dateofbirth").datepicker({
						dateFormat : "yy-mm-dd",
						maxDate : '+0d',
						changeYear : true,
						onSelect : function(date) {
							$scope.formInfo.dateOfBirth = date;
						}
					});
					$("#datepicker").datepicker({
						dateFormat : "yy-mm-dd",
						maxDate : '+0d',
						changeYear : true,
						onSelect : function(date) {
							$scope.formInfo.dateOfVisit = date;
						}
					});
					$("#dateOfRestoration").datepicker({
						dateFormat : "yy-mm-dd",
						maxDate : '+0d',
						changeYear : true,
						onSelect : function(date) {
							$scope.formInfo.dateOfRestoration = date;
						}
					});
					$("#dateOfAdmission").datepicker({
						dateFormat : "yy-mm-dd",
						maxDate : '+0d',
						changeYear : true,
						onSelect : function(date) {
							$scope.formInfo.dateOfAdmission = date;
						}
					});
					
				
					// calculating BMI
					
					$scope.calculateBMI = function(modelH,modelW){
						$scope.childHeightInM = modelH/100; // converting height into meters
						$scope.childWeightForBMI = modelW;
						$scope.formInfo.bodyMassIndex = ($scope.childWeightForBMI/($scope.childHeightInM * $scope.childHeightInM)).toFixed(1);
					}
					
					// resetting error messages 
					$scope.resetErrOfAvailability = function(){
						if($scope.formInfo.availabilityOfChild != undefined){
							document.getElementById('availabiltyError').innerHTML = "";
						}
						if($scope.formInfo.availabilityOfChild ==  false){
							if($scope.formInfo.childGoneWhere != undefined){
								document.getElementById('availabiltyIfNoError').innerHTML = "";
							}
						}
					   if($scope.formInfo.availabilityOfChild ==  true){
							$scope.formInfo.childGoneWhere = undefined;
							$scope.formInfo.childGoneWhere = "";
						}
						 if($scope.formInfo.restorationType != undefined){
							document.getElementById('typeofRestoreError').innerHTML = "";
						}
						 if($scope.formInfo.fatherName != undefined){
							document.getElementById('nameFatherError').innerHTML = "";
						}
						 if($scope.formInfo.motherName != undefined){
							document.getElementById('nameMotherError').innerHTML = "";
						}
						 if($scope.formInfo.parentTemporaryAddress != undefined){
							document.getElementById('addressError').innerHTML = "";
						}
						if($scope.formInfo.parentPermanentAddress != undefined){
							 document.getElementById('permanentaddressError').innerHTML = "";
						 }
						if($scope.formInfo.parentMobileNo.length == 10){
							 document.getElementById('phoneNoError2').innerHTML = "";
						}
						 if($scope.formInfo.childLook != undefined){
							 document.getElementById('childLookError').innerHTML = "";
						 }
						 if($scope.formInfo.childEnrolled != undefined){
							 document.getElementById('childEnrolledError').innerHTML = "";
						 }
						 if($scope.formInfo.childEnrolled == false){
							 $scope.formInfo.dateOfAdmission = undefined;
							 $scope.formInfo.classOfStudy = undefined;
							 $scope.formInfo.classOfStudy = "";
							 $scope.formInfo.rollNo = undefined;
							 $scope.formInfo.rollNo = "";
							 $scope.formInfo.schoolAddress = undefined;
							 $scope.formInfo.schoolAddress = "";
							 $scope.formInfo.schoolType = undefined;
							 $scope.formInfo.schoolType = "";
							 $scope.formInfo.childPerformance = undefined;
							 $scope.formInfo.childPerformance = "";
							 $scope.formInfo.teacherName = undefined;
							 $scope.formInfo.teacherName = "";
							 $scope.formInfo.remarksOfTeacher = undefined;
							 $scope.formInfo.remarksOfTeacher = "";
							 $scope.formInfo.remarks = undefined;
							 $scope.formInfo.remarks = "";
							 $scope.typeList.itemsAvailable[0].checked = false;
							 $scope.typeList.itemsAvailable[1].checked = false;
							 $scope.typeList.itemsAvailable[2].checked = false;
							 $scope.typeList.itemsAvailable[3].checked = false;
							 $scope.typeList.itemsAvailable[4].checked = false;
						 }
						 
						 if($scope.formInfo.classOfStudy != undefined){
							 document.getElementById('classError').innerHTML = "";
						}
						 if($scope.formInfo.classOfStudy > 12)
						 {
							 $scope.formInfo.classOfStudy = undefined;
							 $scope.formInfo.classOfStudy = "";
							 document.getElementById('classError').innerHTML = "Not a valid class. Please enter class less than or eqaul to 12";
						 }
				
						 if($scope.formInfo.rollNo != undefined){
							 document.getElementById('rollNumrror').innerHTML = "";
						}
						 if($scope.formInfo.schoolAddress != undefined){
							 document.getElementById('nameSchoolError').innerHTML = "";
						}
						 if($scope.formInfo.schoolType != undefined){
							 document.getElementById('typeOfSchoolError').innerHTML = "";
						}
						 if($scope.formInfo.childPerformance != undefined){
							 document.getElementById('performanceError').innerHTML = "";
						}
						if($scope.formInfo.teacherName != undefined){
							 document.getElementById('namePrinciplError').innerHTML = "";
						}
						 if($scope.formInfo.healthCardProvided != undefined){
							 document.getElementById('healthcardError').innerHTML = "";
						 }
						 if($scope.formInfo.healthCardProvided == true){
							 $scope.formInfo.healthCardProvidedReason = undefined;
							 $scope.formInfo.healthCardProvidedReason = "";
						 }
						 if($scope.formInfo.healthCardProvided == false){
							 if($scope.formInfo.healthCardProvidedReason != undefined){
								 document.getElementById('specifyReasonError').innerHTML = "";
							 }
							}
						 if($scope.formInfo.routineCheckUp != undefined){
							 document.getElementById('routineCheckError').innerHTML = "";
						 }
						 if($scope.formInfo.routineCheckUp == true){
							 $scope.formInfo.routineCheckupReason = undefined;
							 $scope.formInfo.routineCheckupReason = "";
						 }
						 if($scope.formInfo.routineCheckUp == false){
							 if($scope.formInfo.routineCheckupReason != undefined){
								 document.getElementById('specifyRoutineCheckError').innerHTML = "";
							 }
							}
						 if($scope.formInfo.illness != undefined){
							 document.getElementById('lastVisitError').innerHTML = "";
						 }
						 if($scope.formInfo.illness == false){
							 $scope.formInfo.illnessStatusReason = undefined;
							 $scope.formInfo.illnessStatusReason = "";
						 }
						if($scope.formInfo.illness == true){
							 if($scope.formInfo.illnessStatusReason != undefined){
								 document.getElementById('specifylastVisitError').innerHTML = "";
							 }
							 }
						 if($scope.formInfo.hospitalized != undefined){
							 document.getElementById('hospitalizedError').innerHTML = "";
						 }
						 if($scope.formInfo.hospitalized == false){
							 $scope.formInfo.isHospitalizedReason = undefined;
							 $scope.formInfo.isHospitalizedReason = "";
						 }
						 if($scope.formInfo.hospitalized ==  true){
							 if($scope.formInfo.isHospitalizedReason != undefined){
								 document.getElementById('specifyhospitalizedError').innerHTML = "";
							 }
							}
						 if($scope.formInfo.intellectiveStatus != undefined){
							 document.getElementById('intellectivestatusError').innerHTML = "";
						 }
						 if($scope.formInfo.skillDeveloped != undefined){
							 document.getElementById('skillDevelopError').innerHTML = "";
						 }
						 if($scope.formInfo.skillDeveloped == false){
							 $scope.formInfo.courseName = undefined;
							 $scope.formInfo.courseName = "";
							 $scope.formInfo.instituteName = undefined;
							 $scope.formInfo.instituteName = "";
							 $scope.formInfo.courseStatus = undefined;
							 $scope.formInfo.vocationalProgress = undefined;
							 $scope.progressPass = false;
							 
						 }
						 if($scope.formInfo.skillDeveloped == true){
							 if($scope.formInfo.courseName != undefined){
								 document.getElementById('vocationalCourseError').innerHTML = "";
							 }
						}
						 if($scope.formInfo.courseName != undefined){
							 document.getElementById('vocationalCourseError').innerHTML = "";
						 }
						 if($scope.formInfo.courseName != undefined){
							 document.getElementById('institutionError').innerHTML = "";
						 }
						 if($scope.formInfo.courseStatus != undefined){
							 document.getElementById('statusourseError').innerHTML = "";
						 }
						  if($scope.formInfo.vocationalProgress != undefined){
							 document.getElementById('progressError').innerHTML = "";
						 }
						  if($scope.formInfo.vocationalProgressStatus != undefined){
							 document.getElementById('specifyPassedError').innerHTML = "";
						 }
						  if($scope.formInfo.parentsBehaviour != undefined){
							 document.getElementById('behaviorparentsError').innerHTML = "";
						 }
						  if($scope.formInfo.sexuallyAbused != undefined){
							 document.getElementById('sexuallyAssultedError').innerHTML = "";
						 }
						  if($scope.formInfo.beatenByParents != undefined){
							 document.getElementById('beatenChildError').innerHTML = "";
						 }
						  if($scope.formInfo.beatenByParents == false){
							  $scope.formInfo.beatenByParentsFrequency = undefined;
							  $scope.formInfo.beatenByParentsFrequency = "";
						  }
						if($scope.formInfo.beatenByParents == true){
							 if($scope.formInfo.beatenByParentsFrequency != undefined){
							 document.getElementById('specifybeatenChildError').innerHTML = "";
						 }
						 }
						  if($scope.formInfo.childDoHouseholdChores != undefined){
							 document.getElementById('householdChoresError').innerHTML = "";
						 }
						  if($scope.formInfo.childDoHouseholdChores == false){
							  $scope.formInfo.typeOfWork = undefined;
							  $scope.formInfo.typeOfWork = "";
						  }
						 if($scope.formInfo.childDoHouseholdChores == true){
							 if($scope.formInfo.typeOfWork != undefined){
							 document.getElementById('specifyhouseholdChoresError').innerHTML = "";
						 }
						 }
						
						 if($scope.formInfo.timeSpentWithParents != undefined){
							 document.getElementById('timeSpendError').innerHTML = "";
						 }
						 if($scope.formInfo.behaviourOfNeighbour != undefined){
							 document.getElementById('behaviourNeighbourError').innerHTML = "";
						 }
						if($scope.formInfo.childShareProblems != undefined){
							 document.getElementById('sharingProblemError').innerHTML = "";
						 }
						if($scope.formInfo.childShareProblems == false){
							$scope.typeList.problemShareTime[0].checked = false;
							$scope.typeList.problemShareTime[1].checked = false;
							$scope.typeList.problemShareTime[2].checked = false;
							$scope.typeList.problemShareTime[3].checked = false;
							$scope.typeList.problemShareTime[4].checked = false;
							$scope.typeList.problemShareTime[5].checked = false;
							
						}
						  if($scope.formInfo.headingForFacilitation == true){
								if($scope.formInfo.schemeName == undefined){
							 document.getElementById('nameOfSchemeError').innerHTML = "";
						 }
						 }
						  if($scope.formInfo.headingForFacilitation == false){
							  $scope.formInfo.schemeName = undefined;
							  $scope.formInfo.schemeName = "";
						  }
						  if($scope.formInfo.typeOfFacilitation != undefined){
							 document.getElementById('typeOffacilitationError').innerHTML = "";
						 }
					}
				
					$scope.validateName = function(){
						if($scope.formInfo.parentMobileNo.length < 10){
							 document.getElementById('phoneNoError2').innerHTML = "Please enter 10 digit of mobile number.";
						}
					}
					
				
					$scope.validateForm = function() {
						if($scope.formInfo.fatherAdhaarCardNo==null && $scope.formInfo.motherAdhaarCardNo==null){
							  document.getElementById('adharcardError4').innerHTML = "At lest one adhaar card number is required.";
							  $('html,body').animate({
							        scrollTop: $('#adharcardError4').offset().top - 250},'slow');
							 return false;
						  }
					if($scope.formInfo.availabilityOfChild == undefined){
						$('#availabiltyError').html("Please select one option");
						$('html,body').animate({
						        scrollTop: $('#availabiltyError').offset().top - 250},'slow');
						 return false;
					}else{
						document.getElementById('availabiltyError').innerHTML = "";
					}
					 
				if($scope.formInfo.availabilityOfChild ==  false){
					if($scope.formInfo.childGoneWhere == undefined || $scope.formInfo.childGoneWhere == ""){
						$('#availabiltyIfNoError').html("Please specify where he is gone ");
						$('html,body').animate({
						        scrollTop: $('#availabiltyIfNoError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('availabiltyIfNoError').innerHTML = "";
					}
					}
					
					 if($scope.formInfo.restorationType == undefined){
						$('#typeofRestoreError').html("Please select type of restoration ");
						$('html,body').animate({
						        scrollTop: $('#typeofRestoreError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('typeofRestoreError').innerHTML = "";
					}
					 
					 if($scope.selectedChild.childName == undefined || $scope.selectedChild.childName == "")
						 {
						 $('#nameChildError').html("Please enter name of thee child");
							$('html,body').animate({
							        scrollTop: $('#nameChildError').offset().top - 250},'slow');
							 return false;
						 }
					 else{
						 document.getElementById('nameChildError').innerHTML = "";
					 }
					
					 if($scope.selectedChild.age == undefined){
						 $('#ageChildError').html("Please select age pf child");
						$('html,body').animate({
						        scrollTop: $('#ageChildError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('ageChildError').innerHTML = "";
					}
					 
					 if($scope.selectedChild.childSex == undefined){
						 $('#genderChildError').html("Please select age pf child");
						$('html,body').animate({
						        scrollTop: $('#genderChildError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('genderChildError').innerHTML = "";
					}
					 
					 
					 if($scope.selectedChild.adhaarNo == undefined || $scope.selectedChild.adhaarNo == ""){
						 $('#adharcardError1').html("Please enter aadhaar number");
						$('html,body').animate({
						        scrollTop: $('#adharcardError1').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('adharcardError1').innerHTML = "";
					}
					 
					 if($scope.formInfo.fatherName == undefined || $scope.formInfo.fatherName == ""){
						 $('#nameFatherError').html("Please enter father/guardian name");
						$('html,body').animate({
						        scrollTop: $('#nameFatherError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('nameFatherError').innerHTML = "";
					}
					 
					 if($scope.formInfo.motherName == undefined || $scope.formInfo.motherName == ""){
						 $('#nameMotherError').html("Please enter mother/guardian name");
						$('html,body').animate({
						        scrollTop: $('#nameMotherError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('nameMotherError').innerHTML = "";
					}
					 
					 if($scope.formInfo.parentTemporaryAddress == undefined || $scope.formInfo.parentTemporaryAddress == ""){
						 $('#addressError').html("Please enter temporary address");
						$('html,body').animate({
						        scrollTop: $('#addressError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('addressError').innerHTML = "";
					}
					 
					 if($scope.formInfo.parentPermanentAddress == undefined || $scope.formInfo.parentPermanentAddress == ""){
						 $('#permanentaddressError').html("Please enter permanent address");
						$('html,body').animate({
						        scrollTop: $('#permanentaddressError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('permanentaddressError').innerHTML = "";
					}
					 
					 /*if($scope.formInfo.fatherAdhaarCardNo == undefined || $scope.formInfo.fatherAdhaarCardNo == ""){
						 $('#adharcardError2').html("Please enter father's aadhaar number");
						$('html,body').animate({
						        scrollTop: $('#adharcardError2').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('adharcardError2').innerHTML = "";
					}
					 
					 if($scope.formInfo.motherAdhaarCardNo == undefined || $scope.formInfo.motherAdhaarCardNo == ""){
						 $('#adharcardError3').html("Please enter mother's aadhaar number");
						$('html,body').animate({
						        scrollTop: $('#adharcardError3').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('adharcardError3').innerHTML = "";
					}*/
					 
					 if($scope.formInfo.childLook == undefined){
						 $('#childLookError').html("How does the child look ?");
						$('html,body').animate({
						        scrollTop: $('#childLookError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('childLookError').innerHTML = "";
					}
					 
					 
					// Educational status of child error checking
					 
					 
					 if($scope.formInfo.childEnrolled == undefined){
						 $('#childEnrolledError').html("Child is enrolled or not ?");
						$('html,body').animate({
						        scrollTop: $('#childEnrolledError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('childEnrolledError').innerHTML = "";
					}
					 
					 
					 
					 
				if($scope.formInfo.childEnrolled == true){
					 if($scope.formInfo.childEnrolled == undefined){
						 $('#childEnrolledError').html("Child is enrolled in school or not ?");
						$('html,body').animate({
						        scrollTop: $('#childEnrolledError').offset().top - 250},'slow');
						 return false;
					}
					 else if($scope.formInfo.classOfStudy == undefined || $scope.formInfo.classOfStudy == ""){
						 $('#classError').html("Please enter class");
							$('html,body').animate({
							        scrollTop: $('#classError').offset().top - 250},'slow');
							 return false;
						}
					 
					 else if($scope.formInfo.rollNo == undefined || $scope.formInfo.rollNo == ""){
						 $('#rollNumrror').html("Please enter roll number");
						$('html,body').animate({
						        scrollTop: $('#rollNumrror').offset().top - 250},'slow');
						 return false;
					}
					 else if($scope.formInfo.schoolAddress == undefined || $scope.formInfo.schoolAddress == ""){
						 $('#nameSchoolError').html("Please enter school address");
						$('html,body').animate({
						        scrollTop: $('#nameSchoolError').offset().top - 250},'slow');
						 return false;
					}
					 else if($scope.formInfo.schoolType == undefined){
						 $('#typeOfSchoolError').html("Please enter type of school");
						$('html,body').animate({
						        scrollTop: $('#typeOfSchoolError').offset().top - 250},'slow');
						 return false;
					}
					 
					 else if($scope.formInfo.childPerformance == undefined || $scope.formInfo.childPerformance == ""){
						 $('#performanceError').html("Please enter performance of child");
						$('html,body').animate({
						        scrollTop: $('#performanceError').offset().top - 250},'slow');
						 return false;
					}
					 
					 else if($scope.formInfo.teacherName == undefined || $scope.formInfo.teacherName == ""){
						 $('#namePrinciplError').html("Please enter name of teacher/principal");
						$('html,body').animate({
						        scrollTop: $('#namePrinciplError').offset().top - 250},'slow');
						 return false;
					}
					else{
						document.getElementById('childEnrolledError').innerHTML = "";
						document.getElementById('classError').innerHTML = "";
						document.getElementById('rollNumrror').innerHTML = "";
						document.getElementById('nameSchoolError').innerHTML = "";
						document.getElementById('typeOfSchoolError').innerHTML = "";
						document.getElementById('performanceError').innerHTML = "";
						document.getElementById('namePrinciplError').innerHTML = "";
					}
					 }
				
					 var childAvailList = [ $scope.typeList.itemsAvailable];
						var availabiltyError = false;
						
						for(var i=0; i<childAvailList.length; i++){
							for(var j=0; j<childAvailList[i].length; j++){
								if(childAvailList[i][j].checked == true){
									availabiltyError = true;
								}
							}
						}
						
						if($scope.formInfo.childEnrolled == true){
						if(availabiltyError == false){
							$('#availabilityChildError').html("Please select at least one option");
							$('html,body').animate({
							        scrollTop: $('#availabilityChildError').offset().top - 250},'slow');
							 return false;
						}else{
							document.getElementById('availabilityChildError').innerHTML = "";
						}
						}
						
						
						 if($scope.formInfo.healthCardProvided == undefined){
							 $('#healthcardError').html("Health card provided or not ?");
							$('html,body').animate({
							        scrollTop: $('#healthcardError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('healthcardError').innerHTML = "";
						}
						 
						if($scope.formInfo.healthCardProvided == false){
						 if($scope.formInfo.healthCardProvidedReason == undefined){
							 $('#specifyReasonError').html("Specify reason");
							$('html,body').animate({
							        scrollTop: $('#specifyReasonError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('specifyReasonError').innerHTML = "";
						}
						 }
						 
						 
						 if($scope.formInfo.routineCheckUp == undefined){
							 $('#routineCheckError').html("Specify routine checked up or not");
							$('html,body').animate({
							        scrollTop: $('#routineCheckError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('routineCheckError').innerHTML = "";
						}
						 
						 if($scope.formInfo.routineCheckUp == false){
						 if($scope.formInfo.routineCheckupReason == undefined){
							 $('#specifyRoutineCheckError').html("Specify the reason");
							$('html,body').animate({
							        scrollTop: $('#specifyRoutineCheckError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('specifyRoutineCheckError').innerHTML = "";
						}
						 }
						 
						 
						 
						 if($scope.formInfo.illness == undefined){
							 $('#lastVisitError').html("Please mention illeness is there or not");
							$('html,body').animate({
							        scrollTop: $('#lastVisitError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('lastVisitError').innerHTML = "";
						}
						 
						 if($scope.formInfo.illness == true){
						 if($scope.formInfo.illnessStatusReason == undefined){
							 $('#specifylastVisitError').html("Specify the reason");
							$('html,body').animate({
							        scrollTop: $('#specifylastVisitError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('specifylastVisitError').innerHTML = "";
						}
						 }
						 
						 if($scope.formInfo.hospitalized == undefined){
							 $('#hospitalizedError').html("Please mention child is hospitalized or not.");
							$('html,body').animate({
							        scrollTop: $('#hospitalizedError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('hospitalizedError').innerHTML = "";
						}
						 
						 
						if($scope.formInfo.hospitalized ==  true){
						 if($scope.formInfo.isHospitalizedReason == undefined){
							 $('#specifyhospitalizedError').html("Specify the reason");
							$('html,body').animate({
							        scrollTop: $('#specifyhospitalizedError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('specifyhospitalizedError').innerHTML = "";
						}
						}
						 
						 if($scope.formInfo.intellectiveStatus == undefined){
							 $('#intellectivestatusError').html("Please select intellective status of the child.");
							$('html,body').animate({
							        scrollTop: $('#intellectivestatusError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('intellectivestatusError').innerHTML = "";
						}
						 
						 
						 if($scope.formInfo.skillDeveloped == undefined){
							 $('#skillDevelopError').html("Please select skill development of chiild.");
							$('html,body').animate({
							        scrollTop: $('#skillDevelopError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('skillDevelopError').innerHTML = "";
						}
						 
					 if($scope.formInfo.skillDeveloped == true){
						 if($scope.formInfo.courseName == undefined || $scope.formInfo.courseName == ""){
							 $('#vocationalCourseError').html("Please mention vocational course of child.");
							$('html,body').animate({
							        scrollTop: $('#vocationalCourseError').offset().top - 250},'slow');
							 return false;
						}
						 else if($scope.formInfo.instituteName == undefined || $scope.formInfo.courseName == ""){
							 $('#institutionError').html("Please mention the institutional name.");
							$('html,body').animate({
							        scrollTop: $('#institutionError').offset().top - 250},'slow');
							 return false;
						}
						 
						 else if($scope.formInfo.courseStatus == undefined){
							 $('#statusourseError').html("Please mention status of course.");
							$('html,body').animate({
							        scrollTop: $('#statusourseError').offset().top - 250},'slow');
							 return false;
						}
						 
						 else if($scope.formInfo.vocationalProgress == undefined){
							 $('#progressError').html("Please mention the progress of the vocational training.");
							$('html,body').animate({
							        scrollTop: $('#progressError').offset().top - 250},'slow');
							 return false;
						}
						 
						 else if($scope.formInfo.vocationalProgressStatus == undefined){
							 $('#specifyPassedError').html("please specify the status of the employment.");
							$('html,body').animate({
							        scrollTop: $('#specifyPassedError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('vocationalCourseError').innerHTML = "";
							document.getElementById('institutionError').innerHTML = "";
							document.getElementById('statusourseError').innerHTML = "";
							document.getElementById('progressError').innerHTML = "";
							document.getElementById('specifyPassedError').innerHTML = "";
						}
						 }
						 
					
						 if($scope.formInfo.parentsBehaviour == undefined){
							 $('#behaviorparentsError').html("please mention behaviour of the parents with the child.");
							$('html,body').animate({
							        scrollTop: $('#behaviorparentsError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('behaviorparentsError').innerHTML = "";
						}
						 
						 
						 
						 if($scope.formInfo.sexuallyAbused == undefined){
							 $('#sexuallyAssultedError').html("please mention child is sexually assaulted or not.");
							$('html,body').animate({
							        scrollTop: $('#sexuallyAssultedError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('sexuallyAssultedError').innerHTML = "";
						}
						
						 
						 if($scope.formInfo.beatenByParents == undefined){
							 $('#beatenChildError').html("Whether Child beaten up by the parent?");
							$('html,body').animate({
							        scrollTop: $('#beatenChildError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('beatenChildError').innerHTML = "";
						}
						 
						 
						 if($scope.formInfo.beatenByParents == true){
						 if($scope.formInfo.beatenByParentsFrequency == undefined){
							 $('#specifybeatenChildError').html("Please specify how frequently is the child being beaten up.");
							$('html,body').animate({
							        scrollTop: $('#specifybeatenChildError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('specifybeatenChildError').innerHTML = "";
						}
						 } 
						 
						 
						 if($scope.formInfo.childDoHouseholdChores == undefined){
							 $('#householdChoresError').html("Whether Child do household chores?");
							$('html,body').animate({
							        scrollTop: $('#householdChoresError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('householdChoresError').innerHTML = "";
						}
						 
						 if($scope.formInfo.childDoHouseholdChores == true){
						 if($scope.formInfo.typeOfWork == undefined){
							 $('#specifyhouseholdChoresError').html("please specify what type of work");
							$('html,body').animate({
							        scrollTop: $('#specifyhouseholdChoresError').offset().top - 250},'slow');
							 return false;
						}
						else{
							document.getElementById('specifyhouseholdChoresError').innerHTML = "";
						}
						 }
						
							// for behavior error of child
							var childbehavrList = [ $scope.typeList.childsBehaviour];
							var checkError = false;
							
							for(var i=0; i<childbehavrList.length; i++){
								for(var j=0; j<childbehavrList[i].length; j++){
									if(childbehavrList[i][j].checked == true){
										checkError = true;
									}
								}
							}
							if(checkError == false){
								$('#behaviorChildError').html("Please select at least one option");
								$('html,body').animate({
								        scrollTop: $('#behaviorChildError').offset().top - 250},'slow');
								 return false;
							}else{
								document.getElementById('behaviorChildError').innerHTML = "";
							}
							
							// for compliance error of child
							var childComplianceByGovt = [ $scope.typeList.complianceByGovt];
							var noCompliancError = false;
							
							for(var i=0; i<childComplianceByGovt.length; i++){
								for(var j=0; j<childComplianceByGovt[i].length; j++){
									if(childComplianceByGovt[i][j].checked == true){
										noCompliancError = true;
									}
								}
							}
							if(noCompliancError == false){
								$('#behaviorErr').html("Please select at least one option");
								$('html,body').animate({
								        scrollTop: $('#behaviorErr').offset().top - 250},'slow');
								 return false;
							}else{
								document.getElementById('behaviorErr').innerHTML = "";
							}
						
							if($scope.formInfo.timeSpentWithParents == undefined){
								 $('#timeSpendError').html("please specify the time spends by parents with child.");
								$('html,body').animate({
								        scrollTop: $('#timeSpendError').offset().top - 250},'slow');
								 return false;
							}
							else{
								document.getElementById('timeSpendError').innerHTML = "";
							}
							
							
							if($scope.formInfo.behaviourOfNeighbour == undefined){
								 $('#behaviourNeighbourError').html("please specify the behaviour of the neighbour towards the child");
								$('html,body').animate({
								        scrollTop: $('#behaviourNeighbourError').offset().top - 250},'slow');
								 return false;
							}
							else{
								document.getElementById('behaviourNeighbourError').innerHTML = "";
							}
							
							if($scope.formInfo.childShareProblems == undefined){
								 $('#sharingProblemError').html("please select one option.");
								$('html,body').animate({
								        scrollTop: $('#sharingProblemError').offset().top - 250},'slow');
								 return false;
							}
							else{
								document.getElementById('sharingProblemError').innerHTML = "";
							}
							
							
							var shareProblemList = [ $scope.typeList.problemShareTime];
							var shareListError = false;
							
							for(var i=0; i<shareProblemList.length; i++){
								for(var j=0; j<shareProblemList[i].length; j++){
									if(shareProblemList[i][j].checked == true){
										shareListError = true;
									}
								}
							}
							
							if($scope.formInfo.childShareProblems == true){
							if(shareListError == false){
								$('#sharingListsError').html("Please select at least one option");
								$('html,body').animate({
								        scrollTop: $('#sharingListsError').offset().top - 250},'slow');
								 return false;
							}else{
								document.getElementById('sharingListsError').innerHTML = "";
							}
							}
							//end
							
							if($scope.formInfo.headingForFacilitation == undefined){
								 $('#facilitationError').html("please select one option.");
								$('html,body').animate({
								        scrollTop: $('#facilitationError').offset().top - 250},'slow');
								 return false;
							}
							else{
								document.getElementById('facilitationError').innerHTML = "";
							}
							
						if($scope.formInfo.headingForFacilitation == true){
							if($scope.formInfo.schemeName == undefined){
								 $('#nameOfSchemeError').html("please specify the name of the scheme/program.");
								$('html,body').animate({
								        scrollTop: $('#nameOfSchemeError').offset().top - 250},'slow');
								 return false;
							}
							else{
								document.getElementById('nameOfSchemeError').innerHTML = "";
							}
							if($scope.formInfo.typeOfFacilitation == undefined){
								 $('#typeOffacilitationError').html("please specify the name of the scheme/program.");
								$('html,body').animate({
								        scrollTop: $('#typeOffacilitationError').offset().top - 250},'slow');
								 return false;
							}
							else{
								document.getElementById('typeOffacilitationError').innerHTML = "";
							}
						}
							
							
							
					
				
					if ($scope.formInfo.dateOfVisit === undefined) {
							$("#dateOfVisit").datepicker("show");
						} else if ($scope.formInfo.dateOfBirth === undefined) {
							$("#dateofbirth").datepicker("show");
						}else if($scope.formInfo.childEnrolled == true && $scope.formInfo.dateOfAdmission === undefined){
							$("#dateOfAdmission").datepicker("show");
						}
						else {
							$('#submitModal').modal('show');
						}

					};

					// validate adhar card
					$scope.validateAdharCard = function(name, errorId) {
						if (name != undefined) {
							if (name.length != 12 && name != "") {
								if (errorId == 'adharcardError1'
										|| errorId == 'adharcardError2'
										|| errorId == 'adharcardError3') {
									document.getElementById(errorId).innerHTML = "Please enter 12 digit aadhaar number";
									return false;
								}

							} else {
								document.getElementById(errorId).innerHTML = "";
								return true;
							}
						} else {
							if (errorId == 'adharcardError1'
									|| errorId == 'adharcardError2'
									|| errorId == 'adharcardError3') {
								document.getElementById(errorId).innerHTML = "Please enter 12 digit aadhaar number";
								return false;
							}
						}
						document.getElementById('adharcardError4').innerHTML = "";
					};

					$scope.itemsAvailableArr = [];
					$scope.setitemsAvailableIds = function(selectedObjs) {
						$scope.itemsAvailableString = "";
						$scope.itemsAvailableNameString = "";
						$scope.itemsAvailableArr.push(selectedObjs);
						for (var i = 0; i < $scope.itemsAvailableArr.length; i++) {
							if ($scope.itemsAvailableArr[i].checked == false) {
								$scope.itemsAvailableArr.splice(i, 1);
							}
						}
						for (var i = 0; i < $scope.itemsAvailableArr.length; i++) {
							if ($scope.itemsAvailableArr[i].checked == false) {
								$scope.itemsAvailableArr.splice(i, 1);
							}
						}
						for (var j = 0; j < $scope.itemsAvailableArr.length; j++) {
							$scope.itemsAvailableString += $scope.itemsAvailableArr[j].id
									+ ",";
							if ($scope.langType == 0)
								$scope.itemsAvailableNameString += $scope.itemsAvailableArr[j].name
										+ ",  ";
							else
								$scope.itemsAvailableNameString += $scope.itemsAvailableArr[j].typeNameHindi
										+ ",  ";
						}
						$scope.itemsAvailableString = $scope.itemsAvailableString
								.substring(0,
										$scope.itemsAvailableString.length - 1);
						$scope.formInfo.availableItems = $scope.itemsAvailableString;
						/*
						 * if(!$scope.itemsAvailableString.includes('404')){
						 * $scope.formInfo.otheritemsAvailableLeavingSchool =
						 * null; }
						 */
						$scope.itemsAvailableNameString = $scope.itemsAvailableNameString
								.substring(
										0,
										$scope.itemsAvailableNameString.length - 3);
						$scope.nameString.itemsAvailableNameString = $scope.itemsAvailableNameString;
						console.log("itemsAvailableIds: "
								+ $scope.nameString.itemsAvailableNameString);
						
						if($scope.formInfo.childEnrolled == true){
							if(availabiltyError != false){
							 document.getElementById('availabilityChildError').innerHTML = "";
						}
						}
					};
					
					$scope.childsBehaviourArr = [];
					$scope.setChildsBehaviourIds = function(selectedObjs) {
						$scope.childsBehaviourString = "";
						$scope.childsBehaviourNameString = "";
						$scope.childsBehaviourArr.push(selectedObjs);
						for (var i = 0; i < $scope.childsBehaviourArr.length; i++) {
							if ($scope.childsBehaviourArr[i].checked == false) {
								$scope.childsBehaviourArr.splice(i, 1);
							}
						}
						for (var i = 0; i < $scope.childsBehaviourArr.length; i++) {
							if ($scope.childsBehaviourArr[i].checked == false) {
								$scope.childsBehaviourArr.splice(i, 1);
							}
						}
						for (var j = 0; j < $scope.childsBehaviourArr.length; j++) {
							$scope.childsBehaviourString += $scope.childsBehaviourArr[j].id
									+ ",";
							if ($scope.langType == 0)
								$scope.childsBehaviourNameString += $scope.childsBehaviourArr[j].name
										+ ",  ";
							else
								$scope.childsBehaviourNameString += $scope.childsBehaviourArr[j].typeNameHindi
										+ ",  ";
						}
						$scope.childsBehaviourString = $scope.childsBehaviourString
								.substring(0,
										$scope.childsBehaviourString.length - 1);
						$scope.formInfo.childsBehaviour = $scope.childsBehaviourString;
						/*
						 * if(!$scope.childsBehaviourString.includes('404')){
						 * $scope.formInfo.otherchildsBehaviourLeavingSchool =
						 * null; }
						 */
						$scope.childsBehaviourNameString = $scope.childsBehaviourNameString
								.substring(
										0,
										$scope.childsBehaviourNameString.length - 3);
						$scope.nameString.childsBehaviourNameString = $scope.childsBehaviourNameString;
						console.log("childsBehaviourIds: "
								+ $scope.nameString.childsBehaviourNameString);
						
						
						
					};
					
					$scope.complianceByGovtArr = [];
					$scope.complianceOthersChecked=false;
					$scope.setComplianceByGovtIds = function(selectedObjs) {
						$scope.complianceByGovtString = "";
						$scope.complianceByGovtNameString = "";
						$scope.complianceByGovtArr.push(selectedObjs);
						for (var i = 0; i < $scope.complianceByGovtArr.length; i++) {
							if ($scope.complianceByGovtArr[i].checked == false) {
								$scope.complianceByGovtArr.splice(i, 1);
							}
						}
						for (var i = 0; i < $scope.complianceByGovtArr.length; i++) {
							if ($scope.complianceByGovtArr[i].checked == false) {
								$scope.complianceByGovtArr.splice(i, 1);
							}
						}
						for (var j = 0; j < $scope.complianceByGovtArr.length; j++) {
							$scope.complianceByGovtString += $scope.complianceByGovtArr[j].id
									+ ",";
							if ($scope.langType == 0)
								$scope.complianceByGovtNameString += $scope.complianceByGovtArr[j].name
										+ ",  ";
							else
								$scope.complianceByGovtNameString += $scope.complianceByGovtArr[j].typeNameHindi
										+ ",  ";
						}
						$scope.complianceByGovtString = $scope.complianceByGovtString
								.substring(0,
										$scope.complianceByGovtString.length - 1);
						$scope.formInfo.complianceByGovt = $scope.complianceByGovtString;
						/*
						 * if(!$scope.complianceByGovtString.includes('404')){
						 * $scope.formInfo.othercomplianceByGovtLeavingSchool =
						 * null; }
						 */
						$scope.complianceByGovtNameString = $scope.complianceByGovtNameString
								.substring(
										0,
										$scope.complianceByGovtNameString.length - 3);
						$scope.nameString.complianceByGovtNameString = $scope.complianceByGovtNameString;
						console.log("complianceByGovtIds: "
								+ $scope.nameString.complianceByGovtNameString);
						
						if(selectedObjs.name=='Others'){
							$scope.complianceOthersChecked=!$scope.complianceOthersChecked;
						}
					};
					
					$scope.problemShareTimeArr = [];
					$scope.setProblemShareTimeIds = function(selectedObjs) {
						$scope.problemShareTimeString = "";
						$scope.problemShareTimeNameString = "";
						$scope.problemShareTimeArr.push(selectedObjs);
						for (var i = 0; i < $scope.problemShareTimeArr.length; i++) {
							if ($scope.problemShareTimeArr[i].checked == false) {
								$scope.problemShareTimeArr.splice(i, 1);
							}
						}
						for (var i = 0; i < $scope.problemShareTimeArr.length; i++) {
							if ($scope.problemShareTimeArr[i].checked == false) {
								$scope.problemShareTimeArr.splice(i, 1);
							}
						}
						for (var j = 0; j < $scope.problemShareTimeArr.length; j++) {
							$scope.problemShareTimeString += $scope.problemShareTimeArr[j].id
									+ ",";
							if ($scope.langType == 0)
								$scope.problemShareTimeNameString += $scope.problemShareTimeArr[j].name
										+ ",  ";
							else
								$scope.problemShareTimeNameString += $scope.problemShareTimeArr[j].typeNameHindi
										+ ",  ";
						}
						$scope.problemShareTimeString = $scope.problemShareTimeString
								.substring(0,
										$scope.problemShareTimeString.length - 1);
						$scope.formInfo.problemShareTime = $scope.problemShareTimeString;
						/*
						 * if(!$scope.problemShareTimeString.includes('404')){
						 * $scope.formInfo.othercomplianceByGovtLeavingSchool =
						 * null; }
						 */
						$scope.problemShareTimeNameString = $scope.problemShareTimeNameString
								.substring(
										0,
										$scope.problemShareTimeNameString.length - 3);
						$scope.nameString.problemShareTimeNameString = $scope.problemShareTimeNameString;
						console.log("problemShareTimeIds: "
								+ $scope.nameString.problemShareTimeNameString);
					};
					$scope.clearImageField = function(type){
				    	switch(type){
						case 'childPhoto':
							$timeout(function() {
								$scope.childPhoto = $scope.defaultImage;
						    }, 100);
							break;
				    	}
				    };
					$scope.getBase64=function(file, type) {
					 	var reader = new FileReader();
					 	reader.readAsDataURL(file);
					 	reader.onload = function () {
					 		switch(type){
					 			case 'childPhoto':
					 				$timeout(function() {
					 					$scope.childPhoto=reader.result;
					 			    }, 100);
					 				break;
					 		}
					 	};
					 	reader.onerror = function (error) {
//					 	 console.log('Error: ', error);
					 	};
					};
					
					$scope.getReports = function ($files,type) {
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
					};
					
					$scope.printData=$scope.formInfo;
					$scope.saveData = function() {
					
						/*$scope.formInfo.childName = $scope.selectedChild.childName;
						$scope.formInfo.childAge = $scope.selectedChild.age;
						$scope.formInfo.childSex = $scope.selectedChild.childSex;*/
						$scope.formInfo.dateOfRestoration = $scope.selectedChild.dateOfRestoration;
						$scope.formInfo.adhaarCardNo = $scope.selectedChild.adhaarNo;
						$scope.formInfo.childPhoto=$scope.childPhoto == $scope.defaultImage ? null : $scope.childPhoto;
						
						
						
						
						console.log($scope.formInfo);
//						$scope.formInfo.childPhoto = $scope.childPhoto == $scope.defaultImage ? null : $scope.childPhoto;
						$http.post('saveFollowUpForm', $scope.formInfo).then(
								function() {
									console.log("success");
								});
						$('#printModal').modal('show');
					};
					
					$scope.reDirect =  function()
					{
						$window.location.reload();
					}
					
					$scope.printSubmitData = function(){
//						$scope.printData.childPhotoPath = $scope.formInfo.childPhotoPath;
						$(".loader").css("display", "block");
	            		var serverURL = 'downloadPDFDataFollowUpForm?type='+$scope.lang;
	            		commonService.downloadFile(serverURL, $scope.printData);
	        			$(".loader").css("display", "none");
					};
					
					// for select drop down list's placeholder
					/*$(".select select" ).change(function() {
						$(".select select").css('color','#000');
					});	*/				
					
					
					
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

myAppConstructor.directive('onlyTwelveDigits', function() {

	return {
		restrict : 'A',
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) {
				return;
			}

			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					var val = '';
				}

				var clean = val.replace(/[^0-9]/g, '');
				if (!angular.isUndefined(clean)) {
					var num = 0;
					if (clean.length > 10) {
						num = clean.slice(0, 12);
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
				if (event.keyCode === 32) {
					event.preventDefault();
				}
			});
		}
	};
});
myAppConstructor.directive('onlyTenDigits', function() {

	return {
		restrict : 'A',
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) {
				return;
			}

			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					var val = '';
				}

				var clean = val.replace(/[^0-9]/g, '');
				if (!angular.isUndefined(clean)) {
					var num = 0;
					if (clean.length > 10) {
						num = clean.slice(0, 10);
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
				if (event.keyCode === 32) {
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
					if(clean.length>2 ){
						num =clean.slice(0,2);
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
directive('onlyElevenDigits', function () {
	
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
					if(clean.length>11 ){
						num =clean.slice(0,11);
						clean= num;
					}
					
				}
				
//				if(clean == "0"){
//					clean = '';
//					ngModelCtrl.$setViewValue(clean);
//					ngModelCtrl.$render();
//				}
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