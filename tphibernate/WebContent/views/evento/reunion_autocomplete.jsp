<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


 
<script type="text/javascript">

var listaInvitaciones = [];

$(
	//carga invitaciones ya hechas (usuarios+estados)
	function(){		
		
	<c:forEach var="invitacion" items="${reunionForm.invitaciones}">
		listaInvitaciones.push({
			id: "${invitacion.usuario.id}" , 
			nombre:  "${invitacion.usuario.nombre} ${invitacion.usuario.apellido} (${invitacion.usuario.nombreUsuario})" , 
			estado:  "${invitacion.aceptado}"
		});
    </c:forEach>

	} 	 
)

</script>

<script type="text/javascript" src="../views/ui/js/jquery.i18n.properties.js"></script>
<script type="text/javascript" src="../views/ui/calendario/reunionAutocomplete.js"></script>