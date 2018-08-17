<div ng-controller="RestorationOrderController" ng-cloak>
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForRestorationOrder"
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
							class="btn btn-info bigbutton" type="submit"
							ng-click="saveRestorationData()" class="close"
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
	<div class="modal fade" id="successModalForRestorationOrder"
		tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been saved successfully.</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id"
							class="btn btn-info bigbutton" type="submit"
							ng-click="printRestorationForm()" class="close"
							data-dismiss="modal" aria-hidden="true">
							<span translate>Print</span>
						</button>
						<button id="button4id" name="button4id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">
							<span translate>Ok</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	{{restorationData}} <input type="hidden" id="cwcName"
		value="${cwcName}"> <input type="hidden" id="district"
		value="${district}">
	<div class="box-border box-border-padding">
		<!-- <hr>
		<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a>
		| <a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
		<hr> -->
		<div class="childlist-heading1 borderPersonal">
			<span translate>RELEASE CUM RESTORATION ORDER</span> <br> <span
				translate>FORM 44</span> <br> <span translate>[Rule 82
				(1)]</span>
		</div>
		<form class="form-horizontal basicchildform" name="restorationOrder"
			id="restorationOrder">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>RELEASE CUM RESTORATION ORDER</span>
				</div>
				<jsp:include page="./common/childNameStrip.jsp" />
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>1. Name of the Child</span>
					</label>
					<div class="col-md-7">
						<input id="childName" name="childName"
							ng-model="prefetchData.childName"
							placeholder="{{'Enter name of the Child'| translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2. Son/daughter of</span>
					</label>
					<div class="col-md-7">
						<input id="parentName" name="parentName" readonly
							class="form-control input-md" type="text"
							ng-model="prefetchData.sirFatherName">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>3. Address</span>
					</label>
					<div class="col-md-7">
						<input id="childAddress" name="childAddress" readonly
							class="form-control input-md" type="text"
							ng-model="prefetchData.sirAddress">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4. Case No/Profile Number</span>
					</label>
					<div class="col-md-7">
						<input id="caseno" name="caseno" class="form-control input-md"
							type="text" ng-model="prefetchData.caseNum" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Child ordered to be placed in</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7" ng-if="restorationViewPage">
<!-- 						<input id="caseno" name="caseno" placeholder="" -->
<!-- 							class="form-control input-md" type="text" required -->
<!-- 							ng-model="childOrderPlacedInName" readonly> -->
							
					   <select class="form-control" disabled ng-model="childOrderPlacedIn.id">
					   	    <option ng-repeat="item in placedOrder" ng-value="{{item.id}}">
					   	    {{lang=='en'?item.name:item.typeNameHindi}}
							</option>
					   </select>		
					</div>
					<div class="col-md-7" ng-if="!restorationViewPage">
						<select id="placedIn" name="placedIn"
							ng-options="item as lang=='en'?item.name:item.typeNameHindi for item in placedOrder"
							class="form-control" required
							ng-model="restorationselectedChild.childOrderPlacedIn">
							<option value="" disabled selected>{{'Select' | translate}}</span>
							</option>
						</select>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Ordered By</span>
					</label>
					<div class="col-md-7">
						<input id="orderedBy" name="orderedBy"
							ng-model="prefetchData.cwc.name" required
							placeholder="{{'Enter ordered by whom'| translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>7. District</span>
					</label>
					<div class="col-md-7">
						<input id="districtName" name="districtName"
							ng-model="prefetchData.district.name" required
							placeholder="{{'Enter district name'| translate}}"
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Under section</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input required id="underSection" name="underSection"
							maxlength="30" ng-model="restorationselectedChild.section"
							placeholder="{{'Enter section name'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="restorationViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9. For a term of (in months)</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input only-three-digits id="termPeriod" name="termPeriod"
							ng-model="restorationselectedChild.timePeriod" required
							placeholder="{{'Enter term period'| translate}}"
							class="form-control input-md" type="number"
							ng-disabled="restorationViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>10. On the date</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-2 col-sm-6 col-xs-6">
						<input type="text" id="onTheDate10"
							ng-model="restorationselectedChild.placedDate" readonly
							class="form-control" ng-disabled="restorationViewPage">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>11. Present Institution</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="institutionName" name="institutionName" maxlength="30"
							ng-model="restorationselectedChild.presentInstitution"
							placeholder="{{'Enter institution name'| translate}}" required
							class="form-control input-md" type="text"
							ng-disabled="restorationViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>12. Present Institution District</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="institutionDistrict" name="placedIn"
							class="form-control" ng-if="!restorationViewPage" required
							ng-options="area as area.areaName for area in districtList"
							ng-model="restorationselectedChild.institutionDistrict">
							<option value="" disabled selected>{{'Select' | translate}}</option>
						</select> <input
							ng-model="restorationselectedChild.institutionDistrict.areaName"
							ng-if="restorationViewPage" required
							class="form-control input-md" type="text" readonly>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>13. Under supervision and the authority of</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="underSupervision" name="underSupervision" maxlength="30"
							ng-model="restorationselectedChild.authorityIncharge"
							placeholder="{{'Enter supervision and the authority name'| translate}}"
							required class="form-control input-md" type="text"
							ng-disabled="restorationViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>14. Reason for discharge</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input id="reasonofDischarge" name="reasonofDischarge"
							maxlength="130"
							ng-model="restorationselectedChild.dischargeReason"
							placeholder="{{'Enter reason for discharge'| translate}}"
							required class="form-control input-md" type="text"
							ng-disabled="restorationViewPage">
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>15. Date of order</span>
						<span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-2 col-sm-6 col-xs-6">
						<input type="text" id="dateOfOrder"
							ng-model="restorationselectedChild.orderDate" readonly
							class="form-control" ng-disabled="restorationViewPage">
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>16. Place of order</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="placeofOrder" name="placeofOrder" required maxlength="30"
							ng-model="restorationselectedChild.placeOfOrder"
							placeholder="{{'Enter place of order'| translate}}"
							class="form-control input-md" type="text"
							ng-disabled="restorationViewPage">
					</div>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id"
						ng-click="restorationOrder.$invalid ? '' : validateRestorationOrder()"
						class="btn btn-info" ng-if="!restorationViewPage" type="submit">
						<span translate>Submit</span>
					</button>
					<button id="button2id" name="button2id" class="btn btn-info"
						ng-if="restorationViewPage" type="submit"
						ng-click="printRestorationForm()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>