<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="icon" href="/imagenes/Roulette.gif">

    <title><fmt:message key="titulo"/></title>

    <link href="<c:url value='/views/ui/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/views/ui/css/login.css'/>" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="../js/ie-emulation-modes-warning.js"></script> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>

  <body>

    <div class="container">

		<c:url value="/autenticacion/validar.do" var="x"></c:url>
		<form:form method="POST" commandName="usuarioAutenticacionForm" action="${x}" cssClass="form-signin">	
			<div class="panel panel-default">
				<div class="panel-body">

					
					<h2 class="form-signin-heading"><fmt:message key="login.label.identifiquese" /></h2>

					<!-- nombreUsuario -->
					<fmt:message key='login.label.usuario' var="usuario" />
					<form:input path="nombreUsuario" cssClass="form-control"  placeholder="${usuario}" autofocus="true"  />
					<form:errors path="nombreUsuario" cssStyle="color: red" />

					<!-- contrasenia -->
					<fmt:message key='login.label.contrasenia' var="contrasenia" />
					<form:password path="contrasenia" cssClass="form-control"  placeholder="${contrasenia}"   />

					<form:errors path="contrasenia" cssStyle="color: red" />

					<!-- recordarme -->
					<fmt:message key='login.label.recordarme' var="recordarme" />
					<form:checkbox path="recordarme" cssClass="form-control"  placeholder="${recordarme}"   />

					
					<!-- Submit -->
					<form:button id="btnEntrar" class="btn btn-lg btn-primary btn-block" ><fmt:message key="login.label.entrar" /></form:button>

					
				</div>
			</div>
		</form:form>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
  

</body></html>