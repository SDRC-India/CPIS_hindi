<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<div id="HRDetails" ng-controller="HRDetailsController">
		<div class="modal fade" id="HRDetailsconfirmationModal" tabindex="-1"
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
							<button id="submitData" name="button3id"
								class="btn btn-info bigbutton" type="submit"
								ng-click="saveDCPUHRDetails()" class="close"
								data-dismiss="modal" aria-hidden="true" data-toggle="modal">
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
		<div class="modal fade" id="alertModal1" tabindex="-1" role="dialog"
			data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<h4 class="modal-title" id="myModalLabel">
							<span translate>The form has been submitted successfully.</span>
						</h4>
						<p style="text-align: center">
							<button id="button5id" name="button5id"
								class="btn btn-info bigbutton2" type="submit" class="close"
								data-dismiss="modal" aria-hidden="true"
								ng-click="reDirectPage()">
								<span translate>Ok</span>
							</button>
							<button id="button5id" name="button5id"
								class="btn btn-info bigbutton2" type="submit" class="close"
								data-dismiss="modal" aria-hidden="true"
								ng-click="printDCPUHumanResourceDetailsSubmitData()">
								<span translate>Print</span>
							</button>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="nocci" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content"
					style="border-radius: 0px; padding-top: 40px; padding-bottom: 40px;">
					<div class="modal-body">
						<p style="text-align: center; font-size: 18px; font-weight: bold;">
							<span translate>No CCIs available.</span> <br>
						</p>
						<button type="button" class="btn btn-default printOK"
							data-dismiss="modal" aria-hidden="true">
							<span translate>OK</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="box-border box-border-padding">
			<!-- <hr>
							<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 						
							<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
							<hr> -->
			<div class="grey-header marginTop"
				style="border-top: none; margin-top: 4px;">
				<span translate>HR DETAILS OF DCPU</span>
			</div>
			<form class="form-horizontal basicchildform" name="hrDetailsDCPU"
				id="hr-dcpu">
				<fieldset>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>1.Name of DPO</span>
						</label>
						<div class="col-md-7">
							<input id="dpoName" name="dpoName"
								ng-model="dcpuhrdetailsFormInfo.dpoName" maxlength="40"
								placeholder="{{'Enter name of DPO'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="sex" name="sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.dpoSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<!-- 														<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{sex.name}}</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="contactNumber" name="contactNumber"
								ng-model="dcpuhrdetailsFormInfo.dpoContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" type="text"
								only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="emaildpo" name="emaildpo"
								ng-model="dcpuhrdetailsFormInfo.dpoEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.dpoEmail,'emaildpoEmail')">
							<div id=emaildpoEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>2.Name of P.O.I.C</span>
						</label>
						<div class="col-md-7">
							<input id="poicName" name="poicName"
								ng-model="dcpuhrdetailsFormInfo.poicName" maxlength="40"
								placeholder="{{'Enter name of P.O.I.C.'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="poicSex" name="poicSex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.poicSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="poicContactNumber" name="poicContactNumber"
								ng-model="dcpuhrdetailsFormInfo.poicContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="poicEmail" name="poicEmail"
								ng-model="dcpuhrdetailsFormInfo.poicEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.poicEmail,'emailpoicEmail')">
							<div id=emailpoicEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>3.Name of P.O.N.I.C</span>
						</label>
						<div class="col-md-7">
							<input id="ponicName" name="ponicName" maxlength="40"
								ng-model="dcpuhrdetailsFormInfo.ponicName"
								placeholder="{{'Enter name of P.O.N.I.C.'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="ponicSex" name="ponicSex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.ponicSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="ponicContactNumber" name="ponicContactNumber"
								ng-model="dcpuhrdetailsFormInfo.ponicContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="ponicEmail" name="ponicEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								ng-model="dcpuhrdetailsFormInfo.ponicEmail"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.ponicEmail,'emailponicEmail')">
							<div id=emailponicEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>4.Name of Legal cum Probation officer available</span>
						</label>
						<div class="col-md-7">
							<input id="lcpoavilName" name="lcpoavilName"
								ng-model="dcpuhrdetailsFormInfo.lcpoavilName" maxlength="40"
								placeholder="{{'Enter name of Legal cum Probation officer available'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="lcpoavilSex" name="lcpoavilSex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.lcpoavilSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="lcpoavilContactNumber" name="lcpoavilContactNumber"
								ng-model="dcpuhrdetailsFormInfo.lcpoavilContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="lcpoavilEmail" name="lcpoavilEmail"
								ng-model="dcpuhrdetailsFormInfo.lcpoavilEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.lcpoavilEmail,'emaillcpoavilEmail')">
							<div id=emaillcpoavilEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>5.Name of Counsellor</span>
						</label>
						<div class="col-md-7">
							<input id="counsellorName" name="counsellorName"
								ng-model="dcpuhrdetailsFormInfo.counsellorName" maxlength="40"
								placeholder="{{'Enter name of Counsellor'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="counsellorSex" name="counsellorSex"
								class="form-control"
								ng-model="dcpuhrdetailsFormInfo.counsellorSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="counsellorContactNumber"
								name="counsellorContactNumber"
								ng-model="dcpuhrdetailsFormInfo.counsellorContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="counsellorEmail" name="counsellorEmail"
								ng-model="dcpuhrdetailsFormInfo.counsellorEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.counsellorEmail,'emailcounsellorEmail')">
							<div id=emailcounsellorEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"><span
							translate>6.Name of Social Worker 1</span> </label>
						<div class="col-md-7">
							<input id="sw1Name" name="sw1Name"
								ng-model="dcpuhrdetailsFormInfo.sw1Name" maxlength="40"
								placeholder="{{'Enter name of Social Worker 1'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="sw1Sex" name="sw1Sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.sw1Sex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="sw1ContactNumber" name="sw1ContactNumber"
								ng-model="dcpuhrdetailsFormInfo.sw1ContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="sw1Email" name="sw1Email"
								ng-model="dcpuhrdetailsFormInfo.sw1Email" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.sw1Email,'emailsw1Email')">
							<div id=emailsw1Email class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>7.Name of Social Worker 2</span>
						</label>
						<div class="col-md-7">
							<input id="sw2Name" name="sw2Name"
								ng-model="dcpuhrdetailsFormInfo.sw2Name" maxlength="40"
								placeholder="{{'Enter name of Social Worker 2'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="sw2Sex" name="sw2Sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.sw2Sex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="sw2ContactNumber" name="sw2ContactNumber"
								ng-model="dcpuhrdetailsFormInfo.sw2ContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="sw2Email" name="sw2Email"
								ng-model="dcpuhrdetailsFormInfo.sw2Email" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.sw2Email,'emailsw2Email')">
							<div id=emailsw2Email class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"><span
							translate>8.Name of Accountant</span> </label>
						<div class="col-md-7">
							<input id="accountantName" name="accountantName"
								ng-model="dcpuhrdetailsFormInfo.accountantName" maxlength="40"
								placeholder="{{'Enter name of Accountant'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="accountantSex" name="accountantSex"
								class="form-control"
								ng-model="dcpuhrdetailsFormInfo.accountantSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="accountantContactNumber"
								name="accountantContactNumber"
								ng-model="dcpuhrdetailsFormInfo.accountantContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="accountantEmail" name="accountantEmail"
								ng-model="dcpuhrdetailsFormInfo.accountantEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.accountantEmail,'emailaccountantEmail')">
							<div id=emailaccountantEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>9.Name of Data Analyst</span>
						</label>
						<div class="col-md-7">
							<input id="daName" name="daName"
								ng-model="dcpuhrdetailsFormInfo.daName" maxlength="40"
								placeholder="{{'Enter name of Data Analyst'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="sex" name="sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.daSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="contactNumber" name="contactNumber"
								ng-model="dcpuhrdetailsFormInfo.daContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="daemail" name="daemail"
								ng-model="dcpuhrdetailsFormInfo.daEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.daEmail,'emaildaEmail')">
							<div id=emaildaEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>10.Name of Assistant cum Data entry operator</span>
						</label>
						<div class="col-md-7">
							<input id="acdeoName" name="acdeoName"
								ng-model="dcpuhrdetailsFormInfo.acdeoName" maxlength="40"
								placeholder="{{'Enter name of Assistant cum Data entry operator'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="acdeoSex" name="acdeoSex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.acdeoSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="acdeoContactNumber" name="acdeoContactNumber"
								ng-model="dcpuhrdetailsFormInfo.acdeoContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="acdeoEmail" name="acdeoEmail"
								ng-model="dcpuhrdetailsFormInfo.acdeoEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.acdeoEmail,'emailacdeoEmail')">
							<div id=emailacdeoEmail class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>11.Name of Out Reach Worker 1</span>
						</label>
						<div class="col-md-7">
							<input id="orw1Name" name="orw1Name"
								ng-model="dcpuhrdetailsFormInfo.orw1Name" maxlength="40"
								placeholder="{{'Enter name of Out Reach Worker 1'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="orw1Sex" name="orw1Sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.orw1Sex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="orw1ContactNumber" name="orw1ContactNumber"
								ng-model="dcpuhrdetailsFormInfo.orw1ContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="orw1Email" name="orw1Email"
								ng-model="dcpuhrdetailsFormInfo.orw1Email" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.orw1Email,'emailorw1Email')">
							<div id=emailorw1Email class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>12.Name of Out Reach Worker 2</span>
						</label>
						<div class="col-md-7">
							<input id="orw2Name" name="orw2Name"
								ng-model="dcpuhrdetailsFormInfo.orw2Name" maxlength="40"
								placeholder="{{'Enter name of Out Reach Worker 2'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="orw2Sex" name="orw2Sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.orw2Sex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="orw2ContactNumber" name="orw2ContactNumber"
								ng-model="dcpuhrdetailsFormInfo.orw2ContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="orw2Email" name="orw2Email"
								ng-model="dcpuhrdetailsFormInfo.orw2Email" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.orw2Email,'emailorw2Email')">
							<div id=emailorw2Email class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>13.Name of Out Reach Worker 3</span>
						</label>
						<div class="col-md-7">
							<input id="orw3Name" name="orw3Name"
								ng-model="dcpuhrdetailsFormInfo.orw3Name" maxlength="40"
								placeholder="{{'Enter name of Out Reach Worker 3'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"><span
							translate>Sex</span></label>
						<div class="col-md-7">
							<select id="orw3Sex" name="orw3Sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.orw3Sex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email IdContact Number</span>
						</label>
						<div class="col-md-7">
							<input id="orw3ContactNumber" name="orw3ContactNumber"
								ng-model="dcpuhrdetailsFormInfo.orw3ContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="orw3Email" name="orw3Email"
								ng-model="dcpuhrdetailsFormInfo.orw3Email" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.orw3Email,'emailorw3Email')">
							<div id=emailorw3Email class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>14.Name of Out Reach Worker 4</span>
						</label>
						<div class="col-md-7">
							<input id="orw4Name" name="orw4Name"
								ng-model="dcpuhrdetailsFormInfo.orw4Name" maxlength="40"
								placeholder="{{'Enter name of Social Worker 4'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="orw4Sex" name="orw4Sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.orw4Sex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="orw4ContactNumber" name="orw4ContactNumber"
								ng-model="dcpuhrdetailsFormInfo.orw4ContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="orw4Email" name="orw4Email"
								ng-model="dcpuhrdetailsFormInfo.orw4Email" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.orw4Email,'emailorw4Email')">
							<div id=emailorw4Email class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>15.Name of Social Worker 5</span>
						</label>
						<div class="col-md-7">
							<input id="orw5Name" name="orw5Name"
								ng-model="dcpuhrdetailsFormInfo.orw5Name" maxlength="40"
								placeholder="{{'Enter name of Social Worker 5'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="orw5Sex" name="orw5Sex" class="form-control"
								ng-model="dcpuhrdetailsFormInfo.orw5Sex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="orw5ContactNumber" name="orw5ContactNumber"
								ng-model="dcpuhrdetailsFormInfo.orw5ContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="orw5Email" name="orw5Email"
								ng-model="dcpuhrdetailsFormInfo.orw5Email" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.orw5Email,'emailorw5Email')">
							<div id=emailorw5Email class="error-style"></div>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>16.Other staff if any:</span>
						</label>
						<div class="col-md-7">
							<input id="otherStaffName" name="otherStaffName" maxlength="40"
								ng-model="dcpuhrdetailsFormInfo.otherStaffName"
								placeholder="{{'Enter name of other staff'| translate}}"
								class="form-control input-md" type="text">
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Sex</span>
						</label>
						<div class="col-md-7">
							<select id="otherStaffSex" name="otherStaffSex"
								class="form-control"
								ng-model="dcpuhrdetailsFormInfo.otherStaffSex">
								<option value="" disabled selected>{{'Select Sex' | translate}}
								</option>
								<!--  <option> CCI</option> -->
								<option ng-repeat="sex in sexList" ng-value="{{sex.id}}">{{lang=='en'?sex.name:sex.typeNameHindi}}</option>
							</select>
						</div>
						<div id="producedBeforeCwcerror" class="error-style"></div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Contact Number</span>
						</label>
						<div class="col-md-7">
							<input id="otherStaffContactNumber"
								name="otherStaffContactNumber"
								ng-model="dcpuhrdetailsFormInfo.otherStaffContactNumber"
								placeholder="{{'Enter contact number.'| translate}}"
								class="form-control input-md restrictE" only-ten-digits>
						</div>
					</div>
					<div class="form-group box-border-padding">
						<label class="col-md-4 control-label" for="textinput"> <span
							translate>Email Id</span>
						</label>
						<div class="col-md-7">
							<input id="otherStaffEmail" name="otherStaffEmail"
								ng-model="dcpuhrdetailsFormInfo.otherStaffEmail" type="text"
								ng-pattern="/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/"
								placeholder="{{'Enter Email'| translate}}" maxlength="60"
								class="form-control input-md "
								ng-blur="validateName(dcpuhrdetailsFormInfo.otherStaffEmail,'emailotherStaffEmail')">
							<div id=emailotherStaffEmail class="error-style"></div>
						</div>
					</div>
				</fieldset>
				<div style="text-align: center">
					<button id="button1id" name="button1id"
						ng-click="dcpuhrdetailsFormInfo.$invalid ? '' : validate()"
						ng-if="dcpuhrDetailsSubmitDisable && dcpuhrDetailsDisable"
						class="btn btn-info" type="submit">
						<span translate>Submit</span>
					</button>
				</div>
				<div style="text-align: center">
					<button ng-if="!dcpuhrDetailsDisable && dcpuhrDetailsSubmitDisable"
						id="button1id" name="button1id"
						ng-click="dcpuhrdetailsFormInfo.$invalid ? '' : validate()"
						class="btn btn-info" type="submit">
						<span translate>Update</span>
					</button>
					<button ng-if="dcpuhrDetailsDisable && !dcpuhrDetailsSubmitDisable"
						id="button2id" name="button2id" class="btn btn-info"
						ng-click="editField()">
						<span translate>Edit</span>
					</button>
					<button ng-if="dcpuhrDetailsDisable && !dcpuhrDetailsSubmitDisable"
						id="button3id" name="button3id" class="btn btn-info"
						ng-click="printDCPUHumanResourceDetailsSubmitData()">
						<span translate>Print</span>
					</button>
				</div>
			</form>
		</div>
	</div>
	<script src="resources/js/moment-with-locales.js"></script>
	<script src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript">	$(document).ready(function() {		$("#button1id").on('submit', function() {			$('#hrCCIconfirmationModal').modal('show');		});		$("#button3id").on('submit', function() {			$('#childIdModal').modal('show');		});		$('input').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});		$('textarea').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});	});</script>
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
</body>
</html>