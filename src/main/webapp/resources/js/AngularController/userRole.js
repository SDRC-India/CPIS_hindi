/**
 * 
 */
myAppConstructor.controller('UserRoleController', function($scope, $location, $http, commonService, $rootScope,$window){
	
	$scope.selectedChildId=$('#modelValue').val();
	console.log($scope.selectedChildId);
	$scope.selectedChild = {};
	 $(".loader").css("display", "block");
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.menuList=result;
		console.log($scope.menuList);
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
	$scope.openDeleteModal = function(userId){
		$scope.userId=userId;
		$('#deleteModal').modal('show');
	};
	$scope.changepw = function(email){
		$window.location = 'adminChangePassword?email='+email;
	};
	$scope.deleteUser = function(){
		$http.post("deleteUser?userId="+$scope.userId).
	    then(function(result){
	    	if(result.data=="success"){
	    		$('#deleteSuccessModal').modal('show');
	    	}
	  	  console.log(result);
	    });
	};
	$scope.reloadPage = function(){
		 $window.location.reload();
	}
	/*
	 * Pagination code started here @Author - Sourav ke nath
	 * */
    $scope.currentPage = 1;
    $scope.pageSize = 10;

    $scope.pageChangeHandler = function(num) {
       console.log('Pagination call ' + num);
    };
   
	$http.get("getLoginData").
    then(function(result){
  	  $scope.areaList=result.data.value.areaDetails;
  	  $scope.designationList=result.data.value.designation;
  	  $scope.userList=result.data.value.userList;
  	$(".loader").css("display", "none");
    });
	
	$http.get("getAllCciList").
	then(function(result){
		$scope.cciList = result.data;
	},function(error){
		console.log(error);
	});
	
	$scope.checkCCI = function(){
		$scope.areaCciList = [];
		angular.forEach($scope.cciList, function(value, key){
			if(value.key == $scope.newUser.areaId){
				$scope.areaCciList.push(value);
			}
		});
	}
	
	$scope.checkUsername = function(){
		var match=false;
		angular.forEach($scope.userList, function(value, key){
			if($scope.newUser.userName==value.userName){
//				$scope.usernameError=true;
				match=true;
				document.getElementById('usernameError').innerHTML="Username already exists";
				angular.element("#userName").focus();
//				alert("Username exists.");
			}
		});
		if(match==false){
			document.getElementById('usernameError').innerHTML="";
		}
	};
	$scope.emailExists=false;
	$scope.checkEmail = function(){
		var match=false;
		angular.forEach($scope.userList, function(value, key){
			if($scope.newUser.email.toLowerCase()==value.email.toLowerCase()){
				match=true;
				$scope.emailExists = match;
				document.getElementById('mgmtEmailError').innerHTML="Email already registered";
				angular.element("#roleemail").focus();
//				alert("Username exists.");
			}
//			else{
//				document.getElementById('mgmtEmailError').innerHTML="";
//			}
		});
		if(match==false){
			document.getElementById('mgmtEmailError').innerHTML="";
		}
		
	};
	
	 $scope.getArealist= function (){
		  $scope.selectedArea=null;
		  $scope.filteredUserList =[];
		  $scope.selectedUser=null;
		  $scope.selectedAreaList=[];
		  angular.forEach($scope.designationList, function(value, key) {
			  if(value.designationId==$scope.newUser.designationId){
				  $scope.selectedDesignation=value;
			  }
		  });
		  
		  $scope.selectedAreaLevel=$scope.selectedDesignation.designationAreaLevel;
		  
		  angular.forEach($scope.areaList, function(value, key) {
			  
			  if (value.areaLevel==$scope.selectedAreaLevel) {
				  
				  $scope.selectedAreaList.push(value);
			  }
			  
		  });
	  }
	 
	
	$scope.saveData = function(){
		if($scope.newUser.password === undefined)
			document.getElementById('password').focus();
		else if($scope.newUser.designationId==10 && $scope.newUser.cciId == null || $scope.newUser.cciId == "")
			angular.element("#cci").focus();
		else if($scope.newUser.password != $scope.confirmPassword)
			angular.element("#password_c").focus();
		else if($scope.emailError==true)
			angular.element("#roleemail").focus();
		else if($scope.usernameError==true)
			angular.element("#userName").focus();
		else if($scope.newUser.email=="" || $scope.newUser.email==undefined || $scope.emailExists==true)
			angular.element("#roleemail").focus();
		else{
			$(".loader").css("display", "block");
			$http.post('saveNewUser', $scope.newUser).
			then(function(result){
				console.log(result);
				$( "#newUserModal" ).modal("show");
				$scope.newUser={};
				$scope.confirmPassword = null;
				$(".loader").css("display", "none");
//				$('#password_c').val(''); 
			},function(error){
				console.log(error);
			});
		}
		console.log($scope.newUser);
	};
	
	$scope.validateForm = function(){
		console.log($scope.newUser);
	};
	
	$scope.newUser = {};
	$scope.validatePassword = function(confirmPassword){
		if($scope.newUser.password != undefined){
			if($scope.newUser.password != confirmPassword){
				document.getElementById('passwordError').innerHTML = "Password does not match";
			}else{
				document.getElementById('passwordError').innerHTML = "";
			}
		}
		
		
	};
	
	$scope.validateEmail = function(name, errorId){
		if( name == undefined  ){
			
			 if(errorId=='mgmtEmailError'){
		    	document.getElementById(errorId).innerHTML = "Please provide correct email id";
		        document.getElementById('roleemail').focus();
		        return false;
		    }
		}else{
			document.getElementById(errorId).innerHTML = "";
			return true;
		}
	};
	
//	$scope.openChangePw = function(){
//		$window.location.href = 'changePassword';
//	};
});

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