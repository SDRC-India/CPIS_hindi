/**
 * 
 */
myAppConstructor
    .service('commonService', commonService);
 
  function commonService($http, $q, $window) {
	
//	$scope.selectedChild=null;
	this.selectedChildObject = {};
	this.formObject1 = {};
	this.formType = "";
	this.masterFormObject = {};
	
	this.setTypeOfForm = function(typeOfForm, formObject){
		this.formObject1 = formObject;
		this.formType = typeOfForm;
	};
	
	this.getTypeOfForm = function(){
		this.masterFormObject = {
				formType : this.formType,
				formObject : this.formObject1
		};
		return this.masterFormObject;
	};
	
	
	this.getGridMenuItems = function(){
		var deferred = $q.defer();
        // get posts from database
		$http.get("getGridMenuItems")
          .then(function(result) {
        	  jsonData = result.data;
            deferred.resolve(jsonData);
          }, function(error) {
        	  jsonData = error;
            deferred.reject(error);
          });
        jsonData = deferred.promise;
        return $q.when(jsonData);
	};
	
	this.changeImage = function(item){
		
	};
	
	this.redirectForm = function(url, selectedChildId){
		var deferred = $q.defer();
		if(url=="child_registration" || url=="ciclSocialBackgroundReport" || url=="reportSummary" || url=="constitutionofSociety")
			$window.location.href = url;
		else
			$window.location.href = url+'?selectedChildId='+selectedChildId;
	};
	
	this.setSelectedChildObject = function(selectedChildObject){
		this.selectedChildObject = selectedChildObject;
	};
	
	this.getSelectedChildObject = function(){
		return this.selectedChildObject;
	};
	
	
	this.timeConverter = function(fetchedTime){
		var timeString = "";
		var hr = "";
		var ap = "";
		var minute = "";
		if(fetchedTime != undefined){
			timeString = fetchedTime;
			var timeArr = timeString.split(":");
			minute = timeArr[1];
			if(timeArr[0] > 12){
				if(timeArr[0]>9)
					hr = (timeArr[0]-12).toString();
				else
					hr = "0"+ (timeArr[0]-12).toString();
				ap = "PM";
			}else{
				hr = timeArr[0];
				ap = "AM";
			}
		}
		return hr+":"+minute+" "+ap;
	};
	
//	this.logout=function(){
//		$http.get("logout")
//        .then(function(result) {
//        	console.log(result);
//        });
//	}
	
	
		// download a file
		 var download = function(url, data, method) {
			// url and data options required
			if (url && data) {
				// data can be string of parameters or array/object
				data = typeof data == 'string' ? data : jQuery.param(data);
				// split params into form inputs
				var inputs = '';
				jQuery.each(data.split('&'), function() {
					var pair = this.split('=');
					inputs += '<input type="hidden" name="' + pair[0] + '" value="'	+ pair[1] + '" />';
				});
				// send request
				jQuery(
						'<form action="' + url + '" method="' + (method || 'post')
								+ '">' + inputs + '</form>').appendTo('body')
						.submit().remove();
				setTimeout(function(){
					window.location.href = "";
				}, 1000);	
				$(".loader").css("display", "none");
			}
			;
		this.export_excel = function() {
			alert("excel exported");
		};
		};
		
		var fileNameToDownload = function(response){
			var fileName = {
					"fileName" : response.data
				};
		  download("downloadFile/", fileName, 'POST');
		  $(".loader").css("display", "none");
		};
		
		this.downloadFile = function(serverURL, obj){
			
			if(obj==undefined){
				$http.post(serverURL).
				  then(function(response){
					  fileNameToDownload(response);
				  });
			} else{
				$http.post(serverURL, obj).
				  then(function(response){
					  fileNameToDownload(response);
				  });
			}
		};
		this.clearUploadFile = function(){
			angular.forEach(
    		    	angular.element("input[type='file']"),
    		    	function(inputElem) {
    		    	angular.element(inputElem).val(null);
    		});
		};
		//@author: Pratyush - the following function will return the name of selected drop-down items by selected language.
		this.getNameBySelectedLanguageType = function(comparableName, comparatorObj, languageType){
			var name = "";
			angular.forEach(comparatorObj, function(value, key){
				if(value.name == comparableName){
					name =  languageType == 'en' ? value.name : value.typeNameHindi;
				}
			});
			return name;
		};
	};




$(document).ready(function(){
	$(".openNavMenu").click(function(e){
		e.stopPropagation();
		$("#nav-list-menu").fadeToggle();
		$(".triangle").fadeToggle();
	});
	$("body").click(function(){
		$("#nav-list-menu").fadeOut();
		$(".triangle").fadeOut();
	});
});
/*
 * @author Laxman Paikaray(laxman@sdrc.co.in)
 * 
 * */
function checkSessionOut(data){
	if(typeof data == 'string' && data.indexOf("You have no access to this page") != -1){
		$("body").append('<div id="sessionOutMessage" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body text-center"><h3>Session is expired</h3><a href="ccts_login" class="btn btn-default errorOk" type="submit">OK</a></div></div></div></div>');
		$("#sessionOutMessage").modal("show");
	}
}
