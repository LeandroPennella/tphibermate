<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
<script src="<c:url value="/views/ui/js/jquery-ui-1.11.4.js" />"></script>
 --%>
<script type="text/javascript">
//var listaAutocompletar = [];
var listaInvitaciones = [];


/*
//busca por nombreUsuario si el usuario esta agregado en la tabla invitaciones
function buscarLabelUsuarioEnTablaInvitaciones(labelUsuario){
	var encontrado=false;
	$(".celdaNombreCompuesto").each(
		function() {		     			
   			if($(this).html() == labelUsuario){
   				encontrado=true;
   			}
		}		
	)	
	return encontrado;
}
*/
/*
function llenarListaAutocompletar(data) {                     
    var array = {
            label: sarasa,
            value: 2
        }
    
    //response(array);
    return array;
}
*/


/*
function agregarAListaImpresa(id, nombre)
{
	  //todo: agregar estadox
	  $("#listadoUsuariosTentativo").append(
		  '<li id='+id+'>'+
		  	'<a href="/user/messages"><span class="tab">'+nombre+'</span></a>'+
		  	'<a onclick="sacarDeLista(this)">X</a></li>');
}

function sacarDeLista(elemento)
{
	  
    var id=elemento.parentNode.getAttribute("id");
    node=document.getElementById(id);
    node.parentNode.removeChild(node);
}
*/

$(document).on('click', '.borrar', function (event) {
    event.preventDefault();
    $(this).closest('tr').remove();
});

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
    //console.log("listaInvitaciones")
    //console.log(listaInvitaciones)

    //autocomplete
	 $( "#usuariosAutocomplete" ).autocomplete
	    (
			{
		      	source: function( request, response ) {
			        $.ajax({
				        	url: 'listadoDeUsuarios.do',
				        	type: "GET",
				            data: {parteNombre: request.term, cantMax:5},
			              	dataType : "json",
			              	//contentType : "application/json;charset=UTF-8",
				          success: function(data) {response (filtrarListaAutocompletar(data));}
	      			});
	  			},
	  			select: function (event, ui){
	  				console.log("agregar:"+ui.item.value +"|"+ui.item.label)
	  				agregarATablaInvitaciones(ui.item.value, ui.item.label);
	  			    $(this).val("");
	  			    return false;
				}
			}
		)
	} 	 
)

function filtrarListaAutocompletar(data)
{
	//exluye los usuarios ya invitados al evento y al usuario logueado
	
	var estaInvitado;
	var fueRecienInvitado;
	var listaAutocompletar=[];
	
	//TODO: no incluir usuario logueado
	
	//busca en data(los usuarios que vienen del DAO) aquellos que no estan ya invitados, los agrega a listaAutocompletar y los devuelve
	
	for(var i=0; i<data.length; i++) {
		var label=data[i].nombre + " " +   data[i].apellido+ " (" + data[i].nombreUsuario + ")" ;
		var id=data[i].id;
	
		//busca por nombreUsuario si el usuario esta agregado en la tabla invitaciones
		console.log("== buscando en tabla == id: >"+ id+"<");
		var yaInvitado=false;
		
		$(".celdaIdInvitado").each(
				function() {		     		
					console.log("comparando  celda: >"+ $(this).html()+"<");
		   			if($.trim($(this).html())== $.trim(label)){
		   				yaInvitado=true;
		   			}
				}		
			)
		
			console.log("label: "+label+" > encontrado:" + yaInvitado)
			
		
		/*
		$(".celdaNombreCompuesto").each(
			function() {		     		
				console.log("comparando  celda: >"+ $(this).html()+"<");
	   			if($.trim($(this).html())== $.trim(label)){
	   				yaInvitado=true;
	   			}
			}		
		)
	
		console.log("label: "+label+" > encontrado:" + yaInvitado)
		*/
		
		//estaCargado?	
	  	if(yaInvitado==false){
	  		listaAutocompletar.push({value:data[i].id, label:data[i].nombre + " " +   data[i].apellido+ " (" + data[i].nombreUsuario + ")"});
		}
	}
	  
	return (listaAutocompletar);
	  
}

function agregarATablaInvitaciones(id, nombreCompuesto)
{
	  //todo: agregar estadox
	  $("#tablaInvitaciones").append(
		  '<tr id='+id+'>'+
			  '<td class="celdaNombreCompuesto">'+nombreCompuesto+'</td>'+
			  '<td>pendiente</td>'+
		      '<td>'+
		      	'<input type="hidden" name="invitados" value="'+id+'" />'+
			  	'<input type="button" class="borrar" value="Eliminar" />'+
			  '</td>'+
		  '</tr>');
}

</script>