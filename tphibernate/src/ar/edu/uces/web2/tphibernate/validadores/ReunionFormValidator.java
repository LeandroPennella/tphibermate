package ar.edu.uces.web2.tphibernate.validadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.web2.tphibernate.modelo.base.Invitacion;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.form.ReunionForm;

@Component
public class ReunionFormValidator extends EventoFormValidator implements Validator{ //TODO: heredar de EventoFormValidator
	
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ReunionForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {

		ReunionForm reunionForm= (ReunionForm) object;
		/*
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "evento.error.tituloVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "evento.error.fechaVacio");	//TODO: como no solaparlo con type mismatch
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaInicio", "evento.error.horaInicioVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaFin", "evento.error.horaFinVacio");
		
		//--
		Date fecha=null;
		if (!errors.hasFieldErrors("fecha"))
		{				
			
			
			 try {
				 String sFecha=reunionForm.getFecha();
				 SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
				  //DateFormat d = DateFormat.getDateInstance("dd/MM/yyyy");
				 fecha=dateFormatter.parse(sFecha );	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//--
		if (!(errors.hasFieldErrors("horaInicio")&&errors.hasFieldErrors("horaFin"))) {
			SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
			Date desde = null;
			Date hasta = null;
			String sDesde=reunionForm.getHoraInicio();
			String sHasta=reunionForm.getHoraFin();
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
		*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "temario", "reunion.error.temarioVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idSala", "reunion.error.salaVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tokensInvitadosMasConfirmacion", "reunion.error.participantesVacio");
		if(!errors.hasErrors())
		{

			
		}
	}
}
