<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
        
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            </a>
            
            <a class="brand" href="#">
            	<fmt:message key="titulo"></fmt:message>
           	</a>

            <!-- Login -->
            <ul class="nav pull-right">
                <li class="dropdown">
                    <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
	                    <i class="icon-user"></i> ${usuarioLogueado.getNombreUsuario()} 
	                    <i class="caret"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a tabindex="-1" href="#"><fmt:message key='login.label.perfil'/></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                        	<a href="<c:url value="/logout/logout.do" ></c:url>">
                        		<fmt:message key='login.label.salir'/>
                       		</a>                            
                        </li>
                    </ul>
                </li>
            </ul>
            
        </div>
    </div>
</div>