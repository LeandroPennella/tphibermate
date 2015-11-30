<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link href='<c:url value="/views/ui/css/jquery.timepicker.css"/>' rel="stylesheet">
	<link href='<c:url value="/views/ui/css/bootstrap-datepicker.min.css"/>' rel="stylesheet">
	<script type="text/javascript" src="<c:url value='/views/ui/js/jquery.timepicker.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/views/ui/js/bootstrap-datepicker.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/views/ui/js/bootstrap-datepicker.es.min.js'/>"></script>
	<script type="text/javascript">
	$(function() {
		$('#fecha').datepicker({
		    format: "dd/mm/yyyy",
		    language: "es",
		    orientation: "bottom auto",
		    todayHighlight: true,
		    'autoclose': true
		});
		$("#fecha").addClass( "date start" );
		$('#horaInicio').timepicker({ 'timeFormat': 'H:i' });
		$("#horaInicio").addClass( "time ui-timepicker-input" );
		$('#horaFin').timepicker({ 'timeFormat': 'H:i' });
		$("#horaFin").addClass( "time ui-timepicker-input" );

	});
	</script>