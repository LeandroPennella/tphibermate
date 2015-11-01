package ar.edu.uces.web2.tphibernate.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@SessionAttributes("usuario")
@Controller
public class AgendaController {
	@RequestMapping(value = "/agenda/mostrarCalendario")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/views/agenda/calendario.jsp");
	}
}
