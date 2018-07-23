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
		
		$("#fecha").addClass( "date start" );
		$('#horaInicio').timepicker({ 'timeFormat': 'H:i' });
		$("#horaInicio").addClass( "time ui-timepicker-input" );
		$('#horaFin').timepicker({ 'timeFormat': 'H:i' });
		$("#horaFin").addClass( "time ui-timepicker-input" );
	  	function procesarFecha(){
	  		var fecha = $('#fecha').datepicker('getDate');
	  		var hoy=new Date()
	  		var sFecha=formatearFecha(fecha);
	  		var sHoy=formatearFecha(hoy);
	  		
	  		if(fecha<(new Date())){
	  			alert('fecha pasada');
  			} else {
  				if (sFecha==sHoy){
  					
  				} else {
  					$('horaInicio').text='08:00';
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