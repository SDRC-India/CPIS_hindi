<div ng-controller="ConstitutionOfSJPUController" ng-cloak
	id="constitutionofSJPUBody">
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForconstitutionofSJPU"
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
	<div class="modal fade" id="constitutionofSJPUModal" tabindex="-1"
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
							ng-click="printConstitutionOfSJPUSubmitData()">
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
			<span translate>Constitution of SJPU</span> <br>
		</div>
		<form class="form-horizontal basicchildform" name="constitutionofSJPU"
			id="constitutionofJJB">
			<fieldset>
				<div class="form-group box-border-padding constitutiontop">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>1. Constitution date of the SJPU</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="sjpuDate" ng-model="sjpuformInfo.sjpuDate"
							readonly class="form-control" ng-disabled="viewPage">
						<div id="constitutiondateoftheSJPUError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Name of the Dy. SP (HQ)</span>
					</label>
					<div class="col-md-7">
						<input id="nameSPHQ" name="nameSPHQ"
							ng-model="sjpuformInfo.spName"
							placeholder="{{'Enter name of the Dy. SP (HQ)'| translate}}"
							class="form-control input-md" type="text"
							maxlength='30'>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3. Sex</span>
					</label>
					<div class="col-md-7">
						<select id="constitutionSJPUData" class="form-control"
							ng-model="sjpuformInfo.spSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sjpuSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>4. Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="sjpuJoiningDate"
							ng-model="sjpuformInfo.joiningDate" readonly class="form-control"
							ng-disabled="viewPage">
						<div id="constitutiondateoftheCWCError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Contact number of the Dy.SP (HQ)</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbber" name="contactNumbber"
							ng-model="sjpuformInfo.spContactNo"
							placeholder="{{'Enter contact number of the Dy.SP (HQ)'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(sjpuformInfo.spContactNo,'spPhoneNoError1')"
							class="form-control input-md">
						<div id="spPhoneNoError1" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Email ID of the Dy.SP (HQ)</span>
					</label>
					<div class="col-md-7">
						<input id="emailSPHQ" name="emailSPHQ"
							ng-model="sjpuformInfo.spEmailId" maxlength="100"
							placeholder="{{'Enter email id of the Dy.SP (HQ)'| translate}}"
							class="form-control input-md" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(sjpuformInfo.spEmailId,'emailspError')">
						<div id="emailspError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7. Name of the social worker 1</span>
					</label>
					<div class="col-md-7">
						<input id="SocialWorker1" name="SocialWorker1"
							ng-model="sjpuformInfo.socialWorkerOneName"
							placeholder="{{'Enter name of the social worker 1'| translate}}"
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
							ng-model="sjpuformInfo.socialWorkerOneSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sjpuSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii) Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="sjpuSocialWorkerOneJoiningDate"
							ng-model="sjpuformInfo.socialWorkerOneJoiningDate" readonly
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
						<input only-ten-digits type="text"
							id="contactNumberSocialWorkerdata1"
							name="contactNumberSocialWorkerdata1"
							ng-model="sjpuformInfo.socialWorkerOneContactNo"
							placeholder="{{'Enter contact number of social worker 1'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the person')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(sjpuformInfo.socialWorkerOneContactNo,'spPhoneNoError2')"
							class="form-control input-md">
						<div id="spPhoneNoError2" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the social worker 1</span>
					</label>
					<div class="col-md-7">
						<input id="emailpersonworker1" name="emailpersonworker1"
							ng-model="sjpuformInfo.socialWorkerOneEmailId"
							placeholder="{{'Enter email id of the social worker 1'| translate}}"
							class="form-control input-md" maxlength="100" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(sjpuformInfo.socialWorkerOneEmailId,'emailsocialworkeroneError')">
						<div id="emailsocialworkeroneError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Name of the social worker 2</span>
					</label>
					<div class="col-md-7">
						<input id="socialWorker2" name="socialWorker2"
							placeholder="{{'Enter name of social worker 2'| translate}}"
							ng-model="sjpuformInfo.socialWorkerTwoName"
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
							ng-model="sjpuformInfo.socialWorkerTwoSex.id"
							oninvalid="this.setCustomValidity('Select ')"
							oninput="setCustomValidity('')">
							<option value="" disabled selected>{{'Select Sex' | translate}}</option>
							<option ng-repeat="item in sjpuSex" ng-value="{{item.id}}">{{lang=='en'?item.name:item.typeNameHindi}}</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii) Date of joining</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="sjpuSocialWorkerTwoJoiningDate"
							ng-model="sjpuformInfo.socialWorkerTwoJoiningDate" readonly
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
						translate> social worker 2</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="contactNumbersocialWorker2"
							name="contactNumbersocialWorker2"
							ng-model="sjpuformInfo.socialWorkerTwoContactNo"
							placeholder="{{'Enter contact number social worker 2'| translate}}"
							oninvalid="this.setCustomValidity('Please enter contact no. of the social worker')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateName(sjpuformInfo.socialWorkerTwoContactNo,'spPhoneNoError3')"
							class="form-control input-md">
						<div id="spPhoneNoError3" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv) Email ID of the social worker 2</span>
					</label>
					<div class="col-md-7">
						<input id="emailperson1" name="emailperson1"
							ng-model="sjpuformInfo.socialWorkerTwoEmailId"
							placeholder="{{'Enter email id of the social worker 2'| translate}}"
							class="form-control input-md" maxlength="100" type="text"
							ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
							ng-keyUp="validateName(sjpuformInfo.socialWorkerTwoEmailId,'emailsocialworkertwoError')">
						<div id=emailsocialworkertwoError class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<button id="buttonid" name="button1id" class="btn btn-info"
						ng-click="constitutionofSJPU.$invalid ? '' :validateForm()"
						type="submit">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-if="caseSummaryDisable" type="submit"
						ng-click="printCaseSummarySubmitData()">
						<span translate>Print</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						type="reset"><span translate>RESET</span></button>
				</div>
			</fieldset>
		</form>
		<div class="modal fade" id="errorImageModal" tabindex="-1"
			role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p style="text-align: center">
							<span translate>Uploaded file is not in correct format.</span><br>
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