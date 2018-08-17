   var app = angular.module('cctsLogin', []);
         app.controller('LoginController', function($scope, $http,$window) {
        	 
        	 $scope.selectedAreaLevel="";
        	 $scope.selectedDesignation=null;
        	 $scope.selectedUser=null;
        	 $scope.selectedArea=null;
        	 $scope.password="";
        	 $(".loader").css("display", "block");
        	  $http.get("getLoginData").
              then(function(result){
            	  $(".loader").css("display", "none");
            	  $scope.areaList=result.data.value.areaDetails;
            	  $scope.designationList=result.data.value.designation;
            	  $scope.userList=result.data.value.userList;
            	  
            	  console.log(result);
              });
        	  	var match=false;
        		$scope.checkEmail = function(){
        			angular.forEach($scope.userList, function(value, key){
        				if($scope.email.toLowerCase()==value.email.toLowerCase()){
        					match=true;
        					document.getElementById('emailError').innerHTML="";
        				}
        			});
        			if(match==false){
        				document.getElementById('emailError').innerHTML="Email not registered";
        				angular.element("#roleemail").focus();
        			}
        			
        		};
        	  $scope.loginStatus=$('#loginStatus').val();
        	 $scope.userId=$('#userId').val();
        	 
        	 if($scope.loginStatus=="[User Logged In]"){
        		 $('#logoutModal').modal('show');
        	 }
        	 $scope.logoutUser = function(){
        		 $http.post("logoutUser?userId="+$scope.userId).
                 then(function(result){
               	  console.log(result);
                 });
        	 };
        	  $scope.designationObj={};
        	  $scope.filteredUserList=[];
        	  
        	  $scope.forgotPassword = function (){
        		  if( $scope.email!=null && match==true){
        		  $(".loader").css("display", "block");
        		  $http.post("forgotPassword?email="+$scope.email).
                  then(function(result){
                	  $(".loader").css("display", "none");
                	  $( "#forgetPasswordModal" ).modal("show");
                	  console.log(result);
                  });
        		  }
        	  };
        	  
        	  $scope.getArealist= function (){
        		  $scope.selectedArea=null;
        		  $scope.filteredUserList =[];
        		  $scope.selectedUser=null;
        		  $scope.selectedAreaList=[];
        		  $scope.selectedAreaLevel=$scope.selectedDesignation.designationAreaLevel;
        		  
        		  angular.forEach($scope.areaList, function(value, key) {
        			  
        			  if (value.areaLevel==$scope.selectedAreaLevel) {
        				  
        				  $scope.selectedAreaList.push(value);
        			  }
        			  
        		  });
        	  };
        	  
        	  $scope.getUserlist = function(){
        		  $scope.filteredUserList =[];
        		  
        		  angular.forEach($scope.userList, function(value, key) {
        			  
        		if (value.areaId==$scope.selectedArea.areaId && value.designationId==$scope.selectedDesignation.designationId)	{
        			
        			$scope.filteredUserList.push(value);
        		}  
        			  
        		  });
        			  		          	 
        	  };
        	  
        	 $scope.capsOn = false;
         	 $('[type=password]').keypress(function(e) {
         		 var s = String.fromCharCode(e.which);
         		 if ( s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey ) {
         			 $scope.capsOn = true;
     			  } else {
     				  $scope.capsOn = false;
     			  }
         	 });
        	
        	  $scope.redirect=function(){
        		  $window.location.href = '/CPIS/ccts_login';
        	  }
//        	  $scope.login = function (){
//        		  
//       		  var userObject = {userName : $scope.selectedUser,
//       				  password  :$scope.password};
//        		  
//        		  $http.post('checkCredentials',userObject)
//        		  $http ({
//        			  
//        			  method:'POST', 
//        			  url:'checkCredentials', 
//        			  data : {'username': $scope.selectedUser.userName, 'password':$scope.password }
//        			  
//        		  })       		  
//        		  .then(function(result){
//        			  
//        			  
//        			  console.log(result);
//        		  }) 
//        		  
//        		  .catch (function(error){
//        			  
//        			  console.log(error);
//        			  
//        		  });		  
//        		  
//        	  }
        	  
          });