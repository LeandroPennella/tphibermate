<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="master_header.jsp"></jsp:include>
<title><fmt:message key="titulo.agregarTarea"/></title>
</head>
<body>
<jsp:include page="master_menu.jsp"></jsp:include>
    
    
    
    
    <div class="container">

		<c:url value="/agenda/agregarTarea.do" var="x"></c:url>
		<form:form method="POST" commandName="tarea" action="${x}" >	
			<div >

					<h4 class="form-signin-heading"><fmt:message key="tarea.label.titulo" /></h4>

					<!-- titulo -->
					<div class="control-group">
					<fmt:message key='tarea.label.titulo' var="titulo" />
					<label class="control-label" for="focusedInput">Focused input</label>
					<div class="controls">
					<form:input path="titulo" cssClass="input-xlarge focused"  placeholder="${titulo}" autofocus="true"  />
					<form:errors path="titulo" cssStyle="color: red" />
                    </div>
                    </div>


                                          
                                          
                                            


					<!-- fecha -->
					<fmt:message key='tarea.label.x' var="fecha" />
					<form:input path="fecha" cssClass="form-control"  placeholder="${fecha}"  />
					<form:errors path="fecha" cssStyle="color: red" />

					<!-- hora inicio -->
					<fmt:message key='tarea.label.x' var="horaInicio" />
					<form:input path="horaInicio" cssClass="form-control"  placeholder="${horaInicio}"  />
					<form:errors path="horaInicio" cssStyle="color: red" />
					
					<!-- hora fin -->
					<fmt:message key='tarea.label.horaFin' var="horaFin" />
					<form:input path="horaFin" cssClass="form-control"  placeholder="${horaFin}"  />
					<form:errors path="horaFin" cssStyle="color: red" />
					
					<!-- descripcion -->
					<fmt:message key='tarea.label.descripcion' var="descripcion" />
					<form:input path="descripcion" cssClass="form-control"  placeholder="${descripcion}"  />
					<form:errors path="descripcion" cssStyle="color: red" />
					
					<!-- direccion -->
					<fmt:message key='tarea.label.direccion' var="direccion" />
					<form:input path="direccion" cssClass="form-control"  placeholder="${direccion}"  />
					<form:errors path="direccion" cssStyle="color: red" />
					
					<!-- Submit -->
					<form:button id="btnEntrar" class="btn btn-lg btn-primary btn-block" ><fmt:message key="login.label.entrar" /></form:button>

					

			</div>
		</form:form>
    </div> <!-- /container -->
	
</body>
</html>