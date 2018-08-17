<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><div ng-app="interimDecision" id="fitPersonBody"
	ng-controller="FitPersonController" ng-cloak>
	<div class="modal fade" id="fitPersonModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Please click on the submit button to save the details.</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="saveFitPerson()" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span translate>Submit</span>
						</button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal">
							<span translate>Back</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="childFitPersonIdModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been saved successfully.</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button6id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="printFitPersonSubmitData()">
							<span translate>Print</span>
						</button>
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="reDirect()">
							<span translate>Ok</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="box-border box-border-padding">
		<!-- <hr>
		<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a>
		| <a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
		<hr> -->
		<div class="childlist-heading1 borderPersonal">
			<span translate>ORDER FOR PLACEMENT OF CHILD UNDER THE CARE OF A PARENT, GUARDIAN OR FIT PERSON PENDING INQUIRY</span><br>
			<span translate>FORM-19</span><br>
			<span translate>[Rule 18(8)]</span>
		</div>
		<form class="form-horizontal basicchildform" name="fitPerson"
			id="fitPerson">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>ORDER FOR PLACEMENT OF CHILD UNDER THE CARE OF A PARENT, GUARDIAN OR FIT PERSON PENDING INQUIRY</span>
				</div>
				<div class="col-md-12 summaryspace">
					<div class="col-md-6 childidheader"
						style="margin-left: 0px !important; padding-left: 0px !important;">
						<div class="social_headng">
							<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" /> <span><span
								translate>Child ID:</span>&nbsp;&nbsp;${selectedChild}</span>
						</div>
					</div>
					<div class="col-md-6 childnameheader"
						style="margin-right: 0px !important; padding-right: 0px !important;">
						<div class="social_headng1">
							<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{masterData.childName}}</span>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>1.Case No </span></label>
					<div class="col-md-7">
						<input id="caseno" name="caseno" required ng-model="caseNum"
							placeholder="{{'Enter case No.'| translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>2. Date of case registered</span> </label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfReg"
							ng-model="masterData.dateOfCaseRegistered" readonly
							class="form-control" ng-disabled="fitPersonDisable">
						<div id="caseRegdDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>3.Reference</span><span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<input required id="re" maxlength="30" name="re"
							placeholder="{{'Enter reference'| translate}}"
							ng-model="fitPersonFormData.re" class="form-control input-md"
							type="text" ng-disabled="fitPersonDisable">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>4.Child Name </span></label>
					<div class="col-md-7">
						<input id="childName" name="childName"
							ng-model="masterData.childName"
							placeholder="{{'Enter Name of the child'| translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>5. Date of child found to be in need of care and protection</span></label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateWhenCNCP"
							ng-model="masterData.dateOfCaseRegistered" readonly
							class="form-control" ng-disabled="fitPersonDisable">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>6.Name of the Parent/Guardian/Fit Person</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="FitPersonName" name="FitPersonName" maxlength="30"
							ng-model="fitPersonFormData.fitPersonName"
							placeholder="{{'Enter name of the parent/guardian/fit person'| translate}}"
							class="form-control input-md" type="text" required
							ng-disabled="fitPersonDisable">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>7.Address of Parent/Guardian/Fit Person</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
						<textarea id="addressFitPerson" required name="addressFitPerson"
							maxlength="100" ng-model="fitPersonFormData.fitPersonAddress"
							placeholder="{{'Enter address of parent/guardian/fit person'| translate}}"
							class="form-control input-md" type="text" required
							ng-disabled="fitPersonDisable"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>8.District</span> </label>
					<div class="col-md-7">
						<label type="text" id="fitPersonDist" class="form-control"
							required value="masterData.district" readonly> <!-- <!-- 							<option value="" disabled selected>Select District</option> -->
							<!-- 							<option value="masterData.district" disabled selected>{{masterData.district.name}}</option> -->
							{{masterData.district.name}}
						</label>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>9.Reason for the child being produced before the CWC </span><span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
<!-- 					twohundred-characters-validation -->
						<input id="reasonForProduced" name="reasonForProduced"
							ng-model="fitPersonFormData.reasonForProduced" maxlength="200"
							placeholder="{{'Enter reason for the child being produced before the CWC'| translate}}"
							class="form-control input-md" type="text" required
							ng-disabled="fitPersonDisable">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>10.Child sent to fit person for a period of (in months)</span> <span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<input only-three-digits id="timePeriod" name="timePeriod"
							ng-model="fitPersonFormData.timePeriod" required
							placeholder="{{'Enter the period (in months) for which the child is sent to fit person'| translate}}"
							type="number" class="form-control input-md"
							oninvalid="this.setCustomValidity('Please enter time period in months')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateFields(fitPersonFormData.timePeriod,'noofMonthError')"
							ng-blur="resetInput(fitPersonFormData,'timePeriod','noofMonthError')"
							ng-disabled="fitPersonDisable">
						<div id="noofMonthError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>11.Name of school/ vocational training center</span><span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input required id="schoolName" name="schoolName" maxlength="200"
							ng-model="fitPersonFormData.schoolName"
							placeholder="{{'Enter name of school/ vocational training center'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fitPersonDisable">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>12.Address of school/ vocational training center</span><span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<textarea required id="schoolAddress" name="schoolAddress"
							ng-model="fitPersonFormData.schoolAddress" maxlength="100"
							placeholder="{{'Enter address of school/ vocational training center'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fitPersonDisable"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>13. Date of the form filled</span><span class="mandatory_star">&#42;</span></label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfFormFilled"
							ng-model="fitPersonFormData.dateOfFormFilled" readonly
							class="form-control" ng-disabled="fitPersonDisable">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div style="text-align: center">
					<button ng-if="!fitPersonDisable" id="button1id" name="button1id"
						class="btn btn-info"
						ng-click="fitPerson.$invalid ? '' : validateFitPerson()"
						type="submit">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-if="fitPersonDisable" ng-click="printOldFitPersonSubmitData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript" src="resources/js/jquery-min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/angular.min.js"></script>
<script>
	var app = angular.module('interimDecision', [ 'gettext' ]);
	var myAppConstructor = angular.module('interimDecision');
</script>
<script type="text/javascript"
	src="resources/js/AngularController/interim_decision.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('input').blur(function() {
			var value = $.trim($(this).val());
			$(this).val(value);
		});
		$('textarea').blur(function() {
			var value = $.trim($(this).val());
			$(this).val(value);
		});
	});
</script>
</body>
</html>