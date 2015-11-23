package ar.edu.uces.web2.tphibernate.controladores;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.EventoDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Evento;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;


@SessionAttributes("usuario")
@Controller
public class AgendaController {
	
	private EventoDAO eventoDAO;
	
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	@RequestMapping(value = "/agenda/mostrarCalendario")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario) {
		//Date fecha = new Date();
		
		//http://www.forosdelweb.com/f45/como-recorrer-fechas-374533/
		/*
		
		Date hoy=new Date();
		calendar.setTime(hoy);

		calendar.roll(Calendar.DAY, up);
		Date fechaDomingo=calendar.add(hoy, diasAlDomingo);
		calendar
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String sfecha = sdf.format(hoy);
		Date fecha=sdf.parse(sfecha, new ParsePosition(0));
		*/
		
		//SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy");
		//Date fecha=d.parse(calendar.gdate.DAY_OF_MONTH+"-"+gcalendar.MONTH+"-"+gcalendar.YEAR, new ParsePosition(0));
		
		Calendar calendar = Calendar.getInstance();
		int diaSemana= calendar.get(Calendar.DAY_OF_WEEK);
		int diasAlDomingo=diaSemana-1;
		calendar.roll(Calendar.DATE, -diasAlDomingo);
		
		Map<String,List<Evento>> semana=new TreeMap<String,List<Evento>>();
		List<Evento> eventosDia;
		
		for(int i=0;i<7;i++){
			eventosDia=eventoDAO.getByAutorAndDate(usuario, calendar.getTime() );
			for(Evento evento:eventosDia)
			{
				evento.setUsuarioActual(usuario);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sFecha = sdf.format(calendar.getTime());
			semana.put(sFecha, eventosDia);
			calendar.add(Calendar.DATE, 1);
		}
		return new ModelAndView("/views/agenda/calendario.jsp","semana", semana);
	}
}
