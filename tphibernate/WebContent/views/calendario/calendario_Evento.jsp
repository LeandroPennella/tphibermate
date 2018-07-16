<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<c:set var="hora" value="${param.hora}"/>

<c:set var="estadoEvento" value="${param.estadoEvento}"/>


<c:set var="eventoHoraInicio" value="${param.eventoHoraInicio}"/>
<c:set var="eventoHoraFin" value="${param.eventoHoraFin}"/>

<%-- <c:set var="eventoMinutosDuracion" value="${param.eventoMinutosDuracion}"/>--%>

<c:set var="altoEventoMinutosDuracion" value="${((param.eventoMinutosDuracion)/30)*2.5}"/> <%-- 21 div + 2 borde - 1 borde superior --%>

<c:set var="eventoTitulo" value="${param.eventoTitulo}"/>
<c:set var="eventoEstadoUsuarioActual" value="${param.eventoEstadoUsuarioActual}"/>
<c:set var="eventoId" value="${param.eventoID}"/>
<c:set var="esLaHora" value="${eventoHoraInicio==hora}" />

<c:choose>
					 
	<c:when test="${esLaHora}">
		<c:set var="sUrl" value="${(eventoEstadoUsuarioActual=='tarea')?'Tarea':'Reunion'}"></c:set>

		<div class="draggable" id="e${eventoId}">
			<div 
				class=" evento   ${eventoEstadoUsuarioActual}"
				style="height: ${altoEventoMinutosDuracion}vh;" >
				<a 
					href="<c:url value="/calendario/editar${sUrl}.do?idEvento=${eventoId}"/>" 
					title="${eventoTitulo}: ${eventoHoraInicio} -  ${eventoHoraFin}">
					<b>${eventoTitulo}</b> ${eventoHoraInicio} -  ${eventoHoraFin} 
				</a>
				<div style="display:none;" id="titulo">${eventoTitulo}</div>		
  
			</div>
	 	</div>
	 			
	</c:when>
	
</c:choose>
 