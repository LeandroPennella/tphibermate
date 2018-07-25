
$( function() {

	
	$('.draggable').draggable({
		delay:300,
		axis:"y",
		containment:".dia",
   		snap:".droppable",
   		snapMode:'inner',
   		revert: "invalid" 
	});

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
						
						$(evento).find('p').html('<b>'+evento_titulo+'</b> '+evento_nuevaHoraInicio + " - " +String(results));
						$(evento).find('a').attr('title', evento_titulo+': '+evento_nuevaHoraInicio + " - " +String(results))

			 	   		evento.css('top',0);
			 	   		evento.css('left',0);
			 	   		$(droppable).append(evento);
						
					} else {
						alert('error de comunicacion...');
					}
				},
								
				error: function(XHR, jqXHR, textStatus,	errorThrown) {

					evento.css('top',0);
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
