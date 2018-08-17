
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
<div class="container contain-box row-slide" ng-controller="structureCtrl">
  <div class="row ">
   
    <div class="col-md-12" ng-repeat="content in contentData">
    <h3 class="page_title">{{contentData[0].title}}</h3>
    <div>
    <p>{{contentData[0].content}}</p>
    </div>
    <!--  <h3 class="page_title">ICPS Structure</h3>
      <div >
      <p>State Principal Secretary/Secretary from Child welfare Department acts as the spearhead of State Child Protection Society. Director/Commission of the Child welfare department acts as the administrative head of the society. Then Programme manager (Child Protection), Programme Manager (Training, IEC & advocacy), Programme Officer (CNCP), Programme Officer (CICL), Programme Officer (Training), Programme officer (IEC & advocacy) are included in SCPS to smooth flow of ICPS in the state. </p>
      
      </div> -->
   	</div>
   	 
   	 <div class="col-md-3">
   	 </div>
    <div class="col-md-6 structure-image">
   		 <img src="resources/img/cpis about.jpg" class="img img-responsive" alt="about us image">
    </div>
     <div class="col-md-3">
   	 </div>
</div>
<!-- About Section End -->



</div>

<!-- 4 column ends-->

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
