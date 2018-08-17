<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>

<html lang="en">
<head>
<title>CPIS</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/style.css">

</head>
<body>
<div id="wrapper">
		<div class="content">
			<div class="container-fluid text-center" style="margin: 133px auto;">
    <spring:url value="resources/img/exception.png" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Oops !!! Looks like you are caught in a wrong place ...</h2>
    <h4>Please try again in sometime, while we are fixing this up.</h4>

    <p>${exception.message}</p>

	<%Logger logger = Logger.getLogger(Exception.class);
	
	RuntimeException rte = (RuntimeException)(request.getAttribute("exception"));
	StackTraceElement[] stes = rte != null ? rte.getStackTrace() : null;
	
	if(stes != null && stes.length >0){
		
		 StringWriter stringWritter = new StringWriter();
	     PrintWriter printWritter = new PrintWriter(stringWritter, true);
	     ((RuntimeException)(request.getAttribute("exception"))).printStackTrace(printWritter);
	     printWritter.flush();
	     stringWritter.flush(); 
		
	     logger.error("An exception occourred , Stack Trace :" + stringWritter.toString());
	     
// 		logger.error(((RuntimeException)(request.getAttribute("exception"))).printStackTrace(logger));
// 		for(StackTraceElement ste : stes)
// 		logger.error(ste); 
	}%>
    <!-- Exception: ${exception.message}.
		  	<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
				${stackTrace} 
			</c:forEach>
	  	-->
	  	<div><h3><a href="home">Click here</a> to redirect to homepage.</h3></div>
</div>


</div></div>
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapjs" />
<script src="${bootstrapjs}"></script>
</body>

</html>