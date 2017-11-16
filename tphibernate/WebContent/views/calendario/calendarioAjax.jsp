<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="master_header.jsp"></jsp:include>
	<link href='<c:url value="/views/ui/css/Calendario.css"/>' rel="stylesheet">
     <script src="../ui/js/jquery-ui-1.11.4.js"></script>
	<title><fmt:message key='calendario.titulo'/></title>
</head>
<body>


	<jsp:include page="master_menu.jsp"></jsp:include>
	<div style="  border: 1px solid #CCC;
		  background-color: #E0E0E0;
		  padding: .5em; height:20px;">

		<div style="float: left;"><a href='<c:url value="/calendario/mostrarCalendario.do?semanaOffset=${semanaOffset-1}"/>'><</a></div>
		<div style="float: right;"><a href='<c:url value="/calendario/mostrarCalendario.do?semanaOffset=${semanaOffset+1}"/>'>></a></div>
	</div>
	
	
	<!-- Dias ------------------------------------------------------------ -->
	<div class="panelHoras">
		<div class="headerDia">
			Horas > 41
		</div>
		
		<c:forEach items="${horas}" var="hora">
    		<div class="hora">${hora}</div>
		</c:forEach>	
	</div>
	
	
	
	<!-- Dias ------------------------------------------------------------ -->
	
	 <%-- <c:forEach var="diaSemana" items="${semana}">--%>
	 <c:forEach var="diaSemana" items="${semanaDate}">
	 
	
		<c:set var="diaFecha"  value="${diaSemana.key}"></c:set>
		<fmt:formatDate value="${diaFecha}" var="diaFechaString" pattern="dd/MM/yyyy"/>
		
		<%-- <c:set var="diaFechaString"  value="${diaSemana.key}"></c:set>--%>
	
		
		<c:set var="eventosDia"  value="${diaSemana.value}"></c:set>
		<c:set var="claseDia" value="nada"></c:set>

		<!-- DiaHoy -->
		<%-- <c:if test="${sFechaHoy==diaFecha}" >--%>
		<c:if test="${dFechaHoy==diaFecha}" >
			<c:set var="claseDia" value="hoy"></c:set>
		</c:if>
		
		<!-- Dia ------------------------------------------------------------ -->
		<div class="dia ${claseDia}">
			
			<div class="headerDia">
				<c:out value="${fn:substring(diaFechaString, 0, 5)}" />
			</div>
			
			<!-- Horas ------------------------------------------------------------ -->
			<c:forEach var="hora" items="${horas}" >
			
			<c:set var="altoEvento" value="${evento.getMinutosDuracion()*21}" />
			
			
			
			
			
			
			
			
			emd: ${evento.getMinutosDuracion()}
			
				<div class="hora" style="height:21px;">
					 
					<c:set var="horaInicio" value="${evento.getHoraInicio()}" />

					<!-- Eventos -->
					<c:forEach var="evento" items="${eventosDia}">
						
						<!-- Evento -->
						<jsp:include page="calendarioAjax_Evento.jsp">
							<jsp:param name="eventoID" value ="${evento.getId()}"/>
							
							<jsp:param name="eventoHoraInicio" value ="${evento.getHoraInicio()}"/>
							<jsp:param name="eventoHoraFin" value ="${evento.getHoraFin()}"/>
							
							
							<jsp:param name="eventoTitulo" value ="${evento.getTitulo()}"/>
							<jsp:param name="eventoEstadoUsuarioActual" value ="${evento.obtenerEstado(usuarioLogueado)}"/>
							
							<jsp:param name="estadoEvento" value ="${estadoEvento}"/>
							
							<jsp:param name="hora" value ="${hora}"/>
							
						</jsp:include>
					
					</c:forEach>
					<!-- /Eventos -->
					
					
				</div>
			</c:forEach>

		</div>
	</c:forEach>
</body>
</html>