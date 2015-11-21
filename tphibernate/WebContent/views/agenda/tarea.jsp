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
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" id="sidebar">
			</div>
			<!--/span-->
			<div class="span9" id="content">
				<!-- morris stacked chart -->
				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left"><fmt:message key='tarea.titulo'/></div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<c:url value="/agenda/agregarTarea.do" var="x"></c:url>
								<form:form method="POST" commandName="tarea" action="${x}" cssClass="form-horizontal" >
								
									<fieldset>
										<legend><fmt:message key='tarea.tituloAgregar'/></legend>
										<form:hidden path="id"/>										 
										<div class="control-group">
											
											<fmt:message key='evento.label.titulo' var="titulo" />
											<label class="control-label" for="titulo"><fmt:message key="evento.label.titulo" /></label>
											<div class="controls">
												<form:input id="titulo" path="titulo" cssClass="input-xxlarge focused"  placeholder="${titulo}" autofocus="true"  />
												<form:errors path="titulo" cssStyle="color: red" />
											</div>

										</div>
										<div class="control-group">
											<fmt:message key='evento.label.fecha' var="lfecha" />
										    <span class="control-label"><fmt:message key="evento.label.fecha" /></span>
										    <div class="controls form-inline">
												
												
												
												<fmt:formatDate value="${tarea.fecha}" var="dateString" pattern="dd/MM/yyyy" /> 
												<form:input id="fecha" path="fecha" cssClass="input-small" placeholder="${lfecha}" value="${dateString}"/>
												
												<%--
												<form:input id="fecha" path="fecha" cssClass="input-small" placeholder="${lfecha}"/>
												--%>
												
									            <fmt:message key='evento.label.horaInicio' var="horaInicio" />
												<label for="horaInicio"><fmt:message key="evento.label.horaInicio" /></label>
												<form:input id="horaInicio" path="horaInicio" cssClass="input-small "  placeholder="${horaInicio}"/>
												
												<fmt:message key='evento.label.horaFin' var="horaFin" />
												<label for="horaFin"><fmt:message key="evento.label.horaFin" /></label>
												<form:input id="horaFin" path="horaFin" cssClass="input-small "  placeholder="${horaInicio}"/>
											  	<div >
											  	<div><form:errors path="fecha" cssStyle="color: red" /></div>
											  	<div><form:errors path="horaInicio" cssStyle="color: red" /></div>
											  	<div><form:errors path="horaFin" cssStyle="color: red" /></div>
											  		
											  		
													
											  	</div>
									    	</div>
									  	</div>

									  	
										<div class="control-group">
											<fmt:message key='tarea.label.descripcion' var="descripcion" />
											<label class="control-label" for="descripcion"><fmt:message key="tarea.label.descripcion" /></label>
											<div class="controls">
												<form:input id="descripcion" path="descripcion" cssClass="input-xxlarge "  placeholder="${descripcion}" />
												<form:errors path="descripcion" cssStyle="color: red" />
											</div>
										</div>
										
										<div class="control-group">
											<fmt:message key='tarea.label.direccion' var="direccion" />
											<label class="control-label" for="direccion"><fmt:message key="tarea.label.direccion" /></label>
											<div class="controls">
												<form:input id="direccion" path="direccion" cssClass="input-xxlarge "  placeholder="${direccion}" />
												<form:errors path="direccion" cssStyle="color: red" />
											</div>
										</div>
																				
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Save changes</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
									</fieldset>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
                             
                                        
    <%-- 
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
	--%>
</body>
</html>