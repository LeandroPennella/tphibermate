

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
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;


@SessionAttributes("usuarioLogueado")
@Controller
public class CalendarioAjaxController {
	
	private EventoDAO eventoDAO;
	
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	@RequestMapping(value = "/calendario/mostrarCalendarioAjax")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado, @RequestParam(value="semanaOffset", required=false) Integer semanaOffset) {
		//Date fecha = new Date();
		
		//http://www.forosdelweb.com/f45/como-recorrer-fechas-374533/
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Calendar calendar = Calendar.getInstance();
		

		//int diasAlDomingo= (calendar.get(Calendar.DAY_OF_WEEK))-1;
		//int diasAlDomingo=diaSemanaActual-1;
		
		//posicionar calendario en Domingo
		//posicionarCalendario (calendar, /*diasAlDomingo,*/ semanaOffset);
		Calendar calendar = crearCalendario (semanaOffset);

		
		
		
		
		//fecha hoy
		//String sFechaHoy=sdf.format(new Date());
		//Date dFechaHoy=new Date();


		 
		
		ModelAndView mv=new ModelAndView("/views/calendario/calendarioAjax.jsp");
		//mv.addObject("semana", getSemanaString(usuarioLogueado,  calendar,  sdf));
		//posicionarCalendario(calendar, diasAlDomingo, semanaOffset);
		mv.addObject("SemanaConEventos", getSemanaConEventos(usuarioLogueado,  calendar,  new SimpleDateFormat("dd/MM/yyyy")/*sdf*/));
		mv.addObject("semanaOffset", semanaOffset);
		//mv.addObject("sFechaHoy",sFechaHoy);
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
		calendar.roll(Calendar.DATE, -diasAlDomingo);
		if (semanaOffset!=null)
			//???
			{calendar.roll(Calendar.WEEK_OF_YEAR, semanaOffset);}
		else
		{semanaOffset=0;}

		return calendar;
	}
	
	public List<String> getHorasDia() {
		List<String> horas = new ArrayList<String>();	
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
	/*
	public Map<String,List<Evento>> getSemanaString(Usuario usuarioLogueado, Calendar calendar, SimpleDateFormat sdf)
	{
		Map<String,List<Evento>> semana  =new TreeMap<String,List<Evento>>();
		
		List<Evento> eventosDia;
		
		for(int i=0;i<7;i++){
			eventosDia=eventoDAO.getByAutorAndDate(usuarioLogueado, calendar.getTime() );
			for(Evento evento:eventosDia)
			{
				evento.setUsuarioActual(usuarioLogueado);
			}
	
			String sFecha = sdf.format(calendar.getTime());
			semana.put(sFecha, eventosDia);
			calendar.add(Calendar.DATE, 1);
		}
		return semana;
	}
	*/
	public Map<Date,List<Evento>> getSemanaConEventos(Usuario usuarioLogueado, Calendar calendar, SimpleDateFormat sdf){

		Map<Date,List<Evento>> semana = new TreeMap<Date,List<Evento>> (); 
		
		List<Evento> eventosDia;
		

 		for(int i=0;i<7;i++){
			eventosDia=eventoDAO.getByAutorAndDate(usuarioLogueado, calendar.getTime() );
			for(Evento evento:eventosDia)
			{
				evento.setUsuarioActual(usuarioLogueado);
			}
	
			//String sFecha = sdf.format(calendar.getTime());
			semana.put(calendar.getTime(), eventosDia);
			calendar.add(Calendar.DATE, 1);
		}
		
		
		return semana;
	}
	
	
}

