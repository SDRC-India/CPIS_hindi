<!-- 	<link rel="stylesheet" href="resources/css/style.css"> -->
<!-- 	<link rel="stylesheet" href="resources/css/bootstrap.min.css"> -->
<!-- 	<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css"> --><%-- 	
<spring:url value="/resources/css/style.css" var="styleCss" /> --%><%-- 	
<link href="${styleCss}" rel="stylesheet" /> --%><%-- 	
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css" var="jQueryUiCss" /> --%><%-- 	
<link href="${jQueryUiCss}" rel="stylesheet"></link> --%><%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<div ng-app="interimDecision" ng-controller="CaseMonitoringController" ng-cloak>
	<div class="modal fade" id="confirmationModalForCaseMonitoring" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Please click on the submit button to register the child.</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align:center">
						<button id="button3id" name="button3id" class="btn btn-info" type="submit" ng-click="saveData()" class="close" data-dismiss="modal" aria-hidden="true">Submit</button>
						<button id="button4id" name="button4id" class="btn btn-info" type="submit" class="close" data-dismiss="modal" aria-hidden="true">Back</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="box-border box-border-padding">
		<hr>
			<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 						
			<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
			<hr>
				<div class="childlist-heading">
					<span translate>Case monitoring sheet for committee</span>
					<br>
						<span translate>FORM 26
							<br>
							</span>[Rule 20(1)]
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
									<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 socialChildData">
										<div class="col-sm-3 col-xs-3 col-md-3 social_headng">
											<h5 style="color: #fff; font-size: 12px;">
												<span translate>Child ID</span>
											</h5>
										</div>
									</div>
									<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 paddingleft">
										<div class="col-sm-2 col-xs-2 col-md-2 social_headng1">
											<h5 style="color: #fff">
												<span translate>Name of Child</span>
											</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<form class="form-horizontal basicchildform" name="casemonitor" method="post"						id="casemonitor">
							<fieldset>
								<div class="form-group box-border-padding">
									<label class="col-md-4 control-label" for="textinput">
										<span translate>1. Child Welfare Committee, District*</span>
									</label>
									<div class="col-md-7">
										<input id="childwc" name="childwc"										placeholder="{{'Input name of the District'| translate}}"										class="form-control input-md" type="text"										ng-model="prefetchData.childwc" readonly  oninvalid="this.setCustomValidity('Input name of the District')" oninput="setCustomValidity('')">
											<div id="childwcerror" class="error-style"></div>
										</div>
									</div>
									<div class="form-group box-border-padding">
										<label class="col-md-4 control-label" for="textinput">
											<span translate>2. Case No.*</span>
										</label>
										<div class="col-md-7">
											<input id="newcaseno" name="newcaseno"										placeholder="{{'Input the case number and date'| translate}}"										class="form-control input-md" type="number"										ng-model="prefetchData.caseNo" readonly oninvalid="this.setCustomValidity('Input the case number and date')" oninput="setCustomValidity('')" ng-keyUp="validateFields(formInfo.caseNo,'casenoerror')">
												<div id="casenoerror" class="error-style"></div>
											</div>
										</div>
										<div class="form-group box-border-padding">
											<label class="col-md-4 control-label" for="textinput">
												<span translate>3. Case Name*</span>
											</label>
											<div class="col-md-7">
												<input id="newcasename" name="newcasename"										placeholder="{{'Input the name of the case'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.caseName" readonly										ng-keyUp="validateFields(formInfo.caseName,'casenameerror')">
												</div>
											</div>
											<div class="form-group box-border-padding">
												<label class="col-md-4 control-label" for="textinput">
													<span translate>4. Police Station</span>
												</label>
												<div class="col-md-7">
													<input id="ps" name="ps"										placeholder="{{'Input the name of the police station where case registered'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.psName">
														<div id="pserror" class="error-style"></div>
													</div>
												</div>
												<div class="form-group box-border-padding">
													<label class="col-md-4 control-label" for="textinput">
														<span translate>5. Date*</span>
													</label>
													<div class="col-md-7">
														<input type="text" placeholder="{{'Input date'| translate}}" name="date" id="date"									ng-model="formInfo.date" readonly class="form-control">
															<div id="childProducedDateerror" class="error-style"></div>
														</div>
													</div>
													<div class="form-group box-border-padding">
														<label class="col-md-4 control-label" for="textinput">
															<span translate>6. U/S</span>
														</label>
														<div class="col-md-7">
															<input id="us" name="us"										placeholder="{{'Input the sections under which the child is booked'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.sectionsChildBooked">
																<div id="userror" class="error-style"></div>
															</div>
														</div>
														<div class="form-group box-border-padding">
															<label class="col-md-4 control-label" for="textinput">
																<span translate>7. FIR/ GD/ DD No</span>
															</label>
															<div class="col-md-7">
																<input id="fir" name="fir"										placeholder="{{'Input the FIR/GD/DD No.'| translate}}"										class="form-control input-md" type="number"										ng-model="formInfo.firGdDdNo">
																	<div id="firerror" class="error-style"></div>
																</div>
															</div>
															<div class="form-group box-border-padding">
																<label class="col-md-4 control-label" for="textinput">
																	<span translate>8. Name of Probation Officer</span>
																</label>
																<div class="col-md-7">
																	<input id="npo" name="npo"										placeholder="{{'Input the name of Probation Officer'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.probationOfficerName" ng-keyUp="validateFields(formInfo.npo,'npoerror')">
																		<div id="npoerror" class="error-style"></div>
																	</div>
																</div>
																<div class="form-group box-border-padding">
																	<label class="col-md-4 control-label" for="textinput">
																		<span translate>9. Name of IO</span>
																	</label>
																	<div class="col-md-7">
																		<input id="io" name="io" placeholder="{{'Input the name of the IO'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.ioName" ng-keyUp="validateFields(formInfo.io,'ioerror')">
																			<div id="ioerror" class="error-style"></div>
																		</div>
																	</div>
																	<div class="grey-header">
																		<span translate>10. Particulars of child</span>
																	</div>
																	<div class="form-group box-border-padding">
																		<label class="col-md-4 control-label" for="textinput">
																			<span translate>(i) Name</span>
																		</label>
																		<div class="col-md-7">
																			<input id="uname" name="uname" placeholder="{{'Input name of the child'| translate}}"										class="form-control input-md" type="text" readonly										ng-model="prefetchData.childName" ng-pattern="/^[a-zA-Z\s]*$/" ng-keyUp="validateFields(formInfo.uname,'usererror')">
																				<div id="usererror" class="error-style"></div>
																			</div>
																		</div>
																		<div class="form-group box-border-padding">
																			<label class="col-md-4 control-label" for="textinput">
																				<span translate>(ii) Parents/Guardian Name</span>
																			</label>
																			<div class="col-md-7">
																				<input id="pgcontact" name="pgcontact"										placeholder="{{'Input name of parents/guardian with contact no.'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.pgName"										ng-keyUp="validateFields(formInfo.pgcontact,'pgNameError')">
																					<div id="pgNameError" class="error-style"></div>
																				</div>
																			</div>
																			<div class="form-group box-border-padding">
																				<label class="col-md-4 control-label" for="textinput">
																					<span translate>(iii) Parents/ Guardian Contact No.</span>
																				</label>
																				<div class="col-md-7">
																					<input id="pgcontact" name="pgContactNo"										placeholder="{{'Input name of parents/guardian with contact no.'| translate}}"										class="form-control input-md" type="number"										ng-model="formInfo.pgContactNo" ng-pattern="/^[0-9]{1}[0-9]{9}$/"										ng-keyUp="validateFields(formInfo.pgcontact,'pgContactError')">
																						<div id="pgContactError" class="error-style"></div>
																					</div>
																				</div>
																				<div class="form-group box-border-padding">
																					<label class="col-md-4 control-label" for="textinput">
																						<span translate>(iv) Present address</span>
																					</label>
																					<div class="col-md-7">
																						<input id="prAddress" name="prAddress"										placeholder="{{'Input present address of the child'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.childPresentAddress"										ng-keyUp="validateFields(formInfo.personProducingChildAddress,'prAddresserror')">
																							<div id="prAddresserror" class="error-style"></div>
																						</div>
																					</div>
																					<div class="form-group box-border-padding">
																						<label class="col-md-4 control-label" for="textinput">
																							<span translate>(v) Permanent address</span>
																						</label>
																						<div class="col-md-7">
																							<input id="peAddress" name="peAddress"										placeholder="{{'Input permanent address of the child'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.childPermanentAddress"										ng-keyUp="validateFields(formInfo.peAddress,'peAddresserror')">
																								<div id="peAddresserror" class="error-style"></div>
																							</div>
																						</div>
																						<div class="form-group box-border-padding">
																							<label class="col-md-4 control-label" for="textinput">
																								<span translate>(vi) Date when child produced before committee*</span>
																							</label>
																							<div class="col-md-7">
																								<input type="text" name="childProducedBeforeCommittee" id="childProducedBeforeCommittee" 										ng-model="prefetchData.childProducedBeforeCommitteeDate" readonly class="form-control">
																									<div id="childProducedDateerror" class="error-style"></div>
																								</div>
																							</div>
																							<div class="form-group box-border-padding">
																								<label class="col-md-4 control-label" for="textinput">
																									<span translate>(vii) Time*
																									</label>
																									<div class="col-md-7">
																										<select ng-options="item as item for item in hour" ng-model="hr" required class="form-control" style="width:32%; display:inline;">
																											<option value="" disabled selected>HH</option>
																										</select>
																										<select ng-options="item as item for item in min" ng-model="minute" required class="form-control" style="width:32%; display:inline;">
																											<option value="" disabled selected>MM</option>
																										</select>
																										<select ng-options="item as item for item in ampm" ng-model="ap" required class="form-control" style="width:32%; display:inline;"></select>
																									</div>
																								</div>
																								<div class="form-group box-border-padding">
																									<label class="col-md-4 control-label" for="textinput">
																										<span translate>(viii) Date and time of first production*</span>
																									</label>
																									<div class="col-md-7">
																										<input id="datefproduct" name="datefproduct"										placeholder="{{'Input the date and time the child is first produced before the Committee'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.datefproduct" readonly>
																											<div id="datefproducterror" class="error-style"></div>
																										</div>
																									</div>
																									<div class="form-group box-border-padding">
																										<label class="col-md-4 control-label" for="textinput">
																											<span translate>(ix) Date of medical examination under Section 54 Cr. Pc. (if any)</span>
																										</label>
																										<div class="col-md-7">
																											<input type="text" id="datepicker1" ng-model="formInfo.dateOfMedicalExamination" readonly class="form-control">
																												<div id="meidcalExamination" class="error-style"></div>
																											</div>
																										</div>
																										<div class="grey-header">
																											<span translate>11. Age determination</span>
																										</div>
																										<div class="form-group box-border-padding">
																											<label class="col-md-4 control-label" for="textinput">
																												<span translate>(i) Age on the Date of offence</span>
																											</label>
																											<div class="col-md-7">
																												<input id="ageondate" name="ageondate"										placeholder="{{'Input age of child on the date of offence'| translate}}"										class="form-control input-md" type="number"										ng-model="formInfo.ageondate"										ng-keyUp="validateFields(formInfo.ageondate,'ageondateerror')">
																													<div id="ageondateerror" class="error-style"></div>
																												</div>
																											</div>
																											<div class="form-group box-border-padding">
																												<label class="col-md-4 control-label" for="textinput">
																													<span translate>(ii) Date of age Determination</span>
																												</label>
																												<div class="col-md-7">
																													<input id="ageondtrmntion" name="ageondtrmntion"										placeholder="{{'Input the date when age of the child was determined'| translate}}"										class="form-control input-md" readonly										ng-model="formInfo.dateOfAgeDetermination"										ng-keyUp="validateFields(formInfo.ageondtrmntion,'ageondtrmntionerror')">
																														<div id="ageondtrmntionerror" class="error-style"></div>
																													</div>
																												</div>
																												<div class="form-group box-border-padding">
																													<label class="col-md-4 control-label" for="textinput">
																														<span translate>(iii) Time taken for age determination</span>
																													</label>
																													<div class="col-md-7">
																														<input id="timetakeage" name="timetakeage"										placeholder="{{'Input the time taken for determining the age of the child'| translate}}"										class="form-control input-md" type="number"										ng-model="formInfo.timeTakenForAgeDetermination"										ng-keyUp="validateFields(formInfo.timetakeage,'timetakeageerror')">
																															<div id="timetakeageerror" class="error-style"></div>
																														</div>
																													</div>
																													<div class="form-group box-border-padding">
																														<label class="col-md-4 control-label" for="textinput">
																															<span translate>(iv) Determination by</span>
																														</label>
																														<div class="col-md-7">
																															<input id="determinby" name="determinby"										placeholder="{{'Input the name of determinator'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.nameOfDeterminator"										ng-keyUp="validateFields(formInfo.determinby,'determinbyerror')">
																																<div id="determinbyerror" class="error-style"></div>
																															</div>
																														</div>
																														<div class="form-group box-border-padding">
																															<label class="col-md-4 control-label" for="textinput">
																																<span translate>(v) Committee</span>
																															</label>
																															<div class="col-md-7">
																																<input id="commitee" name="commitee"										placeholder="{{'Input the name of the Committee'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.ageDeterminationCommitteeName">
																																	<div id="commiteeerror" class="error-style"></div>
																																</div>
																															</div>
																															<div class="grey-header">
																																<span translate>12. Evidence Relied:</span>
																															</div>
																															<div class="form-group box-border-padding">
																																<label class="col-md-4 control-label" for="textinput">
																																	<span translate>(i) Documents</span>
																																</label>
																																<div class="col-md-7">
																																	<input id="doc" name="doc"										placeholder="{{'Input the documents relied on to determine the child's age'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.doc">
																																		<div id="docerror" class="error-style"></div>
																																	</div>
																																</div>
																																<div class="form-group box-border-padding">
																																	<label class="col-md-4 control-label" for="textinput">
																																		<span translate>(ii) Medical</span>
																																	</label>
																																	<div class="col-md-7">
																																		<input id="medical" name="medical"										placeholder="{{'Input the medical reports relied on to determine the child\'s age'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.medical">
																																			<div id="medicalerror" class="error-style"></div>
																																		</div>
																																	</div>
																																	<div class="grey-header">
																																		<span translate>13. Placement of the child</span>
																																	</div>
																																	<div class="form-group box-border-padding">
																																		<label class="col-md-4 control-label" for="textinput">
																																			<span translate>(i) In Children's Home</span>
																																		</label>
																																		<div class="col-md-7">
																																			<input id="chilrenhome" name="chilrenhome"										placeholder="{{'Input if sent to Children's Home'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.childrenHomeName"										ng-keyUp="validateFields(formInfo.chilrenhome,'chilrenhomeerror')">
																																				<div id="chilrenhomeerror" class="error-style"></div>
																																			</div>
																																		</div>
																																		<div class="form-group box-border-padding">
																																			<label class="col-md-4 control-label" for="textinput">
																																				<span translate>(ii) Sent under supervision (Name of Institution)</span>
																																			</label>
																																			<div class="col-md-7">
																																				<input id="supervision" name="supervision"										placeholder="{{'Input the name of Institution where child is sent'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.supervisionInstitutionName"										ng-keyUp="validateFields(formInfo.supervision,'supervisionerror')">
																																					<div id="supervisionerror" class="error-style"></div>
																																				</div>
																																			</div>
																																			<div class="form-group box-border-padding">
																																				<label class="col-md-4 control-label" for="textinput">
																																					<span translate>(iii) From</span>
																																				</label>
																																				<div class="col-md-7">
																																					<input id="from" name="form" readonly										placeholder="{{'Input the date on which the child is sent to the Institution'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.dateChildSentToSupervisionInstitution">
																																						<div id="formerror" class="error-style"></div>
																																					</div>
																																				</div>
																																				<div class="form-group box-border-padding">
																																					<label class="col-md-4 control-label" for="textinput">
																																						<span translate>(iv) To</span>
																																					</label>
																																					<div class="col-md-7">
																																						<input id="to" name="to" readonly										placeholder="{{'Input the date till which the child is sent to the Institution'| translate}}"										class="form-control input-md" type="text"										ng-model="formInfo.dateTillChildSentToSupervisionInstitution">
																																							<div id="toerror" class="error-style"></div>
																																						</div>
																																					</div>
																																					<div class="grey-header">
																																						<span translate>14. Progress of Enquiry</span>
																																					</div>
																																					<div class="form-group box-border-padding">
																																						<div class="col-md-4"></div>
																																						<div class="col-md-7">
																																							<div class="col-md-6 text-center">
																																								<strong>
																																									<span translate>Scheduled Date
																																									</strong>
																																								</div>
																																								<div class="col-md-6 text-center">
																																									<strong>
																																										<span translate>Actual Date
																																										</strong>
																																									</div>
																																								</div>
																																							</div>
																																							<div class="form-group box-border-padding">
																																								<label class="col-md-4 control-label" for="textinput">
																																									<span translate>(i) Age determination</span>
																																								</label>
																																								<div class="col-md-7">
																																									<div class="col-md-6 text-center">
																																										<input id="agedeterminS" name="agedeterminS"											placeholder="{{'Input the scheduled date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.scheduledDateOfAgeDetermination">
																																											<!-- 											ng-keyUp="validateFields(formInfo.agedetermin,'agedeterminerror')"> -->
																																											<div id="agedeterminerrorS" class="error-style"></div>
																																										</div>
																																										<div class="col-md-6 text-center">
																																											<input id="agedeterminA" name="agedeterminA"											placeholder="{{'Input the actual date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.actualDateOfAgeDetermination">
																																												<!-- 											ng-keyUp="validateFields(formInfo.agedetermin,'agedeterminerror')"> -->
																																												<div id="agedeterminerrorA" class="error-style"></div>
																																											</div>
																																										</div>
																																									</div>
																																									<div class="form-group box-border-padding">
																																										<label class="col-md-4 control-label" for="textinput">
																																											<span translate>(ii) Social Investigation Report (Form No.22)</span>
																																										</label>
																																										<div class="col-md-7">
																																											<div class="col-md-6 text-center">
																																												<input id="socialivgS" name="socialivgS"											placeholder="{{'Input the scheduled date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.scheduledDateOfSocialInvestigationReport">
																																													<!-- 											ng-keyUp="validateFields(formInfo.socialivg,'socialivgerror')"> -->
																																													<div id="socialivgerrorS" class="error-style"></div>
																																												</div>
																																												<div class="col-md-6 text-center">
																																													<input id="socialivgA" name="socialivgA"											placeholder="{{'Input the actual date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.actualDateOfSocialInvestigationReport">
																																														<!-- 											ng-keyUp="validateFields(formInfo.socialivg,'socialivgerror')"> -->
																																														<div id="socialivgerrorA" class="error-style"></div>
																																													</div>
																																												</div>
																																											</div>
																																											<div class="form-group box-border-padding">
																																												<label class="col-md-4 control-label" for="textinput">
																																													<span translate>(iii) Submission of Report on Provisions of further investigation, if any</span>
																																												</label>
																																												<div class="col-md-7">
																																													<div class="col-md-6 text-center">
																																														<input id="submissionreportS" name="submissionreportS"											placeholder="{{'Input the scheduled date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.scheduledDateOfSubmissionReportOnProvisions">
																																															<!-- 											ng-keyUp="validateFields(formInfo.submissionreport,'submissionreporterror')"> -->
																																															<div id="submissionreporterrorS" class="error-style"></div>
																																														</div>
																																														<div class="col-md-6 text-center">
																																															<input id="submissionreportA" name="submissionreportA"											placeholder="{{'Input the actual date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.actualDateOfSubmissionReportOnProvisions">
																																																<!-- 											ng-keyUp="validateFields(formInfo.submissionreport,'submissionreporterror')"> -->
																																																<div id="submissionreporterrorA" class="error-style"></div>
																																															</div>
																																														</div>
																																													</div>
																																													<div class="form-group box-border-padding">
																																														<label class="col-md-4 control-label" for="textinput">
																																															<span translate>(iv) Statement of Child</span>
																																														</label>
																																														<div class="col-md-7">
																																															<div class="col-md-6 text-center">
																																																<input id="statechildS" name="statechildS"											placeholder="{{'Input the scheduled date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.scheduledDateOfStatementOfChild">
																																																	<div id="statechilderrorS" class="error-style"></div>
																																																</div>
																																																<div class="col-md-6 text-center">
																																																	<input id="statechildA" name="statechildA"											placeholder="{{'Input the actual date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.actualDateOfStatementOfChild">
																																																		<div id="statechilderrorA" class="error-style"></div>
																																																	</div>
																																																</div>
																																															</div>
																																															<div class="form-group box-border-padding">
																																																<label class="col-md-4 control-label" for="textinput">
																																																	<span translate>(v) Individual Care Plan (In case of child in institutional care Individual Care Plan should be prepared within one month of admittance)</span>
																																																</label>
																																																<div class="col-md-7">
																																																	<div class="col-md-6 text-center">
																																																		<input id="indivisualcareplanS" name="indivisualcareplanS"											placeholder="{{'Input the scheduled date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.scheduledDateOfIndividualCarePlan">
																																																			<!-- 											ng-keyUp="validateFields(formInfo.indivisualcareplan,'indivisualcareplanerror')"> -->
																																																			<div id="indivisualcareplanerrorS" class="error-style"></div>
																																																		</div>
																																																		<div class="col-md-6 text-center">
																																																			<input id="indivisualcareplanA" name="indivisualcareplanA"											placeholder="{{'Input the actual date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.actualDateOfIndividualCarePlan">
																																																				<!-- 											ng-keyUp="validateFields(formInfo.indivisualcareplan,'indivisualcareplanerror')"> -->
																																																				<div id="indivisualcareplanerrorA" class="error-style"></div>
																																																			</div>
																																																		</div>
																																																	</div>
																																																	<div class="form-group box-border-padding">
																																																		<label class="col-md-4 control-label" for="textinput">
																																																			<span translate>(vi) Dispositional(Final) Order</span>
																																																		</label>
																																																		<div class="col-md-7">
																																																			<div class="col-md-6 text-center">
																																																				<input id="dispositionalS" name="dispositionalS"											placeholder="{{'Input the scheduled date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.scheduledDateOfFinalOrder">
																																																					<!-- 											ng-keyUp="validateFields(formInfo.dispositional,'dispositionalerror')"> -->
																																																					<div id="dispositionalerrorS" class="error-style"></div>
																																																				</div>
																																																				<div class="col-md-6 text-center">
																																																					<input id="dispositionalA" name="dispositionalA"											placeholder="{{'Input the actual date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.actualDateOfFinalOrder">
																																																						<!-- 											ng-keyUp="validateFields(formInfo.dispositional,'dispositionalerror')"> -->
																																																						<div id="dispositionalerrorA" class="error-style"></div>
																																																					</div>
																																																				</div>
																																																			</div>
																																																			<div class="form-group box-border-padding">
																																																				<label class="col-md-4 control-label" for="textinput">
																																																					<span translate>(vii) Post Dispositional Review</span>
																																																				</label>
																																																				<div class="col-md-7">
																																																					<div class="col-md-6 text-center">
																																																						<input id="postdispositionalS" name="postdispositionalS"											placeholder="{{'Input the scheduled date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.scheduledDateOfPostDispositionalReview">
																																																							<!-- 											ng-keyUp="validateFields(formInfo.postdispositional,'postdispositionalerror')"> -->
																																																							<div id="postdispositionalerrorS" class="error-style"></div>
																																																						</div>
																																																						<div class="col-md-6 text-center">
																																																							<input id="postdispositionalA" name="postdispositionalA"											placeholder="{{'Input the actual date'| translate}}" readonly											class="form-control input-md datePickerClass" type="text"											ng-model="formInfo.actualDateOfPostDispositionalReview">
																																																								<!-- 											ng-keyUp="validateFields(formInfo.postdispositional,'postdispositionalerror')"> -->
																																																								<div id="postdispositionalerrorA" class="error-style"></div>
																																																							</div>
																																																						</div>
																																																					</div>
																																																					<div style="text-align: center">
																																																						<button id="button1id" name="button1id" class="btn btn-info"									type="submit" ng-click = "casemonitor.$invalid ? '' : validateForm()">Submit</button>
																																																						<button id="button2id" name="button2id" class="btn btn-info"									type="reset">Reset</button>
																																																					</div>
																																																				</fieldset>
																																																			</form>
																																																		</div>
																																																		<!-- 	<script type="text/javascript" src="resources/js/jquery-min.js"></script>                       -->
																																																		<!-- 	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script> -->
																																																		<!-- 	<script type="text/javascript" src="resources/js/angular.min.js"></script> -->
																																																		<script>		var app = angular.module('interimDecision',['gettext']);		var myAppConstructor= angular.module('interimDecision');	</script>
																																																		<!-- 	<script src="resources/js/AngularController/interim_decision.js"></script> -->
																																																		<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script> -->
																																																		<!--     <script src="resources/js/bootstrap-datetimepicker.min.js"></script> -->
																																																		<!--     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
																																																		<script type="text/javascript">		$(document).ready(function() {				$( "#datepicker1" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});			$( "#ageondtrmntion" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});			$( "#from" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});			$( "#to" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});			$( ".datePickerClass" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});		});	</script><%-- 	
																																																		<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js" --%>
																																																			<%-- 		var="jQueryUiCore" /> --%><%-- 	
																																																			<script src="${jQueryUiCore}"></script> --%>
																																																		</div>