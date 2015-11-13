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
                     <ul class="nav pull-right">
                         <li class="dropdown">
                             <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> ${usuario.getNombreUsuario()} <i class="caret"></i></a>
                             <ul class="dropdown-menu">
                                 <li>
                                     <a tabindex="-1" href="#">Profile</a>
                                 </li>
                                 <li class="divider"></li>
                                 <li>
                                 	<a href="<c:url value="/logout/logout.do" ></c:url>">Salir</a>
                                     <!-- <a tabindex="-1" href="login.html">Logout</a> -->
                                 </li>
                             </ul>
                         </li>
                     </ul>
                     <ul class="nav">
                         <li class="active">
                             <a href="#">Dashboard</a>
                         </li>
                         <li class="dropdown">
                             <a href="#" data-toggle="dropdown" class="dropdown-toggle">Settings <b class="caret"></b></a>
                             <ul class="dropdown-menu" id="menu1">

                                 <li>
                                     <a href="<c:url value="/agenda/crearTarea.do" ></c:url>">Agregar Tarea</a>
                                 </li>
                                 <li>
                                     <a href="#">Other Link</a>
                                 </li>
                                 <li class="divider"></li>
                                 <li>
                                     <a href="#">Other Link</a>
                                 </li>
                                 <li>
                                     <a href="#">Other Link</a>
                                 </li>
                             </ul>
                         </li>                         
                     </ul>
                 </div>
                 <!--/.nav-collapse -->
             </div>
         </div>
     </div>