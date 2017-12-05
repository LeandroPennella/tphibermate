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
<c:set var="altoEventoMD" value="${(((eventoMD)/30)*23)-1}"/> <%-- 21 div + 2 borde - 1 borde superior --%>

<c:set var="eventoT" value="${param.eventoTitulo}"/>
<c:set var="eventoEstadoUA" value="${param.eventoEstadoUsuarioActual}"/>
<c:set var="eventoId" value="${param.eventoID}"/>

 




 <c:set var="comp" value="${eventoHI==hora}" />
<c:choose>
						 
	<c:when test="${comp}">
		<c:set var="sUrl" value="${(eventoEstadoUA=='tarea')?'Tarea':'Reunion'}"></c:set>
			
			<div class="${eventoEstadoUA}" style="height: ${altoEventoMD}px;">
			<a href="<c:url value="/calendario/editar${sUrl}.do?idEvento=${eventoId}"/>" style="padding-bottom: 21px;" >
					<b>${eventoHI} -  ${eventoHF} </b>- ${eventoT}
				
			</a>		
			</div>
		
	</c:when>
</c:choose>
 