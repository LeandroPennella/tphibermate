package ar.edu.uces.web2.tphibernate.validadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		EventoForm eventoForm= (EventoForm) object;

		//TODO: Evaluar fecha y hora con regex?
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "evento.error.tituloVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "evento.error.fechaVacio");	//TODO: como no solaparlo con type mismatch
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaInicio", "evento.error.horaInicioVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaFin", "evento.error.horaFinVacio");
		if (!(errors.hasFieldErrors("fecha"))) {
			Date fechaValidada=fechaValida(eventoForm.getFecha());
			if (fechaValidada==null) {
				errors.rejectValue("fecha", "evento.error.fechaIncorrecta");
			}			else 			{
				//TODO: validar en referencia al año actual
				Calendar calendar= Calendar.getInstance();
				calendar.setTime(fechaValidada);
				if (calendar.get(Calendar.YEAR)>2100){errors.rejectValue("fecha", "evento.error.aniomayor");}	
				if (calendar.get(Calendar.YEAR)<2000){errors.rejectValue("fecha", "evento.error.aniomenor");}		
			}
		}


		Date desde = null;
		Date hasta = null;
		boolean sonFechas=true;
		
		if (errors.hasFieldErrors("horaInicio")){
			sonFechas=false;
		} else {
			desde=horaValida(eventoForm.getHoraInicio());
			if (desde==null)
			{
				errors.rejectValue("horaInicio", "evento.error.horaInicio");
				sonFechas=false;
			}
		}
		if (errors.hasFieldErrors("horaFin")) {
			sonFechas=false;
		} else {
			hasta=horaValida(eventoForm.getHoraFin());
			if (hasta==null)
			{
				errors.rejectValue("horaFin", "evento.error.horaFin");
				sonFechas=false;
			}
		}
		if (sonFechas&& !desde.before(hasta)){
				errors.rejectValue("horaFin", "evento.error.intervalo");
		}
	}	
	
	
	public static Date horaValida(String hora) {
		Date horaValida=null;
		try{
			SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
			horaValida= formatoHora.parse(hora);
		} catch (ParseException e){
			
			return null;
		}
		return horaValida;
	}
	
	 public static Date fechaValida(String fecha) {
		 Date fechaValida=null;
	        try {
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	            formatoFecha.setLenient(false);
	            fechaValida=formatoFecha.parse(fecha);
	        } catch (ParseException e) {
	            return null;
	        }
	        return fechaValida;
	    }
}