<!DOCTYPE html><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<html lang="en">
	<head>
		<!-- 	  <meta charset="utf-8"> -->
		<!-- 	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
		<title>Escort Order</title>
		<!-- Bootstrap css -->
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
			<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
				<!-- <script type="text/javascript" src="resources/js/angular.min.js"></script> -->
				<!-- Font awesome css -->
				<!-- 	  <link rel="stylesheet" href="resources/fonts/font-awesome/font-awesome.min.css"> -->
				<spring:url value="/resources/css/style.css" var="styleCss" />
				<link href="${styleCss}" rel="stylesheet" />
				<link rel="stylesheet" href="resources/css/styles.css">
					<!-- jquery-ui.css file is not that big so we can afford to load it -->
					<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"	var="jQueryUiCss" />
					<link href="${jQueryUiCss}" rel="stylesheet"></link>
					<style>	#js-upload-files{		    display: inline-block;	}</style>
				</head>
				<body ng-controller="FinalOrderController">
					<div class="box-border box-border-padding">
						<!-- <hr>
							<a href="javascript:void(0)" ng-click="changeLanguage('en')">English</a> | 						
							<a href="javascript:void(0)" ng-click="changeLanguage('hi_IN')">Hindi</a>
							<hr> -->
								<div class="childlist-heading1 borderPersonal">
									<span translate>ESCORT ORDER</span>
									<br>
										<span translate>FORM 45</span>
										<br>
											<span translate>[Rules 7 (1) (ii),13(8)(iv)( C ) (cd), 17(vi), 19(20), 65(3)(viii), 69E(2), 69 I (4),69J(1), 69J(3)]</span>
										</div>
										<div class="grey-header marginTop"					style="border-top: none; margin-top: 4px;">
											<span translate>ESCORT ORDER</span>
										</div>
										<form class="form-horizontal basicchildform" name="caseSummary"			id="caseSummary">
											<fieldset>
												<jsp:include page="./common/childNameStrip.jsp" />
												<div class="form-group">
													<label class="col-md-4 control-label" for="textarea"></label>
													<div class="col-md-7"></div>
												</div>
												<table id="act-and-rules" class="table table-striped table-bordered"					width="100%" cellspacing="0">
													<thead>
														<tr class="headingCLR">
															<th class="borderRight"  style="font-family: inherit;">
																<span translate>FORM NAME</span>
															</th>
															<th class="resizeicon borderRight text-center" >
																<span translate>DOWNLOAD</span>
															</th>
															<th class="resizeicon borderRight text-center" ng-show="upload">
																<span translate>UPLOAD</span>
															</th>
														</tr>
													</thead>
													<tbody>
														<tr ng-show="upload">
															<td valign="middle">
																<span translate>ESCORT ORDER FORM 45</span>
															</td>
															<td valign="middle" class="resize">
																<a href="" target="_blank">
																	<i class="downloadicon fa-file-pdf-o fa-lg"									aria-hidden="true" ng-click="downloadPdf('45','template')"></i>
																</a>
															</td>
															<td valign="middle">
																<div class="form-group box-border-padding" >
																	<div class="col-md-12 text-right">
																		<input class="input-file" type="file" file-model="escortOrderModel" name="escortOrder[]" id="js-upload-files"  ng-files="getReport($files,'escortOrder')" accept=".pdf">
																			<button  class="" type="submit" class="btn btn-sm btn-primary"	ng-show="upload"											id="js-upload-submit" ng-click="uploadFiles('escortOrder')">
																				<span translate>Upload</span>
																			</button>
																		</div>
																	</div>
																</td>
															</tr>
															<tr ng-if="!upload">
																<td valign="middle">
																	<span translate>ESCORT ORDER FORM 45</span>
																</td>
																<td valign="middle" colspan="3">
																	<div class="form-group box-border-padding" >
																		<div class="col-md-12" style="margin-left: 30%;">
																			<button  class="col-md-3" type="submit" class="btn btn-sm btn-primary"												id="js-upload-submit" ng-click="downloadPdf('null','escortOrder')" style="margin-left: 245px;">
																				<span translate>Download</span>
																			</button>
																		</div>
																	</div>
																</td>
															</tr>
														</tbody>
													</table>
												</fieldset>
											</form>
										</div>
									</body>
								</html>