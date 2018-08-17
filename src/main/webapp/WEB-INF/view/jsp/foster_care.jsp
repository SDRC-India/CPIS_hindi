<div ng-app="interimDecision" id="fostercareBody"
	ng-controller="FosterCareController">
	<div class="modal fade" id="thankyouModal1" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate> Please click on the submit button to save the details.</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="saveData('FG')" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Submit</span></button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="childFosterCareIdModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body printModal">
					<h4 class="modal-title" id="myModalLabel">
						<span translate> The form has been saved successfully.</span>
					</h4>
					<p class="recordsaveHeading" style="text-align: center">
						<button id="button5id" name="button6id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="printFosterCareSubmitData()"><span translate>Print</span></button>
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="thankyouModal2" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate> Are you sure to you want to save this form
							?</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="saveData('FP')" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Submit</span></button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
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
			<span translate> ORDER OF FOSTER CARE PLACEMENT WITH A FAMILY OR GROUP FOSTER CARE</span><br><span translate>FORM32</span><br><span translate>[Rule 23(15)]</span>
		</div>
		<div class="grey-header marginTop"
			style="border-top: none; margin-top: 4px;">
			<span translate> ORDER OF FOSTER CARE PLACEMENT WITH A FAMILY OR GROUP FOSTER CARE</span>
		</div>
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
					<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{selectedChildInfo.childName}}</span>
				</div>
			</div>
		</div>
		<div class="fosterSelection interimPlanmargintop">
			<input type="radio" name="type" class="fosterRadioBtn"
				ng-click="eraseData('FP')" id="firstRadio" ng-model="careForFamily"
				ng-checked="firstradioChecked" ng-disabled="firstRadioDisable">
			<label style="margin-right: 70px; margin-left: 2px;"><span
				translate>Foster Care For Family </span></label> <input type="radio"
				name="type" class="fosterRadioBtn" ng-click="eraseData('FG')"
				id="secondRadio" ng-model="careForFamily"
				ng-checked="secondradioChecked" ng-disabled="secondRadioDisable">
			<label style="margin-left: 2px;"><span translate>
					Group Foster Care</label>
		</div>
		<form ng-show="!careForFamily" class="form-horizontal basicchildform"
			name="basicdetail" id="basicdetail">
			<fieldset>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 1.Child Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="childName" name="childName" readonly
							ng-model="selectedChildInfo.childName"
							placeholder="{{'Enter Name of the child'| translate}}"
							class="form-control input-md"
							type="text" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 2.Age </span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="childAge" name="childAge" readonly
							ng-model="selectedChildInfo.age"
							placeholder="{{'Enter Age of the child'| translate}}"
							class="form-control input-md" type="text" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="textarea"><span
						translate> 3.Address </span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea class="form-control" id="childAddress" maxlength="100"
							ng-model="formInfo.childAddress"
							placeholder="{{'Enter Address'| translate}}" required
							ng-disabled="fosterDisable"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 4.Child's Father Name</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="childFatherName" name="childFatherName"
							ng-model="formInfo.parentName1" maxlength="30"
							placeholder="{{'Enter Father Name of the child'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fosterDisable" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 5.Child's Mother Name</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="childMotherName" name="childMotherName"
							ng-model="formInfo.parentName2" maxlength="30"
							placeholder="{{'Enter Mother Name of the child'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fosterDisable" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 6.Foster Parent Name 1</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input required id="fosterParent1" name="fosterParent1"
							ng-model="formInfo.fosterParentName1" maxlength="30"
							placeholder="{{'Enter Foster Parent Name 1'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fosterDisable">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 7.Foster Parent Name 2 </span><span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input required id="fosterParent2" name="fosterParent2"
							ng-model="formInfo.fosterParentName2" maxlength="30"
							placeholder="{{'Enter Foster Parent Name 2'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fosterDisable">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="textarea"><span
						translate> 8.Foster Parent Address</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
						<textarea class="form-control" id="fosterAddress" maxlength="100"
							ng-model="formInfo.fosterParentAddress"
							placeholder="{{'Enter Foster Parent Address'| translate}}"
							ng-disabled="fosterDisable" required></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 9.Foster Parent Contact No.</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
						<input only-ten-digits id="fosterContactNo" name="fosterContactNo"
							placeholder="{{'Enter Foster Parent Contact No.'| translate}}"
							class="form-control input-md" required
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-model="formInfo.fosterParentContact"
							ng-blur="resetphnNoBlank(formInfo.fosterParentContact,'fosterContactNo')"
							ng-keyUp="validateName(formInfo.fosterParentContact,'phoneNoError')"
							maxlength="10" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-disabled="fosterDisable">
						<div id="phoneNoError" class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<!-- 					<button id="button3id1" name="button3id" class="btn btn-info" -->
					<!-- 						type="submit" ng-click="openUpdateModalForFP()">Update</button> -->
					<button ng-if="!fosterDisable" id="button1id" name="button1id"
						class="btn btn-info" type="submit" ng-click="openModalForFP()"><span translate>Submit</span></button>
					<button ng-if="fosterDisable" id="button2id" name="button2id"
						class="btn btn-info" ng-click="printOldFosterCareSubmitData()"><span translate>Print</span></button>
				</div>
			</fieldset>
		</form>
		<!-- group foster care start -->
		<form ng-show="careForFamily"
			class="form-horizontal basicchildform groupFoster ng-pristine ng-valid"
			name="groupDetail" id="groupDetail">
			<fieldset>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate> 1.Child Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="groupChildName" name="childName" readonly
							ng-model="selectedChildInfo.childName"
							placeholder="{{'Enter Name of the child'| translate}}"
							class="form-control input-md"
							type="text" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 2. Age</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="groupChildAge" name="childAge" readonly
							ng-model="selectedChildInfo.age"
							placeholder="{{'Enter Age of the child'| translate}}"
							class="form-control input-md" type="text" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 3.CCI Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="groupCCIName" name="groupCCIName"
							ng-model="formInfo.cciName" maxlength="30"
							placeholder="{{'Enter CCI Name'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fosterDisable" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="textarea"><span
						translate> 4. CCI Address</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea class="form-control" id="groupCCIAddress"
							maxlength="100" ng-model="formInfo.cciAddress"
							placeholder="{{'Enter CCI Address'| translate}}" required
							ng-disabled="fosterDisable"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 5.Duration of Stay at Foster Care</span><span
						class="mandatory_star">&#42;</span><br><span
						translate>(in months)</span> </label>
					<div class="col-md-7">
						<input only-three-digits id="groupTimeDuration"
							name="groupTimeDuration"
							placeholder="{{'Enter Duration'| translate}}"
							class="form-control input-md" type="number"
							ng-model="formInfo.durationOfStayAtFosterCare" required
							ng-disabled="fosterDisable"
							ng-keyUp="validateFields(formInfo.durationOfStayAtFosterCare,'durationMonthserror')"
							ng-blur="resetInput(formInfo,'durationOfStayAtFosterCare','durationMonthserror')">
						<div id="durationMonthserror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate> 6.Child Welfare Officer/Social Worker Name </span><span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="groupWorkerName" name="groupWorkerName"
							ng-model="formInfo.fosterCareWorkerName" maxlength="30"
							placeholder="{{'Enter CWO/Social Worker Name'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="fosterDisable" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="textarea"><span
						translate> 7.Child Welfare Officer/Social Worker Address</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
						<textarea class="form-control" id="group Worker Address"
							ng-model="formInfo.fosterCareWorkerAddress" maxlength="100"
							placeholder="{{'Enter CWO/Social Worker Address'| translate}}"
							ng-disabled="fosterDisable" required></textarea>
					</div>
				</div>
				<div style="text-align: center">
					<!-- 								<button id="groupbutton3id" name="button3id" class="btn btn-info" type="submit" ng-click="openUpdateModalForFG()">Update</button> -->
					<button ng-if="!fosterDisable" id="groupbutton1id" name="button1id"
						class="btn btn-info" type="submit" ng-click="openModalForFG()"><span translate>Submit</span></button>
					<button ng-if="fosterDisable" id="button2id" name="button2id"
						class="btn btn-info" ng-click="printOldFosterCareSubmitData()"><span translate>Print</span></button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script>
	var app = angular.module('interimDecision', [ 'gettext' ]);
	var myAppConstructor = angular.module('interimDecision');
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#firstRadio').prop('checked', true);
	});
</script>
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