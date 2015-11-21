package ar.edu.uces.web2.tphibernate.validadores;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.web2.tphibernate.modelo.form.EventoForm;

@Component
public class EventoFormValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return EventoForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		EventoForm eventoFrom= (EventoForm) object;

		//TODO: Evaluar fecha y hora con regex?
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "evento.error.tituloVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "evento.error.fechaVacio");	//TODO: como no solaparlo con type mismatch
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaInicio", "evento.error.horaInicioVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaFin", "evento.error.horaFinVacio");
		if (!(errors.hasFieldErrors("horaInicio")&&errors.hasFieldErrors("horaFin"))) {
			SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
			Date desde = null;
			Date hasta = null;
			String sDesde=eventoFrom.getHoraInicio();
			String sHasta=eventoFrom.getHoraFin();
			boolean sonFechas=true;
			try{
				desde= parser.parse(sDesde);
			} catch (java.text.ParseException e){
				errors.rejectValue("horaInicio", "evento.error.horaInicio");
				sonFechas=false;
			}
			try{
				hasta = parser.parse(sHasta);
			} catch (java.text.ParseException pe){
				errors.rejectValue("horaFin", "evento.error.horaFin");
				sonFechas=false;
			}
			if (sonFechas&& !desde.before(hasta)){
					errors.rejectValue("horaFin", "evento.error.intervalo");
			}
		}	
	}
}