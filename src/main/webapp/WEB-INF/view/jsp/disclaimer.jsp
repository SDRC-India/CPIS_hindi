
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
   
    <div class="col-md-12">
     <h3 class="page_title">Disclaimer</h3>
      <p> The information contained in this website is for general information purposes only. The information is provided by CPIS and while we endeavour to keep the information up to date and correct, we make no representations or warranties of any kind, express or implied, about the completeness, accuracy, reliability, suitability or availability with respect to the website or the information, products, services, or related graphics contained on the website for any purpose. Any reliance you place on such information is therefore strictly at your own risk. Users are advised to verify/ check any information, and to obtain any appropriate professional advice before acting on the information provided on the website.
In no event will we be liable for any loss or damage including without limitation, indirect or consequential loss or damage, or any loss or damage whatsoever arising from loss of data or profits arising out of, or in connection with, the use of this website.
Every effort is made to keep the website up and running smoothly. However, CPIS takes no responsibility for, and will not be liable for the website being temporarily unavailable due to technical issues beyond our control.
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
