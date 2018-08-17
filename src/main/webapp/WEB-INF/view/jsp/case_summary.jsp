<div ng-controller="CaseSummaryController" ng-cloak id="caseSummaryBody">
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForCaseSummary" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel"><span translate>Please click on the submit button to save the details.</span><span translate></h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button3id" name="button3id" class="btn btn-info bigbutton" type="submit" ng-click="saveCSData()" class="close" data-dismiss="modal" aria-hidden="true"><span translate>Submit</span></button>
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="childCaseSummaryModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	 <h4 class="modal-title" id="myModalLabel"><span translate>The form has been saved successfully.</span></h4>
	          	<p style="text-align:center">
	          <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="printCaseSummarySubmitData()"><span translate>Print</span></button>
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
	        </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="noFileModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	 <h4 class="modal-title" id="myModalLabel"><span translate>Medical Records not uploaded.</span></h4>
	          	<p style="text-align:center">
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true"><span translate>Ok</span></button>
	        </p>                     
	      </div>    
	    </div>
	  </div>
	</div>

	<div class="box-border box-border-padding">
	<!-- <hr>
						<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 
						<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>  		
          <hr> -->				 
		<div class="childlist-heading1 borderPersonal"><span translate>CASE SUMMARY MAINTAINED BY THE CHILD WELFARE COMMITTEE</span><br><span translate>FORM 15</span><br><span translate>[Rule 17 (1)(i)]</span></div>

		<form class="form-horizontal basicchildform" name="caseSummary"
			id="caseSummary">
			<fieldset>

				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;"><span translate>CASE SUMMARY</span></div>

				<div class="col-md-12 summaryspace">
					<div class="col-md-6 childidheader"
						style="margin-left: 0px !important; padding-left: 0px !important;">
						<div class="social_headng">
							<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" /> <span><span translate>Child ID:</span>&nbsp;&nbsp;${selectedChild}</span>
						</div>
					</div>
					<div class="col-md-6 childnameheader"
						style="margin-right: 0px !important; padding-right: 0px !important;">
						<div class="social_headng1">
							<span><span translate>Name of Child:</span>{{selectedChild.childName}} </span>
						</div>
					</div>
				</div>

				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"><span translate>1. Case No</span></label>
					<div class="col-md-7">
						<input id="caseno" name="caseno" maxlength="30"
							class="form-control input-md" type="text"
							ng-model="selectedChild.caseNum" readonly>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>2. In Reference</span></label>
					<div class="col-md-7">
						<input id="retext" name="retext" placeholder="{{'Enter Reference'| translate}}" maxlength="50"
							class="form-control input-md" type="text" ng-model="formInfo.re" ng-disabled="caseSummaryDisable">
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>3. Case Record</span></label>
					<div class="col-md-7">
						<input id="caserecord" name="caserecord" maxlength="100"
							placeholder="{{'Enter Case Record'| translate}}" class="form-control input-md"
							type="text" ng-model="formInfo.caseRecord" ng-disabled="caseSummaryDisable">
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>4. Name of the Child</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="childname" name="childname" ng-model="selectedChild.childName"
							placeholder="{{'Enter Child Name'| translate}}" class="form-control input-md" 
							type="text" readonly>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>5. Father's/Mother's/Guardian's Name (if available)</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="guardianname" name="guardianname" maxlength="30"
							placeholder="{{'Enter Father\'s/Mother\'s/Guardian\'s Name'| translate}}"
							class="form-control input-md" type="text" ng-model="formInfo.parentOrGuardianName" ng-disabled="caseSummaryDisable">
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>6. Date of production</span></label>
					<div class="col-md-2 col-sm-6 col-xs-6 ">
						<input type="text" id="datepicker"
							ng-model="selectedChild.dateOfFirstProduction" readonly
							class="form-control" readonly>
						<div id="childProducedDateerror" class="error-style" ng-disabled="caseSummaryDisable"></div>
					</div>
					<i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>7. Name of Person Producing the Child</span></label>
					<div class="col-md-7" readonly>
<!-- 					thirty-characters-validation -->
						<input id="personproduce" class="form-control input-md" type="text" 
							ng-model="selectedChild.personWhoProduceChild" readonly>

					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>8. A List of All Follow up Dates (of the child, before the Committee)</span></label>
					<div class="col-md-7">
						 <textarea class="form-control input-md" id="followupdate" ng-model="date" readonly></textarea>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>9. Orders passed by the CWC</span><span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<label ng-repeat="order in ordersByCWC" class="col-md-10">
							<input type="checkbox" ng-model="order.checked" ng-disabled="caseSummaryDisable"
							ng-change="validateOrders(order)"> {{lang=='en'?order.name:order.typeNameHindi}}
						</label>

						<div class="col-md-11" id="otherfield"
							ng-if="otherFieldFlag == true">
							<input id="otherorder" placeholder="{{'Please specify'| translate}}"
								class="form-control input-md" type="text"
								ng-blur="blur(formInfo.ordersPassedByCwcOthers,'ordersPassedByCwcOthers')"
								ng-trim="false" required maxlength="250"
								ng-model="formInfo.ordersPassedByCwcOthers" ng-disabled="caseSummaryDisable">
						</div>
					</div>
				</div>
					<div id="ordersPassedeError" class="error-style" style="text-align:center;margin-top: -17px; margin-bottom: 29px; margin-left: -50px;"></div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>10. Medical Records including but not limited to age verification</span></label>
				
						<!-- <input id="filebutton" name="filebutton" class="input-file"
							type="file" ng-disabled="caseSummaryDisable"> -->
						<div class="col-md-3" ng-if="!caseSummaryDisable">
							<input type="file" name="childImage[]" id="js-upload-files"	ng-files="getReport($files,'medicalReports')" style="margin-top:15px;"  accept=".pdf">
						</div>
						<div class="col-md-1" >
<!-- 						<img " src={{medicalReports}} alt="No image" height="45" width="45" data-action="zoom" ng-if="!caseSummaryDisable"> -->
<!-- 						<img " src={{formInfo.medicalReports}} height="45" width="45"  ng-if="caseSummaryDisable" data-action="zoom"> -->
						</div>
						<div class="col-md-1">
						<button id="button2id" name="button2id" class="btn otherbut"
							ng-click="downloadPdf(formInfo.medicalReports,'Medical Record')"
							ng-if="caseSummaryDisable" class="close"><span translate>Download</span></span></button></div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>11. Social Investigation Report under Form 22</span></label>
					<div class="col-md-7" style="margin-top: 15px;">
						<label for="radio-10"><input name="radio1" id="radio-10" ng-disabled="true"
							ng-model="formInfo.socialInvestigationReport"
							ng-value="trueValue" type="radio"><span translate> Yes</span> </label>&nbsp;&nbsp; <label
							for="radio-11"><input name="radio1" id="radio-11" ng-disabled="true"
							ng-model="formInfo.socialInvestigationReport"
							ng-value="falseValue" type="radio"><span translate> No</span> </label>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>12. Individual Care Plan under Form 7</span></label>
					<div class="col-md-7" style="margin-top: 8px;">
						<label for="radio-12"><input name="radio2" id="radio-12" ng-disabled="true"
							ng-model="formInfo.individualCarePlan" ng-value="trueValue"
							type="radio"><span translate> Yes</span> </label>&nbsp;&nbsp; <label for="radio-13"><input
							name="radio2" id="radio-13" ng-disabled="true"
							ng-model="formInfo.individualCarePlan" ng-value="falseValue"
							type="radio"><span translate> No</span> </label>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>13. Rehabilitation Card in Form 14</span></label>
					<div class="col-md-7" style="margin-top: 7px;">
						<label for="radio-14"><input name="radio3" id="radio-14" ng-disabled="true"
							ng-model="formInfo.rehabitationCard" ng-value="trueValue"
							type="radio"><span translate> Yes</span> </label>&nbsp;&nbsp; <label for="radio-15"><input ng-disabled="true"
							name="radio3" id="radio-15" ng-model="formInfo.rehabitationCard"
							ng-value="falseValue" type="radio"><span translate> No</span> </label>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>14. Case History Form 43</span></label>
					<div class="col-md-7" style="margin-top: 8px;">
						<label for="radio-16"><input name="radio4" id="radio-16" ng-disabled="true"
							ng-model="formInfo.caseHistory" ng-value="trueValue"
							 type="radio"><span translate> Yes </span></label>&nbsp;&nbsp; <label for="radio-17"><input
							name="radio4" id="radio-17" ng-model="formInfo.caseHistory" ng-disabled="true"
							ng-value="falseValue" type="radio"><span translate> No</span> </label>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>15. All details, documents and records with regards to Sponsorship/Foster Care/Adoption services (if applicable).</span></label>
					<div class="col-md-7" style="margin-top: 25px;">
						<label for="radio-16"><input name="radio5" id="radio-18" ng-disabled="caseSummaryDisable"
							ng-model="formInfo.allDetails" ng-value="trueValue" type="radio"><span translate>Yes </span></label>&nbsp;&nbsp; <label for="radio-17"><input ng-disabled="caseSummaryDisable"
							name="radio5" id="radio-19" ng-model="formInfo.allDetails"
							ng-value="falseValue" type="radio"> <span translate>No </span></label>
					</div>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput"><span translate>16. Date</span><span class="mandatory_star">&#42;</span></label>
					<div class="col-md-2 col-sm-6 col-xs-6">
						<input type="text" ng-model="formInfo.dateOfFormFilled" id="formFilledDate" readonly
							class="form-control" ng-disabled="caseSummaryDisable">
					</div>
					<i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
				</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>17. Place</span></label>
					<div class="col-md-7">
						<input id="summaryPlace" placeholder="{{'Enter Place'| translate}}" thirty-characters-validation
							class="form-control input-md" type="text" ng-model="selectedChild.district.name" readonly>

					</div>
				</div>

				<div style="text-align: center" >
					<button ng-if="!caseSummaryDisable" id="buttonid" name="button1id" class="btn btn-info" ng-click = "caseSummary.$invalid ? '' : validateCsForm()"
						type="submit"><span translate>Submit</span></button>
						<button id="button2id" name="button2id" class="btn btn-info" ng-if="caseSummaryDisable"
						type="submit" ng-click="printCaseSummarySubmitData()"><span translate>Print</span></button>
<!-- 					<button id="button2id" name="button2id" class="btn btn-info" -->
<!-- 						type="reset">RESET</button> -->
				</div>
			</fieldset>
		</form>
		<div class="modal fade" id="errorImageModal" tabindex="-1"	role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p style="text-align: center"><span translate>Uploaded file is not in correct format.</span><br>
							<button id="button5id" name="button5id" class="btn btn-info"
								type="submit" class="close" data-dismiss="modal"
								aria-hidden="true"><span translate>Ok</span></button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		$(document).ready(function() {
// 			$("#datepicker").datepicker({
// 				dateFormat : "yy-mm-dd",
// 				maxDate : '+0d'
// 			});
// 			$("#datepicker2").datepicker({
// 				dateFormat : "yy-mm-dd",
// 				maxDate : '+0d'
// 			});
// 			$("#buttonid").click(function(){
// 				$('#confirmationModalForCaseSummary').modal('show');
// 			})
			
		});
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>	
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
	<script type="text/javascript" src="resources/js/download.js"></script>
