package ar.edu.uces.web2.tphibernate.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.ReunionDAO;
import ar.edu.uces.web2.tphibernate.dao.SalaDAO;
import ar.edu.uces.web2.tphibernate.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.validadores.TareaValidator;

@SessionAttributes("usuario") 

@Controller
public class ReunionController {
	private ReunionDAO reunionDAO;
	private UsuarioDAO usuarioDAO;
	private SalaDAO salaDAO;
	private Map<String, Usuario> posiblesParticipantes;
	
	@Autowired
	public void setReunionDAO (ReunionDAO reunionDAO) {
		this.reunionDAO = reunionDAO;
	}
	
	@Autowired
	public void setUsuarioDAO (UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	@Autowired
	public void setSalaDAO (SalaDAO salaDAO) {
		this.salaDAO = salaDAO;
	}
	
	@Autowired
	private TareaValidator reunionValidator; 
	
	@RequestMapping(value = "/agenda/crearReunion")
	public ModelAndView crear() {
		ModelAndView mv=new ModelAndView("/views/agenda/reunion.jsp","reunion", new Reunion());
		List<Usuario>usuarios=usuarioDAO.getAll();
		List<Sala>salas=salaDAO.getAll();
		/*
		posiblesParticipantes= new HashMap<String, Usuario>();
		for (Usuario usuario: usuarios) {
			posiblesParticipantes.put(Integer.toString(usuario.getId()), usuario);
		}
		mv.addObject("posiblesParticipantes", posiblesParticipantes);
		*/
		mv.addObject("usuarios", usuarios);
		return mv;
	}
	
	@RequestMapping(value = "/agenda/agregarReunion")
	public ModelAndView save(@ModelAttribute("reunion") Reunion reunion, BindingResult result, @ModelAttribute("usuario") Usuario usuario) {
		/*this.reunionValidator.validate(reunion, result);
		if (result.hasErrors()) {
			return new ModelAndView("/views/agenda/reunion.jsp","reunion", reunion);
		}*/
		reunion.setAutor(usuario);
		reunionDAO.save(reunion);
		return new ModelAndView("/views/agenda/calendario.jsp");
	}
}
