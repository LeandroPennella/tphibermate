package ar.edu.uces.web2.tphibernate.controladores;

import java.text.SimpleDateFormat;
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
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.form.ReunionForm;
import ar.edu.uces.web2.tphibernate.validadores.ReunionFormValidator;

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
	private ReunionFormValidator reunionValidator; 
	
	@RequestMapping(value = "/agenda/crearReunion")
	public ModelAndView crear() {
		
		ReunionForm reunionForm=new ReunionForm();
		reunionForm.setUsuarios(usuarioDAO.getAll());//TODO: aca?
		reunionForm.setSalas(salaDAO.getAll());
		/*
		posiblesParticipantes= new HashMap<String, Usuario>();
		for (Usuario usuario: usuarios) {
			posiblesParticipantes.put(Integer.toString(usuario.getId()), usuario);
		}
		mv.addObject("posiblesParticipantes", posiblesParticipantes);
				mv.addObject("salas", salas);
		mv.addObject("usuarios", usuarios);

		*/
		ModelAndView mv=new ModelAndView("/views/agenda/reunion.jsp","reunionForm", reunionForm);
		return mv;
	}
	
	@RequestMapping(value = "/agenda/agregarReunion")
	public ModelAndView save(@ModelAttribute("reunionForm") ReunionForm reunionForm, BindingResult result, @ModelAttribute("usuario") Usuario usuario) {
		Reunion reunion=null;
		this.reunionValidator.validate(reunionForm, result);
		if (result.hasErrors()) {
			//TODO: Agregarle salas y usuarios
			return new ModelAndView("/views/agenda/reunion.jsp","reunion", reunionForm);
		} else
		{reunion=this.reunionValidator.getReunion();}
		

		
		//reunion=reunionForm		//TODO: donde?
		
		reunion.setAutor(usuario);
		reunionDAO.save(reunion);
		return new ModelAndView("/views/agenda/calendario.jsp");
	}
}
