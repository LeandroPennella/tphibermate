

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
public class CalendarioController {
	
	private EventoDAO eventoDAO;
	
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	@RequestMapping(value = "/agenda/mostrarCalendarioAjax")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado, @RequestParam(value="semanaOffset", required=false) Integer semanaOffset) {
		//Date fecha = new Date();
		
		//http://www.forosdelweb.com/f45/como-recorrer-fechas-374533/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		int diaSemana= calendar.get(Calendar.DAY_OF_WEEK);
		int diasAlDomingo=diaSemana-1;
		calendar.roll(Calendar.DATE, -diasAlDomingo);
		if (semanaOffset!=null)
			{calendar.roll(Calendar.WEEK_OF_YEAR, semanaOffset);}
		else
		{semanaOffset=0;}
		Map<String,List<Evento>> semana=new TreeMap<String,List<Evento>>();
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
		String sFechaHoy=sdf.format(new Date());


		List<String> horas = new ArrayList<String>();
		horas.add("0:00");
		horas.add("0:30");
		horas.add("1:00");
		horas.add("1:30");
		horas.add("2:00");
		horas.add("2:30");
		horas.add("3:00");
		horas.add("3:30");
		horas.add("4:00");
		horas.add("4:30");
		horas.add("5:00");
		horas.add("5:30");
		horas.add("6:00");
		horas.add("6:30");
		horas.add("7:00");
		horas.add("7:30");
		horas.add("8:00");
		horas.add("8:30");
		horas.add("9:00");
		horas.add("9:30");
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
		
		ModelAndView mv=new ModelAndView("/views/agenda/calendarioAjax.jsp","semana", semana);
		mv.addObject("semanaOffset", semanaOffset);
		mv.addObject("sFechaHoy",sFechaHoy);
		mv.addObject("horas",horas);
		return mv;
	}
	

	
}

