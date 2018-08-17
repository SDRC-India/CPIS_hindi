<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notification</title>
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
<body ng-app="cctsApp" ng-controller="NotificationController" ng-cloak>
<jsp:include page="./common/cctsHeader.jsp" />
	<div class="col-md-12">
				<div class="box-right">
					<div class="col-md-12 borderBottom" style="padding: 0; margin: -5px 0px 15px 0;">
						<div class="col-md-6"
							style="padding: 0; margin: 1px; height: 22px;">
							<div class="childlist-heading1">Notification</div>
						</div>
						<div class="col-md-6 headingBar"
							style="padding: 0; margin: 0; height: 31px;">
							<div id="custom-search-input search " style="padding: 5px; margin: -5px;">
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
					<table id="example" class="table table-bordered table-hover"
						width="100%" cellspacing="0">
						<thead>
							<tr class="headingCLR">
								<th class="borderRight">Sl. No</th>
								<th class="borderRight">Notification Type</th>
								<th class="borderRight">Message</th>
								<th class="borderRight">Received on</th>
								<th class="borderRight">Mark As Read</th>
							</tr>
						</thead>
						<tbody>
							<tr dir-paginate="data in dataSet | filter:query |itemsPerPage: pageSize" ng-class="data.isRead==true?'unread':''">
								<td valign="middle" class="borderRight" >{{dataSet.indexOf(data)+1}}</td>
								<td valign="middle" class="borderRight">{{data.notificationType}}</td>
								<td valign="middle" class="borderRight">{{data.notificationMsg}}</td>
								<td valign="middle" class="borderRight">{{data.dateOfAction}}</td>
								
								<td valign="middle" class="borderRight" style="width:112px;background-color:#386d5c;text-align: center;">
								<input id="{{ data.notificationId }}" ng-click="setIsRead(data)" class="styled" type="checkbox" ng-disabled={{data.isRead}} ng-checked={{data.isRead}}
							          name="{{ data.notificationId }}" value="{{ data }}" />
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
		src="resources/js/AngularController/notificationController.js"></script>
			<script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script>
		
		<script type="text/javascript" src="resources/js/AngularController/paginationController.js"></script>
</body>
</html>