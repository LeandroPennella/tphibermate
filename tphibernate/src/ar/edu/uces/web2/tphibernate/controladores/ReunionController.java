package ar.edu.uces.web2.tphibernate.controladores;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.InvitacionDAO;
import ar.edu.uces.web2.tphibernate.dao.ReunionDAO;
import ar.edu.uces.web2.tphibernate.dao.SalaDAO;
import ar.edu.uces.web2.tphibernate.dao.UsuarioDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Invitacion;
import ar.edu.uces.web2.tphibernate.modelo.base.Reunion;
import ar.edu.uces.web2.tphibernate.modelo.base.Sala;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;
import ar.edu.uces.web2.tphibernate.modelo.base.UsuarioInvitado;
import ar.edu.uces.web2.tphibernate.modelo.form.ReunionForm;
import ar.edu.uces.web2.tphibernate.validadores.EventoFormValidator;
import ar.edu.uces.web2.tphibernate.validadores.ReunionFormValidator;

@SessionAttributes("usuarioLogueado") 

@Controller
public class ReunionController {
	
	private ReunionDAO reunionDAO;
	private UsuarioDAO usuarioDAO;
	private SalaDAO salaDAO;
	private InvitacionDAO invitacionDAO;
	
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
	public void setInvitacionDAO (InvitacionDAO invitacionDAO) {
		this.invitacionDAO = invitacionDAO;
	}
	
	@Autowired
	private ReunionFormValidator reunionValidator; 

	@Autowired
	private EventoFormValidator eventoFormValidator; 
	
	

	@RequestMapping(value = "/calendario/listadoDeUsuarios", method = RequestMethod.GET)
	public @ResponseBody List<Usuario> listadoDeUsuarios (String parteNombre)
	{
		return usuarioDAO.find(parteNombre);			
	}
	
	
	
	@RequestMapping(value = "/calendario/crearReunion")
	public ModelAndView crear(@ModelAttribute("usuarioLogueado") Usuario usuarioLogueado) {
		
		ReunionForm reunionForm=new ReunionForm();
		/*
		 * reemplazado por autocomplete + tabla
		Map<Usuario,Integer> mapaUsuariosMasConfirmacion=new TreeMap<Usuario,Integer>();			
		List<Usuario> usuarios=usuarioDAO.getAll();													
		for(Usuario usuario: usuarios)
		{	
			if (usuario.getId()!=usuarioLogueado.getId()){
				mapaUsuariosMasConfirmacion.put(usuario, -1);
			}
		}
		reunionForm.setMapaUsuariosMasConfirmacion(mapaUsuariosMasConfirmacion);					//todos los usuarios, todos con idConfirmacion -1 (no invitado)
		*/
		reunionForm.setSalas(salaDAO.getAll());
		ModelAndView mv=new ModelAndView("/views/calendario/reunion.jsp","reunionForm", reunionForm);
		return mv;
	}
	
	@RequestMapping(value = "/calendario/editarReunion")
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
		Set<Invitacion>invitacionesHechas=reunion.getInvitaciones();
		reunionForm.setInvitaciones(invitacionesHechas);
		
				
		/*
		Map<Usuario,Integer> mapaUsuariosMasConfirmacion=new TreeMap<Usuario,Integer>();			
		List<Usuario> usuarios=usuarioDAO.getAll();//toma los usuarios en las invitaciones hechas y los marca como ya invitados
		for(Usuario usuario: usuarios)
		{	
			if (usuario.getId()!=usuarioLogueado.getId()){
				int idConfirmacion=-1;
				for(Invitacion invitacion:invitacionesHechas) 			
				{
					//TODO: Revisar
					
					
					 
					//if ((invitacion.getUsuario().getId()==usuario.getId())){
//						idConfirmacion=idConfirmacion=invitacion.getAceptado();
					//}

					
					if ((invitacion.getUsuario().getId()!=usuario.getId())){
 						idConfirmacion=-1;		
 					} else {idConfirmacion=invitacion.getAceptado();}
				}
				mapaUsuariosMasConfirmacion.put(usuario, idConfirmacion);
				
			}
		}
		reunionForm.setMapaUsuariosMasConfirmacion(mapaUsuariosMasConfirmacion);							//todos los usuarios, los invitados con idConfirmacion, los que no con -1
		*/
		
		
		
		return  new ModelAndView("/views/calendario/reunion.jsp","reunionForm",reunionForm);
	}
	
	@RequestMapping(value = "/calendario/modificarAsistenciaReunion")
	public ModelAndView modificarAsistencia(@ModelAttribute("reunionForm") ReunionForm reunionForm, BindingResult result, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado){
	
		if (!reunionForm.getIdEvento().isEmpty())  {
			if (reunionForm.getIdEstado()!=0){				
				Invitacion invitacion=invitacionDAO.getByReunionUsuario(Long.parseLong(reunionForm.getIdEvento()), usuarioLogueado.getId());
				invitacion.setAceptado(reunionForm.getIdEstado());
				invitacionDAO.update(invitacion);
				return new ModelAndView("/views/index.jsp");
			}
		}
		return new ModelAndView("/views/calendario/reunion.jsp","reunionForm", reunionForm);
	}
		
	@RequestMapping(value = "/calendario/agregarReunion")//TODO: modificar url
	public ModelAndView save(@ModelAttribute("reunionForm") ReunionForm reunionForm, BindingResult result, @ModelAttribute("usuarioLogueado") Usuario usuarioLogueado ) {

		this.eventoFormValidator.validate(reunionForm, result);
		this.reunionValidator.validate(reunionForm, result);
		if (result.hasErrors()) {		//tiene errores
			return new ModelAndView("/views/calendario/reunion.jsp","reunionForm", save_conError(reunionForm));
		} else {																							//no tiene errores
			save_sinError(reunionForm, usuarioLogueado);
			return new ModelAndView("/views/index.jsp");
		}
	}
	private ReunionForm save_conError(ReunionForm reunionForm) {
		//TODO: Agregarle salas y usuarios

		if (!reunionForm.getIdEvento().isEmpty())  { //en modificacion
			Reunion reunion=reunionDAO.get(Long.parseLong(reunionForm.getIdEvento()));
			reunionForm.setInvitaciones(reunion.getInvitaciones());
			/*
			 * en desuso por autocomplete + tablainvitaciones
			 
			Map<Usuario,Integer> mapaUsuariosMasConfirmacion=new TreeMap<Usuario,Integer>();			
			List<Usuario> usuarios=usuarioDAO.getAll();//toma los usuarios en las invitaciones hechas y los marca como ya invitados
			for(Usuario usuario: usuarios)
			{	
				if (usuario.getId()!=usuarioLogueado.getId()){
					int idConfirmacion=-1;
					for(String tokenInvitadoMasConfirmacion:reunionForm.getTokensInvitadosMasConfirmacion()) 			
					{
						StringTokenizer invitacionTokenizer=new StringTokenizer(tokenInvitadoMasConfirmacion,"|");
						int idUsuario=Integer.parseInt(invitacionTokenizer.nextToken());
						//int idConfirmacion=Integer.parseInt(invitacionTokenizer.nextToken());

						if ((idUsuario==usuario.getId())) {
							idConfirmacion=Integer.parseInt(invitacionTokenizer.nextToken());
						}
					}
					mapaUsuariosMasConfirmacion.put(usuario, idConfirmacion);			
				}
			}
			reunionForm.setMapaUsuariosMasConfirmacion(mapaUsuariosMasConfirmacion);							//todos los usuarios, los invitados con idConfirmacion, los que no con -1
			*/
		} else { 																//errores en creacion
			/*
			 * en desuso autocomplete + tablainvitaciones
			Map<Usuario,Integer> mapaUsuariosMasConfirmacion=new TreeMap<Usuario,Integer>();			
			List<Usuario> usuarios=usuarioDAO.getAll();													
			for(Usuario usuario: usuarios)
			{	
				if (usuario.getId()!=usuarioLogueado.getId()){
					mapaUsuariosMasConfirmacion.put(usuario, -1);
				}
			}
			reunionForm.setMapaUsuariosMasConfirmacion(mapaUsuariosMasConfirmacion);
			*/	
		}
		
		reunionForm.setSalas(salaDAO.getAll());
		reunionForm.setIdEvento(null);
		return reunionForm;

	}
	private void save_sinError(ReunionForm reunionForm, Usuario usuarioLogueado) {
		
		Reunion reunion;
		if (!reunionForm.getIdEvento().isEmpty())  { //modificar
			reunion=reunionDAO.get(Long.parseLong(reunionForm.getIdEvento()));
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
		
		

		//TODO: agrega nuevos, no modifica
		//for(Integer idInvitado:reunionForm.getIdsInvitados())//{listaParticipantes.addAll(new Usuario(){id=idParticipante}}	

		
		//List<String> tokensInvitadosMasConfirmacion=new ArrayList<String>();							//lista de pares idUsuario|confirmacion elegidoen el formulario//todos los usuarios posibles, y seteados los agregados //TODO: Ajax: listar solo los usuarios que no estar invitados
		Set<Invitacion>invitaciones=new HashSet<Invitacion>();
		//TODO: revisar invitados > si no estaban agregar,  si estaban y no estan, quitarlos
		/*
		for(String tokenInvitadoMasConfirmacion:reunionForm.getTokensInvitadosMasConfirmacion())
		{
			StringTokenizer invitacionTokenizer=new StringTokenizer(tokenInvitadoMasConfirmacion,"|");
			
			int idUsuario=Integer.parseInt(invitacionTokenizer.nextToken());
			int idConfirmacion=Integer.parseInt(invitacionTokenizer.nextToken());
			if (idUsuario!=0){
				Invitacion invitacion=new Invitacion();
				
				Usuario usuarioInvitado=new Usuario();
				usuarioInvitado.setId(idUsuario);
				invitacion.setUsuario(usuarioInvitado);													//TODO: Contructor de usuario
				
				if (idConfirmacion!=-1){invitacion.setAceptado(idConfirmacion);}
				invitacion.setReunion(reunion);	//TODO: SolucionInvitados
				invitaciones.add(invitacion);
			}
		}
		*/
		if (reunion.getInvitaciones()==null){
			reunion.setInvitaciones(invitaciones);
		} else {
			reunion.getInvitaciones().clear();
			reunion.getInvitaciones().addAll(invitaciones);
		}
			
		reunionDAO.save(reunion);
	}
	
	@RequestMapping(value = "/calendario/eliminarReunion")
	public ModelAndView delete(@RequestParam("idEvento")Integer idEvento){
		Reunion reunion=reunionDAO.get(idEvento);
		reunionDAO.delete(reunion);
		return new ModelAndView("/views/index.jsp");
	}

}
