
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
				   		//todo: minimo movimiento
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
  			var evento = $(ui.draggable);
			var evento_id = $(ui.draggable).attr('id');		
 	   		var evento_nuevaHoraInicio = $(this).attr("id").toString();
 	   		
 	   		var evento_titulo=$(evento).find('#titulo').text();
 	   		var evento_horaInicio=$(evento).find('#horaInicio').text();
 	   		var evento_horaFin=$(evento).find('#horaFin').text();
 	   		
 	   		var evento_nuevaHoraFin;//todo: calcular
 	   		
 	   		var droppable=$(this);
 	   		
 	   		var eventoJson_id=evento_id.substr(1);
 	   		var eventoJson_hr=evento_nuevaHoraInicio.substr(-4,2)+':'+evento_nuevaHoraInicio.substr(-2);
 	   		var eventoJson={"id":eventoJson_id, "horaInicio":eventoJson_hr};

 	   		
 	   		
 	   		
 	   		//cambiarEvento(evento_id,evento_nuevaHoraInicio);
 	   		evento.css('top',0);
 	   		evento.css('left',0);
 	   		$(droppable).append($(ui.draggable));
 	   		
			$.ajax({
				url: "../evento/mover.do", 
				type: "POST",
				data:JSON.stringify(eventoJson),
				contentType : "application/json;charset=UTF-8",
				dataType : "text",			
				success : function(results, status, xhr){

					if (results){
						//todo: cambiar hora // agregar horafin
						$(evento).find('a').html('<b>'+evento_titulo+'</b>hi:'+evento_nuevaHoraInicio + " HF:" +String(results));
						$

			 	   		//Cambiar Evento de celda

						
					} else {
						alert('sin resultado...');
					}
				},
								
				error: function(XHR, jqXHR, textStatus,	errorThrown) {
					//TODO: rollbackear drop?
					var errorHtml = "Error!<br/>";
					errorHtml += "Status: "+ textStatus+"<br/>" 
					errorHtml += " | Desc: "	+ errorThrown+"<br/>"
					errorHtml += " | XHR: "	+ XHR.responseText+"<br/>"
					errorHtml += " | jqXHR: "	+ jqXHR.responseText+"<br/>"
					alert(errorHtml);
				}
          	});
  		}
	});
	
	mostrarSemana();
});
