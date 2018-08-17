<div ng-controller="ConstitutionofDCPCController" ng-cloak
	id="constitutionofDCPCBody">
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForconstitutionofDCPC"
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
							data-dismiss="modal" aria-hidden="true" translate>Back</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="constitutionofDCPCModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been saved successfully.</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reLoad()">
							<span translate>Ok</span>
						</button>
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"
							ng-click="printConstitutionOfDCPCSubmitData()">
							<span translate>Print</span>
						</button>
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
		<div class="childlist-heading1 borderPersonal">
			<span translate>Constitution of DCPC</span> <br>
		</div>
		<form class="form-horizontal basicchildform" name="constitutionofDCPC"
			id="constitutionofDCPC">
			<fieldset>
				<div class="form-group box-border-padding constitutiontop">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>1. Constitution date of the DCPC</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dcpcDate" ng-model="dcpcformInfo.dcpcDate"
							readonly class="form-control" ng-disabled="viewPage">
						<div id="constitutiondateoftheDCPCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Name of the Zilla Parishad (Chairperson, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="namezillaParishad" name="namezillaParishad"
							ng-model="dcpcformInfo.zillaParishadName"
							placeholder="{{'Enter name of the Zilla Parishad (Chairperson, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i). Sex</span>
					</label>
					<div class="col-md-7">
						<select id="constitutionDCPCData" class="form-control"
							ng-model="dcpcformInfo.zillaParishadSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii). Contact number of the Zilla Parishad (Chairperson, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbberZillaParishad"
							name="contactNumbberZillaParishad"
							ng-model="dcpcformInfo.zillaParishadContactNo"
							placeholder="{{'Enter contact no. of the zilla parishad (chairperson, DCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the zilla parishad ')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.zillaParishadContactNo,'dcpcPhoneNoError1')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError1" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii). Email ID of the Zilla Parishad (Chairperson, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailPrinciple" name="emailPrinciple"
							ng-model="dcpcformInfo.zillaParishadEmailId" maxlength="100"
							placeholder="{{'Enter email id of the zilla parishad (Chairperson, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.zillaParishadEmailId,'emailzpError')">
						<div id=emailzpError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3. Name of the District Magistrate (Co-Chairperson, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="SocialWorker1" name="SocialWorker1"
							placeholder="{{'Enter name of the district magistrate (Co-Chairperson, DCPC)'| translate}}"
							ng-model="dcpcformInfo.magistrateName"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="SocialWorkerdata1" class="form-control"
							ng-model="dcpcformInfo.magistrateSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of the District Magistrate (Co-Chairperson, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbberDistrictMagistrate1"
							name="contactNumbberDistrictMagistrate1"
							ng-model="dcpcformInfo.magistrateContactNo"
							placeholder="{{'Enter contact number of district magistrate (Co-Chairperson, DCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of district magistrate')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.magistrateContactNo,'dcpcPhoneNoError2')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError2" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of the District Magistrate (Co-Chairperson, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailpersonworker1" name="emailpersonworker1"
							ng-model="dcpcformInfo.magistrateEmailId" maxlength="100"
							placeholder="{{'Enter email id of the district magistrate (Co-Chairperson, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.magistrateEmailId,'emaildistmagistrateError')">
						<div id=emaildistmagistrateError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4. Name of the DEO (member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="socialWorker2" name="socialWorker2"
							ng-model="dcpcformInfo.deoName"
							placeholder="{{'Enter name of DEO (member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="socialWorker2Data" class="form-control"
							ng-model="dcpcformInfo.deoSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of the DEO (member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbberDEO"
							ng-model="dcpcformInfo.deoContactNo"
							name="contactNumbbersocialWorker2"
							placeholder="{{'Enter contact number of DEO (member, DCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the social worker')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.deoContactNo,'dcpcPhoneNoError3')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError3" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of the DEO (member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson1" name="emailperson1"
							ng-model="dcpcformInfo.deoEmailId" maxlength="100"
							placeholder="{{'Enter email id of the DEO (member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.deoEmailId,'emaildcpcdeoError')">
						<div id=emaildcpcdeoError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Name of the CDMO (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="socialWorker2" name="socialWorker2"
							ng-model="dcpcformInfo.cdmoName"
							placeholder="{{'Enter name of CDMO (Member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="socialWorker3Data" class="form-control"
							ng-model="dcpcformInfo.cdmoSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of the CDMO (Member, DCPC) </span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberCDMO"
							ng-model="dcpcformInfo.cdmoContactNo"
							name="contactNumbbersocialWorker3"
							placeholder="{{'Enter contact number of CDMO (Member, DCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the social worker')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.cdmoContactNo,'dcpcPhoneNoError4')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError4" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of the CDMO (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson2" name="emailperson2"
							ng-model="dcpcformInfo.cdmoEmailId" maxlength="100"
							placeholder="{{'Enter email id of the CDMO (Member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.cdmoEmailId,'emailcdmoError')">
						<div id=emailcdmoError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Name of the DLO (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="socialWorker3" name="socialWorker3"
							ng-model="dcpcformInfo.dloName"
							placeholder="{{'Enter name of DLO (Member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="socialWorker4Data" class="form-control"
							ng-model="dcpcformInfo.dloSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of the DLO (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberDLO"
							ng-model="dcpcformInfo.dloContactNo"
							name="contactNumbbersocialWorker4"
							placeholder="{{'Enter contact no. of the DLO (Member,DCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the DLO (Member,DCPC)')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.dloContactNo,'dcpcPhoneNoError5')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError5" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of the DLO (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson3" name="emailperson3"
							ng-model="dcpcformInfo.dloEmailId" maxlength="100"
							placeholder="{{'Enter email id of the DLO (Member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.dloEmailId,'emaildloError')">
						<div id=emaildloError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7. Name of the PDDRDA (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="socialWorker4" name="socialWorker4"
							ng-model="dcpcformInfo.pddrdaName"
							placeholder="{{'Enter name of PDDRDA (Member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="socialWorker5Data" class="form-control"
							ng-model="dcpcformInfo.pddrdaSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of the PDDRDA (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberPDDRDA"
							ng-model="dcpcformInfo.pddrdaContactNo"
							name="contactNumbbersocialWorker5"
							placeholder="{{'Enter contact number of PDDRDA (Member, DCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the social worker')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.pddrdaContactNo,'dcpcPhoneNoError6')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError6" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of the PDDRDA (Member, DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson4" name="emailperson4"
							ng-model="dcpcformInfo.pddrdaEmailId" maxlength="100"
							placeholder="{{'Enter email id of the PDDRDA (Member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.pddrdaEmailId,'emailpddrdaError')">
						<div id=emailpddrdaError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Name of the Superintendent of Police (Member,DCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="socialWorker5" name="socialWorker5"
							ng-model="dcpcformInfo.policeSuperintendentName"
							placeholder="{{'Enter name of Superintendent of Police (Member, DCPC)'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="socialWorker5Data" class="form-control"
							ng-model="dcpcformInfo.policeSuperintendentSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9. Name of Member 1</span>
					</label>
					<div class="col-md-7">
						<input id="memberOne" name="memberOne"
							ng-model="dcpcformInfo.memberOneName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberOneSex" class="form-control"
							ng-model="dcpcformInfo.memberOneSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member </span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberOne"
							ng-model="dcpcformInfo.memberOneContactNo"
							name="contactNumbberMemberOne"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberOneContactNo,'dcpcPhoneNoError7')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError7" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberOne" name="emailMemberOne"
							ng-model="dcpcformInfo.memberOneEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberOneEmailId,'emailMemberOneError')">
						<div id=emailMemberOneError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10. Name of Member 2</span>
					</label>
					<div class="col-md-7">
						<input id="memberTwo" name="memberTwo"
							ng-model="dcpcformInfo.memberTwoName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberTwoSex" class="form-control"
							ng-model="dcpcformInfo.memberTwoSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberTwo"
							ng-model="dcpcformInfo.memberTwoContactNo"
							name="contactNumbberMemberTwo"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact number of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberTwoContactNo,'dcpcPhoneNoError8')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError8" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberTwo" name="emailMemberTwo"
							ng-model="dcpcformInfo.memberTwoEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberTwoEmailId,'emailMemberTwoError')">
						<div id=emailMemberTwoError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>11. Name of Member 3</span>
					</label>
					<div class="col-md-7">
						<input id="memberThree" name="memberThree"
							ng-model="dcpcformInfo.memberThreeName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberThreeSex" class="form-control"
							ng-model="dcpcformInfo.memberThreeSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberThree"
							ng-model="dcpcformInfo.memberThreeContactNo"
							name="contactNumberMemberThree"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact number of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberThreeContactNo,'dcpcPhoneNoError9')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError9" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberThree" name="emailMemberThree"
							ng-model="dcpcformInfo.memberThreeEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberThreeEmailId,'emailMemberThreeError')">
						<div id=emailMemberThreeError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>12. Name of Member 4</span>
					</label>
					<div class="col-md-7">
						<input id="memberFour" name="memberFour"
							ng-model="dcpcformInfo.memberFourName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberFourSex" class="form-control"
							ng-model="dcpcformInfo.memberFourSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberFour"
							ng-model="dcpcformInfo.memberFourContactNo"
							name="contactNumberMemberFour"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact number of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberFourContactNo,'dcpcPhoneNoError10')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError10" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberFour" name="emailMemberFour"
							ng-model="dcpcformInfo.memberFourEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberFourEmailId,'emailMemberFourError')">
						<div id=emailMemberFourError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>13. Name of Member 5</span>
					</label>
					<div class="col-md-7">
						<input id="memberFive" name="memberFive"
							ng-model="dcpcformInfo.memberFiveName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberFiveSex" class="form-control"
							ng-model="dcpcformInfo.memberFiveSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberFive"
							ng-model="dcpcformInfo.memberFiveContactNo"
							name="contactNumberMemberFive"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact number of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberFiveContactNo,'dcpcPhoneNoError11')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError11" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberFive" name="emailMemberFive"
							ng-model="dcpcformInfo.memberFiveEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberFiveEmailId,'emailMemberFiveError')">
						<div id=emailMemberFiveError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>14. Name of Member 6</span>
					</label>
					<div class="col-md-7">
						<input id="memberSix" name="memberSix"
							ng-model="dcpcformInfo.memberSixName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberSixSex" class="form-control"
							ng-model="dcpcformInfo.memberSixSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberSix"
							ng-model="dcpcformInfo.memberSixContactNo"
							name="contactNumberMemberSix"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact number of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberSixContactNo,'dcpcPhoneNoError12')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError12" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberSix" name="emailMemberSix"
							ng-model="dcpcformInfo.memberSixEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberSixEmailId,'emailMemberSixError')">
						<div id=emailMemberSixError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>15. Name of Member 7</span>
					</label>
					<div class="col-md-7">
						<input id="memberSeven" name="memberSeven"
							ng-model="dcpcformInfo.memberSevenName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberSevenSex" class="form-control"
							ng-model="dcpcformInfo.memberSevenSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberSeven"
							ng-model="dcpcformInfo.memberSevenContactNo"
							name="contactNumberMemberSeven"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact number of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberSevenContactNo,'dcpcPhoneNoError13')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError13" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberSeven" name="emailMemberSeven"
							ng-model="dcpcformInfo.memberSevenEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberSevenEmailId,'emailMemberSevenError')">
						<div id=emailMemberSevenError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>16. Name of Member 8</span>
					</label>
					<div class="col-md-7">
						<input id="memberEight" name="memberEight"
							ng-model="dcpcformInfo.memberEightName"
							placeholder="{{'Enter member name'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="memberEightSex" class="form-control"
							ng-model="dcpcformInfo.memberEightSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in dcpcSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact number of Member </span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumberMemberEight"
							ng-model="dcpcformInfo.memberEightContactNo"
							name="contactNumberMemberEight"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact number of member')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(dcpcformInfo.memberEightContactNo,'dcpcPhoneNoError14')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError14" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email ID of Member</span>
					</label>
					<div class="col-md-7">
						<input id="emailMemberEight" name="emailMemberEight"
							ng-model="dcpcformInfo.memberEightEmailId" maxlength="100"
							placeholder="{{'Enter email id'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(dcpcformInfo.memberEightEmailId,'emailMemberEightError')">
						<div id=emailMemberEightError class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<button id="buttonid" name="button1id" class="btn btn-info"
						ng-click="constitutionofDCPC.$invalid ? '' : validateForm()"
						type="submit">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-if="caseSummaryDisable" type="submit"
						ng-click="printCaseSummarySubmitData()">
						<span translate>Print</span>
					</button>
					<!-- 					<button id="button2id" name="button2id" class="btn btn-info" -->
					<!-- 						type="reset">RESET</button> -->
				</div>
			</fieldset>
		</form>
		<div class="modal fade" id="errorImageModal" tabindex="-1"
			role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p style="text-align: center">
							<span translate>Uploaded file is not in correct format.</span> <br>
							<button id="button5id" name="button5id" class="btn btn-info"
								type="submit" class="close" data-dismiss="modal"
								aria-hidden="true">
								<span translate>Ok</span>
							</button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">	$(document).ready(function() {		$('input').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});		$('textarea').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});	});</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
	var="jQueryUiCore" />
<script src="${jQueryUiCore}"></script>
<script type="text/javascript" src="resources/js/download.js"></script>