package ar.edu.uces.web2.tphibernate.validadores;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.form.UsuarioAutenticacionForm;

@Component
public class ReunionValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioAutenticacionForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		Reunion reunion= (Reunion) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "tarea.error.tituloVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "tarea.error.fechaVacio");
		/*if (!errors.hasFieldErrors("fecha")) {
			if (!esFecha(tarea.getFecha()))
			{
				errors.rejectValue("nombre", "error.jugador.valor.comienzaConNumero");
			}
		}*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaInicio", "tarea.error.horaInicioVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaFin", "tarea.error.horaFinVacio");
		if (!(errors.hasFieldErrors("horaInicio")&&errors.hasFieldErrors("horaFin"))) {
			SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
			Date desde = null;
			Date hasta = null;
			try{
				desde= parser.parse(reunion.getHoraInicio());
			} catch (java.text.ParseException e){
				errors.rejectValue("horaFin", "tarea.error.horaInicio");
			}
			try{
				hasta = parser.parse(reunion.getHoraFin());
			} catch (java.text.ParseException pe){
				errors.rejectValue("horaFin", "tarea.error.horaFin");
			}
			if (desde.before(hasta)){
				errors.rejectValue("horaFin", "tarea.error.intervalo");
			}
			
			
		}
	}
}
