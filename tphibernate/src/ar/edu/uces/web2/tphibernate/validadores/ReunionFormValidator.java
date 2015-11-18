package ar.edu.uces.web2.tphibernate.validadores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.form.ReunionForm;

@Component
public class ReunionFormValidator implements Validator{ //TODO: heredar de EventoFormValidator
	
	private Reunion reunion;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ReunionForm.class.isAssignableFrom(clazz);
	}
	public Reunion getReunion()
	{return this.reunion;}
	
	@Override
	public void validate(Object object, Errors errors) {
		ReunionForm reunionForm= (ReunionForm) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "evento.error.tituloVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "evento.error.fechaVacio");	//TODO: como no solaparlo con type mismatch
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaInicio", "evento.error.horaInicioVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaFin", "evento.error.horaFinVacio");
		Date fecha=null;
		if (!errors.hasFieldErrors("fecha"))
		{				
			
			
			 try {
				 String sFecha=reunionForm.getFecha();
				 DateFormat d=new DateFormat();
				fecha=d.parse(sFecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "temario", "reunion.error.temarioVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idSala", "reunion.error.salaVacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idsParticipantes", "reunion.error.participantesVacio");
		if(!errors.hasErrors())
		{
			this.reunion.setTitulo(reunionForm.getTitulo());
			this.reunion.setFecha(fecha);
			this.reunion.setHoraInicio(reunionForm.getHoraInicio());
			this.reunion.setHoraFin(reunionForm.getHoraFin());
			this.reunion.setTemario(reunionForm.getTemario());
			Sala sala=new Sala();
			sala.setId(reunionForm.getIdSala());
			this.reunion.setSala(sala);
			List<Usuario>listaParticipantes=new ArrayList<Usuario>();
			for(Integer idPaticipante:reunionForm.getIdsParticipantes())//{listaParticipantes.addAll(new Usuario(){id=idParticipante}}	
			{
				Usuario usuario=new Usuario();
				usuario.setId(idPaticipante);
				listaParticipantes.add(usuario);
			}
			this.reunion.setParticipantes(listaParticipantes);
			
		}
	}
}
