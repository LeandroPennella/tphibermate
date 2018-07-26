<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--master -->
<%@ page isELIgnored="false"%>
<%@ page import="org.springframework.web.servlet.support.RequestContext"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="dia" value="${now}" pattern="d" />
<% String lang = (new RequestContext(request)).getLocale().getLanguage(); %>
<!--/master -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!--master -->
	<script type="text/javascript">
		var localeLanguage='<%=lang%>';/*<fmt:message key="lang"/>*/
	</script> 
	<!--/master -->	

	<meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/jquery-ui.css"/>'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/bootstrap.3.min.css"/>'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/bootstrap-datepicker.min.css"/>'>
	 
    <script type="text/javascript" src='<c:url value="/views/ui/js/jquery-1.12.4.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/views/ui/js/jquery-ui-1.12.1.js"/>'></script> 
     
    <script type="text/javascript" src='<c:url value="/views/ui/js/bootstrap.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/views/ui/js/bootstrap-datepicker.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/views/ui/js/bootstrap-datepicker.es.min.js"/>'></script>
	
	<link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/calendario/calendarioNavegacion.css"/>'>
	<script type="text/javascript" src='<c:url value="/views/ui/calendario/calendarioNavegacion.js"/>'></script>
	<link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/calendario/calendarioGrilla.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/calendario/calendarioEvento.css"/>'>
    <script type="text/javascript" src='<c:url value="/views/ui/calendario/calendarioDragnDrop.js"/>'></script>   	
	 

	<title><fmt:message key='calendario.titulo'/></title>
	
</head>
<body>


	

<!-- Menu ------------------------------------------------------------------------------------------------------------------------->

<nav class="navbar navbar-default">

  <div class="container-fluid">
    <!-- agrupa Brand y toggle  -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Alternar navegacion</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand active" href="#">Calendario<span class="sr-only">(current)</span></a>
    </div>

    <!-- toggling -->
    <div class="collapse navbar-collapse" id="navbar-collapse-1">
      <ul class="nav navbar-nav">

		<li>
		    <a href="<c:url value="/calendario/crearTarea.do" ></c:url>"><fmt:message key='tarea.tituloAgregar'/></a>
		</li>
		<li>
		    <a href="<c:url value="/calendario/crearReunion.do" ></c:url>"><fmt:message key='reunion.tituloAgregar'/></a>
		</li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			  	<i class="icon-user"></i> 
				${usuarioLogueado.getNombreUsuario()}
				<span class="caret"></span>
			</a>
          <ul class="dropdown-menu">            
            <li><a tabindex="-1" href="#"><fmt:message key='login.label.perfil'/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="<c:url value="/logout/logout.do" ></c:url>"><fmt:message key='login.label.salir'/></a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->

</nav>
  



<!-- Barra  -------------------------------------------------------------------------------------------------------------------------->

<div id="barrasuperior">

   	<div id="toggleBoton">&#9776; </div>  
   	
	<a href='<c:url value="/calendario/mostrarCalendario.do"/>'><fmt:message key="calendario.accion.hoy" /></a> |
	
	<fmt:formatDate value="${fecha}" var="mesApuntado" pattern="MM"/>
	<fmt:formatDate value="${fecha}" var="anioApuntado" pattern="yyyy"/>
	<fmt:formatDate value="${fecha}" var="diaApuntado" pattern="dd"/>
	<%--
	<c:out value=" ${diaApuntado}" />
	<fmt:formatDate value="${fecha}" var="diaApuntadoString" pattern="dd/MM/yyyy"/>
		--%>
			
	<a href='<c:url value="/calendario/mostrarCalendario.do?anio=${anioApuntado}&mes=${mesApuntado}&dia=${diaApuntado}&offset=-1"/>'> < </a> |
	<a href='<c:url value="/calendario/mostrarCalendario.do?anio=${anioApuntado}&mes=${mesApuntado}&dia=${diaApuntado}&offset=1"/>'> > </a> |
	

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
	
	<fmt:message key="calendario.etiquetas.de" var="deInt" />
	
	<c:if test="${not empty mesSegundo}">
		<c:if test="${empty anoSegundo}">
			<c:set var="mesMostrado"  value="${mesPrimero} - ${mesSegundo} ${deInt} ${anoPrimero} "></c:set>
		</c:if>
		<c:if test="${not empty anoSegundo}">
			<c:set var="mesMostrado"  value="${mesPrimero} ${deInt} ${anoPrimero} - ${mesSegundo} ${deInt} ${anoSegundo}"></c:set>
		</c:if>
	</c:if>
	<c:if test="${empty mesSegundo}">
		<c:set var="mesMostrado"  value="${mesPrimero} ${deInt} ${anoPrimero} "></c:set>
	</c:if>
			
	<c:out value=" ${mesMostrado}" />

	<!-- / Mes mostrado -->


	
</div>


<div style="clear:both;"></div>

   <div id="mySidenav" class="sidenav">
     <div id="calendarioenlinea"></div>
   </div>


<!-- Main -------------------------------------------------------------------------------------------------------------------------->
<div id="main">


	<!-- Cabecera ------------------------------------------------------------ -->
	<div class="calendario-cabecera">
	
		<!-- Horas -->
        <div class="columnas-horas">
          	<div class="celda ">
          	<%-- <fmt:message key="calendario.etiquetas.hora" />--%>
          	</div>
        </div>

		
 		<!-- Dias -->
		 <c:forEach var="diaSemana" items="${SemanaConEventos}">
		 	<c:set var="diaFecha"  value="${diaSemana.key}"></c:set>
		
			<c:set var="claseDia" value="nada"></c:set>
	
			<!-- set DiaHoy -->		
			<c:if test="${dFechaHoy==diaFecha}" ><c:set var="claseDia" value="hoy"></c:set></c:if>
	
	        <div class="columnas-dias<%-- claseDia --%>">
	          	<div class="celda ">
	            
					<fmt:formatDate value="${diaFecha}" var="nombreDia" pattern="EEE"/>
					<div class="DiaNombre"><c:out value="${nombreDia}" /></div>
					
					<fmt:formatDate value="${diaFecha}" var="diaFechaString" pattern="dd/MM/yyyy"/>
					<div class="DiaNumero"><c:out value="${fn:substring(diaFechaString, 0, 2)}" /></div>
	          	
	          	</div>
			</div>
		</c:forEach>
	</div>

	<!-- Cuerpo-------------------------------------------------------------------------------------------->
	<div class=" calendario-cuerpo">
      
		<!--  Horas --------------------------------------------------------------------------------------->
		
        <div class="columnas-horas">
        	<c:set var="mediaHora" value="false"></c:set>
			<c:forEach var="hora" items="${horas}" >
				
          		<div class="celda ">
					<c:if test="${mediaHora eq false}">
					  ${hora}
					</c:if>
				</div>         		
          		<c:set var="mediaHora" value="${not mediaHora}"></c:set> 
			</c:forEach>
		</div>
		
		<!--  Dias  --------------------------------------------------------------------------------------->
		
		<c:forEach var="diaSemana" items="${SemanaConEventos}">
		
			<c:set var="eventosDia"  value="${diaSemana.value}"></c:set>
			
			<c:set var="diaFechaCuerpo"  value="${diaSemana.key}"></c:set>
			<fmt:formatDate value="${diaFechaCuerpo}" var="diaFechaStringCuerpo" pattern="dd/MM/yyyy"/>
			<c:set var="numeroDia" value="${fn:substring(diaFechaStringCuerpo, 0, 2)}" />
			
			<div class="columnas-dias" id="d${numeroDia}">	
			<c:forEach var="hora" items="${horas}" >			
				
	          	<div class="celda "><div class="droppable" id="d${numeroDia}h${fn:replace(hora, ':', '')}">
					
					<!-- Eventos ------------------------------------------------------------------------>
					
					<c:forEach var="evento" items="${eventosDia}">				
						
						<!-- Evento -->
						
						<c:set var="altoEventoMinutosDuracion" value="${(evento.obtenerMinutosDuracion()/30)*2.5}"/> <%-- 21 div + 2 borde - 1 borde superior --%>
						<c:set var="esLaHora" value="${evento.getHoraInicio()==hora}" />
						
						<c:choose>
											 
							<c:when test="${esLaHora}">
								<c:set var="sUrl" value="${(evento.obtenerEstado(usuarioLogueado)=='tarea')?'Tarea':'Reunion'}"></c:set>
						
								<div id="e${evento.getIdEvento()}"
									<c:if  test="${evento.obtenerEstado(usuarioLogueado)=='reunionAutor' || evento.obtenerEstado(usuarioLogueado)=='tarea'}">class="draggable"</c:if> 
									<c:if  test="${evento.obtenerEstado(usuarioLogueado)!='reunionAutor' && evento.obtenerEstado(usuarioLogueado)!='tarea'}">class="nodraggable"</c:if>
									>
									<div 
										class=" evento   ${evento.obtenerEstado(usuarioLogueado)}"
										style="height: ${altoEventoMinutosDuracion}vh;" >
										<a
											class="default-link"  
											href="<c:url value="/calendario/editar${sUrl}.do?idEvento=${evento.getIdEvento()}"/>" 
											title="${evento.getTitulo()}: ${evento.getHoraInicio()} -  ${evento.getHoraFin()}"></a>
											<p>
												<b>${evento.getTitulo()}</b> ${evento.getHoraInicio()} -  ${evento.getHoraFin()}
											</p> 
									</div>
									<div style="display:none;" id="titulo">${evento.getTitulo()}</div>		  
							 	</div>
							 			
							</c:when>
							
						</c:choose>
					
					</c:forEach>
					<!-- /Eventos -->

				  </div></div>       			
			</c:forEach>
			</div>	
		</c:forEach>
		<!-- Dias -->	
	</div>	
	<!-- cuerpo -->
</div>
<!-- Main -->	
</body>
</html>