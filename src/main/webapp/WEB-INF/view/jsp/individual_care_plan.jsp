<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">
	<head>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
		<title>Interim Order</title>
		<link rel="stylesheet" href="resources/css/style.css">
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
		<link rel="stylesheet" href="resources/css/dataTables.bootstrap.min.css">
		<spring:url value="/resources/css/style.css" var="styleCss" />
		<link href="${styleCss}" rel="stylesheet" />
		<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
			var="jQueryUiCss" />
		<link href="${jQueryUiCss}" rel="stylesheet"></link>
	</head>


	<body ng-app="interimDecision" ng-controller="InterimDecisionController"
		ng-cloak>
		<jsp:include page="./common/cctsHeader.jsp" />
		
<%-- 		<input type="text" id="modelValue" value="${selectedChild }"> --%>
	
		<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Choose Order</h4>
					</div>
					<div class="modal-body">
						<p class="text-center">
							<button type="button" href="#" id="hfone" data-dismiss="modal"
								aria-hidden="true">Order for Placement of Child Under
								The Care of a Parent, Guardian or Fit Person Pending Inquiry</button>
							<br> <br>
							<button type="button" href="#" id="hftwo" data-dismiss="modal"
								aria-hidden="true">Order of Foster Care Placement with a
								Family or Group Foster Care</button>
							<br> <br>
							<button type="button" href="#" id="hfthree" data-dismiss="modal"
								aria-hidden="true">Order of Placement of a Child in an
								Institution (Children's Home/Fit Facility/SAA)</button>
						</p>
					</div>
				</div>
			</div>
		</div>
	
		<div class="container">
			<div class="row interim_div">
				<div class="col-md-3">
	
					<div class="box-border">
						<div class="panel-heading">Interim Decisions</div>
	
						<div class="panel-group">
	
							<div class="panel panel-default">
								<div class="panel-body">
									<ul>
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 05-08-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 21-09-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 09-10-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 15-11-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 02-12-2016</a></li> -->
										<li><span class="glyphicon glyphicon-plus"></span> <a
											href="#" onclick="javascript:OpenModal();">Add New</a></li>
									</ul>
	
								</div>
							</div>
						</div>
	
						<div class="panel-heading">Case Monitoring Forms</div>
	
						<div class="panel-group">
	
							<div class="panel panel-default">
								<div class="panel-body">
									<ul>
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 05-08-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 21-09-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 09-10-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 15-11-2016</a></li> -->
<!-- 										<li><span class="glyphicons glyphicons-chevron-right"></span><a -->
<!-- 											href="#"> 02-12-2016</a></li> -->
										<li><span class="glyphicon glyphicon-plus"></span> <a
											href="#" onclick="javascript:OpenCaseMonitoring();">Add
												New</a></li>
									</ul>
	
								</div>
							</div>
						</div>
	
					</div>
				</div>
	
				<div class="col-md-9">
	
	
	
					<div>
						<div id="one"><jsp:include page="fit_person.jsp" /></div>
	
						<div id="two"><jsp:include page="foster_care.jsp" /></div>
	
						<div id="three"><jsp:include page="fit_institution.jsp" /></div>
	
						<div id="four"><jsp:include page="case_monitoring.jsp" /></div>
					</div>
	
					<input type="hidden" id="modelValue" value="${selectedChild }">
	
				</div>
			</div>
		</div>
		<div class="container FCFooter">
			<div class="row  footer-bg">
				<div class="col-md-6" style="color: #eee; line-height: 10px;">
					Supported by<a href="#" style="color: #79c8ff;">&nbsp; <img
						src="resources/img/Unicef_logo.png" alt="UNICEF">
						</td></a>
				</div>
				<div class="col-md-6" style="color: #eee; text-align: right;">
					Powered by <a href="http://sdrc.co.in" target="_blank"
						style="color: #ffcc00;"> &nbsp;SDRC</a>
				</div>
			</div>
		</div>
	
		<script type="text/javascript" src="resources/js/jquery-min.js"></script>
		<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/js/angular.min.js"></script>
	
		<script>
			var app = angular.module('interimDecision',[]);
			var myAppConstructor= angular.module('interimDecision');
		</script>
		<script type="text/javascript"
			src="resources/js/AngularService/commonService.js"></script>
		<script type="text/javascript"
			src="resources/js/AngularController/interim_decision.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
		<script type="text/javascript">
			
			function OpenModal(){
					$('#thankyouModal').modal('show');
			}
			
			function OpenCaseMonitoring(){
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").show();
			}
			
			
			$("#one").show();
			$("#two").hide();
			$("#three").hide();
			$("#four").hide();
			
			$("#hfone").click(function(){
			    $("#one").show();
			    $("#two").hide();
			    $("#three").hide();
			    $("#four").hide();
			
			});
			
			$("#hftwo").click(function(){
			    $("#two").show();
			    $("#one").hide();
			    $("#three").hide();
			    $("#four").hide();
			
			});
			
			$("#hfthree").click(function(){
			    $("#three").show();
			    $("#one").hide();
			    $("#two").hide();
			    $("#four").hide();
			
			});
					
			function toggleIcon(e) {
			    $(e.target)
			        .prev('.panel-heading')
			        .find(".more-less")
			        .toggleClass('glyphicon-plus glyphicon-minus');
			}
			$('.panel-group').on('hidden.bs.collapse', toggleIcon);
			$('.panel-group').on('shown.bs.collapse', toggleIcon);
					
		</script>
		
		<script type="text/javascript">
			$(document).ready(function() {
			    $('.interim_div').css('min-height', $(window).innerHeight()-110);
			  }
			);
		</script>
		<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js" var="jQueryUiCore" />
		<script src="${jQueryUiCore}"></script>
	</body>
</html>