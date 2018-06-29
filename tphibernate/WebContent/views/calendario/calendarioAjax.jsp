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
	<!--<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->
	

	<!--navegador-->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	

    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/jquery-ui.css"/>'>
    <!-- en master  <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/bootstrap.3.min.css"/>'>-->
	<link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/calendarioNavegacion.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/bootstrap-datepicker.min.css"/>'>
	 
    <!-- en master <script type="text/javascript" src='<c:url value="/views/ui/js/jquery-1.12.4.js"/>'></script>-->
    <script type="text/javascript" src='<c:url value="/views/ui/js/jquery-ui-1.11.4.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/views/ui/js/bootstrap.min.js"/>'></script>
    
	
	<script type="text/javascript" src='<c:url value="/views/ui/js/bootstrap-datepicker.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/views/ui/js/bootstrap-datepicker.es.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/views/ui/js/calendarioNavegacion.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/views/ui/js/calendarioDragnDrop.js" />'></script>
	<!--navegador-->
	<title><fmt:message key='calendario.titulo'/></title>
</head>
<body>


	<jsp:include page="master_menu.jsp"></jsp:include>



	<div style="  border: 1px solid #CCC;
		  background-color: #E0E0E0;
		  padding: .5em; height:20px;">
		<a href='<c:url value="/calendario/mostrarCalendarioAjax.do?semanaOffset=0"/>'><fmt:message key="calendario.accion.hoy" /> |</a>
		<a href='<c:url value="/calendario/mostrarCalendarioAjax.do?semanaOffset=${semanaOffset-1}"/>'> < |</a>
		<a href='<c:url value="/calendario/mostrarCalendarioAjax.do?semanaOffset=${semanaOffset+1}"/>'> > |</a>

		 

		<!--  Mes mostrado -->

	 	<c:forEach var="diaSemana" items="${SemanaConEventos}">
	 	
			<c:set var="diaFecha"  value="${diaSemana.key}"></c:set>
			
			<fmt:formatDate value="${diaFecha}" var="mesIterado" pattern="MMMM"/>
			<fmt:formatDate value="${diaFecha}" var="anoIterado" pattern="yyyy"/>
			
			<c:if test="${empty mesPrimero}">
				<c:set var="mesPrimero"  value="${mesIterado}"></c:set>
			</c:if>
			<c:if test="${empty anoPrimero}">
				<c:set var="anoPrimero"  value="${anoIterado}"></c:set>
			</c:if>
			
			<c:if test="${mesIterado!=mesPrimero}">
				<c:set var="mesSegundo"  value="${mesIterado}"></c:set>
			</c:if>
			<c:if test="${anoIterado!=anoPrimero}">
				<c:set var="anoSegundo"  value="${anoIterado}"></c:set>
			</c:if>
		</c:forEach>
		
		<c:if test="${not empty mesSegundo}">
			<c:if test="${empty anoSegundo}">
				<c:set var="mesMostrado"  value="${mesPrimero} - ${mesSegundo} de ${anoPrimero} "></c:set>
			</c:if>
			<c:if test="${not empty anoSegundo}">
				<c:set var="mesMostrado"  value="${mesPrimero} de ${anoPrimero} - ${mesSegundo} de ${anoSegundo}"></c:set>
			</c:if>
		</c:if>
		<c:if test="${empty mesSegundo}">
			<c:set var="mesMostrado"  value="${mesPrimero} de ${anoPrimero} "></c:set>
		</c:if>
				
		<c:out value=" ${mesMostrado}" />

		<!-- / Mes mostrado -->

 
	</div>
	
	
	<!-- Horas ------------------------------------------------------------ -->
	<div class="panelHoras">
		<div class="headerDia">
			<fmt:message key="calendario.etiquetas.hora" />
		</div>
		
		<c:forEach items="${horas}" var="hora">
    		<div class="hora">${hora}</div>
		</c:forEach>	
	</div>
	
	
	
	<!-- Dias ------------------------------------------------------------ -->
	
	 <%-- <c:forEach var="diaSemana" items="${semana}">--%>
	 <c:forEach var="diaSemana" items="${SemanaConEventos}">
	 
	
		<c:set var="diaFecha"  value="${diaSemana.key}"></c:set>
		<fmt:formatDate value="${diaFecha}" var="diaFechaString" pattern="dd/MM/yyyy"/>
		<fmt:formatDate value="${diaFecha}" var="nombreDia" pattern="EEE"/>
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
				
				<c:out value="${nombreDia}" />
				<!-- <c:out value="${diaFechaString}" /> -->
				<c:out value="${fn:substring(diaFechaString, 0, 2)}" />
				
			</div>
			
			<!-- Horas ------------------------------------------------------------ -->
			<c:forEach var="hora" items="${horas}" >

			
				<div class="hora" id="${hora}">
					 
					<!-- Eventos -->
					
					<c:forEach var="evento" items="${eventosDia}">
						
						<!-- Evento -->
						<jsp:include page="calendarioAjax_Evento.jsp">
							<jsp:param name="eventoID" value ="${evento.getId()}"/>
							
							<jsp:param name="eventoHoraInicio" value ="${evento.getHoraInicio()}"/>
							<jsp:param name="eventoHoraFin" value ="${evento.getHoraFin()}"/>
							<jsp:param name="eventoMinutosDuracion" value ="${evento.obtenerMinutosDuracion()}"/>
							
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