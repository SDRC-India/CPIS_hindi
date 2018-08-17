
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
 <jsp:include page="./common/header.jsp" />
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-app="app" ng-controller="contactUsController">

<!-- Nav Menu Section Start-->
 <jsp:include page="./common/nav.jsp" />
<!-- Nav Menu Section End -->

<!-- About Section -->
<div class="container contain-box row-slide">
  <div class="row ">
   
     <h3 class="page_title">Contact Us</h3>
     <div class="col-md-6">                                                

     	<h4>Director</h4>
     	<p>Women Welfare Department </p>	
     	<p> 8th floor, Jawahar Bhawan</p>
        <p> Ashok Marg,  Lucknow, UP</p>
        <p> Pin code- 226001 </p>
        <p><span class="fa fa-envelope"></span> Email:scpsup@gmail.com</p>
         
<!--      	<p> <span  class="fa fa-phone"></span> Phone: 0674 238 0804  </p> -->
<!--         <p>fax: +91674 238 0804 </p> -->
<!--         <p>Website:Website: sdrc.co.in</p> -->
     </div>
     <div class="col-md-6 contact-margin">
      <div id="successSend" class="sucess-style"></div>
     <h5>Feedback Form</h5>
      <form >
       <div class="form-group">
		    <label for="exampleInputName">Your Name</label>
		    <input type="text" class="form-control" id="exampleInputName" ng-change="resetErrMsg()" placeholder="Name" ng-model="contact.fromUserName">
		  </div>
		  <div id="nameError" class="error-style"></div>
		  
		  <div class="form-group">
		    <label for="exampleInputEmail">Email address</label>
		    <input type="email" class="form-control" id="exampleInputEmail" ng-change="resetErrMsg()" placeholder="Email" ng-model="contact.fromMailId">
		  </div>
		  <div id="emailError" class="error-style"></div>
		  
		  <div class="form-group">
		    <label for="exampleInputSubject">Subject </label>
		    <input type="text" class="form-control" id="exampleInputSubject" ng-change="resetErrMsg()" placeholder="Subject" ng-model="contact.subject">
		  </div>
		  <div id="subjectError" class="error-style"></div>
		  
		  	<div class="form-group">
		    <label for="exampleInputMessage">Message </label>
		    <textarea  class="form-control" id="exampleInputMessage" ng-change="resetErrMsg()" placeholder="Message" ng-model="contact.message"></textarea>
		  </div>
		  <div id="messageError" class="error-style"></div>
		  
		  <button type="submit" class="btn btn-info" ng-click="sendMail()">Send</button>
		</form>
     </div>	
  </div>
</div>
<!-- About Section End -->





<div class="container">
  <div class="row">
    <hr>
  </div>
</div>

<div class="modal fade" id="printModal" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	          <h4 class="modal-title" id="myModalLabel">
	          	<span translate>Feedback has been sent successfully.</span><br>
	          <p style="text-align:center">
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reDirect()">OK</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
<!-- Footer Section Starts-->
 <jsp:include page="./common/footer.jsp" />
<!-- Footer Section End-->
 <jsp:include page="./common/footer_scripts.jsp" />
 <script src="resources/js/jquery-min.js"></script>
 <script src="resources/js/jquery-ui.js"></script>
 <spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
	
 <script type="text/javascript" src="resources/js/ng_app.js"></script>
 
 <script type="text/javascript">
		
		$(document).ready(function() {
			$('input').blur(function() {
			    var value = $.trim( $(this).val() );
			    $(this).val( value );
			});
			$('textarea').blur(function() {
			    var value = $.trim( $(this).val() );
			    $(this).val( value );
			});
		});

	</script>
</body>
</html>
