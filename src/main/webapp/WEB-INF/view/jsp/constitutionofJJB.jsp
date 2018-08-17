<div ng-controller="ConstitutionOfJJBController" ng-cloak
	id="ConstitutionOfJJBController">
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForconstitutionofJJB"
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
							data-dismiss="modal" aria-hidden="true"><span translate>Back</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="constitutionofJJBModal" tabindex="-1"
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
							ng-click="printConstitutionOfJJBSubmitData()">
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
			<span translate>Constitution of JJB</span> <br>
		</div>
		<form class="form-horizontal basicchildform" name="constitutionofJJB"
			id="constitutionofJJB">
			<fieldset>
				<div class="form-group box-border-padding constitutiontop">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>1. Constitution date of the JJB</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="jjbDate" ng-model="jjbformInfo.jjbDate"
							readonly class="form-control" ng-disabled="viewPage">
						<div id="constitutiondateofthejjbError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Name of the Principal Magistrate/Chief Judicial Magistrate</span>
					</label>
					<div class="col-md-7">
						<input id="namePrinciple" name="namePrinciple"
							ng-model="jjbformInfo.magistrateName"
							placeholder="{{'Enter name of the Principle Magistrate/Chief Judicial Magistrate'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3. Sex</span>
					</label>
					<div class="col-md-7">
						<select id="constitutionjjbData" class="form-control"
							ng-model="jjbformInfo.magistrateSex.id"
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
						<input type="text" id="JoiningDate"
							ng-model="jjbformInfo.joiningDate" readonly class="form-control"
							ng-disabled="viewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Contact number of Principle Magistrate/Chief Judicial Magistrate</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbberMagistrate"
							name="contactNumbberMagistrate"
							ng-model="jjbformInfo.magistrateContactNo"
							placeholder="{{'Enter contact number of principle magistrate/chief judicial magistrate'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(jjbformInfo.magistrateContactNo,'phoneNoError1')"
							class="form-control input-md">
						<div id="phoneNoError1" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">
					<span translate>6. Email ID of the Principle Magistrate/Chief Judicial Magistrate</span>
					</label>
					<div class="col-md-7">
						<input id="emailPrinciple" name="emailPrinciple"
							ng-model="jjbformInfo.magistrateEmailId" maxlength="100"
							placeholder="{{'Enter email id of the principle magistrate/chief judicial magistrate'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(jjbformInfo.magistrateEmailId,'emailmagistrateError')">
						<div id="emailmagistrateError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7. Name of the social worker 1</span>
					</label>
					<div class="col-md-7">
						<input id="SocialWorker1" name="SocialWorker1"
							placeholder="{{'Enter name of the social worker 1'| translate}}"
							ng-model="jjbformInfo.socialWorkerOneName"
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
							ng-model="jjbformInfo.socialWorkerOneSex.id"
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
						<input type="text" id="socialWorkerOneJoiningDate"
							ng-model="jjbformInfo.socialWorkerOneJoiningDate" readonly
							class="form-control" ng-disabled="viewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Contact number of the</span> <br /> <span
						translate> social worker 1</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbberSocialWorkerdata1"
							name="contactNumbberSocialWorkerdata1"
							placeholder="{{'Enter contact number of social worker 1'| translate}}"
							ng-model="jjbformInfo.socialWorkerOneContactNo"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(jjbformInfo.socialWorkerOneContactNo,'phoneNoError2')"
							class="form-control input-md">
						<div id="phoneNoError2" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the social worker 1</span>
					</label>
					<div class="col-md-7">
						<input id="emailpersonworker1" name="emailpersonworker1"
							ng-model="jjbformInfo.socialWorkerOneEmailId" maxlength="100"
							placeholder="{{'Enter email id of the social worker 1'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(jjbformInfo.socialWorkerOneEmailId,'emailsocialoneError1')">
						<div id="emailsocialoneError1" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Name of the social worker 2</span>
					</label>
					<div class="col-md-7">
						<input id="socialWorker2" name="socialWorker2"
							placeholder="{{'Enter name of social worker 2'| translate}}"
							ng-model="jjbformInfo.socialWorkerTwoName"
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
							ng-model="jjbformInfo.socialWorkerTwoSex.id"
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
						<input type="text" id="socialWorkerTwoJoiningDate"
							ng-model="jjbformInfo.socialWorkerTwoJoiningDate" readonly
							class="form-control" ng-disabled="viewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii) Contact number of the </span> <br /> <span
						translate> social worker 2 </span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbbersocialWorker2"
							name="contactNumbbersocialWorker2"
							ng-model="jjbformInfo.socialWorkerTwoContactNo"
							placeholder="{{'Enter contact number of the social worker 2'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the social worker')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(jjbformInfo.socialWorkerTwoContactNo,'phoneNoError3')"
							class="form-control input-md">
						<div id="phoneNoError3" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the social worker 2</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson1" name="emailperson1"
							ng-model="jjbformInfo.socialWorkerTwoEmailId" maxlength="100"
							placeholder="{{'Enter email id of the social worker 2'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(jjbformInfo.socialWorkerTwoEmailId,'emailsocialtwoError2')">
						<div id="emailsocialtwoError2" class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<button id="buttonid" name="button1id" class="btn btn-info"
						ng-click="constitutionofJJB.$invalid ? '' :validateForm()"
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
	var="jQueryUiCore" />
<script src="${jQueryUiCore}"></script>
<script type="text/javascript" src="resources/js/download.js"></script>