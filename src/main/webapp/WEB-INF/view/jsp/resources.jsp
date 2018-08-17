<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">
<head>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
<!-- 	  <meta charset="utf-8"> -->
<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<title>CCTS Home</title>
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

<body ng-cloak>
	<jsp:include page="./common/cctsHeader.jsp" />


	<div class="container">
		<div class="row" style="margin-bottom:40px;">
					<div class="col-md-12 borderBottom" style="padding: 0; margin: 7px 0 15px 0;">
						<div class="col-md-6" style="height: 22px;">
							<div class="childlist-heading1">Resources</div>
						</div>
						<div class="col-md-6 headingBar" style="height: 31px;">
							<div id="custom-search-input search " style="padding: 5px; margin: -6px;">
								<div class="input-group col-md-6" style="float: right; height: 30px;">
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
								<th class="borderRight text-center" width="10%">Sl. No</th>
								<th class="borderRight" width="70%">File Name</th>
								<th class="borderRight text-center" width="20%">Download File</th>
							</tr>
						</thead>

						<tbody>
						<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 14<br>
								[Rules 7 (1) (ii), 13(8)(iv)( C ) (cd), 17(vi), 19(20), 65(3)(viii), 69E(2), 69 I (4), 69J(1), 69J(3)]<br>
								REHABILITATION CARD
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 14.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">1</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप  14<br>
								[नियम 7 (ii), 13(8)(vi)(ग)(गघ), 17(vi), 19(20), 65(3)(viii), 69ङ(2), 69झ(4),69त्र(1),69त्र(3)]
								पुनर्वास कार्ड
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 14.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
						
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">2</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 21<br>
								[Rule 19(3)]<br>
								ORDER FOR SOCIAL INVESTIGATION REPORT OF CHILD IN NEED OF CARE AND PROTECTION
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 21.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">2</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 21<br>
								[नियम 19(3)]<br>
								देखरेख तथा संरक्षण की आवश्यकता रखने वाले बालक/बलिका के  लिए सामाजिक  जांच के आदेश
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 21.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">3</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 30<br>
								[Rule 23(9)]<br>
								HOME STUDY REPORT FOR PROSPECTIVE FOSTER PARENTS
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 30.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">3</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 30<br>
								{नियम 23(9)}<br>
								संभावित पालक माता पिता के घर की अध्ययन रिपोट
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 30.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">4</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 31<br>
								[Rule 23(4)]<br>
								CHILD STUDY REPORT
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 31.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">4</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 31<br>
								[नियम 23(4)]<br>
								बाल अध्ययन  रिपोट
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 31.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">5</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 35<br>
								[Rule 23(18)]<br>
								MONTHLY INSPECTION OF FOSTER FAMILIES/GROUP FOSTER CARE
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 35.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">5</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 35<br>
								[नियम 23(18)]<br>
								पालन पोषण करने वाले परिबरों/समूह पालन पोषण देखरेख का मासिक निरिक्षन
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 35.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">6</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 42<br>
								[Rules 69 (D) (4)]<br>
								OVERNIGHT PROTECTIVE STAY
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 42.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">6</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 42<br>
								[नियम 69 (घ) (4)]<br>
								रातभर का संरक्षण प्रवास
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 42.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">7</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 45<br>
								[Rules 82(4)]<br>
								ESCORT ORDER
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 45.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">7</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 45<br>
								{नियम  82(4)}<br>
								मार्गरक्षा आदेश
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 45.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">8</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 2<br>
								[Rule 8 (7)]<br>
								UNDERTAKING BY THE PARENT OR GUARDIAN OR FIT PERSON GIVEN INTERIM CUSTODY PENDING INQUIRY
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 2.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">12</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 2<br>
								[नियम  8(7)]<br>
								उस माता-पिता अथवा संरक्षक अथवा उपयुक्त ब्यक्ति कि  वचनबद्धता  जिसे जांच के लंबन के दौरान अंतरिम अभिरक्षा दी गयी है 
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 2_Hi.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">9</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 5<br>
								[Rule 10 (2)]<br>
								ORDER FOR SOCIAL INVESTIGATION REPORT
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 5.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">13</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 5<br>
								[नियम  10(2)]<br>								
								सामाजिक जांच रिपोर्ट के लिए आदेश
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 5_Hi.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">10</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 8<br>
								[Rule 11(6)]<br>
								UNDERTAKING/ BOND TO BE EXECUTED BY A PARENT/ GUARDIAN/ /FIT PERSON IN WHOSE CARE A CHILD IN CONFLICT WITH LAW IS PLACED
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 8.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">10</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप  8<br>
								[नियम 11(6)]<br>								
								माता-पिता/संरक्षक/ योग्य ब्यक्ति जिसकी देखरेख में कानून का उल्लंधन करने बाले बालक/बलिका की रखा गया है द्वारा निष्पादित की जाने बाली  वचनबद्धता/बंध पत्र
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 8_Hi.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">11</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								FORM 9<br>
								[Rules 11(7)]<br>
								PERSONAL BOND BY CHILD
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 9.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							<tr>
								<td valign="middle" class="borderRight rowHeight text-center" width="10%">11</td>
								<td valign="middle" class="borderRight rowHeight" width="70%">
								प्ररूप 9<br>
								[नियम  11(7)]<br>
								बालक द्वारा ब्यक्तिगत बंध पत्र
								</td>
								<td valign="middle" class="borderRight rowHeight text-center" width="20%">
								<a href="resources/pdftemplate/uploadpdftemplate/FORM 9_Hi.pdf"><button class="btn btn-info otherbut" type="submit">Download</button></a>
								</td>
							</tr>
							
						</tbody>
					</table>
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
	<!-- <script type="text/javascript"
		src="resources/js/AngularController/headerController.js"></script> -->

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