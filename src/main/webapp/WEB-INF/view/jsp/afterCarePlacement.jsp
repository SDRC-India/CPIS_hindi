<div ng-controller="AfterCareController" ng-cloak>
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="afterCareModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Are you sure you want to submit the form ?</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="submitData" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="saveAfterCare()" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span translate>Submit</span>
						</button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true">
							<span translate>Back</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="alertModal" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been saved successfully.</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button6id"
							class="btn btn-info bigbutton" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"
							ng-click="printAfterCareData()"><span translate>Print</span></button>
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
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
			<span translate>ORDER OF AFTER CARE PLACEMENT</span> <br> <span
				translate>Form 37</span> <br> <span translate>[Rule
				25(2)]</span>
		</div>
		<form class="form-horizontal basicchildform" id="afterCare"
			name="afterCareplacement">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>Order of After Care Placement</span>
				</div>
				<jsp:include page="./common/childNameStrip.jsp" />
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>1. Child Name</span>
					</label>
					<div class="col-md-7">
						<input id="aftercareChildName" name="aftercareChildName"
							placeholder="{{'Enter name of the child'| translate}}"
							class="form-control input-md" type="text"
							ng-model="selectedChild.childName" readonly
							oninvalid="this.setCustomValidity('Please enter case no')"
							oninput="setCustomValidity('')" ng-disabled="afterCareDisable">
						<div id="aftercareChildNameerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Father Name</span>
					</label>
					<div class="col-md-7">
						<input id="fatherName" readonly class="form-control input-md"
							type="text" ng-model="afterCarePrefetchObj.fatherName">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3. Mother Name</span>
					</label>
					<div class="col-md-7">
						<input id="motherName" readonly class="form-control input-md"
							type="text" ng-model="afterCarePrefetchObj.motherName">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>4. Completing 18 years on</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="completing18datepicker" readonly
							class="form-control" ng-model="afterCareObj.completing18On"
							ng-disabled="afterCareDisable">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Purpose of Rehabilitation</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="sponsorperiod" required
							placeholder="{{'Enter purpose of rehabilitation'| translate}}"
							maxlength="129"
							oninvalid="this.setCustomValidity('Please enter purpose of rehabilitation')"
							oninput="setCustomValidity('')" class="form-control input-md"
							ng-disabled="afterCareDisable" type="text"
							ng-model="afterCareObj.purposeOfRehabilitation">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Organisation Name</span> <br> <span translate>(Where child will be placed)</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input required id="otherfactorsofimportance" maxlength="30"
							placeholder="{{'Enter organisation name'| translate}}"
							oninvalid="this.setCustomValidity('Please enter name of organisation where child will be placed')"
							oninput="setCustomValidity('')" class="form-control input-md"
							ng-disabled="afterCareDisable" type="text"
							ng-model="afterCareObj.organizationName">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7. Amount released per month</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="amountReleased" required
							placeholder="{{'Enter amount released'| translate}}"
							oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
							class="form-control input-md" ng-disabled="afterCareDisable"
							type="number" maxlength="9"
							ng-model="afterCareObj.amountReleased">
					</div>
				</div>
				<!-- 				<div class="form-group box-border-padding" ng-show="sponsorshipPlacement.sponsorshipPlacementOrder"> -->
				<!-- 					<label class="col-md-4 control-label" for="textinput"></label> -->
				<!-- 					<div class="col-md-4"> -->
				<!-- 						<input id="selectDatafromsponsor" -->
				<!-- 							placeholder="{{'Please specify the amount'| translate}}" -->
				<!-- 							oninvalid="this.setCustomValidity('Please enter other factors of importance')" -->
				<!-- 							oninput="setCustomValidity('')" class="form-control input-md" -->
				<!-- 							type="text" ng-model="sponsorshipPlacement.selectDatafromsponsor"> -->
				<!-- 					</div> -->
				<!-- 					<div class="col-md-4"> -->
				<!-- 						<select  ng-model="ap2" class="form-control" style="width:32%; display:inline;"> -->
				<!-- 					  		<option value="" >Select</option> -->
				<!-- 					  		<option value="" >Per Month</option> -->
				<!-- 					  </select> -->
				<!-- 					</div> -->
				<!-- 				  </div> -->
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Period (in months)</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="periodOf" placeholder="{{'Enter period'| translate}}"
							required
							oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
							class="form-control input-md" ng-disabled="afterCareDisable"
							type="number" maxlength="3" ng-model="afterCareObj.period">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9. Bank account holder name</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="attitudeofclassmates" name="attitudeofclassmates"
							ng-disabled="afterCareDisable"
							placeholder="{{'Enter the name of bank account holder'| translate}}"
							maxlength="30" class="form-control input-md" type="text"
							ng-model="afterCareObj.nameOfAccountHolder" required>
						<div id="attitudeofclassmatesError" class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<button id="afterCareid" name="button1id" class="btn btn-info"
						ng-if="!afterCareDisable" type="submit"
						ng-click="afterCareplacement.$invalid ? '' : validateAcForm()">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-if="afterCareDisable" type="submit"
						ng-click="printAfterCareData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">	$(document).ready(function() {		/* $("#datepicker").datepicker({			dateFormat : "yy-mm-dd",			maxDate : '+0d'		}); */		$('input').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});		$('textarea').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});	});</script>