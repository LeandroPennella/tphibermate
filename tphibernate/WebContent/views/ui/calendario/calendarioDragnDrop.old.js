
    $( function() {
   	 
   	 
    	/*
   		$(".dragable").draggable({
   			snap:".celda", 
   		 	axis: "y" ,//containment:".columnas-horas"
   		 	
   		 	
   		 	stop:function( event, ui ) {
	 	        var item_id = $(ui.draggable).attr('id');
	 	        var item_horaInicio = $(this).attr("id").toString();	 	        
	 	        alert('evento '+item_id +' movido a '+ item_horaInicio);
   		 	}
  		});
   	 */
   	
   		$( '.draggable' ).each(function(){
   	  	var $d = $(this);
   	   		$d.draggable({
   	   			axis:"y",
   	   			/*containment:$d.closest('.dia'),*/
   	        	snap:".celda"//,snapTolerance:2
   	      });
   		});
   	 
   	 
   	/*
 		$( ".celda" ).droppable({
        	drop: function( event, ui ) {
        		alert('drop');
	         	
	          	var item_id = $(ui.draggable).attr('id');
	   	 	   	var item_horaInicio = $(this).attr("id").toString();	
	   	 		var evento={"id":item_id, "horaInicio":item_horaInicio};
	   	 	   	alert('evento '+item_id +' movido a '+ item_horaInicio);
	   	 	   	
	           	
	           	
				$.ajax({
					url: "../evento/mover.do", //?origenId="+item_id+"&destinoId="+location,
					type: "POST",
					//timeout: 10000,
					//data:{id:item_id, horaInicio:item_horaInicio}, //JSON.stringify(evento), 
					data:JSON.stringify(evento),
					contentType : "application/json;charset=UTF-8",
					dataType : "text",//lo que recive
					
					success : function(results, status, xhr){
						//alert('resultado' + results + ' - status: ' + status + ' - xhr:'+xhr);
						if (results){
							alert('si');
							$("#"+item_id).children('a').children('b').html('hi:'+item_horaInicio + " HF:" +String(results));//todo: agregar horafin
						} else {
							alert('no');
						}
					},
									
					error: function(XHR, jqXHR, textStatus,	errorThrown) {
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
        		*/
   	 
	   	 $( '.celda' ).droppable({
	   		
			drop: function( event, ui ) {
				
	 	        var item_id = $(ui.draggable).attr('id');
	 	        var item_horaInicio = $(this).attr("id").toString();	 	        
	 	        alert('evento '+item_id +' movido a '+ item_horaInicio);	  	    
 	        }
	   	 	
	   	 });
   	
	});