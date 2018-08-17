myAppConstructor.controller("roleManagementController", function($scope,$http,gettextCatalog){
	$scope.changeLanguage = function(lang){
		console.log(lang)
		   gettextCatalog.setCurrentLanguage(lang);
	}
	$scope.formData = {};
	$scope.validatePassword = function(confirmPassword){
		if($scope.formData.password != undefined){
			if($scope.formData.password != confirmPassword){
				document.getElementById('passwordError').innerHTML = "Password do not match";
			}else{
				document.getElementById('passwordError').innerHTML = "";
			}
		}
		
		
	};
	
});