<div ng-controller="personalDetailsController" id="personalDetailsBody"
	ng-cloak>
	<div class="modal fade" id="personalDetailsConfirmationModal"
		tabindex="-1" role="dialog" data-backdrop="static">
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
							ng-click="saveData()" class="close" data-dismiss="modal"
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
	<div class="modal fade" id="icpPersonDetailsModal" tabindex="-1"
		role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate> The form has been saved successfully. </span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button6id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="printicpPersonDetailsASubmitData()">
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
	<!-- 	<hr>
		<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a>
		| <a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
		<hr> -->
		<div class="childlist-heading1 borderPersonal">
			<span translate>INDIVIDUAL CARE PLAN</span> <br> <span translate>FORM
				7</span> <br> <span translate> [Rules 11(3), 13(7)(vi),
				13(8)(ii), 19(4), 19(17), 62(6)(vii), 62(6)(x), 69 I (3)]</span> <br>
			<span translate> Child in Conflict with Law/Child in Need of
				Care and Protection</span>
		</div>
		<form class="form-horizontal personaldetails" name="personaldetails"
			id="personaldetails">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>PERSONAL DETAILS OF THE CHILD</span>
				</div>
				<div class="col-md-12 summaryspace">
					<div class="col-md-6 childidheader"
						style="margin-left: 0px !important; padding-left: 0px !important;">
						<div class="social_headng">
							<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" /> <span>
								<span translate>Child ID</span>:&nbsp;&nbsp;${selectedChild}
							</span>
						</div>
					</div>
					<div class="col-md-6 childnameheader"
						style="margin-right: 0px !important; padding-right: 0px !important;">
						<div class="social_headng1">
							<span> <span translate>Name of Child</span>:&nbsp;&nbsp;
								{{prefetchData.childName}}
							</span>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>1. Designation</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="designation" name="designation" class="form-control"
							ng-if="!prsnlDtlsViewPage"
							ng-options="designation as lang=='en'?designation.name:designation.typeNameHindi for designation in designationList"
							ng-model="personalDetail.designation" required>
							<option value="" disabled selected>{{'Select Designation' | translate}}</span>
							</option>
						</select> <input ng-model="lang=='en'?personalDetail.designation.name:personalDetail.designation.typeNameHindi" placeholder=""
							ng-if="prsnlDtlsViewPage" type="text"
							class="form-control input-md" readonly />
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="designationName" name="designationName"
							maxlength="30"
							ng-model="personalDetail.nameOfOfficer"
							ng-disabled="prsnlDtlsViewPage"
							placeholder="{{prsnlDtlsViewPage == true ? '' : 'Enter Name' | translate}}"
							class="form-control input-md" type="text" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>3. Date of preparing the ICP</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="datepicker" ng-disabled="prsnlDtlsViewPage"
							ng-model="personalDetail.dateOfIcp" readonly class="form-control">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4. Case/Profile No.</span>
					</label>
					<div class="col-md-7">
						<input ng-model="prefetchData.caseNum"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Year</span>
					</label>
					<div class="col-md-7">
						<input id="caseyr" name="caseyr" ng-model="prefetchData.year"
							placeholder="{{'Year'| translate}}" class="form-control input-md"
							type="number" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. FIR No.</span>
					</label>
					<div class="col-md-7">
						<input id="firNo" placeholder="{{'Enter FIR No.'| translate}}"
							maxlength="30" class="form-control input-md" type="text" readonly
							ng-model="prefetchData.firNumber">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate> 7. U/Sections (Type of offence), applicable in case of Children in Conflict with Law </span>
					</label>
					<div class="col-md-7" style="padding-top: 12px;">
						<input id="uSection"
							placeholder="{{'Enter Sections.'| translate}}"
							class="form-control input-md" type="text" maxlength="40"
							ng-model="personalDetail.uSections"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Police Station</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="policeStation" ng-model="personalDetail.policeStation"
							maxlength="30"
							placeholder="{{'Enter Police Station'| translate}}"
							class="form-control input-md" type="text" required
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate> 9. Address of the Board or the Committee </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7" style="padding-top: 12px;">
						<textarea id="boardAddress" ng-model="personalDetail.boardAddress"
							placeholder="{{'Enter Address of the Board or the Committee'| translate}}"
							maxlength="200" class="form-control input-md" type="text"
							required ng-disabled="prsnlDtlsViewPage"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate> 10. Admission No. (if child is in an institution) </span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7" style="padding-top: 12px;">
						<input id="admissionNo" ng-model="personalDetail.admissionNum"
							maxlength="30" required
							placeholder="{{'Enter Admission No'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate> 11. Date of Admission (if child is in an institution) </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6" style="padding-top: 0px;">
						<input type="text" id="datepicker1"
							ng-model="personalDetail.admissionDate" readonly
							class="form-control" ng-disabled="prsnlDtlsViewPage">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate> 12. Stay of the child (Fill as applicable) </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="stayPeriod" name="stayPeriod"
							ng-if="!prsnlDtlsViewPage" required
							ng-model="personalDetail.stayOfChild" class="form-control"
							ng-options="item as lang=='en'?item.name:item.typeNameHindi for item in stayOfTheChild">
							<option value="" disabled selected>{{'Select Time Period' | translate}}
							</option>
						</select> <input placeholder="" ng-if="prsnlDtlsViewPage"
							class="form-control input-md" type="text"
							ng-model="lang=='en'?personalDetail.stayOfChild.name:personalDetail.stayOfChild.typeNameHindi" readonly>
					</div>
				</div>
				<div class="grey-header">
					<span translate>13. PERSONAL DETAILS</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Name of the Child</span>
					</label>
					<div class="col-md-7">
						<input id="childName"
							placeholder="{{'Enter Name of the Child'| translate}}"
							class="form-control input-md" type="text"
							maxlength="30" ng-model="prefetchData.childName"
							readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Age</span>
					</label>
					<div class="col-md-7">
						<input id="age" name="age" ng-model="prefetchData.age"
							placeholder="{{'Enter Age of the Child'| translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(iii) Date of Birth</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dob" ng-model="personalDetail.dob" readonly
							class="form-control" ng-disabled="prsnlDtlsViewPage">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="sex" name="sex" class="form-control"
							ng-model="prefetchData.childSex" ng-disabled="true">
							<option value="" disabled selected><span translate>Select
									Sex</span>
							</option>
							<option ng-repeat="sex in childSexList" ng-value="sex.id">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(v) Father's Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="fathername"
							placeholder="{{'Enter Father\'s Name'| translate}}" required
							class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage" maxlength="30"
							ng-model="personalDetail.fatherName" disabled>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(vi) Mother's Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="motherName"
							placeholder="{{'Enter Mother\'s Name'| translate}}" required
							class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage" maxlength="30"
							ng-model="personalDetail.motherName" disabled>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(vii) Nationality</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="nationality" ng-model="personalDetail.nationality"
							required placeholder="{{'Enter Nationality'| translate}}"
							class="form-control input-md" type="text"
							maxlength="30" ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(viii) Religion</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="religion" name="religion" class="form-control"
							ng-model="personalDetail.religion.id" ng-disabled="true">
							<option value="" disabled selected translate>Select Religion
							</option>
							<option ng-repeat="religion in religionList"
								ng-value="religion.id">{{lang=='en'?religion.name:religion.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding"
					ng-if="personalDetail.religion.id == 187">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>Please specify</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="religionOther" ng-model="personalDetail.religionOther"
							class="form-control input-md" required
							maxlength="30"
							placeholder="{{'Please specify'| translate}}"
							class="form-control input-md" type="text" ng-disabled="true">
					</div>
				</div>
				<div class="form-group box-border-padding"
					ng-if="personalDetail.religion.id == 184">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ix) Caste</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="caste" name="caste" class="form-control"
							ng-model="personalDetail.caste.id" ng-disabled="true">
							<option value="" disabled selected><span translate>Select Caste</span>
							</option>
							<option ng-repeat="caste in casteList" ng-value="caste.id">{{lang=='en'?caste.name:caste.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(x) Language Spoken</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="language" ng-model="personalDetail.language"
							maxlength="30" required
							placeholder="{{'Enter Language Spoken'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(xi) Level of Education</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="education" ng-model="personalDetail.education"
							required ng-if="!prsnlDtlsViewPage" name="education"
							class="form-control">
							<option value="" disabled selected>{{'Select Level of Education' | translate}}
							</option>
							<option ng-repeat="item in educationLevels" ng-value="{{item}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select> <input ng-model="lang=='en'?personalDetail.education.name:personalDetail.education.typeNameHindi" placeholder=""
							class="form-control input-md" type="text" readonly
							ng-if="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(xii) Details of Savings Account of the child, if any</span>
					</label>
					<div class="col-md-7" ng-if="!prsnlDtlsViewPage">
						<label class="radio-inline" for="radios-0"> <input
							name="radios" id="radios-0" ng-value="true" type="radio"
							ng-model="radioObject.accountDetail"> <span translate>Yes</span>
						</label> <label class="radio-inline" for="radios-1"> <input
							name="radios1" id="radios-1" ng-value="false" checked="checked"
							type="radio" ng-model="radioObject.accountDetail"
							ng-click="clearFieldForQ13(radioObject.accountDetail, 'savAcc')">
							<span translate>No</span>
						</label>
					</div>
					<div class="col-md-7" ng-if="prsnlDtlsViewPage">
						<input id="accountDetail"
							placeholder="{{personalDetail.accountDetail != null ? 'Yes' : 'No' | translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding inputBOXSpace"
					ng-if="radioObject.accountDetail || personalDetail.accountDetail">
					<label class="col-md-4 control-label" for="textinput"></label>
					<div class="col-md-7">
						<input id="accountDetail"
							placeholder="{{'Enter Details of Savings Account'| translate}}"
							class="form-control input-md" type="text" required
							ng-blur="blur(personalDetail.accountDetail,'accountDetail')"
							ng-trim="false" ng-model="personalDetail.accountDetail"
							maxlength="100" ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span translate>(xiii) Details of child's earnings and belongings, if any</span>
					</label>
					<div class="col-md-7" ng-if="!prsnlDtlsViewPage">
						<label class="radio-inline" for="radios-2"> <input
							name="radiosOne" id="radios-2" ng-value="true" type="radio"
							ng-model="radioObject.earnings"> <span translate>Yes</span>
						</label> <label class="radio-inline" for="radios-3"> <input
							name="radiosOne1" id="radios-3" ng-value="false"
							checked="checked" type="radio"
							ng-click="clearFieldForQ13(radioObject.earnings,'earnings')"
							ng-model="radioObject.earnings"> <span translate>
								No</span>
						</label>
					</div>
					<div class="col-md-7" ng-if="prsnlDtlsViewPage">
						<input id="accountDetail"
							placeholder="{{personalDetail.earnings != null ? 'Yes' : 'No' | translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding inputBOXSpace"
					ng-if="radioObject.earnings || personalDetail.earnings">
					<label class="col-md-4 control-label" for="textinput"></label>
					<div class="col-md-7">
						<input id="religion"
							placeholder="{{'Enter details of child's earnings and belongings'| translate}}"
							class="form-control input-md" type="text" required
							ng-blur="blur(personalDetail.earnings,'earnings')"
							ng-trim="false" ng-model="personalDetail.earnings"
							maxlength="100" ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate> (xiv) Details of awards/rewards received by the child, if any </span>
					</label>
					<div class="col-md-7" ng-if="!prsnlDtlsViewPage">
						<label class="radio-inline" for="radios-4"> <input
							name="radiosTwo" id="radios-4" ng-value="true" type="radio"
							ng-model="radioObject.awardDetails"> <span translate>Yes</span>
						</label> <label class="radio-inline" for="radios-5"> <input
							name="radiosTwo1" id="radios-5" ng-value="false"
							checked="checked" type="radio"
							ng-click="clearFieldForQ13(radioObject.awardDetails,'awards')"
							ng-model="radioObject.awardDetails"> <span translate>No</span>
						</label>
					</div>
					<div class="col-md-7" ng-if="prsnlDtlsViewPage">
						<input
							placeholder="{{personalDetail.awardDetails != null ? 'Yes' : 'No' | translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding inputBOXSpace"
					ng-if="radioObject.awardDetails || personalDetail.awardDetails">
					<label class="col-md-4 control-label" for="textinput"></label>
					<div class="col-md-7">
						<input id="religion"
							placeholder="{{'Enter Details of awards/rewards'| translate}}"
							class="form-control input-md" type="text" required
							ng-blur="blur(personalDetail.awardDetails,'awardDetails')"
							ng-trim="false" ng-model="personalDetail.awardDetails"
							maxlength="100" ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="grey-header">
					<span translate>14. Based on the results of Case History, Social Investigation report and interaction with the child, give details on following areas of concern and interventions required, if any</span> 
					<span class="mandatory_star">&#42;</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Category 1</span> <br> <span translate>Child's expectation from care and protection</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category1a" ng-model="personalDetail.cat1aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category1b" required ng-model="personalDetail.cat1pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Category 2</span> <br> <span translate>Health and nutrition needs</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category2a" ng-model="personalDetail.cat2aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category2b" required ng-model="personalDetail.cat2pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Category 3</span> <br>
						<span translate>Emotional and psychological support needs</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category3a" ng-model="personalDetail.cat3aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category3b" required ng-model="personalDetail.cat3pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate> (iv) Category 4</span> <br> 
						<span translate>Educational and Training needs </span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category4a" ng-model="personalDetail.cat4aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category4b" required ng-model="personalDetail.cat4pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(v) Category 5</span> <br> <span translate>
							Leisure, creativity and play</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category5a" ng-model="personalDetail.cat5aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category5b" required ng-model="personalDetail.cat5pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(vi) Category 6</span> <br> 
						<span translate>Attachments and Inter-personal Relationships</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category6a" ng-model="personalDetail.cat6aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category6b" required ng-model="personalDetail.cat6pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(vii) Category 7</span> <br> 
						<span translate>Religious beliefs</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category7a" ng-model="personalDetail.cat7aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category7b" required ng-model="personalDetail.cat7pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(viii) Category 8</span> <br> 
						<span translate>Self care and life skill training for Protection from all kinds of abuse, neglect and maltreatment</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category8a" ng-model="personalDetail.cat8aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category8b" required ng-model="personalDetail.cat8pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ix) Category 9</span> <br> 
						<span translate>Independent living skills</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category9a" ng-model="personalDetail.cat9aoc" required
							class="form-control input-md" type="text" maxlength="200"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category9b" required ng-model="personalDetail.cat9pi"
							maxlength="200" class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(x) Category 10</span> <br> 
					<span translate>Any other such as significant experiences which may have impacted the development of the child like trafficking, domestic violence, parental neglect, bullying in school, etc. (Please specify)</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> <span
							translate>Areas of concern</span>
						</label> <input id="category10a" required
							ng-model="personalDetail.cat10aoc" maxlength="200"
							class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> <span
							translate>Proposed Interventions</span>
						</label> <input id="category10b" required
							ng-model="personalDetail.cat10pi" maxlength="200"
							class="form-control input-md" type="text"
							ng-disabled="prsnlDtlsViewPage">
					</div>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id" class="btn btn-info"
						type="submit" ng-if="!prsnlDtlsViewPage"
						ng-click="personaldetails.$invalid ? '' : validateForm()">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-if="prsnlDtlsViewPage"
						ng-click="printicpPersonDetailsASubmitData()" type="submit">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">				$(document).ready(function() {			$('input').blur(function() {			    var value = $.trim( $(this).val() );			    $(this).val( value );			});			$('textarea').blur(function() {			    var value = $.trim( $(this).val() );			    $(this).val( value );			});		});	</script>