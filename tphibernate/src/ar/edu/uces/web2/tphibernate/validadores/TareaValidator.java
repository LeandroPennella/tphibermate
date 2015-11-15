package ar.edu.uces.web2.tphibernate.validadores;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import ar.edu.uces.web2.tphibernate.modelo.base.Tarea;
import ar.edu.uces.web2.tphibernate.modelo.form.UsuarioAutenticacionForm;

@Component
public class TareaValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioAutenticacionForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		Tarea tarea= (Tarea) object;
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
				desde= parser.parse(tarea.getHoraInicio());
			} catch (java.text.ParseException e){
				errors.rejectValue("horaFin", "tarea.error.horaInicio");
			}
			try{
				hasta = parser.parse(tarea.getHoraFin());
			} catch (java.text.ParseException pe){
				errors.rejectValue("horaFin", "tarea.error.horaFin");
			}
			if (desde.before(hasta)){
				errors.rejectValue("horaFin", "tarea.error.intervalo");
			}
			
			
		}
	}
}
