
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
<jsp:include page="./common/header.jsp" />
</head>
<body data-spy="scroll" data-offset="20" data-target="#navbar"
	id="mymain" ng-controller="homeCtrl">

	<!-- Nav Menu Section Start-->
	<jsp:include page="./common/nav.jsp" />
	<!-- Nav Menu Section End -->

	<!-- About Section -->
	<div class="container contain-box row-slide">
		<div class="row ">

			<div class="col-md-12">
				<h3 class="page_title">Sitemap</h3>
				<ul>
					<ul class="siteMapData">
						<li>Home</li>
						<li>About Us</li>
						<li>ICPS structure</li>
						<ul class="siteMapData">
							<li>structure</li>
							<li>Info Map</li>
						</ul>
						<li>Programme</li>
						<li>RTI</li>
						<li>Gallery</li>
						<ul class="siteMapData">
							<li>Photo Gallery</li>
							<li>Audio Gallery</li>
							<li>Video Gallery</li>
						</ul>

						<li>CCTS Login</li>
					</ul>
				</ul>

			</div>


		</div>
	</div>
	<!-- About Section End -->





	<div class="container">
		<div class="row">
			<hr>
		</div>
	</div>
	<!-- Footer Section Starts-->
	<jsp:include page="./common/footer.jsp" />
	<!-- Footer Section End-->
	<jsp:include page="./common/footer_scripts.jsp" />
</body>
</html>
