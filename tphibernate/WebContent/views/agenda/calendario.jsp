<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style >
.tarea{background-color: #4986E7;}
.reunion{background-color: #16A765;}
</style>
<jsp:include page="master_header.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="master_menu.jsp"></jsp:include>
<c:forEach var="evento" items="${eventos}">
<div class="${evento.getEstado()}">


<a href="<c:url value="/agenda/editarTarea.do?id=${evento.id}"/>">
<b>${evento.getHoraInicio()} -  ${evento.getHoraFin()} </b>- ${evento.getTitulo()}
</a>
</div> 
</c:forEach>
</body>
</html>