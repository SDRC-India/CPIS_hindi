myAppConstructor.controller('CCTSController', CCTSController)
function CCTSController($scope, $http, commonService, $location, $window,$interval,$timeout, gettextCatalog) {
	
	$scope.districts = [];
	$scope.sexFilterList = [];
	$scope.ageFilterList = [];
	$scope.casteFilterList = [];
	$scope.educationFilterList = [];
	$scope.tempDistrictList = [];
	$scope.selectedChild={};
	$scope.data={};
	$scope.radioChecked=false;
	$scope.showMenu=true;
	$http({
        method : "GET",
        url : "getLang"
    }).then(function mySucces(response) {
    	$scope.lang=response.data;
    	$scope.changeLanguage($scope.lang);
	});
	
	$scope.changeLanguage = function(lang){
		console.log(lang)
		$scope.lang=lang;
		   gettextCatalog.setCurrentLanguage(lang);
		   
		   $http({
		        method : "GET",
		        url : "setLangString?language="+$scope.lang
		    }).then(function mySucces(response) {
		    	
		});
	}	
	
	
/*	$http.post("getNotificationCount?userId="+$('#userId').val()).then(
			function(response) {
				$scope.notificationCount=response.data;
			},
			function(error){
				console.log(error);
			});*/
//	var districtFilterFlag = 0;
//	var ageFilterFlag = 0;
//	var sexFilterFlag = 0;
//	var educationFilterFlag = 0;
//	var tempChildList = [];
	
	$scope.dataSet = [];
	$scope.menuList=[];
//	window.onunload = function(){ 
//		 $http.get("logout").
//        then(function(result){
//      	  console.log(result);
//        });
//	 };
	commonService.getGridMenuItems()
	.then(function (result){
		$scope.getGridMenuItemsSuccessful = true;
		$scope.checkAllDataSuccessful();
		$scope.menuList=result;
		if($scope.menuList.length==0){
			$scope.showMenu=false;
		}
	});
	
	$scope.getNotificationCount=function(){
	$http.post("getNotificationCount").then(
			function(response) {
				$scope.notificationCount=response.data;
				if($scope.notificationCount==-1){
					$window.location.href = "ccts_login";
				}
			},
			function(error){
				console.log(error);
			});
	};
	
	$interval(function() {
		$scope.getNotificationCount()
	},1000);
	
	$(".loader").css("display", "block");
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.getFilterOptionsSuccessful){
			$timeout(function(){
				$(".loader").css("display", "none");
			},1000);
			
		}
	};
	/*
	 * Pagination code started here @Author - Sourav ke nath
	 * */
    $scope.currentPage = 1;
    $scope.pageSize = 10;

    $scope.pageChangeHandler = function(num) {
       console.log('Pagination call ' + num);
    };
	/*
	 * setRadio code started here @Author - Sourav ke nath
	 * */
    $scope.setRadio = function(value)
    {
      if ($scope.radioSelected != value.childId) {
        $scope.radioSelected = value.childId;
        $scope.selectedChild=value;
            $('#qnimate').animate({height: "300px"});
      }
      else {
        $scope.radioSelected = null;
            $('#qnimate').animate({height: "0px"});
      }
    };
	$scope.setSelectedChild=function(child){
		if($scope.radioSelected != null){
			$scope.selectedChild=child;
		}else{
			$scope.selectedChild={};
		}
		
	};
	$scope.redirectForm=function(url){
		if(url=="child_registration" || url=="ciclSocialBackgroundReport" || url=="reportSummary" || url=="constitutionofSociety"){
//			$window.location.href = '/CPIS/'+url;
			commonService.redirectForm(url, undefined);
		}else{
			if($scope.selectedChild.childId==null || $scope.selectedChild.childId == undefined 
				|| $scope.selectedChild.childId == ""){
				$('#noChildSelected').modal('show');
			}else if($scope.selectedChild.childId!=null && $scope.selectedChild.programType==1 && url=="childfostercare"){
				$('#ciclChild').modal('show');
			}else if($scope.selectedChild.childId!=null && $scope.selectedChild.programType==1 && url=="interim_order"){
				url="ciclinterimOrder";
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}else if($scope.selectedChild.childId!=null && url=="followUpForm" && $scope.selectedChild.programType==1 ){
				$('#ciclChild').modal('show');
			}else{
				if($scope.selectedChild.programType==1 && url=="socialInvestigation"){
					url="ciclSocialInvestigationReport";
				}
				commonService.redirectForm(url, $scope.selectedChild.childId);
			}
		}
	};
	
//	commonService.redirectForm()
//	.then(function (result){
//		$scope.menuList=result;
//	});
	
	$scope.checkAllDataSuccessful = function(){
		if($scope.getGridMenuItemsSuccessful && $scope.getUserWiseAreaListSuccessful && $scope.getFilterOptionsSuccessful ){
			$(".loader").css("display", "none");
		}
	};
	$scope.getGridMenuItemsSuccessful = false;
	$scope.getUserWiseAreaListSuccessful = false;
	$scope.getFilterOptionsSuccessful = false;
	 $(".loader").css("display", "block");
	
	
	$http.get("getChildList").
		then(function(result){
			$scope.getGridMenuItemsSuccessful = true;
			$scope.checkAllDataSuccessful();
			$scope.mstChildList = result.data;
			$scope.childList = result.data;
			$scope.dataSet = result.data;

			$http.get("getUserWiseAreaList").
			then(function(result){
				$scope.getUserWiseAreaListSuccessful = true;
				$scope.checkAllDataSuccessful();
				$scope.districts = result.data;
				
				for(var i=0; i<=5; i++){
					$scope.tempDistrictList.push($scope.districts[i]);
				}
				console.log(result);
				$http.get("getFilterOptions").
				then(function(result){
					$scope.getFilterOptionsSuccessful = true;
					$scope.checkAllDataSuccessful();
					for(var i=0; i<result.data.length; i++){
						if(result.data[i].typeId == 25)
							$scope.sexFilterList.push(result.data[i]);
						else if(result.data[i].typeId == 3)
							$scope.ageFilterList.push(result.data[i]);
//						else if(result.data[i].typeId == 4)
//							$scope.casteFilterList.push(result.data[i]);
//						else if(result.data[i].typeId == 5)
//							$scope.educationFilterList.push(result.data[i]);
					}
					console.log(result);
				}),(function(error){
					console.log(error);
				});
			}),(function(error){
				console.log(error);
			});
		}),
		function(error){
		
	};
	
	$scope.resetFilters = function(){
		$scope.dataSet = $scope.mstChildList;
		
		for(var i=0; i<$scope.ageFilterList.length; i++){
			if($scope.ageFilterList[i].checked == true)
				$scope.ageFilterList[i].checked = false;
		}
		
		for(var i=0; i<$scope.sexFilterList.length; i++){
			if($scope.sexFilterList[i].checked == true)
				$scope.sexFilterList[i].checked = false;
		}
		
		age1 = null;
		age2 = null;
		age3 = null;
		age4 = null;
		sex1 = null;
		sex2 = null;
		sex3 = null;
	};
	
	var age1 = null;
	var age2 = null;
	var age3 = null;
	var age4 = null;
	var sex1 = null;
	var sex2 = null;
	var sex3 = null;
	var tempChildList = [];

	$scope.filterGlobal = function(item){
		tempChildList = [];
		
		if(item.checked ){
			if(item.id == 18)
				age1 = item;
			else if(item.id == 19)
				age2 = item;
			else if(item.id == 20)
				age3 = item;
			else if(item.id == 389)
				age4 = item;
			else if(item.id == 144)
				sex1 = item;
			else if(item.id == 145)
				sex2 = item;
			else if(item.id == 146)
				sex3 = item;
		}else if(item.checked == false){
			if(item.id == 18)
				age1 = null;
			else if(item.id == 19)
				age2 = null;
			else if(item.id == 20)
				age3 = null;
			else if(item.id == 389)
				age4 = null;
			else if(item.id == 144)
				sex1 = null;
			else if(item.id == 145)
				sex2 = null;
			else if(item.id == 146)
				sex3 = null;
		}
 
		if(age1 && (!age2 && !age3 && !age4 && !sex1 && !sex2 && !sex3)){
			singleFilterAge(0,10);
		}else if(age2 && (!age1 && !age3 && !age4 && !sex1 && !sex2 && !sex3)){
			singleFilterAge(10,15);
		}else if(age3 && (!age1 && !age2 && !age4 && !sex1 && !sex2 && !sex3)){
			singleFilterAge(15,19);
		}else if(age4 && (!age1 && !age2 && !age3 && !sex1 && !sex2 && !sex3)){
			singleFilterAge(19,40);//change
		}else if(sex1 && (!age1 && !age2 && !age3 && !age4 && !sex2 && !sex3)){
			singleFilterSex(sex1.id);
		}else if(sex2 && (!age1 && !age2 && !age3 && !age4 && !sex1 && !sex3)){
			singleFilterSex(sex2.id);
		}else if(sex3 && (!age1 && !age2 && !age3 && !age4 && !sex1 && !sex2)){
			singleFilterSex(sex3.id);
		}
		
		else if(age1 && age2 && (!age3 && !age4 && !sex1 && !sex2 && !sex3)){
			singleFilterAge(0, 15);
		}else if(age1 && age3 && (!age2 && !age4 && !sex1 && !sex2 && !sex3)){
			doubleFilterAge(0, 10, 15, 19);
		}
		else if(age1 && age4 && (!age2 && !age3 && !sex1 && !sex2 && !sex3)){
			doubleFilterAge(0, 10, 19, 40);
		} // age 4
		else if(age1 && sex1 && (!age2 && !age3 && !age4 && !sex2 && !sex3)){
			hybridFilter(0, 10, sex1.id);
		}else if(age1 && sex2 && (!age2 && !age3 && !age4 && !sex1 && !sex3)){
			hybridFilter(0, 10, sex2.id);
		}else if(age1 && sex3 && (!age2 && !age3 && !age4 && !sex1 && !sex2)){
			hybridFilter(0, 10, sex3.id);
		}else if(age2 && age3 && (!age1 && !sex1 && !age4 && !sex2 && !sex3)){
			singleFilterAge(10, 19);
		}
		else if(age2 && age4 && (!age1 && !sex1 && !age3 && !sex2 && !sex3)){
			doubleFilterAge(10,14 , 19, 40);
		} // age 4
		else if(age2 && sex1 && (!age1 && !age3 && !age4 && !sex2 && !sex3)){
			hybridFilter(10, 15, sex1.id);
		}else if(age2 && sex2 && (!age1 && !age3 && !age4 && !sex1 && !sex3)){
			hybridFilter(10, 15, sex2.id);
		}else if(age2 && sex3 && (!age1 && !age3 && !age4 && !sex1 && !sex2)){
			hybridFilter(10, 15, sex3.id);
		}
		else if(age3 && age4 && (!age1 && !sex1 && !age2 && !sex2 && !sex3)){
			singleFilterAge(15, 40);
		}// age 4
		
		else if(age3 && sex1 && (!age1 && !age2 && !age4 && !sex2 && !sex3)){
			hybridFilter(15, 19, sex1.id);
		}else if(age3 && sex2 && (!age1 && !age2 && !age4 && !sex1 && !sex3)){
			hybridFilter(15, 19, sex2.id);
		}else if(age3 && sex3 && (!age1 && !age2 && !age4 && !sex1 && !sex2)){
			hybridFilter(15, 19, sex3.id);
		}
		
		else if(age4 && sex1 && (!age1 && !age2 && !age3 && !sex2 && !sex3)){
			hybridFilter(19, 40, sex1.id);
		}// age4
		else if(age4 && sex2 && (!age1 && !age2 && !age3 && !sex1 && !sex3)){
			hybridFilter(19, 40, sex2.id);
		}// age4
		else if(age4 && sex3 && (!age1 && !age2 && !age3 && !sex1 && !sex2)){
			hybridFilter(19, 40, sex3.id);
		}// age4
		
		else if(sex1 && sex2 && (!age1 && !age2 && !age4 && !age3 && !sex3)){
			doubleFilterSex(sex1.id, sex2.id);
		}else if(sex1 && sex3 && (!age1 && !age2 && !age4 && !age3 && !sex2)){
			doubleFilterSex(sex1.id, sex3.id);
		}else if(sex2 && sex3 && (!age1 && !age2 && !age4 && !age3 && !sex1)){
			doubleFilterSex(sex2.id, sex3.id);
		}
		
		else if(age1 && age2 && age3 && (!age4 && !sex1 && !sex2 && !sex3)){
			singleFilterAge(0, 19);
		}
		
		else if(age1 && age2 && age4 && (!age3 && !sex1 && !sex2 && !sex3)){
			doubleFilterAge(0,14 , 19, 40);
		}
		
		else if(age1 && age3 && age4 && (!age2 && !sex1 && !sex2 && !sex3)){
			doubleFilterAge(0,10 , 19, 40);
		}
		
		else if(age2 && age3 && age4 && (!age1 && !sex1 && !sex2 && !sex3)){
			singleFilterAge(14, 40);
		}
		
		else if(age1 && age2 && age3 && age4 && ( !sex1 && !sex2 && !sex3)){
			singleFilterAge(0, 40);
		}
		
		
		else if(age1 && age2 && sex1 && (!age3 && !age4 && !sex2 && !sex3)){
			hybridFilter(0, 15, sex1.id);
		}else if(age1 && age2 && sex2 && (!age3 && !age4 && !sex1 && !sex3)){
			hybridFilter(0, 15, sex2.id);
		}else if(age1 && age2 && sex3 && (!age3  && !age4 && !sex1 && !sex2)){
			hybridFilter(0, 15, sex3.id);
		}else if(age1 && age3 && sex1 && (!age2 && !age4 && !sex2 && !sex3)){
			hybridFilterDoubleAge(0, 10, 15, 18, sex1.id);
		}else if(age1 && age3 && sex2 && (!age2 && !age4 && !sex1 && !sex3)){
			hybridFilterDoubleAge(0, 10, 15, 18, sex2.id);
		}else if(age1 && age3 && sex3 && (!age2 && !age4 && !sex1 && !sex2)){
			hybridFilterDoubleAge(0, 10, 15, 18, sex3.id);
		}
		/// age 4
		
		else if(age1 && age4 && sex1 && (!age2 && !age3 && !sex2 && !sex3)){
			hybridFilterDoubleAge(0, 10, 19, 40, sex1.id);
		}else if(age1 && age4 && sex2 && (!age2 && !age3 && !sex1 && !sex3)){
			hybridFilterDoubleAge(0, 10, 19, 40, sex2.id);
		}else if(age1 && age4 && sex3 && (!age2 && !age3 && !sex1 && !sex2)){
			hybridFilterDoubleAge(0, 10, 19, 40, sex3.id);
		}
		
		/////
		else if(age1 && sex1 && sex2 && (!age2 && !age3 && !age4 && !sex3)){
			hybridFilterDoubleSex(0, 10, sex1.id, sex2.id);
		}else if(age1 && sex1 && sex3 && (!age2 && !age3 && !age4 && !sex2)){
			hybridFilterDoubleSex(0, 10, sex1.id, sex3.id);
		}else if(age1 && sex2 && sex3 && (!age2 && !age3 && !age4 && !sex1)){
			hybridFilterDoubleSex(0, 10, sex2.id, sex3.id);
		}else if(age2 && age3 && sex1 && (!age1 && !sex2 && !age4 && !sex3)){
			hybridFilter(10, 19, sex1.id);
		}else if(age2 && age3 && sex2 && (!age1 && !sex1 && !age4 && !sex3)){
			hybridFilter(10, 19, sex2.id);
		}else if(age2 && age3 && sex3 && (!age1 && !sex1 && !age4 && !sex2)){
			hybridFilter(10, 19, sex3.id);
		}else if(age2 && sex1 && sex2 && (!age1 && !age3 && !age4 && !sex3)){
			hybridFilterDoubleSex(10, 15, sex1.id, sex2.id);
		}else if(age2 && sex1 && sex3 && (!age1 && !age3 && !age4 && !sex2)){
			hybridFilterDoubleSex(10, 15, sex1.id, sex3.id);
		}else if(age2 && sex2 && sex3 && (!age1 && !age3  && !age4 && !sex1)){
			hybridFilterDoubleSex(10, 15, sex2.id, sex3.id);
		}else if(age3 && sex1 && sex2 && (!age1 && !age2 && !age4 && !sex3)){
			hybridFilterDoubleSex(15, 19, sex1.id, sex2.id);
		}else if(age3 && sex1 && sex3 && (!age1 && !age2 && !age4 && !sex2)){
			hybridFilterDoubleSex(15, 19, sex1.id, sex3.id);
		}else if(age3 && sex2 && sex3 && (!age1 && !age2  && !age4 && !sex1)){
			hybridFilterDoubleSex(15, 19, sex2.id, sex3.id);
		}else if(sex1 && sex2 && sex3 && (!age1 && !age2 && !age4 && !age3)){
			tripleFilterSex(sex1.id, sex2.id, sex3.id);
		}
		// age4
		else if(age4 && sex1 && sex2 && (!age1 && !age2 && !age3 && !sex3)){
			hybridFilterDoubleSex(19,40, sex1.id, sex2.id);
		}else if(age4 && sex1 && sex3 && (!age1 && !age2 && !age3 && !sex2)){
			hybridFilterDoubleSex(19, 40, sex1.id, sex3.id);
		}else if(age4 && sex2 && sex3 && (!age1 && !age2  && !age3 && !sex1)){
			hybridFilterDoubleSex(19, 40, sex2.id, sex3.id);
		}
		
		///
		
		else if(age1 && age2 && age3 && sex1 && (!sex2 && !sex3 && !age4)){
			hybridFilter(0, 19, sex1.id);
		}else if(age1 && age2 && age3 && sex2 && (!sex1 && !sex3 && !age4)){
			hybridFilter(0, 19, sex2.id);
		}else if(age1 && age2 && age3 && sex3 && (!sex1 && !sex2 && !age4)){
			hybridFilter(0, 19, sex3.id);
		}
		// age4
		/*else if(age1 && age2 && age4 && sex1 && (!sex2 && !sex3 && !age3)){
			hybridFilterDoubleAgeSex(0, 15, 19, 40, sex1.id, sex2.id);
		}*/
		
		else if(age2 && age3 && age4 && sex1 && (!sex2 && !sex3 && !age1)){
			hybridFilter(10, 40, sex1.id);
		}
		
		else if(age2 && age3 && age4 && sex2 && (!sex1 && !sex3 && !age1)){
			hybridFilter(10, 40, sex2.id);
		}
		
		
		else if(age2 && age3 && age4 && sex3 && (!sex2 && !sex1 && !age1)){
			hybridFilter(10, 40, sex3.id);
		}
		
		else if(age2 && age3 && age4 && sex3 && age1 && (!sex2 && !sex1 ))
			{
			hybridFilter(0, 40, sex3.id);
			}
		
		else if(age2 && age3 && age4 && sex1 && age1 && (!sex2 && !sex3 ))
		{
		hybridFilter(0, 40, sex1.id);
		}
		
		else if(age2 && age3 && age4 && sex2 && age1 && (!sex1 && !sex3 ))
		{
		hybridFilter(0, 40, sex2.id);
		}
		
		//
		// == not edited code for sex and age====
		else if(age1 && age2 && sex1 && sex2 && (!age3 &&! age4 && !sex3)){
			hybridFilterDoubleSex(0, 15, sex1.id, sex2.id);
		}else if(age1 && age2 && sex1 && sex3 && (!age3 &&!age4 && !sex2)){
			hybridFilterDoubleSex(0, 15, sex1.id, sex3.id);
		}else if(age1 && age2 && sex2 && sex3 && (!age3 &&!age4 && !sex1)){
			hybridFilterDoubleSex(0, 15, sex2.id, sex3.id);
		}
		
		
		
		else if(age1 && age3 && sex1 && sex2 && (!age2 &&!age4 && !sex3)){
			hybridFilterDoubleAgeSex(0, 10, 15, 19, sex1.id, sex2.id);
		}else if(age1 && age3 && sex1 && sex3 && (!age2 &&!age4 && !sex2)){
			hybridFilterDoubleAgeSex(0, 10, 15, 19, sex1.id, sex3.id);
		}else if(age1 && age3 && sex2 && sex3 && (!age2 &&!age4 && !sex1)){
			hybridFilterDoubleAgeSex(0, 10, 15, 19, sex2.id, sex3.id);
		}
		
		//age 4
		else if(age1 && age4 && sex1 && sex2 && (!age2 &&!age3 && !sex3)){
			hybridFilterDoubleAgeSex(0, 10, 19, 40, sex1.id, sex2.id);
		}else if(age1 && age4 && sex1 && sex3 && (!age2 &&!age3 && !sex2)){
			hybridFilterDoubleAgeSex(0, 10, 19, 40, sex1.id, sex3.id);
		}else if(age1 && age4 && sex2 && sex3 && (!age2 &&!age3 && !sex1)){
			hybridFilterDoubleAgeSex(0, 10, 19, 40, sex2.id, sex3.id);
		}
		///
		else if(age2 && age3 && sex1 && sex2 && (!age1 &&!age4 && !sex3)){
			hybridFilterDoubleSex(10, 19, sex1.id, sex2.id);
		}else if(age2 && age3 && sex1 && sex3 && (!age1 &&!age4 && !sex2)){
			hybridFilterDoubleSex(10, 19, sex1.id, sex3.id);
		}else if(age2 && age3 && sex2 && sex3 && (!age1 &&!age4 && !sex1)){
			hybridFilterDoubleSex(10, 19, sex2.id, sex3.id);
		}
		
		// age4
		else if(age2 && age4 && sex1 && sex2 && (!age1 &&!age3 && !sex3)){
			hybridFilterDoubleAgeSex(10, 15, 19, 40, sex1.id, sex2.id);
		}else if(age2 && age4 && sex1 && sex3 && (!age1 &&!age3 && !sex2)){
			hybridFilterDoubleAgeSex(10, 15, 19, 40, sex1.id, sex3.id);
		}else if(age2 && age4 && sex2 && sex3 && (!age1 &&!age3 && !sex1)){
			hybridFilterDoubleAgeSex(10, 15, 19, 40, sex2.id, sex3.id);
		}
		
		else if(age4 && age3 && sex1 && sex2 && (!age1 &&!age2 && !sex3)){
			hybridFilterDoubleSex(15, 40, sex1.id, sex2.id);
		}else if(age4 && age3 && sex1 && sex3 && (!age1 &&!age2 && !sex2)){
			hybridFilterDoubleSex(15, 40, sex1.id, sex3.id);
		}else if(age4 && age3 && sex2 && sex3 && (!age1 &&!age2 && !sex1)){
			hybridFilterDoubleSex(15, 40, sex2.id, sex3.id);
		}
		
		///
		
		else if(age1 && sex1 && sex2 && sex3 && (!age2 && !age3 && !age4)){
			singleFilterAge(0, 10);
		}else if(age2 && sex1 && sex2 && sex3 && (!age1 && !age3 && !age4)){
			singleFilterAge(10, 15);
		}else if(age3 && sex1 && sex2 && sex3 && (!age1 && !age2 && !age4 )){
			singleFilterAge(15, 18);
		}
		
		else if(age4 && sex1 && sex2 && sex3 && (!age1 && !age2 && !age3 )){
			singleFilterAge(19, 40);
		}
		
		else if(age1 && age2 && age3 && age4 && sex1 && sex2 && (!sex3)){
			doubleFilterSex(sex1.id, sex2.id);
		}else if(age1 && age2 && age3 && age4 && sex1 && sex3 && (!sex2)){
			doubleFilterSex(sex1.id, sex3.id);
		}else if(age1 && age2 && age3 && age4 && sex2 && sex3 && (!sex1)){
			doubleFilterSex(sex2.id, sex3.id);
		}
		
		else if(age1 && age2 && sex1 && sex2 && sex3 && (!age3 && !age4)){
			singleFilterAge(0, 15);
		}else if(age1 && age3 && sex1 && sex2 && sex3 && (!age2 && !age4)){
			doubleFilterAge(0, 10, 15, 19);
		}else if(age2 && age3 && sex1 && sex2 && sex3 && (!age1 && !age4)){
			singleFilterAge(10, 19);
		}
		// age4
		
		else if(age1 && age4 && sex1 && sex2 && sex3 && (!age3 && !age2))
			{
			doubleFilterAge(0, 10, 19, 40);
			}
		else if(age2 && age4 && sex1 && sex2 && sex3 && (!age1 && !age3)){
			doubleFilterAge(10, 15, 19,40);
		}
		
		else if(age3 && age4 && sex1 && sex2 && sex3 && (!age1 && !age2)){
			singleFilterAge(15, 40);
		}
		//
		
		else{
			tempChildList = $scope.mstChildList;
		}
		
		
		
//		else if(age1 && sex1 && sex2 && (!age2 && !age3 && !sex3)){
//			hybridFilterDoubleSex(0, 10, sex1.id, sex2.id);
//		}else if(age1 && sex1 && sex2 && (!age2 && !age3 && !sex3)){
//			hybridFilterDoubleSex(0, 10, sex1.id, sex2.id);
//		}
		
		$scope.dataSet = tempChildList;
	};

	var singleFilterAge = function(lb, ub){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if($scope.mstChildList[i].currentAge >= lb && $scope.mstChildList[i].currentAge < ub)
				tempChildList.push($scope.mstChildList[i]);
		}
	};

	var singleFilterSex = function(sexId){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if($scope.mstChildList[i].childSex == sexId)
				tempChildList.push($scope.mstChildList[i]);
		}
	};

	var doubleFilterAge = function(lb1, ub1, lb2, ub2){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if( ($scope.mstChildList[i].currentAge >= lb1 && $scope.mstChildList[i].currentAge < ub1) ||
				($scope.mstChildList[i].currentAge >= lb2 && $scope.mstChildList[i].currentAge < ub2)	)
				tempChildList.push($scope.mstChildList[i]);
		}
	};

	var doubleFilterSex = function(sexId1, sexId2){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if( ($scope.mstChildList[i].childSex == sexId1) ||
				($scope.mstChildList[i].childSex == sexId2)	)
				tempChildList.push($scope.mstChildList[i]);
		}
	};
	
	var tripleFilterSex = function(sexId1, sexId2, sexId3){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if( ($scope.mstChildList[i].childSex == sexId1) ||
				($scope.mstChildList[i].childSex == sexId2) ||
				($scope.mstChildList[i].childSex == sexId3))
				tempChildList.push($scope.mstChildList[i]);
		}
	};

	var hybridFilter = function(lb, ub, sexId){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if( ($scope.mstChildList[i].currentAge >= lb && $scope.mstChildList[i].currentAge < ub) &&
				($scope.mstChildList[i].childSex == sexId)	)
				tempChildList.push($scope.mstChildList[i]);
		}
	};
	
	var hybridFilterDoubleAge = function(lb1, ub1, lb2, ub2, sexId){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if( (($scope.mstChildList[i].currentAge >= lb1 && $scope.mstChildList[i].currentAge < ub1) ||
				($scope.mstChildList[i].currentAge >= lb2 && $scope.mstChildList[i].currentAge < ub2)) && $scope.mstChildList[i].childSex == sexId)
				tempChildList.push($scope.mstChildList[i]);
		}
	};
	
	var hybridFilterDoubleSex = function(lb, ub, sexId1, sexId2){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if( ($scope.mstChildList[i].currentAge >= lb && $scope.mstChildList[i].currentAge < ub) &&
				($scope.mstChildList[i].childSex == sexId1 || $scope.mstChildList[i].childSex == sexId2)	)
				tempChildList.push($scope.mstChildList[i]);
		}
	};
	
	var hybridFilterDoubleAgeSex = function(lb1, ub1, lb2, ub2, sexId1, sexId2){
		for(var i=0; i<$scope.mstChildList.length; i++){
			if( (($scope.mstChildList[i].currentAge >= lb1 && $scope.mstChildList[i].currentAge < ub1) ||
				($scope.mstChildList[i].currentAge >= lb2 && $scope.mstChildList[i].currentAge < ub2)) && 
				($scope.mstChildList[i].childSex == sexId1 || $scope.mstChildList[i].childSex == sexId2))
				tempChildList.push($scope.mstChildList[i]);
		}
	};
	
//	var tripleFilterSexSingleAge = function(lb1, ub1, sexId1, sexId2, sexId3){
//		for(var i=0; i<$scope.mstChildList.length; i++){
//			if( ($scope.mstChildList[i].age >= lb1 && $scope.mstChildList[i].age < ub1) && 
//				($scope.mstChildList[i].childSex == sexId1 || $scope.mstChildList[i].childSex == sexId2))
//				tempChildList.push($scope.mstChildList[i]);
//		}
//	};

	//for age filter if needed
	// for(var i=0; i<$scope.mstChildData.length; i++){
			// 	if($scope.mstChildData[i].age == age2)
			// 		tempChildList.push($scope.mstChildData[i]);
			// }

	//for sex filter
	// for(var i=0; i<$scope.mstChildData.length; i++){
			// 	if($scope.mstChildData[i].childSex == sex3)
			// 		tempChildList.push($scope.mstChildData[i]);
			// }
	
}
