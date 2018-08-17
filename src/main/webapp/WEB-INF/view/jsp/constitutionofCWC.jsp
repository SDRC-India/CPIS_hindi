<div ng-controller="constitutionofCWCController" ng-cloak
	id="constitutionCWCBody">
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForConstitutionCWC"
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
							ng-click="saveCWCConstitution()" class="close"
							data-dismiss="modal" aria-hidden="true">
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
	<div class="modal fade" id="constitutionCWCModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been saved successfully.</span>
					</h4>
					<p style="text-align: center">
						<!-- 	          <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="onstitutionCWCSubmitData()">Print</button> -->
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reLoad()">
							<span translate>Ok</span>
						</button>
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"
							ng-click="printConstitutionOfCWCSubmitData()">
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
			<span translate>Constitution of CWC</span> <br>
		</div>
		<form class="form-horizontal basicchildform" name="constitutionOfCWC"
			id="constitutionOfCWC">
			<fieldset>
				<div class="form-group box-border-padding constitutiontop">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>1. Constitution date of the CWC</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="constitutionDate" readonly
							class="form-control" ng-disabled="cwcViewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Name of the Chairperson</span>
					</label>
					<div class="col-md-7">
						<input id="nameChairperson" maxlength='30'
							ng-model="cwcConstitution.chairpersonName" name="nameChairperson"
							placeholder="{{'Enter name of the chairperson'| translate}}"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3. Sex</span>
					</label>
					<div class="col-md-7">
						<select id="constitutionCwcData" class="form-control"
							ng-model="cwcConstitution.chairpersonSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>4. Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfJoiningCP" readonly
							class="form-control" ng-disabled="cwcViewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Contact number of the Chairperson</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="cpContactNumber" name="contactNumbber"
							ng-model="cwcConstitution.chairpersonContact"
							placeholder="{{'Enter contact number'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(cwcConstitution.chairpersonContact,'cpPhoneNoError')"
							class="form-control input-md">
						<div id="cpPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Email ID of the Chairperson</span>
					</label>
					<div class="col-md-7">
						<input id="emailChairperson"
							ng-model="cwcConstitution.chairpersonEmail"
							name="emailChairperson"
							placeholder="{{'Enter email id of the chairperson'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							maxlength="100"
							ng-keyUp="validateName(cwcConstitution.chairpersonEmail,'emailChairpersonError')">
						<div id="emailChairpersonError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7. Name of the member 1</span>
					</label>
					<div class="col-md-7">
						<input id="member1" ng-model="cwcConstitution.memberOneName"
							name="member1"
							placeholder="{{'Enter name of the member 1'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member1Data" class="form-control"
							ng-model="cwcConstitution.memberOneSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii) Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfJoiningP1" readonly
							class="form-control" ng-disabled="cwcViewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Contact number of the member 1</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="m1contactNumber" name="m1contactNumber"
							placeholder="{{'Enter contact number of the member 1'| translate}}"
							ng-model="cwcConstitution.memberOneContact"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							ng-keyUp="validateName(cwcConstitution.memberOneContact,'m1PhoneNoError')"
							oninput="setCustomValidity('')" class="form-control input-md">
						<div id="m1PhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the member 1</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson" ng-model="cwcConstitution.memberOneEmail"
							name="emailperson"
							placeholder="{{'Enter email id of the member 1'| translate}}"
							class="form-control input-md" maxlength="100" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(cwcConstitution.memberOneEmail,'emailMemberoneError')">
						<div id="emailMemberoneError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Name of the member 2</span>
					</label>
					<div class="col-md-7">
						<input id="member1" ng-model="cwcConstitution.memberTwoName"
							name="member1"
							placeholder="{{'Enter name of the member 2'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member2Data" class="form-control"
							ng-model="cwcConstitution.memberTwoSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii) Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfJoiningP2" readonly
							class="form-control" ng-disabled="cwcViewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Contact number of the member 2</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="m2contactNumber" name="m2contactNumber"
							placeholder="{{'Enter contact number of the member 2'| translate}}"
							ng-model="cwcConstitution.memberTwoContact"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(cwcConstitution.memberTwoContact,'m2PhoneNoError')"
							class="form-control input-md">
						<div id="m2PhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the member 2</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson1" ng-model="cwcConstitution.memberTwoEmail"
							name="emailperson1"
							placeholder="{{'Enter email id of the member 2'| translate}}"
							class="form-control input-md" maxlength="100" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(cwcConstitution.memberTwoEmail,'emailMembertwoError')">
						<div id="emailMembertwoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9. Name of the member 3</span>
					</label>
					<div class="col-md-7">
						<input id="member1" ng-model="cwcConstitution.memberThreeName"
							name="member1"
							placeholder="{{'Enter name of the member 3'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member3Data" class="form-control"
							ng-model="cwcConstitution.memberThreeSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii) Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfJoiningP3" readonly
							class="form-control" ng-disabled="cwcViewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Contact number of the member 3</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="m3contactNumber" name="m3contactNumber"
							placeholder="{{'Enter contact number of the member 3'| translate}}"
							ng-model="cwcConstitution.memberThreeContact"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(cwcConstitution.memberThreeContact,'m3PhoneNoError')"
							class="form-control input-md">
						<div id="m3PhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the member 3</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson3" name="emailperson3"
							ng-model="cwcConstitution.memberThreeEmail"
							placeholder="{{'Enter email id of the member 3'| translate}}"
							class="form-control input-md" maxlength="100" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(cwcConstitution.memberThreeEmail,'emailMemberthreeError')">
						<div id="emailMemberthreeError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10. Name of the member 4</span>
					</label>
					<div class="col-md-7">
						<input id="member4" ng-model="cwcConstitution.memberFourName"
							name="member4"
							placeholder="{{'Enter name of the member 4'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i) Sex</span>
					</label>
					<div class="col-md-7">
						<select id="member4Data" class="form-control"
							ng-model="cwcConstitution.memberFourSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii) Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfJoiningP4" readonly
							class="form-control" ng-disabled="cwcViewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Contact number of the member 4 </span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="m4contactNumber" name="m4contactNumber"
							placeholder="{{'Enter contact number of the member 4'| translate}}"
							ng-model="cwcConstitution.memberFourContact"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(cwcConstitution.memberFourContact,'m4PhoneNoError')"
							class="form-control input-md">
						<div id="m4PhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the member 4</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson4"
							ng-model="cwcConstitution.memberFourEmail" name="emailperson4"
							placeholder="{{'Enter email id of the member 4'| translate}}"
							class="form-control input-md" maxlength="100" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(cwcConstitution.memberFourEmail,'emailMemberfourError')">
						<div id="emailMemberfourError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>11. Name of the data entry operator</span>
					</label>
					<div class="col-md-7">
						<input id="DataentryOperator" ng-model="cwcConstitution.dEOName"
							name="DataentryOperator"
							placeholder="{{'Enter name of the data entry operator'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(i) Sex</span> </label>
					<div class="col-md-7">
						<select id="DEOsex" class="form-control"
							ng-model="cwcConstitution.dEOSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii) Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfJoiningDEO" readonly
							class="form-control" ng-disabled="cwcViewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Contact number of the data entry operator</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="deoContactNumber"
							name="deoContactNumber"
							placeholder="{{'Enter contact number of the data entry operator'| translate}}"
							ng-model="cwcConstitution.dEOContact"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(cwcConstitution.dEOContact,'deoPhoneNoError')"
							class="form-control input-md">
						<div id="deoPhoneNoError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the data entry operator</span>
					</label>
					<div class="col-md-7">
						<input id="DataentryOperatoremail"
							ng-model="cwcConstitution.dEOEmail" name="DataentryOperatoremail"
							placeholder="{{'Enter email id of the data entry operator'| translate}}"
							class="form-control input-md" maxlength="100" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(cwcConstitution.dEOEmail,'emailMemberdeoError')">
						<div id="emailMemberdeoError" class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<button class="btn btn-info"
						ng-click="constitutionOfCWC.$invalid ? '' : validateCWCForm()">
						<span translate>Submit</span>
					</button>
					<!-- 						<button id="button2id" name="button2id" class="btn btn-info" ng-if="caseSummaryDisable" -->
					<!-- 						type="submit" ng-click="printCaseSummarySubmitData()">Print</button> -->
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
<script type="text/javascript">		$(document).ready(function() {		$('input').blur(function() {		    var value = $.trim( $(this).val() );		    $(this).val( value );		});		$('textarea').blur(function() {		    var value = $.trim( $(this).val() );		    $(this).val( value );		});		});	</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
	var="jQueryUiCore" />
<script src="${jQueryUiCore}"></script>