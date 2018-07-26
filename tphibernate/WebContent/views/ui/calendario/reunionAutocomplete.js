




$(document).on('click', '.borrar', function (event) {
    event.preventDefault();
    $(this).closest('tr').remove();
});

//autocomplete
$(function(){
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
	);
})

function getLabel(tag) {
	var leliminar;
	jQuery.i18n.properties({
	    name: 'mensajes',
	    path: '../views/ui/messages/',  
	    mode: 'both',
	    language: localeLanguage, 
	    callback: function() {
	    	leliminar= jQuery.i18n.prop(tag);        
	    }
	});
	return leliminar;

}

function filtrarListaAutocompletar(data)
{
	//exluye los usuarios ya invitados al evento y al usuario logueado
	//console.log("recibidos: "+data.length);

	var estaInvitado;
	var fueRecienInvitado;
	var listaAutocompletar=[];
	
	//TODO: no incluir usuario logueado
	
	//busca en data(los usuarios que vienen del DAO) aquellos que no estan ya invitados, los agrega a listaAutocompletar y los devuelve
	
	for(var i=0; i<data.length; i++) {
		var label=data[i].nombre + " " +   data[i].apellido+ " (" + data[i].nombreUsuario + ")" ;
		var id=data[i].id;
	
		//busca por id si el usuario esta agregado en la tabla invitaciones
		var yaInvitado=false;
		
		$(".hiddenIdInvitado").each(
				function() {		     		
					console.log("comparando  celda: >"+ $(this).val()+"<");
		   			if($.trim($(this).val())== id){
		   				yaInvitado=true;
		   			}
				}		
			)
		
	  	if(yaInvitado==false){
	  		listaAutocompletar.push({value:data[i].id, label:data[i].nombre + " " +   data[i].apellido+ " (" + data[i].nombreUsuario + ")"});
		}
	}
	  
	return (listaAutocompletar.slice(0,5));
	  
}

function agregarATablaInvitaciones(id, nombreCompuesto)
{
	  //todo: agregar estadox
	  $("#tablaInvitaciones").append(
		  '<tr>'+//id='+id+'
			  '<td class="celdaNombreCompuesto">'+nombreCompuesto+'</td>'+
				//TODO: reemplazar texto estado por iconos
			  '<td>'+getLabel('reunion.estado.pendiente')+'</td>'+
		      '<td>'+
		      	'<input type="hidden" name="invitados" class="hiddenIdInvitado" value="'+id+'" />'+
			  	'<input type="button" class="borrar" value='+getLabel('evento.label.eliminar')+' />'+
			  '</td>'+
		  '</tr>');
}

