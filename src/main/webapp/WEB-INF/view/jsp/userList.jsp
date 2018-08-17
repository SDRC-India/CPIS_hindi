<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<html>
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="resources/js/jquery-min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/angular.min.js"></script>
<script src="resources/js/AngularController/login_controller.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/styles.css">
<title>User list</title>
</head>
<body ng-app="userRole" ng-controller="UserRoleController" ng-cloak>

<jsp:include page="./common/adminHeader.jsp" />

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">Do you want to delete the user?</h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button3id" name="button3id" class="btn btn-info bigbutton" type="submit" ng-click="deleteUser()" class="close" data-dismiss="modal" aria-hidden="true">Yes</button>
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true">No</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="deleteSuccessModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog modalCenter">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">User deleted successfully.</h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reloadPage()">Close</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>	
	<div class="container">
		<div class="row">
		<div class="col-md-12 headingBar" style="padding: 0; margin: 0; height: 31px;">
		<div class="col-md-7" style="padding: 5px; margin: 10px 0 0 20px;"></div>
							<div id="custom-search-input search " style="padding: 5px; margin: 10px 5px 0 0;">
								<div class="input-group col-md-3" style="float: right; height: 30px;">
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
			<div class="col-md-12" style="padding-top:20px">
			<table id="example" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr class="headingCLR">
							<th class="borderRight" style="padding-right: 30px; width:7%;">Sl. No</th>
							<th class="borderRight" style="padding-right: 30px; width:48%;">Name</th>
							<th class="borderRight" style="padding-right: 30px; width:30%;">User Name</th>
							<th class="borderRight" style="padding-right: 30px; width:15%;">Change Email</th>
							<th class="borderRight" style="padding-right: 30px; width:15%;">Delete User</th>
						</tr>
					</thead>

					<tbody>
						<tr dir-paginate="user in userList |filter:query | itemsPerPage: pageSize" style="padding-right: 30px; width:7%;">
							<td>{{$index+1}}</td>
							<td style="padding-right: 30px; width:48%;">
							{{user.name}}</td>
							<td style="padding-right: 30px; width:30%;">
							{{user.userName}}</td>
							<td style="padding-right: 30px; width:15%;">
<!-- 							<a href="adminChangePassword?email={{user.email}}"><button	class="btn btn-sm btn-primary" type="button" id="changepw" ng-click="">Change Password</button></a> -->
							<button	class="btn btn-sm btn-primary" type="button" id="changepw" ng-click="changepw(user.email)">Change Email</button>
							</td>
							<td style="padding-right: 30px; width:15%;">
							<button	class="btn btn-sm btn-primary" type="button" id="deleteUser" ng-click="openDeleteModal(user.userId)">Delete</button>
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>	
	<script>
	var app = angular.module('userRole', []);
	var myAppConstructor= angular.module('userRole',['angularUtils.directives.dirPagination']);
	</script>
	<script type="text/javascript" src="resources/js/AngularService/commonService.js"></script>
	
	<script src="resources/js/AngularController/userRole.js"></script>
	<script src="resources/js/moment-with-locales.js"></script>
    <script src="resources/js/jquery-ui.js"></script>
    <script type="text/javascript" src="resources/js/AngularController/paginationController.js"></script>
</body>
</html>