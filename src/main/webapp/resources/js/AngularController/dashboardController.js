var app = angular.module('dashboardApp', [ 'google-maps' ]);

app.run(function ($rootScope) {
	$rootScope.rootpath="/CPIS/";
	return $rootScope.rootpath;
});

app.controller('DashboardController',
				function($scope, $http) {
		
	$scope.cciType = [];
	$scope.cciDetails = [];
	$scope.cciDetailsForInf = [];
	$scope.selectedCCI = {};
	$scope.cciCapacityInfo = [];
	$scope.showName = false;
	var data1 = [];
//	$scope.map = {};
	$scope.selected = "";
	
	$http.get("getCCIDetails").
       then(function(result){
    	   $scope.cciType = result.data.cciTypes;
    	   $scope.cciDetailsForInf = result.data.cciDetails;
    	   $scope.cciDetails = result.data.cciDetails;
//    	   $scope.selectDefaultCCI();
       	   console.log(result);
       	   
       	   $http.get("getHRDetails").
       	   then(function(result){
       		   $scope.hrDetailsList = result.data;
       		   console.log(result);
       	   }, function(error){
       		   console.log(error);
       	   });
       	   
       }),(function(error){
       	console.log(error);
       });
	
//	$scope.selectDefaultCCI = function(){
//		$scope.showName = false;
//		for(var i=0; i<$scope.cciDetails.length; i++){
//			if($scope.cciDetails[i].cciType == 2){
//				$scope.selected = $scope.cciDetails[i].cciTypeDetails.typeName;
//				$scope.selectedCCI = $scope.cciDetails[i];
//				$scope.changeInfrastructure();
//			}
//		}
//		$scope.map.markers = [];
//		$scope.map.markers.push($scope.selectedCCI.geoLocationObject); 
//		$scope.changeStatsCCI();
//	};
	
	$http.get('countAdd').
    then(function(result){
    	if(result.data.noOfCounter==undefined)
    		$scope.count=1;
    	else
    	$scope.count=result.data.noOfCounter;

   }, function(error){
	   console.log(error);
   });
	
	$scope.changeStatsCCI = function(){
		$scope.showData=true;
		$scope.cciCapacityInfo = [ {name : "Total Capacity", value : $scope.selectedCCI.totalCapacity},
		                           {name : "Total Occupancy", value : $scope.selectedCCI.totalChildrenLiving},
                                   {name : "Boys", value : $scope.selectedCCI.totalBoysLiving},
                                   {name : "Girls", value : $scope.selectedCCI.totalGirlsLiving},
                                   {name : "Third Gender", value : $scope.selectedCCI.totalThirdGenderLiving}];
		data1 = [{
	        name: "Boys",
	        value: $scope.selectedCCI.totalBoysLiving
	    }, {
	        name: "Girls",
	        value: $scope.selectedCCI.totalGirlsLiving
	    },{
	        name: "Third Gender",
	        value: $scope.selectedCCI.totalThirdGenderLiving
	    }];
		
		sexGraph();
	};
	
	$scope.changeInfrastructure = function(){
		$scope.infData = {
        		"total_rooms": $scope.selectedCCI.total_rooms_value,
    			"no_of_children":$scope.selectedCCI.no_of_children_value,
    			"no_of_room_boys":$scope.selectedCCI.no_of_room_boys_value,
    			"no_of_room_girls":$scope.selectedCCI.no_of_room_girls_value,
    			"proper_elec":$scope.selectedCCI.proper_elec_value?"Yes":"No",
    			"no_of_toilets":$scope.selectedCCI.no_of_toilets_value,
    			"drinking_water_facility":$scope.selectedCCI.drinking_water_facility_value?"Yes":"No",
    			"no_of_toilet_boys":$scope.selectedCCI.no_of_toilet_boys_value,
    			"no_of_toilet_girls":$scope.selectedCCI.no_of_toilet_girls_value,
    			"boundary_wall":$scope.selectedCCI.boundary_wall_value?"Yes":"No"
	    };		
	};
	
	$scope.infrastructureFlag = false;
	var switchInfrastructureFlag = function(){
		 if($scope.selectedCCI.building_Type == null &&
			$scope.selectedCCI.status_of_Building == null &&
			$scope.selectedCCI.area_of_Building == null &&
			$scope.selectedCCI.total_rooms_value == null &&
			$scope.selectedCCI.no_of_room_boys_value == null &&
			$scope.selectedCCI.no_of_room_girls_value == null &&
			$scope.selectedCCI.no_of_toilets_value == null &&
			$scope.selectedCCI.no_of_toilet_boys_value == null &&
			$scope.selectedCCI.no_of_toilet_girls_value == null &&
			$scope.selectedCCI.number_of_ContactPoints == null
			){
			 $scope.infrastructureFlag = true;
		 }
	};
	
	$scope.changeCCI = function(cciType){
		$scope.showName = false;
		$scope.showData=false;
		$scope.showInfrastructure=false;
		$scope.map.markers = [];
		$scope.selectedCCIArr = [];
		for(var i=0; i< $scope.cciType.length; i++){
			if(cciType.typeName == $scope.cciType[i].typeName){
				cciType = $scope.cciType[i];
			}
		}
		$scope.selected = cciType.typeName;
		for(var i=0; i<$scope.cciDetails.length; i++){
			if($scope.cciDetails[i].cciType == cciType.typeId){
				$scope.selectedCCI = $scope.cciDetails[i];
				$scope.selectedCCIArr.push($scope.cciDetails[i]);
				$scope.selectedCCIKeys = Object.keys($scope.selectedCCI);
			}
		}
		
		selectDefaultHr();
		
		d3.select(".sexBarChart").select("svg").remove();
		for(var j=0; j < $scope.selectedCCIArr.length; j++){
			$scope.map.markers.push($scope.selectedCCIArr[j].geoLocationObject);
		}
		$scope.changeStatsCCI();
		$scope.changeInfrastructure();
		infrastructureFlag = false;
	};
	
	var selectDefaultHr = function(){
		$scope.hrInfo={};
//		$("#noHrData").show();
		$("#cciDiv").hide();
		$("#saaDiv").hide();
		$("#osDiv").hide();
		if($scope.hrDetailsList != undefined && $scope.hrDetailsList != "" && $scope.hrDetailsList.length > 0){
			if($scope.selectedCCI.cciId != undefined){
				for(var j=0; j<$scope.hrDetailsList.length; j++){
					if($scope.hrDetailsList[j].nameOfCCI != undefined || $scope.hrDetailsList[j].nameOfCCI != null){
						if($scope.hrDetailsList[j].nameOfCCI.id == $scope.selectedCCI.cciId){
							$scope.hrInfo = $scope.hrDetailsList[j];
							$("#cciDiv").show();
							$("#saaDiv").hide();
							$("#osDiv").hide();
//							$("#noHrData").hide();
//							if($scope.hrInfo.nameOfOpenShelter === null){
//								
//							}else if($scope.hrInfo.nameOfSAA === null){
//								
//							}else{
//							}
						}
					}else if($scope.hrDetailsList[j].nameOfSAA != undefined || $scope.hrDetailsList[j].nameOfSAA != null){
						if($scope.hrDetailsList[j].nameOfSAA.id == $scope.selectedCCI.cciId){
							$scope.hrInfo = $scope.hrDetailsList[j];
							$("#cciDiv").hide();
							$("#saaDiv").show();
							$("#osDiv").hide();
//							$("#noHrData").hide();
						}
					}else if($scope.hrDetailsList[j].nameOfOpenShelter != undefined || $scope.hrDetailsList[j].nameOfOpenShelter != null){
						if($scope.hrDetailsList[j].nameOfOpenShelter.id == $scope.selectedCCI.cciId){
							$scope.hrInfo = $scope.hrDetailsList[j];
							$("#cciDiv").hide();
							$("#saaDiv").hide();
							$("#osDiv").show();
//							$("#noHrData").hide();
						}
					}
			}
		}
	}
		$scope.hrInfoKeys = Object.keys($scope.hrInfo);
	};
	
	$scope.showCasteGraph = false;
	$scope.showSexGraph = true;
	$scope.buttonFlag = false;

	$scope.changeGraph = function(name) {
//		if (type.name == "Age") {
//			$scope.showCasteGraph = false;
//			$scope.showSexGraph = false;
//		}
		$scope.selectedTypeShow=name;
//
		if (name == "Caste") {
			$scope.showCasteGraph = true;
			$scope.showSexGraph = false;
			data = [];
			for(var j=0;j<$scope.masterCasteData.length;j++){
				if($scope.masterCasteData[j].cciId == $scope.selectedCCI.cciId){
					data.push($scope.masterCasteData[j]);
				}
			}
			casteGraph();
			resetSexGraph();
			animateCasteGraph();
		} else if (name == "Sex") {
			$scope.showCasteGraph = false;
			$scope.showSexGraph = true;
			
			
			
			startAnimationSexGraph();
			resetCasteGraph();
		}
		else if (name == "Age")
			{
			$scope.showCasteGraph = true;
			$scope.showSexGraph = false;
			data=[]
			for(var j=0;j<$scope.masterCasteData.length;j++)
				{

				if($scope.masterAgeData[j].cciId == $scope.selectedCCI.cciId){
					data.push($scope.masterAgeData[j]);
				}
			
				}
			casteGraph();
			resetSexGraph();
			animateCasteGraph();
		}

	};
	
	$scope.categoryWiseStats = [ {
		id : 1,
		name : "Age"
	}, {
		id : 2,
		name : "Sex"
	}, {
		id : 3,
		name : "Caste"
	}, {
		id : 4,
		name : "Education"
	} ];
	
	
					
	$scope.ff = "CCI InfoMap";
	$scope.map = "";
	$scope.center = {
		latitude : 27.4,
		longitude : 80
	};

	$scope.zoom = 6;
	$scope.show = true;

	$scope.polygons = {
			path: [{longitude:81.5900258,latitude:28.0234432}, {longitude:81.592023,latitude:28.0223795}, {longitude:81.5938592,latitude:28.0220213}, {longitude:81.5962026,latitude:28.0212422}, {longitude:81.5985429,latitude:28.0197874}, {longitude:81.6007157,latitude:28.0187114}, {longitude:81.6074188,latitude:28.0153246}, {longitude:81.6097881,latitude:28.01362}, {longitude:81.6137123,latitude:28.0116594}, {longitude:81.6161179,latitude:28.0104661}, {longitude:81.6183281,latitude:28.0092502}, {longitude:81.6189571,latitude:28.0087397}, {longitude:81.6218946,latitude:28.0046328}, {longitude:81.6242567,latitude:28.0015138}, {longitude:81.6266097,latitude:28.0009656}, {longitude:81.627367,latitude:28.0019236}, {longitude:81.6329465,latitude:27.9992006}, {longitude:81.6366388,latitude:27.9973556}, {longitude:81.6379317,latitude:27.9974461}, {longitude:81.6391592,latitude:27.9972849}, {longitude:81.6399302,latitude:27.9968818}, {longitude:81.6400722,latitude:27.9964697}, {longitude:81.6417769,latitude:27.9946784}, {longitude:81.6375,latitude:27.987222}, {longitude:81.642778,latitude:27.985278}, {longitude:81.648611,latitude:27.984444}, {longitude:81.654444,latitude:27.983333}, {longitude:81.660278,latitude:27.9825}, {longitude:81.666389,latitude:27.981389}, {longitude:81.672222,latitude:27.980556}, {longitude:81.678611,latitude:27.980556}, {longitude:81.684444,latitude:27.979444}, {longitude:81.690556,latitude:27.978611}, {longitude:81.695833,latitude:27.976667}, {longitude:81.701111,latitude:27.975}, {longitude:81.706389,latitude:27.973056}, {longitude:81.710556,latitude:27.969722}, {longitude:81.715278,latitude:27.967222}, {longitude:81.719167,latitude:27.963611}, {longitude:81.723333,latitude:27.960278}, {longitude:81.726944,latitude:27.955833}, {longitude:81.731111,latitude:27.9525}, {longitude:81.735,latitude:27.949167}, {longitude:81.739167,latitude:27.945556}, {longitude:81.743333,latitude:27.942222}, {longitude:81.7475,latitude:27.938611}, {longitude:81.751667,latitude:27.935278}, {longitude:81.755556,latitude:27.931944}, {longitude:81.76,latitude:27.927778}, {longitude:81.764167,latitude:27.924167}, {longitude:81.768056,latitude:27.920833}, {longitude:81.772222,latitude:27.917222}, {longitude:81.776389,latitude:27.913889}, {longitude:81.780556,latitude:27.910556}, {longitude:81.785278,latitude:27.907778}, {longitude:81.793004,latitude:27.913444}, {longitude:81.8031374,latitude:27.9053808}, {longitude:81.8046738,latitude:27.9042614}, {longitude:81.8051498,latitude:27.9034543}, {longitude:81.8084626,latitude:27.9031109}, {longitude:81.8123389,latitude:27.9042785}, {longitude:81.8135277,latitude:27.904458}, {longitude:81.8154886,latitude:27.9039067}, {longitude:81.8185319,latitude:27.9034903}, {longitude:81.821435,latitude:27.9033778}, {longitude:81.8242359,latitude:27.9021277}, {longitude:81.8248475,latitude:27.9008684}, {longitude:81.8301445,latitude:27.8993042}, {longitude:81.8338244,latitude:27.8973686}, {longitude:81.834894,latitude:27.8958606}, {longitude:81.8394067,latitude:27.8915}, {longitude:81.8414991,latitude:27.8902786}, {longitude:81.8454108,latitude:27.8887228}, {longitude:81.8473014,latitude:27.8873038}, {longitude:81.8482141,latitude:27.8868933}, {longitude:81.8512049,latitude:27.8845595}, {longitude:81.8561759,latitude:27.8808066}, {longitude:81.8601691,latitude:27.8783215}, {longitude:81.8612366,latitude:27.8775579}, {longitude:81.8623205,latitude:27.8770032}, {longitude:81.8641186,latitude:27.8759475}, {longitude:81.8648907,latitude:27.8756907}, {longitude:81.8667553,latitude:27.8744978}, {longitude:81.8678701,latitude:27.873495}, {longitude:81.868848,latitude:27.8728611}, {longitude:81.871547,latitude:27.8705674}, {longitude:81.8732763,latitude:27.8695091}, {longitude:81.875446,latitude:27.867645}, {longitude:81.8775954,latitude:27.8661772}, {longitude:81.8799228,latitude:27.8621989}, {longitude:81.8808844,latitude:27.8608733}, {longitude:81.8831499,latitude:27.8585534}, {longitude:81.8840545,latitude:27.8579986}, {longitude:81.8849998,latitude:27.8569395}, {longitude:81.8870371,latitude:27.8566657}, {longitude:81.8894927,latitude:27.8565389}, {longitude:81.8927778,latitude:27.856003}, {longitude:81.8959185,latitude:27.8552646}, {longitude:81.8983362,latitude:27.8546918}, {longitude:81.9001022,latitude:27.8536491}, {longitude:81.9021794,latitude:27.8538873}, {longitude:81.9035922,latitude:27.8545489}, {longitude:81.9060048,latitude:27.855094}, {longitude:81.9078366,latitude:27.8553904}, {longitude:81.9090279,latitude:27.8557503}, {longitude:81.9110872,latitude:27.8562108}, {longitude:81.912937,latitude:27.8566554}, {longitude:81.9137033,latitude:27.8569412}, {longitude:81.9162394,latitude:27.8572112}, {longitude:81.9190259,latitude:27.8580707}, {longitude:81.9230392,latitude:27.85896}, {longitude:81.9250449,latitude:27.8594461}, {longitude:81.9258291,latitude:27.8599648}, {longitude:81.9260813,latitude:27.861237}, {longitude:81.9261187,latitude:27.8634118}, {longitude:81.9259347,latitude:27.8661705}, {longitude:81.9259024,latitude:27.8710865}, {longitude:81.9252809,latitude:27.8755302}, {longitude:81.93,latitude:27.879722}, {longitude:81.933889,latitude:27.883611}, {longitude:81.936944,latitude:27.8875}, {longitude:81.940833,latitude:27.891389}, {longitude:81.944722,latitude:27.894722}, {longitude:81.948611,latitude:27.898611}, {longitude:81.9525,latitude:27.902222}, {longitude:81.956389,latitude:27.905833}, {longitude:81.961389,latitude:27.908889}, {longitude:81.966111,latitude:27.911944}, {longitude:81.971944,latitude:27.914444}, {longitude:81.977778,latitude:27.917222}, {longitude:81.984444,latitude:27.919167}, {longitude:81.992222,latitude:27.920556}, {longitude:81.999722,latitude:27.922222}, {longitude:82.005556,latitude:27.921111}, {longitude:82.012222,latitude:27.921111}, {longitude:82.018056,latitude:27.92}, {longitude:82.024444,latitude:27.92}, {longitude:82.031111,latitude:27.919722}, {longitude:82.036944,latitude:27.918889}, {longitude:82.043333,latitude:27.918611}, {longitude:82.049167,latitude:27.917778}, {longitude:82.055278,latitude:27.916667}, {longitude:82.060556,latitude:27.915}, {longitude:82.066389,latitude:27.914167}, {longitude:82.071667,latitude:27.912222}, {longitude:82.076944,latitude:27.910278}, {longitude:82.081111,latitude:27.906944}, {longitude:82.085556,latitude:27.904167}, {longitude:82.089722,latitude:27.900833}, {longitude:82.093889,latitude:27.8975}, {longitude:82.0975,latitude:27.893056}, {longitude:82.100278,latitude:27.888056}, {longitude:82.1025,latitude:27.881944}, {longitude:82.105556,latitude:27.876944}, {longitude:82.108333,latitude:27.871944}, {longitude:82.1125,latitude:27.868333}, {longitude:82.117222,latitude:27.865833}, {longitude:82.123056,latitude:27.864722}, {longitude:82.128889,latitude:27.863889}, {longitude:82.135833,latitude:27.864444}, {longitude:82.1425,latitude:27.864444}, {longitude:82.148889,latitude:27.864167}, {longitude:82.155,latitude:27.8625}, {longitude:82.160278,latitude:27.860833}, {longitude:82.165556,latitude:27.858889}, {longitude:82.170833,latitude:27.857222}, {longitude:82.175,latitude:27.853611}, {longitude:82.179722,latitude:27.851111}, {longitude:82.183611,latitude:27.8475}, {longitude:82.188333,latitude:27.845}, {longitude:82.1925,latitude:27.841389}, {longitude:82.197222,latitude:27.838889}, {longitude:82.201944,latitude:27.836111}, {longitude:82.206389,latitude:27.833611}, {longitude:82.210556,latitude:27.83}, {longitude:82.215278,latitude:27.8275}, {longitude:82.22,latitude:27.824722}, {longitude:82.224722,latitude:27.822222}, {longitude:82.229444,latitude:27.819444}, {longitude:82.233333,latitude:27.816111}, {longitude:82.2375,latitude:27.812778}, {longitude:82.241667,latitude:27.809167}, {longitude:82.245556,latitude:27.805833}, {longitude:82.249722,latitude:27.802222}, {longitude:82.253889,latitude:27.798889}, {longitude:82.258056,latitude:27.795278}, {longitude:82.261944,latitude:27.791944}, {longitude:82.266111,latitude:27.788333}, {longitude:82.270278,latitude:27.785}, {longitude:82.274167,latitude:27.781389}, {longitude:82.278333,latitude:27.778056}, {longitude:82.283056,latitude:27.775278}, {longitude:82.287222,latitude:27.771944}, {longitude:82.291667,latitude:27.769167}, {longitude:82.295833,latitude:27.765833}, {longitude:82.3,latitude:27.762222}, {longitude:82.304722,latitude:27.759722}, {longitude:82.308611,latitude:27.756111}, {longitude:82.313333,latitude:27.753611}, {longitude:82.318056,latitude:27.751111}, {longitude:82.323333,latitude:27.749167}, {longitude:82.329722,latitude:27.748889}, {longitude:82.335556,latitude:27.748056}, {longitude:82.341389,latitude:27.746944}, {longitude:82.3475,latitude:27.746111}, {longitude:82.352778,latitude:27.744167}, {longitude:82.3575,latitude:27.741667}, {longitude:82.361389,latitude:27.738056}, {longitude:82.365556,latitude:27.734722}, {longitude:82.369444,latitude:27.731111}, {longitude:82.373611,latitude:27.727778}, {longitude:82.377222,latitude:27.723333}, {longitude:82.380556,latitude:27.719167}, {longitude:82.384722,latitude:27.715556}, {longitude:82.388056,latitude:27.711389}, {longitude:82.391667,latitude:27.706944}, {longitude:82.394444,latitude:27.701944}, {longitude:82.398611,latitude:27.698333}, {longitude:82.401944,latitude:27.694167}, {longitude:82.406111,latitude:27.690833}, {longitude:82.410833,latitude:27.688056}, {longitude:82.415,latitude:27.684722}, {longitude:82.419444,latitude:27.681944}, {longitude:82.424167,latitude:27.679444}, {longitude:82.429444,latitude:27.6775}, {longitude:82.434722,latitude:27.675556}, {longitude:82.440556,latitude:27.674722}, {longitude:82.445833,latitude:27.672778}, {longitude:82.451667,latitude:27.671944}, {longitude:82.458333,latitude:27.671667}, {longitude:82.464722,latitude:27.671667}, {longitude:82.471667,latitude:27.672222}, {longitude:82.478889,latitude:27.672778}, {longitude:82.486389,latitude:27.674444}, {longitude:82.494167,latitude:27.675833}, {longitude:82.501111,latitude:27.676667}, {longitude:82.507778,latitude:27.678611}, {longitude:82.514444,latitude:27.680556}, {longitude:82.5189459,latitude:27.6892515}, {longitude:82.5236459,latitude:27.6890165}, {longitude:82.5290191,latitude:27.6878708}, {longitude:82.535,latitude:27.685278}, {longitude:82.540833,latitude:27.684167}, {longitude:82.5475,latitude:27.684167}, {longitude:82.553889,latitude:27.683889}, {longitude:82.560833,latitude:27.684722}, {longitude:82.568056,latitude:27.685278}, {longitude:82.575556,latitude:27.686667}, {longitude:82.583333,latitude:27.688333}, {longitude:82.590278,latitude:27.688889}, {longitude:82.598056,latitude:27.690278}, {longitude:82.604722,latitude:27.692222}, {longitude:82.611389,latitude:27.694444}, {longitude:82.617222,latitude:27.696944}, {longitude:82.623889,latitude:27.698889}, {longitude:82.630556,latitude:27.700833}, {longitude:82.6375,latitude:27.703056}, {longitude:82.644167,latitude:27.705}, {longitude:82.650833,latitude:27.706944}, {longitude:82.658056,latitude:27.7075}, {longitude:82.665,latitude:27.708333}, {longitude:82.670278,latitude:27.706389}, {longitude:82.676111,latitude:27.705278}, {longitude:82.681944,latitude:27.704444}, {longitude:82.689722,latitude:27.705833}, {longitude:82.695556,latitude:27.708333}, {longitude:82.701111,latitude:27.711111}, {longitude:82.705833,latitude:27.708333}, {longitude:82.71,latitude:27.705}, {longitude:82.712778,latitude:27.699722}, {longitude:82.715833,latitude:27.694722}, {longitude:82.718611,latitude:27.689444}, {longitude:82.720833,latitude:27.683611}, {longitude:82.7225,latitude:27.676667}, {longitude:82.722222,latitude:27.671111}, {longitude:82.721111,latitude:27.665833}, {longitude:82.720833,latitude:27.66}, {longitude:82.7225,latitude:27.653333}, {longitude:82.726111,latitude:27.648889}, {longitude:82.729444,latitude:27.644722}, {longitude:82.7325,latitude:27.639444}, {longitude:82.734167,latitude:27.632778}, {longitude:82.733056,latitude:27.6275}, {longitude:82.732778,latitude:27.621944}, {longitude:82.731667,latitude:27.616667}, {longitude:82.733056,latitude:27.611389}, {longitude:82.735278,latitude:27.605278}, {longitude:82.738611,latitude:27.601111}, {longitude:82.741389,latitude:27.595833}, {longitude:82.744444,latitude:27.590833}, {longitude:82.746667,latitude:27.584722}, {longitude:82.747222,latitude:27.578611}, {longitude:82.746111,latitude:27.573333}, {longitude:82.744167,latitude:27.568611}, {longitude:82.743056,latitude:27.563611}, {longitude:82.742778,latitude:27.557778}, {longitude:82.744444,latitude:27.551111}, {longitude:82.747222,latitude:27.545833}, {longitude:82.750278,latitude:27.540833}, {longitude:82.7525,latitude:27.534167}, {longitude:82.754722,latitude:27.528056}, {longitude:82.757222,latitude:27.522222}, {longitude:82.759444,latitude:27.516389}, {longitude:82.762222,latitude:27.511111}, {longitude:82.764444,latitude:27.505278}, {longitude:82.7689036,latitude:27.5007381}, {longitude:82.7849179,latitude:27.5000641}, {longitude:82.7933027,latitude:27.4982219}, {longitude:82.8014023,latitude:27.4964424}, {longitude:82.8167886,latitude:27.4989123}, {longitude:82.832481,latitude:27.5019568}, {longitude:82.8480835,latitude:27.499492}, {longitude:82.8592384,latitude:27.4988449}, {longitude:82.866944,latitude:27.492222}, {longitude:82.872778,latitude:27.491111}, {longitude:82.879167,latitude:27.491111}, {longitude:82.885556,latitude:27.490833}, {longitude:82.892778,latitude:27.491389}, {longitude:82.900278,latitude:27.492778}, {longitude:82.906944,latitude:27.495}, {longitude:82.914722,latitude:27.496389}, {longitude:82.920556,latitude:27.495278}, {longitude:82.925833,latitude:27.493611}, {longitude:82.929722,latitude:27.49}, {longitude:82.933889,latitude:27.486389}, {longitude:82.936667,latitude:27.481389}, {longitude:82.94,latitude:27.476944}, {longitude:82.943611,latitude:27.472778}, {longitude:82.9475,latitude:27.469167}, {longitude:82.952222,latitude:27.466667}, {longitude:82.9575,latitude:27.464722}, {longitude:82.962778,latitude:27.463056}, {longitude:82.968056,latitude:27.461111}, {longitude:82.973889,latitude:27.46}, {longitude:82.979722,latitude:27.459167}, {longitude:82.985556,latitude:27.458056}, {longitude:82.991389,latitude:27.456944}, {longitude:82.997778,latitude:27.456944}, {longitude:83.003611,latitude:27.455833}, {longitude:83.008889,latitude:27.453889}, {longitude:83.014722,latitude:27.453056}, {longitude:83.02,latitude:27.451111}, {longitude:83.025833,latitude:27.45}, {longitude:83.031667,latitude:27.449167}, {longitude:83.0375,latitude:27.448056}, {longitude:83.043889,latitude:27.447778}, {longitude:83.050833,latitude:27.448611}, {longitude:83.0575,latitude:27.448333}, {longitude:83.064444,latitude:27.448889}, {longitude:83.071389,latitude:27.449444}, {longitude:83.078056,latitude:27.449444}, {longitude:83.084444,latitude:27.449167}, {longitude:83.090833,latitude:27.448889}, {longitude:83.097222,latitude:27.448889}, {longitude:83.104167,latitude:27.449444}, {longitude:83.110833,latitude:27.449167}, {longitude:83.116667,latitude:27.448056}, {longitude:83.123611,latitude:27.448889}, {longitude:83.130556,latitude:27.449444}, {longitude:83.137222,latitude:27.449167}, {longitude:83.144167,latitude:27.449722}, {longitude:83.150556,latitude:27.449722}, {longitude:83.157778,latitude:27.450278}, {longitude:83.164167,latitude:27.45}, {longitude:83.170556,latitude:27.449722}, {longitude:83.176389,latitude:27.448889}, {longitude:83.181667,latitude:27.446944}, {longitude:83.186111,latitude:27.444444}, {longitude:83.190278,latitude:27.440833}, {longitude:83.193611,latitude:27.436389}, {longitude:83.197222,latitude:27.432222}, {longitude:83.2,latitude:27.426944}, {longitude:83.203333,latitude:27.422778}, {longitude:83.2112833,latitude:27.4233733}, {longitude:83.213787,latitude:27.4210571}, {longitude:83.2183179,latitude:27.4166265}, {longitude:83.2207117,latitude:27.4150845}, {longitude:83.2257437,latitude:27.4116095}, {longitude:83.225278,latitude:27.407778}, {longitude:83.23,latitude:27.405}, {longitude:83.234722,latitude:27.402222}, {longitude:83.239167,latitude:27.399722}, {longitude:83.243333,latitude:27.396111}, {longitude:83.247778,latitude:27.393611}, {longitude:83.251944,latitude:27.39}, {longitude:83.255833,latitude:27.386389}, {longitude:83.258056,latitude:27.380556}, {longitude:83.260278,latitude:27.374722}, {longitude:83.261944,latitude:27.367778}, {longitude:83.263889,latitude:27.363056}, {longitude:83.266111,latitude:27.357222}, {longitude:83.268333,latitude:27.351389}, {longitude:83.271111,latitude:27.346111}, {longitude:83.275833,latitude:27.343333}, {longitude:83.281111,latitude:27.341667}, {longitude:83.2822497,latitude:27.341224}, {longitude:83.286111,latitude:27.339722}, {longitude:83.291389,latitude:27.337778}, {longitude:83.297222,latitude:27.336944}, {longitude:83.303611,latitude:27.336667}, {longitude:83.309444,latitude:27.335556}, {longitude:83.316111,latitude:27.335556}, {longitude:83.323056,latitude:27.336111}, {longitude:83.330556,latitude:27.3375}, {longitude:83.3375,latitude:27.339444}, {longitude:83.342222,latitude:27.3425}, {longitude:83.347222,latitude:27.345556}, {longitude:83.351111,latitude:27.349167}, {longitude:83.355,latitude:27.352778}, {longitude:83.358889,latitude:27.356111}, {longitude:83.362778,latitude:27.359722}, {longitude:83.365833,latitude:27.363889}, {longitude:83.369722,latitude:27.3675}, {longitude:83.372778,latitude:27.371667}, {longitude:83.376667,latitude:27.375278}, {longitude:83.378889,latitude:27.379722}, {longitude:83.381944,latitude:27.383889}, {longitude:83.383889,latitude:27.388611}, {longitude:83.386944,latitude:27.392778}, {longitude:83.39,latitude:27.396667}, {longitude:83.392222,latitude:27.401389}, {longitude:83.394167,latitude:27.406111}, {longitude:83.396389,latitude:27.410833}, {longitude:83.396667,latitude:27.416389}, {longitude:83.393056,latitude:27.420833}, {longitude:83.3928052,latitude:27.424963}, {longitude:83.391413,latitude:27.428354}, {longitude:83.3917615,latitude:27.4293448}, {longitude:83.3914271,latitude:27.4300236}, {longitude:83.3896003,latitude:27.4305964}, {longitude:83.3845846,latitude:27.4352247}, {longitude:83.3840341,latitude:27.4368036}, {longitude:83.3846285,latitude:27.4379132}, {longitude:83.3857559,latitude:27.4381315}, {longitude:83.3861658,latitude:27.4387137}, {longitude:83.3862068,latitude:27.4395868}, {longitude:83.3854894,latitude:27.4409148}, {longitude:83.3837472,latitude:27.4412604}, {longitude:83.3838253,latitude:27.4427425}, {longitude:83.3987878,latitude:27.4493767}, {longitude:83.4003114,latitude:27.4545961}, {longitude:83.4015412,latitude:27.4565968}, {longitude:83.4018905,latitude:27.4580063}, {longitude:83.4000724,latitude:27.45946}, {longitude:83.3997426,latitude:27.4642493}, {longitude:83.3995581,latitude:27.465977}, {longitude:83.3980618,latitude:27.4656133}, {longitude:83.3964598,latitude:27.4660984}, {longitude:83.3950714,latitude:27.466896}, {longitude:83.3955375,latitude:27.4693537}, {longitude:83.3954555,latitude:27.4697902}, {longitude:83.3946766,latitude:27.4695902}, {longitude:83.3937132,latitude:27.4696265}, {longitude:83.3929548,latitude:27.4699357}, {longitude:83.3899418,latitude:27.4712451}, {longitude:83.388742,latitude:27.4705553}, {longitude:83.3882405,latitude:27.4711723}, {longitude:83.3884627,latitude:27.4728897}, {longitude:83.3887688,latitude:27.4736712}, {longitude:83.389832,latitude:27.4749413}, {longitude:83.391395,latitude:27.475227}, {longitude:83.3920981,latitude:27.4758881}, {longitude:83.3919932,latitude:27.4773778}, {longitude:83.3916757,latitude:27.4782111}, {longitude:83.3906656,latitude:27.4784788}, {longitude:83.3908646,latitude:27.4796942}, {longitude:83.3897474,latitude:27.4798055}, {longitude:83.3889209,latitude:27.479759}, {longitude:83.3886979,latitude:27.4794098}, {longitude:83.3891879,latitude:27.4789514}, {longitude:83.3972937,latitude:27.4794131}, {longitude:83.4040565,latitude:27.4792114}, {longitude:83.4065792,latitude:27.4785745}, {longitude:83.4078975,latitude:27.4788487}, {longitude:83.4155675,latitude:27.4785433}, {longitude:83.4260764,latitude:27.4779438}, {longitude:83.4412087,latitude:27.4772112}, {longitude:83.4544467,latitude:27.4766671}, {longitude:83.4600581,latitude:27.4763763}, {longitude:83.4649956,latitude:27.4761755}, {longitude:83.4703334,latitude:27.4759521}, {longitude:83.4775174,latitude:27.4756423}, {longitude:83.4887448,latitude:27.4751701}, {longitude:83.49953,latitude:27.4746426}, {longitude:83.5121629,latitude:27.4741159}, {longitude:83.521839,latitude:27.4737171}, {longitude:83.530833,latitude:27.473333}, {longitude:83.5373024,latitude:27.4729847}, {longitude:83.5436489,latitude:27.4726687}, {longitude:83.5488379,latitude:27.4724334}, {longitude:83.5531235,latitude:27.4722302}, {longitude:83.5583045,latitude:27.4719454}, {longitude:83.56573,latitude:27.4716244}, {longitude:83.5724736,latitude:27.4712789}, {longitude:83.5795827,latitude:27.4709505}, {longitude:83.5853459,latitude:27.4707348}, {longitude:83.5919813,latitude:27.4704083}, {longitude:83.5985414,latitude:27.4701606}, {longitude:83.6051084,latitude:27.4698654}, {longitude:83.6124119,latitude:27.4695557}, {longitude:83.6142607,latitude:27.469384}, {longitude:83.6161668,latitude:27.4687519}, {longitude:83.623908,latitude:27.4654162}, {longitude:83.6294529,latitude:27.4630457}, {longitude:83.6356687,latitude:27.4601836}, {longitude:83.640448,latitude:27.4579158}, {longitude:83.6457124,latitude:27.4554541}, {longitude:83.6507629,latitude:27.453077}, {longitude:83.6532438,latitude:27.4519234}, {longitude:83.6607067,latitude:27.4483774}, {longitude:83.6684162,latitude:27.4447732}, {longitude:83.6752375,latitude:27.4416099}, {longitude:83.6795965,latitude:27.4396544}, {longitude:83.6830973,latitude:27.4380637}, {longitude:83.6862324,latitude:27.4366463}, {longitude:83.6936445,latitude:27.4333375}, {longitude:83.7022147,latitude:27.4293276}, {longitude:83.7116191,latitude:27.4249394}, {longitude:83.7222216,latitude:27.4200373}, {longitude:83.7288849,latitude:27.4169622}, {longitude:83.7336777,latitude:27.4147957}, {longitude:83.7384311,latitude:27.4124898}, {longitude:83.7429485,latitude:27.4103595}, {longitude:83.7479642,latitude:27.407994}, {longitude:83.7525,latitude:27.405833}, {longitude:83.7573583,latitude:27.4036212}, {longitude:83.7625,latitude:27.401111}, {longitude:83.7675,latitude:27.399444}, {longitude:83.772222,latitude:27.396667}, {longitude:83.7775,latitude:27.394722}, {longitude:83.782222,latitude:27.392222}, {longitude:83.786667,latitude:27.389444}, {longitude:83.791944,latitude:27.3875}, {longitude:83.796389,latitude:27.384722}, {longitude:83.801667,latitude:27.383056}, {longitude:83.806389,latitude:27.380278}, {longitude:83.811667,latitude:27.378333}, {longitude:83.816111,latitude:27.375556}, {longitude:83.820833,latitude:27.373056}, {longitude:83.826111,latitude:27.371111}, {longitude:83.830556,latitude:27.368333}, {longitude:83.835278,latitude:27.365833}, {longitude:83.839722,latitude:27.363056}, {longitude:83.843889,latitude:27.359444}, {longitude:83.848611,latitude:27.356667}, {longitude:83.853056,latitude:27.354167}, {longitude:83.858333,latitude:27.352222}, {longitude:83.8589373,latitude:27.3523319}, {longitude:83.8564141,latitude:27.3483172}, {longitude:83.8550761,latitude:27.340289}, {longitude:83.8425878,latitude:27.3211105}, {longitude:83.8287614,latitude:27.3228945}, {longitude:83.8546301,latitude:27.3112982}, {longitude:83.8978932,latitude:27.2791854}, {longitude:83.8956632,latitude:27.2680351}, {longitude:83.9019073,latitude:27.2519787}, {longitude:83.9219778,latitude:27.2965799}, {longitude:83.9237619,latitude:27.3166504}, {longitude:83.9081515,latitude:27.3304767}, {longitude:83.9545367,latitude:27.2350303}, {longitude:83.9639029,latitude:27.225218}, {longitude:83.9616728,latitude:27.2136217}, {longitude:83.9763912,latitude:27.2033635}, {longitude:83.9853114,latitude:27.1819549}, {longitude:83.9509686,latitude:27.1409219}, {longitude:83.9523066,latitude:27.1288796}, {longitude:83.9384802,latitude:27.1110391}, {longitude:83.9407103,latitude:27.0896306}, {longitude:83.9549827,latitude:27.0860625}, {longitude:83.9906636,latitude:27.068668}, {longitude:84.005382,latitude:27.0717901}, {longitude:84.0205464,latitude:27.0954287}, {longitude:84.0116261,latitude:27.1047949}, {longitude:84.0241144,latitude:27.0445834}, {longitude:84.0406169,latitude:27.0445834}, {longitude:84.0303586,latitude:27.0120246}, {longitude:84.0495371,latitude:26.990616}, {longitude:84.0432929,latitude:26.9803578}, {longitude:84.0366028,latitude:26.9839259}, {longitude:84.0410629,latitude:26.9678694}, {longitude:84.046861,latitude:26.9620713}, {longitude:84.0361568,latitude:26.9411088}, {longitude:84.0250065,latitude:26.9428928}, {longitude:84.0383868,latitude:26.9308505}, {longitude:84.047753,latitude:26.9317425}, {longitude:84.0490911,latitude:26.9147941}, {longitude:84.0424009,latitude:26.9089959}, {longitude:84.0522132,latitude:26.8916015}, {longitude:84.0762978,latitude:26.8786672}, {longitude:84.085664,latitude:26.8853573}, {longitude:84.0901241,latitude:26.8773291}, {longitude:84.0999364,latitude:26.8880334}, {longitude:84.1008284,latitude:26.9009677}, {longitude:84.1302652,latitude:26.8853573}, {longitude:84.1409694,latitude:26.8875874}, {longitude:84.1316032,latitude:26.8554746}, {longitude:84.1503357,latitude:26.8394181}, {longitude:84.1690682,latitude:26.834512}, {longitude:84.1748663,latitude:26.8358501}, {longitude:84.1806645,latitude:26.8572586}, {longitude:84.2092092,latitude:26.871085}, {longitude:84.2212515,latitude:26.872423}, {longitude:84.240876,latitude:26.8626107}, {longitude:84.2520263,latitude:26.8447703}, {longitude:84.2511343,latitude:26.8304979}, {longitude:84.2484582,latitude:26.8206857}, {longitude:84.24266,latitude:26.8220237}, {longitude:84.2524723,latitude:26.8086434}, {longitude:84.241322,latitude:26.7756385}, {longitude:84.2248196,latitude:26.756906}, {longitude:84.2341858,latitude:26.7395116}, {longitude:84.2475662,latitude:26.7292533}, {longitude:84.278787,latitude:26.7511079}, {longitude:84.2979655,latitude:26.7533379}, {longitude:84.318482,latitude:26.716319}, {longitude:84.3256182,latitude:26.6842062}, {longitude:84.3510408,latitude:26.6204265}, {longitude:84.3706653,latitude:26.6155204}, {longitude:84.3786935,latitude:26.6217646}, {longitude:84.4072383,latitude:26.638267}, {longitude:84.4143744,latitude:26.6271167}, {longitude:84.4023321,latitude:26.6712718}, {longitude:84.3028716,latitude:26.6173045}, {longitude:84.2948434,latitude:26.6066002}, {longitude:84.279233,latitude:26.6066002}, {longitude:84.2720968,latitude:26.599464}, {longitude:84.202073,latitude:26.6244406}, {longitude:84.1521197,latitude:26.638713}, {longitude:84.1485516,latitude:26.6275627}, {longitude:84.1061805,latitude:26.637375}, {longitude:84.1043965,latitude:26.6324688}, {longitude:84.0816499,latitude:26.6427271}, {longitude:84.0825419,latitude:26.599464}, {longitude:84.0571193,latitude:26.5735953}, {longitude:84.0486451,latitude:26.5504027}, {longitude:84.0428469,latitude:26.5410365}, {longitude:84.0004758,latitude:26.5249801}, {longitude:83.9741612,latitude:26.5321163}, {longitude:83.9625649,latitude:26.5084777}, {longitude:83.9456164,latitude:26.5102617}, {longitude:83.9460624,latitude:26.5182899}, {longitude:83.9349122,latitude:26.5245341}, {longitude:83.9023533,latitude:26.5182899}, {longitude:83.9072594,latitude:26.5004495}, {longitude:83.9023533,latitude:26.4500502}, {longitude:83.9817434,latitude:26.4344398}, {longitude:83.9906636,latitude:26.4478201}, {longitude:84.0018139,latitude:26.443806}, {longitude:84.0111801,latitude:26.4482661}, {longitude:84.0303586,latitude:26.4130312}, {longitude:84.0548892,latitude:26.4018809}, {longitude:84.0718377,latitude:26.3844865}, {longitude:84.0923542,latitude:26.3902846}, {longitude:84.1712982,latitude:26.3737822}, {longitude:84.1672841,latitude:26.3381013}, {longitude:84.1815565,latitude:26.3171388}, {longitude:84.1686221,latitude:26.2930541}, {longitude:84.1712982,latitude:26.2676315}, {longitude:84.1552418,latitude:26.2578192}, {longitude:84.1454296,latitude:26.2618333}, {longitude:84.1574719,latitude:26.2439929}, {longitude:84.1106407,latitude:26.2417628}, {longitude:84.0923542,latitude:26.2404248}, {longitude:84.0914622,latitude:26.2341806}, {longitude:84.0794199,latitude:26.2319506}, {longitude:84.0794199,latitude:26.2212463}, {longitude:84.0495371,latitude:26.2257064}, {longitude:84.0241144,latitude:26.2199083}, {longitude:84.0192083,latitude:26.209204}, {longitude:84.0236684,latitude:26.2007298}, {longitude:84.0089501,latitude:26.1993917}, {longitude:84.0022599,latitude:26.1900255}, {longitude:84.0086671,latitude:26.1743189}, {longitude:84.0276825,latitude:26.1628188}, {longitude:84.0272365,latitude:26.1530066}, {longitude:84.0183163,latitude:26.1449784}, {longitude:84.0424009,latitude:26.131598}, {longitude:84.0424009,latitude:26.1115275}, {longitude:84.0490911,latitude:26.0994852}, {longitude:84.0910162,latitude:26.0963631}, {longitude:84.1159928,latitude:26.0749546}, {longitude:84.1365093,latitude:26.0468558}, {longitude:84.1672841,latitude:26.0254473}, {longitude:84.1726362,latitude:25.9986866}, {longitude:84.1663921,latitude:25.9915504}, {longitude:84.1922608,latitude:25.9893204}, {longitude:84.201627,latitude:26.0004707}, {longitude:84.2961814,latitude:25.9469493}, {longitude:84.3514868,latitude:25.9594376}, {longitude:84.4081303,latitude:25.9313389}, {longitude:84.4232947,latitude:25.8925359}, {longitude:84.4505014,latitude:25.8903058}, {longitude:84.4665578,latitude:25.8836157}, {longitude:84.4852903,latitude:25.8894138}, {longitude:84.5066988,latitude:25.8724654}, {longitude:84.5200792,latitude:25.8613151}, {longitude:84.5263233,latitude:25.8555169}, {longitude:84.5405957,latitude:25.8622071}, {longitude:84.5285534,latitude:25.8778175}, {longitude:84.5468398,latitude:25.8412446}, {longitude:84.5566521,latitude:25.8376765}, {longitude:84.5441638,latitude:25.8300943}, {longitude:84.5267693,latitude:25.8341084}, {longitude:84.6204317,latitude:25.7939674}, {longitude:84.6101735,latitude:25.7774649}, {longitude:84.6208777,latitude:25.7725588}, {longitude:84.6003612,latitude:25.7605165}, {longitude:84.5954551,latitude:25.739108}, {longitude:84.6222158,latitude:25.7471362}, {longitude:84.63069,latitude:25.7284037}, {longitude:84.5557601,latitude:25.7364319}, {longitude:84.5356896,latitude:25.7266196}, {longitude:84.5303374,latitude:25.7248356}, {longitude:84.5312294,latitude:25.6927228}, {longitude:84.5245393,latitude:25.6842485}, {longitude:84.5165111,latitude:25.6775584}, {longitude:84.5058068,latitude:25.6824645}, {longitude:84.4884123,latitude:25.6780044}, {longitude:84.4893044,latitude:25.6909387}, {longitude:84.4665578,latitude:25.6855866}, {longitude:84.4603136,latitude:25.6904927}, {longitude:84.4629897,latitude:25.6985209}, {longitude:84.4487173,latitude:25.7145773}, {longitude:84.4241867,latitude:25.7119013}, {longitude:84.4237407,latitude:25.6949528}, {longitude:84.4018861,latitude:25.6994129}, {longitude:84.3938579,latitude:25.7288497}, {longitude:84.3813696,latitude:25.7342018}, {longitude:84.3693273,latitude:25.7359859}, {longitude:84.3675432,latitude:25.741784}, {longitude:84.3546089,latitude:25.7435681}, {longitude:84.3314163,latitude:25.7444601}, {longitude:84.3242801,latitude:25.7333098}, {longitude:84.3800316,latitude:25.7484742}, {longitude:84.3885058,latitude:25.7444601}, {longitude:84.318482,latitude:25.6717602}, {longitude:84.2926133,latitude:25.6686381}, {longitude:84.2854771,latitude:25.661502}, {longitude:84.2836931,latitude:25.6771124}, {longitude:84.2172374,latitude:25.664178}, {longitude:84.202965,latitude:25.6695302}, {longitude:84.1944908,latitude:25.703427}, {longitude:84.1445375,latitude:25.7208215}, {longitude:84.1369553,latitude:25.7239436}, {longitude:84.1356173,latitude:25.7168074}, {longitude:84.1266971,latitude:25.7185914}, {longitude:84.123129,latitude:25.7239436}, {longitude:84.1351713,latitude:25.7315258}, {longitude:84.1481056,latitude:25.7310797}, {longitude:84.0945842,latitude:25.7203755}, {longitude:84.0696076,latitude:25.6953988}, {longitude:84.0798659,latitude:25.6525817}, {longitude:84.0762978,latitude:25.6374173}, {longitude:84.0143022,latitude:25.6160088}, {longitude:83.9442784,latitude:25.5673936}, {longitude:83.9210858,latitude:25.5615954}, {longitude:83.8961092,latitude:25.5281445}, {longitude:83.8729166,latitude:25.5205624}, {longitude:83.8666724,latitude:25.4933557}, {longitude:83.851062,latitude:25.4737312}, {longitude:83.8292074,latitude:25.4616888}, {longitude:83.8332216,latitude:25.4500925}, {longitude:83.8385737,latitude:25.4367122}, {longitude:83.8051228,latitude:25.4246699}, {longitude:83.7944186,latitude:25.4318061}, {longitude:83.7993247,latitude:25.4108435}, {longitude:83.7823762,latitude:25.3988012}, {longitude:83.7636438,latitude:25.3988012}, {longitude:83.7587376,latitude:25.4005853}, {longitude:83.7533855,latitude:25.4041534}, {longitude:83.7498174,latitude:25.3921111}, {longitude:83.7618597,latitude:25.388097}, {longitude:83.7377751,latitude:25.4010313}, {longitude:83.7270708,latitude:25.3983552}, {longitude:83.7154745,latitude:25.3992472}, {longitude:83.7012022,latitude:25.3858669}, {longitude:83.6896059,latitude:25.3858669}, {longitude:83.694512,latitude:25.3961252}, {longitude:83.6753335,latitude:25.3782847}, {longitude:83.6891599,latitude:25.3711485}, {longitude:83.6690893,latitude:25.3644584}, {longitude:83.6588311,latitude:25.3631203}, {longitude:83.6601691,latitude:25.3711485}, {longitude:83.6503569,latitude:25.3680264}, {longitude:83.6423287,latitude:25.3412658}, {longitude:83.6298403,latitude:25.3430498}, {longitude:83.6222581,latitude:25.3296695}, {longitude:83.6070938,latitude:25.3323455}, {longitude:83.5879153,latitude:25.3265474}, {longitude:83.5642767,latitude:25.3158431}, {longitude:83.5642767,latitude:25.3082609}, {longitude:83.5464362,latitude:25.311829}, {longitude:83.5348399,latitude:25.3153971}, {longitude:83.5254737,latitude:25.2993407}, {longitude:83.5178915,latitude:25.3024628}, {longitude:83.5178915,latitude:25.2930965}, {longitude:83.5080792,latitude:25.2864064}, {longitude:83.5027271,latitude:25.2895284}, {longitude:83.4946989,latitude:25.2872984}, {longitude:83.498267,latitude:25.2828383}, {longitude:83.4862247,latitude:25.2797162}, {longitude:83.4804265,latitude:25.2823923}, {longitude:83.460356,latitude:25.2520635}, {longitude:83.4398395,latitude:25.2591997}, {longitude:83.4313653,latitude:25.2444813}, {longitude:83.4193229,latitude:25.2462653}, {longitude:83.4193229,latitude:25.2534015}, {longitude:83.4090647,latitude:25.2493874}, {longitude:83.3796279,latitude:25.2119224}, {longitude:83.3881021,latitude:25.2070163}, {longitude:83.3720457,latitude:25.2012182}, {longitude:83.3497452,latitude:25.1989881}, {longitude:83.3359188,latitude:25.1833777}, {longitude:83.3479611,latitude:25.1784716}, {longitude:83.343055,latitude:25.1588471}, {longitude:83.3524212,latitude:25.1517109}, {longitude:83.3573274,latitude:25.1240582}, {longitude:83.3537593,latitude:25.1102318}, {longitude:83.3399329,latitude:25.1124619}, {longitude:83.3278906,latitude:25.0611706}, {longitude:83.3220925,latitude:25.0558184}, {longitude:83.3336887,latitude:25.0509123}, {longitude:83.3158483,latitude:25.0272737}, {longitude:83.3198624,latitude:25.0156774}, {longitude:83.343947,latitude:25.0103253}, {longitude:83.3457311,latitude:24.9920388}, {longitude:83.3582194,latitude:24.9139868}, {longitude:83.3515292,latitude:24.9032825}, {longitude:83.3600034,latitude:24.8916862}, {longitude:83.3550973,latitude:24.8854421}, {longitude:83.3644635,latitude:24.8787519}, {longitude:83.3542053,latitude:24.8707237}, {longitude:83.3684776,latitude:24.8631415}, {longitude:83.3903322,latitude:24.8332587}, {longitude:83.3956843,latitude:24.8109582}, {longitude:83.3867641,latitude:24.7841975}, {longitude:83.4193229,latitude:24.7699251}, {longitude:83.4532198,latitude:24.7333522}, {longitude:83.4799805,latitude:24.7378123}, {longitude:83.4887294,latitude:24.7252585}, {longitude:83.4920228,latitude:24.7052535}, {longitude:83.4808725,latitude:24.7056995}, {longitude:83.5125393,latitude:24.6833989}, {longitude:83.499605,latitude:24.6686805}, {longitude:83.4973749,latitude:24.6517321}, {longitude:83.5241356,latitude:24.6441499}, {longitude:83.5205675,latitude:24.6298775}, {longitude:83.5415301,latitude:24.6245254}, {longitude:83.5174455,latitude:24.6031168}, {longitude:83.5250277,latitude:24.5803703}, {longitude:83.5169994,latitude:24.5491495}, {longitude:83.4933608,latitude:24.5420133}, {longitude:83.498267,latitude:24.5268489}, {longitude:83.3930083,latitude:24.5009802}, {longitude:83.3925623,latitude:24.4804637}, {longitude:83.381412,latitude:24.4554871}, {longitude:83.3898862,latitude:24.4376466}, {longitude:83.4005905,latitude:24.4372006}, {longitude:83.4001445,latitude:24.4086559}, {longitude:83.4300272,latitude:24.4037497}, {longitude:83.4514358,latitude:24.3649467}, {longitude:83.4411775,latitude:24.3609326}, {longitude:83.4264591,latitude:24.3399701}, {longitude:83.4032665,latitude:24.337294}, {longitude:83.3760598,latitude:24.3145474}, {longitude:83.3805199,latitude:24.3002751}, {longitude:83.3961303,latitude:24.2935849}, {longitude:83.4019285,latitude:24.2659322}, {longitude:83.3971441,latitude:24.2553564}, {longitude:83.3934543,latitude:24.2471997}, {longitude:83.3919838,latitude:24.2461377}, {longitude:83.3773979,latitude:24.2356034}, {longitude:83.3782899,latitude:24.2008145}, {longitude:83.3488531,latitude:24.1263306}, {longitude:83.3390409,latitude:24.1102742}, {longitude:83.3234305,latitude:24.101354}, {longitude:83.3149563,latitude:24.1071521}, {longitude:83.3109422,latitude:24.0928798}, {longitude:83.2890876,latitude:24.0728093}, {longitude:83.2774913,latitude:24.0500627}, {longitude:83.2761533,latitude:24.023302}, {longitude:83.2587588,latitude:24.0117057}, {longitude:83.2418104,latitude:24.0139357}, {longitude:83.2369043,latitude:24.0014474}, {longitude:83.2159417,latitude:23.9894051}, {longitude:83.2083595,latitude:23.9729027}, {longitude:83.2110356,latitude:23.9604144}, {longitude:83.1896271,latitude:23.9211654}, {longitude:83.1584316,latitude:23.9065381}, {longitude:83.1584739,latitude:23.9062723}, {longitude:83.1584069,latitude:23.9059763}, {longitude:83.1577371,latitude:23.9056753}, {longitude:83.156791,latitude:23.9054299}, {longitude:83.1555286,latitude:23.9051768}, {longitude:83.1547184,latitude:23.9049544}, {longitude:83.1543324,latitude:23.9043537}, {longitude:83.1540533,latitude:23.9033892}, {longitude:83.1544944,latitude:23.9022936}, {longitude:83.1276315,latitude:23.8899446}, {longitude:83.0865984,latitude:23.8783483}, {longitude:83.0611758,latitude:23.8752262}, {longitude:83.0366451,latitude:23.8832544}, {longitude:82.953687,latitude:23.8725501}, {longitude:82.9389686,latitude:23.8756722}, {longitude:82.9224662,latitude:23.8926206}, {longitude:82.8809871,latitude:23.9104611}, {longitude:82.8488743,latitude:23.947034}, {longitude:82.8207756,latitude:23.9648745}, {longitude:82.8078413,latitude:23.9630904}, {longitude:82.798921,latitude:23.9706726}, {longitude:82.797137,latitude:24.0050155}, {longitude:82.7525359,latitude:24.0085836}, {longitude:82.7418316,latitude:24.0215179}, {longitude:82.7400475,latitude:24.0308842}, {longitude:82.7556579,latitude:24.0438185}, {longitude:82.7547659,latitude:24.0732553}, {longitude:82.7480757,latitude:24.0803914}, {longitude:82.7413856,latitude:24.0750393}, {longitude:82.7262212,latitude:24.0826215}, {longitude:82.7083807,latitude:24.0808375}, {longitude:82.7012445,latitude:24.0968939}, {longitude:82.6649445,latitude:24.1235576}, {longitude:82.6633336,latitude:24.1241006}, {longitude:82.6575354,latitude:24.1348048}, {longitude:82.6709158,latitude:24.1584434}, {longitude:82.67939,latitude:24.1566594}, {longitude:82.6883102,latitude:24.1441711}, {longitude:82.720423,latitude:24.1388189}, {longitude:82.7230991,latitude:24.1566594}, {longitude:82.7360334,latitude:24.1682557}, {longitude:82.7271132,latitude:24.2231151}, {longitude:82.7498598,latitude:24.2360494}, {longitude:82.7507518,latitude:24.2681623}, {longitude:82.7641322,latitude:24.2922469}, {longitude:82.7605641,latitude:24.3729749}, {longitude:82.7543199,latitude:24.3796651}, {longitude:82.7467377,latitude:24.3725289}, {longitude:82.718639,latitude:24.3711909}, {longitude:82.7070427,latitude:24.3850173}, {longitude:82.7257752,latitude:24.4260503}, {longitude:82.719977,latitude:24.4296184}, {longitude:82.7329114,latitude:24.4327405}, {longitude:82.7248831,latitude:24.4447828}, {longitude:82.7306813,latitude:24.4702054}, {longitude:82.7239911,latitude:24.4764496}, {longitude:82.7364794,latitude:24.4996422}, {longitude:82.7155169,latitude:24.5134685}, {longitude:82.7458457,latitude:24.5415673}, {longitude:82.7413856,latitude:24.5545016}, {longitude:82.7123948,latitude:24.5562856}, {longitude:82.7601181,latitude:24.5999948}, {longitude:82.7677002,latitude:24.610699}, {longitude:82.796245,latitude:24.5995488}, {longitude:82.8007051,latitude:24.5527176}, {longitude:82.7627941,latitude:24.6454879}, {longitude:82.6950004,latitude:24.6441499}, {longitude:82.6958924,latitude:24.6758167}, {longitude:82.6655636,latitude:24.6999013}, {longitude:82.5875116,latitude:24.6735866}, {longitude:82.5741313,latitude:24.6628824}, {longitude:82.5290841,latitude:24.6521781}, {longitude:82.5183799,latitude:24.6784928}, {longitude:82.486713,latitude:24.6753707}, {longitude:82.4764548,latitude:24.6833989}, {longitude:82.4483561,latitude:24.6905351}, {longitude:82.444788,latitude:24.6802768}, {longitude:82.4322997,latitude:24.7061455}, {longitude:82.4202573,latitude:24.7052535}, {longitude:82.4015249,latitude:24.6838449}, {longitude:82.4117831,latitude:24.6579762}, {longitude:82.4033089,latitude:24.6361217}, {longitude:82.405539,latitude:24.6173892}, {longitude:82.4144592,latitude:24.6062389}, {longitude:82.4091071,latitude:24.5977647}, {longitude:82.3609378,latitude:24.6017788}, {longitude:82.3457734,latitude:24.6187272}, {longitude:82.329717,latitude:24.6236334}, {longitude:82.3092005,latitude:24.6129291}, {longitude:82.2940361,latitude:24.6138211}, {longitude:82.2980502,latitude:24.6566382}, {longitude:82.2918061,latitude:24.6646664}, {longitude:82.2962662,latitude:24.6700186}, {longitude:82.2851159,latitude:24.6700186}, {longitude:82.2784257,latitude:24.6807228}, {longitude:82.2668294,latitude:24.6784928}, {longitude:82.2583552,latitude:24.6820609}, {longitude:82.2632613,latitude:24.686521}, {longitude:82.2480969,latitude:24.6753707}, {longitude:82.2530031,latitude:24.6660045}, {longitude:82.2351626,latitude:24.6660045}, {longitude:82.2298105,latitude:24.6695725}, {longitude:82.2356086,latitude:24.6762627}, {longitude:82.2284724,latitude:24.6909811}, {longitude:82.2431908,latitude:24.7016854}, {longitude:82.2414068,latitude:24.7382583}, {longitude:82.2382847,latitude:24.7543147}, {longitude:82.249435,latitude:24.7565448}, {longitude:82.2436368,latitude:24.7757233}, {longitude:82.2208902,latitude:24.767249}, {longitude:82.2092939,latitude:24.7596669}, {longitude:82.1994817,latitude:24.7520847}, {longitude:82.1914535,latitude:24.7529767}, {longitude:82.1954676,latitude:24.763681}, {longitude:82.1892234,latitude:24.7824134}, {longitude:82.2048338,latitude:24.7855355}, {longitude:82.2092939,latitude:24.7815214}, {longitude:82.2088479,latitude:24.7975778}, {longitude:82.1883314,latitude:24.7980238}, {longitude:82.1789652,latitude:24.804714}, {longitude:82.173167,latitude:24.8011459}, {longitude:82.1629087,latitude:24.80516}, {longitude:82.1593407,latitude:24.7922257}, {longitude:82.132134,latitude:24.804268}, {longitude:82.1263358,latitude:24.7940097}, {longitude:82.1120634,latitude:24.7922257}, {longitude:82.1000211,latitude:24.8100661}, {longitude:82.0817347,latitude:24.8221085}, {longitude:82.0371335,latitude:24.8328127}, {longitude:82.0241992,latitude:24.844409}, {longitude:82.0050207,latitude:24.8506532}, {longitude:81.9680018,latitude:24.843963}, {longitude:81.9590815,latitude:24.8305827}, {longitude:81.9345509,latitude:24.8515452}, {longitude:81.9265227,latitude:24.8609114}, {longitude:81.9283067,latitude:24.8729538}, {longitude:81.8966399,latitude:24.8925783}, {longitude:81.9122503,latitude:24.9309352}, {longitude:81.9042221,latitude:24.9420855}, {longitude:81.9015461,latitude:24.9826726}, {longitude:81.8944099,latitude:24.9875787}, {longitude:81.8859357,latitude:24.9929308}, {longitude:81.8814755,latitude:24.999621}, {longitude:81.8930718,latitude:24.999621}, {longitude:81.899762,latitude:25.0072032}, {longitude:81.8912878,latitude:25.0134474}, {longitude:81.8462406,latitude:25.0072032}, {longitude:81.8292922,latitude:25.0192455}, {longitude:81.7891512,latitude:25.0103253}, {longitude:81.7383059,latitude:25.03887}, {longitude:81.7325077,latitude:25.0486823}, {longitude:81.7164513,latitude:25.0531424}, {longitude:81.7133292,latitude:25.040208}, {longitude:81.705301,latitude:25.037978}, {longitude:81.6821084,latitude:25.0437761}, {longitude:81.6830005,latitude:25.0567105}, {longitude:81.6758643,latitude:25.0660767}, {longitude:81.6620379,latitude:25.0651847}, {longitude:81.6584698,latitude:25.0799031}, {longitude:81.6321551,latitude:25.1030957}, {longitude:81.6227889,latitude:25.1111239}, {longitude:81.625019,latitude:25.1204901}, {longitude:81.6165447,latitude:25.1227202}, {longitude:81.6022724,latitude:25.117368}, {longitude:81.5911221,latitude:25.1231662}, {longitude:81.5920141,latitude:25.1365465}, {longitude:81.6098546,latitude:25.1472508}, {longitude:81.6152067,latitude:25.1441287}, {longitude:81.6187748,latitude:25.1597391}, {longitude:81.6053945,latitude:25.1655373}, {longitude:81.6098546,latitude:25.1735655}, {longitude:81.58577,latitude:25.1864998}, {longitude:81.5817559,latitude:25.1682133}, {longitude:81.5665915,latitude:25.195866}, {longitude:81.549197,latitude:25.1864998}, {longitude:81.5340326,latitude:25.1882838}, {longitude:81.507718,latitude:25.1851618}, {longitude:81.507718,latitude:25.1762415}, {longitude:81.5300185,latitude:25.1633072}, {longitude:81.5188682,latitude:25.1517109}, {longitude:81.5246664,latitude:25.1436827}, {longitude:81.4952296,latitude:25.1521569}, {longitude:81.4894315,latitude:25.1343165}, {longitude:81.471591,latitude:25.1352085}, {longitude:81.4818493,latitude:25.117368}, {longitude:81.4943376,latitude:25.1017576}, {longitude:81.507718,latitude:25.1066637}, {longitude:81.4947836,latitude:25.0821331}, {longitude:81.4863094,latitude:25.0839172}, {longitude:81.4836333,latitude:25.0749969}, {longitude:81.4582107,latitude:25.0865932}, {longitude:81.4586567,latitude:25.0928374}, {longitude:81.4381402,latitude:25.1102318}, {longitude:81.430112,latitude:25.1334244}, {longitude:81.4060274,latitude:25.1236122}, {longitude:81.4002292,latitude:25.1338704}, {longitude:81.3864029,latitude:25.1338704}, {longitude:81.391309,latitude:25.1231662}, {longitude:81.4006752,latitude:25.1075558}, {longitude:81.3645483,latitude:25.1378845}, {longitude:81.3493839,latitude:25.1673213}, {longitude:81.3360036,latitude:25.1664293}, {longitude:81.3297594,latitude:25.1726734}, {longitude:81.312811,latitude:25.1637532}, {longitude:81.3047828,latitude:25.1673213}, {longitude:81.3110269,latitude:25.1490348}, {longitude:81.3083509,latitude:25.1258422}, {longitude:81.3168251,latitude:25.11603}, {longitude:81.3230692,latitude:25.11826}, {longitude:81.2749,latitude:25.1534949}, {longitude:81.2691018,latitude:25.1677673}, {longitude:81.2610736,latitude:25.1441287}, {longitude:81.2579516,latitude:25.116922}, {longitude:81.2459093,latitude:25.1053257}, {longitude:81.2592896,latitude:25.0874853}, {longitude:81.2530454,latitude:25.079011}, {longitude:81.2615197,latitude:25.0683068}, {longitude:81.2307449,latitude:25.0179075}, {longitude:81.2017541,latitude:24.9956069}, {longitude:81.2129044,latitude:24.9817805}, {longitude:81.1999701,latitude:24.9670622}, {longitude:81.2062142,latitude:24.9394095}, {longitude:81.2124584,latitude:24.9345033}, {longitude:81.1990781,latitude:24.9349493}, {longitude:81.1995241,latitude:24.9269211}, {longitude:81.1843597,latitude:24.9291512}, {longitude:81.1522469,latitude:24.9162169}, {longitude:81.1402045,latitude:24.9166629}, {longitude:81.1344064,latitude:24.8939163}, {longitude:81.1165659,latitude:24.9019445}, {longitude:81.0893592,latitude:24.9246911}, {longitude:81.0764249,latitude:24.9523438}, {longitude:81.0264716,latitude:24.9550199}, {longitude:81.0193354,latitude:24.9452076}, {longitude:81.0211195,latitude:24.9380714}, {longitude:81.0166594,latitude:24.9322733}, {longitude:81.000157,latitude:24.9403015}, {longitude:81.0095232,latitude:24.9541278}, {longitude:80.9791944,latitude:24.9380714}, {longitude:80.9720582,latitude:24.9394095}, {longitude:80.9809785,latitude:24.9295972}, {longitude:80.9510957,latitude:24.9269211}, {longitude:80.9448515,latitude:24.9679542}, {longitude:80.9368233,latitude:24.9724143}, {longitude:80.8971283,latitude:24.9568039}, {longitude:80.8931142,latitude:24.9505597}, {longitude:80.88464,latitude:24.9487757}, {longitude:80.8748277,latitude:24.9407475}, {longitude:80.9002504,latitude:24.9394095}, {longitude:80.8408801,latitude:24.9354202}, {longitude:80.8021279,latitude:24.9443156}, {longitude:80.8030199,latitude:24.9568039}, {longitude:80.7900856,latitude:24.9719683}, {longitude:80.8186303,latitude:24.9737523}, {longitude:80.8248745,latitude:24.9862407}, {longitude:80.8364708,latitude:24.9862407}, {longitude:80.8480671,latitude:24.998283}, {longitude:80.8502971,latitude:25.0031891}, {longitude:80.8351327,latitude:25.039762}, {longitude:80.8266585,latitude:25.039316}, {longitude:80.8409309,latitude:25.0513583}, {longitude:80.8708136,latitude:25.0709828}, {longitude:80.8752738,latitude:25.079457}, {longitude:80.8641235,latitude:25.0883773}, {longitude:80.8730437,latitude:25.1026496}, {longitude:80.8650155,latitude:25.1236122}, {longitude:80.8752738,latitude:25.1445747}, {longitude:80.8882081,latitude:25.1414526}, {longitude:80.9047105,latitude:25.1601851}, {longitude:80.885978,latitude:25.1771336}, {longitude:80.8779498,latitude:25.19542}, {longitude:80.8641235,latitude:25.1878378}, {longitude:80.8364708,latitude:25.192744}, {longitude:80.8422689,latitude:25.1713354}, {longitude:80.8320106,latitude:25.1414526}, {longitude:80.8378088,latitude:25.1276263}, {longitude:80.8186303,latitude:25.1307484}, {longitude:80.8097101,latitude:25.1307484}, {longitude:80.808372,latitude:25.1410066}, {longitude:80.7994518,latitude:25.1463588}, {longitude:80.7820574,latitude:25.1369925}, {longitude:80.7740292,latitude:25.1468048}, {longitude:80.7379022,latitude:25.1347625}, {longitude:80.7254139,latitude:25.1227202}, {longitude:80.7200618,latitude:25.117368}, {longitude:80.7173857,latitude:25.1289643}, {longitude:80.7008833,latitude:25.1280723}, {longitude:80.6995453,latitude:25.1369925}, {longitude:80.7231839,latitude:25.1485888}, {longitude:80.7205078,latitude:25.1013116}, {longitude:80.7459304,latitude:25.0995276}, {longitude:80.7512826,latitude:25.0910533}, {longitude:80.7758132,latitude:25.0843632}, {longitude:80.7811653,latitude:25.0696448}, {longitude:80.7758132,latitude:25.0616166}, {longitude:80.766001,latitude:25.0611706}, {longitude:80.7334421,latitude:25.0625086}, {longitude:80.727198,latitude:25.0718749}, {longitude:80.7142636,latitude:25.0678608}, {longitude:80.6794747,latitude:25.0816871}, {longitude:80.6683245,latitude:25.0865932}, {longitude:80.650484,latitude:25.0874853}, {longitude:80.6562821,latitude:25.0745509}, {longitude:80.6718925,latitude:25.0674147}, {longitude:80.6736766,latitude:25.0540344}, {longitude:80.6353196,latitude:25.0977435}, {longitude:80.6201552,latitude:25.0897153}, {longitude:80.613465,latitude:25.1066637}, {longitude:80.6014227,latitude:25.1044337}, {longitude:80.5875964,latitude:25.1088938}, {longitude:80.6014227,latitude:25.1227202}, {longitude:80.6085589,latitude:25.1329784}, {longitude:80.6250613,latitude:25.1378845}, {longitude:80.6321975,latitude:25.1231662}, {longitude:80.6192632,latitude:25.1200441}, {longitude:80.5907185,latitude:25.1316404}, {longitude:80.6058829,latitude:25.1494808}, {longitude:80.5947326,latitude:25.155279}, {longitude:80.5875964,latitude:25.0857012}, {longitude:80.5679719,latitude:25.0852552}, {longitude:80.5626197,latitude:25.0914994}, {longitude:80.5599437,latitude:25.0749969}, {longitude:80.5434412,latitude:25.0678608}, {longitude:80.5189106,latitude:25.0821331}, {longitude:80.5001781,latitude:25.0897153}, {longitude:80.5095444,latitude:25.1008656}, {longitude:80.4908119,latitude:25.078119}, {longitude:80.5019622,latitude:25.0674147}, {longitude:80.493042,latitude:25.0544804}, {longitude:80.49438,latitude:25.0451142}, {longitude:80.4702954,latitude:25.0446682}, {longitude:80.4600371,latitude:25.0696448}, {longitude:80.456023,latitude:25.0736589}, {longitude:80.4457647,latitude:25.0656307}, {longitude:80.4408586,latitude:25.0821331}, {longitude:80.4243562,latitude:25.0714288}, {longitude:80.4100838,latitude:25.076781}, {longitude:80.3940274,latitude:25.0718749}, {longitude:80.4051777,latitude:25.0607246}, {longitude:80.379309,latitude:25.0558184}, {longitude:80.3659287,latitude:25.0576025}, {longitude:80.3641447,latitude:25.0477902}, {longitude:80.3802011,latitude:25.0415461}, {longitude:80.3717268,latitude:25.0326258}, {longitude:80.3677127,latitude:25.0254897}, {longitude:80.3587925,latitude:25.0259357}, {longitude:80.3480882,latitude:25.0165694}, {longitude:80.33783,latitude:25.0210296}, {longitude:80.3257877,latitude:25.0031891}, {longitude:80.3141914,latitude:25.0036351}, {longitude:80.2669142,latitude:25.0303958}, {longitude:80.2776184,latitude:25.0437761}, {longitude:80.2820786,latitude:25.0634006}, {longitude:80.3106233,latitude:25.0821331}, {longitude:80.3132994,latitude:25.1013116}, {longitude:80.3440741,latitude:25.1249502}, {longitude:80.3512103,latitude:25.1450207}, {longitude:80.3868912,latitude:25.1628612}, {longitude:80.415436,latitude:25.1650912}, {longitude:80.4239102,latitude:25.1740115}, {longitude:80.4016096,latitude:25.2208427}, {longitude:80.3989335,latitude:25.2431432}, {longitude:80.3909053,latitude:25.2458193}, {longitude:80.377971,latitude:25.2373451}, {longitude:80.3579005,latitude:25.2511715}, {longitude:80.3409521,latitude:25.2783781}, {longitude:80.3119613,latitude:25.2819462}, {longitude:80.3039331,latitude:25.2890824}, {longitude:80.3115153,latitude:25.3341296}, {longitude:80.3025951,latitude:25.3582142}, {longitude:80.3132994,latitude:25.3720405}, {longitude:80.3088392,latitude:25.3916651}, {longitude:80.2954589,latitude:25.4077215}, {longitude:80.2736043,latitude:25.4251159}, {longitude:80.2557639,latitude:25.428684}, {longitude:80.2450596,latitude:25.4050454}, {longitude:80.2294492,latitude:25.4108435}, {longitude:80.2111627,latitude:25.4041534}, {longitude:80.1937683,latitude:25.4161957}, {longitude:80.1879701,latitude:25.3836368}, {longitude:80.1589794,latitude:25.3778387}, {longitude:80.1589794,latitude:25.3662424}, {longitude:80.1371248,latitude:25.3555381}, {longitude:80.1268666,latitude:25.3408197}, {longitude:80.0960918,latitude:25.351524}, {longitude:80.0831575,latitude:25.3559841}, {longitude:80.0702231,latitude:25.3430498}, {longitude:80.0207159,latitude:25.3430498}, {longitude:80.0100116,latitude:25.3247633}, {longitude:80.0126876,latitude:25.3015707}, {longitude:79.9912791,latitude:25.2832843}, {longitude:79.9966312,latitude:25.2685659}, {longitude:79.949354,latitude:25.2654438}, {longitude:79.9426639,latitude:25.271688}, {longitude:79.9332976,latitude:25.2632138}, {longitude:79.9324056,latitude:25.2502794}, {longitude:79.9208093,latitude:25.2507254}, {longitude:79.9025228,latitude:25.2569696}, {longitude:79.8936026,latitude:25.2462653}, {longitude:79.8726401,latitude:25.2538475}, {longitude:79.8610438,latitude:25.2431432}, {longitude:79.8472174,latitude:25.2400212}, {longitude:79.8472174,latitude:25.232439}, {longitude:79.8628278,latitude:25.2190586}, {longitude:79.8516775,latitude:25.2034482}, {longitude:79.8623818,latitude:25.1771336}, {longitude:79.8605978,latitude:25.156171}, {longitude:79.872194,latitude:25.1503729}, {longitude:79.8476634,latitude:25.1334244}, {longitude:79.8427573,latitude:25.116476}, {longitude:79.831161,latitude:25.0981895}, {longitude:79.8186727,latitude:25.1102318}, {longitude:79.8556916,latitude:25.0986355}, {longitude:79.7691654,latitude:25.1253962}, {longitude:79.7455268,latitude:25.1436827}, {longitude:79.7178741,latitude:25.1338704}, {longitude:79.676395,latitude:25.1369925}, {longitude:79.6661368,latitude:25.1271803}, {longitude:79.638038,latitude:25.1311944}, {longitude:79.6201976,latitude:25.1427907}, {longitude:79.6028031,latitude:25.154833}, {longitude:79.5898688,latitude:25.1472508}, {longitude:79.597897,latitude:25.1316404}, {longitude:79.5738124,latitude:25.1592931}, {longitude:79.5653382,latitude:25.1775796}, {longitude:79.5501738,latitude:25.1695514}, {longitude:79.5136009,latitude:25.1258422}, {longitude:79.517169,latitude:25.1017576}, {longitude:79.5002205,latitude:25.0990816}, {longitude:79.4899623,latitude:25.0825791}, {longitude:79.4605255,latitude:25.0964055}, {longitude:79.462885,latitude:25.1169101}, {longitude:79.4571568,latitude:25.1191302}, {longitude:79.4351028,latitude:25.1267343}, {longitude:79.4310887,latitude:25.1352085}, {longitude:79.4150323,latitude:25.1392226}, {longitude:79.4150323,latitude:25.1231662}, {longitude:79.3989759,latitude:25.1133539}, {longitude:79.3891637,latitude:25.1204901}, {longitude:79.3802434,latitude:25.153941}, {longitude:79.3838115,latitude:25.1704434}, {longitude:79.403882,latitude:25.1896219}, {longitude:79.4078962,latitude:25.2074623}, {longitude:79.4319808,latitude:25.2030022}, {longitude:79.442685,latitude:25.2123685}, {longitude:79.442239,latitude:25.2208427}, {longitude:79.4270746,latitude:25.2248568}, {longitude:79.441793,latitude:25.2516175}, {longitude:79.4484832,latitude:25.2507254}, {longitude:79.4310887,latitude:25.2596457}, {longitude:79.4154783,latitude:25.2591997}, {longitude:79.401206,latitude:25.2623217}, {longitude:79.402098,latitude:25.2792702}, {longitude:79.3673091,latitude:25.27258}, {longitude:79.3583889,latitude:25.2636598}, {longitude:79.3512527,latitude:25.2699039}, {longitude:79.3445625,latitude:25.2641058}, {longitude:79.3320742,latitude:25.2761481}, {longitude:79.3307362,latitude:25.2939885}, {longitude:79.3418865,latitude:25.3046928}, {longitude:79.3343043,latitude:25.3171811}, {longitude:79.3445625,latitude:25.3345756}, {longitude:79.3673091,latitude:25.2944346}, {longitude:79.3499147,latitude:25.2886364}, {longitude:79.3102196,latitude:25.2618757}, {longitude:79.2932712,latitude:25.2400212}, {longitude:79.2816749,latitude:25.2270868}, {longitude:79.2736467,latitude:25.2480494}, {longitude:79.2620504,latitude:25.2676739}, {longitude:79.2776608,latitude:25.270796}, {longitude:79.2879191,latitude:25.2904205}, {longitude:79.2562523,latitude:25.2819462}, {longitude:79.2602664,latitude:25.310045}, {longitude:79.2763228,latitude:25.3269934}, {longitude:79.2914872,latitude:25.3292235}, {longitude:79.2946092,latitude:25.3403737}, {longitude:79.3289521,latitude:25.2297629}, {longitude:79.325384,latitude:25.2226267}, {longitude:79.3106657,latitude:25.2195046}, {longitude:79.3325202,latitude:25.2007722}, {longitude:79.3467926,latitude:25.2141525}, {longitude:79.3409944,latitude:25.2306549}, {longitude:79.2789988,latitude:25.1972041}, {longitude:79.3017454,latitude:25.1753495}, {longitude:79.3137877,latitude:25.1508189}, {longitude:79.3008534,latitude:25.1307484}, {longitude:79.2821209,latitude:25.1334244}, {longitude:79.2700786,latitude:25.1262882}, {longitude:79.2763228,latitude:25.1200441}, {longitude:79.2580363,latitude:25.1240582}, {longitude:79.2544682,latitude:25.1303024}, {longitude:79.2620504,latitude:25.1410066}, {longitude:79.2433179,latitude:25.1410066}, {longitude:79.2375198,latitude:25.1311944}, {longitude:79.2397498,latitude:25.117814}, {longitude:79.2228014,latitude:25.1231662}, {longitude:79.207191,latitude:25.1245042}, {longitude:79.206299,latitude:25.1137999}, {longitude:79.1960407,latitude:25.1316404}, {longitude:79.1969327,latitude:25.1427907}, {longitude:79.1848904,latitude:25.1512649}, {longitude:79.166604,latitude:25.1423447}, {longitude:79.168834,latitude:25.1316404}, {longitude:79.1799843,latitude:25.1289643}, {longitude:79.1790923,latitude:25.117814}, {longitude:79.1567917,latitude:25.1120159}, {longitude:79.1380592,latitude:25.11826}, {longitude:79.129139,latitude:25.1106778}, {longitude:79.1148666,latitude:25.117814}, {longitude:79.1251249,latitude:25.1294103}, {longitude:79.1077305,latitude:25.1436827}, {longitude:79.1202188,latitude:25.1641992}, {longitude:79.090782,latitude:25.1762415}, {longitude:79.0840919,latitude:25.192744}, {longitude:79.0640213,latitude:25.1726734}, {longitude:79.0582232,latitude:25.1508189}, {longitude:79.0430588,latitude:25.1383306}, {longitude:79.0283404,latitude:25.1401146}, {longitude:79.0216503,latitude:25.1468048}, {longitude:79.0359226,latitude:25.1740115}, {longitude:79.0247723,latitude:25.1864998}, {longitude:78.9957816,latitude:25.195866}, {longitude:78.9824012,latitude:25.1989881}, {longitude:79.0002417,latitude:25.2159365}, {longitude:78.9931055,latitude:25.2221807}, {longitude:78.9948896,latitude:25.233777}, {longitude:79.011838,latitude:25.2493874}, {longitude:79.0292324,latitude:25.2493874}, {longitude:79.0265564,latitude:25.234669}, {longitude:79.0564391,latitude:25.2257488}, {longitude:79.0551011,latitude:25.2172746}, {longitude:79.012284,latitude:25.273918}, {longitude:78.9926595,latitude:25.2779321}, {longitude:78.9770491,latitude:25.2618757}, {longitude:78.971251,latitude:25.2449273}, {longitude:78.9641148,latitude:25.2195046}, {longitude:78.9485044,latitude:25.2203967}, {longitude:78.9244198,latitude:25.2105844}, {longitude:78.8824947,latitude:25.2110304}, {longitude:78.8673303,latitude:25.1900679}, {longitude:78.8740205,latitude:25.1633072}, {longitude:78.893199,latitude:25.1731195}, {longitude:78.8668843,latitude:25.232439}, {longitude:78.8419076,latitude:25.2293169}, {longitude:78.8758045,latitude:25.2556316}, {longitude:78.8918609,latitude:25.2453733}, {longitude:78.9088094,latitude:25.274364}, {longitude:78.9181756,latitude:25.2752561}, {longitude:78.9404762,latitude:25.2627677}, {longitude:78.9485044,latitude:25.2658898}, {longitude:78.9476124,latitude:25.2837303}, {longitude:78.9699129,latitude:25.2850683}, {longitude:78.9529645,latitude:25.311829}, {longitude:78.9275418,latitude:25.3318995}, {longitude:78.9190676,latitude:25.3461719}, {longitude:78.9284339,latitude:25.35197}, {longitude:78.9386921,latitude:25.3528621}, {longitude:78.9458283,latitude:25.3470639}, {longitude:78.9556406,latitude:25.3479559}, {longitude:78.9485044,latitude:25.3698105}, {longitude:78.9311099,latitude:25.3769467}, {longitude:78.9311099,latitude:25.4032613}, {longitude:78.9493964,latitude:25.4001393}, {longitude:78.9681289,latitude:25.3965712}, {longitude:78.9783871,latitude:25.3760547}, {longitude:78.9690209,latitude:25.4251159}, {longitude:78.9601007,latitude:25.4389423}, {longitude:78.9427062,latitude:25.4416183}, {longitude:78.9293259,latitude:25.428238}, {longitude:78.9030112,latitude:25.4469705}, {longitude:78.9275418,latitude:25.4657029}, {longitude:78.93557,latitude:25.4799753}, {longitude:78.935124,latitude:25.4942477}, {longitude:78.932448,latitude:25.5241304}, {longitude:78.9418142,latitude:25.5312666}, {longitude:78.9418142,latitude:25.5513371}, {longitude:78.9257578,latitude:25.5598114}, {longitude:78.9101474,latitude:25.5593653}, {longitude:78.9168376,latitude:25.5410789}, {longitude:78.9195136,latitude:25.5272525}, {longitude:78.9163916,latitude:25.5205624}, {longitude:78.8758045,latitude:25.5241304}, {longitude:78.8691143,latitude:25.5513371}, {longitude:78.8856168,latitude:25.5607034}, {longitude:78.8338794,latitude:25.5161022}, {longitude:78.8285273,latitude:25.5098581}, {longitude:78.8271893,latitude:25.5036139}, {longitude:78.8459218,latitude:25.4915716}, {longitude:78.857072,latitude:25.467041}, {longitude:78.8535039,latitude:25.4523226}, {longitude:78.8459218,latitude:25.4545527}, {longitude:78.8370015,latitude:25.4478625}, {longitude:78.8343255,latitude:25.4322521}, {longitude:78.8129169,latitude:25.430468}, {longitude:78.8138089,latitude:25.4465245}, {longitude:78.7892783,latitude:25.4420643}, {longitude:78.7651937,latitude:25.430022}, {longitude:78.7549354,latitude:25.4340361}, {longitude:78.7482452,latitude:25.4483085}, {longitude:78.7246066,latitude:25.4634729}, {longitude:78.7437851,latitude:25.4848814}, {longitude:78.7365606,latitude:25.4935768}, {longitude:78.7426003,latitude:25.4982328}, {longitude:78.7567195,latitude:25.4759612}, {longitude:78.7897243,latitude:25.4835434}, {longitude:78.8298653,latitude:25.4657029}, {longitude:78.8749125,latitude:25.3876509}, {longitude:78.895429,latitude:25.3809608}, {longitude:78.8923069,latitude:25.3751626}, {longitude:78.8967671,latitude:25.3653504}, {longitude:78.9012272,latitude:25.389435}, {longitude:78.8766965,latitude:25.3443878}, {longitude:78.8758045,latitude:25.3341296}, {longitude:78.856626,latitude:25.3171811}, {longitude:78.8668843,latitude:25.310937}, {longitude:78.8722364,latitude:25.2881904}, {longitude:78.8624242,latitude:25.2832843}, {longitude:78.8115789,latitude:25.2997867}, {longitude:78.8035507,latitude:25.3020168}, {longitude:78.814701,latitude:25.310937}, {longitude:78.7919544,latitude:25.3149511}, {longitude:78.7834802,latitude:25.3033548}, {longitude:78.7910623,latitude:25.2930965}, {longitude:78.8057807,latitude:25.270796}, {longitude:78.8334334,latitude:25.3314535}, {longitude:78.8436917,latitude:25.3323455}, {longitude:78.8378935,latitude:25.35197}, {longitude:78.9079173,latitude:25.3394817}, {longitude:78.7643017,latitude:25.3582142}, {longitude:78.7611796,latitude:25.3822988}, {longitude:78.7527054,latitude:25.390327}, {longitude:78.7415551,latitude:25.3988012}, {longitude:78.7433391,latitude:25.4126276}, {longitude:78.7544894,latitude:25.4063834}, {longitude:78.7598415,latitude:25.4019233}, {longitude:78.7089962,latitude:25.3787307}, {longitude:78.6929398,latitude:25.3876509}, {longitude:78.6871417,latitude:25.3805148}, {longitude:78.6715313,latitude:25.3845289}, {longitude:78.6693012,latitude:25.3930031}, {longitude:78.6693012,latitude:25.4037074}, {longitude:78.6519068,latitude:25.4130736}, {longitude:78.6563669,latitude:25.4202098}, {longitude:78.6367424,latitude:25.4139656}, {longitude:78.6287142,latitude:25.4063834}, {longitude:78.6032915,latitude:25.4170877}, {longitude:78.5881271,latitude:25.3921111}, {longitude:78.5702867,latitude:25.3849749}, {longitude:78.5778689,latitude:25.3769467}, {longitude:78.5569063,latitude:25.3840829}, {longitude:78.5609204,latitude:25.3956792}, {longitude:78.5350518,latitude:25.3640123}, {longitude:78.5252395,latitude:25.3631203}, {longitude:78.5314837,latitude:25.3350216}, {longitude:78.5412959,latitude:25.3265474}, {longitude:78.5252395,latitude:25.3064769}, {longitude:78.5207794,latitude:25.2881904}, {longitude:78.5283616,latitude:25.274364}, {longitude:78.505169,latitude:25.2815002}, {longitude:78.4966948,latitude:25.2939885}, {longitude:78.5002629,latitude:25.3042468}, {longitude:78.4922347,latitude:25.3238713}, {longitude:78.4690421,latitude:25.3033548}, {longitude:78.467258,latitude:25.2930965}, {longitude:78.4404974,latitude:25.3042468}, {longitude:78.4297931,latitude:25.2971106}, {longitude:78.4289011,latitude:25.2806082}, {longitude:78.4445115,latitude:25.2699039}, {longitude:78.3976803,latitude:25.2177206}, {longitude:78.3838539,latitude:25.2061243}, {longitude:78.3851919,latitude:25.194974}, {longitude:78.4034784,latitude:25.1887299}, {longitude:78.4173048,latitude:25.1726734}, {longitude:78.4181968,latitude:25.1534949}, {longitude:78.4418354,latitude:25.1575091}, {longitude:78.4462955,latitude:25.1691053}, {longitude:78.4427274,latitude:25.1289643}, {longitude:78.424887,latitude:25.1245042}, {longitude:78.3954502,latitude:25.1307484}, {longitude:78.3744877,latitude:25.1084478}, {longitude:78.3276565,latitude:25.0888233}, {longitude:78.3249804,latitude:25.0723209}, {longitude:78.3379147,latitude:25.0272737}, {longitude:78.3276565,latitude:24.999621}, {longitude:78.2139236,latitude:24.9157709}, {longitude:78.1996512,latitude:24.8961464}, {longitude:78.1653083,latitude:24.881874}, {longitude:78.1653083,latitude:24.8479771}, {longitude:78.191623,latitude:24.8395029}, {longitude:78.2090174,latitude:24.8131882}, {longitude:78.2361651,latitude:24.7660853}, {longitude:78.2201677,latitude:24.7471785}, {longitude:78.2509425,latitude:24.7056995}, {longitude:78.2678909,latitude:24.6695725}, {longitude:78.2567407,latitude:24.64861}, {longitude:78.269675,latitude:24.6151592}, {longitude:78.2589707,latitude:24.5580697}, {longitude:78.2241818,latitude:24.5411213}, {longitude:78.2165996,latitude:24.531755}, {longitude:78.2197217,latitude:24.5228348}, {longitude:78.2402382,latitude:24.5094544}, {longitude:78.2397922,latitude:24.4893839}, {longitude:78.269675,latitude:24.4760036}, {longitude:78.2607548,latitude:24.454149}, {longitude:78.3611073,latitude:24.3859093}, {longitude:78.3624454,latitude:24.3783271}, {longitude:78.3267644,latitude:24.3386321}, {longitude:78.3258724,latitude:24.3297118}, {longitude:78.3446049,latitude:24.3033972}, {longitude:78.3615533,latitude:24.296261}, {longitude:78.3825159,latitude:24.2739604}, {longitude:78.4070465,latitude:24.2770825}, {longitude:78.4115066,latitude:24.2877868}, {longitude:78.4346992,latitude:24.297599}, {longitude:78.4333612,latitude:24.3132094}, {longitude:78.4404974,latitude:24.3256977}, {longitude:78.4677041,latitude:24.3395241}, {longitude:78.4752863,latitude:24.3676228}, {longitude:78.4895586,latitude:24.3859093}, {longitude:78.505169,latitude:24.3939375}, {longitude:78.5524462,latitude:24.3604866}, {longitude:78.5783149,latitude:24.3569185}, {longitude:78.6019535,latitude:24.3181155}, {longitude:78.622916,latitude:24.3096413}, {longitude:78.6171179,latitude:24.296261}, {longitude:78.621578,latitude:24.2864487}, {longitude:78.6340663,latitude:24.2895708}, {longitude:78.6545828,latitude:24.2739604}, {longitude:78.6693012,latitude:24.2449697}, {longitude:78.698738,latitude:24.2342654}, {longitude:78.7183625,latitude:24.2391715}, {longitude:78.737541,latitude:24.2422936}, {longitude:78.7460152,latitude:24.2391715}, {longitude:78.7607336,latitude:24.2338194}, {longitude:78.7665317,latitude:24.2507678}, {longitude:78.7602876,latitude:24.25835}, {longitude:78.7321888,latitude:24.2538899}, {longitude:78.7848182,latitude:24.1852041}, {longitude:78.8022126,latitude:24.1820821}, {longitude:78.8240672,latitude:24.2012605}, {longitude:78.8133629,latitude:24.2101808}, {longitude:78.8387856,latitude:24.2146409}, {longitude:78.8628702,latitude:24.2141949}, {longitude:78.8789266,latitude:24.2231151}, {longitude:78.8789266,latitude:24.2538899}, {longitude:78.9119314,latitude:24.2766365}, {longitude:78.9079173,latitude:24.3011671}, {longitude:78.9667908,latitude:24.3537965}, {longitude:78.974373,latitude:24.3988436}, {longitude:78.9868614,latitude:24.4233742}, {longitude:78.9819552,latitude:24.4474589}, {longitude:78.9444903,latitude:24.4434448}, {longitude:78.9311099,latitude:24.4599472}, {longitude:78.9088094,latitude:24.4666373}, {longitude:78.9007812,latitude:24.4884919}, {longitude:78.9137155,latitude:24.5009802}, {longitude:78.9244198,latitude:24.4991962}, {longitude:78.9235277,latitude:24.4884919}, {longitude:78.9306639,latitude:24.4844778}, {longitude:78.9485044,latitude:24.4862618}, {longitude:78.9663448,latitude:24.4969661}, {longitude:78.9440443,latitude:24.5094544}, {longitude:78.9400302,latitude:24.5339851}, {longitude:78.9444903,latitude:24.5562856}, {longitude:78.9132695,latitude:24.5812623}, {longitude:78.9043492,latitude:24.6044549}, {longitude:78.8526119,latitude:24.6209573}, {longitude:78.8445837,latitude:24.6289855}, {longitude:78.8361095,latitude:24.6254174}, {longitude:78.8289733,latitude:24.608023}, {longitude:78.8017666,latitude:24.608023}, {longitude:78.7937384,latitude:24.5982107}, {longitude:78.77679,latitude:24.5933046}, {longitude:78.7495833,latitude:24.6049009}, {longitude:78.740217,latitude:24.6597603}, {longitude:78.7723299,latitude:24.7043614}, {longitude:78.7647477,latitude:24.7949018}, {longitude:78.7799121,latitude:24.8069441}, {longitude:78.7803581,latitude:24.8149723}, {longitude:78.7643017,latitude:24.8613575}, {longitude:78.6684092,latitude:24.9023905}, {longitude:78.6536908,latitude:24.9443156}, {longitude:78.6403105,latitude:24.9403015}, {longitude:78.6425405,latitude:24.9527898}, {longitude:78.622024,latitude:24.9648321}, {longitude:78.6443246,latitude:25.0353019}, {longitude:78.6420945,latitude:25.0616166}, {longitude:78.62024,latitude:25.0914994}, {longitude:78.5970474,latitude:25.0977435}, {longitude:78.5939253,latitude:25.1575091}, {longitude:78.5627045,latitude:25.2261948}, {longitude:78.5685026,latitude:25.2587536}, {longitude:78.5569063,latitude:25.2694579}, {longitude:78.5560143,latitude:25.3484019}, {longitude:78.5760848,latitude:25.35197}, {longitude:78.6577049,latitude:25.388097}, {longitude:78.7036441,latitude:25.3586602}, {longitude:78.7134564,latitude:25.3528621}, {longitude:78.7357569,latitude:25.349294}, {longitude:78.701414,latitude:25.428684}, {longitude:78.6858036,latitude:25.4478625}, {longitude:78.6750994,latitude:25.4380502}, {longitude:78.6527988,latitude:25.4438484}, {longitude:78.6336203,latitude:25.4380502}, {longitude:78.6487847,latitude:25.5656095}, {longitude:78.623362,latitude:25.5709616}, {longitude:78.621132,latitude:25.5821119}, {longitude:78.6055216,latitude:25.5888021}, {longitude:78.580991,latitude:25.5642715}, {longitude:78.541742,latitude:25.5651635}, {longitude:78.4864365,latitude:25.5821119}, {longitude:78.4619059,latitude:25.5673936}, {longitude:78.4552157,latitude:25.5517832}, {longitude:78.4369293,latitude:25.5642715}, {longitude:78.426225,latitude:25.5615954}, {longitude:78.4088306,latitude:25.5330507}, {longitude:78.4097226,latitude:25.5187783}, {longitude:78.4168588,latitude:25.5178863}, {longitude:78.4092766,latitude:25.5040599}, {longitude:78.4066005,latitude:25.4790833}, {longitude:78.4208729,latitude:25.4777453}, {longitude:78.3936662,latitude:25.4710551}, {longitude:78.3771637,latitude:25.4483085}, {longitude:78.3611073,latitude:25.4465245}, {longitude:78.347727,latitude:25.4549987}, {longitude:78.3352387,latitude:25.4309141}, {longitude:78.3325626,latitude:25.4045994}, {longitude:78.310708,latitude:25.3849749}, {longitude:78.2995578,latitude:25.3836368}, {longitude:78.2942056,latitude:25.3675804}, {longitude:78.3231964,latitude:25.3550921}, {longitude:78.3312246,latitude:25.3368056}, {longitude:78.308032,latitude:25.310937}, {longitude:78.3450509,latitude:25.2980027}, {longitude:78.3450509,latitude:25.2828383}, {longitude:78.3619994,latitude:25.2654438}, {longitude:78.3544172,latitude:25.2462653}, {longitude:78.3686895,latitude:25.2418052}, {longitude:78.3753797,latitude:25.2257488}, {longitude:78.6768834,latitude:25.5941542}, {longitude:78.7112263,latitude:25.6066426}, {longitude:78.7411091,latitude:25.6039665}, {longitude:78.8053347,latitude:25.624483}, {longitude:78.8111329,latitude:25.6744363}, {longitude:78.7892783,latitude:25.6998589}, {longitude:78.7910623,latitude:25.7105632}, {longitude:78.7607336,latitude:25.7208215}, {longitude:78.7482452,latitude:25.7328638}, {longitude:78.7455692,latitude:25.7440141}, {longitude:78.7513673,latitude:25.7524883}, {longitude:78.7625176,latitude:25.7520423}, {longitude:78.775898,latitude:25.7614085}, {longitude:78.7995366,latitude:25.7993195}, {longitude:78.8004286,latitude:25.8126998}, {longitude:78.7986445,latitude:25.818052}, {longitude:78.8111329,latitude:25.8300943}, {longitude:78.8222831,latitude:25.8153759}, {longitude:78.8204991,latitude:25.7993195}, {longitude:78.8414616,latitude:25.8051176}, {longitude:78.8494898,latitude:25.7957514}, {longitude:78.8619782,latitude:25.7993195}, {longitude:78.8410156,latitude:25.820728}, {longitude:78.8463678,latitude:25.8385685}, {longitude:78.8441377,latitude:25.8452587}, {longitude:78.8530579,latitude:25.8434746}, {longitude:78.8655463,latitude:25.8345544}, {longitude:78.8468138,latitude:25.8604231}, {longitude:78.8579641,latitude:25.8720194}, {longitude:78.8824947,latitude:25.8702353}, {longitude:78.8811567,latitude:25.8885218}, {longitude:78.8874008,latitude:25.89878}, {longitude:78.8762505,latitude:25.9157285}, {longitude:78.894091,latitude:25.9259867}, {longitude:78.9097014,latitude:25.9460573}, {longitude:78.9239738,latitude:25.9527474}, {longitude:78.9275418,latitude:25.9554235}, {longitude:78.9293259,latitude:25.9491793}, {longitude:78.9311099,latitude:25.976386}, {longitude:78.9244198,latitude:25.9844142}, {longitude:78.9431522,latitude:25.9906584}, {longitude:78.9502884,latitude:26.0196491}, {longitude:78.9444903,latitude:26.0370436}, {longitude:78.9757111,latitude:26.0597902}, {longitude:78.9824012,latitude:26.0736165}, {longitude:78.9971196,latitude:26.0740625}, {longitude:79.0042558,latitude:26.0901189}, {longitude:78.971697,latitude:26.1088514}, {longitude:78.9690209,latitude:26.1235698}, {longitude:78.9427062,latitude:26.1391802}, {longitude:78.9654528,latitude:26.1556826}, {longitude:78.9864154,latitude:26.1503305}, {longitude:79.0006877,latitude:26.1547906}, {longitude:79.0033638,latitude:26.1730771}, {longitude:78.9917675,latitude:26.1770912}, {longitude:79.0024718,latitude:26.1967157}, {longitude:79.010054,latitude:26.2047439}, {longitude:79.0149601,latitude:26.1976077}, {longitude:79.0207582,latitude:26.208758}, {longitude:79.0171901,latitude:26.2319506}, {longitude:79.0568852,latitude:26.2328426}, {longitude:79.0626833,latitude:26.247115}, {longitude:79.0484109,latitude:26.2658475}, {longitude:79.0368146,latitude:26.2671855}, {longitude:79.0533171,latitude:26.2796738}, {longitude:79.0671434,latitude:26.287702}, {longitude:79.0778477,latitude:26.287256}, {longitude:79.0831998,latitude:26.2939462}, {longitude:79.0760636,latitude:26.2752137}, {longitude:79.0836458,latitude:26.2725376}, {longitude:79.0939041,latitude:26.3117866}, {longitude:79.1086225,latitude:26.3086645}, {longitude:79.1264629,latitude:26.3162467}, {longitude:79.126909,latitude:26.3318571}, {longitude:79.1251249,latitude:26.3381013}, {longitude:79.1157587,latitude:26.3349792}, {longitude:79.1335991,latitude:26.3447915}, {longitude:79.090336,latitude:26.3795804}, {longitude:79.0769557,latitude:26.3662}, {longitude:79.0805238,latitude:26.405449}, {longitude:79.0867679,latitude:26.4179373}, {longitude:79.1005943,latitude:26.4116932}, {longitude:79.1170967,latitude:26.4170453}, {longitude:79.126909,latitude:26.444698}, {longitude:79.0947961,latitude:26.4571864}, {longitude:79.0831998,latitude:26.4531723}, {longitude:79.0809698,latitude:26.443806}, {longitude:79.0484109,latitude:26.4558483}, {longitude:79.0644674,latitude:26.4866231}, {longitude:79.0608993,latitude:26.5031255}, {longitude:79.051087,latitude:26.5008955}, {longitude:79.0394907,latitude:26.5156139}, {longitude:79.0408287,latitude:26.52275}, {longitude:79.0314625,latitude:26.5249801}, {longitude:79.0332466,latitude:26.5361304}, {longitude:79.0194202,latitude:26.5414825}, {longitude:79.0207582,latitude:26.5517408}, {longitude:79.0069319,latitude:26.5579849}, {longitude:78.9975656,latitude:26.5557549}, {longitude:78.9980116,latitude:26.5508488}, {longitude:78.9980116,latitude:26.5642291}, {longitude:78.9797252,latitude:26.5740413}, {longitude:78.9913215,latitude:26.5825156}, {longitude:78.9779411,latitude:26.5923278}, {longitude:79.0055938,latitude:26.6106143}, {longitude:79.0029178,latitude:26.6266707}, {longitude:78.9873074,latitude:26.6440651}, {longitude:79.0011337,latitude:26.6739479}, {longitude:78.9971196,latitude:26.6810841}, {longitude:78.9837393,latitude:26.6855442}, {longitude:78.9480584,latitude:26.6966945}, {longitude:78.9480584,latitude:26.6627976}, {longitude:78.9378001,latitude:26.6627976}, {longitude:78.9039032,latitude:26.7131969}, {longitude:78.8829407,latitude:26.7042767}, {longitude:78.8651002,latitude:26.7047227}, {longitude:78.8405696,latitude:26.7261312}, {longitude:78.8354376,latitude:26.736324}, {longitude:78.8325193,latitude:26.7395435}, {longitude:78.8301161,latitude:26.7427628}, {longitude:78.8287428,latitude:26.7465952}, {longitude:78.8259962,latitude:26.7495078}, {longitude:78.8244512,latitude:26.7534933}, {longitude:78.8222196,latitude:26.758245}, {longitude:78.8187864,latitude:26.7616171}, {longitude:78.8131216,latitude:26.764376}, {longitude:78.8057401,latitude:26.7628433}, {longitude:78.7947538,latitude:26.7608507}, {longitude:78.7854841,latitude:26.7591647}, {longitude:78.7760427,latitude:26.7588581}, {longitude:78.7705496,latitude:26.7603909}, {longitude:78.767288,latitude:26.76269}, {longitude:78.7653997,latitude:26.7663685}, {longitude:78.7640264,latitude:26.7723458}, {longitude:78.7633398,latitude:26.7761772}, {longitude:78.7621381,latitude:26.7810813}, {longitude:78.7580183,latitude:26.7849124}, {longitude:78.7542417,latitude:26.7870578}, {longitude:78.7506368,latitude:26.787824}, {longitude:78.7470319,latitude:26.7867513}, {longitude:78.7441137,latitude:26.7842994}, {longitude:78.7410238,latitude:26.778476}, {longitude:78.7381056,latitude:26.7751044}, {longitude:78.7310674,latitude:26.7746447}, {longitude:78.7276342,latitude:26.7770968}, {longitude:78.7259176,latitude:26.780315}, {longitude:78.7269476,latitude:26.7856786}, {longitude:78.7295225,latitude:26.790429}, {longitude:78.7284925,latitude:26.7939534}, {longitude:78.725231,latitude:26.7967115}, {longitude:78.7180212,latitude:26.7956389}, {longitude:78.7099531,latitude:26.793034}, {longitude:78.7051466,latitude:26.7931872}, {longitude:78.6993101,latitude:26.7933405}, {longitude:78.6886671,latitude:26.7898161}, {longitude:78.6838606,latitude:26.787824}, {longitude:78.680599,latitude:26.7847592}, {longitude:78.6783674,latitude:26.7810813}, {longitude:78.6766508,latitude:26.7781695}, {longitude:78.6764791,latitude:26.7744914}, {longitude:78.6766508,latitude:26.7706599}, {longitude:78.6759641,latitude:26.766675}, {longitude:78.6744192,latitude:26.7642227}, {longitude:78.6718443,latitude:26.76269}, {longitude:78.6680677,latitude:26.76269}, {longitude:78.6637762,latitude:26.7634564}, {longitude:78.6591413,latitude:26.7642227}, {longitude:78.6534765,latitude:26.7662152}, {longitude:78.6478117,latitude:26.768361}, {longitude:78.6431768,latitude:26.7692806}, {longitude:78.6390569,latitude:26.7697404}, {longitude:78.6349371,latitude:26.7711197}, {longitude:78.6306455,latitude:26.7726523}, {longitude:78.626869,latitude:26.7728056}, {longitude:78.6237791,latitude:26.7709665}, {longitude:78.6213758,latitude:26.7686675}, {longitude:78.6194875,latitude:26.766062}, {longitude:78.6182859,latitude:26.7633031}, {longitude:78.6179426,latitude:26.7596245}, {longitude:78.6165693,latitude:26.7556393}, {longitude:78.6145094,latitude:26.7531867}, {longitude:78.6117628,latitude:26.7519604}, {longitude:78.6091879,latitude:26.7505808}, {longitude:78.6064413,latitude:26.7498144}, {longitude:78.6028364,latitude:26.750121}, {longitude:78.6004331,latitude:26.7508874}, {longitude:78.5976865,latitude:26.7513473}, {longitude:78.594425,latitude:26.7519604}, {longitude:78.5908201,latitude:26.7521137}, {longitude:78.5875585,latitude:26.751194}, {longitude:78.5844686,latitude:26.7499677}, {longitude:78.5805204,latitude:26.7479749}, {longitude:78.5764005,latitude:26.7478216}, {longitude:78.5729673,latitude:26.7496611}, {longitude:78.5705641,latitude:26.752267}, {longitude:78.5690191,latitude:26.7553327}, {longitude:78.5667875,latitude:26.7587049}, {longitude:78.5640409,latitude:26.7614638}, {longitude:78.5592344,latitude:26.7625368}, {longitude:78.5537412,latitude:26.7636097}, {longitude:78.549278,latitude:26.7640695}, {longitude:78.5446432,latitude:26.7640695}, {longitude:78.5408666,latitude:26.7657554}, {longitude:78.5374334,latitude:26.768361}, {longitude:78.5350518,latitude:26.7716244}, {longitude:78.5257604,latitude:26.7780163}, {longitude:78.5190656,latitude:26.7810813}, {longitude:78.5113409,latitude:26.7812345}, {longitude:78.5041311,latitude:26.780928}, {longitude:78.4953764,latitude:26.7820008}, {longitude:78.4862783,latitude:26.7844527}, {longitude:78.4790685,latitude:26.7870578}, {longitude:78.4728887,latitude:26.7875175}, {longitude:78.466347,latitude:26.7874181}, {longitude:78.4615591,latitude:26.7881305}, {longitude:78.4573164,latitude:26.7905569}, {longitude:78.4549131,latitude:26.7948474}, {longitude:78.4518232,latitude:26.8009764}, {longitude:78.4470167,latitude:26.8087904}, {longitude:78.4425535,latitude:26.8153783}, {longitude:78.4363737,latitude:26.8236508}, {longitude:78.4327688,latitude:26.8256423}, {longitude:78.4267607,latitude:26.8239572}, {longitude:78.4152594,latitude:26.8202806}, {longitude:78.4049597,latitude:26.818289}, {longitude:78.3996382,latitude:26.8181359}, {longitude:78.3955183,latitude:26.8202806}, {longitude:78.3900251,latitude:26.8253359}, {longitude:78.3844831,latitude:26.8380752}, {longitude:78.3810499,latitude:26.8408323}, {longitude:78.378475,latitude:26.8477246}, {longitude:78.3756056,latitude:26.856582}, {longitude:78.3720007,latitude:26.8640859}, {longitude:78.3644476,latitude:26.8682205}, {longitude:78.3556929,latitude:26.8691393}, {longitude:78.3464231,latitude:26.8673017}, {longitude:78.3332052,latitude:26.8628608}, {longitude:78.3253088,latitude:26.8624014}, {longitude:78.3104971,latitude:26.8633456}, {longitude:78.2995107,latitude:26.8613548}, {longitude:78.2887449,latitude:26.8571946}, {longitude:78.2811918,latitude:26.8536722}, {longitude:78.276042,latitude:26.8470866}, {longitude:78.2734671,latitude:26.8423386}, {longitude:78.2736387,latitude:26.8355991}, {longitude:78.2743254,latitude:26.8300847}, {longitude:78.2748403,latitude:26.8215062}, {longitude:78.2719221,latitude:26.8155315}, {longitude:78.2669439,latitude:26.8133866}, {longitude:78.2588758,latitude:26.814459}, {longitude:78.252181,latitude:26.8175231}, {longitude:78.2466879,latitude:26.8219657}, {longitude:78.2387915,latitude:26.8264082}, {longitude:78.2310667,latitude:26.8291656}, {longitude:78.219737,latitude:26.8293188}, {longitude:78.210124,latitude:26.8262551}, {longitude:78.2139236,latitude:26.8483384}, {longitude:78.2045573,latitude:26.8554746}, {longitude:78.2050033,latitude:26.8773291}, {longitude:78.191177,latitude:26.8884794}, {longitude:78.2264119,latitude:26.8826813}, {longitude:78.22998,latitude:26.8938315}, {longitude:78.2527266,latitude:26.9045358}, {longitude:78.2612008,latitude:26.9317425}, {longitude:78.232656,latitude:26.9335266}, {longitude:78.2054493,latitude:26.9388787}, {longitude:78.190285,latitude:26.9469069}, {longitude:78.2157076,latitude:26.9540431}, {longitude:78.1461298,latitude:26.9531511}, {longitude:78.1340875,latitude:26.9571652}, {longitude:78.1082188,latitude:26.950029}, {longitude:78.115355,latitude:26.9379867}, {longitude:78.112679,latitude:26.9272824}, {longitude:78.0952845,latitude:26.9290664}, {longitude:78.0912704,latitude:26.911672}, {longitude:78.0868103,latitude:26.9018597}, {longitude:78.1015287,latitude:26.9049818}, {longitude:78.0604956,latitude:26.9156861}, {longitude:78.0422092,latitude:26.9152401}, {longitude:78.0216926,latitude:26.8947236}, {longitude:78.0176785,latitude:26.8822352}, {longitude:78.0279368,latitude:26.8759911}, {longitude:78.037749,latitude:26.8768831}, {longitude:78.0480073,latitude:26.8746531}, {longitude:78.0221386,latitude:26.8608267}, {longitude:78.0163405,latitude:26.8639488}, {longitude:78.0065282,latitude:26.91078}, {longitude:77.9918099,latitude:26.91078}, {longitude:77.9753074,latitude:26.8973996}, {longitude:77.9628191,latitude:26.912564}, {longitude:77.9418566,latitude:26.8996297}, {longitude:77.9289223,latitude:26.91301}, {longitude:77.9097438,latitude:26.9143481}, {longitude:77.9066217,latitude:26.9241603}, {longitude:77.8999315,latitude:26.9246063}, {longitude:77.9030536,latitude:26.9009677}, {longitude:77.8901193,latitude:26.8893714}, {longitude:77.8713868,latitude:26.9054278}, {longitude:77.8214335,latitude:26.9263904}, {longitude:77.8147433,latitude:26.9228223}, {longitude:77.7991329,latitude:26.9281744}, {longitude:77.7888747,latitude:26.9223763}, {longitude:77.7732643,latitude:26.9362026}, {longitude:77.7531938,latitude:26.9375407}, {longitude:77.7442735,latitude:26.9232683}, {longitude:77.7291091,latitude:26.9259444}, {longitude:77.7188509,latitude:26.9165781}, {longitude:77.7108227,latitude:26.9031978}, {longitude:77.7197429,latitude:26.8965076}, {longitude:77.7157288,latitude:26.8813432}, {longitude:77.6978883,latitude:26.8800052}, {longitude:77.6938742,latitude:26.872869}, {longitude:77.6782638,latitude:26.8782211}, {longitude:77.6644375,latitude:26.8599347}, {longitude:77.6162682,latitude:26.8483384}, {longitude:77.6002118,latitude:26.8425402}, {longitude:77.6046719,latitude:26.832728}, {longitude:77.5565027,latitude:26.8220237}, {longitude:77.5208218,latitude:26.8220237}, {longitude:77.5119016,latitude:26.8278219}, {longitude:77.5136856,latitude:26.8452163}, {longitude:77.4980752,latitude:26.8478924}, {longitude:77.4543661,latitude:26.8768831}, {longitude:77.4418778,latitude:26.8777751}, {longitude:77.4365256,latitude:26.8679629}, {longitude:77.4222533,latitude:26.8670709}, {longitude:77.4262674,latitude:26.8532445}, {longitude:77.4173471,latitude:26.8429862}, {longitude:77.4392017,latitude:26.8193476}, {longitude:77.450798,latitude:26.7805446}, {longitude:77.4641783,latitude:26.7693944}, {longitude:77.4802348,latitude:26.753784}, {longitude:77.4931691,latitude:26.7671643}, {longitude:77.4967372,latitude:26.7743005}, {longitude:77.4811268,latitude:26.7876808}, {longitude:77.4284974,latitude:26.7702864}, {longitude:77.4481219,latitude:26.7466478}, {longitude:77.4552581,latitude:26.8893714}, {longitude:77.4824648,latitude:26.9036438}, {longitude:77.5796953,latitude:26.9308505}, {longitude:77.5939677,latitude:26.9375407}, {longitude:77.5872775,latitude:26.9446768}, {longitude:77.6488271,latitude:26.9736676}, {longitude:77.6653295,latitude:26.9669774}, {longitude:77.6702356,latitude:26.9772357}, {longitude:77.6920902,latitude:26.9790197}, {longitude:77.7143908,latitude:26.991508}, {longitude:77.722419,latitude:27.0004283}, {longitude:77.7398134,latitude:26.9977522}, {longitude:77.7478416,latitude:26.9986442}, {longitude:77.7424895,latitude:27.0062264}, {longitude:77.7438275,latitude:27.0236209}, {longitude:77.7549778,latitude:27.0227288}, {longitude:77.7353533,latitude:27.0338791}, {longitude:77.721973,latitude:27.0262969}, {longitude:77.7036865,latitude:27.0004283}, {longitude:77.7362453,latitude:26.988386}, {longitude:77.6818319,latitude:27.0329871}, {longitude:77.684508,latitude:27.0450294}, {longitude:77.6733577,latitude:27.0517196}, {longitude:77.6568553,latitude:27.0227288}, {longitude:77.6341087,latitude:27.027189}, {longitude:77.6274185,latitude:27.0155927}, {longitude:77.6153762,latitude:27.027189}, {longitude:77.5903996,latitude:27.0316491}, {longitude:77.5837094,latitude:27.0392313}, {longitude:77.5573947,latitude:27.0370012}, {longitude:77.566761,latitude:27.0601938}, {longitude:77.565869,latitude:27.0780343}, {longitude:77.5462444,latitude:27.0807103}, {longitude:77.5212678,latitude:27.06733}, {longitude:77.5141316,latitude:27.0704521}, {longitude:77.5172537,latitude:27.0896306}, {longitude:77.4985212,latitude:27.0949827}, {longitude:77.5136856,latitude:27.107917}, {longitude:77.529296,latitude:27.1043489}, {longitude:77.5444604,latitude:27.1154992}, {longitude:77.5908456,latitude:27.1230814}, {longitude:77.5970898,latitude:27.1288796}, {longitude:77.5935217,latitude:27.1480581}, {longitude:77.606456,latitude:27.1543022}, {longitude:77.605118,latitude:27.1685746}, {longitude:77.6149302,latitude:27.1748187}, {longitude:77.6358927,latitude:27.1699126}, {longitude:77.6546252,latitude:27.1757108}, {longitude:77.6653295,latitude:27.185969}, {longitude:77.6729117,latitude:27.2002414}, {longitude:77.6394608,latitude:27.2171898}, {longitude:77.644367,latitude:27.2390444}, {longitude:77.6385688,latitude:27.2408284}, {longitude:77.6390148,latitude:27.2470726}, {longitude:77.6234044,latitude:27.264021}, {longitude:77.6127002,latitude:27.2604529}, {longitude:77.606456,latitude:27.2724952}, {longitude:77.6140382,latitude:27.2791854}, {longitude:77.5917376,latitude:27.300594}, {longitude:77.6135922,latitude:27.3148663}, {longitude:77.6140382,latitude:27.3434111}, {longitude:77.5743432,latitude:27.3269086}, {longitude:77.5426764,latitude:27.342519}, {longitude:77.5391083,latitude:27.3532233}, {longitude:77.5221598,latitude:27.3576834}, {longitude:77.4971832,latitude:27.381322}, {longitude:77.4757746,latitude:27.3875662}, {longitude:77.4664084,latitude:27.3844441}, {longitude:77.4320655,latitude:27.3991625}, {longitude:77.4267134,latitude:27.4165569}, {longitude:77.4151171,latitude:27.4254772}, {longitude:77.4293895,latitude:27.4620501}, {longitude:77.3963846,latitude:27.4776605}, {longitude:77.3919245,latitude:27.5021911}, {longitude:77.3825583,latitude:27.5164635}, {longitude:77.3522295,latitude:27.5200316}, {longitude:77.335281,latitude:27.5298438}, {longitude:77.332605,latitude:27.5387641}, {longitude:77.3401872,latitude:27.5467923}, {longitude:77.3303749,latitude:27.5713229}, {longitude:77.3259148,latitude:27.5967456}, {longitude:77.335281,latitude:27.6016517}, {longitude:77.3499994,latitude:27.5936235}, {longitude:77.3111964,latitude:27.6119099}, {longitude:77.3424172,latitude:27.652943}, {longitude:77.3407771,latitude:27.6946282}, {longitude:77.3303749,latitude:27.7109245}, {longitude:77.3022762,latitude:27.7131545}, {longitude:77.2978161,latitude:27.7185067}, {longitude:77.296478,latitude:27.729657}, {longitude:77.3125345,latitude:27.7385772}, {longitude:77.3013842,latitude:27.7564176}, {longitude:77.3040602,latitude:27.7880845}, {longitude:77.295586,latitude:27.7996808}, {longitude:77.2759615,latitude:27.8063709}, {longitude:77.2897879,latitude:27.8179672}, {longitude:77.2888959,latitude:27.8291175}, {longitude:77.3129805,latitude:27.8300095}, {longitude:77.3272528,latitude:27.8375917}, {longitude:77.3345199,latitude:27.8483237}, {longitude:77.3372247,latitude:27.8500325}, {longitude:77.3432341,latitude:27.8537195}, {longitude:77.3478753,latitude:27.8564551}, {longitude:77.3502642,latitude:27.856797}, {longitude:77.353267,latitude:27.8548156}, {longitude:77.3544959,latitude:27.854343}, {longitude:77.3556107,latitude:27.8542827}, {longitude:77.3650296,latitude:27.8542827}, {longitude:77.3670816,latitude:27.8546693}, {longitude:77.3708987,latitude:27.8568235}, {longitude:77.3848743,latitude:27.865052}, {longitude:77.3957209,latitude:27.8709367}, {longitude:77.4074861,latitude:27.876806}, {longitude:77.4096541,latitude:27.8801783}, {longitude:77.4131938,latitude:27.8831642}, {longitude:77.417562,latitude:27.8895388}, {longitude:77.4194048,latitude:27.891248}, {longitude:77.4219737,latitude:27.8919172}, {longitude:77.4287217,latitude:27.8917254}, {longitude:77.4316496,latitude:27.8906164}, {longitude:77.4328079,latitude:27.8903889}, {longitude:77.4377307,latitude:27.8904742}, {longitude:77.4500326,latitude:27.8904894}, {longitude:77.4527468,latitude:27.8912816}, {longitude:77.4550063,latitude:27.8927508}, {longitude:77.4680208,latitude:27.9140056}, {longitude:77.4686385,latitude:27.9317001}, {longitude:77.4775587,latitude:27.9401744}, {longitude:77.4846949,latitude:27.9437424}, {longitude:77.4953991,latitude:27.9308081}, {longitude:77.5110095,latitude:27.9374983}, {longitude:77.5190377,latitude:27.9317001}, {longitude:77.5359862,latitude:27.9490946}, {longitude:77.5359862,latitude:27.965597}, {longitude:77.5150236,latitude:27.965597}, {longitude:77.5096715,latitude:27.9731792}, {longitude:77.5350942,latitude:27.9932497}, {longitude:77.4788967,latitude:28.044095}, {longitude:77.4829108,latitude:28.0605974}, {longitude:77.4699765,latitude:28.08379}, {longitude:77.4860329,latitude:28.0971704}, {longitude:77.4922771,latitude:28.0980624}, {longitude:77.491385,latitude:28.1127808}, {longitude:77.4784507,latitude:28.1078747}, {longitude:77.4998593,latitude:28.1274992}, {longitude:77.4985212,latitude:28.1355274}, {longitude:77.4953991,latitude:28.1457856}, {longitude:77.4873709,latitude:28.1413255}, {longitude:77.4962912,latitude:28.160058}, {longitude:77.4922771,latitude:28.1850346}, {longitude:77.5016433,latitude:28.1872647}, {longitude:77.5056574,latitude:28.1952929}, {longitude:77.5190377,latitude:28.2019831}, {longitude:77.5234979,latitude:28.2073352}, {longitude:77.5377702,latitude:28.1908328}, {longitude:77.5422303,latitude:28.1814665}, {longitude:77.5319721,latitude:28.1703163}, {longitude:77.489155,latitude:28.2117953}, {longitude:77.5154697,latitude:28.2296358}, {longitude:77.5114556,latitude:28.2546124}, {longitude:77.5386623,latitude:28.2546124}, {longitude:77.5413383,latitude:28.2479222}, {longitude:77.4927231,latitude:28.2804811}, {longitude:77.4722065,latitude:28.2813731}, {longitude:77.4646244,latitude:28.277359}, {longitude:77.4673004,latitude:28.2697768}, {longitude:77.4958452,latitude:28.3072418}, {longitude:77.4637323,latitude:28.3380166}, {longitude:77.4940611,latitude:28.3580871}, {longitude:77.4922771,latitude:28.3696834}, {longitude:77.4668544,latitude:28.3995661}, {longitude:77.4739906,latitude:28.4040262}, {longitude:77.4744366,latitude:28.4133925}, {longitude:77.4360796,latitude:28.431679}, {longitude:77.4258214,latitude:28.4544255}, {longitude:77.4182392,latitude:28.4557636}, {longitude:77.3977226,latitude:28.4584396}, {longitude:77.3977226,latitude:28.471374}, {longitude:77.3758681,latitude:28.4762801}, {longitude:77.3642718,latitude:28.4856463}, {longitude:77.3607037,latitude:28.4976886}, {longitude:77.3457383,latitude:28.5153004}, {longitude:77.3369151,latitude:28.5237449}, {longitude:77.2985657,latitude:28.5571569}, {longitude:77.2939098,latitude:28.5742182}, {longitude:77.2928863,latitude:28.5761741}, {longitude:77.2957337,latitude:28.5825582}, {longitude:77.3001722,latitude:28.5868739}, {longitude:77.3015404,latitude:28.5868763}, {longitude:77.302858,latitude:28.586664}, {longitude:77.3044219,latitude:28.5857974}, {longitude:77.3088577,latitude:28.5893282}, {longitude:77.3101587,latitude:28.5907418}, {longitude:77.3111384,latitude:28.5919458}, {longitude:77.3111321,latitude:28.5926253}, {longitude:77.3113247,latitude:28.5931229}, {longitude:77.311977,latitude:28.5938047}, {longitude:77.3126578,latitude:28.5951813}, {longitude:77.3134894,latitude:28.5958149}, {longitude:77.3131816,latitude:28.5963398}, {longitude:77.313382,latitude:28.5965248}, {longitude:77.3141858,latitude:28.5967547}, {longitude:77.314928,latitude:28.596967}, {longitude:77.3171201,latitude:28.5968503}, {longitude:77.3194945,latitude:28.5974307}, {longitude:77.3201253,latitude:28.5975835}, {longitude:77.3212323,latitude:28.5976895}, {longitude:77.3218904,latitude:28.5976426}, {longitude:77.3225959,latitude:28.5975281}, {longitude:77.3258952,latitude:28.5981814}, {longitude:77.3263688,latitude:28.5985095}, {longitude:77.3267148,latitude:28.5987492}, {longitude:77.3271159,latitude:28.5986704}, {longitude:77.3281611,latitude:28.5993706}, {longitude:77.3297187,latitude:28.6004141}, {longitude:77.3316769,latitude:28.6011371}, {longitude:77.3336188,latitude:28.6014856}, {longitude:77.3347131,latitude:28.6019424}, {longitude:77.3356036,latitude:28.6016928}, {longitude:77.3367463,latitude:28.6018012}, {longitude:77.3376421,latitude:28.6024417}, {longitude:77.3384233,latitude:28.6032323}, {longitude:77.3386991,latitude:28.6029897}, {longitude:77.3395778,latitude:28.6041325}, {longitude:77.3402922,latitude:28.6046086}, {longitude:77.3407915,latitude:28.6052157}, {longitude:77.3412222,latitude:28.6051404}, {longitude:77.341991,latitude:28.607764}, {longitude:77.3417627,latitude:28.6136069}, {longitude:77.3416914,latitude:28.6136261}, {longitude:77.3416608,latitude:28.613724}, {longitude:77.3415287,latitude:28.6137665}, {longitude:77.3417203,latitude:28.6157252}, {longitude:77.3418543,latitude:28.6170951}, {longitude:77.342008,latitude:28.6187598}, {longitude:77.3419372,latitude:28.6193425}, {longitude:77.3416803,latitude:28.6201852}, {longitude:77.3414331,latitude:28.6207682}, {longitude:77.3410433,latitude:28.6212264}, {longitude:77.3403164,latitude:28.6218056}, {longitude:77.3401657,latitude:28.6220337}, {longitude:77.3404229,latitude:28.6236487}, {longitude:77.3395999,latitude:28.624098}, {longitude:77.3386036,latitude:28.6249339}, {longitude:77.336511,latitude:28.6263181}, {longitude:77.33443,latitude:28.6277275}, {longitude:77.3337696,latitude:28.6288811}, {longitude:77.3322902,latitude:28.6299179}, {longitude:77.3312417,latitude:28.6313165}, {longitude:77.3296659,latitude:28.631841}, {longitude:77.3288035,latitude:28.632283}, {longitude:77.3270954,latitude:28.6329216}, {longitude:77.3260092,latitude:28.6331684}, {longitude:77.3243082,latitude:28.6349278}, {longitude:77.3220117,latitude:28.6367878}, {longitude:77.3160837,latitude:28.6412336}, {longitude:77.3164414,latitude:28.6421609}, {longitude:77.3165831,latitude:28.6423809}, {longitude:77.3173679,latitude:28.6434285}, {longitude:77.3172108,latitude:28.6435806}, {longitude:77.3173257,latitude:28.6441466}, {longitude:77.3174719,latitude:28.6449794}, {longitude:77.3183163,latitude:28.6470773}, {longitude:77.3187834,latitude:28.6481509}, {longitude:77.3193995,latitude:28.6494967}, {longitude:77.320361,latitude:28.6520115}, {longitude:77.3206129,latitude:28.6539073}, {longitude:77.3200813,latitude:28.6545721}, {longitude:77.3201289,latitude:28.6625839}, {longitude:77.322538,latitude:28.6696714}, {longitude:77.3244481,latitude:28.6712418}, {longitude:77.3250493,latitude:28.6738757}, {longitude:77.3252446,latitude:28.6778565}, {longitude:77.3260836,latitude:28.6782546}, {longitude:77.3300672,latitude:28.678618}, {longitude:77.3329522,latitude:28.6816422}, {longitude:77.3321929,latitude:28.6828859}, {longitude:77.3307278,latitude:28.6883914}, {longitude:77.3273146,latitude:28.6936705}, {longitude:77.3236879,latitude:28.6987055}, {longitude:77.3258776,latitude:28.702434}, {longitude:77.325427,latitude:28.7087935}, {longitude:77.328209,latitude:28.7114762}, {longitude:77.3281119,latitude:28.7130927}, {longitude:77.3271329,latitude:28.7133516}, {longitude:77.3262756,latitude:28.7131088}, {longitude:77.3245283,latitude:28.7130415}, {longitude:77.3235183,latitude:28.7121669}, {longitude:77.3225002,latitude:28.7114612}, {longitude:77.3213854,latitude:28.7126223}, {longitude:77.3206527,latitude:28.7129281}, {longitude:77.3172355,latitude:28.7141043}, {longitude:77.3158161,latitude:28.7128444}, {longitude:77.3135116,latitude:28.713441}, {longitude:77.3044242,latitude:28.7118347}, {longitude:77.2990287,latitude:28.7098032}, {longitude:77.2959313,latitude:28.7050915}, {longitude:77.292275,latitude:28.7058931}, {longitude:77.2912591,latitude:28.7063661}, {longitude:77.2892268,latitude:28.7083785}, {longitude:77.2873192,latitude:28.7123335}, {longitude:77.2863386,latitude:28.7144478}, {longitude:77.2893996,latitude:28.717168}, {longitude:77.2897332,latitude:28.7209279}, {longitude:77.2908705,latitude:28.7227353}, {longitude:77.2866348,latitude:28.7249004}, {longitude:77.286462,latitude:28.7249887}, {longitude:77.2843538,latitude:28.7279871}, {longitude:77.2800429,latitude:28.732486}, {longitude:77.2756656,latitude:28.7355624}, {longitude:77.2735863,latitude:28.7359584}, {longitude:77.260922,latitude:28.7355868}, {longitude:77.2558344,latitude:28.7385417}, {longitude:77.2552701,latitude:28.7410026}, {longitude:77.2556778,latitude:28.7411371}, {longitude:77.2558301,latitude:28.7429884}, {longitude:77.2570339,latitude:28.7449704}, {longitude:77.257787,latitude:28.7469515}, {longitude:77.2589211,latitude:28.7476457}, {longitude:77.2594189,latitude:28.7486823}, {longitude:77.2595916,latitude:28.7497386}, {longitude:77.2595659,latitude:28.7504901}, {longitude:77.2588449,latitude:28.7512041}, {longitude:77.2586121,latitude:28.7525821}, {longitude:77.2552561,latitude:28.754071}, {longitude:77.2547894,latitude:28.755797}, {longitude:77.2536446,latitude:28.7555637}, {longitude:77.2521308,latitude:28.755385}, {longitude:77.2505333,latitude:28.7551696}, {longitude:77.2483897,latitude:28.7554527}, {longitude:77.246746,latitude:28.7558948}, {longitude:77.2454371,latitude:28.7565118}, {longitude:77.2440906,latitude:28.7572605}, {longitude:77.2424974,latitude:28.7578201}, {longitude:77.2402636,latitude:28.7581729}, {longitude:77.2380505,latitude:28.7590513}, {longitude:77.2386778,latitude:28.7619672}, {longitude:77.23559,latitude:28.7712591}, {longitude:77.2334828,latitude:28.7711838}, {longitude:77.2328799,latitude:28.774953}, {longitude:77.2280336,latitude:28.7845746}, {longitude:77.2244287,latitude:28.7863875}, {longitude:77.2210395,latitude:28.7791603}, {longitude:77.2137954,latitude:28.7843867}, {longitude:77.2072766,latitude:28.7866961}, {longitude:77.2033005,latitude:28.791877}, {longitude:77.2018972,latitude:28.7938703}, {longitude:77.2000239,latitude:28.7969636}, {longitude:77.1992836,latitude:28.801029}, {longitude:77.1992428,latitude:28.8045263}, {longitude:77.2006007,latitude:28.8087861}, {longitude:77.2021248,latitude:28.8135672}, {longitude:77.2184241,latitude:28.8134714}, {longitude:77.2187202,latitude:28.8108429}, {longitude:77.2193639,latitude:28.8174872}, {longitude:77.2207713,latitude:28.8187176}, {longitude:77.2231209,latitude:28.8196257}, {longitude:77.2232518,latitude:28.8205845}, {longitude:77.2232111,latitude:28.8214812}, {longitude:77.2236692,latitude:28.8223178}, {longitude:77.2231166,latitude:28.8231092}, {longitude:77.2215161,latitude:28.8252684}, {longitude:77.2209282,latitude:28.8266971}, {longitude:77.2209196,latitude:28.8285844}, {longitude:77.2214282,latitude:28.8304417}, {longitude:77.2207158,latitude:28.8327783}, {longitude:77.220484,latitude:28.833064}, {longitude:77.2147868,latitude:28.8472316}, {longitude:77.2150014,latitude:28.8480943}, {longitude:77.2154884,latitude:28.8527816}, {longitude:77.2112892,latitude:28.8564633}, {longitude:77.2090846,latitude:28.8572987}, {longitude:77.210946,latitude:28.8673805}, {longitude:77.2169831,latitude:28.8773175}, {longitude:77.2264129,latitude:28.8892136}, {longitude:77.2318446,latitude:28.896872}, {longitude:77.2315625,latitude:28.904221}, {longitude:77.2261106,latitude:28.9090689}, {longitude:77.2171013,latitude:28.9148423}, {longitude:77.211597,latitude:28.9250903}, {longitude:77.2090893,latitude:28.9358231}, {longitude:77.2028972,latitude:28.9417446}, {longitude:77.201919,latitude:28.9462049}, {longitude:77.1992862,latitude:28.9575941}, {longitude:77.2139659,latitude:29.0061417}, {longitude:77.1626746,latitude:29.0485128}, {longitude:77.1573225,latitude:29.0775035}, {longitude:77.1220876,latitude:29.1056022}, {longitude:77.13859,latitude:29.134593}, {longitude:77.1265477,latitude:29.1542175}, {longitude:77.1323458,latitude:29.1716119}, {longitude:77.139928,latitude:29.1818702}, {longitude:77.138144,latitude:29.2010487}, {longitude:77.1252097,latitude:29.2246873}, {longitude:77.1426041,latitude:29.2376216}, {longitude:77.1426041,latitude:29.251448}, {longitude:77.1296698,latitude:29.2728565}, {longitude:77.1443881,latitude:29.2960491}, {longitude:77.1421581,latitude:29.3014012}, {longitude:77.1542004,latitude:29.3170116}, {longitude:77.1537544,latitude:29.3352981}, {longitude:77.1466182,latitude:29.3451104}, {longitude:77.1238716,latitude:29.3451104}, {longitude:77.1176275,latitude:29.3571527}, {longitude:77.1167354,latitude:29.3767772}, {longitude:77.1466182,latitude:29.3897115}, {longitude:77.1256557,latitude:29.410674}, {longitude:77.1247636,latitude:29.4222703}, {longitude:77.139482,latitude:29.4418948}, {longitude:77.1279457,latitude:29.4503775}, {longitude:77.1243176,latitude:29.4530451}, {longitude:77.1145054,latitude:29.4619654}, {longitude:77.1234256,latitude:29.48605}, {longitude:77.1198575,latitude:29.4972003}, {longitude:77.0975569,latitude:29.5119186}, {longitude:77.0850686,latitude:29.5337732}, {longitude:77.0895287,latitude:29.5444775}, {longitude:77.1095993,latitude:29.5529517}, {longitude:77.0948772,latitude:29.5801711}, {longitude:77.1058779,latitude:29.5969372}, {longitude:77.1186925,latitude:29.6448569}, {longitude:77.1292238,latitude:29.6586564}, {longitude:77.1310078,latitude:29.6662386}, {longitude:77.1189655,latitude:29.6858631}, {longitude:77.1323458,latitude:29.6996894}, {longitude:77.1434961,latitude:29.7059336}, {longitude:77.1225336,latitude:29.7429526}, {longitude:77.1131673,latitude:29.7487507}, {longitude:77.1180735,latitude:29.7755114}, {longitude:77.1524164,latitude:29.7933518}, {longitude:77.1537544,latitude:29.8138684}, {longitude:77.1689188,latitude:29.8294788}, {longitude:77.1675807,latitude:29.8464272}, {longitude:77.1822991,latitude:29.8731879}, {longitude:77.1800691,latitude:29.9061927}, {longitude:77.2112899,latitude:29.9405356}, {longitude:77.2295763,latitude:29.9619442}, {longitude:77.2353745,latitude:29.9811227}, {longitude:77.2630272,latitude:30.0020852}, {longitude:77.2732855,latitude:30.034198}, {longitude:77.2706094,latitude:30.0484704}, {longitude:77.2862198,latitude:30.0573906}, {longitude:77.3317129,latitude:30.0645268}, {longitude:77.3682859,latitude:30.0917335}, {longitude:77.3697775,latitude:30.0919433}, {longitude:77.3968306,latitude:30.0957476}, {longitude:77.4142251,latitude:30.1073439}, {longitude:77.3981687,latitude:30.1189402}, {longitude:77.412441,latitude:30.1278604}, {longitude:77.4177932,latitude:30.1412408}, {longitude:77.411549,latitude:30.149715}, {longitude:77.4235913,latitude:30.1684475}, {longitude:77.4467839,latitude:30.1684475}, {longitude:77.4561501,latitude:30.1809358}, {longitude:77.4730986,latitude:30.188518}, {longitude:77.4958452,latitude:30.2335651}, {longitude:77.5199298,latitude:30.2603258}, {longitude:77.545466,latitude:30.2734662}, {longitude:77.5549431,latitude:30.2869036}, {longitude:77.5686257,latitude:30.2971877}, {longitude:77.5789527,latitude:30.3013296}, {longitude:77.5841653,latitude:30.3054995}, {longitude:77.5848606,latitude:30.3214857}, {longitude:77.5806468,latitude:30.324788}, {longitude:77.5797374,latitude:30.3302478}, {longitude:77.5881926,latitude:30.346981}, {longitude:77.5930371,latitude:30.3526502}, {longitude:77.595093,latitude:30.3588697}, {longitude:77.5890906,latitude:30.3654965}, {longitude:77.5869847,latitude:30.3790917}, {longitude:77.5747892,latitude:30.384317}, {longitude:77.5631929,latitude:30.4043875}, {longitude:77.5774652,latitude:30.4124157}, {longitude:77.5846014,latitude:30.4088476}, {longitude:77.6091321,latitude:30.4106317}, {longitude:77.6220664,latitude:30.4048335}, {longitude:77.6341087,latitude:30.4110777}, {longitude:77.661782,latitude:30.3945993}, {longitude:77.6705454,latitude:30.389017}, {longitude:77.6804846,latitude:30.3770664}, {longitude:77.6865099,latitude:30.3803541}, {longitude:77.7108227,latitude:30.3388238}, {longitude:77.7514097,latitude:30.3245515}, {longitude:77.8125133,latitude:30.2906546}, {longitude:77.8611285,latitude:30.2714761}, {longitude:77.8740628,latitude:30.2741522}, {longitude:77.8963634,latitude:30.266124}, {longitude:77.9315983,latitude:30.2469455}, {longitude:77.9030536,latitude:30.2130486}, {longitude:77.8959174,latitude:30.1862879}, {longitude:77.8655886,latitude:30.1550671}, {longitude:77.8383819,latitude:30.11225}, {longitude:77.8129593,latitude:30.0895034}, {longitude:77.7866446,latitude:30.0997617}, {longitude:77.7576539,latitude:30.0480244}, {longitude:77.7732643,latitude:30.03286}, {longitude:77.7723722,latitude:30.0261698}, {longitude:77.7491796,latitude:30.0141275}, {longitude:77.7407054,latitude:29.994057}, {longitude:77.7300012,latitude:29.9873668}, {longitude:77.7451655,latitude:29.9677423}, {longitude:77.7175128,latitude:29.9347375}, {longitude:77.7286631,latitude:29.9284933}, {longitude:77.7331232,latitude:29.9133289}, {longitude:77.7112687,latitude:29.8879063}, {longitude:77.7032405,latitude:29.8896903}, {longitude:77.7032405,latitude:29.8678358}, {longitude:77.7134987,latitude:29.8566855}, {longitude:77.7300012,latitude:29.8531174}, {longitude:77.7291091,latitude:29.837507}, {longitude:77.7424895,latitude:29.8245726}, {longitude:77.7420435,latitude:29.8094083}, {longitude:77.76479,latitude:29.7853236}, {longitude:77.7607759,latitude:29.7527648}, {longitude:77.7692502,latitude:29.7344783}, {longitude:77.7589919,latitude:29.7135158}, {longitude:77.7951188,latitude:29.7005815}, {longitude:77.7893207,latitude:29.6858631}, {longitude:77.8111752,latitude:29.6724827}, {longitude:77.8290157,latitude:29.6693607}, {longitude:77.8700487,latitude:29.6938913}, {longitude:77.8740628,latitude:29.7019195}, {longitude:77.9048376,latitude:29.7023655}, {longitude:77.9101898,latitude:29.6987974}, {longitude:77.920002,latitude:29.7139618}, {longitude:77.9409646,latitude:29.7144078}, {longitude:77.9521148,latitude:29.7054876}, {longitude:77.9409646,latitude:29.7001355}, {longitude:77.9454247,latitude:29.6872011}, {longitude:77.9623731,latitude:29.6702527}, {longitude:77.9721854,latitude:29.6756048}, {longitude:77.9654952,latitude:29.6555343}, {longitude:77.9831142,latitude:29.636872}, {longitude:77.9766455,latitude:29.6140552}, {longitude:77.9623731,latitude:29.6100411}, {longitude:77.9516688,latitude:29.6158393}, {longitude:77.9472087,latitude:29.5904166}, {longitude:77.9726314,latitude:29.5832805}, {longitude:77.9837817,latitude:29.5881866}, {longitude:77.9770915,latitude:29.5583038}, {longitude:77.9940399,latitude:29.5551817}, {longitude:77.9882418,latitude:29.5458155}, {longitude:78.036857,latitude:29.5525057}, {longitude:78.0426552,latitude:29.5721302}, {longitude:78.036411,latitude:29.5810504}, {longitude:78.0439932,latitude:29.5930927}, {longitude:78.0591576,latitude:29.6015669}, {longitude:78.0819042,latitude:29.6020129}, {longitude:78.0908244,latitude:29.6247595}, {longitude:78.1340875,latitude:29.6586564}, {longitude:78.1389936,latitude:29.6760508}, {longitude:78.1657543,latitude:29.6796189}, {longitude:78.1822567,latitude:29.6916612}, {longitude:78.193407,latitude:29.7099477}, {longitude:78.2210597,latitude:29.7175299}, {longitude:78.231318,latitude:29.7041496}, {longitude:78.3031258,latitude:29.7576709}, {longitude:78.3316706,latitude:29.7960279}, {longitude:78.386084,latitude:29.7737273}, {longitude:78.4110641,latitude:29.7724572}, {longitude:78.4123986,latitude:29.7723893}, {longitude:78.4155314,latitude:29.770111}, {longitude:78.4320231,latitude:29.7581169}, {longitude:78.4877746,latitude:29.7411685}, {longitude:78.5073991,latitude:29.6907692}, {longitude:78.5114132,latitude:29.6506282}, {longitude:78.5274696,latitude:29.6243135}, {longitude:78.6050756,latitude:29.5618719}, {longitude:78.6394185,latitude:29.5475995}, {longitude:78.6933858,latitude:29.5087966}, {longitude:78.738879,latitude:29.5047824}, {longitude:78.7857102,latitude:29.4762377}, {longitude:78.815147,latitude:29.3852514}, {longitude:78.8089028,latitude:29.3830213}, {longitude:78.8142549,latitude:29.370087}, {longitude:78.8289733,latitude:29.368303}, {longitude:78.8361095,latitude:29.371425}, {longitude:78.8325414,latitude:29.3812373}, {longitude:78.856626,latitude:29.3856974}, {longitude:78.8624242,latitude:29.3946176}, {longitude:78.8700064,latitude:29.3923876}, {longitude:78.896321,latitude:29.4236084}, {longitude:78.9003351,latitude:29.446801}, {longitude:78.895875,latitude:29.4561672}, {longitude:78.9230817,latitude:29.4561672}, {longitude:78.7973065,latitude:29.3558146}, {longitude:78.7901703,latitude:29.3522465}, {longitude:78.7825881,latitude:29.3464484}, {longitude:78.7812501,latitude:29.332176}, {longitude:78.7888323,latitude:29.3295}, {longitude:78.7977525,latitude:29.3384202}, {longitude:78.7660857,latitude:29.3410963}, {longitude:78.7576115,latitude:29.3495705}, {longitude:78.7415551,latitude:29.332622}, {longitude:78.7281747,latitude:29.3339601}, {longitude:78.7272827,latitude:29.3174576}, {longitude:78.7143484,latitude:29.3192417}, {longitude:78.7585035,latitude:29.2866829}, {longitude:78.7669777,latitude:29.2857908}, {longitude:78.8138089,latitude:29.25234}, {longitude:78.8312034,latitude:29.2608142}, {longitude:78.8423537,latitude:29.254124}, {longitude:78.8526119,latitude:29.2612602}, {longitude:78.8708984,latitude:29.2407437}, {longitude:78.8700064,latitude:29.2287014}, {longitude:78.8829407,latitude:29.2251333}, {longitude:78.8923069,latitude:29.214875}, {longitude:78.8878468,latitude:29.2104149}, {longitude:78.8989971,latitude:29.2023867}, {longitude:78.8863747,latitude:29.19553}, {longitude:78.8868725,latitude:29.1942231}, {longitude:78.9056873,latitude:29.1805321}, {longitude:78.9047953,latitude:29.1631377}, {longitude:78.894983,latitude:29.1568935}, {longitude:78.9003351,latitude:29.1506494}, {longitude:78.9146075,latitude:29.1488653}, {longitude:78.9235277,latitude:29.1573395}, {longitude:78.9444903,latitude:29.1497574}, {longitude:78.9609927,latitude:29.1617997}, {longitude:78.9667908,latitude:29.1653678}, {longitude:78.9788332,latitude:29.1546635}, {longitude:78.9837393,latitude:29.137269}, {longitude:78.9926595,latitude:29.1337009}, {longitude:79.0087159,latitude:29.136377}, {longitude:79.0278944,latitude:29.1328089}, {longitude:79.0296785,latitude:29.1252267}, {longitude:79.0006877,latitude:29.1167525}, {longitude:79.010946,latitude:29.1573395}, {longitude:79.0216503,latitude:29.1671518}, {longitude:79.0270024,latitude:29.1720579}, {longitude:79.0287864,latitude:29.1524334}, {longitude:79.0461809,latitude:29.1546635}, {longitude:79.0729416,latitude:29.1519874}, {longitude:79.1005943,latitude:29.1314709}, {longitude:79.1309231,latitude:29.1292408}, {longitude:79.1420733,latitude:29.1198746}, {longitude:79.1438574,latitude:29.1064942}, {longitude:79.1349372,latitude:29.0819636}, {longitude:79.1371672,latitude:29.0694753}, {longitude:79.1438574,latitude:29.0601091}, {longitude:79.1652659,latitude:29.0511888}, {longitude:79.1541156,latitude:29.0329024}, {longitude:79.1581298,latitude:29.0230901}, {longitude:79.168388,latitude:29.0159539}, {longitude:79.1848904,latitude:29.018184}, {longitude:79.1831064,latitude:29.0244281}, {longitude:79.1973788,latitude:29.0221981}, {longitude:79.2040689,latitude:29.0288883}, {longitude:79.2990694,latitude:28.9530663}, {longitude:79.3070976,latitude:28.9584184}, {longitude:79.3516987,latitude:28.9526203}, {longitude:79.3597269,latitude:28.9704608}, {longitude:79.3771214,latitude:28.9370099}, {longitude:79.401652,latitude:28.9321038}, {longitude:79.3958538,latitude:28.8852726}, {longitude:79.4110182,latitude:28.8558358}, {longitude:79.4333188,latitude:28.861634}, {longitude:79.4596335,latitude:28.8589579}, {longitude:79.4765819,latitude:28.8669861}, {longitude:79.5002524,latitude:28.8633176}, {longitude:79.5159649,latitude:28.8837337}, {longitude:79.5363474,latitude:28.8924088}, {longitude:79.5541879,latitude:28.8785824}, {longitude:79.5474977,latitude:28.8544978}, {longitude:79.5510658,latitude:28.8460236}, {longitude:79.5764885,latitude:28.8504837}, {longitude:79.5920989,latitude:28.8665401}, {longitude:79.6135074,latitude:28.8687702}, {longitude:79.6309019,latitude:28.8589579}, {longitude:79.6661368,latitude:28.8482536}, {longitude:79.6728269,latitude:28.8576199}, {longitude:79.6844232,latitude:28.8571739}, {longitude:79.6879913,latitude:28.8683241}, {longitude:79.7053858,latitude:28.8701082}, {longitude:79.7120759,latitude:28.8808125}, {longitude:79.7406207,latitude:28.8830425}, {longitude:79.7499869,latitude:28.8933008}, {longitude:79.7745175,latitude:28.8919627}, {longitude:79.791466,latitude:28.8705542}, {longitude:79.8026163,latitude:28.8718922}, {longitude:79.8084144,latitude:28.862972}, {longitude:79.8079684,latitude:28.8562818}, {longitude:79.7945881,latitude:28.8527137}, {longitude:79.7981562,latitude:28.8460236}, {longitude:79.793696,latitude:28.8393334}, {longitude:79.8012782,latitude:28.8295211}, {longitude:79.8128745,latitude:28.8299672}, {longitude:79.8244708,latitude:28.8446855}, {longitude:79.8539076,latitude:28.8442395}, {longitude:79.8605978,latitude:28.8339813}, {longitude:79.8753161,latitude:28.8304132}, {longitude:79.8757621,latitude:28.8183709}, {longitude:79.8672879,latitude:28.7965163}, {longitude:79.8891425,latitude:28.785812}, {longitude:79.9011848,latitude:28.8023144}, {longitude:79.9118891,latitude:28.7648495}, {longitude:79.9225933,latitude:28.7327367}, {longitude:79.9408798,latitude:28.7215864}, {longitude:79.9676405,latitude:28.7157882}, {longitude:79.988157,latitude:28.7171263}, {longitude:80.0028754,latitude:28.7269385}, {longitude:80.0336502,latitude:28.7612814}, {longitude:80.0403404,latitude:28.783136}, {longitude:80.025622,latitude:28.7996384}, {longitude:80.058889,latitude:28.831111}, {longitude:80.0572025,latitude:28.8337798}, {longitude:80.0586268,latitude:28.8353193}, {longitude:80.063056,latitude:28.8275}, {longitude:80.067778,latitude:28.825}, {longitude:80.073889,latitude:28.824167}, {longitude:80.079722,latitude:28.823333}, {longitude:80.086389,latitude:28.823333}, {longitude:80.092778,latitude:28.823333}, {longitude:80.099444,latitude:28.823333}, {longitude:80.104722,latitude:28.821667}, {longitude:80.110833,latitude:28.820556}, {longitude:80.116667,latitude:28.819722}, {longitude:80.121944,latitude:28.818056}, {longitude:80.1275,latitude:28.816389}, {longitude:80.132222,latitude:28.813889}, {longitude:80.135833,latitude:28.809722}, {longitude:80.14,latitude:28.806111}, {longitude:80.143611,latitude:28.801944}, {longitude:80.147222,latitude:28.797778}, {longitude:80.150833,latitude:28.793611}, {longitude:80.154444,latitude:28.789167}, {longitude:80.1575,latitude:28.784167}, {longitude:80.160556,latitude:28.779167}, {longitude:80.164167,latitude:28.775}, {longitude:80.168056,latitude:28.770556}, {longitude:80.172222,latitude:28.767222}, {longitude:80.176389,latitude:28.763889}, {longitude:80.181111,latitude:28.761389}, {longitude:80.185833,latitude:28.758611}, {longitude:80.191389,latitude:28.756944}, {longitude:80.196667,latitude:28.755278}, {longitude:80.201944,latitude:28.753611}, {longitude:80.208056,latitude:28.752778}, {longitude:80.214444,latitude:28.752778}, {longitude:80.221111,latitude:28.752778}, {longitude:80.2275,latitude:28.7525}, {longitude:80.234167,latitude:28.7525}, {longitude:80.24,latitude:28.751667}, {longitude:80.246111,latitude:28.750833}, {longitude:80.250278,latitude:28.7475}, {longitude:80.254444,latitude:28.744167}, {longitude:80.256944,latitude:28.738056}, {longitude:80.258333,latitude:28.732778}, {longitude:80.260278,latitude:28.725833}, {longitude:80.262222,latitude:28.719167}, {longitude:80.265833,latitude:28.715}, {longitude:80.270556,latitude:28.7125}, {longitude:80.275278,latitude:28.709722}, {longitude:80.280833,latitude:28.708056}, {longitude:80.286667,latitude:28.707222}, {longitude:80.291944,latitude:28.705556}, {longitude:80.298056,latitude:28.704722}, {longitude:80.302778,latitude:28.701944}, {longitude:80.308333,latitude:28.699722}, {longitude:80.313056,latitude:28.697222}, {longitude:80.316667,latitude:28.692778}, {longitude:80.320278,latitude:28.688611}, {longitude:80.3212586,latitude:28.6863232}, {longitude:80.322778,latitude:28.682778}, {longitude:80.325833,latitude:28.677778}, {longitude:80.328889,latitude:28.6725}, {longitude:80.3325,latitude:28.668333}, {longitude:80.335556,latitude:28.663333}, {longitude:80.338056,latitude:28.657222}, {longitude:80.341111,latitude:28.652222}, {longitude:80.343333,latitude:28.646389}, {longitude:80.346944,latitude:28.642222}, {longitude:80.350556,latitude:28.637778}, {longitude:80.353611,latitude:28.632778}, {longitude:80.357778,latitude:28.629444}, {longitude:80.362778,latitude:28.626944}, {longitude:80.368056,latitude:28.625}, {longitude:80.373889,latitude:28.624167}, {longitude:80.380556,latitude:28.624167}, {longitude:80.3875,latitude:28.625}, {longitude:80.395278,latitude:28.626667}, {longitude:80.403056,latitude:28.628056}, {longitude:80.409722,latitude:28.630278}, {longitude:80.416389,latitude:28.6325}, {longitude:80.423611,latitude:28.633333}, {longitude:80.429444,latitude:28.6325}, {longitude:80.435,latitude:28.630833}, {longitude:80.440278,latitude:28.628889}, {longitude:80.445,latitude:28.626389}, {longitude:80.449722,latitude:28.623889}, {longitude:80.454722,latitude:28.621111}, {longitude:80.458056,latitude:28.616944}, {longitude:80.461944,latitude:28.612778}, {longitude:80.464167,latitude:28.606944}, {longitude:80.466667,latitude:28.600833}, {longitude:80.469167,latitude:28.595}, {longitude:80.472222,latitude:28.59}, {longitude:80.476389,latitude:28.586389}, {longitude:80.481111,latitude:28.583889}, {longitude:80.485833,latitude:28.581389}, {longitude:80.490556,latitude:28.578889}, {longitude:80.494722,latitude:28.575278}, {longitude:80.499722,latitude:28.572778}, {longitude:80.503889,latitude:28.569444}, {longitude:80.508611,latitude:28.566944}, {longitude:80.515,latitude:28.566944}, {longitude:80.518056,latitude:28.571111}, {longitude:80.52,latitude:28.575833}, {longitude:80.521111,latitude:28.580833}, {longitude:80.52,latitude:28.587222}, {longitude:80.518333,latitude:28.593889}, {longitude:80.516389,latitude:28.600833}, {longitude:80.514722,latitude:28.6075}, {longitude:80.512778,latitude:28.614167}, {longitude:80.510833,latitude:28.621111}, {longitude:80.51,latitude:28.627222}, {longitude:80.508056,latitude:28.633889}, {longitude:80.507222,latitude:28.640278}, {longitude:80.505278,latitude:28.646944}, {longitude:80.5035837,latitude:28.6521168}, {longitude:80.5039839,latitude:28.6557062}, {longitude:80.5030457,latitude:28.6644178}, {longitude:80.5056648,latitude:28.670938}, {longitude:80.5106918,latitude:28.6742555}, {longitude:80.522106,latitude:28.6813438}, {longitude:80.5396655,latitude:28.6907328}, {longitude:80.5503463,latitude:28.6917111}, {longitude:80.5533863,latitude:28.6916125}, {longitude:80.5614354,latitude:28.6834716}, {longitude:80.5664869,latitude:28.6806886}, {longitude:80.570368,latitude:28.6810942}, {longitude:80.5723318,latitude:28.6833286}, {longitude:80.5735446,latitude:28.6862824}, {longitude:80.58099,latitude:28.6836438}, {longitude:80.581944,latitude:28.6820925}, {longitude:80.5842881,latitude:28.6799279}, {longitude:80.5865582,latitude:28.6793828}, {longitude:80.5906683,latitude:28.6798021}, {longitude:80.5932969,latitude:28.6789635}, {longitude:80.590454,latitude:28.6745913}, {longitude:80.5830856,latitude:28.6738946}, {longitude:80.5807993,latitude:28.6712902}, {longitude:80.579444,latitude:28.668333}, {longitude:80.5797659,latitude:28.6649976}, {longitude:80.578333,latitude:28.663056}, {longitude:80.580833,latitude:28.657222}, {longitude:80.585,latitude:28.653611}, {longitude:80.589722,latitude:28.651111}, {longitude:80.594444,latitude:28.648611}, {longitude:80.599444,latitude:28.646111}, {longitude:80.604167,latitude:28.643333}, {longitude:80.609444,latitude:28.641667}, {longitude:80.614167,latitude:28.639167}, {longitude:80.619722,latitude:28.6375}, {longitude:80.625556,latitude:28.636389}, {longitude:80.631389,latitude:28.635556}, {longitude:80.638056,latitude:28.635556}, {longitude:80.644444,latitude:28.635556}, {longitude:80.650556,latitude:28.634444}, {longitude:80.656389,latitude:28.633611}, {longitude:80.6625,latitude:28.632778}, {longitude:80.666111,latitude:28.628611}, {longitude:80.666944,latitude:28.622222}, {longitude:80.670556,latitude:28.618056}, {longitude:80.674722,latitude:28.614722}, {longitude:80.679444,latitude:28.611944}, {longitude:80.684722,latitude:28.610278}, {longitude:80.688889,latitude:28.606944}, {longitude:80.689722,latitude:28.600556}, {longitude:80.689722,latitude:28.595}, {longitude:80.691667,latitude:28.588056}, {longitude:80.695278,latitude:28.583889}, {longitude:80.7,latitude:28.581389}, {longitude:80.704722,latitude:28.578611}, {longitude:80.71,latitude:28.576944}, {longitude:80.715278,latitude:28.575278}, {longitude:80.721389,latitude:28.574444}, {longitude:80.727222,latitude:28.573611}, {longitude:80.734444,latitude:28.574167}, {longitude:80.740833,latitude:28.574167}, {longitude:80.746944,latitude:28.573333}, {longitude:80.752222,latitude:28.571667}, {longitude:80.756944,latitude:28.568889}, {longitude:80.760556,latitude:28.564722}, {longitude:80.763611,latitude:28.559722}, {longitude:80.767222,latitude:28.555278}, {longitude:80.770278,latitude:28.549722}, {longitude:80.773333,latitude:28.544444}, {longitude:80.776389,latitude:28.539444}, {longitude:80.78,latitude:28.535278}, {longitude:80.784722,latitude:28.5325}, {longitude:80.789444,latitude:28.53}, {longitude:80.793611,latitude:28.526667}, {longitude:80.798333,latitude:28.523889}, {longitude:80.803611,latitude:28.522222}, {longitude:80.808611,latitude:28.519722}, {longitude:80.813333,latitude:28.517222}, {longitude:80.8175,latitude:28.513611}, {longitude:80.822222,latitude:28.511111}, {longitude:80.826944,latitude:28.508611}, {longitude:80.831111,latitude:28.505278}, {longitude:80.836389,latitude:28.503333}, {longitude:80.841944,latitude:28.501667}, {longitude:80.847778,latitude:28.500833}, {longitude:80.854167,latitude:28.500556}, {longitude:80.861111,latitude:28.502778}, {longitude:80.866944,latitude:28.505556}, {longitude:80.873611,latitude:28.5075}, {longitude:80.880556,latitude:28.508333}, {longitude:80.887222,latitude:28.508056}, {longitude:80.8925,latitude:28.506389}, {longitude:80.896111,latitude:28.502222}, {longitude:80.899167,latitude:28.497222}, {longitude:80.900833,latitude:28.490278}, {longitude:80.901667,latitude:28.484167}, {longitude:80.900833,latitude:28.478889}, {longitude:80.899722,latitude:28.473611}, {longitude:80.898611,latitude:28.468611}, {longitude:80.901111,latitude:28.4625}, {longitude:80.905833,latitude:28.46}, {longitude:80.910556,latitude:28.457222}, {longitude:80.914722,latitude:28.453889}, {longitude:80.919444,latitude:28.451389}, {longitude:80.924722,latitude:28.449722}, {longitude:80.929167,latitude:28.446111}, {longitude:80.933889,latitude:28.443611}, {longitude:80.938611,latitude:28.441111}, {longitude:80.943889,latitude:28.439167}, {longitude:80.950833,latitude:28.44}, {longitude:80.956667,latitude:28.4425}, {longitude:80.9625,latitude:28.445278}, {longitude:80.967778,latitude:28.443611}, {longitude:80.971389,latitude:28.439167}, {longitude:80.976111,latitude:28.436667}, {longitude:80.982222,latitude:28.435833}, {longitude:80.988333,latitude:28.436944}, {longitude:80.993056,latitude:28.440278}, {longitude:81,latitude:28.442222}, {longitude:81.006944,latitude:28.443056}, {longitude:81.011111,latitude:28.439722}, {longitude:81.014722,latitude:28.435278}, {longitude:81.015556,latitude:28.429167}, {longitude:81.015556,latitude:28.423333}, {longitude:81.014444,latitude:28.418056}, {longitude:81.013333,latitude:28.413056}, {longitude:81.014167,latitude:28.406667}, {longitude:81.018889,latitude:28.404167}, {longitude:81.024444,latitude:28.402222}, {longitude:81.031389,latitude:28.403056}, {longitude:81.037222,latitude:28.405833}, {longitude:81.044444,latitude:28.406389}, {longitude:81.049722,latitude:28.404722}, {longitude:81.054444,latitude:28.402222}, {longitude:81.059722,latitude:28.400278}, {longitude:81.065,latitude:28.398611}, {longitude:81.070556,latitude:28.396944}, {longitude:81.076389,latitude:28.395833}, {longitude:81.082222,latitude:28.395}, {longitude:81.088333,latitude:28.394167}, {longitude:81.094167,latitude:28.393333}, {longitude:81.099444,latitude:28.391389}, {longitude:81.105278,latitude:28.390556}, {longitude:81.110833,latitude:28.388611}, {longitude:81.116667,latitude:28.387778}, {longitude:81.121944,latitude:28.386111}, {longitude:81.127222,latitude:28.384444}, {longitude:81.133333,latitude:28.383333}, {longitude:81.139167,latitude:28.3825}, {longitude:81.144444,latitude:28.380833}, {longitude:81.150278,latitude:28.379722}, {longitude:81.155833,latitude:28.378056}, {longitude:81.161111,latitude:28.376389}, {longitude:81.166944,latitude:28.375278}, {longitude:81.172222,latitude:28.373611}, {longitude:81.177778,latitude:28.371944}, {longitude:81.183056,latitude:28.37}, {longitude:81.188889,latitude:28.369167}, {longitude:81.193611,latitude:28.366667}, {longitude:81.197778,latitude:28.363056}, {longitude:81.201944,latitude:28.359722}, {longitude:81.205556,latitude:28.355556}, {longitude:81.209167,latitude:28.351111}, {longitude:81.2125,latitude:28.3475}, {longitude:81.214722,latitude:28.341667}, {longitude:81.217778,latitude:28.336667}, {longitude:81.220278,latitude:28.330556}, {longitude:81.2225,latitude:28.324722}, {longitude:81.224444,latitude:28.318056}, {longitude:81.226111,latitude:28.311111}, {longitude:81.228611,latitude:28.305278}, {longitude:81.230278,latitude:28.298333}, {longitude:81.232222,latitude:28.291667}, {longitude:81.2328334,latitude:28.2892526}, {longitude:81.233611,latitude:28.286389}, {longitude:81.235833,latitude:28.280278}, {longitude:81.237778,latitude:28.273611}, {longitude:81.240556,latitude:28.268611}, {longitude:81.243611,latitude:28.263333}, {longitude:81.246667,latitude:28.258333}, {longitude:81.25,latitude:28.253889}, {longitude:81.254167,latitude:28.250556}, {longitude:81.257778,latitude:28.246389}, {longitude:81.261944,latitude:28.243056}, {longitude:81.266111,latitude:28.239444}, {longitude:81.270278,latitude:28.236111}, {longitude:81.274444,latitude:28.2325}, {longitude:81.278056,latitude:28.228333}, {longitude:81.281944,latitude:28.225}],
			stroke : {
			color : '#f75b46',
			weight : 2,
		},
		editable : true,
		draggable : false,
		geodesic : false,
		visible : true,
		fill : {
			color : 'rgb(242,230,223)',
			opacity : 0.7
		}
	};
    
    $scope.pixelOffset = {
    		pixelOffset : new google.maps.Size(0, -28)
	};
    $scope.showInfrastructure = false;
    $scope.map = {
    		center : {
    			latitude : 27.4, 
    			longitude :80 
    		},
    		bounds : {},
    		clickMarkers : [],
    		zoom : 6,
    		events : {
    			"mouseover" : function(mapModel, eventName, originalEventArgs) {
    				for (var i = 0; i < $scope.map.markers.length; i++) {
    					if ($scope.map.markers[i].id == originalEventArgs.id) {
    						$scope.map.markers[i].showWindow = true;
    						break;
    					}
    				}

    				// clickMarkers.windows.showWindow = true;
    				$scope.$apply();
    			},
    			"mouseout" : function(mapModel, eventName, originalEventArgs) {
    				for (var i = 0; i < $scope.map.markers.length; i++) {
    					if ($scope.map.markers[i].id == originalEventArgs.id) {
    						$scope.map.markers[i].showWindow = false;
    						break;
    					}
    				}
    				// clickMarkers.windows.showWindow = true;
    				$scope.$apply();
    			},
    			"click" : function(mapModel, eventName, originalEventArgs) {
    				$scope.showInfrastructure = true;
    				for(var j=0; j < $scope.selectedCCIArr.length; j++){
    					if(originalEventArgs.title == $scope.selectedCCIArr[j].name){
    						$scope.selectedCCI = $scope.selectedCCIArr[j];
    						$scope.selectedCCI.boundary_wall_value = $scope.selectedCCI.boundary_wall_value == true ? "Yes" : "No";
    						$scope.selectedCCI.drinking_water_facility_value = $scope.selectedCCI.drinking_water_facility_value == true ? "Yes" : "No";
    						$scope.selectedCCI.power_Backupfacility_AllRooms = $scope.selectedCCI.power_Backupfacility_AllRooms == true ? "Yes" : "No";
    						$scope.selectedCCI.separate_Toiletsfor_Staff = $scope.selectedCCI.separate_Toiletsfor_Staff == true ? "Yes" : "No";
    						$scope.selectedCCI.proper_elec_value = $scope.selectedCCI.proper_elec_value == true ? "Yes" : "No";
    						$scope.selectedCCIKeys = $scope.selectedCCI;
    					}
    				}
    				switchInfrastructureFlag();
    				$scope.showName = true;
    				data = [];
    				if($scope.selectedTypeShow=='Age')
    				{
    					for(var j=0;j<$scope.masterCasteData.length;j++)
    					{

    					if($scope.masterAgeData[j].cciId == $scope.selectedCCI.cciId){
    						data.push($scope.masterAgeData[j]);
    					}
    				
    					}	
    				}	
    				else
    				{	
    				for(var j=0;j<$scope.masterCasteData.length;j++){
    					if($scope.masterCasteData[j].cciId == $scope.selectedCCI.cciId){
    						data.push($scope.masterCasteData[j]);
    					}
    				}
    				}
    				
    				d3.select(".barChart").select("svg").remove();
    				resetCasteGraph();
    				casteGraph();
    				$scope.changeStatsCCI();
    				$scope.changeInfrastructure();
    				selectDefaultHr();
    				$('html, body').animate({
    					'scrollTop' : $("#scrollHere").position().top
    				},1000);
    			}
    		}
    	};
    $scope.map.markers = [];

//			    @Naseem Akhtar
			        // Graph Visualization Starts Here
    		
    			var colors = [ "#f0b569", "#f19765", "#e86f2c" ];
    			var colors1 = [ "#f0b569", "#f19765", "#e86f2c" ];
    			var colors2 = [ "#e86f2c" ,"#f0b569","#f19765" ];
    			
					// For caste
    			$http.get('getCasteData').
    		    then(function(result){
    		    	console.log(result.data);
    		    	$scope.masterCasteData = result.data;
    		   }, function(error){
    			   console.log(error);
    		   });
    			
    			$http.get('getAgeData').
    		    then(function(result){
    		    	console.log(result.data);
    		    	$scope.masterAgeData = result.data;
    		   }, function(error){
    			   console.log(error);
    		   });
    			
			/* Data in strings like it would be if imported from a csv */
    			var data = [];
//				var data = [ {
//					category : "General",
//					boys : "02",
//					girls : "05"
//				}, {
//					category : "OBC",
//					boys : "06",
//					girls : "04"
//				}, {
//					category : "SC",
//					boys : "05",
//					girls : "05"
//				}, {
//					category : "ST",
//					boys : "01",
//					girls : "0"
//				}, {
//					category : "EBC",
//					boys : "02",
//					girls : "06"
//				}, {
//					category : "Others",
//					boys : "03",
//					girls : "07"
//				}, ];

				var rect;
				var x;
				var y;
				var svg;
				var width;
				var height;
				
				var resetCasteGraph = function(){
					svg.selectAll('rect').attr("y", function(d){
			        	return height;
			        })
			        .attr("height", 0);
				};
				
				var animateCasteGraph = function(){
					svg.selectAll("rect")
					.transition().duration(2000)
					.attr("y", function(d){
						if(isNaN(d.y) || d.y == undefined){}
						else
							return y(d.y0 + d.y);
					})
					.attr("height", function(d){
						if(isNaN(d.y) || d.y == undefined){}
						else
						return y(d.y0) - y(d.y0 + d.y);
					});
				};
				
				var casteGraph = function(){
					d3.select(".barChart svg").remove();
				var margin = {
						top : 20,
						right : 80,
						bottom : 35,
						left : 30
				};

						width = 450 - margin.left - margin.right, 
						height = 300- margin.top - margin.bottom;

					svg = d3.select(".barChart").append("svg").attr(
							"width", width + margin.left + margin.right).attr(
							"height", height + margin.top + margin.bottom)
							.append("g").attr(
									"transform",
									"translate(" + margin.left + ","
											+ margin.top + ")");
					

//					// Transpose the data into layers
					var dataset = d3.layout.stack()(
							[ "girls", "boys", "thirdGender" ].map(function(childType) {
								return data.map(function(d) {
									return {
										x : d.category,
										y : +d[childType]
									};
								});
							}));
//
//					// Set x, y and colors
					x = d3.scale.ordinal().domain(
							dataset[0].map(function(d) {
								return d.x;
							})).rangeRoundBands([ 10, width - 10 ], 0.02);

					y = d3.scale.linear().domain(
							[ 0, d3.max(dataset, function(d) {
								return d3.max(d, function(d) {
									return d.y0 + d.y;
								});
							}) ]).range([ height, 0 ]);
//
//
//					// Define and draw axes
					var yAxis = d3.svg.axis().scale(y).orient("left").ticks(5);

					var xAxis = d3.svg.axis().scale(x).orient("bottom");
					
					 var tip = d3.tip()
				        .attr('class', 'd3-tip')
				        .offset([-10, 0])
				        .html(function(d) {
				            return "<strong>Value:</strong> <span style='color:#ffcc00'>" + d.y + "</span>";
				        });
					 
					svg.call(tip);

					svg.append("g").attr("class", "y axis").attr("transform",
							"translate(10,0)").call(yAxis);

					svg.append("g").attr("class", "x axis").attr("transform",
							"translate(0," + height + ")").call(xAxis);
//
//					// Create groups for each series, rects for each segment
					var groups = svg.selectAll("g.cost").data(dataset).enter()
							.append("g").attr("class", "cost").style("fill",
									function(d, i) {
										return colors[i];
									}).attr("transform",
											"translate(10,0)");

					rect = groups.selectAll("rect").data(function(d) {
						return d;
					}).enter().append("rect").attr("x", function(d) {
						return dataset[0].length==1?x(d.x)+129:dataset[0].length==2?x(d.x)+53:dataset[0].length==3?x(d.x)+26:dataset[0].length==4?x(d.x)+14:x(d.x); // adjust the bar to left or right.
					}).attr("y", function(d) {
						return height;
					}).attr("height", 0)
					.attr("width", 30)
					.on('mouseover', tip.show)
			        .on('mouseout', tip.hide);
					
					svg.selectAll("rect")
					.transition().duration(3000)
					.attr("y", function(d){
						return y(d.y0 + d.y);
					})
					.attr("height", function(d){
						return y(d.y0) - y(d.y0 + d.y);
					});
					
					// Draw legend
					var legend = svg.selectAll(".legend").data(colors1).enter()
							.append("g").attr("class", "legend").attr(
									"transform", function(d, i) {
										return "translate(46," + i * 20 + ")";
									});

					legend.append("rect").attr("x", width - 18).attr("width",
							18).style("height", "18px").style("fill",
							function(d, i) {
								return colors1.slice().reverse()[i];
							});

					legend.append("text").attr("x", width + 5).attr("y", 9)
							.attr("dy", ".35em").style("text-anchor", "start")
							.text(function(d, i) {
								switch (i) {
								case 0:
									return "Third Gender";
								case 1:
									return "Boys";
								case 2:
									return "Girls";
								}
							});
				};
				casteGraph();

					// Caste visualization ends here

					// For Sex starts here
					var svg1;
					var height1;
					var y1;
					
					var resetSexGraph = function(){
						svg1.selectAll('rect').attr("y", function(d){
				        	return height1;
				        })
				        .attr("height", 0);
					};
					
					var startAnimationSexGraph = function(){
						svg1.selectAll('rect')
				        .transition().duration(2000)
				        .attr('y', function(d) {
				            return y1(d.value);
				        })
				        .attr("height", function(d) {
				            return height1 - y1(d.value);
				        });
					};
					
					var sexGraph = function(){
						d3.select(".sexBarChart svg").remove();
					    var margin1 = {
					            top: 20,
					            right: 20,
					            bottom: 35,
					            left: 30
					        },
					        width1 = 350 - margin1.left - margin1.right;
				        height1 = 300 - margin1.top - margin1.bottom;

//					    var colors = ["#ffc000", "#ff9402"];

					    var x1 = d3.scale.ordinal()
					        .domain(data1, function(d) {
					            return d.name;
					            console.log(d);
					        })
					        .rangeRoundBands([10, width1], 0.02);

					    y1 = d3.scale.linear()
					        .range([height1, 0]);

					    var xAxis1 = d3.svg.axis()
					        .scale(x1)
					        .orient("bottom");

					    var yAxis1 = d3.svg.axis()
					        .scale(y1)
					        .orient("left")
					        .ticks(5);

					    var tip = d3.tip()
					        .attr('class', 'd3-tip')
					        .offset([-10, 0])
					        .html(function(d) {
					            return "<strong>Value:</strong> <span style='color:#ffcc00'>" + d.value + "</span>";
					        });

					    svg1 = d3.select(".sexBarChart").append("svg")
					        .attr("width", width1 + margin1.left + margin1.right)
					        .attr("height", height1 + margin1.top + margin1.bottom)
					        .append("g")
					        .attr("transform", "translate(" + margin1.left + "," + margin1.top + ")");

					    svg1.call(tip);

					    x1.domain(data1.map(function(d) {
					        return d.name;
					    }));
					    y1.domain([0, d3.max(data1, function(d) {
					        return d.value;
					    })]);

					    svg1.append("g")
					        .attr("class", "x axis")
					        .attr("transform", "translate(0," + height1 + ")")
					        .call(xAxis1);

					    svg1.append("g")
					        .attr("class", "y axis")
					        .attr("transform", "translate(10,0)")
					        .call(yAxis1);

					    svg1.selectAll(".bar")
					        .data(data1)
					        .enter().append("rect")
					        .attr("class", "bar")
					        .style("fill",
					            function(d, i) {
					                return colors1[i];
					            })
					        .attr("x", function(d) {
					            return x1(d.name) + 34;
					        })
					        .attr("width", 30)
					        .attr("y", function(d){
					        	return height1;
					        })
					        .attr("height", 0)
					        .on('mouseover', tip.show)
					        .on('mouseout', tip.hide);
					    
					    svg1.selectAll('rect')
				        .transition().duration(3000)
				        .attr('y', function(d) {
				        	if(!isNaN(y1(d.value)))
				        		return y1(d.value);
				        	else
				        		return 0;
				        })
				        .attr("height", function(d) {
				            return height1 - y1(d.value);
				        });

					    function type(d) {
					        d.value = +d.value;
					        return d;
					    }
					    
					 // Draw legend
						var legend = svg1.selectAll(".legend").data(colors2).enter()
								.append("g").attr("class", "legend").attr(
										"transform", function(d, i) {
											return "translate(46," + i * 20 + ")";
										});

						legend.append("rect").attr("x", width1 - 18).attr("width",
								18).attr("y", 0).style("height", "18px").style("fill",
								function(d, i) {
									return colors2.slice().reverse()[i];
								});

						legend.append("text").attr("x", width1 + 5).attr("y", 9)
								.attr("dy", ".35em").style("text-anchor", "start")
								.text(function(d, i) {
									switch (i) {
									case 0:
										return "Girls";
									case 1:
										return "Boys";
									case 2:
										return "Third Gender";
									}
								});
					};
					
//					casteGraph();
					
					// For Sex ends here
			      
			       
});

app.controller("navCtrl",['$scope','$location','$rootScope',function($scope,$location,$rootScope){
	
	var topdata={"links": [{
	    "name": "Help",
	    "url":"help" ,
	    
	}, {
	"name": "FAQ",
	    "url":"faq",
	    
	}, ,{
	"name": "Contact Us",
	"url":"contact",
	"className": ""
	}]
	}
	$scope.toplinks = topdata.links;
	var data = {
	     "links": [{
					    "name": "Home",
					        "url":"home" ,
					        
					},{
					    "name": "About Us",
					        "url":"about",
					        
					},  {
						"name": "ICPS Structure",
	                "url": "#",
	                "className": "dropdown",
	                "sub": [{
	                "name": "Structure",
		            "url": "structure"
		            
		            }, {
		                "name": "Info Map",
		                    "url":"cciInfoMap"
		            }]
	        },{
			    "name": "Programme",
		        "url": "programme",
		       
	        },{
			    "name": "RTI",
		        "url": "rti",
		       
	        },{
			    "name": "Gallery",
		        "url": "#",
	            "className": "dropdown",
	            "sub": [{
	            "name": "Photo Gallery",
	            "url": "gallery"
	            
	            }, {
	                "name": "Audio Gallery",
	                "url":"audiogallery"
	            }, {
	                "name": "Video Gallery",
	                "url":"videogallery"
	            }]
	        },{
			    "name": "CCTS Login",
			    "url": "ccts_login",
		        
	        }]
	    };
	console.log(data.links);

	$scope.links = data.links;


	}]);
