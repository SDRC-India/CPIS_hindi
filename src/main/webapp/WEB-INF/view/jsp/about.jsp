
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
<div class="container contain-box row-slide" ng-controller="aboutUsCtrl">
  <div class="row">
   <div class="col-md-8">
     <h3 class="page_title" >{{title}}</h3>
<!--      {{objectiveData}} -->
     <p>{{aboutContent}}</p>
     </div>
    <div class="col-md-4">
    <img src="resources/img/pix-4.jpg" class="img img-responsive" alt="about us" style="margin-top:30px;">
    </div>
    
  
  </div>
</div>
<!-- About Section End -->



</div>

<!-- 4 column ends-->

<div class="container">
  <div class="row">
    <hr>
    <br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;
    
  </div>
</div>
<!-- Footer Section Starts-->
 <jsp:include page="./common/footer.jsp" />
<!-- Footer Section End-->
 <jsp:include page="./common/footer_scripts.jsp" />
</body>

</html>
