<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html lang="en">
<head>
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<title>CCTS Home</title>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<!-- Bootstrap css -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styles.css">
<link rel="stylesheet" href="resources/css/chatmodal.css">
<!-- <link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"> -->
<!-- Font awesome css -->
<!-- 	  <link rel="stylesheet" href="resources/fonts/font-awesome/font-awesome.min.css"> -->
<spring:url value="/resources/css/style.css" var="styleCss" />
<link href="${styleCss}" rel="stylesheet" />
</head>

<body ng-app="cctsApp" ng-controller="CCTSController" ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />
	
	<div class="modal fade" id="noChildSelected" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign" translate>Please select a child to proceed further.</h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="ciclChild" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <p style="text-align:center">
	          	<h4 class="selectChildalign" translate>This form is not applicable for Child in conflict with law.</h4>
	          </p>  
	          <button type="button" class="btn btn-default printOK" data-dismiss="modal" aria-hidden="true">OK</button>  
	      </div>    
	    </div>
	  </div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-3">

				<div class="box-border">
					<div class="childlist-heading11"><span translate>Filter by</span> <a id="update"><i class="glyphicon glyphicon-refresh pull-right filtrIcon" ng-click="resetFilters()"></i></a></div>

					<div class="panel-group" id="accordion" role="tablist"
						aria-multiselectable="true" style="margin: 0; padding: 0;">

<!-- 						<div class="panel panel-default"> -->
<!-- 							<div class="panel-heading" role="tab" id="headingOne"> -->
<!-- 								<h4 class="panel-title"> -->
<!-- 									<a role="button" data-toggle="collapse" -->
<!-- 										data-parent="#accordion" href="#collapseOne" -->
<!-- 										aria-expanded="true" aria-controls="collapseOne"> <i -->
<!-- 										class="more-less glyphicon glyphicon-plus"></i> Districts -->
<!-- 									</a> -->
<!-- 								</h4> -->
<!-- 							</div> -->
<!-- 							<div id="collapseOne" class="panel-collapse collapse" -->
<!-- 								role="tabpanel" aria-labelledby="headingOne"> -->
<!-- 								<div class="panel-body"> -->

<!-- 									<ul> -->
<!-- 										<li style="margin-bottom: 15px;"> -->
<!-- 											<div id="custom-search-input"> -->
<!-- 												<div class="input-group col-md-12"> -->
<!-- 													<input type="text" class="  search-query form-control" -->
<!-- 														placeholder="Search" /> <span class="input-group-btn"> -->
<!-- 														<button class="btn btn-danger" type="button"> -->
<!-- 															<span class=" glyphicon glyphicon-search"></span> -->
<!-- 														</button> -->
<!-- 													</span> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</li> -->
<!-- 										<li ng-repeat="district in tempDistrictList"><label><input -->
<!-- 												type="checkbox" class="myinput" ng-model="district.checked">{{district.areaName}}</label></li> -->
<!-- 										<li >70 more districts</li> -->
<!-- 									</ul> -->

<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

						<div class="panel-default">
							<div class="panel-heading" role="tab" id="headingTwo">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseTwo"
										aria-expanded="true" aria-controls="collapseTwo"> <i
										class="more-less glyphicon glyphicon-plus"></i> <span translate>Age</span>
									</a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse filterAge"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body">
									<ul>
										<li ng-repeat="item in ageFilterList"><label><input
												type="checkbox" class="myinput" ng-model="item.checked"
												ng-click="filterGlobal(item)"><span  ng-bind="item.name | translate"></span></label></li>
									</ul>
								</div>
							</div>
						</div>

						<div class="panel-default">
							<div class="panel-heading" role="tab" id="headingThree">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseThree"
										aria-expanded="true" aria-controls="collapseThree"> <i
										class="more-less glyphicon glyphicon-plus"></i> <span translate>Sex</span>
									</a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse filterAge"
								role="tabpanel" aria-labelledby="headingThree">
								<div class="panel-body">
									<ul>
										<li ng-repeat="sex in sexFilterList"><label><input
												type="checkbox" class="myinput" ng-model="sex.checked"
												ng-click="filterGlobal(sex)"><span  ng-bind="sex.name | translate"></span></label></li>
									</ul>
								</div>
							</div>
						</div>
						
					<div class="chat_window popup-box" id="qnimate">
			        	<div class="top_menu">
			        		<div class="buttons"><div class="button close"></div>
			        			<div class="button minimize"></div><div class="button maximize"></div></div>
			        			<div class="title titletext"><span style="color: #94c11e">C</span>hild <span style="color: #94c11e">S</span>tatus</div></div><ul class="messages"></ul>
<!-- 			        			<div class="bottom_wrapper clearfix"> -->
<!-- 			        			</div> -->
			        			<ul>
			        			<li class="chattext"><span translate>CCI Name</span> : <label ng-if="selectedChild.cciName!=null">{{selectedChild.cciName}}</label><label ng-if="selectedChild.cciName==null"><b>No CCI</b></label></li>
			        			<li class="chattext"><span translate>Social Investigation Report</span> : <label class="glyphicon glyphicon-ok" ng-if="selectedChild.sirFilled==1" style="color: green"></label><label class="glyphicon glyphicon-remove" ng-if="selectedChild.sirFilled!=1" style="color: red"></label></li>
			        			<li class="chattext"><span translate>Individual Care Plan</span> : <label class="glyphicon glyphicon-ok" ng-if="selectedChild.icpFilled==1" style="color: green"></label><label class="glyphicon glyphicon-remove" ng-if="selectedChild.icpFilled!=1" style="color: red"></label></li>
			        			<li class="chattext"><span translate>Case History</span> : <label class="glyphicon glyphicon-ok" ng-if="selectedChild.caseHistoryFilled==1" style="color: green"></label><label class="glyphicon glyphicon-remove" ng-if="selectedChild.caseHistoryFilled!=1" style="color: red"></label></li>
			        			<li class="chattext"><span translate>Final Order</span> : <label class="glyphicon glyphicon-ok" ng-if="selectedChild.finalOrderFilled==1" style="color: green"></label><label class="glyphicon glyphicon-remove" ng-if="selectedChild.finalOrderFilled!=1" style="color: red"></label></li>
			        			</ul>
<!-- 			        			<div class="message_template"><li><div class="text_wrapper"><div class="text">Test</div></div></li></div> -->
					</div>

<!-- 						<div class="panel panel-default"> -->
<!-- 							<div class="panel-heading" role="tab" id="headingFour"> -->
<!-- 								<h4 class="panel-title"> -->
<!-- 									<a role="button" data-toggle="collapse" -->
<!-- 										data-parent="#accordion" href="#collapseFour" -->
<!-- 										aria-expanded="true" aria-controls="collapseFour"> <i -->
<!-- 										class="more-less glyphicon glyphicon-plus"></i> Caste -->
<!-- 									</a> -->
<!-- 								</h4> -->
<!-- 							</div> -->
<!-- 							<div id="collapseFour" class="panel-collapse collapse" -->
<!-- 								role="tabpanel" aria-labelledby="headingFour"> -->
<!-- 								<div class="panel-body"> -->
<!-- 									<ul> -->
<!-- 										<li ng-repeat="caste in casteFilterList"><label><input type="checkbox" -->
<!-- 											ng-model="caste.checked" class="myinput"> -->
<!-- 											{{caste.name}}</label></li> -->
<!-- 									</ul> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 						<div class="panel panel-default"> -->
<!-- 							<div class="panel-heading" role="tab" id="headingFive"> -->
<!-- 								<h4 class="panel-title"> -->
<!-- 									<a role="button" data-toggle="collapse" -->
<!-- 										data-parent="#accordion" href="#collapseFive" -->
<!-- 										aria-expanded="true" aria-controls="collapseFive"> <i -->
<!-- 										class="more-less glyphicon glyphicon-plus"></i> Education -->
<!-- 									</a> -->
<!-- 								</h4> -->
<!-- 							</div> -->
<!-- 							<div id="collapseFive" class="panel-collapse collapse" -->
<!-- 								role="tabpanel" aria-labelledby="headingFive"> -->
<!-- 								<div class="panel-body"> -->
<!-- 									<ul> -->
<!-- 										<li ng-repeat="education in educationFilterList"><label><input -->
<!-- 												type="checkbox" class="myinput" ng-model="education.checked" -->
<!-- 												>{{education.name}}</label></li> -->
<!-- 									</ul> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

					</div>
				</div>
			</div>
	<div class="col-md-3"></div>
			<div class="col-md-9">
				<div class="box-right">
					<div class="col-md-12 borderBottom" style="padding: 0; margin: -5px 0px 15px 0;">
						<div class="col-md-6"
							style="padding: 0; margin: 1px; height: 22px;">
							<div class="childlist-heading1"><span translate>Child Records</span></div>
						</div>
						<div class="col-md-6 headingBar"
							style="padding: 0; margin: 0; height: 31px;">
							<div id="custom-search-input search " style="padding: 5px; margin: -5px;">
								<div class="input-group col-md-6"
									style="float: right; height: 30px;">
									<input type="text" class="search-query form-control borderSearch serchOptionClr"
										style="height: 30px;border: none;" placeholder="{{'Search' | translate}}" ng-model="query" />
									<span class="input-group-btn">
										<button class="btn btn-danger btnsize" style="margin-left:0px;" type="button">
											<span class=" glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>

					<table id="example" class="table table-striped table-bordered"
						width="100%" cellspacing="0">
						<thead>
							<tr class="headingCLR">
								<th class="borderRight" translate>Sl. No</th>
								<th class="borderRight" translate>Child ID</th>
								<th class="borderRight" translate>Child Name</th>
								<th class="borderRight" translate>Record Created on</th>
								<th class="borderRight" style="width:140px;text-align: center;" translate>Photo</th>
								<th class="borderRight" style="width:112px;background-color:#386d5c;"></th>
							</tr>
						</thead>
						<tbody>
							<tr dir-paginate="data in dataSet | filter:query |itemsPerPage: pageSize" >
								<td valign="middle" class="borderRight" >{{dataSet.indexOf(data)+1}}</td>
								<td valign="middle" class="borderRight">{{data.childId}}</td>
								<td valign="middle" class="borderRight">{{data.childName}}</td>
								<td valign="middle" class="borderRight">{{data.recordCreatedOn}}</td>
								<td valign="middle" class="borderRight" style="text-align:center">
								<img src="{{data.childPhoto}}" height="80" width="80" ></td>
								<td valign="middle" class="borderRight" style="width:112px;background-color:#386d5c;text-align: center;">
<!-- 								<input type="radio" name="optradio" ng-model="radioChecked" ng-click="setSelectedChild(data)"> -->
								<input id="{{ data.childId }}" ng-click="setRadio(data);setSelectedChild(data)" ng-checked="data.childId == radioSelected" class="styled" type="radio"
							          name="{{ data.childName }}" value="{{ data.childId }}" />
								</td>

							</tr>

						</tbody>
					</table>
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
	<jsp:include page="./common/cctsFooter.jsp" />

	<script type="text/javascript" src="resources/js/jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/angular.min.js"></script>
    <script type="text/javascript" src="resources/js/pagination.js"></script>
  
	<script>
		var app = angular.module('cctsApp', ['gettext','angularUtils.directives.dirPagination']);
		var myAppConstructor = angular.module('cctsApp');
	</script>
	<script src="resources/js/angular-gettext.min.js"></script>
	<script type="text/javascript" src="resources/js/translations.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularController/cCTSController.js"></script>
<!-- 			<script type="text/javascript" -->
<!-- 		src="resources/js/AngularController/headerController.js"></script> -->

	<script type="text/javascript">
	$( document ).ready( function() {
		  $( "#update" ).on( "click", function( e ) {
		    var $icon = $( this ).find( ".glyphicon.glyphicon-refresh" ),
		      animateClass = "glyphicon-refresh-animate";

		    $icon.addClass( animateClass );
		    // setTimeout is to indicate some async operation
		    window.setTimeout( function() {
		      $icon.removeClass( animateClass );
		    }, 500 );
		}); 
    });	  
	</script>

	<script type="text/javascript">
		function toggleIcon(e) {
			$(e.target).prev('.panel-heading').find(".more-less").toggleClass(
					'glyphicon-plus glyphicon-minus');
		}
		$('.panel-group').on('hidden.bs.collapse', toggleIcon);
		$('.panel-group').on('shown.bs.collapse', toggleIcon);
	</script>
	
  <script type="text/javascript" src="resources/js/AngularController/paginationController.js"></script>


	<!-- <script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script> -->
	<!-- <script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script> -->
</body>
</html>