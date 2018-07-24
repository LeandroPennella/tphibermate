$(function(){
	function evaluarCancelar(){
		if(huboCambios()){
			//TODO: revertir
			alert("va a perder los cambios realizados");
		}
	};
	
	function huboCambios(){
		//TODO: evaluar si hubo cambios
		return true;
	};
})