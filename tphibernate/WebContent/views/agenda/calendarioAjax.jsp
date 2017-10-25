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

		<div style="float: left;"><a href='<c:url value="/agenda/mostrarCalendario.do?semanaOffset=${semanaOffset-1}"/>'><</a></div>
		<div style="float: right;"><a href='<c:url value="/agenda/mostrarCalendario.do?semanaOffset=${semanaOffset+1}"/>'>></a></div>
	</div>
	
	
	
	<div class="panelHoras">
		<div class="headerDia">
			Horas
		</div>
		<c:forEach begin="0" end="48" varStatus="ihora">
			<div class="hora">
				Ix: ${ihora.index} - 
				<%-- (ihora.index)%2==0) --%>
				<c:set var="indexPar" value="${((ihora.index)%2==0)?'+':'-'}"/> 
				<%--${indexPar} --%>
				<%-- <c:set var="horaRenglon" value="${Math.round(ihora.index/2)}"/>--%>
				<fmt:formatNumber var="horaRenglon"  value="${(ihora.index-0.2) / 2}"  maxFractionDigits="0" /> 
				<c:set var="horaRenglon" value="${horaRenglon.concat(':').concat((ihora.index%2!=0)?'30':'00')}"/>
				${horaRenglon}
				</div>
		</c:forEach>
	</div>
	
	
	
	<!-- Dias ------------------------------------------------------------ -->
	<c:forEach var="dia" items="${semana}">
		<c:set var="diaFecha"  value="${dia.key}"></c:set>
		<c:set var="eventosDia"  value="${dia.value}"></c:set>
		<c:set var="claseDia" value="nada"></c:set>
		<c:if test="${sFechaHoy==diaFecha}" >
			<c:set var="claseDia" value="hoy"></c:set>
		</c:if>
		<div class="dia ${claseDia}">
			
			<div class="headerDia">
				<c:set var="diaFechaCorta" value="${fn:substring(diaFecha, 0, 5)}" />
				${diaFechaCorta}
			</div>
			
			<!-- Horas ------------------------------------------------------------ -->
			<c:forEach begin="0" end="48" varStatus="ihora">
				<div class="hora">
					<%-- IndexR: ${ihora.index} ${indexPar} --%>
					<c:set var="indexPar" value="${((ihora.index)%2==0)?'+':'-'}"/> 

					 
					<fmt:formatNumber var="horaRenglon"  value="${(ihora.index-0.2) / 2}"  maxFractionDigits="0" />
					<c:set var="horaRenglon" value="${horaRenglon.concat(':').concat((ihora.index%2!=0)?'30':'00')}"/>					
					
					<%-- rompe al querer castear string a date --%>
					<fmt:formatDate value="${horaRenglon}" pattern="h:m" var="horaRenglon" />




					<%--HoraEvento--%>
					2 + 
					
					<%--
					 * averiguar que tipo trae evento.getHoraInicio
					 * castearlo a string 
					 --%>
					 
					<c:set var="horaInicioV" value="${evento.getHoraInicio()}" />	
					<fmt:formatDate value="${evento.getHoraInicio()}" pattern="H:m" var="horaInicio" />
					<c:set var="strDate" value="${horaInicio}"/>
					<c:forEach var="evento" items="${eventosDia}">

					hI:
					<%-- ${strDate} ---%> 
					${evento.getHoraInicio()}
					>
					${horaInicioV}
					
					| hR:
					${horaRenglon}			
				
						<c:set var="comp" value="${evento.getHoraInicio()==horaRenglon})" />
						| c:  
						${comp} 
						
						 
					 	<c:choose>						 
							<c:when test="${evento.getHoraInicio()}.equals(${horaRenglon})">
							
								<c:set var="usuarioActual" value="${usuarioLogueado}"/>
								<c:set var="estadoEvento" value="${evento.obtenerEstado(usuarioActual)}"/>
								
								<div class="${estadoEvento}">
									<c:set var="sUrl" value="${(estadoEvento=='tarea')?'Tarea':'Reunion'}"></c:set>
									<a href="<c:url value="/agenda/editar${sUrl}.do?idEvento=${evento.id}"/>">
										<b>${evento.getHoraInicio()} -  ${evento.getHoraFin()} </b>- ${evento.getTitulo()}
									</a>
								</div>
							sarasa<br/>				
							</c:when>
						</c:choose>					
																
					</c:forEach>
					
					
				</div>
			</c:forEach>

		</div>
	</c:forEach>
</body>
</html>