<div ng-app="interimDecision" id="fitInstitutionBody"
	ng-controller="FitInstitution" ng-cloak>
	<div class="modal fade" id="confirmationModal" tabindex="-1"
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
	<div class="modal fade" id="childFitIdModal" tabindex="-1"
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
							aria-hidden="true" ng-click="printFitInstitutionSubmitData()">
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
		| <a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
		<hr> -->
		<div class="childlist-heading1 borderPersonal">
			<span translate>ORDER OF PLACEMENT OF A CHILD IN AN INSTITUTION (Children's Home/Fit Facility/SAA)<br>FORM 18<br>[Rules 18 (5), 18 (9) and 19 (26) ]
			</span>
		</div>
		<form class="form-horizontal basicchildform" name="childInstitution"
			id="childInstitution">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>ORDER OF PLACEMENT OF A CHILD IN AN INSTITUTION (Children's Home/Fit Facility/SAA)</span>
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
							<span><span translate>Name of Child:</span>&nbsp;&nbsp;{{prefetchData.childName |
								limitTo : 30}}</span>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>1. Case No</span></label>
					<div class="col-md-7">
						<input id="casenoinstitution" name="caseNo"
							ng-model="prefetchData.caseNum"
							oninvalid="this.setCustomValidity('Input case no')"
							oninput="setCustomValidity('')"
							placeholder="{{'Enter case no.'| translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"><span
						translate>2. Date of Placement</span> <span class="mandatory_star">&#42;</span></label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfPlacemennt"
							ng-model="formInfo.dateChildPlacedInFitInstitution" readonly
							class="form-control">
						<div id="dateOfPlacementError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>3. Child Name</span> </label>
					<div class="col-md-7">
<!-- 					only-digits-characters-whitespace -->
						<input id="childNameinstitution" name="childNameinstitution"
							ng-model="prefetchData.childName"
							placeholder="{{'Enter Name of the child'| translate}}"
							class="form-control input-md" type="text" readonly />
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>4. Child Age</span> </label>
					<div class="col-md-7">
						<input id="childAgeinstitution" name="childAgeinstitution"
							placeholder="{{'Enter Age of the child'| translate}}"
							class="form-control input-md" ng-model="prefetchData.age"
							type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>5. Name of the Parent</span><span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input required id="NameOfParents" name="NameOfParents"
							placeholder="{{'Enter Name of the Parents'| translate}}"
							class="form-control input-md" max-length="30"
							type="text" ng-model="formInfo.parentName"
							ng-disabled="fitInstituteDisable">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>6. Address of Child</span><span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<textarea required id="addressOfParents" name="addressOfParents"
							placeholder="{{'Enter address of child'| translate}}"
							maxlength="100" class="form-control input-md"
							ng-model="formInfo.addressOfChild" type="text"
							ng-disabled="fitInstituteDisable"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>7. Name of CWC</span> </label>
					<div class="col-md-7">
						<input id="NameOfCWC" name="NameOfCWC"
							placeholder="{{'Enter Name of CWC'| translate}}"
							class="form-control input-md" ng-model="prefetchData.cwc.name"
							type="text" readonly>
					</div>
				</div>
				<!-- 							<div class="form-group box-border-padding"> -->
				<!-- 							  <label class="col-md-4 control-label" for="textinput">8. Children's Home/SAA/Facility <span class="mandatory_star">&#42;</span></label>   -->
				<!-- 							  <div class="col-md-7"> -->
				<!-- 								  <input id="ChildrenHomeSAAFacility" name="ChildrenHomeSAAFacility" -->
				<!-- 								  	oninvalid="this.setCustomValidity('Input case no')" oninput="setCustomValidity('')" -->
				<!-- 								  	placeholder="{{'Enter Children's Home/SAA/Facility'| translate}}" class="form-control input-md"  -->
				<!-- 								  	ng-model="formInfo.childrenHomeSAAFacility" type="text" required> -->
				<!-- 							  </div> -->
				<!-- 							</div> -->
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>8. Children's Home/SAA/Facility</span> <span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<select id="cci" name="cci" ng-if="!fitInstituteDisable" required
							class="form-control" ng-model="formInfo.cci"
							ng-disabled="fitInstituteDisable">
							<option value="" disabled selected><span translate>{{'Select' | translate}}</span>
							</option>
							<option ng-repeat="cci in cciList | filter:cncpList"
								ng-value="cci">{{cci.name}} (Strength-{{cci.childCount}})</option>
						</select> <input placeholder="" class="form-control input-md" type="text"
							readonly ng-model="formInfo.cci.name" ng-if="fitInstituteDisable" />
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span
						translate>9. Child sent to fit Institution for a period of (in months)</span> <span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<input only-three-digits id="timePeriodinMonths"
							name="timePeriodinMonths" ng-disabled="fitInstituteDisable"
							placeholder="{{'Enter Child sent to fit Institution for a period of (in months)'| translate}}"
							class="form-control input-md" type="number"
							ng-model="formInfo.periodForWhichSentToFitInstitution"
							ng-keyUp="validateFields(formInfo.periodForWhichSentToFitInstitution,'timePeriodinMonthserror')"
							ng-blur="resetInput(formInfo,'periodForWhichSentToFitInstitution','timePeriodinMonthserror')"
							required>
						<div id="timePeriodinMonthserror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"><span translate>10. Date of Order </span><span
						class="mandatory_star">&#42;</span></label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfOrder"
							ng-model="formInfo.dateOfFormFilled" readonly
							class="form-control" ng-disabled="fitInstituteDisable">
						<div id="dateOfFormFilledError" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div style="text-align: center">
					<button ng-if="!fitInstituteDisable" id="button1id"
						name="button1id" class="btn btn-info"
						ng-click="childInstitution.$invalid ? '' : validateForm()"
						type="submit">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-if="fitInstituteDisable"
						ng-click="printOldFitInstitutionSubmitData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script>
	var app = angular.module('interimDecision', [ 'gettext' ]);
	var myAppConstructor = angular.module('interimDecision');
</script>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>