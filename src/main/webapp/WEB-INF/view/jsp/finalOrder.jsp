<!DOCTYPE html>

<%@page import="org.sdrc.cpis.util.Constants"%>
<%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<html lang="en">
	<head>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
		<title> Final Order</title>
		
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
		<link rel="stylesheet" href="resources/css/dataTables.bootstrap.min.css">
		<spring:url value="/resources/css/style.css" var="styleCss" />
		<link href="${styleCss}" rel="stylesheet" />
		<link rel="stylesheet" href="resources/css/styles.css">
		<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
			var="jQueryUiCss" />
		<link href="${jQueryUiCss}" rel="stylesheet"></link>
	</head>


	<body ng-app="finalOrder" ng-controller="FinalOrderController" id="finalOrderBody"
		ng-cloak>
		<jsp:include page="./common/cctsHeader.jsp" />
		<%
				UserDetailModel user = null;
				
				user = (UserDetailModel)request.getSession().getAttribute(Constants.USER_PRINCIPAL);
				Integer designationId=user.getDesignationId();
				Integer cciId=user.getCciId();
				%>
				<input type="hidden" id="designationId" value = "<%=designationId%>" >
		
<%-- 		<input type="text" id="modelValue" value="${selectedChild }"> --%>
	
		<div class="container">
			<div class="row interim_div">
				<div class="col-md-3">
	
					<div class="box-border">
						<div class="panel-heading" onclick="javascript:OpenSponsorship();"> <span translate>Order of Sponsorship Placement</span> </div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenAftercare();"> <span translate>Order of After Care Placement</span> </div>
						<div class="panel-group"></div>
						<div ng-if="isCNCPChild" class="panel-heading" onclick="javascript:OpenCaseSummary();"> <span translate>Case Summary</span> </div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenRestoration();"> <span translate>Restoration Order </span></div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenEscortOrder();"> <span translate>Escort Order</span> </div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenRehabilitation();"> <span translate>Rehabilitation Card</span></div>
						<div class="panel-group"></div>
						<div ng-if="isCNCPChild" class="panel-heading" onclick="javascript:OpenLegallyfree();"> <span translate>LEGALLY FREE FOR ADOPTION</span></div>
					</div>
				</div>
	
				<div class="col-md-9">
					<div>
					 	
					 	<div id="one"><jsp:include page="sponshorshipPlacement.jsp" /></div>
					 	
						<div id="two"><jsp:include page="afterCarePlacement.jsp" /></div>
	
						<div id="three"><jsp:include page="case_summary.jsp" /></div>
	
						<div id="four"><jsp:include page="restoration_order.jsp" /></div>
	
						<div id="five"><jsp:include page="escort_order.jsp" /></div>
						
						<div id="six"><jsp:include page="rehabilitation.jsp" /></div>
						
						<div id="seven"><jsp:include page="legallyFreeAdoption.jsp" /></div>
					</div>
	
					<input type="hidden" id="modelValue" value="${selectedChild }">
				</div>
			</div>
			<div class="modal fade" id="uploadIdTrue" tabindex="-1" role="dialog" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
								<h4 class="modal-title" id="myModalLabel"> <span translate> File uploaded successfully.</span></h4>
								<p style="text-align: center">
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true" ng-click="reDirect()">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="uploadIdFalse" tabindex="-1" role="dialog" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
								<h4 class="modal-title" id="myModalLabel"> <span translate>Upload failed.</span></h4>
								<p style="text-align: center">
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="uploadFileIdFalse" tabindex="-1" role="dialog" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
								<h4 class="modal-title" id="myModalLabel"> <span translate>Choose file to upload.</span></h4>
								<p style="text-align: center">
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
								<h4 class="modal-title" id="myModalLabel">Uploaded file is not in correct format.</h4>
								<p style="text-align: center">
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="noInterim" tabindex="-1" role="dialog" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
									  <h4 class="modal-title" id="myModalLabel"> <span translate>No Interim Orders Found.Please Fill up Interim Orders First.</span></h4>
								<p style="text-align: center">
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true" ng-click="reDirectToHome()">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./common/cctsFooter.jsp" />
	
		<script type="text/javascript" src="resources/js/jquery-min.js"></script>
		<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
		<!-- <script type="text/javascript" src="resources/js/angular.min.js"></script> -->
	
		<script>
			var app = angular.module('finalOrder',['gettext']);
			var myAppConstructor= angular.module('finalOrder');
		</script>
		<script type="text/javascript"
			src="resources/js/AngularService/commonService.js"></script>
		<script type="text/javascript" src="resources/js/AngularController/finalOrder.js"></script>
					<!-- <script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
		<script	type="text/javascript" src="resources/js/moment-with-locales.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	
		<script type="text/javascript">
			
			function OpenModal(){
					$('#thankyouModal').modal('show');
			}
			function OpenRehabilitation(){
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").show();
				$("#seven").hide();
			}

			$("#one").show();
			$("#two").hide();
			$("#three").hide();
			$("#four").hide();
			$("#five").hide();
			$("#six").hide();
			$("#seven").hide();
			
			function OpenCaseSummary(){
				$("#one").hide();
				$("#two").hide();
				$("#three").show();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
// 				angular.element("#caseSummaryBody").scope().caseSummaryDisable=false;
			}
			
			function OpenRestoration(){
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").show();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
			}
			
			function OpenEscortOrder(){
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").show();
				$("#six").hide();
				$("#seven").hide();
			}
			
			function OpenSponsorship(){
				$("#one").show();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
// 				angular.element("#sponsorshipBody").scope().sponsorshipDisable=false;
			}
			
			function OpenAftercare(){
				$("#one").hide();
				$("#two").show();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
			}
			function OpenLegallyfree(){
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").show();
			}
					
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