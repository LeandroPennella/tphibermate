package ar.edu.uces.web2.tphibernate.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.EventoDAO;
import ar.edu.uces.web2.tphibernate.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Evento;


@SessionAttributes("usuario")
@Controller
public class AgendaController {
	
	private EventoDAO eventoDAO;
	
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	@RequestMapping(value = "/agenda/mostrarCalendario")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response) {
		List<Evento> eventos=eventoDAO.getAll();
		return new ModelAndView("/views/agenda/calendario.jsp","eventos", eventos);
	}
}
