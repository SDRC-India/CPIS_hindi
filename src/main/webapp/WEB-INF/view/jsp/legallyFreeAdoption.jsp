<div ng-controller="LegallyFreeController" ng-cloak><%@page
		import="org.sdrc.cpis.util.Constants"%><%@page
		import="org.sdrc.cpis.models.UserDetailModel"%>
	<%	UserDetailModel user = null;		user = (UserDetailModel) request.getSession().getAttribute(Constants.USER_PRINCIPAL);%>
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<input type="hidden" id="cwcId" value="<%=user.getAreaId()%>">
	<div class="modal fade" id="confirmationlegallyfree" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span translate>Please click on the submit button to save
							the details.</span>
					</h4>
				</div>
				<div class="modal-body">
					<p style="text-align: center">
						<button id="submitData" name="button3id"
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
	<div class="modal fade" id="legallyfree" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4 class="modal-title" id="myModalLabel">
						<span translate>The form has been saved successfully.</span>
					</h4>
					<p style="text-align: center">
						<button id="button5id" name="button6id"
							class="btn btn-info bigbutton" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true"
							ng-click="printLegallyFreeData()">
							<span translate>Print</span>
						</button>
						<button id="button5id" name="button5id"
							class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">
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
			<span translate>CERTIFICATE DECLARING THE CHILD LEGALLY FREE
				FOR ADOPTION</span> <br> <span translate>Form 25</span> <br> <span
				translate>[Rule 19(29)]</span>
		</div>
		<form class="form-horizontal basicchildform"
			id="legallyFreeForAdoption" name="legallyFreeForAdoption">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;">
					<span translate>CERTIFICATE DECLARING THE CHILD LEGALLY FREE
						FOR ADOPTION</span>
				</div>
				<jsp:include page="./common/childNameStrip.jsp" />
				<div class="form-group box-border-padding interimPlanmargintop">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>1.District of CWC</span>
					</label>
					<div class="col-md-7">
						<input id="cwcDistrictName" name="cwcDistrictName"
							placeholder="{{'Enter name of the child'| translate}}"
							class="form-control input-md" type="text" ng-model="cwcDistrict"
							readonly ng-disabled="legallyFreeDisable">
					</div>
					<div id="producedBeforeCwcerror" class="error-style"></div>
				</div>
				<div class="form-group box-border-padding ">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>2.Child Name</span>
					</label>
					<div class="col-md-7">
						<input id="aftercareChildName" name="aftercareChildName"
							placeholder="{{'Enter name of the child'| translate}}"
							class="form-control input-md" type="text"
							ng-model="selectedChild.childName" readonly
							oninput="setCustomValidity('')" ng-disabled="legallyFreeDisable">
						<div id="aftercareChildNameerror" class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label">
						<span translate>3. Date of Birth</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="legallyFreeDateOfBirth" readonly
							class="form-control" ng-model="legallyFormInfo.dateOfBirth"
							ng-disabled="legallyFreeDisable">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>4. Name of SAA/CCI</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<select id="producedBeforeCwc" name="producedBeforeCwc"
							class="form-control" ng-disabled="legallyFreeDisable"
							ng-model="legallyFormInfo.saaOrCciId"
							ng-change="cciSelectChange(legallyFormInfo.saaOrCciId)" required>
							<option value="" disabled selected>{{'Select SAA/CCI' | translate}}</span>
							</option>
							<option ng-repeat="cci in cciList" ng-value="cci.cciId">{{cci.name}}</option>
						</select>
					</div>
					<div id="producedBeforeCwcerror" class="error-style"></div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>5. Address of SAA/CCI</span>
					</label>
					<div class="col-md-7">
						<textarea id="motherName" ng-disabled="legallyFreeDisable"
							ng-model="cciAddress" readonly class="form-control input-md"
							type="text"></textarea>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>6. Order No</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
						<input maxlength="15" id="motherName"
							placeholder="{{'Enter order no'| translate}}"
							ng-disabled="legallyFreeDisable" class="form-control input-md"
							type="text" ng-model="legallyFormInfo.orderNo" required>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>7. Order Date</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="legallyFreeOrderDate" readonly
							class="form-control" ng-model="legallyFormInfo.orderDate"
							ng-disabled="legallyFreeDisable">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>8. Inquiry report of the Probation Officer/ Child Welfare Officer / Social Worker / Case Worker/any other </span>
					</label>
					<div class="col-md-7 " style="padding-top: 14px;">
						<input class="input-file" type="file"
							file-model="escortOrderModel" ng-if="!legallyFreeDisable"
							name="inquiryReport[]" id="js-upload-files"
							ng-files="getReport($files,'inquiryReport')" accept=".pdf"
							style="display: inline-block; margin-top: 20px;">
						<button class="col-md-3" class="btn btn-sm btn-primary"
							ng-if="legallyFreeDisable && legallyFormInfo.inquiryReport"
							ng-disabled="(legallyFormInfo.inquiryReport==null)?true:false"
							id="js-upload-submit"
							ng-click="downloadPdf(legallyFormInfo.inquiryReport,'legally_free_for_adoption')"
							ng-if="legallyFreeDisable && legallyFormInfo.inquiryReport"
							style="margin-left: 20px;">
							<span translate>Download</span>
						</button>
						<label class="col-md-7 control-label"
							ng-if="legallyFreeDisable && !legallyFormInfo.inquiryReport">
							<span translate>No data available</span>
						</label>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>9. Deed of surrender executed by the biological parent(s) or the legal guardian of the child before this Committee on (date)</span>
					</label>
					<div class="col-md-7 " style="padding-top: 14px;">
						<input class="input-file" type="file"
							file-model="escortOrderModel" ng-if="!legallyFreeDisable"
							name="surrenderDeed[]" id="js-upload-files"
							ng-files="getReport($files,'surrenderDeed')" accept=".pdf"
							style="display: inline-block; margin-top: 20px;">
						<button class="col-md-3" class="btn btn-sm btn-primary"
							ng-if="legallyFreeDisable && legallyFormInfo.surrenderDeed"
							ng-disabled="(legallyFormInfo.surrenderDeed==null)?true:false"
							id="js-upload-submit"
							ng-click="downloadPdf(legallyFormInfo.surrenderDeed,'legally_free_for_adoption')"
							style="margin-left: 20px;">
							<span translate>Download</span>
						</button>
						<label class="col-md-7 control-label"
							ng-if="legallyFreeDisable && !legallyFormInfo.surrenderDeed">
							<span translate>No data available</span>
						</label>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>10. Declaration submitted by District Child Protection Unit and the Child Care Institution or Specialized Adoption Agency</span>
					</label>
					<div class="col-md-7 " style="padding-top: 14px;">
						<input class="input-file" ng-if="!legallyFreeDisable" type="file"
							file-model="escortOrderModel" name="declaration[]"
							id="js-upload-files" ng-files="getReport($files,'declaration')"
							accept=".pdf" style="display: inline-block; margin-top: 20px;">
						<button class="col-md-3" class="btn btn-sm btn-primary"
							ng-if="legallyFreeDisable && legallyFormInfo.declaration"
							ng-disabled="(legallyFormInfo.declaration==null)?true:false"
							id="js-upload-submit"
							ng-click="downloadPdf(legallyFormInfo.declaration,'legally_free_for_adoption')"
							style="margin-left: 20px;">
							<span translate>Download</span>
						</button>
						<label class="col-md-7 control-label"
							ng-if="legallyFreeDisable && !legallyFormInfo.declaration">
							<span translate> No data available</span>
						</label>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 col-sm-12 col-xs-12 control-label"
						for="textinput"> <span translate>11. Date</span> <span
						class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<input type="text" id="legallyFreeDate"
							ng-disabled="legallyFreeDisable" readonly class="form-control"
							ng-model="legallyFormInfo.legallyFreeDate">
						<div id="childProducedDateerror" class="error-style"></div>
					</div>
					<i class="fa fa-calendar fa-5x"
						style="font-size: 27px !important; margin-top: 3px; color: #396c5d; margin-left: -7px;"
						aria-hidden="true"></i>
				</div>
				<div class="form-group box-border-padding ">
					<label class="col-md-4 control-label" for="textinput"> <span
						translate>12. Place</span> <span class="mandatory_star">&#42;</span>
					</label>
					<div class="col-md-7">
<!-- 					thirty-characters-validation -->
						<input id="place" name="place" maxlength="30"
							ng-model="legallyFormInfo.legallyFreePlace"
							placeholder="{{'Enter place'| translate}}"
							ng-disabled="legallyFreeDisable" class="form-control input-md"
							type="text" required>
						<div id="aftercareChildNameerror" class="error-style"></div>
					</div>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id"
						ng-click="legallyFreeForAdoption.$invalid ? '' : validatelegallyForm()"
						ng-if="!legallyFreeDisable" class="btn btn-info">
						<span translate>Submit</span>
					</button>
					<button ng-if="legallyFreeDisable" id="button2id" name="button2id"
						class="btn btn-info" ng-click="printLegallyFreeData()">
						<span translate>Print</span>
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript" src="resources/js/download.js"></script>
<script type="text/javascript">	$(document).ready(function() {		$('input').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});		$('textarea').blur(function() {			var value = $.trim($(this).val());			$(this).val(value);		});	});</script>