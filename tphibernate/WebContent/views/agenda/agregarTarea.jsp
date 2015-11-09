<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="titulo.agregarTarea"/></title>
</head>
<body>

    <div class="container">

		<c:url value="/agenda/agregarTarea.do" var="x"></c:url>
		<form:form method="POST" commandName="Tarea" action="${x}" >	
			<div >

					<h4 class="form-signin-heading"><fmt:message key="login.label.identifiquese" /></h4>

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
					<form:label path="recordarme" class="checkbox">
						<form:checkbox path="recordarme"  placeholder="${recordarme}"   />
						<fmt:message key="login.label.recordarme" />
					</form:label>
					
					<!-- Submit -->
					<form:button id="btnEntrar" class="btn btn-lg btn-primary btn-block" ><fmt:message key="login.label.entrar" /></form:button>

					

			</div>
		</form:form>
    </div> <!-- /container -->
	
</body>
</html>