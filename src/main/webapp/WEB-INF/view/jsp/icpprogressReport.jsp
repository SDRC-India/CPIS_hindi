<div ng-controller="ProgressReportController" id="progressReportBody"
	ng-cloak>
	<div class="modal fade" id="progressReportConfirmationModal"
		tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate> Please click on the submit button to save the details. </span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id"
							class="btn btn-info casemodalSubmit" type="submit"
							ng-click="saveData()" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span translate>Submit</span>
						</button>
						<button id="button4id" name="button4id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span translate>Back</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="icpPersonDetailsBModal" tabindex="-1"
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
							aria-hidden="true" ng-click="printProgressReport()">
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
		<!-- <hr>
			<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a>
		| 
			<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
			<hr> -->
		<div class="childlist-heading1 borderPersonal">
			<span translate>INDIVIDUAL CARE PLAN</span> <br> <span translate>FORM 7</span> <br> <span translate> [Rules 11(3), 13(7)(vi),
				13(8)(ii), 19(4), 19(17), 62(6)(vii), 62(6)(x), 69 I (3)]</span> <br>
			<span translate> Child in Conflict with Law/Child in Need of Care and Protection</span>
		</div>
		<form class="form-horizontal basicchildform" name="progressReportForm"
			id="progressReportForm">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>PROGRESS REPORT OF THE CHILD</span>
				</div>
				<div class="col-md-12 summaryspace">
					<div class="col-md-6 childidheader"
						style="margin-left: 0px !important; padding-left: 0px !important;">
						<div class="social_headng">
							<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" /> <span>
								<span translate>Child ID</span> :&nbsp;&nbsp;${selectedChild}
							</span>
						</div>
					</div>
					<div class="col-md-6 childnameheader"
						style="margin-right: 0px !important; padding-right: 0px !important;">
						<div class="social_headng1">
							<span> <span translate>Name of Child</span>
								:&nbsp;&nbsp;{{prefetchData.childName}}
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
							ng-options="item as lang=='en'?item.name:item.typeNameHindi for item in designationList"
							ng-model="progressReport.typeOfOfficerOrWorker" required
							ng-if="!progressReportViewPage">
							<option value="" disabled selected>{{'Select designation' | translate}}
							</option>
						</select> <input ng-model="lang=='en'?progressReport.typeOfOfficerOrWorker.name:progressReport.typeOfOfficerOrWorker.typeNameHindi"
							placeholder="" class="form-control input-md" type="text" readonly
							ng-if="progressReportViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Name</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="probationWorkerWelfareName"
							ng-model="progressReport.nameOfOfficerOrWorker"
							ng-disabled="progressReportViewPage" maxlength="30"
							name="probationWorkerWelfareName"
							placeholder="{{'Enter Name of the Probation Officer/Case Worker/Child Welfare Officer'| translate}}"
							class="form-control input-md" type="text" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span translate> 3. Period of the report(in months) </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input only-three-digits type="text" id="periodOfReport"
							placeholder="{{'Enter period of the report'| translate}}"
							ng-model="progressReport.periodOfReport" required
							ng-disabled="progressReportViewPage" maxlength="30"
							class="form-control">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>4. Admission No.</span>
					</label>
					<div class="col-md-7">
						<input id="addmissionNo" name="addmissionNo"
							ng-model="icpAdata.admissionNum"
							placeholder="{{'Addmission No.'| translate}}"
							class="form-control input-md"
							ng-disabled="progressReportViewPage" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>5. Board or Committee</span>
					</label>
					<div class="col-md-7">
						<input id="boardCommitteeName" name="boardCommitteeName"
							placeholder="{{'Enter Name of the Board or Committee'| translate}}"
							class="form-control input-md" type="text"
							ng-model="prefetchData.cwc.name" readonly
							ng-disabled="progressReportViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>6. Profile No./Case No.</span>
					</label>
					<div class="col-md-7">
						<input id="profileNo" name="profileNo"
							placeholder="{{'Profile No.'| translate}}"
							ng-model="prefetchData.caseNum"
							ng-disabled="progressReportViewPage"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>7. Name of the child</span>
					</label>
					<div class="col-md-7">
						<input id="nameofChild" name="nameofChild"
							ng-model="prefetchData.childName"
							placeholder="{{'Enter Name of the Child'| translate}}"
							ng-disabled="progressReportViewPage"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					{{'8. Stay of the child (Fill as applicable)' | translate}} <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="stayPeriod" name="stayPeriod" class="form-control"
							required ng-model="progressReport.stayOfChild"
							ng-if="!progressReportViewPage"
							ng-options="item as lang=='en'?item.name:item.typeNameHindi for item in stayOfTheChild">
							<option value="" disabled selected>{{'Select Time Period' | translate}}</span>
							</option>
						</select> <input ng-model="lang=='en'?progressReport.stayOfChild.name:progressReport.stayOfChild.typeNameHindi" placeholder=""
							class="form-control input-md" type="text" readonly
							ng-if="progressReportViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>9. Place of interview</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="placeofInterview" name="placeofInterview" required
							ng-model="progressReport.placeOfInterview" maxlength="30"
							placeholder="{{'Enter place of the interview'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="progressReportViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>10. Date of Interview</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfInterview"
							ng-model="progressReport.dateOfInterview" readonly
							class="form-control" ng-disabled="progressReportViewPage">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="textarea"> 
					<span translate>11. General conduct and progress of the child	during the period of the report</span>
					</label>
					<div class="col-md-7">
						<textarea class="form-control" id="progressofthechildDetails"
							ng-model="progressReport.generalConductAndProgress"
							maxlength="200"
							placeholder="{{'Enter progress of the child Details during the period of the repor'| translate}}"
							ng-disabled="progressReportViewPage"></textarea>
					</div>
				</div>
				<div class="grey-header">
					<span translate>12. Progress made with regard to proposed interventions</span> <span class="mandatory_star">&#42;</span>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(i) Category 1</span> <br> <span translate>Child's expectation from care and protection</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category1areas" ng-model="icpAdata.cat1pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category1proposed" ng-model="progressReport.cat1Poc"
							required ng-disabled="progressReportViewPage" maxlength="200"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(ii) Category 2</span> <br> <span translate>
							Health and nutrition needs</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category2areas" ng-model="icpAdata.cat2pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category2proposed" ng-model="progressReport.cat2poc"
							required ng-disabled="progressReportViewPage" maxlength="190"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(iii) Category 3</span> <br> <span translate>
							Emotional and psychological support needs</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category3areas" ng-model="icpAdata.cat3pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category3proposed" ng-model="progressReport.cat3poc"
							required ng-disabled="progressReportViewPage" maxlength="190"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(iv) Category 4</span> <br> <span translate>
							Educational and Training needs</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category4areas" ng-model="icpAdata.cat4pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category4proposed" ng-model="progressReport.cat4poc"
							required ng-disabled="progressReportViewPage" maxlength="190"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(v) Category 5</span> <br> <span translate>Leisure, creativity and play</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category5areas" ng-model="icpAdata.cat5pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category5proposed" ng-model="progressReport.cat5poc"
							required ng-disabled="progressReportViewPage" maxlength="200"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(vi) Category 6</span> <br> <span translate>Attachments and Inter-personal Relationships</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category6areas" ng-model="icpAdata.cat6pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category6proposed" ng-model="progressReport.cat6poc"
							required ng-disabled="progressReportViewPage" maxlength="190"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(vii) Category 7</span> <br> 
					<span translate>Religious beliefs</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category7areas" ng-model="icpAdata.cat7pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category7proposed" ng-model="progressReport.cat7poc"
							required ng-disabled="progressReportViewPage" maxlength="190"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">
					 <span translate>(viii) Category 8</span> <br> <span translate>Self care and life skill training for Protection from all kinds of abuse, neglect and maltreatment</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category8areas" ng-model="icpAdata.cat8pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category8proposed" ng-model="progressReport.cat8poc"
							required ng-disabled="progressReportViewPage" maxlength="200"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(ix) Category 9</span> <br> <span translate>Independent living skills</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category9areas" ng-model="icpAdata.cat9pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category9proposed" ng-model="progressReport.cat9poc"
							required ng-disabled="progressReportViewPage" maxlength="200"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate>(x) Category 10</span> <br> 
							<span translate>Any other such as significant experiences which may have impacted the development of the child like trafficking, domestic violence, parental neglect, bullying in school, etc. (Please specify)</span>
					</label>
					<div class="col-md-3">
						<label class="control-label" for="textinput"> 
						<span translate>Proposed Interventions</span>
						</label> <input id="category10areas" ng-model="icpAdata.cat10pi"
							class="form-control input-md" type="text" readonly>
					</div>
					<div class="col-md-4">
						<label class="control-label" for="textinput"> 
						<span translate>Progress of the child</span>
						</label> <input id="category10proposed" ng-model="progressReport.cat10poc"
							required ng-disabled="progressReportViewPage" maxlength="200"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate> 13. Any proceedings before the Committee or Board or Children's Court </span>
					</label>
					<div class="col-md-7">
						<select id="proceedingComm" name="proceedingComm"
							ng-if="!progressReportViewPage" class="form-control"
							ng-model="progressReport.proceedings"
							ng-options="item as lang=='en'?item.name:item.typeNameHindi for item in proceedingsBeforeCommittee">
							<option value="" disabled selected>{{'Select' | translate}}
							</option>
						</select> <input ng-model="lang=='en'?progressReport.proceedings.name:progressReport.proceedings.typeNameHindi" placeholder=""
							class="form-control input-md" type="text" readonly
							ng-if="progressReportViewPage"><br>
							<input ng-if="progressReport.proceedings.id==171" id="otherProceedings" ng-model="progressReport.otherProceedings"
							 ng-disabled="progressReportViewPage" maxlength="200" placeholder="{{'Please specify'| translate}}"
							class="form-control input-md" type="text">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>14. Period of supervision completed on</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="supervisionCompletionDate"
							ng-model="progressReport.supervisionCompletionDate" readonly
							ng-disabled="progressReportViewPage" class="form-control">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate> 15. Result of supervision with remarks (if any)</span>
					</label>
					<div class="col-md-7">
						<input id="resultSupervision"
							ng-model="progressReport.resultOfSupervision"
							name="resultSupervision" maxlength="200"
							placeholder="{{'Enter Result of supervision with remarks'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="progressReportViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> 
					<span translate> 16. Name and Addresses under whose care the child is to live after the supervision is over </span>
					</label>
					<div class="col-md-7">
						<select id="proceedingComm" class="form-control"
							ng-if="!progressReportViewPage"
							ng-model="progressReport.typeOfParent" name="proceedingComm"
							ng-options="item as lang=='en'?item.name:item.typeNameHindi for item in icpParentType"
							ng-change="clearFieldQ16()">
							<option value="" disabled selected>{{'Select' | translate}}
							</option>
						</select> <input ng-model="progressReport.typeOfParent.name" placeholder=""
							class="form-control input-md" type="text" readonly
							ng-if="progressReportViewPage">
						<div class="col-md-6 hideshowonclick"
							ng-if="progressReport.typeOfParent">
							<label class="control-label" for="textinput"> 
							<span translate>(i) Name</span> <span class="mandatory_star">&#42;</span>
							</label> <input id="nameOfParents" ng-model="progressReport.nameOfParent"
								placeholder=""
								required class="form-control input-md" type="text"
								maxlength="30" ng-disabled="progressReportViewPage">
						</div>
						<div class="col-md-6 hideshowonclick"
							ng-if="progressReport.typeOfParent">
							<label class="control-label" for="textinput"> 
							<span translate>(ii) Address</span> <span class="mandatory_star">&#42;</span>
							</label> <input id="addressOfParents"
								ng-model="progressReport.addressOfParent"
								placeholder=""
								required class="form-control input-md" type="text"
								maxlength="200" ng-disabled="progressReportViewPage">
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>17. Date of report</span>
						<span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" ng-model="progressReport.dateOfReport"
							id="dateOfReport" readonly class="form-control"
							ng-disabled="progressReportViewPage">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id" class="btn btn-info"
						type="submit"
						ng-click="progressReportForm.$invalid ? '' : validateForm()"
						ng-if="!progressReportViewPage">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						type="submit" ng-if="progressReportViewPage"
						ng-click="printProgressReport()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">				$(document).ready(function() {			$('input').blur(function() {			    var value = $.trim( $(this).val() );			    $(this).val( value );			});			$('textarea').blur(function() {			    var value = $.trim( $(this).val() );			    $(this).val( value );			});		});	</script>
</div>