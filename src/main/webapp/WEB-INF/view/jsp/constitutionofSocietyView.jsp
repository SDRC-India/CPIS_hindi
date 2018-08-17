	
<div ng-controller="ConstitutionViewController" ng-cloak>
<!-- <hr>
							<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 						
							<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
							<hr> -->
	<div style="margin-bottom:40px;">
		<div class="col-md-12 borderBottom" style="padding: 0; margin: 7px 0 15px 0;">
			<div class="col-md-6" style="height: 22px;">
				<div class="childlist-heading1">
					<span translate>Constitution of Society</span>
				</div>
			</div>
		</div>
		<div class="col-md-12 borderBottom" style="background:#f3f3f3; padding:5px 0; margin:0 10px 0 0;" id="example">
			<div class="col-md-3" style="height:35px; padding-left:-15px;">
				<select id="selectbasic" name="selectbasic" class="form-control" ng-model="constituionView.district">
					<option value="" disabled selected>
						{{'Select District' | translate}}
					</option>
					<option ng-repeat="item in districtList | orderBy:'areaName'" ng-value="{{item.areaId}}">{{item.areaName}}</option>
				</select>
			</div>
			<div class="col-md-3" style="height:35px;">
				<select id="selectbasic" name="selectbasic" class="form-control" ng-model="constituionView.constitutionType">
					<option value="" disabled selected>
						{{'Select Constitution Type' | translate}}
					</option>
					<option value="1">CWC</option>
					<option value="2">JJB</option>
					<option value="3">SJPU</option>
					<option value="4">DCPC</option>
					<option value="5">BCPC</option>
				</select>
			</div>
			<div class="col-md-3" style="height:35px;" ng-if="constituionView.constitutionType=='5'">
				<!-- 								<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput">Block name<span class="mandatory_star">&#42;</span> -->
			</label>
			<div class="col-md-12 col-sm-6 col-xs-6">
				<select id="blockId" name="blockName"										class="form-control" ng-model="constituionView.blockId" >
					<option value="" disabled selected>
						<span translate>Select block
						</option>
						<option ng-repeat="item in blockList" ng-value="{{item.id}}">{{item.name}}</option>
					</select>
				</div>
			</div>
			<div class="col-md-3" style="height:35px;">
				<button id="buttonid" name="buttonname" class="btn btn-info otherbut" type="submit" ng-click="showConstituion()"><span translate>Submit</span></button>
			</div>
		</div>
		<!-- 					<div class="col-md-12 borderBottom" style="padding: 0; margin: 7px 0 10px 0;"> -->
		<!-- 						<div class="col-md-3" style="height: 22px;"> -->
		<!-- 							<div class="childlist-heading1">District: <span>Agra</span></div> -->
		<!-- 						</div> -->
		<!-- 						<div class="col-md-3" style="height: 22px;"> -->
		<!-- 							<div class="childlist-heading1">Constitution Type: <span>CWC</span></div> -->
		<!-- 					</div> -->
		<!-- 					</div> -->
		<div  dir-paginate="constitutionViewObj in constitutionListObject |itemsPerPage: pageSize">
			<div class="col-md-12 borderBottom" style="background:#396e5e; padding-left:35px;">
				<div class="constitution-heading">
					<span translate>Constitution Formation Date:</span>
					<span>{{constitutionViewObj.constitutionFormationDate}}
					</div>
				</div>
				<table id="example" class="table table-striped table-bordered"	width="99%" cellspacing="0">
					<thead>
						<tr class="headingCLR">
							<th class="borderRight text-center" width="5%">
								<span translate>Sl. No</span>
							</th>
							<th class="borderRight" width="14%">
								<span translate>Designation</span>
							</th>
							<th class="borderRight" width="10%">
								<span translate>Date of Joining</span>
							</th>
							<th class="borderRight" width="20%">
								<span translate>Name</span>
							</th>
							<th class="borderRight" width="10%">
								<span translate>Contact No.</span>
							</th>
							<th class="borderRight" width="20%">
								<span translate>E-mail Id</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="obj in constitutionViewObj.constitutionViewModel">
							<td valign="middle" class="borderRight rowHeight text-center" width="5%">{{$index+1}}</td>
							<td valign="middle" class="borderRight rowHeight" width="14%">{{obj.designation}}</td>
							<td valign="middle" class="borderRight rowHeight" width="10%">{{obj.joiningDate === null || obj.joiningDate.trim() === "" ? "NA" : obj.joiningDate}}</td>
							<td valign="middle" class="borderRight rowHeight" width="20%">{{obj.name === null || obj.name.trim() === "" ? "NA" : obj.name}}</td>
							<td valign="middle" class="borderRight rowHeight" width="10%">{{obj.contactNo === null || obj.contactNo.trim() === "" ? "NA" : obj.contactNo}}</td>
							<td valign="middle" class="borderRight rowHeight" width="20%">{{obj.emailId === null || obj.emailId.trim() === "" ? "NA" : obj.emailId}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--Pagination -->
			<div ng-controller="PaginationController" style="margin-bottom:40px">
				<div class="text-right">
					<dir-pagination-controls boundary-links="true" on-page-change="pageChangeHandler(newPageNumber)" template-url="resources/html/pagination.html"></dir-pagination-controls>
				</div>
			</div>
			<!--End Pagination -->
		</div>
	</div>
</div>
<div class="modal fade" id="nooptionselected" tabindex="-1" role="dialog" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content"				style="border-radius: 0px; padding-top: 40px; padding-bottom: 40px;">
			<div class="modal-body">
				<p style="text-align: center; font-size: 18px;font-weight:bold;">
					<span translate>Please select options from all dropdown to proceed further.</span>
					<br>
					</p>
					<button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">{{'OK' | translate}}</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="nodata" tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content"				style="border-radius: 0px; padding-top: 40px; padding-bottom: 40px;">
				<div class="modal-body">
					<p style="text-align: center; font-size: 18px;font-weight:bold;">
						<span translate>No data available for current selection.</span>
						<br>
						</p>
						<button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">{{'OK' | translate}}</button>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">		function toggleIcon(e) {			$(e.target).prev('.panel-heading').find(".more-less").toggleClass(					'glyphicon-plus glyphicon-minus');		}		$('.panel-group').on('hidden.bs.collapse', toggleIcon);		$('.panel-group').on('shown.bs.collapse', toggleIcon);	</script>		    