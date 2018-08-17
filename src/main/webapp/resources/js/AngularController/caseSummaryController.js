
//var app = angular.module('FinalOrder',[]);
	
myAppConstructor.controller('CaseSummaryController', function($scope,$http,commonService,$rootScope,$location, $timeout,gettextCatalog){
	$scope.changeLanguage = function(lang){
		console.log(lang)
		   gettextCatalog.setCurrentLanguage(lang);
	}
	$scope.otherFieldFlag = false;
	$scope.formInfo = {};
	$scope.trueValue = true;
	$scope.falseValue = false;
	$scope.ordersByCWC = [];
	
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});

	var ids = "";
	$scope.selectedChildId=$('#modelValue').val();
	
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.menuList=result;
		console.log($scope.menuList);
	});
	
	$rootScope.$on("selectedChild", function(event, data){
		$scope.selectedChild = data;
		console.log($scope.selectedChild);
	});
	
	/*$http.get("getChildById?selectedChildId="+$scope.selectedChildId).
	then(function(result){
		$scope.selectedChild = result.data;
		console.log(result);
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
	},function(error){
		console.log(error);
	});*/
	
	$scope.redirectForm=function(url){
		if(url == "child_registration"){
			commonService.redirectForm(url, $scope.selectedChild.childId);
		}else{
			if($scope.selectedChild.childId==null || $scope.selectedChild.childId == undefined 
				|| $scope.selectedChild.childId == ""){
				$('#noChildSelected').modal('show');
			}else{
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}
		}
	};
	
	$http.get('getTypeDetails').
	then(function(result){
		$scope.ordersByCWC = result.data.ordersByCWC;
		console.log(result);
	}, function(error){
		console.log(error);
	});
	
//	$scope.ordersByCWC = [{name:"Declaration that child is in need of care and protection", id:1, checked : false},
//	                      {name:"Finding on age of child", id:2, checked : false},
//	                      {name:"Medical Examination", id:3, checked : false},
//	                      {name:"Interim custody", id:4, checked : false},
//	                      {name:"Undertaking (by parent, guardian or fit person, if applicable)", id:5, checked : false},
//	                      {name:"Order appointing Case Worker & NGO etc.", id:6, checked : false},
//	                      {name:"Order for compensation/recovery of wages (if applicable)", id:7, checked : false},
//	                      {name:"Final Order (concluding inquiry)", id:8, checked : false},
//	                      {name:"Any other order", id:9, checked : false}];
	
	$scope.validateOrders = function(order){
		if(order.checked == true){
			if(order.id == 41)
				$scope.otherFieldFlag = true;
			
			for(var i=0; i<$scope.ordersByCWC.length; i++){
				if(order.id == $scope.ordersByCWC[i].id)
					$scope.ordersByCWC[i].checked = true;
			}
		}
		else{
			if(order.id == 41){
				$scope.otherFieldFlag = false;
				$scope.formInfo.ordersPassedByCwcOthers = null;
			}
			for(var i=0; i<$scope.ordersByCWC.length; i++){
				if(order.id == $scope.ordersByCWC[i].id)
					$scope.ordersByCWC[i].checked = false;
			}
		}
	};
	
	
	finalizeOrdersPassedByCWC = function(){
		ids = "";
		
		for(var i=0; i<$scope.ordersByCWC.length; i++){
			if($scope.ordersByCWC[i].checked == true){
				ids += $scope.ordersByCWC[i].id + ",";
			}
		}
		ids = ids.substring(0, ids.length - 1);
		$scope.formInfo.ordersPassedByCWC = ids;
	};
	
	$scope.validateCsForm = function(){
		
		finalizeOrdersPassedByCWC();
		
		$('#confirmationModalForCaseSummary').modal('show');
		console.log($scope.formInfo);
	};
	
	$scope.saveCSData = function(){
		$scope.printData = $scope.formInfo;
		$http.post('saveCaseSummaryCWCData',$scope.formInfo).
		then(function(result){
			console.log(result);
			$( "#childCaseSummaryModal" ).modal("show");
		}, function(error){
			console.log(error);
		});
	};
	//====================================download pdf============================
	$scope.printCaseSummarySubmitData = function(){
		
		var serverURL = 'downloadPDFDataReportForCaseSummary';
		
		$scope.printData.childName=$scope.selectedChild.childName;
		commonService.downloadFile(serverURL, $scope.printFitData);
		/*
    	$http.post(serverURL, $scope.printData).
		  then(function(response){
			  var fileName = {
						"fileName" : response.data
					};
			  commonService.download("downloadFile/", fileName, 'POST');
		  });*/
    };
    $scope.date="";
    $scope.interimOrders=[];
	$http.get("findAllInterimOrders?childId="+$scope.selectedChildId).
	then(function(result){
		
		$scope.interimOrders = result.data;
	for(var i=0;i<result.data.length;i++){
			if(i!=result.data.length-1){$scope.date += result.data[i].dateOfFormFilled+" , ";}
			else $scope.date += result.data[i].dateOfFormFilled;
		}
		
		//$scope.interimOrders.dateOfFormFilled
		console.log(result.data);
		$rootScope.$broadcast("interimOrders", $scope.interimOrders);
	},function(error){
		console.log(error);
	});
	
	
    
    $scope.formInfo.ordersPassedByCWC="";
	$http.get("getCaseSummary?childId="+$scope.selectedChildId).
	then(function(result){
		$scope.formInfo = result.data;
		$timeout(function(){
			var sss = $scope.formInfo.ordersPassedByCWC;
			$scope.ddd = sss.split(',');
			for(var i=0; i < $scope.ordersByCWC.length; i++){
				for(var j=0; j < $scope.ddd.length; j++){
				if(Number($scope.ordersByCWC[i].id) == $scope.ddd[j]){
				$scope.ordersByCWC[i].checked = true;
				
				}
				}
				}
		},500);
		
		$rootScope.$broadcast("selectedChild", $scope.selectedChild);
		console.log($scope.formInfo);
	},function(error){
		console.log(error);
	});

	
	
    
});
