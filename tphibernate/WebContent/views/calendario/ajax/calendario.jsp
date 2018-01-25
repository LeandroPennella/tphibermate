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
		.dia{width:14%; float: left; border: solid 1px gray;height:100%; border: 1px solid darkgrey;}
		.hoy{ background-color: lightgray;}
		.headerDia{background-color: silver; text-align: center; font-weight: bold;}
	</style>
	<jsp:include page="master_header.jsp"></jsp:include>
	<title><fmt:message key='calendario.titulo'/></title>
</head>
<body>
	<jsp:include page="master_menu.jsp"></jsp:include>


	<div style="  border: 1px solid #CCC;
		  background-color: #E0E0E0;
		  padding: .5em; height:20px;">

		<div style="float: left;"><a href='<c:url value="/calendario/mostrarCalendario.do?corrimientoSemana=${corrimientoSemana-1}"/>'><</a></div>
		<div style="float: right;"><a href='<c:url value="/calendario/mostrarCalendario.do?corrimientoSemana=${corrimientoSemana+1}"/>'>></a></div>
	</div>
	
<jsp:useBean id="now" class="java.util.Date" scope="page" />

	<c:forEach var="dia" items="${semana}">
		<%-- <c:set var="diaFecha"  value="${dia.key}"></c:set>--%>
		<fmt:formatDate value="${dia.key}" pattern="dd/MM/yyyy"  var="diaFecha"/>
		<c:set var="eventosDia"  value="${dia.value}"></c:set>
		<c:set var="claseDia" value="nada"></c:set>
		<%-- <c:set var="diaHoy"  value="${now}" ></c:set>--%>
		<fmt:formatDate value="${now}" pattern="dd/MM/yyyy" var="diaHoy" />
		<c:if test="${diaFecha==diaHoy}" >
			<c:set var="claseDia" value="hoy"></c:set>
		</c:if>
		<div class="dia ${claseDia}">
			
			<div class="headerDia">
				<%-- <c:set var="diaFechaCorta" value="${fn:substring(diaFecha, 0, 5)}" />
				${diaFechaCorta}
				<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${diaFecha}" />
				--%>
				${diaFecha}
				
			</div>
				
				
				
			<c:forEach var="evento" items="${eventosDia}">
				<c:set var="usuarioActual" value="${usuarioLogueado}"/>
				<%--
				<c:set var="duracion" value="${evento.getHoraFin()}-${evento.getHoraIncio()}"/> 
				--%>
				
				<c:set var="estadoEvento" value="${evento.obtenerEstado(usuarioActual)}"/>
				<div class="${estadoEvento}">
					<c:set var="sUrl" value="${(estadoEvento=='tarea')?'Tarea':'Reunion'}"></c:set>
					<a href="<c:url value="/calendario/editar${sUrl}.do?idEvento=${evento.id}"/>">
						<b>${evento.getHoraInicio()} -  ${evento.getHoraFin()} </b>- ${evento.getTitulo()} - duracion
					</a>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
				
</body>
</html>