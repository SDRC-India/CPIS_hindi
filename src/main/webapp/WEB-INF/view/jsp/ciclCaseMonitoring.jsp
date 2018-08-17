<div id="ciclCaseMonitoring" ng-cloak>
	<div class="modal fade" id="ciclCaseMonitoringModal" tabindex="-1"
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
							ng-click="saveCiclCaseMonitoringData()" class="close"
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
	<div class="modal fade" id="ciclCaseMonitoringSuccessModal"
		tabindex="-1" role="dialog">
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
							ng-click="printCaseHistoryData()">
							<span translate>Print</span>
						</button>
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">
							<span translate>Ok</span>
						</button>
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
			<span translate>CASE MONITORING SHEET</span> <br> <span translate>FORM 11</span> <br> <span translate>[Rule 12(1)]</span>
		</div>
		<form class="form-horizontal basicchildform" id="ciclCase"
			name="ciclCase">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>CASE MONITORING SHEET</span>
				</div>
				<div class="col-md-12 summaryspace">
					<div class="col-md-6 childidheader"
						style="margin-left: 0px !important; padding-left: 0px !important;">
						<div class="social_headng">
							<img src="resources/img/cpis_ccts_Child_ID_SVG.svg"
								style="width: 6%;" /> <span> <span translate>Child ID:</span>&nbsp;&nbsp;{{childId}}
							</span>
						</div>
					</div>
					<div class="col-md-6 childnameheader"
						style="margin-right: 0px !important; padding-right: 0px !important;">
						<div class="social_headng1">
							<span> <span translate>Name of Child:</span>&nbsp;&nbsp;{{childBgData.childName
								| limitTo : 30}}
							</span>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>1.District</span>
					</label>
					<div class="col-md-7">
						<input id="juvenileDistrict" name="juvenileDistrict"
							placeholder="{{'Enter name of the district'| translate}}"
							class="form-control input-md" type="text"
							ng-model="ciclCaseMonitoring.district" readonly>
						<div id="juvenileDistricterror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2.Case No</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="caseno" name="caseno"
							placeholder="{{'Enter case number'| translate}}"
							class="form-control input-md" type="text" maxlength="15"
							ng-model="ciclCaseMonitoring.caseNo" required
							oninvalid="this.setCustomValidity('Please enter case no')"
							oninput="setCustomValidity('')"
							ng-disabled="viewCaseMonitoring || freezeCaseNo">
						<div id="casenoerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3.Case Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="newcasename" name="newcasename"
							placeholder="{{'Enter the name of the case'| translate}}"
							maxlength="30"
							ng-model="ciclCaseMonitoring.caseName"
							class="form-control input-md" type="text" required
							ng-disabled="viewCaseMonitoring">
						<div id="newcasenameerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4.Police Station </span>
					</label>
					<div class="col-md-7">
						<input maxlength="30" id="ps" name="ps" readonly
							ng-model="childBgData.policeStation"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>5.Date</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" placeholder="{{'Enter date'| translate}}"
							name="date" id="date" readonly class="form-control date1"
							ng-model="ciclCaseMonitoring.date"
							ng-disabled="viewCaseMonitoring">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6.U/S</span>
					</label>
					<div class="col-md-7">
						<input id="us" name="us"
							placeholder="{{'Enter the sections under which the child is booked'| translate}}"
							class="form-control input-md" type="text" maxlength="30"
							ng-model="ciclCaseMonitoring.underSection"
							ng-disabled="viewCaseMonitoring">
						<div id="userror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7.FIR No.</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" class="form-control input-md" type="text"
							ng-model="childMstData.firNumber" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8.DD No.</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" class="form-control input-md" type="text"
							ng-model="childMstData.ddNumber" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9.GD No.</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" class="form-control input-md" type="text"
							ng-model="childMstData.gdNumber" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10.Name of Probation Officer</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="npo" name="npo" required maxlength="30"
							placeholder="{{'Enter the name of probation officer'| translate}}"
							class="form-control input-md" type="text"
							ng-model="ciclCaseMonitoring.probationOfficerName"
							ng-disabled="viewCaseMonitoring">
						<div id="npoerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>11.Name of Lawyer</span>
					</label>
					<div class="col-md-7">
						<input id="lawyer" name="lawyer"
							placeholder="{{'Enter the name of the lawyer'| translate}}"
							class="form-control input-md" type="text"
							maxlength="30"
							ng-model="ciclCaseMonitoring.lawyerName"
							ng-disabled="viewCaseMonitoring">
						<div id="ioerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>12.Name of IO</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="io" name="io"
							placeholder="{{'Enter the name of the IO'| translate}}" required
							class="form-control input-md" type="text"
							maxlength="30" ng-model="ciclCaseMonitoring.ioName"
							ng-disabled="viewCaseMonitoring">
						<div id="ioerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>13.Name of Child Welfare Police Officer</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="io" name="io"
							placeholder="{{'Enter the name of child welfare police officer'| translate}}"
							required class="form-control input-md" type="text"
							maxlength="30"
							ng-model="ciclCaseMonitoring.childWelfareOfficerName"
							ng-disabled="viewCaseMonitoring">
						<div id="ioerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>14.Nature Of Offense </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="natureofOffense" class="form-control" required
							ng-if="!viewCaseMonitoring"
							ng-options="crime as lang=='en'?crime.name:crime.typeNameHindi for crime in natureOfCrimeList"
							ng-model="ciclCaseMonitoring.natureOfOffence">
							<option value="" disabled selected translate>Select
							</option>
						</select> <input class="form-control input-md" type="text"
							ng-if="viewCaseMonitoring" readonly
							value="{{lang=='en'?ciclCaseMonitoring.natureOfOffence.name:ciclCaseMonitoring.natureOfOffence.typeNameHindi}}">
					</div>
				</div>
				<div class="grey-header">
					<span translate>15. Particulars of child</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i)Name</span>
					</label>
					<div class="col-md-7">
						<input maxlength="30" class="form-control input-md"
							type="text" ng-model="childBgData.childName" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii)Parents/ Guardian Contact No.</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input only-ten-digits id="pgcontact" name="pgContactNo" required
							placeholder="{{'Enter contact no. of parents/guardian'| translate}}"
							class="form-control input-md" ng-pattern="/^[0-9]{10,10}$/"
							ng-model="ciclCaseMonitoring.parentContactnumber"
							ng-keyUp="validateFields(ciclCaseMonitoring.parentContactnumber,'pgContactError')"
							ng-disabled="viewCaseMonitoring"
							ng-blur="resetphnNo(ciclCaseMonitoring.parentContactnumber,'pgcontact')">
						<div id="pgContactError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii)Present address </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea id="prAddress" name="prAddress" required
							placeholder="{{'Enter present address of the child'| translate}}"
							class="form-control input-md" type="text" maxlength="100"
							ng-model="ciclCaseMonitoring.presentAddress"
							ng-disabled="viewCaseMonitoring"></textarea>
						<div id="prAddresserror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv)Permanent address </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea id="peAddress" name="peAddress" required
							placeholder="{{'Enter permanent address of the child'| translate}}"
							class="form-control input-md" type="text" maxlength="100"
							ng-model="ciclCaseMonitoring.permanentAddress"
							ng-disabled="viewCaseMonitoring"></textarea>
						<div id="peAddresserror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>16.Date when child apprehended</span> 
						<span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" name="dateChildApprehended"
							id="dateChildApprehended" readonly class="form-control"
							ng-disabled="viewCaseMonitoring"
							ng-model="ciclCaseMonitoring.aprehendedDate">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>17.Time when child apprehended</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-5" ng-if="!viewCaseMonitoring">
						<select ng-options="item as item for item in hour" required
							ng-model="$parent.hr1" class="form-control"
							style="width: 32%; display: inline;">
							<option value="" disabled selected><span translate>HH</span>
							</option>
						</select> <select ng-options="item as item for item in min" required
							class="form-control" ng-model="$parent.min1"
							style="width: 32%; display: inline;">
							<option value="" disabled selected><span translate>MM</span>
							</option>
						</select> <select ng-options="item as item for item in ampm" required
							ng-model="$parent.period1" class="form-control"
							style="width: 32%; display: inline;">
							<option value="" disabled selected><span translate>AM/PM</span>
							</option>
						</select>
					</div>
					<div class="col-md-4" ng-if="viewCaseMonitoring">
						<input ng-model="apprehendedTime" class="form-control" type="text"
							readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>18.Date of first production</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input id="dateOfFirstProduction" name="dateOfFirstProduction"
							ng-model="childMstData.dateOfFirstProduction"
							class="form-control" type="text" readonly disabled>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>19.Time of first production</span>
					</label>
					<!-- 					<div class="col-md-5" ng-if="!viewCaseMonitoring"> -->
					<!-- 						<select ng-options="item as item for item in hour" required -->
					<!-- 							ng-model="$parent.hr2" -->
					<!-- 							class="form-control" style="width: 32%; display: inline;"> -->
					<!-- 							<option value="" disabled selected>HH</option> -->
					<!-- 						</select> -->
					<!-- 						<select ng-options="item as item for item in min" required -->
					<!-- 							ng-model="$parent.min2" -->
					<!-- 							class="form-control" style="width: 32%; display: inline;"> -->
					<!-- 							<option value="" disabled selected>MM</option> -->
					<!-- 						</select> -->
					<!-- 						<select ng-options="item as item for item in ampm" required -->
					<!-- 							ng-model="$parent.period2" -->
					<!-- 							class="form-control" style="width: 32%; display: inline;"> -->
					<!-- 							<option value="" disabled selected>AM/PM</option> -->
					<!-- 						</select> -->
					<!-- 					</div> -->
					<div class="col-md-4">
						<input ng-model="firstProductionTime" class="form-control"
							type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>20.Date of medical examination under Section 54 Cr. Pc. (if any)</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfMedicalExamination"
							ng-model="ciclCaseMonitoring.medicalExaminationDate" readonly
							class="form-control" ng-disabled="viewCaseMonitoring">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="grey-header">
					<span translate>21. Age determination</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i)Age on the Date of offense </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="ageondate" type="number"
							ng-model="ciclCaseMonitoring.childAgeOnDateOfOffence"
							ng-disabled="viewCaseMonitoring"
							ng-keyUp="validateFields(ciclCaseMonitoring.childAgeOnDateOfOffence,'ageondateerror')"
							placeholder="{{'Enter age of child on the date of offence'| translate}}"
							class="form-control input-md"
							ng-blur="resetInput(ciclCaseMonitoring,'childAgeOnDateOfOffence','ageondateerror')"
							required>
						<div id="ageondateerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(ii)Date of age Determination</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" name="ageDeterminationAge"
							id="ageDeterminationAge" readonly class="form-control"
							ng-disabled="viewCaseMonitoring"
							ng-model="ciclCaseMonitoring.childAgeDeterminationDate">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(iii)Time taken for age determination </span><br>(in hrs)
					
					</label>
					<div class="col-md-7">
						<input only-three-digits id="timetakeage" name="timetakeage"
							placeholder="{{'Enter the time taken for determining the age of the child'| translate}}"
							class="form-control input-md" type="text"
							ng-model="ciclCaseMonitoring.ageDeterminationTime"
							ng-disabled="viewCaseMonitoring" ng-disabled="viewCCIpending"
							ng-blur="resetText(ciclCaseMonitoring,'ageDeterminationTime','timetakeageerror')">
						<div id="timetakeageerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv)Determination by</span>
					</label>
					<div class="col-md-7">
						<select class="form-control" ng-if="!viewCaseMonitoring"
							ng-options="det as lang=='en'?det.name:det.typeNameHindi for det in determinationByList"
							ng-model="ciclCaseMonitoring.determinationBy">
							<option value="" disabled selected translate>Select determined by
							</option>
						</select> <input placeholder=""
							ng-model="ciclCaseMonitoring.determinationBy.name"
							class="form-control input-md" type="text"
							ng-if="viewCaseMonitoring" readonly>
					</div>
				</div>
				<div class="grey-header">
					<span translate>22. Evidence Relied:</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i)Documents</span>
					</label>
					<div class="col-md-7">
						<div class="col-md-3" ng-if="!viewCaseMonitoring">
							<input type="file" class="" file-model="evidenceDocument"
								name="evidenceDocument[]" id="js-upload-files" multiple
								ng-files="getReport($files)" accept=".pdf">
						</div>
						<div>
							<button class="col-md-3" type="button"
								class="btn btn-sm btn-primary" id="hsrStudyButton"
								style="margin-left: 10px; margin-top: 10px;"
								ng-if="viewCaseMonitoring && ciclCaseMonitoring.evidenceReliedDocument"
								ng-click="downloadPdf(ciclCaseMonitoring.evidenceReliedDocument,'MedicalEvidenceRelied')">Download</button>
							<label class="col-md-7 control-label"
								ng-if="viewCaseMonitoring && !ciclCaseMonitoring.evidenceReliedDocument">
								<span translate>Data not available</span>
							</label>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label"> <span translate>(ii)Medical</span>
					</label>
					<div class="col-md-7">
						<input id="medicalName" name="medicalName"
							maxlength="60"
							placeholder="{{'Enter medical name'| translate}}"
							ng-disabled="viewCaseMonitoring" class="form-control input-md"
							type="text"
							ng-model="ciclCaseMonitoring.evidenceReliedMedicalName">
						<div id="medicalNameError" class="error-style"></div>
					</div>
				</div>
				<div class="grey-header">
					<span translate>23. Custody of The Child</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(i)In</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="previousData" class="form-control" required
							ng-if="!viewCaseMonitoring"
							ng-options="custody as lang=='en'?custody.name:custody.typeNameHindi for custody in childInCustodyOfList"
							ng-model="ciclCaseMonitoring.cocByWhom">
							<option value="" disabled selected translate>Select
							</option>
						</select> <input ng-model="ciclCaseMonitoring.cocByWhom.name"
							ng-if="viewCaseMonitoring" placeholder=""
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding"
					ng-if="ciclCaseMonitoring.cocByWhom">
					<label class="col-md-4 control-label"> 
					<span translate>(ii) Name of Observation Home/ Place of Safety/ Others</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input maxlength="30" id="obsrvtnName"
							name="obsrvtnName" ng-model="ciclCaseMonitoring.cocByWhomName"
							placeholder="{{'Enter name of observation home/ place of safety/ others'| translate}}"
							required class="form-control input-md" type="text"
							ng-disabled="viewCaseMonitoring"
							ng-blur="blur(ciclCaseMonitoring.cocByWhomName,'cocByWhomName')"
							ng-trim="false">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<div class="col-md-4"></div>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<strong> <span translate>From</span>
							</strong> <i class="fa fa-calendar fa-5x"
								style="font-size: 22px !important; margin-top: 3px; color: #396c5d; margin-left: 9px;"
								aria-hidden="true"></i>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<strong> <span translate>To</span>
							</strong> <i class="fa fa-calendar fa-5x"
								style="font-size: 22px !important; margin-top: 3px; color: #396c5d; margin-left: 9px;"
								aria-hidden="true"></i>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii)</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12"
						style="margin-bottom: -14px;">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="cocFromDate" name="cocFromDate"
								placeholder="{{'Enter date'| translate}}" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.cocFromDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="cocToDate" name="cocToDate"
								placeholder="{{'Enter date'| translate}}" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.cocToDate"
								ng-disabled="viewCaseMonitoring">
							<div id="ciclToerror" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>(iv).Date of grant of bail</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfGrantOfBail" readonly
							class="form-control" ng-model="ciclCaseMonitoring.cocBailDate"
							ng-disabled="viewCaseMonitoring">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(v)Sent under supervision(Name of Institution)</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="to" name="to" required
							placeholder="{{'Enter name of the institution'| translate}}"
							class="form-control input-md" type="text"
							maxlength="30"
							ng-model="ciclCaseMonitoring.supervision_institution_name"
							ng-disabled="viewCaseMonitoring">
						<div id="toerror" class="error-style"></div>
					</div>
				</div>
				<div class="grey-header">
					<span translate>24. Progress of Enquiry</span>
				</div>
				<div class="form-group box-border-padding">
					<div class="col-md-4"></div>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<strong> <span translate>Scheduled Date</span>
							</strong> <i class="fa fa-calendar fa-5x"
								style="font-size: 22px !important; margin-top: 3px; color: #396c5d; margin-left: 9px;"
								aria-hidden="true"></i>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<strong> <span translate>Actual Date</span>
							</strong> <i class="fa fa-calendar fa-5x"
								style="font-size: 22px !important; margin-top: 3px; color: #396c5d; margin-left: 9px;"
								aria-hidden="true"></i>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(i)Day 1: Social Background Report by Police (in Form No. 1)</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="socialBackgroundScheduledDate"
								name="socialBackgroundScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.socialBackgroundScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="socialBackgroundActualDate"
								name="socialBackgroundActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.socialBackgroundActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(ii)Day 1: Consideration of Bail</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="bailConsiderationScheduledDate"
								name="bailConsiderationScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.bailConsiderationScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="bailConsiderationActualDate"
								name="bailConsiderationActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.bailConsiderationActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iii)Day 2: Age determination</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="ageDeterminationScheduledDate"
								name="ageDeterminationScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.ageDeterminationScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="ageDeterminationActualDate"
								name="ageDeterminationActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.ageDeterminationActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(iv)Day 2: SIR (Form No.6) by Probation Officer</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="sirByProbationOfficerScheduledDate"
								name="sirByProbationOfficerScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.sirByProbationOfficerScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="sirByProbationOfficerActualDate"
								name="sirByProbationOfficerActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.sirByProbationOfficerActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(v)Day 2: Section 173 CrPC Final Report by Police on completion of Investigation</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="crpc173ReportScheduledDate"
								name="crpc173ReportScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.crpc173ReportScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="crpc173ReportActualDate"
								name="crpc173ReportActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.crpc173ReportActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(vi)Day 3: Submission of Report on Provisions of further investigation, if any</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="reportsOnProvisionScheduledDate"
								name="reportsOnProvisionScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.reportsOnProvisionScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="reportsOnProvisionActualDate"
								name="reportsOnProvisionActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.reportsOnProvisionActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(vii)Day 3: Section 251 CrPC Notice</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="crpc251ReportScheduledDate"
								name="crpc251ReportScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.crpc251ReportScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="crpc251ReportActualDate"
								name="crpc251ReportActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.crpc251ReportActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(viii)Day 4-6: Prosecution Evidence</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidenceFrom"
								name="prosecutionEvidenceFrom"
								placeholder="{{'From'| translate}}" readonly
								ng-model="ciclCaseMonitoring.prosecutionEvidienceFromDate"
								class="form-control input-md datePickerClass" type="text"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidenceTo" name="prosecutionEvidenceTo"
								placeholder="{{'To'| translate}}" readonly
								ng-model="ciclCaseMonitoring.prosecutionEvidienceToDate"
								class="form-control input-md datePickerClass" type="text"
								ng-disabled="viewCaseMonitoring">
							<div id="prosecutionEvidenceToerror" class="error-style"></div>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>a.</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidienceScheduledDate"
								name="prosecutionEvidienceScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.prosecutionEvidienceScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidienceActualDate"
								name="prosecutionEvidienceActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.prosecutionEvidienceActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>b.</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidienceScheduledDate1"
								name="prosecutionEvidienceScheduledDate1" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.prosecutionEvidienceScheduledDate1"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidienceActualDate1"
								name="prosecutionEvidienceActualDate1" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.prosecutionEvidienceActualDate1"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>c.</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidienceScheduledDate2"
								name="prosecutionEvidienceScheduledDate2" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.prosecutionEvidienceScheduledDate2"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="prosecutionEvidienceActualDate2"
								name="prosecutionEvidienceActualDate2" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.prosecutionEvidienceActualDate2"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(ix)Day 7: Statement of child under Section 281 CrPC</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="crpc281ReportScheduledDate"
								name="crpc281ReportScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.crpc281ReportScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="crpc281ReportActualDate"
								name="crpc281ReportActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.crpc281ReportActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(x)Day 8: Defence Evidence</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="defenceEvidienceScheduledDate"
								name="defenceEvidienceScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.defenceEvidienceScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="defenceEvidienceActualDate"
								name="defenceEvidienceActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.defenceEvidienceActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(xi)Day 8: Individual Care Plan (In case of child in institutional care Individual Care Plan should be prepared within one month of admittance</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="carePlanScheduledDate" name="carePlanScheduledDate"
								readonly class="form-control input-md datePickerClass"
								type="text" ng-model="ciclCaseMonitoring.carePlanScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="carePlanActualDate" name="carePlanActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.carePlanActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(xii)Day 9: Final Arguments</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="finalArgumentsScheduledDate"
								name="finalArgumentsScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.finalArgumentsScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="finalArgumentsActualDate"
								name="finalArgumentsActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.finalArgumentsActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(xiii)Day 10: Dispositional (Final) Order</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="dispositionalOrderScheduledDate"
								name="dispositionalOrderScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.dispositionalOrderScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="dispositionalOrderActualDate"
								name="dispositionalOrderActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.dispositionalOrderActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>(xiv)Day 11: Post Dispositional Review</span>
					</label>
					<div class="col-md-7 col-sm-12 col-xs-12">
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="postDispositionalReviewScheduledDate"
								name="postDispositionalReviewScheduledDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.postDispositionalReviewScheduledDate"
								ng-disabled="viewCaseMonitoring">
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 text-center">
							<input id="postDispositionalReviewActualDate"
								name="postDispositionalReviewActualDate" readonly
								class="form-control input-md datePickerClass" type="text"
								ng-model="ciclCaseMonitoring.postDispositionalReviewActualDate"
								ng-disabled="viewCaseMonitoring">
						</div>
					</div>
				</div>
				<div style="text-align: center; margin-top: 27px;">
					<button id="button1id" name="button1id" class="btn btn-info"
						ng-if="!viewCaseMonitoring" type="submit"
						ng-click="ciclCase.$invalid ? '' : validateCiclCaseMonitoringForm()">
						<span translate>Submit</span>
					</button>
					<button id="button1id" name="button1id" class="btn btn-info"
						ng-if="viewCaseMonitoring" type="submit"
						ng-click="printCaseHistoryData()">
						<span translate>Print</span>
					</button>
				</div>
				<a href="#" class="back-to-top" style="display: inline;"> <i
					class="fa fa-arrow-circle-up"></i>
				</a>
			</fieldset>
		</form>
	</div>
</div>
<script>		
// $(document).ready(function() {
	$("#cocFromDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#cocToDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#dateOfFirstProduction").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'			});
	$("#dateChildApprehended").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'			});
	$("#dateOfMedicalExamination").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'			});
	$("#ageDeterminationAge").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'			});
	$("#dateOfGrantOfBail").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'			});
	$("#socialBackgroundScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#socialBackgroundActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#bailConsiderationScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",	
		maxDate : '+0d'			});
	$("#bailConsiderationActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#ageDeterminationScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#ageDeterminationActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#sirByProbationOfficerScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#sirByProbationOfficerActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'	
		});	
	$("#crpc173ReportScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});			
	$("#crpc173ReportActualDate").datepicker({	
		dateFormat : "yy-mm-dd",	
		maxDate : '+0d'			});	
	$("#reportsOnProvisionScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'	
		});
	$("#reportsOnProvisionActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#crpc251ReportScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#crpc251ReportActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#crpc281ReportScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#crpc281ReportActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'			});
	$("#defenceEvidienceScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#defenceEvidienceActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#carePlanScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#carePlanActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#finalArgumentsScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#finalArgumentsActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#dispositionalOrderScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#dispositionalOrderActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'	
		});	
	$("#postDispositionalReviewScheduledDate").datepicker({	
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#postDispositionalReviewActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#prosecutionEvidenceFrom").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#prosecutionEvidenceTo").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#prosecutionEvidienceScheduledDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#prosecutionEvidienceActualDate").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#prosecutionEvidienceScheduledDate1").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
	$("#prosecutionEvidienceActualDate1").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'	
		});
	$("#prosecutionEvidienceScheduledDate2").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});	
	$("#prosecutionEvidienceActualDate2").datepicker({
		dateFormat : "yy-mm-dd",
		maxDate : '+0d'
		});
// 	});
</script>
<script type="text/javascript" src="resources/js/download.js"></script>
<script type="text/javascript">		$(document).ready(function() {			$('input').blur(function() {				var value = $.trim($(this).val());				$(this).val(value);			});			$('textarea').blur(function() {				var value = $.trim($(this).val());				$(this).val(value);			});		});	</script>
<script>		jQuery(document).ready(function(){			jQuery('.back-to-top').fadeOut();			var offset = 250;			var duration = 500;			jQuery(window).scroll(function(){				if (jQuery(this).scrollTop() > offset) {					jQuery('.back-to-top').fadeIn(duration);				}else{					jQuery('.back-to-top').fadeOut(duration);				}			});			jQuery('.back-to-top').click(function(event) {				event.preventDefault();				jQuery('html, body').animate({scrollTop: 0}, duration);				return false;			});		});	</script>