<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
<script src="<c:url value="/views/ui/js/jquery-ui-1.11.4.js" />"></script>
 --%>
<script type="text/javascript">
//var listaAutocompletar = [];
var listaInvitaciones = [];

function agregarATablaInvitaciones(id, nombreCompuesto)
{
	  //todo: agregar estadox
	  $("#tablaInvitaciones").append(
		  '<tr id='+id+'>'+
		  '<td class="celdaNombreCompuesto">'+nombreCompuesto+'</td>'+
		  '<td>pendiente</td>'+
	      '<td><input type="button" class="borrar" value="Eliminar" /></td>'+
		  '</tr>');
}

//busca por nombreUsuario si el usuario esta agregado en la tabla invitaciones
function buscarEnTablaInvitaciones(nombreCompuesto){
	var encontrado=false;
	$(".celdaNombreCompuesto").each(
		function() {		     			
   			if($(this).html() == nombreCompuesto){
   				encontrado=true;
   			}
		}		
	)	
	return encontrado;
}


function llenarListaAutocompletar(data)
{
	//console.log("> llenarListaAutocompletar ==================================================")
	//console.log("data:"+data)
	//console.log("listaAutocompletar:"+listaAutocompletar)
	var estaInvitado;
	var fueRecienInvitado;
	var listaAutocompletar=[];
	
	for(var i=0; i<data.length; i++) {
		//console.log ("* data "+i+": "+ data[i] + " | " + data[i].nombre)
		
		//estaInvitado?
		//console.log ("* - estaInvitado?")
		
		/*
		//TODO: reemplazar por leerTablaInvitaciones
		for(var j=0; j<listaInvitaciones.length; j++) {
			if(listaInvitaciones[j].id == data[i].id) {	
				estaInvitado = true;
				break;
			}
		}
		*/
		
		 //estaCargado?
		 var label=data[i].nombre + " " +   data[i].apellido+ " (" + data[i].nombreUsuario + ")" ;
		  //console.log ("* - estaCargado? | Lista:"+listaAutocompletar )
		//console.log("    listaAutocompletar.length:"+listaAutocompletar.length)
/*
		 if (listaAutocompletar.length>0)
		 {
		  for (var j=0; j<listaAutocompletar.length;j++){
			  //console.log("    comprobacion: " + listaAutocompletar[j]+" | " + label + ";")
			  if(listaAutocompletar[j]==label){
				  estaCargado = true;
				  break;
			  }
		  }
		 }
	 */
	 
	 //todo: fueRecienInvitado
	 
	 /*
	  if ((estaInvitado != true) && (fueRecienInvitado != true)){
	  	listaAutocompletar.push(label);
	  }
		*/
		console.log("esta?"+buscarEnTablaInvitaciones(label))
	  if(buscarEnTablaInvitaciones(label)==false){
		  listaAutocompletar.push(label);
		}
	}
	  
	  //console.log(" < llenarListaAutocompletar | listaAutocompletar: "+ listaAutocompletar)
	  return listaAutocompletar;
	  //response(nombres.slice(0,5)); > para limitar la lista, pero se hace desde el controlador
}



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
    console.log("listaInvitaciones")
    console.log(listaInvitaciones)

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
				          success: function( data ) {
				        	  response(llenarListaAutocompletar(data));	  
				          }
	      			});
	  			},
	  			select: function (event, ui){
	  				agregarATablaInvitaciones(ui.item.id, ui.item.label);
	  			    $(this).val("");
	  			    return false;
				}
			}
		)
	} 	 
)

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
</script>