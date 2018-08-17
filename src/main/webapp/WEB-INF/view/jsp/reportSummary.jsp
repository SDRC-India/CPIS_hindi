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
		<title>Report</title>
		<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
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


	<body ng-app="reportApp" ng-controller="reportSummeryController" ng-cloak>
		<jsp:include page="./common/cctsHeader.jsp" />
		
		<%
				UserDetailModel user = null;
				
				user = (UserDetailModel)request.getSession().getAttribute(Constants.USER_PRINCIPAL);
				Integer designationId=user.getDesignationId();
				Integer cciId=user.getCciId();
				%>
	<input type="hidden" id="designationId" value = "<%=designationId%>" >
	<input type="hidden" id="cciId" value = "<%=cciId%>" >	
	<div class="modal fade" id="noChildSelected" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign">Please select a child to proceed further</h4>
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>
		<div class="container">
			<div class="row interim_div">
				<div class="col-md-3">
	
					<div class="box-border">
						<div ng-if="showHR" class="panel-heading" onclick="javascript:OpenHumanresource();" >
						<span translate>human resource details</span></div>
						<div class="panel-group"></div>
						<div ng-if="showInfra" class="panel-heading" onclick="javascript:OpenInfrasture();">
						<span translate>infrastructure of institution</span></div>
						<div class="panel-group"></div>
						<div ng-if="showFinance" class="panel-heading" onclick="javascript:OpenFinancial();">
						<span translate>financial report</span></div>
						<div class="panel-group"></div>
						<div ng-if="showInspection" class="panel-heading" onclick="javascript:OpenInspection();">
						<span translate>CCI Inspection Report(Form 46)</span></div>
						
					</div>
				</div>
	
				<div class="col-md-9">
					<div>
					 	
					 	<div id="one"><jsp:include page="human_resource.jsp" /></div>
					 	
						<div id="two"><jsp:include page="infrastructure.jsp" /></div>
	
						<div id="three"><jsp:include page="financialreport.jsp" /></div>
	
						<div id="four"><jsp:include page="inspectionreport.jsp" /></div>
	
					</div>
	
					<input type="hidden" id="modelValue" value="${selectedChild }">
				</div>
			</div>
			
			
			
			
		</div>
		<jsp:include page="./common/cctsFooter.jsp" />
		
		<script type="text/javascript" src="resources/js/jquery-min.js"></script>
		<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
		<!-- <script type="text/javascript" src="resources/js/angular.min.js"></script> -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
		<script>
			var app = angular.module('reportApp',['gettext']);
			var myAppConstructor= angular.module('reportApp');
		</script>
		
		<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
		<script type="text/javascript" src="resources/js/AngularController/reportSummeryController.js"></script>
		<!-- <script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
		<script	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
		
	
<!-- 		<script	type="text/javascript" src="resources/js/moment-with-locales.js"></script> -->
<!-- 		<script src="resources/js/bootstrap-datetimepicker.min.js"></script> -->
<!-- 		<script type="text/javascript" src="resources/js/jquery-ui.js"></script> -->
<!-- 		<script type="text/javascript"	src="resources/js/AngularService/commonService.js"></script> -->
<!-- 		<script type="text/javascript" src="resources/js/AngularController/reportSummeryController.js"></script> -->
		
		<script type="text/javascript">
			function OpenModal(){
					$('#thankyouModal').modal('show');
			}
			function OpenHumanresource(){
				$("#one").show();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
			}
			function OpenInfrasture(){
				$("#one").hide();
				$("#two").show();
				$("#three").hide();
				$("#four").hide();
			}
			
			function OpenFinancial(){
				$("#one").hide();
				$("#two").hide();
				$("#three").show();
				$("#four").hide();
			}
			
			
			
			function OpenInspection(){
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").show();
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