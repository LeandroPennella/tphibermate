package ar.edu.uces.web2.tphibernate.controladores;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.ReunionDAO;
import ar.edu.uces.web2.tphibernate.dao.SalaDAO;
import ar.edu.uces.web2.tphibernate.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Invitado;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.base.UsuarioInvitado;
import ar.edu.uces.web2.tphibernate.modelo.form.ReunionForm;
import ar.edu.uces.web2.tphibernate.validadores.ReunionFormValidator;

@SessionAttributes("usuarioLogueado") 

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
		// ------------------------------------------------
		//reunionForm.setUsuarios(usuarioDAO.getAll());								//TODO: reemplazar
		// ------------------------------------------------
		List<UsuarioInvitado> usuariosInvitados=new ArrayList<UsuarioInvitado>();
		List<Usuario> usuarios=usuarioDAO.getAll();
		for(Usuario usuario : usuarios)
		{
			UsuarioInvitado usuarioInvitado=new UsuarioInvitado();
			usuarioInvitado.setUsuario(usuario);
			usuarioInvitado.setAgregado(false);
			usuariosInvitados.add(usuarioInvitado);
		}
		reunionForm.setUsuariosInvitados(usuariosInvitados);
		// ------------------------------------------------
		
		reunionForm.setSalas(salaDAO.getAll());
		ModelAndView mv=new ModelAndView("/views/agenda/reunion.jsp","reunionForm", reunionForm);
		mv.addObject("reunionAModificar",new Reunion());
		return mv;
	}
	
	@RequestMapping(value = "/agenda/editarReunion")
	public ModelAndView editar(@RequestParam("idEvento")Long idReunion, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado) {		//viene del calendario con el id del evento a modificar

		Reunion reunion=reunionDAO.get(idReunion);

		
		ReunionForm reunionForm=new ReunionForm();
		reunionForm.setIdEvento(Long.toString(reunion.getId()));
		reunionForm.setTitulo(reunion.getTitulo());
		
		SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
		String sFecha=dateFormatter.format(reunion.getFecha());
		reunionForm.setFecha(sFecha);
		reunionForm.setHoraInicio(reunion.getHoraInicio());		
		reunionForm.setHoraFin(reunion.getHoraFin());
		
		reunionForm.setTemario(reunion.getTemario());
		reunionForm.setIdSala(reunion.getSala().getId());
	
		reunionForm.setSalas(salaDAO.getAll());
		
		
		reunionForm.setEstado(reunion.obtenerEstado(usuarioLogueado));
		
		//---------------------------------------------
		Set<Invitado>invitados=reunion.getInvitados();
		List<UsuarioInvitado> usuariosInvitados=new ArrayList<UsuarioInvitado>();
		
		List<Usuario> usuarios=usuarioDAO.getAll();
		for(Usuario usuarioActual : usuarios)
		{
			boolean estaInvitado=false;
			UsuarioInvitado usuarioInvitado=new UsuarioInvitado();
			usuarioInvitado.setUsuario(usuarioActual);
			//usuario actual esta entre los invitados?
			for(Invitado invitado:invitados)
			{
				if ((invitado.getUsuario().getId()==usuarioActual.getId()))
				{estaInvitado=true;}
			}

			usuarioInvitado.setAgregado(estaInvitado);
			usuariosInvitados.add(usuarioInvitado);
		}
		reunionForm.setUsuariosInvitados(usuariosInvitados);
		
		reunionForm.setInvitados(invitados);
		
		
		
		return  new ModelAndView("/views/agenda/reunion.jsp","reunionForm",reunionForm);
	}
	
	
	@RequestMapping(value = "/agenda/agregarReunion")
	public ModelAndView save(@ModelAttribute("reunionForm") ReunionForm reunionForm, BindingResult result, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado ) {

		
		this.reunionValidator.validate(reunionForm, result);
		if (result.hasErrors()) {
			//TODO: Agregarle salas y usuarios
			return new ModelAndView("/views/agenda/reunion.jsp","reunionForm", reunionForm);
		} else {
			//TODO: donde?
			Reunion reunion;
			
			
			if (!reunionForm.getIdEvento().isEmpty())  { //modificar
				reunion=reunionDAO.get(Long.parseLong(reunionForm.getIdEvento()));
				
				//reunionAModificar.
				//reunion.setId(Long.parseLong(reunionForm.getIdEvento()));
			}  else  {									//crear
				reunion=new Reunion();
				reunion.setAutor(usuarioLogueado);
			}
			
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
			if (reunionForm.getIdEstado()!=0)
			{
				listaInvitados=reunion.getInvitados();
				for(Invitado invitadoYo:listaInvitados)
				{
					if (invitadoYo.getUsuario().getId()==usuarioLogueado.getId())
					{
						//listaInvitados[invitadoYo]=
					}
				}
			}
			
			
			//TODO: agrega nuevos, no modifica
			for(Integer idInvitado:reunionForm.getIdsInvitados())//{listaParticipantes.addAll(new Usuario(){id=idParticipante}}	
			{
				if (idInvitado!=0){
				Invitado invitado=new Invitado();
				Usuario usuarioInvitado=new Usuario();
				usuarioInvitado.setId(idInvitado);
				invitado.setUsuario(usuarioInvitado);
				invitado.setAceptado(0);
				invitado.setReunion(reunion);	//TODO: SolucionInvitados
				listaInvitados.add(invitado);
				}
			}
			
			reunion.setInvitados(listaInvitados);
				
			reunionDAO.save(reunion);
			return new ModelAndView("/views/index.jsp");
		}
	}
	
	
}
