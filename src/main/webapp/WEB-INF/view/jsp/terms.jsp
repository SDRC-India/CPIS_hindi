
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
				<h3 class="page_title">Terms & Condition</h3>

				<p>Welcome to our website. If you continue to browse and use
					this website, you are agreeing to comply with and be bound by the
					following terms and conditions of use:
				<li>The content of the pages of this website is for your
					general information and use only. It is subject to change without
					notice.</li>

				<li>This website does not provide any warranty or guarantee as
					to the accuracy, timeliness, performance, completeness or
					suitability of the information and materials found or offered on
					this website for any particular purpose. You acknowledge that such
					information and materials may contain inaccuracies or errors and we
					expressly exclude liability for any such inaccuracies or errors to
					the fullest extent permitted by law.</li>

				<li>Your use of any information or materials on this website is
					entirely at your own risk, for which we shall not be liable.</li>

				<li>This website contains material which is owned by or
					licensed to us. This material includes, but is not limited to, the
					design, layout, look, appearance and graphics. Reproduction is
					prohibited other than in accordance with the copyright notice,
					which forms part of these terms and conditions.</li>

				<li>All trademarks reproduced in this website, which are not
					the property of, or licensed to the operator, are acknowledged on
					the website.</li>

				<li>Unauthorized use of this website may give rise to a claim
					for damages and/or be a criminal offence.</li>

				<li>From time to time, this website may also include links to
					other websites. These links are provided for your convenience to
					provide further information. They do not signify that we endorse
					the website(s). We have no responsibility for the content of the
					linked website(s).</li>

				</p>

			</div>
		</div>
	</div>


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
