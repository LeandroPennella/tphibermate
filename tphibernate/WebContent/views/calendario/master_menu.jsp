<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#"><fmt:message key="titulo"></fmt:message></a>
            <div class="nav-collapse collapse">
            <!-- botonera -->
            <ul class="nav">
                    <li class="active">
                        <a href="<c:url value="/calendario/mostrarCalendario.do" ></c:url>"><fmt:message key='calendario.titulo'/></a>
                    </li>
                    <!-- 
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle"><fmt:message key='menu.secciones'/><b class="caret"></b></a>
                        <ul class="dropdown-menu" id="menu1">
-->
                            <li>
                                <a href="<c:url value="/calendario/crearTarea.do" ></c:url>"><fmt:message key='tarea.tituloAgregar'/></a>
                            </li>
                            <li>
                                <a href="<c:url value="/calendario/crearReunion.do" ></c:url>"><fmt:message key='reunion.tituloAgregar'/></a>
                            </li>
                    <!-- 
                        </ul>
                    </li>        
                    -->                 
                </ul>
        
                <!-- Login -->
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> ${usuarioLogueado.getNombreUsuario()} <i class="caret"></i></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="#"><fmt:message key='login.label.perfil'/></a>
                            </li>
                            <li class="divider"></li>
                            <li>
                            <a href="<c:url value="/logout/logout.do" ></c:url>"><fmt:message key='login.label.salir'/></a>
                                <!-- <a tabindex="-1" href="login.html">Logout</a> -->
                            </li>
                        </ul>
                    </li>
                </ul>
                                </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>