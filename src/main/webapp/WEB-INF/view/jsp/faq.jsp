
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
 <jsp:include page="./common/header.jsp" />
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-controller="homeCtrl">

<!-- Nav Menu Section Start-->
 <jsp:include page="./common/nav.jsp" />
<!-- Nav Menu Section End -->

<!-- About Section -->
<div class="container contain-box row-slide">
  <div class="row ">
   
    <div class="col-md-12 contact-margin">
     <h3 class="page_title">Frequently Asked Questions</h3>
     
     <div class="text-format1">
     <h4> How can I view PDF files?</h4>
      <p>This website includes some content that is available in non-HTML format. They might not be visible properly if your browser does not have the required plug-ins.For example, Acrobat Reader software is required to view Adobe Acrobat PDF files. If you do not have this software installed on your computer, you can download it for free.</p>
   	  
   	   </div>
   	   	
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
