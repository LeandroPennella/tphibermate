

package ar.edu.uces.web2.tphibernate.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.EventoDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Evento;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Tarea;
import ar.edu.uces.web2.tphibernate.modelo.form.EventoForm;
import ar.edu.uces.web2.tphibernate.modelo.form.ReunionForm;
import ar.edu.uces.web2.tphibernate.modelo.form.TareaForm;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;


@SessionAttributes("usuarioLogueado")
@Controller
public class CalendarioController {
	
	private EventoDAO eventoDAO;
	
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	
	
	@RequestMapping(value = "/calendario/mostrarCalendario")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado, @RequestParam(value="semanaOffset", required=false) Integer semanaOffset) {
		Calendar calendar = crearCalendario (semanaOffset);
		
		ModelAndView mv=new ModelAndView("/views/calendario/calendario.jsp"); //navegacion
		
		mv.addObject("SemanaConEventos", getSemanaConEventos(usuarioLogueado,  calendar,  new SimpleDateFormat("dd/MM/yyyy")/*sdf*/));
		mv.addObject("semanaOffset", semanaOffset);
		mv.addObject("dFechaHoy",new Date());
		mv.addObject("horas",getHorasDia());
		return mv;
	}
	
	public Calendar /*void*/ crearCalendario(/*Calendar calendar, int diasAlDomingo, */Integer semanaOffset)
	{
		Calendar calendar = Calendar.getInstance();
		
		int diasAlDomingo= (calendar.get(Calendar.DAY_OF_WEEK))-1;
		//posicionar		
		//???
		calendar.roll(Calendar.DAY_OF_YEAR, -diasAlDomingo);//TODO: revisar
		
			
		if (semanaOffset!=null) {
			//???
			
			int semFut=calendar.get(Calendar.WEEK_OF_YEAR)+semanaOffset;
			calendar.roll(Calendar.WEEK_OF_YEAR, semanaOffset);
			if (semFut<1) {calendar.roll(Calendar.YEAR, -1);}

			//System.out.println("a�o:" +calendar.get(Calendar.YEAR)+ " - sem:" + calendar.get(Calendar.WEEK_OF_YEAR));
		} else {
			semanaOffset=0;
			}

		return calendar;
	}
	
	public List<String> getHorasDia() {
		List<String> horas = new ArrayList<String>();	
		//TODO: implementar con for

		horas.add("00:00");
		horas.add("00:30");
		horas.add("01:00");
		horas.add("01:30");
		horas.add("02:00");
		horas.add("02:30");
		horas.add("03:00");
		horas.add("03:30");
		horas.add("04:00");
		horas.add("04:30");
		horas.add("05:00");
		horas.add("05:30");
		horas.add("06:00");
		horas.add("06:30");
		horas.add("07:00");
		horas.add("07:30");
		horas.add("08:00");
		horas.add("08:30");
		horas.add("09:00");
		horas.add("09:30");
		horas.add("10:00");
		horas.add("10:30");
		horas.add("11:00");
		horas.add("11:30");
		horas.add("12:00");
		horas.add("12:30");
		horas.add("13:00");
		horas.add("13:30");
		horas.add("14:00");
		horas.add("14:30");
		horas.add("15:00");
		horas.add("15:30");
		horas.add("16:00");
		horas.add("16:30");
		horas.add("17:00");
		horas.add("17:30");
		horas.add("18:00");
		horas.add("18:30");
		horas.add("19:00");
		horas.add("19:30");
		horas.add("20:00");
		horas.add("20:30");
		horas.add("21:00");
		horas.add("21:30");
		horas.add("22:00");
		horas.add("22:30");
		horas.add("23:00");
		horas.add("23:30");
		return horas;
	}


	public Map<Date,List<EventoForm>> getSemanaConEventos(Usuario usuarioLogueado, Calendar calendar, SimpleDateFormat sdf){

		
		Map<Date,List<EventoForm>> semana = new TreeMap<Date,List<EventoForm>> (); 
		
		List<Evento> eventosDia;
		List<EventoForm> eventosFormDia;

 		for(int i=0;i<7;i++){
			eventosDia=eventoDAO.getByAutorAndDate(usuarioLogueado, calendar.getTime() );
			eventosFormDia=new ArrayList<EventoForm>();
			for(Evento evento:eventosDia)
			{
				evento.setUsuarioActual(usuarioLogueado);
				if (evento instanceof Reunion) {eventosFormDia.add(new ReunionForm(evento));} 
				if (evento instanceof Tarea) {eventosFormDia.add(new TareaForm(evento));}
			}
	
			//String sFecha = sdf.format(calendar.getTime());
			semana.put(calendar.getTime(), eventosFormDia);
			calendar.add(Calendar.DATE, 1);
		}
		
		
		return semana;
	}
	/*
	public void posicionarEventosDia(List<EventoForm> eventos){
		for(EventoForm evento : eventos)
		{
			for(EventoForm eventoComparado : eventos)
			{
				if (eventoComparado.getIdEvento()!=evento.getIdEvento())
				{
					//todo: crear setRenglon? 

					//todo: pasar ##:## > ### y comparar numericamente
					int horaInicio=Integer.parseInt(eventoComparado.getHoraInicio().replaceAll(":", ""));
					int horaFin=Integer.parseInt(eventoComparado.getHoraFin().replaceAll(":", ""));
					
					//if((eventoComparado.getHoraInicio()>=evento.getHoraInicio())&&(eventoComparado.getHoraFin()<=evento.getHoraFin()))
					//{
//						evento.setEventosSimultaneos(evento.getEventosSimultaneos()+1);
					//}
					
				}
			}
		}
	}*/
}

