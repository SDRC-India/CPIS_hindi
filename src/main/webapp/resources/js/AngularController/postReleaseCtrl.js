myAppConstructor.controller('postReleaseController', function($scope) {
			$scope.birthCertificate = true;

		});

myAppConstructor.controller('personalDetailsController', function($scope, $http,gettextCatalog) {
	$scope.changeLanguage = function(lang){
		console.log(lang)
		   gettextCatalog.setCurrentLanguage(lang);
	}

	$scope.saveData = function(){
		console.log($scope.personalDetail);
		$http.post('/CPIS/saveICPA', $scope.personalDetail).
		then(function(result){
			console.log(result);
		},function(error){
			console.log(error);
		});
	};
});

myAppConstructor.controller('individualcarePlanController', function($scope) {
	$scope.birthCertificate = true;

});