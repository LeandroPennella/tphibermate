<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

<script type="text/javascript" src="../ui/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../ui/js/jquery.i18n.properties.js"></script>

<script>


$(document).ready(
    function(){
        $('.botonEliminar').val(getLabel('es','evento.label.eliminar'));
})

function getLabel(lang, tag) {
	var leliminar;
	jQuery.i18n.properties({
	    name: 'mensajes',
	    path: '../ui/messages/',  
	    mode: 'both',
	    language: lang, 
	    callback: function() {
	    	leliminar= jQuery.i18n.prop(tag);        
	    }
	});
	return leliminar;

}
</script>
</head>
<body>
<input class="botonEliminar" id="b1" type="text"/> 
<div id="d1"></div>
</body>
</html>