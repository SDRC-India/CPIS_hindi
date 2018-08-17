/**
 *  Author- Biswabhusan Pradhan
 */
myAppConstructor
		.controller("NotificationController",
				function($rootScope, $scope, $http, $window, commonService,$interval) {
			commonService.getGridMenuItems()
			.then(function (result){
				$scope.getGridMenuItemsSuccessful = true;
				$scope.menuList=result;
				if($scope.menuList.length==0){
					$scope.showMenu=false;
				}
			});
			
			  $scope.redirectForm=function(url){
					if(url=="child_registration" || url=="ciclSocialBackgroundReport" || url=="reportSummary" || url=="constitutionofSociety"){
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
			$scope.currentPage = 1;
		    $scope.pageSize = 20;

		    $scope.pageChangeHandler = function(num) {
		       console.log('Pagination call ' + num);
		    };
		    
		    $scope.getNotificationCount = function(){
				    $http.post("getNotificationCount").then(
							function(response) {
								$scope.notificationCount=response.data;
							},
							function(error){
								console.log(error);
							});
		    };
				    
				    $interval(function() {
						$scope.getNotificationCount()
					},1000);
		    
				    
			$scope.getNotificationList = function(){	    
					$http.post("getNotificationList").then(
							function(response) {
								$scope.dataSet=response.data;
							},
							function(error){
								console.log(error);
							});
			};
			
			$interval(function() {
				$scope.getNotificationList()
			},1000);
					
			$scope.setIsRead = function(data){
				$http.post('setMarkAsRead?notificationId='+data.notificationId)
				.then(function(response){
					data.isRead=true;
					console.log("Mark as read set");
				});
			};
				});