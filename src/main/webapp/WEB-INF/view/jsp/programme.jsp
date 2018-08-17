
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
 <jsp:include page="./common/header.jsp" />
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-controller="homeCtrl" ng-cloak>

<!-- Nav Menu Section Start-->
 <jsp:include page="./common/nav.jsp" />
<!-- Nav Menu Section End -->

<!-- About Section -->
<div class="container contain-box row-slide" ng-app="upcpisApp" ng-controller="programmeCtrl">
  <div class="row ">
   
    <div class="col-md-12" >
     <!-- <h3 class="page_title">Programme</h3>
     <p>In order to safe guard and for betterment of all children living in difficult circumstances, the Integrated Child Protection Scheme has introduced the following programmes: 
 
     </p>
     <ul>
	     <li>Open Shelter & CHILD LINE programmes for Children in need of care & protection in urban and semi-urban areas.</li>
	     <li>Offers family based solutions through foster care, Kin ship care, improving sponsorship and adoption programmes.</li>
	     <li>Offers after care services, improved quality institutional services and general grant-in-aid for need based innovative interventions.</li>
	     <li>Within statutory support services the scheme calls for the strengthening of CWCs, JJBs, SJPUs, as well as seeing to the setup of these services in each district. </li>
	     <li>In case of Children conflicted with law, Observation Homes & Special Homes are set up to take care of the children with regular counselling in order to bring them back to the main stream.</li>
					<li>Beyond this, ICPS also outlines the need for human
						resource development for strengthening counselling services,
						training and capacity building, knowledge-base, conduct research
						studies, create and manage a child tracking system, carry out
						advocacy and public education programmes, and monitoring and
						evaluation of the scheme.</li>
				</ul> -->
				
	<h3 style="color : #f06626">{{title}}</h3>
<!-- 	{{programmes}} -->
    <p id="programmes"></p>
   	</div>
    
    
  
  </div>
</div>
<!-- About Section End -->





<div class="container">
  <div class="row">
    <hr>
    <br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>
  </div>
</div>
<!-- Footer Section Starts-->
 <jsp:include page="./common/footer.jsp" />
<!-- Footer Section End-->
 <jsp:include page="./common/footer_scripts.jsp" />
 <script src="resources/js/ng_app.js"></script>
</body>

</html>
