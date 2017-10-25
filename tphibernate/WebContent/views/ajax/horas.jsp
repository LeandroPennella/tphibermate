<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>







<c:forEach begin="0" end="47" varStatus="ihora">
			<div class="hora">
				Ix: ${ihora.index} >
				
				<%-- <c:set var="horaRenglon" value="${Math.round(ihora.index/2)}"/>--%>
				<fmt:formatNumber var="horaRenglon"  value="${(ihora.index) / 2}"  />
		      	hr1: ${horaRenglon} >
		      	<c:set var = "horaRenglon" value = "${fn:substringBefore(horaRenglon, '.')}" />
				hr2: ${horaRenglon} >
<%-- 				<fmt:formatNumber value="${horaRenglon}" var="horaRenglon" maxIntegerDigits="3"/>  --%>
				<c:set var="StringFechaRenglon" value=" "/>
				
				<c:set var="horaRenglon" value="${StringFechaRenglon.concat(horaRenglon)}" />
 				<c:set var="horaRenglon" value="${horaRenglon.concat(':').concat((ihora.index%2!=0)?'30':'00')}"/> 
 				
				
				<%-- <fmt:formatDate value="${horaRenglon}"  pattern="h:m" var="horaRenglon" />--%>					      			      
<%--  					 <fmt:parseDate value = "${horaRenglon}" var = "horaRenglon" pattern = "yyyy-MM-dd HH:mm" />    --%>
				
				${horaRenglon}
				</div>
		</c:forEach>
</body>
</html>