		myAppConstructor.config(['$qProvider', function ($qProvider) {
		    $qProvider.errorOnUnhandledRejections(false);
		}]);
		myAppConstructor.controller('ConstitutionController', function ($scope, $http,commonService,$timeout,$rootScope,gettextCatalog) {
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
            commonService.getGridMenuItems()
        	.then(function (result){
        		$scope.menuList=result;
        		if($scope.menuList.length==0){
        			$scope.showMenu=false;
        		}
        	});
        	$scope.redirectForm=function(url){
        		if(url=="child_registration" || url=="ciclSocialBackgroundReport" || url=="reportSummary" || url=="constitutionofSociety"){
        			commonService.redirectForm(url, undefined);
        		}else{
        			$('#noChildSelected').modal('show');
        		}
        	};
        	$scope.blockList = {};
//        	$rootscope.$broadcast("blocklist", $scope.blockList);
        	$rootScope.$on("blocklist", function(event, data){
        		$scope.blockList = data;
        	});
        	
        	
        	$scope.districtList = [];
        	$http.get("getLoginData").
            then(function(result){
          	  $(".loader").hide();
          	  $scope.areaList=result.data.value.areaDetails;
          	  $scope.designationList=result.data.value.designation;
          	  $scope.userList=result.data.value.userList;
          	  for(var i=0 ; i<$scope.areaList.length; i++){
          		  if($scope.areaList[i].areaLevel == 4){
          			$scope.districtList.push($scope.areaList[i]);
          		  }
          	  }
            });
        	$http.get('getTypeDetails').
       	    then(function(result){
       		    $scope.sex = result.data.genderList;
       		    $scope.sexList = result.data.genderList;
       		    console.log($scope.sex);
       	    },function(error){
       		    console.log(error);
       	    });
        	$scope.constituionView = {};
        	$scope.showConstituion = function(){
	    		if ($scope.constituionView.district == undefined || $scope.constituionView.constitutionType == undefined
					|| $scope.constituionView.constitutionType == "5" && $scope.constituionView.blockId == undefined){
        				$('#nooptionselected').modal('show');
        		}else{
        			var url = Number($scope.constituionView.constitutionType) ==5 ?
        				  ('getConstitutionView?district='+$scope.constituionView.district +
        				   '&constitutionType='+ Number($scope.constituionView.constitutionType)+
        				   '&blockId='+($scope.constituionView.blockId==undefined ? null : ($scope.constituionView.blockId)))
        				   :('getConstitutionView?district='+$scope.constituionView.district+
        				   '&constitutionType='+ Number($scope.constituionView.constitutionType));
        				  
	        		$http.get(url).
		      		then(function(result){
		      			$scope.constitutionListObject=result.data;
		      			if($scope.constitutionListObject.length==0)
		      				$('#nodata').modal('show');
		      		},function(error){
		      		    console.log(error);
		      		});
        		}
        	};
 
        });
//		------------------------------------------Coded By SOurav-------------------------------------------------
		myAppConstructor.controller('ConstitutionOfJJBController', function ($scope, $http,commonService, $window) {
            //This will hide the DIV by default.
        	$scope.selectedChildId=$('#modelValue').val();
        	$scope.jjbformInfo={};

        	commonService.getGridMenuItems()
        	.then(function (result){
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
        	
        	$("#jjbDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.jjbformInfo.jjbDate = date;
        		  }	
        	});
        	
        	$("#JoiningDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.jjbformInfo.joiningDate = date;
        		  }	
        	});
        	
        	$("#socialWorkerOneJoiningDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.jjbformInfo.socialWorkerOneJoiningDate = date;
        		  }	
        	});
        	
        	$("#socialWorkerTwoJoiningDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.jjbformInfo.socialWorkerTwoJoiningDate = date;
        		  }	
        	});
        	$scope.validateName = function(name, errorId){
        		if(name == undefined){
        			if(errorId=='emailmagistrateError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.jjbformInfo.magistrateEmailId.focus();
        		    }
        		    else if(errorId=='emailsocialoneError1'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.jjbformInfo.socialWorkerOneEmailId.focus();
        		    }
        		    else if(errorId=='emailsocialtwoError2'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.jjbformInfo.socialWorkerTwoEmailId.focus();
        		    }
        		}else if(errorId=='phoneNoError1' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofJJB.contactNumbberMagistrate.focus();
    	            return false;
    		    }else if(errorId=='phoneNoError2' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofJJB.contactNumbberSocialWorkerdata1.focus();
    	            return false;
    		    }else if(errorId=='phoneNoError3' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofJJB.contactNumbberSocialWorkerdata2.focus();
    	            return false;
    		    }else{
        			document.getElementById(errorId).innerHTML = "";
        	        return true;
        		}
        	};
        	
        	$scope.resetphnNo = function(model,id){
     		   if($("#"+id).val() != "" && $("#"+id).val().length != 10){
     			   $("#"+id).focus();
     			   return true;
     		   }
     		   else{
     			   return false;
     		   }
     	   }
        	$scope.validateForm = function (){
        		
        	   $scope.phnValidation1 = $scope.resetphnNo($scope.jjbformInfo.magistrateContactNo,'contactNumbberMagistrate');
     		   $scope.phnValidation2 = $scope.resetphnNo($scope.jjbformInfo.socialWorkerOneContactNo,'contactNumbberSocialWorkerdata1');
     		   $scope.phnValidation3 = $scope.resetphnNo($scope.jjbformInfo.socialWorkerTwoContactNo,'contactNumbbersocialWorker2');
     		   if($scope.phnValidation1){
     			   return false;
     		   }
     		   if($scope.phnValidation2){
     			   return false;
     		   }
     		   if($scope.phnValidation3){
     			   return false;
     		   }
        		
		       	     var myArray = [{email:$scope.jjbformInfo.magistrateEmailId,errorName:'emailmagistrateError'},
		         		           {email:$scope.jjbformInfo.socialWorkerOneEmailId,errorName:'emailsocialoneError1'},
		         			       {email:$scope.jjbformInfo.socialWorkerTwoEmailId,errorName:'emailsocialtwoError2'}];

			     		             function checkDuplicateInObject(propertyName, inputArray) {
			     		               var seenDuplicate = "",
			     		                   testObject = {};
			     		               inputArray.map(function(item) {
			     		                 var itemPropertyName = item[propertyName]; 
			     		                 if (itemPropertyName in testObject) {
			     		                	 if(testObject[itemPropertyName].email!=undefined){
			     		                	   testObject[itemPropertyName].duplicate = true;
			           		                   item.duplicate = true;
			           		                   seenDuplicate = testObject[itemPropertyName];
			     		                	 }else{
			     		                	   testObject[itemPropertyName] = item;
			           		                   delete item.duplicate; 
			     		                	 }
			     		                 }
			     		                 else {
			     		                   testObject[itemPropertyName] = item;
			     		                   delete item.duplicate;
			     		                 }
			     		               });
			     		               return seenDuplicate;
			     		             }

                        var filteredEmail= checkDuplicateInObject('email', myArray);
 		                if (filteredEmail!="") {
 	         		        document.getElementById(filteredEmail.errorName).innerHTML = "Email id should not match with other email id";
 	         		   	    $('html,body').animate({
 	      			        scrollTop: $("#"+filteredEmail.errorName).offset().top - 250},'slow');
 	         		        return false;
 	         		    }

          	    if($scope.jjbformInfo.jjbDate === undefined){
          	    	$( "#jjbDate" ).datepicker("show");
          	    }else{
          	    	$('#confirmationModalForconstitutionofJJB').modal('show');
          	    }
             };
             
             $scope.reLoad = function(){
         		$window.location.reload();
         	};
         	$scope.printData = {};
        	$scope.saveData = function (){
        		$scope.printData = $scope.jjbformInfo;
        		if($scope.lang=='en'){
        		$scope.printData.magistrateSex.name = $scope.jjbformInfo.magistrateSex.id == null?null:($scope.jjbformInfo.magistrateSex.id==1?"Male":$scope.jjbformInfo.magistrateSex.id==2?"Female":"Third gender");
        		$scope.printData.socialWorkerOneSex.name = $scope.jjbformInfo.socialWorkerOneSex.id == null?null:($scope.jjbformInfo.socialWorkerOneSex.id==1?"Male":$scope.jjbformInfo.socialWorkerOneSex.id==2?"Female":"Third gender");
        		$scope.printData.socialWorkerTwoSex.name = $scope.jjbformInfo.socialWorkerTwoSex.id == null?null:($scope.jjbformInfo.socialWorkerTwoSex.id==1?"Male":$scope.jjbformInfo.socialWorkerTwoSex.id==2?"Female":"Third gender");
        		}
        		else{
        			$scope.printData.magistrateSex.name = $scope.jjbformInfo.magistrateSex.id == null?null:($scope.jjbformInfo.magistrateSex.id==1?"पुस्र्ष":$scope.jjbformInfo.magistrateSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.socialWorkerOneSex.name = $scope.jjbformInfo.socialWorkerOneSex.id == null?null:($scope.jjbformInfo.socialWorkerOneSex.id==1?"पुस्र्ष":$scope.jjbformInfo.socialWorkerOneSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.socialWorkerTwoSex.name = $scope.jjbformInfo.socialWorkerTwoSex.id == null?null:($scope.jjbformInfo.socialWorkerTwoSex.id==1?"पुस्र्ष":$scope.jjbformInfo.socialWorkerTwoSex.id==2?"महिला":"तीसरा लिंग");
        		}
        		$http.post('saveConstitutionOfJJB',$scope.jjbformInfo).
        		  then(function(result){
        			  if(result.status == 200){
        				  $('#constitutionofJJBModal').modal('show');
          			  }
        		  },function(error){
        		    console.log(error);
        		  });	
              		  
            };
          //====================================download pdf============================
            $scope.printConstitutionOfJJBSubmitData = function(){
            	$(".loader").css("display", "block");
            		var serverURL = 'downloadPDFDataForConstitutionOfJJB?type='+$scope.lang;
            		commonService.downloadFile(serverURL, $scope.printData);
            		$scope.jjbformInfo = {};
        			$(".loader").css("display", "none");
            };

        });
//		------------------------------------------Coded By SOurav-------------------------------------------------
		myAppConstructor.controller('ConstitutionOfSJPUController', function ($scope, $http,commonService,$window) {
            //This will hide the DIV by default.
        	$scope.selectedChildId=$('#modelValue').val();
        	$scope.sjpuformInfo={};
    
        	commonService.getGridMenuItems()
        	.then(function (result){
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
        	$http.get('getTypeDetails').
       	    then(function(result){
       		    $scope.sjpuSex = result.data.genderList;
       		    console.log($scope.sex);
       	    },function(error){
       		    console.log(error);
       	    });
        	
        	$("#sjpuDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.sjpuformInfo.sjpuDate = date;
        		  }	
        	});
        	
        	$("#sjpuJoiningDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.sjpuformInfo.joiningDate = date;
        		  }	
        	});
        	
        	$("#sjpuSocialWorkerOneJoiningDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.sjpuformInfo.socialWorkerOneJoiningDate = date;
        		  }	
        	});
        	
        	$("#sjpuSocialWorkerTwoJoiningDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.sjpuformInfo.socialWorkerTwoJoiningDate = date;
        		  }	
        	});
        	$scope.validateName = function(name, errorId){
        		if( name == undefined  ){
        			if(errorId=='emailspError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.sjpuformInfo.spEmailId.focus();
        		    }else if(errorId=='emailsocialworkeroneError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.sjpuformInfo.socialWorkerOneEmailId.focus();
        		    }else if(errorId=='emailsocialworkertwoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.sjpuformInfo.socialWorkerTwoEmailId.focus();
        		    }
        		}else if(errorId=='spPhoneNoError1' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		    }else if(errorId=='spPhoneNoError2' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		    }else if(errorId=='spPhoneNoError3' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		    }else{
        			document.getElementById(errorId).innerHTML = "";
        	        return true;
        		}
        	};
        	
        	$scope.resetphnNo = function(model,id){
      		   if($("#"+id).val() != "" && $("#"+id).val().length != 10){
      			   $("#"+id).focus();
      			   return true;
      		   }
      		   else{
      			   return false;
      		   }
      	   }
        	
        	$scope.validateForm = function (){
        		$scope.phnValidation1 = $scope.resetphnNo($scope.sjpuformInfo.spContactNo,'contactNumbber');
      		   $scope.phnValidation2 = $scope.resetphnNo($scope.sjpuformInfo.socialWorkerOneContactNo,'contactNumberSocialWorkerdata1');
      		   $scope.phnValidation3 = $scope.resetphnNo($scope.sjpuformInfo.socialWorkerTwoContactNo,'contactNumbersocialWorker2');
      		   if($scope.phnValidation1){
      			   return false;
      		   }
      		   if($scope.phnValidation2){
      			   return false;
      		   }
      		   if($scope.phnValidation3){
      			   return false;
      		   }
         		
        	     var myArray = [{email:$scope.sjpuformInfo.spEmailId,errorName:'emailspError'},
            		           {email:$scope.sjpuformInfo.socialWorkerOneEmailId,errorName:'emailsocialworkeroneError'},
            			       {email:$scope.sjpuformInfo.socialWorkerTwoEmailId,errorName:'emailsocialworkertwoError'}];

        		             function checkDuplicateInObject(propertyName, inputArray) {
        		               var seenDuplicate = "",
        		                   testObject = {};
        		               inputArray.map(function(item) {
        		                 var itemPropertyName = item[propertyName]; 
        		                 if (itemPropertyName in testObject) {
        		                	 if(testObject[itemPropertyName].email!=undefined){
        		                	   testObject[itemPropertyName].duplicate = true;
              		                   item.duplicate = true;
              		                   seenDuplicate = testObject[itemPropertyName];
        		                	 }else{
        		                	   testObject[itemPropertyName] = item;
              		                   delete item.duplicate; 
        		                	 }
        		                 }
        		                 else {
        		                   testObject[itemPropertyName] = item;
        		                   delete item.duplicate;
        		                 }
        		               });
        		               return seenDuplicate;
        		             }

                            var filteredEmail= checkDuplicateInObject('email', myArray);
    		                if (filteredEmail!="") {
    	         		        document.getElementById(filteredEmail.errorName).innerHTML = "Email id should not match with other email id";
    	         		   	    $('html,body').animate({
    	      			        scrollTop: $("#"+filteredEmail.errorName).offset().top - 250},'slow');
    	         		        return false;
    	         		    }
        				
			          	    if($scope.sjpuformInfo.sjpuDate === undefined){
			          	    	$( "#sjpuDate" ).datepicker("show");
			          	    }else{
			          	    	$('#confirmationModalForconstitutionofSJPU').modal('show');
			          	    }
             };
             
             $scope.reLoad = function(){
          		$window.location.reload();
          	};
          	$scope.printData = {};  
        	$scope.saveData = function (){
        		$scope.printData = $scope.sjpuformInfo;
        		$scope.printData.spSex.name = $scope.sjpuformInfo.spSex.id == null?null:($scope.sjpuformInfo.spSex.id==1?"Male":$scope.sjpuformInfo.spSex.id==2?"Female":"Third gender");
        		$scope.printData.socialWorkerOneSex.name = $scope.sjpuformInfo.socialWorkerOneSex.id == null?null:($scope.sjpuformInfo.socialWorkerOneSex.id==1?"Male":$scope.sjpuformInfo.socialWorkerOneSex.id==2?"Female":"Third gender");
        		$scope.printData.socialWorkerTwoSex.name = $scope.sjpuformInfo.socialWorkerTwoSex.id == null?null:($scope.sjpuformInfo.socialWorkerTwoSex.id==1?"Male":$scope.sjpuformInfo.socialWorkerTwoSex.id==2?"Female":"Third gender");
       		    
        		$http.post('saveConstitutionOfSJPU',$scope.sjpuformInfo).
        		  then(function(result){
        			  if(result.status == 200){
        				  $('#constitutionofSJPUModal').modal('show');
        			  } 
        		  },function(error){
        		    console.log(error);
        		  });	
              		  
            };
          //====================================download pdf============================
            $scope.printConstitutionOfSJPUSubmitData = function(){
            	$(".loader").css("display", "block");
            		var serverURL = 'downloadPDFDataForConstitutionOfSJPU?type='+$scope.lang;
            		commonService.downloadFile(serverURL, $scope.printData);
            		$scope.sjpuformInfo = {};
        			$(".loader").css("display", "none");
            };
 
        });
//		------------------------------------------Coded By SOurav-------------------------------------------------
		myAppConstructor.controller('ConstitutionofDCPCController', function ($scope, $http,commonService,$window) {
            //This will hide the DIV by default.
        	$scope.selectedChildId=$('#modelValue').val();
        	$scope.dcpcformInfo={};
    
        	commonService.getGridMenuItems()
        	.then(function (result){
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
        	$http.get('getTypeDetails').
       	    then(function(result){
       		    $scope.dcpcSex = result.data.genderList;
       		    console.log($scope.sex);
       	    },function(error){
       		    console.log(error);
       	    });
        	
        	$("#dcpcDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.dcpcformInfo.dcpcDate = date;
        		  }	
        	});
        	$scope.validateName = function(name, errorId){
        		if( name == undefined  ){
        			if(errorId=='emailzpError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.zillaParishadEmailId.focus();
        		    }else if(errorId=='emaildistmagistrateError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.magistrateEmailId.focus();
        		    }else if(errorId=='emaildcpcdeoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.deoEmailId.focus();
        		    }else if(errorId=='emailcdmoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.cdmoEmailId.focus();
        		    }else if(errorId=='emaildloError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.dloEmailId.focus();
        		    }else if(errorId=='emailpddrdaError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.pddrdaEmailId.focus();
        		    }else if(errorId=='emailMemberOneError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberOneEmailId.focus();
        		    }else if(errorId=='emailMemberTwoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberTwoEmailId.focus();
        		    }else if(errorId=='emailMemberThreeError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberThreeEmailId.focus();
        		    }else if(errorId=='emailMemberFourError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberFourEmailId.focus();
        		    }else if(errorId=='emailMemberFiveError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberFiveEmailId.focus();
        		    }else if(errorId=='emailMemberSixError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberSixEmailId.focus();
        		    }else if(errorId=='emailMemberSevenError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberSevenEmailId.focus();
        		    }else if(errorId=='emailMemberEightError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionofDCPC.memberEightEmailId.focus();
        		    }
        		}else if(errorId=='dcpcPhoneNoError1' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumbberZillaParishad.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError2' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumbberSocialWorkerdata1.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError3' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumbberSocialWorkerdata2.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError4' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumbbersocialWorker3.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError5' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumbbersocialWorker4.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError6' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumbberSocialWorkerdata5.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError7' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberOne.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError8' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberTwo.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError9' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberThree.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError10' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberFour.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError11' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberFive.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError12' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberSix.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError13' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberSeven.focus();
    	            return false;
    		    }else if(errorId=='dcpcPhoneNoError14' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionofDCPC.contactNumberMemberEight.focus();
    	            return false;
    		    }else{
        			document.getElementById(errorId).innerHTML = "";
        	        return true;
        		}
        	};
        	
        	
        	$scope.resetphnNo = function(model,id){
       		   if($("#"+id).val() != "" && $("#"+id).val().length != 10){
       			   $("#"+id).focus();
       			   return true;
       		   }
       		   else{
       			   return false;
       		   }
       	   };
        	$scope.validateForm = function (){
        	   $scope.phnValidation1 = $scope.resetphnNo($scope.dcpcformInfo.zillaParishadContactNo,'contactNumbberZillaParishad');
       		   $scope.phnValidation2 = $scope.resetphnNo($scope.dcpcformInfo.magistrateContactNo,'contactNumbberDistrictMagistrate1');
       		   $scope.phnValidation3 = $scope.resetphnNo($scope.dcpcformInfo.deoContactNo,'contactNumbberDEO');
       		   $scope.phnValidation4 = $scope.resetphnNo($scope.dcpcformInfo.cdmoContactNo,'contactNumberCDMO');
       		   $scope.phnValidation5 = $scope.resetphnNo($scope.dcpcformInfo.dloContactNo,'contactNumberDLO');
       		   $scope.phnValidation6 = $scope.resetphnNo($scope.dcpcformInfo.pddrdaContactNo,'contactNumberPDDRDA');
       		   $scope.phnValidation7 = $scope.resetphnNo($scope.dcpcformInfo.memberOneContactNo,'contactNumberMemberOne');
       		   $scope.phnValidation8 = $scope.resetphnNo($scope.dcpcformInfo.memberTwoContactNo,'contactNumberMemberTwo');
       		   $scope.phnValidation9 = $scope.resetphnNo($scope.dcpcformInfo.memberThreeContactNo,'contactNumberMemberThree');
       		   $scope.phnValidation10 = $scope.resetphnNo($scope.dcpcformInfo.memberFourContactNo,'contactNumberMemberFour');
       		   $scope.phnValidation11 = $scope.resetphnNo($scope.dcpcformInfo.memberFiveContactNo,'contactNumberMemberFive');
       		   $scope.phnValidation12 = $scope.resetphnNo($scope.dcpcformInfo.memberSixContactNo,'contactNumberMemberSix');
       		   $scope.phnValidation13 = $scope.resetphnNo($scope.dcpcformInfo.memberSevenContactNo,'contactNumberMemberSeven');
       		   $scope.phnValidation14 = $scope.resetphnNo($scope.dcpcformInfo.memberEightContactNo,'contactNumberMemberEight');
       		
       		   if($scope.phnValidation1){
       			   return false;
       		   }
       		   if($scope.phnValidation2){
       			   return false;
       		   }
       		   if($scope.phnValidation3){
       			   return false;
       		   }
       		  if($scope.phnValidation4){
    			   return false;
    		   }
       		  if($scope.phnValidation5){
 			   return false;
 		       }
       		  if($scope.phnValidation6){
  			   return false;
  		       }
       		  if($scope.phnValidation7){
    			   return false;
    		       }
       		  if($scope.phnValidation8){
    			   return false;
    		       }
       		  if($scope.phnValidation9){
    			   return false;
    		       }
       		  if($scope.phnValidation10){
    			   return false;
    		       }
       		  if($scope.phnValidation11){
    			   return false;
    		       }
       		  if($scope.phnValidation12){
    			   return false;
		       }
       		  if($scope.phnValidation13){
    			   return false;
		       }
	   		 if($scope.phnValidation14){
				   return false;
			       }
        		
        		var myArray = [{email:$scope.dcpcformInfo.zillaParishadEmailId,errorName:'emailzpError'},
            		           {email:$scope.dcpcformInfo.magistrateEmailId,errorName:'emaildistmagistrateError'},
            			       {email:$scope.dcpcformInfo.deoEmailId,errorName:'emaildcpcdeoError'},
            		           {email:$scope.dcpcformInfo.cdmoEmailId,errorName:'emailcdmoError'},
        			    	   {email:$scope.dcpcformInfo.dloEmailId,errorName:'emaildloError'},
        			    	   {email:$scope.dcpcformInfo.pddrdaEmailId,errorName:'emailpddrdaError'},
        			    	   {email:$scope.dcpcformInfo.memberOneEmailId,errorName:'emailMemberOneError'},
        			    	   {email:$scope.dcpcformInfo.memberTwoEmailId,errorName:'emailMemberTwoError'},
        			    	   {email:$scope.dcpcformInfo.memberThreeEmailId,errorName:'emailMemberThreeError'},
        			    	   {email:$scope.dcpcformInfo.memberFourEmailId,errorName:'emailMemberFourError'},
        			    	   {email:$scope.dcpcformInfo.memberFiveEmailId,errorName:'emailMemberFiveError'},
        			    	   {email:$scope.dcpcformInfo.memberSixEmailId,errorName:'emailMemberSixError'},
        			    	   {email:$scope.dcpcformInfo.memberSevenEmailId,errorName:'emailMemberSevenError'},
        			    	   {email:$scope.dcpcformInfo.memberEightEmailId,errorName:'emailMemberEightError'}];

        		             function checkDuplicateInObject(propertyName, inputArray) {
        		               var seenDuplicate = "",
        		                   testObject = {};
        		               inputArray.map(function(item) {
        		                 var itemPropertyName = item[propertyName]; 
        		                 if (itemPropertyName in testObject) {
        		                	 if(testObject[itemPropertyName].email!=undefined){
        		                	   testObject[itemPropertyName].duplicate = true;
              		                   item.duplicate = true;
              		                   seenDuplicate = testObject[itemPropertyName];
        		                	 }else{
        		                	   testObject[itemPropertyName] = item;
              		                   delete item.duplicate; 
        		                	 }
        		                 }
        		                 else {
        		                   testObject[itemPropertyName] = item;
        		                   delete item.duplicate;
        		                 }
        		               });
        		               return seenDuplicate;
        		             }

                            var filteredEmail= checkDuplicateInObject('email', myArray);
        		             console.log('Duplicate emails: ' + filteredEmail.email);
    		                if (filteredEmail!="") {
    	         		        document.getElementById(filteredEmail.errorName).innerHTML = "Email id should not match with other email id";
    	         		   	    $('html,body').animate({
    	      			        scrollTop: $("#"+filteredEmail.errorName).offset().top - 250},'slow');
    	         		        return false;
    	         		    }
        		
        		
          	    if($scope.dcpcformInfo.dcpcDate === undefined){
          	    	$( "#dcpcDate" ).datepicker("show");
          	    }else{
          	    	$('#confirmationModalForconstitutionofDCPC').modal('show');
          	    }
             };
            $scope.reLoad = function(){
           		$window.location.reload();
           	};
           	$scope.printData = {};
        	$scope.saveData = function (){
        		$scope.printData = $scope.dcpcformInfo;
        		if($scope.lang=='en'){
        		$scope.printData.zillaParishadSex.name = $scope.dcpcformInfo.zillaParishadSex.id == null?null:($scope.dcpcformInfo.zillaParishadSex.id==1?"Male":$scope.dcpcformInfo.zillaParishadSex.id==2?"Female":"Third gender");
        		$scope.printData.magistrateSex.name = $scope.dcpcformInfo.magistrateSex.id == null?null:($scope.dcpcformInfo.magistrateSex.id==1?"Male":$scope.dcpcformInfo.magistrateSex.id==2?"Female":"Third gender");
        		$scope.printData.deoSex.name = $scope.dcpcformInfo.deoSex.id == null?null:($scope.dcpcformInfo.deoSex.id==1?"Male":$scope.dcpcformInfo.deoSex.id==2?"Female":"Third gender");
        		$scope.printData.cdmoSex.name = $scope.dcpcformInfo.cdmoSex.id == null?null:($scope.dcpcformInfo.cdmoSex.id==1?"Male":$scope.dcpcformInfo.cdmoSex.id==2?"Female":"Third gender");
        		$scope.printData.dloSex.name = $scope.dcpcformInfo.dloSex.id == null?null:($scope.dcpcformInfo.dloSex.id==1?"Male":$scope.dcpcformInfo.dloSex.id==2?"Female":"Third gender");
        		$scope.printData.pddrdaSex.name = $scope.dcpcformInfo.pddrdaSex.id == null?null:($scope.dcpcformInfo.pddrdaSex.id==1?"Male":$scope.dcpcformInfo.pddrdaSex.id==2?"Female":"Third gender");
        		$scope.printData.policeSuperintendentSex.name = $scope.dcpcformInfo.policeSuperintendentSex.id == null?null:($scope.dcpcformInfo.policeSuperintendentSex.id==1?"Male":$scope.dcpcformInfo.policeSuperintendentSex.id==2?"Female":"Third gender");
        		$scope.printData.memberOneSex.name = $scope.dcpcformInfo.memberOneSex.id == null?null:($scope.dcpcformInfo.memberOneSex.id==1?"Male":$scope.dcpcformInfo.memberOneSex.id==2?"Female":"Third gender");
        		$scope.printData.memberTwoSex.name = $scope.dcpcformInfo.memberTwoSex.id == null?null:($scope.dcpcformInfo.memberTwoSex.id==1?"Male":$scope.dcpcformInfo.memberTwoSex.id==2?"Female":"Third gender");
        		$scope.printData.memberThreeSex.name = $scope.dcpcformInfo.memberThreeSex.id == null?null:($scope.dcpcformInfo.memberThreeSex.id==1?"Male":$scope.dcpcformInfo.memberThreeSex.id==2?"Female":"Third gender");
        		$scope.printData.memberFourSex.name = $scope.dcpcformInfo.memberFourSex.id == null?null:($scope.dcpcformInfo.memberFourSex.id==1?"Male":$scope.dcpcformInfo.memberFourSex.id==2?"Female":"Third gender");
        		$scope.printData.memberFiveSex.name = $scope.dcpcformInfo.memberFiveSex.id == null?null:($scope.dcpcformInfo.memberFiveSex.id==1?"Male":$scope.dcpcformInfo.memberFiveSex.id==2?"Female":"Third gender");
        		$scope.printData.memberSixSex.name = $scope.dcpcformInfo.memberSixSex.id == null?null:($scope.dcpcformInfo.memberSixSex.id==1?"Male":$scope.dcpcformInfo.memberSixSex.id==2?"Female":"Third gender");
        		$scope.printData.memberSevenSex.name = $scope.dcpcformInfo.memberSevenSex.id == null?null:($scope.dcpcformInfo.memberSevenSex.id==1?"Male":$scope.dcpcformInfo.memberSevenSex.id==2?"Female":"Third gender");
        		$scope.printData.memberEightSex.name = $scope.dcpcformInfo.memberEightSex.id == null?null:($scope.dcpcformInfo.memberEightSex.id==1?"Male":$scope.dcpcformInfo.memberEightSex.id==2?"Female":"Third gender");
        		}
        		else{
        			$scope.printData.zillaParishadSex.name = $scope.dcpcformInfo.zillaParishadSex.id == null?null:($scope.dcpcformInfo.zillaParishadSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.zillaParishadSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.magistrateSex.name = $scope.dcpcformInfo.magistrateSex.id == null?null:($scope.dcpcformInfo.magistrateSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.magistrateSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.deoSex.name = $scope.dcpcformInfo.deoSex.id == null?null:($scope.dcpcformInfo.deoSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.deoSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.cdmoSex.name = $scope.dcpcformInfo.cdmoSex.id == null?null:($scope.dcpcformInfo.cdmoSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.cdmoSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.dloSex.name = $scope.dcpcformInfo.dloSex.id == null?null:($scope.dcpcformInfo.dloSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.dloSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.pddrdaSex.name = $scope.dcpcformInfo.pddrdaSex.id == null?null:($scope.dcpcformInfo.pddrdaSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.pddrdaSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.policeSuperintendentSex.name = $scope.dcpcformInfo.policeSuperintendentSex.id == null?null:($scope.dcpcformInfo.policeSuperintendentSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.policeSuperintendentSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberOneSex.name = $scope.dcpcformInfo.memberOneSex.id == null?null:($scope.dcpcformInfo.memberOneSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberOneSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberTwoSex.name = $scope.dcpcformInfo.memberTwoSex.id == null?null:($scope.dcpcformInfo.memberTwoSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberTwoSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberThreeSex.name = $scope.dcpcformInfo.memberThreeSex.id == null?null:($scope.dcpcformInfo.memberThreeSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberThreeSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberFourSex.name = $scope.dcpcformInfo.memberFourSex.id == null?null:($scope.dcpcformInfo.memberFourSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberFourSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberFiveSex.name = $scope.dcpcformInfo.memberFiveSex.id == null?null:($scope.dcpcformInfo.memberFiveSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberFiveSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberSixSex.name = $scope.dcpcformInfo.memberSixSex.id == null?null:($scope.dcpcformInfo.memberSixSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberSixSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberSevenSex.name = $scope.dcpcformInfo.memberSevenSex.id == null?null:($scope.dcpcformInfo.memberSevenSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberSevenSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberEightSex.name = $scope.dcpcformInfo.memberEightSex.id == null?null:($scope.dcpcformInfo.memberEightSex.id==1?"पुस्र्ष":$scope.dcpcformInfo.memberEightSex.id==2?"महिला":"तीसरा लिंग");
        		}
        		
        		  $http.post('saveConstitutionOfDCPC',$scope.dcpcformInfo).
        		  then(function(result){
        			  if(result.status == 200){
        				  $('#constitutionofDCPCModal').modal('show');
        			  }	  
        		  },function(error){
        		    console.log(error);
        		  });	
            };
          //====================================download pdf============================
            $scope.printConstitutionOfDCPCSubmitData = function(){
            	$(".loader").css("display", "block");
            		var serverURL = 'downloadPDFDataForConstitutionOfDCPC?type='+$scope.lang;
            		commonService.downloadFile(serverURL, $scope.printData);
            		$scope.dcpcformInfo = {};
        			$(".loader").css("display", "none");
            };

        });
//		------------------------------------------Coded By SOurav-------------------------------------------------
		myAppConstructor.controller('ConstitutionViewController', function ($scope, $http,commonService,$window) {
            //This will hide the DIV by default.
        	$scope.selectedChildId=$('#modelValue').val();
        	$scope.dcpcformInfo={};
    
        	commonService.getGridMenuItems()
        	.then(function (result){
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
        	$http.get('getTypeDetails').
       	    then(function(result){
       		    $scope.dcpcSex = result.data.genderList;
       		    console.log($scope.sex);
       	    },function(error){
       		    console.log(error);
       	    });
        	
        	/*
        	 * Pagination code started here @Author - Sourav ke nath
        	 * */
            $scope.currentPage = 1;
            $scope.pageSize = 2;

            $scope.pageChangeHandler = function(num) {
               console.log('Pagination call ' + num);
            };
        	/*
        	 * setRadio code started here @Author - Sourav ke nath
        	 * */

        });
//		------------------------------------------Pratyush-------------------------------------------------
		myAppConstructor.controller('constitutionofCWCController', function ($scope, $http,commonService, $window) {
            
            $scope.cwcConstitution = {};
            
        	commonService.getGridMenuItems()
        	.then(function (result){
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
        	
    	    $http.get('getTypeDetails').
    	    then(function(result){
    		    $scope.sex = result.data.genderList;
    		    console.log($scope.sex);
    	    },function(error){
    		    console.log(error);
    	    });
        	
        	$("#constitutionDate").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.cwcConstitution.constitutionDate = date;
        		  }	
        	});
        	
        	$("#dateOfJoiningCP").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.cwcConstitution.chairpersonJoiningDate = date;
        		  }	
        	});
        	
        	$("#dateOfJoiningP1").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.cwcConstitution.memberOneJoiningDate = date;
        		  }	
        	});
        	
        	$("#dateOfJoiningP2").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.cwcConstitution.memberTwoJoiningDate = date;
        		  }	
        	});
        	
        	$("#dateOfJoiningP3").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.cwcConstitution.memberThreeJoiningDate = date;
        		  }	
        	});
        	
        	$("#dateOfJoiningP4").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.cwcConstitution.memberFourJoiningDate = date;
        		  }	
        	});
        	
        	$("#dateOfJoiningDEO").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.cwcConstitution.dEOJoiningDate = date;
        		  }	
        	});
        	
        	$scope.validateName = function(name, errorId){
        		if(name == undefined){
        		 if(errorId=='emailChairpersonError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionOfCWC.emailChairperson.focus();
        		    }else if(errorId=='emailMemberoneError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.cwcConstitution.memberOneEmail.focus();
        		    }else if(errorId=='emailMembertwoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.cwcConstitution.memberTwoEmail.focus();
        		    }else if(errorId=='emailMemberthreeError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.cwcConstitution.memberThreeEmail.focus();
        		    }else if(errorId=='emailMemberfourError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.cwcConstitution.memberFourEmail.focus();
        		    }else if(errorId=='emailMemberdeoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.cwcConstitution.dEOEmail.focus();
        		    }
        		}else if(errorId=='cpPhoneNoError' && name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionOfCWC.cpContactNumber.focus();
    	            return false;
    		    }else if(errorId=='m1PhoneNoError'&& name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionOfCWC.m1contactNumber.focus();
    	            return false;
    		    }else if(errorId=='m2PhoneNoError'&& name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionOfCWC.m2contactNumber.focus();
    	            return false;
    		    }else if(errorId=='m3PhoneNoError'&& name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionOfCWC.m3contactNumber.focus();
    	            return false;
    		    }else if(errorId=='m4PhoneNoError'&& name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionOfCWC.m4contactNumber.focus();
    	            return false;
    		    }else if(errorId=='deoPhoneNoError'&& name.length != 10 && name != "") {
            		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
    		        document.constitutionOfCWC.deoContactNumber.focus();
    	            return false;
    		    }else{
        			document.getElementById(errorId).innerHTML = "";
        	        return true;
        		}
        	};
        	
        	$scope.resetphnNo = function(model,id){
        		   if($("#"+id).val() != "" && $("#"+id).val().length != 10){
        			   $("#"+id).focus();
        			   return true;
        		   }
        		   else{
        			   return false;
        		   }
        	   }
        	
        	$scope.validateCWCForm = function(){
        		$scope.phnValidation1 = $scope.resetphnNo($scope.cwcConstitution.chairpersonContact,'cpContactNumber');
        		   $scope.phnValidation2 = $scope.resetphnNo($scope.cwcConstitution.memberOneContact,'m1contactNumber');
        		   $scope.phnValidation3 = $scope.resetphnNo($scope.cwcConstitution.memberTwoContact,'m2contactNumber');
        		   $scope.phnValidation4 = $scope.resetphnNo($scope.cwcConstitution.memberThreeContact,'m3contactNumber');
        		   $scope.phnValidation5 = $scope.resetphnNo($scope.cwcConstitution.memberFourContact,'m4contactNumber');
        		   $scope.phnValidation6 = $scope.resetphnNo($scope.cwcConstitution.dEOContact,'deoContactNumber');
        		   if($scope.phnValidation1){
        			   return false;
        		   }
        		   if($scope.phnValidation2){
        			   return false;
        		   }
        		   if($scope.phnValidation3){
        			   return false;
        		   }
        		   if($scope.phnValidation4){
        			   return false;
        		   }
        		   if($scope.phnValidation5){
        			   return false;
        		   }
        		   if($scope.phnValidation6){
        			   return false;
        		   }
        		     var myArray = [{email:$scope.cwcConstitution.chairpersonEmail,errorName:'emailChairpersonError'},
		         		           {email:$scope.cwcConstitution.memberOneEmail,errorName:'emailMemberoneError'},
		         			       {email:$scope.cwcConstitution.memberTwoEmail,errorName:'emailMembertwoError'},
		         			       {email:$scope.cwcConstitution.memberThreeEmail,errorName:'emailMemberthreeError'},
		         			       {email:$scope.cwcConstitution.memberFourEmail,errorName:'emailMemberfourError'},
		         			       {email:$scope.cwcConstitution.dEOEmail,errorName:'emailMemberdeoError'}];

			     		             function checkDuplicateInObject(propertyName, inputArray) {
			     		               var seenDuplicate = "",
			     		                   testObject = {};
			     		               inputArray.map(function(item) {
			     		                 var itemPropertyName = item[propertyName]; 
			     		                 if (itemPropertyName in testObject) {
			     		                	 if(testObject[itemPropertyName].email!=undefined){
			     		                	   testObject[itemPropertyName].duplicate = true;
			           		                   item.duplicate = true;
			           		                   seenDuplicate = testObject[itemPropertyName];
			     		                	 }else{
			     		                	   testObject[itemPropertyName] = item;
			           		                   delete item.duplicate; 
			     		                	 }
			     		                 }
			     		                 else {
			     		                   testObject[itemPropertyName] = item;
			     		                   delete item.duplicate;
			     		                 }
			     		               });
			     		               return seenDuplicate;
			     		             }

                     var filteredEmail= checkDuplicateInObject('email', myArray);
		                if (filteredEmail!="") {
	         		        document.getElementById(filteredEmail.errorName).innerHTML = "Email id should not match with other email id";
	         		   	    $('html,body').animate({
	      			        scrollTop: $("#"+filteredEmail.errorName).offset().top - 250},'slow');
	         		        return false;
	         		    }

        		if($scope.cwcConstitution.constitutionDate == undefined){
        			$("#constitutionDate").datepicker("show");
        		}else{
        			$('#confirmationModalForConstitutionCWC').modal('show');
        		}	
        	};
        	$scope.reLoad = function(){
        		$window.location.reload();
        	};
        	$scope.printData = {};
        	$scope.saveCWCConstitution = function(){
        		$scope.printData = $scope.cwcConstitution;
        		if($scope.lang=='en'){
        		$scope.printData.chairpersonSex.name = $scope.cwcConstitution.chairpersonSex.id==null?null:($scope.cwcConstitution.chairpersonSex.id==1?"Male":$scope.cwcConstitution.chairpersonSex.id==2?"Female":"Third gender");
        		$scope.printData.memberOneSex.name = $scope.cwcConstitution.memberOneSex.id==null?null:($scope.cwcConstitution.memberOneSex.id==1?"Male":$scope.cwcConstitution.memberOneSex.id==2?"Female":"Third gender");
        		$scope.printData.memberTwoSex.name = $scope.cwcConstitution.memberTwoSex.id==null?null:($scope.cwcConstitution.memberTwoSex.id==1?"Male":$scope.cwcConstitution.memberTwoSex.id==2?"Female":"Third gender");
        		$scope.printData.memberThreeSex.name = $scope.cwcConstitution.memberThreeSex.id==null?null:($scope.cwcConstitution.memberThreeSex.id==1?"Male":$scope.cwcConstitution.memberThreeSex.id==2?"Female":"Third gender");
        		$scope.printData.memberFourSex.name = $scope.cwcConstitution.memberFourSex.id==null?null:($scope.cwcConstitution.memberFourSex.id==1?"Male":$scope.cwcConstitution.memberFourSex.id==2?"Female":"Third gender");
        		$scope.printData.dEOSex.name = $scope.cwcConstitution.dEOSex.id==null?null:($scope.cwcConstitution.dEOSex.id==1?"Male":$scope.cwcConstitution.dEOSex.id==2?"Female":"Third gender");
        	}
        		else{
        			$scope.printData.chairpersonSex.name = $scope.cwcConstitution.chairpersonSex.id==null?null:($scope.cwcConstitution.chairpersonSex.id==1?"पुस्र्ष":$scope.cwcConstitution.chairpersonSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberOneSex.name = $scope.cwcConstitution.memberOneSex.id==null?null:($scope.cwcConstitution.memberOneSex.id==1?"पुस्र्ष":$scope.cwcConstitution.memberOneSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberTwoSex.name = $scope.cwcConstitution.memberTwoSex.id==null?null:($scope.cwcConstitution.memberTwoSex.id==1?"पुस्र्ष":$scope.cwcConstitution.memberTwoSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberThreeSex.name = $scope.cwcConstitution.memberThreeSex.id==null?null:($scope.cwcConstitution.memberThreeSex.id==1?"पुस्र्ष":$scope.cwcConstitution.memberThreeSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.memberFourSex.name = $scope.cwcConstitution.memberFourSex.id==null?null:($scope.cwcConstitution.memberFourSex.id==1?"पुस्र्ष":$scope.cwcConstitution.memberFourSex.id==2?"महिला":"तीसरा लिंग");
            		$scope.printData.dEOSex.name = $scope.cwcConstitution.dEOSex.id==null?null:($scope.cwcConstitution.dEOSex.id==1?"पुस्र्ष":$scope.cwcConstitution.dEOSex.id==2?"महिला":"तीसरा लिंग");
        		}
        		
        		$http.post('saveCWCConstitution',$scope.cwcConstitution).
           		then(function(result){
           			if(result.status == 200){
           				$('#constitutionCWCModal').modal('show');
           			}
           		},function(error){
           			console.log(error);
           		});
        		
        	};
        	
        	//====================================download pdf============================
            $scope.printConstitutionOfCWCSubmitData = function(){
            	$(".loader").css("display", "block");
            		var serverURL = 'downloadPDFDataForConstitutionOfCWC?type='+$scope.lang;
            		commonService.downloadFile(serverURL, $scope.printData);
            		$scope.cwcConstitution = {};
            		document.getElementById("dateOfJoiningCP").value="";
            		document.getElementById("dateOfJoiningP1").value="";
            		document.getElementById("dateOfJoiningP2").value="";
            		document.getElementById("dateOfJoiningP3").value="";
            		document.getElementById("dateOfJoiningP4").value="";
            		document.getElementById("dateOfJoiningDEO").value="";
            		document.getElementById("constitutionDate").value="";
        			$(".loader").css("display", "none");
            };
 
        });
		
		myAppConstructor.controller('constitutionOfBCPCController', function ($scope, $http,commonService, $window,$rootScope,$filter) {
            
            $scope.bcpcConstitution = {};
        	commonService.getGridMenuItems()
        	.then(function (result){
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
        	
    	    $http.get('getTypeDetails').
    	    then(function(result){
    		    $scope.sex = result.data.genderList;
    		    console.log($scope.sex);
    	    },function(error){
    		    console.log(error);
    	    });
    	    
    		$http.get("getBCPCConstitution").
            then(function(result){
            	$scope.blockList = result.data; 
            	$rootScope.$broadcast("blocklist", $scope.blockList);
            },function(error){
    		    console.log(error);
    	    });
    		
    	    $("#bcpc_datepicker").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.bcpcConstitution.constitutionDate = date;
        		  }	
        	});
    	    
    	    $scope.validateName = function(name, errorId){
        		if( name == undefined && document.constitutionBCPC != undefined ){
        			if(errorId=='bcPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.bcContactNumber.focus();
        	            return false;
        		    }else if(errorId=='bdoPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.bdoContactNumber.focus();
        	            return false;
        		    }else if(errorId=='dcpcPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.dcpuContactNumber.focus();
        	            return false;
        		    }else if(errorId=='icdsPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.icdsContactNumber.focus();
        	            return false;
        		    }else if(errorId=='beoPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.beoContactNumber.focus();
        	            return false;
        		    }else if(errorId=='oichcPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.oichcContactNumber.focus();
        	            return false;
        		    }else if(errorId=='vlgLvlPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.vlgLvlContactNumber.focus();
        	            return false;
        		    }else if(errorId=='csmPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.csmPhoneNoErr.focus();
        	            return false;
        		    }else if(errorId=='cmPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionBCPC.cmPhoneNoErr.focus();
        	            return false;
        		    }else if(errorId=='emailbcError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.blockChairmanEmail.focus();
        		    }else if(errorId=='emailbdoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.bdoEmail.focus();
        		    }else if(errorId=='emailbcpcmemberError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.dcpuMemberEmail.focus();
        		    }else if(errorId=='emailicdsError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.icdsFunctionaryEmail.focus();
        		    }else if(errorId=='emailbeoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.beoEmail.focus();
        		    }else if(errorId=='emailoichcError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.oichcEmail.focus();
        		    }else if(errorId=='emailbcpcchairmanError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.vlgLvlCPCEmail.focus();
        		    }else if(errorId=='emailcsmError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.csmEmail.focus();
        		    }else if(errorId=='emailcmError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.bcpcConstitution.cmEmail.focus();
        		    }
        		}else{
        			document.getElementById(errorId).innerHTML = "";
        	        return true;
        		}
        	};
        	
        	$scope.validateBCPCForm = function(){
        		var myArray = [{email:$scope.bcpcConstitution.blockChairmanEmail,errorName:'emailbcError'},
	         		           {email:$scope.bcpcConstitution.bdoEmail,errorName:'emailbdoError'},
	         			       {email:$scope.bcpcConstitution.dcpuMemberEmail,errorName:'emailbcpcmemberError'},
	         			       {email:$scope.bcpcConstitution.icdsFunctionaryEmail,errorName:'emailicdsError'},
	         			       {email:$scope.bcpcConstitution.beoEmail,errorName:'emailbeoError'},
	         			       {email:$scope.bcpcConstitution.oichcEmail,errorName:'emailoichcError'},
	         			       {email:$scope.bcpcConstitution.vlgLvlCPCEmail,errorName:'emailbcpcchairmanError'},
	         			       {email:$scope.bcpcConstitution.csmEmail,errorName:'emailcsmError'},
	         			       {email:$scope.bcpcConstitution.cmEmail,errorName:'emailcmError'}];

		     		             function checkDuplicateInObject(propertyName, inputArray) {
		     		               var seenDuplicate = "",
		     		                   testObject = {};
		     		               inputArray.map(function(item) {
		     		                 var itemPropertyName = item[propertyName]; 
		     		                 if (itemPropertyName in testObject) {
		     		                	 if(testObject[itemPropertyName].email!=undefined){
		     		                	   testObject[itemPropertyName].duplicate = true;
		           		                   item.duplicate = true;
		           		                   seenDuplicate = testObject[itemPropertyName];
		     		                	 }else{
		     		                	   testObject[itemPropertyName] = item;
		           		                   delete item.duplicate; 
		     		                	 }
		     		                 }
		     		                 else {
		     		                   testObject[itemPropertyName] = item;
		     		                   delete item.duplicate;
		     		                 }
		     		               });
		     		               return seenDuplicate;
		     		             }

                 var filteredEmail= checkDuplicateInObject('email', myArray);
	                if (filteredEmail!="") {
         		        document.getElementById(filteredEmail.errorName).innerHTML = "Email id should not match with other email id";
         		   	    $('html,body').animate({
      			        scrollTop: $("#"+filteredEmail.errorName).offset().top - 250},'slow');
         		        return false;
         		    }
        		if($scope.bcpcConstitution.constitutionDate == undefined){
        			$("#bcpc_datepicker").datepicker("show");
        		}else{
        			$('#confirmationModalForConstitutionBCPC').modal('show');
        		}	
        	};
        	
        	$scope.reLoad = function(){
        		$window.location.reload();
        	};
        	$scope.printData = {};
        	$scope.saveBCPCConstitution = function(){
        		$scope.printData = $scope.bcpcConstitution;
        		console.log($scope.bcpcConstitution);
        		$http.post('saveBCPCConstitution',$scope.bcpcConstitution).
           		then(function(result){
           			if(result.status == 200){
           				$('#constitutionBCPCModal').modal('show');
           			}
           		},function(error){
           			console.log(error);
           		});
        	};
        	
        	  //====================================download pdf============================
            $scope.printConstitutionBCPCSubmitData = function(){
            	$(".loader").css("display", "block");
            	if($scope.lang==='en'){
            		$scope.printData.blockName=($filter('filter')($scope.blockList, {id: $scope.printData.blockId}))[0].name;
                	$scope.printData.blockChairmanSex.name = $scope.printData.blockChairmanSex.id==null?null:($scope.printData.blockChairmanSex.id==1?"Male":$scope.printData.blockChairmanSex.id==2?"Female":"Third gender");
                	$scope.printData.bdoSex.name = $scope.printData.bdoSex.id==null?null:($scope.printData.bdoSex.id==1?"Male":$scope.printData.bdoSex.id==2?"Female":"Third gender");
                	$scope.printData.dcpuMemberSex.name = $scope.printData.dcpuMemberSex.id==null?null:($scope.printData.dcpuMemberSex.id==1?"Male":$scope.printData.dcpuMemberSex.id==2?"Female":"Third gender");
                	$scope.printData.icdsFunctionarySex.name = $scope.printData.icdsFunctionarySex.id==null?null:($scope.printData.icdsFunctionarySex.id==1?"Male":$scope.printData.icdsFunctionarySex.id==2?"Female":"Third gender");
                	$scope.printData.beoSex.name = $scope.printData.beoSex.id==null?null:($scope.printData.beoSex.id==1?"Male":$scope.printData.beoSex.id==2?"Female":"Third gender");
                	$scope.printData.oichcSex.name = $scope.printData.oichcSex.id==null?null:($scope.printData.oichcSex.id==1?"Male":$scope.printData.oichcSex.id==2?"Female":"Third gender");
                	$scope.printData.vlgLvlCPCSex.name = $scope.printData.vlgLvlCPCSex.id==null?null:($scope.printData.vlgLvlCPCSex.id==1?"Male":$scope.printData.vlgLvlCPCSex.id==2?"Female":"Third gender");
                	$scope.printData.csmSex.name = $scope.printData.csmSex.id==null?null:($scope.printData.csmSex.id==1?"Male":$scope.printData.csmSex.id==2?"Female":"Third gender");
                	$scope.printData.cmSex.name = $scope.printData.cmSex.id==null?null:($scope.printData.cmSex.id==1?"Male":$scope.printData.cmSex.id==2?"Female":"Third gender");
                	
            	}else{
            		$scope.printData.blockName=($filter('filter')($scope.blockList, {id: $scope.printData.blockId}))[0].name;
                	$scope.printData.blockChairmanSex.name = $scope.printData.blockChairmanSex.id==null?null:($scope.printData.blockChairmanSex.id==1?"पुरुष":$scope.printData.blockChairmanSex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.bdoSex.name = $scope.printData.bdoSex.id==null?null:($scope.printData.bdoSex.id==1?"पुरुष":$scope.printData.bdoSex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.dcpuMemberSex.name = $scope.printData.dcpuMemberSex.id==null?null:($scope.printData.dcpuMemberSex.id==1?"पुरुष":$scope.printData.dcpuMemberSex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.icdsFunctionarySex.name = $scope.printData.icdsFunctionarySex.id==null?null:($scope.printData.icdsFunctionarySex.id==1?"पुरुष":$scope.printData.icdsFunctionarySex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.beoSex.name = $scope.printData.beoSex.id==null?null:($scope.printData.beoSex.id==1?"पुरुष":$scope.printData.beoSex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.oichcSex.name = $scope.printData.oichcSex.id==null?null:($scope.printData.oichcSex.id==1?"पुरुष":$scope.printData.oichcSex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.vlgLvlCPCSex.name = $scope.printData.vlgLvlCPCSex.id==null?null:($scope.printData.vlgLvlCPCSex.id==1?"पुरुष":$scope.printData.vlgLvlCPCSex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.csmSex.name = $scope.printData.csmSex.id==null?null:($scope.printData.csmSex.id==1?"पुरुष":$scope.printData.csmSex.id==2?"महिला":"तीसरा लिंग");
                	$scope.printData.cmSex.name = $scope.printData.cmSex.id==null?null:($scope.printData.cmSex.id==1?"पुरुष":$scope.printData.cmSex.id==2?"महिला":"तीसरा लिंग");
                	
            	}
        		
            		var serverURL = 'downloadPDFDataConstitutionOfBCPC?type='+$scope.lang;
            		commonService.downloadFile(serverURL, $scope.printData);
            		$scope.bcpcConstitution = {};
        			$(".loader").css("display", "none");
            };
		});
		
		myAppConstructor.controller('constitutionOfVCPCController', function ($scope, $http,commonService, $window) {
            
            $scope.vcpcConstitution = {};
        	commonService.getGridMenuItems()
        	.then(function (result){
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
        	
    	    $http.get('getTypeDetails').
    	    then(function(result){
    		    $scope.sex = result.data.genderList;
    		    console.log($scope.sex);
    	    },function(error){
    		    console.log(error);
    	    });
    	    
    	    $("#vcpc_datepicker").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d',
        		onSelect: function(date) {
        		    $scope.vcpcConstitution.constitutionDate = date;
        		  }	
        	});
    	    
    	    $scope.validateName = function(name, errorId){
        		if( name == undefined  ){
        			if(errorId=='vlgHeadManPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.vlgHeadMancontactNumber.focus();
        	            return false;
        		    }else if(errorId=='anmPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.anmContactNumber.focus();
        	            return false;
        		    }else if(errorId=='awPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.awContactNumber.focus();
        	            return false;
        		    }else if(errorId=='stPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.stContactNumber.focus();
        	            return false;
        		    }else if(errorId=='dcpuPhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.dcpuContactNumber.focus();
        	            return false;
        		    }else if(errorId=='cr1PhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.cr1ContactNumber.focus();
        	            return false;
        		    }else if(errorId=='cr2PhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.cr2ContactNumber.focus();
        	            return false;
        		    }else if(errorId=='vm1PhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.vm1ContactNumber.focus();
        	            return false;
        		    }else if(errorId=='vm2PhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.vm2contactNumber.focus();
        	            return false;
        		    }else if(errorId=='vm3PhoneNoError') {
                		document.getElementById(errorId).innerHTML = "Please enter <b>10</b> digit phone number";
        		        document.constitutionVCPC.vm3contactNumber.focus();
        	            return false;
        		    }else if(errorId=='emailheadmanError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.vlgHeadManEmail.focus();
        		    }else if(errorId=='emailanmError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.anmEmail.focus();
        		    }else if(errorId=='emailawError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.awEmail.focus();
        		    }else if(errorId=='emailstError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.schoolTeacherEmail.focus();
        		    }else if(errorId=='emailvcpcmemberError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.dcpuMemberEmail.focus();
        		    }else if(errorId=='emailcrOneError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.crOneEmail.focus();
        		    }else if(errorId=='emailcrTwoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.crTwoEmail.focus();
        		    }else if(errorId=='emailvmOneError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.vlgMemberOneEmail.focus();
        		    }else if(errorId=='emailvmtwoError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.vlgMemberTwoEmail.focus();
        		    }else if(errorId=='emailvmthreeError'){
        		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
        		        document.constitutionVCPC.vlgMemberThreeEmail.focus();
        		    }
        		}else{
        			document.getElementById(errorId).innerHTML = "";
        	        return true;
        		}
        	};
        	
        	$scope.validateVCPCForm = function(){
        		var myArray = [{email:$scope.vcpcConstitution.vlgHeadManEmail,errorName:'emailheadmanError'},
	         		           {email:$scope.vcpcConstitution.anmEmail,errorName:'emailanmError'},
	         			       {email:$scope.vcpcConstitution.awEmail,errorName:'emailawError'},
	         			       {email:$scope.vcpcConstitution.schoolTeacherEmail,errorName:'emailstError'},
	         			       {email:$scope.vcpcConstitution.dcpuMemberEmail,errorName:'emailvcpcmemberError'},
	         			       {email:$scope.vcpcConstitution.crOneEmail,errorName:'emailcrOneError'},
	         			       {email:$scope.vcpcConstitution.crTwoEmail,errorName:'emailcrTwoError'},
	         			       {email:$scope.vcpcConstitution.vlgMemberOneEmail,errorName:'emailvmOneError'},
	         			       {email:$scope.vcpcConstitution.vlgMemberTwoEmail,errorName:'emailvmtwoError'},
	         			       {email:$scope.vcpcConstitution.vlgMemberThreeEmail,errorName:'emailvmthreeError'}];

		     		             function checkDuplicateInObject(propertyName, inputArray) {
		     		               var seenDuplicate = "",
		     		                   testObject = {};
		     		               inputArray.map(function(item) {
		     		                 var itemPropertyName = item[propertyName]; 
		     		                 if (itemPropertyName in testObject) {
		     		                	 if(testObject[itemPropertyName].email!=undefined){
		     		                	   testObject[itemPropertyName].duplicate = true;
		           		                   item.duplicate = true;
		           		                   seenDuplicate = testObject[itemPropertyName];
		     		                	 }else{
		     		                	   testObject[itemPropertyName] = item;
		           		                   delete item.duplicate; 
		     		                	 }
		     		                 }
		     		                 else {
		     		                   testObject[itemPropertyName] = item;
		     		                   delete item.duplicate;
		     		                 }
		     		               });
		     		               return seenDuplicate;
		     		             }

                 var filteredEmail= checkDuplicateInObject('email', myArray);
	                if (filteredEmail!="") {
         		        document.getElementById(filteredEmail.errorName).innerHTML = "Email id should not match with other email id";
         		   	    $('html,body').animate({
      			        scrollTop: $("#"+filteredEmail.errorName).offset().top - 250},'slow');
         		        return false;
         		    }
        		if($scope.vcpcConstitution.constitutionDate == undefined){
        			$("#vcpc_datepicker").datepicker("show");
        		}else{
        			$('#confirmationModalForConstitutionVCPC').modal('show');
        		}	
        	};
        	
        	$scope.reLoad = function(){
        		$window.location.reload();
        	};
        	
        	$scope.saveVCPCConstitution = function(){
        		console.log($scope.vcpcConstitution);
        		$http.post('saveVCPCConstitution',$scope.vcpcConstitution).
           		then(function(result){
           			if(result.status == 200){
           				$('#constitutionVCPCModal').modal('show');
           			}
           		},function(error){
           			console.log(error);
           		});
        	};
		}); 
		
		myAppConstructor.controller("HRDetailsController", function($scope,$http,$timeout,$window,commonService,$rootScope){
			$scope.dcpuhrDetailsDisable = false;
			$scope.dcpuhrDetailsSubmitDisable = false;
			$scope.dcpuhrdetailsFormInfo = {};
			$http.get('getDCPUHRDetails').
		    then(function(result){
		    	if(result.data.id==undefined){
		    		$scope.dcpuhrdetailsFormInfo = null;
		    		$scope.dcpuhrDetailsDisable = true;
		    		$scope.dcpuhrDetailsSubmitDisable = true;
		    	}
		    	else{
		    		$("#hr-dcpu select, #hr-dcpu input").attr("disabled", "disabled");
		    		$scope.dcpuhrDetailsDisable = true;
		    		$scope.dcpuhrDetailsSubmitDisable = false;
		    		$scope.dcpuhrdetailsFormInfo = result.data;
		    		$scope.printHRDetailsData = $scope.dcpuhrdetailsFormInfo;
		    	}
		    	
		   }, function(error){
			   console.log(error);
		   });
			
			$scope.validate=function(){
				$('#HRDetailsconfirmationModal').modal('show');
			};
			
			$scope.validateName = function(name, errorId){
				if(name == undefined){
					if(errorId=='emaildpoEmail'){
						document.getElementById('emaildpoEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#emaildpo").focus();
				    }else if(errorId=='emailpoicEmail'){
						document.getElementById('emailpoicEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#poicEmail").focus();
				    }else if(errorId=='emailponicEmail'){
						document.getElementById('emailponicEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#ponicEmail").focus();
				    }else if(errorId=='emaillcpoavilEmail'){
						document.getElementById('emaillcpoavilEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#lcpoavilEmail").focus();
				    }else if(errorId=='emailcounsellorEmail'){
						document.getElementById('emailcounsellorEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#counsellorEmail").focus();
				    }else if(errorId=='emailsw1Email'){
						document.getElementById('emailsw1Email').innerHTML = "Please provide correct email id";
				    	angular.element("#sw1Email").focus();
				    }else if(errorId=='emailsw2Email'){
						document.getElementById('emailsw2Email').innerHTML = "Please provide correct email id";
				    	angular.element("#sw2Email").focus();
				    }else if(errorId=='emailaccountantEmail'){
						document.getElementById('emailaccountantEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#accountantEmail").focus();
				    }else if(errorId=='emaildaEmail'){
						document.getElementById('emaildaEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#daemail").focus();
				    }else if(errorId=='emailacdeoEmail'){
						document.getElementById('emailacdeoEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#acdeoEmail").focus();
				    }else if(errorId=='emailorw1Email'){
						document.getElementById('emailorw1Email').innerHTML = "Please provide correct email id";
				    	angular.element("#orw1Email").focus();
				    }else if(errorId=='emailorw2Email'){
						document.getElementById('emailorw2Email').innerHTML = "Please provide correct email id";
				    	angular.element("#orw2Email").focus();
				    }else if(errorId=='emailorw3Email'){
						document.getElementById('emailorw3Email').innerHTML = "Please provide correct email id";
				    	angular.element("#orw3Email").focus();
				    }else if(errorId=='emailorw4Email'){
						document.getElementById('emailorw4Email').innerHTML = "Please provide correct email id";
				    	angular.element("#orw4Email").focus();
				    }else if(errorId=='emailorw5Email'){
						document.getElementById('emailorw5Email').innerHTML = "Please provide correct email id";
				    	angular.element("#orw5Email").focus();
				    }else if(errorId=='emailotherStaffEmail'){
						document.getElementById('emailotherStaffEmail').innerHTML = "Please provide correct email id";
				    	angular.element("#otherStaffEmail").focus();
				    }
				    
				}else{
					document.getElementById(errorId).innerHTML = "";
			        return true;
				}
			};
			$scope.printHRDetailsData = {};
			$scope.saveDCPUHRDetails = function (){
				$scope.printHRDetailsData = $scope.dcpuhrdetailsFormInfo;
				
				$http.post('saveDCPUHRDetails', $scope.dcpuhrdetailsFormInfo).
				  then(function(response){
					  console.log(response);
//					  $scope.dcpuhrdetailsFormInfo={};
					  $('#alertModal1').modal('show');
				  },function(error){
				    console.log(error);
				  });
			 };
			 
			//====================================download pdf HumanResourceDetailsHRCCI============================
		     $scope.printDCPUHumanResourceDetailsSubmitData = function(){
		     	$(".loader").css("display", "block");
		     	if($scope.lang==='en'){
		     		$scope.printHRDetailsData.accountantSexName = $scope.printHRDetailsData.accountantSex == null ? null : ($scope.printHRDetailsData.accountantSex == 1?"Male":($scope.printHRDetailsData.accountantSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.acdeoSexName = $scope.printHRDetailsData.acdeoSex == null ? null: ($scope.printHRDetailsData.acdeoSex == 1?"Male":($scope.printHRDetailsData.acdeoSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.counsellorSexName = $scope.printHRDetailsData.counsellorSex == null ? null : ($scope.printHRDetailsData.counsellorSex == 1?"Male":($scope.printHRDetailsData.counsellorSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.daSexName = $scope.printHRDetailsData.daSex == null ? null : ($scope.printHRDetailsData.daSex == 1?"Male":($scope.printHRDetailsData.daSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.dpoSexName = $scope.printHRDetailsData.dpoSex == null ? null : ($scope.printHRDetailsData.dpoSex == 1?"Male":($scope.printHRDetailsData.dpoSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.lcpoavilSexName = $scope.printHRDetailsData.lcpoavilSex == null ? null : ($scope.printHRDetailsData.lcpoavilSex == 1?"Male":($scope.printHRDetailsData.lcpoavilSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.orw1SexName = $scope.printHRDetailsData.orw1Sex == null ? null : ($scope.printHRDetailsData.orw1Sex == 1?"Male":($scope.printHRDetailsData.orw1Sex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.orw2SexName = $scope.printHRDetailsData.orw2Sex == null ? null : ($scope.printHRDetailsData.orw2Sex == 1?"Male":($scope.printHRDetailsData.orw2Sex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.orw3SexName = $scope.printHRDetailsData.orw3Sex == null ? null : ($scope.printHRDetailsData.orw3Sex == 1?"Male":($scope.printHRDetailsData.orw3Sex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.orw4SexName = $scope.printHRDetailsData.orw4Sex == null ? null : ($scope.printHRDetailsData.orw4Sex == 1?"Male":($scope.printHRDetailsData.orw4Sex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.orw5SexName = $scope.printHRDetailsData.orw5Sex == null ? null : ($scope.printHRDetailsData.orw5Sex == 1?"Male":($scope.printHRDetailsData.orw5Sex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.otherStaffSexName = $scope.printHRDetailsData.otherStaffSex == null ? null : ($scope.printHRDetailsData.otherStaffSex == 1?"Male":($scope.printHRDetailsData.otherStaffSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.poicSexName = $scope.printHRDetailsData.poicSex == null ? null : ($scope.printHRDetailsData.poicSex == 1?"Male":($scope.printHRDetailsData.poicSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.ponicSexName = $scope.printHRDetailsData.ponicSex == null ? null : ($scope.printHRDetailsData.ponicSex == 1?"Male":($scope.printHRDetailsData.ponicSex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.sw1SexName = $scope.printHRDetailsData.sw1Sex == null ? null : ($scope.printHRDetailsData.sw1Sex == 1?"Male":($scope.printHRDetailsData.sw1Sex == 2?"Female":"Third gender"));
			     	$scope.printHRDetailsData.sw2SexName = $scope.printHRDetailsData.sw2Sex == null ? null : ($scope.printHRDetailsData.sw2Sex == 1?"Male":($scope.printHRDetailsData.sw2Sex == 2?"Female":"Third gender"));
		     	}else{
		     		$scope.printHRDetailsData.accountantSexName = $scope.printHRDetailsData.accountantSex == null ? null : ($scope.printHRDetailsData.accountantSex == 1?"पुरुष":($scope.printHRDetailsData.accountantSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.acdeoSexName = $scope.printHRDetailsData.acdeoSex == null ? null: ($scope.printHRDetailsData.acdeoSex == 1?"पुरुष":($scope.printHRDetailsData.acdeoSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.counsellorSexName = $scope.printHRDetailsData.counsellorSex == null ? null : ($scope.printHRDetailsData.counsellorSex == 1?"पुरुष":($scope.printHRDetailsData.counsellorSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.daSexName = $scope.printHRDetailsData.daSex == null ? null : ($scope.printHRDetailsData.daSex == 1?"पुरुष":($scope.printHRDetailsData.daSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.dpoSexName = $scope.printHRDetailsData.dpoSex == null ? null : ($scope.printHRDetailsData.dpoSex == 1?"Male":($scope.printHRDetailsData.dpoSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.lcpoavilSexName = $scope.printHRDetailsData.lcpoavilSex == null ? null : ($scope.printHRDetailsData.lcpoavilSex == 1?"पुरुष":($scope.printHRDetailsData.lcpoavilSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.orw1SexName = $scope.printHRDetailsData.orw1Sex == null ? null : ($scope.printHRDetailsData.orw1Sex == 1?"पुरुष":($scope.printHRDetailsData.orw1Sex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.orw2SexName = $scope.printHRDetailsData.orw2Sex == null ? null : ($scope.printHRDetailsData.orw2Sex == 1?"पुरुष":($scope.printHRDetailsData.orw2Sex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.orw3SexName = $scope.printHRDetailsData.orw3Sex == null ? null : ($scope.printHRDetailsData.orw3Sex == 1?"पुरुष":($scope.printHRDetailsData.orw3Sex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.orw4SexName = $scope.printHRDetailsData.orw4Sex == null ? null : ($scope.printHRDetailsData.orw4Sex == 1?"पुरुष":($scope.printHRDetailsData.orw4Sex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.orw5SexName = $scope.printHRDetailsData.orw5Sex == null ? null : ($scope.printHRDetailsData.orw5Sex == 1?"पुरुष":($scope.printHRDetailsData.orw5Sex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.otherStaffSexName = $scope.printHRDetailsData.otherStaffSex == null ? null : ($scope.printHRDetailsData.otherStaffSex == 1?"पुरुष":($scope.printHRDetailsData.otherStaffSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.poicSexName = $scope.printHRDetailsData.poicSex == null ? null : ($scope.printHRDetailsData.poicSex == 1?"पुरुष":($scope.printHRDetailsData.poicSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.ponicSexName = $scope.printHRDetailsData.ponicSex == null ? null : ($scope.printHRDetailsData.ponicSex == 1?"पुरुष":($scope.printHRDetailsData.ponicSex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.sw1SexName = $scope.printHRDetailsData.sw1Sex == null ? null : ($scope.printHRDetailsData.sw1Sex == 1?"पुरुष":($scope.printHRDetailsData.sw1Sex == 2?"महिला":"तीसरा लिंग"));
			     	$scope.printHRDetailsData.sw2SexName = $scope.printHRDetailsData.sw2Sex == null ? null : ($scope.printHRDetailsData.sw2Sex == 1?"पुरुष":($scope.printHRDetailsData.sw2Sex == 2?"महिला":"तीसरा लिंग"));
		     	}
		     	
		     		var serverURL = 'downloadPDFDataForDCPUHRDetails?type='+$scope.lang;
		     		commonService.downloadFile(serverURL, $scope.printHRDetailsData);
		 			$(".loader").css("display", "none");
		     };
			 
			 $scope.reDirectPage = function(){
				 $window.location.reload();
			 };
			 
			 $scope.editField = function(){
				 $scope.dcpuhrDetailsDisable=false;
				 $scope.dcpuhrDetailsSubmitDisable = true;
				 $("#hr-dcpu select, #hr-dcpu input").removeAttr("disabled");
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
						if (angular.isUndefined(val) || val === null) {
							val = '';
						}
						
						var clean = val.toString().replace(/[^0-9]/g, '');
						if(!angular.isUndefined(clean)) {
							var num=0;
							if(clean.length> 2 ){
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
