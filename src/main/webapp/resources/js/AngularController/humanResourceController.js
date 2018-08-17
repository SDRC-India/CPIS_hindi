myAppConstructor.controller("HumanResourceController", function($scope,$http,gettextCatalog){
	$scope.changeLanguage = function(lang){
		console.log(lang)
		   gettextCatalog.setCurrentLanguage(lang);
	}
	
	$scope.humanResource = 'CCI';

	
	$scope.humanResource = 'CCI';
	$scope.cciList=[];
	
	
	 $scope.saveAfterCare = function (){
		  $http.post('saveCCIHumanResource',$scope.formInfo).
		  then(function(response){/*
			  $('#childIdModal').modal('show');
			  $scope.childId=response.data;
			  $scope.disableDDNumber=false; 
			  $scope.disableGDNumber=false;
		  */},function(error){
		    console.log(error);
		  });
		 
	 };
	

});