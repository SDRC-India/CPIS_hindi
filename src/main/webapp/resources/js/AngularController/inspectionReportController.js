myAppConstructor.controller("inspectionReportController", function($scope,$http,gettextCatalog){
	
	$scope.changeLanguage = function(lang){
		console.log(lang)
		   gettextCatalog.setCurrentLanguage(lang);
	}
	
});
