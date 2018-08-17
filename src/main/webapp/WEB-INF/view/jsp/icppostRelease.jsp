<div ng-controller="PostReleaseController" id="postReleaseDiv" ng-cloak>
	<div class="modal fade" id="postReleaseConfirmationModal" tabindex="-1"		role="dialog" data-backdrop="static">
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
	<div class="modal fade" id="icpPostReleaseModal" tabindex="-1"		role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>							The form has been saved successfully.						</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button6id" class="btn btn-info"							type="submit" class="close" data-dismiss="modal"							aria-hidden="true" ng-click="printicpPersonDetailsDSubmitData()">
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
							<form class="form-horizontal basicchildform" name="postReleaseDetail"			id="postReleaseDetail">
								<fieldset>
									<div class="grey-header marginTop"					style="border-top: none; margin-top: 4px;">
										<span translate>						POST-RELEASE/RESTORATION REPORT OF THE CHILD					</span>
									</div>
									<div class="col-md-12 summaryspace">
										<div class="col-md-6 childidheader"						style="margin-left: 0px !important; padding-left: 0px !important;">
											<div class="social_headng">
												<img src="resources/img/cpis_ccts_Child_ID_SVG.svg" />
												<span>
													<span translate>Child ID</span> 	:&nbsp;&nbsp;${selectedChild} 
												</span>
											</div>
										</div>
										<div class="col-md-6 childnameheader"						style="margin-right: 0px !important; padding-right: 0px !important;">
											<div class="social_headng1">
												<span>
													<span translate>Name of Child:</span>								&nbsp;&nbsp;{{prefetchData.childName}}							
												</span>
											</div>
										</div>
									</div>
									<div class="form-group box-border-padding interimPlanmargintop">
										<label class="col-md-4 control-label" for="textinput">
											<span translate>1. Status of Bank Account</span>
											<span class="mandatory_star">&#42;</span>
										</label>
										<div class="col-md-7">
											<select required id="statusOfBankaccount" name="statusOfBankaccount"							class="form-control" ng-model="formInfo.statusOfBankAccount"							ng-if="!viewPostReleasePage"							ng-options="status as lang=='en'?status.name:status.typeNameHindi for status in bankAccountStatus">
												<option value="" disabled selected>
													{{'Select' | translate}}
												</option>
											</select>
											<input placeholder="" class="form-control input-md"							type="text"	readonly ng-model="lang=='en'?formInfo.statusOfBankAccount.name : formInfo.statusOfBankAccount.typeNameHindi"							ng-if="viewPostReleasePage" />
										</div>
									</div>
									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput">
											<span translate>							2. Are earnings and belongings of the child handed over?						</span>
										</label>
										<div class="col-md-7" ng-if="!viewPostReleasePage">
											<label class="radio-inline" for="radios-0">
												<input							name="radios" id="radios-0" ng-value="trueValue" type="radio"							ng-model="query.belongingsHandedOver" ng-click="clearToWhomField()">
													<span translate>Yes</span>
												</label>
												<label class="radio-inline" for="radios-1">
													<input							name="radios1" id="radios-1" ng-value="falseValue"							checked="checked" type="radio" ng-click="clearToWhomField()"							ng-model="query.belongingsHandedOver">
														<span translate>No</span>
													</label>
												</div>
												<div class="col-md-7" ng-if="viewPostReleasePage">
													<input placeholder="{{formInfo.belongingsHandedToParents.name != null ? 'Yes' : 'No' | translate}}"						class="form-control input-md" type="text" readonly />
												</div>
											</div>
											<div class="form-group box-border-padding"					ng-if="query.belongingsHandedOver || formInfo.belongingsHandedToParents.name">
												<label class="col-md-4 control-label" for="textinput">
													<span translate>To whom</span>
													<span class="mandatory_star">&#42;</span>
												</label>
												<div class="col-md-7">
													<select id="interactionReport" name="interactionReport"							class="form-control" ng-if="!viewPostReleasePage"							ng-model="formInfo.belongingsHandedToParents"							ng-options="whom as lang=='en'?whom.name:whom.typeNameHindi for whom in childBelongingsHandedOverToWhom"							required>
														<option value="" disabled selected>
															{{'Select' | translate}}
														</option>
													</select>
													<input placeholder="" class="form-control input-md"							type="text"	readonly ng-model="lang=='en'?formInfo.belongingsHandedToParents.name : formInfo.belongingsHandedToParents.typeNameHindi"							ng-if="viewPostReleasePage" />
												</div>
											</div>
											<div class="form-group box-border-padding">
												<label class="col-md-4 control-label" for="textinput">
													<span translate>							3. First interaction report by whom						</span>
													<span class="mandatory_star">&#42;</span>
												</label>
												<div class="col-md-7">
													<select id="interactionReport" name="interactionReport"							ng-if="!viewPostReleasePage" required							class="form-control" ng-model="formInfo.interactionReportBy"							ng-options="fInteraction as lang=='en'?fInteraction.name:fInteraction.typeNameHindi for fInteraction in firstInteractionReportByWhom">
														<option value="" disabled selected>
															{{'Select' | translate}}
														</option>
													</select>
													<input placeholder="" class="form-control input-md"							type="text"	readonly ng-model="lang=='en'?formInfo.interactionReportBy.name:formInfo.interactionReportBy.typeNameHindi"							ng-if="viewPostReleasePage" />
												</div>
											</div>
											<div class="form-group box-border-padding">
												<label class="col-md-4 control-label" for="textinput">
													<span translate> 	4. First interaction report of those are identified for follow-up with the child post-release </span>
													<span class="mandatory_star">&#42;					</span>
												</label>
												<div class="col-md-7">
													<label class="control-label" for="textinput"></label>
													<input id="interactionreportChild" maxlength="200"							placeholder="{{viewPostReleasePage == false ? 'Enter first interaction report' : ''  | translate}}"							ng-disabled="viewPostReleasePage" required class="form-control input-md" type="text"							ng-model="formInfo.interactionDetails">
													</div>
												</div>
												<div class="form-group box-border-padding">
													<label class="col-md-4 control-label" for="textinput">
														<span translate>						 	5. Progress made with reference to Rehabilitation and Restoration Plan						 </span>
														<span class="mandatory_star">&#42;</span>
													</label>
													<div class="col-md-7">
														<input id="progressMade" name="progressMade" class="form-control input-md" 							placeholder="{{viewPostReleasePage == false ? 'Enter progress plan' : '' | translate}}"							type="text" ng-disabled="viewPostReleasePage" required maxlength="200"							ng-model="formInfo.progressMade">
														</div>
													</div>
													<div class="form-group box-border-padding">
														<label class="col-md-4 control-label" for="textinput">
															<span translate>6. Family's behavior/attitude towards the child</span>
															<span class="mandatory_star">&#42;</span>
														</label>
														<div class="col-md-7">
															<input required id="familysBehavior" name="familysBehavior"							placeholder="{{viewPostReleasePage == false ? 'Enter Family\'s behavior/attitude towards the child' : '' | translate}}"							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.familyBehaviour" ng-disabled="viewPostReleasePage">
															</div>
														</div>
														<div class="form-group box-border-padding">
															<label class="col-md-4 control-label" for="textinput">
																<span translate>							7. Social milieu of the child, particularly attitude of neighbours/community						</span>
																<span class="mandatory_star">&#42;</span>
															</label>
															<div class="col-md-7">
																<input required id="attitudeofneighbours" name="attitudeofneighbours"							placeholder="{{viewPostReleasePage == false ? 'Enter  attitude of neighbours/community' : '' | translate}}"							class="form-control input-md" type="text" maxlength="200"							ng-disabled="viewPostReleasePage"							ng-model="formInfo.socialMilieu">
																</div>
															</div>
															<div class="form-group box-border-padding">
																<label class="col-md-4 control-label" for="textinput">
																	<span translate>							8. How is the child using the skills acquired						</span>
																	<span class="mandatory_star">&#42;</span>
																</label>
																<div class="col-md-7">
																	<input required id="skillsacquired" name="skillsacquired"							placeholder="{{'Enter how is the child using the skills acquired'| translate}}"							class="form-control input-md" type="text" maxlength="200"							ng-model="formInfo.howUsingSkills" ng-disabled="viewPostReleasePage">
																	</div>
																</div>
																<div>
																	<div class="form-group box-border-padding">
																		<label class="col-md-4 control-label" for="textinput">
																			<span translate>								9. Whether the child has been admitted to a School or vocation?							</span>
																			<span class="mandatory_star">&#42;</span>
																		</label>
																		<div class="col-md-7" ng-if="!viewPostReleasePage">
																			<label class="radio-inline" for="radios-2">
																				<input								name="radios2" id="radios-2" ng-value="trueValue" type="radio"								ng-model="query.childAdmittedToSchool"								ng-click="clearFieldForQ9()">
																					<span translate>Yes</span>
																				</label>
																				<label class="radio-inline" for="radios-3">
																					<input								name="radios3" id="radios-3" ng-value="falseValue" type="radio"								ng-model="query.childAdmittedToSchool" checked="checked"								ng-click="clearFieldForQ9()">
																						<span translate>No</span>
																					</label>
																				</div>
																				<div class="col-md-7" ng-if="viewPostReleasePage">
																					<input placeholder="{{formInfo.childWentToLearningInstitueType.name != null ? 'Yes' : 'No' | translate}}"								class="form-control input-md" type="text" readonly />
																				</div>
																			</div>
																		</div>
																		<div ng-if="query.childAdmittedToSchool || formInfo.childWentToLearningInstitueType.name">
																			<div class="form-group box-border-padding margintopspace">
																				<label class="col-md-4 control-label" for="textinput"></label>
																				<div class="col-md-7">
																					<label class="control-label" for="textinput">
																						<span translate>Specify</span>
																						<span class="mandatory_star">&#42;</span>
																					</label>
																					<div class="form-group box-border-padding marginleft">
																						<select id="stayPeriod" name="stayPeriod" class="form-control"										ng-model="formInfo.childWentToLearningInstitueType"										ng-if="!viewPostReleasePage && query.childAdmittedToSchool" required										ng-options="insti as lang=='en'?insti.name:insti.typeNameHindi for insti in learningInstitute">
																							<option value="" disabled selected>
																								{{'Select' | translate}}
																							</option>
																						</select>
																						<input placeholder="" class="form-control input-md" type="text"										ng-model="lang=='en'?formInfo.childWentToLearningInstitueType.name:formInfo.childWentToLearningInstitueType.typeNameHindi"										ng-if="viewPostReleasePage" readonly />
																					</div>
																				</div>
																			</div>
																			<div class="form-group box-border-padding margintopspace">
																				<label class="col-md-4 control-label" for="textinput"></label>
																				<div class="col-md-3">
																					<label class="control-label marginleftspace" for="textinput">
																						<span translate>Name of the school/institute/any other agency</span>
																						<span class="mandatory_star">&#42;</span>
																					</label>
																					<input id="category1a" placeholder="{{'Enter school/institute name'| translate}}"									class="form-control input-md" type="text" required maxlength="30"									ng-model="formInfo.schoolName" ng-disabled="viewPostReleasePage">
																					</div>
																					<div class="col-md-3 margin-topspace">
																						<label class="control-label" for="textinput">
																							<span translate>Date</span>
																							<span class="mandatory_star">&#42;</span>
																						</label>
																						<input type="text" id="dateOfAdmissionInSchool" readonly									class="form-control" ng-model="formInfo.admissionDate"									ng-disabled="viewPostReleasePage">
																						</div>
																					</div>
																				</div>
																				<div class="form-group box-border-padding">
																					<label class="col-md-4 control-label" for="textinput">
																						<span translate>						10. Report of second and third follow-up interaction with the child after two months and six months respectively							</span>
																					</label>
																					<div class="col-md-7">
																						<input id="interactionwiththeChild" name="interactionwiththeChild" maxlength="200"							placeholder="{{viewPostReleasePage == false ? 'Enter report' : '' | translate}}" 							ng-disabled="viewPostReleasePage" class="form-control input-md" type="text"							ng-model="formInfo.reportOfFollowUp">
																						</div>
																					</div>
																					<div class="form-group box-border-padding">
																						<label class="col-md-4 control-label" for="textinput">
																							<span translate>11. Efforts towards social mainstreaming and child's opinion/views about it</span>
																							<span class="mandatory_star">&#42;</span>
																						</label>
																						<div class="col-md-7">
																							<input required id="mainstreaming" name="mainstreaming" maxlength="200"							placeholder="{{viewPostReleasePage == false ? 'Enter efforts towards social mainstreaming' : '' | translate}}"							class="form-control input-md" type="text"							ng-model="formInfo.childView" ng-disabled="viewPostReleasePage">
																							</div>
																						</div>
																						<div>
																							<div class="grey-header">
																								<span translate>							12. Identity Cards and Compensation						</span>
																							</div>
																							<div class="form-group box-border-padding">
																								<label class="col-md-4 control-label" for="textinput">
																									<span translate>(i) Birth Certificate</span>
																									<span class="mandatory_star">&#42;</span>
																								</label>
																								<div class="col-md-7" ng-if="!viewPostReleasePage">
																									<label class="radio-inline" for="radios-4">
																										<input								name="radios4" id="birthCertificatey" ng-value="true"								type="radio" ng-model="formInfo.idBirthCertificateProduced"								ng-click="clearFieldForQ12(formInfo.idBirthCertificateProduced,'birthC')">
																											<span translate>Yes</span>
																										</label>
																										<label class="radio-inline" for="radios-5">
																											<input								name="radios5" id="birthCertificaten" ng-value="false"								type="radio" ng-model="formInfo.idBirthCertificateProduced"								checked="checked" ng-click="clearFieldForQ12(formInfo.idBirthCertificateProduced,'birthC')">
																												<span translate>No</span>
																											</label>
																										</div>
																										<div class="col-md-7" ng-if="viewPostReleasePage">
																											<input placeholder="{{formInfo.idBirthCertificateProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																										</div>
																									</div>
																									<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idBirthCertificateProduced">
																										<label class="col-md-4 control-label" for="textinput"></label>
																										<div class="col-md-7">
																											<label class="control-label" for="textinput"></label>
																											<input id="identityCardNo" maxlength="30"								placeholder="{{'Enter Birth Certificate No.'| translate}}" required ng-disabled="viewPostReleasePage"								ng-blur="blur(formInfo.idBirthCertificateNum,'idBirthCertificateNum')"												 ng-trim="false"								class="form-control input-md" type="text" ng-model="formInfo.idBirthCertificateNum">
																											</div>
																										</div>
																										<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idBirthCertificateProduced">
																											<label class="col-md-4 control-label" for="textinput"></label>
																											<div class="col-md-7">
																												<label class="control-label" for="textinput"></label>
																												<input id="actionTaken" placeholder="{{'Enter Action Taken'| translate}}" required								ng-disabled="viewPostReleasePage"  maxlength="200"								class="form-control input-md" type="text" ng-model="formInfo.idBirthCertificateNum">
																												</div>
																											</div>
																										</div>
																										<div>
																											<div class="form-group box-border-padding">
																												<label class="col-md-4 control-label" for="textinput">
																													<span translate>(ii) School Certificate</span>
																													<span class="mandatory_star">&#42;</span>
																												</label>
																												<div class="col-md-7" ng-if="!viewPostReleasePage">
																													<label class="radio-inline" for="radios-4">
																														<input								name="schoolcertificate" id="schoolcertificate" ng-value="true"								type="radio" ng-model="formInfo.idSchoolCertificateProduced"								ng-click="clearFieldForQ12(formInfo.idSchoolCertificateProduced,'schoolC')">
																															<span translate>Yes</span>
																														</label>
																														<label class="radio-inline" for="radios-5">
																															<input								name="schoolcertificate" id="schoolcertificate" ng-value="false"								type="radio" ng-model="formInfo.idSchoolCertificateProduced" checked="checked"								ng-click="clearFieldForQ12(formInfo.idSchoolCertificateProduced,'schoolC')">
																																<span translate>No</span>
																															</label>
																														</div>
																														<div class="col-md-7" ng-if="viewPostReleasePage">
																															<input placeholder="{{formInfo.idSchoolCertificateProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																														</div>
																													</div>
																													<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idSchoolCertificateProduced">
																														<label class="col-md-4 control-label" for="textinput"></label>
																														<div class="col-md-7">
																															<label class="control-label" for="textinput"></label>
																															<input id="identityCardNoSchool" ng-model="formInfo.idSchoolCertificateNum"								placeholder="{{'Enter School Certificate No.'| translate}}" required								class="form-control input-md" type="text" maxlength="30"								ng-blur="blur(formInfo.idSchoolCertificateNum,'idSchoolCertificateNum')"												 ng-trim="false"								ng-disabled="viewPostReleasePage">
																															</div>
																														</div>
																														<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idSchoolCertificateProduced">
																															<label class="col-md-4 control-label" for="textinput"></label>
																															<div class="col-md-7">
																																<label class="control-label" for="textinput"></label>
																																<input id="actionTakenSchool" ng-model="formInfo.idSchoolCertificateNum"								placeholder="{{'Enter Action Taken'| translate}}" required maxlength="200"								class="form-control input-md" type="text"								ng-disabled="viewPostReleasePage">
																																</div>
																															</div>
																														</div>
																														<div>
																															<div class="form-group box-border-padding">
																																<label class="col-md-4 control-label" for="textinput">
																																	<span translate>(iii) Caste Certificate</span>
																																	<span class="mandatory_star">&#42;</span>
																																</label>
																																<div class="col-md-7" ng-if="!viewPostReleasePage">
																																	<label class="radio-inline">
																																		<input								name="Castecertificate" id="Castecertificate" ng-value="true"								type="radio" ng-model="formInfo.idCasteCertificateProduced"								ng-click="clearFieldForQ12(formInfo.idCasteCertificateProduced,'casteC')">
																																			<span translate>Yes</span>
																																		</label>
																																		<label class="radio-inline">
																																			<input								name="Castecertificate" id="Castecertificate" ng-value="false"								type="radio" ng-model="formInfo.idCasteCertificateProduced" checked="checked"								ng-click="clearFieldForQ12(formInfo.idCasteCertificateProduced,'casteC')">
																																				<span translate>No</span>
																																			</label>
																																		</div>
																																		<div class="col-md-7" ng-if="viewPostReleasePage">
																																			<input placeholder="{{formInfo.idCasteCertificateProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																																		</div>
																																	</div>
																																	<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idCasteCertificateProduced">
																																		<label class="col-md-4 control-label" for="textinput"></label>
																																		<div class="col-md-7">
																																			<label class="control-label"></label>
																																			<input id="identityCardNoCaste" placeholder="{{'Enter Caste Certificate No.'| translate}}" maxlength="30"								class="form-control input-md" ng-model="formInfo.idCasteCertificateNum"								ng-blur="blur(formInfo.idCasteCertificateNum,'idCasteCertificateNum')"												 ng-trim="false"								type="text" required ng-disabled="viewPostReleasePage">
																																			</div>
																																		</div>
																																		<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idCasteCertificateProduced">
																																			<label class="col-md-4 control-label" for="textinput"></label>
																																			<div class="col-md-7">
																																				<label class="control-label"></label>
																																				<input								id="actionTakenCaste" placeholder="{{'Enter Action Taken'| translate}}" maxlength="200"								class="form-control input-md" type="text" required								ng-model="formInfo.idCasteCertificateNum" ng-disabled="viewPostReleasePage">
																																				</div>
																																			</div>
																																		</div>
																																		<div>
																																			<div class="form-group box-border-padding">
																																				<label class="col-md-4 control-label" for="textinput">
																																					<span translate>(iv) BPL Card</span>
																																					<span class="mandatory_star">&#42;</span>
																																				</label>
																																				<div class="col-md-7" ng-if="!viewPostReleasePage">
																																					<label class="radio-inline">
																																						<input name="BPLCard"								id="BPLCard" ng-value="true" type="radio" ng-model="formInfo.idBplCardProduced"								ng-click="clearFieldForQ12(formInfo.idBplCardProduced,'bplC')">
																																							<span translate>Yes</span>
																																						</label>
																																						<label class="radio-inline">
																																							<input name="BPLCard"								id="BPLCard" ng-value="false" type="radio" ng-model="formInfo.idBplCardProduced"								checked="checked" ng-click="clearFieldForQ12(formInfo.idBplCardProduced,'bplC')">
																																								<span translate>No</span>
																																							</label>
																																						</div>
																																						<div class="col-md-7" ng-if="viewPostReleasePage">
																																							<input placeholder="{{formInfo.idBplCardProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																																						</div>
																																					</div>
																																					<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idBplCardProduced">
																																						<label class="col-md-4 control-label" for="textinput"></label>
																																						<div class="col-md-7">
																																							<label class="control-label"></label>
																																							<input								id="identityCardNoBPLCard" placeholder="{{'Enter BPL Card No.'| translate}}"								class="form-control input-md" type="text" required maxlength="30"								ng-blur="blur(formInfo.idBplCardNum,'idBplCardNum')"												 ng-trim="false"								ng-model="formInfo.idBplCardNum" ng-disabled="viewPostReleasePage">
																																							</div>
																																						</div>
																																						<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idBplCardProduced">
																																							<label class="col-md-4 control-label" for="textinput"></label>
																																							<div class="col-md-7">
																																								<label class="control-label"></label>
																																								<input								id="actionTakenBPLCard" placeholder="{{'Enter Action Taken'| translate}}"								class="form-control input-md" type="text" required maxlength="200"								ng-model="formInfo.idBplCardNum" ng-disabled="viewPostReleasePage">
																																								</div>
																																							</div>
																																						</div>
																																						<div>
																																							<div class="form-group box-border-padding">
																																								<label class="col-md-4 control-label" for="textinput">
																																									<span translate>(v) Disability Certificate</span>
																																									<span class="mandatory_star">&#42;</span>
																																								</label>
																																								<div class="col-md-7" ng-if="!viewPostReleasePage">
																																									<label class="radio-inline">
																																										<input								name="DisabilityCertificate" id="DisabilityCertificate"								ng-value="true" type="radio" ng-model="formInfo.idDisabiltyCertificateProduced"								ng-click="clearFieldForQ12(formInfo.idDisabiltyCertificateProduced,'disabilityC')">
																																											<span translate>Yes</span>
																																										</label>
																																										<label class="radio-inline">
																																											<input								name="DisabilityCertificate" id="BPLCard" ng-value="false"								type="radio" ng-model="formInfo.idDisabiltyCertificateProduced"								checked="checked" ng-click="clearFieldForQ12(formInfo.idDisabiltyCertificateProduced,'disabilityC')">
																																												<span translate>No</span>
																																											</label>
																																										</div>
																																										<div class="col-md-7" ng-if="viewPostReleasePage">
																																											<input placeholder="{{formInfo.idDisabiltyCertificateProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																																										</div>
																																									</div>
																																									<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idDisabiltyCertificateProduced">
																																										<label class="col-md-4 control-label" for="textinput"></label>
																																										<div class="col-md-7">
																																											<label class="control-label"></label>
																																											<input								id="identityCardNoDisabilityCertificate"								ng-blur="blur(formInfo.idDisabiltyCertificateNum,'idDisabiltyCertificateNum')"												 ng-trim="false"								placeholder="{{'Enter Disability Certificate No.'| translate}}" maxlength="30" 								class="form-control input-md" type="text" required								ng-model="formInfo.idDisabiltyCertificateNum"								ng-disabled="viewPostReleasePage">
																																											</div>
																																										</div>
																																										<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idDisabiltyCertificateProduced">
																																											<label class="col-md-4 control-label" for="textinput"></label>
																																											<div class="col-md-7">
																																												<label class="control-label"></label>
																																												<input								id="actionTakenDisabilityCertificate"								placeholder="{{'Enter Action Taken'| translate}}" required								class="form-control input-md" type="text" maxlength="200"								ng-model="formInfo.idDisabiltyCertificateNum"								ng-disabled="viewPostReleasePage">
																																												</div>
																																											</div>
																																										</div>
																																										<div>
																																											<div class="form-group box-border-padding">
																																												<label class="col-md-4 control-label" for="textinput">
																																													<span translate>(vi) Immunization Card</span>
																																													<span class="mandatory_star">&#42;</span>
																																												</label>
																																												<div class="col-md-7" ng-if="!viewPostReleasePage">
																																													<label class="radio-inline">
																																														<input								name="Immunizationcard" id="Immunizationcard" ng-value="true"								type="radio" ng-model="formInfo.idImmunizationCardProduced"								ng-click="clearFieldForQ12(formInfo.idImmunizationCardProduced,'immunizationC')">
																																															<span translate>Yes</span>
																																														</label>
																																														<label class="radio-inline">
																																															<input								name="Immunizationcard" id="Immunizationcard" ng-value="false"								type="radio" ng-model="formInfo.idImmunizationCardProduced" checked="checked"								ng-click="clearFieldForQ12(formInfo.idImmunizationCardProduced,'immunizationC')">
																																																<span translate>No</span>
																																															</label>
																																														</div>
																																														<div class="col-md-7" ng-if="viewPostReleasePage">
																																															<input placeholder="{{formInfo.idImmunizationCardProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																																														</div>
																																													</div>
																																													<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idImmunizationCardProduced">
																																														<label class="col-md-4 control-label" for="textinput"></label>
																																														<div class="col-md-7">
																																															<label class="control-label"></label>
																																															<input								id="identityCardNoidImmunizationcard" required								placeholder="{{'Enter Immunization Card No.'| translate}}" maxlength="30"								class="form-control input-md" type="text"								ng-blur="blur(formInfo.idImmunizationCardNum,'idImmunizationCardNum')"												 ng-trim="false"								ng-model="formInfo.idImmunizationCardNum"								ng-disabled="viewPostReleasePage">
																																															</div>
																																														</div>
																																														<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idImmunizationCardProduced">
																																															<label class="col-md-4 control-label" for="textinput"></label>
																																															<div class="col-md-7">
																																																<label class="control-label"></label>
																																																<input								id="actionTakenidImmunizationcard"								placeholder="{{'Enter Action Taken'| translate}}" required								class="form-control input-md" type="text" maxlength="200"								ng-model="formInfo.idImmunizationCardNum"								ng-disabled="viewPostReleasePage">
																																																</div>
																																															</div>
																																														</div>
																																														<div>
																																															<div class="form-group box-border-padding">
																																																<label class="col-md-4 control-label" for="textinput">
																																																	<span translate>(vii) Ration Card</span>
																																																	<span class="mandatory_star">&#42;</span>
																																																</label>
																																																<div class="col-md-7" ng-if="!viewPostReleasePage">
																																																	<label class="radio-inline">
																																																		<input name="RationCard"								id="RationCard" ng-value="true" type="radio"								ng-model="formInfo.idRationCardProduced" ng-click="clearFieldForQ12(formInfo.idRationCardProduced,'rationC')">
																																																			<span translate>Yes</span>
																																																		</label>
																																																		<label class="radio-inline">
																																																			<input name="RationCard"								id="RationCard" ng-value="false" type="radio"								ng-model="formInfo.idRationCardProduced" checked="checked"								ng-click="clearFieldForQ12(formInfo.idRationCardProduced,'rationC')">
																																																				<span translate>No</span>
																																																			</label>
																																																		</div>
																																																		<div class="col-md-7" ng-if="viewPostReleasePage">
																																																			<input placeholder="{{formInfo.idRationCardProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																																																		</div>
																																																	</div>
																																																	<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idRationCardProduced">
																																																		<label class="col-md-4 control-label" for="textinput"></label>
																																																		<div class="col-md-7">
																																																			<label class="control-label"></label>
																																																			<input								id="identityCardNoidRationCard" required								ng-blur="blur(formInfo.idRationCardNum,'idRationCardNum')"												 ng-trim="false"								placeholder="{{'Enter Ration Card No.'| translate}}" maxlength="30"								class="form-control input-md" type="text"								ng-model="formInfo.idRationCardNum"								ng-disabled="viewPostReleasePage">
																																																			</div>
																																																		</div>
																																																		<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idRationCardProduced">
																																																			<label class="col-md-4 control-label" for="textinput"></label>
																																																			<div class="col-md-7">
																																																				<label class="control-label"></label>
																																																				<input								id="actionTakenidRationCard" required								placeholder="{{'Enter Action Taken'| translate}}" maxlength="200"								class="form-control input-md" type="text"								ng-model="formInfo.idRationCardNum"								ng-disabled="viewPostReleasePage">
																																																				</div>
																																																			</div>
																																																		</div>
																																																		<div>
																																																			<div class="form-group box-border-padding">
																																																				<label class="col-md-4 control-label" for="textinput">
																																																					<span translate>(viii) Adhaar Card</span>
																																																					<span class="mandatory_star">&#42;</span>
																																																				</label>
																																																				<div class="col-md-7" ng-if="!viewPostReleasePage">
																																																					<label class="radio-inline">
																																																						<input name="AdhaarCard"								id="AdhaarCard" ng-value="true" type="radio"								ng-model="formInfo.idAdhaarCardProduced"								ng-click="clearFieldForQ12(formInfo.idRationCardProduced,'adhaarC')">
																																																							<span translate>Yes</span>
																																																						</label>
																																																						<label class="radio-inline">
																																																							<input name="AdhaarCard"								id="AdhaarCard" ng-value="false" type="radio"								ng-model="formInfo.idAdhaarCardProduced" checked="checked"								ng-click="clearFieldForQ12(formInfo.idRationCardProduced,'adhaarC')">
																																																								<span translate>No</span>
																																																							</label>
																																																						</div>
																																																						<div class="col-md-7" ng-if="viewPostReleasePage">
																																																							<input placeholder="{{formInfo.idAdhaarCardProduced == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																																																						</div>
																																																					</div>
																																																					<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.idAdhaarCardProduced">
																																																						<label class="col-md-4 control-label" for="textinput"></label>
																																																						<div class="col-md-7">
																																																							<label class="control-label"></label>
																																																							<input								id="identityCardNoAdhaarCard" required								placeholder="{{'Enter Adhaar Card No.'| translate}}" maxlength="30"								ng-blur="blur(formInfo.idAdhaarCardNum,'idAdhaarCardNum')"												 ng-trim="false"								class="form-control input-md" type="text"								ng-model="formInfo.idAdhaarCardNum"								ng-disabled="viewPostReleasePage">
																																																							</div>
																																																						</div>
																																																						<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.idAdhaarCardProduced">
																																																							<label class="col-md-4 control-label" for="textinput"></label>
																																																							<div class="col-md-7">
																																																								<label class="control-label"></label>
																																																								<input required								id="actionTakenAdhaarCard" placeholder="{{'Enter Action Taken'| translate}}"								class="form-control input-md" type="text" maxlength="200"								ng-model="formInfo.idAdhaarCardNum"								ng-disabled="viewPostReleasePage">
																																																								</div>
																																																							</div>
																																																						</div>
																																																						<div>
																																																							<div class="form-group box-border-padding">
																																																								<label class="col-md-4 control-label" for="textinput">
																																																									<span translate>(ix) Received Compensation from Government</span>
																																																									<span class="mandatory_star">&#42;</span>
																																																								</label>
																																																								<div class="col-md-7" ng-if="!viewPostReleasePage">
																																																									<label class="radio-inline">
																																																										<input name="compensation"								id="compensation" ng-value="true" type="radio"								ng-model="formInfo.recievedCompensation"								ng-click="clearFieldForQ12(formInfo.recievedCompensation,'compenR')">
																																																											<span translate>Yes</span>
																																																										</label>
																																																										<label class="radio-inline">
																																																											<input name="compensation"								id="compensation" ng-value="false" type="radio"								ng-model="formInfo.recievedCompensation" checked="checked"								ng-click="clearFieldForQ12(formInfo.recievedCompensation,'compenR')">
																																																												<span translate>No</span>
																																																											</label>
																																																										</div>
																																																										<div class="col-md-7" ng-if="viewPostReleasePage">
																																																											<input placeholder="{{formInfo.recievedCompensation == true ? 'Yes' : 'No' | translate}}"							class="form-control input-md" type="text" readonly />
																																																										</div>
																																																									</div>
																																																									<div class="form-group box-border-padding margintopspace"						ng-if="formInfo.recievedCompensation">
																																																										<label class="col-md-4 control-label" for="textinput"></label>
																																																										<div class="col-md-7">
																																																											<label class="control-label"></label>
																																																											<input								id="identityCardNocompensation" required								placeholder="{{'Enter Received Compensation from Government'| translate}}"								class="form-control input-md" type="text" maxlength="30"								ng-model="formInfo.recievedCompensationAction"								ng-blur="blur(formInfo.recievedCompensationAction,'recievedCompensationAction')"												 ng-trim="false"								ng-disabled="viewPostReleasePage">
																																																											</div>
																																																										</div>
																																																										<div class="form-group box-border-padding margintopspace"						ng-if="!formInfo.recievedCompensation">
																																																											<label class="col-md-4 control-label" for="textinput"></label>
																																																											<div class="col-md-7">
																																																												<label class="control-label"></label>
																																																												<input								id="actionTakencompensation" required								placeholder="{{'Enter Action Taken'| translate}}" maxlength="200"								class="form-control input-md" type="text"								ng-model="formInfo.recievedCompensationAction"								ng-disabled="viewPostReleasePage">
																																																												</div>
																																																											</div>
																																																										</div>
																																																										<div style="text-align: center">
																																																											<button id="button1id" name="button1id" class="btn btn-info" ng-if="!viewPostReleasePage"						type="submit" ng-click="postReleaseDetail.$invalid ? '' : validateForm()">{{'Submit'| translate}}</button>
																																																											<button id="button2id" name="button2id" class="btn btn-info" ng-if="viewPostReleasePage"						type="submit" ng-click="printicpPersonDetailsDSubmitData()">
																																																												<span translate>Print</span>
																																																											</button>
																																																										</div>
																																																									</fieldset>
																																																								</form>
																																																							</div>
																																																						</div>
																																																						<script type="text/javascript">				$(document).ready(function() {			$('input').blur(function() {			    var value = $.trim( $(this).val() );			    $(this).val( value );			});			$('textarea').blur(function() {			    var value = $.trim( $(this).val() );			    $(this).val( value );			});		});	</script>