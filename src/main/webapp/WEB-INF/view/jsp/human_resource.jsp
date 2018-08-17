<div id="humanResource" ng-controller="HumanResourceController">
	<div class="modal fade" id="hrCCIconfirmationModal" tabindex="-1"		role="dialog">
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
						<button id="submitData" name="button3id"							class="btn btn-info bigbutton" type="submit"							ng-click="saveCCIHR()" class="close" data-dismiss="modal"							aria-hidden="true" data-toggle="modal">
							<span translate>Submit</span>
						</button>
						<button id="button4id" name="button4id"							class="btn btn-info bigbutton2" type="submit" class="close"							data-dismiss="modal" aria-hidden="true">
							<span translate>Back</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="humanresourceSAA" tabindex="-1"		role="dialog">
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
						<button id="submitData" name="button3id"							class="btn btn-info bigbutton" type="submit"							ng-click="saveCCISAA()" class="close" data-dismiss="modal"							aria-hidden="true" data-toggle="modal">
							<span translate>Submit</span>
						</button>
						<button id="button4id" name="button4id"							class="btn btn-info bigbutton2" type="submit" class="close"							data-dismiss="modal" aria-hidden="true">
							<span translate>Back</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="humanresourceOS" tabindex="-1"		role="dialog">
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
						<button id="submitData" name="button3id"							class="btn btn-info bigbutton" type="submit"							ng-click="saveCCIOS()" class="close" data-dismiss="modal"							aria-hidden="true" data-toggle="modal">
							<span translate>Submit</span>
						</button>
						<button id="button4id" name="button4id"							class="btn btn-info bigbutton2" type="submit" class="close"							data-dismiss="modal" aria-hidden="true">
							<span translate>Back</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="alertModal1" tabindex="-1" role="dialog"		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been submitted successfully.</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button5id"							class="btn btn-info bigbutton2" type="submit" class="close"							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">
							<span translate>Ok</span>
						</button>
						<button id="button5id" name="button5id" class="btn btn-info bigbutton2" 							type="submit" class="close" data-dismiss="modal" aria-hidden="true" 							ng-click="printCCIHumanResourceSubmitData()">
							<span translate>Print</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="alertModal2" tabindex="-1" role="dialog"		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been submitted successfully.</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button5id"							class="btn btn-info bigbutton2" type="submit" class="close"							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">
							<span translate>Ok</span>
						</button>
						<button id="button5id" name="button5id" class="btn btn-info bigbutton2" 							type="submit" class="close" data-dismiss="modal" aria-hidden="true" 							ng-click="printCCISAAHumanResourceSubmitData()">
							<span translate>Print</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="alertModal3" tabindex="-1" role="dialog"		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been submitted successfully.</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button5id"							class="btn btn-info bigbutton2" type="submit" class="close"							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">
							<span translate>Ok</span>
						</button>
						<button id="button5id" name="button5id" class="btn btn-info bigbutton2" 							type="submit" class="close" data-dismiss="modal" aria-hidden="true" 							ng-click="printCCIOSHumanResourceSubmitData()">
							<span translate>Print</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="nocci" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content"				style="border-radius: 0px; padding-top: 40px; padding-bottom: 40px;">
				<div class="modal-body">
					<p style="text-align: center; font-size: 18px;font-weight:bold;">
						<span translate>No CCIs available.</span>
						<br>
						</p>
						<button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">
							<span translate>OK</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 	<div class="modal fade" id="hrsaa" tabindex="-1" role="dialog"> -->
		<!-- 		<div class="modal-dialog"> -->
		<!-- 			<div class="modal-content" -->
		<!-- 				style="border-radius: 0px; padding-top: 40px; padding-bottom: 40px;"> -->
		<!-- 				<div class="modal-body"> -->
		<!-- 					<p style="text-align: center; font-size: 18px;font-weight:bold;"> -->
		<!-- 						Data is not available.<br> -->
		<!-- 					</p> -->
		<!-- 					<button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- 	</div> -->
		<div class="box-border box-border-padding">
			<!-- <hr>
				<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 						
				<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
				<hr> -->
					<div class="grey-header marginTop"			style="border-top: none; margin-top: 4px;">
						<span translate>HUMAN RESOURCE DETAILS</span>
					</div>
					<div class="fosterSelection ">
						<input type="radio" name="humanResource" class="fosterRadioBtn"				ng-click="eraseData('cci')" ng-checked="true" id=""				ng-model="humanResource" ng-value="'CCI'" data-toggle="modal"				data-target="#hrcci" ng-disabled='firstRadioDisable'>
							<label style="margin-right: 70px; margin-left: 2px;">
								<span translate>HR CCI</span>
							</label>
							<input				type="radio" name="humanResource" class="fosterRadioBtn"				ng-click="eraseData('saa')" id="secondRadio" ng-value="'SAA'"				ng-model="humanResource" data-toggle="modal" data-target="#hrsaa" ng-disabled='secondRadioDisable'>
								<label style="margin-right: 70px; margin-left: 2px;">
									<span translate>HR SAA</span>
								</label>
								<input				type="radio" name="humanResource" class="fosterRadioBtn"				ng-click="eraseData('os')" id="thirdRadio" ng-model="humanResource"				ng-checked="thirdradioChecked" ng-value="'OS'" ng-disabled='thirdRadioDisable'>
									<label style="margin-left: 2px;">
										<span translate>HR OS</span>
									</label>
								</div>
								<!-- 			start HR CCI -->
								<form ng-show="humanResource == 'CCI'"			class="form-horizontal basicchildform" name="humanResourceCCI"			id="humanResourceCCI">
									<fieldset>
										<div class="form-group box-border-padding">
											<label class="col-md-4 control-label" for="textinput">
												<span translate>1.Names of the Districts</span>
											</label>
											<div class="col-md-7">
												<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="district.name" readonly							placeholder="{{'Enter name of districts'| translate}}"							class="form-control input-md" type="text">
												</div>
											</div>
											<div class="form-group box-border-padding">
												<label class="col-md-4 control-label" for="textinput">
													<span translate>2.Name of CCI</span>
													<span class="mandatory_star">&#42;</span>
												</label>
												<div class="col-md-7">
													<select id="producedBeforeCwc" name="producedBeforeCwc"							class="form-control" ng-model="ccihrFormInfo.nameOfCCI" required>
														<option value="" disabled selected>
															<span translate>Select CCI</span>
														</option>
														<!--  <option> CCI</option> -->
														<option ng-repeat="cci in cciLists" ng-value="{{cci}}">{{cci.name}}</option>
													</select>
												</div>
												<div id="producedBeforeCwcerror" class="error-style"></div>
											</div>
											<div class="form-group box-border-padding">
												<label class="col-md-4 control-label" for="textinput">
													<span translate>3.Officer In charge /Superintendent</span>
												</label>
												<div class="col-md-7">
													<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.officerIncharge_Superintendent"							placeholder="{{'Enter no of Officer in charge/superintendent'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
													</div>
												</div>
												<div class="form-group box-border-padding">
													<label class="col-md-4 control-label" for="textinput">
														<span translate>4.Counselor</span>
													</label>
													<div class="col-md-7">
														<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.counselor"							placeholder="{{'Enter no of Counselor'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
														</div>
													</div>
													<div class="form-group box-border-padding">
														<label class="col-md-4 control-label" for="textinput">
															<span translate>5.Probation officer/Case Worker/Child Welfare Officer</span>
														</label>
														<div class="col-md-7">
															<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.po_so_cwo"							placeholder="{{'Enter no of officers'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
															</div>
														</div>
														<div class="form-group box-border-padding">
															<label class="col-md-4 control-label" for="textinput">
																<span translate>6.House Mother/Father</span>
															</label>
															<div class="col-md-7">
																<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.houseMother_Father"							placeholder="{{'Enter no of house mother/father'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																</div>
															</div>
															<div class="form-group box-border-padding">
																<label class="col-md-4 control-label" for="textinput">
																	<span translate>7.Para medical staff</span>
																</label>
																<div class="col-md-7">
																	<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.paraMedicalStaff"							placeholder="{{'Enter no of staff'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																	</div>
																</div>
																<div class="form-group box-border-padding">
																	<label class="col-md-4 control-label" for="textinput">
																		<span translate>8.Store Keeper cum Accountant</span>
																	</label>
																	<div class="col-md-7">
																		<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.storeKeeperCumAccountan"							placeholder="{{'Enter no of store keeper cum accountant'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																		</div>
																	</div>
																	<div class="form-group box-border-padding">
																		<label class="col-md-4 control-label" for="textinput">
																			<span translate>9.Cook</span>
																		</label>
																		<div class="col-md-7">
																			<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.cook" placeholder="{{'Enter no of cook'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																			</div>
																		</div>
																		<div class="form-group box-border-padding">
																			<label class="col-md-4 control-label" for="textinput">
																				<span translate>10.Assistant Cook</span>
																			</label>
																			<div class="col-md-7">
																				<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.asstCook"							placeholder="{{'Enter no of assistant cook'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																				</div>
																			</div>
																			<div class="form-group box-border-padding">
																				<label class="col-md-4 control-label" for="textinput">
																					<span translate>11.House Keeper</span>
																				</label>
																				<div class="col-md-7">
																					<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.houseKeeper"							placeholder="{{'Enter no of house keeper'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																					</div>
																				</div>
																				<div class="form-group box-border-padding">
																					<label class="col-md-4 control-label" for="textinput">
																						<span translate>12.Educator(Volunteer /part time)</span>
																					</label>
																					<div class="col-md-7">
																						<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.educator"							placeholder="{{'Enter no of educator'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																						</div>
																					</div>
																					<div class="form-group box-border-padding">
																						<label class="col-md-4 control-label" for="textinput">
																							<span translate>13.MBBS Doctor (Volunteer/ Part time)</span>
																						</label>
																						<div class="col-md-7">
																							<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.mbbsDoctor"							placeholder="{{'Enter no of doctor'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																							</div>
																						</div>
																						<div class="form-group box-border-padding">
																							<label class="col-md-4 control-label" for="textinput">
																								<span translate>14.Art &amp; Craft cum Music teacher (Volunteer/ Part time)</span>
																							</label>
																							<div class="col-md-7">
																								<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.artCraftMusicTeacher"							placeholder="{{'Enter no of teacher'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																								</div>
																							</div>
																							<div class="form-group box-border-padding">
																								<label class="col-md-4 control-label" for="textinput">
																									<span translate>15.PT Instructor cum Yoga Teacher (Volunteer/ Part time)</span>
																								</label>
																								<div class="col-md-7">
																									<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.ptInstructorYogaTeacher"							placeholder="{{'Enter no of PT instructor cum yoga teacher'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																									</div>
																								</div>
																								<div class="form-group box-border-padding">
																									<label class="col-md-4 control-label" for="textinput">
																										<span translate>16.Special Educator/ Therapist</span>
																									</label>
																									<div class="col-md-7">
																										<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.special_Educator_Therapist"							placeholder="{{'Enter no of special educator/therapist'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																										</div>
																									</div>
																									<div class="form-group box-border-padding">
																										<label class="col-md-4 control-label" for="textinput">
																											<span translate>17.Female Nurse</span>
																										</label>
																										<div class="col-md-7">
																											<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.female_Nurse"							placeholder="{{'Enter no of  female nurse'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																											</div>
																										</div>
																										<div class="form-group box-border-padding">
																											<label class="col-md-4 control-label" for="textinput">
																												<span translate>18.Care Taker cum Vocational Instructor</span>
																											</label>
																											<div class="col-md-7">
																												<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.careTaker_VocationalInstructor"							placeholder="{{'Enter no of care taker cum vocational instructor'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																												</div>
																											</div>
																											<div class="form-group box-border-padding">
																												<label class="col-md-4 control-label" for="textinput">
																													<span translate>19.Other</span>
																												</label>
																												<div class="col-md-7">
																													<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccihrFormInfo.others"							placeholder="{{'Other'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																													</div>
																												</div>
																												<div style="text-align: center">
																													<button id="button1id" name="button1id"						ng-click="humanResourceCCI.$invalid ? '' : validateCCI()"						class="btn btn-info" type="submit">
																														<span translate>Submit</span>
																													</button>
																													<!-- 								<button id="button2id" name="button2id" class="btn otherbut" type="reset" >RESET</button> -->
																												</div>
																											</fieldset>
																										</form>
																										<!-- 					end of HR CCI -->
																										<!-- 					start of HR SAA -->
																										<form ng-show="humanResource == 'SAA'"			class="form-horizontal basicchildform" name="hrSSAdetail"			id="hrSSAdetail">
																											<fieldset>
																												<div class="form-group box-border-padding">
																													<label class="col-md-4 control-label" for="textinput">
																														<span translate>1.Names of the Districts</span>
																													</label>
																													<div class="col-md-7">
																														<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="district.name" readonly							placeholder="{{'Enter name of districts'| translate}}"							class="form-control input-md" type="text">
																														</div>
																													</div>
																													<div class="form-group box-border-padding">
																														<label class="col-md-4 control-label" for="textinput">
																															<span translate>2.Name of SAA</span>
																															<span class="mandatory_star">&#42;</span>
																														</label>
																														<div class="col-md-7">
																															<select id="producedBeforeCwc" name="producedBeforeCwc"							class="form-control" ng-model="ccisaaFormInfo.nameOfSAA" required>
																																<option value="" disabled selected>
																																	<span translate>Select SAA</span>
																																</option>
																																<option ng-repeat="cci in cciList" ng-if="cci.typeId==4"								ng-value="{{cci}}">{{cci.name}}</option>
																															</select>
																														</div>
																														<div id="producedBeforeCwcerror" class="error-style"></div>
																													</div>
																													<div class="form-group box-border-padding">
																														<label class="col-md-4 control-label" for="textinput">
																															<span translate>3.Project Coordinator</span>
																														</label>
																														<div class="col-md-7">
																															<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.project_Coordinator"							placeholder="{{'Enter no of  project coordinator'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																															</div>
																														</div>
																														<div class="form-group box-border-padding">
																															<label class="col-md-4 control-label" for="textinput">
																																<span translate>4.Programme Manager</span>
																															</label>
																															<div class="col-md-7">
																																<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.programme_Manager"							placeholder="{{'Enter no of programme manager'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																</div>
																															</div>
																															<div class="form-group box-border-padding">
																																<label class="col-md-4 control-label" for="textinput">
																																	<span translate>5.Social worker cum Early Child hood educator</span>
																																</label>
																																<div class="col-md-7">
																																	<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.social_worker_Cum_Early_ChildHood_Educator"							placeholder="{{'Enter no of social worker'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																	</div>
																																</div>
																																<div class="form-group box-border-padding">
																																	<label class="col-md-4 control-label" for="textinput">
																																		<span translate>6.Nurse</span>
																																	</label>
																																	<div class="col-md-7">
																																		<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.nurse" placeholder="{{'Enter no of nurse'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																		</div>
																																	</div>
																																	<div class="form-group box-border-padding">
																																		<label class="col-md-4 control-label" for="textinput">
																																			<span translate>7.Part time Doctor (Child Specialist)</span>
																																		</label>
																																		<div class="col-md-7">
																																			<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.partTime_Doctor_Child_Specialist"							placeholder="{{'Enter no of doctor'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																			</div>
																																		</div>
																																		<div class="form-group box-border-padding">
																																			<label class="col-md-4 control-label" for="textinput">
																																				<span translate>8.Ayah</span>
																																			</label>
																																			<div class="col-md-7">
																																				<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.ayah" placeholder="{{'Enter no of  ayah'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																				</div>
																																			</div>
																																			<div class="form-group box-border-padding">
																																				<label class="col-md-4 control-label" for="textinput">
																																					<span translate>9.Chowkidar</span>
																																				</label>
																																				<div class="col-md-7">
																																					<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.chowkidar"							placeholder="{{'Enter no of chowkidar'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																					</div>
																																				</div>
																																				<div class="form-group box-border-padding">
																																					<label class="col-md-4 control-label" for="textinput">
																																						<span translate>10.Others</span>
																																					</label>
																																					<div class="col-md-7">
																																						<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="ccisaaFormInfo.others"							placeholder="{{'Others'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																						</div>
																																					</div>
																																					<div style="text-align: center">
																																						<button id="button1id" name="button1id"						ng-click="hrSSAdetail.$invalid ? '' : validateSSA()"						class="btn btn-info" type="submit" data-toggle="modal">
																																							<span translate>Submit</span>
																																						</button>
																																						<button ng-if="fosterDisable" id="button2id" name="button2id"						class="btn btn-info" ng-click="printOldFosterCareSubmitData()">
																																							<span translate>Print</span>
																																						</button>
																																						<!-- 								<button id="button2id" name="button2id" class="btn otherbut" type="reset" >RESET</button> -->
																																					</div>
																																				</fieldset>
																																			</form>
																																			<!-- 			end of HR SAA -->
																																			<!-- 				start of HR OS -->
																																			<form ng-show="humanResource == 'OS'"			class="form-horizontal basicchildform" name="hrOSDetail"			id="hrOSDetail">
																																				<fieldset>
																																					<div class="form-group box-border-padding">
																																						<label class="col-md-4 control-label" for="textinput">
																																							<span translate>1.Name of the District</span>
																																						</label>
																																						<div class="col-md-7">
																																							<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="district.name" readonly							placeholder="{{'Enter name of district'| translate}}"							class="form-control input-md" type="text">
																																							</div>
																																						</div>
																																						<div class="form-group box-border-padding">
																																							<label class="col-md-4 control-label" for="textinput">
																																								<span translate>2.Name of OS</span>
																																								<span class="mandatory_star">&#42;</span>
																																							</label>
																																							<div class="col-md-7">
																																								<select id="producedBeforeCwc" name="producedBeforeCwc"							class="form-control" ng-model="cciosFormInfo.nameOfOpenShelter"							required>
																																									<option value="" disabled selected>
																																										<span translate>Select OS</span>
																																									</option>
																																									<option ng-repeat="cci in cciList" ng-if="cci.typeId==13"								ng-value="{{cci}}">{{cci.name}}</option>
																																								</select>
																																							</div>
																																							<div id="producedBeforeCwcerror" class="error-style"></div>
																																						</div>
																																						<div class="form-group box-border-padding">
																																							<label class="col-md-4 control-label" for="textinput">
																																								<span translate>3.Project Coordinator cum Counselor</span>
																																							</label>
																																							<div class="col-md-7">
																																								<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="cciosFormInfo.projectCoordinator_cum_Counselor"							placeholder="{{'Enter no of project coordinator cum counselor'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																								</div>
																																							</div>
																																							<div class="form-group box-border-padding">
																																								<label class="col-md-4 control-label" for="textinput">
																																									<span translate>4.Social Worker</span>
																																								</label>
																																								<div class="col-md-7">
																																									<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="cciosFormInfo.social_Worker"							placeholder="{{'Enter no of social worker'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																									</div>
																																								</div>
																																								<div class="form-group box-border-padding">
																																									<label class="col-md-4 control-label" for="textinput">
																																										<span translate>5.Care giver cum bridge course educator</span>
																																									</label>
																																									<div class="col-md-7">
																																										<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="cciosFormInfo.careGiver_Cum_BridgeCourse_Educator"							placeholder="{{'Enter no of care giver cum bridge course educator'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																										</div>
																																									</div>
																																									<div class="form-group box-border-padding">
																																										<label class="col-md-4 control-label" for="textinput">
																																											<span translate>6.Out reach Worker</span>
																																										</label>
																																										<div class="col-md-7">
																																											<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="cciosFormInfo.outReach_Worker"							placeholder="{{'Enter no of Out reach worker'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																											</div>
																																										</div>
																																										<div class="form-group box-border-padding">
																																											<label class="col-md-4 control-label" for="textinput">
																																												<span translate>7.Helper for Cleaning &amp; Cooking</span>
																																											</label>
																																											<div class="col-md-7">
																																												<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="cciosFormInfo.helper_For_Cleaning_Cooking"							placeholder="{{'Enter no of Helper'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																												</div>
																																											</div>
																																											<div class="form-group box-border-padding">
																																												<label class="col-md-4 control-label" for="textinput">
																																													<span translate>8.Others</span>
																																												</label>
																																												<div class="col-md-7">
																																													<input id="withWhomChildFoundName" name="withWhomChildFoundName"							ng-model="cciosFormInfo.others"							placeholder="{{'Others'| translate}}"							class="form-control input-md restrictE" type="number"							only-two-digits>
																																													</div>
																																												</div>
																																												<div style="text-align: center">
																																													<button id="button1id" name="button1id"						ng-click="hrOSDetail.$invalid ? '' : validateOS()"						class="btn btn-info" type="submit" data-toggle="modal">
																																														<span translate>Submit</span>
																																													</button>
																																													<button ng-if="fosterDisable" id="button2id" name="button2id"						class="btn btn-info" ng-click="printOldFosterCareSubmitData()">
																																														<span translate>Print</span>
																																													</button>
																																													<!-- 								<button id="button2id" name="button2id" class="btn otherbut" type="reset" >RESET</button> -->
																																												</div>
																																											</fieldset>
																																										</form>
																																										<!-- end of HR OS -->
																																									</div>
																																								</div>
																																								<script src="resources/js/moment-with-locales.js"></script>
																																								<script src="resources/js/jquery-ui.js"></script>
																																								<script type="text/javascript">	$(document).ready(function() {
																																									$("#button1id").on('submit', function() {			
																																										$('#hrCCIconfirmationModal').modal('show');		
																																										});		
																																									$("#button3id").on('submit', function() {			
																																										$('#childIdModal').modal('show');		
																																										});		// 			
																																										$(document).ready(function() {		// 				
																																											$('#firstRadio').prop('checked', true);		// 			
																																											});		$('input').blur(function() {			
																																												var value = $.trim($(this).val());			
																																												$(this).val(value);		
																																												});		
																																											$('textarea').blur(function() {			
																																												var value = $.trim($(this).val());			
																																												$(this).val(value);		
																																												});	
																																											});</script>
																																								<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"	var="jQueryUiCore" />
																																								<script src="${jQueryUiCore}"></script>