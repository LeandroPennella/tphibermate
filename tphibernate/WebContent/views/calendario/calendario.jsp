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

<script type="text/javascript">
	var localeLanguage='<%=lang%>';
</script> 
<!--/master -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%-- <jsp:include page="master_header.jsp"></jsp:include>--%>
	
	<!--  old  --<link href='<c:url value="/views/ui/css/Calendario.css"/>' rel="stylesheet">-->
	
	<link href='<c:url value="/views/ui/css/CalendarioGrilla.css"/>' rel="stylesheet">
	<link href='<c:url value="/views/ui/css/CalendarioEvento.css"/>' rel="stylesheet">
	<!--<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->
	

	<!--navegador-->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	

    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/jquery-ui.css"/>'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/bootstrap.3.min.css"/>'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/CalendarioGrilla.css"/>'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/CalendarioEvento.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/calendarioNavegacion.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/views/ui/css/bootstrap-datepicker.min.css"/>'>
	 
    <script type="text/javascript" src='<c:url value="/views/ui/js/jquery-1.12.4.js"/>'></script>
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


	

<!-- Menu -->

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
      <a class="navbar-brand" href="#">Calendario</a>
    </div>

    <!-- toggling -->
    <div class="collapse navbar-collapse" id="navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Tarea<span class="sr-only">(current)</span></a></li>
        <li><a href="#">Reunion</a></li>

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
  




	<div id="barrasuperior">
    	<div id="toggleBoton">&#9776; </div>  
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
	

	<div style="clear:both;"></div>
	
    <div id="mySidenav" class="sidenav">
      <div id="calendarioenlinea"></div>
    </div>

























<div id="main">





	<!-- Dias ------------------------------------------------------------ -->

	<div class="calendario-cabecera">
        <div class="columnas-horas">
          	<div class="fila "></div>
        </div>

	

	
	
	 
	 <c:forEach var="diaSemana" items="${SemanaConEventos}">
	 
	
		<c:set var="diaFecha"  value="${diaSemana.key}"></c:set>
		<fmt:formatDate value="${diaFecha}" var="diaFechaString" pattern="dd/MM/yyyy"/>
		<fmt:formatDate value="${diaFecha}" var="nombreDia" pattern="EEE"/>		
		<c:set var="eventosDia"  value="${diaSemana.value}"></c:set>
		<c:set var="claseDia" value="nada"></c:set>

		<!-- set DiaHoy -->		
		<c:if test="${dFechaHoy==diaFecha}" ><c:set var="claseDia" value="hoy"></c:set></c:if>







        <div class="columnas-dias">
          	<div class="fila ">
            	<div class="DiaNombre">Lun</div>
            	<div class="DiaNumero">4</div>
          	</div>
		</div>
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
	
	 
	 <c:forEach var="diaSemana" items="${SemanaConEventos}">
	 
	
		<c:set var="diaFecha"  value="${diaSemana.key}"></c:set>
		<fmt:formatDate value="${diaFecha}" var="diaFechaString" pattern="dd/MM/yyyy"/>
		<fmt:formatDate value="${diaFecha}" var="nombreDia" pattern="EEE"/>
		
	
		
		<c:set var="eventosDia"  value="${diaSemana.value}"></c:set>
		<c:set var="claseDia" value="nada"></c:set>

		<!-- set DiaHoy -->
		
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

	</div>	
</body>
</html>