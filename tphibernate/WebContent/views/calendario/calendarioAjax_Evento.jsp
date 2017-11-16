<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:set var="hora" value="${param.hora}"/>

<c:set var="estadoEvento" value="${param.estadoEvento}"/>


<c:set var="eventoHI" value="${param.eventoHoraInicio}"/>
<c:set var="eventoHF" value="${param.eventoHoraFin}"/>

<c:set var="eventoMD" value="${param.eventoMinutosDuracion}"/>
<c:set var="altoEventoMD" value="${((eventoMD)/30)*21}"/>

<c:set var="eventoT" value="${param.eventoTitulo}"/>
<c:set var="eventoEstadoUA" value="${param.eventoEstadoUsuarioActual}"/>
<c:set var="eventoId" value="${param.eventoID}"/>

 




 <c:set var="comp" value="${eventoHI==hora}" />
<c:choose>
						 
	<c:when test="${comp}">
				
		<div class="${eventoEstadoUA}" style="height: ${altoEventoMD}px;">
			<c:set var="sUrl" value="${(eventoEstadoUA=='tarea')?'Tarea':'Reunion'}"></c:set>
			<a href="<c:url value="/calendario/editar${sUrl}.do?idEvento=${eventoId}"/>">
				<b>${eventoHI} -  ${eventoHF} </b>- ${eventoT}
			</a>
		</div>
						
	</c:when>
</c:choose>
 