
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html lang="en" ng-app="upcpisApp">
<head>

<jsp:include page="./common/header.jsp" />
</head>
<body data-spy="scroll" data-offset="20" data-target="#navbar"	id="mymain" ng-controller="homeCtrl" ng-cloak>

	<!-- Nav Menu Section Start-->
	<jsp:include page="./common/nav.jsp" />
	<!-- Nav Menu Section End -->
	<!-- Hero Area Section -->
	<section id="hero-area">
		<div class="container-fluid">
			<div class="row-slide">
				<div class="col-md-12">
					<div class="container-fluid">
						<div class="col-md-12">
							<div id="myCarousel" class="carousel slide" data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators">
									<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
									<li data-target="#myCarousel" data-slide-to="1"></li>
									<li data-target="#myCarousel" data-slide-to="2"></li>
								</ol>
								<!-- Wrapper for slides -->
								<div class="carousel-inner" role="listbox">
									<div class="item active">
										<img src="resources/img/home_slider/banner_1.jpg"
											alt="CPIS UP">
									</div>
									<div class="item">
										<img src="resources/img/home_slider/banner_2.jpg"
											alt="CPIS UP">
									</div>
									<div class="item">
										<img src="resources/img/home_slider/banner_3.jpg"
											alt="CPIS UP">
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	

	<div class="container-fluid"  ng-controller="quickStatsController">
		<div class="col-md-12 quick-start">
		 <div class="col-md-5"></div>
  <div class="col-md-2">
		<h1 > {{quickStatsTitle}} </h1>
    </div>
   <div class="col-md-5"></div>
	
		<div class="col-md-12" style="margin-top: 20px;">
<!-- 			<div class="col-md-7 col-sm-7 col-xs-12 cctsloginclass" > -->
<!-- 				<img class="cctsimage" src="resources/img/cpis_home_SVG_ccts_logo.svg">&nbsp;  -->
<!-- 				</div > -->
<!-- 				<div class="col-md-5 col-sm-5 col-xs-12 cctsloginbutton "> -->
<!-- 				<a href="ccts_login" class="btn btn-info loginbutton">Login</a> -->
<!-- 			</div> -->
				<div class="sm-padding col-lg-3 col-md-3 col-sm-6 col-xs-12 " ng-repeat="score in overAllScore"> 
				<img src="{{score.imgSrc}}" alt="Quick stats" class="img-responsive home-image-circle">
					<div class="quick-strt-div">
					<span class="home-image-font">{{score.newValue}}</span><br>
					<span > {{score.indicatorName}}</span>	
					</div>	
    		</div>
    		
		</div>
	</div>
	</div>
	<div class="container-fluid carousel">
		<div class="col-md-12">
		<div class="col-md-12">

				
				<div class="col-lg-4 col-md-4 col-sm-4 four-col-box paddingachievement"
				ng-controller="homeCtrl">
				<div class="ca-hover " ng-repeat="achievment in achievements"
					ng-cloak>
					<div class="carousel-img">
						<img src="resources/img/pix-2.jpg" alt="Achievment"
							class="img-responsive">
					</div>
					<div class="carousel-content">
						<h3 class="bordernoproject">{{achievment.title}}</h3>
						<p id="achive1">{{achievment.content | limitTo: 231}}</p>
<!-- 						<br> <a href="#" class="btn btn-default">Read more &rarr;</a> -->
						</p>
					</div>
				</div>
			</div>
			
			<div class="col-lg-4 col-md-4 col-sm-4 four-col-box"
				ng-controller="homeCtrl" ng-cloak>
				<div class="ca-hover " ng-repeat="project in projects">
					<div class="carousel-img">
						<img src="resources/img/pix-3.jpg" alt="Projects"
							class="img-responsive">
					</div>
					<div class="carousel-content">
						<h3 class="bordernoproject">{{project.title}}</h3>
							<p id="achive2">{{project.content | limitTo: 250}}</p>
							<!-- 						<br> <a href="#" class="btn btn-default">Read more &rarr;</a> -->
						</p>
					</div>
				</div>
			</div>
			
			
			<div class="col-lg-4 col-md-4 col-sm-4 four-col-box"
				ng-controller="homeCtrl" ng-cloak>
				<div class="ca-hover " >
<!-- 					<div class="carousel-img"> -->
<!-- 						<img src="resources/img/notice-board.jpg" alt="Projects" -->
<!-- 							class="img-responsive"> -->
<!-- 					</div> -->
					<div class="carousel-content" >
					<h3 class="bordernoproject">{{title}}</h3>
						<p class="carousel-content">
							<ul><marquee direction="up" scrollamount="3" Height=280px onmouseover="this.stop();" onmouseout="this.start();">
							<li ng-repeat="notice in noticeData">{{notice.content | limitTo: 250}}</li>
							</marquee></ul>
						<!-- <h3 class="bordernoticeboard">Notice Board</h3>
						<p class="carousel-content">
							<ul><marquee direction="up" scrollamount="3" Height=280px onmouseover="this.stop();" onmouseout="this.start();">
								<li>On 21st September 2016, the JJ Act 2015 (Rule-2, 2016) has been published.</li><br>
								<li>CHILD LINE is India's first 24 hour free emergency telephonic help line which provides outreach service to all the children living in difficult circumstances.</li><br>
								<li>On 21st September 2016, the JJ Act 2015 (Rule-2, 2016) has been published.</li><br>
								<li>CHILD LINE is India's first 24 hour free emergency telephonic help line which provides outreach service to all the children living in difficult circumstances.</li><br>
								<li>On 21st September 2016, the JJ Act 2015 (Rule-2, 2016) has been published.</li><br>
								<li>CHILD LINE is India's first 24 hour free emergency telephonic help line which provides outreach service to all the children living in difficult circumstances.</li><br>
								<li>On 21st September 2016, the JJ Act 2015 (Rule-2, 2016) has been published.</li><br>
								<li>CHILD LINE is India's first 24 hour free emergency telephonic help line which provides outreach service to all the children living in difficult circumstances.</li><br>
								<li>On 21st September 2016, the JJ Act 2015 (Rule-2, 2016) has been published.</li><br>
								<li>CHILD LINE is India's first 24 hour free emergency telephonic help line which provides outreach service to all the children living in difficult circumstances.</li><br>
								<li>On 21st September 2016, the JJ Act 2015 (Rule-2, 2016) has been published.</li><br>
								<li>CHILD LINE is India's first 24 hour free emergency telephonic help line which provides outreach service to all the children living in difficult circumstances.</li><br>
							</marquee></ul>
							
						</p> -->
<!-- 						<a href="#" class="btn btn-default">Read more &rarr;</a> -->
						</p>
					</div>
				</div>
			</div>
			</div>
			
			

			<!--     This is temporary code for notice board have to change later -->
			

			<!--     Temporary ends here -->


			<!-- Notice Board Starts-->
			<%--     <jsp:include page="./common/notice.jsp" /> --%>
			<!-- Notice Board Ends-->

		</div>


	</div>
	<!-- 4 column ends-->
<!-- 	<div class="container"> -->
<!--   <div class="row"> -->
<!--     <hr> -->
<!--   </div> -->
<!-- </div> -->
<div class="container-fluid" ng-controller="homeCtrl" ng-cloak>
  <div class="col-md-12">
    <div class="col-lg-4 col-md-4 col-sm-6" >
      <div class="three-col gallery-col" style="padding-top: 0px; padding-margin: 0px;">
        <div class="three-col-content gallery-content">
          <h3>{{mediaTitle}}</h3>
          <li ng-repeat="media in mediaGalleries"><a href="{{media.href}}"><img src="{{media.imagePath}}" style="width:20px">&nbsp;  {{media.content}}</a></li>
          <!-- <li><a href="audiogallery"><img src="resources/img/cpis_home_SVG_Audio_Icon.svg" style="width:20px">&nbsp;  Audio Gallery</a></li>

		   <li><a href="videogallery"><img src="resources/img/cpis_home_SVG_Video_Icon.svg" style="width:20px">&nbsp;  Video Gallery</a></li>

		   <li><a href="/CPIS/gallery"><img src="resources/img/cpis_home_SVG_Photo_Icon.svg" style="width:20px">&nbsp;  Photo Gallery</a></li> -->
        </div>
      </div>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-6">
      <div class="three-col gallery-col">
        <div class="three-col-content ">
          <h3>{{keyContactTitle}}</h3>
          <ul>
          <li ng-repeat="contact in keyContacts"><a href="#">{{contact.content}}</a></li>
<!--           <li><a href="#">DCPU</a></li> -->

<!-- 		  <li><a href="#">CWC</a></li> -->

<!-- 		  <li><a href="#">JJB</a></li> -->

<!-- 		  <li><a href="#">SJPU</a></li> -->

<!-- 		  <li><a href="#">CCIs</a></li> -->

<!-- 		  <li><a href="#">Registered Institutions</a></li> -->

          </ul>
        </div>
      </div>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-6" ng-controller="homeCtrl">
      <div class="three-col gallery-col" >
        <div class="three-col-content " ng-repeat="st in icpsStructures" ng-cloak>
          <h3>{{st.title}}</h3>
          <p id="achive3">{{st.content | limitTo: 222}}</p><br>
            <a href="structure" class="btn btn-default">Read more &rarr;</a></p>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- <div class="container footerlogo" >
  
    <div class="col-md-12 footer-padding" >
  <h4 class="textcenterlink"> Useful Links</h4>
  
			<hr>
			
      <div class="sm-padding col-lg-3 col-md-3 col-sm-6 col-xs-6 useful-logo"> 
      <a class="borderlogo" href="#" ><img src="resources/img/logo.png" alt="Up"  height="70px" /></a>
      </div>
       <div class="sm-padding col-lg-3 col-md-3 col-sm-6 col-xs-6 useful-logo"> 
       <a class="borderlogo" href="#"><img src="resources/img/logo.png" alt="Up"  height="70px" /></a> 
       </div>
        <div class="sm-padding col-lg-3 col-md-3 col-sm-6 col-xs-6 useful-logo"> 
       <a class="borderlogo" href="#"><img src="resources/img/logo.png" alt="Up"  height="70px" /></a>
       </div>
        <div class="sm-padding col-lg-3 col-md-3 col-sm-6 col-xs-6 useful-logo"> 
       <a href="#" ><img src="resources/img/logo.png" alt="Up"  height="70px" /></a> 
    </div>

    <div class="col-md-4"> </div>
   
   </div>
  </div> -->
	<!-- Footer Section Starts-->
	<jsp:include page="./common/footer.jsp" />
	<!-- Footer Section End-->
	<jsp:include page="./common/footer_scripts.jsp" />
	<script src="resources/js/ng_app.js"></script>
	
</body>
</html>
