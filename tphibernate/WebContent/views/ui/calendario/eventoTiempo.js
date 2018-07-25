	$(function() {
		$('#fecha').datepicker({
		    format: "dd/mm/yyyy",
		    language: localeLanguage,
		    orientation: "bottom auto",
		    todayHighlight: true,
		    'autoclose': true
		}).on('changeDate', procesarFecha);
		
		/*
		$("#fecha").addClass( "date start" );
		$('#horaInicio').timepicker({ 'timeFormat': 'H:i' });	
		$('#horaFin').timepicker({ 'timeFormat': 'H:i' });
		*/
		
		

		$('#horaInicio').timepicker({ 			
			'timeFormat': 'H:i' ,		
			'step': '30',
			'useSelect': true,
			'maxTime':'23:00',
        	'scrollDefault': '08:00'
			});
		
		$("#horaInicio").on('change', function(){
			var h=$(this).val()
			cambiarHoraFin(h);							
			});
		
		$('#horaFin').timepicker({ 
			'timeFormat': 'H:i',
			'forceRoundTime': true,
			'step': '30',
			'useSelect': true
			});
		/*
		$("#horaInicio").addClass( "time ui-timepicker-input" );
		$("#horaFin").addClass( "time ui-timepicker-input" );
		*/
		
		$("select").width(90);
		$("select").prop('disabled',$("#fecha").prop('disabled'));
		
		function cambiarHoraFin(horaInicio){
			
	    	
	    	var horaMinima=sumarMediaHoraHora(horaInicio);
	    	var horaPropuesta = sumarHora(horaInicio);
			if(horaPropuesta =="24:00"){horaPropuesta ="23:30"}

	    	// establecer valores
	    	
	    	$("#horaFin").timepicker('setTime',horaPropuesta);
			$("#horaFin").timepicker('option',{
							'minTime': horaMinima,
							'maxTime':'23:30'});
			$("select").width(90);
		}
		
		
		
		
		function sumarHora(horaInicio){
			var arrayHoraInicio = horaInicio.split(":"); //split time by colon

			
    		var h_HoraPropuesta=parseInt(arrayHoraInicio[0]);
	    	var m_HoraPropuesta=arrayHoraInicio[1];    		
	    		
	    	var h_HoraPropuesta = h_HoraPropuesta+1; //agrega una hora
	    	return  h_HoraPropuesta+":"+m_HoraPropuesta; 
		}
		
		
		
		
		function sumarMediaHoraHora(horaInicio){
			
			var arrayHoraInicio = horaInicio.split(":"); //split time by colon
	    	
	    	var h_HoraMinima=parseInt(arrayHoraInicio[0]);
	    	var m_HoraMinima=arrayHoraInicio[1];
	    	
			
	    	if(m_HoraMinima=="30"){
	    		m_HoraMinima="00";
	    		//TODO: validar limite
	    		h_HoraMinima+=1;
    		} else {
    			m_HoraMinima="30";
    		}
			
	    	return  h_HoraMinima+":"+m_HoraMinima;
		}
		
		
		
		
	  	function procesarFecha(){
	  		var fecha = $('#fecha').datepicker('getDate');
	  		var hoy=new Date()
	  		var sFecha=formatearFecha(fecha);
	  		var sHoy=formatearFecha(hoy);
	  		
	  		if (sFecha==sHoy){
	  			var horaActualRedondeada='08:00';  //TODO:establecer hora actual
	  			//TODO: mejorar si no esta definida
	  			if($("#horaInicio").val()=="00:00"){//si no esta definida
	  				$("#horaInicio").timepicker('setTime',horaActualRedondeada);
	  				$("#horaFin").timepicker('setTime',sumarHora(horaActualRedondeada));
	  			}
	  		} else {
	  			if(fecha<hoy){
	  				//TODO: opcion revertir
		  			alert('Atenion. Esta ingresando una fecha pasada');
		  			
  				} else {
  				//TODO: mejorar si no esta definida
  					if($("#horaInicio").val()=="00:00"){//si no esta definida
  						$("#horaInicio").timepicker('setTime','08:00');
  						$("#horaFin").timepicker('setTime','09:00');
  					}
  				}
  					
  			}

	  		
	  	};
	  	
	  	
	  	function formatearFecha(fecha){
	  		var fechaR=fecha.getFullYear() + '/' +
	  	    	((fecha.getMonth()+1)<10 ? '0' : '') + (fecha.getMonth()+1) + '/' +
	  	    	(fecha.getDate()<10 ? '0' : '') + fecha.getDate();
	  		
			
	  		return  fechaR;
	  			
	  	};
	  	
	  	
	});
	/*
	$(document).ready(function(){
		//alert($("#fecha").prop('disabled'));
		alert($("#fecha").val());
		})
		*/