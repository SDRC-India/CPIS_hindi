var app = angular.module('upcpisApp', ['simpleAngularTicker']);
app.run(function ($rootScope) {
	$rootScope.rootpath="/CPIS/";
	return $rootScope.rootpath;
});
app.run(function ($rootScope) {
	$rootScope.rootlang=1;//Language english 1 || Hindi 2
	return $rootScope.rootlang;
});	
/**********************Nav controller**********************/
app.controller("navCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	
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

     $scope.links= [{
				    "name": "Home",
				        "url": "home" ,
				        
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
	       
        },
        
       {
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
		    "name": "CCTS  Login",
	        "url": "ccts_login",
	        
        }];
     
     $scope.activate = function (link) {
    	    
    	    $scope.active = link;
    	    
    	  }
     
     
     
//     $scope.navClass = function (page) {
//         var currentRoute = $location.path().substring(1) || 'home';
//         return page === currentRoute ? 'activeMenu' : '';
//     };  

//$scope.links = data.links;

$scope.changeLang = function(lang){
//	if(lang=='hi')
//		$rootScope.$broadcast('lang',lang);
	if(lang=='en')
		$scope.rootlang=1;
	else
		$scope.rootlang=2;
		$http({
	        method : "GET",
	        url : "setLang?language="+$scope.rootlang
	    }).then(function mySucces(response) {
	    	$rootScope.$broadcast('lang',lang);
	    	console.log(response.data);
});
};

$rootScope.$on("lang", function(event, data){
	if(data=='hi'){
		$scope.rootlang=2;
	}
	else{
		$scope.rootlang=1;
	}
	
	$scope.getheaderContents();
});

$scope.getheaderContents = function(){
$http({
    method : "GET",
    url : "getContent?viewName=projectName&language="+$scope.rootlang
}).then(function mySucces(response) {
	//console.log(response.data[0].title);
    //$scope.myWelcome = response.data;
	$scope.subtitle=response.data[0].content;
	$scope.subtitle1=response.data[1].content;
	$scope.title=response.data[0].title;
}, function myError(response) {
    $scope.myWelcome = response.statusText;
});
}
$scope.getheaderContents();
}]);
//==================== dahboard at glance controller====================//

//===========================dahboard at glance controller ends===========//
//===========================content for home page  starts===========//

app.controller("homeCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	var url = $location.absUrl().split('/')[4];//current page
	console.log($scope.rootlang);//root language
	$rootScope.$on("lang", function(event, data){
		if(data=='hi'){
			$scope.rootlang=2;
		}
		else{
			$scope.rootlang=1;
		}
			$scope.getAllContent();
			
	});
	$scope.getAllContent = function(){
		$(".loader").css("display", "block");
		$scope.mediaRecieved=false;
		$scope.objectivesRecieved=false;
		$scope.noticesRecieved=false;
		$scope.missionsRecieved=false;
		$scope.achievementsRecieved=false;
		$scope.projectsRecieved=false;
		$scope.structuresRecieved=false;
		$scope.noticesRecieved=false;
		
		$scope.checkDataRecieved = function(){
			if($scope.mediaRecieved && $scope.objectivesRecieved && $scope.noticesRecieved && $scope.missionsRecieved
					&& $scope.achievementsRecieved && $scope.projectsRecieved && $scope.structuresRecieved && $scope.noticesRecieved){
				$(".loader").css("display", "none");
			}
		}
		
		
	$http({
        method : "GET",
        url : "getContent?viewName=home&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	console.log(response.data);
    	$scope.keyContacts = [];
    	angular.forEach(response.data,function(value, key){
    		if(value.contentId==9 || value.contentId==27){
    			$scope.keyContactTitle=value.title;
    		}
    		if(value.parentId==9 || value.parentId==27)
    			$scope.keyContacts.push(value);
    		
    	});
    	
        //$scope.myWelcome = response.data;
    	$scope.contentData=response.data;
    	//console.log(homeData);
     
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	
	$http.get('countAdd').
    then(function(result){
    	if(result.data.noOfCounter==undefined)
    		$scope.count=1;
    	else
    	$scope.count=result.data.noOfCounter;

   }, function(error){
	   console.log(error);
   });
	
	$http.get("getContent?viewName=objectives&language="+$scope.rootlang)
	.then(function(result){
		$scope.objectivesRecieved=true;
		$scope.checkDataRecieved();
		$scope.objectiveData = result.data;
	}, function(error){
		console.log(error);
	});
	
	$http.get("getContent?viewName=mission&language="+$scope.rootlang)
	.then(function(result){
		$scope.missionsRecieved=true;
		$scope.checkDataRecieved();
		$scope.missionData = result.data;
	}, function(error){
		console.log(error);
	});
	
	$http({
        method : "GET",
        url : "getContent?viewName=achievements&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	$scope.achievementsRecieved=true;
    	$scope.checkDataRecieved();
    	//console.log(response.data);
        //$scope.myWelcome = response.data;
    	$scope.achievements=response.data;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	
	$http({
        method : "GET",
        url : "getContent?viewName=projects&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	$scope.projectsRecieved=true;
    	$scope.checkDataRecieved();
    	console.log(response.data);
        //$scope.myWelcome = response.data;
    	$scope.projects=response.data;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	
	$http({
	    method : "GET",
	    url : "getContent?viewName=structure&language="+$scope.rootlang
	}).then(function mySucces(response) {
		$scope.structuresRecieved=true;
		$scope.checkDataRecieved();
		//console.log(response.data);
	    //$scope.myWelcome = response.data;
		$scope.icpsStructures=response.data;
	}, function myError(response) {
	    $scope.myWelcome = response.statusText;
	});
	
	$http({
        method : "GET",
        url : "getContent?viewName=notice&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	//console.log(response.data[0].title);
    	$scope.noticesRecieved=true;
    	$scope.checkDataRecieved();
        //$scope.myWelcome = response.data;
    	$scope.noticeData=response.data;
    	$scope.title=response.data[0].title;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	
	$http({
        method : "GET",
        url : "getContent?viewName=mediaGallery&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	$scope.mediaRecieved=true;
    	$scope.checkDataRecieved();
    	//console.log(response.data[0].title);
        //$scope.myWelcome = response.data;
    	$scope.mediaGalleries=[];
    	angular.forEach(response.data, function(value, key){
    		if(value.contentId==5 || value.contentId==35)
    			$scope.mediaTitle=value.title;
    		else
    			$scope.mediaGalleries.push(value);
    	});
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	}
	$scope.getAllContent();
}]);
//objectives controller for home page
app.controller("objectiveCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	
	$http({
        method : "GET",
        url : "getContent?viewName=objectives&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	//console.log(response.data);
        //$scope.myWelcome = response.data;
    	$scope.contentData=response.data;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });

}]);
//projects controller for home page
app.controller("projectsCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	
	$http({
        method : "GET",
        url : "getContent?viewName=projects&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	console.log(response.data);
        //$scope.myWelcome = response.data;
    	$scope.contentData=response.data;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });

}]);

app.controller("quickStatsController",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	$scope.lang='en';
	$rootScope.$on("lang", function(event, data){
		$scope.lang=data;
		$scope.getQuickStats();
	});
	$scope.getQuickStats=function(){
		
		$http({
	        method : "GET",
	        url : "getContent?viewName=quickStats&language="+$scope.rootlang
	    }).then(function mySucces(response) {
	    	$scope.quickStatsTitle=response.data[0].title;
	    }, function myError(response) {
	        $scope.myWelcome = response.statusText;
	    });
		
		$http({
	        method : "GET",
	        url : "getOverAllScoreOfIndicator"
	    }).then(function mySucces(response) {
	    	console.log(response.data);
	        //$scope.myWelcome = response.data;
	    	$scope.overAllScore=response.data;
	    	
	    	$scope.overAllScore[0].imgSrc = "resources/img/cpis_home_quickStats_CNCP_registed.svg";
	    	$scope.overAllScore[1].imgSrc = "resources/img/cpis_home_quickStats_CNCP_disposed.svg";
	    	$scope.overAllScore[2].imgSrc = "resources/img/cpis_home_quickStats_CICL_registed.svg";
	    	$scope.overAllScore[3].imgSrc = "resources/img/cpis_home_quickStats_CICL_disposed.svg";
	    				
	    }, function myError(response) {
	        $scope.myWelcome = response.statusText;
	    });
	}
	$scope.getQuickStats();

}]);

//Achievements controller for home page
app.controller("achievementsCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	
	$http({
        method : "GET",
        url : "getContent?viewName=achievements&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	//console.log(response.data);
        //$scope.myWelcome = response.data;
    	$scope.contentData=response.data;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });

}]);
//icps Structure controller fro home page
app.controller("structureCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	$rootScope.$on("lang", function(event, data){
		if(data=='hi'){
			$scope.rootlang=2;
		}
		else{
			$scope.rootlang=1;
		}
		
		$scope.getStructure();
	});
	$scope.getStructure = function(){
	$http({
	    method : "GET",
	    url : "getContent?viewName=structure&language="+$scope.rootlang
	}).then(function mySucces(response) {
		//console.log(response.data);
	    //$scope.myWelcome = response.data;
		$scope.contentData=response.data;
	}, function myError(response) {
	    $scope.myWelcome = response.statusText;
	});
	}
	$scope.getStructure();
}]);
//Notice board controller
app.controller("noticeCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){

	$http({
        method : "GET",
        url : "getContent?viewName=notice&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	//console.log(response.data[0].title);
        //$scope.myWelcome = response.data;
    	$scope.noticeData=response.data;
    	$scope.title=response.data[0].title;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });

}]);

app.controller("programmeCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	$rootScope.$on("lang", function(event, data){
		if(data=='hi'){
			$scope.rootlang=2;
		}
		else{
			$scope.rootlang=1;
		}
		
		$scope.getProgrammes();
	});
	
	$scope.getProgrammes = function(){
	$http({
        method : "GET",
        url : "getContent?viewName=programmes&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	//console.log(response.data[0].title);
        //$scope.myWelcome = response.data;
    	$scope.programmes=response.data[0].content;
    	$scope.title=response.data[0].title;
    	$("#programmes").html($scope.programmes);
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	}
	$scope.getProgrammes();
}]);

app.controller("aboutUsCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	$rootScope.$on("lang", function(event, data){
		if(data=='hi'){
			$scope.rootlang=2;
		}
		else{
			$scope.rootlang=1;
		}
		
		$scope.getAboutContents();
	});
	
	$scope.getAboutContents = function(){
	$http({
        method : "GET",
        url : "getContent?viewName=about&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	//console.log(response.data[0].title);
        //$scope.myWelcome = response.data;
    	$scope.aboutContent=response.data[0].content;
    	$scope.title=response.data[0].title;
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	}
	$scope.getAboutContents();
}]);



app.controller("rtiCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	$rootScope.$on("lang", function(event, data){
		if(data=='hi'){
			$scope.rootlang=2;
		}
		else{
			$scope.rootlang=1;
		}
		
		$scope.getRtiContents();
	});
	
	$scope.getRtiContents = function(){
		$scope.rtiContent=[];
	$http({
        method : "GET",
        url : "getContent?viewName=rti&language="+$scope.rootlang
    }).then(function mySucces(response) {
    	$scope.rtiTitle=response.data[0].title;
    	for(var i=1;i<response.data.length;i++){
    		$scope.rtiContent.push(response.data[i]);
    	}
    }, function myError(response) {
        $scope.myWelcome = response.statusText;
    });
	}
	$scope.getRtiContents();
	
}]);

app.controller("galleryCtrl",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	$rootScope.$on("lang", function(event, data){
		if(data=='hi'){
			$scope.rootlang=2;
		}
		else{
			$scope.rootlang=1;
		}
		
		$scope.getGalleryContents();
	});
	
	$scope.getGalleryContents = function(){
		$scope.rtiContent=[];
		$http({
	        method : "GET",
	        url : "getContent?viewName=mediaGallery&language="+$scope.rootlang
	    }).then(function mySucces(response) {
//	    	$scope.mediaRecieved=true;
//	    	$scope.checkDataRecieved();
//	    	$scope.mediaGalleries=[];
	    	angular.forEach(response.data, function(value, key){
	    		if(value.contentId==36 || value.contentId==6)
	    			$scope.audioTitle=value.content;
	    		else if(value.contentId==37 || value.contentId==7)
	    			$scope.videoTitle=value.content;
	    		else if(value.contentId==38 || value.contentId==8)
	    			$scope.imageTitle=value.content;
	    	});
	    }, function myError(response) {
	        $scope.myWelcome = response.statusText;
	    });
	}
	$scope.getGalleryContents();
	
}]);

app.controller("contactUsController",['$scope','$location','$rootScope','$http',function($scope,$location,$rootScope,$http){
	$scope.contact={};
	
	
	$scope.resetErrMsg = function(){
		if($scope.contact.fromUserName != undefined){
			document.getElementById('nameError').innerHTML = "";
			$('#successSend').html("");
		}
		if($scope.contact.fromMailId != undefined || $scope.contact.fromMailId != ""){
			document.getElementById('emailError').innerHTML = "";
			$('#successSend').html("");
		}
		if($scope.contact.subject != undefined){
			document.getElementById('subjectError').innerHTML = "";
			$('#successSend').html("");
		}
		if($scope.contact.message != undefined){
			document.getElementById('messageError').innerHTML = "";
			$('#successSend').html("");
		}
		
	}
	
	$scope.sendMail = function(){
		
		if($scope.contact.fromUserName == undefined || $scope.contact.fromUserName == ""){
			$('#nameError').html("Please enter your name");
			$('html,body').animate({
			        scrollTop: $('#nameError').offset().top - 250},'slow');
			 return false;
		}else if($scope.contact.fromMailId == undefined || $scope.contact.fromMailId == ""){
			$('#emailError').html("Please enter your valid email id");
			$('html,body').animate({
			        scrollTop: $('#emailError').offset().top - 250},'slow');
			 return false;
		}
		else if($scope.contact.subject == undefined || $scope.contact.subject == ""){
			$('#subjectError').html("Please enter the subject name");
			$('html,body').animate({
			        scrollTop: $('#subjectError').offset().top - 250},'slow');
			 return false;
		}
		else if($scope.contact.message == undefined || $scope.contact.message == ""){
			$('#messageError').html("Please give the feedback");
			$('html,body').animate({
			        scrollTop: $('#messageError').offset().top - 250},'slow');
			 return false;
		}
		else{
		$(".loader").css("display", "block");
		$http({
	        method : "POST",
	        url : "sendMail",
	        data: $scope.contact
	    }).then(function mySucces(response) {
//	    	console.log(response.data);
	    	if(response.status===200){
	    		$scope.contact={};
			    $(".loader").css("display", "none");
				$('#successSend').html("Feedback has been sent successfully.");
				$('html,body').animate({
				        scrollTop: $('#successSend').offset().top - 250},'slow');
				 return false;
				
			}
//	    	$('#printModal').modal('show');
	    	});
		}
	}
}]);



