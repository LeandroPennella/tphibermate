<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style >
		html,body,.container
		{
		    height:80%;
		}
		a:link{color:black;}
		a:visited{color:black;}
		.tarea{background-color: #4986E7; border: 1px solid darkgrey;}
		.reunionAutor{background-color: #16A765; border: 1px solid darkgrey;}
		.reunionNoConfirmado{background-color: yellow; border: 1px solid darkgrey;}
		.reunionConfirmada{background-color: #16A765; border: 1px solid darkgrey;}
		.reunionCancelada{background-color: red; border: 1px solid darkgrey;}
		.dia{ border: solid 1px gray;height:100%; border: 1px solid darkgrey;}
		.hoy{ background-color: lightgray;}
	</style>
	<jsp:include page="master_header.jsp"></jsp:include>
	<title><fmt:message key='calendario.titulo'/></title>
</head>
<body>
	<jsp:include page="master_menu.jsp"></jsp:include>
	<div style="  border: 1px solid #CCC;
  background-color: #E0E0E0;
  padding: .5em; height:20px;">

	<div style="float: left;"><a href='<c:url value="/agenda/mostrarCalendario.do?semanaOffset=${semanaOffset-1}"/>'><</a></div>
	<div style="float: right;"><a href='<c:url value="/agenda/mostrarCalendario.do?semanaOffset=${semanaOffset+1}"/>'>></a></div>
	</div>
	<c:forEach var="dia" items="${semana}">
		<c:set var="diaFecha"  value="${dia.key}"></c:set>
		<c:set var="eventosDia"  value="${dia.value}"></c:set>
		
		<c:set var="claseDia" value="nada"></c:set>
		<c:if test="${sFechaHoy==diaFecha}" >
		<c:set var="claseDia" value="hoy"></c:set>
		</c:if>
			<div class="span3 dia ${claseDia}">
			${diaFecha}
			<c:forEach var="evento" items="${eventosDia}">
				<c:set var="usuarioActual" value="${usuarioLogueado}"/>
				<c:set var="estadoEvento" value="${evento.obtenerEstado(usuarioActual)}"/>
				<div class="${estadoEvento}">
				
				<c:set var="sUrl" value="${(estadoEvento=='tarea')?'Tarea':'Reunion'}"></c:set>
				<a href="<c:url value="/agenda/editar${sUrl}.do?idEvento=${evento.id}"/>">
				<b>${evento.getHoraInicio()} -  ${evento.getHoraFin()} </b>- ${evento.getTitulo()}
				</a>
				
				</div>
			 
			</c:forEach>
		</div>
    
</c:forEach>
	
	
	
	
</body>
</html>