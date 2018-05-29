<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../ui/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" language="JavaScript" src="../ui/js/jquery.i18n.properties.min.js"></script>

<script>
jQuery.i18n.properties({
    name: 'mensajes', 
    path: '../../src/recursos/messages/', 
    mode: 'both',
    language: 'en', 
    callback: function() {
        $("#eliminar").text(jQuery.i18n.prop('evento.label.eliminar'));
        
    }
});</script>
</head>
<body>
<input id="eliminar"></input> 
</body>
</html>