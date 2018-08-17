<!DOCTYPE html>

<%@page import="org.sdrc.cpis.util.Constants"%>
<%@page import="org.sdrc.cpis.models.UserDetailModel"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="en">
	<head>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
		<title>Individual Care Plan</title>
		
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
		<link rel="stylesheet" href="resources/css/dataTables.bootstrap.min.css">
		<spring:url value="/resources/css/style.css" var="styleCss" />
		<link href="${styleCss}" rel="stylesheet" />
		<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
			var="jQueryUiCss" />
		<link href="${jQueryUiCss}" rel="stylesheet"></link>
		<link rel="stylesheet" href="resources/css/styles.css">
		
		<script type="text/javascript" src="resources/js/jquery-min.js"></script>
		<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
		<!-- <script type="text/javascript" src="resources/js/angular.min.js"></script> -->
		<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	</head>


	<body ng-app="individualCare" ng-controller="individualcarePlanController" id="icpbody"
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
	<div class="modal fade" id="personalDetailsCheck" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">
	          	<span translate>
	          		Please submit personal details of the child to proceed further.
	          	</span></h4>
      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true">
	          	<span translate>Close</span></button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="cciCheck" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">
	          	<span translate>
	          		Selected child has never been sent to any CCI.
	          	</span>
	          </h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="redirect()">
	          	<span translate>Close</span></button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="ciclChild" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign">
	<span translate>
		This form is not applicable for Child in conflict with law.
	</span></h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">
	          	<span translate>OK</span></button>  
	      </div>    
	    </div>
	  </div>
	</div>
	
		<div class="modal fade" id="icpNotFilled" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign">
	<span translate>ICP for this child is yet to be filled.</span></h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true" ng-click="redirect()">
	          	<span translate>OK</span></button>  
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="finalOrderModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign">
	<span translate>
		Final order for this child has been filled up.Could not make any changes.
	</span></h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">
	          	<span translate>OK</span></button>  
	      </div>    
	    </div>
	  </div>
	</div>
	
		<div class="container">
			<div class="row interim_div">
				<div class="col-md-3">
	
					<div class="box-border">
						<div ng-if="personalDetailsShow" class="panel-heading margnbtm" onclick="javascript:OpenIndividualplan();"><span translate>PERSONAL DETAILS</span></div>
						<div ng-if="progressReportShow" class="panel-heading cursrpointerRm"><span translate>PROGRESS REPORT OF THE CHILD</span></div>
							<div class="panel-group">
	
							<div class="panel-default">
								<div class="panel-body">
									<ul>
											<li ng-repeat="item in icpBlist"><span class="glyphicons glyphicons-chevron-right"></span><a
											href="#" ng-click="showForm(item)"> {{item.dateOfReport}}</a></li>
										
										<li ng-if="addNewProgressReport"><span class="glyphicon glyphicon-plus margnbtm"></span> <a
											href="#" onclick="javascript:OpenIndividualProgress();"><span translate>Add New</span></a></li>
									</ul>
	
								</div>
							</div>
						</div>
	
						<div ng-if="preReleaseShow"  class="panel-heading margnbtm" onclick="javascript:OpenIndividualPrerelease();"><span translate>PRE-RELEASE REPORT</span></div>
	
						<div ng-if="postReleaseShow"  class="panel-heading margnbtm" onclick="javascript:OpenIndividualPost();"><span translate>POST-RELEASE/RESTORATION REPORT OF THE CHILD</span></div>
	
					</div>
				</div>
	
				<div class="col-md-9">
					<div class="showDetails">
						<div id="one"><jsp:include page="icpPersonalDetails.jsp" /></div>
	
						<div id="two"><jsp:include page="icpprogressReport.jsp" /></div>
	
						<div id="three"><jsp:include page="icpprereleasereport.jsp" /></div>
	
						<div id="four"><jsp:include page="icppostRelease.jsp" /></div>
					</div>
					
					
					<input type="hidden" id="modelValue" value="${selectedChild }">
					<input type="hidden" id="modelValue" value="${selectedChild }">
					
				</div>
			</div>
		</div>
		<jsp:include page="./common/cctsFooter.jsp" />
	
		<script>
			var app = angular.module('individualCare',['gettext']);
			var myAppConstructor= angular.module('individualCare');
		</script>
		<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
		<script type="text/javascript" src="resources/js/AngularController/individualcarePlanController.js"></script>
<!-- 				<script type="text/javascript" -->
<!-- 		src="resources/js/AngularController/headerController.js"></script> -->
		<script	type="text/javascript" src="resources/js/moment-with-locales.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	
		<script type="text/javascript">
			
			
			function OpenIndividualplan(){
				$("#one").show();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				
				angular.element("#personalDetailsBody").scope().$apply(function() {
					if(angular.element("#personalDetailsBody").scope().prefetchData.finalOrderFilled==1 && angular.element("#icpbody").scope().personalDetailsData.id==null){
						$('#finalOrderModal').modal('show');
					}
				});
			}
			
			
			$("#one").hide();
			$("#two").hide();
			$("#three").hide();
			$("#four").hide();
			
			function OpenIndividualProgress(){
				angular.element("#personalDetailsBody").scope().$apply(function() {
					if(angular.element("#personalDetailsBody").scope().icpAdata.id==null){
						$('#personalDetailsCheck').modal('show');
					}
					else{
						$("#one").hide();
						$("#two").show();
						$("#three").hide();
						$("#four").hide();
					}
				});
				
				
				
				angular.element("#progressReportBody").scope().$apply(function() {
					angular.element("#progressReportBody").scope().progressReport={};
					angular.element("#progressReportBody").scope().progressReportViewPage = false;
				});
			}
			function OpenIndividualPrerelease(){
				angular.element("#personalDetailsBody").scope().$apply(function() {
					if(angular.element("#personalDetailsBody").scope().icpAdata.id==null){
						$('#personalDetailsCheck').modal('show');
					}
					else if(angular.element("#personalDetailsBody").scope().prefetchData.finalOrderFilled==1 && angular.element("#preReleaseBody").scope().formInfo.id==null){
						$('#finalOrderModal').modal('show');
					}
					else{
				$("#one").hide();
				$("#two").hide();
				$("#three").show();
				$("#four").hide();
				
				}
				});
// 				angular.element("#preReleaseBody").scope().$apply(function() {
// 					angular.element("#preReleaseBody").scope().formInfo={};
// 					angular.element("#preReleaseBody").scope().preReleaseView = false;
// 				});
			}
			
			function OpenIndividualPost(){
				angular.element("#personalDetailsBody").scope().$apply(function() {
					if(angular.element("#personalDetailsBody").scope().icpAdata.id==null){
						$('#personalDetailsCheck').modal('show');
					}
					else if(angular.element("#personalDetailsBody").scope().prefetchData.finalOrderFilled==1 && angular.element("#postReleaseDiv").scope().formInfo.id==null){
						$('#finalOrderModal').modal('show');
					}
					else{
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").show();
			}
				});
// 				angular.element("#postReleaseDiv").scope().$apply(function() {
// 					angular.element("#postReleaseDiv").scope().formInfo={};
// 					angular.element("#postReleaseDiv").scope().viewPostReleasePage = false;
// 				});
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