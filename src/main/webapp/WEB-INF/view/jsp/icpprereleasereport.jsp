<div ng-controller="PreReleaseReport" id="preReleaseBody" ng-cloak>
	<div class="modal fade" id="preReleaseConfirmationModal" tabindex="-1"		role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>							Please click on the submit button to save the details.						</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="button3id" name="button3id" class="btn btn-info casemodalSubmit"							type="submit" ng-click="saveData()" class="close"							data-dismiss="modal" aria-hidden="true">
							<span translate>Submit</span>
						</button>
						<button id="button4id" name="button4id" class="btn btn-info"							type="submit" class="close" data-dismiss="modal"							aria-hidden="true">
							<span translate>Back</span>
						</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="icpPersonDetailsCModal" tabindex="-1"		role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>							The form has been saved successfully.						</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button6id" class="btn btn-info"							type="submit" class="close" data-dismiss="modal"							aria-hidden="true" ng-click="printPreReleaseForm()">
							<span translate>Print</span>
						</button>
						<button id="button5id" name="button5id" class="btn btn-info"							type="submit" class="close" data-dismiss="modal"							aria-hidden="true" ng-click="reDirect()">
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
					<span translate>INDIVIDUAL CARE PLAN</span>
					<br>
						<span translate>FORM 7</span>
						<br>
							<span translate>				[Rules 11(3), 13(7)(vi), 13(8)(ii), 19(4), 19(17), 62(6)(vii), 62(6)(x), 69 I (3)]</span>
							<br>
								<span translate>				Child in Conflict with Law/Child in Need of Care and Protection</span>
							</div>
							<form class="form-horizontal basicchildform" name="preReleaseReport"			id="preReleaseReport" enctype="multipart/form-data">
								<fieldset>
									<div class="grey-header" style="border-top: none; margin-top: 4px;">
										<span translate>PRE-RELEASE REPORT</span>
									</div>
									<div class="col-md-12 summaryspace">
										<div class="col-md-6 childidheader"						style="margin-left: 0px !important; padding-left: 0px !important;">
											<div class="social_headng">
												<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" />
												<span>
													<span translate>Child ID:</span> 	&nbsp;&nbsp;${selectedChild} 
												</span>
											</div>
										</div>
										<div class="col-md-6 childnameheader"						style="margin-right: 0px !important; padding-right: 0px !important;">
											<div class="social_headng1">
												<span>
													<span translate>Name of Child:</span>								&nbsp;&nbsp; {{prefetchData.childName}}							
												</span>
											</div>
										</div>
									</div>
									<div class="form-group box-border-padding interimPlanmargintop">
										<label class="col-md-4 control-label" for="textinput">
											<span translate>1. Choose transfer/release</span>
											<span class="mandatory_star">&#42;</span>
										</label>
					<div class="col-md-7">
						<select id="transferplace" name="transferplace"
							ng-if="!preReleaseView" class="form-control"
							ng-model="$parent.formInfo.orderType" required
							ng-options="order as lang=='en'?order.name:order.typeNameHindi for order in orderType">
							<option value="" disabled selected>{{'Select' |	translate}}</option>
						</select> <input class="form-control input-md" type="text"
							ng-model="lang=='en'?formInfo.orderType.name:formInfo.orderType.typeNameHindi" ng-if="preReleaseView"
							readonly>
					</div>
				</div>
										<div class="form-group box-border-padding">
											<label class="col-md-4 control-label" for="textinput">
												<span translate>							2. Details of place of transfer						</span>
												<span class="mandatory_star">&#42;</span>
											</label>
											<div class="col-md-7">
												<input id="transferDetails" maxlength="90"							placeholder="{{'Enter details of place of transfer/release'| translate}}"							class="form-control input-md" type="text"							ng-model="formInfo.placeOfTransfer" required							ng-disabled="preReleaseView">
												</div>
											</div>
											<div class="form-group box-border-padding">
												<label class="col-md-4 control-label" for="textinput">
													<span translate>							3. Details of authority concerned responsible in the place of transfer/release						</span>
													<span class="mandatory_star">&#42;</span>
												</label>
												<div class="col-md-7" style="padding-top:12px;">
													<input id="authorityDetails" maxlength="200"							placeholder="{{'Enter details of authority'| translate}}"							class="form-control input-md" type="text"							ng-model="formInfo.authorityName" required							ng-disabled="preReleaseView">
													</div>
												</div>
												<div class="form-group box-border-padding">
													<label class="col-md-4 control-label" for="textinput">
														<span translate>							4. Details of placement of the child in different institutions/family						</span>
														<span class="mandatory_star">&#42;</span>
													</label>
													<div class="col-md-7" style="padding-top:12px;">
														<input id="placementDetails" maxlength="200"							placeholder="{{'Enter details of placement'| translate}}"							class="form-control input-md" type="text" required							ng-model="formInfo.institutionDetails"							ng-disabled="preReleaseView">
														</div>
													</div>
													<div class="form-group box-border-padding">
														<label class="col-md-4 control-label" for="textinput">
															<span translate>							5. Training undergone and skills acquired						</span>
															<span class="mandatory_star">&#42;</span>
														</label>
														<div class="col-md-7" style="padding-top:12px;">
															<input id="training" maxlength="200"							placeholder="{{'Enter training undergone and skills acquired'| translate}}"							class="form-control input-md" type="text"							ng-model="formInfo.skillsAcquired" required							ng-disabled="preReleaseView">
															</div>
														</div>
														<div class="form-group box-border-padding">
															<label class="col-md-4 control-label" for="textinput">
																<span translate>							6. Last progress report of the child (to be attached, refer Part B)						</span>
															</label>
															<!-- 					<div class="col-md-3"> -->
															<!-- 						<input ng-disabled="preReleaseView" type="file" name="files[]" id="js-upload-files" multiple> -->
															<!-- 					</div> -->
															<!-- 					<div class="col-md-3 upload-relese-button" style="float:right;"> -->
															<div class="col-md-3" style="padding-top:12px;">
																<button type="button" class="btn btn-sm btn-primary" required							id="" ng-click="printLatestReport()">
																	<span translate>Download</span>
																</button>
															</div>
															<!-- </div> -->
														</div>
														<div class="form-group box-border-padding">
															<label class="col-md-4 control-label" for="textinput">
																<span translate>7. Father's Name</span>
															</label>
															<div class="col-md-7">
																<input id="fathername" placeholder="" readonly ng-model="icpAdata.fatherName"							class="form-control input-md"  type="text">
																</div>
															</div>
															<div class="grey-header">
																<span translate>						8. Rehabilitation and restoration plan of the child ( to be prepared with reference to progress reports of the child)					</span>
																<span class="mandatory_star">&#42;</span>
															</div>
															<div class="form-group box-border-padding">
																<label class="col-md-4 control-label" for="textinput">
																	<span translate>(i) Category 1</span>
																	<br>
																		<span translate>							Child's expectation from care and protection</span>
																		<span class="mandatory_star">&#42;</span>
																	</label>
																	<div class="col-md-7">
																		<label class="control-label" for="textinput">
																			<span translate>								Rehabilitation and restoration plan of the child							</span>
																		</label>
																		<input id="category1" maxlength="200"							class="form-control input-md" type="text" required							ng-model="formInfo.cat1rrpc" ng-disabled="preReleaseView">
																		</div>
																	</div>
																	<div class="form-group box-border-padding">
																		<label class="col-md-4 control-label" for="textinput">
																			<span translate>(ii) Category 2</span>
																			<br>
																				<span translate>Health and nutrition</span>
																				<span class="mandatory_star">&#42;</span>
																			</label>
																			<div class="col-md-7">
																				<label class="control-label" for="textinput">
																					<span translate>								Rehabilitation and restoration plan of the child							</span>
																				</label>
																				<input id="category2" ng-disabled="preReleaseView" maxlength="195"							class="form-control input-md" type="text" required							ng-model="formInfo.cat2rrpc">
																				</div>
																			</div>
																			<div class="form-group box-border-padding">
																				<label class="col-md-4 control-label" for="textinput">
																					<span translate>(iii) Category 3</span>
																					<br>
																						<span translate>							Emotional and psychological</span>
																						<span class="mandatory_star">&#42;</span>
																					</label>
																					<div class="col-md-7">
																						<label class="control-label" for="textinput">
																							<span translate>								Rehabilitation and restoration plan of the child							</span>
																						</label>
																						<input id="category3" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat3rrpc">
																						</div>
																					</div>
																					<div class="form-group box-border-padding">
																						<label class="col-md-4 control-label" for="textinput">
																							<span translate>(iv) Category 4</span>
																							<br>
																								<span translate>Educational and Training</span>
																								<span class="mandatory_star">&#42;</span>
																							</label>
																							<div class="col-md-7">
																								<label class="control-label" for="textinput">
																									<span translate>								Rehabilitation and restoration plan of the child							</span>
																								</label>
																								<input id="category4" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat4rrpc">
																								</div>
																							</div>
																							<div class="form-group box-border-padding">
																								<label class="col-md-4 control-label" for="textinput">
																									<span translate>(v) Category 5</span>
																									<br>
																										<span translate>							Leisure, creativity and play</span>
																										<span class="mandatory_star">&#42;</span>
																									</label>
																									<div class="col-md-7" >
																										<label class="control-label" for="textinput">
																											<span translate>								Rehabilitation and restoration plan of the child							</span>
																										</label>
																										<input id="category5" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat5rrpc">
																										</div>
																									</div>
																									<div class="form-group box-border-padding">
																										<label class="col-md-4 control-label" for="textinput">
																											<span translate>(vi) Category 6</span>
																											<br>
																												<span translate>							Attachments and Inter-personal Relationships</span>
																												<span class="mandatory_star">&#42;</span>
																											</label>
																											<div class="col-md-7">
																												<label class="control-label" for="textinput">
																													<span translate>								Rehabilitation and restoration plan of the child							</span>
																												</label>
																												<input id="category6" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat6rrpc">
																												</div>
																											</div>
																											<div class="form-group box-border-padding">
																												<label class="col-md-4 control-label" for="textinput">
																													<span translate>(vii) Category 7</span>
																													<br>
																														<span translate>Religious belief</span>
																														<span class="mandatory_star">&#42;</span>
																													</label>
																													<div class="col-md-7">
																														<label class="control-label" for="textinput">
																															<span translate>								Rehabilitation and restoration plan of the child							</span>
																														</label>
																														<input id="category7" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat7rrpc">
																														</div>
																													</div>
																													<div class="form-group box-border-padding">
																														<label class="col-md-4 control-label" for="textinput">
																															<span translate>(viii) Category 8</span>
																															<br>
																																<span translate>							Self care and life skill training for Protection from all kinds of abuse, neglect and maltreatment</span>
																																<span class="mandatory_star">&#42;</span>
																															</label>
																															<div class="col-md-7">
																																<label class="control-label" for="textinput">
																																	<span translate>								Rehabilitation and restoration plan of the child							</span>
																																</label>
																																<input id="category8" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat8rrpc">
																																</div>
																															</div>
																															<div class="form-group box-border-padding">
																																<label class="col-md-4 control-label" for="textinput">
																																	<span translate>(ix) Category 9</span>
																																	<br>
																																		<span translate>Independent living skill</span>
																																		<span class="mandatory_star">&#42;</span>
																																	</label>
																																	<div class="col-md-7">
																																		<label class="control-label" for="textinput">
																																			<span translate>								Rehabilitation and restoration plan of the child							</span>
																																		</label>
																																		<input id="category9" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat9rrpc">
																																		</div>
																																	</div>
																																	<div class="form-group box-border-padding">
																																		<label class="col-md-4 control-label" for="textinput">
																																			<span translate>(x) Category 10</span>
																																			<br>
																																				<span translate>Any other</span>
																																				<span class="mandatory_star">&#42;</span>
																																			</label>
																																			<div class="col-md-7">
																																				<label class="control-label" for="textinput">
																																					<span translate>								Rehabilitation and restoration plan of the child							</span>
																																				</label>
																																				<input id="category10" ng-disabled="preReleaseView" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.cat10rrpc">
																																				</div>
																																			</div>
																																			<div class="form-group box-border-padding">
																																				<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput">
																																					<span translate>9. Date of Release/Transfer</span>
																																					<span class="mandatory_star">&#42;</span>
																																				</label>
																																				<div class="col-md-3 col-sm-6 col-xs-6">
																																					<input type="text" id="datepicker9" readonly class="form-control"							ng-model="formInfo.dateOfOrder" ng-disabled="preReleaseView">
																																					</div>
																																					<i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
																																				</div>
																																				<div class="form-group box-border-padding">
																																					<label class="col-md-4 control-label" for="textinput">
																																						<span translate>							10. Requisition for escort if required						</span>
																																					</label>
																																					<div class="col-md-7">
																																						<input id="escort" placeholder="{{'Enter requisition for escort'| translate}}"							class="form-control input-md" type="text" maxlength="90"							ng-model="formInfo.requisitionForEscort"							ng-disabled="preReleaseView">
																																						</div>
																																					</div>
																																					<div class="form-group box-border-padding">
																																						<label class="col-md-4 control-label" for="textinput">
																																							<span translate>							11. Type of Identification Proof						</span>
																																						</label>
																																						<div class="col-md-7">
																																							<select id="idType" name="idType" class="form-control"							ng-options="idProof as lang=='en'?idProof.name:idProof.typeNameHindi for idProof in idProofList"							ng-model="formInfo.identificationProofType"							ng-change="idProofTypeChange()"							ng-if="!preReleaseView">
																																								<option value="" disabled selected>
																																									{{'Select' | translate}}
																																								</option>
																																							</select>
																																							<input placeholder="" class="form-control input-md" type="text"							ng-model="lang=='en'?formInfo.identificationProofType.name:formInfo.identificationProofType.typeNameHindi"							ng-if="preReleaseView" readonly>
																																							</div>
																																						</div>
																																						<div class="form-group box-border-padding" ng-if="formInfo.identificationProofType.id == 156">
																																							<label class="col-md-4 control-label" for="textinput">
																																								<span translate>Please Specify</span>
																																								<span class="mandatory_star">&#42;</span>
																																							</label>
																																							<div class="col-md-7">
																																								<input id="idTypeOther" ng-disabled="preReleaseView"							placeholder="{{'Please specify type of id proof'| translate}}" required							class="form-control input-md" type="text" maxlength="60"							ng-model="formInfo.otherIdentificationProof">
																																								</div>
																																							</div>
																																							<div class="form-group box-border-padding">
																																								<label class="col-md-4 control-label" for="textinput">
																																									<span translate>							12. Identification Proof Number						</span>
																																								</label>
																																								<div class="col-md-7">
																																									<input id="identification"							placeholder="{{'Enter identification proof of escort'| translate}}"							class="form-control input-md" type="text" maxlength="30"							ng-model="formInfo.identificationProofNumber"							ng-disabled="preReleaseView"							>
																																									</div>
																																								</div>
																																								<div class="form-group box-border-padding">
																																									<label class="col-md-4 control-label" for="textinput">
																																										<span translate>							13. Recommended rehabilitation plan including possible placements/sponsorships						</span>
																																										<span class="mandatory_star">&#42;</span>
																																									</label>
																																									<div class="col-md-7" style="padding-top:12px;">
																																										<input id="rehabilitation" placeholder="{{'Enter rehabilitation plan'| translate}}" required							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.rehabilitationPlan"							ng-disabled="preReleaseView">
																																										</div>
																																									</div>
																																									<div class="form-group box-border-padding">
																																										<label class="col-md-4 control-label" for="textinput">
																																											<span translate>							14. Details of Probation Officer/non-governmental organization for post-release followup						</span>
																																											<span class="mandatory_star">&#42;</span>
																																										</label>
																																										<div class="col-md-7" style="padding-top:12px;">
																																											<input id="poDetails"							placeholder="{{'Enter details of Probation Officer/non-governmental organization'| translate}}"							class="form-control input-md" type="text" required							ng-model="formInfo.officerOrNgoName" maxlength="200"							ng-disabled="preReleaseView">
																																											</div>
																																										</div>
																																										<div class="form-group box-border-padding">
																																											<label class="col-md-4 control-label" for="textinput">
																																												<span translate>							15. Memorandum of Understanding with non-governmental organisation identified for post-release follow up (Attach a copy)						</span>
																																												<span class="mandatory_star">&#42;</span>
																																											</label>
																																											<div class="col-md-3" style="padding-top:12px;">
																																												<input ng-hide="preReleaseView" type="file" name="mouWithNGo[]" required id="js-upload-files" multiple							ng-files="getReport($files,'mouWithNgo')" accept=".pdf">
																																												</div>
																																												<div class="col-md-1">
																																													<button id="button2id" name="button2id" class="btn otherbut" style="margin-top: 20px;"							ng-click="downloadImg(formInfo.mouWithNgo,'MOU With NGO')"							ng-if="formInfo.mouWithNgoPath || preReleaseView" class="close">
																																														<span translate>Download</span>
																																													</button>
																																												</div>
																																											</div>
																																											<div class="form-group box-border-padding">
																																												<label class="col-md-4 control-label" for="textinput">
																																													<span translate>							16. Details of sponsorship agency/individual sponsor, if any						</span>
																																												</label>
																																												<div class="col-md-7" style="padding-top:12px;">
																																													<input id="sponsorDetails"							placeholder="{{'Enter details of sponsorship agency/individual sponsor'| translate}}"							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.detailOfSponsors"							ng-disabled="preReleaseView">
																																													</div>
																																												</div>
																																												<div class="form-group box-border-padding">
																																													<label class="col-md-4 control-label" for="textinput">
																																														<span translate>							17. Memorandum of Understanding between the sponsoring agency and individual sponsor(Attach a copy)						</span>
																																													</label>
																																													<div class="col-md-3" style="padding-top:12px;">
																																														<input ng-hide="preReleaseView" type="file" name="mouOfSponsors[]" id="js-upload-files" multiple 							ng-files="getReport($files,'mouOfSponsors')" accept=".pdf">
																																														</div>
																																														<div class="col-md-1" >
																																															<button id="button2id" name="button2id" class="btn otherbut" style="margin-top: 20px;"							ng-click="downloadImg(formInfo.mouOfSponsors,'MOU With Sponsors Individual/Agency')"							ng-if="formInfo.mouOfSponsors && preReleaseView" class="close">
																																																<span translate>Download</span>
																																															</button>
																																														</div>
																																														<label class="col-md-7 control-label" ng-if="!formInfo.mouOfSponsors && preReleaseView" style="text-align: center !important;">
																																															<span translate>No data available</span>
																																														</label>
																																													</div>
																																													<div class="form-group box-border-padding">
																																														<label class="col-md-4 control-label" for="textinput">
																																															<span translate>							18. Medical examination report before release						</span>
																																															<span class="mandatory_star">&#42;</span>
																																														</label>
																																														<div class="col-md-3" style="padding-top:12px;">
																																															<input ng-hide="preReleaseView" type="file" required name="medicalReport[]" id="js-upload-files" multiple ng-files="getReport($files,'medicalReport')" accept=".pdf">
																																															</div>
																																															<div class="col-md-1" >
																																																<button id="button2id" name="button2id" class="btn otherbut" style="margin-top: 12px;"							ng-click="downloadImg(formInfo.medicalReport,'Medical Examination Report')"							ng-if="formInfo.medicalReportPath || preReleaseView" class="close">
																																																	<span translate>Download</span>
																																																</button>
																																															</div>
																																														</div>
																																														<div class="form-group box-border-padding">
																																															<label class="col-md-4 control-label" for="textinput">
																																																<span translate>19. Any other information</span>
																																															</label>
																																															<div class="col-md-7">
																																																<input id="otherInfo" placeholder="{{'Enter any other information'| translate}}"							class="form-control input-md" type="text"							ng-model="formInfo.otherInfo" maxlength="200"							ng-disabled="preReleaseView">
																																																</div>
																																															</div>
																																															<div style="text-align: center">
																																																<button id="button1id" name="button1id" class="btn btn-info"						type="submit" ng-click="preReleaseReport.$invalid ? '' : validateForm()"						ng-if="!preReleaseView">
																																																	<span translate>Submit</span>
																																																</button>
																																																<button id="button2id" name="button2id" class="btn btn-info" type="submit"						ng-click="printPreReleaseForm()" ng-if="preReleaseView">
																																																	<span translate>Print</span>
																																																</button>
																																															</div>
																																														</fieldset>
																																													</form>
																																													<div class="modal fade" id="errorImageICPModal" tabindex="-1"			role="dialog">
																																														<div class="modal-dialog">
																																															<div class="modal-content">
																																																<div class="modal-body">
																																																	<p style="text-align: center">
																																																		<span translate>								Uploaded file is not in correct format.							</span>
																																																		<br>
																																																			<button id="button5id" name="button5id" class="btn btn-info"								type="submit" class="close" data-dismiss="modal"								aria-hidden="true">
																																																				<span translate>Ok</span>
																																																			</button>
																																																		</p>
																																																	</div>
																																																</div>
																																															</div>
																																														</div>
																																														<div class="modal fade" id="noDataAvailable" tabindex="-1"
																																															role="dialog">
																																															<div class="modal-dialog">
																																																<div class="modal-content">
																																																	<div class="modal-body">
																																																		<p style="text-align: center">
																																																			<span translate> No data available. </span>
																																																			<br>
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
																																													<script type="text/javascript">
																																														$(
																																																document)
																																																.ready(
																																																		function() {
																																																			$(
																																																					'input')
																																																					.blur(
																																																							function() {
																																																								var value = $
																																																										.trim($(
																																																												this)
																																																												.val());
																																																								$(
																																																										this)
																																																										.val(
																																																												value);
																																																							});
																																																			$(
																																																					'textarea')
																																																					.blur(
																																																							function() {
																																																								var value = $
																																																										.trim($(
																																																												this)
																																																												.val());
																																																								$(
																																																										this)
																																																										.val(
																																																												value);
																																																							});
																																																		});
																																													</script>
																																													<script src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>
																																													<script type="text/javascript" src="resources/js/jquery-min.js"></script>
																																													<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
																																													<script type="text/javascript" src="resources/js/moment-with-locales.js"></script>
																																													<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
																																													<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"	var="jQueryUiCss" />
																																													<script type="text/javascript" src="resources/js/download.js"></script>
																																												</div>