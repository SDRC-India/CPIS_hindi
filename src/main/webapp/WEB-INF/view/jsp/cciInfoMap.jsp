<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">
<head>
	<jsp:include page="./common/header.jsp" />
	<link rel="stylesheet" href="resources/css/style.css">
	<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
	<script src="${jQuery}"></script>
	<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css" var="jQueryUiCss" />
	<link href="${jQueryUiCss}" rel="stylesheet"></link>
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
	<script src="resources/js/d3.v4.min.js"></script>
	<script src="https://d3js.org/d3.v3.min.js" charset="utf-8"></script>
	<script src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"></script>
</head>

<body ng-app="dashboardApp" ng-controller="DashboardController" ng-cloak>
	<jsp:include page="./common/nav.jsp" />
	<div class="container row-slide containerWidth">

		<div class="panel dasboardPanel panelColor col-md-3">
			<div class="panel-body" style="padding: 10px;">
				<label class="mrgntop9">{{ff}}</label>
			</div>
		</div>

		<div class="panel dasboardPanel searchBarColor">
			<div class="panel-body" style="margin-top: -10px; ">
<!-- 				<input type="text" class="mobViewRespnsv" -->
<!-- 					placeholder="I'm looking for" -->
<!-- 					style="border: 1px solid #ccc; padding-left: 22px; margin-top: 9px; margin-left: 7px; background-color: #ccc;"> -->
<!-- 				<div class="buttonDiv" > -->
<!-- 					<button type="button" -->
<!-- 						class="btn btn-default btn-sm btnStyle btndownld" ng-disabled="true">Download PDF</button> -->
<!-- 				</div> -->
			</div>
		</div>

		<div class="row" style="margin-top: 5px;">
			<div class="col-sm-12" style="padding: 0 25px;">
			<select class="map-lebel cciStyle" ng-model="cciTypes.typeName" ng-change="changeCCI(cciTypes)">
			    <option value="" disabled selected>Select CCI</option>
			    <option ng-repeat="cciTypes in cciType">{{cciTypes.typeName}}</option>
			</select>
			
<!-- 				<table class="map-lebel"> -->
<!-- 					<tr> -->
<!-- 						<td ng-repeat="cciTypes in cciType"> -->
<!-- 							<div> -->
<!-- 								<button ng-click="changeCCI(cciTypes)" -->
<!-- 									ng-class="{active:cciTypes.typeName == selected}" ng-cloak> -->
<!-- 									{{cciTypes.typeName}}</button> -->
<!-- 							</div> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
				<google-map ng-show=true center="center" zoom="zoom"
					draggable="true"> <polygon static="true"
					ng-model="polygons" path="polygons.path" stroke="polygons.stroke"
					visible="polygons.visible" geodesic="polygons.geodesic"
					fill="polygons.fill" fit="false" editable="polygons.editable"
					draggable="polygons.draggable"> </polygon> <markers class="pushpin"
					models="map.markers" coords="'self'" events="map.events">
				<windows show="'showWindow'" closeClick="'closeClick'" ng-cloak
					options='pixelOffset'>
				<p ng-non-bindable style="width: 200px; font-family: Josefin Sans"><b>Name:</b> {{title}}<br><b>Address:</b> {{address}}</p>
				</windows> </markers> </google-map>
			</div>
		</div>
		<br>
		<div id="scrollHere">
			<div class="panel dasboardPanel" ng-show="showData">
				<div class="panel-body">
					<div class="grey-header" ng-if="showName">{{selectedCCI.name}}</div>
				</div>
			</div>

			<div style="padding-left: 5%;" ng-show="showInfrastructure">
				<button id="tab1" type="button" class="btn buttonCCIInfo tab active">Capacity</button>
				<button id="tab2" type="button" class="btn buttonCCIInfo tab">Infrastructure</button>
				<button id="tab3" type="button" ng-disabled="false"	class="btn buttonCCIInfo tab">Human Resource</button>
			</div>

			<div id="tab1show" class="tab-content col-md-12 box" ng-show="showInfrastructure">
				<div class="row">
					<div class="btn-group displayCci" ng-repeat="cciInfo in cciCapacityInfo">
						<div class="col-md-8 col-sm-12 displayInline">
							<label class="labeldesign"
								ng-disabled="false"
								
								ng-cloak> {{cciInfo.name}}
								</label>
								
								<label class="valuedesign" ng-disabled="false"
								ng-cloak> {{cciInfo.value}}
								</label>
						</div>

					</div>
				</div>
				<div class="row" style="margin-top: 33px;">
					<div class="col-md-2 col-sm-2" style="border-right: 1px solid #555;">
						<label style="font-size: 15px;">View category-wise
							statistics</label><br>
					<!-- 	<div class="hoverNone" ng-disabled="true"
							style="background-color: grey;">
							<label class="btn" style="text-transform: none;"> Age</label>
						</div> -->
						<div class="mobviewCatgry active" ng-click="changeGraph('Sex')">
							<label class="btn mobviewCatgry"
								style="display: block; text-align: left !important;">
								Sex</label>
						</div>
						<div class="mobviewCatgry" ng-click="changeGraph('Caste')">
							<label class="btn mobviewCatgry"
								style="display: block; text-align: left !important;">
								Caste</label>
						</div>
						
					<!-- 	<div class="mobviewCatgry" ng-click="changeGraph('Age')">
							<label class="btn mobviewCatgry"
								style="display: block; text-align: left !important;">
								Age</label>
						</div> -->
					</div>
					<div class="barChart col-md-6 col-sm-6" ng-show="showCasteGraph"></div>
					<div class="sexBarChart col-md-6 col-sm-6" ng-show="showSexGraph"></div>
					<div class="col-md-4">
<!-- 					<img src="resources/img/pix-2.jpg" class="img img-responsive" alt="CCI image"> -->
<!-- 					Address:{{selectedCCI.address}}<br> -->
<!-- 					Line one<br> -->
<!-- 					Line two					 -->
					</div>
				</div>
			</div>
			<!-- the following style is for temporary use only. -->
			<style>
.hoverNone:hover label {
	background-color: grey;
}
/* .col-sm-2:active label{ */
/* 	background-color: black; */
/* } */
</style>
			<!-- div for  infrastructure -->
			<div id="tab2show" class="tab-content col-md-12 box" style="padding-left: 4%;" ng-show="showInfrastructure">
<!-- 				<p id="inf-title">View Infrastructure Details</p> -->
				<div style="color:#e00;margin: 50px 20px;font-size: 20px;" ng-show="infrastructureFlag">
					No data available
				</div>
				<div ng-show="!infrastructureFlag">
					<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">Building (Rented/Owned)</div>
							<div class="infranumbottom">{{selectedCCI.building_Type.name}}</div>
						</div>
	
						<div class="col-md-6">
							<div class="infraBorderbottom">Status of building (Pakka/Kacha)</div>
							<div class="infranumbottom">{{selectedCCI.status_of_Building.name}}</div>
						</div>
					</div>
					<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">Area of the building (in Sq.ft.)</div>
							<div class="infranumbottom">{{selectedCCI.area_of_Building}}</div>
						</div>
	
						<div class="col-md-6">
							<div class="infraBorderbottom">If building protected by boundary wall?</div>
							<div class="infranumbottom">{{selectedCCI.boundary_wall_value}}</div>
						</div>
					</div>
					
									<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">Total number of rooms available</div>
							<div class="infranumbottom">{{selectedCCI.total_rooms_value}}</div>
						</div>
	
						<div class="col-md-6">
							<div class="infraBorderbottom">Number of rooms for boys</div>
							<div class="infranumbottom">{{selectedCCI.no_of_room_boys_value}}</div>
						</div>
					</div>
					
					<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">Number of rooms for girls</div>
							<div class="infranumbottom">{{selectedCCI.no_of_room_girls_value}}</div>
						</div>
	
						<div class="col-md-6">
							<div class="infraBorderbottom">Total number of toilets available</div>
							<div class="infranumbottom">{{selectedCCI.no_of_toilets_value}}</div>
						</div>
					</div>
					
					<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">Number of toilets for boys</div>
							<div class="infranumbottom">{{selectedCCI.no_of_toilet_boys_value}}</div>
						</div>
	
						<div class="col-md-6">
							<div class="infraBorderbottom">Number of toilets for girls</div>
							<div class="infranumbottom">{{selectedCCI.no_of_toilet_girls_value}}</div>
						</div>
					</div>
					
					<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">If separate toilets for staff?</div>
							<div class="infranumbottom">{{selectedCCI.separate_Toiletsfor_Staff}}</div>
						</div>
	
						<div class="col-md-6">
							<div class="infraBorderbottom">Numbers of contact points</div>
							<div class="infranumbottom">{{selectedCCI.number_of_ContactPoints}}</div>
						</div>
					</div>
	
					<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">If electricity available in all rooms?</div>
							<div class="infranumbottom">{{selectedCCI.proper_elec_value}}</div>
						</div>
	
						<div class="col-md-6">
							<div class="infraBorderbottom">If power backup facility for all the rooms?</div>
							<div class="infranumbottom">{{selectedCCI.power_Backupfacility_AllRooms}}</div>
						</div>
					</div>
					
					<div class="col-md-12 infra_list">
						<div class="col-md-6">
							<div class="infraBorderbottom">If drinking water available?</div>
							<div class="infranumbottom">{{selectedCCI.drinking_water_facility_value}}</div>
						</div>
	
					</div>
				</div>

			</div>
			<!--div for  infrastructure ends-->
			
			
			<!-- Div for HR starts -->
<!-- 			<div ng-if="{{hrInfo}}==null" id="noHrData"><p>No data available.</p></div> -->
			<div id="tab3show" class="tab-content col-md-12 box" style="padding-left: 4%;" >
<!-- 				<p id="inf-title">View Human Resource Details</p> -->
				<div style="color:#e00;margin: 50px 20px;font-size: 20px;" ng-show="!hrInfoKeys.length">
					No data available
				</div>

			<!-- Div for CCI HR starts -->
			<div id="cciDiv">
				<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Officer Incharge/Superintendent</div>
						<div class="infranumbottom">{{hrInfo.officerIncharge_Superintendent}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Counselor</div>
						<div class="infranumbottom">{{hrInfo.counselor}}</div>
					</div>
				</div>
				<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Probation officer/Case Worker/Child Welfare Officer</div>
						<div class="infranumbottom">{{hrInfo.po_so_cwo}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">House Mother/Father</div>
						<div class="infranumbottom">{{hrInfo.houseMother_Father}}</div>
					</div>
				</div>
			<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Para medical staff</div>
						<div class="infranumbottom">{{hrInfo.paraMedicalStaff}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Store Keeper cum Accountant</div>
						<div class="infranumbottom">{{hrInfo.storeKeeperCumAccountan}}</div>
					</div>
				</div>
				
			<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Cook</div>
						<div class="infranumbottom">{{hrInfo.cook}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Asst. Cook</div>
						<div class="infranumbottom">{{hrInfo.asstCook}}</div>
					</div>
				</div>
				
			<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">House Keeper</div>
						<div class="infranumbottom">{{hrInfo.houseKeeper}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Educator(Volunter/part time)</div>
						<div class="infranumbottom">{{hrInfo.educator}}</div>
					</div>
				</div>
				
			<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">MBBS Doctor (Volunteer/ Part time)</div>
						<div class="infranumbottom">{{hrInfo.mbbsDoctor}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Art & Craft cum Music teacher (Volunteer/ Part time)</div>
						<div class="infranumbottom">{{hrInfo.artCraftMusicTeacher}}</div>
					</div>
				</div>
				
			<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">PT Instructor cum Yoga Teacher (Volunteer/ Part time)</div>
						<div class="infranumbottom">{{hrInfo.ptInstructorYogaTeacher}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Special Educator/ Therapist</div>
						<div class="infranumbottom">{{hrInfo.special_Educator_Therapist}}</div>
					</div>
				</div>
				
			<div class="col-md-12 infra_list">

					<div class="col-md-6">
						<div class="infraBorderbottom">Female Nurse</div>
						<div class="infranumbottom">{{hrInfo.female_Nurse}}</div>
					</div>
					
						<div class="col-md-6">
						<div class="infraBorderbottom">Care Taker cum Vocational Instructor</div>
						<div class="infranumbottom">{{hrInfo.careTaker_VocationalInstructor}}</div>
					</div>
					<div class="col-md-6">
						<div class="infraBorderbottom">Others</div>
						<div class="infranumbottom">{{hrInfo.others}}</div>
					</div>
				</div>
				
				</div>
			<!-- Div for CCI HR ends -->
			
			<!-- Div for SAA HR starts -->
			
			<div id="saaDiv">
				<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Project Coordinator</div>
						<div class="infranumbottom">{{hrInfo.project_Coordinator}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Programme Manager</div>
						<div class="infranumbottom">{{hrInfo.programme_Manager}}</div>
					</div>
				</div>
				<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Social worker cum early child hood educator</div>
						<div class="infranumbottom">{{hrInfo.social_worker_Cum_Early_ChildHood_Educator}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Part time Doctor (Child Specialist)</div>
						<div class="infranumbottom">{{hrInfo.partTime_Doctor_Child_Specialist}}</div>
					</div>
				</div>
			<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Nurse</div>
						<div class="infranumbottom">{{hrInfo.nurse}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Ayah</div>
						<div class="infranumbottom">{{hrInfo.ayah}}</div>
					</div>
				</div>
			<div class="col-md-12 infra_list">
					
					<div class="col-md-6">
						<div class="infraBorderbottom">Chowkidar</div>
						<div class="infranumbottom">{{hrInfo.chowkidar}}</div>
					</div>
					<div class="col-md-6">
						<div class="infraBorderbottom">Others</div>
						<div class="infranumbottom">{{hrInfo.others}}</div>
					</div>
				</div>
				</div>
				<!-- Div for SAA HR ends -->
				
				<!-- Div for OS HR starts -->
			
			<div id="osDiv">
				<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Project Coordinator cum Counselor</div>
						<div class="infranumbottom">{{hrInfo.projectCoordinator_cum_Counselor}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Social Worker</div>
						<div class="infranumbottom">{{hrInfo.social_Worker}}</div>
					</div>
				</div>
				<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Care giver cum bridge course educator</div>
						<div class="infranumbottom">{{hrInfo.careGiver_Cum_BridgeCourse_Educator}}</div>
					</div>

					<div class="col-md-6">
						<div class="infraBorderbottom">Out reach Worker</div>
						<div class="infranumbottom">{{hrInfo.outReach_Worker}}</div>
					</div>
				</div>
			<div class="col-md-12 infra_list">
					<div class="col-md-6">
						<div class="infraBorderbottom">Helper for Cleaning & Cooking</div>
						<div class="infranumbottom">{{hrInfo.helper_For_Cleaning_Cooking}}</div>
					</div>
					<div class="col-md-6">
						<div class="infraBorderbottom">Others</div>
						<div class="infranumbottom">{{hrInfo.others}}</div>
					</div>
				</div>

				</div>
				<!-- Div for OS HR ends -->
				
		</div>
		<!-- Div for HR ends -->
	</div>
	</div>
	
	
	
	<!-- @author: Pratyush Kumar Rath The following script is for switching between tabs. -->
	<script type="text/javascript">
		$("#cciDiv").hide();
		$("#saaDiv").hide();
		$("#osDiv").hide();
	</script>
	<script>
		var $contents = $('.tab-content');
		$contents.slice(1).hide();
		$('.tab').click(function() {
			var $target = $('#' + this.id + 'show').show();
			$contents.not($target).hide();
		});
	</script>
	<script>
		$(document).ready(function() {
			$(".first-btn").addClass("active");
			$(".map-lebel button").click(function() {
				$(".map-lebel button.active").removeClass("active");
				$(this).addClass("active");
			});
			$(".mobviewCatgry").click(function(){
				$(".mobviewCatgry.active").removeClass("active");
				$(this).addClass("active");
			});
		});
	</script>
	<script	src='//maps.googleapis.com/maps/api/js?key=AIzaSyCLyYjTLM4UUAnzm7CU7NIqOYi08LzoAFA&v=3.31'></script>
	<spring:url value="/webjars/angularjs/1.5.5/angular.min.js"	var="angularmin" />
	<script src="${angularmin}" type="text/javascript"></script>

	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js" var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>

	<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js" var="bootstrapjs" />
	<script src="${bootstrapjs}"></script>

	<script src="resources/js/html2canvas.js"></script>

	<script src="resources/js/lodash.min.js" type="text/javascript"></script>
	<script src='resources/js/angular-google-maps.min.js'></script>
	<script src="resources/js/angular-simple-logger.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.1/angular-route.js"></script>
	<script src="resources/js/AngularController/dashboardController.js"></script>

	<!-- Footer Section Starts-->
 
<!-- Footer Section End-->
 <jsp:include page="./common/footer_scripts.jsp" />
</body>
<jsp:include page="./common/footer.jsp" />
</html>
