/*
package ar.edu.uces.web2.tphibernate.controladores;

import java.text.SimpleDateFormat;
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
public class CalendarioController {
	
	private EventoDAO eventoDAO;
	
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}



	@RequestMapping(value = "/calendario/mostrarCalendario")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado, @RequestParam(value="corrimientoSemana", required=false) Integer corrimientoSemana) {
		//Date fecha = new Date();
		
		//http://www.forosdelweb.com/f45/como-recorrer-fechas-374533/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		
		Map<Date,List<Evento>> semana=new TreeMap<Date,List<Evento>>();
		List<Evento> eventos;
		
		int diaSemana= calendar.get(Calendar.DAY_OF_WEEK);
		int diasAlDomingo=diaSemana-1;
		
		calendar.roll(Calendar.DATE, -diasAlDomingo);
		
		if (corrimientoSemana!=null)
			{calendar.roll(Calendar.WEEK_OF_YEAR, corrimientoSemana);}
		else
			{corrimientoSemana=0;}
		
		for(int i=0;i<7;i++){
			eventos=eventoDAO.getByAutorAndDate(usuarioLogueado, calendar.getTime() );
			
			for(Evento evento:eventos)
			{
				evento.setUsuarioActual(usuarioLogueado);
			}
			
			//String sFecha = sdf.format(calendar.getTime());
			semana.put(calendar.getTime(), eventos);
			calendar.add(Calendar.DATE, 1);
		}
		//String sFechaHoy=sdf.format(new Date());
		ModelAndView mv=new ModelAndView("/views/calendario/calendario.jsp","semana", semana);
		mv.addObject("corrimientoSemana", corrimientoSemana);
		//mv.addObject("sFechaHoy",sFechaHoy);
		return mv;
	}
}
*/