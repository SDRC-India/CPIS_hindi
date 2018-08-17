<!DOCTYPE html><%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@page import="org.sdrc.cpis.util.Constants"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
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
<body ng-app="ciclInterimDecision"
	ng-controller="ciclInterimDecisionController" ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
	<%
				UserDetailModel user = null;
				
				user = (UserDetailModel)request.getSession().getAttribute(Constants.USER_PRINCIPAL);
				Integer designationId=user.getDesignationId();
				Integer cciId=user.getCciId();
				%>
				<input type="hidden" id="designationId" value = "<%=designationId%>" >
	<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content modalHeight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Choose Order</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="interim_modaltext text-center">
						<a href="#" id="hfone" data-dismiss="modal"
							ng-click="openInterimForm(3)" aria-hidden="true"> <span
							translate>Supervision Order.</span> <br>(<span translate>Form</span>-3)
						</a> <a href="#" id="hftwo" data-dismiss="modal"
							ng-click="openInterimForm(4)" aria-hidden="true"> 
							<span translate>Order of placing a child in child care institution pending inquiry</span><br>(<span translate>Form</span>-4)
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="errorModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<p style="text-align: center">
						<span translate>Uploaded file is not in correct format.</span> <br>
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span translate>Ok</span>
						</button>
					</p>
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
									<li
										ng-repeat="item in interimOrdersList | orderBy:'dateOfOrder'"
										ng-click="showForm(item)"><span
										class="glyphicons glyphicons-chevron-right"></span> <a
										href="#"> {{item.dateOfOrder}}</a></li>
									<li ng-if="childMstData.finalOrderFilled == 0 && forJJB"><span
										class="glyphicon glyphicon-plus"></span> <a href="#"
										onclick="javascript:OpenModal();"> <span translate>Add
												New</span>
									</a></li>
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
									<li
										ng-repeat="item in caseMonitoringList | orderBy:'createdDate'"
										ng-click="showCMForm(item)"><span
										class="glyphicons glyphicons-chevron-right"></span> <a
										href="#"> {{item.createdDate}}</a></li>
									<li ng-if="childMstData.finalOrderFilled == 0 && forJJB"><span
										class="glyphicon glyphicon-plus"></span> <a href="#"
										ng-click="openCaseMonitoring()"> <span translate>Add
												New</span>
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div>
					<div id="one">
						<jsp:include page="supervisionOrder.jsp" />
					</div>
					<div id="two">
						<jsp:include page="childCareInstitution.jsp" />
					</div>
					<div id="four">
						<jsp:include page="ciclCaseMonitoring.jsp" />
					</div>
				</div>
				<input type="hidden" id="childId" value="${selectedChild}">
			</div>
		</div>
	</div>
	<jsp:include page="./common/cctsFooter.jsp" />
	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<!-- <script type="text/javascript" src="resources/js/angular.min.js"></script> -->
	<script>		var app = angular.module('ciclInterimDecision', ['gettext']);		var myAppConstructor = angular.module('ciclInterimDecision');	</script>
	<script src="resources/js/AngularService/commonService.js"></script>
	<!-- <script src="resources/js/angular-gettext.min.js"></script><script type="text/javascript" src="resources/js/translations.js"></script> -->
	<script src="resources/js/AngularController/ciclInterimOrder.js"></script>
	<!-- 	<script type="text/javascript" src="resources/js/AngularController/headerController.js"></script> -->
	<script src="resources/js/moment-with-locales.js"></script>
	<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
	<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript">		function OpenModal() {			$('#thankyouModal').modal('show');		}		$("#two").hide();		$("#four").hide();		function toggleIcon(e) {			$(e.target).prev('.panel-heading').find(".more-less").toggleClass(					'glyphicon-plus glyphicon-minus');		}		$('.panel-group').on('hidden.bs.collapse', toggleIcon);		$('.panel-group').on('shown.bs.collapse', toggleIcon);	</script>
	<script type="text/javascript">		$(document).ready(function() {			$('.interim_div').css('min-height', $(window).innerHeight() - 110);		});	</script>
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>