<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>


<html lang="en">
	<head>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
		<title>Constitution of Society</title>
		
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


	<body ng-app="Constitution" ng-controller="ConstitutionController"
		ng-cloak>
		
		<jsp:include page="./common/cctsHeader.jsp" />
		
<%-- 		<input type="text" id="modelValue" value="${selectedChild }"> --%>
	<div class="modal fade" id="noChildSelected" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign"><span translate>Please select a child to proceed further.</span></h4>
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>
		<div class="container">
			<div class="row interim_div">
				<div class="col-md-3" style="margin-top:45px;">
	
					<div class="box-border">
					<div class="panel-heading" onclick="javascript:OpenHRDCPU();" ><span translate>HR details of DCPU</span></div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenCWC();"><span translate>Constitution of CWC</span> </div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenJJB();"><span translate>Constitution of JJB</span> </div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenSJPU();"><span translate>Constitution of SJPU </span></div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenDCPC();"><span translate>Constitution of DCPC </span></div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenBCPC();"><span translate>Constitution of BCPC </span></div>
						<div class="panel-group"></div>
						<div class="panel-heading" onclick="javascript:OpenView();"><span translate>View Constitution List</span></div>
					</div>
				</div>
	
				<div class="col-md-9">
					<div>
					 	<div id="zero"><jsp:include page="hr_dcpu.jsp" /></div>
					 	
					 	<div id="one"><jsp:include page="constitutionofCWC.jsp" /></div>
					 	
						<div id="two"><jsp:include page="constitutionofJJB.jsp" /></div>
	
						<div id="three"><jsp:include page="constitutionofSJPU.jsp" /></div>
	
						<div id="four"><jsp:include page="constitutionofDCPC.jsp" /></div>
						
						<div id="five"><jsp:include page="constitutionofBCPC.jsp" /></div>
						
<%-- 						<div id="six"><jsp:include page="constitutionVCPC.jsp" /></div> --%>
						
						<div id="seven"><jsp:include page="constitutionofSocietyView.jsp" /></div>
	
					</div>
	
					<input type="hidden" id="modelValue" value="${selectedChild }">
				</div>
			</div>
			<div class="modal fade" id="uploadIdTrue" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<p style="text-align: center"><span translate>Uploaded file Successfully.</span><br>
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true" ng-click="reDirect()">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="uploadIdFalse" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<p style="text-align: center"><span translate>Upload failed.</span><br>
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="uploadFileIdFalse" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<p style="text-align: center"><span translate>Choose file to upload.</span><br>
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true">Ok</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="errorModal" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<p style="text-align: center"><span translate>Uploaded file is not in correct format.</span><br>
								<button id="button5id" name="button5id" class="btn btn-info"
									type="submit" class="close" data-dismiss="modal"
									aria-hidden="true">Ok</button>
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
	    <script type="text/javascript" src="resources/js/pagination.js"></script>
		<script>
			var app = angular.module('Constitution',['angularUtils.directives.dirPagination','gettext']);
			var myAppConstructor= angular.module('Constitution');
		</script>
		
		<script type="text/javascript" src="resources/js/AngularController/ConstitutionController.js"></script>
<!-- 					<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->
		<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
		
		<script	type="text/javascript" src="resources/js/moment-with-locales.js"></script>
		<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
        <script type="text/javascript" src="resources/js/AngularController/paginationController.js"></script>
		<script type="text/javascript">
			
			function OpenModal(){
					$('#thankyouModal').modal('show');
			}
			
			$("#zero").show();
			$("#one").hide();
			$("#two").hide();
			$("#three").hide();
			$("#four").hide();
			$("#five").hide();
			$("#six").hide();
			$("#seven").hide();
			function OpenHRDCPU(){
				$("#zero").show();
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
			}
			function OpenSJPU(){
				$("#zero").hide();
				$("#one").hide();
				$("#two").hide();
				$("#three").show();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
// 				angular.element("#caseSummaryBody").scope().caseSummaryDisable=false;
			}

			function OpenVCPC(){
				$("#zero").hide();
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").show();
				$("#seven").hide();
			}
			
			
			function OpenDCPC(){
				$("#zero").hide();
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").show();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
			}
			
			
			function OpenCWC(){
				$("#zero").hide();
				$("#one").show();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
				
// 				angular.element("#sponsorshipBody").scope().sponsorshipDisable=false;
			}
			
			function OpenJJB(){
				$("#zero").hide();
				$("#one").hide();
				$("#two").show();
				$("#three").hide();
				$("#four").hide();
				$("#five").hide();
				$("#six").hide();
				$("#seven").hide();
			}
			
			function OpenBCPC(){
				$("#zero").hide();
				$("#one").hide();
				$("#two").hide();
				$("#three").hide();
				$("#four").hide();
				$("#five").show();
				$("#six").hide();
				$("#seven").hide();
				
			}
			function OpenView(){
				$("#zero").hide();
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