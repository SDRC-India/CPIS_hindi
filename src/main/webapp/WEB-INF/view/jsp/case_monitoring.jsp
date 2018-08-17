<div id="caseMonitoring" ng-controller="CaseMonitoringController"
	ng-cloak>
	<div class="modal fade" id="confirmationModalForCaseMonitoring"
		tabindex="-1" role="dialog">
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
							class="btn btn-info casemodalSubmit" type="submit"
							ng-click="saveData()" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Submit</span></button>
						<button id="button4id" name="button4id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true"><span translate>Back</span></button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="caseMonitoringModal" tabindex="-1"
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
							aria-hidden="true" ng-click="printCaseMonitoringSubmitData()"><span translate>Print</span></button>
						<button id="button5id" name="button5id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="reDirect()"><span translate>Ok</span></button>
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
			<span translate>Case monitoring sheet for committee</span><br>
			<span translate>FORM 26</span><br><span translate>[Rule 20(1)]</span>
		</div>
		<form class="form-horizontal basicchildform" name="casemonitor"
			method="post" id="casemonitor">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>Case monitoring sheet</span>
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
							<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{prefetchData.childName}}</span>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>1.Child Welfare Committee, District </span></label>
					<div class="col-md-7">
						<input id="childwc" name="childwc"
							placeholder="{{'Enter name of the District'| translate}}"
							class="form-control input-md" type="text"
							ng-model="prefetchData.cwc.name" readonly
							oninvalid="this.setCustomValidity('Input name of the District')"
							oninput="setCustomValidity('')">
						<div id="childwcerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>2.Case No</span> </label>
					<div class="col-md-7">
						<input id="newcaseno" name="newcaseno"
							placeholder="{{'Enter the case number and date'| translate}}"
							class="form-control input-md" type="text"
							ng-model="prefetchData.caseNum" readonly
							oninvalid="this.setCustomValidity('Input the case number and date')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateFields(formInfo.caseNo,'casenoerror')">
						<div id="casenoerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>3.Case Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="newcasename" name="newcasename"
							placeholder="{{'Enter the name of the case'| translate}}"
							class="form-control input-md" maxlength="30"
							type="text" ng-model="formInfo.caseName" required
							oninvalid="this.setCustomValidity('Input the name of the case')"
							oninput="setCustomValidity('')"
							ng-keyUp="validateFields(formInfo.caseName,'casenameerror')"
							ng-disabled="caseMonitoringDisable">
						<div id="casenameerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>4.Police Station</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="ps" name="ps" required
							placeholder="{{'Enter the name of the police station where case registered'| translate}}"
							class="form-control input-md" type="text" maxlength="30"
							ng-model="formInfo.psName" ng-disabled="caseMonitoringDisable">
						<div id="pserror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>5.Date</span> <span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" name="date" id="date" ng-model="formInfo.date"
							readonly class="form-control date1"
							ng-disabled="caseMonitoringDisable">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>6.U/S</span></label>
					<div class="col-md-7">
						<input id="us" name="us"
							placeholder="{{'Enter the sections under which the child is booked'| translate}}"
							class="form-control input-md" type="text" maxlength="30"
							ng-model="formInfo.sectionsChildBooked"
							ng-disabled="caseMonitoringDisable">
						<div id="userror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>7.FIR/ GD/ DD No</span></label>
					<div class="col-md-7">
						<input id="fir" name="fir"
							placeholder="{{'Enter the FIR/GD/DD No.'| translate}}"
							class="form-control input-md" type="text" maxlength="15"
							ng-model="formInfo.firGdDdNo" ng-disabled="caseMonitoringDisable">
						<div id="firerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>8.Name of Probation Officer</span> <span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="npo" name="npo" required maxlength="30"
							placeholder="{{'Enter the name of Probation Officer'| translate}}"
							class="form-control input-md" type="text"
							ng-model="formInfo.probationOfficerName"
							ng-keyUp="validateFields(formInfo.npo,'npoerror')"
							ng-disabled="caseMonitoringDisable">
						<div id="npoerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>9.Name of IO </span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="io" name="io" maxlength="30"
							placeholder="{{'Enter the name of the IO'| translate}}" required
							class="form-control input-md" type="text"
							ng-model="formInfo.ioName"
							ng-keyUp="validateFields(formInfo.io,'ioerror')"
							ng-disabled="caseMonitoringDisable">
						<div id="ioerror" class="error-style"></div>
					</div>
				</div>
				<div class="grey-header">
					<span translate>10. Particulars of child</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(i)Name </span></label>
					<div class="col-md-7">
<!-- 					ng-pattern="/^[a-zA-Z\s]*$/" -->
						<input id="uname" name="uname"
							placeholder="{{'Enter name of the child'| translate}}"
							class="form-control input-md" type="text" readonly
							ng-model="prefetchData.childName"
							ng-keyUp="validateFields(formInfo.uname,'usererror')"
							ng-disabled="caseMonitoringDisable">
						<div id="usererror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(ii)Parent/Guardian Name </span><span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="pgName" name="pgName" required maxlength="30"
							placeholder="{{'Enter name of parent/guardian'| translate}}"
							class="form-control input-md" type="text"
							ng-model="formInfo.pgName"
							ng-keyUp="validateFields(formInfo.pgcontact,'pgNameError')"
							ng-disabled="caseMonitoringDisable">
						<div id="pgNameError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(iii)Parent/Guardian Contact No.</span></label>
					<div class="col-md-7">
						<input id="pgcontact" name="pgContactNo" only-ten-digits
							placeholder="{{'Enter parent/guardian contact no.'| translate}}"
							class="form-control input-md" ng-model="formInfo.pgContactNo"
							ng-keyUp="validateFields(formInfo.pgContactNo,'pgContactError')"
							ng-disabled="caseMonitoringDisable">
						<div id="pgContactError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(iv)Present address </span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea id="prAddress" name="prAddress" required
							placeholder="{{'Enter present address of the child'| translate}}"
							class="form-control input-md" type="text" maxlength="100"
							ng-model="formInfo.childPresentAddress"
							ng-keyUp="validateFields(formInfo.personProducingChildAddress,'prAddresserror')"
							ng-disabled="caseMonitoringDisable"></textarea>
						<div id="prAddresserror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(v)Permanent address </span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea id="peAddress" name="peAddress" required
							placeholder="{{'Enter permanent address of the child'| translate}}"
							class="form-control input-md" type="text" maxlength="100"
							ng-model="formInfo.childPermanentAddress"
							ng-keyUp="validateFields(formInfo.peAddress,'peAddresserror')"
							ng-disabled="caseMonitoringDisable"></textarea>
						<div id="peAddresserror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>(vi)Date when child produced before committee</span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" name="childProducedBeforeCommittee"
							id="childProducedBeforeCommittee"
							ng-model="formInfo.childProducedBeforeCommitteeDate" readonly
							class="form-control" ng-disabled="caseMonitoringDisable">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(vii)Time</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select ng-options="item as item for item in hour" ng-model="hr"
							ng-disabled="caseMonitoringDisable" required class="form-control"
							style="width: 32%; display: inline;">
							<option value="" disabled selected>HH</option>
						</select> <select ng-options="item as item for item in min"
							ng-disabled="caseMonitoringDisable" ng-model="minute" required
							class="form-control" style="width: 32%; display: inline;">
							<option value="" disabled selected>MM</option>
						</select> <select ng-options="item as item for item in ampm" ng-model="ap"
							ng-disabled="caseMonitoringDisable" required class="form-control"
							style="width: 32%; display: inline;">
							<option value="" disabled selected>AM/PM</option>
						</select>
					</div>
					<!-- 					<div class="col-md-7" ng-if="caseMonitoringDisable"> -->
					<!-- 					<input type="text" ng-model="timeOfProduction" readonly ng-disabled="caseMonitoringDisable"> -->
					<!-- 					</div> -->
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(viii)Date and time of first production </span></label>
					<div class="col-md-7">
						<input id="dateOfFirstProduction" name="dateOfFirstProduction"
							placeholder="{{'Enter Date of first production.'| translate}}"
							class="form-control input-md" type="text"
							ng-model="prefetchData.dateOfFirstProduction"
							style="width: 32%; display: inline;" readonly> <input
							id="timeOfFirstProduction" name="timeOfFirstProduction"
							placeholder="{{'Enter time of first production.'| translate}}"
							class="form-control input-md" type="text"
							ng-model="prefetchData.timeOfFirstProduction"
							style="width: 32%; display: inline;" readonly>
						<!-- 						<input id="datefproduct" name="datefproduct" -->
						<!-- 							placeholder="{{'Input the date and time the child is first produced before the Committee'| translate}}" -->
						<!-- 							class="form-control input-md" type="text" ng-model="olala" -->
						<!-- 							readonly> -->
						<!-- 						<div id="datefproducterror" class="error-style"></div> -->
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>(ix)Date of medical examination under Section 54 Cr. Pc. (if any)</span></label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="medicalExaminationDatePicker"
							ng-model="formInfo.dateOfMedicalExamination" readonly
							class="form-control" ng-disabled="caseMonitoringDisable">
						<div id="meidcalExamination" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="grey-header">
					<span translate>11. Age determination</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(i)Age on the Date of offence </span><span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<input only-two-digits id="ageondate"
							placeholder="{{'Enter age of child on the date of offence'| translate}}"
							class="form-control input-md"
							oninvalid="this.setCustomValidity('Please enter age of the child')"
							oninput="setCustomValidity('')" required
							ng-model="formInfo.ageondate"
							ng-keyUp="validateFields(formInfo.ageondate,'ageondateerror')"
							ng-blur="resetInput(formInfo,'ageondate','ageondateerror')"
							ng-disabled="caseMonitoringDisable">
						<div id="ageondateerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>(ii)Date of age Determination</span></label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input id="ageondtrmntion" name="ageondtrmntion"
							class="form-control input-md" readonly
							ng-model="formInfo.dateOfAgeDetermination"
							ng-keyUp="validateFields(formInfo.ageondtrmntion,'ageondtrmntionerror')"
							ng-disabled="caseMonitoringDisable">
						<div id="ageondtrmntionerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(iii)Time taken for age determination</span><br><span translate>(in hrs)</span></label>
					<div class="col-md-7">
						<input only-two-digits id="timetakeage" name="timetakeage"
							placeholder="{{'Enter the time taken for determining the age of the child'| translate}}"
							class="form-control input-md" type="text"
							ng-model="formInfo.timeTakenForAgeDetermination"
							ng-keyUp="validateFields(formInfo.timeTakenForAgeDetermination,'timetakeageerror')"
							ng-blur="resetText(formInfo,'timeTakenForAgeDetermination','timetakeageerror')"
							ng-disabled="caseMonitoringDisable">
						<div id="timetakeageerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(iv)Determination by</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="determinby" name="determinby" maxlength="30"
							placeholder="{{'Enter the name of determinator'| translate}}"
							class="form-control input-md" type="text"
							ng-model="formInfo.nameOfDeterminator"
							ng-keyUp="validateFields(formInfo.determinby,'determinbyerror')"
							ng-disabled="caseMonitoringDisable">
						<div id="determinbyerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(v)Committee</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="commitee" name="commitee" maxlength="30"
							placeholder="{{'Enter the name of the Committee'| translate}}"
							class="form-control input-md"
							type="text" ng-model="formInfo.ageDeterminationCommitteeName"
							ng-disabled="caseMonitoringDisable">
						<div id="commiteeerror" class="error-style"></div>
					</div>
				</div>
				<div class="grey-header">
					<span translate>12. Evidence Relied:</span></span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(i) Documents</span></label>
					<div class="col-md-2">
						<input ng-if="!caseMonitoringDisable" type="file"
							name="determineChildAge[]" id="js-upload-files" multiple
							ng-files="getReport($files,'determineChildAge')"
							ng-disabled="caseMonitoringDisable" accept=".pdf">
					</div>
					<div class="col-md-7" style="margin-left: 50px;">
						<button id="button2id" name="button2id" class="btn otherbut"
							ng-if="caseMonitoringDisable && formInfo.doc"
							ng-click="downloadImg(formInfo.doc,'Evidence Relied Document')"
							class="close">
							<span translate>Download</span>
						</button>
						<label class="col-md-7 control-label"
							ng-if="caseMonitoringDisable && !formInfo.doc"> <span
							translate>No data available </span></label>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(ii) Medical</span></label>
					<div class="col-md-2">
						<input ng-if="!caseMonitoringDisable" type="file"
							name="medicalReportsRelied[]" id="js-upload-files" multiple
							ng-files="getReport($files,'medicalReportsRelied')"
							ng-disabled="caseMonitoringDisable" accept=".pdf">
					</div>
					<div class="col-md-7" style="margin-left: 50px;">
						<button id="button2id" name="button2id" class="btn otherbut"
							ng-if="caseMonitoringDisable && formInfo.medical"
							ng-click="downloadImg(formInfo.medical,'Evidence Relied Medical')"
							class="close">
							<span translate>Download</span>
						</button>
						<label class="col-md-7 control-label"
							ng-if="caseMonitoringDisable && !formInfo.medical"><span
							translate> No data available</span> </label>
					</div>
				</div>
				<div class="grey-header">
					<span translate>13. Placement of the child</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(i)In Children's Home</span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="chilrenhome" name="chilrenhome" maxlength="30"
							placeholder="{{'Enter if sent to Children\'s Home'| translate}}"
							class="form-control input-md" type="text"
							ng-model="formInfo.childrenHomeName"
							ng-keyUp="validateFields(formInfo.chilrenhome,'chilrenhomeerror')"
							ng-disabled="caseMonitoringDisable" required>
						<div id="chilrenhomeerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(ii)Sent under supervision (Name of Institution)</span><span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="supervision" name="supervision" maxlength="30"
							placeholder="{{'Enter the name of Institution where child is sent'| translate}}"
							class="form-control input-md" type="text"
							ng-model="formInfo.supervisionInstitutionName"
							ng-keyUp="validateFields(formInfo.supervision,'supervisionerror')"
							ng-disabled="caseMonitoringDisable" required>
						<div id="supervisionerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>(iii)From</span><span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input id="from" name="form" readonly
							class="form-control input-md" type="text"
							ng-model="formInfo.dateChildSentToSupervisionInstitution"
							ng-disabled="caseMonitoringDisable">
						<div id="formerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>(iv)To</span><span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input id="to" name="to" readonly class="form-control input-md"
							type="text"
							ng-model="formInfo.dateTillChildSentToSupervisionInstitution"
							ng-disabled="caseMonitoringDisable">
						<div id="toerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="grey-header">
					<span translate>14. Progress of Enquiry</span>
				</div>
				<div class="form-group box-border-padding">
					<div class="col-md-4"></div>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<strong><span translate>Scheduled Date</span></strong><i
								class="fa fa-calendar fa-5x"
								style="font-size: 22px !important; margin-top: 3px; color: #396c5d; margin-left: 9px;"
								aria-hidden="true"></i>
						</div>
						<div class="col-md-6 text-center">
							<strong><span translate>Actual Date</span></strong><i
								class="fa fa-calendar fa-5x"
								style="font-size: 22px !important; margin-top: 3px; color: #396c5d; margin-left: 9px;"
								aria-hidden="true"></i>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(i)Age determination</span></label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="agedeterminS" name="agedeterminS" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.scheduledDateOfAgeDetermination"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.agedetermin,'agedeterminerror')"> -->
							<div id="agedeterminerrorS" class="error-style"></div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="agedeterminA" name="agedeterminA" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.actualDateOfAgeDetermination"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.agedetermin,'agedeterminerror')"> -->
							<div id="agedeterminerrorA" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii)Social Investigation Report (Form No.22)</span><span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12 ">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="socialivgS" name="socialivgS" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.scheduledDateOfSocialInvestigationReport"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.socialivg,'socialivgerror')"> -->
							<div id="socialivgerrorS" class="error-style"></div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="socialivgA" name="socialivgA" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.actualDateOfSocialInvestigationReport"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.socialivg,'socialivgerror')"> -->
							<div id="socialivgerrorA" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(iii)Submission of Report on Provisions of further investigation, if any</span></label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="submissionreportS" name="submissionreportS" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.scheduledDateOfSubmissionReportOnProvisions"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.submissionreport,'submissionreporterror')"> -->
							<div id="submissionreporterrorS" class="error-style"></div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="submissionreportA" name="submissionreportA" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.actualDateOfSubmissionReportOnProvisions"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.submissionreport,'submissionreporterror')"> -->
							<div id="submissionreporterrorA" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(iv)Statement of Child</span><span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="statechildS" name="statechildS" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.scheduledDateOfStatementOfChild"
								ng-disabled="caseMonitoringDisable">
							<div id="statechilderrorS" class="error-style"></div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="statechildA" name="statechildA" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.actualDateOfStatementOfChild"
								ng-disabled="caseMonitoringDisable">
							<div id="statechilderrorA" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(v)Individual Care Plan (In case of child in institutional care Individual Care Plan should be prepared within one month of admittance)</span></label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="indivisualcareplanS" name="indivisualcareplanS"
								readonly class="form-control input-md datePickerClass"
								type="text"
								ng-model="formInfo.scheduledDateOfIndividualCarePlan"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.indivisualcareplan,'indivisualcareplanerror')"> -->
							<div id="indivisualcareplanerrorS" class="error-style"></div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="indivisualcareplanA" name="indivisualcareplanA"
								readonly class="form-control input-md datePickerClass"
								type="text" ng-model="formInfo.actualDateOfIndividualCarePlan"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.indivisualcareplan,'indivisualcareplanerror')"> -->
							<div id="indivisualcareplanerrorA" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(vi)Dispositional (Final) Order</span><span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="dispositionalS" name="dispositionalS" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.scheduledDateOfFinalOrder"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.dispositional,'dispositionalerror')"> -->
							<div id="dispositionalerrorS" class="error-style"></div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="dispositionalA" name="dispositionalA" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.actualDateOfFinalOrder"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.dispositional,'dispositionalerror')"> -->
							<div id="dispositionalerrorA" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>(vii)Post Dispositional Review</span><span
						class="mandatory_star">&#42;</span> </label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="postdispositionalS" name="postdispositionalS" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.scheduledDateOfPostDispositionalReview"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.postdispositional,'postdispositionalerror')"> -->
							<div id="postdispositionalerrorS" class="error-style"></div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="postdispositionalA" name="postdispositionalA" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="formInfo.actualDateOfPostDispositionalReview"
								ng-disabled="caseMonitoringDisable">
							<!-- 											ng-keyUp="validateFields(formInfo.postdispositional,'postdispositionalerror')"> -->
							<div id="postdispositionalerrorA" class="error-style"></div>
						</div>
					</div>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id" class="btn btn-info"
						ng-show="caseMonitoringBtn" type="submit"
						ng-click="casemonitor.$invalid ? '' : validateForm()">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-show="!caseMonitoringBtn"
						ng-click="printCaseMonitoringSubmitData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal fade" id="errorImgModal" tabindex="-1" role="dialog">
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
	<script type="text/javascript">
		$(document).ready(function() {
			$("#datepicker1").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$("#ageondtrmntion").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$("#from").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$("#to").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$(".datePickerClass").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
		});
	</script>
	<script type="text/javascript" src="resources/js/download.js"></script>
</div>