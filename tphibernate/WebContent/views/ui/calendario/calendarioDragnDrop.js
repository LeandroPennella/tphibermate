
$( function() {

	
	$('.draggable').draggable({
		delay:300,
		axis:"y",
		containment:".dia",
   		snap:".droppable",
   		snapMode:'inner',
   		revert: "invalid",
   		start: function() {
   			$(this).parent().css('z-index', 2);
   			$(this).find('.evento').css('box-shadow','2px 2px 5px #999999');
   		},
        stop: function() {
        	$(this).parent().css('z-index', '');
        	$(this).find('.evento').css('box-shadow','');
        }
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
						//pej desborde;
						evento.css('top',0);
						//alert('error de comunicacion...');
					}
				},
								
				error: function(XHR, jqXHR, textStatus,	errorThrown) {

					evento.css('top',0);
					var errorHtml = "";
					errorHtml +="Error!" + " \n\n"
					errorHtml += " - Status: "+ textStatus+" \n\n" 
					errorHtml += " - Desc: "	+ errorThrown+" \n\n"
					errorHtml += " - XHR: "	+ XHR.responseText+" \n\n"
					errorHtml += " - jqXHR: "	+ jqXHR.responseText+" \n\n"
					alert(errorHtml);
				}
          	});
  		}
	});
});
