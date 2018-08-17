
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
 <jsp:include page="./common/header.jsp" />
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-controller="rtiCtrl">

<!-- Nav Menu Section Start-->
 <jsp:include page="./common/nav.jsp" />
<!-- Nav Menu Section End -->

<!-- About Section -->
<div class="container contain-box row-slide">
  <div class="row ">
   
    <div class="col-md-8">
     <h3 class="page_title">{{rtiTitle}}</h3>
     
     <table class="table tableRti table-bordered ">
		<tbody><tr>
			<th ng-repeat="heading in rtiContent">{{heading.content}}</th>
<!-- 			<th>Name/Designation/e-mail id/Address &amp; Tele Fax</th> -->
<!-- 			<th>State/District</th> -->
			</tr>
	    		<tr class="odd">

			<td width="8%" class="views-field-counter first">1</td>
			<td width="62%">DCPU<br>	
			
				<ul class="list_items">
				<li> <i class="fa fa-angle-right"></i> DCPU </li>	
				<li> <i class="fa fa-angle-right"></i> Email Id :- dcpu@gmail.com</li>				
				<li> <i class="fa fa-angle-right"></i> Tel. No. :- 011-12345671</li>				
				<li> <i class="fa fa-angle-right"></i> Fax No.  :- 011-12345674</li>		
			</ul>
			</td>
			<td width="30%"><p>Agra</p>
		<br></td>
		</tr> 	
	
			
    	</tbody>
    	
    	
      </table>
    
  
  </div>
</div>
<!-- About Section End -->
 </div>




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
</body>

</html>
