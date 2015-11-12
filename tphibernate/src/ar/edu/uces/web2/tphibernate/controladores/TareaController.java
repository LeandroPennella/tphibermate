package ar.edu.uces.web2.tphibernate.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.TareaDAO;

@Controller
public class TareaController {
	private TareaDAO tareaDAO;
	
	@Autowired
	public void setTareaDAO (TareaDAO tareaDAO) {
		this.tareaDAO = tareaDAO;
	}
	
	@RequestMapping(value = "/agenda/agregarTarea")
	public ModelAndView save() {
		
		return new ModelAndView("");
	}
}
