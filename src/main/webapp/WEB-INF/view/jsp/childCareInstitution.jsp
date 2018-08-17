<div id="childCareInstitutionBody" ng-cloak>
	<div class="modal fade" id="cciConfirmationModal" tabindex="-1"
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
							ng-click="saveChildCareForm()" class="close" data-dismiss="modal"
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
	<div class="modal fade" id="cciSuccessModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been saved successfully</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button6id" class="btn btn-info"
							type="submit" class="close" data-dismiss="modal"
							aria-hidden="true" ng-click="printCciSubmitData()">
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
			<span translate>ORDER OF PLACING A CHILD IN CHILD CARE INSTITUTION PENDING INQUIRY</span> <br> 
			<span translate>FORM 4</span> <br>
			<span translate>[Rule 10 (1)(iv)]</span>
		</div>
		<form class="form-horizontal basicchildform"
			name="childCareInstitution" id="childCareInstitution">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>ORDER OF PLACING A CHILD IN CHILD CARE INSTITUTION PENDING INQUIRY</span>
				</div>
				<div class="col-md-12 summaryspace">
					<div class="col-md-6 childidheader"
						style="margin-left: 0px !important; padding-left: 0px !important;">
						<div class="social_headng">
							<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" /> <span>
								<span translate>Child ID:</span>&nbsp;&nbsp;{{childId}}
							</span>
						</div>
					</div>
					<div class="col-md-6 childnameheader"
						style="margin-right: 0px !important; padding-right: 0px !important;">
						<div class="social_headng1">
							<span> <span translate>Name of Child:</span>&nbsp;&nbsp;{{childBgData.childName}}
							</span>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label">
						<span translate>1. Date of Placement</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfPlacemennt" readonly
							class="form-control" ng-model="ccInstitution.dateOfPlacement"
							ng-disabled="viewCCIpending">
					</div>
					<div id="dateOfPlacemenntError" class="error-style"></div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2.Child Name </span>
					</label>
					<div class="col-md-7">
						<input maxlength="30" id="childNameinstitution"
							name="childNameinstitution" ng-model="childBgData.childName"
							placeholder="" class="form-control input-md" type="text" readonly />
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3.Name of the Parent</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="NameOfParents" name="NameOfParents" required
							placeholder="{{'Enter Name of the Parents'| translate}}"
							maxlength="30" class="form-control input-md"
							type="text" ng-model="ccInstitution.parentName"
							ng-disabled="viewCCIpending">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4.Child Age </span>
					</label>
					<div class="col-md-7">
						<input id="childAgeinstitution" name="childAgeinstitution"
							placeholder="{{'Enter Age of the child'| translate}}"
							class="form-control input-md" ng-model="childBgData.age"
							type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5.Address of Child</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea id="addressOfParents" name="addressOfParents" required
							placeholder="{{'Enter address of child'| translate}}"
							maxlength="100" class="form-control input-md"
							ng-model="ccInstitution.addressOfChild" type="text"
							ng-disabled="viewCCIpending"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7.FIR No.</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" ng-model="childBgData.firNumber"
							placeholder="" class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8.DD No.</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" ng-model="childBgData.ddNumber"
							placeholder="" class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9.Police Station</span>
					</label>
					<div class="col-md-7">
						<input maxlength="30" id="policeStation"
							name="policeStation" ng-model="childBgData.policeStation"
							placeholder="" class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10.Name of Child Care Institution (Observation Home/ Place of Safety)</span> 
						<span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<!-- <input id="NameOfCWC" name="NameOfCWC"							placeholder="{{'Enter Name of CCI'| translate}}" class="form-control input-md"							ng-model="prefetchData.cwc.name" type="text"> -->
						<select id="NameOfCWC" name="NameOfCWC" class="form-control"
							ng-if="!viewCCIpending" required
							ng-options="cci as cci.name for cci in cciList | filter : ciclCCI"
							ng-model="ccInstitution.cciObject">
							<option value="" disabled selected translate>Select
							</option>
						</select> <input ng-if="viewCCIpending" class="form-control input-md"
							ng-model="ccInstitution.cciObject.name" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>11.Child sent to CCI for a period of (in months)</span>
						<span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input only-three-digits id="timePeriodinMonths"
							name="timePeriodinMonths"
							placeholder="{{'Enter Child sent to CCI for a period of (in months)'| translate}}"
							class="form-control input-md" type="number"
							ng-model="ccInstitution.duration" ng-disabled="viewCCIpending"
							ng-blur="resetText(ccInstitution,'duration','cciperiodMonthError')"
							required>
						<div id="cciperiodMonthError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>12.Next Date of Hearing</span> 
						<span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfHearing" readonly
							class="form-control" ng-model="ccInstitution.nextDateOfHearing"
							ng-disabled="viewCCIpending">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>13.Date of Order</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="cciDateOfOrder" name="cciDateOfOrder"
							ng-model="ccInstitution.dateOfOrder" readonly
							class="form-control">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id" class="btn btn-info"
						ng-if="!viewCCIpending"
						ng-click="childCareInstitution.$invalid ? '' : validateChildCareForm()"
						type="submit">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						type="submit" ng-if="viewCCIpending"
						ng-click="printCciSubmitData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">		
// $(document).ready(function() {
	$("#dateOfHearing").datepicker({dateFormat: "yy-mm-dd", changeYear:true});
	$("#dateOfPlacemennt").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear:true});
	$("#cciDateOfOrder").datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d', changeYear:true});
// 	});	
	</script>
