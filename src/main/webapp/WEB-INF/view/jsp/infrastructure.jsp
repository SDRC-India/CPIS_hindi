	
<div ng-controller="InfraStructureController" ng-cloak>
	<div class="modal fade" id="infraCCI" tabindex="-1"		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Please click on the submit button to save the details.</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="submitData" name="button3id" class="btn btn-info bigbutton"							
						type="submit" ng-click="saveInfraCCI()" class="close"							
						data-dismiss="modal" aria-hidden="true" data-toggle="modal" data-target="#alertModalforInfra" translate>Submit
						</button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"							
						type="submit" class="close" data-dismiss="modal"							
						aria-hidden="true" translate>Back</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="infraSAA" tabindex="-1"		role="dialog">
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
						<button id="submitData" name="button3id" class="btn btn-info bigbutton"							
						type="submit" ng-click="saveInfraSAA()" class="close"							
						data-dismiss="modal" aria-hidden="true" data-toggle="modal" data-target="#alertModalforInfra" translate>Submit
						</button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"							
						type="submit" class="close" data-dismiss="modal"							
						aria-hidden="true" translate>Back
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="infraOS" tabindex="-1"		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Please click on the submit button to save the details.</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="submitData" name="button3id" class="btn btn-info bigbutton"							type="submit" ng-click="saveInfraOS()" class="close"							data-dismiss="modal" aria-hidden="true" data-toggle="modal" data-target="#alertModalforInfra" translate>
							Submit
						</button>
						<button id="button4id" name="button4id" class="btn btn-info bigbutton2"							type="submit" class="close" data-dismiss="modal"							aria-hidden="true" translate>
							Back
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="nocci1" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content"				style="border-radius: 0px; padding-top: 40px; padding-bottom: 40px;">
				<div class="modal-body">
					<p style="text-align: center; font-size: 18px;font-weight:bold;">
						<span translate>No CCIs available.</span>
						<br>
						</p>
						<button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true" translate>
							OK
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- <div class="modal fade" id="infrassa" tabindex="-1" role="dialog"><div class="modal-dialog"><div class="modal-content"				style="border-radius: 0px; padding-top: 40px; padding-bottom: 40px;"><div class="modal-body"><p style="text-align: center; font-size: 18px;font-weight:bold;">						Data is not available.<br></p><button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button></div></div></div></div> -->
		<div class="modal fade" id="alertModalforInfra1" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<h4 class="modal-title" id="myModalLabel">
							<span translate>The form has been submitted successfully.</span>
						</h4>
						<p style="text-align:center">
							<!-- 			  <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="printAfterCareData()">Print</button> -->
							<button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()" translate>
								Ok</button>
								<button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" 							
								class="close" data-dismiss="modal" aria-hidden="true" ng-click="printInfraCCISubmitData()" translate>
									Print
								</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="alertModalforInfra2" tabindex="-1" role="dialog" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<h4 class="modal-title" id="myModalLabel">
								<span translate>The form has been submitted successfully.</span>
							</h4>
							<p style="text-align:center">
								<!-- 			  <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="printAfterCareData()">Print</button> -->
								<button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">
									<span translate>Ok</span>
								</button>
								<button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" 							class="close" data-dismiss="modal" aria-hidden="true" ng-click="printInfraSAASubmitData()">
									<span translate>Print</span>
								</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="alertModalforInfra3" tabindex="-1" role="dialog" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<h4 class="modal-title" id="myModalLabel">
								<span translate>The form has been submitted successfully.</span>
							</h4>
							<p style="text-align:center">
								<!-- 			  <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="printAfterCareData()">Print</button> -->
								<button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()" translate>
									Ok </button>
									<button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" 							class="close" data-dismiss="modal" aria-hidden="true" ng-click="printInfraOSSubmitData()" translate>
										Print
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
							<div class="grey-header marginTop"								style="border-top: none; margin-top: 4px;">
								<span translate>infrastructure of institution</span>
							</div>
							<div class="fosterSelection ">
								<input type="radio" name="infra" class="fosterRadioBtn" 				ng-click="eraseData('cci')" id="firstRadio" ng-model="infraSt"				 ng-value="'infraCCI'" data-toggle="modal"				data-target="#infracci" ng-disabled="firstRadioDisable">
									<label style="margin-right: 70px; margin-left: 2px;">
										<span translate>Infra CCI</span>
									</label>
									<input type="radio" name="infra" class="fosterRadioBtn"				ng-click="eraseData('saa')" id="secondRadio"  ng-value="'infraSSA'"				 ng-model="infraSt" data-toggle="modal"				data-target="#infrassa" ng-disabled="secondRadioDisable"				>
										<label style="margin-right: 70px; margin-left: 2px;">
											<span translate>Infra SAA</span>
										</label>
										<input type="radio" name="infra" class="fosterRadioBtn"				ng-click="eraseData('os')" id="thirdRadio" ng-model="infraSt" 				 ng-value="'infraOS'" ng-disabled="thirdRadioDisable">
											<label style="margin-left: 2px;">
												<span translate>Infra OS</span>
											</label>
										</div>
										<!-- 			start HR CCI -->
										<form ng-show="infraSt == 'infraCCI'" class="form-horizontal basicchildform"			name="infraCCIDetail" id="infraCCIDetail">
											<fieldset>
												<div class="form-group box-border-padding">
													<label class="col-md-4 control-label" for="textinput">
														<span translate>1. Name of the District</span>
													</label>
													<div class="col-md-7">
														<input id="withWhomChildFoundName" readonly  name="withWhomChildFoundName" ng-model="district.name"							  	placeholder="{{'Enter district name'| translate}}" class="form-control input-md" 							  	type="text" >
														</div>
													</div>
													<div class="form-group box-border-padding">
														<label class="col-md-4 control-label" for="textinput">
															<span translate>2. Name of CCI</span>
															<span class="mandatory_star">&#42;</span>
														</label>
														<div class="col-md-7">
															<select id="producedBeforeCwc" name="producedBeforeCwc" class="form-control"  ng-model="infraCCI.nameOfCCI" required>
																<option value="" disabled selected>
																	<span translate>Select CCI</span>
																</option>
																<!-- <option> CCI</option> -->
																<option ng-repeat="cci in cciLists"  ng-value="{{cci}}">{{cci.name}}</option>
															</select>
														</div>
														<div id="producedBeforeCwcerror" class="error-style"></div>
													</div>
													<div class="form-group box-border-padding">
														<label class="col-md-4 control-label" for="textinput">
															<span translate>3. Building</span>
														</label>
														<div class="col-md-7">
															<label class="radio-inline" for="radios-0">
																<input type="radio" name="type" class="fosterRadioBtn" value="Rent" ng-model="infraCCI.building_Type">
																	<span translate>Rented</span>
																</label>
																<label class="radio-inline" for="radios-0">
																	<input type="radio" name="type" class="fosterRadioBtn" value="Own" ng-model="infraCCI.building_Type" >
																		<span translate>Own</span>
																	</label>
																</div>
															</div>
															<div class="form-group box-border-padding">
																<label class="col-md-4 control-label" for="textinput">
																	<span translate>4. Area of the Building (Square feet)</span>
																</label>
																<div class="col-md-7">
																	<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraCCI.area_of_Building"							  	placeholder="{{'Enter area of the building'| translate}}" class="form-control input-md" 							  	type="text" valid-decimal-number62  >
																	</div>
																</div>
																<div class="form-group box-border-padding">
																	<label class="col-md-4 control-label" for="textinput">
																		<span translate>5. Building protected by boundary wall</span>
																	</label>
																	<div class="col-md-7">
																		<label class="radio-inline" for="radios-0">
																			<input type="radio" name="type1" class="fosterRadioBtn" ng-value="buildingProtectedYes" ng-model="infraCCI.building_Protected_by_Boundarywall" >
																				<span translate>Yes</span>
																			</label>
																			<label class="radio-inline" for="radios-0">
																				<input type="radio" name="type1" class="fosterRadioBtn" ng-value="buildingProtectedNo" ng-model="infraCCI.building_Protected_by_Boundarywall" >
																					<span translate>No</span>
																				</label>
																			</div>
																		</div>
																		<div class="form-group box-border-padding">
																			<label class="col-md-4 control-label" for="textinput">
																				<span translate>6. Status of Building</span>
																			</label>
																			<div class="col-md-7">
																				<label class="radio-inline" for="radios-0">
																					<input type="radio" name="type2" class="fosterRadioBtn" value="Pakka" ng-model="infraCCI.status_of_Building" >
																						<span translate>Pakka</span>
																					</label>
																					<label class="radio-inline" for="radios-0">
																						<input type="radio" name="type2" class="fosterRadioBtn" value="Kachha" ng-model="infraCCI.status_of_Building">
																							<span translate>Kachha</span>
																						</label>
																					</div>
																				</div>
																				<div class="form-group box-border-padding">
																					<label class="col-md-4 control-label" for="textinput">
																						<span translate>7. Total numbers of Room</span>
																					</label>
																					<div class="col-md-7">
																						<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraCCI.total_Numbers_Room"							  	placeholder="{{'Enter total no of room'| translate}}" class="form-control input-md" 							  	type="number" only-two-digits>
																						</div>
																					</div>
																					<div class="form-group box-border-padding">
																						<label class="col-md-4 control-label" for="textinput">
																							<span translate>8. Availability of electricity in all rooms</span>
																						</label>
																						<div class="col-md-7">
																							<label class="radio-inline" for="radios-0">
																								<input type="radio" name="type3" class="fosterRadioBtn" ng-value="availabilityOfElectricityYes" ng-model="infraCCI.availability_Electricity_Allrooms" >
																									<span translate>Yes</span>
																								</label>
																								<label class="radio-inline" for="radios-0">
																									<input type="radio" name="type3" class="fosterRadioBtn" ng-value="availabilityOfElectricityNo" ng-model="infraCCI.availability_Electricity_Allrooms">
																										<span translate>No</span>
																									</label>
																								</div>
																							</div>
																							<div class="form-group box-border-padding">
																								<label class="col-md-4 control-label" for="textinput">
																									<span translate>9. Power backup facility for all the rooms</span>
																								</label>
																								<div class="col-md-7">
																									<label class="radio-inline" for="radios-0">
																										<input type="radio" name="type4" class="fosterRadioBtn" ng-value="powerBackupYes" ng-model="infraCCI.power_Backupfacility_AllRooms" >
																											<span translate>Yes</span>
																										</label>
																										<label class="radio-inline" for="radios-0">
																											<input type="radio" name="type4" class="fosterRadioBtn" ng-value="powerBackupNo" ng-model="infraCCI.power_Backupfacility_AllRooms" >
																												<span translate>No</span>
																											</label>
																										</div>
																									</div>
																									<div class="form-group box-border-padding">
																										<label class="col-md-4 control-label" for="textinput">
																											<span translate>10. Number of toilets available</span>
																										</label>
																										<div class="col-md-7">
																											<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraCCI.number_of_toiletsAvailable"							  	placeholder="{{'Enter no of toilets available'| translate}}" class="form-control input-md" 							  	type="number" only-two-digits>
																											</div>
																										</div>
																										<div class="form-group box-border-padding">
																											<label class="col-md-4 control-label" for="textinput">
																												<span translate>11. Availability of Drinking water</span>
																											</label>
																											<div class="col-md-7">
																												<label class="radio-inline" for="radios-0">
																													<input type="radio" name="type5" class="fosterRadioBtn" ng-value="availabilityOfDrinkingYes" ng-model="infraCCI.availability_of_DrinkingWater" >
																														<span translate>Yes</span>
																													</label>
																													<label class="radio-inline" for="radios-0">
																														<input type="radio" name="type5" class="fosterRadioBtn" ng-value="availabilityOfDrinkingNo" ng-model="infraCCI.availability_of_DrinkingWater" >
																															<span translate>No</span>
																														</label>
																													</div>
																												</div>
																												<div class="form-group box-border-padding">
																													<label class="col-md-4 control-label" for="textinput1">
																														<span translate>12.Total Capacity</span>
																													</label>
																													<div class="col-md-7">
																														<input id="textinput1"   name="withWhomChildFoundName" ng-model="infraCCI.total_capacity"							  	placeholder="{{'Enter total capacity'| translate}}" class="form-control input-md" 							  	type="number">
																														</div>
																													</div>
																													<div style="text-align:center">
																														<button id="button1id" name="button1id" ng-click = "infraCCIDetail.$invalid ? '' : validateInfraCCI()"								class="btn btn-info" type="submit" data-toggle="modal" >
																															<span translate>Submit</span>
																														</button>
																														<button ng-if="fosterDisable" id="button2id" name="button2id" class="btn btn-info"						ng-click="printOldFosterCareSubmitData()">
																															<span translate>Print</span>
																														</button>
																														<!-- 								<button id="button2id" name="button2id" class="btn otherbut" type="reset" >RESET</button> -->
																													</div>
																												</fieldset>
																											</form>
																											<!-- 					end of HR CCI -->
																											<!-- 					start of HR SAA -->
																											<form ng-show="infraSt == 'infraSSA'" class="form-horizontal basicchildform"			name="infraSSADetail" id="infraSSADetail">
																												<fieldset>
																													<div class="form-group box-border-padding">
																														<label class="col-md-4 control-label" for="textinput">
																															<span translate>1. Names of the Districts </span>
																														</label>
																														<div class="col-md-7">
																															<input id="withWhomChildFoundName" readonly   name="withWhomChildFoundName" ng-model="district.name"							  	placeholder="{{'Enter name of districts'| translate}}" class="form-control input-md" 							  	type="text" >
																															</div>
																														</div>
																														<div class="form-group box-border-padding">
																															<label class="col-md-4 control-label" for="textinput">
																																<span translate>2. Name of SAA</span>
																																<span class="mandatory_star">&#42;</span>
																															</label>
																															<div class="col-md-7">
																																<select id="producedBeforeCwc" name="producedBeforeCwc" class="form-control" ng-model="infraSAA.nameOfSAA" required>
																																	<option value="" disabled selected>
																																		<span translate>Select SAA</span>
																																	</option>
																																	<!-- <option> SAA</option> -->
																																	<option ng-repeat="cci in cciList" ng-if="cci.typeId==4" ng-value="{{cci}}">{{cci.name}}</option>
																																</select>
																															</div>
																															<div id="producedBeforeCwcerror" class="error-style"></div>
																														</div>
																														<div class="form-group box-border-padding">
																															<label class="col-md-4 control-label" for="textinput">
																																<span translate>3. Building </span>
																															</label>
																															<div class="col-md-7">
																																<label class="radio-inline" for="radios-0">
																																	<input type="radio" name="ssabuilding" class="fosterRadioBtn" value="Rent" ng-model="infraSAA.building_Type">
																																		<span translate>Rented</span></label>
																																		<label class="radio-inline" for="radios-0">
																																			<input type="radio" name="ssabuilding" class="fosterRadioBtn" value="Own" ng-model="infraSAA.building_Type">
																																				<span translate>Own</span>
																																			</label>
																																		</div>
																																	</div>
																																	<div class="form-group box-border-padding">
																																		<label class="col-md-4 control-label" for="textinput">
																																			<span translate>4. Area of the Building (Square feet)</span>
																																		</label>
																																		<div class="col-md-7">
																																			<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraSAA.area_of_Building"							  	placeholder="{{'Enter area of the building'| translate}}" class="form-control input-md" 							  	type="text" valid-decimal-number62 >
																																			</div>
																																		</div>
																																		<div class="form-group box-border-padding">
																																			<label class="col-md-4 control-label" for="textinput">
																																				<span translate>5. Status of Building</span>
																																			</label>
																																			<div class="col-md-7">
																																				<label class="radio-inline" for="radios-0">
																																					<input type="radio" name="ssapakka" class="fosterRadioBtn" value="Pakka" ng-model="infraSAA.status_of_Building"  >
																																						<span translate>Pakka</span>
																																					</label>
																																					<label class="radio-inline" for="radios-0">
																																						<input type="radio" name="ssapakka" class="fosterRadioBtn" value="Kachha" ng-model="infraSAA.status_of_Building" >
																																							<span translate>Kachha</span>
																																						</label>
																																					</div>
																																				</div>
																																				<div class="form-group box-border-padding">
																																					<label class="col-md-4 control-label" for="textinput">
																																						<span translate>6. Building protected by boundary wall</span>
																																					</label>
																																					<div class="col-md-7">
																																						<label class="radio-inline" for="radios-0">
																																							<input type="radio" name="ssayes" class="fosterRadioBtn" ng-value="ssaBuildingProtectedYes" ng-model="infraSAA.building_Protected_by_Boundarywall" >
																																								<span translate>Yes</span>
																																							</label>
																																							<label class="radio-inline" for="radios-0">
																																								<input type="radio" name="ssayes" class="fosterRadioBtn" ng-value="ssaBuildingProtectedNo" ng-model="infraSAA.building_Protected_by_Boundarywall">
																																									<span translate>No</span>
																																								</label>
																																							</div>
																																						</div>
																																						<div class="form-group box-border-padding">
																																							<label class="col-md-4 control-label" for="textinput">
																																								<span translate>7. Number of Rooms available</span>
																																							</label>
																																							<div class="col-md-7">
																																								<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraSAA.total_Numbers_Room"							  	placeholder="{{'Enter no of rooms available'| translate}}" class="form-control input-md" 							  	type="number" only-two-digits>
																																								</div>
																																							</div>
																																							<div class="form-group box-border-padding">
																																								<label class="col-md-4 control-label" for="textinput">
																																									<span translate>8. Availability of electricity in all rooms</span>
																																								</label>
																																								<div class="col-md-7">
																																									<label class="radio-inline" for="radios-0">
																																										<input type="radio" name="ssaelectricity" class="fosterRadioBtn" ng-value="ssaAVailabilityOfElectricityYes" ng-model="infraSAA.availability_Electricity_Allrooms" >
																																											<span translate>Yes</span>
																																										</label>
																																										<label class="radio-inline" for="radios-0">
																																											<input type="radio" name="ssaelectricity" class="fosterRadioBtn" ng-value="ssaAVailabilityOfElectricityNo" ng-model="infraSAA.availability_Electricity_Allrooms">
																																												<span translate>No</span>
																																											</label>
																																										</div>
																																									</div>
																																									<div class="form-group box-border-padding">
																																										<label class="col-md-4 control-label" for="textinput">
																																											<span translate>9. Power backup facility for all the rooms</span>
																																										</label>
																																										<div class="col-md-7">
																																											<label class="radio-inline" for="radios-0">
																																												<input type="radio" name="ssapower" class="fosterRadioBtn" ng-value="ssaPowerBackupYes" ng-model="infraSAA.power_Backupfacility_AllRooms" >
																																													<span translate>Yes</span>
																																												</label>
																																												<label class="radio-inline" for="radios-0">
																																													<input type="radio" name="ssapower" class="fosterRadioBtn" ng-value="ssaPowerBackupNo" ng-model="infraSAA.power_Backupfacility_AllRooms" >
																																														<span translate>No</span>
																																													</label>
																																												</div>
																																											</div>
																																											<div class="form-group box-border-padding">
																																												<label class="col-md-4 control-label" for="textinput">
																																													<span translate>10. Number of toilets available</span>
																																												</label>
																																												<div class="col-md-7">
																																													<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraSAA.number_of_toiletsAvailable"							  	placeholder="{{'Enter no of toilets available'| translate}}" class="form-control input-md" 							  	type="number" only-two-digits>
																																													</div>
																																												</div>
																																												<div class="form-group box-border-padding">
																																													<label class="col-md-4 control-label" for="textinput">
																																														<span translate>11. Availability of Drinking water</span>
																																													</label>
																																													<div class="col-md-7">
																																														<label class="radio-inline" for="radios-0">
																																															<input type="radio" name="ssadrink" class="fosterRadioBtn" ng-value="ssaAvailabilityOfDrinkingYes" ng-model="infraSAA.availability_of_DrinkingWater" >
																																																<span translate>Yes</span>
																																															</label>
																																															<label class="radio-inline" for="radios-0">
																																																<input type="radio" name="ssadrink" class="fosterRadioBtn" ng-value="ssaAvailabilityOfDrinkingNo" ng-model="infraSAA.availability_of_DrinkingWater" >
																																																	<span translate>No</span>
																																																</label>
																																															</div>
																																														</div>
																																														<div class="form-group box-border-padding">
																																															<label class="col-md-4 control-label" for="textinput2">
																																																<span translate>12. Total Capacity</span>
																																															</label>
																																															<div class="col-md-7">
																																																<input id="textinput2"   name="withWhomChildFoundName" ng-model="infraSAA.total_capacity"							  	placeholder="{{'Enter total capacity'| translate}}" class="form-control input-md" 							  	type="number">
																																																</div>
																																															</div>
																																															<div style="text-align:center">
																																																<button id="button1id" name="button1id" ng-click = "infraSSADetail.$invalid ? '' : validateInfraSSA()"								class="btn btn-info" type="submit" data-toggle="modal" >
																																																	<span translate>Submit</span>
																																																</button>
																																																<button ng-if="fosterDisable" id="button2id" name="button2id" class="btn btn-info"						ng-click="printOldFosterCareSubmitData()">
																																																	<span translate>Print</span>
																																																</button>
																																																<!-- 								<button id="button2id" name="button2id" class="btn otherbut" type="reset" >RESET</button> -->
																																															</div>
																																														</fieldset>
																																													</form>
																																													<!-- 			end of HR SAA -->
																																													<!-- 				start of HR OS -->
																																													<form ng-show="infraSt == 'infraOS'" class="form-horizontal basicchildform"			name="infraOSDetail" id="infraOSDetail">
																																														<fieldset>
																																															<div class="form-group box-border-padding">
																																																<label class="col-md-4 control-label" for="textinput">
																																																	<span translate>1. Names of the Districts</span>
																																																</label>
																																																<div class="col-md-7">
																																																	<input id="withWhomChildFoundName" readonly  name="withWhomChildFoundName" ng-model="district.name"							  	placeholder="{{'Enter name of districts'| translate}}" class="form-control input-md" 							  	type="text" >
																																																	</div>
																																																</div>
																																																<div class="form-group box-border-padding">
																																																	<label class="col-md-4 control-label" for="textinput">
																																																		<span translate>2. Name of OS</span>
																																																		<span class="mandatory_star">&#42;</span>
																																																	</label>
																																																	<div class="col-md-7">
																																																		<select id="producedBeforeCwc" name="producedBeforeCwc" class="form-control" ng-model="infraOS.nameOfOS" required>
																																																			<option value="" disabled selected>
																																																				<span translate>Select OS</span>
																																																			</option>
																																																			<!-- <option> OS</option> -->
																																																			<option ng-repeat="cci in cciList" ng-if="cci.typeId==13" ng-value="{{cci}}">{{cci.name}}</option>
																																																		</select>
																																																	</div>
																																																	<div id="producedBeforeCwcerror" class="error-style"></div>
																																																</div>
																																																<div class="form-group box-border-padding">
																																																	<label class="col-md-4 control-label" for="textinput">
																																																		<span translate>3. Building</span>
																																																	</label>
																																																	<div class="col-md-7">
																																																		<label class="radio-inline" for="radios-0">
																																																			<input type="radio" name="osbuilding" class="fosterRadioBtn" value="Rent" ng-model="infraOS.building_Type">
																																																				<span translate>Rented</span>
																																																			</label>
																																																			<label class="radio-inline" for="radios-0">
																																																				<input type="radio" name="osbuilding" class="fosterRadioBtn" value="Own" ng-model="infraOS.building_Type" >
																																																					<span translate>Own</span>
																																																				</label>
																																																			</div>
																																																		</div>
																																																		<div class="form-group box-border-padding">
																																																			<label class="col-md-4 control-label" for="textinput">
																																																				<span translate>4. Status of Building</span>
																																																			</label>
																																																			<div class="col-md-7">
																																																				<label class="radio-inline" for="radios-0">
																																																					<input type="radio" name="ospakka" class="fosterRadioBtn" value="Pakka" ng-model="infraOS.status_of_Building"  >
																																																						<span translate>Pakka</span>
																																																					</label>
																																																					<label class="radio-inline" for="radios-0">
																																																						<input type="radio" name="ospakka" class="fosterRadioBtn" value="Kachha" ng-model="infraOS.status_of_Building" >
																																																							<span translate>Kachha</span>
																																																						</label>
																																																					</div>
																																																				</div>
																																																				<div class="form-group box-border-padding">
																																																					<label class="col-md-4 control-label" for="textinput">
																																																						<span translate>5. Area of the Building (Square feet)</span>
																																																					</label>
																																																					<div class="col-md-7">
																																																						<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraOS.area_of_Building"							  	placeholder="{{'Enter area of the building'| translate}}" class="form-control input-md" 							  	type="text" valid-decimal-number62 >
																																																						</div>
																																																					</div>
																																																					<div class="form-group box-border-padding">
																																																						<label class="col-md-4 control-label" for="textinput">
																																																							<span translate>6. Building protected by boundary wall</span>
																																																						</label>
																																																						<div class="col-md-7">
																																																							<label class="radio-inline" for="radios-0">
																																																								<input type="radio" name="osboundary" class="fosterRadioBtn" ng-value="osBuildingProtectedYes" ng-model="infraOS.building_Protected_by_Boundarywall" >
																																																									<span translate>Yes</span>
																																																								</label>
																																																								<label class="radio-inline" for="radios-0">
																																																									<input type="radio" name="osboundary" class="fosterRadioBtn" ng-value="osBuildingProtectedNo" ng-model="infraOS.building_Protected_by_Boundarywall">
																																																										<span translate>No</span>
																																																									</label>
																																																								</div>
																																																							</div>
																																																							<div class="form-group box-border-padding">
																																																								<label class="col-md-4 control-label" for="textinput">
																																																									<span translate>7. Number of Rooms available</span>
																																																								</label>
																																																								<div class="col-md-7">
																																																									<input id="totalRooms"   name="withWhomChildFoundName" ng-model="infraOS.total_Numbers_Room" 							  	placeholder="{{'Enter no of rooms available'| translate}}" class="form-control input-md" ng-blur="checkRoom()"							  	type="number" only-two-digits>
																																																										<div id="roomError" class="error-style"></div>
																																																									</div>
																																																								</div>
																																																								<div class="form-group box-border-padding">
																																																									<label class="col-md-4 control-label" for="textinput">
																																																										<span translate>8. Number of Rooms for Boys</span>
																																																									</label>
																																																									<div class="col-md-7">
																																																										<input id="roomForBoys"   name="withWhomChildFoundName" ng-model="infraOS.numbers_RoomForBoys"							  	placeholder="{{'Enter no of rooms for boys'| translate}}" class="form-control input-md" 							  	type="number" only-two-digits ng-blur="checkRoom()">
																																																										</div>
																																																									</div>
																																																									<div class="form-group box-border-padding">
																																																										<label class="col-md-4 control-label" for="textinput">
																																																											<span translate>9. Number of Rooms for Girls</span>
																																																										</label>
																																																										<div class="col-md-7">
																																																											<input id="roomForGirls"   name="withWhomChildFoundName" ng-model="infraOS.numbers_RoomForGirls"							  	placeholder="{{'Enter no of rooms for girls'| translate}}" class="form-control input-md" 							  	type="number" only-two-digits ng-blur="checkRoom()">
																																																											</div>
																																																										</div>
																																																										<div class="form-group box-border-padding">
																																																											<label class="col-md-4 control-label" for="textinput">
																																																												<span translate>10. Total Number of Toilets available</span>
																																																											</label>
																																																											<div class="col-md-7">
																																																												<input id="totalToilets"   name="withWhomChildFoundName" ng-model="infraOS.totalNumber_of_toiletsAvailable"							  	placeholder="{{'Enter no of toilets available'| translate}}" class="form-control input-md" ng-blur="checkToilets()"							  	type="number" only-two-digits>
																																																													<div id="toiletError" class="error-style"></div>
																																																												</div>
																																																											</div>
																																																											<div class="form-group box-border-padding">
																																																												<label class="col-md-4 control-label" for="textinput">
																																																													<span translate>11. Number of Toilets for Boys</span>
																																																												</label>
																																																												<div class="col-md-7">
																																																													<input id="toiletForBoys"   name="withWhomChildFoundName"  ng-model="infraOS.number_of_toiletsAvailableFor_Boys"							  	placeholder="{{'Enter no of toilets for boys'| translate}}" class="form-control input-md" ng-blur="checkToilets()"							  	type="number" only-two-digits>
																																																													</div>
																																																												</div>
																																																												<div class="form-group box-border-padding">
																																																													<label class="col-md-4 control-label" for="textinput">
																																																														<span translate>12. Number of Toilets for Girls</span>
																																																													</label>
																																																													<div class="col-md-7">
																																																														<input id="toiletForGirls"   name="withWhomChildFoundName" ng-model="infraOS.number_of_toiletsAvailableFor_Girls"							  	placeholder="{{'Enter no of toilets for girls'| translate}}" class="form-control input-md" ng-blur="checkToilets()"							  	type="number" only-two-digits>
																																																														</div>
																																																													</div>
																																																													<div class="form-group box-border-padding">
																																																														<label class="col-md-4 control-label" for="textinput">
																																																															<span translate>13. Separate Toilets for Staff</span>
																																																														</label>
																																																														<div class="col-md-7">
																																																															<label class="radio-inline" for="radios-0">
																																																																<input type="radio" name="osstaff" class="fosterRadioBtn" ng-value="osSeparateToiletsYes" ng-model="infraOS.separate_Toiletsfor_Staff"  >
																																																																	<span translate>Yes</span>
																																																																</label>
																																																																<label class="radio-inline" for="radios-0">
																																																																	<input type="radio" name="osstaff" class="fosterRadioBtn" ng-value="osSeparateToiletsNo" ng-model="infraOS.separate_Toiletsfor_Staff" >
																																																																		<span translate>No</span>
																																																																	</label>
																																																																</div>
																																																															</div>
																																																															<div class="form-group box-border-padding">
																																																																<label class="col-md-4 control-label" for="textinput">
																																																																	<span translate>14. Availability of electricity in all rooms</span>
																																																																</label>
																																																																<div class="col-md-7">
																																																																	<label class="radio-inline" for="radios-0">
																																																																		<input type="radio" name="oselectricy" class="fosterRadioBtn"  ng-value="osAvailabilityOfElectricityYes" ng-model="infraOS.availability_Electricity_Allrooms" >
																																																																			<span translate>Yes</span>
																																																																		</label>
																																																																		<label class="radio-inline" for="radios-0">
																																																																			<input type="radio" name="oselectricy" class="fosterRadioBtn" ng-value="osAvailabilityOfElectricityNo" ng-model="infraOS.availability_Electricity_Allrooms" >
																																																																				<span translate>No</span>
																																																																			</label>
																																																																		</div>
																																																																	</div>
																																																																	<div class="form-group box-border-padding">
																																																																		<label class="col-md-4 control-label" for="textinput">
																																																																			<span translate>15. Availability of power back up system</span>
																																																																		</label>
																																																																		<div class="col-md-7">
																																																																			<label class="radio-inline" for="radios-0">
																																																																				<input type="radio" name="ospower" class="fosterRadioBtn" ng-value="osAvailabilityOfPowerYes" ng-model="infraOS.power_Backupfacility_AllRooms"  >
																																																																					<span translate>Yes</span>
																																																																				</label>
																																																																				<label class="radio-inline" for="radios-0">
																																																																					<input type="radio" name="ospower" class="fosterRadioBtn" ng-value="osAvailabilityOfPowerNo" ng-model="infraOS.power_Backupfacility_AllRooms" >
																																																																						<span translate>No</span>
																																																																					</label>
																																																																				</div>
																																																																			</div>
																																																																			<div class="form-group box-border-padding">
																																																																				<label class="col-md-4 control-label" for="textinput">
																																																																					<span translate>16. Numbers of Contact points</span>
																																																																				</label>
																																																																				<div class="col-md-7">
																																																																					<input id="withWhomChildFoundName"   name="withWhomChildFoundName" ng-model="infraOS.number_of_ContactPoints"							  	placeholder="{{'Enter no of contact points'| translate}}" class="form-control input-md" 							  	type="number" only-two-digits>
																																																																					</div>
																																																																				</div>
																																																																				<div class="form-group box-border-padding">
																																																																					<label class="col-md-4 control-label" for="textinput">
																																																																						<span translate>17. Availability of Drinking water</span>
																																																																					</label>
																																																																					<div class="col-md-7">
																																																																						<label class="radio-inline" for="radios-0">
																																																																							<input type="radio" name="oswater" class="fosterRadioBtn" ng-value="osAvailabilityOfDrinkingYes" ng-model="infraOS.availability_of_DrinkingWater"  >
																																																																								<span translate>Yes</span>
																																																																							</label>
																																																																							<label class="radio-inline" for="radios-0">
																																																																								<input type="radio" name="oswater" class="fosterRadioBtn" ng-value="osAvailabilityOfDrinkingNo" ng-model="infraOS.availability_of_DrinkingWater" >
																																																																									<span translate>No</span>
																																																																								</label>
																																																																							</div>
																																																																						</div>
																																																																						<div class="form-group box-border-padding">
																																																																							<label class="col-md-4 control-label" for="textinput3">
																																																																								<span translate>18. Total Capacity</span>
																																																																							</label>
																																																																							<div class="col-md-7">
																																																																								<input id="textinput3"   name="withWhomChildFoundName" ng-model="infraOS.total_capacity"							  	placeholder="{{'Enter total capacity'| translate}}" class="form-control input-md" 							  	type="number">
																																																																								</div>
																																																																							</div>
																																																																							<div style="text-align:center">
																																																																								<button id="button1id" name="button1id" ng-click = "infraOSDetail.$invalid ? '' : validateInfraOSDetail()"								class="btn btn-info" type="submit" data-toggle="modal">
																																																																									<span translate>Submit</span>
																																																																								</button>
																																																																								<button ng-if="fosterDisable" id="button2id" name="button2id" class="btn btn-info"						ng-click="printOldFosterCareSubmitData()">
																																																																									<span translate>Print</span>
																																																																								</button>
																																																																								<!-- 								<button id="button2id" name="button2id" class="btn otherbut" type="reset" >RESET</button> -->
																																																																							</div>
																																																																						</fieldset>
																																																																					</form>
																																																																					<!-- end of HR OS -->
																																																																				</div>
																																																																			</div>
																																																																			<script type="text/javascript" src="resources/js/angular.min.js"></script>
																																																																			<!-- 			<script src="resources/js/AngularController/infraStructureController.js"></script> -->
																																																																			<script type="text/javascript">		$(document).ready(function() {			$("#button3id").on('submit', function(){				  $('#childIdModal').modal('show');			});						$(document).ready(function() {				$('#firstRadio').prop('checked', true);			});												$('input').blur(function() {    		    var value = $.trim( $(this).val() );    		    $(this).val( value );    		});			$('textarea').blur(function() {    		    var value = $.trim( $(this).val() );    		    $(this).val( value );    		});					});	</script>
																																																																			<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"		var="jQueryUiCore" />
																																																																			<script src="${jQueryUiCore}"></script>