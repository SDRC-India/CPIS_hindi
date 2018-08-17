<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<title>Role Management User List</title>
<!-- Bootstrap css -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styles.css">
<!-- <link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"> -->
<!-- Font awesome css -->
<!-- 	  <link rel="stylesheet" href="resources/fonts/font-awesome/font-awesome.min.css"> -->
<spring:url value="/resources/css/style.css" var="styleCss" />
<link href="${styleCss}" rel="stylesheet" />
</head>

<body ng-app="cctsApp" ng-controller="CCTSController" ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />


	<div class="container">
		<div class="row" style="margin-bottom:40px;">
					<div class="col-md-12 borderBottom" style="padding: 0; margin: 7px 0 15px 0;">
						<div class="col-md-6" style="height: 22px;">
							<div class="childlist-heading1">Role Management User List</div>
						</div>
												<div class="col-md-6 headingBar"
							style="padding: 0; margin: 0; height: 31px;">
							<div id="custom-search-input search " style="padding: 5px; margin: -6px;">
								<div class="input-group col-md-6"
									style="float: right; height: 30px;">
									<input type="text" class="search-query form-control borderSearch serchOptionClr"
										style="height: 30px;border: none;" placeholder="Search" ng-model="query" />
									<span class="input-group-btn">
										<button class="btn btn-danger btnsize" style="margin-left:0px;" type="button">
											<span class=" glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>

					<table id="example" class="table table-striped table-bordered"	width="99%" cellspacing="0">
						<thead>
							<tr class="headingCLR">
								<th class="borderRight text-center" width="5%">Sl. No</th>
								<th class="borderRight" width="34%">Name</th>
								<th class="borderRight" width="15%">User Name</th>
								<th class="borderRight" width="15%">Designation</th>
								<th class="borderRight" width="20%">E-mail Id</th>
								<th class="borderRight" width="9%">Remove User</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="5%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="34%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="15%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="15%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="20%">1</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="9%"><i class="fa fa-trash" aria-hidden="true"></i></td>

							</tr>
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="5%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="34%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="15%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="15%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="20%">1</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="9%"><i class="fa fa-trash" aria-hidden="true"></i></td>

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
		var app = angular.module('cctsApp', []);
		var myAppConstructor = angular.module('cctsApp',['angularUtils.directives.dirPagination']);
	</script>
	<script type="text/javascript"
		src="resources/js/AngularService/commonService.js"></script>
	<script type="text/javascript"
		src="resources/js/AngularController/cCTSController.js"></script>

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