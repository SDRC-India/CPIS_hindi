<div id="superVisionBody" ng-cloak>
	<div class="modal fade" id="supervisionConfirmationModal" tabindex="-1"
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
							ng-click="saveSuperVisionData()" class="close"
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
	<div class="modal fade" id="supervisionSuccessModal" tabindex="-1"
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
							aria-hidden="true" ng-click="printSupervisionData()">
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
			<span translate>SUPERVISION ORDER</span> <br> <span translate>FORM 3</span> <br> <span translate>[Rule 10 (1)(iii)]</span>
		</div>
		<form class="form-horizontal basicchildform" name="supervisionOrder"
			id="supervisionOrder">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>SUPERVISION ORDER</span>
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
							<span> <span translate>Name of Child:</span>&nbsp;&nbsp;{{childBgData.childName
								| limitTo : 30}}
							</span>
						</div>
					</div>
				</div>
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>1. Child is placed under the care of </span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="careOfWhom" class="form-control" required
							ng-if="!viewSupervision"
							ng-disabled="childMstData.finalOrderFilled == 1"
							ng-options="care as lang=='en'?care.name:care.typeNameHindi for care in supervisionCareUnderWhomList"
							ng-model="$parent.supervisionData.childUnderCareOfWhom">
							<option value="" disabled selected translate>Select</option>
						</select> <input placeholder=""
							value="{{lang=='en'?supervisionData.childUnderCareOfWhom.name:supervisionData.childUnderCareOfWhom.typeNameHindi}}"
							ng-if="viewSupervision" type="text" class="form-control input-md"
							readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2.FIR No.</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" id="firDDNo" name="firDDNo" placeholder=""
							ng-model="childBgData.firNumber" class="form-control input-md"
							type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3.DD No.</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" placeholder=""
							ng-model="childBgData.ddNumber" class="form-control input-md"
							type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding ">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label">
						<span translate>4. Date</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="fir_ddNoDate" readonly name="fir_ddNoDate"
							ng-model="childBgData.entryDate" class="form-control">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Police Station</span>
					</label>
					<div class="col-md-7">
						<input thirty-characters-validation id="policeStation"
							name="policeStation" placeholder="" class="form-control input-md"
							ng-model="childBgData.policeStation" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Child Name</span>
					</label>
					<div class="col-md-7">
						<input thirty-characters-validation id="childName"
							name="childName" placeholder="" ng-model="childBgData.childName"
							class="form-control input-md" type="text" readonly />
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7.Person/Institution Name</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="NameOfParents" name="NameOfParents"
							maxlength="30"
							placeholder="{{'Enter person/Institution name'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="viewSupervision || childMstData.finalOrderFilled == 1"
							ng-model="supervisionData.supervisionAuthorityName" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8.Address of person/institution</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<textarea id="addressOfParents" name="addressOfParents"
							placeholder="{{'Enter address of person/institution'| translate}}"
							class="form-control input-md"
							ng-disabled="viewSupervision || childMstData.finalOrderFilled == 1"
							maxlength="100"
							ng-model="supervisionData.supervisionAuthorityAddress"
							type="text" required></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9.Child Placed Period (in months)</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input type="number" id="childPlacedPeriod"
							placeholder="{{'Enter child placed period'| translate}}"
							ng-model="supervisionData.childPlacedPeriod" only-three-digits
							ng-disabled="viewSupervision || childMstData.finalOrderFilled == 1"
							class="form-control"
							ng-blur="resetText(supervisionData,'childPlacedPeriod','childPlacedError')"
							required>
						<div id="childPlacedError" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10.District Name</span>
					</label>
					<div class="col-md-7">
						<input thirty-characters-validation id="NameOfCWC"
							name="NameOfCWC" ng-model="childMstData.district.name"
							placeholder="" class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>11.Date of Order</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="dateOfOrder"
							ng-disabled="childMstData.finalOrderFilled == 1"
							ng-model="supervisionData.dateOfOrder" readonly
							class="form-control">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id" class="btn btn-info"
						ng-click="supervisionOrder.$invalid ? '' : validateSupervisionForm()"
						type="submit" ng-if="!viewSupervision">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						type="submit" ng-if="viewSupervision"
						ng-click="printSupervisionData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
	<script src="resources/js/moment-with-locales.js"></script>
	<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript">			
// 														$(document).ready(function() {				
														$("#childPlacedPeriod").keypress(function(event) {			      
															if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {			           
																return false;			      
																}			   
															});				
														$("#dateOfOrder").datepicker({
															dateFormat: "yy-mm-dd", maxDate: '+0d'
															});			
// 														});		
														</script>
</div>