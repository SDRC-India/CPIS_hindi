/**
 * 
 */
myAppConstructor.controller('RestorationOrderController', function($scope,$http,commonService,$rootScope,$location,gettextCatalog){
	$scope.changeLanguage = function(lang){
		console.log(lang)
		   gettextCatalog.setCurrentLanguage(lang);
	}
	$scope.selectedChildId=$('#modelValue').val();
	$scope.cwcName=$('#cwcName').val();
	$scope.district=$('#district').val();
	
	console.log($scope.cwcName+" "+$scope.district);
	
	$scope.selectedChild = {};
	$scope.restorationselectedChild= {};
	
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});
	
	$http.get("getDistrictList?areaLevel=4").
	then(function(result){
	$scope.districtList = result.data;
		$rootScope.$broadcast("districtList", result.data);
		console.log($scope.districtList);
	},function(error){
		console.log(error);
	});
	$rootScope.$on("districtList", function(event, data){
		$scope.districtList = data;
		console.log($scope.districtList);
	});
	
	$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
	then(function(result){
		$scope.selectedChild = result.data;
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
		console.log($scope.selectedChild);
	},function(error){
		console.log(error);
	});
	
	$http.get("getRestorationData?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.restorationselectedChild = result.data;
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
		console.log($scope.restorationselectedChild);
	},function(error){
		console.log(error);
	});
	
	
	$http.get("getTypeDetails").
	then(function(result){
		$rootScope.$broadcast("typeDetails",result);
	},function(error){
		console.log(error);
	});
	
	$rootScope.$on("typeDetails", function(event, data){
		var result = data;
		$scope.placedOrder = result.data.placedOrder;
	});
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.prefetchData = data;
	});
	
	$scope.validateRestorationOrder = function(){
			$('#restorationModal').modal('show');
	};
	
	$scope.saveRestorationData = function(){
		console.log($scope.restorationselectedChild);
		$scope.restorationselectedChild.childId=$scope.prefetchData.childId;
		$http.post('saveRestorationData',$scope.restorationselectedChild).
		then(function(result){
			console.log(result);
			$scope.restorationData={};
		}, function(error){
			console.log(error);
		});
	};
});