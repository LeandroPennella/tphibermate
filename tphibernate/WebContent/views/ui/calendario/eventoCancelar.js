	function evaluarCancelar(fecha){
		if(huboCambios()){
			
			//TODO: revertir
			if (confirm("va a perder los cambios realizados")){

		  		$(location).attr(
	  				'href',
	  				'../calendario/mostrarCalendario.do?anio='+
	  				fecha.substring(6,10)+
	  				'&mes='+
	  				fecha.substring(3,5)+
	  				'&dia='+
	  				fecha.substring(0,2));  	
			}
		}
	};
	
	function huboCambios(){
		//TODO: evaluar si hubo cambios
		return true;
	};
	
