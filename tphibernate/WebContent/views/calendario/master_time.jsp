<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
	<link href='<c:url value="/views/ui/css/bootstrap-datepicker.min.css"/>' rel="stylesheet">
	
	<script type="text/javascript" src="<c:url value='/views/ui/js/bootstrap-datepicker.min.js'/>"></script>

	<script type="text/javascript" src="<c:url value='/views/ui/js/bootstrap-datepicker.es.min.js'/>"></script>

	<link href='<c:url value="/views/ui/css/jquery.timepicker.css"/>' rel="stylesheet">
	<script type="text/javascript" src="<c:url value='/views/ui/js/jquery.timepicker.min.js'/>"></script>
		
	<script type="text/javascript">
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
		
		function cambiarHoraFin(horaInicio){
			
			
			//TODO: reutilizar agregarHora, agregarMediaHora
			
			//horaInicio
			
			var arrayHoraInicio = horaInicio.split(":"); //split time by colon
			
	    	//horaMinima=horaInicio + 1/2
	    	
	    	var h_HoraMinima=parseInt(arrayHoraInicio[0]);
	    	var m_HoraMinima=arrayHoraInicio[1];
	    	
			
	    	if(m_HoraMinima=="30"){
	    		m_HoraMinima="00";
	    		//TODO: validar limite
	    		h_HoraMinima+=1;
    		} else {
    			m_HoraMinima="30";
    		}
			
	    	var horaMinima= h_HoraMinima+":"+m_HoraMinima;
	    	
	    		
			//hora propuesta	    	
	    	
    		var h_HoraPropuesta=parseInt(arrayHoraInicio[0]);
	    	var m_HoraPropuesta=arrayHoraInicio[1];    		
	    		
	    	var h_HoraPropuesta = h_HoraPropuesta+1; //agrega una hora
	    	var horaPropuesta = h_HoraPropuesta+":"+m_HoraPropuesta; //newTime: 1 hr mas
			if(horaPropuesta =="24:00"){horaPropuesta ="23:30"}


	    	// establecer valores
	    	
	    	$("#horaFin").timepicker('setTime',horaPropuesta);
			$("#horaFin").timepicker('option',{
							'minTime': horaMinima,
							'maxTime':'23:30'});
			$("select").width(90);
		}
		
		
		
	  	function procesarFecha(){
	  		var fecha = $('#fecha').datepicker('getDate');
	  		var hoy=new Date()
	  		var sFecha=formatearFecha(fecha);
	  		var sHoy=formatearFecha(hoy);
	  		
	  		if (sFecha==sHoy){
	  			var horaActualRedondeada='10:00';  //TODO:establecer hora actual
	  			//$("#horaInicio").val(horaActualRedondeada);
	  			$("#horaInicio").timepicker('setTime',horaActualRedondeada);//TODO: si no esta definida
	  		} else {
	  			if(fecha<hoy){
	  				//TODO: opcion revertir
		  			alert('Atenion. Esta ingresando una fecha pasada');
		  			
  				} else {
  					$("#horaInicio").timepicker('setTime',horaActualRedondeada);//TODO: si no esta definida
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
	</script>