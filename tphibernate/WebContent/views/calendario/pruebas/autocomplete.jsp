<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<script src="http://localhost:8080/tphibernate/views/ui/js/jquery-1.11.3.min.js" /></script>
	<script src="http://localhost:8080/tphibernate/views/ui/js/jquery-ui-1.11.4.js" /></script>
	<link rel="stylesheet" href="http://localhost:8080/tphibernate/views/ui/css/jquery-ui.css">
	<script>
	  
	 $(function(){
			function agregar (usuarioId){}
		    $( "#usuariosAutocomplete" ).autocomplete({
		      	source: function( request, response ) {
			        $.ajax({
				        	url: 'listadoDeUsuarios.do',
				        	type: "GET",
				        	
				            data: {
				            	parteNombre: request.term
			              	},
			              	dataType : "json",
			              	
				          success: function( data ) {
				            response( data );
				          }
	      			});
	  			}})
			});
	  
	</script>
</head>
<body>
<input id="usuariosAutocomplete"  />
</body>
</html>