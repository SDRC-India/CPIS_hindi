<div ng-controller="constitutionOfBCPCController" ng-cloak
	id="constitutionBCPCBody">
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForConstitutionBCPC"
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
							ng-click="saveBCPCConstitution()" class="close"
							data-dismiss="modal" aria-hidden="true" translate>Submit</button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" translate>Back</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="constitutionBCPCModal" tabindex="-1"
		role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<p style="text-align: center">
						<span translate>The form has been saved successfully.</span> <br>
						<button id="button5id" name="button6id"
							class="btn btn-info bigbutton" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"
							ng-click="printConstitutionBCPCSubmitData()">
							<span translate>Print</span>
						</button>
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reLoad()">
							<span translate>Ok</span>
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
			<span translate>Constitution of BCPC</span> <br>
		</div>
		<form class="form-horizontal basicchildform" name="constitutionBCPC"
			id="constitutionBCPC">
			<fieldset>
				<div class="form-group box-border-padding constitutiontop">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>1. Block name</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<select id="blockId" name="blockName" class="form-control"
							ng-model="bcpcConstitution.blockId" required>
							<option value="" disabled selected translate>Select block</span>
							</option>
							<option ng-repeat="item in blockList" ng-value="{{item.id}}">{{item.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding constitutiontop">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>2. Constitution date of the BCPC</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="bcpc_datepicker" readonly
							class="form-control">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3. Name of the Block Chairman (Chairperson BCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="nameChairperson" name="nameChairperson"
							placeholder="{{'Enter name of the Block Chairman (Chairperson BCPC)'| translate}}"
							ng-model="bcpcConstitution.blockChairmanName"
							class="form-control input-md" type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="constitutionBCPCData" class="form-control"
							ng-model="bcpcConstitution.blockChairmanSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. Block Chairman (Chairperson BCPC)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="bcContactNumber" name="contactNumbber"
							ng-model="bcpcConstitution.blockChairmanContact"
							placeholder="{{'Enter contact number of Block Chairman (Chairperson BCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(bcpcConstitution.blockChairmanContact,'bcPhoneNoError')"
							ng-pattern="/^[0-9]{1}[0-9]{9}$/" class="form-control input-md">
						<div id="bcPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the Block Chairman (Chairperson BCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailChairperson" name="emailChairperson"
							ng-model="bcpcConstitution.blockChairmanEmail"
							placeholder="{{'Enter email id of the Block Chairman (Chairperson BCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.blockChairmanEmail,'emailbcError')">
						<div id=emailbcError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4. Name of the Block Development Officer (Member Secretary BCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="member2" name="member2"
							placeholder="{{'Enter name of the Block Development Officer (Member Secretary BCPC)'| translate}}"
							ng-model="bcpcConstitution.bdoName" class="form-control input-md"
							type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member1Data" class="form-control"
							ng-model="bcpcConstitution.bdoSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the Block Development Officer (Member Secretary BCPC)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="bdoContactNumber"
							name="contactNumbbermember1"
							placeholder="{{'Enter contact number of Block Development Officer (Member Secretary BCPC)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-model="bcpcConstitution.bdoContact"
							ng-keyUp="validateName(bcpcConstitution.bdoContact,'bdoPhoneNoError')"
							class="form-control input-md">
						<div id="bdoPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the Block Development Officer (Member Secretary BCPC)</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson" name="emailperson"
							ng-model="bcpcConstitution.bdoEmail"
							placeholder="{{'Enter email id of the Block Development Officer (Member Secretary BCPC)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.bdoEmail,'emailbdoError')">
						<div id=emailbdoError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Name of the Member from DCPU</span>
					</label>
					<div class="col-md-7">
						<input id="member4" name="member4"
							placeholder="{{'Enter name of the member from DCPU'| translate}}"
							ng-model="bcpcConstitution.dcpuMemberName"
							class="form-control input-md" type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member2Data" class="form-control"
							ng-model="bcpcConstitution.dcpuMemberSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the Member from DCPU</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="dcpuContactNumber"
							name="contactNumbbermember3"
							placeholder="{{'Enter contact number of member from DCPU'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-model="bcpcConstitution.dcpuMemberContact"
							ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-keyUp="validateName(bcpcConstitution.dcpuMemberContact,'dcpcPhoneNoError')"
							class="form-control input-md">
						<div id="dcpcPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the member from DCPU</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson2" name="emailperson2"
							ng-model="bcpcConstitution.dcpuMemberEmail"
							placeholder="{{'Enter email id of the member from DCPU'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.dcpuMemberEmail,'emailbcpcmemberError')">
						<div id=emailbcpcmemberError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Name of the ICDS functionary</span>
					</label>
					<div class="col-md-7">
						<input id="member4" name="member4"
							placeholder="{{'Enter name of the ICDS functionary'| translate}}"
							ng-model="bcpcConstitution.icdsFunctionaryName"
							class="form-control input-md" type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member3Data" class="form-control"
							ng-model="bcpcConstitution.icdsFunctionarySex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the ICDS functionary</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="icdsContactNumber"
							name="contactNumbbermember4"
							placeholder="{{'Enter contact number of ICDS functionary'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-model="bcpcConstitution.icdsFunctionaryContact"
							ng-keyUp="validateName(bcpcConstitution.icdsFunctionaryContact,'icdsPhoneNoError')"
							class="form-control input-md">
						<div id="icdsPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the ICDS functionary</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson4" name="emailperson4"
							ng-model="bcpcConstitution.icdsFunctionaryEmail"
							placeholder="{{'Enter email id of the ICDS functionary'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.icdsFunctionaryEmail,'emailicdsError')">
						<div id=emailicdsError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7. Name of the Block Education Officer</span>
					</label>
					<div class="col-md-7">
						<input id="member5" name="member5"
							placeholder="{{'Enter name of the Block Education Officer'| translate}}"
							ng-model="bcpcConstitution.beoName" class="form-control input-md"
							type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member5Data" class="form-control"
							ng-model="bcpcConstitution.beoSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the Block Education Officer</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="beoContactNumber"
							name="contactNumbbermember2"
							placeholder="{{'Enter contact number of Block Education Officer'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-model="bcpcConstitution.beoContact"
							ng-keyUp="validateName(bcpcConstitution.beoContact,'beoPhoneNoError')"
							class="form-control input-md">
						<div id="beoPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the Block Education Officer</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson5" name="emailperson5"
							ng-model="bcpcConstitution.beoEmail"
							placeholder="{{'Enter email id of the Block Education Officer'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.beoEmail,'emailbeoError')">
						<div id=emailbeoError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Name of the Officer Incharge CHC</span>
					</label>
					<div class="col-md-7">
						<input id="DataentryOperator" name="DataentryOperator"
							placeholder="{{'Enter name of the Officer Incharge CHC'| translate}}"
							ng-model="bcpcConstitution.oichcName"
							class="form-control input-md" type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="DataentryOperatorsex" class="form-control"
							ng-model="bcpcConstitution.oichcSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the Officer Incharge CHC</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="oichcContactNumber"
							name="contactNumbbermember1"
							placeholder="{{'Enter contact number of Officer Incharge CHC'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-model="bcpcConstitution.oichcContact"
							ng-keyUp="validateName(bcpcConstitution.oichcContact,'oichcPhoneNoError')"
							class="form-control input-md">
						<div id="oichcPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the Officer Incharge CHC</span>
					</label>
					<div class="col-md-7">
						<input id="DataentryOperatoremail" name="DataentryOperatoremail"
							ng-model="bcpcConstitution.oichcEmail"
							placeholder="{{'Enter email id of the Officer Incharge CHC'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.oichcEmail,'emailoichcError')">
						<div id=emailoichcError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9. Name of Chairman, Village Level Child Protection Committee</span>
					</label>
					<div class="col-md-7">
						<input id="nameofChairman" name="nameofChairman"
							placeholder="{{'Enter name of the Chairman,village level child protection committee'| translate}}"
							ng-model="bcpcConstitution.vlgLvlCPCName"
							class="form-control input-md" type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="DataentryOperatorsex" class="form-control"
							ng-model="bcpcConstitution.vlgLvlCPCSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the Chairman</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="vlgLvlContactNumber"
							name="contactNumbbermember6"
							placeholder="{{'Enter contact number of Chairman'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-model="bcpcConstitution.vlgLvlCPCContact"
							ng-keyUp="validateName(bcpcConstitution.vlgLvlCPCContact,'vlgLvlPhoneNoError')"
							class="form-control input-md">
						<div id="vlgLvlPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the Chairman</span>
					</label>
					<div class="col-md-7">
						<input id="DataentryOperatoremail1" name="DataentryOperatoremail1"
							ng-model="bcpcConstitution.vlgLvlCPCEmail"
							placeholder="{{'Enter email id the Chairman'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.vlgLvlCPCEmail,'emailbcpcchairmanError')">
						<div id=emailbcpcchairmanError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10. Name of the Civil Society Member</span>
					</label>
					<div class="col-md-7">
						<input id="civilSociety" name="civilSociety"
							placeholder="{{'Enter name of the Civil Society Member'| translate}}"
							ng-model="bcpcConstitution.csmName" class="form-control input-md"
							type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="DataentryOperatorsex" class="form-control"
							ng-model="bcpcConstitution.csmSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the Civil Society Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="csmPhoneNoErr"
							name="contactNumbbermember7"
							placeholder="{{'Enter contact number of Civil Society Member'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-model="bcpcConstitution.csmContact"
							ng-keyUp="validateName(bcpcConstitution.csmContact,'csmPhoneNoError')"
							class="form-control input-md">
						<div id="csmPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the Civil Society Member</span>
					</label>
					<div class="col-md-7">
						<input id="CivilSocietyMemberemail1"
							name="CivilSocietyMemberemail1"
							ng-model="bcpcConstitution.csmEmail"
							placeholder="{{'Enter email id of the civil society member'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.csmEmail,'emailcsmError')">
						<div id=emailcsmError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>11. Name of the Community Member</span>
					</label>
					<div class="col-md-7">
						<input id="civilSociety1" name="civilSociety1"
							placeholder="{{'Enter name of the Community Member'| translate}}"
							ng-model="bcpcConstitution.cmName" class="form-control input-md"
							type="text" maxlength="40">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="DataentryOperatorsex1" class="form-control"
							ng-model="bcpcConstitution.cmSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii) Contact No. of the Community Member</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="cmPhoneNoErr" name="contactNumber"
							placeholder="{{'Enter contact number of the community member'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')" ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							ng-model="bcpcConstitution.cmContact"
							ng-keyUp="validateName(bcpcConstitution.cmContact,'cmPhoneNoError')"
							class="form-control input-md">
						<div id="cmPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Email id of the Community Member</span>
					</label>
					<div class="col-md-7">
						<input id="CivilSocietyMemberemail4"
							name="CivilSocietyMemberemail4"
							ng-model="bcpcConstitution.cmEmail"
							placeholder="{{'Enter email id of the Community Member'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(bcpcConstitution.cmEmail,'emailcmError')">
						<div id=emailcmError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>12. Number of GPCPC formed</span>
					</label>
					<div class="col-md-7">
						<input id="gpcpcFormed" name="gpcpcFormed"
							placeholder="{{'Enter Number of GPCPC formed'| translate}}"
							ng-model="bcpcConstitution.gpcpcFormed"
							class="form-control input-md" type="number" only-two-digits>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>13. Number of VCPC formed</span>
					</label>
					<div class="col-md-7">
						<input id="vcpcFormed" name="vcpcFormed"
							placeholder="{{'Enter Number of VCPC formed'| translate}}"
							ng-model="bcpcConstitution.vcpcFormed"
							class="form-control input-md" type="number" only-two-digits>
					</div>
				</div>
				<div style="text-align: center">
					<button id="buttonid" name="button1id" class="btn btn-info"
						ng-click="constitutionBCPC.$invalid ? '' : validateBCPCForm()"
						type="submit">
						<span translate>Submit</span>
					</button>
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