
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
 <jsp:include page="./common/header.jsp" />
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-controller="galleryCtrl" ng-cloak>

<!-- Nav Menu Section Start-->
 <jsp:include page="./common/nav.jsp" />
<!-- Nav Menu Section End -->

<!-- About Section -->
<div class="container contain-box row-slide">
  <div class="row ">
   
    <div class="col-md-12">
     <h3 class="page_title">{{audioTitle}}</h3>
     <h4> content to be uploaded</h4>
    
   	</div>
    
    
  
  </div>
</div>
<!-- About Section End -->





<div class="container">
  <div class="row">
    <hr>
    <br>&nbsp;<br><br>&nbsp;<br>&nbsp;<br>&nbsp;<br>
    <br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br><br>&nbsp;<br>
  </div>
</div>
<!-- Footer Section Starts-->
 <jsp:include page="./common/footer.jsp" />
<!-- Footer Section End-->
 <jsp:include page="./common/footer_scripts.jsp" />
</body>
</html>
