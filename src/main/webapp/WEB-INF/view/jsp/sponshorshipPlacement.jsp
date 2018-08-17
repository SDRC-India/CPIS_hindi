<div ng-controller="SponsorshipController" id="sponsorshipBody" ng-cloak>
	<div class="modal fade" id="sponsorshipModal" tabindex="-1"
		role="dialog">
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
						<button id="submitData" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="saveSponsorshipData()" class="close"
							data-dismiss="modal" aria-hidden="true"><span translate>Submit</span></button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="sponsorshipAlertModal" tabindex="-1"
		role="dialog">
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
							ng-click="printSponsorshipData()">
							<span translate>Print</span>
						</button>
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()"><span translate>OK</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<input type="hidden" id="idValue" value="${selectedId}">
	<div class="box-border box-border-padding">
		<!-- <hr>
		<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a>
		| <a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
		<hr> -->
		<div class="childlist-heading1 borderPersonal">
			<span translate>ORDER OF SPONSORSHIP PLACEMENT</span> <br> <span
				translate>Form 36</span> <br> <span translate>[Rule
				24(5)]</span>
		</div>
		<form class="form-horizontal basicchildform" id="sponsorshipPlacement"
			name="sponsorshipPlacement">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>Order of Sponsorship Placement</span>
				</div>
				<jsp:include page="./common/childNameStrip.jsp" />
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>1.Child Name</span>
					</label>
					<div class="col-md-7">
						<input id="sponsorshipChildName" name="chilName"
							class="form-control input-md" type="text"
							oninvalid="this.setCustomValidity('Please enter case no')"
							oninput="setCustomValidity('')" ng-model="prefetchData.childName"
							ng-disabled="sponsorshipDisable" readonly>
						<div id="sponsorChildnameerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="textarea"> <span
						translate>2.Address</span>
					</label>
					<div class="col-md-7">
						<textarea class="form-control" id="sponsorshipAddress"
							ng-model="prefetchData.sirAddress"
							ng-disabled="sponsorshipDisable" readonly></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3.Age</span>
					</label>
					<div class="col-md-7">
						<input id="sponsorshipAge" required class="form-control input-md"
							type="number" name="sponsorshipAge" ng-model="prefetchData.age"
							ng-keyUp="validateName(sponsorshipPlacement.sponsorshipAge,'sponsorshipAgeError')"
							ng-disabled="sponsorshipDisable" readonly>
						<div id="sponsorshipAgeError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4. Father Name</span>
					</label>
					<div class="col-md-7">
						<input id="fatherName" class="form-control input-md" type="text"
							ng-model="prefetchData.sirFatherName" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5.Mother Name</span>
					</label>
					<div class="col-md-7">
						<input id="motherName" class="form-control input-md" type="text"
							ng-model="prefetchData.sirMotherName" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6.Sponsorship Support</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7" ng-if="!sponsorshipDisable">
						<select id="sponsorshipPlacementOrder" class="form-control"
							required ng-disabled="sponsorshipDisable"
							ng-model="sponsorshipData.sponsorshipFor"
							ng-change="clearOther()"
							oninvalid="this.setCustomValidity('Please select sponsorship support details')"
							oninput="setCustomValidity('')"
							ng-options="item as lang=='en'?item.name:item.typeNameHindi for item in sponsorshipSupportFor">
							<option value="" disabled selected>{{'Select' | translate}}</option>
							<!-- 										<option ng-repeat="item in sponsorshipSupportFor" ng-value="item.id" onClick="setSelectedObj(item)">{{item.name}}</option> -->
						</select>
					</div>
					<div ng-if="sponsorshipDisable" class="col-md-7"
						style="margin-top: 10px; margin-left: -16px;">
						<input type="text" class="form-control input-md"
							ng-model="lang=='en'?sponsorshipData.sponsorshipFor.name:sponsorshipData.sponsorshipFor.typeNameHindi"
							ng-disabled="sponsorshipDisable" style="margin-left: 16px;">
					</div>
					<div class="col-md-4"></div>
					<div ng-if="sponsorshipData.sponsorshipFor.id==352"
						class="col-md-7" style="margin-top: 10px; margin-left: 0px;">
						<input placeholder="{{'Please specify'| translate}}" required
							type="text" class="form-control input-md" maxlength="30"
							ng-model="sponsorshipData.otherSponsorshipFor"
							ng-disabled="sponsorshipDisable"
							ng-blur="sponsorsBlur(sponsorshipData.otherSponsorshipFor,'otherSponsorshipFor')"
							ng-trim="false">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>7. Sponsorship Support Directed to DCPU</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input id="selectDatafromsponsor"
							placeholder="{{'Please specify the amount'| translate}}"
							oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
							maxlength="9" class="form-control input-md" type="number"
							ng-model="sponsorshipData.sponsorshipAmount"
							ng-disabled="sponsorshipDisable" required>
					</div>
					<div class="col-md-5">
						<select ng-model="sponsorshipData.typeOfSponsorship" id="per_"
							class="form-control" style="width: 32%; display: inline;"
							ng-disabled="sponsorshipDisable"
							ng-required="sponsorshipData.sponsorshipAmount" required>
							<option value="">{{'Select' | translate}}</option>
							<option value="Per Month">{{'Per Month' | translate}}</option>
							<option value="One Time">{{'One Time' | translate}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>8.Sponsorship Period</span> <br> <span translate>(days/month)</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input id="sponsorperiod"
							placeholder="{{'Enter sponsorship period'| translate}}"
							oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
							maxlength="9" class="form-control input-md" type="number"
							ng-model="sponsorshipData.sponsorshipPeriod"
							ng-disabled="sponsorshipDisable" required>
					</div>
					<div class="col-md-5">
						<select ng-model="sponsorshipData.daysOrMonths" id="days-or-month"
							class="form-control" style="width: 32%; display: inline;"
							ng-disabled="sponsorshipDisable"
							ng-required="sponsorshipData.sponsorshipPeriod" required>
							<option value="">{{'Select' | translate}}</option>
							<option value="Days">{{'Days' | translate}}</option>
							<option value="Months">{{'Months' | translate}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9.Open a bank account in the name of the child</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="accountName" name="accountName" maxlength="30"
							placeholder="{{'Enter name of the person who will operate the bank account'| translate}}"
							class="form-control input-md" type="text"
							ng-model="sponsorshipData.accountName" required
							ng-disabled="sponsorshipDisable" onclick="return validate();">
						<div id="accountName" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10.Bank Account Operated By</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="attitudeofclassmates" name="attitudeofclassmates"
							required
							placeholder="{{'Enter name of the person who will operate the bank account'| translate}}"
							class="form-control input-md" type="text" maxlength="30"
							ng-model="sponsorshipData.operatedBy" reqiured
							ng-disabled="sponsorshipDisable">
						<div id="attitudeofclassmatesError" class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id" class="btn btn-info"
						ng-if="!sponsorshipDisable" type="submit"
						ng-click="sponsorshipPlacement.$invalid ? '' : validateSponsorshipForm()"><span translate>Submit</span></button>
					<button ng-if="sponsorshipDisable" id="button2id" name="button2id"
						class="btn btn-info" type="submit"
						ng-click="printSponsorshipData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">function validate() {    var firstname = document.getElementById("FirstName");    var alpha = /^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/;    if (firstname.value == "") {        alert('Please enter Name');        return false;    }    else if (!firstname.value.match(alpha)) {        alert('Invalid ');               return false;   }   else    {    return true;   }}</script>
</html>