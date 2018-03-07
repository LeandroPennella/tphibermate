	  function agregarALista(id, nombre)
	  {
		  //todo: agregar estadox
		  $("#listadoUsuariosTentativo").append(
			  '<li id='+id+'>'+
			  	'<a href="/user/messages"><span class="tab">'+nombre+'</span></a>'+
			  	'<a onclick="sacarDeLista(this)">X</a></li>');
	  };
      function sacarDeLista(elemento)
      {
    	  
          var id=elemento.parentNode.getAttribute("id");
          node=document.getElementById(id);
          node.parentNode.removeChild(node);
      }
 $(
	 function()
	 {
	    $( "#usuariosAutocomplete" ).autocomplete
	    (
 			{
		      	source: function( request, response ) {
			        $.ajax({
				        	url: 'listadoDeUsuarios.do',
				        	type: "GET",
				            data: {parteNombre: request.term},
			              	dataType : "json",
			              	//contentType : "application/json;charset=UTF-8",
				          success: function( data ) {
				        	  
				        	  var nombres = [];
				        	  for(var i=0; i<data.length; i++) {
				        	  	//todo: revisar que no este ya invitado
				        	  	nombres.push(data[i].nombre + ", " + data[i].apellido);
				        	  	//agregarAListado(data[i].nombre + ", " + data[i].apellido);
				        	  	
				        	  }
				        	  response(nombres);
				        	  
				        	  //response(nombres.slice(0,5));
				          }
				           
	      			});
	  			},
	  			select: function (event, ui){
	  				//todo: sacar a funcion
	  				agregarALista(ui.item.id, ui.item.label);
	  				$(this).val('');
	  				//$("#listadoUsuariosTentativo").append('<li id='+ui.item.id+'><a href="/user/messages"><span class="tab">'+ui.item.label+'</span></a></li>');
				}
		
 			}
		)
	}
	 
	 
 );

 