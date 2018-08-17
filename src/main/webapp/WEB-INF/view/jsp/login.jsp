
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
 <jsp:include page="./common/header.jsp" />
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-controller="homeCtrl" >
	

<div class="container">
<div class="login">
	<h3 class="text-center">Login Here</h3>
	
	<div  class="col-md-4"></div>
	<div  class="col-md-4 login_form">
			<serror:Error id="msgBoxlogin"  errorList="${formError}"
								cssInfClass="${className}">
								${formError}
							</serror:Error>
		
		<form action="/CPIS/validateLogin" method="post">
       	<div class="form-group">
		    <label for="">User Name</label>
		    <input type="text" class="form-control" name="username" id="exampleInputEmail1" required placeholder="Email">
		  </div>
		  <div class="form-group">
		    <label >Password</label>
		    <input type="password" class="form-control" name="password" id="exampleInputEmail1" required placeholder="Password">
		  </div>
		  
		  <button type="submit" class="btn btn-info">Submit</button>
		</form>
		</div>
<div  class="col-md-4"></div>
</div>
</div>
 <jsp:include page="./common/footer_scripts.jsp" />
</body>
</html>
