var sdrc_export = new function() {
	"use strict";
	this.root = "http://localhost/";
	this.init = function(rootUri) {
		this.root = rootUri;
		console.log("in init");
	};
	// Please give container Id and export pdf btn ids
	this.export_pdf = function(containerId, exportPdfbtn) {
		var serverURL;
		$("#" + exportPdfbtn).on("click",function(event) {
			var $scope = angular.element("body").scope();
			event.preventDefault();
			d3.selectAll("svg").attr("version", 1.1).attr("xmlns",
					"http://www.w3.org/2000/svg");
			
			
			
			d3.selectAll("svg").attr("version", 1.1).attr("xmlns", "http://www.w3.org/2000/svg");		
			d3.select("#pageMap").selectAll("path").attr("style",function(d) {
				return  "fill:"+ $(this).css('fill')+";stroke:"+$(this).css('stroke');});
			d3.select("#trendsvg").selectAll("path").attr("style",function(d) {
				return  "fill:"+ $(this).css('fill')+";stroke:"+$(this).css('stroke');
			});
			var mapImageSvg = $("#pageMap").html();
			var lineChartImageSvg = $("#pageLineChart").html();
			d3.select("#pageMap").selectAll("path").attr('style', null);
//			var legendImage = "<svg>" + $(".legends-section").html().replace("nbsp;", "");
			var chartSvgs = [];
			chartSvgs.push(mapImageSvg); chartSvgs.push(lineChartImageSvg);
			$(".loader").show();
			
			var serverURL = "api/exportToPdf?iusnId=" + $scope.selectedIndicator.indicatorUnitSubgroupId + 
			"&timeperiodId=" + $scope.selectedTimeperiod.timePeriodId +
			"&areaId=" + $scope.selectedDistrictId;
			
	
			$.ajax({
				url : serverURL, 
				method : 'POST',
				data : JSON.stringify(chartSvgs),
				contentType : 'application/json',
				success : function(data) {
					var fileName = {
							"fileName" : data
						};
					if(typeof data == 'string' && data.indexOf("You have no access to this page.") != -1){
						$(".loader").css("display", "none");
						$("body").append('<div id="sessionOutMessage" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body text-center"><h3>Session is expired</h3><a href="ccts_login" class="btn btn-default errorOk" type="submit">OK</a></div></div></div></div>');
						$("#sessionOutMessage").modal("show");
					}
					else
						$.download("downloadFile/", fileName, 'POST');
				}
			});
		});
	};
	/*this.export_excel = function(containerId, exportExcelbtn) {
		var serverURL;
		$("#" + exportExcelbtn).on("click",function(event) {
			var $scope = angular.element("body").scope();
			event.preventDefault();
			d3.selectAll("svg").attr("version", 1.1).attr("xmlns",
					"http://www.w3.org/2000/svg");
			var spiderSvg = $("sdrc-spider").html();
			var columnSvg = $("sdrc-bar-chart").html();
			var chartSvgs = [];
			chartSvgs.push(spiderSvg); chartSvgs.push(columnSvg);
			$(".loader").show();
			
			var serverURL = "api/exportToExcel?formId=" + $scope.selectedParentSector.formId + 
			"&lastVisitDataId=" + $scope.lastVisiDataId +
			"&areaId=" + $scope.selectedDistrict.areaId + 
			"&noOfFacilities=" + $scope.noOfFacilities;
			
	
			$.ajax({
				url : serverURL, 
				method : 'POST',
				data : JSON.stringify(chartSvgs),
				contentType : 'application/json',
				success : function(data) {
					var fileName = {
							"fileName" : data
						};
						$.download("downloadFile/", fileName, 'POST');
				}
			});
		});
	};*/
	// download a file
	$.download = function(url, data, method) {
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
			$(".loader").css("display", "none");
		}
		;
	this.export_excel = function() {
		alert("excel exported");
	};
	};
};

