package ar.edu.uces.web2.tphibernate.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.TareaDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Tarea;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.validadores.TareaValidator;

@SessionAttributes("usuario") 

@Controller
public class TareaController {
	private TareaDAO tareaDAO;
	
	@Autowired
	public void setTareaDAO (TareaDAO tareaDAO) {
		this.tareaDAO = tareaDAO;
	}
	@Autowired
	private TareaValidator tareaValidator; 
	
	@RequestMapping(value = "/agenda/crearTarea")
	public ModelAndView crear() {
		return new ModelAndView("/views/agenda/tarea.jsp","tarea", new Tarea());
	}
	
	@RequestMapping(value = "/agenda/agregarTarea")
	public ModelAndView save(@ModelAttribute("tarea") Tarea tarea, BindingResult result, @ModelAttribute("usuario") Usuario usuario) {
		
		this.tareaValidator.validate(tarea, result);
		if (result.hasErrors()) {
			return new ModelAndView("/views/agenda/tarea.jsp","tarea", tarea);
		}
		
		tarea.setAutor(usuario);
		tareaDAO.save(tarea);
		return new ModelAndView("/views/agenda/calendario.jsp");
	}
}
