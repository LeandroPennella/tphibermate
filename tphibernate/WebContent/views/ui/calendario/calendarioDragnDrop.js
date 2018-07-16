
var semana=[];
var eventos=[];

function mostrarSemana(){
	//debugger; 
	//alert('semana');
}

function cambiarEvento( evento, evento_nuevoRecipiente){
	alert('evento '+evento_id +' movido a '+ evento_nuevaHoraInicio);  
	//encontrar elemento
	
}

$( function() {
	
	
	$('.calendario-cuerpo').children('.columnas-dias').each(
	
		function(){
			var $dia=$(this);
			var $dia_id=$(this).attr('id');
	    	//alert('dia:' + $dia_id);	
			
			$droppables=$dia.children('.celda').children('.droppable');
			var $droppables_ids='';
			$droppables.each(function(){
				$droppables_ids+='#'+($(this).attr('id')+',');
		   	});
		    $droppables_ids=$droppables_ids.slice(0,-1);
		    //alert($droppables_ids);
			var dia=[];	    
		    dia.id=$dia_id;
		
			$dia.find('.draggable').each(
					
				function(){
					var $evento=$(this);
					var $evento_id=$(this).attr('id');
					//alert('e: ' +$evento_id + ' - c:' + $dia_id + ' - s:'+ $droppables_ids);
					
					$evento.draggable({
						axis:"y",
						containment:$dia,
				   		snap:$droppables_ids,
				   		snapMode:'inner'
				   		
					  });
					
					
					var evento={};					
					evento.id=$evento_id
					evento.horario=$evento.parent().attr('id')
					dia.push(evento);


				}
			)
	    	
			semana.push(dia);
		}
	);
	
	

	$( ".droppable" ).droppable({
  		drop: function( event, ui ) {
			var evento_id = $(ui.draggable).attr('id');
			var evento = $(ui.draggable);
 	   		var evento_nuevaHoraInicio = $(this).attr("id").toString();
 	   		
 	   		var eventoJson_id=evento_id.substr(1);
 	   		var eventoJson_hr=evento_nuevaHoraInicio.substr(-4,2)+':'+evento_nuevaHoraInicio.substr(-2);
 	   		var eventoJson={"id":eventoJson_id, "horaInicio":eventoJson_hr};

 	   		var eventoJsonString=JSON.stringify(eventoJson);
 	   		
   			//alert("id: "+eventoJson_id+ " - horaInicio: " + eventoJson_hr);
 	   		//alert("ejs: "+eventoJsonString);
 	   		
			$.ajax({
				url: "../evento/mover.do", //?origenId="+item_id+"&destinoId="+location,
				type: "POST",
				//timeout: 10000,
				//data:{id:item_id, horaInicio:item_horaInicio}, //JSON.stringify(evento), 
				data:JSON.stringify(eventoJson),
				contentType : "application/json;charset=UTF-8",
				dataType : "text",//lo que recive
				
				success : function(results, status, xhr){
					//alert('resultado' + results + ' - status: ' + status + ' - xhr:'+xhr);
					if (results){
						alert('si');
						
						//todo: cambiar hora // agregar horafin
						$("#"+item_id).children('a').children('b').html('hi:'+item_horaInicio + " HF:" +String(results));
						

			 	   		//Cambiar Evento de celda
			 	   		//cambiarEvento(evento_id,evento_nuevaHoraInicio);
			 	   		evento.css('top',0);
			 	   		evento.css('left',0);
			 	   		$(this).append($(ui.draggable));
						
					} else {
						alert('no');
					}
				},
								
				error: function(XHR, jqXHR, textStatus,	errorThrown) {
					//TODO: rollbackear drop?
					var errorHtml = "An error ocurred <br/>";
					errorHtml += "Status: "+ textStatus 
					errorHtml += " | Reason: "	+ errorThrown
					errorHtml += " | XHR: "	+ XHR.responseText
					errorHtml += " | jqXHR: "	+ jqXHR.responseText
					alert(errorHtml);
				}
          	});
  		}
	});
	
	mostrarSemana();
});
