/**
 * Author- Biswabhusan Pradhan
 */
myAppConstructor
		.controller("HeaderController",
				function($rootScope, $scope, $http, $window, commonService,gettextCatalog) {
			$scope.changeLanguage = function(lang){
				console.log(lang)
				   gettextCatalog.setCurrentLanguage(lang);
			}
			
					$http.post("getNotificationCount?userId="+$('#userId').val()).then(
							function(response) {
								$scope.notificationCount=response.data;
							},
							function(error){
								console.log(error);
							});
				});