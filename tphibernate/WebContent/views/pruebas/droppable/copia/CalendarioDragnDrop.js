
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
					alert('e: ' +$evento_id + ' - c:' + $dia_id + ' - s:'+ $droppables_ids);
					
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
					//tablaPosiciones.push[{"dia":$dia_id}]
					//todo: crear tabla posiciones
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
 	   		//cambiarEvento(evento_id,evento_nuevaHoraInicio);
 	   		evento.css('top',0);
 	   		evento.css('left',0);
 	   		$(this).append($(ui.draggable));
  		}
	});
	
	mostrarSemana();
});
