
<!DOCTYPE html><%@page import="org.sdrc.cpis.util.Constants"%>
<%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><html
	lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico"
	type="image/x-icon">
<title>Interim Order</title>
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
<body ng-app="interimDecision" ng-controller="InterimDecisionController"
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
	<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content modalHeight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Please select an appropriate form to continue.</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="interim_modaltext text-center">
						<a href="#" id="hfthree" data-dismiss="modal" aria-hidden="true"><span
							translate>Order of Placement of a Child in an Institution (Children's Home/Fit Facility/SAA) (FORM-18)</span></a> <a href="#"
							id="hfone" data-dismiss="modal" aria-hidden="true"><span
							translate>Order for Placement of Child Under The Care of a Parent, Guardian or Fit Person Pending Inquiry</span><br><span translate>(FORM-19)</span></a>
						<a href="#" id="hftwo" data-dismiss="modal" aria-hidden="true"><span
							translate>Order of Foster Care Placement with a Family or Group Foster Care(FORM-32)</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row interim_div">
			<div class="col-md-3">
				<div class="box-border">
					<div class="panel-heading panelPoinetr">
						<span translate>Interim Decisions</span>
					</div>
					<div class="panel-group">
						<div class=" panel-default">
							<div class="panel-body">
								<ul>
									<li ng-repeat="item in interimOrders" ng-click="eraseData()"><span
										class="glyphicons glyphicons-chevron-right"></span><a href="#"
										ng-click="showForm(item)"> {{item.dateOfFormFilled}}</a></li>
									<li ng-if="finalOrderFilled && forCWC"><span
										class="glyphicon glyphicon-plus"></span> <a href="#"
										onclick="javascript:OpenModal();"><span translate>Add New</span></a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel-heading panelPoinetr">
						<span translate>Case Monitoring Forms</span>
					</div>
					<div class="panel-group">
						<div class=" panel-default">
							<div class="panel-body">
								<ul>
									<li ng-repeat="item in caseMonitering"><span
										class="glyphicons glyphicons-chevron-right"></span><a href="#"
										ng-click="showForm(item)"> {{item.date}}</a></li>
									<li ng-if="finalOrderFilled && forCWC"><span
										class="glyphicon glyphicon-plus"></span> <a href="#"
										onclick="javascript:OpenCaseMonitoring();"><span translate>Add New</span></a></li>
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
	<jsp:include page="./common/cctsFooter.jsp" />
	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<!-- <script type="text/javascript" src="resources/js/angular.min.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
	<script>
		var app = angular.module('interimDecision', [ 'gettext' ]);
		var myAppConstructor = angular.module('interimDecision');
	</script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularController/interim_decision.js"></script>
	<!-- <script type="text/javascript"		src="resources/js/AngularController/headerController.js"></script> -->
	<!-- <script src="resources/js/AngularController/child_basic.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
	<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		function OpenModal() {
			$('#thankyouModal').modal('show');
		}
		function OpenCaseMonitoring() {
			$("#one").hide();
			$("#two").hide();
			$("#three").hide();
			$("#four").show();
			angular
					.element("#caseMonitoring")
					.scope()
					.$apply(
							function() {
								angular.element("#caseMonitoring").scope().formInfo = {};
								angular.element("#caseMonitoring").scope().caseMonitoringBtn = true;
								angular.element("#caseMonitoring").scope().caseMonitoringDisable = false;
								angular.element("#caseMonitoring").scope().hr = null;
								angular.element("#caseMonitoring").scope().minute = null;
								angular.element("#caseMonitoring").scope().ap = null;
								angular.element("#caseMonitoring").scope().determineChildAge = "";
								angular.element("#caseMonitoring").scope().medicalReportsRelied = "";
							});
		}
		$("#one").show();
		$("#two").hide();
		$("#three").hide();
		$("#four").hide();
		$("#hfone")
				.click(
						function() {
							$("#one").show();
							$("#two").hide();
							$("#three").hide();
							$("#four").hide();
							angular
									.element("#fitPersonBody")
									.scope()
									.$apply(
											function() {
												angular.element(
														"#fitPersonBody")
														.scope().fitPersonFormData = {};
												angular.element(
														"#fitPersonBody")
														.scope().fitPersonBtn = true;
												angular.element(
														"#fitPersonBody")
														.scope().fitPersonDisable = false;
											});
						});
		$("#hftwo")
				.click(
						function() {
							$("#two").show();
							$("#one").hide();
							$("#three").hide();
							$("#four").hide();
							angular
									.element("#fostercareBody")
									.scope()
									.$apply(
											function() {
												angular.element(
														"#fostercareBody")
														.scope().formInfo = {};
												angular.element(
														"#fostercareBody")
														.scope().firstRadioDisable = false;
												angular.element(
														"#fostercareBody")
														.scope().secondRadioDisable = false;
												angular.element(
														"#fostercareBody")
														.scope().fosterGroupBtn = true;
												angular.element(
														"#fostercareBody")
														.scope().fosterParentBtn = true;
												angular.element(
														"#fostercareBody")
														.scope().fosterDisable = false;
											});
						});
		$("#hfthree")
				.click(
						function() {
							$("#three").show();
							$("#one").hide();
							$("#two").hide();
							$("#four").hide();
							angular
									.element("#fitInstitutionBody")
									.scope()
									.$apply(
											function() {
												angular.element(
														"#fitInstitutionBody")
														.scope().formInfo = {};
												angular.element(
														"#fitInstitutionBody")
														.scope().showFitInstitutionBtn = true;
												angular.element(
														"#fitInstitutionBody")
														.scope().fitInstituteDisable = false;
											});
						});
		function toggleIcon(e) {
			$(e.target).prev('.panel-heading').find(".more-less").toggleClass(
					'glyphicon-plus glyphicon-minus');
		}
		$('.panel-group').on('hidden.bs.collapse', toggleIcon);
		$('.panel-group').on('shown.bs.collapse', toggleIcon);
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.interim_div').css('min-height', $(window).innerHeight() - 110);
		});
	</script>
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>