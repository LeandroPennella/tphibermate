
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
				}
			)
		}
	);
	
	

	$( ".droppable" ).droppable({
  		drop: function( event, ui ) {
  			var evento = $(ui.draggable);
			var evento_id = $(ui.draggable).attr('id');		
 	   		var evento_droppedId= $(this).attr("id").toString();
			var evento_nuevaHoraInicio=evento_droppedId.substr(-4,2)+':'+evento_droppedId.substr(-2);
			
 	   		var evento_titulo=$(evento).find('#titulo').text();
 	   		
 	   		var droppable=$(this);
 	   		
 	   		var eventoJson={"id":evento_id.substr(1), "horaInicio":evento_nuevaHoraInicio};

 	   		
			$.ajax({
				url: "../evento/mover.do", 
				type: "POST",
				data:JSON.stringify(eventoJson),
				contentType : "application/json;charset=UTF-8",
				dataType : "text",			
				success : function(results, status, xhr){

					if (results){
						
						$(evento).find('a').html('<b>'+evento_titulo+'</b> '+evento_nuevaHoraInicio + " - " +String(results));

			 	   		//todo: Cambiar Evento de celda aca
			 	   		//cambiarEvento(evento_id,evento_nuevaHoraInicio);
			 	   		evento.css('top',0);
			 	   		evento.css('left',0);
			 	   		$(droppable).append(evento);
						
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
});
