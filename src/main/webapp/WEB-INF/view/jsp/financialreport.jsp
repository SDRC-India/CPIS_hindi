
<div ng-controller="financialReportController" ng-cloak>

	<div class="modal fade" id="frSuccessResponse" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					 <h4 class="modal-title" id="myModalLabel">
						<span translate>The file submitted successfully.</span></h4>
						<p style="text-align: center">
						<button id="button5id" name="button5id"	class="btn btn-info bigbutton2" type="submit" class="close"
							data-dismiss="modal" aria-hidden="true" ng-click="reDirectPage()"><span translate>Ok</span></button>
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
	<div ng-show="showFinanceForm">
		<form class="form-horizontal basicchildform" name="financialForm"
			id="financialForm">
			<fieldset>
				<div class="grey-header marginTop"
					style="border-top: none; margin-top: 4px;"><span translate>Financial Report</span></div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>1.Select CCI</span> <span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<select id="producedBeforeCwc" name="producedBeforeCwc" required
							class="form-control" ng-model="financialReport.cciId">
							<option value="" disabled selected translate>Select CCI</option>
							<option ng-repeat="cci in cciList" ng-value="cci.id">{{cci.name}}</option>
						</select>
					</div>
					<div id="producedBeforeCwcerror" class="error-style"></div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>2.Select Period</span> <span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<select id="producedBeforeCwc" name="producedBeforeCwc" required
							class="form-control" ng-model="financialReport.quarterId">
							<option value="" disabled selected translate>Select period</option>
							<option ng-repeat="quarter in quarterlist" ng-value="quarter.id">{{quarter.name}}</option>
						</select>
					</div>
					<div id="producedBeforeCwcerror" class="error-style"></div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>3.Select Year</span> <span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7">
						<select id="producedBeforeCwc" name="producedBeforeCwc" required
							class="form-control" ng-model="financialReport.yearId">
							<option value="" disabled selected translate>Select year</option>
							<option ng-repeat="yr in yearlist" ng-value="yr.id">{{yr.name}}</option>
						</select>
					</div>
					<div id="producedBeforeCwcerror" class="error-style"></div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput"><span translate>4.Financial Report</span> <span class="mandatory_star">&#42;</span></label>
					<div class="col-md-7 ">
						<input class="input-file" type="file" file-model="financialReport"
							name="financial" id="financialReportId" required
							ng-files="getReport($files,'financialReportPdf')" accept=".pdf"
							style="display: inline-block; margin-top: 0px;">
					</div>
				</div>
				<div style="text-align: center">
					<button id="button1id" name="button1id"
						ng-click="financialForm.$invalid ? '' : saveFinancialReport()"
						class="btn btn-info" type="submit" style="margin-bottom: 30px; margin-top: 20px;"><span translate>Submit</span></button>
    
				</div>
	</fieldset>
		</form>
		</div>
				<div class="childlist-heading1 borderPersonal"
					style="padding: 0; margin: -5px 0px 15px 0;"><span translate>Records of Financial Report</span></div>

				<table id="example" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr class="headingCLR">
							<th class="borderRight" style="padding-right: 50px;"><span translate>Sl. No</span></th>
							<th class="borderRight" style="padding-right: 50px;"><span translate>CCI Name</span></th>
							<th class="borderRight" style="padding-right: 50px;"><span translate>Year</span></th>
							<th class="borderRight" style="padding-right: 50px;"><span translate>Period</span></th>
							<th class="borderRight" style="padding-left: 50px;"><span translate>Report</span></th>

						</tr>
					</thead>

					<tbody>
						<tr ng-repeat="data in financialReportlist">
							<td>{{$index+1}}
							</td>
							<td>
							{{data.value2}}
							</td>
							<td>
							{{data.name}}
							</td>
							<td>
							{{data.other}}
							</td>
							<td>
								<button
									class="col-md-3 btn btn-sm btn-primary inspctiondownload" type="button"
									id="js-upload-submit" ng-click="downloadPdf(data.value,'report_summary')" ng-if="data.value" 
									style="margin-left: 20px;width: 100px;" translate>Download</button>
							</td>
						</tr>
					</tbody>
				</table>
	</div>
</div>

<script type="text/javascript">
		$(document).ready(function() {
			$("#button3id").on('submit', function(){
				  $('#childIdModal').modal('show');
			});
			$( "#datepicker" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});
			$( "#datepicker1" ).datepicker({dateFormat: "yy-mm-dd", maxDate: '+0d'});
			$('input').blur(function() {
    		    var value = $.trim( $(this).val() );
    		    $(this).val( value );
    		});
			$('textarea').blur(function() {
    		    var value = $.trim( $(this).val() );
    		    $(this).val( value );
    		});
			
		});
	</script>


<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
	var="jQueryUiCore" />
<script type="text/javascript" src="resources/js/download.js"></script>
<script src="${jQueryUiCore}"></script>