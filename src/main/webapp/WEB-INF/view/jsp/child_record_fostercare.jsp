<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
	<head>
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
	  <title>RECORD OF A CHILD IN FOSTER CARE</title>
	  <!-- Bootstrap css -->
	  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
	  <link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">

	 
	  <!-- Font awesome css -->
<!-- 	  <link rel="stylesheet" href="resources/fonts/font-awesome/font-awesome.min.css"> -->
		<spring:url value="/resources/css/style.css" var="styleCss" />
		<link href="${styleCss}" rel="stylesheet" />
		<link rel="stylesheet" href="resources/css/styles.css">
		<!-- jquery-ui.css file is not that big so we can afford to load it -->
		<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
			var="jQueryUiCss" />
		<link href="${jQueryUiCss}" rel="stylesheet"></link>

			
	</head>
	

	<body ng-app="childBasic" ng-controller="BasicForm17" ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
	<div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to register the child.</span></h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button3id" name="button3id" class="btn btn-info" type="submit" ng-click="saveData()" class="close" data-dismiss="modal" aria-hidden="true">Submit</button>
	          <button id="button4id" name="button4id" class="btn btn-info" type="submit" class="close" data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="childIdModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <p style="text-align:center">
	          	<span translate>The form has been submitted successfully.</span>
	          <br>
	          <button id="button5id" name="button5id" class="btn btn-info" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">Ok</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				
				<div class="box-border box-border-padding">
				<hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr>	
					<div class="childlist-heading1 borderPersonal"><span translate>RECORD OF A CHILD IN FOSTER CARE<br>FORM 34<br>[Rule 23(17)]</span></div>	
					
<!-- 				<div class="col-md-12"> -->
<!-- 					<div class="col-md-6" -->
<!-- 						style="margin-left: 0px !important; padding-left: 0px !important;"> -->
<!-- 						<div class="social_headng"> -->
<!-- 							<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" /> <span>Child -->
<%-- 								ID:&nbsp;&nbsp;${selectedChild}</span> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-6" -->
<!-- 						style="margin-right: 0px !important; padding-right: 0px !important;"> -->
<!-- 						<div class="social_headng1"> -->
<!-- 							<span>Name of Child:&nbsp;&nbsp;</span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
					<form class="form-horizontal basicchildform" name="basicdetail" id="basicdetail">
						<fieldset>
			
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>1. Case No</span></label>  
						  <div class="col-md-7">
						  <input id="caseno" name="caseno"  placeholder="{{'Enter case no'| translate}}" class="form-control input-md" type="text" ng-model="formInfo.caseNo" readonly>
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>2. Name of the Child</span></label>  
						  <div class="col-md-7">
						  <input id="childName" name="childName"  placeholder="{{'Enter name of the Child'| translate}}" class="form-control input-md" type="text"  readonly>
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>3. Age</span></label>  
						  <div class="col-md-7">
						  <input id="age" name="age"  placeholder="{{'Enter age'| translate}}" class="form-control input-md" type="text"  readonly>
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>4. Sex</span></label>  
						  <div class="col-md-7">
						  <input id="sex" name="sex"  placeholder="{{'Enter sex'| translate}}" class="form-control input-md" type="text"  readonly>
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>5. Name of the Child Care Institution, if any from where the child has been given for foster care</span></label>  
						  <div class="col-md-7">
						  <input id="cciName" name="cciName"  placeholder="{{'Enter name of the Child Care Institution'| translate}}" class="form-control input-md" type="text" >
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>5.(i) Address</span></label>  
						  <div class="col-md-7">
						  <input id="address" name="address"  placeholder="{{'Enter address'| translate}}" class="form-control input-md" type="text" >
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>6. Individual Care Plan</span></label>  
						  <div class="col-md-2">
						  <label class="radio-inline" for="radios-1"><input name="radiosOne" id="radios-1" value="Yes"  type="radio">Yes</label> 
    					<label class="radio-inline" for="radios-2"><input name="radiosOne" id="radios-2" value="No" checked="checked" type="radio">No</label>
					  	</div>
					  	<div class="col-md-5">
						  <button id="buttonView" name="buttonView" class="btn btn-info" type="reset" ><span translate>View ICP</span></button>
						  <button id="buttonPrint" name="buttonPrint" class="btn btn-info" type="reset" ><span translate>Print ICP</span></button>
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>7. Any other source of referral</span></label>  
						  <div class="col-md-7">
						  <input id="otherReferral" name="otherReferral"  placeholder="{{'Enter any other source of referral'| translate}}" class="form-control input-md" type="text" >
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>8. Details of the child placed in foster care</span></label>  
						  <div class="col-md-7">
						  <input id="childDetails" name="childDetails"  placeholder="{{'Enter details of the child placed in foster care'| translate}}" class="form-control input-md" type="text" >
					  	</div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>8. (i) Photograph of the child</span></label>  
						    	<div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload photo</span></button>
							  </div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>8. (ii) Photograph of foster care giver/parent</span></label>  
						    	<div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload photo</span></button>
							  </div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>8.(iii) Photograph of biological parents</span></label>  
						    	<div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload photo</span></button>
							  </div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>9. Details of the placement</span></label>  
						  <div class="col-md-7">
						  <select id="placementDetails" name="placementDetails" class="form-control">
							      <option value="" disabled selected><span translate>Select</span></option>
							    <option value="release"><span translate>Individual</span></option>
							    <option value="transfer"><span translate>Group</span></option>
							    </select>
					  		</div>
						</div>
						
						<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>9.(i) Date</span></label>  
							  <div class="col-md-2">
								<input type="text" id="datepicker" readonly class="form-control">
							  </div>
							</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>9.(ii) Details of the child placed in foster care</span></label>  
						  <div class="col-md-7">
						  <input id="childDetails" name="childDetails"  placeholder="{{'Enter details of the child placed in foster care'| translate}}" class="form-control input-md" type="text" >
					  	</div>
						</div>	
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>10. Home Study Report of the biological family, where applicable with photograph</span></label>  
						    	<div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload file</span></button>
							  </div>
							  <div class="col-md-3">
							  	<a href="#" ><span translate>Download Child Study Report - Form 30 [Rule 23(9)]</span></a>
							  </div>
						</div>	
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>11. Home Study report of the foster family- individual or group care, with photograph</span></label>  
						    	<div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload file</span></button>
							  </div>
						</div>	
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>12.Child Study Report</span></label>  
						    	<div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload file</span></button>
							  </div>
							  <div class="col-md-3">
							  	<a href="#" ><span translate>Download Child Study Report - Form 31 [Rule 23(4)]</span></a>
							  </div>
						</div>	
	
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>13. Address of the Child Welfare Committee</span></label>  
						  <div class="col-md-7">
						  <input id="cwcAddress" name="cwcAddress"  placeholder="{{'Enter address of the Child Welfare Committee'| translate}}" class="form-control input-md" type="text" >
					  	</div>
					  	</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>14. Record (number and significant details) of each visit with the child, foster family, Biological family, if available and child's school</span></label>  
						  <div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload file</span></button>
							  </div>
						</div>
						
						<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>15. Record of all reviews of the placement including observations, extent and quality of compliance with Care Plan, child's developmental milestones, child's academic progress, and any changes in family environment</span></label>  
						  <div class="col-md-2">
							  	<input type="file" name="files[]" id="js-upload-files" multiple>
							  	</div>
							  	<div class="col-md-2">
							  	<button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit"><span translate>Upload file</span></button>
							  </div>
						</div>
						
						<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>16. In the case of extension or termination of the placement, record of date</span></label>  
							  <div class="col-md-2">
								<input type="text" id="datepicker2" readonly class="form-control">
							  </div>
							</div>
							
					    <div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>16.(i) Reason for extension or termination</span></label>  
						  <div class="col-md-7">
						  <input id="terminationReason" name="terminationReason"  placeholder="{{'Enter reason for extension or termination'| translate}}" class="form-control input-md" type="text" >
					  	</div>
					  	</div>
					  	
					  	<div class="form-group box-border-padding">
							  <label class="col-md-4 control-label" for="textinput"><span translate>17. Date of the child being handed over to the foster family</span></label>  
							  <div class="col-md-2">
								<input type="text" id="datepicker3" readonly class="form-control">
							  </div>
							</div>
					  	
					  	<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>18. Financial assistance provided, if any</span></label>  
						  <div class="col-md-7">
						  <input id="financialAssistance" name="financialAssistance"  placeholder="{{'Enter financial assistance provided'| translate}}" class="form-control input-md" type="text" >
					  	</div>
					  	</div>
					  	
					  	<div class="form-group box-border-padding">
						  <label class="col-md-4 control-label" for="textinput"><span translate>19.Name of the Case Worker appointed</span></label>  
						  <div class="col-md-7">
						  <input id="caseWorker" name="caseWorker"  placeholder="{{'Enter name of the Case Worker appointed'| translate}}" class="form-control input-md" type="text" >
					  	</div>
					  	</div>
					  	
							
							
							<div style="text-align:center">
								<button id="button1id" name="button1id" ng-click = "basicdetail.$invalid ? '' : validateForm()"
								class="btn btn-info" type="submit"><span translate>Submit</span></button>
								<button id="button2id" name="button2id" class="btn btn-info" type="reset" ><span translate>Reset</span></button>
							</div>
	
							</fieldset>
					</form>
	        	</div>
			</div>
		</div>
	</div>
	<jsp:include page="./common/cctsFooter.jsp" />
	
	<script type="text/javascript" src="resources/js/jquery-min.js"></script>                      
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/angular.min.js"></script>
	
	<script>
	var app = angular.module('childBasic', ['gettext']);
	var myAppConstructor= angular.module('childBasic');
	</script>
	<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
	  
		  <!-- <script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script> -->
	
	<script src="resources/js/AngularController/child_basic.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="resources/js/bootstrap-datetimepicker.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   	<script type="text/javascript">
		$(document).ready(function() {
// 			$("#basicdetail").on('submit', function(){
// 	   			$('#thankyouModal').modal('show');
// 			});
	
			//$("#button3id").on('submit', function(){
				//   $('#childIdModal').modal('show');
			//});
			$( "#datepicker" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});
// 			$( "#datepicker" ).datepicker('setDate', 'today');
			$( "#datepicker2" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});
// 			$( "#datepicker2" ).datepicker('setDate', 'today');
		});
	</script>
		
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>
