<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- <jsp:include page="fragments/headtag.jsp" /> --%>
<body>
	<div id="wrapper" class="exceptionWrapper1" bgcolor="white" onclick="location.href='<spring:url value="/" htmlEscape="true" />'">
		<spring:url value="/resources/images/cotton_connect_session_time_out.svg" var="sessionOutImage" />
		<div class="exceptionSVGBg">
			<img alt="" src="${sessionOutImage}" />
		</div>		
	</div>
</body>


</body>
</html>