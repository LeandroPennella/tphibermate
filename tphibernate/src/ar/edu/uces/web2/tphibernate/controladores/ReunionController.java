package ar.edu.uces.web2.tphibernate.controladores;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import ar.edu.uces.web2.tphibernate.modelo.base.Invitado;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
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

		this.reunionValidator.validate(reunionForm, result);
		if (result.hasErrors()) {
			//TODO: Agregarle salas y usuarios
			return new ModelAndView("/views/agenda/reunion.jsp","reunion", reunionForm);
		} else
		{
		

		
		//reunion=reunionForm		//TODO: donde?
		Reunion reunion=new Reunion();
		reunion.setTitulo(reunionForm.getTitulo());
		SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
		Date fecha=dateFormatter.parse(reunionForm.getFecha(), new ParsePosition(0));	
		reunion.setFecha(fecha);
		reunion.setHoraInicio(reunionForm.getHoraInicio());
		reunion.setHoraFin(reunionForm.getHoraFin());
		reunion.setTemario(reunionForm.getTemario());
		Sala sala=new Sala();
		sala.setId(reunionForm.getIdSala());
		reunion.setSala(sala);
		Set<Invitado>listaInvitados=new HashSet<Invitado>();
		
		for(Integer idInvitado:reunionForm.getIdsInvitados())//{listaParticipantes.addAll(new Usuario(){id=idParticipante}}	
		{
			Invitado invitado=new Invitado();
			Usuario usuarioInvitado=new Usuario();
			usuarioInvitado.setId(idInvitado);
			invitado.setUsuario(usuarioInvitado);
			invitado.setAceptado(false);
			listaInvitados.add(invitado);
		}
		
		reunion.setInvitados(listaInvitados);
		reunion.setAutor(usuario);
		reunionDAO.save(reunion);
		return new ModelAndView("/views/agenda/calendario.jsp");
		}
	}
}
