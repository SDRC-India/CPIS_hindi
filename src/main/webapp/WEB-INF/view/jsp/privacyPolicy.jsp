
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
				<h3 class="page_title">Privacy Policy</h3>
				<p>This privacy policy sets out how CPIS uses and protects any
					information that you give while you use this website. CPIS is
					committed to ensuring that your privacy is protected. When we ask
					you to provide certain information by which you can be identified
					when using this website, then you can be assured that it will only
					be used in accordance with this privacy statement. CPIS may change
					this policy from time to time by updating this page. You should
					check this page from time to time to ensure that you are happy with
					any changes.</p>
				<h4 class="page_title">What we collect</h4>
				<p>While using our Site, we may ask you to provide us with
					certain Personal Information (information that can be used to
					contact or identify you) and Non-Personal Information.</p>
				<h4 class="page_title">What we do with the information we
					gather</h4>
				<p>Except as otherwise stated in this privacy policy, we do
					not sell, trade, rent or otherwise share for marketing purposes
					your personal information with third parties without your consent.
					In general, the Personal Information you provide to us is used to
					help us communicate with you. For example, we use Personal
					Information to contact users in response to questions, solicit
					feedback from users, provide technical support, and inform users
					about promotional offers.</p>
				<h4 class="page_title">Security</h4>
				<p>We are committed to ensuring that your information is
					secure. In order to prevent unauthorized access or disclosure, we
					have put in place suitable physical, electronic and managerial
					procedures to safeguard and secure the information we collect
					online.</p>
				<h4 class="page_title">Why we use cookies</h4>
				<p>The site may use cookies to enhance users' experience.
					Cookies help us provide you with a better website, by enabling us
					to monitor which pages you find useful and which you do not. A
					cookie in no way gives us access to your computer or any
					information about you, other than the data you choose to share with
					us. The user may choose to set their web browser to refuse Cookies
					or alert the user when the Cookies are being sent. However, this
					may prevent you from taking full advantage of the website.</p>
				<h4 class="page_title">Links to other websites</h4>
				<p>At many places in this website, you will find links to
					other websites/ portals. These links have been placed for your
					convenience. CPIS has no control over the nature, content and
					availability of those sites. The inclusion of any links does not
					necessarily imply a recommendation or endorse the views expressed
					within them.</p>
				<h4 class="page_title">Copyright Policy</h4>
				<p>This website and its content is copyright of CPIS - &copy; CPIS 2017. All rights reserved.<br>
					Any redistribution or reproduction of
					part or all of the contents in any form is prohibited other than
					the following:</p>
					<li>You may reproduce the content partially or fully, with duly & prominently acknowledging the source. </li><br>
					<p>However, the permission to reproduce any material that is copyright of any third party has to be obtained from the copyright holders concerned. The contents of this website cannot be used in any misleading or objectionable context or derogatory manner.

					</p>
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
